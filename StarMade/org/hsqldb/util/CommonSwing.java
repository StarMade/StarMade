package org.hsqldb.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

class CommonSwing
{
  protected static String messagerHeader = "Database Manager Swing Error";
  protected static String Native = "Native";
  protected static String Java = "Java";
  protected static String Motif = "Motif";
  protected static String plaf = "plaf";
  protected static String GTK = "GTK";
  
  static Image getIcon(String paramString)
  {
    if (paramString.equalsIgnoreCase("SystemCursor")) {
      return new ImageIcon(CommonSwing.class.getResource("Hourglass.gif")).getImage();
    }
    if (paramString.equalsIgnoreCase("Frame")) {
      return new ImageIcon(CommonSwing.class.getResource("hsqldb.gif")).getImage();
    }
    if (paramString.equalsIgnoreCase("Execute")) {
      return new ImageIcon(CommonSwing.class.getResource("run_exc.gif")).getImage();
    }
    if (paramString.equalsIgnoreCase("StatusRunning")) {
      return new ImageIcon(CommonSwing.class.getResource("RedCircle.gif")).getImage();
    }
    if (paramString.equalsIgnoreCase("StatusReady")) {
      return new ImageIcon(CommonSwing.class.getResource("GreenCircle.gif")).getImage();
    }
    if (paramString.equalsIgnoreCase("Clear")) {
      return new ImageIcon(CommonSwing.class.getResource("Clear.png")).getImage();
    }
    if (paramString.equalsIgnoreCase("Problem")) {
      return new ImageIcon(CommonSwing.class.getResource("problems.gif")).getImage();
    }
    if (paramString.equalsIgnoreCase("BoldFont")) {
      return new ImageIcon(CommonSwing.class.getResource("Bold.gif")).getImage();
    }
    if (paramString.equalsIgnoreCase("ItalicFont")) {
      return new ImageIcon(CommonSwing.class.getResource("Italic.gif")).getImage();
    }
    if (paramString.equalsIgnoreCase("ColorSelection")) {
      return new ImageIcon(CommonSwing.class.getResource("Colors.png")).getImage();
    }
    if (paramString.equalsIgnoreCase("Close")) {
      return new ImageIcon(CommonSwing.class.getResource("Close.png")).getImage();
    }
    return null;
  }
  
  protected static void errorMessage(String paramString)
  {
    Object[] arrayOfObject = { "OK" };
    JOptionPane.showOptionDialog(null, paramString, messagerHeader, -1, 2, null, arrayOfObject, arrayOfObject[0]);
  }
  
  public static void errorMessage(Exception paramException)
  {
    errorMessage(paramException, false);
  }
  
  public static void errorMessage(Exception paramException, boolean paramBoolean)
  {
    Object[] arrayOfObject = { "OK" };
    JOptionPane.showOptionDialog(null, paramException, messagerHeader, -1, 0, null, arrayOfObject, arrayOfObject[0]);
    if (!paramBoolean) {
      paramException.printStackTrace();
    }
  }
  
  static void setFramePositon(JFrame paramJFrame)
  {
    Dimension localDimension1 = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension localDimension2 = paramJFrame.getSize();
    if (localDimension1.width >= 640)
    {
      paramJFrame.setLocation((localDimension1.width - localDimension2.width) / 2, (localDimension1.height - localDimension2.height) / 2);
    }
    else
    {
      paramJFrame.setLocation(0, 0);
      paramJFrame.setSize(localDimension1);
    }
  }
  
  static void setSwingLAF(Component paramComponent, String paramString)
  {
    try
    {
      if (paramString.equalsIgnoreCase(Native)) {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } else if (paramString.equalsIgnoreCase(Java)) {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } else if (paramString.equalsIgnoreCase(Motif)) {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      }
      SwingUtilities.updateComponentTreeUI(paramComponent);
      if ((paramComponent instanceof Frame)) {
        ((Frame)paramComponent).pack();
      }
    }
    catch (Exception localException)
    {
      errorMessage(localException);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.util.CommonSwing
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */