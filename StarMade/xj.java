/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.collision.shapes.BoxShape;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShape;
/*     */ import com.bulletphysics.collision.shapes.ConcaveShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.collision.shapes.CylinderShape;
/*     */ import com.bulletphysics.collision.shapes.PolyhedralConvexShape;
/*     */ import com.bulletphysics.collision.shapes.ShapeHull;
/*     */ import com.bulletphysics.collision.shapes.SphereShape;
/*     */ import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.dynamics.constraintsolver.HingeConstraint;
/*     */ import com.bulletphysics.dynamics.constraintsolver.SliderConstraint;
/*     */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*     */ import com.bulletphysics.linearmath.IDebugDraw;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.TransformUtil;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public final class xj extends IDebugDraw
/*     */ {
/*     */   private static float[] jdField_a_of_type_ArrayOfFloat;
/* 254 */   private int jdField_a_of_type_Int = 0;
/*     */ 
/* 259 */   private final Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/* 261 */   private static FloatBuffer jdField_a_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer((
/* 261 */     xj.jdField_a_of_type_ArrayOfFloat = new float[16]).length)
/* 261 */     ;
/*     */ 
/*     */   public static void a(Transform paramTransform, CollisionShape paramCollisionShape, Vector3f paramVector3f, int paramInt)
/*     */   {
/* 273 */     Object localObject1 = ObjectPool.get(Transform.class);
/* 274 */     ObjectPool localObjectPool = ObjectPool.get(Vector3f.class);
/*     */ 
/* 277 */     GlUtil.d();
/* 278 */     paramTransform.getOpenGLMatrix(jdField_a_of_type_ArrayOfFloat);
/* 279 */     jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 280 */     jdField_a_of_type_JavaNioFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/* 281 */     jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 282 */     GL11.glMultMatrix(jdField_a_of_type_JavaNioFloatBuffer);
/*     */ 
/* 299 */     GL11.glEnable(2903);
/* 300 */     GL11.glColor3f(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/* 301 */     GL11.glDisable(2896);
/* 302 */     GL11.glDisable(3553);
/*     */     Object localObject2;
/*     */     Object localObject4;
/* 303 */     if (paramCollisionShape.getShapeType() == BroadphaseNativeType.COMPOUND_SHAPE_PROXYTYPE)
/*     */     {
/* 305 */       paramTransform = (CompoundShape)paramCollisionShape;
/* 306 */       localObject2 = (Transform)((ObjectPool)localObject1).get();
/* 307 */       for (int i = paramTransform.getNumChildShapes() - 1; i >= 0; i--) {
/* 308 */         paramTransform.getChildTransform(i, (Transform)localObject2);
/* 309 */         localObject4 = paramTransform.getChildShape(i);
/* 310 */         a((Transform)localObject2, (CollisionShape)localObject4, paramVector3f, paramInt);
/*     */       }
/* 312 */       ((ObjectPool)localObject1).release(localObject2);
/*     */     }
/*     */     else
/*     */     {
/* 323 */       paramTransform = 1;
/*     */       Object localObject3;
/* 325 */       if ((paramInt & 0x1) == 0)
/*     */       {
/* 329 */         switch (xk.a[paramCollisionShape.getShapeType().ordinal()]) {
/*     */         case 1:
/* 331 */           if ((paramCollisionShape instanceof BoxShape))
/*     */           {
/* 333 */             Vector3f localVector3f1 = ((BoxShape)paramCollisionShape)
/* 333 */               .getHalfExtentsWithMargin((Vector3f)localObjectPool.get());
/*     */ 
/* 335 */             GL11.glScalef(2.0F * localVector3f1.x, 2.0F * localVector3f1.y, 2.0F * localVector3f1.z);
/*     */ 
/* 337 */             xP.a();
/*     */ 
/* 339 */             localObjectPool.release(localVector3f1);
/* 340 */             paramTransform = 0;
/* 341 */           }break;
/*     */         case 2:
/* 345 */           ((SphereShape)paramCollisionShape)
/* 346 */             .getMargin();
/*     */ 
/* 350 */           System.err.println("cannot draw debug sphere");
/*     */ 
/* 358 */           paramTransform = 0;
/* 359 */           break;
/*     */         case 3:
/* 392 */           float f2 = (
/* 392 */             localObject2 = (StaticPlaneShape)paramCollisionShape)
/* 392 */             .getPlaneConstant();
/* 393 */           localObject4 = ((StaticPlaneShape)localObject2).getPlaneNormal((Vector3f)localObjectPool.get());
/* 394 */           (
/* 395 */             paramVector3f = (Vector3f)localObjectPool.get())
/* 395 */             .scale(f2, (Tuple3f)localObject4);
/* 396 */           localObject1 = (Vector3f)localObjectPool.get();
/* 397 */           localObject2 = (Vector3f)localObjectPool.get();
/* 398 */           TransformUtil.planeSpace1((Vector3f)localObject4, (Vector3f)localObject1, (Vector3f)localObject2);
/*     */           Vector3f localVector3f2;
/* 399 */           (
/* 402 */             localVector3f2 = (Vector3f)localObjectPool.get())
/* 402 */             .scaleAdd(100.0F, (Tuple3f)localObject1, paramVector3f);
/*     */           Vector3f localVector3f4;
/* 404 */           (
/* 405 */             localVector3f4 = (Vector3f)localObjectPool.get())
/* 405 */             .scale(100.0F, (Tuple3f)localObject1);
/* 406 */           localVector3f4.sub(paramVector3f, localVector3f4);
/*     */           Vector3f localVector3f5;
/* 408 */           (
/* 409 */             localVector3f5 = (Vector3f)localObjectPool.get())
/* 409 */             .scaleAdd(100.0F, (Tuple3f)localObject2, paramVector3f);
/*     */           Vector3f localVector3f6;
/* 411 */           (
/* 412 */             localVector3f6 = (Vector3f)localObjectPool.get())
/* 412 */             .scale(100.0F, (Tuple3f)localObject2);
/* 413 */           localVector3f6.sub(paramVector3f, localVector3f6);
/*     */ 
/* 415 */           GL11.glBegin(1);
/* 416 */           GL11.glVertex3f(localVector3f2.x, localVector3f2.y, localVector3f2.z);
/* 417 */           GL11.glVertex3f(localVector3f4.x, localVector3f4.y, localVector3f4.z);
/* 418 */           GL11.glVertex3f(localVector3f5.x, localVector3f5.y, localVector3f5.z);
/* 419 */           GL11.glVertex3f(localVector3f6.x, localVector3f6.y, localVector3f6.z);
/* 420 */           GL11.glEnd();
/*     */ 
/* 422 */           localObjectPool.release(localObject4);
/* 423 */           localObjectPool.release(paramVector3f);
/* 424 */           localObjectPool.release(localObject1);
/* 425 */           localObjectPool.release(localObject2);
/* 426 */           localObjectPool.release(localVector3f2);
/* 427 */           localObjectPool.release(localVector3f4);
/* 428 */           localObjectPool.release(localVector3f5);
/* 429 */           localObjectPool.release(localVector3f6);
/*     */ 
/* 431 */           break;
/*     */         case 4:
/* 437 */           int j = (
/* 437 */             localObject2 = (CylinderShape)paramCollisionShape)
/* 437 */             .getUpAxis();
/*     */ 
/* 439 */           ((CylinderShape)localObject2).getRadius();
/* 440 */           paramVector3f = (Vector3f)localObjectPool.get();
/* 441 */           float f1 = VectorUtil.getCoord(((CylinderShape)localObject2).getHalfExtentsWithMargin(paramVector3f), j);
/*     */ 
/* 452 */           switch (j) { case 2:
/* 453 */             GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F); break;
/*     */           case 3:
/* 454 */             GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F); break;
/*     */           default:
/* 456 */             GL11.glTranslatef(-f1, 0.0F, 0.0F);
/* 457 */             GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*     */           }
/*     */ 
/* 462 */           System.err.println("cannot draw debug cylinder");
/* 463 */           localObjectPool.release(paramVector3f);
/*     */ 
/* 465 */           break;
/*     */         default:
/* 468 */           if (paramCollisionShape.isConvex())
/*     */           {
/* 470 */             xP.a();
/* 471 */             localObject2 = (ConvexShape)paramCollisionShape;
/* 472 */             if (paramCollisionShape.getUserPointer() == null)
/*     */             {
/* 475 */               localObject3 = new ShapeHull((ConvexShape)localObject2);
/*     */ 
/* 481 */               float f3 = paramCollisionShape.getMargin();
/* 482 */               ((ShapeHull)localObject3).buildHull(f3);
/* 483 */               ((ConvexShape)localObject2).setUserPointer(localObject3);
/*     */             }
/*     */           }
/*     */           break;
/*     */         }
/*     */       }
/*     */       Vector3f localVector3f3;
/* 555 */       if (paramTransform != 0)
/*     */       {
/* 557 */         if ((paramCollisionShape.isPolyhedral()) && ((paramCollisionShape instanceof PolyhedralConvexShape))) {
/* 558 */           localObject2 = (PolyhedralConvexShape)paramCollisionShape;
/*     */ 
/* 560 */           GL11.glBegin(1);
/*     */ 
/* 562 */           localObject3 = (Vector3f)localObjectPool.get(); localVector3f3 = (Vector3f)localObjectPool.get();
/*     */ 
/* 564 */           for (paramVector3f = 0; paramVector3f < ((PolyhedralConvexShape)localObject2).getNumEdges(); paramVector3f++) {
/* 565 */             ((PolyhedralConvexShape)localObject2).getEdge(paramVector3f, (Vector3f)localObject3, localVector3f3);
/*     */ 
/* 567 */             GL11.glVertex3f(((Vector3f)localObject3).x, ((Vector3f)localObject3).y, ((Vector3f)localObject3).z);
/* 568 */             GL11.glVertex3f(localVector3f3.x, localVector3f3.y, localVector3f3.z);
/*     */           }
/* 570 */           GL11.glEnd();
/*     */ 
/* 572 */           localObjectPool.release(localObject3);
/* 573 */           localObjectPool.release(localVector3f3);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 622 */       if ((paramCollisionShape.isConcave()) && (paramCollisionShape.getShapeType() != BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE))
/*     */       {
/* 626 */         localObject2 = (ConcaveShape)paramCollisionShape;
/*     */ 
/* 631 */         (
/* 632 */           localObject3 = (Vector3f)localObjectPool.get())
/* 632 */           .set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 633 */         (
/* 634 */           localVector3f3 = (Vector3f)localObjectPool.get())
/* 634 */           .set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*     */ 
/* 636 */         (
/* 637 */           paramVector3f = new xl()).a = 
/* 637 */           ((paramInt & 0x1) != 0);
/*     */ 
/* 639 */         ((ConcaveShape)localObject2).processAllTriangles(paramVector3f, localVector3f3, (Vector3f)localObject3);
/*     */ 
/* 641 */         localObjectPool.release(localObject3);
/* 642 */         localObjectPool.release(localVector3f3);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 666 */     GlUtil.c();
/*     */ 
/* 678 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   public static void a(TypedConstraint paramTypedConstraint, Vector3f paramVector3f)
/*     */   {
/* 691 */     GlUtil.d();
/* 692 */     GL11.glEnable(2903);
/* 693 */     GL11.glDisable(2896);
/* 694 */     GL11.glColor3f(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/* 695 */     GL11.glDisable(3553);
/* 696 */     Object localObject = paramTypedConstraint.getConstraintType();
/*     */     Transform localTransform;
/* 700 */     switch (xk.b[localObject.ordinal()])
/*     */     {
/*     */     case 1:
/* 703 */       break;
/*     */     case 2:
/* 706 */       break;
/*     */     case 3:
/* 708 */       paramTypedConstraint = (HingeConstraint)paramTypedConstraint;
/* 709 */       localObject = new Transform();
/* 710 */       (
/* 711 */         localTransform = new Transform())
/* 711 */         .setIdentity();
/* 712 */       paramTypedConstraint.getRigidBodyA().getWorldTransform((Transform)localObject);
/*     */ 
/* 716 */       paramTypedConstraint.getAFrame(localTransform);
/* 717 */       ((Transform)localObject).mul(localTransform);
/* 718 */       a((Transform)localObject, 25.0F, paramVector3f);
/*     */ 
/* 720 */       ((Transform)localObject).setIdentity();
/* 721 */       localTransform.setIdentity();
/* 722 */       paramTypedConstraint.getRigidBodyB().getWorldTransform((Transform)localObject);
/*     */ 
/* 725 */       paramTypedConstraint.getBFrame(localTransform);
/* 726 */       ((Transform)localObject).mul(localTransform);
/* 727 */       a((Transform)localObject, 25.0F, new Vector3f(0.0F, 1.0F, 1.0F));
/* 728 */       break;
/*     */     case 4:
/* 731 */       break;
/*     */     case 5:
/* 734 */       paramTypedConstraint = (SliderConstraint)paramTypedConstraint;
/* 735 */       localObject = new Transform();
/* 736 */       (
/* 737 */         localTransform = new Transform())
/* 737 */         .setIdentity();
/* 738 */       paramTypedConstraint.getRigidBodyA().getWorldTransform((Transform)localObject);
/*     */ 
/* 742 */       paramTypedConstraint.getFrameOffsetA(localTransform);
/* 743 */       ((Transform)localObject).mul(localTransform);
/* 744 */       a((Transform)localObject, 25.0F, paramVector3f);
/*     */ 
/* 746 */       ((Transform)localObject).setIdentity();
/* 747 */       localTransform.setIdentity();
/* 748 */       paramTypedConstraint.getRigidBodyB().getWorldTransform((Transform)localObject);
/*     */ 
/* 751 */       paramTypedConstraint.getFrameOffsetB(localTransform);
/* 752 */       ((Transform)localObject).mul(localTransform);
/* 753 */       a((Transform)localObject, 25.0F, new Vector3f(0.0F, 1.0F, 1.0F));
/* 754 */     case 6: } GlUtil.c();
/*     */ 
/* 763 */     GL11.glEnable(2896);
/*     */   }
/*     */ 
/*     */   public static void a(Transform paramTransform, float paramFloat, Vector3f paramVector3f)
/*     */   {
/* 776 */     GlUtil.d();
/* 777 */     GL11.glDisable(3553);
/* 778 */     GL11.glEnable(2903);
/* 779 */     GL11.glDisable(2896);
/* 780 */     GL11.glColor3f(paramVector3f.x, paramVector3f.y, paramVector3f.z);
/* 781 */     paramTransform.getOpenGLMatrix(jdField_a_of_type_ArrayOfFloat);
/* 782 */     jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 783 */     jdField_a_of_type_JavaNioFloatBuffer.put(jdField_a_of_type_ArrayOfFloat);
/* 784 */     jdField_a_of_type_JavaNioFloatBuffer.rewind();
/* 785 */     GL11.glMultMatrix(jdField_a_of_type_JavaNioFloatBuffer);
/*     */ 
/* 788 */     GL11.glBegin(1);
/* 789 */     GL11.glVertex3f(-paramFloat, 0.0F, 0.0F);
/* 790 */     GL11.glVertex3f(paramFloat, 0.0F, 0.0F);
/* 791 */     GL11.glVertex3f(0.0F, 0.0F, -paramFloat);
/* 792 */     GL11.glVertex3f(0.0F, 0.0F, paramFloat);
/* 793 */     GL11.glVertex3f(0.0F, -paramFloat, 0.0F);
/* 794 */     GL11.glVertex3f(0.0F, paramFloat, 0.0F);
/* 795 */     GL11.glEnd();
/*     */ 
/* 797 */     GL11.glDisable(2903);
/* 798 */     GL11.glEnable(2896);
/* 799 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final void draw3dText(Vector3f paramVector3f, String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void drawContactPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, int paramInt, Vector3f paramVector3f3)
/*     */   {
/* 823 */     if ((this.jdField_a_of_type_Int & 0x8) != 0) {
/* 824 */       (
/* 825 */         paramInt = this.jdField_a_of_type_JavaxVecmathVector3f)
/* 825 */         .scaleAdd(paramFloat * 100.0F, paramVector3f2, paramVector3f1);
/* 826 */       GL11.glBegin(1);
/*     */ 
/* 842 */       GL11.glColor3f(paramVector3f3.x, paramVector3f3.y, paramVector3f3.z);
/* 843 */       GL11.glVertex3f(paramVector3f1.x, paramVector3f1.y, paramVector3f1.z);
/* 844 */       GL11.glVertex3f(paramInt.x, paramInt.y, paramInt.z);
/* 845 */       GL11.glEnd();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void drawLine(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
/*     */   {
/* 870 */     GL11.glBegin(1);
/* 871 */     GL11.glColor3f(paramVector3f3.x, paramVector3f3.y, paramVector3f3.z);
/* 872 */     GL11.glVertex3f(paramVector3f1.x, paramVector3f1.y, paramVector3f1.z);
/* 873 */     GL11.glVertex3f(paramVector3f2.x, paramVector3f2.y, paramVector3f2.z);
/* 874 */     GL11.glEnd();
/*     */   }
/*     */ 
/*     */   public final int getDebugMode()
/*     */   {
/* 882 */     this.jdField_a_of_type_Int = Math.min(1025, ((Integer)xu.G.a()).intValue());
/*     */ 
/* 900 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void reportErrorWarning(String paramString)
/*     */   {
/* 908 */     System.err.println(paramString);
/*     */   }
/*     */ 
/*     */   public final void setDebugMode(int paramInt)
/*     */   {
/* 916 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xj
 * JD-Core Version:    0.6.2
 */