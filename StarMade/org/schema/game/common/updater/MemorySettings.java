/*     */ package org.schema.game.common.updater;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Properties;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.EtchedBorder;
/*     */ import javax.swing.border.TitledBorder;
/*     */ import sE;
/*     */ import sk;
/*     */ import sl;
/*     */ import sr;
/*     */ 
/*     */ public class MemorySettings extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = -8542005436420601006L;
/* 117 */   private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*     */   private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*     */   private JTextField b;
/*     */   private JTextField c;
/*     */   private JTextField d;
/*     */   private JTextField e;
/*     */   private JTextField f;
/*     */ 
/*     */   public static boolean a()
/*     */   {
/*  38 */     return System.getProperty("os.arch").contains("64");
/*     */   }
/*     */ 
/*     */   public static void a() {
/*  42 */     File localFile = new File(sE.a(), "settings.properties");
/*  43 */     Properties localProperties = new Properties();
/*     */ 
/*  45 */     if (localFile.exists()) {
/*  46 */       localObject = new FileInputStream(localFile);
/*  47 */       localProperties.load((InputStream)localObject);
/*  48 */       ((FileInputStream)localObject).close();
/*     */     } else {
/*  50 */       System.err.println("ERROR, FILE DOES NOT EXIST: " + localFile.getAbsolutePath());
/*  51 */       return;
/*     */     }
/*     */ 
/*  54 */     sr.a = Integer.parseInt(localProperties.get("maxMemory").toString());
/*  55 */     sr.b = Integer.parseInt(localProperties.get("minMemory").toString());
/*  56 */     sr.c = Integer.parseInt(localProperties.get("earlyGenMemory").toString());
/*     */ 
/*  58 */     sr.d = Integer.parseInt(localProperties.get("maxMemory32").toString());
/*  59 */     sr.e = Integer.parseInt(localProperties.get("minMemory32").toString());
/*  60 */     sr.f = Integer.parseInt(localProperties.get("earlyGenMemory32").toString());
/*     */ 
/*  62 */     sr.g = Integer.parseInt(localProperties.get("serverMaxMemory").toString());
/*  63 */     sr.h = Integer.parseInt(localProperties.get("serverMinMemory").toString());
/*  64 */     sr.i = Integer.parseInt(localProperties.get("serverEarlyGenMemory").toString());
/*     */ 
/*  66 */     sr.j = Integer.parseInt(localProperties.get("port").toString());
/*     */ 
/*  68 */     localFile.createNewFile();
/*  69 */     Object localObject = new FileOutputStream(localFile);
/*  70 */     localProperties.store((OutputStream)localObject, "Properties for the StarMade Starter");
/*  71 */     ((FileOutputStream)localObject).flush();
/*  72 */     ((FileOutputStream)localObject).close();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString) {
/*     */     try {
/*  81 */       (
/*  82 */         paramArrayOfString = new MemorySettings())
/*  82 */         .setDefaultCloseOperation(2);
/*  83 */       paramArrayOfString.setVisible(true);
/*     */       return;
/*     */     }
/*     */     catch (Exception localException) {
/*  86 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void b()
/*     */   {
/*  90 */     File localFile = new File(sE.a(), "settings.properties");
/*     */     Properties localProperties;
/*  91 */     (
/*  94 */       localProperties = new Properties())
/*  94 */       .put("maxMemory", String.valueOf(sr.a));
/*  95 */     localProperties.put("minMemory", String.valueOf(sr.b));
/*  96 */     localProperties.put("earlyGenMemory", String.valueOf(sr.c));
/*     */ 
/*  98 */     localProperties.put("maxMemory32", String.valueOf(sr.d));
/*  99 */     localProperties.put("minMemory32", String.valueOf(sr.e));
/* 100 */     localProperties.put("earlyGenMemory32", String.valueOf(sr.f));
/*     */ 
/* 102 */     localProperties.put("serverMaxMemory", String.valueOf(sr.g));
/* 103 */     localProperties.put("serverMinMemory", String.valueOf(sr.h));
/* 104 */     localProperties.put("serverEarlyGenMemory", String.valueOf(sr.i));
/*     */ 
/* 106 */     localProperties.put("port", String.valueOf(sr.j));
/*     */ 
/* 109 */     localFile.createNewFile();
/* 110 */     FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
/* 111 */     localProperties.store(localFileOutputStream, "Properties for the StarMade Starter");
/* 112 */     localFileOutputStream.flush();
/* 113 */     localFileOutputStream.close();
/* 114 */     System.out.println("Memory Settings saved to: " + localFile.getAbsolutePath());
/*     */   }
/*     */ 
/*     */   public MemorySettings()
/*     */   {
/* 129 */     setAlwaysOnTop(true);
/* 130 */     setTitle("Memory Settings");
/* 131 */     setBounds(100, 100, 387, 354);
/* 132 */     getContentPane().setLayout(new BorderLayout());
/*     */ 
/* 134 */     (
/* 135 */       localObject1 = new JPanel())
/* 135 */       .setLayout(new FlowLayout(2));
/* 136 */     getContentPane().add((Component)localObject1, "South");
/*     */ 
/* 138 */     (
/* 139 */       localObject2 = new JButton("OK"))
/* 139 */       .addActionListener(new sk(this));
/*     */ 
/* 179 */     ((JButton)localObject2).setActionCommand("OK");
/* 180 */     ((JPanel)localObject1).add((Component)localObject2);
/* 181 */     getRootPane().setDefaultButton((JButton)localObject2);
/*     */ 
/* 184 */     (
/* 185 */       localObject2 = new JButton("Cancel"))
/* 185 */       .addActionListener(new sl(this));
/*     */ 
/* 191 */     ((JButton)localObject2).setActionCommand("Cancel");
/* 192 */     ((JPanel)localObject1).add((Component)localObject2);
/*     */ 
/* 196 */     Object localObject1 = new JTabbedPane(1);
/* 197 */     getContentPane().add((Component)localObject1, "North");
/* 198 */     ((JTabbedPane)localObject1).addTab("Client & SinglePlayer", null, this.jdField_a_of_type_JavaxSwingJPanel, null);
/* 199 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 200 */     (
/* 201 */       localObject2 = new GridBagLayout()).columnWidths = 
/* 201 */       new int[] { 0, 0 };
/* 202 */     ((GridBagLayout)localObject2).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
/* 203 */     ((GridBagLayout)localObject2).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 204 */     ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/* 205 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject2);
/*     */ 
/* 207 */     Object localObject2 = new JLabel("Client & Single Player Memory Settings");
/* 208 */     (
/* 209 */       localObject3 = new GridBagConstraints()).insets = 
/* 209 */       new Insets(0, 0, 5, 0);
/* 210 */     ((GridBagConstraints)localObject3).gridx = 0;
/* 211 */     ((GridBagConstraints)localObject3).gridy = 0;
/* 212 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/*     */ 
/* 215 */     (
/* 216 */       localObject2 = new JTextPane())
/* 216 */       .setText("Please keep in mind that 32 bit OS have a limit of allocating memory. Should 1024 throw out of memory exceptions, please try less then 1024");
/* 217 */     (
/* 218 */       localObject3 = new GridBagConstraints()).insets = 
/* 218 */       new Insets(0, 0, 5, 0);
/* 219 */     ((GridBagConstraints)localObject3).fill = 1;
/* 220 */     ((GridBagConstraints)localObject3).gridx = 0;
/* 221 */     ((GridBagConstraints)localObject3).gridy = 1;
/* 222 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/*     */ 
/* 225 */     (
/* 226 */       localObject2 = new JPanel())
/* 226 */       .setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Maximal Memory (MB)", 4, 2, null, null));
/* 227 */     (
/* 228 */       localObject3 = new GridBagConstraints()).insets = 
/* 228 */       new Insets(0, 0, 5, 0);
/* 229 */     ((GridBagConstraints)localObject3).fill = 1;
/* 230 */     ((GridBagConstraints)localObject3).gridx = 0;
/* 231 */     ((GridBagConstraints)localObject3).gridy = 2;
/* 232 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/* 233 */     (
/* 234 */       localObject3 = new GridBagLayout()).columnWidths = 
/* 234 */       new int[] { 0, 0 };
/* 235 */     ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/* 236 */     ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 237 */     ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 238 */     ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
/*     */ 
/* 240 */     this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/*     */     GridBagConstraints localGridBagConstraints;
/* 241 */     (
/* 242 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 242 */       2;
/* 243 */     localGridBagConstraints.gridx = 0;
/* 244 */     localGridBagConstraints.gridy = 0;
/* 245 */     ((JPanel)localObject2).add(this.jdField_a_of_type_JavaxSwingJTextField, localGridBagConstraints);
/* 246 */     if (a())
/* 247 */       this.jdField_a_of_type_JavaxSwingJTextField.setText(String.valueOf(sr.a));
/*     */     else {
/* 249 */       this.jdField_a_of_type_JavaxSwingJTextField.setText(String.valueOf(sr.d));
/*     */     }
/* 251 */     this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/*     */ 
/* 255 */     (
/* 256 */       localObject2 = new JPanel())
/* 256 */       .setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Initial Memory (MB)", 4, 2, null, null));
/* 257 */     (
/* 258 */       localObject3 = new GridBagConstraints()).insets = 
/* 258 */       new Insets(0, 0, 5, 0);
/* 259 */     ((GridBagConstraints)localObject3).fill = 1;
/* 260 */     ((GridBagConstraints)localObject3).gridx = 0;
/* 261 */     ((GridBagConstraints)localObject3).gridy = 3;
/* 262 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/* 263 */     (
/* 264 */       localObject3 = new GridBagLayout()).columnWidths = 
/* 264 */       new int[] { 0, 0 };
/* 265 */     ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/* 266 */     ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 267 */     ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 268 */     ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
/*     */ 
/* 270 */     this.b = new JTextField();
/* 271 */     (
/* 272 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 272 */       2;
/* 273 */     localGridBagConstraints.gridx = 0;
/* 274 */     localGridBagConstraints.gridy = 0;
/* 275 */     ((JPanel)localObject2).add(this.b, localGridBagConstraints);
/*     */ 
/* 277 */     if (a())
/* 278 */       this.b.setText(String.valueOf(sr.b));
/*     */     else {
/* 280 */       this.b.setText(String.valueOf(sr.e));
/*     */     }
/* 282 */     this.b.setColumns(10);
/*     */ 
/* 286 */     (
/* 287 */       localObject2 = new JPanel())
/* 287 */       .setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Early Generation Memory", 4, 2, null, null));
/* 288 */     (
/* 289 */       localObject3 = new GridBagConstraints()).fill = 
/* 289 */       1;
/* 290 */     ((GridBagConstraints)localObject3).gridx = 0;
/* 291 */     ((GridBagConstraints)localObject3).gridy = 4;
/* 292 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/* 293 */     (
/* 294 */       localObject3 = new GridBagLayout()).columnWidths = 
/* 294 */       new int[] { 0, 0 };
/* 295 */     ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/* 296 */     ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 297 */     ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 298 */     ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
/*     */ 
/* 300 */     this.c = new JTextField();
/* 301 */     (
/* 302 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 302 */       2;
/* 303 */     localGridBagConstraints.gridx = 0;
/* 304 */     localGridBagConstraints.gridy = 0;
/* 305 */     ((JPanel)localObject2).add(this.c, localGridBagConstraints);
/* 306 */     if (a())
/* 307 */       this.c.setText(String.valueOf(sr.c));
/*     */     else {
/* 309 */       this.c.setText(String.valueOf(sr.f));
/*     */     }
/*     */ 
/* 312 */     this.c.setColumns(10);
/*     */ 
/* 316 */     (
/* 317 */       localObject2 = new JPanel())
/* 317 */       .setBorder(new EmptyBorder(5, 5, 5, 5));
/* 318 */     ((JTabbedPane)localObject1).addTab("Dedicated Server", null, (Component)localObject2, null);
/* 319 */     (
/* 320 */       localObject3 = new GridBagLayout()).columnWidths = 
/* 320 */       new int[] { 0, 0 };
/* 321 */     ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
/* 322 */     ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 323 */     ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/* 324 */     ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
/*     */ 
/* 326 */     Object localObject3 = new JLabel("Dedicated Server Memory Settings");
/* 327 */     (
/* 328 */       localGridBagConstraints = new GridBagConstraints()).insets = 
/* 328 */       new Insets(0, 0, 5, 0);
/* 329 */     localGridBagConstraints.gridx = 0;
/* 330 */     localGridBagConstraints.gridy = 0;
/* 331 */     ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/*     */ 
/* 334 */     (
/* 335 */       localObject3 = new JTextPane())
/* 335 */       .setText("Please keep in mind that 32 bit OS have a limit of allocating memory. Should 1024 throw out of memory exceptions, please try less then 1024");
/* 336 */     (
/* 337 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 337 */       1;
/* 338 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 339 */     localGridBagConstraints.gridx = 0;
/* 340 */     localGridBagConstraints.gridy = 1;
/* 341 */     ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/*     */ 
/* 344 */     (
/* 345 */       localObject3 = new JPanel())
/* 345 */       .setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Maximal Memory (MB)", 4, 2, null, null));
/* 346 */     (
/* 347 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 347 */       1;
/* 348 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 349 */     localGridBagConstraints.gridx = 0;
/* 350 */     localGridBagConstraints.gridy = 2;
/* 351 */     ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/* 352 */     (
/* 353 */       localObject1 = new GridBagLayout()).columnWidths = 
/* 353 */       new int[] { 0, 0 };
/* 354 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
/* 355 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 356 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 357 */     ((JPanel)localObject3).setLayout((LayoutManager)localObject1);
/*     */ 
/* 359 */     this.d = new JTextField();
/* 360 */     this.d.setText(String.valueOf(sr.g));
/* 361 */     this.d.setColumns(10);
/* 362 */     (
/* 363 */       localObject1 = new GridBagConstraints()).fill = 
/* 363 */       2;
/* 364 */     ((GridBagConstraints)localObject1).gridx = 0;
/* 365 */     ((GridBagConstraints)localObject1).gridy = 0;
/* 366 */     ((JPanel)localObject3).add(this.d, localObject1);
/*     */ 
/* 370 */     (
/* 371 */       localObject3 = new JPanel())
/* 371 */       .setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Initial Memory (MB)", 4, 2, null, null));
/* 372 */     (
/* 373 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 373 */       1;
/* 374 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 375 */     localGridBagConstraints.gridx = 0;
/* 376 */     localGridBagConstraints.gridy = 3;
/* 377 */     ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/* 378 */     (
/* 379 */       localObject1 = new GridBagLayout()).columnWidths = 
/* 379 */       new int[] { 0, 0 };
/* 380 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
/* 381 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 382 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 383 */     ((JPanel)localObject3).setLayout((LayoutManager)localObject1);
/*     */ 
/* 385 */     this.e = new JTextField();
/* 386 */     this.e.setText(String.valueOf(sr.h));
/* 387 */     this.e.setColumns(10);
/* 388 */     (
/* 389 */       localObject1 = new GridBagConstraints()).fill = 
/* 389 */       2;
/* 390 */     ((GridBagConstraints)localObject1).gridx = 0;
/* 391 */     ((GridBagConstraints)localObject1).gridy = 0;
/* 392 */     ((JPanel)localObject3).add(this.e, localObject1);
/*     */ 
/* 396 */     (
/* 397 */       localObject3 = new JPanel())
/* 397 */       .setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Early Generation Memory", 4, 2, null, null));
/* 398 */     (
/* 399 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/* 399 */       1;
/* 400 */     localGridBagConstraints.gridx = 0;
/* 401 */     localGridBagConstraints.gridy = 4;
/* 402 */     ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/* 403 */     (
/* 404 */       localObject1 = new GridBagLayout()).columnWidths = 
/* 404 */       new int[] { 0, 0 };
/* 405 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
/* 406 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 407 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 408 */     ((JPanel)localObject3).setLayout((LayoutManager)localObject1);
/*     */ 
/* 410 */     this.f = new JTextField();
/* 411 */     this.f.setText(String.valueOf(sr.i));
/* 412 */     this.f.setColumns(10);
/* 413 */     (
/* 414 */       localObject1 = new GridBagConstraints()).fill = 
/* 414 */       2;
/* 415 */     ((GridBagConstraints)localObject1).gridx = 0;
/* 416 */     ((GridBagConstraints)localObject1).gridy = 0;
/* 417 */     ((JPanel)localObject3).add(this.f, localObject1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.updater.MemorySettings
 * JD-Core Version:    0.6.2
 */