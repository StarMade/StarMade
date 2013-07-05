/*    */ import java.awt.GridBagConstraints;
/*    */ import java.awt.GridBagLayout;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTable;
/*    */ import org.schema.schine.network.objects.Sendable;
/*    */ 
/*    */ public class qy extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = 6724191831876425314L;
/*    */   private rZ jdField_a_of_type_RZ;
/*    */   private JTable jdField_a_of_type_JavaxSwingJTable;
/*    */   public final Sendable a;
/*    */ 
/*    */   public qy(Sendable paramSendable)
/*    */   {
/* 28 */     this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable = paramSendable;
/* 29 */     (
/* 30 */       paramSendable = new GridBagLayout()).columnWidths = 
/* 30 */       new int[] { 0, 0 };
/* 31 */     paramSendable.rowHeights = new int[] { 0, 0 };
/* 32 */     paramSendable.columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 33 */     paramSendable.rowWeights = new double[] { 1.0D, 4.9E-324D };
/* 34 */     setLayout(paramSendable);
/*    */ 
/* 36 */     paramSendable = new JScrollPane();
/*    */     GridBagConstraints localGridBagConstraints;
/* 37 */     (
/* 38 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 38 */       1;
/* 39 */     localGridBagConstraints.gridx = 0;
/* 40 */     localGridBagConstraints.gridy = 0;
/* 41 */     add(paramSendable, localGridBagConstraints);
/*    */ 
/* 43 */     this.jdField_a_of_type_RZ = new rZ();
/*    */ 
/* 45 */     this.jdField_a_of_type_JavaxSwingJTable = new JTable(this.jdField_a_of_type_RZ);
/* 46 */     this.jdField_a_of_type_JavaxSwingJTable.setDefaultRenderer(rY.class, new sc());
/* 47 */     this.jdField_a_of_type_JavaxSwingJTable.setDefaultRenderer(String.class, new sc());
/* 48 */     this.jdField_a_of_type_JavaxSwingJTable.setDefaultEditor(rY.class, new sb());
/*    */ 
/* 50 */     a();
/* 51 */     paramSendable.setViewportView(this.jdField_a_of_type_JavaxSwingJTable);
/*    */   }
/*    */ 
/*    */   public void a() {
/* 55 */     a(new qz(this, "ID"));
/*    */ 
/* 62 */     a(new qA(this, "CLASS"));
/*    */   }
/*    */ 
/*    */   public final void a(rY paramrY)
/*    */   {
/* 79 */     this.jdField_a_of_type_RZ.a().add(paramrY);
/* 80 */     this.jdField_a_of_type_JavaxSwingJTable.invalidate();
/* 81 */     this.jdField_a_of_type_JavaxSwingJTable.validate();
/*    */   }
/*    */   public final void b() {
/* 84 */     this.jdField_a_of_type_RZ.a();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qy
 * JD-Core Version:    0.6.2
 */