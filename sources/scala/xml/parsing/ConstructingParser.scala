package scala.xml.parsing ;

object ConstructingParser {
  def fromSource(inp: scala.io.Source): ConstructingParser =
    fromSource(new ConstructingHandler(), inp);

  def fromSource(theHandle: ConstructingHandler, inp: scala.io.Source): ConstructingParser  = {
    val p = new ConstructingParser() {
      val input = inp;
      override val handle = theHandle;
      def nextch = { ch = input.next; pos = input.pos; }
      override val preserveWS = true;
    };
    p.nextch;
    p
  }
}

/** an xml parser. parses XML and invokes callback methods of a MarkupHandler
 */
abstract class ConstructingParser extends MarkupParser[Node] {

  val handle = new ConstructingHandler();

  /** report a syntax error */
  def reportSyntaxError(str: String): Unit = throw FatalError(str);

  /** this method assign the next character to ch and advances in input */
  def nextch: Unit;

}