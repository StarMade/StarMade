import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;

public class class_760
  implements class_80
{
  public int field_136;
  public int field_139;
  protected byte field_136;
  
  public final class_762 a149()
  {
    return class_762.values()[this.jdField_field_136_of_type_Byte];
  }
  
  public class_760() {}
  
  public class_760(int paramInt1, int paramInt2, byte paramByte)
  {
    a29(paramInt1, paramInt2);
    this.jdField_field_136_of_type_Byte = paramByte;
  }
  
  public long a28()
  {
    return a150(this.jdField_field_136_of_type_Int, this.field_139);
  }
  
  public static long a150(int paramInt1, int paramInt2)
  {
    int i = Math.min(paramInt1, paramInt2);
    paramInt1 = Math.max(paramInt1, paramInt2);
    return Math.abs(i) * 2147483647 + Math.abs(paramInt1);
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_Int = ((Integer)paramclass_69[0].a4()).intValue();
    this.field_139 = ((Integer)paramclass_69[1].a4()).intValue();
    this.jdField_field_136_of_type_Byte = ((Byte)paramclass_69[2].a4()).byteValue();
  }
  
  public class_69 toTagStructure()
  {
    return new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_136_of_type_Int)), new class_69(class_79.field_551, null, Integer.valueOf(this.field_139)), new class_69(class_79.field_549, null, Byte.valueOf(this.jdField_field_136_of_type_Byte)), new class_69(class_79.field_548, null, null) });
  }
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public void a29(int paramInt1, int paramInt2)
  {
    if ((!jdField_field_136_of_type_Boolean) && (paramInt1 == paramInt2)) {
      throw new AssertionError();
    }
    if ((!jdField_field_136_of_type_Boolean) && ((paramInt1 == 0) || (paramInt2 == 0))) {
      throw new AssertionError();
    }
    this.jdField_field_136_of_type_Int = Math.min(paramInt1, paramInt2);
    this.field_139 = Math.max(paramInt1, paramInt2);
  }
  
  public final boolean a7()
  {
    return this.jdField_field_136_of_type_Byte == 1;
  }
  
  public final boolean b2()
  {
    return this.jdField_field_136_of_type_Byte == 2;
  }
  
  public final boolean c3()
  {
    return this.jdField_field_136_of_type_Byte == 0;
  }
  
  public final void a13()
  {
    this.jdField_field_136_of_type_Byte = 1;
  }
  
  public final void b4()
  {
    this.jdField_field_136_of_type_Byte = 2;
  }
  
  public final void c1()
  {
    this.jdField_field_136_of_type_Byte = 0;
  }
  
  public final void a4(byte paramByte)
  {
    this.jdField_field_136_of_type_Byte = paramByte;
  }
  
  public final boolean a18(int paramInt)
  {
    return (this.jdField_field_136_of_type_Int == paramInt) || (this.field_139 == paramInt);
  }
  
  public final RemoteIntegerArray a151(NetworkObject paramNetworkObject)
  {
    (paramNetworkObject = new RemoteIntegerArray(3, paramNetworkObject)).set(0, Integer.valueOf(this.jdField_field_136_of_type_Int));
    paramNetworkObject.set(1, Integer.valueOf(this.field_139));
    paramNetworkObject.set(2, Integer.valueOf(this.jdField_field_136_of_type_Byte));
    return paramNetworkObject;
  }
  
  public String toString()
  {
    return "Rel[a=" + this.jdField_field_136_of_type_Int + ", b=" + this.field_139 + ", rel=" + a149().name() + "]";
  }
  
  static
  {
    jdField_field_136_of_type_Boolean = !lY.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_760
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */