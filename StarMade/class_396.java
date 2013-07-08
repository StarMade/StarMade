import java.util.ArrayList;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;

public class class_396
{
  private static final ArrayList jdField_field_789_of_type_JavaUtilArrayList;
  private static boolean jdField_field_789_of_type_Boolean;
  
  public static CubeMeshBufferContainer a()
  {
    synchronized (jdField_field_789_of_type_JavaUtilArrayList)
    {
      while (jdField_field_789_of_type_JavaUtilArrayList.isEmpty()) {
        jdField_field_789_of_type_JavaUtilArrayList.wait();
      }
      return (CubeMeshBufferContainer)jdField_field_789_of_type_JavaUtilArrayList.remove(0);
    }
  }
  
  private static void a1()
  {
    if (!jdField_field_789_of_type_Boolean)
    {
      for (int i = 0; i < 4; i++) {
        jdField_field_789_of_type_JavaUtilArrayList.add(CubeMeshBufferContainer.a1());
      }
      jdField_field_789_of_type_Boolean = true;
    }
  }
  
  public static void a2(CubeMeshBufferContainer paramCubeMeshBufferContainer)
  {
    if ((!field_790) && (jdField_field_789_of_type_JavaUtilArrayList.contains(paramCubeMeshBufferContainer))) {
      throw new AssertionError();
    }
    synchronized (jdField_field_789_of_type_JavaUtilArrayList)
    {
      jdField_field_789_of_type_JavaUtilArrayList.add(paramCubeMeshBufferContainer);
      jdField_field_789_of_type_JavaUtilArrayList.notify();
    }
    if ((!field_790) && (jdField_field_789_of_type_JavaUtilArrayList.size() > 4)) {
      throw new AssertionError();
    }
  }
  
  static
  {
    jdField_field_789_of_type_JavaUtilArrayList = new ArrayList(4);
    a1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_396
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */