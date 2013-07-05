/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class id extends yz
/*     */   implements ys
/*     */ {
/*     */   private yP jdField_a_of_type_YP;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private cv jdField_a_of_type_Cv;
/*     */   private eX jdField_a_of_type_EX;
/*  31 */   private static long jdField_a_of_type_Long = 0L;
/*     */ 
/*  34 */   private static boolean b = false;
/*     */ 
/*     */   public static boolean b()
/*     */   {
/*  25 */     return (b) || (System.currentTimeMillis() - jdField_a_of_type_Long < 200L);
/*     */   }
/*     */ 
/*     */   public id(ClientState paramClientState, cv paramcv)
/*     */   {
/*  37 */     super(paramClientState);
/*  38 */     this.jdField_a_of_type_Cv = paramcv;
/*  39 */     this.jdField_a_of_type_YP = new yP(140, 30, d.h(), a());
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  46 */     paramyz = null; if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0))
/*     */     {
/*  48 */       paramyz = (ct)a();
/*     */ 
/*  50 */       if (!b()) {
/*  51 */         System.err.println("PRESSED MOUSE TO ACTIVATE");
/*  52 */         b = true;
/*     */ 
/*  54 */         paramyz.b().add(new ie(this, paramyz));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final boolean c()
/*     */   {
/*     */     cv[] arrayOfcv;
/* 129 */     int i = (arrayOfcv = cv.values()).length; for (int j = 0; j < i; j++)
/*     */     {
/*     */       cv localcv;
/* 130 */       if (((
/* 130 */         localcv = arrayOfcv[j]) != 
/* 130 */         this.jdField_a_of_type_Cv) && (localcv.a() == Keyboard.getEventKey()))
/*     */       {
/* 132 */         System.err.println("DUIPLICATE KEY");
/*     */ 
/* 134 */         cu localcu2 = localcv.a();
/*     */         cu localcu1;
/* 134 */         if (((a(localcu1 = this.jdField_a_of_type_Cv.a(), localcu2)) || (a(localcu2, localcu1)) ? 1 : 0) != 0) {
/* 135 */           System.err.println("KEYS RELATED: -> DUPLICATE");
/* 136 */           ((ct)a()).a().b("WARNING: duplicate detected:\nKeys for \"" + localcv.a() + "\"(" + localcv.b() + ") and\n\"" + this.jdField_a_of_type_Cv.a() + "\"(" + this.jdField_a_of_type_Cv.b() + ") have been\nswitched");
/*     */ 
/* 141 */           localcv.a(this.jdField_a_of_type_Cv.a());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 146 */     return true;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 161 */     if (!this.jdField_a_of_type_Boolean) {
/* 162 */       c();
/*     */     }
/* 164 */     GlUtil.d();
/* 165 */     r();
/* 166 */     this.jdField_a_of_type_YP.b.set(0, Keyboard.getKeyName(this.jdField_a_of_type_Cv.a()));
/* 167 */     this.jdField_a_of_type_YP.b();
/* 168 */     l();
/* 169 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 175 */     return 30.0F;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 180 */     return 140.0F;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   private static boolean a(cu paramcu1, cu paramcu2)
/*     */   {
/*     */     while (true)
/*     */     {
/* 193 */       System.err.println("CHEKCING " + paramcu1 + " AND " + paramcu2);
/* 194 */       if (paramcu1 == paramcu2) {
/* 195 */         return true;
/*     */       }
/* 197 */       if (paramcu1.a()) break;
/* 198 */       paramcu1 = paramcu1.a();
/*     */     }
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 208 */     this.jdField_a_of_type_YP.b = new ArrayList();
/* 209 */     this.jdField_a_of_type_YP.b.add(Keyboard.getKeyName(this.jdField_a_of_type_Cv.a()));
/* 210 */     this.jdField_a_of_type_YP.c();
/*     */ 
/* 212 */     this.g = true;
/* 213 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     id
 * JD-Core Version:    0.6.2
 */