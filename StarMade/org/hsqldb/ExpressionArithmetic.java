package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.navigator.RangeIterator;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

public class ExpressionArithmetic extends Expression
{
  ExpressionArithmetic(int paramInt, Expression paramExpression1, Expression paramExpression2)
  {
    super(paramInt);
    this.nodes = new Expression[2];
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    switch (this.opType)
    {
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
      return;
    }
    throw Error.runtimeError(201, "Expression");
  }

  ExpressionArithmetic(int paramInt, Expression paramExpression)
  {
    super(paramInt);
    this.nodes = new Expression[1];
    this.nodes[0] = paramExpression;
    switch (this.opType)
    {
    case 31:
      return;
    }
    throw Error.runtimeError(201, "Expression");
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    switch (this.opType)
    {
    case 1:
      if (this.valueData == null)
        return "NULL";
      if (this.dataType == null)
        throw Error.runtimeError(201, "Expression");
      return this.dataType.convertToSQLString(this.valueData);
    }
    String str1 = getContextSQL(this.nodes.length > 0 ? this.nodes[0] : null);
    String str2 = getContextSQL(this.nodes.length > 1 ? this.nodes[1] : null);
    switch (this.opType)
    {
    case 91:
      localStringBuffer.append(' ').append("CAST").append('(');
      localStringBuffer.append(str1).append(' ').append("AS").append(' ');
      localStringBuffer.append(this.dataType.getTypeDefinition());
      localStringBuffer.append(')');
      break;
    case 31:
      localStringBuffer.append('-').append(str1);
      break;
    case 32:
      localStringBuffer.append(str1).append('+').append(str2);
      break;
    case 33:
      localStringBuffer.append(str1).append('-').append(str2);
      break;
    case 34:
      localStringBuffer.append(str1).append('*').append(str2);
      break;
    case 35:
      localStringBuffer.append(str1).append('/').append(str2);
      break;
    case 36:
      localStringBuffer.append(str1).append("||").append(str2);
      break;
    default:
      throw Error.runtimeError(201, "Expression");
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
    case 1:
      localStringBuffer.append("VALUE = ").append(this.dataType.convertToSQLString(this.valueData));
      localStringBuffer.append(", TYPE = ").append(this.dataType.getNameString());
      return localStringBuffer.toString();
    case 25:
    case 26:
      localStringBuffer.append("VALUELIST ");
      localStringBuffer.append(" TYPE = ").append(this.dataType.getNameString());
      for (i = 0; i < this.nodes.length; i++)
      {
        localStringBuffer.append(this.nodes[i].describe(paramSession, paramInt + paramInt));
        localStringBuffer.append(' ');
      }
      break;
    case 31:
      localStringBuffer.append("NEGATE ");
      break;
    case 32:
      localStringBuffer.append("ADD ");
      break;
    case 33:
      localStringBuffer.append("SUBTRACT ");
      break;
    case 34:
      localStringBuffer.append("MULTIPLY ");
      break;
    case 35:
      localStringBuffer.append("DIVIDE ");
      break;
    case 36:
      localStringBuffer.append("CONCAT ");
      break;
    case 91:
      localStringBuffer.append("CAST ");
      localStringBuffer.append(this.dataType.getTypeDefinition());
      localStringBuffer.append(' ');
    }
    if (getLeftNode() != null)
    {
      localStringBuffer.append(" arg_left=[");
      localStringBuffer.append(this.nodes[0].describe(paramSession, paramInt + 1));
      localStringBuffer.append(']');
    }
    if (getRightNode() != null)
    {
      localStringBuffer.append(" arg_right=[");
      localStringBuffer.append(this.nodes[1].describe(paramSession, paramInt + 1));
      localStringBuffer.append(']');
    }
    return localStringBuffer.toString();
  }

  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean)
  {
    if (this.opType == 1)
      return paramHsqlList;
    for (int i = 0; i < this.nodes.length; i++)
      if (this.nodes[i] != null)
        paramHsqlList = this.nodes[i].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean);
    return paramHsqlList;
  }

  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    for (int i = 0; i < this.nodes.length; i++)
      if (this.nodes[i] != null)
        this.nodes[i].resolveTypes(paramSession, this);
    switch (this.opType)
    {
    case 1:
      break;
    case 31:
      if ((this.nodes[0].isUnresolvedParam()) || (this.nodes[0].dataType == null))
        throw Error.error(5567);
      this.dataType = this.nodes[0].dataType;
      if (!this.dataType.isNumberType())
        throw Error.error(5563);
      if (this.nodes[0].opType != 1)
        return;
      setAsConstantValue(paramSession);
      break;
    case 32:
      if (((this.nodes[0].dataType != null) && (this.nodes[0].dataType.isCharacterType())) || ((this.nodes[1].dataType != null) && (this.nodes[1].dataType.isCharacterType())))
      {
        this.opType = 36;
        resolveTypesForConcat(paramSession, paramExpression);
      }
      break;
    case 33:
    case 34:
    case 35:
      resolveTypesForArithmetic(paramSession, paramExpression);
      break;
    case 36:
      resolveTypesForConcat(paramSession, paramExpression);
      break;
    }
    throw Error.runtimeError(201, "Expression");
  }

  void resolveTypesForArithmetic(Session paramSession, Expression paramExpression)
  {
    if ((this.nodes[0].isUnresolvedParam()) && (this.nodes[1].isUnresolvedParam()))
      this.nodes[0].dataType = (this.nodes[1].dataType = Type.SQL_INTEGER);
    if ((this.nodes[0].dataType == null) && (this.nodes[1].dataType == null))
      this.nodes[0].dataType = (this.nodes[1].dataType = Type.SQL_INTEGER);
    if (this.nodes[0].isUnresolvedParam())
    {
      if (this.nodes[1].dataType == null)
        throw Error.error(5567);
      if ((this.nodes[1].dataType.isIntervalType()) && (paramExpression != null))
        switch (paramExpression.opType)
        {
        case 41:
        case 42:
        case 43:
        case 44:
        case 45:
          for (int i = 0; i < paramExpression.nodes.length; i++)
            if (paramExpression.nodes[i] != this)
            {
              if ((paramExpression.nodes[i].dataType == null) || (!paramExpression.nodes[i].dataType.isDateTimeType()))
                break;
              this.nodes[0].dataType = paramExpression.nodes[i].dataType;
              break;
            }
          break;
        }
      if (this.nodes[0].dataType == null)
        switch (this.opType)
        {
        case 33:
          if (this.nodes[1].dataType.isIntervalType())
            this.nodes[0].dataType = Type.SQL_TIMESTAMP_WITH_TIME_ZONE;
          break;
        case 32:
          if (this.nodes[1].dataType.isDateTimeType())
          {
            if (this.nodes[1].dataType.typeComparisonGroup == 91)
              this.nodes[0].dataType = Type.SQL_INTERVAL_YEAR_TO_MONTH_MAX_PRECISION;
            else
              this.nodes[0].dataType = Type.SQL_INTERVAL_DAY_TO_SECOND_MAX_PRECISION;
          }
          else if (this.nodes[1].dataType.isIntervalType())
            this.nodes[0].dataType = Type.SQL_TIMESTAMP_WITH_TIME_ZONE;
          break;
        }
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = this.nodes[1].dataType;
    }
    else if (this.nodes[1].isUnresolvedParam())
    {
      if (this.nodes[0].dataType == null)
        throw Error.error(5567);
      switch (this.opType)
      {
      case 34:
      case 35:
        if (this.nodes[0].dataType.isIntervalType())
          this.nodes[1].dataType = Type.SQL_DECIMAL;
        else
          this.nodes[1].dataType = this.nodes[0].dataType;
        break;
      case 32:
      case 33:
        if (this.nodes[0].dataType.isDateTimeType())
        {
          if ((this.dataType != null) && (this.dataType.isIntervalType()))
            this.nodes[1].dataType = this.nodes[0].dataType;
          else if (this.nodes[0].dataType.typeComparisonGroup == 91)
            this.nodes[1].dataType = Type.SQL_INTERVAL_YEAR_TO_MONTH_MAX_PRECISION;
          else
            this.nodes[1].dataType = Type.SQL_INTERVAL_DAY_TO_SECOND_MAX_PRECISION;
        }
        else
          this.nodes[1].dataType = this.nodes[0].dataType;
        break;
      }
    }
    if ((this.nodes[0].dataType == null) || (this.nodes[1].dataType == null))
      throw Error.error(5567);
    Object localObject;
    if ((this.dataType != null) && (this.dataType.isIntervalType()))
    {
      if ((this.nodes[0].dataType.isDateTimeType()) && (this.nodes[1].dataType.isDateTimeType()))
      {
        if (this.nodes[0].dataType.typeComparisonGroup != this.nodes[1].dataType.typeComparisonGroup)
          throw Error.error(5562);
      }
      else
      {
        localObject = this.nodes[0].dataType.getCombinedType(paramSession, this.nodes[1].dataType, this.opType);
        if (localObject == null)
          throw Error.error(5562);
        if (((Type)localObject).isIntervalType())
        {
          if (((Type)localObject).typeCode != this.dataType.typeCode)
            throw Error.error(5562);
        }
        else if (((Type)localObject).isNumberType())
        {
          this.nodes[0] = new ExpressionOp(this.nodes[0], this.dataType);
          this.nodes[1] = new ExpressionOp(this.nodes[1], this.dataType);
          this.nodes[0].resolveTypes(paramSession, this);
          this.nodes[1].resolveTypes(paramSession, this);
        }
        else
        {
          throw Error.error(5562);
        }
      }
    }
    else
    {
      this.dataType = this.nodes[0].dataType.getCombinedType(paramSession, this.nodes[1].dataType, this.opType);
      if (this.dataType.isDateTimeType())
        if (this.nodes[0].dataType.isIntervalType())
        {
          if (this.opType != 32)
            throw Error.error(5563);
          localObject = this.nodes[0];
          this.nodes[0] = this.nodes[1];
          this.nodes[1] = localObject;
        }
        else if ((this.nodes[1].dataType.isNumberType()) && (!paramSession.database.sqlSyntaxOra))
        {
          throw Error.error(5562);
        }
    }
    if ((this.nodes[0].opType == 1) && (this.nodes[1].opType == 1))
      setAsConstantValue(paramSession);
  }

  void resolveTypesForConcat(Session paramSession, Expression paramExpression)
  {
    if (this.dataType != null)
      return;
    if (this.nodes[0].isUnresolvedParam())
      this.nodes[0].dataType = getParameterType(this.nodes[1].dataType);
    else if (this.nodes[1].isUnresolvedParam())
      this.nodes[1].dataType = getParameterType(this.nodes[0].dataType);
    if (this.nodes[0].dataType == null)
      this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
    if (this.nodes[1].dataType == null)
      this.nodes[1].dataType = Type.SQL_VARCHAR_DEFAULT;
    if ((this.nodes[0].dataType.isBinaryType() ^ this.nodes[1].dataType.isBinaryType()))
      throw Error.error(5563);
    Object localObject;
    if (this.nodes[0].dataType.isArrayType())
    {
      localObject = this.nodes[1];
      if (((Expression)localObject).opType == 99)
      {
        if (paramExpression == null)
          throw Error.error(5563);
        this.nodes[1] = ((Expression)localObject).getLeftNode();
        ((Expression)localObject).nodes[0] = this;
        paramExpression.replaceNode(this, (Expression)localObject);
      }
    }
    if ((this.nodes[0].dataType.isArrayType() ^ this.nodes[1].dataType.isArrayType()))
      throw Error.error(5563);
    if ((this.nodes[0].dataType.isCharacterType()) && (!this.nodes[1].dataType.isCharacterType()))
    {
      if (paramSession.database.sqlEnforceTypes)
        throw Error.error(5562);
      localObject = CharacterType.getCharacterType(12, this.nodes[1].dataType.displaySize(), this.nodes[0].dataType.getCollation());
      this.nodes[1] = ExpressionOp.getCastExpression(paramSession, this.nodes[1], (Type)localObject);
    }
    if ((this.nodes[1].dataType.isCharacterType()) && (!this.nodes[0].dataType.isCharacterType()))
    {
      if (paramSession.database.sqlEnforceTypes)
        throw Error.error(5562);
      localObject = CharacterType.getCharacterType(12, this.nodes[0].dataType.displaySize(), this.nodes[1].dataType.getCollation());
      this.nodes[0] = ExpressionOp.getCastExpression(paramSession, this.nodes[0], (Type)localObject);
    }
    this.dataType = this.nodes[0].dataType.getCombinedType(paramSession, this.nodes[1].dataType, 36);
    if ((this.nodes[0].opType == 1) && (this.nodes[1].opType == 1))
      setAsConstantValue(paramSession);
  }

  private Type getParameterType(Type paramType)
  {
    if (paramType == null)
      return null;
    switch (paramType.typeCode)
    {
    case 1:
    case 12:
      return Type.SQL_VARCHAR_DEFAULT;
    case 40:
      return Type.SQL_CLOB;
    case 60:
    case 61:
      return Type.SQL_VARBINARY_DEFAULT;
    case 30:
      return Type.SQL_BLOB;
    case 14:
    case 15:
      return Type.SQL_BIT_VARYING_MAX_LENGTH;
    case 50:
      return paramType;
    }
    return null;
  }

  public Object getValue(Session paramSession)
  {
    switch (this.opType)
    {
    case 1:
      return this.valueData;
    case 5:
      localObject1 = paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex);
      return localObject1;
    case 31:
      return ((NumberType)this.dataType).negate(this.nodes[0].getValue(paramSession, this.nodes[0].dataType));
    }
    Object localObject1 = this.nodes[0].getValue(paramSession);
    Object localObject2 = this.nodes[1].getValue(paramSession);
    switch (this.opType)
    {
    case 32:
      return this.dataType.add(localObject1, localObject2, this.nodes[1].dataType);
    case 33:
      return this.dataType.subtract(localObject1, localObject2, this.nodes[1].dataType);
    case 34:
      return this.dataType.multiply(localObject1, localObject2);
    case 35:
      return this.dataType.divide(paramSession, localObject1, localObject2);
    case 36:
      if ((!paramSession.database.sqlConcatNulls) && (this.nodes[0].dataType.isCharacterType()))
        if ((localObject1 == null) && (localObject2 != null))
          localObject1 = "";
        else if ((localObject1 != null) && (localObject2 == null))
          localObject2 = "";
      return this.dataType.concat(paramSession, localObject1, localObject2);
    }
    throw Error.runtimeError(201, "Expression");
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ExpressionArithmetic
 * JD-Core Version:    0.6.2
 */