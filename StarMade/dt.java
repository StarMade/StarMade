/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientStateInterface;
/*     */ 
/*     */ public final class dt extends Camera
/*     */   implements dw
/*     */ {
/*     */   boolean jdField_a_of_type_Boolean;
/*     */   private Matrix3f jdField_a_of_type_JavaxVecmathMatrix3f;
/*     */   du jdField_a_of_type_Du;
/*     */   private Camera jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/* 194 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 1.0F);
/* 195 */   private Vector3f jdField_b_of_type_JavaxVecmathVector3f = new Vector3f(1.0F, 0.0F, 0.0F);
/* 196 */   private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 1.0F, 0.0F);
/*     */   public boolean b;
/* 198 */   private boolean jdField_c_of_type_Boolean = false;
/*     */   private Matrix3f jdField_b_of_type_JavaxVecmathMatrix3f;
/*     */   private float[] jdField_a_of_type_ArrayOfFloat;
/*     */   int jdField_a_of_type_Int;
/*     */   final SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   private Camera jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera;
/*     */   private wW jdField_a_of_type_WW;
/*     */ 
/*     */   public dt(al paramal, Camera paramCamera, le paramle)
/*     */   {
/* 207 */     super(new dy(paramal));
/* 208 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramal.a();
/* 209 */     this.jdField_a_of_type_ArrayOfFloat = new float[6];
/* 210 */     this.jdField_a_of_type_ArrayOfFloat[4] = 0.0F;
/* 211 */     this.jdField_a_of_type_ArrayOfFloat[5] = 3.141593F;
/*     */ 
/* 213 */     this.jdField_a_of_type_ArrayOfFloat[2] = -1.570796F;
/* 214 */     this.jdField_a_of_type_ArrayOfFloat[3] = 1.570796F;
/*     */ 
/* 216 */     this.jdField_a_of_type_ArrayOfFloat[0] = -1.570796F;
/* 217 */     this.jdField_a_of_type_ArrayOfFloat[1] = 1.570796F;
/*     */ 
/* 219 */     this.jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera = paramCamera;
/*     */ 
/* 221 */     this.jdField_c_of_type_Boolean = true;
/* 222 */     paramCamera = paramle.b();
/* 223 */     paramCamera = (byte)Math.max(0, Math.min(5, paramCamera));
/* 224 */     this.jdField_a_of_type_Int = org.schema.game.common.data.element.Element.orientationBackMapping[paramCamera];
/*     */ 
/* 226 */     if ((this.jdField_a_of_type_Int > 1) && (this.jdField_a_of_type_Int < 4))
/*     */     {
/* 229 */       (
/* 230 */         paramle = new Matrix3f())
/* 230 */         .setIdentity();
/* 231 */       paramle.rotY(1.570796F);
/*     */ 
/* 233 */       this.jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 234 */       this.jdField_a_of_type_JavaxVecmathMatrix3f.setIdentity();
/* 235 */       System.err.println("ROTATION_X: " + this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int] + " oo " + this.jdField_a_of_type_Int + " orig: " + paramCamera);
/* 236 */       this.jdField_a_of_type_JavaxVecmathMatrix3f.rotX(this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int]);
/* 237 */       this.jdField_a_of_type_JavaxVecmathMatrix3f.mul(paramle);
/* 238 */       this.jdField_b_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 239 */       this.jdField_b_of_type_JavaxVecmathMatrix3f.setIdentity();
/* 240 */       this.jdField_b_of_type_JavaxVecmathMatrix3f.rotX(-this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int]);
/* 241 */       this.jdField_b_of_type_JavaxVecmathMatrix3f.mul(paramle);
/*     */     }
/*     */     else {
/* 244 */       this.jdField_a_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 245 */       this.jdField_a_of_type_JavaxVecmathMatrix3f.setIdentity();
/*     */ 
/* 248 */       System.err.println("ROTATION_Y: " + this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int] + " oo " + this.jdField_a_of_type_Int + " orig: " + paramCamera);
/* 249 */       this.jdField_a_of_type_JavaxVecmathMatrix3f.rotY(this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int]);
/*     */ 
/* 251 */       this.jdField_b_of_type_JavaxVecmathMatrix3f = new Matrix3f();
/* 252 */       this.jdField_b_of_type_JavaxVecmathMatrix3f.setIdentity();
/* 253 */       this.jdField_b_of_type_JavaxVecmathMatrix3f.rotY(-this.jdField_a_of_type_ArrayOfFloat[this.jdField_a_of_type_Int]);
/*     */     }
/*     */ 
/* 257 */     this.jdField_a_of_type_Du = new du(this, new dy(paramal));
/*     */ 
/* 259 */     this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera = new Camera(new dy(paramal));
/*     */ 
/* 261 */     a();
/* 262 */     this.jdField_a_of_type_Du.a();
/* 263 */     this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a();
/*     */   }
/*     */ 
/*     */   public final Camera a()
/*     */   {
/* 268 */     return this.jdField_a_of_type_Du;
/*     */   }
/*     */ 
/*     */   public final Vector3f b() {
/* 272 */     Transform localTransform = new Transform(this.jdField_a_of_type_Du.getWorldTransform());
/*     */ 
/* 274 */     return GlUtil.c(new Vector3f(), localTransform);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 279 */     super.a();
/* 280 */     if (this.jdField_c_of_type_Boolean)
/* 281 */       getWorldTransform().basis.set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis);
/*     */   }
/*     */ 
/*     */   public final synchronized void a(xq paramxq)
/*     */   {
/* 300 */     if (this.jdField_c_of_type_Boolean) {
/* 301 */       GlUtil.e(this.jdField_b_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 302 */       GlUtil.f(this.jdField_c_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 303 */       GlUtil.c(this.jdField_a_of_type_JavaxVecmathVector3f, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 304 */       this.jdField_c_of_type_Boolean = false;
/*     */     }
/*     */ 
/* 307 */     super.a(paramxq);
/*     */     Object localObject;
/* 308 */     if (wV.a()) {
/* 309 */       ((dy)this.jdField_a_of_type_Du.a()).a().b(((dy)a()).a());
/* 310 */       ((dy)this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a()).a().b(((dy)a()).a());
/*     */ 
/* 315 */       this.jdField_a_of_type_Du.a(paramxq);
/* 316 */       this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(paramxq);
/*     */ 
/* 318 */       if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != null) {
/* 319 */         if (Keyboard.isKeyDown(cv.u.a()))
/*     */         {
/* 321 */           b(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.e());
/* 322 */           c(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.f());
/* 323 */           a(this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.c());
/*     */         }
/* 327 */         else if (this.jdField_b_of_type_Boolean) {
/* 328 */           localObject = new Vector3f();
/* 329 */           Vector3f localVector3f1 = new Vector3f();
/* 330 */           Vector3f localVector3f2 = new Vector3f();
/* 331 */           GlUtil.e((Vector3f)localObject, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 332 */           GlUtil.f(localVector3f1, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/* 333 */           GlUtil.c(localVector3f2, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
/*     */ 
/* 336 */           Transform localTransform1 = new Transform();
/* 337 */           GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector3f, localTransform1);
/* 338 */           GlUtil.d(this.jdField_c_of_type_JavaxVecmathVector3f, localTransform1);
/* 339 */           GlUtil.c(this.jdField_b_of_type_JavaxVecmathVector3f, localTransform1);
/*     */ 
/* 341 */           Transform localTransform2 = new Transform();
/* 342 */           GlUtil.a(localVector3f2, localTransform2);
/* 343 */           GlUtil.d(localVector3f1, localTransform2);
/* 344 */           GlUtil.c((Vector3f)localObject, localTransform2);
/*     */           Matrix3f localMatrix3f;
/* 346 */           (
/* 347 */             localMatrix3f = new Matrix3f())
/* 347 */             .sub(localTransform2.basis, localTransform1.basis);
/*     */ 
/* 350 */           this.jdField_a_of_type_Du.getWorldTransform().basis.add(localMatrix3f);
/*     */ 
/* 353 */           this.jdField_b_of_type_JavaxVecmathVector3f.set((Tuple3f)localObject);
/* 354 */           this.jdField_c_of_type_JavaxVecmathVector3f.set(localVector3f1);
/* 355 */           this.jdField_a_of_type_JavaxVecmathVector3f.set(localVector3f2);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 361 */     if ((this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController != null) && (!Keyboard.isKeyDown(157)) && (!Keyboard.isKeyDown(54))) {
/* 362 */       getWorldTransform().basis.set(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform().basis);
/* 363 */       (
/* 364 */         localObject = new Matrix3f(this.jdField_b_of_type_JavaxVecmathMatrix3f))
/* 364 */         .invert();
/* 365 */       getWorldTransform().basis.mul((Matrix3f)localObject);
/*     */     }
/*     */ 
/* 368 */     if ((this.jdField_a_of_type_WW != null) && (this.jdField_a_of_type_WW.a()))
/*     */     {
/* 370 */       this.jdField_a_of_type_WW.a(paramxq);
/* 371 */       getWorldTransform().set(this.jdField_a_of_type_WW.getWorldTransform());
/*     */     }
/*     */ 
/* 374 */     if (this.jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera != null) {
/* 375 */       a(this.jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera);
/* 376 */       this.jdField_b_of_type_OrgSchemaSchineGraphicsengineCameraCamera = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(ClientStateInterface paramClientStateInterface, xq paramxq)
/*     */   {
/* 388 */     if (Keyboard.isKeyDown(cv.u.a()))
/* 389 */       this.jdField_a_of_type_OrgSchemaSchineGraphicsengineCameraCamera.a(paramClientStateInterface, paramxq);
/*     */     else {
/* 391 */       this.jdField_a_of_type_Du.a(paramClientStateInterface, paramxq);
/*     */     }
/* 393 */     super.a(paramClientStateInterface, paramxq);
/*     */   }
/*     */ 
/*     */   public final void b() {
/* 397 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public final void a(Camera paramCamera)
/*     */   {
/* 402 */     this.jdField_a_of_type_WW = new wW(paramCamera, this);
/*     */   }
/*     */ 
/*     */   public final SegmentController a()
/*     */   {
/* 408 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dt
 * JD-Core Version:    0.6.2
 */