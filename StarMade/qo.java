/*    */ import java.awt.GridBagConstraints;
/*    */ import java.awt.GridBagLayout;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ public final class qo extends JPanel
/*    */ {
/*    */   private ct jdField_a_of_type_Ct;
/*    */   private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*    */ 
/*    */   public qo(ct paramct)
/*    */   {
/* 24 */     this.jdField_a_of_type_Ct = paramct;
/* 25 */     (
/* 26 */       paramct = new GridBagLayout()).columnWidths = 
/* 26 */       new int[] { 0, 0, 0 };
/* 27 */     paramct.rowHeights = new int[] { 0, 0 };
/* 28 */     paramct.columnWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
/* 29 */     paramct.rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 30 */     setLayout(paramct);
/*    */ 
/* 32 */     this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/* 33 */     this.jdField_a_of_type_JavaxSwingJTextField.addKeyListener(new qp(this));
/*    */ 
/* 42 */     (
/* 43 */       paramct = new GridBagConstraints()).weightx = 
/* 43 */       1.0D;
/* 44 */     paramct.anchor = 17;
/* 45 */     paramct.insets = new Insets(0, 0, 0, 5);
/* 46 */     paramct.fill = 2;
/* 47 */     paramct.gridx = 0;
/* 48 */     paramct.gridy = 0;
/* 49 */     add(this.jdField_a_of_type_JavaxSwingJTextField, paramct);
/* 50 */     this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/*    */ 
/* 52 */     (
/* 53 */       paramct = new JButton("Send"))
/* 53 */       .addActionListener(new qq(this));
/*    */     GridBagConstraints localGridBagConstraints;
/* 60 */     (
/* 61 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 61 */       13;
/* 62 */     localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
/* 63 */     localGridBagConstraints.gridx = 1;
/* 64 */     localGridBagConstraints.gridy = 0;
/* 65 */     add(paramct, localGridBagConstraints);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qo
 * JD-Core Version:    0.6.2
 */