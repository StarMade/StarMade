/*   1:    */import java.awt.BorderLayout;
/*   2:    */import java.awt.Container;
/*   3:    */import java.awt.FlowLayout;
/*   4:    */import java.awt.GridBagConstraints;
/*   5:    */import java.awt.GridBagLayout;
/*   6:    */import java.awt.GridLayout;
/*   7:    */import java.awt.Insets;
/*   8:    */import java.io.PrintStream;
/*   9:    */import java.util.Arrays;
/*  10:    */import java.util.Comparator;
/*  11:    */import java.util.HashSet;
/*  12:    */import java.util.Iterator;
/*  13:    */import java.util.Locale;
/*  14:    */import javax.swing.JButton;
/*  15:    */import javax.swing.JDialog;
/*  16:    */import javax.swing.JFrame;
/*  17:    */import javax.swing.JList;
/*  18:    */import javax.swing.JPanel;
/*  19:    */import javax.swing.JRootPane;
/*  20:    */import javax.swing.JScrollPane;
/*  21:    */import javax.swing.JTabbedPane;
/*  22:    */import javax.swing.JTextField;
/*  23:    */import javax.swing.JTree;
/*  24:    */import javax.swing.event.TreeSelectionEvent;
/*  25:    */import javax.swing.event.TreeSelectionListener;
/*  26:    */import javax.swing.tree.DefaultMutableTreeNode;
/*  27:    */import javax.swing.tree.TreePath;
/*  28:    */import org.schema.game.common.data.element.ElementInformation;
/*  29:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  30:    */import org.schema.game.common.facedit.ElementTreePanel;
/*  31:    */
/*  39:    */public final class ne
/*  40:    */  extends JDialog
/*  41:    */  implements TreeSelectionListener
/*  42:    */{
/*  43:    */  private static final long serialVersionUID = 3170967672363822879L;
/*  44:    */  private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*  45: 45 */  private final HashSet jdField_a_of_type_JavaUtilHashSet = new HashSet();
/*  46:    */  private JTabbedPane jdField_a_of_type_JavaxSwingJTabbedPane;
/*  47:    */  private JPanel jdField_a_of_type_JavaxSwingJPanel;
/*  48:    */  private JPanel b;
/*  49:    */  private ElementTreePanel jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel;
/*  50:    */  private nd jdField_a_of_type_Nd;
/*  51:    */  private JList jdField_a_of_type_JavaxSwingJList;
/*  52:    */  private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*  53:    */  
/*  54:    */  private void a() {
/*  55: 55 */    HashSet localHashSet = new HashSet(this.jdField_a_of_type_JavaUtilHashSet);
/*  56:    */    
/*  57:    */    String str;
/*  58: 58 */    if ((str = this.jdField_a_of_type_JavaxSwingJTextField.getText()).trim().length() != 0)
/*  59:    */    {
/*  61: 61 */      System.err.println("CHECKING ALL FOR " + str.trim().toLowerCase(Locale.ENGLISH));
/*  62: 62 */      for (localObject1 = this.jdField_a_of_type_JavaUtilHashSet.iterator(); ((Iterator)localObject1).hasNext();) {
/*  63: 63 */        if (!(localObject2 = (ElementInformation)((Iterator)localObject1).next()).getName().toLowerCase(Locale.ENGLISH).contains(str.trim().toLowerCase(Locale.ENGLISH))) {
/*  64: 64 */          localHashSet.remove(localObject2);
/*  65:    */        }
/*  66:    */      }
/*  67:    */    }
/*  68:    */    
/*  70: 70 */    Object localObject1 = new nf();
/*  71:    */    
/*  77: 77 */    System.err.println("FINAL SET " + localHashSet.size());
/*  78: 78 */    Object localObject2 = new ElementInformation[localHashSet.size()];
/*  79: 79 */    localHashSet.toArray((Object[])localObject2);
/*  80: 80 */    Arrays.sort((Object[])localObject2, (Comparator)localObject1);
/*  81:    */    
/*  82: 82 */    this.jdField_a_of_type_JavaxSwingJList.setListData((Object[])localObject2);
/*  83:    */  }
/*  84:    */  
/* 108:    */  public ne(JFrame paramJFrame, nd paramnd)
/* 109:    */  {
/* 110:110 */    super(paramJFrame, true);
/* 111:111 */    this.jdField_a_of_type_Nd = paramnd;
/* 112:112 */    paramnd = (paramJFrame = ElementKeyMap.typeList()).length; for (nd localnd = 0; localnd < paramnd; localnd++) { short s = paramJFrame[localnd];
/* 113:113 */      this.jdField_a_of_type_JavaUtilHashSet.add(ElementKeyMap.getInfo(s));
/* 114:    */    }
/* 115:    */    
/* 116:116 */    setAlwaysOnTop(true);
/* 117:    */    
/* 118:118 */    setBounds(100, 100, 702, 440);
/* 119:119 */    getContentPane().setLayout(new BorderLayout());
/* 120:    */    
/* 121:121 */    this.jdField_a_of_type_JavaxSwingJTabbedPane = new JTabbedPane(1);
/* 122:122 */    getContentPane().add(this.jdField_a_of_type_JavaxSwingJTabbedPane, "Center");
/* 123:    */    
/* 124:124 */    this.jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/* 125:125 */    this.jdField_a_of_type_JavaxSwingJTabbedPane.addTab("Search", null, this.jdField_a_of_type_JavaxSwingJPanel, null);
/* 126:    */    
/* 127:127 */    (paramJFrame = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 128:128 */    paramJFrame.rowHeights = new int[] { 0, 0, 0 };
/* 129:129 */    paramJFrame.columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 130:130 */    paramJFrame.rowWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/* 131:131 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout(paramJFrame);
/* 132:    */    
/* 133:133 */    this.jdField_a_of_type_JavaxSwingJTextField = new JTextField("");
/* 134:    */    
/* 135:135 */    (paramnd = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 136:136 */    paramnd.fill = 2;
/* 137:137 */    paramnd.gridx = 0;
/* 138:138 */    paramnd.gridy = 0;
/* 139:139 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJTextField, paramnd);
/* 140:140 */    this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/* 141:    */    
/* 142:142 */    this.jdField_a_of_type_JavaxSwingJTextField.addKeyListener(new ng(this));
/* 143:    */    
/* 164:164 */    this.jdField_a_of_type_JavaxSwingJList = new JList();
/* 165:165 */    this.jdField_a_of_type_JavaxSwingJList.addMouseListener(new nh(this));
/* 166:    */    
/* 182:182 */    this.jdField_a_of_type_JavaxSwingJList.setSelectionMode(0);
/* 183:    */    
/* 184:184 */    (paramnd = new GridBagConstraints()).fill = 1;
/* 185:185 */    paramnd.gridx = 0;
/* 186:186 */    paramnd.gridy = 1;
/* 187:187 */    this.jdField_a_of_type_JavaxSwingJPanel.add(new JScrollPane(this.jdField_a_of_type_JavaxSwingJList), paramnd);
/* 188:    */    
/* 191:191 */    this.b = new JPanel();
/* 192:192 */    this.jdField_a_of_type_JavaxSwingJTabbedPane.addTab("Tree", null, this.b, null);
/* 193:193 */    this.b.setLayout(new GridLayout(0, 1, 0, 0));
/* 194:    */    
/* 195:195 */    this.jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel = new ElementTreePanel(this);
/* 196:196 */    this.jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel.a().setEditable(false);
/* 197:197 */    this.jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel.a().addMouseListener(new ni(this));
/* 198:    */    
/* 215:215 */    this.b.add(this.jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel);
/* 216:    */    
/* 221:221 */    (
/* 222:222 */      paramJFrame = new JPanel()).setLayout(new FlowLayout(2));
/* 223:223 */    getContentPane().add(paramJFrame, "South");
/* 224:    */    
/* 225:225 */    (
/* 226:226 */      paramnd = new JButton("OK")).setActionCommand("OK");
/* 227:227 */    paramJFrame.add(paramnd);
/* 228:228 */    getRootPane().setDefaultButton(paramnd);
/* 229:229 */    paramnd.addActionListener(new nj(this));
/* 230:    */    
/* 240:240 */    (
/* 241:241 */      paramnd = new JButton("Cancel")).setActionCommand("Cancel");
/* 242:242 */    paramnd.addActionListener(new nk(this));
/* 243:    */    
/* 249:249 */    paramJFrame.add(paramnd);
/* 250:    */    
/* 253:253 */    a();
/* 254:    */  }
/* 255:    */  
/* 258:    */  public final void valueChanged(TreeSelectionEvent paramTreeSelectionEvent)
/* 259:    */  {
/* 260:260 */    if (((paramTreeSelectionEvent = (DefaultMutableTreeNode)paramTreeSelectionEvent.getPath().getLastPathComponent()).getUserObject() instanceof ElementInformation)) {
/* 261:261 */      paramTreeSelectionEvent = (ElementInformation)paramTreeSelectionEvent.getUserObject();
/* 262:262 */      this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramTreeSelectionEvent;
/* 263:263 */      System.err.println("NOW SELECTED " + this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation);
/* 264:    */    }
/* 265:    */  }
/* 266:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ne
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */