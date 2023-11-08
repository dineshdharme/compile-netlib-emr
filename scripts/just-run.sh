




sbt assembly && /usr/bin/spark-submit \
--class org.test.netlib.EntryPoint \
target/scala-2.12/compile-netlib-emr-assembly-0.1.0-SNAPSHOT.jar


sbt assembly && /mnt/684C285C4C2826F2/opt/spark-3.4.1-bin-hadoop3/bin/spark-submit \
--class org.test.netlib.EntryPoint \
target/scala-2.12/compile-netlib-emr-assembly-0.1.0.jar


sbt assembly && /path/to/spark/spark-3.4.1-bin-hadoop3/bin/spark-submit \
--class org.test.netlib.EntryPoint \
target/scala-2.12/compile-netlib-emr-assembly-0.1.0.jar



