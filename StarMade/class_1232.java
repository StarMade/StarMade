import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Set;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.world.Universe;

public final class class_1232
  extends class_1254
{
  private Vector3f jdField_field_128_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_162 = new Vector3f();
  private boolean jdField_field_128_of_type_Boolean = true;
  private static final long serialVersionUID = 1L;
  
  public class_1232(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  public final Vector3f a4()
  {
    return this.jdField_field_128_of_type_JavaxVecmathVector3f;
  }
  
  public final boolean c()
  {
    double d;
    if ((d = Math.random()) > 0.9D) {
      this.field_162.set(0.0F, 1.0F, 0.0F);
    } else if (d > 0.7D) {
      this.field_162.set(0.0F, -1.0F, 0.0F);
    } else if (d > 0.6D) {
      this.field_162.set(1.0F, 0.0F, 0.0F);
    } else if (d > 0.5D) {
      this.field_162.set(0.0F, 0.0F, 1.0F);
    } else if (d > 0.4D) {
      this.field_162.set(0.0F, 0.0F, -1.0F);
    } else if (d > 0.3D) {
      this.field_162.set(-1.0F, 0.0F, 0.0F);
    }
    this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    Object localObject3 = null;
    Object localObject1;
    if ((localObject1 = ((class_1256)a6().field_223).a7()) != null)
    {
      ((class_797)localObject1).calcWorldTransformRelative(((class_797)a7()).getSectorId(), ((class_1041)((class_797)a7()).getState()).a62().getSector(((class_797)a7()).getSectorId()).field_136);
      localObject3 = localObject1;
      Object localObject2 = this;
      ((class_1232)localObject2).a12(new class_1115());
      System.err.println("[AI] Hidden Entity. Getting new Target");
      ((class_1232)localObject2).a12(new class_1115());
      System.err.println("[AI] Dead Entity. Getting new Target");
      Vector3f localVector3f;
      (localVector3f = new Vector3f()).sub(((class_797)((class_1232)localObject2).a7()).getWorldTransform().origin, localObject3.getClientTransform().origin);
      ((class_1232)localObject2).a12(new class_1115());
      System.err.println("[AI] Entity in shop distance. searching new entity");
      ((class_1256)((class_1232)localObject2).a6().field_223).a8(null);
      if (((((class_797)((class_1232)localObject2).a7()).getFactionId() == -1) && ((localObject3 instanceof class_365)) && ((localObject3 instanceof class_739)) && (!((class_365)localObject3).a26().isEmpty()) && (!((class_739)localObject3).a().isEmpty()) && (((class_1256)((class_1232)localObject2).a6().field_223).a11() < 0) ? 0 : ((localObject3 instanceof class_747)) && (((class_747)localObject3).c3()) && (localVector3f.length() > 375.0F) ? 0 : ((localObject3 instanceof class_747)) && (((class_747)localObject3).a7()) ? 0 : localVector3f.length() > 750.0F ? 0 : ((localObject3 instanceof class_365)) && (((class_365)localObject3).a26().isEmpty()) && (!((class_991)localObject3).a().a1()) ? 0 : localObject3.isHidden() ? 0 : localObject3.isHidden() ? 0 : 1) != 0)
      {
        localObject2 = ((class_797)a7()).getWorldTransform().origin;
        localObject1 = new Vector3f(((class_797)localObject1).getClientTransform().origin);
        this.jdField_field_128_of_type_JavaxVecmathVector3f.sub((Tuple3f)localObject1, (Tuple3f)localObject2);
        if (this.jdField_field_128_of_type_JavaxVecmathVector3f.length() < a6().b())
        {
          if (this.jdField_field_128_of_type_Boolean) {
            System.currentTimeMillis();
          }
          this.jdField_field_128_of_type_Boolean = false;
          this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
          a12(new class_1208());
        }
        else
        {
          this.jdField_field_128_of_type_Boolean = true;
        }
      }
      else
      {
        a12(new class_1115());
      }
    }
    else
    {
      a12(new class_1115());
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1232
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */