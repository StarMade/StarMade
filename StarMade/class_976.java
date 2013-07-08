import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.schema.schine.graphicsengine.core.settings.typegetter.TypeNotKnowException;

public abstract class class_976
{
  private static Map field_1261 = new HashMap();
  
  public static class_976 a1(Class paramClass)
  {
    if (field_1261.containsKey(paramClass)) {
      return (class_976)field_1261.get(paramClass);
    }
    if (paramClass.equals(Float.class)) {
      field_1261.put(paramClass, new class_1020());
    }
    if (paramClass.equals(String.class)) {
      field_1261.put(paramClass, new class_974());
    }
    if (paramClass.equals(Integer.class)) {
      field_1261.put(paramClass, new class_1026());
    }
    if (paramClass.equals(Short.class)) {
      field_1261.put(paramClass, new class_1024());
    }
    if (paramClass.equals(Boolean.class)) {
      field_1261.put(paramClass, new class_1022());
    }
    if (field_1261.containsKey(paramClass)) {
      return (class_976)field_1261.get(paramClass);
    }
    throw new TypeNotKnowException(paramClass.toString());
  }
  
  public abstract Object a(String paramString);
  
  public String toString()
  {
    return Arrays.toString(getClass().getTypeParameters());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_976
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */