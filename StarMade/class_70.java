import java.io.PrintStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class class_70
{
  private static Pattern jdField_field_536_of_type_JavaUtilRegexPattern = Pattern.compile("(.*)-([0-9]+x[0-9]+)(.*)");
  private final HashMap jdField_field_536_of_type_JavaUtilHashMap;
  
  public class_70()
  {
    new HashMap();
    this.jdField_field_536_of_type_JavaUtilHashMap = new HashMap();
  }
  
  public final HashMap a()
  {
    return this.jdField_field_536_of_type_JavaUtilHashMap;
  }
  
  public final void a1(String paramString1, String paramString2)
  {
    long l1 = System.currentTimeMillis();
    class_1395 localclass_1395 = class_969.a3().a6(paramString1, !paramString2.contains("-gui-"));
    class_1380 localclass_1380;
    (localclass_1380 = new class_1380(localclass_1395)).c11(paramString2);
    localclass_1380.d10(paramString2.contains("-c-"));
    Matcher localMatcher;
    if ((localMatcher = jdField_field_536_of_type_JavaUtilRegexPattern.matcher(paramString1)).matches())
    {
      String[] arrayOfString;
      int k = Integer.parseInt((arrayOfString = localMatcher.group(2).split("x"))[0]);
      int i = Integer.parseInt(arrayOfString[1]);
      int j = k * i;
      System.err.println("MULTITEXTURE: " + k + "x" + i + " : " + j);
      localclass_1380.a167(k, i);
      localclass_1380.c4(localclass_1395.field_1591 / k);
      localclass_1380.a72(localclass_1395.field_1590 / i);
    }
    localclass_1380.c();
    this.jdField_field_536_of_type_JavaUtilHashMap.put(paramString2, localclass_1380);
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 300L) {
      System.err.println("[WARNING] initializing Texture " + paramString1 + " took " + l2 + " ms");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_70
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */