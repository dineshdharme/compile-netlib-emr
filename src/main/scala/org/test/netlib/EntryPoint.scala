package org.test.netlib

import breeze.linalg.DenseMatrix
import breeze.stats.regression.leastSquares
import TimeTrackerUtil.TimeTracker

object EntryPoint {


  def NaiveMultiplication(n: Int, flag: Boolean): Unit = {

    val vl = java.text.NumberFormat.getIntegerInstance.format(n)
    println(f"Naive Multipication with vector length " + vl)

    if (flag) {
      println(s"Behind the flag = ${flag}")
      import dev.ludovic.netlib.arpack.NativeARPACK
      import dev.ludovic.netlib.blas.NativeBLAS
      import dev.ludovic.netlib.lapack.NativeLAPACK
      println(s"BLAS dev.ludovic.netlib.blas.NativeBLAS: ${NativeBLAS.getInstance()}")
      println(s"LAPACK dev.ludovic.netlib.blas.NativeBLAS: ${NativeLAPACK.getInstance()}")
      println(s"ARPACK dev.ludovic.netlib.blas.NativeBLAS: ${NativeARPACK.getInstance()}")
    }

    // number of rows  > number of columns
    val sm: DenseMatrix[Double] = DenseMatrix.rand(n + 5, n)


    val features2DMatrix = sm(::, 1 to -1)
    val targetPPNRVector = sm(::, 0)
    val result = leastSquares(features2DMatrix, targetPPNRVector)
    println("Breeze Linear Equation Solver results")
    println(result)
    println(s"intercept = ${result.coefficients.toArray.slice(1 , 5).mkString("[", ",", "]")}")
    println(s"r^2 = ${result.rSquared}")


  }



  def main(args: Array[String]): Unit = {

    println("netlib native check project")
    val N_Dim = 500
    val timerLocal = new TimeTracker(Some("Library Native Loading Testing"))
    timerLocal.start("FirstCall")
    val loopNum = 5
    for (i <- 0 to loopNum){
       NaiveMultiplication(N_Dim, true)
    }
    timerLocal.stop("FirstCall")
    println(timerLocal.toString)

  }
}

