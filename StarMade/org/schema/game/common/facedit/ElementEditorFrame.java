/*   1:    */package org.schema.game.common.facedit;
/*   2:    */
/*   3:    */import java.awt.BorderLayout;
/*   4:    */import java.awt.EventQueue;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.util.Observable;
/*   7:    */import java.util.Observer;
/*   8:    */import javax.swing.JFileChooser;
/*   9:    */import javax.swing.JFrame;
/*  10:    */import javax.swing.JLabel;
/*  11:    */import javax.swing.JMenu;
/*  12:    */import javax.swing.JMenuBar;
/*  13:    */import javax.swing.JMenuItem;
/*  14:    */import javax.swing.JPanel;
/*  15:    */import javax.swing.JScrollPane;
/*  16:    */import javax.swing.JSplitPane;
/*  17:    */import javax.swing.ScrollPaneLayout;
/*  18:    */import javax.swing.border.EmptyBorder;
/*  19:    */import javax.swing.event.TreeSelectionEvent;
/*  20:    */import javax.swing.event.TreeSelectionListener;
/*  21:    */import javax.swing.tree.DefaultMutableTreeNode;
/*  22:    */import javax.swing.tree.TreePath;
/*  23:    */import nA;
/*  24:    */import nC;
/*  25:    */import nD;
/*  26:    */import nE;
/*  27:    */import nF;
/*  28:    */import nG;
/*  29:    */import nH;
/*  30:    */import nI;
/*  31:    */import nK;
/*  32:    */import nL;
/*  33:    */import nc;
/*  34:    */import nz;
/*  35:    */import org.schema.game.common.data.element.ElementInformation;
/*  36:    */
/*  37:    */public class ElementEditorFrame
/*  38:    */  extends JFrame
/*  39:    */  implements Observer, TreeSelectionListener
/*  40:    */{
/*  41:    */  private static final long serialVersionUID = -1919570665689983369L;
/*  42:    */  private JPanel jdField_a_of_type_JavaxSwingJPanel;
/*  43:    */  private JSplitPane jdField_a_of_type_JavaxSwingJSplitPane;
/*  44:    */  private JMenuBar jdField_a_of_type_JavaxSwingJMenuBar;
/*  45:    */  private JMenu jdField_a_of_type_JavaxSwingJMenu;
/*  46:    */  private JMenu jdField_b_of_type_JavaxSwingJMenu;
/*  47:    */  private JMenuItem jdField_a_of_type_JavaxSwingJMenuItem;
/*  48:    */  private JMenuItem jdField_b_of_type_JavaxSwingJMenuItem;
/*  49:    */  private JMenuItem c;
/*  50:    */  private JMenuItem d;
/*  51:    */  private JMenuItem e;
/*  52:    */  private JMenuItem f;
/*  53:    */  private JMenuItem g;
/*  54: 54 */  private nc jdField_a_of_type_Nc = new nc();
/*  55:    */  
/*  56:    */  private JMenuItem h;
/*  57:    */  
/*  58:    */  private JMenuItem i;
/*  59:    */  private JFileChooser jdField_a_of_type_JavaxSwingJFileChooser;
/*  60:    */  private JMenuItem j;
/*  61:    */  private JMenuItem k;
/*  62:    */  private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*  63:    */  
/*  64:    */  public static void main(String[] paramArrayOfString)
/*  65:    */  {
/*  66: 66 */    EventQueue.invokeLater(new nz());
/*  67:    */  }
/*  68:    */  
/*  81:    */  public ElementEditorFrame()
/*  82:    */  {
/*  83: 83 */    setTitle("StarMade - Block Editor");
/*  84: 84 */    setDefaultCloseOperation(3);
/*  85: 85 */    setBounds(100, 100, 1200, 700);
/*  86:    */    
/*  87: 87 */    this.jdField_a_of_type_JavaxSwingJMenuBar = new JMenuBar();
/*  88: 88 */    setJMenuBar(this.jdField_a_of_type_JavaxSwingJMenuBar);
/*  89:    */    
/*  90: 90 */    this.jdField_a_of_type_JavaxSwingJMenu = new JMenu("File");
/*  91: 91 */    this.jdField_a_of_type_JavaxSwingJMenuBar.add(this.jdField_a_of_type_JavaxSwingJMenu);
/*  92:    */    
/*  93: 93 */    this.jdField_a_of_type_JavaxSwingJMenuItem = new JMenuItem("New");
/*  94: 94 */    this.jdField_a_of_type_JavaxSwingJMenuItem.addActionListener(new nC(this));
/*  95:    */    
/* 102:102 */    this.jdField_a_of_type_JavaxSwingJMenu.add(this.jdField_a_of_type_JavaxSwingJMenuItem);
/* 103:    */    
/* 104:104 */    this.d = new JMenuItem("Open");
/* 105:105 */    this.d.addActionListener(new nD(this));
/* 106:    */    
/* 115:115 */    this.jdField_a_of_type_JavaxSwingJMenu.add(this.d);
/* 116:    */    
/* 117:117 */    this.jdField_b_of_type_JavaxSwingJMenuItem = new JMenuItem("Save");
/* 118:118 */    this.jdField_b_of_type_JavaxSwingJMenuItem.addActionListener(new nE(this));
/* 119:    */    
/* 123:123 */    this.jdField_a_of_type_JavaxSwingJMenu.add(this.jdField_b_of_type_JavaxSwingJMenuItem);
/* 124:    */    
/* 125:125 */    this.c = new JMenuItem("Save As...");
/* 126:126 */    this.c.addActionListener(new nF(this));
/* 127:    */    
/* 134:134 */    this.jdField_a_of_type_JavaxSwingJMenu.add(this.c);
/* 135:    */    
/* 136:136 */    this.e = new JMenuItem("Exit");
/* 137:137 */    this.e.addActionListener(new nG(this));
/* 138:    */    
/* 142:142 */    this.jdField_a_of_type_JavaxSwingJMenu.add(this.e);
/* 143:    */    
/* 144:144 */    this.jdField_b_of_type_JavaxSwingJMenu = new JMenu("Edit");
/* 145:145 */    this.jdField_a_of_type_JavaxSwingJMenuBar.add(this.jdField_b_of_type_JavaxSwingJMenu);
/* 146:    */    
/* 147:147 */    this.f = new JMenuItem("Move Entry");
/* 148:148 */    this.f.addActionListener(new nH());
/* 149:    */    
/* 153:153 */    this.j = new JMenuItem("Create New Entry");
/* 154:154 */    this.jdField_b_of_type_JavaxSwingJMenu.add(this.j);
/* 155:155 */    this.jdField_b_of_type_JavaxSwingJMenu.add(this.f);
/* 156:156 */    this.j.addActionListener(new nI(this));
/* 157:    */    
/* 171:171 */    this.g = new JMenuItem("Duplicate Entry");
/* 172:172 */    this.g.addActionListener(new nK());
/* 173:    */    
/* 176:176 */    this.jdField_b_of_type_JavaxSwingJMenu.add(this.g);
/* 177:    */    
/* 178:178 */    this.k = new JMenuItem("Remove Entry");
/* 179:179 */    this.k.setEnabled(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null);
/* 180:180 */    this.k.addActionListener(new nA(this));
/* 181:    */    
/* 209:209 */    this.jdField_b_of_type_JavaxSwingJMenu.add(this.k);
/* 210:    */    
/* 211:211 */    this.h = new JMenuItem("Add Category");
/* 212:212 */    this.jdField_b_of_type_JavaxSwingJMenu.add(this.h);
/* 213:    */    
/* 214:214 */    this.i = new JMenuItem("Remove Category");
/* 215:215 */    this.jdField_b_of_type_JavaxSwingJMenu.add(this.i);
/* 216:216 */    this.jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/* 217:217 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 218:218 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout(new BorderLayout(0, 0));
/* 219:219 */    setContentPane(this.jdField_a_of_type_JavaxSwingJPanel);
/* 220:    */    
/* 221:221 */    this.jdField_a_of_type_JavaxSwingJSplitPane = new JSplitPane();
/* 222:222 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJSplitPane, "Center");
/* 223:    */    
/* 225:225 */    this.jdField_a_of_type_JavaxSwingJSplitPane.setDividerLocation(400);
/* 226:226 */    ElementTreePanel localElementTreePanel = new ElementTreePanel(this);
/* 227:227 */    this.jdField_a_of_type_JavaxSwingJSplitPane.setLeftComponent(new JScrollPane(localElementTreePanel));
/* 228:228 */    this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(new JLabel("nothing selected"));
/* 229:    */  }
/* 230:    */  
/* 266:    */  public void update(Observable paramObservable, Object paramObject)
/* 267:    */  {
/* 268:268 */    paramObservable = new nL(this, ((Short)paramObject).shortValue());
/* 269:269 */    this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(paramObservable);
/* 270:    */  }
/* 271:    */  
/* 276:    */  public void valueChanged(TreeSelectionEvent paramTreeSelectionEvent)
/* 277:    */  {
/* 278:278 */    if (((paramTreeSelectionEvent = (DefaultMutableTreeNode)paramTreeSelectionEvent.getPath().getLastPathComponent()).getUserObject() instanceof ElementInformation)) {
/* 279:279 */      System.err.println("Setting content pane: " + paramTreeSelectionEvent.getUserObject());
/* 280:280 */      this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = ((ElementInformation)paramTreeSelectionEvent.getUserObject());
/* 281:281 */      this.k.setEnabled(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null);
/* 282:282 */      paramTreeSelectionEvent = new nL(this, this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getId());
/* 283:283 */      paramTreeSelectionEvent = new JScrollPane(paramTreeSelectionEvent);
/* 284:284 */      ScrollPaneLayout localScrollPaneLayout = new ScrollPaneLayout();
/* 285:285 */      paramTreeSelectionEvent.setLayout(localScrollPaneLayout);
/* 286:286 */      this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(paramTreeSelectionEvent);
/* 287:    */    }
/* 288:    */  }
/* 289:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.facedit.ElementEditorFrame
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */