import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteStringArray;

public final class class_629
  extends class_760
{
  private String field_136;
  private String field_139;
  
  public final long a28()
  {
    return Math.abs(this.jdField_field_136_of_type_Int) * 2147483647 + Math.abs(this.jdField_field_139_of_type_Int);
  }
  
  public final void a29(int paramInt1, int paramInt2)
  {
    this.jdField_field_136_of_type_Int = paramInt1;
    this.jdField_field_139_of_type_Int = paramInt2;
  }
  
  public final void a30(String paramString1, int paramInt1, int paramInt2, byte paramByte, String paramString2)
  {
    this.jdField_field_136_of_type_Int = paramInt1;
    this.jdField_field_139_of_type_Int = paramInt2;
    this.jdField_field_136_of_type_Byte = paramByte;
    this.jdField_field_136_of_type_JavaLangString = paramString2;
    this.jdField_field_139_of_type_JavaLangString = paramString1;
  }
  
  public final String a()
  {
    return this.jdField_field_136_of_type_JavaLangString;
  }
  
  public final RemoteStringArray a31(NetworkObject paramNetworkObject)
  {
    (paramNetworkObject = new RemoteStringArray(5, paramNetworkObject)).set(0, String.valueOf(this.jdField_field_136_of_type_Int));
    paramNetworkObject.set(1, String.valueOf(this.jdField_field_139_of_type_Int));
    paramNetworkObject.set(2, String.valueOf(this.jdField_field_136_of_type_Byte));
    paramNetworkObject.set(3, this.jdField_field_136_of_type_JavaLangString);
    paramNetworkObject.set(4, this.jdField_field_139_of_type_JavaLangString);
    return paramNetworkObject;
  }
  
  public final void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_Int = ((Integer)paramclass_69[0].a4()).intValue();
    this.jdField_field_139_of_type_Int = ((Integer)paramclass_69[1].a4()).intValue();
    this.jdField_field_136_of_type_Byte = ((Byte)paramclass_69[2].a4()).byteValue();
    this.jdField_field_136_of_type_JavaLangString = ((String)paramclass_69[3].a4());
    this.jdField_field_139_of_type_JavaLangString = ((String)paramclass_69[4].a4());
  }
  
  public final class_69 toTagStructure()
  {
    return new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_136_of_type_Int)), new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_139_of_type_Int)), new class_69(class_79.field_549, null, Byte.valueOf(this.jdField_field_136_of_type_Byte)), new class_69(class_79.field_556, null, this.jdField_field_136_of_type_JavaLangString), new class_69(class_79.field_556, null, this.jdField_field_139_of_type_JavaLangString), new class_69(class_79.field_548, null, null) });
  }
  
  public final String b()
  {
    return this.jdField_field_139_of_type_JavaLangString;
  }
  
  public final String toString()
  {
    return "RelOffer[a=" + this.jdField_field_136_of_type_Int + ", b=" + this.jdField_field_139_of_type_Int + ", rel=" + a149().name() + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_629
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */