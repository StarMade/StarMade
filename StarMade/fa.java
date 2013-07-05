/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class fa extends v
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private eW jdField_a_of_type_EW;
/*     */   private eW jdField_b_of_type_EW;
/*     */   private yG jdField_a_of_type_YG;
/*     */   private static yq jdField_a_of_type_Yq;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private w jdField_a_of_type_W;
/*     */   private String jdField_b_of_type_JavaLangString;
/*  32 */   private float jdField_a_of_type_Float = 0.0F;
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yP jdField_b_of_type_YP;
/*     */   private yP jdField_c_of_type_YP;
/*     */   private Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*     */   private String jdField_c_of_type_JavaLangString;
/*     */ 
/*     */   public fa(ClientState paramClientState, w paramw, String paramString1, String paramString2)
/*     */   {
/*  41 */     super(paramClientState);
/*  42 */     this.jdField_a_of_type_W = paramw;
/*  43 */     this.jdField_c_of_type_JavaLangString = paramString2;
/*  44 */     this.jdField_a_of_type_YE = new yE(xe.a().a("info-panel-gui-"), paramClientState);
/*     */ 
/*  46 */     this.jdField_a_of_type_YG = new yG(363.0F, 110.0F, paramClientState);
/*  47 */     this.jdField_b_of_type_JavaLangString = paramString1;
/*  48 */     this.jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F - this.jdField_a_of_type_Float);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  59 */     if (!this.jdField_a_of_type_Boolean) {
/*  60 */       c();
/*     */     }
/*     */ 
/*  69 */     GlUtil.d();
/*  70 */     r();
/*     */ 
/*  74 */     this.jdField_a_of_type_JavaxVecmathVector4f.set(1.0F, 1.0F, 1.0F, 1.0F - this.jdField_a_of_type_Float);
/*  75 */     this.jdField_a_of_type_EW.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  76 */     this.jdField_a_of_type_YE.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  77 */     this.jdField_a_of_type_YP.a(new Color(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w));
/*     */ 
/*  79 */     this.jdField_a_of_type_YE.b();
/*     */ 
/*  81 */     this.jdField_a_of_type_EW.a().c(null);
/*  82 */     this.jdField_a_of_type_YE.a().c(null);
/*  83 */     this.jdField_a_of_type_YP.a(new Color(1, 1, 1, 1));
/*     */ 
/*  86 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  93 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/*  98 */     return this.jdField_a_of_type_YE.b();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 110 */     this.jdField_a_of_type_YP = new yP(220, 100, d.n(), a());
/* 111 */     this.jdField_a_of_type_YP.b = new ArrayList();
/* 112 */     this.jdField_a_of_type_YP.b.add(this.jdField_b_of_type_JavaLangString);
/*     */ 
/* 115 */     this.jdField_b_of_type_YP = new yP(200, 30, d.d(), a());
/* 116 */     this.jdField_b_of_type_YP.b = new ArrayList();
/* 117 */     this.jdField_b_of_type_YP.b.add(this.jdField_c_of_type_JavaLangString);
/* 118 */     this.jdField_c_of_type_YP = new yP(200, 30, d.k(), a());
/* 119 */     this.jdField_c_of_type_YP.b = new ArrayList();
/* 120 */     this.jdField_c_of_type_YP.b.add("(click to drag)");
/*     */ 
/* 122 */     this.jdField_a_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.c, "OK", this.jdField_a_of_type_W);
/*     */ 
/* 124 */     this.jdField_b_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.d, "CANCEL", this.jdField_a_of_type_W);
/*     */ 
/* 127 */     if (jdField_a_of_type_Yq == null) {
/* 128 */       jdField_a_of_type_Yq = new yq(a(), this.jdField_a_of_type_YE);
/* 129 */       this.jdField_a_of_type_YE.h(48);
/*     */     } else {
/* 131 */       this.jdField_a_of_type_YE.a().set(jdField_a_of_type_Yq.a.a());
/* 132 */       jdField_a_of_type_Yq = new yq(a(), this.jdField_a_of_type_YE);
/*     */     }
/*     */ 
/* 135 */     this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YP);
/*     */ 
/* 137 */     this.jdField_a_of_type_YE.a(this.jdField_b_of_type_YP);
/* 138 */     this.jdField_a_of_type_YE.a(this.jdField_c_of_type_YP);
/* 139 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YG);
/* 140 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_EW);
/* 141 */     this.jdField_a_of_type_YE.a(jdField_a_of_type_Yq);
/*     */ 
/* 146 */     this.jdField_b_of_type_YP.a(14.0F, 10.0F, 0.0F);
/* 147 */     this.jdField_c_of_type_YP.a(280.0F, 15.0F, 0.0F);
/*     */ 
/* 149 */     this.jdField_a_of_type_YG.a(23.0F, 42.0F, 0.0F);
/*     */ 
/* 151 */     this.jdField_a_of_type_EW.b(0.5F, 0.5F, 0.5F);
/*     */ 
/* 153 */     this.jdField_b_of_type_EW.b(0.5F, 0.5F, 0.5F);
/*     */ 
/* 157 */     this.jdField_a_of_type_EW.a(330.0F, 158.0F, 0.0F);
/* 158 */     this.jdField_b_of_type_EW.a(220.0F, 158.0F, 0.0F);
/*     */ 
/* 163 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final void a(float paramFloat)
/*     */   {
/* 168 */     this.jdField_a_of_type_Float = paramFloat;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fa
 * JD-Core Version:    0.6.2
 */