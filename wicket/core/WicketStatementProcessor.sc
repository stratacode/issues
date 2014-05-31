import sc.lang.StatementProcessor;
import sc.lang.AbstractInterpreter;

class WicketStatementProcessor extends StatementProcessor {
   disableRefresh = true;

   public WicketStatementProcessor() {
      AbstractInterpreter.statementProcessor = this;
   }
}
