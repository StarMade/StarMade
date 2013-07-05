/*    */ import java.sql.Array;
/*    */ import java.sql.ResultSet;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public final class kw
/*    */ {
/*    */   public String a;
/*    */   public q a;
/*    */   public int a;
/*    */   public long a;
/*    */   public String b;
/*    */   public String c;
/*    */   public String d;
/*    */   public boolean a;
/*    */   public int b;
/*    */   public Vector3f a;
/*    */   public q b;
/*    */   public q c;
/*    */   public int c;
/*    */ 
/*    */   public kw(ResultSet paramResultSet)
/*    */   {
/* 44 */     this.jdField_a_of_type_JavaLangString = paramResultSet.getString(1).trim();
/* 45 */     this.jdField_a_of_type_Q = new q(paramResultSet.getInt(2), paramResultSet.getInt(3), paramResultSet.getInt(4));
/* 46 */     this.jdField_a_of_type_Int = paramResultSet.getByte(5);
/* 47 */     this.d = paramResultSet.getString(6).trim();
/* 48 */     this.jdField_b_of_type_Int = paramResultSet.getInt(7);
/* 49 */     this.jdField_c_of_type_JavaLangString = paramResultSet.getString(8).trim();
/* 50 */     this.jdField_b_of_type_JavaLangString = paramResultSet.getString(9).trim();
/* 51 */     this.jdField_a_of_type_Long = paramResultSet.getLong(10);
/* 52 */     this.jdField_a_of_type_Boolean = paramResultSet.getBoolean(11);
/*    */ 
/* 55 */     double d1 = ((Double)(
/* 55 */       arrayOfObject = (Object[])paramResultSet.getArray(12)
/* 54 */       .getArray())[
/* 55 */       0]).doubleValue();
/* 56 */     double d2 = ((Double)arrayOfObject[1]).doubleValue();
/* 57 */     double d3 = ((Double)arrayOfObject[2]).doubleValue();
/* 58 */     this.jdField_a_of_type_JavaxVecmathVector3f = new Vector3f((float)d1, (float)d2, (float)d3);
/*    */ 
/* 62 */     Object[] arrayOfObject = (Object[])paramResultSet.getArray(13)
/* 62 */       .getArray();
/* 63 */     this.jdField_b_of_type_Q = new q(((Integer)arrayOfObject[0]).intValue(), ((Integer)arrayOfObject[1]).intValue(), ((Integer)arrayOfObject[2]).intValue());
/* 64 */     this.jdField_c_of_type_Q = new q(((Integer)arrayOfObject[3]).intValue(), ((Integer)arrayOfObject[4]).intValue(), ((Integer)arrayOfObject[5]).intValue());
/* 65 */     this.jdField_c_of_type_Int = paramResultSet.getInt(14);
/*    */ 
/* 79 */     this.jdField_a_of_type_JavaLangString = a(this.jdField_a_of_type_JavaLangString, this.jdField_a_of_type_Int);
/*    */   }
/*    */ 
/*    */   public static String a(String paramString, int paramInt)
/*    */   {
/* 90 */     switch (paramInt) { case 1:
/* 91 */       paramString = "ENTITY_SHOP_" + paramString; break;
/*    */     case 2:
/* 92 */       paramString = "ENTITY_SPACESTATION_" + paramString; break;
/*    */     case 3:
/* 93 */       paramString = "ENTITY_FLOATINGROCK_" + paramString; break;
/*    */     case 4:
/* 94 */       paramString = "ENTITY_PLANET_" + paramString; break;
/*    */     case 5:
/* 95 */       paramString = "ENTITY_SHIP_" + paramString;
/*    */     }
/* 97 */     return paramString;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kw
 * JD-Core Version:    0.6.2
 */