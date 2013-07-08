/*   1:    */import java.awt.BorderLayout;
/*   2:    */import java.awt.Component;
/*   3:    */import java.awt.Container;
/*   4:    */import java.awt.FlowLayout;
/*   5:    */import java.awt.GridBagConstraints;
/*   6:    */import java.awt.GridBagLayout;
/*   7:    */import java.awt.Insets;
/*   8:    */import java.awt.LayoutManager;
/*   9:    */import javax.swing.JButton;
/*  10:    */import javax.swing.JDialog;
/*  11:    */import javax.swing.JFrame;
/*  12:    */import javax.swing.JLabel;
/*  13:    */import javax.swing.JPanel;
/*  14:    */import javax.swing.JRootPane;
/*  15:    */import javax.swing.JTextField;
/*  16:    */import javax.swing.border.EmptyBorder;
/*  17:    */
/*  23:    */public final class qb
/*  24:    */  extends JDialog
/*  25:    */{
/*  26:    */  private static final long serialVersionUID = -490690742906601152L;
/*  27: 27 */  private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*  28:    */  
/*  29:    */  private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*  30:    */  
/*  31:    */  private JTextField b;
/*  32:    */  
/*  33:    */  private JTextField c;
/*  34:    */  
/*  35:    */  public qb(JFrame paramJFrame, qa paramqa, qe paramqe)
/*  36:    */  {
/*  37: 37 */    super(paramJFrame, true);
/*  38: 38 */    setTitle("Create Connection");
/*  39: 39 */    setBounds(100, 100, 320, 166);
/*  40: 40 */    getContentPane().setLayout(new BorderLayout());
/*  41: 41 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  42: 42 */    getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  43:    */    
/*  44: 44 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
/*  45: 45 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0 };
/*  46: 46 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/*  47: 47 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  48: 48 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*  49:    */    
/*  50: 50 */    Object localObject1 = new JLabel("Login Name");
/*  51:    */    Object localObject2;
/*  52: 52 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
/*  53: 53 */    ((GridBagConstraints)localObject2).anchor = 17;
/*  54: 54 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  55: 55 */    ((GridBagConstraints)localObject2).gridy = 0;
/*  56: 56 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*  57:    */    
/*  59: 59 */    this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/*  60:    */    
/*  61: 61 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  62: 62 */    ((GridBagConstraints)localObject1).fill = 2;
/*  63: 63 */    ((GridBagConstraints)localObject1).gridx = 1;
/*  64: 64 */    ((GridBagConstraints)localObject1).gridy = 0;
/*  65: 65 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJTextField, localObject1);
/*  66: 66 */    this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/*  67:    */    
/*  69: 69 */    localObject1 = new JLabel("Host URL");
/*  70:    */    
/*  71: 71 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/*  72: 72 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/*  73: 73 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  74: 74 */    ((GridBagConstraints)localObject2).gridy = 1;
/*  75: 75 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*  76:    */    
/*  78: 78 */    this.b = new JTextField();
/*  79:    */    
/*  80: 80 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  81: 81 */    ((GridBagConstraints)localObject1).fill = 2;
/*  82: 82 */    ((GridBagConstraints)localObject1).gridx = 1;
/*  83: 83 */    ((GridBagConstraints)localObject1).gridy = 1;
/*  84: 84 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.b, localObject1);
/*  85: 85 */    this.b.setColumns(10);
/*  86:    */    
/*  88: 88 */    (
/*  89: 89 */      localObject1 = new JLabel("Port")).setHorizontalAlignment(2);
/*  90:    */    
/*  91: 91 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/*  92: 92 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 0, 5);
/*  93: 93 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  94: 94 */    ((GridBagConstraints)localObject2).gridy = 2;
/*  95: 95 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*  96:    */    
/*  98: 98 */    this.c = new JTextField();
/*  99:    */    
/* 100:100 */    (localObject1 = new GridBagConstraints()).fill = 2;
/* 101:101 */    ((GridBagConstraints)localObject1).gridx = 1;
/* 102:102 */    ((GridBagConstraints)localObject1).gridy = 2;
/* 103:103 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.c, localObject1);
/* 104:104 */    this.c.setColumns(10);
/* 105:105 */    this.c.setText("4242");
/* 106:    */    
/* 108:108 */    (
/* 109:109 */      localObject1 = new JPanel()).setLayout(new FlowLayout(2));
/* 110:110 */    getContentPane().add((Component)localObject1, "South");
/* 111:    */    
/* 112:112 */    (
/* 113:113 */      localObject2 = new JButton("OK")).addActionListener(new qc(this, paramqe, paramqa, paramJFrame));
/* 114:    */    
/* 140:140 */    ((JButton)localObject2).setActionCommand("OK");
/* 141:141 */    ((JPanel)localObject1).add((Component)localObject2);
/* 142:142 */    getRootPane().setDefaultButton((JButton)localObject2);
/* 143:    */    
/* 145:145 */    (
/* 146:146 */      localObject2 = new JButton("Cancel")).addActionListener(new qd(this));
/* 147:    */    
/* 151:151 */    ((JButton)localObject2).setActionCommand("Cancel");
/* 152:152 */    ((JPanel)localObject1).add((Component)localObject2);
/* 153:    */  }
/* 154:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */