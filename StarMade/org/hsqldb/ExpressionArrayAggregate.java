package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.RowType;
import org.hsqldb.types.Type;

public class ExpressionArrayAggregate extends Expression
{
  boolean isDistinctAggregate;
  SortAndSlice sort;
  String separator = ",";
  ArrayType arrayDataType;
  Type exprType;
  Expression condition = Expression.EXPR_TRUE;

  ExpressionArrayAggregate(int paramInt, boolean paramBoolean, Expression paramExpression, SortAndSlice paramSortAndSlice, String paramString)
  {
    super(paramInt);
    this.isDistinctAggregate = paramBoolean;
    this.sort = paramSortAndSlice;
    if (paramString != null)
      this.separator = paramString;
    if (paramInt == 85)
    {
      this.nodes = new Expression[] { paramExpression };
      return;
    }
    if (paramSortAndSlice == null)
    {
      this.nodes = new Expression[] { paramExpression };
    }
    else
    {
      HsqlArrayList localHsqlArrayList = paramSortAndSlice.getExpressionList();
      this.nodes = new Expression[localHsqlArrayList.size() + 1];
      localHsqlArrayList.toArray(this.nodes);
      this.nodes[localHsqlArrayList.size()] = paramExpression;
      paramSortAndSlice.prepare(1);
    }
  }

  boolean isSelfAggregate()
  {
    return true;
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    String str = getContextSQL(this.nodes.length > 0 ? this.nodes[0] : null);
    switch (this.opType)
    {
    case 82:
      localStringBuffer.append(' ').append("ARRAY_AGG").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 83:
      localStringBuffer.append(' ').append("GROUP_CONCAT").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 85:
      localStringBuffer.append(' ').append("MEDIAN").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 84:
    default:
      throw Error.runtimeError(201, "ExpressionAggregate");
    }
    return localStringBuffer.toString();
  }

  protected String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    localStringBuffer.append('\n');
    for (int i = 0; i < paramInt; i++)
      localStringBuffer.append(' ');
    switch (this.opType)
    {
    case 82:
      localStringBuffer.append("ARRAY_AGG").append(' ');
      break;
    case 83:
      localStringBuffer.append("GROUP_CONCAT").append(' ');
      break;
    case 85:
      localStringBuffer.append("MEDIAN").append(' ');
    case 84:
    }
    if (getLeftNode() != null)
    {
      localStringBuffer.append(" arg=[");
      localStringBuffer.append(this.nodes[0].describe(paramSession, paramInt + 1));
      localStringBuffer.append(']');
    }
    return localStringBuffer.toString();
  }

  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean)
  {
    HsqlList localHsqlList = this.condition.resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, null, false);
    if (localHsqlList != null)
      ExpressionColumn.checkColumnsResolved(localHsqlList);
    if (paramHsqlList == null)
      paramHsqlList = new ArrayListIdentity();
    paramHsqlList.add(this);
    return paramHsqlList;
  }

  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    this.nodeDataTypes = new Type[this.nodes.length];
    for (int i = 0; i < this.nodes.length; i++)
      if (this.nodes[i] != null)
      {
        this.nodes[i].resolveTypes(paramSession, this);
        if (this.nodes[i].isUnresolvedParam())
          throw Error.error(5567);
        if (this.nodes[i].dataType == null)
          throw Error.error(5567);
        this.nodeDataTypes[i] = this.nodes[i].dataType;
      }
    this.exprType = this.nodes[(this.nodes.length - 1)].dataType;
    if (this.exprType.isLobType())
      throw Error.error(5534);
    if (this.exprType.isArrayType())
      throw Error.error(5534);
    RowType localRowType = new RowType(this.nodeDataTypes);
    switch (this.opType)
    {
    case 82:
      this.arrayDataType = new ArrayType(localRowType, 1024);
      this.dataType = new ArrayType(this.exprType, 1024);
      break;
    case 83:
      this.arrayDataType = new ArrayType(localRowType, 1024);
      this.dataType = Type.SQL_VARCHAR_DEFAULT;
      break;
    case 85:
      this.arrayDataType = new ArrayType(this.nodeDataTypes[0], 1024);
      this.dataType = SetFunction.getType(paramSession, 85, this.exprType);
      if (!this.exprType.isNumberType())
        throw Error.error(5563);
      break;
    case 84:
    }
    this.condition.resolveTypes(paramSession, null);
  }

  public boolean equals(Expression paramExpression)
  {
    if (!(paramExpression instanceof ExpressionArrayAggregate))
      return false;
    ExpressionArrayAggregate localExpressionArrayAggregate = (ExpressionArrayAggregate)paramExpression;
    if ((this.opType == paramExpression.opType) && (this.exprSubType == paramExpression.exprSubType) && (this.isDistinctAggregate == localExpressionArrayAggregate.isDistinctAggregate) && (this.separator.equals(localExpressionArrayAggregate.separator)) && (this.condition.equals(localExpressionArrayAggregate.condition)))
      return super.equals(paramExpression);
    return false;
  }

  public Object updateAggregatingValue(Session paramSession, Object paramObject)
  {
    if (!this.condition.testCondition(paramSession))
      return paramObject;
    Object localObject1 = null;
    switch (this.opType)
    {
    case 82:
    case 83:
      localObject2 = new Object[this.nodes.length];
      for (int i = 0; i < this.nodes.length; i++)
        localObject2[i] = this.nodes[i].getValue(paramSession);
      if ((this.opType == 83) && (localObject2[(localObject2.length - 1)] == null))
        return paramObject;
      localObject1 = localObject2;
      break;
    case 85:
      localObject1 = this.nodes[0].getValue(paramSession);
    case 84:
    }
    Object localObject2 = (HsqlArrayList)paramObject;
    if (localObject2 == null)
      localObject2 = new HsqlArrayList();
    ((HsqlArrayList)localObject2).add(localObject1);
    return localObject2;
  }

  public Object getAggregatedValue(Session paramSession, Object paramObject)
  {
    if (paramObject == null)
      return null;
    HsqlArrayList localHsqlArrayList = (HsqlArrayList)paramObject;
    Object[] arrayOfObject = localHsqlArrayList.toArray();
    Object localObject1;
    int i;
    if (this.isDistinctAggregate)
    {
      localObject1 = new SortAndSlice();
      ((SortAndSlice)localObject1).prepareSingleColumn(this.nodes.length - 1);
      this.arrayDataType.sort(paramSession, arrayOfObject, (SortAndSlice)localObject1);
      i = this.arrayDataType.deDuplicate(paramSession, arrayOfObject, (SortAndSlice)localObject1);
      arrayOfObject = (Object[])ArrayUtil.resizeArrayIfDifferent(arrayOfObject, i);
    }
    if (this.sort != null)
      this.arrayDataType.sort(paramSession, arrayOfObject, this.sort);
    Object localObject2;
    Object localObject3;
    switch (this.opType)
    {
    case 82:
      localObject1 = new Object[arrayOfObject.length];
      for (i = 0; i < localHsqlArrayList.size(); i++)
      {
        localObject2 = (Object[])arrayOfObject[i];
        localObject1[i] = localObject2[(localObject2.length - 1)];
      }
      return localObject1;
    case 83:
      localObject1 = new StringBuffer(16 * localHsqlArrayList.size());
      for (i = 0; i < arrayOfObject.length; i++)
      {
        if (i > 0)
          ((StringBuffer)localObject1).append(this.separator);
        localObject2 = (Object[])arrayOfObject[i];
        localObject3 = this.exprType.convertToString(localObject2[(localObject2.length - 1)]);
        ((StringBuffer)localObject1).append((String)localObject3);
      }
      return ((StringBuffer)localObject1).toString();
    case 85:
      localObject1 = new SortAndSlice();
      ((SortAndSlice)localObject1).prepareSingleColumn(1);
      this.arrayDataType.sort(paramSession, arrayOfObject, (SortAndSlice)localObject1);
      i = arrayOfObject.length % 2 == 0 ? 1 : 0;
      if (i != 0)
      {
        localObject2 = arrayOfObject[(arrayOfObject.length / 2 - 1)];
        localObject3 = arrayOfObject[(arrayOfObject.length / 2)];
        Object localObject4 = ((NumberType)this.dataType).add(localObject2, localObject3, this.dataType);
        return ((NumberType)this.dataType).divide(paramSession, localObject4, Integer.valueOf(2));
      }
      return this.dataType.convertToType(paramSession, arrayOfObject[(arrayOfObject.length / 2)], this.exprType);
    case 84:
    }
    return null;
  }

  public Expression getCondition()
  {
    return this.condition;
  }

  public boolean hasCondition()
  {
    return (this.condition != null) && (this.condition != Expression.EXPR_TRUE);
  }

  public void setCondition(Expression paramExpression)
  {
    this.condition = paramExpression;
  }

  public Expression duplicate()
  {
    ExpressionArrayAggregate localExpressionArrayAggregate = (ExpressionArrayAggregate)super.duplicate();
    if (this.condition != null)
      localExpressionArrayAggregate.condition = this.condition.duplicate();
    return localExpressionArrayAggregate;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ExpressionArrayAggregate
 * JD-Core Version:    0.6.2
 */