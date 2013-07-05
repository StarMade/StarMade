/*     */ package org.schema.game.common.updater;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import sf;
/*     */ import sg;
/*     */ import sh;
/*     */ import si;
/*     */ import sj;
/*     */ import sm;
/*     */ import sr;
/*     */ import sy;
/*     */ 
/*     */ public class Launcher extends JFrame
/*     */ {
/*     */   public static int a;
/*     */   private static final long serialVersionUID = -2537463060839705206L;
/*     */   private JPanel a;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  41 */     System.setProperty("http.agent", "");
/*     */ 
/*  43 */     if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].equals("-nogui"))) {
/*  44 */       if (paramArrayOfString.length > 1) paramArrayOfString[1].equals("-force"); sy.a(); return;
/*     */     }
/*  46 */     EventQueue.invokeLater(new sf());
/*     */   }
/*     */ 
/*     */   public Launcher()
/*     */   {
/*  67 */     setTitle("StarMade [Launcher v" + jdField_a_of_type_Int + "]");
/*  68 */     setDefaultCloseOperation(3);
/*  69 */     setBounds(100, 100, 849, 551);
/*     */ 
/*  71 */     Object localObject1 = new JMenuBar();
/*  72 */     setJMenuBar((JMenuBar)localObject1);
/*     */ 
/*  74 */     Object localObject2 = new JMenu("Options");
/*  75 */     ((JMenuBar)localObject1).add((JMenu)localObject2);
/*     */ 
/*  77 */     (
/*  78 */       localObject1 = new JMenuItem("Memory Settings"))
/*  78 */       .addActionListener(new sg());
/*     */ 
/*  86 */     ((JMenu)localObject2).add((JMenuItem)localObject1);
/*     */ 
/*  88 */     (
/*  89 */       localObject1 = new JMenuItem("Mirror Settings"))
/*  89 */       .addActionListener(new sh(this));
/*     */ 
/* 110 */     ((JMenu)localObject2).add((JMenuItem)localObject1);
/*     */ 
/* 112 */     (
/* 113 */       localObject1 = new JMenuItem("Server Port"))
/* 113 */       .addActionListener(new si(this));
/*     */ 
/* 147 */     ((JMenu)localObject2).add((JMenuItem)localObject1);
/* 148 */     this.jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/* 149 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 150 */     setContentPane(this.jdField_a_of_type_JavaxSwingJPanel);
/* 151 */     (
/* 152 */       localObject1 = new GridBagLayout()).columnWidths = 
/* 152 */       new int[] { 0, 0 };
/* 153 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0 };
/* 154 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 155 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 1.0D, 1.0D, 1.0D, 4.9E-324D };
/* 156 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*     */ 
/* 158 */     (
/* 159 */       localObject1 = new JScrollPane())
/* 159 */       .setPreferredSize(new Dimension(400, 100));
/* 160 */     ((JScrollPane)localObject1).setMinimumSize(new Dimension(23, 30));
/* 161 */     (
/* 162 */       localObject2 = new GridBagConstraints()).fill = 
/* 162 */       1;
/* 163 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 164 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 165 */     ((GridBagConstraints)localObject2).gridy = 0;
/* 166 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/* 168 */     (
/* 169 */       localObject2 = new JTextPane())
/* 169 */       .setPreferredSize(new Dimension(300, 30));
/* 170 */     ((JScrollPane)localObject1).setViewportView((Component)localObject2);
/* 171 */     ((JTextPane)localObject2).setText("If the download gets stuck, launching the starter as Administrator helps in some cases. You can also download the latest Version manually from http://files.star-made.org/build/ and extract it to the directory where this launcher is located.\r\n\r\nIntel graphics cards are known to have buggy drivers in old versions, so be sure to update to the newest version.\r\n\r\nThere is a CrashAndBugReporter.jar in the StarMade directory if you want to send in a crash report manually.\r\nPlease use 64 bit java for maximal performance.\r\nIf you have any questions about the game, feel free to mail me at schema@star-made.org\r\n\r\nHave fun playing!");
/* 172 */     SwingUtilities.invokeLater(new sj((JScrollPane)localObject1));
/*     */ 
/* 181 */     (
/* 182 */       localObject1 = new sm())
/* 182 */       .setMinimumSize(new Dimension(450, 200));
/* 183 */     (
/* 184 */       localObject2 = new GridBagConstraints()).weighty = 
/* 184 */       3.0D;
/* 185 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 186 */     ((GridBagConstraints)localObject2).fill = 1;
/* 187 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 188 */     ((GridBagConstraints)localObject2).gridy = 1;
/* 189 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/* 191 */     localObject1 = new sr();
/* 192 */     (
/* 193 */       localObject2 = new GridBagConstraints()).weighty = 
/* 193 */       1.0D;
/* 194 */     ((GridBagConstraints)localObject2).fill = 1;
/* 195 */     ((GridBagConstraints)localObject2).weightx = 1.0D;
/* 196 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 197 */     ((GridBagConstraints)localObject2).gridy = 2;
/* 198 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  31 */     jdField_a_of_type_Int = 9;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.updater.Launcher
 * JD-Core Version:    0.6.2
 */