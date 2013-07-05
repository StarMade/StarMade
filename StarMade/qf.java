/*     */ import java.awt.Component;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.ListModel;
/*     */ import org.schema.game.common.staremote.Staremote;
/*     */ 
/*     */ public final class qf extends JPanel
/*     */ {
/*  31 */   private Object jdField_a_of_type_JavaLangObject = new Object();
/*     */   private static final long serialVersionUID = -2984518551942439061L;
/*     */   private JButton jdField_a_of_type_JavaxSwingJButton;
/*     */   private JButton b;
/*  36 */   Boolean jdField_a_of_type_JavaLangBoolean = Boolean.valueOf(false);
/*     */ 
/*     */   public qf(JFrame paramJFrame, Staremote paramStaremote) {
/*  39 */     (
/*  40 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  40 */       new int[] { 0, 0 };
/*  41 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
/*  42 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/*  43 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 1.0D, 1.0D, 4.9E-324D };
/*  44 */     setLayout((LayoutManager)localObject1);
/*     */ 
/*  46 */     Object localObject1 = new JPanel();
/*  47 */     (
/*  48 */       localObject2 = new GridBagConstraints()).weighty = 
/*  48 */       5.0D;
/*  49 */     ((GridBagConstraints)localObject2).weightx = 1.0D;
/*  50 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/*  51 */     ((GridBagConstraints)localObject2).fill = 1;
/*  52 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  53 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  54 */     add((Component)localObject1, localObject2);
/*  55 */     (
/*  56 */       localObject2 = new GridBagLayout()).columnWidths = 
/*  56 */       new int[] { 0, 0, 0 };
/*  57 */     ((GridBagLayout)localObject2).rowHeights = new int[] { 0, 0 };
/*  58 */     ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
/*  59 */     ((GridBagLayout)localObject2).rowWeights = new double[] { 1.0D, 4.9E-324D };
/*  60 */     ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
/*     */ 
/*  62 */     Object localObject2 = new JPanel();
/*  63 */     (
/*  64 */       localObject3 = new GridBagConstraints()).weightx = 
/*  64 */       1.0D;
/*  65 */     ((GridBagConstraints)localObject3).insets = new Insets(0, 0, 5, 0);
/*  66 */     ((GridBagConstraints)localObject3).fill = 1;
/*  67 */     ((GridBagConstraints)localObject3).gridx = 0;
/*  68 */     ((GridBagConstraints)localObject3).gridy = 0;
/*  69 */     ((JPanel)localObject1).add((Component)localObject2, localObject3);
/*  70 */     (
/*  71 */       localObject3 = new GridBagLayout()).columnWidths = 
/*  71 */       new int[] { 0, 0 };
/*  72 */     ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/*  73 */     ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/*  74 */     ((GridBagLayout)localObject3).rowWeights = new double[] { 1.0D, 4.9E-324D };
/*  75 */     ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
/*     */ 
/*  77 */     Object localObject3 = new JScrollPane();
/*  78 */     (
/*  79 */       localObject4 = new GridBagConstraints()).fill = 
/*  79 */       1;
/*  80 */     ((GridBagConstraints)localObject4).gridx = 0;
/*  81 */     ((GridBagConstraints)localObject4).gridy = 0;
/*  82 */     ((JPanel)localObject2).add((Component)localObject3, localObject4);
/*     */ 
/*  84 */     localObject2 = new qa();
/*  85 */     Object localObject4 = new JList((ListModel)localObject2);
/*     */ 
/*  87 */     ((JScrollPane)localObject3).setViewportView((Component)localObject4);
/*     */ 
/*  89 */     localObject3 = new JPanel();
/*     */     Object localObject5;
/*  90 */     (
/*  91 */       localObject5 = new GridBagConstraints()).weightx = 
/*  91 */       0.001D;
/*  92 */     ((GridBagConstraints)localObject5).fill = 1;
/*  93 */     ((GridBagConstraints)localObject5).gridx = 1;
/*  94 */     ((GridBagConstraints)localObject5).gridy = 0;
/*  95 */     ((JPanel)localObject1).add((Component)localObject3, localObject5);
/*  96 */     (
/*  97 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  97 */       new int[] { 0 };
/*  98 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*  99 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 4.9E-324D };
/* 100 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/* 101 */     ((JPanel)localObject3).setLayout((LayoutManager)localObject1);
/*     */ 
/* 103 */     (
/* 104 */       localObject1 = new JButton("Connect"))
/* 104 */       .setEnabled(false);
/* 105 */     ((JButton)localObject1).addActionListener(new qg((JList)localObject4, paramJFrame, paramStaremote));
/*     */ 
/* 115 */     (
/* 116 */       localObject5 = new GridBagConstraints()).fill = 
/* 116 */       2;
/* 117 */     ((GridBagConstraints)localObject5).anchor = 13;
/* 118 */     ((GridBagConstraints)localObject5).insets = new Insets(0, 0, 5, 0);
/* 119 */     ((GridBagConstraints)localObject5).gridx = 0;
/* 120 */     ((GridBagConstraints)localObject5).gridy = 0;
/* 121 */     ((JPanel)localObject3).add((Component)localObject1, localObject5);
/*     */ 
/* 123 */     (
/* 124 */       localObject5 = new JButton("Add Connection"))
/* 124 */       .addActionListener(new qh(paramJFrame, (qa)localObject2));
/*     */     GridBagConstraints localGridBagConstraints;
/* 129 */     (
/* 130 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 130 */       2;
/* 131 */     localGridBagConstraints.anchor = 13;
/* 132 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 133 */     localGridBagConstraints.gridx = 0;
/* 134 */     localGridBagConstraints.gridy = 2;
/* 135 */     ((JPanel)localObject3).add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 137 */     this.b = new JButton("Edit Connection ");
/* 138 */     this.b.addActionListener(new qi(this, (JList)localObject4, (JButton)localObject1, paramJFrame, (qa)localObject2));
/*     */ 
/* 150 */     this.b.setEnabled(false);
/* 151 */     (
/* 152 */       localObject5 = new GridBagConstraints()).insets = 
/* 152 */       new Insets(0, 0, 5, 0);
/* 153 */     ((GridBagConstraints)localObject5).fill = 2;
/* 154 */     ((GridBagConstraints)localObject5).anchor = 13;
/* 155 */     ((GridBagConstraints)localObject5).gridx = 0;
/* 156 */     ((GridBagConstraints)localObject5).gridy = 3;
/* 157 */     ((JPanel)localObject3).add(this.b, localObject5);
/*     */ 
/* 159 */     this.jdField_a_of_type_JavaxSwingJButton = new JButton("Remove Connection");
/* 160 */     this.jdField_a_of_type_JavaxSwingJButton.setEnabled(false);
/* 161 */     this.jdField_a_of_type_JavaxSwingJButton.addActionListener(new qj(this, (JList)localObject4, (JButton)localObject1, (qa)localObject2));
/*     */ 
/* 175 */     (
/* 176 */       localObject2 = new JButton("Uplink Settings"))
/* 176 */       .addActionListener(new qk());
/*     */ 
/* 183 */     (
/* 184 */       localObject5 = new GridBagConstraints()).anchor = 
/* 184 */       13;
/* 185 */     ((GridBagConstraints)localObject5).fill = 2;
/* 186 */     ((GridBagConstraints)localObject5).insets = new Insets(0, 0, 5, 0);
/* 187 */     ((GridBagConstraints)localObject5).gridx = 0;
/* 188 */     ((GridBagConstraints)localObject5).gridy = 5;
/* 189 */     ((JPanel)localObject3).add((Component)localObject2, localObject5);
/* 190 */     (
/* 191 */       localObject2 = new GridBagConstraints()).fill = 
/* 191 */       2;
/* 192 */     ((GridBagConstraints)localObject2).anchor = 13;
/* 193 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 194 */     ((GridBagConstraints)localObject2).gridy = 7;
/* 195 */     ((JPanel)localObject3).add(this.jdField_a_of_type_JavaxSwingJButton, localObject2);
/*     */ 
/* 197 */     localObject2 = new JPanel();
/* 198 */     (
/* 199 */       localObject3 = new GridBagConstraints()).weighty = 
/* 199 */       1.0D;
/* 200 */     ((GridBagConstraints)localObject3).weightx = 0.2D;
/* 201 */     ((GridBagConstraints)localObject3).fill = 1;
/* 202 */     ((GridBagConstraints)localObject3).gridx = 0;
/* 203 */     ((GridBagConstraints)localObject3).gridy = 1;
/* 204 */     add((Component)localObject2, localObject3);
/* 205 */     (
/* 206 */       localObject3 = new GridBagLayout()).columnWidths = 
/* 206 */       new int[] { 0, 0 };
/* 207 */     ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/* 208 */     ((GridBagLayout)localObject3).columnWeights = new double[] { 0.0D, 4.9E-324D };
/* 209 */     ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 210 */     ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
/*     */ 
/* 212 */     (
/* 213 */       localObject3 = new JButton("   Exit   "))
/* 213 */       .addActionListener(new ql());
/*     */ 
/* 218 */     (
/* 219 */       localObject5 = new GridBagConstraints()).insets = 
/* 219 */       new Insets(5, 0, 5, 5);
/* 220 */     ((GridBagConstraints)localObject5).weightx = 1.0D;
/* 221 */     ((GridBagConstraints)localObject5).anchor = 13;
/* 222 */     ((GridBagConstraints)localObject5).gridx = 0;
/* 223 */     ((GridBagConstraints)localObject5).gridy = 0;
/* 224 */     ((JPanel)localObject2).add((Component)localObject3, localObject5);
/*     */ 
/* 226 */     ((JList)localObject4).addListSelectionListener(new qm(this, (JButton)localObject1, (JList)localObject4));
/*     */ 
/* 233 */     ((JList)localObject4).addMouseListener(new c((JList)localObject4, new qn(this, (JList)localObject4, paramJFrame, paramStaremote)));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qf
 * JD-Core Version:    0.6.2
 */