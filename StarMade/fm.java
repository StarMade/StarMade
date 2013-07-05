/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class fm extends yz
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private float jdField_a_of_type_Float;
/*     */   private float jdField_b_of_type_Float;
/*     */   private yP jdField_a_of_type_YP;
/*  29 */   private boolean jdField_a_of_type_Boolean = true;
/*     */ 
/*  33 */   private zE jdField_a_of_type_ZE = new zE(30.0F);
/*     */   private String jdField_b_of_type_JavaLangString;
/*  37 */   private Color jdField_a_of_type_OrgNewdawnSlickColor = new Color(1, 1, 1, 1);
/*     */ 
/*  39 */   private boolean jdField_b_of_type_Boolean = true;
/*     */ 
/*     */   public fm(ClientState paramClientState, String paramString, Color paramColor)
/*     */   {
/*  43 */     super(paramClientState);
/*  44 */     if (this.jdField_a_of_type_YE == null) {
/*  45 */       this.jdField_a_of_type_YE = new yE(xe.a().a("std-message-gui-"), paramClientState);
/*  46 */       this.jdField_a_of_type_YP = new yP(300, 300, paramClientState);
/*  47 */       paramClientState = null; this.jdField_a_of_type_YP.b = new ArrayList();
/*     */     }
/*     */ 
/*  50 */     Object localObject = null; this.jdField_b_of_type_JavaLangString = paramString;
/*     */ 
/*  52 */     this.jdField_a_of_type_OrgNewdawnSlickColor.r = paramColor.r;
/*  53 */     this.jdField_a_of_type_OrgNewdawnSlickColor.g = paramColor.g;
/*  54 */     this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = paramColor.jdField_b_of_type_Float;
/*  55 */     this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = paramColor.jdField_a_of_type_Float;
/*     */ 
/*  57 */     this.jdField_a_of_type_YP.b.clear();
/*     */ 
/*  59 */     paramString = (paramClientState = paramString.split("\n")).length;
/*  59 */     for (paramColor = 0; paramColor < paramString; paramColor++) { localObject = paramClientState[paramColor];
/*  60 */       this.jdField_a_of_type_YP.b.add(localObject); }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/*  69 */     this.jdField_b_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final void b() {
/*  73 */     if (this.jdField_a_of_type_Boolean) {
/*  74 */       c();
/*     */     }
/*  76 */     if ((!this.jdField_b_of_type_Boolean) || (this.jdField_b_of_type_Float < 0.0F)) {
/*  77 */       return;
/*     */     }
/*     */ 
/*  81 */     this.jdField_a_of_type_YE.a().a().set(this.jdField_a_of_type_OrgNewdawnSlickColor.r, this.jdField_a_of_type_OrgNewdawnSlickColor.g, this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float, 1.0F);
/*  82 */     if (this.jdField_a_of_type_Float < 0.3F)
/*     */     {
/*  84 */       if (this.jdField_a_of_type_ZE.a() < 0.5F) {
/*  85 */         float f = Math.max(1.0F, 1.0F - this.jdField_a_of_type_Float * 5.0F);
/*  86 */         this.jdField_a_of_type_YE.a().a().set(f / 2.0F + this.jdField_a_of_type_OrgNewdawnSlickColor.r, f / 2.0F + this.jdField_a_of_type_OrgNewdawnSlickColor.g, f / 2.0F + this.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float, 1.0F);
/*     */       }
/*     */     }
/*  89 */     GL11.glEnable(3042);
/*  90 */     GL11.glBlendFunc(770, 771);
/*     */ 
/*  92 */     GlUtil.d();
/*  93 */     r();
/*  94 */     this.jdField_a_of_type_YE.b();
/*  95 */     GlUtil.c();
/*     */ 
/*  97 */     GL11.glDisable(3042);
/*     */ 
/*  99 */     this.jdField_a_of_type_YE.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/* 100 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_a_of_type_Float = 1.0F;
/* 101 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.r = 1.0F;
/* 102 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.g = 1.0F;
/* 103 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickColor.jdField_b_of_type_Float = 1.0F;
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 108 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/* 115 */     return this.jdField_b_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 120 */     return this.jdField_a_of_type_YE.b();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 137 */     this.jdField_a_of_type_YP.a(30.0F, 30.0F, 0.0F);
/* 138 */     this.jdField_a_of_type_YP.a(Color.white);
/* 139 */     this.jdField_a_of_type_YP.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = d.b();
/* 140 */     this.jdField_a_of_type_YP.c();
/*     */ 
/* 143 */     this.jdField_a_of_type_YE.a().c(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/* 144 */     this.jdField_a_of_type_YE.c();
/*     */ 
/* 146 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/*     */ 
/* 151 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final void f()
/*     */   {
/* 160 */     this.jdField_b_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final void g()
/*     */   {
/* 173 */     this.jdField_b_of_type_Float = 0.0F;
/*     */ 
/* 175 */     this.jdField_a_of_type_Float = 0.0F;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq) {
/* 179 */     if (this.jdField_b_of_type_Float < 0.0F) {
/* 180 */       this.jdField_b_of_type_Float += paramxq.a();
/* 181 */       return;
/*     */     }
/* 183 */     this.jdField_a_of_type_Float += paramxq.a();
/* 184 */     this.jdField_a_of_type_ZE.a(paramxq);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fm
 * JD-Core Version:    0.6.2
 */