#set -e
set -x


rm -rf target/
sbt clean assembly && java -Ddev.ludovic.netlib.blas.nativeLibPath=/opt/openblas/lib/libopenblas_threaded.so.0 \
-Ddev.ludovic.netlib.lapack.nativeLibPath=/opt/openblas/lib/libopenblas_threaded.so.0 \
-cp target/scala-2.12/compile-netlib-emr-assembly-0.1.0.jar org.test.netlib.EntryPoint

