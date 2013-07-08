/*  1:   */package org.lwjgl;
/*  2:   */
/*  3:   */import java.awt.Toolkit;
/*  4:   */import java.awt.datatransfer.Clipboard;
/*  5:   */import java.awt.datatransfer.DataFlavor;
/*  6:   */import java.awt.datatransfer.Transferable;
/*  7:   */import javax.swing.JOptionPane;
/*  8:   */import javax.swing.UIManager;
/*  9:   */
/* 42:   */abstract class J2SESysImplementation
/* 43:   */  extends DefaultSysImplementation
/* 44:   */{
/* 45:   */  public long getTime()
/* 46:   */  {
/* 47:47 */    return System.currentTimeMillis();
/* 48:   */  }
/* 49:   */  
/* 50:   */  public void alert(String title, String message) {
/* 51:   */    try {
/* 52:52 */      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/* 53:   */    } catch (Exception e) {
/* 54:54 */      LWJGLUtil.log("Caught exception while setting LAF: " + e);
/* 55:   */    }
/* 56:56 */    JOptionPane.showMessageDialog(null, message, title, 2);
/* 57:   */  }
/* 58:   */  
/* 59:   */  public String getClipboard() {
/* 60:   */    try {
/* 61:61 */      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 62:62 */      Transferable transferable = clipboard.getContents(null);
/* 63:63 */      if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
/* 64:64 */        return (String)transferable.getTransferData(DataFlavor.stringFlavor);
/* 65:   */      }
/* 66:   */    } catch (Exception e) {
/* 67:67 */      LWJGLUtil.log("Exception while getting clipboard: " + e);
/* 68:   */    }
/* 69:69 */    return null;
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.J2SESysImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */