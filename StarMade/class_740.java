import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.schema.game.common.data.UploadInProgressException;
import org.schema.game.common.data.player.FileUploadTooBigException;
import org.schema.game.network.objects.remote.RemoteControlledFileStream;
import org.schema.game.server.controller.CatalogEntryNotFoundException;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.Streamable;
import org.schema.schine.network.server.ServerStateInterface;

public abstract class class_740
{
  private class_788 jdField_field_181_of_type_Class_788;
  private class_790 jdField_field_181_of_type_Class_790;
  protected Sendable field_181;
  private ArrayList jdField_field_181_of_type_JavaUtilArrayList = new ArrayList();
  private long jdField_field_181_of_type_Long;
  private final ArrayList field_1009 = new ArrayList();
  
  public class_740(Sendable paramSendable)
  {
    this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable = paramSendable;
  }
  
  protected abstract String a();
  
  public void c()
  {
    try
    {
      if (!a3().getReceiveBuffer().isEmpty())
      {
        Iterator localIterator = a3().getReceiveBuffer().iterator();
        while (localIterator.hasNext())
        {
          RemoteControlledFileStream localRemoteControlledFileStream = (RemoteControlledFileStream)localIterator.next();
          if (this.jdField_field_181_of_type_Class_790 == null)
          {
            this.jdField_field_181_of_type_Class_790 = new class_790();
            Object localObject = new File(a());
            System.err.println("[UPLOAD] NEW UPLOAD INITIATED! for " + this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable + ": " + a());
            if (((File)localObject).exists())
            {
              System.err.println("[UPLOAD] DELETED EXISTING FILE! for " + this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable);
              ((File)localObject).delete();
            }
            this.jdField_field_181_of_type_Long = 0L;
            ((File)localObject).createNewFile();
            if ((!jdField_field_181_of_type_Boolean) && (!((File)localObject).exists())) {
              throw new AssertionError();
            }
            localObject = new FileOutputStream((File)localObject);
            this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_JavaIoDataOutputStream = new DataOutputStream(new BufferedOutputStream((OutputStream)localObject));
          }
          if (this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_Boolean)
          {
            int i = ((class_1274)localRemoteControlledFileStream.get()).jdField_field_1460_of_type_Short;
            this.jdField_field_181_of_type_Long += i;
            if ((this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getState() instanceof ClientStateInterface))
            {
              class_740 localclass_740 = this;
              String str = "downloading skin: " + localclass_740.a() + "... " + localclass_740.jdField_field_181_of_type_Long / 1000L + " kb";
              ((class_371)localclass_740.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getState()).a4().a25("upload", str);
            }
            this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_JavaIoDataOutputStream.write(((class_1274)localRemoteControlledFileStream.get()).jdField_field_1460_of_type_ArrayOfByte, 0, i);
            this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_JavaIoDataOutputStream.flush();
            if (((class_1274)localRemoteControlledFileStream.get()).jdField_field_1460_of_type_Boolean)
            {
              this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_JavaIoDataOutputStream.close();
              File localFile = new File(a());
              if ((!jdField_field_181_of_type_Boolean) && (!localFile.exists())) {
                throw new AssertionError();
              }
              this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_JavaIoFile = localFile;
            }
          }
          if (((class_1274)localRemoteControlledFileStream.get()).jdField_field_1460_of_type_Boolean)
          {
            if (this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_JavaIoDataOutputStream != null) {
              this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_JavaIoDataOutputStream.close();
            }
            this.jdField_field_181_of_type_Class_790.field_1050 = true;
          }
        }
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
      if (this.jdField_field_181_of_type_Class_790 != null) {
        this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_Boolean = false;
      }
    }
  }
  
  protected abstract File a1(String paramString);
  
  public final void d()
  {
    if (!this.field_1009.isEmpty()) {
      for (int i = 0; i < this.field_1009.size(); i++)
      {
        long l1 = System.currentTimeMillis();
        localObject2 = (class_754)this.field_1009.get(i);
        if ((l1 - ((class_754)localObject2).jdField_field_1014_of_type_Long > 5000L ? 1 : 0) != 0) {
          try
          {
            a4(((class_754)this.field_1009.get(i)).jdField_field_1014_of_type_JavaLangString);
            this.field_1009.remove(i);
            i--;
          }
          catch (UploadInProgressException localUploadInProgressException)
          {
            System.err.println("[UPLOADCONTROLLER] " + this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getState() + " " + this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable + " cannot deffer because of ongoing upload. trying next time");
          }
        }
      }
    }
    Object localObject3;
    Object localObject1;
    if (!this.jdField_field_181_of_type_JavaUtilArrayList.isEmpty()) {
      try
      {
        localObject2 = this;
        localObject3 = (String)this.jdField_field_181_of_type_JavaUtilArrayList.remove(0);
        try
        {
          if ((localObject4 = ((class_740)localObject2).a1((String)localObject3)).exists())
          {
            ((class_740)localObject2).jdField_field_181_of_type_Class_788 = new class_788();
            ((class_740)localObject2).jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_JavaIoDataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream((File)localObject4)));
            ((class_740)localObject2).jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_Long = ((File)localObject4).length();
            ((class_740)localObject2).jdField_field_181_of_type_Class_788.field_1047 = 0L;
          }
          else
          {
            System.err.println("[UploadController] File " + ((File)localObject4).getName() + " does not yet exist. Deferring download by 5 seconds for " + ((class_740)localObject2).jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable);
            class_754 localclass_754 = new class_754((String)localObject3, System.currentTimeMillis());
            ((class_740)localObject2).field_1009.add(localclass_754);
          }
        }
        catch (IOException localIOException)
        {
          Object localObject4;
          (localObject4 = localIOException).printStackTrace();
          if (!(((class_740)localObject2).jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getState() instanceof ServerStateInterface)) {
            class_933.a2((Exception)localObject4);
          }
        }
        catch (CatalogEntryNotFoundException localCatalogEntryNotFoundException)
        {
          ((class_371)((class_740)localObject2).jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getState()).a4().b1("Entry not found:\n" + (String)localObject3);
        }
        ((class_740)localObject2).jdField_field_181_of_type_JavaUtilArrayList.clear();
      }
      catch (FileUploadTooBigException localFileUploadTooBigException)
      {
        (localObject1 = localFileUploadTooBigException).printStackTrace();
        if ((this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getState() instanceof ClientStateInterface)) {
          class_933.a2((Exception)localObject1);
        }
      }
    }
    Object localObject2 = this;
    if ((this.jdField_field_181_of_type_Class_788 != null) && (System.currentTimeMillis() - ((class_740)localObject2).jdField_field_181_of_type_Class_788.field_1048 > 30L))
    {
      localObject3 = new class_1274();
      long l2 = ((class_740)localObject2).jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_Long - ((class_740)localObject2).jdField_field_181_of_type_Class_788.field_1047;
      if (((class_740)localObject2).jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_JavaIoDataInputStream != null) {
        if (l2 < 128L)
        {
          ((class_1274)localObject3).jdField_field_1460_of_type_Short = ((short)(int)l2);
          ((class_740)localObject2).jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_JavaIoDataInputStream.readFully(((class_1274)localObject3).jdField_field_1460_of_type_ArrayOfByte, 0, (int)l2);
          ((class_740)localObject2).jdField_field_181_of_type_Class_788.field_1047 += l2;
        }
        else
        {
          ((class_1274)localObject3).jdField_field_1460_of_type_Short = 128;
          ((class_740)localObject2).jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_JavaIoDataInputStream.readFully(((class_1274)localObject3).jdField_field_1460_of_type_ArrayOfByte);
          ((class_740)localObject2).jdField_field_181_of_type_Class_788.field_1047 += 128L;
        }
      }
      if ((l2 = ((class_740)localObject2).jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_Long - ((class_740)localObject2).jdField_field_181_of_type_Class_788.field_1047) <= 0L) {
        ((class_1274)localObject3).jdField_field_1460_of_type_Boolean = true;
      }
      localObject1 = new RemoteControlledFileStream((class_1274)localObject3, ((class_740)localObject2).jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getNetworkObject());
      ((class_740)localObject2).a3().add((Streamable)localObject1);
      ((class_740)localObject2).jdField_field_181_of_type_Class_788.field_1048 = System.currentTimeMillis();
      if ((((class_740)localObject2).jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getState() instanceof ClientStateInterface)) {
        ((class_740)localObject2).a6();
      }
      if (l2 <= 0L)
      {
        ((class_740)localObject2).jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_JavaIoDataInputStream.close();
        ((class_740)localObject2).jdField_field_181_of_type_Class_788 = null;
        ((class_740)localObject2).b1();
      }
    }
    if ((this.jdField_field_181_of_type_Class_790 != null) && (this.jdField_field_181_of_type_Class_790.field_1050))
    {
      localObject1 = this.jdField_field_181_of_type_Class_790.jdField_field_1049_of_type_JavaIoFile;
      System.err.println("[" + this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getState() + "][" + this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable + "] FINISHED DOWNLOADING: " + ((File)localObject1).getAbsolutePath());
      a2((File)localObject1);
      this.jdField_field_181_of_type_Class_790 = null;
    }
  }
  
  protected abstract void a2(File paramFile);
  
  protected void a6()
  {
    String str = "upload finished!";
    if (this.jdField_field_181_of_type_Class_788.field_1047 < this.jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_Long) {
      str = "uploading: " + this.jdField_field_181_of_type_Class_788.field_1047 / 1000L + " kb of " + this.jdField_field_181_of_type_Class_788.jdField_field_1046_of_type_Long / 1000L + " kb";
    }
    ((class_371)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable.getState()).a4().a25("download", str);
  }
  
  protected void b1() {}
  
  public void a4(String paramString)
  {
    if ((this.jdField_field_181_of_type_JavaUtilArrayList.size() > 0) || (this.jdField_field_181_of_type_Class_788 != null)) {
      throw new UploadInProgressException();
    }
    this.jdField_field_181_of_type_JavaUtilArrayList.add(paramString);
  }
  
  protected abstract RemoteBuffer a3();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_740
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */