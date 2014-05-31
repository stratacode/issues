import sc.bind.Bind;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TForm  extends JFrame {

   //public int xpad := addressPanel.testSlider.value, ypad = 5;
   //public static int gap = 20;

   public object addressPanel extends JPanel {

      object address1Label extends JLabel {
      }
      object address1Field extends JTextField {
         location := SwingUtil.point(1 + ((int) address1Label.preferredSize.width) + 2, 3);
      }

   }

   defaultCloseOperation = JFrame.EXIT_ON_CLOSE;

    public TForm() {
        pack();
        setSize(300,300);
        setVisible(true);
    }

    public static void sendDelayedEvent(final int eventType, final Object obj, final sc.type.IBeanMapper prop) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               sc.bind.Bind.sendEvent(eventType, obj, prop);
            }
        });
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		new TForm();
            }
        });
    }

}
