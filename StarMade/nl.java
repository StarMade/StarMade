/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ 
/*     */ public final class nl extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 5531493462948253661L;
/*  33 */   private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*     */   private oK jdField_a_of_type_OK;
/*     */   private JButton jdField_a_of_type_JavaxSwingJButton;
/*     */   private JButton b;
/*     */   private JList jdField_a_of_type_JavaxSwingJList;
/*     */   private JButton c;
/*     */ 
/*     */   public nl(JFrame paramJFrame, ElementInformation paramElementInformation, ol paramol)
/*     */   {
/*  66 */     super(paramJFrame, true);
/*     */     oy localoy;
/*  67 */     (
/*  71 */       localoy = new oy())
/*  71 */       .a(paramElementInformation.id);
/*  72 */     HashSet localHashSet = new HashSet();
/*     */ 
/*  74 */     for (Object localObject1 = localoy.a.iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (oz)((Iterator)localObject1).next();
/*  75 */       localHashSet.add(localObject2);
/*     */     }
/*  77 */     setBounds(100, 100, 450, 300);
/*  78 */     getContentPane().setLayout(new BorderLayout());
/*  79 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  80 */     getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  81 */     (
/*  82 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  82 */       new int[] { 50, 0, 200 };
/*  83 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
/*  84 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 0.0D, 0.0D };
/*  85 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D };
/*  86 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*     */ 
/*  88 */     this.jdField_a_of_type_JavaxSwingJButton = new JButton("Edit");
/*  89 */     (
/*  90 */       localObject2 = new GridBagConstraints()).anchor = 
/*  90 */       17;
/*  91 */     ((GridBagConstraints)localObject2).weightx = 1.0D;
/*  92 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
/*  93 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  94 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  95 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJButton, localObject2);
/*     */ 
/* 100 */     this.c = new JButton("Add");
/* 101 */     (
/* 102 */       localObject2 = new GridBagConstraints()).anchor = 
/* 102 */       17;
/* 103 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
/* 104 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 105 */     ((GridBagConstraints)localObject2).gridy = 0;
/* 106 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.c, localObject2);
/*     */ 
/* 109 */     this.b = new JButton("Delete");
/* 110 */     (
/* 111 */       localObject2 = new GridBagConstraints()).anchor = 
/* 111 */       13;
/* 112 */     ((GridBagConstraints)localObject2).weightx = 1.0D;
/* 113 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 114 */     ((GridBagConstraints)localObject2).gridx = 2;
/* 115 */     ((GridBagConstraints)localObject2).gridy = 0;
/*     */ 
/* 117 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.b, localObject2);
/*     */ 
/* 120 */     Object localObject2 = new JScrollPane();
/* 121 */     (
/* 122 */       localObject1 = new GridBagConstraints()).weighty = 
/* 122 */       1.0D;
/* 123 */     ((GridBagConstraints)localObject1).weightx = 1.0D;
/* 124 */     ((GridBagConstraints)localObject1).gridwidth = 3;
/* 125 */     ((GridBagConstraints)localObject1).fill = 1;
/* 126 */     ((GridBagConstraints)localObject1).gridx = 0;
/* 127 */     ((GridBagConstraints)localObject1).gridy = 1;
/* 128 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject1);
/*     */ 
/* 130 */     this.jdField_a_of_type_JavaxSwingJList = new JList();
/* 131 */     this.jdField_a_of_type_OK = new oK();
/* 132 */     this.jdField_a_of_type_JavaxSwingJList.setModel(this.jdField_a_of_type_OK);
/* 133 */     localObject1 = localHashSet.iterator();
/* 134 */     while (((Iterator)localObject1).hasNext()) {
/* 135 */       this.jdField_a_of_type_OK.a((Comparable)((Iterator)localObject1).next());
/*     */     }
/* 137 */     ((JScrollPane)localObject2).setViewportView(this.jdField_a_of_type_JavaxSwingJList);
/*     */ 
/* 141 */     (
/* 142 */       localObject2 = new JPanel())
/* 142 */       .setLayout(new FlowLayout(2));
/* 143 */     getContentPane().add((Component)localObject2, "South");
/*     */ 
/* 145 */     (
/* 146 */       localObject1 = new JButton("OK"))
/* 146 */       .setActionCommand("OK");
/* 147 */     ((JPanel)localObject2).add((Component)localObject1);
/* 148 */     getRootPane().setDefaultButton((JButton)localObject1);
/*     */ 
/* 150 */     ((JButton)localObject1).addActionListener(new nm(this, localHashSet, localoy, paramElementInformation, paramol));
/*     */ 
/* 174 */     (
/* 175 */       localObject1 = new JButton("Cancel"))
/* 175 */       .setActionCommand("Cancel");
/*     */ 
/* 177 */     ((JButton)localObject1).addActionListener(new nn(this));
/*     */ 
/* 184 */     ((JPanel)localObject2).add((Component)localObject1);
/*     */ 
/* 188 */     this.jdField_a_of_type_JavaxSwingJButton.addActionListener(new no(this, paramJFrame));
/*     */ 
/* 205 */     this.c.addActionListener(new nq(this, paramJFrame));
/*     */ 
/* 222 */     this.b.addActionListener(new ns(this));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     nl
 * JD-Core Version:    0.6.2
 */