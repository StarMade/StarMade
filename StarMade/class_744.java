import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.game.common.updater.FileUtil;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.client.ClientToServerConnection;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteLong;
import org.schema.schine.network.objects.remote.RemoteString;

public class class_744
{
  private String jdField_field_1011_of_type_JavaLangString = "";
  private int jdField_field_1012_of_type_Int;
  private final class_748 jdField_field_1012_of_type_Class_748;
  public String field_1012;
  private ArrayList jdField_field_1012_of_type_JavaUtilArrayList;
  private ArrayList jdField_field_1011_of_type_JavaUtilArrayList;
  private boolean jdField_field_1012_of_type_Boolean;
  
  public class_744(class_748 paramclass_748)
  {
    new ArrayList();
    this.jdField_field_1012_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_1011_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_1012_of_type_Class_748 = paramclass_748;
    if (paramclass_748.isOnServer())
    {
      this.jdField_field_1012_of_type_JavaLangString = null;
      new File("./server-skins/").mkdirs();
    }
  }
  
  public final void a()
  {
    if (this.jdField_field_1012_of_type_JavaUtilArrayList.size() > 0) {
      synchronized (this.jdField_field_1012_of_type_JavaUtilArrayList)
      {
        while (this.jdField_field_1012_of_type_JavaUtilArrayList.size() > 0)
        {
          String str = (String)this.jdField_field_1012_of_type_JavaUtilArrayList.remove(0);
          if (new File(str).exists()) {
            System.err.println("[SERVER][SkinManager] " + this.jdField_field_1012_of_type_Class_748 + " requested: " + str);
          }
        }
      }
    }
    if (!this.jdField_field_1011_of_type_JavaUtilArrayList.isEmpty()) {
      synchronized (this.jdField_field_1011_of_type_JavaUtilArrayList)
      {
        try
        {
          localFile1 = (File)this.jdField_field_1011_of_type_JavaUtilArrayList.get(this.jdField_field_1011_of_type_JavaUtilArrayList.size() - 1);
          if ((!jdField_field_1011_of_type_Boolean) && (!localFile1.exists())) {
            throw new AssertionError(localFile1.getAbsolutePath());
          }
          FileUtil.a3(localFile1, "./tmp" + this.jdField_field_1012_of_type_Class_748.getName() + "/");
          localFile1.delete();
          File localFile2 = new File("./tmp" + this.jdField_field_1012_of_type_Class_748.getName() + "/tmpUpload/").listFiles()[0];
          if ((localFile1 = new File("./server-skins//" + this.jdField_field_1012_of_type_Class_748.getName() + ".png")).exists()) {
            localFile1.delete();
          }
          localFile1 = new File("./server-skins//" + this.jdField_field_1012_of_type_Class_748.getName() + ".png");
          FileUtil.b(localFile2, localFile1);
          localFile1.setLastModified(System.currentTimeMillis());
          this.jdField_field_1011_of_type_JavaLangString = localFile2.getName();
          class_1182.a(new File("./tmp" + this.jdField_field_1012_of_type_Class_748.getName() + "/"));
          this.jdField_field_1012_of_type_Class_748.a127().textureChangedBroadcastBuffer.add(new RemoteLong(Long.valueOf(localFile1.lastModified()), this.jdField_field_1012_of_type_Class_748.a127()));
        }
        catch (IOException localIOException)
        {
          File localFile1 = null;
          localIOException.printStackTrace();
        }
        this.jdField_field_1011_of_type_JavaUtilArrayList.clear();
        return;
      }
    }
  }
  
  public final void b()
  {
    class_744 localclass_744;
    if ((this.jdField_field_1012_of_type_JavaLangString == null) && (!this.jdField_field_1012_of_type_Class_748.isOnServer()))
    {
      this.jdField_field_1012_of_type_JavaLangString = ("./client-skins/" + ((class_371)this.jdField_field_1012_of_type_Class_748.getState()).a20().getName() + "/" + ((class_371)this.jdField_field_1012_of_type_Class_748.getState()).a4().getConnection().getHost() + "/");
      localclass_744 = null;
      new File(this.jdField_field_1012_of_type_JavaLangString).mkdirs();
    }
    if (this.jdField_field_1012_of_type_Boolean)
    {
      try
      {
        localclass_744 = this;
        if (!class_52.field_37) {
          if (!localclass_744.jdField_field_1011_of_type_JavaLangString.equals("defaultMale"))
          {
            if (localclass_744.jdField_field_1012_of_type_Class_748.a7())
            {
              if (!new File(localclass_744.jdField_field_1011_of_type_JavaLangString).exists()) {
                throw new RuntimeException("Can't find skin image: " + localclass_744.jdField_field_1011_of_type_JavaLangString + "\n Please change your skin option to a valid file");
              }
              System.err.println("[SKIN] OWN REFRESH LOADING " + localclass_744.jdField_field_1011_of_type_JavaLangString + " AS " + localclass_744.jdField_field_1012_of_type_Class_748.getName());
              class_969.a2().a1().a1(localclass_744.jdField_field_1011_of_type_JavaLangString, localclass_744.jdField_field_1012_of_type_Class_748.getName());
              localclass_744.jdField_field_1012_of_type_Int = class_969.a2().a5(localclass_744.jdField_field_1012_of_type_Class_748.getName()).a153().a1().field_1592;
            }
            else
            {
              System.err.println("[SKIN] REFRESH LOADING " + localclass_744.jdField_field_1012_of_type_JavaLangString + localclass_744.jdField_field_1012_of_type_Class_748.getName() + ".png AS " + localclass_744.jdField_field_1012_of_type_Class_748.getName());
              class_969.a2().a1().a1(localclass_744.jdField_field_1012_of_type_JavaLangString + localclass_744.jdField_field_1012_of_type_Class_748.getName() + ".png", localclass_744.jdField_field_1012_of_type_Class_748.getName());
              localclass_744.jdField_field_1012_of_type_Int = class_969.a2().a5(localclass_744.jdField_field_1012_of_type_Class_748.getName()).a153().a1().field_1592;
            }
          }
          else
          {
            System.err.println("[SKIN] USING DEFAULT FOR PLAYER " + localclass_744.jdField_field_1012_of_type_Class_748);
            localclass_744.jdField_field_1012_of_type_Int = class_969.a2().a5("defaultMale").a153().a1().field_1592;
          }
        }
      }
      catch (IOException localIOException)
      {
        localclass_744 = null;
        localIOException.printStackTrace();
      }
      this.jdField_field_1012_of_type_Boolean = false;
    }
  }
  
