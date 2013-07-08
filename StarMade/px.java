/*   1:    */import java.awt.BorderLayout;
/*   2:    */import java.awt.Color;
/*   3:    */import java.awt.Component;
/*   4:    */import java.awt.Container;
/*   5:    */import java.awt.FlowLayout;
/*   6:    */import java.awt.Font;
/*   7:    */import java.awt.GridBagConstraints;
/*   8:    */import java.awt.GridBagLayout;
/*   9:    */import java.awt.Insets;
/*  10:    */import java.awt.LayoutManager;
/*  11:    */import javax.swing.JButton;
/*  12:    */import javax.swing.JCheckBox;
/*  13:    */import javax.swing.JDialog;
/*  14:    */import javax.swing.JLabel;
/*  15:    */import javax.swing.JPanel;
/*  16:    */import javax.swing.JPasswordField;
/*  17:    */import javax.swing.JRootPane;
/*  18:    */import javax.swing.JTextField;
/*  19:    */import javax.swing.border.EmptyBorder;
/*  20:    */import javax.swing.border.EtchedBorder;
/*  21:    */
/*  33:    */public final class px
/*  34:    */  extends JDialog
/*  35:    */{
/*  36:    */  private static final long serialVersionUID = 7116287916594864261L;
/*  37: 37 */  private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*  38:    */  
/*  39:    */  private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*  40:    */  
/*  41:    */  private JPasswordField jdField_a_of_type_JavaxSwingJPasswordField;
/*  42:    */  
/*  43:    */  private JCheckBox jdField_a_of_type_JavaxSwingJCheckBox;
/*  44:    */  
/*  46:    */  public px()
/*  47:    */  {
/*  48: 48 */    setTitle("Login to Star-Made.org");
/*  49: 49 */    setBounds(100, 100, 470, 275);
/*  50: 50 */    getContentPane().setLayout(new BorderLayout());
/*  51: 51 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  52: 52 */    getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  53:    */    
/*  54: 54 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0 };
/*  55: 55 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 20, 0, 0, 0, 0 };
/*  56: 56 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D };
/*  57: 57 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*  58: 58 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*  59:    */    
/*  60: 60 */    Object localObject1 = new JLabel("Please enter your www.Star-Made.org credentials");
/*  61:    */    
/*  62: 62 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  63: 63 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  64: 64 */    ((GridBagConstraints)localObject2).gridy = 0;
/*  65: 65 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*  66:    */    
/*  68: 68 */    localObject1 = new JLabel("If you don't have an Account yet, please go to www.star-made.org to create one");
/*  69:    */    
/*  70: 70 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 20, 0);
/*  71: 71 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  72: 72 */    ((GridBagConstraints)localObject2).gridy = 1;
/*  73: 73 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*  74:    */    
/*  76: 76 */    (
/*  77: 77 */      localObject1 = new JPanel()).setBorder(new EtchedBorder(1, null, null));
/*  78:    */    
/*  79: 79 */    (localObject2 = new GridBagConstraints()).weightx = 1.0D;
/*  80: 80 */    ((GridBagConstraints)localObject2).fill = 1;
/*  81: 81 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/*  82: 82 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  83: 83 */    ((GridBagConstraints)localObject2).gridy = 2;
/*  84: 84 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*  85:    */    
/*  86: 86 */    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 0, 80, 0, 50, 0 };
/*  87: 87 */    ((GridBagLayout)localObject2).rowHeights = new int[] { 20, 0 };
/*  88: 88 */    ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  89: 89 */    ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 4.9E-324D };
/*  90: 90 */    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
/*  91:    */    
/*  92: 92 */    Object localObject2 = new JLabel("User Name");
/*  93:    */    GridBagConstraints localGridBagConstraints;
/*  94: 94 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 13;
/*  95: 95 */    localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
/*  96: 96 */    localGridBagConstraints.gridx = 0;
/*  97: 97 */    localGridBagConstraints.gridy = 0;
/*  98: 98 */    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/*  99:    */    
/* 101:101 */    this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/* 102:    */    
/* 103:103 */    (localObject2 = new GridBagConstraints()).weightx = 0.5D;
/* 104:104 */    ((GridBagConstraints)localObject2).anchor = 11;
/* 105:105 */    ((GridBagConstraints)localObject2).fill = 2;
/* 106:106 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
/* 107:107 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 108:108 */    ((GridBagConstraints)localObject2).gridy = 0;
/* 109:109 */    ((JPanel)localObject1).add(this.jdField_a_of_type_JavaxSwingJTextField, localObject2);
/* 110:110 */    this.jdField_a_of_type_JavaxSwingJTextField.setColumns(13);
/* 111:    */    
/* 113:113 */    localObject2 = new JLabel("Password");
/* 114:    */    
/* 115:115 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 13;
/* 116:116 */    localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
/* 117:117 */    localGridBagConstraints.gridx = 2;
/* 118:118 */    localGridBagConstraints.gridy = 0;
/* 119:119 */    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 120:    */    
/* 122:122 */    this.jdField_a_of_type_JavaxSwingJPasswordField = new JPasswordField();
/* 123:    */    
/* 124:124 */    (localObject2 = new GridBagConstraints()).weightx = 1.0D;
/* 125:125 */    ((GridBagConstraints)localObject2).anchor = 11;
/* 126:126 */    ((GridBagConstraints)localObject2).fill = 2;
/* 127:127 */    ((GridBagConstraints)localObject2).gridx = 3;
/* 128:128 */    ((GridBagConstraints)localObject2).gridy = 0;
/* 129:129 */    ((JPanel)localObject1).add(this.jdField_a_of_type_JavaxSwingJPasswordField, localObject2);
/* 130:    */    
/* 133:133 */    this.jdField_a_of_type_JavaxSwingJCheckBox = new JCheckBox("Save Login (encrypted)");
/* 134:    */    
/* 135:135 */    (localObject1 = new GridBagConstraints()).anchor = 13;
/* 136:136 */    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 5, 0);
/* 137:137 */    ((GridBagConstraints)localObject1).gridx = 0;
/* 138:138 */    ((GridBagConstraints)localObject1).gridy = 3;
/* 139:139 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJCheckBox, localObject1);
/* 140:    */    
/* 142:142 */    (
/* 143:143 */      localObject1 = new JButton("Delete Saved Login")).addActionListener(new py());
/* 144:    */    
/* 153:153 */    ((JButton)localObject1).setEnabled(sD.a());
/* 154:154 */    ((JButton)localObject1).setHorizontalAlignment(4);
/* 155:    */    
/* 156:156 */    (localObject2 = new GridBagConstraints()).anchor = 13;
/* 157:157 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 158:158 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 159:159 */    ((GridBagConstraints)localObject2).gridy = 4;
/* 160:160 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 161:    */    
/* 163:163 */    (
/* 164:164 */      localObject1 = new JLabel("Note: this is optional!")).setForeground(new Color(139, 0, 0));
/* 165:165 */    ((JLabel)localObject1).setFont(new Font("Tahoma", 1, 12));
/* 166:    */    
/* 167:167 */    (localObject2 = new GridBagConstraints()).insets = new Insets(15, 0, 5, 0);
/* 168:168 */    ((GridBagConstraints)localObject2).anchor = 17;
/* 169:169 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 170:170 */    ((GridBagConstraints)localObject2).gridy = 5;
/* 171:171 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 172:    */    
/* 174:174 */    localObject1 = new JLabel("You don't have to be logged on to play single, or multiplayer");
/* 175:    */    
/* 176:176 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 177:177 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 178:178 */    ((GridBagConstraints)localObject2).gridy = 6;
/* 179:179 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 180:    */    
/* 182:182 */    (
/* 183:183 */      localObject1 = new JPanel()).setLayout(new FlowLayout(2));
/* 184:184 */    getContentPane().add((Component)localObject1, "South");
/* 185:    */    
/* 186:186 */    (
/* 187:187 */      localObject2 = new JButton("OK")).addActionListener(new pz(this));
/* 188:    */    
/* 210:210 */    ((JButton)localObject2).setActionCommand("OK");
/* 211:211 */    ((JPanel)localObject1).add((Component)localObject2);
/* 212:212 */    getRootPane().setDefaultButton((JButton)localObject2);
/* 213:    */    
/* 215:215 */    (
/* 216:216 */      localObject2 = new JButton("Cancel")).addActionListener(new pA(this));
/* 217:    */    
/* 223:223 */    ((JButton)localObject2).setActionCommand("Cancel");
/* 224:224 */    ((JPanel)localObject1).add((Component)localObject2);
/* 225:    */  }
/* 226:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     px
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */