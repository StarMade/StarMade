import java.util.Random;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.world.Universe;

public final class class_1097
  extends class_1068
{
  private class_48 jdField_field_241_of_type_Class_48 = new class_48();
  private class_48 jdField_field_242_of_type_Class_48 = new class_48();
  private class_48 field_243 = new class_48();
  private Vector3f jdField_field_240_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_244_of_type_JavaxVecmathVector3f = new Vector3f();
  private class_48[] jdField_field_240_of_type_ArrayOfClass_48;
  private int jdField_field_244_of_type_Int = 20;
  private int jdField_field_241_of_type_Int = 5;
  private int jdField_field_242_of_type_Int = 25;
  
  public class_1097(class_1068[] paramArrayOfclass_1068, class_48 paramclass_481, class_48 paramclass_482)
  {
    super(paramArrayOfclass_1068, paramclass_481, paramclass_482, 4, 0);
  }
  
  protected final short a(class_48 paramclass_48)
  {
    a8(paramclass_48, this.jdField_field_241_of_type_Class_48);
    a8(this.jdField_field_244_of_type_Class_48, this.jdField_field_242_of_type_Class_48);
    a8(this.jdField_field_240_of_type_Class_48, this.field_243);
    a9(this.field_243, this.jdField_field_242_of_type_Class_48);
    if (this.jdField_field_240_of_type_ArrayOfClass_48 == null)
    {
      this.jdField_field_240_of_type_ArrayOfClass_48 = new class_48[this.jdField_field_244_of_type_Int];
      for (paramclass_48 = 0; paramclass_48 < this.jdField_field_240_of_type_ArrayOfClass_48.length; paramclass_48++) {
        this.jdField_field_240_of_type_ArrayOfClass_48[paramclass_48] = new class_48(Universe.getRandom().nextInt(this.jdField_field_242_of_type_Class_48.field_475), this.jdField_field_241_of_type_Int + Universe.getRandom().nextInt(this.jdField_field_242_of_type_Int - this.jdField_field_241_of_type_Int), Universe.getRandom().nextInt(this.jdField_field_242_of_type_Class_48.field_477));
      }
    }
    for (paramclass_48 = 0; paramclass_48 < this.jdField_field_240_of_type_ArrayOfClass_48.length; paramclass_48++)
    {
      this.jdField_field_240_of_type_JavaxVecmathVector3f.set(this.jdField_field_241_of_type_Class_48.field_475 + 0.5F, 0.0F, this.jdField_field_241_of_type_Class_48.field_477 + 0.5F);
      this.jdField_field_244_of_type_JavaxVecmathVector3f.set(this.jdField_field_242_of_type_Class_48.field_475 / 2.0F, 0.0F, this.jdField_field_242_of_type_Class_48.field_477 / 2.0F);
      this.jdField_field_240_of_type_JavaxVecmathVector3f.sub(this.jdField_field_244_of_type_JavaxVecmathVector3f);
      float f;
      if (((f = this.jdField_field_240_of_type_JavaxVecmathVector3f.length()) < this.jdField_field_242_of_type_Class_48.field_475 / 2.0F + 0.5F) && (f > 5.5F) && (this.jdField_field_241_of_type_Class_48.field_475 == this.jdField_field_240_of_type_ArrayOfClass_48[paramclass_48].field_475) && (this.jdField_field_241_of_type_Class_48.field_477 == this.jdField_field_240_of_type_ArrayOfClass_48[paramclass_48].field_477) && (this.jdField_field_241_of_type_Class_48.field_476 > this.jdField_field_242_of_type_Class_48.field_476 - this.jdField_field_240_of_type_ArrayOfClass_48[paramclass_48].field_476))
      {
        if ((Math.random() < 0.2D) && (this.jdField_field_241_of_type_Class_48.field_476 == this.jdField_field_242_of_type_Class_48.field_476 - this.jdField_field_240_of_type_ArrayOfClass_48[paramclass_48].field_476 + 1)) {
          return 62;
        }
        return 5;
      }
    }
    return 32767;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1097
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */