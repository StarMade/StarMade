package org.hsqldb.types;

import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.store.BitMap;

public final class BooleanType extends Type
{
  static final BooleanType booleanType = new BooleanType();

  private BooleanType()
  {
    super(16, 16, 0L, 0);
  }

  public int displaySize()
  {
    return 5;
  }

  public int getJDBCTypeCode()
  {
    return 16;
  }

  public Class getJDBCClass()
  {
    return Boolean.class;
  }

  public String getJDBCClassName()
  {
    return "java.lang.Boolean";
  }

  public String getNameString()
  {
    return "BOOLEAN";
  }

  public String getDefinition()
  {
    return "BOOLEAN";
  }

  public boolean isBooleanType()
  {
    return true;
  }

  public Type getAggregateType(Type paramType)
  {
    if (paramType == null)
      return this;
    if (paramType == SQL_ALL_TYPES)
      return this;
    if (this.typeCode == paramType.typeCode)
      return this;
    if (paramType.isCharacterType())
      return paramType.getAggregateType(this);
    throw Error.error(5562);
  }

  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    switch (paramInt)
    {
    case 41:
      if (paramType.isBooleanType())
        return this;
      break;
    }
    throw Error.error(5562);
  }

  public int compare(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2)
      return 0;
    if (paramObject1 == null)
      return -1;
    if (paramObject2 == null)
      return 1;
    boolean bool1 = ((Boolean)paramObject1).booleanValue();
    boolean bool2 = ((Boolean)paramObject2).booleanValue();
    return bool2 ? -1 : bool1 == bool2 ? 0 : 1;
  }

  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    return paramObject;
  }

  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null)
      return paramObject;
    switch (paramType.typeCode)
    {
    case 16:
      return paramObject;
    case 14:
    case 15:
      BinaryData localBinaryData = (BinaryData)paramObject;
      if (localBinaryData.bitLength(paramSessionInterface) == 1L)
        return BitMap.isSet(localBinaryData.getBytes(), 0) ? Boolean.TRUE : Boolean.FALSE;
      break;
    case 40:
      paramObject = Type.SQL_VARCHAR.convertToType(paramSessionInterface, paramObject, paramType);
    case 1:
    case 12:
    case 100:
      paramObject = ((CharacterType)paramType).trim(paramSessionInterface, paramObject, 32, true, true);
      if (((String)paramObject).equalsIgnoreCase("TRUE"))
        return Boolean.TRUE;
      if (((String)paramObject).equalsIgnoreCase("FALSE"))
        return Boolean.FALSE;
      if (((String)paramObject).equalsIgnoreCase("UNKNOWN"))
        return null;
      break;
    case 2:
    case 3:
      return NumberType.isZero(paramObject) ? Boolean.FALSE : Boolean.TRUE;
    case -6:
    case 4:
    case 5:
    case 25:
      if (((Number)paramObject).longValue() == 0L)
        return Boolean.FALSE;
      return Boolean.TRUE;
    }
    throw Error.error(3438);
  }

  public Object convertToTypeJDBC(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null)
      return paramObject;
    switch (paramType.typeCode)
    {
    case 16:
      return paramObject;
    }
    if (paramType.isLobType())
      throw Error.error(5561);
    if (paramType.isCharacterType())
    {
      if ("0".equals(paramObject))
        return Boolean.FALSE;
      if ("1".equals(paramObject))
        return Boolean.TRUE;
    }
    return convertToType(paramSessionInterface, paramObject, paramType);
  }

  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Boolean))
      return paramObject;
    if ((paramObject instanceof String))
      return convertToType(paramSessionInterface, paramObject, Type.SQL_VARCHAR);
    if ((paramObject instanceof Number))
      return NumberType.isZero(paramObject) ? Boolean.FALSE : Boolean.TRUE;
    throw Error.error(5561);
  }

  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject)
  {
    return convertToDefaultType(paramSessionInterface, paramObject);
  }

  public String convertToString(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return ((Boolean)paramObject).booleanValue() ? "TRUE" : "FALSE";
  }

  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null)
      return "UNKNOWN";
    return ((Boolean)paramObject).booleanValue() ? "TRUE" : "FALSE";
  }

  public boolean canConvertFrom(Type paramType)
  {
    return (paramType.typeCode == 0) || (paramType.isBooleanType()) || (paramType.isCharacterType()) || (paramType.isIntegralType()) || ((paramType.isBitType()) && (paramType.precision == 1L));
  }

  public int canMoveFrom(Type paramType)
  {
    return paramType.isBooleanType() ? 0 : -1;
  }

  public static BooleanType getBooleanType()
  {
    return booleanType;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.BooleanType
 * JD-Core Version:    0.6.2
 */