import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.ConcaveShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.collision.shapes.CylinderShape;
import com.bulletphysics.collision.shapes.PolyhedralConvexShape;
import com.bulletphysics.collision.shapes.ShapeHull;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.HingeConstraint;
import com.bulletphysics.dynamics.constraintsolver.SliderConstraint;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectPool;
import java.io.PrintStream;
import java.nio.FloatBuffer;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_919
  extends IDebugDraw
{
  private static float[] jdField_field_215_of_type_ArrayOfFloat;
  private int jdField_field_215_of_type_Int = 0;
  private final Vector3f jdField_field_215_of_type_JavaxVecmathVector3f = new Vector3f();
  private static FloatBuffer jdField_field_215_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer((class_919.jdField_field_215_of_type_ArrayOfFloat = new float[16]).length);
  
  public static void a(Transform paramTransform, CollisionShape paramCollisionShape, Vector3f paramVector3f, int paramInt)
  {
    Object localObject1 = ObjectPool.get(Transform.class);
    ObjectPool localObjectPool = ObjectPool.get(Vector3f.class);
    GlUtil.d1();
    paramTransform.getOpenGLMatrix(jdField_field_215_of_type_ArrayOfFloat);
    jdField_field_215_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_215_of_type_JavaNioFloatBuffer.put(jdField_field_215_of_type_ArrayOfFloat);
    jdField_field_215_of_type_JavaNioFloatBuffer.rewind();
    GL11.glMultMatrix(jdField_field_215_of_type_JavaNioFloatBuffer);
    GL11.glEnable(2903);
    GL11.glColor3f(paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617);
    GL11.glDisable(2896);
    GL11.glDisable(3553);
    Object localObject2;
    Object localObject4;
    if (paramCollisionShape.getShapeType() == BroadphaseNativeType.COMPOUND_SHAPE_PROXYTYPE)
    {
      paramTransform = (CompoundShape)paramCollisionShape;
      localObject2 = (Transform)((ObjectPool)localObject1).get();
      for (int i = paramTransform.getNumChildShapes() - 1; i >= 0; i--)
      {
        paramTransform.getChildTransform(i, (Transform)localObject2);
        localObject4 = paramTransform.getChildShape(i);
        a((Transform)localObject2, (CollisionShape)localObject4, paramVector3f, paramInt);
      }
      ((ObjectPool)localObject1).release(localObject2);
    }
    else
    {
      paramTransform = 1;
      Object localObject3;
      if ((paramInt & 0x1) == 0) {
        switch (class_921.field_1146[paramCollisionShape.getShapeType().ordinal()])
        {
        case 1: 
          if ((paramCollisionShape instanceof BoxShape))
          {
            Vector3f localVector3f1 = ((BoxShape)paramCollisionShape).getHalfExtentsWithMargin((Vector3f)localObjectPool.get());
            GL11.glScalef(2.0F * localVector3f1.field_615, 2.0F * localVector3f1.field_616, 2.0F * localVector3f1.field_617);
            class_990.a();
            localObjectPool.release(localVector3f1);
            paramTransform = 0;
          }
          break;
        case 2: 
          ((SphereShape)paramCollisionShape).getMargin();
          System.err.println("cannot draw debug sphere");
          paramTransform = 0;
          break;
        case 3: 
          float f2 = (localObject2 = (StaticPlaneShape)paramCollisionShape).getPlaneConstant();
          localObject4 = ((StaticPlaneShape)localObject2).getPlaneNormal((Vector3f)localObjectPool.get());
          (paramVector3f = (Vector3f)localObjectPool.get()).scale(f2, (Tuple3f)localObject4);
          localObject1 = (Vector3f)localObjectPool.get();
          localObject2 = (Vector3f)localObjectPool.get();
          TransformUtil.planeSpace1((Vector3f)localObject4, (Vector3f)localObject1, (Vector3f)localObject2);
          Vector3f localVector3f2;
          (localVector3f2 = (Vector3f)localObjectPool.get()).scaleAdd(100.0F, (Tuple3f)localObject1, paramVector3f);
          Vector3f localVector3f4;
          (localVector3f4 = (Vector3f)localObjectPool.get()).scale(100.0F, (Tuple3f)localObject1);
          localVector3f4.sub(paramVector3f, localVector3f4);
          Vector3f localVector3f5;
          (localVector3f5 = (Vector3f)localObjectPool.get()).scaleAdd(100.0F, (Tuple3f)localObject2, paramVector3f);
          Vector3f localVector3f6;
          (localVector3f6 = (Vector3f)localObjectPool.get()).scale(100.0F, (Tuple3f)localObject2);
          localVector3f6.sub(paramVector3f, localVector3f6);
          GL11.glBegin(1);
          GL11.glVertex3f(localVector3f2.field_615, localVector3f2.field_616, localVector3f2.field_617);
          GL11.glVertex3f(localVector3f4.field_615, localVector3f4.field_616, localVector3f4.field_617);
          GL11.glVertex3f(localVector3f5.field_615, localVector3f5.field_616, localVector3f5.field_617);
          GL11.glVertex3f(localVector3f6.field_615, localVector3f6.field_616, localVector3f6.field_617);
          GL11.glEnd();
          localObjectPool.release(localObject4);
          localObjectPool.release(paramVector3f);
          localObjectPool.release(localObject1);
          localObjectPool.release(localObject2);
          localObjectPool.release(localVector3f2);
          localObjectPool.release(localVector3f4);
          localObjectPool.release(localVector3f5);
          localObjectPool.release(localVector3f6);
          break;
        case 4: 
          int j = (localObject2 = (CylinderShape)paramCollisionShape).getUpAxis();
          ((CylinderShape)localObject2).getRadius();
          paramVector3f = (Vector3f)localObjectPool.get();
          float f1 = VectorUtil.getCoord(((CylinderShape)localObject2).getHalfExtentsWithMargin(paramVector3f), j);
          switch (j)
          {
          case 2: 
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            break;
          case 3: 
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            break;
          default: 
            GL11.glTranslatef(-f1, 0.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
          }
          System.err.println("cannot draw debug cylinder");
          localObjectPool.release(paramVector3f);
          break;
        default: 
          if (paramCollisionShape.isConvex())
          {
            class_990.a();
            localObject2 = (ConvexShape)paramCollisionShape;
            if (paramCollisionShape.getUserPointer() == null)
            {
              localObject3 = new ShapeHull((ConvexShape)localObject2);
              float f3 = paramCollisionShape.getMargin();
              ((ShapeHull)localObject3).buildHull(f3);
              ((ConvexShape)localObject2).setUserPointer(localObject3);
            }
          }
          break;
        }
      }
      Vector3f localVector3f3;
      if ((paramTransform != 0) && (paramCollisionShape.isPolyhedral()) && ((paramCollisionShape instanceof PolyhedralConvexShape)))
      {
        localObject2 = (PolyhedralConvexShape)paramCollisionShape;
        GL11.glBegin(1);
        localObject3 = (Vector3f)localObjectPool.get();
        localVector3f3 = (Vector3f)localObjectPool.get();
        for (paramVector3f = 0; paramVector3f < ((PolyhedralConvexShape)localObject2).getNumEdges(); paramVector3f++)
        {
          ((PolyhedralConvexShape)localObject2).getEdge(paramVector3f, (Vector3f)localObject3, localVector3f3);
          GL11.glVertex3f(((Vector3f)localObject3).field_615, ((Vector3f)localObject3).field_616, ((Vector3f)localObject3).field_617);
          GL11.glVertex3f(localVector3f3.field_615, localVector3f3.field_616, localVector3f3.field_617);
        }
        GL11.glEnd();
        localObjectPool.release(localObject3);
        localObjectPool.release(localVector3f3);
      }
      if ((paramCollisionShape.isConcave()) && (paramCollisionShape.getShapeType() != BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE))
      {
        localObject2 = (ConcaveShape)paramCollisionShape;
        (localObject3 = (Vector3f)localObjectPool.get()).set(1.0E+030F, 1.0E+030F, 1.0E+030F);
        (localVector3f3 = (Vector3f)localObjectPool.get()).set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
        (paramVector3f = new class_931()).field_217 = ((paramInt & 0x1) != 0);
        ((ConcaveShape)localObject2).processAllTriangles(paramVector3f, localVector3f3, (Vector3f)localObject3);
        localObjectPool.release(localObject3);
        localObjectPool.release(localVector3f3);
      }
    }
    GlUtil.c2();
    GL11.glEnable(2896);
  }
  
  public static void a1(TypedConstraint paramTypedConstraint, Vector3f paramVector3f)
  {
    GlUtil.d1();
    GL11.glEnable(2903);
    GL11.glDisable(2896);
    GL11.glColor3f(paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617);
    GL11.glDisable(3553);
    Object localObject = paramTypedConstraint.getConstraintType();
    Transform localTransform;
    switch (class_921.field_1147[localObject.ordinal()])
    {
    case 1: 
      break;
    case 2: 
      break;
    case 3: 
      paramTypedConstraint = (HingeConstraint)paramTypedConstraint;
      localObject = new Transform();
      (localTransform = new Transform()).setIdentity();
      paramTypedConstraint.getRigidBodyA().getWorldTransform((Transform)localObject);
      paramTypedConstraint.getAFrame(localTransform);
      ((Transform)localObject).mul(localTransform);
      a2((Transform)localObject, 25.0F, paramVector3f);
      ((Transform)localObject).setIdentity();
      localTransform.setIdentity();
      paramTypedConstraint.getRigidBodyB().getWorldTransform((Transform)localObject);
      paramTypedConstraint.getBFrame(localTransform);
      ((Transform)localObject).mul(localTransform);
      a2((Transform)localObject, 25.0F, new Vector3f(0.0F, 1.0F, 1.0F));
      break;
    case 4: 
      break;
    case 5: 
      paramTypedConstraint = (SliderConstraint)paramTypedConstraint;
      localObject = new Transform();
      (localTransform = new Transform()).setIdentity();
      paramTypedConstraint.getRigidBodyA().getWorldTransform((Transform)localObject);
      paramTypedConstraint.getFrameOffsetA(localTransform);
      ((Transform)localObject).mul(localTransform);
      a2((Transform)localObject, 25.0F, paramVector3f);
      ((Transform)localObject).setIdentity();
      localTransform.setIdentity();
      paramTypedConstraint.getRigidBodyB().getWorldTransform((Transform)localObject);
      paramTypedConstraint.getFrameOffsetB(localTransform);
      ((Transform)localObject).mul(localTransform);
      a2((Transform)localObject, 25.0F, new Vector3f(0.0F, 1.0F, 1.0F));
    }
    GlUtil.c2();
    GL11.glEnable(2896);
  }
  
  public static void a2(Transform paramTransform, float paramFloat, Vector3f paramVector3f)
  {
    GlUtil.d1();
    GL11.glDisable(3553);
    GL11.glEnable(2903);
    GL11.glDisable(2896);
    GL11.glColor3f(paramVector3f.field_615, paramVector3f.field_616, paramVector3f.field_617);
    paramTransform.getOpenGLMatrix(jdField_field_215_of_type_ArrayOfFloat);
    jdField_field_215_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_215_of_type_JavaNioFloatBuffer.put(jdField_field_215_of_type_ArrayOfFloat);
    jdField_field_215_of_type_JavaNioFloatBuffer.rewind();
    GL11.glMultMatrix(jdField_field_215_of_type_JavaNioFloatBuffer);
    GL11.glBegin(1);
    GL11.glVertex3f(-paramFloat, 0.0F, 0.0F);
    GL11.glVertex3f(paramFloat, 0.0F, 0.0F);
    GL11.glVertex3f(0.0F, 0.0F, -paramFloat);
    GL11.glVertex3f(0.0F, 0.0F, paramFloat);
    GL11.glVertex3f(0.0F, -paramFloat, 0.0F);
    GL11.glVertex3f(0.0F, paramFloat, 0.0F);
    GL11.glEnd();
    GL11.glDisable(2903);
    GL11.glEnable(2896);
    GlUtil.c2();
  }
  
  public final void draw3dText(Vector3f paramVector3f, String paramString) {}
  
  public final void drawContactPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, int paramInt, Vector3f paramVector3f3)
  {
    if ((this.jdField_field_215_of_type_Int & 0x8) != 0)
    {
      (paramInt = this.jdField_field_215_of_type_JavaxVecmathVector3f).scaleAdd(paramFloat * 100.0F, paramVector3f2, paramVector3f1);
      GL11.glBegin(1);
      GL11.glColor3f(paramVector3f3.field_615, paramVector3f3.field_616, paramVector3f3.field_617);
      GL11.glVertex3f(paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
      GL11.glVertex3f(paramInt.field_615, paramInt.field_616, paramInt.field_617);
      GL11.glEnd();
    }
  }
  
  public final void drawLine(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3)
  {
    GL11.glBegin(1);
    GL11.glColor3f(paramVector3f3.field_615, paramVector3f3.field_616, paramVector3f3.field_617);
    GL11.glVertex3f(paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
    GL11.glVertex3f(paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    GL11.glEnd();
  }
  
  public final int getDebugMode()
  {
    this.jdField_field_215_of_type_Int = Math.min(1025, ((Integer)class_949.field_1222.a4()).intValue());
    return this.jdField_field_215_of_type_Int;
  }
  
  public final void reportErrorWarning(String paramString)
  {
    System.err.println(paramString);
  }
  
  public final void setDebugMode(int paramInt)
  {
    this.jdField_field_215_of_type_Int = paramInt;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_919
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */