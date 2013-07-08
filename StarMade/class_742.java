import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.File;
import java.io.PrintStream;
import org.schema.game.common.data.player.FileUploadTooBigException;
import org.schema.game.common.updater.FileUtil;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteLong;

public class class_742
  extends class_740
{
  public class_742(class_748 paramclass_748)
  {
    super(paramclass_748);
  }
  
  protected final String a()
  {
    return ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).getName() + "_skin_upload_tmp.zip";
  }
  
  protected final void a6()
  {
    if (((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a7()) {
      super.a6();
    }
  }
  
  protected final File a1(String paramString)
  {
    String str = "client_skin_upload_package.zip";
    if ((paramString = new File(paramString)).length() > 262144L) {
      throw new FileUploadTooBigException(paramString);
    }
    File localFile1;
    (localFile1 = new File("./tmpUpload/")).mkdirs();
    File localFile2;
    (localFile2 = new File("./tmpUpload/" + ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).getName() + ".png")).delete();
    localFile2.createNewFile();
    FileUtil.b(paramString, localFile2);
    class_29.a11("./tmpUpload/", str, null);
    class_1182.a(localFile1);
    return new File(str);
  }
  
  protected final void b1()
  {
    new File(a()).delete();
    String str = "client_skin_upload_package.zip";
    new File(str).delete();
  }
  
  public final void c()
  {
    super.c();
    if (!((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).isOnServer()) {
      for (int i = 0; i < ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a127().textureChangedBroadcastBuffer.getReceiveBuffer().size(); i++)
      {
        RemoteLong localRemoteLong = (RemoteLong)((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a127().textureChangedBroadcastBuffer.getReceiveBuffer().get(i);
        class_371 localclass_371 = (class_371)((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).getState();
        System.err.println("[CLIENT][SKINUPDATE] received texture change broadcast from " + (class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable + " on Client Player " + localclass_371.a20());
        localclass_371.a4().a30().a4((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable, ((Long)localRemoteLong.get()).longValue());
      }
    }
  }
  
  protected final void a2(File paramFile)
  {
    try
    {
      if (paramFile != null)
      {
        if ((!jdField_field_181_of_type_Boolean) && (!paramFile.exists())) {
          throw new AssertionError();
        }
        ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a140().a1(paramFile);
      }
      return;
    }
    catch (Exception localException)
    {
      (paramFile = localException).printStackTrace();
      ((class_1041)((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).getState()).a59().broadcastMessage("[UPLOAD][ERROR] IMPORT FAILED " + paramFile.getClass().getSimpleName(), 3);
    }
  }
  
  protected final RemoteBuffer a3()
  {
    return ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a127().skinUploadBuffer;
  }
  
  static
  {
    jdField_field_181_of_type_Boolean = !lH.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_742
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */