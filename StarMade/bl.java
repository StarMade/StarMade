/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ShipManagerContainer;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3i;
/*     */ 
/*     */ public class bl extends U
/*     */ {
/*     */   private Camera jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/*  47 */   private float jdField_a_of_type_Float = 0.0F;
/*  48 */   private float b = 0.0F;
/*     */   private mF jdField_a_of_type_MF;
/*  49 */   private boolean d = false;
/*     */ 
/*  55 */   private int jdField_a_of_type_Int = -1;
/*     */   private boolean e;
/*     */   private q jdField_a_of_type_Q;
/*     */ 
/*     */   public bl(bj parambj)
/*     */   {
/*  63 */     super(parambj.a());
/*     */ 
/*  57 */     new lA();
/*  58 */     new Vector3f();
/*  59 */     this.e = true;
/*  60 */     this.jdField_a_of_type_Q = new q();
/*     */   }
/*     */ 
/*     */   public static float a()
/*     */   {
/*  94 */     return 5.0F;
/*     */   }
/*     */ 
/*     */   private le a()
/*     */   {
/* 104 */     return a().a().a.a.a.a().a();
/*     */   }
/*     */   public final q a(q paramq) {
/* 107 */     return a().a().a.a.a.a().a().a(paramq);
/*     */   }
/*     */ 
/*     */   public final kd a()
/*     */   {
/* 124 */     return a().a();
/*     */   }
/*     */ 
/*     */   public final mF a()
/*     */   {
/* 132 */     return this.jdField_a_of_type_MF;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 139 */     return this.jdField_a_of_type_Float;
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent()
/*     */   {
/* 146 */     if (Keyboard.getEventKeyState()) {
/* 147 */       if ((Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11) && (!Keyboard.isKeyDown(29))) {
/* 148 */         int j = Keyboard.getEventKey() - 2; bl localbl = this; a().a().d(j); localbl.e = true;
/*     */       }
/*     */       Object localObject2;
/*     */       Object localObject3;
/*     */       le localle;
/*     */       Object localObject1;
/* 154 */       if ((Keyboard.isKeyDown(29)) && 
/* 155 */         (!a().a().getDockingController().a().isEmpty()))
/*     */       {
/*     */         int i;
/* 157 */         if ((
/* 157 */           i = Keyboard.getEventKey() - 2) < 
/* 157 */           a().a().getDockingController().a().size()) {
/* 158 */           localObject2 = a().a().getDockingController().a().iterator();
/* 159 */           localObject3 = null;
/* 160 */           for (int k = 0; k <= i; k++) {
/* 161 */             localObject3 = (ElementDocking)((Iterator)localObject2).next();
/*     */           }
/* 163 */           (
/* 164 */             localle = a())
/* 164 */             .a().a();
/* 165 */           localObject1 = a().a(new q());
/* 166 */           System.err.println("EXIT SHIP FROM EXTRYPOINT " + localObject1);
/*     */ 
/* 169 */           a().a().a.a.a
/* 175 */             .a().a(((ElementDocking)localObject3).from);
/* 176 */           a().a().a((cw)localle.a().a(), (cw)a().a().a(), localle.a(new q()), a().a(new q()), true);
/*     */ 
/* 187 */           this.jdField_a_of_type_Int = -1;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 192 */       if (Keyboard.getEventKey() == cv.C.a())
/*     */       {
/* 194 */         if (a().a().getDockingController().a() != null)
/*     */         {
/* 199 */           localObject1 = null; localObject2 = a().a().a.a.a;
/*     */           try
/*     */           {
/* 203 */             (
/* 204 */               localle = a())
/* 204 */               .a().a();
/* 205 */             localObject1 = a().a(new q());
/* 206 */             System.err.println("EXIT SHIP FROM EXTRYPOINT " + localObject1);
/* 207 */             localObject3 = a().a().getDockingController().a().to.a().a().getSegmentBuffer().a(kd.jdField_a_of_type_Q, false);
/* 208 */             ((ar)localObject2).a().a((le)localObject3);
/*     */ 
/* 210 */             a().a().a((cw)localle.a().a(), (cw)a().a().a(), localle.a(new q()), a().a(new q()), true);
/*     */ 
/* 219 */             this.jdField_a_of_type_Int = -1;
/*     */           }
/*     */           catch (IOException localIOException)
/*     */           {
/* 224 */             localIOException.printStackTrace();
/*     */           }
/*     */           catch (InterruptedException localInterruptedException)
/*     */           {
/* 224 */             localInterruptedException.printStackTrace();
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 229 */       if ((!(
/* 229 */         localObject1 = a().a().a().getCockpits())
/* 229 */         .isEmpty()) && (a().a() == 1))
/*     */       {
/* 234 */         if (Keyboard.getEventKey() == cv.E.a()) {
/* 235 */           if (this.jdField_a_of_type_Int == 0) {
/* 236 */             this.jdField_a_of_type_Int = -1;
/*     */ 
/* 239 */             (
/* 240 */               localObject3 = a().a().a.a.a.a()
/* 239 */               .a().a(new q()))
/* 240 */               .c(kd.jdField_a_of_type_Q);
/* 241 */             ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject3);
/* 242 */             return;
/* 243 */           }if (this.jdField_a_of_type_Int < 0)
/* 244 */             this.jdField_a_of_type_Int = (((ArrayList)localObject1).size() - 1);
/*     */           else {
/* 246 */             this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int - 1, ((ArrayList)localObject1).size());
/*     */           }
/*     */ 
/* 249 */           (
/* 250 */             localObject2 = new q((q)((ArrayList)localObject1).get(this.jdField_a_of_type_Int)))
/* 250 */             .c(kd.jdField_a_of_type_Q);
/* 251 */           ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject2);
/* 252 */           return;
/*     */         }
/*     */ 
/* 255 */         if (Keyboard.getEventKey() == cv.D.a()) {
/* 256 */           if (this.jdField_a_of_type_Int + 1 >= ((ArrayList)localObject1).size()) {
/* 257 */             this.jdField_a_of_type_Int = -1;
/*     */ 
/* 260 */             (
/* 261 */               localObject3 = a().a().a.a.a.a()
/* 260 */               .a().a(new q()))
/* 261 */               .c(kd.jdField_a_of_type_Q);
/* 262 */             ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject3);
/* 263 */             return;
/* 264 */           }if (this.jdField_a_of_type_Int < 0)
/* 265 */             this.jdField_a_of_type_Int = 0;
/*     */           else {
/* 267 */             this.jdField_a_of_type_Int = FastMath.b(this.jdField_a_of_type_Int + 1, ((ArrayList)localObject1).size());
/*     */           }
/* 269 */           (
/* 270 */             localObject2 = new q((q)((ArrayList)localObject1).get(this.jdField_a_of_type_Int)))
/* 270 */             .c(kd.jdField_a_of_type_Q);
/* 271 */           ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject2);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(xp paramxp)
/*     */   {
/* 288 */     if ((xu.W.a().equals("SLOTS")) && (!Keyboard.isKeyDown(42)))
/*     */     {
/* 290 */       paramxp = FastMath.b(a().a().d() + (
/* 290 */         paramxp.f < 0 ? -1 : paramxp.f > 0 ? 1 : 0), 10);
/*     */ 
/* 293 */       a().a().d(paramxp);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 301 */     return this.d;
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/* 317 */     Object localObject = a().a().a.a.a.a();
/*     */ 
/* 319 */     if (a().a() != null) {
/* 320 */       a().a().a(null);
/*     */     }
/* 322 */     if (paramBoolean)
/*     */     {
/* 328 */       this.e = true;
/* 329 */       if ((this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera == null) || (((xb)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a() != a().a()))
/*     */       {
/* 331 */         if ((!f) && (a().a() == null)) throw new AssertionError("SHIP NOT FOUND ");
/*     */ 
/* 333 */         this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new dt(((bi)localObject).a(), xe.a(), a());
/* 334 */         this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(0.0F);
/* 335 */         if (!a(new q()).equals(kd.jdField_a_of_type_Q))
/* 336 */           ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).b = true;
/*     */         else {
/* 338 */           ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).b = false;
/*     */         }
/*     */ 
/*     */       }
/* 342 */       else if (this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera != null) {
/* 343 */         ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).a(xe.a());
/*     */       }
/* 345 */       if ((this.jdField_a_of_type_Int >= 0) && (this.jdField_a_of_type_Int < a().a().a().getCockpits().size())) {
/* 346 */         (
/* 347 */           localObject = new q((q)a().a().a().getCockpits().get(this.jdField_a_of_type_Int)))
/* 347 */           .c(kd.jdField_a_of_type_Q);
/* 348 */         ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject);
/*     */       } else {
/* 350 */         this.jdField_a_of_type_Int = -1;
/* 351 */         (
/* 352 */           localObject = ((bi)localObject).a().a(new q()))
/* 352 */           .c(kd.jdField_a_of_type_Q);
/* 353 */         ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject);
/*     */       }
/*     */ 
/* 357 */       a().a().c("BuildMode");
/* 358 */       a().a().a("FlightMode", "Flight Mode", "(press " + cv.r.b() + " to switch to BUILD MODE; press " + cv.v.b() + " to exit structure)");
/* 359 */       xe.a(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
/*     */     } else {
/* 361 */       this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
/*     */     }
/*     */ 
/* 364 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   private mF b() {
/* 368 */     if ((a().a() == null) || (a().a().a() == null)) {
/* 369 */       return null;
/*     */     }
/*     */ 
/* 374 */     zC localzC = a().a().a();
/*     */ 
/* 376 */     Vector3f localVector3f1 = new Vector3f();
/* 377 */     float f1 = 0.0F;
/* 378 */     float f2 = -1.0F;
/* 379 */     Object localObject2 = null;
/* 380 */     Vector3f localVector3f2 = localzC.a(new Vector3f());
/*     */     Iterator localIterator;
/* 381 */     synchronized (a().getLocalAndRemoteObjectContainer().getLocalObjects()) {
/* 382 */       for (localIterator = a().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         Object localObject3;
/* 383 */         if (((
/* 383 */           localObject3 = (Sendable)localIterator.next()) instanceof mF))
/*     */         {
/* 385 */           if ((
/* 385 */             localObject3 = (mF)localObject3) != 
/* 385 */             a().a()) {
/* 386 */             localVector3f1.set(((mF)localObject3).getWorldTransform().origin);
/*     */ 
/* 390 */             if (a().a() != null)
/* 391 */               localVector3f1.sub(a().a().getWorldTransform().origin);
/*     */             else {
/* 393 */               localVector3f1.sub(xe.a().a());
/*     */             }
/* 395 */             Vector3f localVector3f3 = localzC.a(((mF)localObject3).getWorldTransformClient().origin, new Vector3f(), true);
/*     */ 
/* 397 */             Vector3f localVector3f4 = new Vector3f();
/* 398 */             Vector3f localVector3f5 = new Vector3f();
/* 399 */             localVector3f4.sub(localVector3f3, localVector3f2);
/* 400 */             localVector3f5.sub(a().a().getWorldTransform().origin, ((mF)localObject3).getWorldTransformClient().origin);
/*     */ 
/* 403 */             if ((localVector3f4.length() < 90.0F) && (
/* 404 */               (localObject2 == null) || ((localVector3f4.length() < f1) && (localVector3f5.length() < f2)))) {
/* 405 */               localObject2 = localObject3;
/* 406 */               f1 = localVector3f4.length();
/* 407 */               f2 = localVector3f5.length();
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 413 */     return localObject2;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 425 */     super.a(paramxq);
/*     */ 
/* 427 */     Object localObject1 = this; if (this.e)
/*     */       try
/*     */       {
/* 427 */         Object localObject2;
/* 427 */         if ((localObject2 = ((bl)localObject1).a().a().a(((bl)localObject1).a().a())) != null) { if ((localObject2 = ((cz)localObject2).a(((bl)localObject1).a().a().d())) != null) { if ((localObject2 = ((bl)localObject1).a().a().getSegmentBuffer().a((q)localObject2, true)) != null) ((bl)localObject1).d = (((le)localObject2).a() == 54);  } else ((bl)localObject1).d = false; ((bl)localObject1).e = false; }  } catch (Exception localException) {  }
/*     */ 
/* 428 */     if ((cv.V.a()) && 
/* 429 */       ((xe.a() instanceof dt))) {
/* 430 */       ((dt)xe.a()).b();
/*     */     }
/*     */ 
/* 435 */     wV.a = true;
/*     */ 
/* 437 */     if (this.jdField_a_of_type_Int >= 0) {
/* 438 */       this.jdField_a_of_type_Q.b(((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a());
/* 439 */       this.jdField_a_of_type_Q.a(kd.jdField_a_of_type_Q);
/* 440 */       if (!a().a().a().getCockpits().contains(this.jdField_a_of_type_Q)) {
/* 441 */         this.jdField_a_of_type_Int = -1;
/*     */ 
/* 444 */         (
/* 445 */           localObject1 = a().a().a.a.a.a()
/* 444 */           .a().a(new q()))
/* 445 */           .c(kd.jdField_a_of_type_Q);
/* 446 */         ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject1);
/*     */       }
/* 448 */       if (this.jdField_a_of_type_Int >= 0) {
/* 449 */         if (!a().a().a().cockpit.getVector().equals(this.jdField_a_of_type_Q)) {
/* 450 */           a().a().a().cockpit.forceClientUpdates();
/* 451 */           a().a().a().cockpit.set(this.jdField_a_of_type_Q);
/*     */         }
/*     */       }
/* 454 */       else if (!a().a().a().cockpit.getVector().equals(kd.jdField_a_of_type_Q)) {
/* 455 */         a().a().a().cockpit.forceClientUpdates();
/* 456 */         a().a().a().cockpit.set(kd.jdField_a_of_type_Q);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 461 */     if (this.d)
/*     */     {
/* 463 */       if (a().a().a() != null) {
/* 464 */         this.b += paramxq.a();
/*     */       }
/* 466 */       if ((this.b > 0.0F) && (this.b < 3.0F))
/*     */       {
/* 471 */         if (b() == a().a().a())
/*     */         {
/* 473 */           this.b = 0.0F;
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 481 */         this.b = 0.0F;
/*     */ 
/* 483 */         localObject1 = b();
/*     */ 
/* 485 */         if (this.jdField_a_of_type_MF != localObject1) {
/* 486 */           this.jdField_a_of_type_MF = ((mF)localObject1);
/* 487 */           this.jdField_a_of_type_Float = 0.0F;
/* 488 */           a().a().a(null); return;
/*     */         }
/* 490 */         if (this.jdField_a_of_type_MF != null) {
/* 491 */           this.jdField_a_of_type_Float += paramxq.a();
/* 492 */           if (this.jdField_a_of_type_Float > 5.0F)
/*     */           {
/* 494 */             a().a().a(this.jdField_a_of_type_MF);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 501 */       a().a().a(null);
/* 502 */       this.jdField_a_of_type_Float = 0.0F;
/* 503 */       this.b = 0.0F;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bl
 * JD-Core Version:    0.6.2
 */