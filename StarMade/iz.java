/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class iz extends v
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private eW jdField_a_of_type_EW;
/*     */   private eW jdField_b_of_type_EW;
/*     */   private eW jdField_c_of_type_EW;
/*     */   private static yq jdField_a_of_type_Yq;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private bI jdField_a_of_type_BI;
/*     */   private eW jdField_d_of_type_EW;
/*     */   private String jdField_b_of_type_JavaLangString;
/*  35 */   private float jdField_a_of_type_Float = 0.0F;
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yP jdField_b_of_type_YP;
/*     */   private yP jdField_c_of_type_YP;
/*     */   private Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*     */   private yP jdField_d_of_type_YP;
/*     */   private ix jdField_a_of_type_Ix;
/*     */ 
/*     */   public iz(ClientState paramClientState, bI parambI, String paramString)
/*     */   {
/*  44 */     super(paramClientState);
/*  45 */     this.jdField_a_of_type_BI = parambI;
/*  46 */     this.jdField_a_of_type_YE = new yE(xe.a().a("info-panel-gui-"), paramClientState);
/*  47 */     this.jdField_b_of_type_JavaLangString = paramString;
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
/*  76 */     this.jdField_b_of_type_EW.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  77 */     this.jdField_d_of_type_EW.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  78 */     this.jdField_c_of_type_EW.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  79 */     this.jdField_a_of_type_YE.a().c(this.jdField_a_of_type_JavaxVecmathVector4f);
/*  80 */     this.jdField_a_of_type_YP.a(new Color(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w));
/*     */ 
/*  82 */     this.jdField_a_of_type_YE.b();
/*     */ 
/*  84 */     this.jdField_a_of_type_EW.a().c(null);
/*  85 */     this.jdField_b_of_type_EW.a().c(null);
/*  86 */     this.jdField_d_of_type_EW.a().c(null);
/*  87 */     this.jdField_c_of_type_EW.a().c(null);
/*  88 */     this.jdField_a_of_type_YE.a().c(null);
/*  89 */     this.jdField_a_of_type_YP.a(new Color(1, 1, 1, 1));
/*     */ 
/*  92 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  99 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 104 */     return this.jdField_a_of_type_YE.b();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 116 */     this.jdField_d_of_type_YP = new yP(10, 10, d.k(), a());
/* 117 */     this.jdField_a_of_type_Ix = new ix(a());
/*     */ 
/* 119 */     this.jdField_a_of_type_YP = new yP(200, 200, d.n(), a());
/* 120 */     this.jdField_a_of_type_YP.b = new ArrayList();
/* 121 */     this.jdField_a_of_type_YP.b.add(this.jdField_b_of_type_JavaLangString);
/*     */ 
/* 124 */     this.jdField_b_of_type_YP = new yP(200, 30, d.d(), a());
/* 125 */     this.jdField_b_of_type_YP.b = new ArrayList();
/* 126 */     this.jdField_b_of_type_YP.b.add("Tutorial");
/* 127 */     this.jdField_c_of_type_YP = new yP(200, 30, d.k(), a());
/* 128 */     this.jdField_c_of_type_YP.b = new ArrayList();
/* 129 */     this.jdField_c_of_type_YP.b.add("(click to drag)");
/*     */ 
/* 131 */     this.jdField_a_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.o, "NEXT", this.jdField_a_of_type_BI);
/*     */ 
/* 133 */     this.jdField_b_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.p, "BACK", this.jdField_a_of_type_BI);
/*     */ 
/* 135 */     this.jdField_d_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.q, "SKIP", this.jdField_a_of_type_BI);
/*     */ 
/* 137 */     this.jdField_c_of_type_EW = new eW(xe.a().a("buttons-8x8-gui-"), a(), cU.r, "END", this.jdField_a_of_type_BI);
/*     */ 
/* 140 */     if (jdField_a_of_type_Yq == null) {
/* 141 */       jdField_a_of_type_Yq = new yq(a(), this.jdField_a_of_type_YE);
/* 142 */       this.jdField_a_of_type_YE.h(48);
/* 143 */       this.jdField_a_of_type_YE.a().y -= 100.0F;
/*     */     } else {
/* 145 */       this.jdField_a_of_type_YE.a().set(jdField_a_of_type_Yq.a.a());
/* 146 */       jdField_a_of_type_Yq = new yq(a(), this.jdField_a_of_type_YE);
/*     */     }
/*     */ 
/* 151 */     this.jdField_a_of_type_YE.a(this.jdField_b_of_type_YP);
/* 152 */     this.jdField_a_of_type_YE.a(this.jdField_c_of_type_YP);
/* 153 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/* 154 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_EW);
/*     */ 
/* 156 */     this.jdField_a_of_type_YE.a(this.jdField_d_of_type_EW);
/*     */ 
/* 158 */     this.jdField_a_of_type_YE.a(jdField_a_of_type_Yq);
/*     */ 
/* 161 */     if ((this.jdField_a_of_type_BI.a() instanceof ce)) {
/* 162 */       this.jdField_d_of_type_YP.a("Show tutorial next time");
/* 163 */       this.jdField_a_of_type_YE.a(this.jdField_d_of_type_YP);
/* 164 */       this.jdField_a_of_type_YE.a(this.jdField_a_of_type_Ix);
/* 165 */       this.jdField_d_of_type_YP.a(133.0F, 165.0F, 0.0F);
/* 166 */       this.jdField_a_of_type_Ix.a(98.0F, 156.0F, 0.0F);
/*     */     } else {
/* 168 */       this.jdField_a_of_type_YE.a(this.jdField_b_of_type_EW);
/* 169 */       this.jdField_a_of_type_YE.a(this.jdField_c_of_type_EW);
/*     */     }
/*     */ 
/* 172 */     this.jdField_b_of_type_YP.a(14.0F, 10.0F, 0.0F);
/* 173 */     this.jdField_c_of_type_YP.a(280.0F, 15.0F, 0.0F);
/*     */ 
/* 175 */     this.jdField_a_of_type_YP.a(30.0F, 50.0F, 0.0F);
/*     */ 
/* 177 */     this.jdField_a_of_type_EW.b(0.5F, 0.5F, 0.5F);
/*     */ 
/* 179 */     this.jdField_b_of_type_EW.b(0.5F, 0.5F, 0.5F);
/* 180 */     this.jdField_d_of_type_EW.b(0.5F, 0.5F, 0.5F);
/* 181 */     this.jdField_c_of_type_EW.b(0.5F, 0.5F, 0.5F);
/*     */ 
/* 185 */     this.jdField_a_of_type_EW.a(330.0F, 158.0F, 0.0F);
/* 186 */     this.jdField_b_of_type_EW.a(250.0F, 158.0F, 0.0F);
/* 187 */     this.jdField_d_of_type_EW.a(110.0F, 158.0F, 0.0F);
/* 188 */     this.jdField_c_of_type_EW.a(30.0F, 158.0F, 0.0F);
/*     */ 
/* 193 */     this.jdField_d_of_type_EW.b(true);
/* 194 */     if (((this.jdField_a_of_type_BI.a() instanceof cf)) || (!(this.jdField_a_of_type_BI.a() instanceof cc))) {
/* 195 */       this.jdField_d_of_type_EW.b(false);
/*     */     }
/* 197 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final void a(float paramFloat)
/*     */   {
/* 202 */     this.jdField_a_of_type_Float = paramFloat;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iz
 * JD-Core Version:    0.6.2
 */