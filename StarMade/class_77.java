import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class class_77
{
  private Map jdField_field_546_of_type_JavaUtilMap = new HashMap();
  private List jdField_field_546_of_type_JavaUtilList = new ArrayList();
  private int jdField_field_546_of_type_Int = 0;
  public boolean field_546;
  
  public class_77()
  {
    this.jdField_field_546_of_type_Boolean = true;
  }
  
  public final class_61 a(String paramString, File paramFile)
  {
    try
    {
      localObject = paramFile.getName();
      if (paramString.indexOf(".") > 0) {
        paramString = paramString.substring(0, paramString.indexOf("."));
      }
      if (this.jdField_field_546_of_type_Boolean) {
        while (Character.isDigit(paramString.charAt(paramString.length() - 1))) {
          paramString = paramString.substring(0, paramString.length() - 1);
        }
      }
      paramString = paramString.replaceAll("/", ".");
      if (!this.jdField_field_546_of_type_JavaUtilMap.containsKey(paramString)) {
        this.jdField_field_546_of_type_JavaUtilMap.put(paramString, new ArrayList());
      }
      paramFile = new class_61((String)localObject, paramFile.toURI().toURL());
      ((ArrayList)this.jdField_field_546_of_type_JavaUtilMap.get(paramString)).add(paramFile);
      this.jdField_field_546_of_type_JavaUtilList.add(paramFile);
      this.jdField_field_546_of_type_Int += 1;
      return paramFile;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      Object localObject;
      (localObject = localMalformedURLException).printStackTrace();
      throw new RuntimeException((Throwable)localObject);
    }
  }
  
  public final class_61 a1(String paramString)
  {
    if ((paramString = (List)this.jdField_field_546_of_type_JavaUtilMap.get(paramString)) == null) {
      return null;
    }
    return (class_61)paramString.get(0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_77
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */