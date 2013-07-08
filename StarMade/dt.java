/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.io.PrintStream;
/*   3:    */import javax.vecmath.Matrix3f;
/*   4:    */import javax.vecmath.Tuple3f;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.lwjgl.input.Keyboard;
/*   7:    */import org.schema.game.common.controller.SegmentController;
/*   8:    */import org.schema.schine.graphicsengine.camera.Camera;
/*   9:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  10:    */import org.schema.schine.network.client.ClientStateInterface;
/*  11:    */
/* 186:    */public final class dt
/* 187:    */  extends Camera
/* 188:    */  implements dw
/* 189:    */{
/* 190:    */  boolean jdField_a_of_type_Boolean;
/* 191:    */  private Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f;
/* 192:    */  du jdField_a_of_type_Du;
/* 193:    */  private Camera jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/* 194:194 */  private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 1.0F);
/* 195:195 */  private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f(1.0F, 0.0F, 0.0F);
/* 196:196 */  private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 1.0F, 0.0F);
/* 197:    */  public boolean b;
/* 198:198 */  private boolean jdField_c_of_type_Boolean = false;
/* 199:    */  private Matrix3f jdField_b_of_type_JavaxVecmathMatrix3f;
/* 200:    */  private float[] jdField_a_of_type_ArrayOfFloat;
/* 201:    */  int jdField_a_of_type_Int;
/* 202:    */  final SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/* 203:    */  private Camera jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/* 204:    */  private wW jdField_a_of_type_WW;
/* 205:    */  
/* 206:    */  public dt(al paramal, Camera paramCamera, le paramle) {
/* 207:207 */    super(new dy(paramal));
/* 208:208 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramal.a();
/* 209:209 */    this.jdField_a_of_type_ArrayOfFloat = new float[6];
/* 210:210 */    this.jdField_a_of_type_ArrayOfFloat[4] = 0.0F;
/* 211:211 */    this.jdField_a_of_type_ArrayOfFloat[5] = 3.141593F;
/* 212:    */    
/* 213:213 */    this.jdField_a_of_type_ArrayOfFloat[2] = -1.570796F;
/* 214:214 */    this.jdField_a_of_type_ArrayOfFloat[3] = 1.570796F;
/* 215:    */    
/* 216:216 */    this.jdField_a_of_type_ArrayOfFloat[0] = -1.570796F;
/* 217:217 */    this.jdField_a_of_type_ArrayOfFloat[1] = 1.570796F;
/* 218:    */    
/* 219:219 */    this.jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera;
/* 220:    */    
/* 221:221 */    this.jdField_c_of_type_Boolean = true;
/* 222:222 */    paramCamera = paramle.b();
/* 223:223 */    paramCamera = (byte)Math.max(0, Math.min(5, paramCamera));
/* 224:224 */    this.jdField_a_of_type_Int = org.schema.game.common.data.element.Element.orientationBackMapping[paramCamera];
/* 225:    */    
/* 226:226 */    if ((this.jdField_a_of_type_Int > 1) && (this.jdField_a_of_type_Int < 4))
/* 227:    */    {
/* 230:230 */      (paramle = new Matrix3f()).setIdentity();
/* 231:231 */      paramle.rotY(1.570796F);
/* 232:    */      
/* 233:233 */      this.jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 234:234 */      this.jdField_a_of_type_JavaxVecmathMatrix3f.setIdentity();
/* 235:235 */      System.err.println("ROTATION_X: " + this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int] + " oo " + this.jdField_a_of_type_Int + " orig: " + paramCamera);
/* 236:236 */      this.jdField_a_of_type_JavaxVecmathMatrix3f.rotX(this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int]);
/* 237:237 */      this.jdField_a_of_type_JavaxVecmathMatrix3f.mul(paramle);
/* 238:238 */      this.jdField_b_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 239:239 */      this.jdField_b_of_type_JavaxVecmathMatrix3f.setIdentity();
/* 240:240 */      this.jdField_b_of_type_JavaxVecmathMatrix3f.rotX(-this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int]);
/* 241:241 */      this.jdField_b_of_type_JavaxVecmathMatrix3f.mul(paramle);
/* 242:    */    }
/* 243:    */    else {
/* 244:244 */      this.jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 245:245 */      this.jdField_a_of_type_JavaxVecmathMatrix3f.setIdentity();
/* 246:    */      
/* 248:248 */      System.err.println("ROTATION_Y: " + this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int] + " oo " + this.jdField_a_of_type_Int + " orig: " + paramCamera);
/* 249:249 */      this.jdField_a_of_type_JavaxVecmathMatrix3f.rotY(this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int]);
/* 250:    */      
/* 251:251 */      this.jdField_b_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 252:252 */      this.jdField_b_of_type_JavaxVecmathMatrix3f.setIdentity();
/* 253:253 */      this.jdField_b_of_type_JavaxVecmathMatrix3f.rotY(-this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int]);
/* 254:    */    }
/* 255:    */    
/* 257:257 */    this.jdField_a_of_type_Du = new du(this, new dy(paramal));
/* 258:    */    
/* 259:259 */    this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new Camera(new dy(paramal));
/* 260:    */    
/* 261:261 */    a();
/* 262:262 */    this.jdField_a_of_type_Du.a();
/* 263:263 */    this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a();
/* 264:    */  }
/* 265:    */  
/* 266:    */  public final Camera a()
/* 267:    */  {
/* 268:268 */    return this.jdField_a_of_type_Du;
/* 269:    */  }
/* 270:    */  
/* 271:    */  public final Vector3f b() {
/* 272:272 */    Transform localTransform = new Transform(this.jdField_a_of_type_Du.getWorldTransform());
/* 273:    */    
/* 274:274 */    return GlUtil.c(new Vector3f(), localTransform);
/* 275:    */  }
/* 276:    */  
/* 277:    */  public final void a()
/* 278:    */  {
/* 279:279 */    super.a();
/* 280:280 */    if (this.jdField_c_of_type_Boolean) {
/* 281:281 */      getWorldTransform().basis.set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis);
/* 282:    */    }
/* 283:    */  }
/* 284:    */  
/* 298:    */  public final synchronized void a(xq paramxq)
/* 299:    */  {
/* 300:300 */    if (this.jdField_c_of_type_Boolean) {
/* 301:301 */      GlUtil.e(this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 302:302 */      GlUtil.f(this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 303:303 */      GlUtil.c(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 304:304 */      this.jdField_c_of_type_Boolean = false;
/* 305:    */    }
/* 306:    */    
/* 307:307 */    super.a(paramxq);
/* 308:308 */    Object localObject; if (wV.a()) {
/* 309:309 */      ((dy)this.jdField_a_of_type_Du.a()).a().b(((dy)a()).a());
/* 310:310 */      ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b(((dy)a()).a());
/* 311:    */      
/* 315:315 */      this.jdField_a_of_type_Du.a(paramxq);
/* 316:316 */      this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(paramxq);
/* 317:    */      
/* 318:318 */      if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != null) {
/* 319:319 */        if (Keyboard.isKeyDown(cv.u.a()))
/* 320:    */        {
/* 321:321 */          b(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.e());
/* 322:322 */          c(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.f());
/* 323:323 */          a(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c());
/* 326:    */        }
/* 327:327 */        else if (this.jdField_b_of_type_Boolean) {
/* 328:328 */          localObject = new Vector3f();
/* 329:329 */          Vector3f localVector3f1 = new Vector3f();
/* 330:330 */          Vector3f localVector3f2 = new Vector3f();
/* 331:331 */          GlUtil.e((Vector3f)localObject, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 332:332 */          GlUtil.f(localVector3f1, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 333:333 */          GlUtil.c(localVector3f2, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 334:    */          
/* 336:336 */          Transform localTransform1 = new Transform();
/* 337:337 */          GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector3f, localTransform1);
/* 338:338 */          GlUtil.d(this.jdField_c_of_type_JavaxVecmathVector3f, localTransform1);
/* 339:339 */          GlUtil.c(this.jdField_b_of_type_JavaxVecmathVector3f, localTransform1);
/* 340:    */          
/* 341:341 */          Transform localTransform2 = new Transform();
/* 342:342 */          GlUtil.a(localVector3f2, localTransform2);
/* 343:343 */          GlUtil.d(localVector3f1, localTransform2);
/* 344:344 */          GlUtil.c((Vector3f)localObject, localTransform2);
/* 345:    */          
/* 346:    */          Matrix3f localMatrix3f;
/* 347:347 */          (localMatrix3f = new Matrix3f()).sub(localTransform2.basis, localTransform1.basis);
/* 348:    */          
/* 350:350 */          this.jdField_a_of_type_Du.getWorldTransform().basis.add(localMatrix3f);
/* 351:    */          
/* 353:353 */          this.jdField_b_of_type_JavaxVecmathVector3f.set((Tuple3f)localObject);
/* 354:354 */          this.jdField_c_of_type_JavaxVecmathVector3f.set(localVector3f1);
/* 355:355 */          this.jdField_a_of_type_JavaxVecmathVector3f.set(localVector3f2);
/* 356:    */        }
/* 357:    */      }
/* 358:    */    }
/* 359:    */    
/* 361:361 */    if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != null) && (!Keyboard.isKeyDown(157)) && (!Keyboard.isKeyDown(54))) {
/* 362:362 */      getWorldTransform().basis.set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis);
/* 363:363 */      (
/* 364:364 */        localObject = new Matrix3f(this.jdField_b_of_type_JavaxVecmathMatrix3f)).invert();
/* 365:365 */      getWorldTransform().basis.mul((Matrix3f)localObject);
/* 366:    */    }
/* 367:    */    
/* 368:368 */    if ((this.jdField_a_of_type_WW != null) && (this.jdField_a_of_type_WW.a()))
/* 369:    */    {
/* 370:370 */      this.jdField_a_of_type_WW.a(paramxq);
/* 371:371 */      getWorldTransform().set(this.jdField_a_of_type_WW.getWorldTransform());
/* 372:    */    }
/* 373:    */    
/* 374:374 */    if (this.jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera != null) {
/* 375:375 */      a(this.jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
/* 376:376 */      this.jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
/* 377:    */    }
/* 378:    */  }
/* 379:    */  
/* 386:    */  public final void a(ClientStateInterface paramClientStateInterface, xq paramxq)
/* 387:    */  {
/* 388:388 */    if (Keyboard.isKeyDown(cv.u.a())) {
/* 389:389 */      this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(paramClientStateInterface, paramxq);
/* 390:    */    } else {
/* 391:391 */      this.jdField_a_of_type_Du.a(paramClientStateInterface, paramxq);
/* 392:    */    }
/* 393:393 */    super.a(paramClientStateInterface, paramxq);
/* 394:    */  }
/* 395:    */  
/* 396:    */  public final void b() {
/* 397:397 */    this.jdField_a_of_type_Boolean = true;
/* 398:    */  }
/* 399:    */  
/* 400:    */  public final void a(Camera paramCamera)
/* 401:    */  {
/* 402:402 */    this.jdField_a_of_type_WW = new wW(paramCamera, this);
/* 403:    */  }
/* 404:    */  
/* 406:    */  public final SegmentController a()
/* 407:    */  {
/* 408:408 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/* 409:    */  }
/* 410:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */