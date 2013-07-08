import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteStringArray;

public abstract class class_774
  implements class_80, class_985
{
  private final class_772[] jdField_field_136_of_type_ArrayOfClass_772;
  final SegmentController jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController;
  private StateInterface jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface;
  private class_999 jdField_field_136_of_type_Class_999;
  class_989 jdField_field_136_of_type_Class_989;
  private class_796 jdField_field_136_of_type_Class_796;
  private long jdField_field_136_of_type_Long;
  
  public final boolean a7()
  {
    return this.jdField_field_136_of_type_Class_989.a1();
  }
  
  public final class_989 a181()
  {
    return this.jdField_field_136_of_type_Class_989;
  }
  
  protected abstract class_989 b25();
  
  public class_774(StateInterface paramStateInterface, SegmentController paramSegmentController)
  {
    this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_136_of_type_Class_989 = b25();
    this.jdField_field_136_of_type_ArrayOfClass_772 = new class_772[class_776.values().length];
    this.jdField_field_136_of_type_ArrayOfClass_772[class_776.field_1032.ordinal()] = new class_772(class_776.field_1032, "Any", new class_1018(new String[] { "Any", "Selected Target" }), this);
    this.jdField_field_136_of_type_ArrayOfClass_772[class_776.field_1033.ordinal()] = new class_772(class_776.field_1033, "Ship", new class_1018(new String[] { "Turret", "Ship" }), this);
    this.jdField_field_136_of_type_ArrayOfClass_772[class_776.field_1034.ordinal()] = new class_772(class_776.field_1034, Boolean.valueOf(false), new class_1018(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }), this);
  }
  
  public final void a13()
  {
    for (int i = 0; i < this.jdField_field_136_of_type_ArrayOfClass_772.length; i++) {
      b30(this.jdField_field_136_of_type_ArrayOfClass_772[i]);
    }
  }
  
  public final void a182(class_772 paramclass_772, boolean paramBoolean)
  {
    if (paramBoolean) {
      c22(paramclass_772);
    }
  }
  
  public final class_772 a183(class_776 paramclass_776)
  {
    return this.jdField_field_136_of_type_ArrayOfClass_772[paramclass_776.ordinal()];
  }
  
  public final class_796 a184()
  {
    return this.jdField_field_136_of_type_Class_796;
  }
  
  public final class_772[] a185()
  {
    return this.jdField_field_136_of_type_ArrayOfClass_772;
  }
  
  protected void a169(class_772 paramclass_772)
  {
    if ((paramclass_772.a172() == class_776.field_1034) && (!paramclass_772.a7()))
    {
      System.err.println("[AI] SENTINEL SET TO FALSE ON " + this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface);
      this.jdField_field_136_of_type_Class_989 = b25();
    }
  }
  
  public void a117(class_809 paramclass_809)
  {
    if (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
    {
      this.jdField_field_136_of_type_ArrayOfClass_772[class_776.field_1034.ordinal()].a173(Boolean.valueOf(false), this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer());
      a13();
    }
  }
  
  public final void b36(class_809 paramclass_809)
  {
    if ((this.jdField_field_136_of_type_Class_989.a1()) && ((this.jdField_field_136_of_type_Class_989 instanceof class_770))) {
      ((class_770)this.jdField_field_136_of_type_Class_989).a(paramclass_809);
    }
  }
  
  public void a168(SegmentController paramSegmentController) {}
  
  protected void b30(class_772 paramclass_772)
  {
    if (paramclass_772.a172() == class_776.field_1034)
    {
      if (paramclass_772.a7()) {
        b4();
      } else {
        ((class_1041)this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a69().a168(this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController);
      }
      if ((!jdField_field_136_of_type_Boolean) && (this.jdField_field_136_of_type_Class_989 == null)) {
        throw new AssertionError();
      }
      if (this.jdField_field_136_of_type_Class_989.field_223 != null) {
        this.jdField_field_136_of_type_Class_989.field_223.b2(!paramclass_772.a7());
      }
    }
  }
  
  protected abstract void b4();
  
  private void c22(class_772 paramclass_772)
  {
    if (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject() == null) {
      return;
    }
    RemoteStringArray localRemoteStringArray;
    (localRemoteStringArray = new RemoteStringArray(2, this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject())).set(0, paramclass_772.b());
    localRemoteStringArray.set(1, paramclass_772.a171().toString());
    ((class_778)this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject()).getAiSettingsModification().add(localRemoteStringArray);
  }
  
  public final void a133(class_796 paramclass_796)
  {
    this.jdField_field_136_of_type_Class_796 = paramclass_796;
  }
  
  public final void c1()
  {
    class_772[] arrayOfclass_772 = null;
    if (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
      for (class_772 localclass_772 : this.jdField_field_136_of_type_ArrayOfClass_772) {
        c22(localclass_772);
      }
    }
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    class_69[] arrayOfclass_69;
    int j;
    if (paramclass_69.a2().equals("AIConfig0"))
    {
      paramclass_69 = (class_69[])paramclass_69.a4();
      for (i = 0; (i < paramclass_69.length) && (paramclass_69[i].a3() != class_79.field_548); i++)
      {
        arrayOfclass_69 = (class_69[])paramclass_69[i].a4();
        for (j = 0; j < this.jdField_field_136_of_type_ArrayOfClass_772.length; j++) {
          if ((this.jdField_field_136_of_type_ArrayOfClass_772[j] != null) && (((String)arrayOfclass_69[0].a4()).equals(this.jdField_field_136_of_type_ArrayOfClass_772[j].a172().name()))) {
            try
            {
              this.jdField_field_136_of_type_ArrayOfClass_772[j].a174((String)arrayOfclass_69[1].a4(), true);
              b30(this.jdField_field_136_of_type_ArrayOfClass_772[j]);
            }
            catch (StateParameterNotFoundException localStateParameterNotFoundException1)
            {
              localStateParameterNotFoundException1;
            }
          }
        }
      }
      return;
    }
    paramclass_69 = (class_69[])paramclass_69.a4();
    for (int i = 0; (i < paramclass_69.length) && (paramclass_69[i].a3() != class_79.field_548); i++)
    {
      arrayOfclass_69 = (class_69[])paramclass_69[i].a4();
      for (j = 0; j < this.jdField_field_136_of_type_ArrayOfClass_772.length; j++) {
        if ((this.jdField_field_136_of_type_ArrayOfClass_772[j] != null) && (((String)arrayOfclass_69[0].a4()).equals(this.jdField_field_136_of_type_ArrayOfClass_772[j].a172().name()))) {
          try
          {
            this.jdField_field_136_of_type_ArrayOfClass_772[j].a174((String)arrayOfclass_69[1].a4(), true);
            b30(this.jdField_field_136_of_type_ArrayOfClass_772[j]);
          }
          catch (StateParameterNotFoundException localStateParameterNotFoundException2)
          {
            localStateParameterNotFoundException2;
          }
        }
      }
    }
  }
  
  public class_69 toTagStructure()
  {
    new ArrayList();
    class_69[] arrayOfclass_69 = new class_69[this.jdField_field_136_of_type_ArrayOfClass_772.length + 1];
    for (int i = 0; i < this.jdField_field_136_of_type_ArrayOfClass_772.length; i++)
    {
      class_772 localclass_772 = this.jdField_field_136_of_type_ArrayOfClass_772[i];
      if ((!jdField_field_136_of_type_Boolean) && (localclass_772 == null)) {
        throw new AssertionError(i + " / " + Arrays.toString(this.jdField_field_136_of_type_ArrayOfClass_772));
      }
      arrayOfclass_69[i] = localclass_772.toTagStructure();
    }
    arrayOfclass_69[this.jdField_field_136_of_type_ArrayOfClass_772.length] = new class_69(class_79.field_548, null, null);
    return new class_69(class_79.field_561, "AIConfig0", arrayOfclass_69);
  }
  
  public final void a74(class_941 paramclass_941)
  {
    if (this.jdField_field_136_of_type_Class_989.a1())
    {
      if (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
        try
        {
          if ((!this.jdField_field_136_of_type_Class_989.a1()) && (!(this.jdField_field_136_of_type_Class_999 instanceof class_1252))) {
            this.jdField_field_136_of_type_Class_989.a5().a12(new class_1115());
          }
          this.jdField_field_136_of_type_Class_989.field_223.a14(paramclass_941);
          return;
        }
        catch (Exception localException1)
        {
          (paramclass_941 = localException1).printStackTrace();
          System.err.println("Exception: Error occured updating AI " + paramclass_941.getMessage() + ": Current Program: " + this.jdField_field_136_of_type_Class_989.field_223 + "; state: " + this.jdField_field_136_of_type_Class_989.a5() + "; in " + this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController);
          if (System.currentTimeMillis() - this.jdField_field_136_of_type_Long > 1000L)
          {
            ((class_1041)this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a59().broadcastMessage("AI Error occured on Server!\nPlease send in server error report.\n" + paramclass_941.getClass().getSimpleName(), 3);
            this.jdField_field_136_of_type_Long = System.currentTimeMillis();
          }
          return;
        }
      }
      try
      {
        this.jdField_field_136_of_type_Class_989.a2(paramclass_941);
      }
      catch (Exception localException2)
      {
        (paramclass_941 = localException2).printStackTrace();
        throw new ErrorDialogException(paramclass_941.getMessage());
      }
      if (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()) {
        try
        {
          a183(class_776.field_1034).a13();
          ((class_371)this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a4().b1("WARNING\nThis vessel was AI controlled\n... switched off AI\nre-enable AI with " + ElementKeyMap.getInfo((short)121).getName());
          return;
        }
        catch (StateParameterNotFoundException localStateParameterNotFoundException)
        {
          (paramclass_941 = localStateParameterNotFoundException).printStackTrace();
          throw new ErrorDialogException(paramclass_941.toString());
        }
      }
    }
  }
  
  public final void d2()
  {
    ObjectArrayList localObjectArrayList = ((class_778)this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject()).getAiSettingsModification().getReceiveBuffer();
    for (int i = 0; i < localObjectArrayList.size(); i++)
    {
      RemoteStringArray localRemoteStringArray = (RemoteStringArray)localObjectArrayList.get(i);
      class_772[] arrayOfclass_772;
      int j = (arrayOfclass_772 = this.jdField_field_136_of_type_ArrayOfClass_772).length;
      for (int k = 0; k < j; k++)
      {
        class_772 localclass_772;
        boolean bool;
        if (((bool = (localclass_772 = arrayOfclass_772[k]).b().equals(localRemoteStringArray.get(0).get()))) && (!localclass_772.a171().toString().equals(localRemoteStringArray.get(1).get()))) {
          try
          {
            localclass_772.a174((String)localRemoteStringArray.get(1).get(), this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer());
            if (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
              b30(localclass_772);
            } else {
              a169(localclass_772);
            }
          }
          catch (StateParameterNotFoundException localStateParameterNotFoundException)
          {
            localStateParameterNotFoundException;
          }
        } else if ((!this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (bool)) {
          a169(localclass_772);
        }
      }
    }
  }
  
  public final void a186(NetworkObject paramNetworkObject)
  {
    paramNetworkObject = (class_778)paramNetworkObject;
    if (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
    {
      if (class_1057.field_1330.a4())
      {
        String str = "";
        if (this.jdField_field_136_of_type_Class_989.a1())
        {
          str = "\nTar:" + ((class_1256)this.jdField_field_136_of_type_Class_989.field_223).a7();
          this.jdField_field_136_of_type_Class_999 = this.jdField_field_136_of_type_Class_989.a5();
          ((class_1256)this.jdField_field_136_of_type_Class_989.field_223).a7();
        }
        class_1098 localclass_1098 = ((class_1041)this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a69();
        paramNetworkObject.getDebugState().set("(" + this.jdField_field_136_of_type_Class_989.toString() + "[" + (this.jdField_field_136_of_type_Class_989.a1() ? "ON" : "OFF") + "])" + str + ";\n" + localclass_1098.a217(this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController));
        return;
      }
      if (((String)paramNetworkObject.getDebugState().get()).length() > 0) {
        paramNetworkObject.getDebugState().set("");
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_774
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */