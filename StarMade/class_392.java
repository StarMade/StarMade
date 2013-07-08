import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;

public final class class_392
{
  private class_48[] jdField_field_785_of_type_ArrayOfClass_48 = new class_48[8];
  private Vector3f[] jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
  private Vector3f[] jdField_field_786_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[4];
  private class_48[] jdField_field_786_of_type_ArrayOfClass_48 = new class_48[4];
  private static float[] jdField_field_785_of_type_ArrayOfFloat = { 1.0F, 0.5F, 0.3333333F, 0.25F };
  private Vector3f jdField_field_785_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_392()
  {
    for (int i = 0; i < 8; i++) {
      this.jdField_field_785_of_type_ArrayOfClass_48[i] = new class_48();
    }
    for (i = 0; i < 4; i++)
    {
      this.jdField_field_786_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
      this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
    }
  }
  
  private void a(class_48 paramclass_48, class_48[] paramArrayOfclass_48, int paramInt, boolean paramBoolean, class_386 paramclass_386)
  {
    this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[2].set(a1(paramclass_48, paramArrayOfclass_48[0], paramArrayOfclass_48[1], paramArrayOfclass_48[2], paramInt, paramclass_386));
    this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[3].set(a1(paramclass_48, paramArrayOfclass_48[2], paramArrayOfclass_48[3], paramArrayOfclass_48[4], paramInt, paramclass_386));
    this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[0].set(a1(paramclass_48, paramArrayOfclass_48[4], paramArrayOfclass_48[5], paramArrayOfclass_48[6], paramInt, paramclass_386));
    this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[1].set(a1(paramclass_48, paramArrayOfclass_48[6], paramArrayOfclass_48[7], paramArrayOfclass_48[0], paramInt, paramclass_386));
    paramArrayOfclass_48 = paramInt << 2;
    paramclass_48 = CubeMeshBufferContainer.a((byte)paramclass_48.field_475, (byte)paramclass_48.field_476, (byte)paramclass_48.field_477);
    if (paramBoolean)
    {
      for (paramInt = 3; paramInt >= 0; paramInt--)
      {
        paramclass_386.a3().a5(paramclass_48, (byte)(int)(FastMath.a5(this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[paramInt].field_615, 0.0F, 1.0F) * 15.0F), paramArrayOfclass_48 + (3 - paramInt), 0);
        paramclass_386.a3().a5(paramclass_48, (byte)(int)(FastMath.a5(this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[paramInt].field_616, 0.0F, 1.0F) * 15.0F), paramArrayOfclass_48 + (3 - paramInt), 1);
        paramclass_386.a3().a5(paramclass_48, (byte)(int)(FastMath.a5(this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[paramInt].field_617, 0.0F, 1.0F) * 15.0F), paramArrayOfclass_48 + (3 - paramInt), 2);
      }
      return;
    }
    for (paramInt = 0; paramInt < 4; paramInt++)
    {
      paramclass_386.a3().a5(paramclass_48, (byte)(int)(FastMath.a5(this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[paramInt].field_615, 0.0F, 1.0F) * 15.0F), paramArrayOfclass_48 + paramInt, 0);
      paramclass_386.a3().a5(paramclass_48, (byte)(int)(FastMath.a5(this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[paramInt].field_616, 0.0F, 1.0F) * 15.0F), paramArrayOfclass_48 + paramInt, 1);
      paramclass_386.a3().a5(paramclass_48, (byte)(int)(FastMath.a5(this.jdField_field_785_of_type_ArrayOfJavaxVecmathVector3f[paramInt].field_617, 0.0F, 1.0F) * 15.0F), paramArrayOfclass_48 + paramInt, 2);
    }
  }
  
  private Vector3f a1(class_48 paramclass_481, class_48 paramclass_482, class_48 paramclass_483, class_48 paramclass_484, int paramInt, class_386 paramclass_386)
  {
    int i = 0;
    this.jdField_field_785_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
    this.jdField_field_786_of_type_ArrayOfClass_48[0] = paramclass_481;
    this.jdField_field_786_of_type_ArrayOfClass_48[1] = paramclass_482;
    this.jdField_field_786_of_type_ArrayOfClass_48[2] = paramclass_483;
    this.jdField_field_786_of_type_ArrayOfClass_48[3] = paramclass_484;
    for (paramclass_481 = 0; paramclass_481 < 4; paramclass_481++) {
      if (paramclass_386.a4(this.jdField_field_786_of_type_ArrayOfClass_48[paramclass_481], paramInt, this.jdField_field_786_of_type_ArrayOfJavaxVecmathVector3f[paramclass_481]))
      {
        this.jdField_field_785_of_type_JavaxVecmathVector3f.add(this.jdField_field_786_of_type_ArrayOfJavaxVecmathVector3f[paramclass_481]);
        i++;
      }
    }
    if (i > 1) {
      this.jdField_field_785_of_type_JavaxVecmathVector3f.scale(jdField_field_785_of_type_ArrayOfFloat[(i - 1)]);
    }
    return this.jdField_field_785_of_type_JavaxVecmathVector3f;
  }
  
  final void a2(class_48 paramclass_48, class_386 paramclass_386)
  {
    this.jdField_field_785_of_type_ArrayOfClass_48[2].b(paramclass_48.field_475 - 1, paramclass_48.field_476, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[3].b(paramclass_48.field_475 - 1, paramclass_48.field_476, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[4].b(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[5].b(paramclass_48.field_475 + 1, paramclass_48.field_476, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[6].b(paramclass_48.field_475 + 1, paramclass_48.field_476, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[7].b(paramclass_48.field_475 + 1, paramclass_48.field_476, paramclass_48.field_477 + 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[0].b(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477 + 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[1].b(paramclass_48.field_475 - 1, paramclass_48.field_476, paramclass_48.field_477 + 1);
    a(paramclass_48, this.jdField_field_785_of_type_ArrayOfClass_48, 3, false, paramclass_386);
  }
  
  final void a3(class_48 paramclass_48, int paramInt, class_386 paramclass_386)
  {
    this.jdField_field_785_of_type_ArrayOfClass_48[4].b(paramclass_48.field_475 + 1, paramclass_48.field_476, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[5].b(paramclass_48.field_475 + 1, paramclass_48.field_476 - 1, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[6].b(paramclass_48.field_475, paramclass_48.field_476 - 1, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[7].b(paramclass_48.field_475 - 1, paramclass_48.field_476 - 1, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[0].b(paramclass_48.field_475 - 1, paramclass_48.field_476, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[1].b(paramclass_48.field_475 - 1, paramclass_48.field_476 + 1, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[2].b(paramclass_48.field_475, paramclass_48.field_476 + 1, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[3].b(paramclass_48.field_475 + 1, paramclass_48.field_476 + 1, paramclass_48.field_477);
    a(paramclass_48, this.jdField_field_785_of_type_ArrayOfClass_48, paramInt, paramInt != 5, paramclass_386);
  }
  
  final void b(class_48 paramclass_48, class_386 paramclass_386)
  {
    this.jdField_field_785_of_type_ArrayOfClass_48[6].b(paramclass_48.field_475, paramclass_48.field_476 + 1, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[7].b(paramclass_48.field_475, paramclass_48.field_476 + 1, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[0].b(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[1].b(paramclass_48.field_475, paramclass_48.field_476 - 1, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[2].b(paramclass_48.field_475, paramclass_48.field_476 - 1, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[3].b(paramclass_48.field_475, paramclass_48.field_476 - 1, paramclass_48.field_477 + 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[4].b(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477 + 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[5].b(paramclass_48.field_475, paramclass_48.field_476 + 1, paramclass_48.field_477 + 1);
    a(paramclass_48, this.jdField_field_785_of_type_ArrayOfClass_48, 1, false, paramclass_386);
  }
  
  final void c(class_48 paramclass_48, class_386 paramclass_386)
  {
    this.jdField_field_785_of_type_ArrayOfClass_48[6].b(paramclass_48.field_475, paramclass_48.field_476 - 1, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[7].b(paramclass_48.field_475, paramclass_48.field_476 - 1, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[0].b(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[1].b(paramclass_48.field_475, paramclass_48.field_476 + 1, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[2].b(paramclass_48.field_475, paramclass_48.field_476 + 1, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[3].b(paramclass_48.field_475, paramclass_48.field_476 + 1, paramclass_48.field_477 + 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[4].b(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477 + 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[5].b(paramclass_48.field_475, paramclass_48.field_476 - 1, paramclass_48.field_477 + 1);
    a(paramclass_48, this.jdField_field_785_of_type_ArrayOfClass_48, 0, false, paramclass_386);
  }
  
  final void d(class_48 paramclass_48, class_386 paramclass_386)
  {
    this.jdField_field_785_of_type_ArrayOfClass_48[0].b(paramclass_48.field_475 - 1, paramclass_48.field_476, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[1].b(paramclass_48.field_475 - 1, paramclass_48.field_476, paramclass_48.field_477 + 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[2].b(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477 + 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[3].b(paramclass_48.field_475 + 1, paramclass_48.field_476, paramclass_48.field_477 + 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[4].b(paramclass_48.field_475 + 1, paramclass_48.field_476, paramclass_48.field_477);
    this.jdField_field_785_of_type_ArrayOfClass_48[5].b(paramclass_48.field_475 + 1, paramclass_48.field_476, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[6].b(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477 - 1);
    this.jdField_field_785_of_type_ArrayOfClass_48[7].b(paramclass_48.field_475 - 1, paramclass_48.field_476, paramclass_48.field_477 - 1);
    a(paramclass_48, this.jdField_field_785_of_type_ArrayOfClass_48, 2, false, paramclass_386);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_392
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */