import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.network.objects.NetworkGameState;
import org.schema.game.server.data.blueprintnw.BlueprintEntry;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.remote.RemoteInteger;

public final class class_93
  extends class_1414
  implements class_1412
{
  private class_934 jdField_field_89_of_type_Class_934;
  private class_934 field_90;
  private class_934 field_92;
  private class_930 jdField_field_89_of_type_Class_930;
  
  public class_93(ClientState paramClientState)
  {
    super(paramClientState, 510.0F, 60.0F);
  }
  
  public final class_427 a14()
  {
    return ((class_371)a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_427;
  }
  
  public final void c()
  {
    super.c();
    class_371 localclass_371 = (class_371)a24();
    this.jdField_field_89_of_type_Class_934 = new class_934(localclass_371, 142, 25, new Vector4f(0.3F, 0.3F, 0.7F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.c(), "Create new entry", this, a14());
    this.jdField_field_89_of_type_Class_934.field_89 = "save";
    this.jdField_field_89_of_type_Class_934.b17(4, 1);
    this.field_90 = new class_934(localclass_371, 140, 20, new Vector4f(0.3F, 0.7F, 0.5F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.o(), "Save in local catalog", this, a14());
    this.field_90.field_89 = "save_local";
    this.field_92 = new class_934(localclass_371, 160, 20, new Vector4f(0.5F, 0.7F, 0.3F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.o(), "Upload entry from local", this, a14());
    this.field_92.field_89 = "upload";
    this.field_90.a83().field_615 = 220.0F;
    this.field_92.a83().field_615 = 370.0F;
    class_930 localclass_930 = new class_930(1, 1, localclass_371);
    int i;
    if ((i = ((Integer)localclass_371.a12().a52().saveSlotsAllowed.get()).intValue()) < 0) {
      localclass_930.a136("Used: " + localclass_371.a20().a122().a1().size());
    } else {
      localclass_930.a136("Used: " + localclass_371.a20().a122().a1().size() + "/" + i);
    }
    localclass_930.a83().field_615 = 150.0F;
    localclass_930.a83().field_616 = 2.0F;
    this.jdField_field_89_of_type_Class_930 = new class_930(1, 1, localclass_371);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add("\"Create new Entry\" will save the ship you are currently in into this catalog. You can also save ");
    this.jdField_field_89_of_type_Class_930.field_90.add("a ship in your singleplayer (local) catalog, or upload an entry from it.");
    this.jdField_field_89_of_type_Class_930.a83().field_616 = (this.jdField_field_89_of_type_Class_934.a83().field_616 + this.jdField_field_89_of_type_Class_934.a3() + 4.0F);
    a9(this.jdField_field_89_of_type_Class_930);
    a9(localclass_930);
    a9(this.jdField_field_89_of_type_Class_934);
    a9(this.field_90);
    a9(this.field_92);
  }
  
  public final void b()
  {
    ((class_371)a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_443.a43();
    super.b();
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      paramclass_939 = ((class_371)a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_443.a43();
      paramclass_939 = (((class_371)a24()).a25() != null) || ((paramclass_939 != null) && ((paramclass_939 instanceof class_747))) ? 1 : 0;
      Object localObject;
      if ("save".equals(paramclass_1363.field_89))
      {
        if (paramclass_939 != 0)
        {
          paramclass_1363 = this;
          a14().e2(true);
          paramclass_939 = "Please enter in a name for your blue print!";
          (localObject = new class_168(paramclass_1363, (class_371)paramclass_1363.a24(), "BluePrint", paramclass_939, "BLUEPRINT_" + System.currentTimeMillis())).a10(new class_166());
          ((class_15)localObject).c1();
          return;
        }
        ((class_371)a24()).a4().b1("You must be in a\nship or have one\nselected to save it");
        return;
      }
      if ("save_local".equals(paramclass_1363.field_89))
      {
        if (paramclass_939 != 0)
        {
          paramclass_1363 = this;
          a14().e2(true);
          paramclass_939 = "Please enter in a name for your blue print!";
          (localObject = new class_95(paramclass_1363, (class_371)paramclass_1363.a24(), "BluePrint", paramclass_939, "BLUEPRINT_" + System.currentTimeMillis())).a10(new class_97());
          ((class_15)localObject).c1();
          return;
        }
        ((class_371)a24()).a4().b1("You must be in a\nship or have one\nselected to save it");
        return;
      }
      if ("upload".equals(paramclass_1363.field_89))
      {
        paramclass_1363 = this;
        a14().e2(true);
        paramclass_939 = class_1216.field_1429.a7();
        localObject = "Please enter in a name for your blue print!\n\nAvailable:\n" + paramclass_939;
        (paramclass_939 = new class_172(paramclass_1363, (class_371)paramclass_1363.a24(), "BluePrint", localObject, paramclass_939.isEmpty() ? "" : ((BlueprintEntry)paramclass_939.get(0)).toString())).a10(new class_170());
        paramclass_939.c1();
      }
    }
  }
  
  public final boolean a4()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_93
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */