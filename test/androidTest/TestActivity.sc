public class TestActivity extends Activity {
   String test;
   String error;

   object mainView extends LinearLayout {
      orientation = VERTICAL;   

      object testLabel extends CTextView {
	 text := "TEST: " + test;
      }
      object errorLabel extends CTextView {
	 text := "ERROR: " + TestActivity.this.error;
      }

      String quizName;
      object quizNameField extends CEditText {
         singleLine = true;
         textString :=: quizName;
         layoutParams = new LinearLayout.LayoutParams(FILL_PARENT, WRAP_CONTENT);
      }

      object buttons extends LinearLayout {
	 orientation = HORIZONTAL;   
      
	 object setTestButton extends CButton {
	    text = "Set test";
	    clickCount =: gotoSetTest();
	    layoutParams = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
	 }
	 object setErrorButton extends CButton {
	    text = "Set error";
	    clickCount =: gotoSetError();
	    layoutParams = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
	 }
      }
   }

   void gotoSetTest() {
      test = "This is a test string";
   }
   void gotoSetError() {
      error = "This is an error string";
   }
}
