/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public final class po extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 5674993253545810749L;
/*     */ 
/*     */   public po()
/*     */   {
/*  25 */     (
/*  26 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  26 */       new int[] { 0, 0, 0 };
/*  27 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*  28 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/*  29 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  30 */     setLayout((LayoutManager)localObject1);
/*     */ 
/*  32 */     Object localObject1 = new JLabel(" Tutorial:");
/*  33 */     (
/*  34 */       localObject2 = new GridBagConstraints()).anchor = 
/*  34 */       17;
/*  35 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/*  36 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  37 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  38 */     add((Component)localObject1, localObject2);
/*     */ 
/*  40 */     localObject1 = new pH(xu.O);
/*  41 */     (
/*  42 */       localObject2 = new GridBagConstraints()).insets = 
/*  42 */       new Insets(0, 0, 5, 0);
/*  43 */     ((GridBagConstraints)localObject2).fill = 2;
/*  44 */     ((GridBagConstraints)localObject2).gridx = 1;
/*  45 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  46 */     add((Component)localObject1, localObject2);
/*     */ 
/*  48 */     (
/*  49 */       localObject1 = new JLabel("Graphics Settings"))
/*  49 */       .setFont(new Font("Arial", 0, 13));
/*  50 */     (
/*  51 */       localObject2 = new GridBagConstraints()).anchor = 
/*  51 */       17;
/*  52 */     ((GridBagConstraints)localObject2).gridwidth = 2;
/*  53 */     ((GridBagConstraints)localObject2).insets = new Insets(5, 0, 5, 0);
/*  54 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  55 */     ((GridBagConstraints)localObject2).gridy = 1;
/*  56 */     add((Component)localObject1, localObject2);
/*     */ 
/*  59 */     localObject1 = new JLabel(" Resolution: ");
/*  60 */     (
/*  61 */       localObject2 = new GridBagConstraints()).insets = 
/*  61 */       new Insets(0, 5, 5, 5);
/*  62 */     ((GridBagConstraints)localObject2).anchor = 17;
/*  63 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  64 */     ((GridBagConstraints)localObject2).gridy = 2;
/*  65 */     add((Component)localObject1, localObject2);
/*     */ 
/*  69 */     Object localObject2 = new pH(xu.b);
/*  70 */     (
/*  71 */       localObject3 = new GridBagConstraints()).insets = 
/*  71 */       new Insets(0, 0, 5, 0);
/*  72 */     ((GridBagConstraints)localObject3).fill = 2;
/*  73 */     ((GridBagConstraints)localObject3).gridx = 1;
/*  74 */     ((GridBagConstraints)localObject3).gridy = 2;
/*  75 */     add((Component)localObject2, localObject3);
/*     */ 
/*  77 */     Object localObject3 = new JLabel(" Fullscreen: ");
/*  78 */     (
/*  79 */       localObject4 = new GridBagConstraints()).anchor = 
/*  79 */       17;
/*  80 */     ((GridBagConstraints)localObject4).insets = new Insets(0, 5, 5, 5);
/*  81 */     ((GridBagConstraints)localObject4).gridx = 0;
/*  82 */     ((GridBagConstraints)localObject4).gridy = 3;
/*  83 */     add((Component)localObject3, localObject4);
/*     */ 
/*  87 */     Object localObject4 = new pH(xu.c);
/*  88 */     (
/*  89 */       localObject5 = new GridBagConstraints()).insets = 
/*  89 */       new Insets(0, 0, 5, 0);
/*  90 */     ((GridBagConstraints)localObject5).fill = 2;
/*  91 */     ((GridBagConstraints)localObject5).gridx = 1;
/*  92 */     ((GridBagConstraints)localObject5).gridy = 3;
/*  93 */     add((Component)localObject4, localObject5);
/*     */ 
/*  95 */     Object localObject5 = new JLabel(" Field of View (FOV): ");
/*     */     GridBagConstraints localGridBagConstraints;
/*  96 */     (
/*  97 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/*  97 */       17;
/*  98 */     localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  99 */     localGridBagConstraints.gridx = 0;
/* 100 */     localGridBagConstraints.gridy = 4;
/* 101 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 103 */     localObject5 = new pH(xu.f);
/* 104 */     (
/* 105 */       localGridBagConstraints = new GridBagConstraints()).insets = 
/* 105 */       new Insets(0, 0, 5, 0);
/* 106 */     localGridBagConstraints.fill = 2;
/* 107 */     localGridBagConstraints.gridx = 1;
/* 108 */     localGridBagConstraints.gridy = 4;
/* 109 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 111 */     (
/* 112 */       localObject5 = new JLabel("Sound Settings"))
/* 112 */       .setFont(new Font("Arial", 0, 13));
/* 113 */     (
/* 114 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 114 */       17;
/* 115 */     localGridBagConstraints.gridwidth = 2;
/* 116 */     localGridBagConstraints.insets = new Insets(10, 0, 5, 0);
/* 117 */     localGridBagConstraints.gridx = 0;
/* 118 */     localGridBagConstraints.gridy = 5;
/* 119 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 121 */     localObject5 = new JLabel(" Enable Sound: ");
/* 122 */     (
/* 123 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 123 */       17;
/* 124 */     localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/* 125 */     localGridBagConstraints.gridx = 0;
/* 126 */     localGridBagConstraints.gridy = 6;
/* 127 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 129 */     localObject5 = new pH(xu.g);
/* 130 */     (
/* 131 */       localGridBagConstraints = new GridBagConstraints()).insets = 
/* 131 */       new Insets(0, 0, 5, 0);
/* 132 */     localGridBagConstraints.fill = 2;
/* 133 */     localGridBagConstraints.gridx = 1;
/* 134 */     localGridBagConstraints.gridy = 6;
/* 135 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 137 */     localObject5 = new JLabel(" Sound Volume: ");
/* 138 */     (
/* 139 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 139 */       17;
/* 140 */     localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/* 141 */     localGridBagConstraints.gridx = 0;
/* 142 */     localGridBagConstraints.gridy = 7;
/* 143 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 145 */     localObject5 = new pH(xu.i);
/* 146 */     (
/* 147 */       localGridBagConstraints = new GridBagConstraints()).insets = 
/* 147 */       new Insets(0, 0, 5, 0);
/* 148 */     localGridBagConstraints.fill = 2;
/* 149 */     localGridBagConstraints.gridx = 1;
/* 150 */     localGridBagConstraints.gridy = 7;
/* 151 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 153 */     (
/* 154 */       localObject5 = new JLabel("Control Settings"))
/* 154 */       .setFont(new Font("Arial", 0, 13));
/* 155 */     (
/* 156 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 156 */       16;
/* 157 */     localGridBagConstraints.gridwidth = 2;
/* 158 */     localGridBagConstraints.insets = new Insets(10, 0, 5, 0);
/* 159 */     localGridBagConstraints.gridx = 0;
/* 160 */     localGridBagConstraints.gridy = 8;
/* 161 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 163 */     localObject5 = new JLabel(" Invert Mouse (All):");
/* 164 */     (
/* 165 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 165 */       17;
/* 166 */     localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/* 167 */     localGridBagConstraints.gridx = 0;
/* 168 */     localGridBagConstraints.gridy = 9;
/* 169 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 171 */     localObject5 = new pH(xu.Y);
/* 172 */     (
/* 173 */       localGridBagConstraints = new GridBagConstraints()).insets = 
/* 173 */       new Insets(0, 0, 5, 0);
/* 174 */     localGridBagConstraints.fill = 2;
/* 175 */     localGridBagConstraints.gridx = 1;
/* 176 */     localGridBagConstraints.gridy = 9;
/* 177 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 179 */     localObject5 = new JLabel(" Invert Ship Mouse:");
/* 180 */     (
/* 181 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 181 */       17;
/* 182 */     localGridBagConstraints.insets = new Insets(0, 5, 0, 5);
/* 183 */     localGridBagConstraints.gridx = 0;
/* 184 */     localGridBagConstraints.gridy = 10;
/* 185 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 187 */     localObject5 = new pH(xu.X);
/* 188 */     (
/* 189 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 189 */       2;
/* 190 */     localGridBagConstraints.gridx = 1;
/* 191 */     localGridBagConstraints.gridy = 10;
/* 192 */     add((Component)localObject5, localGridBagConstraints);
/*     */ 
/* 195 */     ((JLabel)localObject3).setLabelFor((Component)localObject4);
/* 196 */     ((JLabel)localObject1).setLabelFor((Component)localObject2);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     po
 * JD-Core Version:    0.6.2
 */