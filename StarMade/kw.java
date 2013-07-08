/*  1:   */import java.sql.Array;
/*  2:   */import java.sql.ResultSet;
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/* 26:   */public final class kw
/* 27:   */{
/* 28:   */  public String a;
/* 29:   */  public q a;
/* 30:   */  public int a;
/* 31:   */  public long a;
/* 32:   */  public String b;
/* 33:   */  public String c;
/* 34:   */  public String d;
/* 35:   */  public boolean a;
/* 36:   */  public int b;
/* 37:   */  public Vector3f a;
/* 38:   */  public q b;
/* 39:   */  public q c;
/* 40:   */  public int c;
/* 41:   */  
/* 42:   */  public kw(ResultSet paramResultSet)
/* 43:   */  {
/* 44:44 */    this.jdField_a_of_type_JavaLangString = paramResultSet.getString(1).trim();
/* 45:45 */    this.jdField_a_of_type_Q = new q(paramResultSet.getInt(2), paramResultSet.getInt(3), paramResultSet.getInt(4));
/* 46:46 */    this.jdField_a_of_type_Int = paramResultSet.getByte(5);
/* 47:47 */    this.d = paramResultSet.getString(6).trim();
/* 48:48 */    this.jdField_b_of_type_Int = paramResultSet.getInt(7);
/* 49:49 */    this.jdField_c_of_type_JavaLangString = paramResultSet.getString(8).trim();
/* 50:50 */    this.jdField_b_of_type_JavaLangString = paramResultSet.getString(9).trim();
/* 51:51 */    this.jdField_a_of_type_Long = paramResultSet.getLong(10);
/* 52:52 */    this.jdField_a_of_type_Boolean = paramResultSet.getBoolean(11);
/* 53:   */    
/* 55:55 */    double d1 = ((Double)(arrayOfObject = (Object[])paramResultSet.getArray(12).getArray())[0]).doubleValue();
/* 56:56 */    double d2 = ((Double)arrayOfObject[1]).doubleValue();
/* 57:57 */    double d3 = ((Double)arrayOfObject[2]).doubleValue();
/* 58:58 */    this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f((float)d1, (float)d2, (float)d3);
/* 59:   */    
/* 62:62 */    Object[] arrayOfObject = (Object[])paramResultSet.getArray(13).getArray();
/* 63:63 */    this.jdField_b_of_type_Q = new q(((Integer)arrayOfObject[0]).intValue(), ((Integer)arrayOfObject[1]).intValue(), ((Integer)arrayOfObject[2]).intValue());
/* 64:64 */    this.jdField_c_of_type_Q = new q(((Integer)arrayOfObject[3]).intValue(), ((Integer)arrayOfObject[4]).intValue(), ((Integer)arrayOfObject[5]).intValue());
/* 65:65 */    this.jdField_c_of_type_Int = paramResultSet.getInt(14);
/* 66:   */    
/* 79:79 */    this.jdField_a_of_type_JavaLangString = a(this.jdField_a_of_type_JavaLangString, this.jdField_a_of_type_Int);
/* 80:   */  }
/* 81:   */  
/* 88:   */  public static String a(String paramString, int paramInt)
/* 89:   */  {
/* 90:90 */    switch (paramInt) {
/* 91:91 */    case 1:  paramString = "ENTITY_SHOP_" + paramString;break;
/* 92:92 */    case 2:  paramString = "ENTITY_SPACESTATION_" + paramString;break;
/* 93:93 */    case 3:  paramString = "ENTITY_FLOATINGROCK_" + paramString;break;
/* 94:94 */    case 4:  paramString = "ENTITY_PLANET_" + paramString;break;
/* 95:95 */    case 5:  paramString = "ENTITY_SHIP_" + paramString;
/* 96:   */    }
/* 97:97 */    return paramString;
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */