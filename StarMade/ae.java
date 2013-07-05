/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ 
/*     */ public final class ae extends U
/*     */ {
/*     */   private Camera a;
/*     */ 
/*     */   public ae(ct paramct)
/*     */   {
/*  30 */     super(paramct);
/*  31 */     paramct = this; if (this.a == null) { xc localxc = new xc(); paramct.a = new Camera(localxc); } if (xe.a() == null) xe.a(paramct.a);
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/*  39 */     super.a(paramxp);
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/*  57 */     wV.a = paramBoolean;
/*  58 */     if (paramBoolean) {
/*  59 */       xc localxc = new xc();
/*  60 */       this.a = new Camera(localxc);
/*  61 */       if (xe.a() != null) {
/*  62 */         this.a.a().set(xe.a().a());
/*     */       }
/*  64 */       xe.a(this.a);
/*     */     }
/*  66 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/*  76 */     wV.a = true;
/*     */ 
/*  78 */     xa localxa = xe.a().a();
/*     */ 
/*  81 */     Vector3f localVector3f1 = new Vector3f(localxa.a.c());
/*  82 */     Vector3f localVector3f2 = new Vector3f(localxa.a.f());
/*  83 */     Vector3f localVector3f3 = new Vector3f(localxa.a.d());
/*     */ 
/*  87 */     float f = Keyboard.isKeyDown(42) ? 50.0F : 5.0F;
/*  88 */     localVector3f1.scale(f * paramxq.a());
/*  89 */     localVector3f2.scale(f * paramxq.a());
/*  90 */     localVector3f3.scale(f * paramxq.a());
/*     */ 
/*  94 */     if ((!Keyboard.isKeyDown(17)) || (!Keyboard.isKeyDown(31))) {
/*  95 */       if (Keyboard.isKeyDown(17)) {
/*  96 */         localxa.a().add(localVector3f1);
/*     */       }
/*  98 */       if (Keyboard.isKeyDown(31)) {
/*  99 */         localVector3f1.scale(-1.0F);
/* 100 */         localxa.a().add(localVector3f1);
/*     */       }
/*     */     }
/* 103 */     if ((!Keyboard.isKeyDown(30)) || (!Keyboard.isKeyDown(32))) {
/* 104 */       if (Keyboard.isKeyDown(30)) {
/* 105 */         localxa.a().add(localVector3f3);
/*     */       }
/* 107 */       if (Keyboard.isKeyDown(32)) {
/* 108 */         localVector3f3.scale(-1.0F);
/* 109 */         localxa.a().add(localVector3f3);
/*     */       }
/*     */     }
/* 112 */     if ((!Keyboard.isKeyDown(16)) || (!Keyboard.isKeyDown(18))) {
/* 113 */       if (Keyboard.isKeyDown(16)) {
/* 114 */         localxa.a().add(localVector3f2);
/*     */       }
/* 116 */       if (Keyboard.isKeyDown(18)) {
/* 117 */         localVector3f2.scale(-1.0F);
/* 118 */         localxa.a().add(localVector3f2);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ae
 * JD-Core Version:    0.6.2
 */