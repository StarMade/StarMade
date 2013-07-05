package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.types.Type;

public class ExpressionValue extends Expression
{
  ExpressionValue(Object paramObject, Type paramType)
  {
    super(1);
    this.nodes = Expression.emptyArray;
    this.dataType = paramType;
    this.valueData = paramObject;
  }

  public byte getNullability()
  {
    return this.valueData == null ? 1 : 0;
  }

  public String getSQL()
  {
    switch (this.opType)
    {
    case 1:
      if (this.valueData == null)
        return "NULL";
      return this.dataType.convertToSQLString(this.valueData);
    }
    throw Error.runtimeError(201, "ExpressionValue");
  }

  protected String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    localStringBuffer.append('\n');
    for (int i = 0; i < paramInt; i++)
      localStringBuffer.append(' ');
    switch (this.opType)
    {
    case 1:
      localStringBuffer.append("VALUE = ").append(this.dataType.convertToSQLString(this.valueData));
      localStringBuffer.append(", TYPE = ").append(this.dataType.getNameString());
      return localStringBuffer.toString();
    }
    throw Error.runtimeError(201, "ExpressionValue");
  }

  Object getValue(Session paramSession, Type paramType)
  {
    if ((this.dataType == paramType) || (this.valueData == null))
      return this.valueData;
    return paramType.convertToType(paramSession, this.valueData, this.dataType);
  }

  public Object getValue(Session paramSession)
  {
    return this.valueData;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ExpressionValue
 * JD-Core Version:    0.6.2
 */