import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.BidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectAVLTreeSet;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.common.controller.elements.ManagerModuleCollection;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.controller.elements.weapon.WeaponElementManager;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_356
  implements class_710, class_965
{
  private class_747 jdField_field_119_of_type_Class_747;
  private static class_1377 jdField_field_119_of_type_Class_1377;
  private final ObjectAVLTreeSet jdField_field_119_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet = new ObjectAVLTreeSet();
  private final Vector4f jdField_field_119_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 0.4F, 0.2F, 0.9F);
  private final Vector4f field_120 = new Vector4f(1.0F, 0.0F, 0.0F, 1.0F);
  private final Vector4f field_121 = new Vector4f();
  private final Vector4f field_138 = new Vector4f();
  private float jdField_field_119_of_type_Float = 0.0F;
  private final Transform jdField_field_119_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private final Vector3f jdField_field_119_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.0F, 0.5F);
  private boolean jdField_field_119_of_type_Boolean = true;
  private final ArrayList jdField_field_119_of_type_JavaUtilArrayList = new ArrayList();
  
  public class_356(class_747 paramclass_747)
  {
    this.jdField_field_119_of_type_Class_747 = paramclass_747;
    ((WeaponElementManager)this.jdField_field_119_of_type_Class_747.a112().getWeapon().getElementManager()).addObserver(this);
  }
  
  public final void a3()
  {
    if (this.jdField_field_119_of_type_Class_747 != null) {
      ((WeaponElementManager)this.jdField_field_119_of_type_Class_747.a112().getWeapon().getElementManager()).deleteObserver(this);
    }
  }
  
  public final void b()
  {
    if (this.jdField_field_119_of_type_Boolean) {
      c();
    }
    if (!this.jdField_field_119_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.isEmpty())
    {
      ObjectBidirectionalIterator localObjectBidirectionalIterator = this.jdField_field_119_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.iterator(this.jdField_field_119_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.last());
      while (localObjectBidirectionalIterator.hasPrevious())
      {
        class_287 localclass_2871;
        if ((localclass_2871 = (class_287)localObjectBidirectionalIterator.previous()).a())
        {
          class_287 localclass_2872 = localclass_2871;
          class_356 localclass_356 = this;
          float f = (float)(System.currentTimeMillis() - localclass_2872.jdField_field_655_of_type_Long);
          localclass_356.jdField_field_119_of_type_Float = Math.min(1.0F, 0.7F + f / 200.0F * 0.3F);
          localclass_356.field_121.set(localclass_356.jdField_field_119_of_type_JavaxVecmathVector4f);
          localclass_356.field_138.set(localclass_356.field_120);
          localclass_356.field_121.scale(localclass_356.jdField_field_119_of_type_Float);
          localclass_356.field_138.scale(localclass_356.jdField_field_119_of_type_Float);
          localclass_356.field_138.field_596 = (0.5F - localclass_356.jdField_field_119_of_type_Float / 2.0F);
          localclass_356.field_138.field_598 = localclass_356.jdField_field_119_of_type_Float;
          GlUtil.a42(jdField_field_119_of_type_Class_1377, "thrustColor0", localclass_356.field_121);
          GlUtil.a42(jdField_field_119_of_type_Class_1377, "thrustColor1", localclass_356.field_138);
          GlUtil.a33(jdField_field_119_of_type_Class_1377, "ticks", localclass_2872.jdField_field_655_of_type_Float);
          localclass_2871.a3(this.jdField_field_119_of_type_ComBulletphysicsLinearmathTransform, this.jdField_field_119_of_type_JavaxVecmathVector3f);
          if ((GlUtil.a20(this.jdField_field_119_of_type_ComBulletphysicsLinearmathTransform.origin, class_969.field_1259.a())) && (GlUtil.a18(this.jdField_field_119_of_type_ComBulletphysicsLinearmathTransform, class_218.field_98)))
          {
            GlUtil.d1();
            GlUtil.b3(this.jdField_field_119_of_type_ComBulletphysicsLinearmathTransform);
            ((Mesh)class_218.field_98.a152().get(0)).f();
            GlUtil.c2();
          }
        }
      }
    }
  }
  
  public final class_747 a10()
  {
    return this.jdField_field_119_of_type_Class_747;
  }
  
  public final void c()
  {
    jdField_field_119_of_type_Class_1377 = class_1376.field_1567;
    this.jdField_field_119_of_type_Boolean = false;
  }
  
  public final void a4(class_708 paramclass_708, Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 != null) && ((paramObject1 instanceof ElementCollection)) && ("s".equals(paramObject2)))
    {
      paramObject1 = (ElementCollection)paramObject1;
      paramclass_708 = this;
      paramObject1 = new class_287(paramclass_708.jdField_field_119_of_type_Class_747, paramObject1);
      if (!paramclass_708.jdField_field_119_of_type_JavaUtilArrayList.contains(paramObject1)) {
        paramclass_708.jdField_field_119_of_type_JavaUtilArrayList.add(paramObject1);
      }
    }
  }
  
  public final void a5(class_941 paramclass_941)
  {
    Object localObject2;
    while (!this.jdField_field_119_of_type_JavaUtilArrayList.isEmpty())
    {
      localObject1 = (class_287)this.jdField_field_119_of_type_JavaUtilArrayList.remove(0);
      if (this.jdField_field_119_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.contains(localObject1))
      {
        localObject2 = this.jdField_field_119_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.iterator();
        for (;;)
        {
          if (!((Iterator)localObject2).hasNext()) {
            break label81;
          }
          class_287 localclass_287 = (class_287)((Iterator)localObject2).next();
          if (((class_287)localObject1).equals(localclass_287))
          {
            localclass_287.a1();
            break;
          }
        }
      }
      else
      {
        label81:
        ((class_287)localObject1).a1();
        this.jdField_field_119_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.add(localObject1);
      }
    }
    Object localObject1 = this.jdField_field_119_of_type_ItUnimiDsiFastutilObjectsObjectAVLTreeSet.iterator();
    while (((ObjectBidirectionalIterator)localObject1).hasNext()) {
      if (!(localObject2 = (class_287)((ObjectBidirectionalIterator)localObject1).next()).a()) {
        ((ObjectBidirectionalIterator)localObject1).remove();
      } else {
        ((class_287)localObject2).a2(paramclass_941);
      }
    }
  }
  
  static
  {
    new ArrayList();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_356
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */