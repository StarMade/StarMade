/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class fq extends yz
/*     */ {
/*  20 */   private float jdField_b_of_type_Float = 5.0F;
/*     */   private float c;
/*     */   private float d;
/*     */   private yP jdField_a_of_type_YP;
/*  26 */   private boolean jdField_a_of_type_Boolean = true;
/*     */ 
/*  31 */   private float e = 0.0F;
/*  32 */   private Color jdField_a_of_type_OrgNewdawnSlickColor = new Color(1, 1, 1, 1);
/*     */   private String jdField_b_of_type_JavaLangString;
/*     */   public float a;
/*     */ 
/*     */   public fq(String paramString1, ClientState paramClientState, String paramString2, Color paramColor)
/*     */   {
/*  38 */     super(paramClientState);
/*     */ 
/*  36 */     this.jdField_a_of_type_Float = 0.0F;
/*     */ 
/*  39 */     this.jdField_b_of_type_JavaLangString = paramString1;
/*  40 */     this.jdField_a_of_type_YP = new yP(800, 40, paramClientState);
/*  41 */     this.jdField_a_of_type_YP.b = new ArrayList();
/*     */ 
/*  43 */     this.jdField_a_of_type_OrgNewdawnSlickColor.r = paramColor.r;
/*  44 */     this.jdField_a_of_type_OrgNewdawnSlickColor.g = paramColor.g;
/*  45 */     this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = paramColor.jdField_b_of_type_Float;
/*  46 */     this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = paramColor.jdField_a_of_type_Float;
/*     */ 
/*  48 */     this.jdField_a_of_type_YP.b.add(paramString2);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  57 */     if (this.jdField_a_of_type_Boolean) {
/*  58 */       c();
/*     */     }
/*  60 */     if (this.d < 0.0F) {
/*  61 */       return;
/*     */     }
/*  63 */     a().x = 100.0F;
/*  64 */     a().y = (this.e + 100.0F);
/*  65 */     if (!j())
/*     */       return;
/*     */     float f;
/*  70 */     if ((
/*  70 */       f = this.jdField_b_of_type_Float - this.c) < 
/*  70 */       1.0F) {
/*  71 */       this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = f;
/*     */     }
/*  73 */     GL11.glEnable(3042);
/*  74 */     GL11.glBlendFunc(770, 771);
/*     */ 
/*  76 */     GlUtil.d();
/*  77 */     r();
/*  78 */     this.jdField_a_of_type_YP.b();
/*  79 */     GlUtil.c();
/*     */ 
/*  81 */     GL11.glDisable(3042);
/*     */ 
/*  83 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = 1.0F;
/*  84 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.r = 1.0F;
/*  85 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.g = 1.0F;
/*  86 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = 1.0F;
/*     */   }
/*     */ 
/*     */   public final boolean equals(Object paramObject)
/*     */   {
/*  93 */     return this.jdField_b_of_type_JavaLangString.equals(((fq)paramObject).jdField_b_of_type_JavaLangString);
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  98 */     return this.jdField_a_of_type_YP.a();
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/* 104 */     return this.jdField_b_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 109 */     return this.jdField_a_of_type_YP.b();
/*     */   }
/*     */ 
/*     */   public final boolean a() {
/* 113 */     return this.c < this.jdField_b_of_type_Float;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 125 */     this.jdField_a_of_type_YP.a(Color.white);
/* 126 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = d.e();
/* 127 */     this.jdField_a_of_type_YP.c();
/*     */ 
/* 133 */     this.jdField_a_of_type_Boolean = false;
/*     */ 
/* 136 */     this.e = (-1.0F * (this.jdField_a_of_type_YP.a() * this.a.y + 5.0F));
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/* 148 */     this.c = 0.0F;
/*     */   }
/*     */ 
/*     */   public final void a(String paramString)
/*     */   {
/* 162 */     this.jdField_a_of_type_YP.b.set(0, paramString);
/*     */   }
/*     */ 
/*     */   public final void f()
/*     */   {
/* 169 */     this.d = 0.0F;
/*     */ 
/* 171 */     this.c = 0.0F;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 180 */     if (this.d < 0.0F) {
/* 181 */       this.d += paramxq.a();
/* 182 */       return;
/*     */     }
/* 184 */     this.c += paramxq.a();
/*     */ 
/* 186 */     float f1 = this.jdField_a_of_type_Float * (this.jdField_a_of_type_YP.a() * this.a.y + 5.0F);
/*     */ 
/* 188 */     float f2 = Math.min(1.0F, Math.max(0.01F, Math.abs(this.e - f1)) / (this.jdField_a_of_type_YP.a() * this.a.y));
/*     */ 
/* 190 */     if (this.e > f1) {
/* 191 */       this.e -= paramxq.a() * 1000.0F * f2;
/* 192 */       if (this.e <= f1)
/* 193 */         this.e = f1;
/*     */     }
/* 195 */     else if (this.e < f1) {
/* 196 */       this.e += paramxq.a() * 1000.0F * f2;
/* 197 */       if (this.e >= f1)
/* 198 */         this.e = f1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fq
 * JD-Core Version:    0.6.2
 */