/*     */ package org.schema.game.common.crashreporter;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Toolkit;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.TitledBorder;
/*     */ import kY;
/*     */ import kZ;
/*     */ 
/*     */ public class CrashReportGUI extends JFrame
/*     */   implements Observer
/*     */ {
/*     */   private static final long serialVersionUID = -4472460272445143389L;
/*     */   private JPanel jdField_a_of_type_JavaxSwingJPanel;
/*     */   private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*     */   private JProgressBar jdField_a_of_type_JavaxSwingJProgressBar;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  41 */     if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].equals("-nogui"))) {
/*  42 */       if (paramArrayOfString.length < 3) {
/*  43 */         System.out.println("You need at least 2 more arguments:\nExample:\njava -jar CrashAndBugReport.jar -nogui myemail@mymaildom.com description of the bug i had");
/*     */ 
/*  46 */         System.exit(0); return;
/*     */       }
/*  48 */       StringBuffer localStringBuffer = new StringBuffer();
/*  49 */       for (int i = 2; i < paramArrayOfString.length; i++) {
/*  50 */         localStringBuffer.append(paramArrayOfString[i] + " ");
/*     */       }
/*  52 */       CrashReporter localCrashReporter = new CrashReporter();
/*     */       try { localCrashReporter.a(paramArrayOfString[1], localStringBuffer.toString());
/*  55 */         localCrashReporter.b();
/*     */         return; } catch (Exception localException) {
/*  58 */         localException.printStackTrace();
/*     */ 
/*  59 */         return;
/*     */       }
/*     */     }
/*  61 */     EventQueue.invokeLater(new kY());
/*     */   }
/*     */ 
/*     */   public CrashReportGUI()
/*     */   {
/*  84 */     setTitle("Report Bug or Crash");
/*  85 */     setDefaultCloseOperation(3);
/*  86 */     setBounds(100, 100, 626, 413);
/*  87 */     this.jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*  88 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  89 */     setContentPane(this.jdField_a_of_type_JavaxSwingJPanel);
/*     */     Object localObject1;
/*  90 */     (
/*  91 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  91 */       new int[] { 0, 0, 0 };
/*  92 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*  93 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 1.0D, 4.9E-324D };
/*  94 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 1.0D, 4.9E-324D };
/*  95 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*     */ 
/*  97 */     (
/*  98 */       localObject1 = new JPanel())
/*  98 */       .setBorder(new TitledBorder(null, "Basic Information", 4, 2, null, null));
/*  99 */     (
/* 100 */       localObject2 = new GridBagConstraints()).gridwidth = 
/* 100 */       2;
/* 101 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 102 */     ((GridBagConstraints)localObject2).fill = 1;
/* 103 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 104 */     ((GridBagConstraints)localObject2).gridy = 0;
/* 105 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 106 */     (
/* 107 */       localObject2 = new GridBagLayout()).columnWidths = 
/* 107 */       new int[] { 198, 112, 86, 0 };
/* 108 */     ((GridBagLayout)localObject2).rowHeights = new int[] { 20, 0 };
/* 109 */     ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 1.0D, 4.9E-324D };
/* 110 */     ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 111 */     ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
/*     */ 
/* 113 */     Object localObject2 = new JLabel("Email (required)");
/*     */     GridBagConstraints localGridBagConstraints;
/* 114 */     (
/* 115 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 115 */       18;
/* 116 */     localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
/* 117 */     localGridBagConstraints.gridx = 0;
/* 118 */     localGridBagConstraints.gridy = 0;
/* 119 */     ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 120 */     ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
/*     */ 
/* 122 */     this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/* 123 */     (
/* 124 */       localObject2 = new GridBagConstraints()).gridwidth = 
/* 124 */       2;
/* 125 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
/* 126 */     ((GridBagConstraints)localObject2).fill = 1;
/* 127 */     ((GridBagConstraints)localObject2).anchor = 18;
/* 128 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 129 */     ((GridBagConstraints)localObject2).gridy = 0;
/* 130 */     ((JPanel)localObject1).add(this.jdField_a_of_type_JavaxSwingJTextField, localObject2);
/* 131 */     this.jdField_a_of_type_JavaxSwingJTextField.setPreferredSize(new Dimension(300, 20));
/* 132 */     this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/*     */ 
/* 134 */     (
/* 135 */       localObject1 = new JPanel())
/* 135 */       .setBorder(new TitledBorder(null, "Bug/Crash description", 4, 2, null, null));
/* 136 */     ((JPanel)localObject1).setPreferredSize(new Dimension(300, 300));
/* 137 */     (
/* 138 */       localObject2 = new GridBagConstraints()).weighty = 
/* 138 */       100.0D;
/* 139 */     ((GridBagConstraints)localObject2).gridheight = 8;
/* 140 */     ((GridBagConstraints)localObject2).gridwidth = 2;
/* 141 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 142 */     ((GridBagConstraints)localObject2).fill = 1;
/* 143 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 144 */     ((GridBagConstraints)localObject2).gridy = 1;
/* 145 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 146 */     (
/* 147 */       localObject2 = new GridBagLayout()).columnWidths = 
/* 147 */       new int[] { 93, 0 };
/* 148 */     ((GridBagLayout)localObject2).rowHeights = new int[] { 19, 19, 19, 0, 0 };
/* 149 */     ((GridBagLayout)localObject2).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 150 */     ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 1.0D, 4.9E-324D };
/* 151 */     ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
/*     */ 
/* 153 */     localObject2 = new JLabel("Where did the game crash?");
/* 154 */     (
/* 155 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 155 */       16;
/* 156 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 157 */     localGridBagConstraints.gridx = 0;
/* 158 */     localGridBagConstraints.gridy = 0;
/* 159 */     ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 160 */     ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
/*     */ 
/* 162 */     localObject2 = new JLabel("What were you doing in the game when the Bug occurred?");
/* 163 */     (
/* 164 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 164 */       18;
/* 165 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 166 */     localGridBagConstraints.gridx = 0;
/* 167 */     localGridBagConstraints.gridy = 1;
/* 168 */     ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 169 */     ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
/*     */ 
/* 171 */     localObject2 = new JLabel("Please describe the Problem:");
/* 172 */     (
/* 173 */       localGridBagConstraints = new GridBagConstraints()).insets = 
/* 173 */       new Insets(0, 0, 5, 0);
/* 174 */     localGridBagConstraints.anchor = 16;
/* 175 */     localGridBagConstraints.gridx = 0;
/* 176 */     localGridBagConstraints.gridy = 2;
/* 177 */     ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/* 178 */     ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
/*     */ 
/* 180 */     (
/* 181 */       localObject2 = new JScrollPane())
/* 181 */       .setHorizontalScrollBarPolicy(31);
/* 182 */     (
/* 183 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 183 */       1;
/* 184 */     localGridBagConstraints.gridx = 0;
/* 185 */     localGridBagConstraints.gridy = 3;
/* 186 */     ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/*     */ 
/* 188 */     (
/* 189 */       localObject1 = new JTextArea())
/* 189 */       .setWrapStyleWord(true);
/* 190 */     ((JTextArea)localObject1).setLineWrap(true);
/* 191 */     ((JScrollPane)localObject2).setViewportView((Component)localObject1);
/*     */ 
/* 193 */     (
/* 194 */       localObject2 = new JButton("Send Report and Logs"))
/* 194 */       .addActionListener(new kZ(this, (JTextArea)localObject1));
/*     */ 
/* 214 */     (
/* 215 */       localObject1 = new JTextPane())
/* 215 */       .setFont(new Font("Arial", 0, 10));
/* 216 */     ((JTextPane)localObject1).setEditable(false);
/* 217 */     ((JTextPane)localObject1).setText("All information you send will only be used to fix bugs and crashes in StarMade, and that purpose only. The information won't be saved permanently and will never be given to a third party.");
/* 218 */     (
/* 219 */       localGridBagConstraints = new GridBagConstraints()).gridwidth = 
/* 219 */       2;
/* 220 */     localGridBagConstraints.anchor = 15;
/* 221 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 222 */     localGridBagConstraints.fill = 2;
/* 223 */     localGridBagConstraints.gridx = 0;
/* 224 */     localGridBagConstraints.gridy = 9;
/* 225 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localGridBagConstraints);
/* 226 */     (
/* 227 */       localObject1 = new GridBagConstraints()).anchor = 
/* 227 */       17;
/* 228 */     ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
/* 229 */     ((GridBagConstraints)localObject1).gridx = 0;
/* 230 */     ((GridBagConstraints)localObject1).gridy = 10;
/* 231 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject1);
/*     */ 
/* 233 */     this.jdField_a_of_type_JavaxSwingJProgressBar = new JProgressBar();
/* 234 */     this.jdField_a_of_type_JavaxSwingJProgressBar.setStringPainted(true);
/* 235 */     (
/* 236 */       localObject1 = new GridBagConstraints()).weightx = 
/* 236 */       100.0D;
/* 237 */     ((GridBagConstraints)localObject1).fill = 1;
/* 238 */     ((GridBagConstraints)localObject1).gridx = 1;
/* 239 */     ((GridBagConstraints)localObject1).gridy = 10;
/* 240 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJProgressBar, localObject1);
/*     */   }
/*     */ 
/*     */   public void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 245 */     if (paramObject != null) {
/* 246 */       if ((paramObject instanceof Integer)) {
/* 247 */         this.jdField_a_of_type_JavaxSwingJProgressBar.setValue(((Integer)paramObject).intValue()); return;
/*     */       }
/* 249 */       if (paramObject.toString().equals("FINISHED")) {
/* 250 */         paramObservable = "Thank You For Sending the Report!\n\nI (schema) will be automatically notified about this Report\nand I will try to fix your issue as soon as I can!\n\nThanks for playing StarMade!\n"; Object[] arrayOfObject = { "OK" }; String str = "Information";
/*     */         JFrame localJFrame;
/* 250 */         (localJFrame = new JFrame(str)).setUndecorated(true); localJFrame.setVisible(true); Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize(); localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2); switch (JOptionPane.showOptionDialog(localJFrame, paramObservable, str, 0, 1, null, arrayOfObject, arrayOfObject[0])) { case 0:
/* 250 */           System.err.println("Exiting because of exit info dialog");
/*     */           try { CrashReporter.a(); } catch (IOException localIOException) { localIOException.printStackTrace(); } System.exit(0); } localJFrame.dispose();
/*     */       }
/*     */ 
/* 256 */       this.jdField_a_of_type_JavaxSwingJProgressBar.setString(paramObject.toString());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.crashreporter.CrashReportGUI
 * JD-Core Version:    0.6.2
 */