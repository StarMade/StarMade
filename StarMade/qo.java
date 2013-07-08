/*  1:   */import java.awt.GridBagConstraints;
/*  2:   */import java.awt.GridBagLayout;
/*  3:   */import java.awt.Insets;
/*  4:   */import javax.swing.JButton;
/*  5:   */import javax.swing.JPanel;
/*  6:   */import javax.swing.JTextField;
/*  7:   */
/* 16:   */public final class qo
/* 17:   */  extends JPanel
/* 18:   */{
/* 19:   */  private ct jdField_a_of_type_Ct;
/* 20:   */  private JTextField jdField_a_of_type_JavaxSwingJTextField;
/* 21:   */  
/* 22:   */  public qo(ct paramct)
/* 23:   */  {
/* 24:24 */    this.jdField_a_of_type_Ct = paramct;
/* 25:   */    
/* 26:26 */    (paramct = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
/* 27:27 */    paramct.rowHeights = new int[] { 0, 0 };
/* 28:28 */    paramct.columnWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
/* 29:29 */    paramct.rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 30:30 */    setLayout(paramct);
/* 31:   */    
/* 32:32 */    this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/* 33:33 */    this.jdField_a_of_type_JavaxSwingJTextField.addKeyListener(new qp(this));
/* 34:   */    
/* 43:43 */    (paramct = new GridBagConstraints()).weightx = 1.0D;
/* 44:44 */    paramct.anchor = 17;
/* 45:45 */    paramct.insets = new Insets(0, 0, 0, 5);
/* 46:46 */    paramct.fill = 2;
/* 47:47 */    paramct.gridx = 0;
/* 48:48 */    paramct.gridy = 0;
/* 49:49 */    add(this.jdField_a_of_type_JavaxSwingJTextField, paramct);
/* 50:50 */    this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/* 51:   */    
/* 52:52 */    (
/* 53:53 */      paramct = new JButton("Send")).addActionListener(new qq(this));
/* 54:   */    
/* 57:   */    GridBagConstraints localGridBagConstraints;
/* 58:   */    
/* 61:61 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 13;
/* 62:62 */    localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
/* 63:63 */    localGridBagConstraints.gridx = 1;
/* 64:64 */    localGridBagConstraints.gridy = 0;
/* 65:65 */    add(paramct, localGridBagConstraints);
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */