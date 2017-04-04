package scopt

import scopt._

/**
  * Created by Andi on 04/04/2017.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val parser = new OptionParser[Config]("ItemSimilarity") {
      head("ItemSimilarity", "Spark")
      opt[Unit]('r', "recursive") action { (_, c) =>
        c.copy(recursive = true) } text("The input path should be searched recursively for files that match the filename pattern (optional), Default: false.")
      opt[String]('o', "output") required() action { (x, c) =>
        c.copy(output = x) } text("Output is a path for all output (required)")
      opt[String]('i', "input") required()  action { (x, c) =>
        c.copy(input = x) } text("Input is a path for input, it may be a filename or dir name. If a directory it will be searched for files matching the -p pattern. (required)")
      note("some notes.\n")
      help("help") text("prints this usage text")
    }
    // parser.parse returns Option[C]
    parser.parse(args, Config()) map { config =>
      println(config)
    } getOrElse {
      // arguments are bad, error message will have been displayed, throw exception, run away!
    }

  }

  case class Config(recursive: Boolean = false, input: String = null, output: String = null)

}
