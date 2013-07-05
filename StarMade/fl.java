/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class fl extends yz
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*  26 */   private float jdField_b_of_type_Float = 5.0F;
/*     */   private float c;
/*     */   private float d;
/*     */   private yP jdField_a_of_type_YP;
/*  32 */   private boolean jdField_a_of_type_Boolean = true;
/*     */ 
/*  37 */   private float e = 0.0F;
/*     */   private String jdField_b_of_type_JavaLangString;
/*  41 */   private Color jdField_a_of_type_OrgNewdawnSlickColor = new Color(1, 1, 1, 1);
/*     */   public float a;
/*     */ 
/*     */   public fl(ClientState paramClientState, String paramString, Color paramColor)
/*     */   {
/*  46 */     super(paramClientState);
/*     */ 
/*  44 */     this.jdField_a_of_type_Float = 0.0F;
/*     */ 
/*  48 */     this.jdField_a_of_type_YP = new yP(300, 300, paramClientState);
/*  49 */     this.jdField_a_of_type_YP.b = new ArrayList();
/*  50 */     this.jdField_b_of_type_JavaLangString = paramString;
/*     */ 
/*  52 */     this.jdField_a_of_type_OrgNewdawnSlickColor.r = paramColor.r;
/*  53 */     this.jdField_a_of_type_OrgNewdawnSlickColor.g = paramColor.g;
/*  54 */     this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = paramColor.jdField_b_of_type_Float;
/*  55 */     this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = paramColor.jdField_a_of_type_Float;
/*     */ 
/*  58 */     paramString = (paramClientState = paramString.split("\n")).length;
/*  58 */     for (paramColor = 0; paramColor < paramString; paramColor++) { Object localObject = paramClientState[paramColor];
/*  59 */       this.jdField_a_of_type_YP.b.add(localObject);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  69 */     if (this.jdField_a_of_type_Boolean) {
/*  70 */       c();
/*     */     }
/*  72 */     if (this.d < 0.0F) {
/*  73 */       return;
/*     */     }
/*  75 */     a().x = (xm.b() - this.a.x * this.jdField_a_of_type_YE.b() - 96.0F);
/*  76 */     a().y = this.e;
/*  77 */     if (!j()) {
/*  78 */       return;
/*     */     }
/*     */ 
/*  81 */     float f = this.jdField_b_of_type_Float - this.c;
/*  82 */     this.jdField_a_of_type_YE.a().a().set(this.jdField_a_of_type_OrgNewdawnSlickColor.r, this.jdField_a_of_type_OrgNewdawnSlickColor.g, this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float, 1.0F);
/*  83 */     if (f < 1.0F) {
/*  84 */       this.jdField_a_of_type_YE.a().a().set(this.jdField_a_of_type_OrgNewdawnSlickColor.r, this.jdField_a_of_type_OrgNewdawnSlickColor.g, this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float, f);
/*  85 */       this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = f;
/*     */     }
/*  87 */     GL11.glEnable(3042);
/*  88 */     GL11.glBlendFunc(770, 771);
/*     */ 
/*  90 */     GlUtil.d();
/*  91 */     r();
/*  92 */     this.jdField_a_of_type_YE.b();
/*  93 */     GlUtil.c();
/*     */ 
/*  95 */     GL11.glDisable(3042);
/*     */ 
/*  97 */     this.jdField_a_of_type_YE.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/*  98 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = 1.0F;
/*  99 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.r = 1.0F;
/* 100 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.g = 1.0F;
/* 101 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = 1.0F;
/*     */   }
/*     */ 
/*     */   public final float a() {
/* 105 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/* 111 */     return this.jdField_b_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 116 */     return this.jdField_a_of_type_YE.b();
/*     */   }
/*     */ 
/*     */   public final boolean a() {
/* 120 */     return this.c < this.jdField_b_of_type_Float;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 131 */     this.jdField_a_of_type_YE = new yE(xe.a().a("std-message-gui-"), a());
/* 132 */     this.jdField_a_of_type_YP.a(30.0F, 30.0F, 0.0F);
/* 133 */     this.jdField_a_of_type_YP.a(Color.white);
/* 134 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = d.b();
/* 135 */     this.jdField_a_of_type_YP.c();
/*     */ 
/* 137 */     this.jdField_a_of_type_YE.a().c(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/* 138 */     this.jdField_a_of_type_YE.c();
/*     */ 
/* 140 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/*     */ 
/* 142 */     this.e = (-1.0F * (this.jdField_a_of_type_YE.a() * this.a.y + 5.0F));
/*     */ 
/* 145 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/* 155 */     String[] arrayOfString = this.jdField_b_of_type_JavaLangString.split("\n");
/* 156 */     this.jdField_a_of_type_YP.b.clear();
/* 157 */     for (String str : arrayOfString) {
/* 158 */       this.jdField_a_of_type_YP.b.add(str);
/*     */     }
/* 160 */     this.c = 0.0F;
/*     */   }
/*     */ 
/*     */   public final void a(String paramString)
/*     */   {
/* 167 */     this.jdField_b_of_type_JavaLangString = paramString;
/*     */   }
/*     */ 
/*     */   public final void f()
/*     */   {
/* 174 */     this.d = 0.0F;
/*     */ 
/* 176 */     this.c = 0.0F;
/*     */   }
/*     */   public final void g() {
/* 179 */     if (this.c < this.jdField_b_of_type_Float - 1.0F)
/* 180 */       this.c = (this.jdField_b_of_type_Float - 1.0F);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 185 */     if (this.jdField_a_of_type_Boolean) {
/* 186 */       return;
/*     */     }
/* 188 */     if (this.d < 0.0F) {
/* 189 */       this.d += paramxq.a();
/* 190 */       return;
/*     */     }
/* 192 */     this.c += paramxq.a();
/*     */ 
/* 194 */     float f1 = this.jdField_a_of_type_Float * (this.jdField_a_of_type_YE.a() * this.a.y + 5.0F);
/*     */ 
/* 196 */     float f2 = Math.min(1.0F, Math.max(0.01F, Math.abs(this.e - f1)) / (this.jdField_a_of_type_YE.a() * this.a.y));
/*     */ 
/* 198 */     if (this.e > f1) {
/* 199 */       this.e -= paramxq.a() * 1000.0F * f2;
/* 200 */       if (this.e <= f1)
/* 201 */         this.e = f1;
/*     */     }
/* 203 */     else if (this.e < f1) {
/* 204 */       this.e += paramxq.a() * 1000.0F * f2;
/* 205 */       if (this.e >= f1)
/* 206 */         this.e = f1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fl
 * JD-Core Version:    0.6.2
 */