import java.util.ArrayList;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.schema.schine.network.client.ClientState;

public class class_934
  extends class_1414
{
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_930 jdField_field_89_of_type_Class_930;
  public final Vector4f field_89;
  private final Vector4f jdField_field_90_of_type_JavaxVecmathVector4f;
  private UnicodeFont jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont;
  private Vector4f jdField_field_92_of_type_JavaxVecmathVector4f;
  private Vector4f field_93;
  private final ArrayList jdField_field_89_of_type_JavaUtilArrayList = new ArrayList();
  private boolean jdField_field_89_of_type_Boolean;
  private Vector3f jdField_field_92_of_type_JavaxVecmathVector3f = new Vector3f();
  private class_938 jdField_field_89_of_type_Class_938;
  private boolean jdField_field_90_of_type_Boolean = true;
  
  public class_934(ClientState paramClientState, int paramInt1, int paramInt2, Object paramObject, class_1412 paramclass_1412)
  {
    this(paramClientState, paramInt1, paramInt2, new Vector4f(0.3F, 0.3F, 0.6F, 0.9F), new Vector4f(0.99F, 0.99F, 0.99F, 1.0F), class_29.o(), paramObject, paramclass_1412);
  }
  
  public class_934(ClientState paramClientState, int paramInt1, int paramInt2, Object paramObject, class_1412 paramclass_1412, class_938 paramclass_938)
  {
    this(paramClientState, paramInt1, paramInt2, new Vector4f(0.3F, 0.3F, 0.6F, 0.9F), new Vector4f(0.99F, 0.99F, 0.99F, 1.0F), class_29.o(), paramObject, paramclass_1412, paramclass_938);
  }
  
  public class_934(ClientState paramClientState, int paramInt1, int paramInt2, Vector4f paramVector4f1, Vector4f paramVector4f2, UnicodeFont paramUnicodeFont, Object paramObject, class_1412 paramclass_1412, class_938 paramclass_938)
  {
    super(paramClientState, paramInt1, paramInt2);
    this.jdField_field_90_of_type_JavaxVecmathVector4f = paramVector4f1;
    this.jdField_field_89_of_type_JavaxVecmathVector4f = paramVector4f2;
    this.jdField_field_92_of_type_JavaxVecmathVector4f = new Vector4f(0.8F, 0.8F, 1.0F, 1.0F);
    this.field_93 = new Vector4f(1.0F, 0.8F, 0.8F, 1.0F);
    this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont = paramUnicodeFont;
    a141(paramclass_1412);
    this.field_96 = true;
    this.jdField_field_89_of_type_JavaUtilArrayList.add(paramObject);
    this.jdField_field_89_of_type_Class_938 = paramclass_938;
  }
  
  public class_934(ClientState paramClientState, int paramInt1, int paramInt2, Vector4f paramVector4f1, Vector4f paramVector4f2, UnicodeFont paramUnicodeFont, Object paramObject, class_1412 paramclass_1412)
  {
    this(paramClientState, paramInt1, paramInt2, paramVector4f1, paramVector4f2, paramUnicodeFont, paramObject, paramclass_1412, null);
  }
  
  public void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    if (!this.jdField_field_90_of_type_Boolean)
    {
      this.jdField_field_89_of_type_Class_930.c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
      this.jdField_field_89_of_type_Class_930.field_89.field_1779 /= 2.0F;
      this.field_96 = false;
    }
    else if ((this.jdField_field_89_of_type_Class_938 != null) && ((this.jdField_field_89_of_type_Class_938.f()) || (!this.jdField_field_89_of_type_Class_938.c()) || (this.jdField_field_89_of_type_Class_938.e())))
    {
      this.jdField_field_89_of_type_Class_930.c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
      this.field_96 = false;
    }
    else if (a_())
    {
      if (Mouse.isButtonDown(0)) {
        this.jdField_field_89_of_type_Class_930.c6(this.field_93);
      } else {
        this.jdField_field_89_of_type_Class_930.c6(this.jdField_field_92_of_type_JavaxVecmathVector4f);
      }
    }
    else
    {
      this.jdField_field_89_of_type_Class_930.c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
    }
    this.jdField_field_89_of_type_Class_930.c12(this.jdField_field_92_of_type_JavaxVecmathVector3f);
    super.b();
    this.field_96 = true;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930 = new class_930((int)this.jdField_field_90_of_type_Float, (int)this.jdField_field_89_of_type_Float, this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont, a24());
    this.jdField_field_89_of_type_Class_930.field_90 = this.jdField_field_89_of_type_JavaUtilArrayList;
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), this.jdField_field_90_of_type_Float, this.jdField_field_89_of_type_Float, this.jdField_field_90_of_type_JavaxVecmathVector4f);
    this.jdField_field_89_of_type_Class_1402.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_1402.field_92 = 3.0F;
    a9(this.jdField_field_89_of_type_Class_1402);
    super.c();
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  public final void b17(int paramInt1, int paramInt2)
  {
    this.jdField_field_92_of_type_JavaxVecmathVector3f.set(paramInt1, paramInt2, 0.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_934
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */