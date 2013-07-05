/*    */ package org.lwjgl;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.Clipboard;
/*    */ import java.awt.datatransfer.DataFlavor;
/*    */ import java.awt.datatransfer.Transferable;
/*    */ import javax.swing.JOptionPane;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ abstract class J2SESysImplementation extends DefaultSysImplementation
/*    */ {
/*    */   public long getTime()
/*    */   {
/* 47 */     return System.currentTimeMillis();
/*    */   }
/*    */ 
/*    */   public void alert(String title, String message) {
/*    */     try {
/* 52 */       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*    */     } catch (Exception e) {
/* 54 */       LWJGLUtil.log("Caught exception while setting LAF: " + e);
/*    */     }
/* 56 */     JOptionPane.showMessageDialog(null, message, title, 2);
/*    */   }
/*    */ 
/*    */   public String getClipboard() {
/*    */     try {
/* 61 */       Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 62 */       Transferable transferable = clipboard.getContents(null);
/* 63 */       if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor))
/* 64 */         return (String)transferable.getTransferData(DataFlavor.stringFlavor);
/*    */     }
/*    */     catch (Exception e) {
/* 67 */       LWJGLUtil.log("Exception while getting clipboard: " + e);
/*    */     }
/* 69 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.J2SESysImplementation
 * JD-Core Version:    0.6.2
 */