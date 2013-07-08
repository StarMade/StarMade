/*   1:    */package org.schema.game.common.crashreporter;
/*   2:    */
/*   3:    */import java.awt.Component;
/*   4:    */import java.awt.Dimension;
/*   5:    */import java.awt.EventQueue;
/*   6:    */import java.awt.Font;
/*   7:    */import java.awt.GridBagConstraints;
/*   8:    */import java.awt.GridBagLayout;
/*   9:    */import java.awt.Insets;
/*  10:    */import java.awt.LayoutManager;
/*  11:    */import java.awt.Toolkit;
/*  12:    */import java.io.IOException;
/*  13:    */import java.io.PrintStream;
/*  14:    */import java.util.Observable;
/*  15:    */import java.util.Observer;
/*  16:    */import javax.swing.JButton;
/*  17:    */import javax.swing.JFrame;
/*  18:    */import javax.swing.JLabel;
/*  19:    */import javax.swing.JOptionPane;
/*  20:    */import javax.swing.JPanel;
/*  21:    */import javax.swing.JProgressBar;
/*  22:    */import javax.swing.JScrollPane;
/*  23:    */import javax.swing.JTextArea;
/*  24:    */import javax.swing.JTextField;
/*  25:    */import javax.swing.JTextPane;
/*  26:    */import javax.swing.border.EmptyBorder;
/*  27:    */import javax.swing.border.TitledBorder;
/*  28:    */import kY;
/*  29:    */import kZ;
/*  30:    */
/*  31:    */public class CrashReportGUI
/*  32:    */  extends JFrame implements Observer
/*  33:    */{
/*  34:    */  private static final long serialVersionUID = -4472460272445143389L;
/*  35:    */  private JPanel jdField_a_of_type_JavaxSwingJPanel;
/*  36:    */  private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*  37:    */  private JProgressBar jdField_a_of_type_JavaxSwingJProgressBar;
/*  38:    */  
/*  39:    */  public static void main(String[] paramArrayOfString)
/*  40:    */  {
/*  41: 41 */    if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].equals("-nogui"))) {
/*  42: 42 */      if (paramArrayOfString.length < 3) {
/*  43: 43 */        System.out.println("You need at least 2 more arguments:\nExample:\njava -jar CrashAndBugReport.jar -nogui myemail@mymaildom.com description of the bug i had");
/*  44:    */        
/*  46: 46 */        System.exit(0);return;
/*  47:    */      }
/*  48: 48 */      StringBuffer localStringBuffer = new StringBuffer();
/*  49: 49 */      for (int i = 2; i < paramArrayOfString.length; i++) {
/*  50: 50 */        localStringBuffer.append(paramArrayOfString[i] + " ");
/*  51:    */      }
/*  52: 52 */      CrashReporter localCrashReporter = new CrashReporter();
/*  53:    */      try {
/*  54: 54 */        localCrashReporter.a(paramArrayOfString[1], localStringBuffer.toString());
/*  55: 55 */        localCrashReporter.b(); return;
/*  56: 56 */      } catch (Exception localException) { 
/*  57:    */        
/*  58: 58 */          localException.printStackTrace();
/*  59: 59 */        return;
/*  60:    */      } }
/*  61: 61 */    EventQueue.invokeLater(new kY());
/*  62:    */  }
/*  63:    */  
/*  82:    */  public CrashReportGUI()
/*  83:    */  {
/*  84: 84 */    setTitle("Report Bug or Crash");
/*  85: 85 */    setDefaultCloseOperation(3);
/*  86: 86 */    setBounds(100, 100, 626, 413);
/*  87: 87 */    this.jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*  88: 88 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  89: 89 */    setContentPane(this.jdField_a_of_type_JavaxSwingJPanel);
/*  90:    */    Object localObject1;
/*  91: 91 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
/*  92: 92 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*  93: 93 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 1.0D, 4.9E-324D };
/*  94: 94 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 1.0D, 4.9E-324D };
/*  95: 95 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*  96:    */    
/*  97: 97 */    (
/*  98: 98 */      localObject1 = new JPanel()).setBorder(new TitledBorder(null, "Basic Information", 4, 2, null, null));
/*  99:    */    
/* 100:100 */    (localObject2 = new GridBagConstraints()).gridwidth = 2;
/* 101:101 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 102:102 */    ((GridBagConstraints)localObject2).fill = 1;
/* 103:103 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 104:104 */    ((GridBagConstraints)localObject2).gridy = 0;
/* 105:105 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 106:    */    
/* 107:107 */    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 198, 112, 86, 0 };
/* 108:108 */    ((GridBagLayout)localObject2).rowHeights = new int[] { 20, 0 };
/* 109:109 */    ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 1.0D, 4.9E-324D };
/* 110:110 */    ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 111:111 */    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
/* 112:    */    
/* 113:113 */    Object localObject2 = new JLabel("Email (required)");
/* 114:    */    GridBagConstraints localGridBagConstraints;
/* 115:115 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 18;
/* 116:116 */    localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
/* 117:117 */    localGridBagConstraints.gridx = 0;
/* 118:118 */    localGridBagConstraints.gridy = 0;
/* 119:119 */    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 120:120 */    ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
/* 121:    */    
/* 122:122 */    this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/* 123:    */    
/* 124:124 */    (localObject2 = new GridBagConstraints()).gridwidth = 2;
/* 125:125 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
/* 126:126 */    ((GridBagConstraints)localObject2).fill = 1;
/* 127:127 */    ((GridBagConstraints)localObject2).anchor = 18;
/* 128:128 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 129:129 */    ((GridBagConstraints)localObject2).gridy = 0;
/* 130:130 */    ((JPanel)localObject1).add(this.jdField_a_of_type_JavaxSwingJTextField, localObject2);
/* 131:131 */    this.jdField_a_of_type_JavaxSwingJTextField.setPreferredSize(new Dimension(300, 20));
/* 132:132 */    this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/* 133:    */    
/* 134:134 */    (
/* 135:135 */      localObject1 = new JPanel()).setBorder(new TitledBorder(null, "Bug/Crash description", 4, 2, null, null));
/* 136:136 */    ((JPanel)localObject1).setPreferredSize(new Dimension(300, 300));
/* 137:    */    
/* 138:138 */    (localObject2 = new GridBagConstraints()).weighty = 100.0D;
/* 139:139 */    ((GridBagConstraints)localObject2).gridheight = 8;
/* 140:140 */    ((GridBagConstraints)localObject2).gridwidth = 2;
/* 141:141 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 142:142 */    ((GridBagConstraints)localObject2).fill = 1;
/* 143:143 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 144:144 */    ((GridBagConstraints)localObject2).gridy = 1;
/* 145:145 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 146:    */    
/* 147:147 */    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 93, 0 };
/* 148:148 */    ((GridBagLayout)localObject2).rowHeights = new int[] { 19, 19, 19, 0, 0 };
/* 149:149 */    ((GridBagLayout)localObject2).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 150:150 */    ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 1.0D, 4.9E-324D };
/* 151:151 */    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
/* 152:    */    
/* 153:153 */    localObject2 = new JLabel("Where did the game crash?");
/* 154:    */    
/* 155:155 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 16;
/* 156:156 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 157:157 */    localGridBagConstraints.gridx = 0;
/* 158:158 */    localGridBagConstraints.gridy = 0;
/* 159:159 */    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 160:160 */    ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
/* 161:    */    
/* 162:162 */    localObject2 = new JLabel("What were you doing in the game when the Bug occurred?");
/* 163:    */    
/* 164:164 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 18;
/* 165:165 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 166:166 */    localGridBagConstraints.gridx = 0;
/* 167:167 */    localGridBagConstraints.gridy = 1;
/* 168:168 */    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 169:169 */    ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
/* 170:    */    
/* 171:171 */    localObject2 = new JLabel("Please describe the Problem:");
/* 172:    */    
/* 173:173 */    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 174:174 */    localGridBagConstraints.anchor = 16;
/* 175:175 */    localGridBagConstraints.gridx = 0;
/* 176:176 */    localGridBagConstraints.gridy = 2;
/* 177:177 */    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 178:178 */    ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
/* 179:    */    
/* 180:180 */    (
/* 181:181 */      localObject2 = new JScrollPane()).setHorizontalScrollBarPolicy(31);
/* 182:    */    
/* 183:183 */    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
/* 184:184 */    localGridBagConstraints.gridx = 0;
/* 185:185 */    localGridBagConstraints.gridy = 3;
/* 186:186 */    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 187:    */    
/* 188:188 */    (
/* 189:189 */      localObject1 = new JTextArea()).setWrapStyleWord(true);
/* 190:190 */    ((JTextArea)localObject1).setLineWrap(true);
/* 191:191 */    ((JScrollPane)localObject2).setViewportView((Component)localObject1);
/* 192:    */    
/* 193:193 */    (
/* 194:194 */      localObject2 = new JButton("Send Report and Logs")).addActionListener(new kZ(this, (JTextArea)localObject1));
/* 195:    */    
/* 214:214 */    (
/* 215:215 */      localObject1 = new JTextPane()).setFont(new Font("Arial", 0, 10));
/* 216:216 */    ((JTextPane)localObject1).setEditable(false);
/* 217:217 */    ((JTextPane)localObject1).setText("All information you send will only be used to fix bugs and crashes in StarMade, and that purpose only. The information won't be saved permanently and will never be given to a third party.");
/* 218:    */    
/* 219:219 */    (localGridBagConstraints = new GridBagConstraints()).gridwidth = 2;
/* 220:220 */    localGridBagConstraints.anchor = 15;
/* 221:221 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 222:222 */    localGridBagConstraints.fill = 2;
/* 223:223 */    localGridBagConstraints.gridx = 0;
/* 224:224 */    localGridBagConstraints.gridy = 9;
/* 225:225 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localGridBagConstraints);
/* 226:    */    
/* 227:227 */    (localObject1 = new GridBagConstraints()).anchor = 17;
/* 228:228 */    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
/* 229:229 */    ((GridBagConstraints)localObject1).gridx = 0;
/* 230:230 */    ((GridBagConstraints)localObject1).gridy = 10;
/* 231:231 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject1);
/* 232:    */    
/* 233:233 */    this.jdField_a_of_type_JavaxSwingJProgressBar = new JProgressBar();
/* 234:234 */    this.jdField_a_of_type_JavaxSwingJProgressBar.setStringPainted(true);
/* 235:    */    
/* 236:236 */    (localObject1 = new GridBagConstraints()).weightx = 100.0D;
/* 237:237 */    ((GridBagConstraints)localObject1).fill = 1;
/* 238:238 */    ((GridBagConstraints)localObject1).gridx = 1;
/* 239:239 */    ((GridBagConstraints)localObject1).gridy = 10;
/* 240:240 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJProgressBar, localObject1);
/* 241:    */  }
/* 242:    */  
/* 243:    */  public void update(Observable paramObservable, Object paramObject)
/* 244:    */  {
/* 245:245 */    if (paramObject != null) {
/* 246:246 */      if ((paramObject instanceof Integer)) {
/* 247:247 */        this.jdField_a_of_type_JavaxSwingJProgressBar.setValue(((Integer)paramObject).intValue());return;
/* 248:    */      }
/* 249:249 */      if (paramObject.toString().equals("FINISHED")) {
/* 250:250 */        paramObservable = "Thank You For Sending the Report!\n\nI (schema) will be automatically notified about this Report\nand I will try to fix your issue as soon as I can!\n\nThanks for playing StarMade!\n";Object[] arrayOfObject = { "OK" };String str = "Information"; JFrame localJFrame; (localJFrame = new JFrame(str)).setUndecorated(true);localJFrame.setVisible(true);Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2); switch (JOptionPane.showOptionDialog(localJFrame, paramObservable, str, 0, 1, null, arrayOfObject, arrayOfObject[0])) {case 0:  System.err.println("Exiting because of exit info dialog"); try { CrashReporter.a(); } catch (IOException localIOException) { localIOException; } System.exit(0); } localJFrame.dispose();
/* 251:    */      }
/* 252:    */      
/* 256:256 */      this.jdField_a_of_type_JavaxSwingJProgressBar.setString(paramObject.toString());
/* 257:    */    }
/* 258:    */  }
/* 259:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.crashreporter.CrashReportGUI
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */