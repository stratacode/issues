import org.fife.ui.autocomplete.*;

import java.util.Collections;
import sc.lang.EditorContext;
import sc.lang.CommandSCLanguage;
import javax.swing.text.JTextComponent;
import javax.swing.text.BadLocationException;

public class SCCompletionProvider extends CompletionProviderBase {
   public EditorContext ctx;

   public enum CompletionTypes {
      ExistingLayer,
      EntireFile,
      ApplicationType,
      Default
   }

   public CompletionTypes completionType = CompletionTypes.Default;

   ArrayList<String> candidates = new ArrayList<String>();
   int relPos;
   JTextComponent lastComp;
   String lastText;

   String getStatementCompleteStart(String input) {
      for (int i = input.length()-1; i >= 0; i--) {
         switch (input.charAt(i)) {
            case ';':
            case '}':
            case '{':
            case '=':
               return input.substring(i+1).trim();
         }
      }
      return input.trim();
   }

   void validate(JTextComponent comp) {
      if (ctx != null) {
         try {
            String text = comp.getDocument().getText(0, comp.getCaretPosition());

            if (lastText == null || !text.equals(lastText) || lastComp != comp) {
               candidates.clear();
               if (completionType == CompletionTypes.ExistingLayer) {
                  relPos = ctx.completeExistingLayer(text, 0, candidates);
                  lastText = text;
               }
               else if (completionType == CompletionTypes.EntireFile) {
                  // TODO: this is probably the most robust way - parse from the beginning of the file in "parse incremental" mode, then take the fragment which results
                  // and try to complete it.  Theoretically that would give us the complete context needed to complete the command.  But the partial values stuff does
                  // not populate the enclosing type property of some productions so that would need to be fixed (it seems to do one?)  anyway, for now we back up till the
                  // next statement and use the general completor.
                  //relPos = ctx.complete(text, 0, candidates, CommandSCLanguage.getCommandSCLanguage().compilationUnit);
                  lastText = text;
                  text = getStatementCompleteStart(text);
                  relPos = ctx.complete(text, 0, candidates);
                  relPos += lastText.length() - text.length(); // Compensate for text we chopped off
               }
               else if (completionType == CompletionTypes.ApplicationType) {
                  relPos = ctx.completeType(text, candidates);
                  lastText = text;
               }
               else {
                  relPos = ctx.complete(text, 0, candidates);
                  lastText = text;
               }
               lastComp = comp;
            }
         }
         catch (BadLocationException exc) {
            System.err.println("*** bad caret position in auto completion provider");
            candidates.clear();
         }
      }
      else
         candidates.clear();
   }

   protected List getCompletionsImpl(JTextComponent comp) {
      if (ctx == null) 
         return Collections.emptyList();

      validate(comp);

      ArrayList<Completion> res = new ArrayList<Completion>();
      for (int i = 0; i < candidates.size(); i++) {
          res.add(new BasicCompletion(this, candidates.get(i))); 
      }
      return res;
   }


   public List getParameterizedCompletions(JTextComponent comp) {
      return getCompletionsImpl(comp);
   }

   public List getCompletionsAt(JTextComponent comp, Point p) {
      return Collections.emptyList();
   }

   public String getAlreadyEnteredText(JTextComponent comp) {
      validate(comp);
      if (candidates.size() == 0)
         return null;
      int caret = comp.getCaretPosition();
      if (relPos == caret)
         return "";
      else if (relPos > caret || lastText == null || caret > lastText.length()) {
         System.err.println("*** invalid relPos in autoComplete: ");
         return null;
      }
      return lastText.substring(relPos, caret);
   }

}
