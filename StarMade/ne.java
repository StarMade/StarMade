/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Insets;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.event.TreeSelectionEvent;
/*     */ import javax.swing.event.TreeSelectionListener;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.TreePath;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.facedit.ElementTreePanel;
/*     */ 
/*     */ public final class ne extends JDialog
/*     */   implements TreeSelectionListener
/*     */ {
/*     */   private static final long serialVersionUID = 3170967672363822879L;
/*     */   private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*  45 */   private final HashSet jdField_a_of_type_JavaUtilHashSet = new HashSet();
/*     */   private JTabbedPane jdField_a_of_type_JavaxSwingJTabbedPane;
/*     */   private JPanel jdField_a_of_type_JavaxSwingJPanel;
/*     */   private JPanel b;
/*     */   private ElementTreePanel jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel;
/*     */   private nd jdField_a_of_type_Nd;
/*     */   private JList jdField_a_of_type_JavaxSwingJList;
/*     */   private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*     */ 
/*     */   private void a()
/*     */   {
/*  55 */     HashSet localHashSet = new HashSet(this.jdField_a_of_type_JavaUtilHashSet);
/*     */     String str;
/*  58 */     if ((
/*  58 */       str = this.jdField_a_of_type_JavaxSwingJTextField.getText())
/*  58 */       .trim().length() != 0)
/*     */     {
/*  61 */       System.err.println("CHECKING ALL FOR " + str.trim().toLowerCase(Locale.ENGLISH));
/*  62 */       for (localObject1 = this.jdField_a_of_type_JavaUtilHashSet.iterator(); ((Iterator)localObject1).hasNext(); ) {
/*  63 */         if (!(
/*  63 */           localObject2 = (ElementInformation)((Iterator)localObject1).next())
/*  63 */           .getName().toLowerCase(Locale.ENGLISH).contains(str.trim().toLowerCase(Locale.ENGLISH))) {
/*  64 */           localHashSet.remove(localObject2);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  70 */     Object localObject1 = new nf();
/*     */ 
/*  77 */     System.err.println("FINAL SET " + localHashSet.size());
/*  78 */     Object localObject2 = new ElementInformation[localHashSet.size()];
/*  79 */     localHashSet.toArray((Object[])localObject2);
/*  80 */     Arrays.sort((Object[])localObject2, (Comparator)localObject1);
/*     */ 
/*  82 */     this.jdField_a_of_type_JavaxSwingJList.setListData((Object[])localObject2);
/*     */   }
/*     */ 
/*     */   public ne(JFrame paramJFrame, nd paramnd)
/*     */   {
/* 110 */     super(paramJFrame, true);
/* 111 */     this.jdField_a_of_type_Nd = paramnd;
/* 112 */     paramnd = (paramJFrame = ElementKeyMap.typeList()).length; for (nd localnd = 0; localnd < paramnd; localnd++) { short s = paramJFrame[localnd];
/* 113 */       this.jdField_a_of_type_JavaUtilHashSet.add(ElementKeyMap.getInfo(s));
/*     */     }
/*     */ 
/* 116 */     setAlwaysOnTop(true);
/*     */ 
/* 118 */     setBounds(100, 100, 702, 440);
/* 119 */     getContentPane().setLayout(new BorderLayout());
/*     */ 
/* 121 */     this.jdField_a_of_type_JavaxSwingJTabbedPane = new JTabbedPane(1);
/* 122 */     getContentPane().add(this.jdField_a_of_type_JavaxSwingJTabbedPane, "Center");
/*     */ 
/* 124 */     this.jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/* 125 */     this.jdField_a_of_type_JavaxSwingJTabbedPane.addTab("Search", null, this.jdField_a_of_type_JavaxSwingJPanel, null);
/* 126 */     (
/* 127 */       paramJFrame = new GridBagLayout()).columnWidths = 
/* 127 */       new int[] { 0, 0 };
/* 128 */     paramJFrame.rowHeights = new int[] { 0, 0, 0 };
/* 129 */     paramJFrame.columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 130 */     paramJFrame.rowWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/* 131 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout(paramJFrame);
/*     */ 
/* 133 */     this.jdField_a_of_type_JavaxSwingJTextField = new JTextField("");
/* 134 */     (
/* 135 */       paramnd = new GridBagConstraints()).insets = 
/* 135 */       new Insets(0, 0, 5, 0);
/* 136 */     paramnd.fill = 2;
/* 137 */     paramnd.gridx = 0;
/* 138 */     paramnd.gridy = 0;
/* 139 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJTextField, paramnd);
/* 140 */     this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/*     */ 
/* 142 */     this.jdField_a_of_type_JavaxSwingJTextField.addKeyListener(new ng(this));
/*     */ 
/* 164 */     this.jdField_a_of_type_JavaxSwingJList = new JList();
/* 165 */     this.jdField_a_of_type_JavaxSwingJList.addMouseListener(new nh(this));
/*     */ 
/* 182 */     this.jdField_a_of_type_JavaxSwingJList.setSelectionMode(0);
/* 183 */     (
/* 184 */       paramnd = new GridBagConstraints()).fill = 
/* 184 */       1;
/* 185 */     paramnd.gridx = 0;
/* 186 */     paramnd.gridy = 1;
/* 187 */     this.jdField_a_of_type_JavaxSwingJPanel.add(new JScrollPane(this.jdField_a_of_type_JavaxSwingJList), paramnd);
/*     */ 
/* 191 */     this.b = new JPanel();
/* 192 */     this.jdField_a_of_type_JavaxSwingJTabbedPane.addTab("Tree", null, this.b, null);
/* 193 */     this.b.setLayout(new GridLayout(0, 1, 0, 0));
/*     */ 
/* 195 */     this.jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel = new ElementTreePanel(this);
/* 196 */     this.jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel.a().setEditable(false);
/* 197 */     this.jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel.a().addMouseListener(new ni(this));
/*     */ 
/* 215 */     this.b.add(this.jdField_a_of_type_OrgSchemaGameCommonFaceditElementTreePanel);
/*     */ 
/* 221 */     (
/* 222 */       paramJFrame = new JPanel())
/* 222 */       .setLayout(new FlowLayout(2));
/* 223 */     getContentPane().add(paramJFrame, "South");
/*     */ 
/* 225 */     (
/* 226 */       paramnd = new JButton("OK"))
/* 226 */       .setActionCommand("OK");
/* 227 */     paramJFrame.add(paramnd);
/* 228 */     getRootPane().setDefaultButton(paramnd);
/* 229 */     paramnd.addActionListener(new nj(this));
/*     */ 
/* 240 */     (
/* 241 */       paramnd = new JButton("Cancel"))
/* 241 */       .setActionCommand("Cancel");
/* 242 */     paramnd.addActionListener(new nk(this));
/*     */ 
/* 249 */     paramJFrame.add(paramnd);
/*     */ 
/* 253 */     a();
/*     */   }
/*     */ 
/*     */   public final void valueChanged(TreeSelectionEvent paramTreeSelectionEvent)
/*     */   {
/* 260 */     if (((
/* 260 */       paramTreeSelectionEvent = (DefaultMutableTreeNode)paramTreeSelectionEvent.getPath()
/* 259 */       .getLastPathComponent())
/* 260 */       .getUserObject() instanceof ElementInformation)) {
/* 261 */       paramTreeSelectionEvent = (ElementInformation)paramTreeSelectionEvent.getUserObject();
/* 262 */       this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramTreeSelectionEvent;
/* 263 */       System.err.println("NOW SELECTED " + this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ne
 * JD-Core Version:    0.6.2
 */