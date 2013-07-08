import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public final class class_1355
{
  public String field_1538;
  public boolean field_1538;
  public Vector field_1538;
  public LinkedList field_1538;
  public class_1355 field_1538;
  public String field_1539;
  
  public class_1355()
  {
    this.jdField_field_1538_of_type_Boolean = false;
    this.jdField_field_1538_of_type_JavaUtilVector = new Vector();
    this.jdField_field_1538_of_type_JavaUtilLinkedList = new LinkedList();
  }
  
  public final String toString()
  {
    String str = "";
    str = str + "<" + this.jdField_field_1538_of_type_JavaLangString + ">\n";
    Iterator localIterator = this.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (localIterator.hasNext())
    {
      class_1355 localclass_1355 = (class_1355)localIterator.next();
      str = str + localclass_1355.toString();
    }
    return str + "</" + this.jdField_field_1538_of_type_JavaLangString + ">\n";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1355
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */