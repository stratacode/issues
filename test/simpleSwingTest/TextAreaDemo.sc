public class TextAreaDemo extends AppFrame {
   class TestClass {
      String child = "here I am\na second line\na third line\na fourth line";
   }

   TestClass testObject = testInst;

   object testInst extends TestClass {
   }

   public object questionScrollPane extends JScrollPane {
      viewportView = questionText;
      location := SwingUtil.point(xpad, ypad);
      size := SwingUtil.dimension(windowWidth - 2*xpad, 60);

      public object questionText extends JTextArea {
         columns = 30;
         rows = 5;
         //editable = false;
         focusable = false;
         lineWrap = true;
         text := testObject.child;
         size := SwingUtil.dimension(windowWidth - 2 * xpad, 60);
      }
   }

   public void start() {
      testObject = testInst;
      testInst.child += "\nA fifth line added after";
   }
}
