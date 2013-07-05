/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
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
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ 
/*     */ public final class nt extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 5531493462948253661L;
/*  35 */   private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*     */   private oK jdField_a_of_type_OK;
/*     */   private JButton jdField_a_of_type_JavaxSwingJButton;
/*     */   private JButton b;
/*     */   private JList jdField_a_of_type_JavaxSwingJList;
/*     */ 
/*     */   public nt(JFrame paramJFrame, Set paramSet)
/*     */   {
/*  59 */     super(paramJFrame, true);
/*     */ 
/*  61 */     HashSet localHashSet = new HashSet();
/*     */ 
/*  63 */     for (Object localObject1 = paramSet.iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (Short)((Iterator)localObject1).next();
/*  64 */       localHashSet.add(ElementKeyMap.getInfo(((Short)localObject2).shortValue()));
/*     */     }
/*  66 */     setBounds(100, 100, 450, 300);
/*  67 */     getContentPane().setLayout(new BorderLayout());
/*  68 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  69 */     getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  70 */     (
/*  71 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  71 */       new int[] { 224, 200 };
/*  72 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
/*  73 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 0.0D };
/*  74 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D };
/*  75 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*     */ 
/*  77 */     this.jdField_a_of_type_JavaxSwingJButton = new JButton("Add");
/*  78 */     (
/*  79 */       localObject2 = new GridBagConstraints()).insets = 
/*  79 */       new Insets(0, 0, 5, 5);
/*  80 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  81 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  82 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJButton, localObject2);
/*     */ 
/*  87 */     this.b = new JButton("Delete");
/*  88 */     (
/*  89 */       localObject2 = new GridBagConstraints()).weightx = 
/*  89 */       1.0D;
/*  90 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
/*  91 */     ((GridBagConstraints)localObject2).gridx = 1;
/*  92 */     ((GridBagConstraints)localObject2).gridy = 0;
/*     */ 
/*  94 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.b, localObject2);
/*     */ 
/*  97 */     Object localObject2 = new JScrollPane();
/*  98 */     (
/*  99 */       localObject1 = new GridBagConstraints()).weighty = 
/*  99 */       1.0D;
/* 100 */     ((GridBagConstraints)localObject1).weightx = 1.0D;
/* 101 */     ((GridBagConstraints)localObject1).gridwidth = 2;
/* 102 */     ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
/* 103 */     ((GridBagConstraints)localObject1).fill = 1;
/* 104 */     ((GridBagConstraints)localObject1).gridx = 0;
/* 105 */     ((GridBagConstraints)localObject1).gridy = 1;
/* 106 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject1);
/*     */ 
/* 108 */     this.jdField_a_of_type_JavaxSwingJList = new JList();
/* 109 */     this.jdField_a_of_type_OK = new oK();
/* 110 */     this.jdField_a_of_type_JavaxSwingJList.setModel(this.jdField_a_of_type_OK);
/* 111 */     localObject1 = localHashSet.iterator();
/* 112 */     while (((Iterator)localObject1).hasNext()) {
/* 113 */       this.jdField_a_of_type_OK.a((Comparable)((Iterator)localObject1).next());
/*     */     }
/* 115 */     ((JScrollPane)localObject2).setViewportView(this.jdField_a_of_type_JavaxSwingJList);
/*     */ 
/* 119 */     (
/* 120 */       localObject2 = new JPanel())
/* 120 */       .setLayout(new FlowLayout(2));
/* 121 */     getContentPane().add((Component)localObject2, "South");
/*     */ 
/* 123 */     (
/* 124 */       localObject1 = new JButton("OK"))
/* 124 */       .setActionCommand("OK");
/* 125 */     ((JPanel)localObject2).add((Component)localObject1);
/* 126 */     getRootPane().setDefaultButton((JButton)localObject1);
/*     */ 
/* 128 */     ((JButton)localObject1).addActionListener(new nu(this, localHashSet, paramSet));
/*     */ 
/* 143 */     (
/* 144 */       localObject1 = new JButton("Cancel"))
/* 144 */       .setActionCommand("Cancel");
/*     */ 
/* 146 */     ((JButton)localObject1).addActionListener(new nv(this));
/*     */ 
/* 153 */     ((JPanel)localObject2).add((Component)localObject1);
/*     */ 
/* 157 */     this.jdField_a_of_type_JavaxSwingJButton.addActionListener(new nw(this, paramJFrame));
/*     */ 
/* 171 */     this.b.addActionListener(new ny(this));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     nt
 * JD-Core Version:    0.6.2
 */