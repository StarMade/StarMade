import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.BeamHandler;
import org.schema.game.common.data.element.BeamHandler.BeamState;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public class class_229
  implements class_965
{
  private List jdField_field_98_of_type_JavaUtilList;
  private static final Vector4f jdField_field_98_of_type_JavaxVecmathVector4f;
  private Vector4f jdField_field_106_of_type_JavaxVecmathVector4f;
  private static final Vector4f jdField_field_108_of_type_JavaxVecmathVector4f;
  private static final Vector4f jdField_field_109_of_type_JavaxVecmathVector4f;
  private static final Vector4f jdField_field_110_of_type_JavaxVecmathVector4f;
  private static final Vector4f jdField_field_111_of_type_JavaxVecmathVector4f;
  private static final Vector4f jdField_field_112_of_type_JavaxVecmathVector4f;
  private static final Vector4f jdField_field_113_of_type_JavaxVecmathVector4f;
  private static final Vector4f jdField_field_114_of_type_JavaxVecmathVector4f;
  private static final Vector4f jdField_field_115_of_type_JavaxVecmathVector4f;
  private static Vector3f jdField_field_98_of_type_JavaxVecmathVector3f;
  private static Vector3f jdField_field_106_of_type_JavaxVecmathVector3f;
  private static Vector3f jdField_field_108_of_type_JavaxVecmathVector3f;
  private ObjectOpenHashSet jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet = new ObjectOpenHashSet();
  private final class_1383 jdField_field_98_of_type_Class_1383 = new class_1383();
  private static final Vector3f jdField_field_109_of_type_JavaxVecmathVector3f;
  private static final Vector3f jdField_field_110_of_type_JavaxVecmathVector3f;
  private final Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private final class_48 jdField_field_98_of_type_Class_48 = new class_48();
  private static final Vector3f jdField_field_111_of_type_JavaxVecmathVector3f;
  private static final Vector3f jdField_field_112_of_type_JavaxVecmathVector3f;
  private static final Vector3f jdField_field_113_of_type_JavaxVecmathVector3f;
  private boolean jdField_field_98_of_type_Boolean;
  private final class_233 jdField_field_98_of_type_Class_233;
  private static Vector3f jdField_field_114_of_type_JavaxVecmathVector3f;
  private static float jdField_field_98_of_type_Float;
  private static Vector3f jdField_field_115_of_type_JavaxVecmathVector3f;
  
  public class_229(class_233 paramclass_233, List paramList, class_231 paramclass_231)
  {
    this.jdField_field_98_of_type_Class_233 = paramclass_233;
    a11(paramList, paramclass_231);
  }
  
  public final void a() {}
  
  public final void d()
  {
    Iterator localIterator = this.jdField_field_98_of_type_JavaUtilList.iterator();
    while (localIterator.hasNext()) {
      ((class_721)localIterator.next()).getHandler().setDrawer(null);
    }
    this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.clear();
  }
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_JavaUtilList == null) {
      try
      {
        throw new NullPointerException("[CLIENT][ERROR] ########## beam handlers null of beam drawer " + this);
      }
      catch (NullPointerException localNullPointerException)
      {
        localNullPointerException.printStackTrace();
        return;
      }
    }
    if (this.jdField_field_98_of_type_Boolean)
    {
      Iterator localIterator = this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator();
      while (localIterator.hasNext())
      {
        BeamHandler localBeamHandler = (BeamHandler)localIterator.next();
        a8(localBeamHandler);
      }
    }
  }
  
  private void a8(BeamHandler paramBeamHandler)
  {
    paramBeamHandler = paramBeamHandler.getBeamStates().values().iterator();
    while (paramBeamHandler.hasNext())
    {
      BeamHandler.BeamState localBeamState = (BeamHandler.BeamState)paramBeamHandler.next();
      if ((!jdField_field_106_of_type_Boolean) && (localBeamState == null)) {
        throw new AssertionError();
      }
      localBeamState.color.set(this.jdField_field_106_of_type_JavaxVecmathVector4f);
      a10(localBeamState, this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
      class_233.jdField_field_119_of_type_Int += 1;
    }
  }
  
  public final void a9(ObjectHeapPriorityQueue paramObjectHeapPriorityQueue)
  {
    if (this.jdField_field_98_of_type_Boolean)
    {
      Iterator localIterator1 = this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator();
      while (localIterator1.hasNext())
      {
        Iterator localIterator2 = ((BeamHandler)localIterator1.next()).getBeamStates().values().iterator();
        while (localIterator2.hasNext())
        {
          BeamHandler.BeamState localBeamState;
          (localBeamState = (BeamHandler.BeamState)localIterator2.next()).color.set(this.jdField_field_106_of_type_JavaxVecmathVector4f);
          jdField_field_115_of_type_JavaxVecmathVector3f.sub(class_969.a1().a83(), localBeamState.from);
          localBeamState.camDistStart = jdField_field_115_of_type_JavaxVecmathVector3f.lengthSquared();
          paramObjectHeapPriorityQueue.enqueue(localBeamState);
        }
      }
    }
  }
  
  public static void a10(BeamHandler.BeamState paramBeamState, Transform paramTransform)
  {
    jdField_field_98_of_type_JavaxVecmathVector3f.set(paramBeamState.from);
    if (paramBeamState.hitPoint != null) {
      jdField_field_106_of_type_JavaxVecmathVector3f.set(paramBeamState.hitPoint);
    } else {
      jdField_field_106_of_type_JavaxVecmathVector3f.set(paramBeamState.field_1883);
    }
    if (!GlUtil.a19(jdField_field_98_of_type_JavaxVecmathVector3f, jdField_field_106_of_type_JavaxVecmathVector3f, class_969.field_1259.a())) {
      return;
    }
    jdField_field_108_of_type_JavaxVecmathVector3f.sub(jdField_field_98_of_type_JavaxVecmathVector3f, jdField_field_106_of_type_JavaxVecmathVector3f);
    float f1 = jdField_field_108_of_type_JavaxVecmathVector3f.length();
    jdField_field_109_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 1.0F);
    paramTransform.origin.set(jdField_field_98_of_type_JavaxVecmathVector3f);
    jdField_field_114_of_type_JavaxVecmathVector3f.cross(jdField_field_108_of_type_JavaxVecmathVector3f, jdField_field_109_of_type_JavaxVecmathVector3f);
    jdField_field_114_of_type_JavaxVecmathVector3f.normalize();
    GlUtil.a30(jdField_field_108_of_type_JavaxVecmathVector3f, paramTransform);
    GlUtil.d2(jdField_field_114_of_type_JavaxVecmathVector3f, paramTransform);
    jdField_field_109_of_type_JavaxVecmathVector3f.cross(jdField_field_108_of_type_JavaxVecmathVector3f, jdField_field_114_of_type_JavaxVecmathVector3f);
    jdField_field_109_of_type_JavaxVecmathVector3f.normalize();
    GlUtil.c3(jdField_field_109_of_type_JavaxVecmathVector3f, paramTransform);
    Matrix3f localMatrix3f = paramTransform.basis;
    Vector3f localVector3f2 = class_969.a1().a83();
    Object localObject = jdField_field_106_of_type_JavaxVecmathVector3f;
    Vector3f localVector3f1 = jdField_field_98_of_type_JavaxVecmathVector3f;
    jdField_field_111_of_type_JavaxVecmathVector3f.sub(localVector3f2, localVector3f1);
    jdField_field_112_of_type_JavaxVecmathVector3f.sub((Tuple3f)localObject, localVector3f1);
    jdField_field_113_of_type_JavaxVecmathVector3f.cross(jdField_field_111_of_type_JavaxVecmathVector3f, jdField_field_112_of_type_JavaxVecmathVector3f);
    jdField_field_113_of_type_JavaxVecmathVector3f.normalize();
    jdField_field_110_of_type_JavaxVecmathVector3f.set(0.0F, 1.0F, 0.0F);
    localMatrix3f.transform(jdField_field_110_of_type_JavaxVecmathVector3f);
    float f3 = (float)FastMath.a2(jdField_field_110_of_type_JavaxVecmathVector3f.dot(jdField_field_113_of_type_JavaxVecmathVector3f));
    jdField_field_110_of_type_JavaxVecmathVector3f.set(1.0F, 0.0F, 0.0F);
    localMatrix3f.transform(jdField_field_110_of_type_JavaxVecmathVector3f);
    float f2 = (float)FastMath.a2(jdField_field_110_of_type_JavaxVecmathVector3f.dot(jdField_field_113_of_type_JavaxVecmathVector3f));
    if (1.570796F > f2) {
      f3 = 6.283186F - f3;
    }
    f2 = f3;
    localObject = paramBeamState.color;
    if (!jdField_field_114_of_type_JavaxVecmathVector4f.equals(jdField_field_98_of_type_JavaxVecmathVector4f))
    {
      jdField_field_114_of_type_JavaxVecmathVector4f.set(jdField_field_98_of_type_JavaxVecmathVector4f);
      GlUtil.a42(class_233.jdField_field_119_of_type_Class_1377, "thrustColor0", jdField_field_114_of_type_JavaxVecmathVector4f);
    }
    if (!jdField_field_115_of_type_JavaxVecmathVector4f.equals((Tuple4f)localObject))
    {
      jdField_field_115_of_type_JavaxVecmathVector4f.set((Tuple4f)localObject);
      GlUtil.a42(class_233.jdField_field_119_of_type_Class_1377, "thrustColor1", jdField_field_115_of_type_JavaxVecmathVector4f);
    }
    float f4;
    if (Float.compare(f4 = f1 / 20.0F, jdField_field_98_of_type_Float) != 0)
    {
      GlUtil.a33(class_233.jdField_field_119_of_type_Class_1377, "texCoordMult", f4);
      jdField_field_98_of_type_Float = f4;
    }
    GlUtil.d1();
    GlUtil.b3(paramTransform);
    GlUtil.a29(57.295776F * (f2 + 1.570796F));
    class_233.jdField_field_119_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
    GlUtil.c2();
  }
  
  public final void e()
  {
    Iterator localIterator = this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (BeamHandler)localIterator.next();
      Object localObject2 = localObject1;
      localObject1 = this;
      if (((BeamHandler)localObject2).getSegmentController().isClientOwnObject())
      {
        GlUtil.a41(class_1376.field_1569, "selectionColor", 0.7F, 0.77F, 0.1F, 1.0F);
        localObject2 = ((BeamHandler)localObject2).getBeamStates().values().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Object localObject3 = (BeamHandler.BeamState)((Iterator)localObject2).next();
          if ((!jdField_field_106_of_type_Boolean) && (localObject3 == null)) {
            throw new AssertionError();
          }
          if (((BeamHandler.BeamState)localObject3).segmentHit != null)
          {
            ((BeamHandler.BeamState)localObject3).segmentHit.a12();
            if (((BeamHandler.BeamState)localObject3).segmentHit.a9() != 0)
            {
              class_1383 localclass_1383 = ((class_229)localObject1).jdField_field_98_of_type_Class_1383;
              SegmentController localSegmentController = ((BeamHandler.BeamState)localObject3).segmentHit.a7().a15();
              Object localObject4 = ((BeamHandler.BeamState)localObject3).segmentHit.a2(((class_229)localObject1).jdField_field_98_of_type_Class_48);
              (localObject3 = localObject1).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(localSegmentController.getWorldTransformClient());
              localObject4 = new Vector3f(((class_48)localObject4).field_475 - 8, ((class_48)localObject4).field_476 - 8, ((class_48)localObject4).field_477 - 8);
              ((class_229)localObject3).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.transform((Tuple3f)localObject4);
              ((class_229)localObject3).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject4);
              GlUtil.d1();
              GlUtil.b3(((class_229)localObject3).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
              float tmp273_272 = (1.05F + localclass_1383.a1() * 0.05F);
              float tmp274_273 = tmp273_272;
              GL11.glScalef(tmp273_272, tmp274_273, tmp274_273);
              class_233.field_120.i();
              GlUtil.c2();
            }
          }
        }
      }
    }
  }
  
  public final void c() {}
  
  public final void f()
  {
    if (this.jdField_field_98_of_type_JavaUtilList != null) {
      d();
    }
    this.jdField_field_98_of_type_JavaUtilList = null;
  }
  
  public final void a11(List paramList, class_231 paramclass_231)
  {
    Object localObject = paramclass_231;
    paramclass_231 = this;
    switch (class_235.field_651[localObject.ordinal()])
    {
    case 1: 
      paramclass_231.jdField_field_106_of_type_JavaxVecmathVector4f = jdField_field_108_of_type_JavaxVecmathVector4f;
      break;
    case 2: 
      paramclass_231.jdField_field_106_of_type_JavaxVecmathVector4f = jdField_field_109_of_type_JavaxVecmathVector4f;
      break;
    case 3: 
      paramclass_231.jdField_field_106_of_type_JavaxVecmathVector4f = jdField_field_110_of_type_JavaxVecmathVector4f;
      break;
    case 4: 
      paramclass_231.jdField_field_106_of_type_JavaxVecmathVector4f = jdField_field_113_of_type_JavaxVecmathVector4f;
      break;
    case 5: 
      paramclass_231.jdField_field_106_of_type_JavaxVecmathVector4f = jdField_field_111_of_type_JavaxVecmathVector4f;
      break;
    case 6: 
      paramclass_231.jdField_field_106_of_type_JavaxVecmathVector4f = jdField_field_112_of_type_JavaxVecmathVector4f;
      break;
    default: 
      paramclass_231.jdField_field_106_of_type_JavaxVecmathVector4f = jdField_field_109_of_type_JavaxVecmathVector4f;
    }
    this.jdField_field_98_of_type_JavaUtilList = paramList;
    if (this.jdField_field_98_of_type_JavaUtilList != null)
    {
      paramclass_231 = this;
      d();
      localObject = paramclass_231.jdField_field_98_of_type_JavaUtilList.iterator();
      while (((Iterator)localObject).hasNext()) {
        ((class_721)((Iterator)localObject).next()).getHandler().setDrawer(paramclass_231);
      }
      return;
    }
    d();
  }
  
  public final void a1(class_941 paramclass_941)
  {
    this.jdField_field_98_of_type_Class_1383.a(paramclass_941);
  }
  
  public final void a12(BeamHandler paramBeamHandler, boolean paramBoolean)
  {
    if (paramBoolean) {
      this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.add(paramBeamHandler);
    } else {
      this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.remove(paramBeamHandler);
    }
    if (this.jdField_field_98_of_type_Boolean != (!this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty())) {
      this.jdField_field_98_of_type_Class_233.a8(this, !this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty());
    }
    this.jdField_field_98_of_type_Boolean = (!this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectOpenHashSet.isEmpty());
  }
  
  static
  {
    jdField_field_98_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
    jdField_field_108_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 1.0F, 1.0F);
    jdField_field_109_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 1.0F, 0.0F, 1.0F);
    jdField_field_110_of_type_JavaxVecmathVector4f = new Vector4f(0.7F, 0.0F, 0.0F, 1.0F);
    jdField_field_111_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 0.0F, 1.0F);
    jdField_field_112_of_type_JavaxVecmathVector4f = new Vector4f(0.7F, 0.7F, 0.7F, 1.0F);
    jdField_field_113_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 0.3F, 1.0F, 1.0F);
    jdField_field_114_of_type_JavaxVecmathVector4f = new Vector4f();
    jdField_field_115_of_type_JavaxVecmathVector4f = new Vector4f();
    jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
    jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
    jdField_field_108_of_type_JavaxVecmathVector3f = new Vector3f();
    jdField_field_109_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 1.0F);
    jdField_field_110_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 1.0F);
    jdField_field_111_of_type_JavaxVecmathVector3f = new Vector3f();
    jdField_field_112_of_type_JavaxVecmathVector3f = new Vector3f();
    jdField_field_113_of_type_JavaxVecmathVector3f = new Vector3f();
    jdField_field_114_of_type_JavaxVecmathVector3f = new Vector3f();
    new Vector3f(-8.0F, -8.0F, -8.0F);
    jdField_field_115_of_type_JavaxVecmathVector3f = new Vector3f();
    new Vector3f();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_229
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */