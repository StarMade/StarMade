import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.schema.schine.graphicsengine.core.ResourceException;
import org.schema.schine.graphicsengine.forms.Mesh;
import org.schema.schine.graphicsengine.meshimporter.XMLOgreParser;

public final class class_71
{
  private final Map jdField_field_537_of_type_JavaUtilMap = new HashMap();
  private class_39 jdField_field_537_of_type_Class_39;
  private static boolean jdField_field_537_of_type_Boolean = true;
  
  public class_71(class_39 paramclass_39)
  {
    this.jdField_field_537_of_type_Class_39 = paramclass_39;
  }
  
  public final boolean a(String paramString1, String paramString2, String paramString3)
  {
    return b(paramString1, paramString2, paramString3);
  }
  
  private boolean b(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      class_984 localclass_984 = null;
      if (this.jdField_field_537_of_type_JavaUtilMap.containsKey(paramString1)) {
        return true;
      }
      (paramString2 = new XMLOgreParser().a11("data/" + paramString3 + "/", paramString2)).c11(paramString1);
      a1(paramString2, paramString3);
      if (jdField_field_537_of_type_Boolean)
      {
        paramString3 = paramString2.a152().iterator();
        while (paramString3.hasNext()) {
          if (((localclass_984 = (class_984)paramString3.next()) instanceof Mesh)) {
            try
            {
              while (!localclass_984.i1()) {
                Mesh.a174((Mesh)localclass_984);
              }
            }
            catch (Exception localException)
            {
              System.err.println("error in " + paramString1);
              localException.printStackTrace();
            }
          }
        }
      }
      this.jdField_field_537_of_type_JavaUtilMap.put(paramString1, paramString2);
      return true;
    }
    catch (ResourceException localResourceException)
    {
      Object localObject = null;
      localResourceException.printStackTrace();
    }
    return false;
  }
  
  private void a1(Mesh paramMesh, String paramString)
  {
    Object localObject;
    if ((paramMesh.a153().a7()) && (paramMesh.a153().b1() != null)) {
      try
      {
        paramMesh.a153().c2(class_969.a3().a6("data/" + paramString + paramMesh.a153().b1(), true));
        String str = paramMesh.a153().b1().replace(".", "_normal.");
        localObject = paramMesh.a153().b1().replace(".", "_specular.");
        this.jdField_field_537_of_type_Class_39.a2("data/" + paramString + str);
        this.jdField_field_537_of_type_Class_39.a2("data/" + paramString + (String)localObject);
        paramMesh.a153().a14(class_969.a3().a6("data/" + paramString + str, true));
        paramMesh.a153().e();
        paramMesh.a153().b3(class_969.a3().a6("data/" + paramString + (String)localObject, true));
        paramMesh.a153().g();
      }
      catch (ResourceException localResourceException) {}catch (IOException localIOException)
      {
        System.err.println("ERROR LOADING: data/" + paramString + paramMesh.a153().b1());
        localIOException.printStackTrace();
      }
    }
    Iterator localIterator = paramMesh.a152().iterator();
    while (localIterator.hasNext()) {
      if (((localObject = (class_984)localIterator.next()) instanceof Mesh))
      {
        paramMesh = (Mesh)localObject;
        a1(paramMesh, paramString);
      }
    }
  }
  
  public final Map a2()
  {
    return this.jdField_field_537_of_type_JavaUtilMap;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_71
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */