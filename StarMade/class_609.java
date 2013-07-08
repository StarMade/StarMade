import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;

public abstract class class_609
  extends class_597
{
  private Transform jdField_field_172_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  protected class_797 field_173;
  private Vector3f jdField_field_172_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_174 = new Vector3f();
  
  public class_609(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
    this.jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_173_of_type_Float = 15.0F;
  }
  
  public final void a15(class_941 paramclass_941)
  {
    c4(paramclass_941);
    d1(paramclass_941);
    super.a15(paramclass_941);
  }
  
  private void d1(class_941 paramclass_941)
  {
    Object localObject;
    if (this.field_173 != null)
    {
      if (!this.field_172)
      {
        if (((localObject = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(this.jdField_field_173_of_type_Int)) != null) && ((localObject instanceof class_665)))
        {
          localObject = (class_665)localObject;
          this.field_173.calcWorldTransformRelative(((class_665)localObject).getId(), ((class_665)localObject).a34());
          this.field_174.sub(this.field_173.getClientTransform().origin, this.jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.origin);
        }
      }
      else if ((localObject = ((class_1041)getState()).a62().getSector(this.jdField_field_173_of_type_Int)) != null)
      {
        this.field_173.calcWorldTransformRelative(((class_670)localObject).a3(), ((class_670)localObject).field_136);
        this.field_174.sub(this.field_173.getClientTransform().origin, this.jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.origin);
      }
      else
      {
        this.jdField_field_173_of_type_Boolean = false;
      }
      if (this.field_174.lengthSquared() != 0.0F)
      {
        GlUtil.a21(new Vector3f(this.jdField_field_173_of_type_JavaxVecmathVector3f), new Vector3f(this.field_174), this.jdField_field_172_of_type_JavaxVecmathVector3f);
        this.jdField_field_172_of_type_JavaxVecmathVector3f.normalize();
        this.jdField_field_172_of_type_JavaxVecmathVector3f.scale(paramclass_941.a() * 2.07F);
        this.jdField_field_173_of_type_JavaxVecmathVector3f.add(this.jdField_field_172_of_type_JavaxVecmathVector3f);
        this.jdField_field_173_of_type_JavaxVecmathVector3f.normalize();
      }
    }
    (localObject = new Vector3f(this.jdField_field_173_of_type_JavaxVecmathVector3f)).scale(paramclass_941.a() * 25.0F);
    this.jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
    this.jdField_field_172_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject);
    a14(this.jdField_field_172_of_type_ComBulletphysicsLinearmathTransform);
  }
  
  public final void b3(class_941 paramclass_941)
  {
    d1(paramclass_941);
  }
  
  public abstract void c4(class_941 paramclass_941);
  
  public final void c5(int paramInt)
  {
    if (paramInt > 0)
    {
      Sendable localSendable;
      if (((localSendable = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().get(paramInt)) != null) && ((localSendable instanceof class_797)))
      {
        this.field_173 = ((class_797)localSendable);
        return;
      }
      System.err.println("Exception: target is not known: ID: " + paramInt + "; " + getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects());
      return;
    }
    this.field_173 = null;
  }
  
  public final void a20(class_615 paramclass_615)
  {
    super.a20(paramclass_615);
    c5(paramclass_615.field_179);
  }
  
  public final class_797 a23()
  {
    return this.field_173;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_609
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */