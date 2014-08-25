package sc.swing;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Cursor;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

import java.util.Enumeration;

import javax.swing.text.StyledDocument;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.BorderFactory;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.AbstractButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import sc.swing.*;

swing.core extends swing.meta, util {
   codeType = sc.layer.CodeType.Framework;
   codeFunction = sc.layer.CodeFunction.UI;

   compiledOnly = true; // No real reason to make this layer dynamic ever and something about Main.sc seems to make it not work

   public void start() {
      sc.layer.LayeredSystem system = getLayeredSystem();
      sc.layer.LayerFileProcessor resourceFileProcessor = new sc.layer.LayerFileProcessor();

      resourceFileProcessor.definedInLayer = this;    
      resourceFileProcessor.prependLayerPackage = true;
      resourceFileProcessor.useSrcDir = true;

      system.registerFileProcessor("png", resourceFileProcessor, this);
      system.registerFileProcessor("gif", resourceFileProcessor, this);
      system.registerFileProcessor("jpg", resourceFileProcessor, this);

      sc.lang.DefaultAnnotationProcessor mainInitProc = new sc.lang.DefaultAnnotationProcessor();
      mainInitProc.typeGroupName = "mainInit";
      mainInitProc.validOnField = false;
      mainInitProc.validOnObject = true;
      mainInitProc.createOnStartup = true;

      system.registerAnnotationProcessor("sc.swing.MainInit", mainInitProc);

      if (activated) {
         system.addTypeGroupDependency("Main.sc", "sc.swing.Main", "mainInit");
      }
   }

}