  public final void c()
  {
    if (!this.jdField_field_1012_of_type_Class_748.a7())
    {
      System.err.println("[SKINMANAGER] " + this.jdField_field_1012_of_type_Class_748 + " " + this.jdField_field_1012_of_type_Class_748.getState() + " received skin filename " + (String)this.jdField_field_1012_of_type_Class_748.a127().skinName.get());
      this.jdField_field_1011_of_type_JavaLangString = ((String)this.jdField_field_1012_of_type_Class_748.a127().skinName.get());
    }
  }
  
  public final void d()
  {
    if ((this.jdField_field_1012_of_type_Class_748.isOnServer()) || (!this.jdField_field_1012_of_type_Class_748.a7())) {
      this.jdField_field_1011_of_type_JavaLangString = ((String)this.jdField_field_1012_of_type_Class_748.a127().skinName.get());
    }
    class_744 localclass_744 = this;
    for (int i = 0; i < localclass_744.jdField_field_1012_of_type_Class_748.a127().skinRequestBuffer.getReceiveBuffer().size(); i++)
    {
      ((RemoteString)localclass_744.jdField_field_1012_of_type_Class_748.a127().skinRequestBuffer.getReceiveBuffer().get(i)).get();
      synchronized (localclass_744.jdField_field_1012_of_type_JavaUtilArrayList)
      {
        localclass_744.jdField_field_1012_of_type_JavaUtilArrayList.add("./server-skins/" + localclass_744.jdField_field_1012_of_type_Class_748.getName() + ".png");
      }
    }
  }
  
  public final void e()
  {
    if (this.jdField_field_1012_of_type_Class_748.isOnServer())
    {
      this.jdField_field_1012_of_type_Class_748.a127().skinName.set(this.jdField_field_1011_of_type_JavaLangString);
      return;
    }
    if (this.jdField_field_1012_of_type_Class_748.a7())
    {
      this.jdField_field_1012_of_type_Class_748.a127().skinName.forceClientUpdates();
      this.jdField_field_1012_of_type_Class_748.a127().skinName.set(this.jdField_field_1011_of_type_JavaLangString);
    }
  }
  
  public final void a1(File paramFile)
  {
    synchronized (this.jdField_field_1011_of_type_JavaUtilArrayList)
    {
      this.jdField_field_1011_of_type_JavaUtilArrayList.add(paramFile);
      return;
    }
  }
  
  public final String a2()
  {
    return this.jdField_field_1011_of_type_JavaLangString;
  }
  
  public final void a3(String paramString)
  {
    this.jdField_field_1011_of_type_JavaLangString = paramString;
  }
  
  public final void b1(String paramString)
  {
    this.jdField_field_1011_of_type_JavaLangString = paramString;
    File localFile;
    if ((localFile = new File(this.jdField_field_1012_of_type_JavaLangString + this.jdField_field_1012_of_type_Class_748.getName() + ".png")).exists()) {
      localFile.delete();
    }
    FileUtil.b(paramString = new File(paramString), localFile);
    localFile.setLastModified(paramString.lastModified());
    this.jdField_field_1012_of_type_Class_748.a139().a4(this.jdField_field_1011_of_type_JavaLangString);
  }
  
  public static long a4()
  {
    return -1L;
  }
  
  public final void f()
  {
    this.jdField_field_1012_of_type_Boolean = true;
  }
  
  public final int a5()
  {
    if (this.jdField_field_1012_of_type_Int == 0) {
      this.jdField_field_1012_of_type_Int = class_969.a2().a5("defaultMale").a153().a1().field_1592;
    }
    return this.jdField_field_1012_of_type_Int;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_744
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */