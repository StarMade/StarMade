import java.util.Iterator;
import java.util.LinkedList;

public final class class_55
{
  public String field_522;
  public boolean field_522;
  public class_58 field_522;
  public LinkedList field_522;
  public class_55 field_522;
  
  public static class_55 a(String paramString, class_55 paramclass_551, class_55 paramclass_552)
  {
    if (paramString.equals(paramclass_551.jdField_field_522_of_type_JavaLangString)) {
      return paramclass_551;
    }
    Iterator localIterator = paramclass_551.jdField_field_522_of_type_JavaUtilLinkedList.iterator();
    while (localIterator.hasNext())
    {
      paramclass_552 = (class_55)localIterator.next();
      if ((paramclass_552 = a(paramString, paramclass_552, paramclass_551)).jdField_field_522_of_type_JavaLangString.equals(paramString)) {
        return paramclass_552;
      }
    }
    return paramclass_552;
  }
  
  public class_55()
  {
    this.jdField_field_522_of_type_Boolean = false;
    this.jdField_field_522_of_type_JavaUtilLinkedList = new LinkedList();
  }
  
  public final String toString()
  {
    String str = "";
    str = str + "<" + this.jdField_field_522_of_type_JavaLangString + ">\n";
    Iterator localIterator = this.jdField_field_522_of_type_JavaUtilLinkedList.iterator();
    while (localIterator.hasNext())
    {
      class_55 localclass_55 = (class_55)localIterator.next();
      str = str + localclass_55.toString();
    }
    return str + "</" + this.jdField_field_522_of_type_JavaLangString + ">\n";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_55
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */