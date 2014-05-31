
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class TextSamplerDemo extends JPanel {
    layout = new BorderLayout();

    object textField extends JTextField {
       columns = 10;
    }

    object passwordField extends JPasswordField {
       columns = 10;
    }

    object formattedTextField extends JFormattedTextField {
       value = java.util.Calendar.getInstance().getTime();
    }

    object textFieldLabel extends JLabel {
       text = "yowza";
       labelFor = textField;
    }

    object passwordFieldLabel extends JLabel {
       text = "hmmm";
       labelFor = passwordField;
    }

    object formattedTextFieldLabel extends JLabel {
       text = "frack";
       labelFor = formattedTextField;
    }

    object actionLabel extends JLabel {
       text = "Type text in a field and press Enter.";
       border = BorderFactory.createEmptyBorder(10,0,0,0);
    }

    object textControlsPane extends JPanel {
       layout = gridbag;
       border = BorderFactory.createCompoundBorder(
                       BorderFactory.createTitledBorder("Text Fields"),
                       BorderFactory.createEmptyBorder(5,5,5,5));
       textControlsPane() {
          add(actionLabel, constraints);
       }
    }

    object textArea extends JTextArea {
       text = "This is an editable JTextArea. " +
                "A text area is a \"plain\" text component, " +
                "which means that although it can display text " +
                "in any font, all of the text is in the same font.";
        font = new Font("Serif", Font.ITALIC, 16);
        lineWrap = true;
        wrapStyleWord = true;
    }

    object gridbag extends GridBagLayout {
    }

    object constraints extends GridBagConstraints {

    }

    JLabel[] labels = {textFieldLabel, passwordFieldLabel, formattedTextFieldLabel};
    javax.swing.JTextField[] textFields = {textField, passwordField, formattedTextField};

        //JScrollPane areaScrollPane = new JScrollPane(textArea);
    object areaScrollPane extends JScrollPane {
       viewportView = textArea;
        //Create a text area.
        verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
        preferredSize = new Dimension(250, 250);
        border = BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Plain Text"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                areaScrollPane.border);
    }

    object editorPane extends JEditorPane {
       editable = false;

       editorPane() {
        java.net.URL helpURL = TextSamplerDemo.class.getResource(
                                        "TextSamplerDemoHelp.html");
        if (helpURL != null) {
            try {
                page = helpURL;
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        } else {
            System.err.println("Couldn't find file: TextSampleDemoHelp.html");
        }
      }
    }

    object editorScrollPane extends JScrollPane {
       viewportView = editorPane;
       verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
       preferredSize = new Dimension(250, 145);
       minimumSize = new Dimension(10, 10);
    }

    object textPane extends JTextPane {
        String[] initString =
                { "This is an editable JTextPane, ",            //regular
                  "another ",                                   //italic
                  "styled ",                                    //bold
                  "text ",                                      //small
                  "component, ",                                //large
                  "which supports embedded components...\n",    //regular
                  " \n",                                        //button
                  "...and embedded icons...",                   //regular
                  " ",                                          //icon
                  "\nJTextPane is a subclass of JEditorPane that " +
                    "uses a StyledEditorKit and StyledDocument, and provides " +
                    "cover methods for interacting with those objects."
                 };

        String[] initStyles =
                { "regular", "italic", "bold", "small", "large",
                  "regular", "button", "regular", "icon",
                  "regular"
                };

        textPane() {
           StyledDocument doc = styledDocument;
           addStylesToDocument(doc);

           try {
               for (int i=0; i < initString.length; i++) {
                   doc.insertString(doc.getLength(), initString[i],
                                    doc.getStyle(initStyles[i]));
               }
           } catch (BadLocationException ble) {
               System.err.println("Couldn't insert initial text into text pane.");
           }
        }
    }

    object textScrollPane extends JScrollPane {
       viewportView = textPane;
       verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
       preferredSize = new Dimension(250, 155);
       minimumSize = new Dimension(10, 10);
    }

    object splitPane extends JSplitPane {
       orientation = JSplitPane.VERTICAL_SPLIT;
       leftComponent = editorScrollPane;
       rightComponent = textScrollPane;
       oneTouchExpandable = true;
       resizeWeight = 0.5;
    }

    object rightPane extends JPanel {
       layout = new GridLayout(1,0);

       border = BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Styled Text"),
                        BorderFactory.createEmptyBorder(5,5,5,5));
       rightPane() {
          add(splitPane);
       }
    }

    object leftPane extends JPanel {
       layout = new BorderLayout();

       leftPane() {
           add(textControlsPane, BorderLayout.PAGE_START);
           add(areaScrollPane, BorderLayout.CENTER);
       }
    }

    public TextSamplerDemo() {
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);

        constraints.gridwidth = GridBagConstraints.REMAINDER; //last
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 1.0;

        add(leftPane, BorderLayout.LINE_START);
        add(rightPane, BorderLayout.LINE_END);
    }

    private void addLabelTextRows(JLabel[] labels,
                                  javax.swing.JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }

    protected void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");

        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, 10);

        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);

        s = doc.addStyle("icon", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        ImageIcon pigIcon = createImageIcon("images/Pig.gif",
                                            "a cute pig");
        if (pigIcon != null) {
            StyleConstants.setIcon(s, pigIcon);
        }

        s = doc.addStyle("button", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        ImageIcon soundIcon = createImageIcon("images/sound.gif",
                                              "sound icon");
        JButton button = new JButton();
        if (soundIcon != null) {
            button.setIcon(soundIcon);
        } else {
            button.setText("BEEP");
        }
        button.setCursor(Cursor.getDefaultCursor());
        button.setMargin(new Insets(0,0,0,0));
        StyleConstants.setComponent(s, button);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = TextSamplerDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TextSamplerDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new TextSamplerDemo());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }
}
