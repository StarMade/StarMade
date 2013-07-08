/*   1:    */package org.schema.game.common.updater;
/*   2:    */
/*   3:    */import java.awt.BorderLayout;
/*   4:    */import java.awt.Component;
/*   5:    */import java.awt.Container;
/*   6:    */import java.awt.FlowLayout;
/*   7:    */import java.awt.GridBagLayout;
/*   8:    */import java.awt.Insets;
/*   9:    */import java.awt.LayoutManager;
/*  10:    */import java.io.File;
/*  11:    */import java.io.FileInputStream;
/*  12:    */import java.io.FileOutputStream;
/*  13:    */import java.io.InputStream;
/*  14:    */import java.io.OutputStream;
/*  15:    */import java.io.PrintStream;
/*  16:    */import java.util.Properties;
/*  17:    */import javax.swing.JButton;
/*  18:    */import javax.swing.JDialog;
/*  19:    */import javax.swing.JLabel;
/*  20:    */import javax.swing.JRootPane;
/*  21:    */import javax.swing.JTabbedPane;
/*  22:    */import javax.swing.JTextField;
/*  23:    */import javax.swing.JTextPane;
/*  24:    */import javax.swing.border.EmptyBorder;
/*  25:    */import javax.swing.border.EtchedBorder;
/*  26:    */import javax.swing.border.TitledBorder;
/*  27:    */import sE;
/*  28:    */import sk;
/*  29:    */import sl;
/*  30:    */import sr;
/*  31:    */
/*  32:    */public class MemorySettings extends JDialog
/*  33:    */{
/*  34:    */  private static final long serialVersionUID = -8542005436420601006L;
/*  35:    */  
/*  36:    */  public static boolean a()
/*  37:    */  {
/*  38: 38 */    return System.getProperty("os.arch").contains("64");
/*  39:    */  }
/*  40:    */  
/*  41:    */  public static void a() {
/*  42: 42 */    File localFile = new File(sE.a(), "settings.properties");
/*  43: 43 */    Properties localProperties = new Properties();
/*  44:    */    
/*  45: 45 */    if (localFile.exists()) {
/*  46: 46 */      localObject = new FileInputStream(localFile);
/*  47: 47 */      localProperties.load((InputStream)localObject);
/*  48: 48 */      ((FileInputStream)localObject).close();
/*  49:    */    } else {
/*  50: 50 */      System.err.println("ERROR, FILE DOES NOT EXIST: " + localFile.getAbsolutePath());
/*  51: 51 */      return;
/*  52:    */    }
/*  53:    */    
/*  54: 54 */    sr.a = Integer.parseInt(localProperties.get("maxMemory").toString());
/*  55: 55 */    sr.b = Integer.parseInt(localProperties.get("minMemory").toString());
/*  56: 56 */    sr.c = Integer.parseInt(localProperties.get("earlyGenMemory").toString());
/*  57:    */    
/*  58: 58 */    sr.d = Integer.parseInt(localProperties.get("maxMemory32").toString());
/*  59: 59 */    sr.e = Integer.parseInt(localProperties.get("minMemory32").toString());
/*  60: 60 */    sr.f = Integer.parseInt(localProperties.get("earlyGenMemory32").toString());
/*  61:    */    
/*  62: 62 */    sr.g = Integer.parseInt(localProperties.get("serverMaxMemory").toString());
/*  63: 63 */    sr.h = Integer.parseInt(localProperties.get("serverMinMemory").toString());
/*  64: 64 */    sr.i = Integer.parseInt(localProperties.get("serverEarlyGenMemory").toString());
/*  65:    */    
/*  66: 66 */    sr.j = Integer.parseInt(localProperties.get("port").toString());
/*  67:    */    
/*  68: 68 */    localFile.createNewFile();
/*  69: 69 */    Object localObject = new FileOutputStream(localFile);
/*  70: 70 */    localProperties.store((OutputStream)localObject, "Properties for the StarMade Starter");
/*  71: 71 */    ((FileOutputStream)localObject).flush();
/*  72: 72 */    ((FileOutputStream)localObject).close();
/*  73:    */  }
/*  74:    */  
/*  78:    */  public static void main(String[] paramArrayOfString)
/*  79:    */  {
/*  80:    */    try
/*  81:    */    {
/*  82: 82 */      (paramArrayOfString = new MemorySettings()).setDefaultCloseOperation(2);
/*  83: 83 */      paramArrayOfString.setVisible(true); return;
/*  84: 84 */    } catch (Exception localException) { 
/*  85:    */      
/*  86: 86 */        localException;
/*  87:    */    }
/*  88:    */  }
/*  89:    */  
/*  90:    */  public static void b()
/*  91:    */  {
/*  92: 90 */    File localFile = new File(sE.a(), "settings.properties");
/*  93:    */    
/*  94:    */    Properties localProperties;
/*  95:    */    
/*  96: 94 */    (localProperties = new Properties()).put("maxMemory", String.valueOf(sr.a));
/*  97: 95 */    localProperties.put("minMemory", String.valueOf(sr.b));
/*  98: 96 */    localProperties.put("earlyGenMemory", String.valueOf(sr.c));
/*  99:    */    
/* 100: 98 */    localProperties.put("maxMemory32", String.valueOf(sr.d));
/* 101: 99 */    localProperties.put("minMemory32", String.valueOf(sr.e));
/* 102:100 */    localProperties.put("earlyGenMemory32", String.valueOf(sr.f));
/* 103:    */    
/* 104:102 */    localProperties.put("serverMaxMemory", String.valueOf(sr.g));
/* 105:103 */    localProperties.put("serverMinMemory", String.valueOf(sr.h));
/* 106:104 */    localProperties.put("serverEarlyGenMemory", String.valueOf(sr.i));
/* 107:    */    
/* 108:106 */    localProperties.put("port", String.valueOf(sr.j));
/* 109:    */    
/* 111:109 */    localFile.createNewFile();
/* 112:110 */    FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
/* 113:111 */    localProperties.store(localFileOutputStream, "Properties for the StarMade Starter");
/* 114:112 */    localFileOutputStream.flush();
/* 115:113 */    localFileOutputStream.close();
/* 116:114 */    System.out.println("Memory Settings saved to: " + localFile.getAbsolutePath());
/* 117:    */  }
/* 118:    */  
/* 119:117 */  private final javax.swing.JPanel jdField_a_of_type_JavaxSwingJPanel = new javax.swing.JPanel();
/* 120:    */  
/* 121:    */  private JTextField jdField_a_of_type_JavaxSwingJTextField;
/* 122:    */  
/* 123:    */  private JTextField b;
/* 124:    */  private JTextField c;
/* 125:    */  private JTextField d;
/* 126:    */  private JTextField e;
/* 127:    */  private JTextField f;
/* 128:    */  
/* 129:    */  public MemorySettings()
/* 130:    */  {
/* 131:129 */    setAlwaysOnTop(true);
/* 132:130 */    setTitle("Memory Settings");
/* 133:131 */    setBounds(100, 100, 387, 354);
/* 134:132 */    getContentPane().setLayout(new BorderLayout());
/* 135:    */    
/* 136:134 */    (
/* 137:135 */      localObject1 = new javax.swing.JPanel()).setLayout(new FlowLayout(2));
/* 138:136 */    getContentPane().add((Component)localObject1, "South");
/* 139:    */    
/* 140:138 */    (
/* 141:139 */      localObject2 = new JButton("OK")).addActionListener(new sk(this));
/* 142:    */    
/* 181:179 */    ((JButton)localObject2).setActionCommand("OK");
/* 182:180 */    ((javax.swing.JPanel)localObject1).add((Component)localObject2);
/* 183:181 */    getRootPane().setDefaultButton((JButton)localObject2);
/* 184:    */    
/* 186:184 */    (
/* 187:185 */      localObject2 = new JButton("Cancel")).addActionListener(new sl(this));
/* 188:    */    
/* 193:191 */    ((JButton)localObject2).setActionCommand("Cancel");
/* 194:192 */    ((javax.swing.JPanel)localObject1).add((Component)localObject2);
/* 195:    */    
/* 198:196 */    Object localObject1 = new JTabbedPane(1);
/* 199:197 */    getContentPane().add((Component)localObject1, "North");
/* 200:198 */    ((JTabbedPane)localObject1).addTab("Client & SinglePlayer", null, this.jdField_a_of_type_JavaxSwingJPanel, null);
/* 201:199 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 202:    */    
/* 203:201 */    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 204:202 */    ((GridBagLayout)localObject2).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
/* 205:203 */    ((GridBagLayout)localObject2).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 206:204 */    ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/* 207:205 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject2);
/* 208:    */    
/* 209:207 */    Object localObject2 = new JLabel("Client & Single Player Memory Settings");
/* 210:    */    
/* 211:209 */    (localObject3 = new java.awt.GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 212:210 */    ((java.awt.GridBagConstraints)localObject3).gridx = 0;
/* 213:211 */    ((java.awt.GridBagConstraints)localObject3).gridy = 0;
/* 214:212 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/* 215:    */    
/* 217:215 */    (
/* 218:216 */      localObject2 = new JTextPane()).setText("Please keep in mind that 32 bit OS have a limit of allocating memory. Should 1024 throw out of memory exceptions, please try less then 1024");
/* 219:    */    
/* 220:218 */    (localObject3 = new java.awt.GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 221:219 */    ((java.awt.GridBagConstraints)localObject3).fill = 1;
/* 222:220 */    ((java.awt.GridBagConstraints)localObject3).gridx = 0;
/* 223:221 */    ((java.awt.GridBagConstraints)localObject3).gridy = 1;
/* 224:222 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/* 225:    */    
/* 227:225 */    (
/* 228:226 */      localObject2 = new javax.swing.JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Maximal Memory (MB)", 4, 2, null, null));
/* 229:    */    
/* 230:228 */    (localObject3 = new java.awt.GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 231:229 */    ((java.awt.GridBagConstraints)localObject3).fill = 1;
/* 232:230 */    ((java.awt.GridBagConstraints)localObject3).gridx = 0;
/* 233:231 */    ((java.awt.GridBagConstraints)localObject3).gridy = 2;
/* 234:232 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/* 235:    */    
/* 236:234 */    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 237:235 */    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/* 238:236 */    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 239:237 */    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 240:238 */    ((javax.swing.JPanel)localObject2).setLayout((LayoutManager)localObject3);
/* 241:    */    
/* 242:240 */    this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/* 243:    */    java.awt.GridBagConstraints localGridBagConstraints;
/* 244:242 */    (localGridBagConstraints = new java.awt.GridBagConstraints()).fill = 2;
/* 245:243 */    localGridBagConstraints.gridx = 0;
/* 246:244 */    localGridBagConstraints.gridy = 0;
/* 247:245 */    ((javax.swing.JPanel)localObject2).add(this.jdField_a_of_type_JavaxSwingJTextField, localGridBagConstraints);
/* 248:246 */    if (a()) {
/* 249:247 */      this.jdField_a_of_type_JavaxSwingJTextField.setText(String.valueOf(sr.a));
/* 250:    */    } else {
/* 251:249 */      this.jdField_a_of_type_JavaxSwingJTextField.setText(String.valueOf(sr.d));
/* 252:    */    }
/* 253:251 */    this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/* 254:    */    
/* 257:255 */    (
/* 258:256 */      localObject2 = new javax.swing.JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Initial Memory (MB)", 4, 2, null, null));
/* 259:    */    
/* 260:258 */    (localObject3 = new java.awt.GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 261:259 */    ((java.awt.GridBagConstraints)localObject3).fill = 1;
/* 262:260 */    ((java.awt.GridBagConstraints)localObject3).gridx = 0;
/* 263:261 */    ((java.awt.GridBagConstraints)localObject3).gridy = 3;
/* 264:262 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/* 265:    */    
/* 266:264 */    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 267:265 */    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/* 268:266 */    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 269:267 */    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 270:268 */    ((javax.swing.JPanel)localObject2).setLayout((LayoutManager)localObject3);
/* 271:    */    
/* 272:270 */    this.b = new JTextField();
/* 273:    */    
/* 274:272 */    (localGridBagConstraints = new java.awt.GridBagConstraints()).fill = 2;
/* 275:273 */    localGridBagConstraints.gridx = 0;
/* 276:274 */    localGridBagConstraints.gridy = 0;
/* 277:275 */    ((javax.swing.JPanel)localObject2).add(this.b, localGridBagConstraints);
/* 278:    */    
/* 279:277 */    if (a()) {
/* 280:278 */      this.b.setText(String.valueOf(sr.b));
/* 281:    */    } else {
/* 282:280 */      this.b.setText(String.valueOf(sr.e));
/* 283:    */    }
/* 284:282 */    this.b.setColumns(10);
/* 285:    */    
/* 288:286 */    (
/* 289:287 */      localObject2 = new javax.swing.JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Early Generation Memory", 4, 2, null, null));
/* 290:    */    
/* 291:289 */    (localObject3 = new java.awt.GridBagConstraints()).fill = 1;
/* 292:290 */    ((java.awt.GridBagConstraints)localObject3).gridx = 0;
/* 293:291 */    ((java.awt.GridBagConstraints)localObject3).gridy = 4;
/* 294:292 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
/* 295:    */    
/* 296:294 */    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 297:295 */    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
/* 298:296 */    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 299:297 */    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 300:298 */    ((javax.swing.JPanel)localObject2).setLayout((LayoutManager)localObject3);
/* 301:    */    
/* 302:300 */    this.c = new JTextField();
/* 303:    */    
/* 304:302 */    (localGridBagConstraints = new java.awt.GridBagConstraints()).fill = 2;
/* 305:303 */    localGridBagConstraints.gridx = 0;
/* 306:304 */    localGridBagConstraints.gridy = 0;
/* 307:305 */    ((javax.swing.JPanel)localObject2).add(this.c, localGridBagConstraints);
/* 308:306 */    if (a()) {
/* 309:307 */      this.c.setText(String.valueOf(sr.c));
/* 310:    */    } else {
/* 311:309 */      this.c.setText(String.valueOf(sr.f));
/* 312:    */    }
/* 313:    */    
/* 314:312 */    this.c.setColumns(10);
/* 315:    */    
/* 318:316 */    (
/* 319:317 */      localObject2 = new javax.swing.JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
/* 320:318 */    ((JTabbedPane)localObject1).addTab("Dedicated Server", null, (Component)localObject2, null);
/* 321:    */    
/* 322:320 */    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 323:321 */    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
/* 324:322 */    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 325:323 */    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/* 326:324 */    ((javax.swing.JPanel)localObject2).setLayout((LayoutManager)localObject3);
/* 327:    */    
/* 328:326 */    Object localObject3 = new JLabel("Dedicated Server Memory Settings");
/* 329:    */    
/* 330:328 */    (localGridBagConstraints = new java.awt.GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 331:329 */    localGridBagConstraints.gridx = 0;
/* 332:330 */    localGridBagConstraints.gridy = 0;
/* 333:331 */    ((javax.swing.JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/* 334:    */    
/* 336:334 */    (
/* 337:335 */      localObject3 = new JTextPane()).setText("Please keep in mind that 32 bit OS have a limit of allocating memory. Should 1024 throw out of memory exceptions, please try less then 1024");
/* 338:    */    
/* 339:337 */    (localGridBagConstraints = new java.awt.GridBagConstraints()).fill = 1;
/* 340:338 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 341:339 */    localGridBagConstraints.gridx = 0;
/* 342:340 */    localGridBagConstraints.gridy = 1;
/* 343:341 */    ((javax.swing.JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/* 344:    */    
/* 346:344 */    (
/* 347:345 */      localObject3 = new javax.swing.JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Maximal Memory (MB)", 4, 2, null, null));
/* 348:    */    
/* 349:347 */    (localGridBagConstraints = new java.awt.GridBagConstraints()).fill = 1;
/* 350:348 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 351:349 */    localGridBagConstraints.gridx = 0;
/* 352:350 */    localGridBagConstraints.gridy = 2;
/* 353:351 */    ((javax.swing.JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/* 354:    */    
/* 355:353 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 356:354 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
/* 357:355 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 358:356 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 359:357 */    ((javax.swing.JPanel)localObject3).setLayout((LayoutManager)localObject1);
/* 360:    */    
/* 361:359 */    this.d = new JTextField();
/* 362:360 */    this.d.setText(String.valueOf(sr.g));
/* 363:361 */    this.d.setColumns(10);
/* 364:    */    
/* 365:363 */    (localObject1 = new java.awt.GridBagConstraints()).fill = 2;
/* 366:364 */    ((java.awt.GridBagConstraints)localObject1).gridx = 0;
/* 367:365 */    ((java.awt.GridBagConstraints)localObject1).gridy = 0;
/* 368:366 */    ((javax.swing.JPanel)localObject3).add(this.d, localObject1);
/* 369:    */    
/* 372:370 */    (
/* 373:371 */      localObject3 = new javax.swing.JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Initial Memory (MB)", 4, 2, null, null));
/* 374:    */    
/* 375:373 */    (localGridBagConstraints = new java.awt.GridBagConstraints()).fill = 1;
/* 376:374 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/* 377:375 */    localGridBagConstraints.gridx = 0;
/* 378:376 */    localGridBagConstraints.gridy = 3;
/* 379:377 */    ((javax.swing.JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/* 380:    */    
/* 381:379 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 382:380 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
/* 383:381 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 384:382 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 385:383 */    ((javax.swing.JPanel)localObject3).setLayout((LayoutManager)localObject1);
/* 386:    */    
/* 387:385 */    this.e = new JTextField();
/* 388:386 */    this.e.setText(String.valueOf(sr.h));
/* 389:387 */    this.e.setColumns(10);
/* 390:    */    
/* 391:389 */    (localObject1 = new java.awt.GridBagConstraints()).fill = 2;
/* 392:390 */    ((java.awt.GridBagConstraints)localObject1).gridx = 0;
/* 393:391 */    ((java.awt.GridBagConstraints)localObject1).gridy = 0;
/* 394:392 */    ((javax.swing.JPanel)localObject3).add(this.e, localObject1);
/* 395:    */    
/* 398:396 */    (
/* 399:397 */      localObject3 = new javax.swing.JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Early Generation Memory", 4, 2, null, null));
/* 400:    */    
/* 401:399 */    (localGridBagConstraints = new java.awt.GridBagConstraints()).fill = 1;
/* 402:400 */    localGridBagConstraints.gridx = 0;
/* 403:401 */    localGridBagConstraints.gridy = 4;
/* 404:402 */    ((javax.swing.JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
/* 405:    */    
/* 406:404 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/* 407:405 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
/* 408:406 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
/* 409:407 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 410:408 */    ((javax.swing.JPanel)localObject3).setLayout((LayoutManager)localObject1);
/* 411:    */    
/* 412:410 */    this.f = new JTextField();
/* 413:411 */    this.f.setText(String.valueOf(sr.i));
/* 414:412 */    this.f.setColumns(10);
/* 415:    */    
/* 416:414 */    (localObject1 = new java.awt.GridBagConstraints()).fill = 2;
/* 417:415 */    ((java.awt.GridBagConstraints)localObject1).gridx = 0;
/* 418:416 */    ((java.awt.GridBagConstraints)localObject1).gridy = 0;
/* 419:417 */    ((javax.swing.JPanel)localObject3).add(this.f, localObject1);
/* 420:    */  }
/* 421:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.updater.MemorySettings
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */