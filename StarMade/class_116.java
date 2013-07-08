import java.util.HashMap;
import javax.vecmath.Vector4f;
import org.schema.game.common.data.player.faction.FactionNotFoundException;
import org.schema.schine.network.client.ClientState;

final class class_116
  extends class_196
{
  private class_934 jdField_field_89_of_type_Class_934;
  private class_105[] jdField_field_89_of_type_ArrayOfClass_105 = new class_105[5];
  private class_773 jdField_field_89_of_type_Class_773;
  
  public class_116(class_112 paramclass_112, ClientState paramClientState, class_1412 paramclass_1412, Object paramObject1, Object paramObject2, class_773 paramclass_773)
  {
    super(paramClientState, paramclass_1412, paramObject1, paramObject2);
    this.jdField_field_89_of_type_Class_773 = paramclass_773;
    this.field_89 = false;
  }
  
  public final void a2() {}
  
  public final void b()
  {
    int i = ((class_371)a24()).a20().a141().a3();
    if (((class_371)a24()).a12().a51().a156(i) == this.jdField_field_89_of_type_Class_773)
    {
      super.b();
      return;
    }
    this.jdField_field_89_of_type_Class_112.d();
  }
  
  public final void c()
  {
    super.c();
    try
    {
      Object localObject1 = this;
      int i = ((class_371)a24()).a20().a141().a3();
      Object localObject3;
      if ((localObject3 = ((class_371)((class_116)localObject1).a24()).a12().a51().a156(i)) == null) {
        throw new FactionNotFoundException(Integer.valueOf(i));
      }
      if ((localObject1 = (class_758)((class_773)localObject3).a162().get(((class_371)((class_116)localObject1).a24()).a20().getName())) == null) {
        throw new FactionNotFoundException(Integer.valueOf(i));
      }
      if ((localObject1 = localObject1).b24(this.jdField_field_89_of_type_Class_773))
      {
        this.jdField_field_89_of_type_Class_934 = new class_934(a24(), 50, 20, new Vector4f(0.5F, 0.0F, 0.0F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.h(), "Kick", new class_107(this, (class_758)localObject1));
        this.jdField_field_89_of_type_Class_934.a161(340.0F, 0.0F, 0.0F);
        this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_934);
      }
      Object localObject2;
      if (((class_758)localObject1).d7(this.jdField_field_89_of_type_Class_773))
      {
        localObject2 = new class_1414(a24());
        (localObject3 = new class_930(100, 20, a24())).a136("Choose a role for this player");
        for (int j = 0; j < 5; j++)
        {
          this.jdField_field_89_of_type_ArrayOfClass_105[j] = new class_105(this.jdField_field_89_of_type_Class_112, a24(), j);
          this.jdField_field_89_of_type_ArrayOfClass_105[j].c();
          this.jdField_field_89_of_type_ArrayOfClass_105[j].a83().field_615 = (j * 130 % 390);
          this.jdField_field_89_of_type_ArrayOfClass_105[j].a83().field_616 = (j / 3 * 30 + 20);
          ((class_1414)localObject2).a9(this.jdField_field_89_of_type_ArrayOfClass_105[j]);
        }
        ((class_1414)localObject2).a9((class_1363)localObject3);
        class_968 localclass_968;
        (localclass_968 = new class_968(406.0F, 80.0F, a24())).c7((class_1363)localObject2);
        localclass_968.a83().field_616 = 25.0F;
        this.jdField_field_89_of_type_Class_1414.a9(localclass_968);
      }
      if ((!((class_758)localObject1).b24(this.jdField_field_89_of_type_Class_773)) && (!((class_758)localObject1).d7(this.jdField_field_89_of_type_Class_773)))
      {
        (localObject2 = new class_930(100, 20, a24())).a136("You don't have permission to edit this member!");
        this.jdField_field_89_of_type_Class_1414.a9((class_1363)localObject2);
      }
      return;
    }
    catch (FactionNotFoundException localFactionNotFoundException)
    {
      localFactionNotFoundException.printStackTrace();
      this.jdField_field_89_of_type_Class_112.d();
    }
  }
  
  public final float a3()
  {
    return 0.0F;
  }
  
  public final float b1()
  {
    return 0.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_116
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */