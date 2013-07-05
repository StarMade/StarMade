/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.Clipboard;
/*     */ import java.awt.datatransfer.ClipboardOwner;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.datatransfer.UnsupportedFlavorException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.schine.graphicsengine.core.settings.PrefixNotFoundException;
/*     */ 
/*     */ public class wz
/*     */   implements ClipboardOwner, wx
/*     */ {
/*     */   private final ArrayList jdField_a_of_type_JavaUtilArrayList;
/*     */   private int jdField_a_of_type_Int;
/*     */   private final StringBuffer jdField_a_of_type_JavaLangStringBuffer;
/*  47 */   private String jdField_a_of_type_JavaLangString = "";
/*  48 */   private int jdField_b_of_type_Int = -1; private int jdField_c_of_type_Int = -1;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private int jdField_d_of_type_Int;
/*     */   private wy jdField_a_of_type_Wy;
/*  54 */   private String jdField_b_of_type_JavaLangString = "";
/*     */ 
/*  56 */   private String jdField_c_of_type_JavaLangString = "";
/*  57 */   private String jdField_d_of_type_JavaLangString = "";
/*     */   private wB jdField_a_of_type_WB;
/*     */   private final int e;
/*  61 */   private int f = 1;
/*     */ 
/*  63 */   private ww jdField_a_of_type_Ww = new wA(this);
/*     */ 
/*  77 */   private int g = 1;
/*     */   private int h;
/*     */   private int i;
/*     */ 
/*     */   public wz(int paramInt1, int paramInt2, wB paramwB)
/*     */   {
/*  81 */     this.jdField_a_of_type_JavaLangStringBuffer = new StringBuffer(paramInt1);
/*  82 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  83 */     this.e = paramInt1;
/*  84 */     this.f = paramInt2;
/*  85 */     this.jdField_a_of_type_WB = paramwB;
/*  86 */     this.jdField_a_of_type_JavaUtilArrayList.add("/load Fireball testship");
/*  87 */     this.jdField_a_of_type_JavaUtilArrayList.add("/give_category_items schema 100 ship");
/*  88 */     this.jdField_a_of_type_JavaUtilArrayList.add("/give_credits schema 9999999999");
/*  89 */     this.jdField_a_of_type_JavaUtilArrayList.add("/jump");
/*     */   }
/*     */   public final void a(String paramString) {
/*  92 */     if ((this.jdField_c_of_type_Int >= 0) && (this.jdField_b_of_type_Int >= 0)) {
/*  93 */       j = Math.min(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/*  94 */       int k = Math.max(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/*  95 */       this.jdField_a_of_type_JavaLangStringBuffer.delete(j, k);
/*  96 */       this.jdField_a_of_type_Int = j;
/*  97 */       this.jdField_a_of_type_Boolean = true;
/*     */     }
/*  99 */     for (int j = 0; (j < paramString.length()) && (this.jdField_a_of_type_JavaLangStringBuffer.length() < this.e); j++) {
/* 100 */       this.jdField_a_of_type_JavaLangStringBuffer.insert(this.jdField_a_of_type_Int, paramString.charAt(j));
/*     */ 
/* 103 */       this.jdField_a_of_type_Int += 1;
/* 104 */       this.jdField_a_of_type_Boolean = true;
/*     */     }
/*     */ 
/* 108 */     d();
/*     */   }
/*     */ 
/*     */   private void h()
/*     */   {
/* 160 */     int j = this.jdField_a_of_type_Int;
/* 161 */     if (this.jdField_a_of_type_Int > 0) {
/* 162 */       this.jdField_a_of_type_Int -= 1;
/* 163 */       if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29)))
/*     */       {
/* 166 */         while ((this.jdField_a_of_type_Int > 0) && (' ' == this.jdField_a_of_type_JavaLangStringBuffer.charAt(this.jdField_a_of_type_Int - 1))) {
/* 167 */           this.jdField_a_of_type_Int -= 1;
/*     */         }
/*     */ 
/* 171 */         while ((this.jdField_a_of_type_Int > 0) && (' ' != this.jdField_a_of_type_JavaLangStringBuffer.charAt(this.jdField_a_of_type_Int - 1))) {
/* 172 */           this.jdField_a_of_type_Int -= 1;
/*     */         }
/*     */       }
/* 175 */       this.jdField_a_of_type_Boolean = true;
/*     */     }
/* 177 */     b(j);
/*     */   }
/*     */ 
/*     */   private void i()
/*     */   {
/* 189 */     int j = this.jdField_a_of_type_Int;
/* 190 */     if (this.jdField_a_of_type_Int < this.jdField_a_of_type_JavaLangStringBuffer.length()) {
/* 191 */       this.jdField_a_of_type_Int += 1;
/* 192 */       if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29)))
/*     */       {
/* 196 */         while ((this.jdField_a_of_type_Int < this.jdField_a_of_type_JavaLangStringBuffer.length()) && (' ' != this.jdField_a_of_type_JavaLangStringBuffer.charAt(this.jdField_a_of_type_Int))) {
/* 197 */           System.err.println("chat carrier reset!!! right ");
/* 198 */           this.jdField_a_of_type_Int += 1;
/*     */         }
/*     */ 
/* 201 */         while ((this.jdField_a_of_type_Int < this.jdField_a_of_type_JavaLangStringBuffer.length()) && (' ' == this.jdField_a_of_type_JavaLangStringBuffer.charAt(this.jdField_a_of_type_Int))) {
/* 202 */           System.err.println("chat carrier reset!!! right ");
/* 203 */           this.jdField_a_of_type_Int += 1;
/*     */         }
/*     */       }
/*     */ 
/* 207 */       this.jdField_a_of_type_Boolean = true;
/*     */     }
/* 209 */     b(j);
/*     */   }
/*     */ 
/*     */   private void j() {
/* 213 */     if (this.f == 1) {
/* 214 */       this.jdField_d_of_type_Int += 1;
/* 215 */       if (this.jdField_d_of_type_Int <= this.jdField_a_of_type_JavaUtilArrayList.size()) {
/* 216 */         this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 217 */         this.jdField_a_of_type_JavaLangStringBuffer.append((String)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - this.jdField_d_of_type_Int));
/* 218 */         System.err.println("chat carrier reset!!! up " + this.jdField_a_of_type_JavaLangStringBuffer.length());
/*     */ 
/* 220 */         this.jdField_a_of_type_Int = this.jdField_a_of_type_JavaLangStringBuffer.length();
/*     */       } else {
/* 222 */         this.jdField_d_of_type_Int = this.jdField_a_of_type_JavaUtilArrayList.size();
/*     */       }
/*     */     }
/* 225 */     else if (this.i > 0)
/*     */     {
/*     */       int j;
/* 228 */       if ((
/* 228 */         j = Math.max(0, this.jdField_b_of_type_JavaLangString.lastIndexOf("\n"))) >= 0)
/*     */       {
/* 230 */         int k = this.jdField_b_of_type_JavaLangString.substring(0, j)
/* 230 */           .lastIndexOf("\n");
/*     */ 
/* 232 */         int m = j - k;
/* 233 */         j = k + Math.min(m, this.jdField_a_of_type_Int - j);
/* 234 */         System.err.println("CHAT CARRIER: " + this.jdField_a_of_type_Int + " -> " + j);
/* 235 */         while ((this.jdField_a_of_type_Int > 0) && (this.jdField_a_of_type_Int > j)) {
/* 236 */           this.jdField_a_of_type_Int -= 1;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 241 */     d();
/* 242 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   private void k()
/*     */   {
/* 247 */     if (this.f == 1) {
/* 248 */       this.jdField_d_of_type_Int -= 1;
/* 249 */       if (this.jdField_d_of_type_Int > 0) {
/* 250 */         this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 251 */         this.jdField_a_of_type_JavaLangStringBuffer.append((String)this.jdField_a_of_type_JavaUtilArrayList.get(this.jdField_a_of_type_JavaUtilArrayList.size() - this.jdField_d_of_type_Int));
/* 252 */         System.err.println("chat carrier reset!!! down " + this.jdField_a_of_type_JavaLangStringBuffer.length());
/*     */ 
/* 254 */         this.jdField_a_of_type_Int = this.jdField_a_of_type_JavaLangStringBuffer.length();
/*     */       } else {
/* 256 */         this.jdField_d_of_type_Int = 0;
/* 257 */         this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 258 */         System.err.println("chat carrier reset!!! keyDown");
/* 259 */         this.jdField_a_of_type_Int = 0;
/*     */       }
/*     */     }
/* 262 */     else if (this.i < this.h) {
/* 263 */       int j = this.jdField_b_of_type_JavaLangString.lastIndexOf("\n");
/*     */       int k;
/* 265 */       if ((
/* 265 */         k = this.jdField_a_of_type_JavaLangStringBuffer.indexOf("\n", this.jdField_a_of_type_Int)) >= 0)
/*     */       {
/*     */         int m;
/* 268 */         if ((
/* 268 */           m = this.jdField_a_of_type_JavaLangStringBuffer.indexOf("\n", k + 1)) < 0)
/*     */         {
/* 269 */           m = this.jdField_a_of_type_JavaLangStringBuffer.length();
/*     */         }
/* 271 */         int n = m - k;
/* 272 */         System.err.println("MAX " + n + " / " + (k - this.jdField_a_of_type_Int) + "; next: " + k + " NNext " + m);
/* 273 */         j = k + Math.min(n, this.jdField_a_of_type_Int - j);
/* 274 */         while ((this.jdField_a_of_type_Int < this.jdField_a_of_type_JavaLangStringBuffer.length()) && (this.jdField_a_of_type_Int < j))
/* 275 */           this.jdField_a_of_type_Int += 1;
/*     */       }
/*     */       else {
/* 278 */         System.err.println("DOWN: " + j + " ---- " + k);
/*     */       }
/*     */     }
/*     */ 
/* 282 */     d();
/* 283 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final void a() {
/* 287 */     d();
/* 288 */     this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/* 289 */     this.jdField_a_of_type_Int = 0;
/* 290 */     this.jdField_a_of_type_Boolean = true;
/* 291 */     g();
/*     */   }
/*     */   private void l() {
/* 294 */     System.err.println("trying copy");
/* 295 */     if ((this.jdField_c_of_type_Int >= 0) && (this.jdField_b_of_type_Int >= 0)) {
/* 296 */       Object localObject = this.jdField_c_of_type_JavaLangString; wz localwz = this; localObject = new StringSelection((String)localObject); Toolkit.getDefaultToolkit().getSystemClipboard().setContents((Transferable)localObject, localwz);
/* 297 */       System.err.println("Copied to clipboard: " + this.jdField_c_of_type_JavaLangString);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 315 */     if (this.jdField_a_of_type_JavaLangStringBuffer.length() >= 0) {
/* 316 */       String str = this.jdField_a_of_type_JavaLangStringBuffer.toString();
/* 317 */       if ((this.jdField_a_of_type_Ww != null) && (!this.jdField_a_of_type_Ww.a(str, this.jdField_a_of_type_WB))) {
/* 318 */         return;
/*     */       }
/* 320 */       this.jdField_a_of_type_WB.onTextEnter(str, !str.startsWith("/"));
/*     */ 
/* 322 */       this.jdField_a_of_type_JavaUtilArrayList.add(str);
/* 323 */       this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/*     */ 
/* 325 */       this.jdField_a_of_type_Int = 0;
/* 326 */       this.jdField_d_of_type_Int = 0;
/*     */ 
/* 328 */       this.jdField_a_of_type_Boolean = true;
/* 329 */       d();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/* 340 */     return this.jdField_a_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final String b()
/*     */   {
/* 347 */     return this.jdField_b_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final String c()
/*     */   {
/* 354 */     return this.jdField_c_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final String d()
/*     */   {
/* 361 */     return this.jdField_d_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final ArrayList a()
/*     */   {
/* 375 */     return this.jdField_a_of_type_JavaUtilArrayList;
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent()
/*     */   {
/* 395 */     if (Keyboard.getEventKeyState())
/*     */     {
/*     */       wz localwz;
/*     */       int j;
/*     */       int m;
/* 398 */       switch (Keyboard.getEventKey()) {
/*     */       case 28:
/* 400 */         if (this.f == 1) {
/* 401 */           if (!Keyboard.isRepeatEvent()) {
/* 402 */             b();
/*     */           }
/*     */         }
/* 405 */         else if (this.h + 1 < this.f)
/* 406 */           a("\n");
/*     */         else {
/* 408 */           System.err.println("[TextAreaInput] line limit reached " + this.h + "/" + this.f);
/*     */         }
/*     */ 
/* 412 */         break;
/*     */       case 211:
/* 414 */         localwz = this; if ((this.jdField_c_of_type_Int >= 0) && (localwz.jdField_b_of_type_Int >= 0)) { j = Math.min(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int); m = Math.max(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int); localwz.jdField_a_of_type_JavaLangStringBuffer.delete(j, m); localwz.jdField_a_of_type_Int = j; localwz.d(); } else if (localwz.jdField_a_of_type_Int < localwz.jdField_a_of_type_JavaLangStringBuffer.length()) { if ((localwz.jdField_c_of_type_Int >= 0) && (localwz.jdField_b_of_type_Int >= 0)) { j = Math.min(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int); m = Math.max(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int); localwz.jdField_a_of_type_JavaLangStringBuffer.delete(j, m); localwz.jdField_a_of_type_Int = j; localwz.d(); } else { localwz.jdField_a_of_type_JavaLangStringBuffer.delete(localwz.jdField_a_of_type_Int, localwz.jdField_a_of_type_Int + 1); } localwz.jdField_a_of_type_Boolean = true; } localwz.d();
/* 415 */         break;
/*     */       case 203:
/* 417 */         h();
/* 418 */         break;
/*     */       case 205:
/* 420 */         i();
/* 421 */         break;
/*     */       case 14:
/* 423 */         localwz = this; if (this.jdField_a_of_type_JavaLangStringBuffer.length() > 0) { if ((localwz.jdField_c_of_type_Int >= 0) && (localwz.jdField_b_of_type_Int >= 0)) { j = Math.min(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int); m = Math.max(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int); localwz.jdField_a_of_type_JavaLangStringBuffer.delete(j, m); localwz.jdField_a_of_type_Int = Math.max(0, j); localwz.d(); } else { localwz.jdField_a_of_type_JavaLangStringBuffer.delete(Math.max(0, localwz.jdField_a_of_type_Int - 1), localwz.jdField_a_of_type_Int); localwz.jdField_a_of_type_Int -= 1; localwz.jdField_a_of_type_Int = Math.max(0, localwz.jdField_a_of_type_Int); } localwz.jdField_a_of_type_Boolean = true; } localwz.d();
/* 424 */         break;
/*     */       case 200:
/* 426 */         j();
/* 427 */         break;
/*     */       case 208:
/* 429 */         k();
/* 430 */         break;
/*     */       case 199:
/* 432 */         localwz = this; j = this.jdField_a_of_type_Int; System.err.println("chat carrier reset!!! pos1"); localwz.jdField_a_of_type_Int = 0; localwz.b(j); localwz.jdField_a_of_type_Boolean = true;
/* 433 */         break;
/*     */       case 207:
/* 435 */         localwz = this; j = this.jdField_a_of_type_Int; localwz.jdField_a_of_type_Int = Math.max(0, localwz.jdField_a_of_type_JavaLangStringBuffer.length()); localwz.b(j); localwz.jdField_a_of_type_Boolean = true;
/* 436 */         break;
/*     */       case 47:
/* 439 */         if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
/* 440 */           localwz = this;
/*     */           try { DataFlavor localDataFlavor = DataFlavor.stringFlavor; String str = "";
/*     */             Transferable localTransferable;
/* 440 */             if ((((localTransferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null)) != null) && (localTransferable.isDataFlavorSupported(localDataFlavor)) ? 1 : 0) != 0) str = (String)localTransferable.getTransferData(localDataFlavor); localwz.a(str); } catch (UnsupportedFlavorException localUnsupportedFlavorException) { localUnsupportedFlavorException.printStackTrace(); } catch (IOException localIOException) { localIOException.printStackTrace(); }
/*     */         }
/*     */         else {
/* 443 */           m();
/* 444 */         }break;
/*     */       case 46:
/* 446 */         if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
/* 447 */           l();
/*     */         }
/*     */         else
/* 450 */           m();
/* 451 */         break;
/*     */       case 30:
/* 453 */         if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
/* 454 */           e();
/*     */         }
/*     */         else
/* 457 */           m();
/* 458 */         break;
/*     */       case 45:
/* 460 */         if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
/* 461 */           localwz = this; l(); if ((localwz.jdField_c_of_type_Int >= 0) && (localwz.jdField_b_of_type_Int >= 0)) { int k = Math.min(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int); int n = Math.max(localwz.jdField_b_of_type_Int, localwz.jdField_c_of_type_Int); System.err.println("current: " + localwz.jdField_a_of_type_JavaLangStringBuffer.toString()); localwz.jdField_a_of_type_JavaLangStringBuffer.delete(k, n); localwz.jdField_a_of_type_Int = k; localwz.d(); localwz.jdField_a_of_type_Boolean = true; }
/*     */         }
/*     */         else {
/* 464 */           m();
/* 465 */         }break;
/*     */       case 15:
/* 468 */         n();
/* 469 */         break;
/*     */       default:
/* 471 */         m();
/*     */       }
/*     */     }
/*     */ 
/* 475 */     g();
/*     */   }
/*     */ 
/*     */   public static void c()
/*     */   {
/*     */   }
/*     */ 
/*     */   private void m()
/*     */   {
/*     */     char c1;
/* 488 */     if (!Character.isIdentifierIgnorable(c1 = Keyboard.getEventCharacter()))
/*     */     {
/* 489 */       String str = String.valueOf(c1);
/* 490 */       a(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void lostOwnership(Clipboard paramClipboard, Transferable paramTransferable)
/*     */   {
/* 497 */     System.out.println("Lost clipboard ownership " + this);
/*     */   }
/*     */ 
/*     */   private void n()
/*     */   {
/*     */     try
/*     */     {
/*     */       String[] arrayOfString;
/* 509 */       if ((
/* 509 */         arrayOfString = this.jdField_a_of_type_WB.getCommandPrefixes()) != null)
/*     */       {
/* 510 */         for (int j = 0; j < arrayOfString.length; j++)
/* 511 */           if ((this.jdField_a_of_type_JavaLangStringBuffer.length() >= arrayOfString[j].length()) && (this.jdField_a_of_type_JavaLangStringBuffer.indexOf(arrayOfString[j]) == 0)) {
/* 512 */             String str = this.jdField_a_of_type_JavaLangStringBuffer.substring(arrayOfString[j].length());
/* 513 */             this.jdField_a_of_type_JavaLangStringBuffer.delete(0, this.jdField_a_of_type_JavaLangStringBuffer.length());
/*     */ 
/* 515 */             this.jdField_a_of_type_Int = 0;
/* 516 */             this.jdField_d_of_type_Int = 0;
/*     */ 
/* 518 */             this.jdField_a_of_type_Boolean = true;
/* 519 */             d();
/* 520 */             System.err.println("AUTOCOMPLETE ON PREFIX: " + arrayOfString[j] + " with \"" + str + "\"");
/* 521 */             a(arrayOfString[j] + this.jdField_a_of_type_WB.handleAutoComplete(str, this.jdField_a_of_type_WB, arrayOfString[j]));
/*     */           }
/*     */       }
/*     */       return;
/*     */     }
/*     */     catch (PrefixNotFoundException localPrefixNotFoundException)
/*     */     {
/* 528 */       localPrefixNotFoundException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 550 */     if ((this.jdField_c_of_type_Int >= 0) || (this.jdField_b_of_type_Int >= 0)) {
/* 551 */       this.jdField_c_of_type_Int = -1;
/* 552 */       this.jdField_b_of_type_Int = -1;
/* 553 */       this.jdField_a_of_type_Boolean = true;
/*     */     }
/* 555 */     g();
/*     */   }
/*     */ 
/*     */   public final void e() {
/* 559 */     if (this.jdField_a_of_type_JavaLangStringBuffer.length() > 0) {
/* 560 */       this.jdField_b_of_type_Int = 0;
/* 561 */       this.jdField_c_of_type_Int = this.jdField_a_of_type_JavaLangStringBuffer.length();
/* 562 */       this.jdField_a_of_type_Boolean = true;
/*     */     }
/*     */   }
/*     */ 
/* 566 */   public final void f() { this.jdField_a_of_type_Boolean = true; }
/*     */ 
/*     */ 
/*     */   public final void a(int paramInt)
/*     */   {
/* 591 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final void a(ww paramww)
/*     */   {
/* 606 */     this.jdField_a_of_type_Ww = paramww;
/*     */   }
/*     */ 
/*     */   public final void a(wy paramwy)
/*     */   {
/* 612 */     this.jdField_a_of_type_Wy = paramwy;
/*     */   }
/*     */   public final void g() {
/* 615 */     if (this.jdField_a_of_type_Boolean) {
/* 616 */       this.jdField_a_of_type_JavaLangString = this.jdField_a_of_type_JavaLangStringBuffer.toString();
/* 617 */       this.jdField_b_of_type_JavaLangString = this.jdField_a_of_type_JavaLangString.substring(0, this.jdField_a_of_type_Int);
/* 618 */       if ((this.jdField_b_of_type_Int >= 0) && (this.jdField_c_of_type_Int >= 0)) {
/* 619 */         int j = Math.min(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/* 620 */         int k = Math.max(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/* 621 */         this.jdField_c_of_type_JavaLangString = this.jdField_a_of_type_JavaLangString.substring(j, k);
/* 622 */         this.jdField_d_of_type_JavaLangString = this.jdField_a_of_type_JavaLangString.substring(0, j);
/*     */       }
/*     */       else {
/* 625 */         this.jdField_c_of_type_JavaLangString = "";
/* 626 */         this.jdField_d_of_type_JavaLangString = "";
/*     */       }
/*     */ 
/* 629 */       this.jdField_a_of_type_Boolean = false;
/* 630 */       String str = this.jdField_a_of_type_JavaLangString; wz localwz = this; if (this.jdField_a_of_type_Wy != null) localwz.jdField_a_of_type_Wy.a(str);
/* 631 */       this.h = (i.a(this.jdField_a_of_type_JavaLangStringBuffer.toString()) - 1);
/* 632 */       this.i = (i.a(this.jdField_b_of_type_JavaLangString.toString()) - 1);
/*     */     }
/* 634 */     if ((this.jdField_b_of_type_Int >= 0) && (this.jdField_b_of_type_Int == this.jdField_c_of_type_Int))
/* 635 */       d();
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/* 640 */     return this.h;
/*     */   }
/*     */   private void b(int paramInt) {
/* 643 */     if ((Keyboard.isKeyDown(54)) || (Keyboard.isKeyDown(42)))
/*     */     {
/* 645 */       if (this.jdField_b_of_type_Int < 0) {
/* 646 */         this.jdField_b_of_type_Int = paramInt;
/*     */       }
/* 648 */       this.jdField_c_of_type_Int = this.jdField_a_of_type_Int; return;
/*     */     }
/*     */ 
/* 651 */     d();
/*     */   }
/*     */ 
/*     */   public final int b()
/*     */   {
/* 658 */     return this.i;
/*     */   }
/*     */ 
/*     */   public final int c()
/*     */   {
/* 664 */     return this.e;
/*     */   }
/*     */ 
/*     */   public final int d()
/*     */   {
/* 670 */     return this.f;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wz
 * JD-Core Version:    0.6.2
 */