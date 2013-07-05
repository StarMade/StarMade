/*     */ import javax.vecmath.Vector3f;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.ChatSystem;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class yu extends yz
/*     */ {
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yP jdField_b_of_type_YP;
/*  17 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private yO jdField_a_of_type_YO;
/*     */   private yt jdField_a_of_type_Yt;
/*  24 */   private String jdField_b_of_type_JavaLangString = "";
/*     */ 
/*     */   public yu(ClientState paramClientState) {
/*  27 */     super(paramClientState);
/*     */ 
/*  29 */     this.jdField_b_of_type_YP = new yP(d.n(), new Color(0.8F, 0.8F, 0.8F, 0.8F), paramClientState);
/*     */ 
/*  31 */     this.jdField_b_of_type_YP.a(new yv(this));
/*     */ 
/*  42 */     this.jdField_a_of_type_YP = new yP(300, 300, d.n(), paramClientState);
/*  43 */     d.n(); this.jdField_a_of_type_Yt = new yt(paramClientState);
/*  44 */     this.jdField_a_of_type_YO = new yO(d.n(), paramClientState);
/*  45 */     this.jdField_a_of_type_YO.jdField_b_of_type_JavaLangString = "[CHAT]: ";
/*     */   }
/*     */ 
/*     */   public final void a() {
/*  49 */     this.jdField_a_of_type_YP.a();
/*     */   }
/*     */   public final void a(xq paramxq) {
/*  52 */     this.jdField_a_of_type_YO.a(paramxq);
/*     */   }
/*     */ 
/*     */   public final void b() {
/*  56 */     if (this.jdField_a_of_type_Boolean) {
/*  57 */       c();
/*     */     }
/*  59 */     GlUtil.d();
/*  60 */     r();
/*  61 */     if (this.jdField_b_of_type_Boolean) {
/*  62 */       this.jdField_a_of_type_Yt.b();
/*     */ 
/*  64 */       this.jdField_a_of_type_YO.b();
/*  65 */       if (this.jdField_b_of_type_JavaLangString.length() > 0)
/*  66 */         this.jdField_b_of_type_YP.b();
/*     */     }
/*     */     else {
/*  69 */       this.jdField_a_of_type_YP.b();
/*     */     }
/*     */ 
/*  72 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  77 */     return this.jdField_a_of_type_YO.a();
/*     */   }
/*     */ 
/*     */   public final String b()
/*     */   {
/*  82 */     return "chatWindow";
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/*  87 */     return this.jdField_a_of_type_YO.b();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 109 */     this.jdField_a_of_type_YP.a = 8;
/*     */ 
/* 111 */     this.jdField_a_of_type_YP.b = a().getVisibleChatLog();
/*     */ 
/* 113 */     this.jdField_b_of_type_YP.a().y -= 55.0F;
/*     */ 
/* 115 */     this.jdField_a_of_type_YO.a().y -= 30.0F;
/* 116 */     this.jdField_a_of_type_YO.c();
/*     */ 
/* 118 */     this.jdField_a_of_type_YP.c();
/* 119 */     this.jdField_a_of_type_Yt.c();
/* 120 */     this.jdField_b_of_type_YP.c();
/*     */ 
/* 122 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final void a(ChatSystem paramChatSystem) {
/* 126 */     this.jdField_a_of_type_YO.a = paramChatSystem.getTextInput();
/*     */   }
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 132 */     this.jdField_b_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final void a(String paramString)
/*     */   {
/* 144 */     this.jdField_b_of_type_JavaLangString = paramString;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yu
 * JD-Core Version:    0.6.2
 */