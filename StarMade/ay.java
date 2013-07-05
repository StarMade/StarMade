/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ 
/*     */ public final class ay extends U
/*     */ {
/*     */   private Camera jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/*  29 */   private float jdField_a_of_type_Float = 0.0F;
/*  30 */   private float b = 0.0F;
/*     */   private mF jdField_a_of_type_MF;
/*  31 */   private boolean d = false;
/*     */   private boolean e;
/*     */ 
/*     */   public ay(ct paramct)
/*     */   {
/*  43 */     super(paramct);
/*     */ 
/*  38 */     new Vector3f();
/*  39 */     this.e = true;
/*  40 */     new q();
/*     */   }
/*     */ 
/*     */   private le a()
/*     */   {
/*  76 */     return a().a().a.a.a.a().a();
/*     */   }
/*     */ 
/*     */   public final void handleKeyEvent()
/*     */   {
/* 109 */     if ((Keyboard.getEventKeyState()) && 
/* 110 */       (Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11) && (!Keyboard.isKeyDown(29))) {
/* 111 */       int i = Keyboard.getEventKey() - 2; ay localay = this; a().a().d(i); localay.e = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 223 */     Object localObject = a().a().a.a.a.a();
/*     */ 
/* 225 */     if (a().a() != null) {
/* 226 */       a().a().a(null);
/*     */     }
/* 228 */     if (paramBoolean)
/*     */     {
/* 232 */       System.err.println("[CLIENT][SEGMENTEXTERNALCONTROLLER] ENTERED: " + a());
/* 233 */       this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new dt((al)localObject, xe.a(), a());
/* 234 */       this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(0.0F);
/* 235 */       q localq = new q(); if (!a().a().a.a.a.a().a().a(localq).equals(kd.a))
/* 236 */         ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).b = true;
/*     */       else {
/* 238 */         ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).b = false;
/*     */       }
/* 240 */       (
/* 241 */         localObject = ((ax)localObject).a().a(new q()))
/* 241 */         .c(kd.a);
/* 242 */       ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject);
/* 243 */       ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a();
/*     */ 
/* 261 */       xe.a(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
/*     */     } else {
/* 263 */       this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
/*     */     }
/*     */ 
/* 266 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   private mF a() {
/* 270 */     if ((a().a() == null) || (a().a().a() == null)) {
/* 271 */       return null;
/*     */     }
/*     */ 
/* 276 */     zC localzC = a().a().a();
/*     */ 
/* 278 */     Vector3f localVector3f1 = new Vector3f();
/* 279 */     float f1 = 0.0F;
/* 280 */     float f2 = -1.0F;
/* 281 */     Object localObject = null;
/* 282 */     Vector3f localVector3f2 = localzC.a(new Vector3f());
/* 283 */     for (Iterator localIterator = a().a().values().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       mF localmF;
/* 284 */       if ((
/* 284 */         localmF = (mF)localIterator.next()) != 
/* 284 */         a().a()) {
/* 285 */         localVector3f1.set(localmF.getWorldTransform().origin);
/*     */ 
/* 289 */         if (a().a() != null)
/* 290 */           localVector3f1.sub(a().a().getWorldTransform().origin);
/*     */         else {
/* 292 */           localVector3f1.sub(xe.a().a());
/*     */         }
/* 294 */         Vector3f localVector3f3 = localzC.a(localmF.getWorldTransform().origin, new Vector3f(), true);
/*     */ 
/* 296 */         Vector3f localVector3f4 = new Vector3f();
/* 297 */         Vector3f localVector3f5 = new Vector3f();
/* 298 */         localVector3f4.sub(localVector3f3, localVector3f2);
/* 299 */         localVector3f5.sub(a().a().a().getWorldTransform().origin, localmF.getWorldTransform().origin);
/*     */ 
/* 302 */         if ((localVector3f4.length() < 90.0F) && (
/* 303 */           (localObject == null) || ((localVector3f4.length() < f1) && (localVector3f5.length() < f2)))) {
/* 304 */           localObject = localmF;
/* 305 */           f1 = localVector3f4.length();
/* 306 */           f2 = localVector3f5.length();
/*     */         }
/*     */       }
/*     */     }
/* 310 */     return localObject;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 322 */     super.a(paramxq);
/*     */ 
/* 324 */     Object localObject1 = this; if (this.e)
/*     */       try
/*     */       {
/* 324 */         Object localObject2;
/* 324 */         if ((localObject2 = ((ay)localObject1).a().a().a(((ay)localObject1).a().a().a())) != null) { localObject2 = ((cz)localObject2).a(((ay)localObject1).a().a().d()); if ((localObject2 = ((ay)localObject1).a().a().a().getSegmentBuffer().a((q)localObject2, true)) != null) ((ay)localObject1).d = (((le)localObject2).a() == 54); ((ay)localObject1).e = false; }
/*     */       } catch (Exception localException) {
/*     */       }
/* 327 */     wV.a = true;
/*     */ 
/* 331 */     if (this.d)
/*     */     {
/* 333 */       if (a().a().a() != null) {
/* 334 */         this.b += paramxq.a();
/*     */       }
/* 336 */       if ((this.b > 0.0F) && (this.b < 3.0F))
/*     */       {
/* 341 */         if (a() == a().a().a())
/*     */         {
/* 343 */           this.b = 0.0F;
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 351 */         this.b = 0.0F;
/*     */ 
/* 353 */         localObject1 = a();
/*     */ 
/* 355 */         if (this.jdField_a_of_type_MF != localObject1) {
/* 356 */           this.jdField_a_of_type_MF = ((mF)localObject1);
/* 357 */           this.jdField_a_of_type_Float = 0.0F;
/* 358 */           a().a().a(null); return;
/*     */         }
/* 360 */         if (this.jdField_a_of_type_MF != null) {
/* 361 */           this.jdField_a_of_type_Float += paramxq.a();
/* 362 */           if (this.jdField_a_of_type_Float > 5.0F)
/*     */           {
/* 364 */             a().a().a(this.jdField_a_of_type_MF);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 371 */       a().a().a(null);
/* 372 */       this.jdField_a_of_type_Float = 0.0F;
/* 373 */       this.b = 0.0F;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ay
 * JD-Core Version:    0.6.2
 */