package org.lwjgl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

abstract class J2SESysImplementation
  extends DefaultSysImplementation
{
  public long getTime()
  {
    return System.currentTimeMillis();
  }
  
  public void alert(String title, String message)
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception local_e)
    {
      LWJGLUtil.log("Caught exception while setting LAF: " + local_e);
    }
    JOptionPane.showMessageDialog(null, message, title, 2);
  }
  
  public String getClipboard()
  {
    try
    {
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      Transferable transferable = clipboard.getContents(null);
      if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
        return (String)transferable.getTransferData(DataFlavor.stringFlavor);
      }
    }
    catch (Exception clipboard)
    {
      LWJGLUtil.log("Exception while getting clipboard: " + clipboard);
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.J2SESysImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */