import java.util.ArrayList;
import org.schema.schine.network.client.ClientState;

final class class_127
  extends class_196
  implements class_1412
{
  public class_127(class_121 paramclass_121, ClientState paramClientState, class_1412 paramclass_1412, Object paramObject1, Object paramObject2)
  {
    super(paramClientState, paramclass_1412, paramObject1, paramObject2);
    this.field_89 = false;
  }
  
  public final void c()
  {
    super.c();
    Object localObject;
    (localObject = new class_133(a24())).c();
    this.jdField_field_89_of_type_Class_1414.a9((class_1363)localObject);
    (localObject = new class_930(100, 100, a24())).a136("No Permission to invite");
    (localObject = new class_125(a24(), "Invite Player", this, (class_930)localObject)).field_89 = "INVITE";
    ((class_934)localObject).a161(235.0F, 213.0F, 0.0F);
    this.jdField_field_89_of_type_Class_972.a9((class_1363)localObject);
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      if ((paramclass_1363 = ((class_371)a24()).a14().field_4.field_4.field_4).a6().a20().a141().b40().size() >= 5)
      {
        paramclass_1363.a6().a4().b1("You can only have five\ninvitations pending\nat the same time");
      }
      else
      {
        paramclass_1363.e2(true);
        new class_412(paramclass_1363, paramclass_1363.a6(), "Create Invitation", "Enter Name of the player you want to invite").c1();
      }
      this.jdField_field_89_of_type_Class_121.d();
    }
  }
  
  public final boolean a4()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_127
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */