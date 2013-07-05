/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.UnicodeFont;
/*     */ import org.newdawn.slick.font.effects.ColorEffect;
/*     */ import org.newdawn.slick.font.effects.OutlineEffect;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class fo extends yz
/*     */ {
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yP jdField_b_of_type_YP;
/*     */   private yE jdField_a_of_type_YE;
/*     */   private String jdField_b_of_type_JavaLangString;
/*  30 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private static UnicodeFont jdField_a_of_type_OrgNewdawnSlickUnicodeFont;
/*     */ 
/*     */   public fo(ClientState paramClientState, String paramString)
/*     */   {
/*  37 */     super(paramClientState);
/*  38 */     this.jdField_b_of_type_JavaLangString = paramString;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*  44 */     this.jdField_a_of_type_YE.a();
/*  45 */     this.jdField_a_of_type_YP.a();
/*     */   }
/*     */ 
/*     */   public final void b() {
/*  49 */     if (this.jdField_a_of_type_Boolean) {
/*  50 */       c();
/*     */     }
/*  52 */     this.jdField_a_of_type_YP.b.set(0, this.jdField_b_of_type_JavaLangString);
/*  53 */     GlUtil.d();
/*  54 */     GL11.glEnable(3042);
/*  55 */     GL11.glBlendFunc(770, 771);
/*  56 */     r();
/*  57 */     if (0L < System.currentTimeMillis()) {
/*  58 */       this.jdField_b_of_type_YP.b.clear();
/*     */     }
/*  60 */     this.jdField_a_of_type_YE.b();
/*     */ 
/*  63 */     GL11.glDisable(3042);
/*  64 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  69 */     return 256.0F;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/*  81 */     return 256.0F;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  93 */     if (jdField_a_of_type_OrgNewdawnSlickUnicodeFont == null) {
/*  94 */       localObject = new Font("Arial", 1, 28);
/*  95 */       (
/*  96 */         fo.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = new UnicodeFont((Font)localObject))
/*  96 */         .getEffects().add(new OutlineEffect(4, Color.black));
/*  97 */       jdField_a_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ColorEffect(Color.white));
/*  98 */       jdField_a_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
/*     */       try {
/* 100 */         jdField_a_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 103 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 105 */     this.jdField_a_of_type_YP = new yP(256, 64, jdField_a_of_type_OrgNewdawnSlickUnicodeFont, a());
/*     */ 
/* 108 */     this.jdField_b_of_type_YP = new yP(256, 64, a());
/* 109 */     this.jdField_a_of_type_YE = new yE(xe.a().a("panel-std-gui-"), a());
/*     */ 
/* 113 */     (
/* 114 */       localObject = new ArrayList())
/* 114 */       .add(this.jdField_b_of_type_JavaLangString);
/* 115 */     this.jdField_a_of_type_YP.b = ((List)localObject);
/*     */ 
/* 117 */     Object localObject = new ArrayList();
/* 118 */     this.jdField_b_of_type_YP.b = ((List)localObject);
/* 119 */     this.jdField_a_of_type_YE.h(48);
/*     */ 
/* 122 */     this.jdField_a_of_type_YE.c();
/* 123 */     this.jdField_a_of_type_YP.c();
/*     */ 
/* 125 */     a(this.jdField_a_of_type_YE);
/* 126 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/* 127 */     this.jdField_a_of_type_YE.a(this.jdField_b_of_type_YP);
/*     */ 
/* 130 */     this.jdField_a_of_type_YP.a(280.0F, 80.0F, 0.0F);
/* 131 */     this.jdField_b_of_type_YP.a(300.0F, 30.0F, 0.0F);
/*     */ 
/* 133 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final void a(String paramString)
/*     */   {
/* 146 */     this.jdField_b_of_type_JavaLangString = paramString;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fo
 * JD-Core Version:    0.6.2
 */