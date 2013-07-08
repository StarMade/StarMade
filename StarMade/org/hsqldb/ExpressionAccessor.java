package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.types.Type;

public class ExpressionAccessor
  extends Expression
{
  ExpressionAccessor(Expression paramExpression1, Expression paramExpression2)
  {
    super(99);
    this.nodes = new Expression[] { paramExpression1, paramExpression2 };
  }
  
  public ColumnSchema getColumn()
  {
    return this.nodes[0].getColumn();
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        paramHsqlList = this.nodes[i].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean);
      }
    }
    return paramHsqlList;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i].resolveTypes(paramSession, this);
      }
    }
    if (this.nodes[0].dataType == null) {
      throw Error.error(5567);
    }
    if (!this.nodes[0].dataType.isArrayType()) {
      throw Error.error(5563);
    }
    this.dataType = this.nodes[0].dataType.collectionBaseType();
    if (this.nodes[1].opType == 8) {
      this.nodes[1].dataType = Type.SQL_INTEGER;
    }
  }
  
  public Object getValue(Session paramSession)
  {
    Object[] arrayOfObject = (Object[])this.nodes[0].getValue(paramSession);
    if (arrayOfObject == null) {
      return null;
    }
    Number localNumber = (Number)this.nodes[1].getValue(paramSession);
    if (localNumber == null) {
      return null;
    }
    if ((localNumber.intValue() < 1) || (localNumber.intValue() > arrayOfObject.length)) {
      throw Error.error(3490);
    }
    return arrayOfObject[(localNumber.intValue() - 1)];
  }
  
  public Object[] getUpdatedArray(Session paramSession, Object[] paramArrayOfObject, Object paramObject, boolean paramBoolean)
  {
    if (paramArrayOfObject == null) {
      throw Error.error(3413);
    }
    Number localNumber = (Number)this.nodes[1].getValue(paramSession);
    if (localNumber == null) {
      throw Error.error(3490);
    }
    int i = localNumber.intValue() - 1;
    if (i < 0) {
      throw Error.error(3490);
    }
    if (i >= this.nodes[0].dataType.arrayLimitCardinality()) {
      throw Error.error(3490);
    }
    Object[] arrayOfObject = paramArrayOfObject;
    if (i >= paramArrayOfObject.length)
    {
      arrayOfObject = new Object[i + 1];
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramArrayOfObject.length);
    }
    else if (paramBoolean)
    {
      arrayOfObject = new Object[paramArrayOfObject.length];
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramArrayOfObject.length);
    }
    arrayOfObject[i] = paramObject;
    return arrayOfObject;
  }
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    String str = getContextSQL(this.nodes[0]);
    localStringBuffer.append(str).append('[');
    localStringBuffer.append(this.nodes[1].getSQL()).append(']');
    return localStringBuffer.toString();
  }
  
  protected String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    localStringBuffer.append('\n');
    for (int i = 0; i < paramInt; i++) {
      localStringBuffer.append(' ');
    }
    localStringBuffer.append("ARRAY ACCESS");
    if (getLeftNode() != null)
    {
      localStringBuffer.append(" array=[");
      localStringBuffer.append(this.nodes[0].describe(paramSession, paramInt + 1));
      localStringBuffer.append(']');
    }
    if (getRightNode() != null)
    {
      localStringBuffer.append(" array_index=[");
      localStringBuffer.append(this.nodes[1].describe(paramSession, paramInt + 1));
      localStringBuffer.append(']');
    }
    return localStringBuffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.ExpressionAccessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */