import com.bulletphysics.linearmath.Transform;
import java.util.HashSet;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;

public final class class_1230
  extends class_1254
{
  private long jdField_field_128_of_type_Long = 0L;
  private final Vector3f jdField_field_128_of_type_JavaxVecmathVector3f = new Vector3f();
  private static final long serialVersionUID = 1L;
  
  public class_1230(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  public final Vector3f a4()
  {
    return this.jdField_field_128_of_type_JavaxVecmathVector3f;
  }
  
  public final boolean c()
  {
    this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
    this.jdField_field_128_of_type_Long = System.currentTimeMillis();
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    if (System.currentTimeMillis() - this.jdField_field_128_of_type_Long > 30000L) {
      a12(new class_1115());
    }
    if (((class_1260)a6()).a25().size() > 0)
    {
      ((class_1256)a6().field_223).a8(null);
      Vector3f localVector3f1 = new Vector3f();
      Vector3f localVector3f2 = new Vector3f();
      int i = 0;
      Iterator localIterator = ((class_1260)a6()).a25().iterator();
      while (localIterator.hasNext())
      {
        SegmentController localSegmentController = (SegmentController)localIterator.next();
        Vector3f localVector3f3 = ((class_747)a7()).getWorldTransform().origin;
        Vector3f localVector3f4 = localSegmentController.getWorldTransform().origin;
        localVector3f2.sub(localVector3f3, localVector3f4);
        localVector3f1.add(localVector3f2);
        i = (i != 0) || (((class_747)a7()).overlapsAABB(localSegmentController, 30.0F)) ? 1 : 0;
      }
      localVector3f1.scale(1000.0F);
      this.jdField_field_128_of_type_JavaxVecmathVector3f.set(localVector3f1);
      if (i == 0)
      {
        ((class_1260)a6()).a25().clear();
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
 * Qualified Name:     class_1230
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */