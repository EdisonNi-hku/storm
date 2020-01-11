import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

/**
 * 实现单词计数topology
 *
 */
public class App {
    private static final String SENTENCE_SPOUT_ID = "sentence-spout";
    private static final String SPLIT_BOLT_ID = "split-bolt";
    private static final String COUNT_BOLT_ID = "count-bolt";
    private static final String REPORT_BOLT_ID = "report-bolt";
    private static final String TOPOLOGY_NAME = "word-count-topology";

    public static void main( String[] args ) throws Exception{

        SentenceSpout spout = new SentenceSpout();
        SplitSentenceBolt splitBolt = new SplitSentenceBolt();
        WordCountBolt countBolt = new WordCountBolt();
        ReportBolt reportBolt = new ReportBolt();

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout(SENTENCE_SPOUT_ID, spout,2);

        builder.setBolt(SPLIT_BOLT_ID, splitBolt,2).setNumTasks(4).shuffleGrouping(SENTENCE_SPOUT_ID);


        builder.setBolt(COUNT_BOLT_ID, countBolt,4).fieldsGrouping( SPLIT_BOLT_ID, new Fields("word"));


        builder.setBolt(REPORT_BOLT_ID, reportBolt).globalGrouping(COUNT_BOLT_ID);


        Config config = new Config();
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(TOPOLOGY_NAME, config, builder.createTopology());

        Utils.sleep(10000);
        cluster.killTopology(TOPOLOGY_NAME);
        cluster.shutdown();

    }
}