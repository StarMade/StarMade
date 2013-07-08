import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

final class class_1187
  implements Runnable
{
  class_1187(class_1201 paramclass_1201, String[] paramArrayOfString) {}
  
  public final void run()
  {
    try
    {
      localObject1 = "";
      for (int i = 0; i < this.jdField_field_1395_of_type_ArrayOfJavaLangString.length; i++) {
        localObject1 = (String)localObject1 + " " + this.jdField_field_1395_of_type_ArrayOfJavaLangString[i];
      }
      class_1201.a1(this.jdField_field_1395_of_type_Class_1201);
      Object localObject2 = class_1182.a3();
      localObject2 = new File((String)localObject2);
      localObject1 = new String[] { class_1201.a4(), "-Djava.net.preferIPv4Stack=true", "-Xmn" + class_1201.field_1419 + "M", "-Xms" + class_1201.field_1418 + "M", "-Xmx" + class_1201.field_1417 + "M", "-Xincgc", "-jar", ((File)localObject2).getAbsolutePath(), "-server", "-gui", "-port:" + class_1201.field_1420, localObject1 };
      System.err.println("RUNNING COMMAND: " + localObject1);
      (localObject1 = new ProcessBuilder((String[])localObject1)).environment();
      if ((localObject2 = new File("./StarMade/")).exists())
      {
        ((ProcessBuilder)localObject1).directory(((File)localObject2).getAbsoluteFile());
        ((ProcessBuilder)localObject1).start();
        System.exit(0);
        return;
      }
      throw new FileNotFoundException("Cannot find the Install Directory: ./StarMade/");
    }
    catch (IOException localIOException)
    {
      Object localObject1;
      (localObject1 = localIOException).printStackTrace();
      class_29.a12((Exception)localObject1);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1187
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */