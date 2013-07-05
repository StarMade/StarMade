package org.hsqldb.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTree;

public class FontDialogSwing extends JDialog
{
  private static boolean isRunning = false;
  private static final String BACKGROUND = "Background";
  private static String defaultFont = "Dialog";
  private static final String FOREGROUND = "Foreground";
  private static JButton bgColorButton;
  private static JCheckBox ckbbold;
  private static JButton closeButton;
  private static JButton fgColorButton;
  private static JComboBox fontsComboBox;
  private static JComboBox fontSizesComboBox;
  private static final String[] fontSizes = { "8", "9", "10", "11", "12", "13", "14", "16", "18", "24", "36" };
  private static DatabaseManagerSwing fOwner;
  private static JFrame frame = new JFrame("DataBaseManagerSwing Font Selection Dialog");
  private static JCheckBox ckbitalic;

  public static void creatFontDialog(DatabaseManagerSwing paramDatabaseManagerSwing)
  {
    if (isRunning)
    {
      frame.setVisible(true);
    }
    else
    {
      CommonSwing.setSwingLAF(frame, CommonSwing.Native);
      fOwner = paramDatabaseManagerSwing;
      frame.setIconImage(CommonSwing.getIcon("Frame"));
      isRunning = true;
      frame.setSize(600, 100);
      CommonSwing.setFramePositon(frame);
      ckbitalic = new JCheckBox(new ImageIcon(CommonSwing.getIcon("ItalicFont")));
      ckbitalic.putClientProperty("is3DEnabled", Boolean.TRUE);
      ckbitalic.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent)
        {
          FontDialogSwing.setStyle();
        }
      });
      ckbbold = new JCheckBox(new ImageIcon(CommonSwing.getIcon("BoldFont")));
      ckbbold.putClientProperty("is3DEnabled", Boolean.TRUE);
      ckbbold.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent)
        {
          FontDialogSwing.setStyle();
        }
      });
      fgColorButton = new JButton("Foreground", new ImageIcon(CommonSwing.getIcon("ColorSelection")));
      fgColorButton.putClientProperty("is3DEnabled", Boolean.TRUE);
      fgColorButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent)
        {
          FontDialogSwing.setColor("Foreground");
        }
      });
      bgColorButton = new JButton("Background", new ImageIcon(CommonSwing.getIcon("ColorSelection")));
      bgColorButton.putClientProperty("is3DEnabled", Boolean.TRUE);
      bgColorButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent)
        {
          FontDialogSwing.setColor("Background");
        }
      });
      closeButton = new JButton("Close", new ImageIcon(CommonSwing.getIcon("Close")));
      closeButton.putClientProperty("is3DEnabled", Boolean.TRUE);
      closeButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent)
        {
          FontDialogSwing.frame.setVisible(false);
        }
      });
      GraphicsEnvironment localGraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String[] arrayOfString = localGraphicsEnvironment.getAvailableFontFamilyNames();
      Dimension localDimension1 = new Dimension(160, 25);
      fontsComboBox = new JComboBox(arrayOfString);
      fontsComboBox.putClientProperty("is3DEnabled", Boolean.TRUE);
      fontsComboBox.setMaximumSize(localDimension1);
      fontsComboBox.setPreferredSize(localDimension1);
      fontsComboBox.setMaximumSize(localDimension1);
      fontsComboBox.setEditable(false);
      fontsComboBox.setSelectedItem(defaultFont);
      fontsComboBox.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent paramAnonymousActionEvent)
        {
          FontDialogSwing.setFont();
        }
      });
      fontSizesComboBox = new JComboBox(fontSizes);
      Dimension localDimension2 = new Dimension(45, 25);
      fontSizesComboBox.putClientProperty("is3DEnabled", Boolean.TRUE);
      fontSizesComboBox.setMinimumSize(localDimension2);
      fontSizesComboBox.setPreferredSize(localDimension2);
      fontSizesComboBox.setMaximumSize(localDimension2);
      fontSizesComboBox.addItemListener(new ItemListener()
      {
        public void itemStateChanged(ItemEvent paramAnonymousItemEvent)
        {
          if (paramAnonymousItemEvent.getStateChange() == 1)
            FontDialogSwing.setFontSize((String)paramAnonymousItemEvent.getItem());
        }
      });
      Container localContainer = frame.getContentPane();
      localContainer.setLayout(new FlowLayout());
      localContainer.add(fontsComboBox);
      localContainer.add(fontSizesComboBox);
      localContainer.add(ckbbold);
      localContainer.add(ckbitalic);
      localContainer.add(fgColorButton);
      localContainer.add(bgColorButton);
      localContainer.add(closeButton);
      frame.pack();
      frame.setVisible(false);
    }
  }

  public static void setFont()
  {
    Font localFont1 = fOwner.txtResult.getFont();
    fOwner.txtResult.setFont(new Font(fontsComboBox.getSelectedItem().toString(), localFont1.getStyle(), localFont1.getSize()));
    Font localFont2 = fOwner.txtResult.getFont();
    fOwner.txtCommand.setFont(new Font(fontsComboBox.getSelectedItem().toString(), localFont2.getStyle(), localFont2.getSize()));
    Font localFont3 = fOwner.txtResult.getFont();
    fOwner.tTree.setFont(new Font(fontsComboBox.getSelectedItem().toString(), localFont3.getStyle(), localFont3.getSize()));
  }

  public static void setFontSize(String paramString)
  {
    Float localFloat = new Float(paramString);
    float f = localFloat.floatValue();
    Font localFont1 = fOwner.tTree.getFont().deriveFont(f);
    fOwner.tTree.setFont(localFont1);
    Font localFont2 = fOwner.txtCommand.getFont().deriveFont(f);
    fOwner.txtCommand.setFont(localFont2);
    Font localFont3 = fOwner.txtResult.getFont().deriveFont(f);
    fOwner.txtResult.setFont(localFont3);
  }

  public static void setStyle()
  {
    int i = 0;
    if (ckbbold.isSelected())
      i |= 1;
    if (ckbitalic.isSelected())
      i |= 2;
    fOwner.tTree.setFont(fOwner.txtCommand.getFont().deriveFont(i));
    fOwner.txtCommand.setFont(fOwner.txtCommand.getFont().deriveFont(i));
    fOwner.txtResult.setFont(fOwner.txtResult.getFont().deriveFont(i));
  }

  public static void setColor(String paramString)
  {
    Color localColor;
    if (paramString.equals("Background"))
    {
      localColor = JColorChooser.showDialog(null, "DataBaseManagerSwing Choose Background Color", fOwner.txtResult.getBackground());
      if (localColor != null)
      {
        bgColorButton.setBackground(localColor);
        fOwner.txtCommand.setBackground(localColor);
        fOwner.txtResult.setBackground(localColor);
      }
    }
    else
    {
      localColor = JColorChooser.showDialog(null, "DataBaseManagerSwing Choose Foreground Color", fOwner.txtResult.getForeground());
      if (localColor != null)
      {
        fgColorButton.setBackground(localColor);
        fOwner.txtCommand.setForeground(localColor);
        fOwner.txtResult.setForeground(localColor);
      }
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.util.FontDialogSwing
 * JD-Core Version:    0.6.2
 */