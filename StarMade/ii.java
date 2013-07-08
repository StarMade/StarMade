/*   1:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   2:    */import java.awt.Color;
/*   3:    */import java.awt.Font;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.List;
/*   6:    */import javax.vecmath.Tuple3f;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */import javax.vecmath.Vector4f;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.UnicodeFont;
/*  11:    */import org.newdawn.slick.font.effects.ColorEffect;
/*  12:    */import org.newdawn.slick.font.effects.OutlineEffect;
/*  13:    */import org.schema.game.common.controller.SegmentController;
/*  14:    */import org.schema.game.common.controller.elements.ManagerModuleCollection;
/*  15:    */import org.schema.game.common.controller.elements.ShipManagerContainer;
/*  16:    */import org.schema.game.common.controller.elements.weapon.WeaponCollectionManager;
/*  17:    */import org.schema.game.common.controller.elements.weapon.WeaponUnit;
/*  18:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  19:    */import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
/*  20:    */import org.schema.game.common.data.world.Segment;
/*  21:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  22:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  23:    */
/*  34:    */public final class ii
/*  35:    */  extends yz
/*  36:    */{
/*  37:    */  private yE jdField_a_of_type_YE;
/*  38:    */  private yE b;
/*  39:    */  private ij jdField_a_of_type_Ij;
/*  40:    */  private yE c;
/*  41:    */  private yE d;
/*  42:    */  private il jdField_a_of_type_Il;
/*  43: 43 */  private zJ jdField_a_of_type_ZJ = new zJ(10.0F);
/*  44:    */  
/*  49:    */  private boolean jdField_a_of_type_Boolean;
/*  50:    */  
/*  55:    */  public ii(ct paramct)
/*  56:    */  {
/*  57: 57 */    super(paramct);this.jdField_a_of_type_Il = new il(paramct);
/*  58:    */    
/*  60: 60 */    this.jdField_a_of_type_YE = new yE(xe.a().a("hud-sides-left-gui-"), paramct);
/*  61: 61 */    this.b = new yE(xe.a().a("hud-sides-right-gui-"), paramct);
/*  62: 62 */    this.c = new yE(xe.a().a("crosshair-c-gui-"), paramct);
/*  63: 63 */    this.d = new yE(xe.a().a("crosshair-simple-c-gui-"), paramct);
/*  64:    */    
/*  65: 65 */    this.jdField_a_of_type_Ij = new ij(paramct);
/*  66:    */  }
/*  67:    */  
/*  69:    */  public final void a() {}
/*  70:    */  
/*  71:    */  public final void b()
/*  72:    */  {
/*  73: 73 */    if (k()) {
/*  74: 74 */      d();
/*  75:    */    }
/*  76: 76 */    GlUtil.d();
/*  77:    */    
/*  78:    */    Object localObject3;
/*  79:    */    
/*  80: 80 */    if ((b()) && ((xe.a() instanceof dt))) {
/*  81: 81 */      Object localObject1 = this;Object localObject2 = null;localObject1 = a().a().b ? ((ii)localObject1).a().a().a().a().a() : ((ii)localObject1).a().a().a().a().a();
/*  82:    */      
/*  83: 83 */      localObject2 = (dt)xe.a();
/*  84:    */      
/*  85: 85 */      Object localObject4 = new Vector3f(((dt)localObject2).b());
/*  86: 86 */      localObject2 = new Vector3f(((dt)localObject2).a());
/*  87:    */      
/*  89: 89 */      Object localObject5 = a();
/*  90:    */      
/*  91: 91 */      float f4 = 0.0F;
/*  92: 92 */      if (localObject5 != null)
/*  93:    */      {
/*  94: 94 */        for (int i = 0; i < ((WeaponCollectionManager)localObject5).getCollection().size(); i++) {
/*  95: 95 */          f4 = Math.max(f4, ((WeaponUnit)((WeaponCollectionManager)localObject5).getCollection().get(i)).getDistance());
/*  96:    */        }
/*  97:    */        
/*  98:    */      } else {
/*  99: 99 */        f4 = 64.0F;
/* 100:    */      }
/* 101:101 */      ((Vector3f)localObject4).scale(f4);
/* 102:    */      
/* 103:103 */      ((Vector3f)localObject2).add((Tuple3f)localObject4);
/* 104:    */      
/* 105:105 */      Object localObject6 = ((ct)a()).a().a().a((Vector3f)localObject2, new Vector3f(), false);
/* 106:    */      
/* 107:107 */      this.d.a().set((Tuple3f)localObject6);
/* 108:108 */      this.d.a().x += 3.0F;
/* 109:109 */      this.d.a().y -= 3.0F;
/* 110:    */      
/* 113:113 */      localObject4 = localObject1;float f1 = f4;localObject1 = this;localObject5 = (dt)xe.a();Vector3f localVector3f = new Vector3f(xe.a().a());localObject5 = new Vector3f(((dt)localObject5).b());localObject6 = (PhysicsExt)((zW)((ii)localObject1).a()).a();((Vector3f)localObject5).scale(f1);((Vector3f)localObject5).add(localVector3f);localObject3 = null; if (((PhysicsExt)localObject6).testRayCollisionPoint(localVector3f, (Vector3f)localObject5, false, (SegmentController)localObject4, null, false, null, false).hasHit()) ((ii)localObject1).d.a().a().set(0.0F, 1.0F, 0.0F, 1.0F); else ((ii)localObject1).d.a().a().set(1.0F, 1.0F, 1.0F, 1.0F); ((ii)localObject1).d.b();((ii)localObject1).d.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);
/* 114:    */      
/* 115:115 */      localObject1 = this;this.jdField_a_of_type_YE.h(17);((ii)localObject1).jdField_a_of_type_YE.b(0.8F, 0.8F, 0.8F);((ii)localObject1).jdField_a_of_type_YE.b();((ii)localObject1).b.h(18);((ii)localObject1).b.b(0.8F, 0.8F, 0.8F);((ii)localObject1).b.b();((ii)localObject1).jdField_a_of_type_Il.h(6);((ii)localObject1).jdField_a_of_type_Il.a().y += 64.0F;((ii)localObject1).jdField_a_of_type_Il.b();(localObject3 = localObject1).c.h(48);localObject3.c.a().x += localObject3.c.b() / 2.0F;localObject3.c.a().y += localObject3.c.a() / 2.0F; if (localObject3.a().a()) { if (((ct)localObject3.a()).a().a() != null) { localObject3.c.a().a().set(localObject3.jdField_a_of_type_ZJ.a(), 1.0F, localObject3.jdField_a_of_type_ZJ.a(), 1.0F);localObject3.c.a().b(1.0F, 1.0F, 1.0F);localObject3.jdField_a_of_type_Boolean = true; } else { if (localObject3.a().a() != null) { localObject3.a();float f2 = Math.min(1.0F, localObject3.a().b() / bl.a());float f3 = 1.0F - f2;localObject3.c.a().b(f3 + 1.0F, f3 + 1.0F, f3 + 1.0F);localObject3.c.a().a().set(f3, f2, 0.0F, 1.0F);localObject3.c.b(f2 * 360.0F); } else { localObject3.c.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);localObject3.c.a().b(2.0F, 2.0F, 2.0F);localObject3.c.b(0.0F); } localObject3.jdField_a_of_type_ZJ.a();localObject3.jdField_a_of_type_Boolean = false; } } else { localObject3.c.a().a().set(1.0F, 1.0F, 1.0F, 1.0F);localObject3.c.a().b(1.0F, 1.0F, 1.0F);localObject3.c.b(0.0F);localObject3.jdField_a_of_type_Boolean = false; } localObject3.c.b();
/* 116:    */    } else {
/* 117:117 */      if ((b()) && (!(xe.a() instanceof dt))) {
/* 118:118 */        System.err.println("WARNING: HudBasic has wrong camera: " + xe.a().getClass());
/* 119:    */      }
/* 120:120 */      this.d.h(48);
/* 121:121 */      this.d.a().x += this.d.b() / 2.0F;
/* 122:122 */      this.d.a().y += this.d.a() / 2.0F;
/* 123:123 */      this.d.b();
/* 124:    */      
/* 125:125 */      localObject3 = null; if (((ct)a()).a().a.a.a.a().c)
/* 126:    */      {
/* 128:128 */        this.jdField_a_of_type_Il.h(6);
/* 129:129 */        this.jdField_a_of_type_Il.a().y += 64.0F;
/* 130:130 */        this.jdField_a_of_type_Il.b();
/* 131:    */      }
/* 132:    */    }
/* 133:    */    
/* 135:135 */    this.jdField_a_of_type_Ij.b();
/* 136:    */    
/* 140:140 */    GlUtil.c();
/* 141:    */  }
/* 142:    */  
/* 226:    */  private bl a()
/* 227:    */  {
/* 228:228 */    return ((ct)a()).a().a.a.a.a().a().a;
/* 229:    */  }
/* 230:    */  
/* 232:    */  public final float a()
/* 233:    */  {
/* 234:234 */    return this.jdField_a_of_type_YE.a();
/* 235:    */  }
/* 236:    */  
/* 237:237 */  private ar a() { return ((ct)a()).a().a.a.a; }
/* 238:    */  
/* 244:    */  private WeaponCollectionManager a()
/* 245:    */  {
/* 246:    */    Object localObject;
/* 247:    */    
/* 252:    */    kd localkd;
/* 253:    */    
/* 257:257 */    if (((localkd = (localObject = (ct)a()).a()) != null) && (((ct)localObject).a().a(localkd))) {
/* 258:    */      try {
/* 259:259 */        localObject = ((ct)localObject).a().a(localkd).a(((ct)localObject).a().d());
/* 260:260 */        for (int i = 0; i < localkd.a().getWeapon().getCollectionManagers().size(); i++) {
/* 261:261 */          if (((WeaponCollectionManager)localkd.a().getWeapon().getCollectionManagers().get(i)).equalsControllerPos((q)localObject))
/* 262:262 */            return (WeaponCollectionManager)localkd.a().getWeapon().getCollectionManagers().get(i);
/* 263:    */        }
/* 264:    */      } catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException) {
/* 265:265 */        
/* 266:    */        
/* 268:268 */          localShipConfigurationNotFoundException;
/* 269:    */      }
/* 270:    */    }
/* 271:    */    
/* 275:272 */    return null;
/* 276:    */  }
/* 277:    */  
/* 278:    */  public final float b()
/* 279:    */  {
/* 280:277 */    return this.jdField_a_of_type_YE.b();
/* 281:    */  }
/* 282:    */  
/* 283:    */  private boolean b() {
/* 284:281 */    return (a().a().a().a.c) || (a().a().a().c);
/* 285:    */  }
/* 286:    */  
/* 294:    */  public final void c()
/* 295:    */  {
/* 296:293 */    this.jdField_a_of_type_YE.c();
/* 297:294 */    this.b.c();
/* 298:295 */    this.c.c();
/* 299:296 */    this.c.a().c(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/* 300:297 */    this.d.c();
/* 301:298 */    this.d.a().c(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
/* 302:299 */    this.jdField_a_of_type_Ij.c();
/* 303:300 */    this.jdField_a_of_type_Il.c();
/* 304:    */    
/* 306:303 */    Object localObject = new Font("Arial", 0, 20);
/* 307:304 */    (
/* 308:305 */      localObject = new UnicodeFont((Font)localObject)).getEffects().add(new OutlineEffect(4, Color.black));
/* 309:306 */    ((UnicodeFont)localObject).getEffects().add(new ColorEffect(Color.green.darker()));
/* 310:307 */    ((UnicodeFont)localObject).addAsciiGlyphs();
/* 311:    */    try {
/* 312:309 */      ((UnicodeFont)localObject).loadGlyphs(); return;
/* 313:310 */    } catch (SlickException localSlickException) { 
/* 314:    */      
/* 315:312 */        localSlickException;
/* 316:    */    }
/* 317:    */  }
/* 318:    */  
/* 321:    */  public final void e()
/* 322:    */  {
/* 323:318 */    this.jdField_a_of_type_Ij.f();
/* 324:    */  }
/* 325:    */  
/* 327:    */  public final void a(xq paramxq)
/* 328:    */  {
/* 329:324 */    this.jdField_a_of_type_Ij.a(paramxq);
/* 330:325 */    if (this.jdField_a_of_type_Boolean) {
/* 331:326 */      this.jdField_a_of_type_ZJ.a(paramxq);
/* 332:    */    }
/* 333:    */  }
/* 334:    */  
/* 336:    */  public final ij a()
/* 337:    */  {
/* 338:333 */    return this.jdField_a_of_type_Ij;
/* 339:    */  }
/* 340:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ii
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */