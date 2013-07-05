package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.Set;
import org.hsqldb.navigator.RangeIterator;
import org.hsqldb.result.Result;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Type;

public class FunctionSQLInvoked extends Expression
{
  RoutineSchema routineSchema;
  Routine routine;
  Expression condition = Expression.EXPR_TRUE;

  FunctionSQLInvoked(RoutineSchema paramRoutineSchema)
  {
    super(paramRoutineSchema.isAggregate() ? 98 : 27);
    this.routineSchema = paramRoutineSchema;
  }

  public void setArguments(Expression[] paramArrayOfExpression)
  {
    this.nodes = paramArrayOfExpression;
  }

  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean)
  {
    HsqlList localHsqlList = this.condition.resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, null, false);
    if (localHsqlList != null)
      ExpressionColumn.checkColumnsResolved(localHsqlList);
    if (isSelfAggregate())
    {
      if (paramHsqlList == null)
        paramHsqlList = new ArrayListIdentity();
      paramHsqlList.add(this);
      return paramHsqlList;
    }
    return super.resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean);
  }

  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    Type[] arrayOfType = new Type[this.nodes.length];
    for (int i = 0; i < this.nodes.length; i++)
    {
      Expression localExpression = this.nodes[i];
      localExpression.resolveTypes(paramSession, this);
      arrayOfType[i] = localExpression.dataType;
    }
    this.routine = this.routineSchema.getSpecificRoutine(arrayOfType);
    for (i = 0; i < this.nodes.length; i++)
      if (this.nodes[i].dataType == null)
        this.nodes[i].dataType = this.routine.getParameterTypes()[i];
    this.dataType = this.routine.getReturnType();
    this.condition.resolveTypes(paramSession, null);
  }

  private Object getValueInternal(Session paramSession, Object[] paramArrayOfObject)
  {
    int i = 0;
    int j = this.routine.getVariableCount();
    int k = this.routine.javaMethodWithConnection ? 1 : 0;
    Object[] arrayOfObject = ValuePool.emptyObjectArray;
    boolean bool = true;
    if (k + this.nodes.length > 0)
    {
      if (this.opType == 98)
      {
        arrayOfObject = new Object[this.routine.getParameterCount()];
        for (int m = 0; m < paramArrayOfObject.length; m++)
          arrayOfObject[(m + 1)] = paramArrayOfObject[m];
      }
      else
      {
        arrayOfObject = new Object[this.nodes.length + k];
      }
      if (!this.routine.isPSM())
      {
        localObject1 = paramSession.getInternalConnection();
        if (k > 0)
          arrayOfObject[0] = localObject1;
      }
    }
    Object localObject1 = this.routine.getParameterTypes();
    for (int n = 0; n < this.nodes.length; n++)
    {
      Expression localExpression = this.nodes[n];
      Object localObject2 = localExpression.getValue(paramSession, localObject1[n]);
      if (localObject2 == null)
      {
        if (this.routine.isNullInputOutput())
          return null;
        if (!this.routine.getParameter(n).isNullable())
          return Result.newErrorResult(Error.error(4811));
      }
      if (this.routine.isPSM())
        arrayOfObject[n] = localObject2;
      else
        arrayOfObject[(n + k)] = localExpression.dataType.convertSQLToJava(paramSession, localObject2);
    }
    Result localResult = this.routine.invoke(paramSession, arrayOfObject, paramArrayOfObject, bool);
    paramSession.releaseInternalConnection();
    if (localResult.isError())
      throw localResult.getException();
    if (i != 0)
      return localResult.valueData;
    return localResult;
  }

  public Object getValue(Session paramSession)
  {
    if (this.opType == 5)
    {
      localObject = paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex);
      return localObject;
    }
    Object localObject = getValueInternal(paramSession, null);
    if ((localObject instanceof Result))
    {
      Result localResult = (Result)localObject;
      if (localResult.isError())
        throw localResult.getException();
      if (localResult.isSimpleValue())
        localObject = localResult.getValueObject();
      else if (localResult.isData())
        localObject = localResult;
      else
        throw Error.error(4605, this.routine.getName().name);
    }
    return localObject;
  }

  public Result getResult(Session paramSession)
  {
    Object localObject = getValueInternal(paramSession, null);
    if ((localObject instanceof Result))
      return (Result)localObject;
    return Result.newPSMResult(localObject);
  }

  void collectObjectNames(Set paramSet)
  {
    paramSet.add(this.routine.getSpecificName());
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(this.routineSchema.getName().getSchemaQualifiedStatementName());
    localStringBuffer.append('(');
    int i = this.nodes.length;
    if (this.opType == 98)
      i = 1;
    for (int j = 0; j < i; j++)
    {
      if (j != 0)
        localStringBuffer.append(',');
      localStringBuffer.append(this.nodes[j].getSQL());
    }
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }

  public String describe(Session paramSession, int paramInt)
  {
    return super.describe(paramSession, paramInt);
  }

  boolean isSelfAggregate()
  {
    return this.routineSchema.isAggregate();
  }

  public boolean isDeterministic()
  {
    return this.routine.isDeterministic();
  }

  public boolean equals(Expression paramExpression)
  {
    if (!(paramExpression instanceof FunctionSQLInvoked))
      return false;
    FunctionSQLInvoked localFunctionSQLInvoked = (FunctionSQLInvoked)paramExpression;
    if ((this.opType == paramExpression.opType) && (this.routineSchema == localFunctionSQLInvoked.routineSchema) && (this.routine == localFunctionSQLInvoked.routine) && (this.condition.equals(localFunctionSQLInvoked.condition)))
      return super.equals(paramExpression);
    return false;
  }

  public Object updateAggregatingValue(Session paramSession, Object paramObject)
  {
    if (!this.condition.testCondition(paramSession))
      return paramObject;
    Object[] arrayOfObject = (Object[])paramObject;
    if (arrayOfObject == null)
      arrayOfObject = new Object[3];
    arrayOfObject[0] = Boolean.FALSE;
    getValueInternal(paramSession, arrayOfObject);
    return arrayOfObject;
  }

  public Object getAggregatedValue(Session paramSession, Object paramObject)
  {
    Object[] arrayOfObject = (Object[])paramObject;
    if (arrayOfObject == null)
      arrayOfObject = new Object[3];
    arrayOfObject[0] = Boolean.TRUE;
    Result localResult = (Result)getValueInternal(paramSession, arrayOfObject);
    if (localResult.isError())
      throw localResult.getException();
    return localResult.getValueObject();
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
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.FunctionSQLInvoked
 * JD-Core Version:    0.6.2
 */