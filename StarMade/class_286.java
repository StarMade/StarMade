import java.io.PrintStream;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.remote.RemoteBoolean;

public final class class_286
  extends class_1363
  implements class_1412
{
  private class_371 jdField_field_89_of_type_Class_371;
  private class_781 jdField_field_89_of_type_Class_781;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_286(ClientState paramClientState, class_781 paramclass_781, boolean paramBoolean)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Boolean = paramBoolean;
    this.jdField_field_89_of_type_Class_371 = ((class_371)a24());
    this.jdField_field_89_of_type_Class_781 = paramclass_781;
  }
  
  public final void a2() {}
  
  public final void b()
  {
    k();
  }
  
  public final void c()
  {
    class_934 localclass_9341;
    (localclass_9341 = new class_934(this.jdField_field_89_of_type_Class_371, 70, 30, new Vector4f(0.3F, 0.6F, 0.3F, 0.9F), new Vector4f(0.99F, 0.99F, 0.99F, 1.0F), class_29.d(), "BUY", this, a14())).field_89 = "buy";
    localclass_9341.b17(14, 4);
    class_934 localclass_9342;
    (localclass_9342 = new class_934(this.jdField_field_89_of_type_Class_371, 80, 20, "owner", this, a14())).field_89 = "owner";
    localclass_9342.b17(5, 1);
    class_934 localclass_9343;
    (localclass_9343 = new class_934(this.jdField_field_89_of_type_Class_371, 90, 20, "description", this, a14())).field_89 = "description";
    localclass_9343.b17(5, 1);
    class_934 localclass_9344;
    (localclass_9344 = new class_934(this.jdField_field_89_of_type_Class_371, 90, 20, "permissions", this, a14())).field_89 = "permission";
    localclass_9344.b17(5, 1);
    class_934 localclass_9345;
    (localclass_9345 = new class_934(this.jdField_field_89_of_type_Class_371, 45, 20, "rate", this, a14())).field_89 = "rate";
    localclass_9345.b17(5, 1);
    class_934 localclass_9346;
    (localclass_9346 = new class_934(this.jdField_field_89_of_type_Class_371, 65, 20, new Vector4f(0.7F, 0.2F, 0.2F, 0.9F), new Vector4f(0.99F, 0.99F, 0.99F, 1.0F), class_29.o(), "delete", this, a14())).field_89 = "delete";
    localclass_9346.b17(6, 1);
    localclass_9341.a83().field_615 += 80.0F;
    localclass_9342.a83().field_615 += 90.0F;
    localclass_9343.a83().field_615 += 100.0F;
    localclass_9344.a83().field_615 += 100.0F;
    localclass_9345.a83().field_615 += 70.0F;
    class_930 localclass_930;
    (localclass_930 = new class_930(10, 10, this.jdField_field_89_of_type_Class_371)).a83().field_616 = 35.0F;
    localclass_930.a136(this.jdField_field_89_of_type_Class_781.field_182);
    a9(localclass_9341);
    a9(localclass_9344);
    a9(localclass_9346);
    a9(localclass_930);
    a9(localclass_9343);
    a9(localclass_9345);
    if (this.jdField_field_89_of_type_Boolean) {
      a9(localclass_9342);
    }
  }
  
  public final float a3()
  {
    return 80.0F;
  }
  
  public final float b1()
  {
    return 510.0F;
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      if ("buy".equals(paramclass_1363.field_89))
      {
        paramclass_1363 = this;
        if (!((class_371)a24()).d2()) {
          ((class_371)paramclass_1363.a24()).a4().b1("ERROR:\nCannot buy!\nYou are not near a shop!");
        }
        paramclass_1363.a14().e2(true);
        paramclass_939 = "Please type in a name for your new Ship!";
        (paramclass_939 = new class_292(paramclass_1363, (class_371)paramclass_1363.a24(), "New Ship", paramclass_939, paramclass_1363.jdField_field_89_of_type_Class_781.field_136 + "_" + System.currentTimeMillis())).a10(new class_290());
        paramclass_939.c1();
        return;
      }
      if ("description".equals(paramclass_1363.field_89))
      {
        paramclass_1363 = this;
        a14().e2(true);
        new class_284(paramclass_1363, (class_371)paramclass_1363.a24(), "Edit entry Description", "Enter a description for the entry", new String(paramclass_1363.jdField_field_89_of_type_Class_781.field_182)).c1();
        return;
      }
      if ("permission".equals(paramclass_1363.field_89))
      {
        paramclass_1363 = this;
        System.err.println("EDIT PERMISSION");
        paramclass_1363.a14().e2(true);
        new class_428(paramclass_1363.jdField_field_89_of_type_Class_371, paramclass_1363.jdField_field_89_of_type_Class_781, paramclass_1363.jdField_field_89_of_type_Boolean).c1();
        return;
      }
      if ("delete".equals(paramclass_1363.field_89))
      {
        paramclass_1363 = this;
        if (((this.jdField_field_89_of_type_Boolean) && (((Boolean)((class_371)paramclass_1363.a24()).a20().a127().isAdminClient.get()).booleanValue()) ? 1 : 0) == 0) {
          if (!((class_371)paramclass_1363.a24()).a20().getName().equals(paramclass_1363.jdField_field_89_of_type_Class_781.field_139))
          {
            ((class_371)paramclass_1363.a24()).a4().b1("ERROR:\nCannot delete!\nYou don not own this!");
            return;
          }
        }
        paramclass_1363.a14().e2(true);
        new class_282(paramclass_1363, (class_371)paramclass_1363.a24(), "Confirm", "Do you really want to delete this entry\n(a backup will be created on the server)").c1();
        return;
      }
      if ("owner".equals(paramclass_1363.field_89))
      {
        paramclass_1363 = this;
        a14().e2(true);
        paramclass_939 = "Change the owner of \"" + paramclass_1363.jdField_field_89_of_type_Class_781.field_136 + "\"";
        (paramclass_939 = new class_296(paramclass_1363, (class_371)paramclass_1363.a24(), "Change Owner", paramclass_939, paramclass_1363.jdField_field_89_of_type_Class_781.field_139)).a10(new class_294());
        paramclass_939.c1();
        return;
      }
      if ("rate".equals(paramclass_1363.field_89))
      {
        paramclass_1363 = this;
        a14().e2(true);
        new class_429(paramclass_1363.jdField_field_89_of_type_Class_371, paramclass_1363.jdField_field_89_of_type_Class_781).c1();
      }
    }
  }
  
  public final class_427 a14()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4;
  }
  
  public final boolean a4()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_286
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */