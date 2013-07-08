import com.bulletphysics.linearmath.Transform;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashSet;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class class_337
  extends class_345
  implements class_1388
{
  public Vector3f field_136;
  public byte field_136;
  public String field_136;
  private boolean jdField_field_136_of_type_Boolean = true;
  private class_251 jdField_field_136_of_type_Class_251;
  private Vector4f jdField_field_136_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
  private float jdField_field_136_of_type_Float;
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject = (class_337)paramObject).jdField_field_136_of_type_JavaxVecmathVector3f.equals(this.jdField_field_136_of_type_JavaxVecmathVector3f)) && (paramObject.jdField_field_136_of_type_Byte == this.jdField_field_136_of_type_Byte) && (paramObject.jdField_field_136_of_type_JavaLangString.equals(this.jdField_field_136_of_type_JavaLangString));
  }
  
  public int hashCode()
  {
    return this.jdField_field_136_of_type_JavaxVecmathVector3f.hashCode() + this.jdField_field_136_of_type_Byte + this.jdField_field_136_of_type_JavaLangString.hashCode();
  }
  
  public void fromTagStructure(class_69 paramclass_69) {}
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public class_69 toTagStructure()
  {
    return null;
  }
  
  protected void a1(DataInputStream paramDataInputStream)
  {
    this.jdField_field_136_of_type_JavaxVecmathVector3f = new Vector3f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
    this.jdField_field_136_of_type_JavaLangString = paramDataInputStream.readUTF();
  }
  
  public void a2(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeFloat(this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615);
    paramDataOutputStream.writeFloat(this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616);
    paramDataOutputStream.writeFloat(this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617);
    paramDataOutputStream.writeUTF(this.jdField_field_136_of_type_JavaLangString);
  }
  
  public final int a3()
  {
    return this.jdField_field_136_of_type_Byte;
  }
  
  public final void a4(byte paramByte)
  {
    this.jdField_field_136_of_type_Byte = paramByte;
  }
  
  public String toString()
  {
    return "TEME[pos=" + this.jdField_field_136_of_type_JavaxVecmathVector3f + ", type=" + this.jdField_field_136_of_type_Byte + ", name=" + this.jdField_field_136_of_type_JavaLangString + "]";
  }
  
  public final boolean a5(int paramInt, class_48 paramclass_48)
  {
    if (((paramInt & 0x1) == 1) && ((this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 / 100.0F * 16.0F < paramclass_48.field_475) || (this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 / 100.0F * 16.0F > paramclass_48.field_475 + 1))) {
      return false;
    }
    if (((paramInt & 0x2) == 2) && ((this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 / 100.0F * 16.0F < paramclass_48.field_476) || (this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 / 100.0F * 16.0F > paramclass_48.field_476 + 1))) {
      return false;
    }
    return ((paramInt & 0x4) != 4) || ((this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 / 100.0F * 16.0F >= paramclass_48.field_477) && (this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 / 100.0F * 16.0F <= paramclass_48.field_477 + 1));
  }
  
  public final class_251 a6(class_48 paramclass_48)
  {
    if (this.jdField_field_136_of_type_Class_251 == null)
    {
      Transform localTransform;
      (localTransform = new Transform()).setIdentity();
      localTransform.origin.set(paramclass_48.field_475 * 100 + this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 - 50.0F, paramclass_48.field_476 * 100 + this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 - 50.0F, paramclass_48.field_477 * 100 + this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 - 50.0F);
      this.jdField_field_136_of_type_Class_251 = new class_297(localTransform, this.jdField_field_136_of_type_JavaLangString);
      return this.jdField_field_136_of_type_Class_251;
    }
    this.jdField_field_136_of_type_Class_251.a().origin.set(paramclass_48.field_475 * 100 + this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 - 50.0F, paramclass_48.field_476 * 100 + this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 - 50.0F, paramclass_48.field_477 * 100 + this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 - 50.0F);
    return this.jdField_field_136_of_type_Class_251;
  }
  
  public final boolean a7()
  {
    return this.jdField_field_136_of_type_Boolean;
  }
  
  public final Vector3f a8()
  {
    return this.jdField_field_136_of_type_JavaxVecmathVector3f;
  }
  
  public int a9(class_1380 paramclass_1380)
  {
    if (this.jdField_field_136_of_type_Byte == 4) {
      return 0;
    }
    if (this.jdField_field_136_of_type_Byte == 1) {
      return 8;
    }
    if (this.jdField_field_136_of_type_Byte == 2) {
      return 9;
    }
    if (this.jdField_field_136_of_type_Byte == 5) {
      return 10;
    }
    return 0;
  }
  
  public final float a10(long paramLong)
  {
    return 0.1F;
  }
  
  public final Vector4f a11()
  {
    this.jdField_field_136_of_type_JavaxVecmathVector4f.field_599 = 1.0F;
    if (((class_228.jdField_field_4_of_type_Int & 0x1) == 1) && ((this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 / 100.0F * 16.0F < class_228.jdField_field_4_of_type_Class_48.field_475) || (this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 / 100.0F * 16.0F > class_228.jdField_field_4_of_type_Class_48.field_475 + 1))) {
      this.jdField_field_136_of_type_JavaxVecmathVector4f.field_599 = 0.1F;
    }
    if (((class_228.jdField_field_4_of_type_Int & 0x2) == 2) && ((this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 / 100.0F * 16.0F < class_228.jdField_field_4_of_type_Class_48.field_476) || (this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 / 100.0F * 16.0F > class_228.jdField_field_4_of_type_Class_48.field_476 + 1))) {
      this.jdField_field_136_of_type_JavaxVecmathVector4f.field_599 = 0.1F;
    }
    if (((class_228.jdField_field_4_of_type_Int & 0x4) == 4) && ((this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 / 100.0F * 16.0F < class_228.jdField_field_4_of_type_Class_48.field_477) || (this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 / 100.0F * 16.0F > class_228.jdField_field_4_of_type_Class_48.field_477 + 1))) {
      this.jdField_field_136_of_type_JavaxVecmathVector4f.field_599 = 0.1F;
    }
    return this.jdField_field_136_of_type_JavaxVecmathVector4f;
  }
  
  public final void a12(float paramFloat)
  {
    this.jdField_field_136_of_type_Boolean = true;
    this.jdField_field_136_of_type_Float = paramFloat;
    class_475.field_5.add(this);
  }
  
  public final void a13()
  {
    this.jdField_field_136_of_type_Boolean = false;
    class_475.field_5.remove(this);
  }
  
  public final boolean b2()
  {
    if (((class_228.jdField_field_4_of_type_Int & 0x1) == 1) && ((this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 / 100.0F * 16.0F < class_228.jdField_field_4_of_type_Class_48.field_475) || (this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615 / 100.0F * 16.0F > class_228.jdField_field_4_of_type_Class_48.field_475 + 1))) {
      return false;
    }
    if (((class_228.jdField_field_4_of_type_Int & 0x2) == 2) && ((this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 / 100.0F * 16.0F < class_228.jdField_field_4_of_type_Class_48.field_476) || (this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616 / 100.0F * 16.0F > class_228.jdField_field_4_of_type_Class_48.field_476 + 1))) {
      return false;
    }
    return ((class_228.jdField_field_4_of_type_Int & 0x4) != 4) || ((this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 / 100.0F * 16.0F >= class_228.jdField_field_4_of_type_Class_48.field_477) && (this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617 / 100.0F * 16.0F <= class_228.jdField_field_4_of_type_Class_48.field_477 + 1));
  }
  
  public final float a14()
  {
    return this.jdField_field_136_of_type_Float;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_337
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */