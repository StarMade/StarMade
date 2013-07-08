/*  1:   */import org.schema.schine.network.objects.NetworkObject;
/*  2:   */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*  3:   */
/* 10:   */public final class ma
/* 11:   */  extends lY
/* 12:   */{
/* 13:   */  private String a;
/* 14:   */  private String b;
/* 15:   */  
/* 16:   */  public final long a()
/* 17:   */  {
/* 18:18 */    return Math.abs(this.jdField_a_of_type_Int) * 2147483647 + Math.abs(this.jdField_b_of_type_Int);
/* 19:   */  }
/* 20:   */  
/* 24:   */  public final void a(int paramInt1, int paramInt2)
/* 25:   */  {
/* 26:26 */    this.jdField_a_of_type_Int = paramInt1;
/* 27:27 */    this.jdField_b_of_type_Int = paramInt2;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public final void a(String paramString1, int paramInt1, int paramInt2, byte paramByte, String paramString2) {
/* 31:31 */    this.jdField_a_of_type_Int = paramInt1;
/* 32:32 */    this.jdField_b_of_type_Int = paramInt2;
/* 33:33 */    this.jdField_a_of_type_Byte = paramByte;
/* 34:34 */    this.jdField_a_of_type_JavaLangString = paramString2;
/* 35:35 */    this.jdField_b_of_type_JavaLangString = paramString1;
/* 36:   */  }
/* 37:   */  
/* 40:   */  public final String a()
/* 41:   */  {
/* 42:42 */    return this.jdField_a_of_type_JavaLangString;
/* 43:   */  }
/* 44:   */  
/* 45:   */  public final RemoteStringArray a(NetworkObject paramNetworkObject) {
/* 46:46 */    (paramNetworkObject = new RemoteStringArray(5, paramNetworkObject)).set(0, String.valueOf(this.jdField_a_of_type_Int));
/* 47:47 */    paramNetworkObject.set(1, String.valueOf(this.jdField_b_of_type_Int));
/* 48:48 */    paramNetworkObject.set(2, String.valueOf(this.jdField_a_of_type_Byte));
/* 49:49 */    paramNetworkObject.set(3, this.jdField_a_of_type_JavaLangString);
/* 50:50 */    paramNetworkObject.set(4, this.jdField_b_of_type_JavaLangString);
/* 51:51 */    return paramNetworkObject;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public final void fromTagStructure(Ah paramAh) {
/* 55:55 */    paramAh = (Ah[])paramAh.a();
/* 56:   */    
/* 57:57 */    this.jdField_a_of_type_Int = ((Integer)paramAh[0].a()).intValue();
/* 58:58 */    this.jdField_b_of_type_Int = ((Integer)paramAh[1].a()).intValue();
/* 59:59 */    this.jdField_a_of_type_Byte = ((Byte)paramAh[2].a()).byteValue();
/* 60:60 */    this.jdField_a_of_type_JavaLangString = ((String)paramAh[3].a());
/* 61:61 */    this.jdField_b_of_type_JavaLangString = ((String)paramAh[4].a());
/* 62:   */  }
/* 63:   */  
/* 64:   */  public final Ah toTagStructure() {
/* 65:65 */    return new Ah(Aj.n, null, new Ah[] { new Ah(Aj.d, null, Integer.valueOf(this.jdField_a_of_type_Int)), new Ah(Aj.d, null, Integer.valueOf(this.jdField_b_of_type_Int)), new Ah(Aj.b, null, Byte.valueOf(this.jdField_a_of_type_Byte)), new Ah(Aj.i, null, this.jdField_a_of_type_JavaLangString), new Ah(Aj.i, null, this.jdField_b_of_type_JavaLangString), new Ah(Aj.a, null, null) });
/* 66:   */  }
/* 67:   */  
/* 76:   */  public final String b()
/* 77:   */  {
/* 78:78 */    return this.jdField_b_of_type_JavaLangString;
/* 79:   */  }
/* 80:   */  
/* 87:   */  public final String toString()
/* 88:   */  {
/* 89:89 */    return "RelOffer[a=" + this.jdField_a_of_type_Int + ", b=" + this.jdField_b_of_type_Int + ", rel=" + a().name() + "]";
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ma
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */