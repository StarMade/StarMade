import java.util.HashMap;

public final class class_40
{
  public static HashMap field_462 = new HashMap();
  
  public static void a(String paramString)
  {
    field_462.put(paramString, Long.valueOf(System.currentTimeMillis()));
  }
  
  public static void b(String paramString)
  {
    field_462.put(paramString, Long.valueOf(System.currentTimeMillis() - ((Long)field_462.get(paramString)).longValue()));
  }
  
  public static long a1(String paramString)
  {
    if ((paramString = (Long)field_462.get(paramString)) != null) {
      return paramString.longValue();
    }
    return -1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_40
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */