package org.hsqldb.types;

import java.io.Serializable;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;

public final class OtherType
  extends Type
{
  static final OtherType otherType = new OtherType();
  
  private OtherType()
  {
    super(1111, 1111, 0L, 0);
  }
  
  public int displaySize()
  {
    return this.precision > 2147483647L ? 2147483647 : (int)this.precision;
  }
  
  public int getJDBCTypeCode()
  {
    return this.typeCode;
  }
  
  public Class getJDBCClass()
  {
    return Object.class;
  }
  
  public String getJDBCClassName()
  {
    return "java.lang.Object";
  }
  
  public int getSQLGenericTypeCode()
  {
    return this.typeCode;
  }
  
  public int typeCode()
  {
    return this.typeCode;
  }
  
  public String getNameString()
  {
    return "OTHER";
  }
  
  public String getDefinition()
  {
    return "OTHER";
  }
  
  public Type getAggregateType(Type paramType)
  {
    if (paramType == null) {
      return this;
    }
    if (paramType == SQL_ALL_TYPES) {
      return this;
    }
    if (this.typeCode == paramType.typeCode) {
      return this;
    }
    throw Error.error(5562);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    return this;
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null) {
      return -1;
    }
    if (paramObject2 == null) {
      return 1;
    }
    return 0;
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    return paramObject;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    return paramObject;
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    if ((paramObject instanceof Serializable)) {
      return paramObject;
    }
    throw Error.error(5561);
  }
  
  public String convertToString(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return StringConverter.byteArrayToHexString(((JavaObjectData)paramObject).getBytes());
  }
  
  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null) {
      return "NULL";
    }
    return StringConverter.byteArrayToSQLHexString(((JavaObjectData)paramObject).getBytes());
  }
  
  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return ((JavaObjectData)paramObject).getObject();
  }
  
  public boolean canConvertFrom(Type paramType)
  {
    if (paramType.typeCode == this.typeCode) {
      return true;
    }
    return paramType.typeCode == 0;
  }
  
  public boolean isObjectType()
  {
    return true;
  }
  
  public static OtherType getOtherType()
  {
    return otherType;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.OtherType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */