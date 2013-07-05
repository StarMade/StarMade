/*    */ package org.schema.game.common.gui;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Container;
/*    */ import java.awt.FlowLayout;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Observable;
/*    */ import java.util.Observer;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.border.EmptyBorder;
/*    */ import pq;
/*    */ 
/*    */ public class ConnectionDialog extends JDialog
/*    */   implements Observer
/*    */ {
/*    */   private static final long serialVersionUID = 418123465094998578L;
/* 34 */   private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*    */   private JLabel jdField_a_of_type_JavaxSwingJLabel;
/*    */   private boolean b;
/*    */   public boolean a;
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/*    */     try
/*    */     {
/* 27 */       (
/* 28 */         paramArrayOfString = new ConnectionDialog())
/* 28 */         .setDefaultCloseOperation(2);
/* 29 */       paramArrayOfString.setVisible(true);
/*    */       return;
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 32 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public ConnectionDialog()
/*    */   {
/* 37 */     this.jdField_a_of_type_Boolean = true;
/*    */ 
/* 43 */     setAlwaysOnTop(true);
/* 44 */     setDefaultCloseOperation(2);
/* 45 */     setTitle("StarMade Connector");
/* 46 */     setBounds(100, 100, 411, 105);
/* 47 */     getContentPane().setLayout(new BorderLayout());
/* 48 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout(new FlowLayout());
/* 49 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 50 */     getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*    */ 
/* 52 */     this.jdField_a_of_type_JavaxSwingJLabel = new JLabel("Connecting");
/* 53 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJLabel);
/*    */     JPanel localJPanel;
/* 56 */     (
/* 57 */       localJPanel = new JPanel())
/* 57 */       .setLayout(new FlowLayout(2));
/* 58 */     getContentPane().add(localJPanel, "South");
/*    */     JButton localJButton;
/* 60 */     (
/* 61 */       localJButton = new JButton("Cancel"))
/* 61 */       .addActionListener(new pq());
/*    */ 
/* 68 */     localJButton.setActionCommand("Cancel");
/* 69 */     localJPanel.add(localJButton);
/*    */   }
/*    */ 
/*    */   public void dispose()
/*    */   {
/* 79 */     super.dispose();
/* 80 */     if ((!this.b) && (this.jdField_a_of_type_Boolean)) {
/* 81 */       System.err.println("Connection Dialog Terminated -> EXIT 0");
/* 82 */       System.exit(0);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void update(Observable paramObservable, Object paramObject)
/*    */   {
/* 89 */     if ("DONE".equals(paramObject)) {
/* 90 */       this.b = true;
/* 91 */       dispose(); return;
/*    */     }
/* 93 */     this.jdField_a_of_type_JavaxSwingJLabel.setText(paramObject.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.gui.ConnectionDialog
 * JD-Core Version:    0.6.2
 */