import java.util.ArrayList;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.client.ClientStateInterface;

public class class_876
{
  private ArrayList jdField_field_1116_of_type_JavaUtilArrayList;
  private StateInterface jdField_field_1116_of_type_OrgSchemaSchineNetworkStateInterface;
  private class_878 jdField_field_1116_of_type_Class_878;
  
  public static void a()
  {
    class_935.a2();
    class_935.a2();
    class_935.a2();
  }
  
  public class_876(StateInterface paramStateInterface)
  {
    this.jdField_field_1116_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    if (class_969.field_1259 == null) {
      (class_969.field_1259 = new class_317()).a1();
    }
    this.jdField_field_1116_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_1116_of_type_Class_878 = new class_878();
    this.jdField_field_1116_of_type_Class_878.start();
  }
  
  public final void a1(SegmentData paramSegmentData, boolean paramBoolean)
  {
    synchronized (this.jdField_field_1116_of_type_JavaUtilArrayList)
    {
      if ((!jdField_field_1116_of_type_Boolean) && (paramSegmentData == null)) {
        throw new AssertionError();
      }
      if (paramBoolean) {
        paramSegmentData.resetFast();
      } else {
        paramSegmentData.reset();
      }
      if (paramSegmentData.getSegment() != null) {
        paramSegmentData.getSegment().a22(null);
      }
      paramSegmentData.setSegment(null);
      if ((!jdField_field_1116_of_type_Boolean) && (this.jdField_field_1116_of_type_JavaUtilArrayList.contains(paramSegmentData))) {
        throw new AssertionError();
      }
      this.jdField_field_1116_of_type_JavaUtilArrayList.add(paramSegmentData);
      return;
    }
  }
  
  public final SegmentData a2()
  {
    synchronized (this.jdField_field_1116_of_type_JavaUtilArrayList)
    {
      if (!this.jdField_field_1116_of_type_JavaUtilArrayList.isEmpty())
      {
        SegmentData localSegmentData = (SegmentData)this.jdField_field_1116_of_type_JavaUtilArrayList.remove(0);
        this.jdField_field_1116_of_type_JavaUtilArrayList.notify();
        if ((!jdField_field_1116_of_type_Boolean) && (localSegmentData == null)) {
          throw new AssertionError();
        }
        if ((!jdField_field_1116_of_type_Boolean) && (localSegmentData.getSize() != 0)) {
          throw new AssertionError();
        }
        return localSegmentData;
      }
    }
    return new SegmentData(this.jdField_field_1116_of_type_OrgSchemaSchineNetworkStateInterface instanceof ClientStateInterface);
  }
  
  public final int a3()
  {
    return this.jdField_field_1116_of_type_JavaUtilArrayList.size();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_876
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */