import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

public class SentenceSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;
    private String[] sentences = {
            "my name is soul",
            "im a boy",
            "i have a dog",
            "my dog has fleas",
            "my girl friend is beautiful"
    };

    private int index=0;

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }


    public void nextTuple() {

        this.collector.emit(new Values(sentences[index]));
        index++;
        if (index>=sentences.length) {
            index=0;
        }
        Utils.sleep(1);
    }


    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("sentence"));
    }

}