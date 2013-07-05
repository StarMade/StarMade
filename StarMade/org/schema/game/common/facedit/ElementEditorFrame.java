/*     */ package org.schema.game.common.facedit;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.EventQueue;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.ScrollPaneLayout;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.event.TreeSelectionEvent;
/*     */ import javax.swing.event.TreeSelectionListener;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.TreePath;
/*     */ import nA;
/*     */ import nC;
/*     */ import nD;
/*     */ import nE;
/*     */ import nF;
/*     */ import nG;
/*     */ import nH;
/*     */ import nI;
/*     */ import nK;
/*     */ import nL;
/*     */ import nc;
/*     */ import nz;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ 
/*     */ public class ElementEditorFrame extends JFrame
/*     */   implements Observer, TreeSelectionListener
/*     */ {
/*     */   private static final long serialVersionUID = -1919570665689983369L;
/*     */   private JPanel jdField_a_of_type_JavaxSwingJPanel;
/*     */   private JSplitPane jdField_a_of_type_JavaxSwingJSplitPane;
/*     */   private JMenuBar jdField_a_of_type_JavaxSwingJMenuBar;
/*     */   private JMenu jdField_a_of_type_JavaxSwingJMenu;
/*     */   private JMenu jdField_b_of_type_JavaxSwingJMenu;
/*     */   private JMenuItem jdField_a_of_type_JavaxSwingJMenuItem;
/*     */   private JMenuItem jdField_b_of_type_JavaxSwingJMenuItem;
/*     */   private JMenuItem c;
/*     */   private JMenuItem d;
/*     */   private JMenuItem e;
/*     */   private JMenuItem f;
/*     */   private JMenuItem g;
/*  54 */   private nc jdField_a_of_type_Nc = new nc();
/*     */   private JMenuItem h;
/*     */   private JMenuItem i;
/*     */   private JFileChooser jdField_a_of_type_JavaxSwingJFileChooser;
/*     */   private JMenuItem j;
/*     */   private JMenuItem k;
/*     */   private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  66 */     EventQueue.invokeLater(new nz());
/*     */   }
/*     */ 
/*     */   public ElementEditorFrame()
/*     */   {
/*  83 */     setTitle("StarMade - Block Editor");
/*  84 */     setDefaultCloseOperation(3);
/*  85 */     setBounds(100, 100, 1200, 700);
/*     */ 
/*  87 */     this.jdField_a_of_type_JavaxSwingJMenuBar = new JMenuBar();
/*  88 */     setJMenuBar(this.jdField_a_of_type_JavaxSwingJMenuBar);
/*     */ 
/*  90 */     this.jdField_a_of_type_JavaxSwingJMenu = new JMenu("File");
/*  91 */     this.jdField_a_of_type_JavaxSwingJMenuBar.add(this.jdField_a_of_type_JavaxSwingJMenu);
/*     */ 
/*  93 */     this.jdField_a_of_type_JavaxSwingJMenuItem = new JMenuItem("New");
/*  94 */     this.jdField_a_of_type_JavaxSwingJMenuItem.addActionListener(new nC(this));
/*     */ 
/* 102 */     this.jdField_a_of_type_JavaxSwingJMenu.add(this.jdField_a_of_type_JavaxSwingJMenuItem);
/*     */ 
/* 104 */     this.d = new JMenuItem("Open");
/* 105 */     this.d.addActionListener(new nD(this));
/*     */ 
/* 115 */     this.jdField_a_of_type_JavaxSwingJMenu.add(this.d);
/*     */ 
/* 117 */     this.jdField_b_of_type_JavaxSwingJMenuItem = new JMenuItem("Save");
/* 118 */     this.jdField_b_of_type_JavaxSwingJMenuItem.addActionListener(new nE(this));
/*     */ 
/* 123 */     this.jdField_a_of_type_JavaxSwingJMenu.add(this.jdField_b_of_type_JavaxSwingJMenuItem);
/*     */ 
/* 125 */     this.c = new JMenuItem("Save As...");
/* 126 */     this.c.addActionListener(new nF(this));
/*     */ 
/* 134 */     this.jdField_a_of_type_JavaxSwingJMenu.add(this.c);
/*     */ 
/* 136 */     this.e = new JMenuItem("Exit");
/* 137 */     this.e.addActionListener(new nG(this));
/*     */ 
/* 142 */     this.jdField_a_of_type_JavaxSwingJMenu.add(this.e);
/*     */ 
/* 144 */     this.jdField_b_of_type_JavaxSwingJMenu = new JMenu("Edit");
/* 145 */     this.jdField_a_of_type_JavaxSwingJMenuBar.add(this.jdField_b_of_type_JavaxSwingJMenu);
/*     */ 
/* 147 */     this.f = new JMenuItem("Move Entry");
/* 148 */     this.f.addActionListener(new nH());
/*     */ 
/* 153 */     this.j = new JMenuItem("Create New Entry");
/* 154 */     this.jdField_b_of_type_JavaxSwingJMenu.add(this.j);
/* 155 */     this.jdField_b_of_type_JavaxSwingJMenu.add(this.f);
/* 156 */     this.j.addActionListener(new nI(this));
/*     */ 
/* 171 */     this.g = new JMenuItem("Duplicate Entry");
/* 172 */     this.g.addActionListener(new nK());
/*     */ 
/* 176 */     this.jdField_b_of_type_JavaxSwingJMenu.add(this.g);
/*     */ 
/* 178 */     this.k = new JMenuItem("Remove Entry");
/* 179 */     this.k.setEnabled(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null);
/* 180 */     this.k.addActionListener(new nA(this));
/*     */ 
/* 209 */     this.jdField_b_of_type_JavaxSwingJMenu.add(this.k);
/*     */ 
/* 211 */     this.h = new JMenuItem("Add Category");
/* 212 */     this.jdField_b_of_type_JavaxSwingJMenu.add(this.h);
/*     */ 
/* 214 */     this.i = new JMenuItem("Remove Category");
/* 215 */     this.jdField_b_of_type_JavaxSwingJMenu.add(this.i);
/* 216 */     this.jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/* 217 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 218 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout(new BorderLayout(0, 0));
/* 219 */     setContentPane(this.jdField_a_of_type_JavaxSwingJPanel);
/*     */ 
/* 221 */     this.jdField_a_of_type_JavaxSwingJSplitPane = new JSplitPane();
/* 222 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJSplitPane, "Center");
/*     */ 
/* 225 */     this.jdField_a_of_type_JavaxSwingJSplitPane.setDividerLocation(400);
/* 226 */     ElementTreePanel localElementTreePanel = new ElementTreePanel(this);
/* 227 */     this.jdField_a_of_type_JavaxSwingJSplitPane.setLeftComponent(new JScrollPane(localElementTreePanel));
/* 228 */     this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(new JLabel("nothing selected"));
/*     */   }
/*     */ 
/*     */   public void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 268 */     paramObservable = new nL(this, ((Short)paramObject).shortValue());
/* 269 */     this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(paramObservable);
/*     */   }
/*     */ 
/*     */   public void valueChanged(TreeSelectionEvent paramTreeSelectionEvent)
/*     */   {
/* 278 */     if (((
/* 278 */       paramTreeSelectionEvent = (DefaultMutableTreeNode)paramTreeSelectionEvent.getPath()
/* 277 */       .getLastPathComponent())
/* 278 */       .getUserObject() instanceof ElementInformation)) {
/* 279 */       System.err.println("Setting content pane: " + paramTreeSelectionEvent.getUserObject());
/* 280 */       this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = ((ElementInformation)paramTreeSelectionEvent.getUserObject());
/* 281 */       this.k.setEnabled(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation != null);
/* 282 */       paramTreeSelectionEvent = new nL(this, this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getId());
/* 283 */       paramTreeSelectionEvent = new JScrollPane(paramTreeSelectionEvent);
/* 284 */       ScrollPaneLayout localScrollPaneLayout = new ScrollPaneLayout();
/* 285 */       paramTreeSelectionEvent.setLayout(localScrollPaneLayout);
/* 286 */       this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(paramTreeSelectionEvent);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.facedit.ElementEditorFrame
 * JD-Core Version:    0.6.2
 */