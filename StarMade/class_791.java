import java.io.PrintStream;
import java.util.Random;
import javax.vecmath.Vector3f;
import org.schema.common.util.ByteUtil;

public abstract class class_791
  implements class_80
{
  public long field_136;
  public final class_48 field_136;
  public byte[] field_136;
  public class_808 field_136;
  public long field_139 = -1L;
  
  public class_791()
  {
    this.jdField_field_136_of_type_Class_48 = new class_48();
    this.jdField_field_136_of_type_Long = System.currentTimeMillis();
  }
  
  public static boolean a193(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 = ByteUtil.d(paramInt1);
    paramInt2 = ByteUtil.d(paramInt2);
    paramInt3 = ByteUtil.d(paramInt3);
    Vector3f localVector3f;
    (localVector3f = new Vector3f()).set(paramInt1 - 8, paramInt2 - 8, paramInt3 - 8);
    return localVector3f.length() < 6.5F;
  }
  
  public static boolean a19(class_48 paramclass_48)
  {
    return a193(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  protected abstract byte[] a191();
  
  public final void a194(Random paramRandom)
  {
    this.jdField_field_136_of_type_ArrayOfByte = a191();
    b37(paramRandom);
  }
  
  protected abstract void b37(Random paramRandom);
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_558, "pos", this.jdField_field_136_of_type_Class_48);
    class_69 localclass_692 = new class_69(class_79.field_555, null, this.jdField_field_136_of_type_ArrayOfByte);
    new class_69(class_79.field_552, "simStartMod", Long.valueOf(this.jdField_field_136_of_type_Long % 1200000L));
    return new class_69(class_79.field_561, "StarSystem", new class_69[] { localclass_691, localclass_692, new class_69(class_79.field_548, null, null) });
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_Class_48.b1((class_48)paramclass_69[0].a4());
    this.jdField_field_136_of_type_ArrayOfByte = ((byte[])paramclass_69[1].a4());
    if (paramclass_69[2].a3() == class_79.field_552)
    {
      long l = ((Long)paramclass_69[2].a4()).longValue();
      this.jdField_field_136_of_type_Long = (this.jdField_field_136_of_type_Long - 1200000L + l);
    }
    System.err.println("Loaded starsystem from disk: " + this.jdField_field_136_of_type_Class_48);
  }
  
  public final class_808 a195(int paramInt)
  {
    paramInt <<= 1;
    return class_808.values()[this.jdField_field_136_of_type_ArrayOfByte[paramInt]];
  }
  
  public final class_808 a196(class_48 paramclass_48)
  {
    int i = a41(paramclass_48.field_475);
    int j = a41(paramclass_48.field_476);
    paramclass_48 = a41(paramclass_48.field_477);
    return a195(a202(i, j, paramclass_48));
  }
  
  public final class_810 a197(class_48 paramclass_48)
  {
    int i = a41(paramclass_48.field_475);
    int j = a41(paramclass_48.field_476);
    paramclass_48 = a41(paramclass_48.field_477);
    i = a202(i, j, paramclass_48);
    paramclass_48 = this;
    i <<= 1;
    return class_810.values()[paramclass_48.jdField_field_136_of_type_ArrayOfByte[(i + 1)]];
  }
  
  public final class_780 a198(class_48 paramclass_48)
  {
    int i = a41(paramclass_48.field_475);
    int j = a41(paramclass_48.field_476);
    paramclass_48 = a41(paramclass_48.field_477);
    i = a202(i, j, paramclass_48);
    paramclass_48 = this;
    i <<= 1;
    return class_780.values()[paramclass_48.jdField_field_136_of_type_ArrayOfByte[(i + 1)]];
  }
  
  public final void a199(int paramInt, class_808 paramclass_808)
  {
    paramInt <<= 1;
    this.jdField_field_136_of_type_ArrayOfByte[paramInt] = ((byte)paramclass_808.ordinal());
  }
  
  public final void a200(int paramInt, class_810 paramclass_810)
  {
    paramInt <<= 1;
    this.jdField_field_136_of_type_ArrayOfByte[(paramInt + 1)] = ((byte)paramclass_810.ordinal());
  }
  
  public final void a201(int paramInt, class_780 paramclass_780)
  {
    paramInt <<= 1;
    this.jdField_field_136_of_type_ArrayOfByte[(paramInt + 1)] = ((byte)paramclass_780.ordinal());
  }
  
  public abstract int a41(int paramInt);
  
  public static int a202(int paramInt1, int paramInt2, int paramInt3)
  {
    return (paramInt3 << 8) + (paramInt2 << 4) + paramInt1;
  }
  
  public static boolean b38(class_48 paramclass_48)
  {
    int i = ByteUtil.d(paramclass_48.field_475);
    int j = ByteUtil.d(paramclass_48.field_476);
    paramclass_48 = ByteUtil.d(paramclass_48.field_477);
    return (i == 8) && (j == 8) && (paramclass_48 == 8);
  }
  
  public void a82(byte[] paramArrayOfByte)
  {
    this.jdField_field_136_of_type_ArrayOfByte = paramArrayOfByte;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_791
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */