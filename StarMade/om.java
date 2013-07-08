/*  1:   */import java.awt.GridBagConstraints;
/*  2:   */import java.awt.GridBagLayout;
/*  3:   */import java.awt.Insets;
/*  4:   */import java.util.ArrayList;
/*  5:   */import javax.swing.JButton;
/*  6:   */import javax.swing.JFrame;
/*  7:   */import javax.swing.JList;
/*  8:   */import javax.swing.JPanel;
/*  9:   */import javax.swing.JScrollPane;
/* 10:   */import javax.swing.border.TitledBorder;
/* 11:   */
/* 23:   */public final class om
/* 24:   */  extends JPanel
/* 25:   */{
/* 26:   */  private static final long serialVersionUID = -6895593119848982353L;
/* 27:   */  private JList jdField_a_of_type_JavaxSwingJList;
/* 28:   */  private nb jdField_a_of_type_Nb;
/* 29:   */  
/* 30:   */  public om(JFrame paramJFrame, ArrayList paramArrayList)
/* 31:   */  {
/* 32:32 */    this.jdField_a_of_type_Nb = new nb(paramArrayList);
/* 33:33 */    this.jdField_a_of_type_JavaxSwingJList = new JList();
/* 34:34 */    setBorder(new TitledBorder(null, "Title", 4, 2, null, null));
/* 35:   */    
/* 36:36 */    (paramArrayList = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
/* 37:37 */    paramArrayList.rowHeights = new int[] { 0, 0, 0 };
/* 38:38 */    paramArrayList.columnWeights = new double[] { 1.0D, 0.0D, 4.9E-324D };
/* 39:39 */    paramArrayList.rowWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/* 40:40 */    setLayout(paramArrayList);
/* 41:   */    
/* 42:42 */    paramArrayList = new JButton("Add");
/* 43:   */    GridBagConstraints localGridBagConstraints;
/* 44:44 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/* 45:45 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 5);
/* 46:46 */    localGridBagConstraints.gridx = 0;
/* 47:47 */    localGridBagConstraints.gridy = 0;
/* 48:48 */    add(paramArrayList, localGridBagConstraints);
/* 49:   */    
/* 50:50 */    paramArrayList.addActionListener(new on(this, paramJFrame));
/* 51:   */    
/* 62:62 */    paramJFrame = new JButton("Delete");
/* 63:   */    
/* 64:64 */    (paramArrayList = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 65:65 */    paramArrayList.gridx = 1;
/* 66:66 */    paramArrayList.gridy = 0;
/* 67:67 */    add(paramJFrame, paramArrayList);
/* 68:68 */    paramJFrame.addActionListener(new oo(this));
/* 69:   */    
/* 81:81 */    paramJFrame = new JScrollPane();
/* 82:   */    
/* 83:83 */    (paramArrayList = new GridBagConstraints()).gridwidth = 2;
/* 84:84 */    paramArrayList.insets = new Insets(0, 0, 0, 5);
/* 85:85 */    paramArrayList.fill = 1;
/* 86:86 */    paramArrayList.gridx = 0;
/* 87:87 */    paramArrayList.gridy = 1;
/* 88:88 */    add(paramJFrame, paramArrayList);
/* 89:   */    
/* 91:91 */    paramJFrame.setViewportView(this.jdField_a_of_type_JavaxSwingJList);
/* 92:   */    
/* 93:93 */    this.jdField_a_of_type_JavaxSwingJList.setModel(this.jdField_a_of_type_Nb);
/* 94:   */  }
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     om
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */