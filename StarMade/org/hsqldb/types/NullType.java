package org.hsqldb.types;

import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;

public final class NullType
  extends Type
{
  static final NullType nullType = new NullType();
  
  private NullType()
  {
    super(0, 0, 0L, 0);
  }
  
  public int displaySize()
  {
    return 4;
  }
  
  public int getJDBCTypeCode()
  {
    return this.typeCode;
  }
  
  public Class getJDBCClass()
  {
    return Void.class;
  }
  
  public String getJDBCClassName()
  {
    return "java.lang.Void";
  }
  
  public String getNameString()
  {
    return "NULL";
  }
  
  public String getDefinition()
  {
    return "NULL";
  }
  
  public Type getAggregateType(Type paramType)
  {
    return paramType;
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    return paramType;
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2)
  {
    throw Error.runtimeError(201, "NullType");
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    return null;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    return null;
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    return null;
  }
  
  public String convertToString(Object paramObject)
  {
    throw Error.runtimeError(201, "NullType");
  }
  
  public String convertToSQLString(Object paramObject)
  {
    throw Error.runtimeError(201, "NullType");
  }
  
  public boolean canConvertFrom(Type paramType)
  {
    return true;
  }
  
  public static Type getNullType()
  {
    return nullType;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.types.NullType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */