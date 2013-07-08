package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.RowType;
import org.hsqldb.types.Type;

public class ExpressionAggregate
  extends Expression
{
  boolean isDistinctAggregate;
  ArrayType arrayType;
  
  ExpressionAggregate(int paramInt, boolean paramBoolean, Expression paramExpression)
  {
    super(paramInt);
    this.nodes = new Expression[2];
    this.isDistinctAggregate = paramBoolean;
    this.nodes[0] = paramExpression;
    this.nodes[1] = Expression.EXPR_TRUE;
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
    case 71: 
      localStringBuffer.append(' ').append("COUNT").append('(');
      break;
    case 72: 
      localStringBuffer.append(' ').append("SUM").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 73: 
      localStringBuffer.append(' ').append("MIN").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 74: 
      localStringBuffer.append(' ').append("MAX").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 75: 
      localStringBuffer.append(' ').append("AVG").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 76: 
      localStringBuffer.append(' ').append("EVERY").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 77: 
      localStringBuffer.append(' ').append("SOME").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 78: 
      localStringBuffer.append(' ').append("STDDEV_POP").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 79: 
      localStringBuffer.append(' ').append("STDDEV_SAMP").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 80: 
      localStringBuffer.append(' ').append("VAR_POP").append('(');
      localStringBuffer.append(str).append(')');
      break;
    case 81: 
      localStringBuffer.append(' ').append("VAR_SAMP").append('(');
      localStringBuffer.append(str).append(')');
      break;
    default: 
      throw Error.runtimeError(201, "ExpressionAggregate");
    }
    return localStringBuffer.toString();
  }
  
  protected String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    localStringBuffer.append('\n');
    for (int i = 0; i < paramInt; i++) {
      localStringBuffer.append(' ');
    }
    switch (this.opType)
    {
    case 71: 
      localStringBuffer.append("COUNT").append(' ');
      break;
    case 72: 
      localStringBuffer.append("SUM").append(' ');
      break;
    case 73: 
      localStringBuffer.append("MIN").append(' ');
      break;
    case 74: 
      localStringBuffer.append("MAX").append(' ');
      break;
    case 75: 
      localStringBuffer.append("AVG").append(' ');
      break;
    case 76: 
      localStringBuffer.append("EVERY").append(' ');
      break;
    case 77: 
      localStringBuffer.append("SOME").append(' ');
      break;
    case 78: 
      localStringBuffer.append("STDDEV_POP").append(' ');
      break;
    case 79: 
      localStringBuffer.append("STDDEV_SAMP").append(' ');
      break;
    case 80: 
      localStringBuffer.append("VAR_POP").append(' ');
      break;
    case 81: 
      localStringBuffer.append("VAR_SAMP").append(' ');
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
    HsqlList localHsqlList = this.nodes[1].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, null, false);
    if (localHsqlList != null) {
      ExpressionColumn.checkColumnsResolved(localHsqlList);
    }
    if (paramHsqlList == null) {
      paramHsqlList = new ArrayListIdentity();
    }
    paramHsqlList.add(this);
    return paramHsqlList;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i].resolveTypes(paramSession, this);
      }
    }
    if (this.nodes[0].getDegree() > 1) {
      this.nodes[0].dataType = new RowType(this.nodes[0].nodeDataTypes);
    }
    if (this.nodes[0].isUnresolvedParam()) {
      throw Error.error(5567);
    }
    if (this.isDistinctAggregate)
    {
      if (this.nodes[0].dataType.isLobType()) {
        throw Error.error(5534);
      }
      if (this.nodes[0].dataType.isCharacterType()) {
        this.arrayType = new ArrayType(this.nodes[0].dataType, 2147483647);
      }
    }
    this.dataType = SetFunction.getType(paramSession, this.opType, this.nodes[0].dataType);
    this.nodes[1].resolveTypes(paramSession, null);
  }
  
  public boolean equals(Expression paramExpression)
  {
    if (!(paramExpression instanceof ExpressionAggregate)) {
      return false;
    }
    ExpressionAggregate localExpressionAggregate = (ExpressionAggregate)paramExpression;
    if (this.isDistinctAggregate == localExpressionAggregate.isDistinctAggregate) {
      return super.equals(paramExpression);
    }
    return false;
  }
  
  public Object updateAggregatingValue(Session paramSession, Object paramObject)
  {
    if (!this.nodes[1].testCondition(paramSession)) {
      return paramObject;
    }
    if (paramObject == null) {
      paramObject = new SetFunction(paramSession, this.opType, this.nodes[0].dataType, this.dataType, this.isDistinctAggregate, this.arrayType);
    }
    Object localObject = this.nodes[0].opType == 11 ? ValuePool.INTEGER_1 : this.nodes[0].getValue(paramSession);
    ((SetFunction)paramObject).add(paramSession, localObject);
    return paramObject;
  }
  
  public Object getAggregatedValue(Session paramSession, Object paramObject)
  {
    if (paramObject == null) {
      return this.opType == 71 ? ValuePool.INTEGER_0 : null;
    }
    return ((SetFunction)paramObject).getValue(paramSession);
  }
  
  public Expression getCondition()
  {
    return this.nodes[1];
  }
  
  public boolean hasCondition()
  {
    return this.nodes[1] != Expression.EXPR_TRUE;
  }
  
  public void setCondition(Expression paramExpression)
  {
    this.nodes[1] = paramExpression;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ExpressionAggregate
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */