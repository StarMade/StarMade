import java.io.PrintStream;
import java.util.Random;
import org.schema.common.util.ByteUtil;

public final class class_789
  extends class_791
{
  public final String getUniqueIdentifier()
  {
    return "VOIDSYSTEM_" + this.jdField_field_136_of_type_Class_48.field_475 + "_" + this.jdField_field_136_of_type_Class_48.field_476 + "_" + this.jdField_field_136_of_type_Class_48.field_477;
  }
  
  protected final byte[] a191()
  {
    return new byte[8192];
  }
  
  public final int a41(int paramInt)
  {
    return ByteUtil.d(paramInt);
  }
  
  public final String toString()
  {
    return "Void" + this.jdField_field_136_of_type_Class_48;
  }
  
  protected final void b37(Random paramRandom)
  {
    int i = 0;
    this.jdField_field_136_of_type_Class_808 = class_808.field_1069;
    for (int j = 0; j < 16; j++) {
      for (int k = 0; k < 16; k++) {
        for (int m = 0; m < 16; m++)
        {
          if (((m == 8) && (k == 8) && (j == 8) ? 1 : 0) != 0)
          {
            a199(i, this.jdField_field_136_of_type_Class_808);
          }
          else if (a193(m, k, j))
          {
            class_29.a6(m + (this.jdField_field_136_of_type_Class_48.field_475 << 4), k + (this.jdField_field_136_of_type_Class_48.field_476 << 4), j + (this.jdField_field_136_of_type_Class_48.field_477 << 4), this, i, paramRandom);
          }
          else
          {
            class_29.a6(m + (this.jdField_field_136_of_type_Class_48.field_475 << 4), k + (this.jdField_field_136_of_type_Class_48.field_476 << 4), j + (this.jdField_field_136_of_type_Class_48.field_477 << 4), this, i, paramRandom);
            if (a195(i) == class_808.field_1067) {
              a199(i, class_808.field_1066);
            }
          }
          i++;
        }
      }
    }
  }
  
  public final void fromTagStructure(class_69 paramclass_69)
  {
    super.fromTagStructure(paramclass_69);
    this.jdField_field_136_of_type_Class_808 = a195(2184);
    System.err.println("[VOIDSYSTEM] LOADED CENTER SECTOR TYPE: " + this.jdField_field_136_of_type_Class_48 + ": " + this.jdField_field_136_of_type_Class_808);
  }
  
  public static class_48 a192(class_48 paramclass_481, class_48 paramclass_482)
  {
    paramclass_482.field_475 = (paramclass_481.field_475 >= 0 ? paramclass_481.field_475 / 16 : paramclass_481.field_475 / 16 - 1);
    paramclass_482.field_476 = (paramclass_481.field_476 >= 0 ? paramclass_481.field_476 / 16 : paramclass_481.field_476 / 16 - 1);
    paramclass_482.field_477 = (paramclass_481.field_477 >= 0 ? paramclass_481.field_477 / 16 : paramclass_481.field_477 / 16 - 1);
    return paramclass_482;
  }
  
  public final void a82(byte[] paramArrayOfByte)
  {
    super.a82(paramArrayOfByte);
    this.jdField_field_136_of_type_Class_808 = a195(2184);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_789
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */