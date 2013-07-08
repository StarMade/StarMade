/*   1:    */import java.awt.Component;
/*   2:    */import java.awt.GridBagConstraints;
/*   3:    */import java.awt.GridBagLayout;
/*   4:    */import java.awt.Insets;
/*   5:    */import java.awt.LayoutManager;
/*   6:    */import javax.swing.JButton;
/*   7:    */import javax.swing.JFrame;
/*   8:    */import javax.swing.JList;
/*   9:    */import javax.swing.JPanel;
/*  10:    */import javax.swing.JScrollPane;
/*  11:    */import javax.swing.ListModel;
/*  12:    */import org.schema.game.common.staremote.Staremote;
/*  13:    */
/*  28:    */public final class qf
/*  29:    */  extends JPanel
/*  30:    */{
/*  31: 31 */  private Object jdField_a_of_type_JavaLangObject = new Object();
/*  32:    */  
/*  33:    */  private static final long serialVersionUID = -2984518551942439061L;
/*  34:    */  private JButton jdField_a_of_type_JavaxSwingJButton;
/*  35:    */  private JButton b;
/*  36: 36 */  Boolean jdField_a_of_type_JavaLangBoolean = Boolean.valueOf(false);
/*  37:    */  
/*  38:    */  public qf(JFrame paramJFrame, Staremote paramStaremote)
/*  39:    */  {
/*  40: 40 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/*  41: 41 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
/*  42: 42 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/*  43: 43 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 1.0D, 1.0D, 4.9E-324D };
/*  44: 44 */    setLayout((LayoutManager)localObject1);
/*  45:    */    
/*  46: 46 */    Object localObject1 = new JPanel();
/*  47:    */    
/*  48: 48 */    (localObject2 = new GridBagConstraints()).weighty = 5.0D;
/*  49: 49 */    ((GridBagConstraints)localObject2).weightx = 1.0D;
/*  50: 50 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/*  51: 51 */    ((GridBagConstraints)localObject2).fill = 1;
/*  52: 52 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  53: 53 */    ((GridBagConstraints)localObject2).gridy = 0;
/*  54: 54 */    add((Component)localObject1, localObject2);
/*  55:    */    
/*  56: 56 */    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
/*  57: 57 */    ((GridBagLayout)localObject2).rowHeights = new int[] { 0, 0 };
/*  58: 58 */    ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
/*  59: 59 */    ((GridBagLayout)localObject2).rowWeights = new double[] { 1.0D, 4.9E-324D };
/*  60: 60 */    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
/*  61:    */    
/*  62: 62 */    Object localObject2 = new JPanel();
/*  63:    */    
/*  64: 64 */    (localObject3 = new GridBagConstraints()).weightx = 1.0D;
/*  65: 65 */    ((GridBagConstraints)localObject3).insets = new Insets(0, 0, 5, 0);
/*  66: 66 */    ((GridBagConstraints)localObject3).fill = 1;
/*  67: 67 */    ((GridBagConstraints)localObject3).gridx = 0;
/*  68: 68 */    ((GridBagConstraints)localObject3).gridy = 0;
/*  69: 69 */    ((JPanel)localObject1).add((Component)localObject2, localObject3);
/*  70:    */    
/*  71: 71 */    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/*  72: 72 */    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/*  73: 73 */    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/*  74: 74 */    ((GridBagLayout)localObject3).rowWeights = new double[] { 1.0D, 4.9E-324D };
/*  75: 75 */    ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
/*  76:    */    
/*  77: 77 */    Object localObject3 = new JScrollPane();
/*  78:    */    
/*  79: 79 */    (localObject4 = new GridBagConstraints()).fill = 1;
/*  80: 80 */    ((GridBagConstraints)localObject4).gridx = 0;
/*  81: 81 */    ((GridBagConstraints)localObject4).gridy = 0;
/*  82: 82 */    ((JPanel)localObject2).add((Component)localObject3, localObject4);
/*  83:    */    
/*  84: 84 */    localObject2 = new qa();
/*  85: 85 */    Object localObject4 = new JList((ListModel)localObject2);
/*  86:    */    
/*  87: 87 */    ((JScrollPane)localObject3).setViewportView((Component)localObject4);
/*  88:    */    
/*  89: 89 */    localObject3 = new JPanel();
/*  90:    */    Object localObject5;
/*  91: 91 */    (localObject5 = new GridBagConstraints()).weightx = 0.001D;
/*  92: 92 */    ((GridBagConstraints)localObject5).fill = 1;
/*  93: 93 */    ((GridBagConstraints)localObject5).gridx = 1;
/*  94: 94 */    ((GridBagConstraints)localObject5).gridy = 0;
/*  95: 95 */    ((JPanel)localObject1).add((Component)localObject3, localObject5);
/*  96:    */    
/*  97: 97 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0 };
/*  98: 98 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*  99: 99 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 4.9E-324D };
/* 100:100 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/* 101:101 */    ((JPanel)localObject3).setLayout((LayoutManager)localObject1);
/* 102:    */    
/* 103:103 */    (
/* 104:104 */      localObject1 = new JButton("Connect")).setEnabled(false);
/* 105:105 */    ((JButton)localObject1).addActionListener(new qg((JList)localObject4, paramJFrame, paramStaremote));
/* 106:    */    
/* 116:116 */    (localObject5 = new GridBagConstraints()).fill = 2;
/* 117:117 */    ((GridBagConstraints)localObject5).anchor = 13;
/* 118:118 */    ((GridBagConstraints)localObject5).insets = new Insets(0, 0, 5, 0);
/* 119:119 */    ((GridBagConstraints)localObject5).gridx = 0;
/* 120:120 */    ((GridBagConstraints)localObject5).gridy = 0;
/* 121:121 */    ((JPanel)localObject3).add((Component)localObject1, localObject5);
/* 122:    */    
/* 123:123 */    (
/* 124:124 */      localObject5 = new JButton("Add Connection")).addActionListener(new qh(paramJFrame, (qa)localObject2));
/* 125:    */    
/* 127:    */    GridBagConstraints localGridBagConstraints;
/* 128:    */    
/* 130:130 */    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
/* 131:131 */    localGridBagConstraints.anchor = 13;
/* 132:132 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 133:133 */    localGridBagConstraints.gridx = 0;
/* 134:134 */    localGridBagConstraints.gridy = 2;
/* 135:135 */    ((JPanel)localObject3).add((Component)localObject5, localGridBagConstraints);
/* 136:    */    
/* 137:137 */    this.b = new JButton("Edit Connection ");
/* 138:138 */    this.b.addActionListener(new qi(this, (JList)localObject4, (JButton)localObject1, paramJFrame, (qa)localObject2));
/* 139:    */    
/* 150:150 */    this.b.setEnabled(false);
/* 151:    */    
/* 152:152 */    (localObject5 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 153:153 */    ((GridBagConstraints)localObject5).fill = 2;
/* 154:154 */    ((GridBagConstraints)localObject5).anchor = 13;
/* 155:155 */    ((GridBagConstraints)localObject5).gridx = 0;
/* 156:156 */    ((GridBagConstraints)localObject5).gridy = 3;
/* 157:157 */    ((JPanel)localObject3).add(this.b, localObject5);
/* 158:    */    
/* 159:159 */    this.jdField_a_of_type_JavaxSwingJButton = new JButton("Remove Connection");
/* 160:160 */    this.jdField_a_of_type_JavaxSwingJButton.setEnabled(false);
/* 161:161 */    this.jdField_a_of_type_JavaxSwingJButton.addActionListener(new qj(this, (JList)localObject4, (JButton)localObject1, (qa)localObject2));
/* 162:    */    
/* 175:175 */    (
/* 176:176 */      localObject2 = new JButton("Uplink Settings")).addActionListener(new qk());
/* 177:    */    
/* 184:184 */    (localObject5 = new GridBagConstraints()).anchor = 13;
/* 185:185 */    ((GridBagConstraints)localObject5).fill = 2;
/* 186:186 */    ((GridBagConstraints)localObject5).insets = new Insets(0, 0, 5, 0);
/* 187:187 */    ((GridBagConstraints)localObject5).gridx = 0;
/* 188:188 */    ((GridBagConstraints)localObject5).gridy = 5;
/* 189:189 */    ((JPanel)localObject3).add((Component)localObject2, localObject5);
/* 190:    */    
/* 191:191 */    (localObject2 = new GridBagConstraints()).fill = 2;
/* 192:192 */    ((GridBagConstraints)localObject2).anchor = 13;
/* 193:193 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 194:194 */    ((GridBagConstraints)localObject2).gridy = 7;
/* 195:195 */    ((JPanel)localObject3).add(this.jdField_a_of_type_JavaxSwingJButton, localObject2);
/* 196:    */    
/* 197:197 */    localObject2 = new JPanel();
/* 198:    */    
/* 199:199 */    (localObject3 = new GridBagConstraints()).weighty = 1.0D;
/* 200:200 */    ((GridBagConstraints)localObject3).weightx = 0.2D;
/* 201:201 */    ((GridBagConstraints)localObject3).fill = 1;
/* 202:202 */    ((GridBagConstraints)localObject3).gridx = 0;
/* 203:203 */    ((GridBagConstraints)localObject3).gridy = 1;
/* 204:204 */    add((Component)localObject2, localObject3);
/* 205:    */    
/* 206:206 */    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 207:207 */    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/* 208:208 */    ((GridBagLayout)localObject3).columnWeights = new double[] { 0.0D, 4.9E-324D };
/* 209:209 */    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 210:210 */    ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
/* 211:    */    
/* 212:212 */    (
/* 213:213 */      localObject3 = new JButton("   Exit   ")).addActionListener(new ql());
/* 214:    */    
/* 219:219 */    (localObject5 = new GridBagConstraints()).insets = new Insets(5, 0, 5, 5);
/* 220:220 */    ((GridBagConstraints)localObject5).weightx = 1.0D;
/* 221:221 */    ((GridBagConstraints)localObject5).anchor = 13;
/* 222:222 */    ((GridBagConstraints)localObject5).gridx = 0;
/* 223:223 */    ((GridBagConstraints)localObject5).gridy = 0;
/* 224:224 */    ((JPanel)localObject2).add((Component)localObject3, localObject5);
/* 225:    */    
/* 226:226 */    ((JList)localObject4).addListSelectionListener(new qm(this, (JButton)localObject1, (JList)localObject4));
/* 227:    */    
/* 233:233 */    ((JList)localObject4).addMouseListener(new c((JList)localObject4, new qn(this, (JList)localObject4, paramJFrame, paramStaremote)));
/* 234:    */  }
/* 235:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */