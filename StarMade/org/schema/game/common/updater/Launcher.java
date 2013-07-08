/*   1:    */package org.schema.game.common.updater;
/*   2:    */
/*   3:    */import java.awt.Component;
/*   4:    */import java.awt.Dimension;
/*   5:    */import java.awt.EventQueue;
/*   6:    */import java.awt.GridBagLayout;
/*   7:    */import java.awt.Insets;
/*   8:    */import java.awt.LayoutManager;
/*   9:    */import javax.swing.JFrame;
/*  10:    */import javax.swing.JMenuBar;
/*  11:    */import javax.swing.JTextPane;
/*  12:    */import javax.swing.SwingUtilities;
/*  13:    */import javax.swing.border.EmptyBorder;
/*  14:    */import sf;
/*  15:    */import sg;
/*  16:    */import sh;
/*  17:    */import si;
/*  18:    */import sj;
/*  19:    */import sm;
/*  20:    */import sr;
/*  21:    */import sy;
/*  22:    */
/*  23:    */public class Launcher extends JFrame
/*  24:    */{
/*  25:    */  public static int a;
/*  26:    */  private static final long serialVersionUID = -2537463060839705206L;
/*  27:    */  private javax.swing.JPanel a;
/*  28:    */  
/*  29:    */  static
/*  30:    */  {
/*  31: 31 */    jdField_a_of_type_Int = 9;
/*  32:    */  }
/*  33:    */  
/*  39:    */  public static void main(String[] paramArrayOfString)
/*  40:    */  {
/*  41: 41 */    System.setProperty("http.agent", "");
/*  42:    */    
/*  43: 43 */    if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].equals("-nogui"))) {
/*  44: 44 */      if (paramArrayOfString.length > 1) paramArrayOfString[1].equals("-force"); sy.a();return;
/*  45:    */    }
/*  46: 46 */    EventQueue.invokeLater(new sf());
/*  47:    */  }
/*  48:    */  
/*  65:    */  public Launcher()
/*  66:    */  {
/*  67: 67 */    setTitle("StarMade [Launcher v" + jdField_a_of_type_Int + "]");
/*  68: 68 */    setDefaultCloseOperation(3);
/*  69: 69 */    setBounds(100, 100, 849, 551);
/*  70:    */    
/*  71: 71 */    Object localObject1 = new JMenuBar();
/*  72: 72 */    setJMenuBar((JMenuBar)localObject1);
/*  73:    */    
/*  74: 74 */    Object localObject2 = new javax.swing.JMenu("Options");
/*  75: 75 */    ((JMenuBar)localObject1).add((javax.swing.JMenu)localObject2);
/*  76:    */    
/*  77: 77 */    (
/*  78: 78 */      localObject1 = new javax.swing.JMenuItem("Memory Settings")).addActionListener(new sg());
/*  79:    */    
/*  86: 86 */    ((javax.swing.JMenu)localObject2).add((javax.swing.JMenuItem)localObject1);
/*  87:    */    
/*  88: 88 */    (
/*  89: 89 */      localObject1 = new javax.swing.JMenuItem("Mirror Settings")).addActionListener(new sh(this));
/*  90:    */    
/* 110:110 */    ((javax.swing.JMenu)localObject2).add((javax.swing.JMenuItem)localObject1);
/* 111:    */    
/* 112:112 */    (
/* 113:113 */      localObject1 = new javax.swing.JMenuItem("Server Port")).addActionListener(new si(this));
/* 114:    */    
/* 147:147 */    ((javax.swing.JMenu)localObject2).add((javax.swing.JMenuItem)localObject1);
/* 148:148 */    this.jdField_a_of_type_JavaxSwingJPanel = new javax.swing.JPanel();
/* 149:149 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 150:150 */    setContentPane(this.jdField_a_of_type_JavaxSwingJPanel);
/* 151:    */    
/* 152:152 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 153:153 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0 };
/* 154:154 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 155:155 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 1.0D, 1.0D, 1.0D, 4.9E-324D };
/* 156:156 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/* 157:    */    
/* 158:158 */    (
/* 159:159 */      localObject1 = new javax.swing.JScrollPane()).setPreferredSize(new Dimension(400, 100));
/* 160:160 */    ((javax.swing.JScrollPane)localObject1).setMinimumSize(new Dimension(23, 30));
/* 161:    */    
/* 162:162 */    (localObject2 = new java.awt.GridBagConstraints()).fill = 1;
/* 163:163 */    ((java.awt.GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 164:164 */    ((java.awt.GridBagConstraints)localObject2).gridx = 0;
/* 165:165 */    ((java.awt.GridBagConstraints)localObject2).gridy = 0;
/* 166:166 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 167:    */    
/* 168:168 */    (
/* 169:169 */      localObject2 = new JTextPane()).setPreferredSize(new Dimension(300, 30));
/* 170:170 */    ((javax.swing.JScrollPane)localObject1).setViewportView((Component)localObject2);
/* 171:171 */    ((JTextPane)localObject2).setText("If the download gets stuck, launching the starter as Administrator helps in some cases. You can also download the latest Version manually from http://files.star-made.org/build/ and extract it to the directory where this launcher is located.\r\n\r\nIntel graphics cards are known to have buggy drivers in old versions, so be sure to update to the newest version.\r\n\r\nThere is a CrashAndBugReporter.jar in the StarMade directory if you want to send in a crash report manually.\r\nPlease use 64 bit java for maximal performance.\r\nIf you have any questions about the game, feel free to mail me at schema@star-made.org\r\n\r\nHave fun playing!");
/* 172:172 */    SwingUtilities.invokeLater(new sj((javax.swing.JScrollPane)localObject1));
/* 173:    */    
/* 181:181 */    (
/* 182:182 */      localObject1 = new sm()).setMinimumSize(new Dimension(450, 200));
/* 183:    */    
/* 184:184 */    (localObject2 = new java.awt.GridBagConstraints()).weighty = 3.0D;
/* 185:185 */    ((java.awt.GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 186:186 */    ((java.awt.GridBagConstraints)localObject2).fill = 1;
/* 187:187 */    ((java.awt.GridBagConstraints)localObject2).gridx = 0;
/* 188:188 */    ((java.awt.GridBagConstraints)localObject2).gridy = 1;
/* 189:189 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 190:    */    
/* 191:191 */    localObject1 = new sr();
/* 192:    */    
/* 193:193 */    (localObject2 = new java.awt.GridBagConstraints()).weighty = 1.0D;
/* 194:194 */    ((java.awt.GridBagConstraints)localObject2).fill = 1;
/* 195:195 */    ((java.awt.GridBagConstraints)localObject2).weightx = 1.0D;
/* 196:196 */    ((java.awt.GridBagConstraints)localObject2).gridx = 0;
/* 197:197 */    ((java.awt.GridBagConstraints)localObject2).gridy = 2;
/* 198:198 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.updater.Launcher
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */