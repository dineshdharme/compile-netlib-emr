package org.test.netlib

import java.io.Serializable
import scala.collection.mutable.{TreeMap => MutableTreeMap}


object TimeTrackerUtil {


  class TimeTracker(name: Option[String] = None, printFormat: String = "min") extends Serializable {

    assert(printFormat.equals("min") || printFormat.equals("sec") || printFormat.equals("hr"),
      "Invalid printFormat given to TimeTracker. Must be 'min', 'sec', or 'hr'")

    private val starts: MutableTreeMap[String, Long] = new MutableTreeMap[String, Long]()

    private val totals: MutableTreeMap[String, Long] = new MutableTreeMap[String, Long]()

    /**
     * Starts a new timer, or re-starts a stopped timer.
     */
    def start(timerLabel: String): Unit = {
      val currentTime = System.nanoTime()
      if (starts.contains(timerLabel)) {
        throw new RuntimeException(s"TimeTracker.start(timerLabel) called again on" +
          s" timerLabel = $timerLabel before that timer was stopped.")
      }
      starts(timerLabel) = currentTime
    }

    /**
     * Stops a timer and returns the elapsed time in seconds.
     */
    def stop(timerLabel: String): Double = {
      val currentTime = System.nanoTime()
      if (!starts.contains(timerLabel)) {
        throw new RuntimeException(s"TimeTracker.stop(timerLabel) called on" +
          s" timerLabel = $timerLabel, but that timer was not started.")
      }
      val elapsed = currentTime - starts(timerLabel)
      starts.remove(timerLabel)
      if (totals.contains(timerLabel)) {
        totals(timerLabel) += elapsed
      } else {
        totals(timerLabel) = elapsed
      }
      elapsed / 1e9
    }

    /**
     * Times a block of code
     */
    def time[R](timerLabel: String, block: => R): R = {
      start(timerLabel)
      val r = block
      stop(timerLabel)
      r
    }

    def printFormat(label: String, elapsed: Double): String = {
      val time = printFormat match {
        case "min" => s"${elapsed / 6e10} min"
        case "sec" => s"${elapsed / 1e9} sec"
        case "hr" => s"${elapsed / 36e11} hr"
      }
      s"  $label: $time"
    }

    /**
     * Print all timing results in seconds.
     */
    override def toString: String = {
      (
        Seq(s"${name.getOrElse("TimeTracker")}:") ++ totals.map { case (label, elapsed) => printFormat(label, elapsed) }
        ).mkString("\n")
    }
  }


}
