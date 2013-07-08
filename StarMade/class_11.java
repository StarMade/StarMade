import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.schema.game.common.data.UploadInProgressException;
import org.schema.game.common.updater.FileUtil;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteStringArray;

public final class class_11
  implements Observer
{
  private class_371 jdField_field_434_of_type_Class_371;
  private final ArrayList jdField_field_434_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_435_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList field_436 = new ArrayList();
  private final ArrayList field_437 = new ArrayList();
  private final Map jdField_field_434_of_type_JavaUtilMap = new HashMap();
  private String jdField_field_434_of_type_JavaLangString = "defaultMale";
  private boolean jdField_field_434_of_type_Boolean = false;
  private boolean jdField_field_435_of_type_Boolean = false;
  private class_10 jdField_field_434_of_type_Class_10;
  
  public class_11(class_371 paramclass_371)
  {
    this.jdField_field_434_of_type_Class_371 = paramclass_371;
    paramclass_371.addObserver(this);
  }
  
  public final void a()
  {
    if (!this.field_436.isEmpty()) {
      synchronized (this.field_436)
      {
        while (!this.field_436.isEmpty())
        {
          class_748 localclass_748 = (class_748)this.field_436.remove(0);
          this.jdField_field_434_of_type_JavaUtilMap.put(localclass_748, new class_10(localclass_748));
          this.jdField_field_434_of_type_Boolean = true;
        }
      }
    }
    if (this.jdField_field_434_of_type_Class_10 == null)
    {
      ??? = this;
      Object localObject3;
      class_10 localclass_10;
      File localFile;
      if (!this.jdField_field_434_of_type_JavaUtilArrayList.isEmpty()) {
        synchronized (((class_11)???).jdField_field_434_of_type_JavaUtilArrayList)
        {
          while (!((class_11)???).jdField_field_434_of_type_JavaUtilArrayList.isEmpty()) {
            if (!(localObject3 = (class_21)((class_11)???).jdField_field_434_of_type_JavaUtilArrayList.remove(0)).jdField_field_439_of_type_Class_748.a7())
            {
              localclass_10 = new class_10(((class_21)localObject3).jdField_field_439_of_type_Class_748);
              ((class_11)???).jdField_field_434_of_type_JavaUtilMap.put(((class_21)localObject3).jdField_field_439_of_type_Class_748, localclass_10);
              if ((localFile = new File(localclass_10.jdField_field_431_of_type_Class_748.a140().field_1012 + localclass_10.jdField_field_431_of_type_Class_748.getName() + ".png")).exists()) {
                localclass_10.jdField_field_431_of_type_Long = localFile.lastModified();
              }
              localclass_10.jdField_field_432_of_type_Long = ((class_21)localObject3).jdField_field_439_of_type_Long;
              ((class_11)???).jdField_field_434_of_type_Boolean = true;
            }
          }
        }
      }
      int i = 1;
      boolean bool;
      if (((class_11)???).jdField_field_434_of_type_Boolean)
      {
        localObject3 = ((class_11)???).jdField_field_434_of_type_JavaUtilMap.values().iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localclass_10 = (class_10)((Iterator)localObject3).next();
          if ((((class_11)???).jdField_field_435_of_type_Boolean) || (localclass_10.jdField_field_431_of_type_Class_748.a7()))
          {
            if (((class_11)???).jdField_field_434_of_type_Class_10 != null) {
              break;
            }
            localFile = null;
            if (!localclass_10.jdField_field_432_of_type_Boolean)
            {
              if (localclass_10.jdField_field_432_of_type_Long < 0L)
              {
                if (!localclass_10.jdField_field_431_of_type_Boolean)
                {
                  if ((localFile = new File(((class_11)???).jdField_field_434_of_type_Class_371.a20().a140().field_1012 + localclass_10.jdField_field_431_of_type_Class_748.getName() + ".png")).exists()) {
                    localclass_10.jdField_field_431_of_type_Long = localFile.lastModified();
                  }
                  ((class_11)???).jdField_field_434_of_type_Class_371.a4().a29().b(localclass_10.jdField_field_431_of_type_Class_748.getName());
                  localclass_10.jdField_field_431_of_type_Boolean = true;
                }
                else
                {
                  synchronized (((class_11)???).field_437)
                  {
                    for (int j = 0; j < ((class_11)???).field_437.size(); j++)
                    {
                      String[] arrayOfString;
                      if ((arrayOfString = (String[])((class_11)???).field_437.get(j))[0].equals(localclass_10.jdField_field_431_of_type_Class_748.getName())) {
                        localclass_10.jdField_field_432_of_type_Long = Long.parseLong(arrayOfString[1]);
                      }
                    }
                  }
                }
              }
              else if (localclass_10.jdField_field_431_of_type_Class_748.a7())
              {
                if (!localObject1.jdField_field_434_of_type_JavaLangString.equals("defaultMale"))
                {
                  ??? = new File(localObject1.jdField_field_434_of_type_JavaLangString);
                  localclass_10.jdField_field_431_of_type_Long = ((File)???).lastModified();
                }
                if (localclass_10.jdField_field_431_of_type_Long > localclass_10.jdField_field_432_of_type_Long)
                {
                  if (localObject1.jdField_field_434_of_type_JavaLangString.equals("defaultMale")) {
                    localObject1.jdField_field_434_of_type_Class_371.a20().a140().a3(localObject1.jdField_field_434_of_type_JavaLangString);
                  } else {
                    try
                    {
                      new File(localObject1.jdField_field_434_of_type_JavaLangString).setLastModified(System.currentTimeMillis());
                      localObject1.jdField_field_434_of_type_Class_371.a20().a140().b1(localObject1.jdField_field_434_of_type_JavaLangString);
                    }
                    catch (IOException localIOException)
                    {
                      localIOException;
                    }
                    catch (UploadInProgressException localUploadInProgressException)
                    {
                      localUploadInProgressException;
                    }
                  }
                  localclass_10.jdField_field_432_of_type_Boolean = true;
                  localclass_10.jdField_field_431_of_type_Class_748.a140().f();
                }
                else
                {
                  localclass_10.jdField_field_432_of_type_Boolean = true;
                  localclass_10.jdField_field_431_of_type_Class_748.a140().a3(localObject1.jdField_field_434_of_type_JavaLangString);
                  localclass_10.jdField_field_431_of_type_Class_748.a140().f();
                }
                localObject1.jdField_field_435_of_type_Boolean = true;
              }
              else if (localclass_10.jdField_field_431_of_type_Class_748.a140().a2().length() != 0)
              {
                if ((!localclass_10.jdField_field_431_of_type_Class_748.a140().a2().equals("defaultMale")) && (localclass_10.jdField_field_431_of_type_Long < localclass_10.jdField_field_432_of_type_Long))
                {
                  localObject1.jdField_field_434_of_type_Class_10 = localclass_10;
                }
                else
                {
                  localclass_10.jdField_field_432_of_type_Boolean = true;
                  localclass_10.jdField_field_431_of_type_Class_748.a140().f();
                }
              }
              bool = false;
            }
          }
        }
      }
      localObject1.jdField_field_434_of_type_Boolean = bool;
      return;
    }
    a1(this.jdField_field_434_of_type_Class_10);
  }
  
  private void a1(class_10 paramclass_10)
  {
    File localFile;
    if (!paramclass_10.field_433)
    {
      localFile = null;
      String str = paramclass_10.jdField_field_431_of_type_Class_748.getName() + ".png";
      System.err.println("[CLIENT] REQUESTING FILE FOR DOWNLOAD: " + str);
      paramclass_10.field_433 = true;
      this.jdField_field_434_of_type_Class_371.a4().a29().a3(str);
      return;
    }
    if (!this.jdField_field_435_of_type_JavaUtilArrayList.isEmpty())
    {
      synchronized (this.jdField_field_435_of_type_JavaUtilArrayList)
      {
        System.err.println("RECEIVED FILES: " + this.jdField_field_435_of_type_JavaUtilArrayList);
        localFile = null;
        class_748 localclass_748 = paramclass_10.jdField_field_431_of_type_Class_748;
        for (int i = 0; i < this.jdField_field_435_of_type_JavaUtilArrayList.size(); i++) {
          if ((localFile = (File)this.jdField_field_435_of_type_JavaUtilArrayList.get(i)).getName().equals(localclass_748.getName() + ".png"))
          {
            Object localObject1 = localclass_748.a140().field_1012;
            (localObject1 = new File((String)localObject1 + localclass_748.getName() + ".png")).delete();
            try
            {
              ((File)localObject1).createNewFile();
              FileUtil.b(localFile, (File)localObject1);
              localFile.delete();
              ((File)localObject1).setLastModified(paramclass_10.jdField_field_432_of_type_Long);
            }
            catch (IOException localIOException)
            {
              localIOException;
            }
            this.jdField_field_435_of_type_JavaUtilArrayList.remove(i);
            paramclass_10.jdField_field_432_of_type_Boolean = true;
            localclass_748.a140().f();
            break;
          }
        }
      }
      this.jdField_field_434_of_type_Class_10 = null;
    }
  }
  
  public final void update(Observable arg1, Object paramObject)
  {
    if (((paramObject instanceof class_748)) && (this.jdField_field_434_of_type_Class_371.getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((class_748)paramObject).getId()))) {
      synchronized (this.field_436)
      {
        System.err.println("[CLIENT][TEXTURE][SYNCHRONIZER] ADDED TO UPDATE QUEUE: " + paramObject + " for " + this.jdField_field_434_of_type_Class_371.a20());
        this.field_436.add((class_748)paramObject);
        return;
      }
    }
  }
  
  public final void a2(RemoteStringArray paramRemoteStringArray)
  {
    synchronized (this.field_437)
    {
      this.field_437.add(new String[] { (String)paramRemoteStringArray.get(0).get(), (String)paramRemoteStringArray.get(1).get() });
      return;
    }
  }
  
  public final void a3(File paramFile)
  {
    synchronized (this.jdField_field_435_of_type_JavaUtilArrayList)
    {
      this.jdField_field_435_of_type_JavaUtilArrayList.add(paramFile);
      return;
    }
  }
  
  public final void a4(class_748 paramclass_748, long paramLong)
  {
    synchronized (this.jdField_field_434_of_type_JavaUtilArrayList)
    {
      this.jdField_field_434_of_type_JavaUtilArrayList.add(new class_21(paramclass_748, paramLong));
      return;
    }
  }
  
  public final void a5(String paramString)
  {
    this.jdField_field_434_of_type_JavaLangString = paramString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_11
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */