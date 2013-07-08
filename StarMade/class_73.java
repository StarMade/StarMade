import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.schema.schine.graphicsengine.core.ResourceException;
import org.schema.schine.graphicsengine.forms.Mesh;

public class class_73
{
  public static class_39 field_134;
  private class_53 jdField_field_134_of_type_Class_53 = new class_53();
  private final List jdField_field_134_of_type_JavaUtilList = new ArrayList();
  private float jdField_field_134_of_type_Float;
  private LinkedList jdField_field_134_of_type_JavaUtilLinkedList = new LinkedList();
  private int jdField_field_134_of_type_Int = 0;
  private String jdField_field_134_of_type_JavaLangString = "";
  private float jdField_field_135_of_type_Float;
  private class_55 jdField_field_134_of_type_Class_55;
  private String jdField_field_135_of_type_JavaLangString;
  private String field_542;
  private String field_543;
  private boolean jdField_field_134_of_type_Boolean = false;
  public static class_1391 field_135;
  private final class_71 jdField_field_134_of_type_Class_71 = new class_71(jdField_field_134_of_type_Class_39);
  private final class_70 jdField_field_134_of_type_Class_70 = new class_70();
  private final class_1362 jdField_field_134_of_type_Class_1362 = new class_1362();
  
  public final class_70 a1()
  {
    return this.jdField_field_134_of_type_Class_70;
  }
  
  public final LinkedList a2()
  {
    return this.jdField_field_134_of_type_JavaUtilLinkedList;
  }
  
  public final String a3()
  {
    return this.jdField_field_134_of_type_JavaLangString;
  }
  
  public final Mesh a4(String paramString)
  {
    return (Mesh)this.jdField_field_134_of_type_Class_71.a2().get(paramString);
  }
  
  public final class_1380 a5(String paramString)
  {
    class_1380 localclass_1380 = (class_1380)this.jdField_field_134_of_type_Class_70.a().get(paramString);
    if ((!jdField_field_135_of_type_Boolean) && (localclass_1380 == null)) {
      throw new AssertionError("Could not find sprites: " + paramString);
    }
    return localclass_1380;
  }
  
  public final boolean a6()
  {
    return this.jdField_field_134_of_type_Boolean;
  }
  
  public final void a7()
  {
    class_73 localclass_73 = this;
    if (class_53.a() == null) {
      localclass_73.jdField_field_134_of_type_Class_53.a1(class_38.field_461);
    }
    localclass_73.jdField_field_134_of_type_Class_55 = class_53.a();
    try
    {
      localclass_73 = this;
      Object localObject = "data//audio-resource/";
      if (!(localObject = new File((String)localObject)).exists()) {
        ((File)localObject).mkdirs();
      }
      localclass_73.a9((File)localObject);
    }
    catch (ResourceException localResourceException1)
    {
      try
      {
        class_933.a2(localResourceException1);
      }
      catch (Exception localException)
      {
        localException;
      }
      System.err.println("COULD NOT LOAD AUDIO PATH!!!!!!");
      System.err.println("Exiting because  audio path cannot be loaded");
      System.exit(-1);
    }
    c();
    d();
    class_72 localclass_72;
    (localclass_72 = new class_72()).jdField_field_538_of_type_Int = 3;
    localclass_72.field_540 = "FONT";
    localclass_72.jdField_field_538_of_type_JavaLangString = "FONT";
    localclass_72.field_539 = "FONT";
    localclass_72.field_541 = "FONT";
    this.jdField_field_134_of_type_JavaUtilList.add(localclass_72);
    try
    {
      this.jdField_field_134_of_type_JavaUtilList.addAll(a());
    }
    catch (ResourceException localResourceException2) {}
    this.jdField_field_134_of_type_Float = (100.0F / this.jdField_field_134_of_type_JavaUtilList.size());
  }
  
  final void a8(class_72 paramclass_72)
  {
    try
    {
      String str3 = paramclass_72.field_541;
      String str2 = paramclass_72.jdField_field_538_of_type_JavaLangString;
      String str1 = paramclass_72.field_539 + paramclass_72.field_540;
      if (class_949.field_1181.b1())
      {
        System.err.println("LOADING SOUND: " + str1);
        if ((str3.toLowerCase(Locale.ENGLISH).equals("ogg")) || (str3.toLowerCase(Locale.ENGLISH).equals("wav"))) {
          class_969.a().a3(str2, new File(str1));
        }
      }
      this.jdField_field_134_of_type_JavaUtilLinkedList.add(0, paramclass_72.jdField_field_538_of_type_JavaLangString + ": " + paramclass_72.field_539 + paramclass_72.field_540);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      throw new ResourceException(paramclass_72.toString());
    }
  }
  
  private void a9(File paramFile)
  {
    if (paramFile.exists())
    {
      File[] arrayOfFile = paramFile.listFiles();
      for (int i = 0; i < arrayOfFile.length; i++) {
        if (arrayOfFile[i].isDirectory())
        {
          a9(arrayOfFile[i]);
        }
        else
        {
          String str1;
          class_72 localclass_72;
          if (arrayOfFile[i].getName().endsWith(".ogg"))
          {
            int j = (j = (str1 = arrayOfFile[i].getName()).lastIndexOf("/")) < 0 ? str1.lastIndexOf("\\") : j;
            String str2 = str1.substring(j + 1, str1.lastIndexOf(".ogg"));
            (localclass_72 = new class_72()).jdField_field_538_of_type_JavaLangString = str2;
            localclass_72.field_539 = (paramFile.getPath() + File.separator);
            localclass_72.field_540 = str1;
            localclass_72.field_541 = "OGG";
            localclass_72.jdField_field_538_of_type_Int = 2;
            this.jdField_field_134_of_type_JavaUtilList.add(localclass_72);
          }
          else if (arrayOfFile[i].getName().endsWith(".wav"))
          {
            int k = (k = (str1 = arrayOfFile[i].getName()).lastIndexOf("/")) < 0 ? str1.lastIndexOf("\\") : k;
            String str3 = str1.substring(k + 1, str1.lastIndexOf(".wav"));
            (localclass_72 = new class_72()).jdField_field_538_of_type_JavaLangString = str3;
            localclass_72.field_539 = (paramFile.getPath() + File.separator);
            localclass_72.field_540 = str1;
            localclass_72.field_541 = "WAV";
            localclass_72.jdField_field_538_of_type_Int = 2;
            this.jdField_field_134_of_type_JavaUtilList.add(localclass_72);
          }
        }
      }
      return;
    }
    throw new ResourceException("Audiopath not found");
  }
  
  public List a()
  {
    ArrayList localArrayList;
    (localArrayList = new ArrayList()).add(new class_66());
    return localArrayList;
  }
  
  public final void b()
  {
    if (!this.jdField_field_134_of_type_JavaUtilList.isEmpty())
    {
      ((class_72)this.jdField_field_134_of_type_JavaUtilList.remove(0)).a(this);
      this.jdField_field_134_of_type_Int += 1;
      this.jdField_field_135_of_type_Float = (this.jdField_field_134_of_type_Int * this.jdField_field_134_of_type_Float);
      this.jdField_field_134_of_type_JavaLangString = ("...loaded  " + (int)this.jdField_field_135_of_type_Float + "% ");
      return;
    }
    this.jdField_field_134_of_type_Boolean = true;
  }
  
  private void c()
  {
    this.jdField_field_135_of_type_JavaLangString = "box";
    this.field_542 = "/models/ground/";
    this.field_543 = "Box";
    class_55 localclass_551;
    Iterator localIterator = (localclass_551 = class_55.a("GroundObject", this.jdField_field_134_of_type_Class_55, null)).jdField_field_522_of_type_JavaUtilLinkedList.iterator();
    while (localIterator.hasNext())
    {
      class_55 localclass_552;
      String str1 = (String)(localclass_552 = (class_55)localIterator.next()).jdField_field_522_of_type_Class_58.a2("filename").a();
      String str2 = (String)localclass_551.jdField_field_522_of_type_Class_58.a2("path").a();
      class_72 localclass_72;
      (localclass_72 = new class_72()).jdField_field_538_of_type_JavaLangString = localclass_552.jdField_field_522_of_type_JavaLangString;
      localclass_72.field_539 = str2;
      localclass_72.field_540 = str1;
      localclass_72.jdField_field_538_of_type_Int = 1;
      this.jdField_field_134_of_type_JavaUtilList.add(localclass_72);
    }
  }
  
  private void d()
  {
    String str1 = "data//image-resource/";
    Object localObject;
    String str2;
    class_72 localclass_72;
    if ((localObject = new File(str1)).exists())
    {
      localObject = ((File)localObject).list();
      for (int i = 0; i < localObject.length; i++) {
        if (localObject[i].endsWith(".png"))
        {
          int j = (j = (str2 = localObject[i]).lastIndexOf("/")) < 0 ? str2.lastIndexOf("\\") : j;
          String str3 = str2.substring(j + 1, str2.lastIndexOf(".png"));
          (localclass_72 = new class_72()).jdField_field_538_of_type_JavaLangString = str3;
          localclass_72.field_539 = str1;
          localclass_72.field_540 = str2;
          localclass_72.jdField_field_538_of_type_Int = 0;
          this.jdField_field_134_of_type_JavaUtilList.add(localclass_72);
        }
      }
      return;
    }
    Iterator localIterator = (localObject = jdField_field_134_of_type_Class_39.b("data.image-resource")).iterator();
    while (localIterator.hasNext()) {
      if ((str2 = (String)localIterator.next()).endsWith(".png"))
      {
        int k = (k = str2.lastIndexOf("/")) < 0 ? str2.lastIndexOf("\\") : k;
        String str4 = str2.substring(k + 1, str2.lastIndexOf(".png"));
        (localclass_72 = new class_72()).jdField_field_538_of_type_JavaLangString = str4;
        localclass_72.field_539 = str1;
        localclass_72.field_540 = str2;
        localclass_72.jdField_field_538_of_type_Int = 0;
        this.jdField_field_134_of_type_JavaUtilList.add(localclass_72);
      }
    }
    System.err.println("Icons from jar added: " + localObject);
  }
  
  final void b1(class_72 paramclass_72)
  {
    if (!this.jdField_field_134_of_type_Class_71.a(paramclass_72.jdField_field_538_of_type_JavaLangString, paramclass_72.field_540, paramclass_72.field_539))
    {
      if (!this.jdField_field_134_of_type_Class_71.a2().containsKey(this.field_543)) {
        this.jdField_field_134_of_type_Class_71.a(this.field_543, this.jdField_field_135_of_type_JavaLangString, this.field_542);
      }
      this.jdField_field_134_of_type_JavaUtilLinkedList.add(0, "**SKIPPED: " + paramclass_72.jdField_field_538_of_type_JavaLangString + "... added default: " + this.field_543);
      this.jdField_field_134_of_type_Class_71.a2().put(paramclass_72.jdField_field_538_of_type_JavaLangString, this.jdField_field_134_of_type_Class_71.a2().get(this.field_543));
      return;
    }
    this.jdField_field_134_of_type_JavaUtilLinkedList.add(0, paramclass_72.jdField_field_538_of_type_JavaLangString + ": " + paramclass_72.field_539 + paramclass_72.field_540);
  }
  
  final void c1(class_72 paramclass_72)
  {
    this.jdField_field_134_of_type_Class_70.a1(paramclass_72.field_539 + paramclass_72.field_540, paramclass_72.jdField_field_538_of_type_JavaLangString);
    this.jdField_field_134_of_type_JavaUtilLinkedList.add(0, paramclass_72.jdField_field_538_of_type_JavaLangString + ": " + paramclass_72.field_539 + paramclass_72.field_540);
  }
  
  public final void a10(String paramString)
  {
    this.jdField_field_134_of_type_JavaLangString = paramString;
  }
  
  static
  {
    jdField_field_134_of_type_Class_39 = new class_39();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_73
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */