/*    */ import java.awt.GridBagConstraints;
/*    */ import java.awt.GridBagLayout;
/*    */ import java.awt.Insets;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.border.TitledBorder;
/*    */ 
/*    */ public final class om extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = -6895593119848982353L;
/*    */   private JList jdField_a_of_type_JavaxSwingJList;
/*    */   private nb jdField_a_of_type_Nb;
/*    */ 
/*    */   public om(JFrame paramJFrame, ArrayList paramArrayList)
/*    */   {
/* 32 */     this.jdField_a_of_type_Nb = new nb(paramArrayList);
/* 33 */     this.jdField_a_of_type_JavaxSwingJList = new JList();
/* 34 */     setBorder(new TitledBorder(null, "Title", 4, 2, null, null));
/* 35 */     (
/* 36 */       paramArrayList = new GridBagLayout()).columnWidths = 
/* 36 */       new int[] { 0, 0, 0 };
/* 37 */     paramArrayList.rowHeights = new int[] { 0, 0, 0 };
/* 38 */     paramArrayList.columnWeights = new double[] { 1.0D, 0.0D, 4.9E-324D };
/* 39 */     paramArrayList.rowWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/* 40 */     setLayout(paramArrayList);
/*    */ 
/* 42 */     paramArrayList = new JButton("Add");
/*    */     GridBagConstraints localGridBagConstraints;
/* 43 */     (
/* 44 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 44 */       17;
/* 45 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 5);
/* 46 */     localGridBagConstraints.gridx = 0;
/* 47 */     localGridBagConstraints.gridy = 0;
/* 48 */     add(paramArrayList, localGridBagConstraints);
/*    */ 
/* 50 */     paramArrayList.addActionListener(new on(this, paramJFrame));
/*    */ 
/* 62 */     paramJFrame = new JButton("Delete");
/* 63 */     (
/* 64 */       paramArrayList = new GridBagConstraints()).insets = 
/* 64 */       new Insets(0, 0, 5, 0);
/* 65 */     paramArrayList.gridx = 1;
/* 66 */     paramArrayList.gridy = 0;
/* 67 */     add(paramJFrame, paramArrayList);
/* 68 */     paramJFrame.addActionListener(new oo(this));
/*    */ 
/* 81 */     paramJFrame = new JScrollPane();
/* 82 */     (
/* 83 */       paramArrayList = new GridBagConstraints()).gridwidth = 
/* 83 */       2;
/* 84 */     paramArrayList.insets = new Insets(0, 0, 0, 5);
/* 85 */     paramArrayList.fill = 1;
/* 86 */     paramArrayList.gridx = 0;
/* 87 */     paramArrayList.gridy = 1;
/* 88 */     add(paramJFrame, paramArrayList);
/*    */ 
/* 91 */     paramJFrame.setViewportView(this.jdField_a_of_type_JavaxSwingJList);
/*    */ 
/* 93 */     this.jdField_a_of_type_JavaxSwingJList.setModel(this.jdField_a_of_type_Nb);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     om
 * JD-Core Version:    0.6.2
 */