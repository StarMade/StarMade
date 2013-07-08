import java.util.ArrayList;
import java.util.HashSet;
import javax.vecmath.Vector3f;
import org.schema.game.client.view.SegmentDrawer;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

final class class_223
  implements class_888
{
  Vector3f jdField_field_105_of_type_JavaxVecmathVector3f = new Vector3f();
  
  private class_223(class_219 paramclass_219) {}
  
  public final boolean handle(Segment paramSegment)
  {
    class_657 localclass_657 = (class_657)paramSegment;
    synchronized (paramSegment.field_36)
    {
      if (((!localclass_657.g()) || (localclass_657.field_36)) && (localclass_657.a1()))
      {
        if ((!jdField_field_105_of_type_Boolean) && (localclass_657.a16().getSegment() != localclass_657)) {
          throw new AssertionError();
        }
        if (SegmentDrawer.a67(paramSegment.a15(), localclass_657, this.jdField_field_105_of_type_JavaxVecmathVector3f, class_219.a1(this.jdField_field_105_of_type_Class_219)))
        {
          this.jdField_field_105_of_type_Class_219.jdField_field_640_of_type_JavaUtilArrayList.add(localclass_657);
          this.jdField_field_105_of_type_Class_219.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.field_108 += 1;
        }
        else if (localclass_657.b6())
        {
          class_219.a(this.jdField_field_105_of_type_Class_219).add(localclass_657);
        }
      }
      else if (localclass_657.g())
      {
        paramSegment = null;
        this.jdField_field_105_of_type_Class_219.jdField_field_640_of_type_OrgSchemaGameClientViewSegmentDrawer.field_98.field_696 += 1L;
      }
      else if (localclass_657.b6())
      {
        class_219.a(this.jdField_field_105_of_type_Class_219).add(localclass_657);
      }
    }
    return !class_933.a1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_223
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */