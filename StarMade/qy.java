/*  1:   */import java.awt.GridBagConstraints;
/*  2:   */import java.awt.GridBagLayout;
/*  3:   */import java.util.ArrayList;
/*  4:   */import javax.swing.JPanel;
/*  5:   */import javax.swing.JScrollPane;
/*  6:   */import javax.swing.JTable;
/*  7:   */import org.schema.schine.network.objects.Sendable;
/*  8:   */
/* 18:   */public class qy
/* 19:   */  extends JPanel
/* 20:   */{
/* 21:   */  private static final long serialVersionUID = 6724191831876425314L;
/* 22:   */  private rZ jdField_a_of_type_RZ;
/* 23:   */  private JTable jdField_a_of_type_JavaxSwingJTable;
/* 24:   */  public final Sendable a;
/* 25:   */  
/* 26:   */  public qy(Sendable paramSendable)
/* 27:   */  {
/* 28:28 */    this.jdField_a_of_type_OrgSchemaSchineNetworkObjectsSendable = paramSendable;
/* 29:   */    
/* 30:30 */    (paramSendable = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 31:31 */    paramSendable.rowHeights = new int[] { 0, 0 };
/* 32:32 */    paramSendable.columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 33:33 */    paramSendable.rowWeights = new double[] { 1.0D, 4.9E-324D };
/* 34:34 */    setLayout(paramSendable);
/* 35:   */    
/* 36:36 */    paramSendable = new JScrollPane();
/* 37:   */    GridBagConstraints localGridBagConstraints;
/* 38:38 */    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
/* 39:39 */    localGridBagConstraints.gridx = 0;
/* 40:40 */    localGridBagConstraints.gridy = 0;
/* 41:41 */    add(paramSendable, localGridBagConstraints);
/* 42:   */    
/* 43:43 */    this.jdField_a_of_type_RZ = new rZ();
/* 44:   */    
/* 45:45 */    this.jdField_a_of_type_JavaxSwingJTable = new JTable(this.jdField_a_of_type_RZ);
/* 46:46 */    this.jdField_a_of_type_JavaxSwingJTable.setDefaultRenderer(rY.class, new sc());
/* 47:47 */    this.jdField_a_of_type_JavaxSwingJTable.setDefaultRenderer(String.class, new sc());
/* 48:48 */    this.jdField_a_of_type_JavaxSwingJTable.setDefaultEditor(rY.class, new sb());
/* 49:   */    
/* 50:50 */    a();
/* 51:51 */    paramSendable.setViewportView(this.jdField_a_of_type_JavaxSwingJTable);
/* 52:   */  }
/* 53:   */  
/* 54:   */  public void a() {
/* 55:55 */    a(new qz(this, "ID"));
/* 56:   */    
/* 62:62 */    a(new qA(this, "CLASS"));
/* 63:   */  }
/* 64:   */  
/* 77:   */  public final void a(rY paramrY)
/* 78:   */  {
/* 79:79 */    this.jdField_a_of_type_RZ.a().add(paramrY);
/* 80:80 */    this.jdField_a_of_type_JavaxSwingJTable.invalidate();
/* 81:81 */    this.jdField_a_of_type_JavaxSwingJTable.validate();
/* 82:   */  }
/* 83:   */  
/* 84:84 */  public final void b() { this.jdField_a_of_type_RZ.a(); }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */