/*   1:    */import java.awt.Toolkit;
/*   2:    */import java.awt.datatransfer.Clipboard;
/*   3:    */import java.awt.datatransfer.ClipboardOwner;
/*   4:    */import java.awt.datatransfer.DataFlavor;
/*   5:    */import java.awt.datatransfer.StringSelection;
/*   6:    */import java.awt.datatransfer.Transferable;
/*   7:    */import java.awt.datatransfer.UnsupportedFlavorException;
/*   8:    */import java.io.IOException;
/*   9:    */import java.io.PrintStream;
/*  10:    */import java.util.ArrayList;
/*  11:    */import org.lwjgl.input.Keyboard;
/*  12:    */import org.schema.schine.graphicsengine.core.settings.PrefixNotFoundException;
/*  13:    */
/*  41:    */public class wz
/*  42:    */  implements ClipboardOwner, wx
/*  43:    */{
/*  44:    */  private final ArrayList jdField_a_of_type_JavaUtilArrayList;
/*  45:    */  private int jdField_a_of_type_Int;
/*  46:    */  private final StringBuffer jdField_a_of_type_JavaLangStringBuffer;
/*  47: 47 */  private String jdField_a_of_type_JavaLangString = "";
/*  48: 48 */  private int jdField_b_of_type_Int = -1; private int jdField_c_of_type_Int = -1;
/*  49:    */  
/*  50:    */  private boolean jdField_a_of_type_Boolean;
/*  51:    */  
/*  52:    */  private int jdField_d_of_type_Int;
/*  53:    */  private wy jdField_a_of_type_Wy;
/*  54: 54 */  private String jdField_b_of_type_JavaLangString = "";
/*  55:    */  
/*  56: 56 */  private String jdField_c_of_type_JavaLangString = "";
/*  57: 57 */  private String jdField_d_of_type_JavaLangString = "";
/*  58:    */  
/*  59:    */  private wB jdField_a_of_type_WB;
/*  60:    */  private final int e;
/*  61: 61 */  private int f = 1;
/*  62:    */  
/*  63: 63 */  private ww jdField_a_of_type_Ww = new wA(this);
/*  64:    */  
/*  77: 77 */  private int g = 1;
/*  78:    */  private int h;
/*  79:    */  private int i;
/*  80:    */  
/*  81: 81 */  public wz(int paramInt1, int paramInt2, wB paramwB) { this.jdField_a_of_type_JavaLangStringBuffer = new StringBuffer(paramInt1);
/*  82: 82 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  83: 83 */    this.e = paramInt1;
/*  84: 84 */    this.f = paramInt2;
/*  85: 85 */    this.jdField_a_of_type_WB = paramwB;
/*  86: 86 */    this.jdField_a_of_type_JavaUtilArrayList.add("/load Fireball testship");
/*  87: 87 */    this.jdField_a_of_type_JavaUtilArrayList.add("/give_category_items schema 100 ship");
/*  88: 88 */    this.jdField_a_of_type_JavaUtilArrayList.add("/give_credits schema 9999999999");
/*  89: 89 */    this.jdField_a_of_type_JavaUtilArrayList.add("/jump");
/*  90:    */  }
/*  91:    */  
/*  92: 92 */  public final void a(String paramString) { if ((this.jdField_c_of_type_Int >= 0) && (this.jdField_b_of_type_Int >= 0)) {
/*  93: 93 */      j = Math.min(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/*  94: 94 */      int k = Math.max(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/*  95: 95 */      this.jdField_a_of_type_JavaLangStringBuffer.delete(j, k);
/*  96: 96 */      this.jdField_a_of_type_Int = j;
/*  97: 97 */      this.jdField_a_of_type_Boolean = true;
/*  98:    */    }
/*  99: 99 */    for (int j = 0; (j < paramString.length()) && (this.jdField_a_of_type_JavaLangStringBuffer.length() < this.e); j++) {
/* 100:100 */      this.jdField_a_of_type_JavaLangStringBuffer.insert(this.jdField_a_of_type_Int, paramString.charAt(j));
/* 101:    */      
/* 103:103 */      this.jdField_a_of_type_Int += 1;
/* 104:104 */      this.jdField_a_of_type_Boolean = true;
/* 105:    */    }
/* 106:    */    
/* 108:108 */    d();
/* 109:    */  }
/* 110:    */  
/* 158:    */  private void h()
/* 159:    */  {
/* 160:160 */    int j = this.jdField_a_of_type_Int;
/* 161:161 */    if (this.jdField_a_of_type_Int > 0) {
/* 162:162 */      this.jdField_a_of_type_Int -= 1;
/* 163:163 */      if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29)))
/* 164:    */      {
/* 166:166 */        while ((this.jdField_a_of_type_Int > 0) && (' ' == this.jdField_a_of_type_JavaLangStringBuffer.charAt(this.jdField_a_of_type_Int - 1))) {
/* 167:167 */          this.jdField_a_of_type_Int -= 1;
/* 168:    */        }
/* 169:    */        
/* 171:171 */        while ((this.jdField_a_of_type_Int > 0) && (' ' != this.jdField_a_of_type_JavaLangStringBuffer.charAt(this.jdField_a_of_type_Int - 1))) {
/* 172:172 */          this.jdField_a_of_type_Int -= 1;
/* 173:    */        }
/* 174:    */      }
/* 175:175 */      this.jdField_a_of_type_Boolean = true;
/* 176:    */    }
/* 177:177 */    b(j);
/* 178:    */  }
/* 179:    */  
/* 187:    */  private void i()
/* 188:    */  {
/* 189:189 */    int j = this.jdField_a_of_type_Int;
/* 190:190 */    if (this.jdField_a_of_type_Int < this.jdField_a_of_type_JavaLangStringBuffer.length()) {
/* 191:191 */      this.jdField_a_of_type_Int += 1;
/* 192:192 */      if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29)))
/* 193:    */      {
/* 196:196 */        while ((this.jdField_a_of_type_Int < this.jdField_a_of_type_JavaLangStringBuffer.length()) && (' ' != this.jdField_a_of_type_JavaLangStringBuffer.charAt(this.jdField_a_of_type_Int))) {
/* 197:197 */          System.err.println("chat carrier reset!!! right ");
/* 198:198 */          this.jdField_a_of_type_Int += 1;
/* 199:    */        }
/* 200:    */        
/* 201:201 */        while ((this.jdField_a_of_type_Int < this.jdField_a_of_type_JavaLangStringBuffer.length()) && (' ' == this.jdField_a_of_type_JavaLangStringBuffer.charAt(this.jdField_a_of_type_Int))) {
/* 202:202 */          System.err.println("chat carrier reset!!! right ");
/* 203:203 */          this.jdField_a_of_type_Int += 1;
/* 204:    */        }
/* 205:    */      }
/* 206:    */      
/* 207:207 */      this.jdField_a_of_type_Boolean = true;
/* 208:    */    }
/* 209:209 */    b(j);
/* 210:    */  }
/* 211:    */  
/* 212:    */  private void j() {
/* 213:213 */    if (this.f == 1) {
/* 214:214 */      this.jdField_d_of_type_Int += 1;
/* 215:215 */      if (this.jdField_d_of_type_Int <= this.jdField_a_of_type_JavaUtilArrayList.size()) {
/* 216:216 */        this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 217:217 */        this.jdField_a_of_type_JavaLangStringBuffer.append((String)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - this.jdField_d_of_type_Int));
/* 218:218 */        System.err.println("chat carrier reset!!! up " + this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 219:    */        
/* 220:220 */        this.jdField_a_of_type_Int = this.jdField_a_of_type_JavaLangStringBuffer.length();
/* 221:    */      } else {
/* 222:222 */        this.jdField_d_of_type_Int = this.jdField_a_of_type_JavaUtilArrayList.size();
/* 223:    */      }
/* 224:    */    }
/* 225:225 */    else if (this.i > 0)
/* 226:    */    {
/* 227:    */      int j;
/* 228:228 */      if ((j = Math.max(0, this.jdField_b_of_type_JavaLangString.lastIndexOf("\n"))) >= 0)
/* 229:    */      {
/* 230:230 */        int k = this.jdField_b_of_type_JavaLangString.substring(0, j).lastIndexOf("\n");
/* 231:    */        
/* 232:232 */        int m = j - k;
/* 233:233 */        j = k + Math.min(m, this.jdField_a_of_type_Int - j);
/* 234:234 */        System.err.println("CHAT CARRIER: " + this.jdField_a_of_type_Int + " -> " + j);
/* 235:235 */        while ((this.jdField_a_of_type_Int > 0) && (this.jdField_a_of_type_Int > j)) {
/* 236:236 */          this.jdField_a_of_type_Int -= 1;
/* 237:    */        }
/* 238:    */      }
/* 239:    */    }
/* 240:    */    
/* 241:241 */    d();
/* 242:242 */    this.jdField_a_of_type_Boolean = true;
/* 243:    */  }
/* 244:    */  
/* 245:    */  private void k()
/* 246:    */  {
/* 247:247 */    if (this.f == 1) {
/* 248:248 */      this.jdField_d_of_type_Int -= 1;
/* 249:249 */      if (this.jdField_d_of_type_Int > 0) {
/* 250:250 */        this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 251:251 */        this.jdField_a_of_type_JavaLangStringBuffer.append((String)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - this.jdField_d_of_type_Int));
/* 252:252 */        System.err.println("chat carrier reset!!! down " + this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 253:    */        
/* 254:254 */        this.jdField_a_of_type_Int = this.jdField_a_of_type_JavaLangStringBuffer.length();
/* 255:    */      } else {
/* 256:256 */        this.jdField_d_of_type_Int = 0;
/* 257:257 */        this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 258:258 */        System.err.println("chat carrier reset!!! keyDown");
/* 259:259 */        this.jdField_a_of_type_Int = 0;
/* 260:    */      }
/* 261:    */    }
/* 262:262 */    else if (this.i < this.h) {
/* 263:263 */      int j = this.jdField_b_of_type_JavaLangString.lastIndexOf("\n");
/* 264:    */      int k;
/* 265:265 */      if ((k = this.jdField_a_of_type_JavaLangStringBuffer.indexOf("\n", this.jdField_a_of_type_Int)) >= 0)
/* 266:    */      {
/* 267:    */        int m;
/* 268:268 */        if ((m = this.jdField_a_of_type_JavaLangStringBuffer.indexOf("\n", k + 1)) < 0) {
/* 269:269 */          m = this.jdField_a_of_type_JavaLangStringBuffer.length();
/* 270:    */        }
/* 271:271 */        int n = m - k;
/* 272:272 */        System.err.println("MAX " + n + " / " + (k - this.jdField_a_of_type_Int) + "; next: " + k + " NNext " + m);
/* 273:273 */        j = k + Math.min(n, this.jdField_a_of_type_Int - j);
/* 274:274 */        while ((this.jdField_a_of_type_Int < this.jdField_a_of_type_JavaLangStringBuffer.length()) && (this.jdField_a_of_type_Int < j)) {
/* 275:275 */          this.jdField_a_of_type_Int += 1;
/* 276:    */        }
/* 277:    */      } else {
/* 278:278 */        System.err.println("DOWN: " + j + " ---- " + k);
/* 279:    */      }
/* 280:    */    }
/* 281:    */    
/* 282:282 */    d();
/* 283:283 */    this.jdField_a_of_type_Boolean = true;
/* 284:    */  }
/* 285:    */  
/* 286:    */  public final void a() {
/* 287:287 */    d();
/* 288:288 */    this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 289:289 */    this.jdField_a_of_type_Int = 0;
/* 290:290 */    this.jdField_a_of_type_Boolean = true;
/* 291:291 */    g();
/* 292:    */  }
/* 293:    */  
/* 294:294 */  private void l() { System.err.println("trying copy");
/* 295:295 */    if ((this.jdField_c_of_type_Int >= 0) && (this.jdField_b_of_type_Int >= 0)) {
/* 296:296 */      Object localObject = this.jdField_c_of_type_JavaLangString;wz localwz = this;localObject = new StringSelection((String)localObject);Toolkit.getDefaultToolkit().getSystemClipboard().setContents((Transferable)localObject, localwz);
/* 297:297 */      System.err.println("Copied to clipboard: " + this.jdField_c_of_type_JavaLangString);
/* 298:    */    }
/* 299:    */  }
/* 300:    */  
/* 313:    */  public final void b()
/* 314:    */  {
/* 315:315 */    if (this.jdField_a_of_type_JavaLangStringBuffer.length() >= 0) {
/* 316:316 */      String str = this.jdField_a_of_type_JavaLangStringBuffer.toString();
/* 317:317 */      if ((this.jdField_a_of_type_Ww != null) && (!this.jdField_a_of_type_Ww.a(str, this.jdField_a_of_type_WB))) {
/* 318:318 */        return;
/* 319:    */      }
/* 320:320 */      this.jdField_a_of_type_WB.onTextEnter(str, !str.startsWith("/"));
/* 321:    */      
/* 322:322 */      this.jdField_a_of_type_JavaUtilArrayList.add(str);
/* 323:323 */      this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 324:    */      
/* 325:325 */      this.jdField_a_of_type_Int = 0;
/* 326:326 */      this.jdField_d_of_type_Int = 0;
/* 327:    */      
/* 328:328 */      this.jdField_a_of_type_Boolean = true;
/* 329:329 */      d();
/* 330:    */    }
/* 331:    */  }
/* 332:    */  
/* 338:    */  public final String a()
/* 339:    */  {
/* 340:340 */    return this.jdField_a_of_type_JavaLangString;
/* 341:    */  }
/* 342:    */  
/* 345:    */  public final String b()
/* 346:    */  {
/* 347:347 */    return this.jdField_b_of_type_JavaLangString;
/* 348:    */  }
/* 349:    */  
/* 352:    */  public final String c()
/* 353:    */  {
/* 354:354 */    return this.jdField_c_of_type_JavaLangString;
/* 355:    */  }
/* 356:    */  
/* 359:    */  public final String d()
/* 360:    */  {
/* 361:361 */    return this.jdField_d_of_type_JavaLangString;
/* 362:    */  }
/* 363:    */  
/* 373:    */  public final ArrayList a()
/* 374:    */  {
/* 375:375 */    return this.jdField_a_of_type_JavaUtilArrayList;
/* 376:    */  }
/* 377:    */  
/* 393:    */  public void handleKeyEvent()
/* 394:    */  {
/* 395:395 */    if (Keyboard.getEventKeyState()) { wz localwz;
/* 396:    */      int j;
/* 397:    */      int m;
/* 398:398 */      switch (Keyboard.getEventKey()) {
/* 399:    */      case 28: 
/* 400:400 */        if (this.f == 1) {
/* 401:401 */          if (!Keyboard.isRepeatEvent()) {
/* 402:402 */            b();
/* 403:    */          }
/* 404:    */        }
/* 405:405 */        else if (this.h + 1 < this.f) {
/* 406:406 */          a("\n");
/* 407:    */        } else {
/* 408:408 */          System.err.println("[TextAreaInput] line limit reached " + this.h + "/" + this.f);
/* 409:    */        }
/* 410:    */        
/* 412:412 */        break;
/* 413:    */      case 211: 
/* 414:414 */        localwz = this; if ((this.jdField_c_of_type_Int >= 0) && (localwz.jdField_b_of_type_Int >= 0)) { j = Math.min(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int);m = Math.max(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int);localwz.jdField_a_of_type_JavaLangStringBuffer.delete(j, m);localwz.jdField_a_of_type_Int = j;localwz.d(); } else if (localwz.jdField_a_of_type_Int < localwz.jdField_a_of_type_JavaLangStringBuffer.length()) { if ((localwz.jdField_c_of_type_Int >= 0) && (localwz.jdField_b_of_type_Int >= 0)) { j = Math.min(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int);m = Math.max(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int);localwz.jdField_a_of_type_JavaLangStringBuffer.delete(j, m);localwz.jdField_a_of_type_Int = j;localwz.d(); } else { localwz.jdField_a_of_type_JavaLangStringBuffer.delete(localwz.jdField_a_of_type_Int, localwz.jdField_a_of_type_Int + 1); } localwz.jdField_a_of_type_Boolean = true; } localwz.d();
/* 415:415 */        break;
/* 416:    */      case 203: 
/* 417:417 */        h();
/* 418:418 */        break;
/* 419:    */      case 205: 
/* 420:420 */        i();
/* 421:421 */        break;
/* 422:    */      case 14: 
/* 423:423 */        localwz = this; if (this.jdField_a_of_type_JavaLangStringBuffer.length() > 0) { if ((localwz.jdField_c_of_type_Int >= 0) && (localwz.jdField_b_of_type_Int >= 0)) { j = Math.min(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int);m = Math.max(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int);localwz.jdField_a_of_type_JavaLangStringBuffer.delete(j, m);localwz.jdField_a_of_type_Int = Math.max(0, j);localwz.d(); } else { localwz.jdField_a_of_type_JavaLangStringBuffer.delete(Math.max(0, localwz.jdField_a_of_type_Int - 1), localwz.jdField_a_of_type_Int);localwz.jdField_a_of_type_Int -= 1;localwz.jdField_a_of_type_Int = Math.max(0, localwz.jdField_a_of_type_Int); } localwz.jdField_a_of_type_Boolean = true; } localwz.d();
/* 424:424 */        break;
/* 425:    */      case 200: 
/* 426:426 */        j();
/* 427:427 */        break;
/* 428:    */      case 208: 
/* 429:429 */        k();
/* 430:430 */        break;
/* 431:    */      case 199: 
/* 432:432 */        localwz = this;j = this.jdField_a_of_type_Int;System.err.println("chat carrier reset!!! pos1");localwz.jdField_a_of_type_Int = 0;localwz.b(j);localwz.jdField_a_of_type_Boolean = true;
/* 433:433 */        break;
/* 434:    */      case 207: 
/* 435:435 */        localwz = this;j = this.jdField_a_of_type_Int;localwz.jdField_a_of_type_Int = Math.max(0, localwz.jdField_a_of_type_JavaLangStringBuffer.length());localwz.b(j);localwz.jdField_a_of_type_Boolean = true;
/* 436:436 */        break;
/* 437:    */      
/* 438:    */      case 47: 
/* 439:439 */        if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
/* 440:440 */          localwz = this; try { DataFlavor localDataFlavor = DataFlavor.stringFlavor;String str = ""; Transferable localTransferable; if ((((localTransferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null)) != null) && (localTransferable.isDataFlavorSupported(localDataFlavor)) ? 1 : 0) != 0) str = (String)localTransferable.getTransferData(localDataFlavor); localwz.a(str); } catch (UnsupportedFlavorException localUnsupportedFlavorException) { localUnsupportedFlavorException; } catch (IOException localIOException) { localIOException;
/* 441:    */          }
/* 442:    */        } else {
/* 443:443 */          m(); }
/* 444:444 */        break;
/* 445:    */      case 46: 
/* 446:446 */        if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
/* 447:447 */          l();
/* 448:    */        }
/* 449:    */        else
/* 450:450 */          m();
/* 451:451 */        break;
/* 452:    */      case 30: 
/* 453:453 */        if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
/* 454:454 */          e();
/* 455:    */        }
/* 456:    */        else
/* 457:457 */          m();
/* 458:458 */        break;
/* 459:    */      case 45: 
/* 460:460 */        if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
/* 461:461 */          localwz = this;l(); if ((localwz.jdField_c_of_type_Int >= 0) && (localwz.jdField_b_of_type_Int >= 0)) { int k = Math.min(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int);int n = Math.max(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int);System.err.println("current: " + localwz.jdField_a_of_type_JavaLangStringBuffer.toString());localwz.jdField_a_of_type_JavaLangStringBuffer.delete(k, n);localwz.jdField_a_of_type_Int = k;localwz.d();localwz.jdField_a_of_type_Boolean = true;
/* 462:    */          }
/* 463:    */        } else {
/* 464:464 */          m(); }
/* 465:465 */        break;
/* 466:    */      
/* 467:    */      case 15: 
/* 468:468 */        n();
/* 469:469 */        break;
/* 470:    */      default: 
/* 471:471 */        m();
/* 472:    */      }
/* 473:    */      
/* 474:    */    }
/* 475:475 */    g();
/* 476:    */  }
/* 477:    */  
/* 480:    */  public static void c() {}
/* 481:    */  
/* 484:    */  private void m()
/* 485:    */  {
/* 486:    */    char c1;
/* 487:    */    
/* 488:488 */    if (!Character.isIdentifierIgnorable(c1 = Keyboard.getEventCharacter())) {
/* 489:489 */      String str = String.valueOf(c1);
/* 490:490 */      a(str);
/* 491:    */    }
/* 492:    */  }
/* 493:    */  
/* 495:    */  public void lostOwnership(Clipboard paramClipboard, Transferable paramTransferable)
/* 496:    */  {
/* 497:497 */    System.out.println("Lost clipboard ownership " + this);
/* 498:    */  }
/* 499:    */  
/* 502:    */  private void n()
/* 503:    */  {
/* 504:    */    try
/* 505:    */    {
/* 506:    */      String[] arrayOfString;
/* 507:    */      
/* 509:509 */      if ((arrayOfString = this.jdField_a_of_type_WB.getCommandPrefixes()) != null) {
/* 510:510 */        for (int j = 0; j < arrayOfString.length; j++)
/* 511:511 */          if ((this.jdField_a_of_type_JavaLangStringBuffer.length() >= arrayOfString[j].length()) && (this.jdField_a_of_type_JavaLangStringBuffer.indexOf(arrayOfString[j]) == 0)) {
/* 512:512 */            String str = this.jdField_a_of_type_JavaLangStringBuffer.substring(arrayOfString[j].length());
/* 513:513 */            this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 514:    */            
/* 515:515 */            this.jdField_a_of_type_Int = 0;
/* 516:516 */            this.jdField_d_of_type_Int = 0;
/* 517:    */            
/* 518:518 */            this.jdField_a_of_type_Boolean = true;
/* 519:519 */            d();
/* 520:520 */            System.err.println("AUTOCOMPLETE ON PREFIX: " + arrayOfString[j] + " with \"" + str + "\"");
/* 521:521 */            a(arrayOfString[j] + this.jdField_a_of_type_WB.handleAutoComplete(str, this.jdField_a_of_type_WB, arrayOfString[j]));
/* 522:    */          }
/* 523:    */      }
/* 524:    */      return;
/* 525:    */    } catch (PrefixNotFoundException localPrefixNotFoundException) {
/* 526:526 */      
/* 527:    */      
/* 528:528 */        localPrefixNotFoundException;
/* 529:    */    }
/* 530:    */  }
/* 531:    */  
/* 550:    */  public final void d()
/* 551:    */  {
/* 552:550 */    if ((this.jdField_c_of_type_Int >= 0) || (this.jdField_b_of_type_Int >= 0)) {
/* 553:551 */      this.jdField_c_of_type_Int = -1;
/* 554:552 */      this.jdField_b_of_type_Int = -1;
/* 555:553 */      this.jdField_a_of_type_Boolean = true;
/* 556:    */    }
/* 557:555 */    g();
/* 558:    */  }
/* 559:    */  
/* 560:    */  public final void e() {
/* 561:559 */    if (this.jdField_a_of_type_JavaLangStringBuffer.length() > 0) {
/* 562:560 */      this.jdField_b_of_type_Int = 0;
/* 563:561 */      this.jdField_c_of_type_Int = this.jdField_a_of_type_JavaLangStringBuffer.length();
/* 564:562 */      this.jdField_a_of_type_Boolean = true;
/* 565:    */    }
/* 566:    */  }
/* 567:    */  
/* 568:566 */  public final void f() { this.jdField_a_of_type_Boolean = true; }
/* 569:    */  
/* 591:    */  public final void a(int paramInt)
/* 592:    */  {
/* 593:591 */    this.jdField_a_of_type_Int = paramInt;
/* 594:    */  }
/* 595:    */  
/* 606:    */  public final void a(ww paramww)
/* 607:    */  {
/* 608:606 */    this.jdField_a_of_type_Ww = paramww;
/* 609:    */  }
/* 610:    */  
/* 614:612 */  public final void a(wy paramwy) { this.jdField_a_of_type_Wy = paramwy; }
/* 615:    */  
/* 616:    */  public final void g() {
/* 617:615 */    if (this.jdField_a_of_type_Boolean) {
/* 618:616 */      this.jdField_a_of_type_JavaLangString = this.jdField_a_of_type_JavaLangStringBuffer.toString();
/* 619:617 */      this.jdField_b_of_type_JavaLangString = this.jdField_a_of_type_JavaLangString.substring(0, this.jdField_a_of_type_Int);
/* 620:618 */      if ((this.jdField_b_of_type_Int >= 0) && (this.jdField_c_of_type_Int >= 0)) {
/* 621:619 */        int j = Math.min(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/* 622:620 */        int k = Math.max(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/* 623:621 */        this.jdField_c_of_type_JavaLangString = this.jdField_a_of_type_JavaLangString.substring(j, k);
/* 624:622 */        this.jdField_d_of_type_JavaLangString = this.jdField_a_of_type_JavaLangString.substring(0, j);
/* 625:    */      }
/* 626:    */      else {
/* 627:625 */        this.jdField_c_of_type_JavaLangString = "";
/* 628:626 */        this.jdField_d_of_type_JavaLangString = "";
/* 629:    */      }
/* 630:    */      
/* 631:629 */      this.jdField_a_of_type_Boolean = false;
/* 632:630 */      String str = this.jdField_a_of_type_JavaLangString;wz localwz = this; if (this.jdField_a_of_type_Wy != null) localwz.jdField_a_of_type_Wy.a(str);
/* 633:631 */      this.h = (i.a(this.jdField_a_of_type_JavaLangStringBuffer.toString()) - 1);
/* 634:632 */      this.i = (i.a(this.jdField_b_of_type_JavaLangString.toString()) - 1);
/* 635:    */    }
/* 636:634 */    if ((this.jdField_b_of_type_Int >= 0) && (this.jdField_b_of_type_Int == this.jdField_c_of_type_Int)) {
/* 637:635 */      d();
/* 638:    */    }
/* 639:    */  }
/* 640:    */  
/* 642:640 */  public final int a() { return this.h; }
/* 643:    */  
/* 644:    */  private void b(int paramInt) {
/* 645:643 */    if ((Keyboard.isKeyDown(54)) || (Keyboard.isKeyDown(42)))
/* 646:    */    {
/* 647:645 */      if (this.jdField_b_of_type_Int < 0) {
/* 648:646 */        this.jdField_b_of_type_Int = paramInt;
/* 649:    */      }
/* 650:648 */      this.jdField_c_of_type_Int = this.jdField_a_of_type_Int;return;
/* 651:    */    }
/* 652:    */    
/* 653:651 */    d();
/* 654:    */  }
/* 655:    */  
/* 658:    */  public final int b()
/* 659:    */  {
/* 660:658 */    return this.i;
/* 661:    */  }
/* 662:    */  
/* 664:    */  public final int c()
/* 665:    */  {
/* 666:664 */    return this.e;
/* 667:    */  }
/* 668:    */  
/* 670:    */  public final int d()
/* 671:    */  {
/* 672:670 */    return this.f;
/* 673:    */  }
/* 674:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */