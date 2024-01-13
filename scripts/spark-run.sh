#set -e
set -x


rm -rf target/

sbt assembly && /mnt/684C285C4C2826F2/Applications/spark-3.5.0-bin-hadoop3/bin/spark-submit \
--conf "spark.executor.extraJavaOptions=-Ddev.ludovic.netlib.blas.nativeLibPath=/opt/openblas/lib/libopenblas_threaded.so.0 -Ddev.ludovic.netlib.lapack.nativeLibPath=/opt/openblas/lib/libopenblas_threaded.so.0" \
--conf "spark.driver.extraJavaOptions=-Ddev.ludovic.netlib.blas.nativeLibPath=/opt/openblas/lib/libopenblas_threaded.so.0 -Ddev.ludovic.netlib.lapack.nativeLibPath=/opt/openblas/lib/libopenblas_threaded.so.0" \
--class org.test.netlib.EntryPoint \
target/scala-2.12/compile-netlib-emr-assembly-0.1.0.jar