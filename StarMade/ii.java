/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.UnicodeFont;
/*     */ import org.newdawn.slick.font.effects.ColorEffect;
/*     */ import org.newdawn.slick.font.effects.OutlineEffect;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ManagerModuleCollection;
/*     */ import org.schema.game.common.controller.elements.ShipManagerContainer;
/*     */ import org.schema.game.common.controller.elements.weapon.WeaponCollectionManager;
/*     */ import org.schema.game.common.controller.elements.weapon.WeaponUnit;
/*     */ import org.schema.game.common.data.physics.PhysicsExt;
/*     */ import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public final class ii extends yz
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yE b;
/*     */   private ij jdField_a_of_type_Ij;
/*     */   private yE c;
/*     */   private yE d;
/*     */   private il jdField_a_of_type_Il;
/*  43 */   private zF jdField_a_of_type_ZF = new zF(10.0F);
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */ 
/*     */   public ii(ct paramct)
/*     */   {
/*  57 */     super(paramct);
/*     */ 
/*  51 */     this.jdField_a_of_type_Il = new il(paramct);
/*     */ 
/*  60 */     this.jdField_a_of_type_YE = new yE(xe.a().a("hud-sides-left-gui-"), paramct);
/*  61 */     this.b = new yE(xe.a().a("hud-sides-right-gui-"), paramct);
/*  62 */     this.c = new yE(xe.a().a("crosshair-c-gui-"), paramct);
/*  63 */     this.d = new yE(xe.a().a("crosshair-simple-c-gui-"), paramct);
/*     */ 
/*  65 */     this.jdField_a_of_type_Ij = new ij(paramct);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b() {
/*  73 */     if (k()) {
/*  74 */       d();
/*     */     }
/*  76 */     GlUtil.d();
/*     */     Object localObject3;
/*  80 */     if ((b()) && ((xe.a() instanceof dt))) {
/*  81 */       Object localObject1 = this; Object localObject2 = null; localObject1 = a().a().b ? ((ii)localObject1).a().a().a().a().a() : ((ii)localObject1).a().a().a().a().a();
/*     */ 
/*  83 */       localObject2 = (dt)xe.a();
/*     */ 
/*  85 */       Object localObject4 = new Vector3f(((dt)localObject2).b());
/*  86 */       localObject2 = new Vector3f(((dt)localObject2).a());
/*     */ 
/*  89 */       Object localObject5 = a();
/*     */ 
/*  91 */       float f4 = 0.0F;
/*  92 */       if (localObject5 != null)
/*     */       {
/*  94 */         for (int i = 0; i < ((WeaponCollectionManager)localObject5).getCollection().size(); i++) {
/*  95 */           f4 = Math.max(f4, ((WeaponUnit)((WeaponCollectionManager)localObject5).getCollection().get(i)).getDistance());
/*     */         }
/*     */       }
/*     */       else {
/*  99 */         f4 = 64.0F;
/*     */       }
/* 101 */       ((Vector3f)localObject4).scale(f4);
/*     */ 
/* 103 */       ((Vector3f)localObject2).add((Tuple3f)localObject4);
/*     */ 
/* 105 */       Object localObject6 = ((ct)a()).a().a().a((Vector3f)localObject2, new Vector3f(), false);
/*     */ 
/* 107 */       this.d.a().set((Tuple3f)localObject6);
/* 108 */       this.d.a().x += 3.0F;
/* 109 */       this.d.a().y -= 3.0F;
/*     */ 
/* 113 */       localObject4 = localObject1; float f1 = f4; localObject1 = this; localObject5 = (dt)xe.a(); Vector3f localVector3f = new Vector3f(xe.a().a()); localObject5 = new Vector3f(((dt)localObject5).b()); localObject6 = (PhysicsExt)((zS)((ii)localObject1).a()).a(); ((Vector3f)localObject5).scale(f1); ((Vector3f)localObject5).add(localVector3f); localObject3 = null; if (((PhysicsExt)localObject6).testRayCollisionPoint(localVector3f, (Vector3f)localObject5, false, (SegmentController)localObject4, null, false, null, false).hasHit()) ((ii)localObject1).d.a().a().set(0.0F, 1.0F, 0.0F, 1.0F); else ((ii)localObject1).d.a().a().set(1.0F, 1.0F, 1.0F, 1.0F); ((ii)localObject1).d.b(); ((ii)localObject1).d.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/* 115 */       localObject1 = this; this.jdField_a_of_type_YE.h(17); ((ii)localObject1).jdField_a_of_type_YE.b(0.8F, 0.8F, 0.8F); ((ii)localObject1).jdField_a_of_type_YE.b(); ((ii)localObject1).b.h(18); ((ii)localObject1).b.b(0.8F, 0.8F, 0.8F); ((ii)localObject1).b.b(); ((ii)localObject1).jdField_a_of_type_Il.h(6); ((ii)localObject1).jdField_a_of_type_Il.a().y += 64.0F; ((ii)localObject1).jdField_a_of_type_Il.b(); (localObject3 = localObject1).c.h(48); localObject3.c.a().x += localObject3.c.b() / 2.0F; localObject3.c.a().y += localObject3.c.a() / 2.0F; if (localObject3.a().a()) { if (((ct)localObject3.a()).a().a() != null) { localObject3.c.a().a().set(localObject3.jdField_a_of_type_ZF.a(), 1.0F, localObject3.jdField_a_of_type_ZF.a(), 1.0F); localObject3.c.a().b(1.0F, 1.0F, 1.0F); localObject3.jdField_a_of_type_Boolean = true; } else { if (localObject3.a().a() != null) { localObject3.a(); float f2 = Math.min(1.0F, localObject3.a().b() / bl.a()); float f3 = 1.0F - f2; localObject3.c.a().b(f3 + 1.0F, f3 + 1.0F, f3 + 1.0F); localObject3.c.a().a().set(f3, f2, 0.0F, 1.0F); localObject3.c.b(f2 * 360.0F); } else { localObject3.c.a().a().set(1.0F, 1.0F, 1.0F, 1.0F); localObject3.c.a().b(2.0F, 2.0F, 2.0F); localObject3.c.b(0.0F); } localObject3.jdField_a_of_type_ZF.a(); localObject3.jdField_a_of_type_Boolean = false; }  } else { localObject3.c.a().a().set(1.0F, 1.0F, 1.0F, 1.0F); localObject3.c.a().b(1.0F, 1.0F, 1.0F); localObject3.c.b(0.0F); localObject3.jdField_a_of_type_Boolean = false; } localObject3.c.b();
/*     */     } else {
/* 117 */       if ((b()) && (!(xe.a() instanceof dt))) {
/* 118 */         System.err.println("WARNING: HudBasic has wrong camera: " + xe.a().getClass());
/*     */       }
/* 120 */       this.d.h(48);
/* 121 */       this.d.a().x += this.d.b() / 2.0F;
/* 122 */       this.d.a().y += this.d.a() / 2.0F;
/* 123 */       this.d.b();
/*     */ 
/* 125 */       localObject3 = null; if (((ct)a()).a().a.a.a.a().c)
/*     */       {
/* 128 */         this.jdField_a_of_type_Il.h(6);
/* 129 */         this.jdField_a_of_type_Il.a().y += 64.0F;
/* 130 */         this.jdField_a_of_type_Il.b();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 135 */     this.jdField_a_of_type_Ij.b();
/*     */ 
/* 140 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   private bl a()
/*     */   {
/* 228 */     return ((ct)a()).a().a.a.a.a().a().a;
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 234 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */   private ar a() {
/* 237 */     return ((ct)a()).a().a.a.a;
/*     */   }
/*     */ 
/*     */   private WeaponCollectionManager a()
/*     */   {
/*     */     Object localObject;
/*     */     kd localkd;
/* 257 */     if (((
/* 257 */       localkd = (
/* 256 */       localObject = (ct)a())
/* 256 */       .a()) != null) && 
/* 257 */       (((ct)localObject).a().a(localkd))) {
/*     */       try {
/* 259 */         localObject = ((ct)localObject).a().a(localkd).a(((ct)localObject).a().d());
/* 260 */         for (int i = 0; i < localkd.a().getWeapon().getCollectionManagers().size(); i++) {
/* 261 */           if (((WeaponCollectionManager)localkd.a().getWeapon().getCollectionManagers().get(i)).equalsControllerPos((q)localObject)) {
/* 262 */             return (WeaponCollectionManager)localkd.a().getWeapon().getCollectionManagers().get(i);
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException)
/*     */       {
/* 268 */         localShipConfigurationNotFoundException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 272 */     return null;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 277 */     return this.jdField_a_of_type_YE.b();
/*     */   }
/*     */ 
/*     */   private boolean b() {
/* 281 */     return (a().a().a().a.c) || (a().a().a().c);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 293 */     this.jdField_a_of_type_YE.c();
/* 294 */     this.b.c();
/* 295 */     this.c.c();
/* 296 */     this.c.a().c(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/* 297 */     this.d.c();
/* 298 */     this.d.a().c(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/* 299 */     this.jdField_a_of_type_Ij.c();
/* 300 */     this.jdField_a_of_type_Il.c();
/*     */ 
/* 303 */     Object localObject = new Font("Arial", 0, 20);
/* 304 */     (
/* 305 */       localObject = new UnicodeFont((Font)localObject))
/* 305 */       .getEffects().add(new OutlineEffect(4, Color.black));
/* 306 */     ((UnicodeFont)localObject).getEffects().add(new ColorEffect(Color.green.darker()));
/* 307 */     ((UnicodeFont)localObject).addAsciiGlyphs();
/*     */     try { ((UnicodeFont)localObject).loadGlyphs();
/*     */       return;
/*     */     } catch (SlickException localSlickException) {
/* 312 */       localSlickException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/* 318 */     this.jdField_a_of_type_Ij.f();
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 324 */     this.jdField_a_of_type_Ij.a(paramxq);
/* 325 */     if (this.jdField_a_of_type_Boolean)
/* 326 */       this.jdField_a_of_type_ZF.a(paramxq);
/*     */   }
/*     */ 
/*     */   public final ij a()
/*     */   {
/* 333 */     return this.jdField_a_of_type_Ij;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ii
 * JD-Core Version:    0.6.2
 */