import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.physics.PairCachingGhostObjectAlignable;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public final class class_714
{
  private static class_712 jdField_field_979_of_type_Class_712 = new class_712();
  private final ArrayList jdField_field_979_of_type_JavaUtilArrayList;
  private final ArrayList jdField_field_980_of_type_JavaUtilArrayList;
  private class_858 jdField_field_979_of_type_Class_858;
  private Vector3f jdField_field_979_of_type_JavaxVecmathVector3f;
  private Vector3f jdField_field_980_of_type_JavaxVecmathVector3f;
  private Vector3f jdField_field_981_of_type_JavaxVecmathVector3f;
  private Quat4f jdField_field_979_of_type_JavaxVecmathQuat4f;
  private Quat4f jdField_field_980_of_type_JavaxVecmathQuat4f;
  private Quat4f jdField_field_981_of_type_JavaxVecmathQuat4f;
  private Matrix3f jdField_field_979_of_type_JavaxVecmathMatrix3f;
  private Transform jdField_field_979_of_type_ComBulletphysicsLinearmathTransform;
  
  public class_714(class_858 paramclass_858)
  {
    new Transform();
    new Transform();
    this.jdField_field_979_of_type_JavaUtilArrayList = new ArrayList(8);
    this.jdField_field_980_of_type_JavaUtilArrayList = new ArrayList(8);
    new Matrix4f();
    new Matrix4f();
    new Matrix4f();
    new Transform();
    this.jdField_field_979_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_980_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_981_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_979_of_type_JavaxVecmathQuat4f = new Quat4f();
    this.jdField_field_980_of_type_JavaxVecmathQuat4f = new Quat4f();
    this.jdField_field_981_of_type_JavaxVecmathQuat4f = new Quat4f();
    new Matrix3f();
    this.jdField_field_979_of_type_JavaxVecmathMatrix3f = new Matrix3f();
    this.jdField_field_979_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    new Vector3f();
    this.jdField_field_979_of_type_Class_858 = paramclass_858;
  }
  
  public final void a(Transform paramTransform)
  {
    b(paramTransform);
  }
  
  public final int a1(Transform paramTransform)
  {
    this.jdField_field_979_of_type_Class_858.getState().getServerTimeDifference();
    Transform localTransform = paramTransform;
    if (this.jdField_field_979_of_type_Class_858.a6().toString().contains("[CSS-Temp2_1373101622407]")) {
      System.err.println(this.jdField_field_979_of_type_Class_858.getState() + " TS FROM " + this.jdField_field_979_of_type_Class_858.getWorldTransform().origin + " TO " + paramTransform.origin + " ");
    }
    if (!this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().isInitialized()) {
      return 1;
    }
    if (a4())
    {
      paramTransform = (PairCachingGhostObjectAlignable)this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject();
      this.jdField_field_979_of_type_JavaxVecmathVector3f.set(paramTransform.localWorldTransform.origin);
      class_29.a5(paramTransform.localWorldTransform.basis, this.jdField_field_980_of_type_JavaxVecmathQuat4f);
      this.jdField_field_979_of_type_JavaxVecmathMatrix3f.set(paramTransform.localWorldTransform.basis);
    }
    else
    {
      this.jdField_field_979_of_type_JavaxVecmathVector3f.set(this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getCurrentPhysicsTransform().origin);
      class_29.a5(this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getCurrentPhysicsTransform().basis, this.jdField_field_980_of_type_JavaxVecmathQuat4f);
      this.jdField_field_979_of_type_JavaxVecmathMatrix3f.set(this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getCurrentPhysicsTransform().basis);
    }
    this.jdField_field_980_of_type_JavaxVecmathVector3f.set(localTransform.origin);
    class_29.a5(localTransform.basis, this.jdField_field_981_of_type_JavaxVecmathQuat4f);
    if (localTransform.basis.determinant() != 1.0F) {
      return 3;
    }
    VectorUtil.setInterpolate3(this.jdField_field_981_of_type_JavaxVecmathVector3f, this.jdField_field_979_of_type_JavaxVecmathVector3f, this.jdField_field_980_of_type_JavaxVecmathVector3f, 0.55F);
    class_29.a4(this.jdField_field_980_of_type_JavaxVecmathQuat4f, this.jdField_field_981_of_type_JavaxVecmathQuat4f, 0.55F, this.jdField_field_979_of_type_JavaxVecmathQuat4f);
    this.jdField_field_979_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_979_of_type_ComBulletphysicsLinearmathTransform.origin.set(this.jdField_field_981_of_type_JavaxVecmathVector3f);
    this.jdField_field_979_of_type_ComBulletphysicsLinearmathTransform.basis.set(this.jdField_field_979_of_type_JavaxVecmathQuat4f);
    b(this.jdField_field_979_of_type_ComBulletphysicsLinearmathTransform);
    return 3;
  }
  
  private static void a2(class_718 paramclass_718)
  {
    class_718 localclass_718 = paramclass_718;
    synchronized ((paramclass_718 = jdField_field_979_of_type_Class_712).field_977)
    {
      if (paramclass_718.field_977.size() + 1 > 13000)
      {
        localclass_718.jdField_field_987_of_type_ComBulletphysicsLinearmathTransform = null;
        localclass_718.jdField_field_987_of_type_Long = -1L;
      }
      else
      {
        paramclass_718.field_977.add(localclass_718);
      }
      return;
    }
  }
  
  public final void a3()
  {
    while (!this.jdField_field_980_of_type_JavaUtilArrayList.isEmpty()) {
      a2((class_718)this.jdField_field_980_of_type_JavaUtilArrayList.remove(this.jdField_field_980_of_type_JavaUtilArrayList.size() - 1));
    }
    while (!this.jdField_field_979_of_type_JavaUtilArrayList.isEmpty()) {
      a2((class_718)this.jdField_field_979_of_type_JavaUtilArrayList.remove(this.jdField_field_979_of_type_JavaUtilArrayList.size() - 1));
    }
  }
  
  private boolean a4()
  {
    return (this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject() != null) && ((this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject() instanceof PairCachingGhostObjectAlignable)) && (((PairCachingGhostObjectAlignable)this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject()).getAttached() != null);
  }
  
  private void b(Transform paramTransform)
  {
    if (this.jdField_field_979_of_type_Class_858.a6().toString().contains("[CSS-Temp2_1373101622407]")) {
      System.err.println(this.jdField_field_979_of_type_Class_858.getState() + " TS TO " + paramTransform.origin);
    }
    if ((this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject() != null) && (this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().isInitialized()))
    {
      if (a4())
      {
        ((PairCachingGhostObjectAlignable)this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject()).localWorldTransform.set(paramTransform);
        return;
      }
      Transform localTransform = paramTransform;
      class_714 localclass_714 = this;
      class_797 localclass_797;
      localclass_714.jdField_field_979_of_type_Class_858.getWorldTransform().set(localTransform);
      if (localclass_714.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject() != null) {
        localclass_714.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject().setWorldTransform(localTransform);
      }
      if ((((this.jdField_field_979_of_type_Class_858.a6() instanceof class_797)) && (!(localclass_797 = (class_797)localclass_714.jdField_field_979_of_type_Class_858.a6()).isOnServer()) && (localclass_797.getSectorId() != ((class_371)localclass_797.getState()).a8()) ? 1 : 0) == 0)
      {
        this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject().setWorldTransform(paramTransform);
        this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getObject().setInterpolationWorldTransform(paramTransform);
        this.jdField_field_979_of_type_Class_858.getPhysicsDataContainer().getCurrentPhysicsTransform().set(paramTransform);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_714
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */