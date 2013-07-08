import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectPool;
import it.unimi.dsi.fastutil.objects.ObjectAVLTreeSet;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
import org.schema.game.common.controller.elements.thrust.ThrusterUnit;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_295
  implements class_965, class_1369
{
  private class_747 jdField_field_98_of_type_Class_747;
  private static class_1377 jdField_field_98_of_type_Class_1377;
  private static Matrix3f jdField_field_98_of_type_JavaxVecmathMatrix3f = new Matrix3f();
  private ObjectAVLTreeSet jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet = new ObjectAVLTreeSet();
  private float jdField_field_98_of_type_Float = 0.0F;
  private Vector4f jdField_field_98_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
  private Vector4f jdField_field_106_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 1.0F, 1.0F);
  private Vector4f field_108 = new Vector4f();
  private Vector4f field_109 = new Vector4f();
  private Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform;
  private boolean jdField_field_98_of_type_Boolean;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f;
  private boolean jdField_field_106_of_type_Boolean;
  private class_48 jdField_field_98_of_type_Class_48;
  private long jdField_field_98_of_type_Long;
  private ObjectPool jdField_field_98_of_type_ComBulletphysicsUtilObjectPool;
  private class_796 jdField_field_98_of_type_Class_796;
  private static Vector4f field_110 = new Vector4f();
  private static Vector4f field_111 = new Vector4f();
  
  public class_295(class_747 paramclass_747)
  {
    new Vector3f();
    this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, -0.5F);
    this.jdField_field_106_of_type_Boolean = true;
    this.jdField_field_98_of_type_Class_48 = new class_48();
    this.jdField_field_98_of_type_Long = -1L;
    this.jdField_field_98_of_type_ComBulletphysicsUtilObjectPool = ObjectPool.get(eF.class);
    this.jdField_field_98_of_type_Class_796 = new class_796();
    this.jdField_field_98_of_type_Class_747 = paramclass_747;
    jdField_field_98_of_type_JavaxVecmathMatrix3f.rotY(3.141593F);
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (this.jdField_field_106_of_type_Boolean) {
      jdField_field_98_of_type_Class_1377 = class_1376.field_1567;
    }
    if (this.jdField_field_98_of_type_Class_747.a7()) {
      return;
    }
    if (!this.jdField_field_98_of_type_Boolean)
    {
      if (this.jdField_field_98_of_type_Class_747.getWorldTransform() != null)
      {
        this.jdField_field_98_of_type_Long = System.currentTimeMillis();
        this.jdField_field_98_of_type_Boolean = true;
      }
      return;
    }
    int i = 1;
    ObjectBidirectionalIterator localObjectBidirectionalIterator = this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.iterator();
    while (localObjectBidirectionalIterator.hasPrevious())
    {
      ((class_212)localObjectBidirectionalIterator.previous()).a1(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform, this.jdField_field_98_of_type_JavaxVecmathVector3f);
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.mul(jdField_field_98_of_type_JavaxVecmathMatrix3f);
      if ((GlUtil.a20(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin, class_969.field_1259.a())) && (GlUtil.a18(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform, class_218.field_98)))
      {
        if (i != 0)
        {
          class_295 localclass_295 = this;
          float f = Math.min(0.99F, localclass_295.jdField_field_98_of_type_Class_747.a8().length() / localclass_295.jdField_field_98_of_type_Class_747.a14());
          localclass_295.field_108.set(localclass_295.jdField_field_98_of_type_JavaxVecmathVector4f);
          localclass_295.field_109.set(localclass_295.jdField_field_106_of_type_JavaxVecmathVector4f);
          localclass_295.field_108.scale(f);
          localclass_295.field_109.scale(f);
          localclass_295.field_109.field_596 = (0.5F - f / 2.0F);
          localclass_295.field_109.field_598 = f;
          if (!field_110.equals(localclass_295.field_108))
          {
            GlUtil.a42(jdField_field_98_of_type_Class_1377, "thrustColor0", localclass_295.field_108);
            field_110.set(localclass_295.field_108);
          }
          if (!field_111.equals(localclass_295.field_109))
          {
            GlUtil.a42(jdField_field_98_of_type_Class_1377, "thrustColor1", localclass_295.field_109);
            field_111.set(localclass_295.field_109);
          }
          GlUtil.a33(jdField_field_98_of_type_Class_1377, "ticks", localclass_295.jdField_field_98_of_type_Float);
          int j = 0;
        }
        GlUtil.d1();
        GlUtil.b3(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
        ((Mesh)class_218.field_98.a152().get(0)).f();
        GlUtil.c2();
      }
    }
  }
  
  public final class_747 a24()
  {
    return this.jdField_field_98_of_type_Class_747;
  }
  
  public final void d() {}
  
  public final void c()
  {
    jdField_field_98_of_type_Class_1377 = class_1376.field_1567;
  }
  
  public final void e()
  {
    this.jdField_field_98_of_type_Long = System.currentTimeMillis();
  }
  
  public final void a1(class_941 paramclass_941)
  {
    if ((this.jdField_field_98_of_type_Long > 0L) && (System.currentTimeMillis() - this.jdField_field_98_of_type_Long > 500L))
    {
      class_295 localclass_295 = this;
      synchronized (this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet)
      {
        Iterator localIterator = localclass_295.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.iterator();
        Object localObject1;
        while (localIterator.hasNext())
        {
          (localObject1 = (class_212)localIterator.next()).a3();
          localclass_295.jdField_field_98_of_type_ComBulletphysicsUtilObjectPool.release(localObject1);
        }
        localclass_295.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.clear();
        localIterator = localclass_295.jdField_field_98_of_type_Class_747.a112().getThrusterElementManager().getCollection().getCollection().iterator();
        while (localIterator.hasNext())
        {
          localObject1 = ((ThrusterUnit)localIterator.next()).getLastElements().entrySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            Map.Entry localEntry = (Map.Entry)((Iterator)localObject1).next();
            localclass_295.jdField_field_98_of_type_Class_48.b1((class_48)localEntry.getValue());
            localclass_295.jdField_field_98_of_type_Class_48.field_477 -= 1;
            Object localObject2 = null;
            try
            {
              localObject2 = localclass_295.jdField_field_98_of_type_Class_747.getSegmentBuffer().a10(localclass_295.jdField_field_98_of_type_Class_48, false, localclass_295.jdField_field_98_of_type_Class_796);
            }
            catch (IOException localIOException)
            {
              localIOException;
            }
            catch (InterruptedException localInterruptedException)
            {
              localInterruptedException;
            }
            if ((localObject2 == null) || (((class_796)localObject2).a9() == 0))
            {
              (localObject2 = (class_212)localclass_295.jdField_field_98_of_type_ComBulletphysicsUtilObjectPool.get()).a2(localclass_295.jdField_field_98_of_type_Class_747, (class_48)localEntry.getValue());
              localclass_295.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.add(localObject2);
            }
          }
        }
      }
      this.jdField_field_98_of_type_Long = -1L;
    }
    this.jdField_field_98_of_type_Float = ((float)(this.jdField_field_98_of_type_Float + paramclass_941.a() / 100.0F * ((Math.random() + 9.999999747378752E-005D) / 0.1000000014901161D)));
    if (this.jdField_field_98_of_type_Float > 1.0F) {
      this.jdField_field_98_of_type_Float = 0.0F;
    }
  }
  
  public final void a13(class_1377 paramclass_1377) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_295
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */