/*  1:   */
/* 10:   */public class mc
/* 11:   */  implements Ak
/* 12:   */{
/* 13:13 */  private final long[] jdField_a_of_type_ArrayOfLong = { 0L, 0L, 0L, 0L, 2047L };
/* 14:14 */  private final String[] jdField_a_of_type_ArrayOfJavaLangString = { "Member 4th Rank", "Member 3rd Rank", "Member 2rd Rank", "Member 1st Rank", "Founder" };
/* 15:   */  
/* 17:   */  public int a;
/* 18:   */  
/* 20:   */  public String getUniqueIdentifier()
/* 21:   */  {
/* 22:22 */    return null;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public boolean isVolatile()
/* 26:   */  {
/* 27:27 */    return false;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public void fromTagStructure(Ah paramAh)
/* 31:   */  {
/* 32:32 */    if (paramAh.a().equals("0")) {
/* 33:33 */      paramAh = (Ah[])paramAh.a();
/* 34:34 */      this.jdField_a_of_type_Int = ((Integer)paramAh[0].a()).intValue();
/* 35:35 */      Ah[] arrayOfAh = (Ah[])paramAh[1].a();
/* 36:36 */      paramAh = (Ah[])paramAh[2].a();
/* 37:37 */      for (int i = 0; i < 5; i++) {
/* 38:38 */        this.jdField_a_of_type_ArrayOfLong[i] = ((Long)arrayOfAh[i].a()).longValue();
/* 39:39 */        this.jdField_a_of_type_ArrayOfJavaLangString[i] = ((String)paramAh[i].a());
/* 40:   */      }
/* 41:41 */      return; }
/* 42:42 */    if (!jdField_a_of_type_Boolean) throw new AssertionError();
/* 43:   */  }
/* 44:   */  
/* 45:   */  public Ah toTagStructure()
/* 46:   */  {
/* 47:47 */    Ah localAh1 = new Ah(Aj.n, null, new Ah[] { new Ah(Aj.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[0])), new Ah(Aj.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[1])), new Ah(Aj.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[2])), new Ah(Aj.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[3])), new Ah(Aj.e, null, Long.valueOf(this.jdField_a_of_type_ArrayOfLong[4])), new Ah(Aj.a, null, null) });
/* 48:   */    
/* 56:56 */    Ah localAh2 = new Ah(Aj.n, null, new Ah[] { new Ah(Aj.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[0]), new Ah(Aj.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[1]), new Ah(Aj.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[2]), new Ah(Aj.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[3]), new Ah(Aj.i, null, this.jdField_a_of_type_ArrayOfJavaLangString[4]), new Ah(Aj.a, null, null) });
/* 57:   */    
/* 66:66 */    return new Ah(Aj.n, "0", new Ah[] { new Ah(Aj.d, null, Integer.valueOf(this.jdField_a_of_type_Int)), localAh1, localAh2, new Ah(Aj.a, null, null) });
/* 67:   */  }
/* 68:   */  
/* 70:   */  public final long[] a()
/* 71:   */  {
/* 72:72 */    return this.jdField_a_of_type_ArrayOfLong;
/* 73:   */  }
/* 74:   */  
/* 76:   */  public final String[] a()
/* 77:   */  {
/* 78:78 */    return this.jdField_a_of_type_ArrayOfJavaLangString;
/* 79:   */  }
/* 80:   */  
/* 81:81 */  public final boolean a(int paramInt) { return (this.jdField_a_of_type_ArrayOfLong[paramInt] & 1L) == 1L; }
/* 82:   */  
/* 83:   */  public final boolean b(int paramInt) {
/* 84:84 */    return (this.jdField_a_of_type_ArrayOfLong[paramInt] & 0x2) == 2L;
/* 85:   */  }
/* 86:   */  
/* 87:87 */  public final boolean c(int paramInt) { return (this.jdField_a_of_type_ArrayOfLong[paramInt] & 0x4) == 4L; }
/* 88:   */  
/* 90:90 */  public final boolean d(int paramInt) { return (this.jdField_a_of_type_ArrayOfLong[paramInt] & 0x8) == 8L; }
/* 91:   */  
/* 92:   */  public final void a(mc parammc) {
/* 93:93 */    for (int i = 0; i < 5; i++) {
/* 94:94 */      if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Int != parammc.jdField_a_of_type_Int)) throw new AssertionError();
/* 95:95 */      this.jdField_a_of_type_ArrayOfLong[i] = parammc.jdField_a_of_type_ArrayOfLong[i];
/* 96:96 */      this.jdField_a_of_type_ArrayOfJavaLangString[i] = parammc.jdField_a_of_type_ArrayOfJavaLangString[i];
/* 97:   */    }
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */