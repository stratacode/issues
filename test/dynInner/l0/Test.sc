import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class Test {
   public static void main(String[] args) {
      TreeSelectionListener l = new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
               System.out.println("*** we are here");
           }
    };
   }
}
