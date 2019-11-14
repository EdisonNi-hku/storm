# storm-calculation-function
This is a .md file about the core calculating function in apache storm

## The signature of this function:
> public void tupleActionFn(int taskId, TupleImpl tuple) throws Exception

## The location of this function:
> package org.apache.storm.executor.bolt;

This function is inside storm/storm-client/src/jvm/org/apache/storm/executor/bolt.

## The utility of this function inside the project:
This function overides the tupleActionFn function in Executor.java and is called after the executor of a certain task is set up and allocated to a worker. The core line of this function is:
> 234    boltObject.execute(tuple);
This line called the execute function defined by the client (Who created and submitted the topology)

## The way we used to test the utility of this function:
Go to storm/storm-client/src/jvm/org/apache/storm/executor/bolt and add flowing codes to BoltExecutor.java below the 234 line:
```
String value11 = tuple.getValue(0).toString();
LOG.info(value11);
LOG.info("compiled by myself");
```
Then complile the project throw following commands at storm/
```bash
# !/bin/bash
mvn clean package install -DskipTests=true 
&& cd storm-dist/binary 
&& mvn package -Dgpg.skip=true
&& cp ./final-package/target/apache-storm-2.2.0-SNAPSHOT.tar.gz ~/source_code/storm/compiled 
&& cd  ~/source_code/storm/compiled 
&& tar zxvf apache-storm-1.1.0.tar.gz
```
Next submit your topology to the localcluster:
> storm local your-topology.jar the-class-to-be-executed
The out put will be:
![screen shot of cmd]https://github.com/EdisonNi-hku/storm/blob/master/command.png
