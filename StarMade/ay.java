/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.Iterator;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import org.lwjgl.input.Keyboard;
/*   8:    */import org.schema.game.common.controller.SegmentController;
/*   9:    */import org.schema.game.common.data.world.Segment;
/*  10:    */import org.schema.schine.graphicsengine.camera.Camera;
/*  11:    */
/*  25:    */public final class ay
/*  26:    */  extends U
/*  27:    */{
/*  28:    */  private Camera jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/*  29: 29 */  private float jdField_a_of_type_Float = 0.0F;
/*  30: 30 */  private float b = 0.0F;
/*  31: 31 */  private mF jdField_a_of_type_MF; private boolean d = false;
/*  32:    */  
/*  36:    */  private boolean e;
/*  37:    */  
/*  41:    */  public ay(ct paramct)
/*  42:    */  {
/*  43: 43 */    super(paramct);new Vector3f();this.e = true;new q();
/*  44:    */  }
/*  45:    */  
/*  77:    */  private le a()
/*  78:    */  {
/*  79: 76 */    return a().a().a.a.a.a().a();
/*  80:    */  }
/*  81:    */  
/* 110:    */  public final void handleKeyEvent()
/* 111:    */  {
/* 112:109 */    if ((Keyboard.getEventKeyState()) && 
/* 113:110 */      (Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11) && (!Keyboard.isKeyDown(29))) {
/* 114:111 */      int i = Keyboard.getEventKey() - 2;ay localay = this;a().a().d(i);localay.e = true;
/* 115:    */    }
/* 116:    */  }
/* 117:    */  
/* 170:    */  public final void a(xp paramxp) {}
/* 171:    */  
/* 224:    */  public final void b(boolean paramBoolean)
/* 225:    */  {
/* 226:223 */    Object localObject = a().a().a.a.a.a();
/* 227:    */    
/* 228:225 */    if (a().a() != null) {
/* 229:226 */      a().a().a(null);
/* 230:    */    }
/* 231:228 */    if (paramBoolean)
/* 232:    */    {
/* 235:232 */      System.err.println("[CLIENT][SEGMENTEXTERNALCONTROLLER] ENTERED: " + a());
/* 236:233 */      this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new dt((al)localObject, xe.a(), a());
/* 237:234 */      this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(0.0F);
/* 238:235 */      q localq = new q(); if (!a().a().a.a.a.a().a().a(localq).equals(kd.a)) {
/* 239:236 */        ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).b = true;
/* 240:    */      } else {
/* 241:238 */        ((dt)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera).b = false;
/* 242:    */      }
/* 243:    */      
/* 244:241 */      (localObject = ((ax)localObject).a().a(new q())).c(kd.a);
/* 245:242 */      ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b((q)localObject);
/* 246:243 */      ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a();
/* 247:    */      
/* 264:261 */      xe.a(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
/* 265:    */    } else {
/* 266:263 */      this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
/* 267:    */    }
/* 268:    */    
/* 269:266 */    super.b(paramBoolean);
/* 270:    */  }
/* 271:    */  
/* 272:    */  private mF a() {
/* 273:270 */    if ((a().a() == null) || (a().a().a() == null)) {
/* 274:271 */      return null;
/* 275:    */    }
/* 276:    */    
/* 279:276 */    zG localzG = a().a().a();
/* 280:    */    
/* 281:278 */    Vector3f localVector3f1 = new Vector3f();
/* 282:279 */    float f1 = 0.0F;
/* 283:280 */    float f2 = -1.0F;
/* 284:281 */    Object localObject = null;
/* 285:282 */    Vector3f localVector3f2 = localzG.a(new Vector3f());
/* 286:283 */    for (Iterator localIterator = a().a().values().iterator(); localIterator.hasNext();) { mF localmF;
/* 287:284 */      if ((localmF = (mF)localIterator.next()) != a().a()) {
/* 288:285 */        localVector3f1.set(localmF.getWorldTransform().origin);
/* 289:    */        
/* 292:289 */        if (a().a() != null) {
/* 293:290 */          localVector3f1.sub(a().a().getWorldTransform().origin);
/* 294:    */        } else {
/* 295:292 */          localVector3f1.sub(xe.a().a());
/* 296:    */        }
/* 297:294 */        Vector3f localVector3f3 = localzG.a(localmF.getWorldTransform().origin, new Vector3f(), true);
/* 298:    */        
/* 299:296 */        Vector3f localVector3f4 = new Vector3f();
/* 300:297 */        Vector3f localVector3f5 = new Vector3f();
/* 301:298 */        localVector3f4.sub(localVector3f3, localVector3f2);
/* 302:299 */        localVector3f5.sub(a().a().a().getWorldTransform().origin, localmF.getWorldTransform().origin);
/* 303:    */        
/* 305:302 */        if ((localVector3f4.length() < 90.0F) && (
/* 306:303 */          (localObject == null) || ((localVector3f4.length() < f1) && (localVector3f5.length() < f2)))) {
/* 307:304 */          localObject = localmF;
/* 308:305 */          f1 = localVector3f4.length();
/* 309:306 */          f2 = localVector3f5.length();
/* 310:    */        }
/* 311:    */      }
/* 312:    */    }
/* 313:310 */    return localObject;
/* 314:    */  }
/* 315:    */  
/* 323:    */  public final void a(xq paramxq)
/* 324:    */  {
/* 325:322 */    super.a(paramxq);
/* 326:    */    
/* 327:324 */    Object localObject1 = this; if (this.e) try { Object localObject2; if ((localObject2 = ((ay)localObject1).a().a().a(((ay)localObject1).a().a().a())) != null) { localObject2 = ((cz)localObject2).a(((ay)localObject1).a().a().d()); if ((localObject2 = ((ay)localObject1).a().a().a().getSegmentBuffer().a((q)localObject2, true)) != null) ((ay)localObject1).d = (((le)localObject2).a() == 54); ((ay)localObject1).e = false;
/* 328:    */        }
/* 329:    */      } catch (Exception localException) {}
/* 330:327 */    wV.a = true;
/* 331:    */    
/* 334:331 */    if (this.d)
/* 335:    */    {
/* 336:333 */      if (a().a().a() != null) {
/* 337:334 */        this.b += paramxq.a();
/* 338:    */      }
/* 339:336 */      if ((this.b > 0.0F) && (this.b < 3.0F))
/* 340:    */      {
/* 344:341 */        if (a() == a().a().a())
/* 345:    */        {
/* 346:343 */          this.b = 0.0F;
/* 347:    */        }
/* 348:    */        
/* 350:    */      }
/* 351:    */      else
/* 352:    */      {
/* 354:351 */        this.b = 0.0F;
/* 355:    */        
/* 356:353 */        localObject1 = a();
/* 357:    */        
/* 358:355 */        if (this.jdField_a_of_type_MF != localObject1) {
/* 359:356 */          this.jdField_a_of_type_MF = ((mF)localObject1);
/* 360:357 */          this.jdField_a_of_type_Float = 0.0F;
/* 361:358 */          a().a().a(null);return;
/* 362:    */        }
/* 363:360 */        if (this.jdField_a_of_type_MF != null) {
/* 364:361 */          this.jdField_a_of_type_Float += paramxq.a();
/* 365:362 */          if (this.jdField_a_of_type_Float > 5.0F)
/* 366:    */          {
/* 367:364 */            a().a().a(this.jdField_a_of_type_MF);
/* 368:    */          }
/* 369:    */        }
/* 370:    */      }
/* 371:    */    }
/* 372:    */    else
/* 373:    */    {
/* 374:371 */      a().a().a(null);
/* 375:372 */      this.jdField_a_of_type_Float = 0.0F;
/* 376:373 */      this.b = 0.0F;
/* 377:    */    }
/* 378:    */  }
/* 379:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ay
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */