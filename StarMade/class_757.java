import java.sql.Array;
import java.sql.ResultSet;
import javax.vecmath.Vector3f;

public final class class_757
{
  public String field_1017;
  public class_48 field_1017;
  public int field_1017;
  public long field_1017;
  public String field_1018;
  public String field_1019;
  public String field_1020;
  public boolean field_1017;
  public int field_1018;
  public Vector3f field_1017;
  public class_48 field_1018;
  public class_48 field_1019;
  public int field_1019;
  
  public class_757(ResultSet paramResultSet)
  {
    this.jdField_field_1017_of_type_JavaLangString = paramResultSet.getString(1).trim();
    this.jdField_field_1017_of_type_Class_48 = new class_48(paramResultSet.getInt(2), paramResultSet.getInt(3), paramResultSet.getInt(4));
    this.jdField_field_1017_of_type_Int = paramResultSet.getByte(5);
    this.field_1020 = paramResultSet.getString(6).trim();
    this.jdField_field_1018_of_type_Int = paramResultSet.getInt(7);
    this.jdField_field_1019_of_type_JavaLangString = paramResultSet.getString(8).trim();
    this.jdField_field_1018_of_type_JavaLangString = paramResultSet.getString(9).trim();
    this.jdField_field_1017_of_type_Long = paramResultSet.getLong(10);
    this.jdField_field_1017_of_type_Boolean = paramResultSet.getBoolean(11);
    double d1 = ((Double)(arrayOfObject = (Object[])paramResultSet.getArray(12).getArray())[0]).doubleValue();
    double d2 = ((Double)arrayOfObject[1]).doubleValue();
    double d3 = ((Double)arrayOfObject[2]).doubleValue();
    this.jdField_field_1017_of_type_JavaxVecmathVector3f = new Vector3f((float)d1, (float)d2, (float)d3);
    Object[] arrayOfObject = (Object[])paramResultSet.getArray(13).getArray();
    this.jdField_field_1018_of_type_Class_48 = new class_48(((Integer)arrayOfObject[0]).intValue(), ((Integer)arrayOfObject[1]).intValue(), ((Integer)arrayOfObject[2]).intValue());
    this.jdField_field_1019_of_type_Class_48 = new class_48(((Integer)arrayOfObject[3]).intValue(), ((Integer)arrayOfObject[4]).intValue(), ((Integer)arrayOfObject[5]).intValue());
    this.jdField_field_1019_of_type_Int = paramResultSet.getInt(14);
    this.jdField_field_1017_of_type_JavaLangString = a(this.jdField_field_1017_of_type_JavaLangString, this.jdField_field_1017_of_type_Int);
  }
  
  public static String a(String paramString, int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      paramString = "ENTITY_SHOP_" + paramString;
      break;
    case 2: 
      paramString = "ENTITY_SPACESTATION_" + paramString;
      break;
    case 3: 
      paramString = "ENTITY_FLOATINGROCK_" + paramString;
      break;
    case 4: 
      paramString = "ENTITY_PLANET_" + paramString;
      break;
    case 5: 
      paramString = "ENTITY_SHIP_" + paramString;
    }
    return paramString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_757
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */