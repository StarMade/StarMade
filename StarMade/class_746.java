import java.io.File;
import java.util.ArrayList;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.server.ServerMessage;

public class class_746
  extends class_740
{
  public class_746(class_748 paramclass_748)
  {
    super(paramclass_748);
  }
  
  protected final String a()
  {
    return ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).getName() + "_upload_tmp.zip";
  }
  
  protected final File a1(String paramString)
  {
    return class_1216.field_1429.a(paramString);
  }
  
  protected final void a6()
  {
    if (((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a7()) {
      super.a6();
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
        if ((((Integer)class_1057.field_1332.a3()).intValue() < 0) || (((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a122().a1().size() < ((Integer)class_1057.field_1332.a3()).intValue())) {
          ((class_1072)((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).getState()).a().a105(paramFile, (class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable);
        } else {
          ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a129(new ServerMessage("Cannot save blueprint:\nout of slots: " + ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a122().a1().size() + "/" + class_1057.field_1332.a3(), 3, ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).getId()));
        }
        if (paramFile.exists()) {
          paramFile.delete();
        }
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
    return ((class_748)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a127().shipUploadBuffer;
  }
  
  static
  {
    jdField_field_181_of_type_Boolean = !lF.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_746
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */