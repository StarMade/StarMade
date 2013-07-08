import javax.vecmath.Vector3f;
import org.lwjgl.input.Mouse;
import org.schema.schine.network.client.ClientState;

public final class class_1416
  extends class_1414
{
  private boolean jdField_field_89_of_type_Boolean;
  private class_48 jdField_field_89_of_type_Class_48 = new class_48();
  public class_1363 field_89;
  private boolean field_90;
  
  public class_1416(ClientState paramClientState, class_1363 paramclass_1363)
  {
    super(paramClientState, 380.0F, 40.0F);
    this.jdField_field_89_of_type_Class_1363 = paramclass_1363;
  }
  
  public final void b()
  {
    this.field_96 = true;
    super.b();
    if ((a_()) && (Mouse.isButtonDown(0)) && (!this.field_90) && (!this.jdField_field_89_of_type_Boolean))
    {
      this.jdField_field_89_of_type_Boolean = true;
      this.jdField_field_89_of_type_Class_48.b(Mouse.getX(), class_933.a() - Mouse.getY(), 0);
    }
    this.field_90 = Mouse.isButtonDown(0);
    Object localObject;
    if (this.jdField_field_89_of_type_Boolean)
    {
      (localObject = new class_48(Mouse.getX(), class_933.a() - Mouse.getY(), 0)).c1(this.jdField_field_89_of_type_Class_48);
      this.jdField_field_89_of_type_Class_1363.a83().field_615 += ((class_48)localObject).field_475;
      this.jdField_field_89_of_type_Class_1363.a83().field_616 += ((class_48)localObject).field_476;
      this.jdField_field_89_of_type_Class_48.b(Mouse.getX(), class_933.a() - Mouse.getY(), 0);
      if (!Mouse.isButtonDown(0)) {
        this.jdField_field_89_of_type_Boolean = false;
      }
    }
    if ((localObject = this.jdField_field_89_of_type_Class_1363).a83().field_615 < 0.0F) {
      ((class_1363)localObject).a83().field_615 = 0.0F;
    }
    if (((class_1363)localObject).a83().field_616 < 0.0F) {
      ((class_1363)localObject).a83().field_616 = 0.0F;
    }
    if (((class_1363)localObject).a83().field_615 + ((class_1363)localObject).b1() > class_933.b()) {
      ((class_1363)localObject).a83().field_615 = (class_933.b() - ((class_1363)localObject).b1());
    }
    if (((class_1363)localObject).a83().field_616 + ((class_1363)localObject).a3() > class_933.a()) {
      ((class_1363)localObject).a83().field_616 = (class_933.a() - ((class_1363)localObject).a3());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1416
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */