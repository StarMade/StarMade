/*    */ import java.awt.BorderLayout;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTextArea;
/*    */ import javax.swing.text.DefaultCaret;
/*    */ 
/*    */ public class qr extends JPanel
/*    */   implements cr
/*    */ {
/*    */   private JTextArea jdField_a_of_type_JavaxSwingJTextArea;
/*    */ 
/*    */   public qr(ct paramct)
/*    */   {
/* 20 */     setLayout(new BorderLayout(0, 0));
/*    */ 
/* 23 */     JScrollPane localJScrollPane = new JScrollPane();
/* 24 */     add(localJScrollPane, "Center");
/*    */ 
/* 26 */     this.jdField_a_of_type_JavaxSwingJTextArea = new JTextArea();
/* 27 */     ((DefaultCaret)this.jdField_a_of_type_JavaxSwingJTextArea.getCaret())
/* 28 */       .setUpdatePolicy(2);
/* 29 */     localJScrollPane.setViewportView(this.jdField_a_of_type_JavaxSwingJTextArea);
/* 30 */     localJScrollPane.setAutoscrolls(true);
/* 31 */     if ((!jdField_a_of_type_Boolean) && (paramct == null)) throw new AssertionError();
/* 32 */     paramct.e().add(this);
/*    */   }
/*    */ 
/*    */   public final void a(String paramString)
/*    */   {
/* 38 */     this.jdField_a_of_type_JavaxSwingJTextArea.append(paramString + "\n");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qr
 * JD-Core Version:    0.6.2
 */