package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.navigator.RangeIterator;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.DTIType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

public class ExpressionLogical extends Expression
{
  boolean noOptimisation;
  boolean isQuantified;
  boolean isTerminal;
  RangeVariable[] rangeArray = RangeVariable.emptyArray;

  ExpressionLogical(int paramInt)
  {
    super(paramInt);
    this.dataType = Type.SQL_BOOLEAN;
  }

  ExpressionLogical(boolean paramBoolean)
  {
    super(1);
    this.dataType = Type.SQL_BOOLEAN;
    this.valueData = (paramBoolean ? Boolean.TRUE : Boolean.FALSE);
  }

  ExpressionLogical(RangeVariable paramRangeVariable1, int paramInt1, RangeVariable paramRangeVariable2, int paramInt2)
  {
    super(41);
    ExpressionColumn localExpressionColumn1 = new ExpressionColumn(paramRangeVariable1, paramInt1);
    ExpressionColumn localExpressionColumn2 = new ExpressionColumn(paramRangeVariable2, paramInt2);
    this.nodes = new Expression[2];
    this.nodes[0] = localExpressionColumn1;
    this.nodes[1] = localExpressionColumn2;
    setEqualityMode();
    this.dataType = Type.SQL_BOOLEAN;
  }

  ExpressionLogical(Expression paramExpression1, Expression paramExpression2)
  {
    super(41);
    this.nodes = new Expression[2];
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    setEqualityMode();
    this.dataType = Type.SQL_BOOLEAN;
  }

  ExpressionLogical(int paramInt, Expression paramExpression1, Expression paramExpression2)
  {
    super(paramInt);
    this.nodes = new Expression[2];
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    switch (this.opType)
    {
    case 41:
    case 42:
    case 43:
    case 44:
    case 45:
      setEqualityMode();
    case 46:
    case 49:
    case 50:
    case 54:
    case 56:
    case 58:
    case 59:
    case 60:
    case 61:
    case 62:
    case 63:
    case 64:
      this.dataType = Type.SQL_BOOLEAN;
      break;
    case 47:
    case 48:
    case 51:
    case 52:
    case 53:
    case 55:
    case 57:
    default:
      throw Error.runtimeError(201, "ExpressionLogical");
    }
  }

  ExpressionLogical(int paramInt, Expression paramExpression1, Expression paramExpression2, Expression paramExpression3)
  {
    super(paramInt);
    this.nodes = new Expression[3];
    this.nodes[0] = paramExpression1;
    this.nodes[1] = paramExpression2;
    this.nodes[2] = paramExpression3;
  }

  ExpressionLogical(int paramInt, Expression paramExpression)
  {
    super(paramInt);
    this.nodes = new Expression[1];
    this.nodes[0] = paramExpression;
    switch (this.opType)
    {
    case 47:
    case 48:
    case 55:
    case 57:
      this.dataType = Type.SQL_BOOLEAN;
      break;
    default:
      throw Error.runtimeError(201, "ExpressionLogical");
    }
    if ((this.opType == 47) && (this.nodes[0].opType == 2))
      this.isSingleColumnNull = true;
    if ((this.opType == 48) && (this.nodes[0].isSingleColumnNull))
      this.isSingleColumnNotNull = true;
  }

  ExpressionLogical(ColumnSchema paramColumnSchema)
  {
    super(48);
    this.nodes = new Expression[1];
    this.dataType = Type.SQL_BOOLEAN;
    Object localObject = new ExpressionColumn(paramColumnSchema);
    localObject = new ExpressionLogical(47, (Expression)localObject);
    this.nodes[0] = localObject;
  }

  void setEqualityMode()
  {
    if (this.nodes[0].opType == 2)
    {
      this.nodes[0].nullability = 0;
      switch (this.nodes[1].opType)
      {
      case 2:
        this.isColumnCondition = true;
        if (this.opType == 41)
          this.isColumnEqual = true;
        this.nodes[1].nullability = 0;
        break;
      case 1:
      case 6:
      case 7:
      case 8:
        this.isSingleColumnCondition = true;
        if (this.opType == 41)
          this.isSingleColumnEqual = true;
        break;
      case 3:
      case 4:
      case 5:
      }
    }
    else if (this.nodes[1].opType == 2)
    {
      this.nodes[1].nullability = 0;
      switch (this.nodes[0].opType)
      {
      case 1:
      case 6:
      case 7:
      case 8:
        this.isSingleColumnCondition = true;
        if (this.opType == 41)
          this.isSingleColumnEqual = true;
        break;
      case 2:
      case 3:
      case 4:
      case 5:
      }
    }
  }

  static ExpressionLogical newNotNullCondition(Expression paramExpression)
  {
    paramExpression = new ExpressionLogical(47, paramExpression);
    return new ExpressionLogical(48, paramExpression);
  }

  static Expression andExpressions(Expression paramExpression1, Expression paramExpression2)
  {
    if (paramExpression1 == null)
      return paramExpression2;
    if (paramExpression2 == null)
      return paramExpression1;
    if ((EXPR_FALSE.equals(paramExpression1)) || (EXPR_FALSE.equals(paramExpression2)))
      return EXPR_FALSE;
    if (paramExpression1 == paramExpression2)
      return paramExpression1;
    return new ExpressionLogical(49, paramExpression1, paramExpression2);
  }

  static Expression orExpressions(Expression paramExpression1, Expression paramExpression2)
  {
    if (paramExpression1 == null)
      return paramExpression2;
    if (paramExpression2 == null)
      return paramExpression1;
    if (paramExpression1 == paramExpression2)
      return paramExpression1;
    return new ExpressionLogical(50, paramExpression1, paramExpression2);
  }

  public void addLeftColumnsForAllAny(RangeVariable paramRangeVariable, OrderedIntHashSet paramOrderedIntHashSet)
  {
    if (this.nodes.length == 0)
      return;
    for (int i = 0; i < this.nodes[0].nodes.length; i++)
    {
      int j = this.nodes[0].nodes[i].getColumnIndex();
      if ((j < 0) || (this.nodes[0].nodes[i].getRangeVariable() != paramRangeVariable))
      {
        paramOrderedIntHashSet.clear();
        return;
      }
      paramOrderedIntHashSet.add(j);
    }
  }

  public void setSubType(int paramInt)
  {
    this.exprSubType = paramInt;
    if ((this.exprSubType == 51) || (this.exprSubType == 52))
      this.isQuantified = true;
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    if (this.opType == 1)
      return super.getSQL();
    String str1 = getContextSQL(this.nodes[0]);
    String str2 = getContextSQL(this.nodes.length > 1 ? this.nodes[1] : null);
    switch (this.opType)
    {
    case 48:
      if (this.nodes[0].opType == 47)
      {
        localStringBuffer.append(getContextSQL(this.nodes[0].nodes[0])).append(' ').append("IS").append(' ').append("NOT").append(' ').append("NULL");
        return localStringBuffer.toString();
      }
      if (this.nodes[0].opType == 58)
      {
        localStringBuffer.append(getContextSQL(this.nodes[0].nodes[0])).append(' ').append("IS").append(' ').append("DISTINCT").append(' ').append("FROM").append(' ').append(getContextSQL(this.nodes[0].nodes[1]));
        return localStringBuffer.toString();
      }
      localStringBuffer.append("NOT").append(' ').append(str1);
      return localStringBuffer.toString();
    case 58:
      localStringBuffer.append(str1).append(' ').append("IS").append(' ').append("NOT").append(' ').append("DISTINCT").append(' ').append("FROM").append(' ').append(str2);
      return localStringBuffer.toString();
    case 47:
      localStringBuffer.append(str1).append(' ').append("IS").append(' ').append("NULL");
      return localStringBuffer.toString();
    case 57:
      localStringBuffer.append(' ').append("UNIQUE").append(' ');
      break;
    case 55:
      localStringBuffer.append(' ').append("EXISTS").append(' ');
      break;
    case 41:
      localStringBuffer.append(str1).append('=').append(str2);
      return localStringBuffer.toString();
    case 42:
      localStringBuffer.append(str1).append(">=").append(str2);
      return localStringBuffer.toString();
    case 43:
      localStringBuffer.append(str1).append('>').append(str2);
      return localStringBuffer.toString();
    case 44:
      localStringBuffer.append(str1).append('<').append(str2);
      return localStringBuffer.toString();
    case 45:
      localStringBuffer.append(str1).append("<=").append(str2);
      return localStringBuffer.toString();
    case 46:
      if ("NULL".equals(str2))
        localStringBuffer.append(str1).append(" IS NOT ").append(str2);
      else
        localStringBuffer.append(str1).append("!=").append(str2);
      return localStringBuffer.toString();
    case 49:
      localStringBuffer.append(str1).append(' ').append("AND").append(' ').append(str2);
      return localStringBuffer.toString();
    case 50:
      localStringBuffer.append(str1).append(' ').append("OR").append(' ').append(str2);
      return localStringBuffer.toString();
    case 54:
      localStringBuffer.append(str1).append(' ').append("IN").append(' ').append(str2);
      return localStringBuffer.toString();
    case 59:
      localStringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(str2);
      return localStringBuffer.toString();
    case 60:
      localStringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(470).append(str2);
      return localStringBuffer.toString();
    case 61:
      localStringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(116).append(str2);
      return localStringBuffer.toString();
    case 62:
      localStringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(299).append(str2);
      return localStringBuffer.toString();
    case 63:
      localStringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(299).append(' ').append(470).append(str2);
      return localStringBuffer.toString();
    case 64:
      localStringBuffer.append(str1).append(' ').append("MATCH").append(' ').append(299).append(' ').append(116).append(str2);
      return localStringBuffer.toString();
    case 51:
    case 52:
    case 53:
    case 56:
    default:
      throw Error.runtimeError(201, "ExpressionLogical");
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
    case 48:
      if (this.nodes[0].opType == 58)
      {
        localStringBuffer.append("DISTINCT");
        return localStringBuffer.toString();
      }
      localStringBuffer.append("NOT");
      break;
    case 58:
      localStringBuffer.append("NOT").append(' ').append("DISTINCT");
      break;
    case 41:
      localStringBuffer.append("EQUAL");
      break;
    case 42:
      localStringBuffer.append("GREATER_EQUAL");
      break;
    case 43:
      localStringBuffer.append("GREATER");
      break;
    case 44:
      localStringBuffer.append("SMALLER");
      break;
    case 45:
      localStringBuffer.append("SMALLER_EQUAL");
      break;
    case 46:
      localStringBuffer.append("NOT_EQUAL");
      break;
    case 49:
      localStringBuffer.append("AND");
      break;
    case 50:
      localStringBuffer.append("OR");
      break;
    case 59:
    case 60:
    case 61:
    case 62:
    case 63:
    case 64:
      localStringBuffer.append("MATCH");
      break;
    case 47:
      localStringBuffer.append("IS").append(' ').append("NULL");
      break;
    case 57:
      localStringBuffer.append("UNIQUE");
      break;
    case 55:
      localStringBuffer.append("EXISTS");
      break;
    case 56:
      localStringBuffer.append("OVERLAPS");
      break;
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 20:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 39:
    case 40:
    case 51:
    case 52:
    case 53:
    case 54:
    default:
      throw Error.runtimeError(201, "ExpressionLogical");
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

  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    if ((this.isQuantified) && (this.nodes[1].opType == 30) && ((this.nodes[1] instanceof ExpressionTable)) && (this.nodes[1].nodes[0].opType == 8))
    {
      this.nodes[0].resolveTypes(paramSession, this);
      this.nodes[1].nodes[0].dataType = new ArrayType(this.nodes[0].dataType, 2147483647);
    }
    for (int i = 0; i < this.nodes.length; i++)
      if (this.nodes[i] != null)
        this.nodes[i].resolveTypes(paramSession, this);
    Object localObject;
    switch (this.opType)
    {
    case 1:
      break;
    case 58:
      changeToRowExpression(0);
      changeToRowExpression(1);
      resolveRowTypes();
      checkRowComparison();
      break;
    case 41:
    case 42:
    case 43:
    case 44:
    case 45:
    case 46:
      resolveTypesForComparison(paramSession, paramExpression);
      break;
    case 49:
      resolveTypesForLogicalOp();
      if (this.nodes[0].opType == 1)
      {
        if (this.nodes[1].opType == 1)
        {
          setAsConstantValue(paramSession);
        }
        else
        {
          localObject = this.nodes[0].getValue(paramSession);
          if ((localObject == null) || (Boolean.FALSE.equals(localObject)))
            setAsConstantValue(Boolean.FALSE);
        }
      }
      else if (this.nodes[1].opType == 1)
      {
        localObject = this.nodes[1].getValue(paramSession);
        if ((localObject == null) || (Boolean.FALSE.equals(localObject)))
          setAsConstantValue(Boolean.FALSE);
      }
      break;
    case 50:
      resolveTypesForLogicalOp();
      if (this.nodes[0].opType == 1)
      {
        if (this.nodes[1].opType == 1)
        {
          setAsConstantValue(paramSession);
        }
        else
        {
          localObject = this.nodes[0].getValue(paramSession);
          if (Boolean.TRUE.equals(localObject))
            setAsConstantValue(Boolean.TRUE);
        }
      }
      else if (this.nodes[1].opType == 1)
      {
        localObject = this.nodes[1].getValue(paramSession);
        if (Boolean.TRUE.equals(localObject))
          setAsConstantValue(Boolean.TRUE);
      }
      break;
    case 47:
      if (this.nodes[0].isUnresolvedParam())
      {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5563);
        this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if (this.nodes[0].opType == 1)
        setAsConstantValue(paramSession);
      break;
    case 48:
      if (this.nodes[0].isUnresolvedParam())
      {
        this.nodes[0].dataType = Type.SQL_BOOLEAN;
      }
      else if (this.nodes[0].opType == 1)
      {
        if (this.nodes[0].dataType.isBooleanType())
          setAsConstantValue(paramSession);
        else
          throw Error.error(5563);
      }
      else
      {
        if ((this.nodes[0].dataType == null) || (!this.nodes[0].dataType.isBooleanType()))
          throw Error.error(5563);
        this.dataType = Type.SQL_BOOLEAN;
      }
      break;
    case 56:
      resolveTypesForOverlaps();
      break;
    case 54:
      resolveTypesForIn(paramSession);
      break;
    case 59:
    case 60:
    case 61:
    case 62:
    case 63:
    case 64:
      resolveTypesForAllAny(paramSession);
      break;
    case 55:
    case 57:
      break;
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 20:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 39:
    case 40:
    case 51:
    case 52:
    case 53:
    default:
      throw Error.runtimeError(201, "ExpressionLogical");
    }
  }

  private void resolveTypesForLogicalOp()
  {
    if (this.nodes[0].isUnresolvedParam())
      this.nodes[0].dataType = Type.SQL_BOOLEAN;
    if (this.nodes[1].isUnresolvedParam())
      this.nodes[1].dataType = Type.SQL_BOOLEAN;
    if ((this.nodes[0].dataType == null) || (this.nodes[1].dataType == null))
      throw Error.error(5571);
    if ((this.nodes[0].opType == 25) || (this.nodes[1].opType == 25))
      throw Error.error(5565);
    if ((Type.SQL_BOOLEAN != this.nodes[0].dataType) || (Type.SQL_BOOLEAN != this.nodes[1].dataType))
      throw Error.error(5568);
  }

  private void resolveTypesForComparison(Session paramSession, Expression paramExpression)
  {
    if ((this.exprSubType == 51) || (this.exprSubType == 52))
    {
      resolveTypesForAllAny(paramSession);
      checkRowComparison();
      return;
    }
    int i = this.nodes[0].getDegree();
    int j = this.nodes[1].getDegree();
    if ((i > 1) || (j > 1))
    {
      if (i != j)
        throw Error.error(5564);
      resolveRowTypes();
      checkRowComparison();
      return;
    }
    if (this.nodes[0].isUnresolvedParam())
      this.nodes[0].dataType = this.nodes[1].dataType;
    else if (this.nodes[1].isUnresolvedParam())
      this.nodes[1].dataType = this.nodes[0].dataType;
    if (this.nodes[0].dataType == null)
      this.nodes[0].dataType = this.nodes[1].dataType;
    else if (this.nodes[1].dataType == null)
      this.nodes[1].dataType = this.nodes[0].dataType;
    if ((this.nodes[0].dataType == null) || (this.nodes[1].dataType == null))
      throw Error.error(5567);
    if ((this.nodes[0].dataType.typeComparisonGroup != this.nodes[1].dataType.typeComparisonGroup) && (!convertDateTimeLiteral(paramSession, this.nodes[0], this.nodes[1])))
      if ((this.nodes[0].dataType.isBitType()) || (this.nodes[0].dataType.isBooleanType()))
      {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5562);
        if (this.nodes[0].dataType.canConvertFrom(this.nodes[1].dataType))
          this.nodes[1] = ExpressionOp.getCastExpression(paramSession, this.nodes[1], this.nodes[0].dataType);
      }
      else if ((this.nodes[1].dataType.isBitType()) || (this.nodes[1].dataType.isBooleanType()))
      {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5562);
        if (this.nodes[1].dataType.canConvertFrom(this.nodes[0].dataType))
          this.nodes[0] = ExpressionOp.getCastExpression(paramSession, this.nodes[0], this.nodes[1].dataType);
      }
      else if (this.nodes[0].dataType.isNumberType())
      {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5562);
        if (this.nodes[0].dataType.canConvertFrom(this.nodes[1].dataType))
          this.nodes[1] = ExpressionOp.getCastExpression(paramSession, this.nodes[1], this.nodes[0].dataType);
      }
      else if (this.nodes[1].dataType.isNumberType())
      {
        if (paramSession.database.sqlEnforceTypes)
          throw Error.error(5562);
        if (this.nodes[1].dataType.canConvertFrom(this.nodes[0].dataType))
          this.nodes[0] = ExpressionOp.getCastExpression(paramSession, this.nodes[0], this.nodes[1].dataType);
      }
      else if (this.nodes[0].dataType.isDateTimeType())
      {
        if ((this.nodes[0].dataType.isDateTimeTypeWithZone() ^ this.nodes[1].dataType.isDateTimeTypeWithZone()))
          this.nodes[0] = new ExpressionOp(this.nodes[0]);
      }
      else
      {
        throw Error.error(5562);
      }
    if ((this.opType != 41) && (this.opType != 46) && ((this.nodes[0].dataType.isArrayType()) || (this.nodes[0].dataType.isLobType()) || (this.nodes[1].dataType.isLobType())))
      throw Error.error(5534);
    if ((this.nodes[0].opType == 14) && (this.nodes[1].opType == 1))
      this.isTerminal = true;
    if (this.nodes[0].dataType.typeComparisonGroup != this.nodes[1].dataType.typeComparisonGroup)
      throw Error.error(5562);
    if ((this.nodes[0].opType == 1) && (this.nodes[1].opType == 1))
      setAsConstantValue(paramSession);
  }

  private void changeToRowExpression(int paramInt)
  {
    if (this.nodes[paramInt].opType != 25)
    {
      this.nodes[paramInt] = new Expression(25, new Expression[] { this.nodes[paramInt] });
      this.nodes[paramInt].nodeDataTypes = new Type[] { this.nodes[paramInt].nodes[0].dataType };
    }
  }

  private void resolveRowTypes()
  {
    for (int i = 0; i < this.nodes[0].nodeDataTypes.length; i++)
    {
      Object localObject1 = this.nodes[0].nodeDataTypes[i];
      Object localObject2 = this.nodes[1].nodeDataTypes[i];
      if (localObject1 == null)
        localObject1 = this.nodes[0].nodeDataTypes[i] =  = localObject2;
      else if (this.nodes[1].dataType == null)
        localObject2 = this.nodes[1].nodeDataTypes[i] =  = localObject1;
      if ((localObject1 == null) || (localObject2 == null))
        throw Error.error(5567);
      if (((Type)localObject1).typeComparisonGroup != ((Type)localObject2).typeComparisonGroup)
        throw Error.error(5562);
      if ((((Type)localObject1).isDateTimeType()) && ((((Type)localObject1).isDateTimeTypeWithZone() ^ ((Type)localObject2).isDateTimeTypeWithZone())))
      {
        this.nodes[0].nodes[i] = new ExpressionOp(this.nodes[0].nodes[i]);
        this.nodes[0].nodeDataTypes[i] = this.nodes[0].nodes[i].dataType;
      }
    }
  }

  void checkRowComparison()
  {
    if ((this.opType == 41) || (this.opType == 46))
      return;
    for (int i = 0; i < this.nodes[0].nodeDataTypes.length; i++)
    {
      Type localType1 = this.nodes[0].nodeDataTypes[i];
      Type localType2 = this.nodes[1].nodeDataTypes[i];
      if ((localType1.isArrayType()) || (localType1.isLobType()) || (localType2.isLobType()))
        throw Error.error(5534);
    }
  }

  private boolean convertDateTimeLiteral(Session paramSession, Expression paramExpression1, Expression paramExpression2)
  {
    if (!paramExpression1.dataType.isDateTimeType())
      if (paramExpression2.dataType.isDateTimeType())
      {
        Expression localExpression = paramExpression1;
        paramExpression1 = paramExpression2;
        paramExpression2 = localExpression;
      }
      else
      {
        return false;
      }
    if (paramExpression1.dataType.isDateTimeTypeWithZone())
      return false;
    if ((paramExpression2.opType == 1) && (paramExpression2.dataType.isCharacterType()))
    {
      try
      {
        paramExpression2.valueData = paramExpression1.dataType.castToType(paramSession, paramExpression2.valueData, paramExpression2.dataType);
        paramExpression2.dataType = paramExpression1.dataType;
      }
      catch (HsqlException localHsqlException)
      {
        if (paramExpression1.dataType == Type.SQL_DATE)
        {
          paramExpression2.valueData = Type.SQL_TIMESTAMP.castToType(paramSession, paramExpression2.valueData, paramExpression2.dataType);
          paramExpression2.dataType = Type.SQL_TIMESTAMP;
        }
      }
      return true;
    }
    return false;
  }

  void resolveTypesForOverlaps()
  {
    if (this.nodes[0].nodes[0].isUnresolvedParam())
      this.nodes[0].nodes[0].dataType = this.nodes[1].nodes[0].dataType;
    if (this.nodes[1].nodes[0].isUnresolvedParam())
      this.nodes[1].nodes[0].dataType = this.nodes[0].nodes[0].dataType;
    if (this.nodes[0].nodes[0].dataType == null)
      this.nodes[0].nodes[0].dataType = (this.nodes[1].nodes[0].dataType = Type.SQL_TIMESTAMP);
    if (this.nodes[0].nodes[1].isUnresolvedParam())
      this.nodes[0].nodes[1].dataType = this.nodes[1].nodes[0].dataType;
    if (this.nodes[1].nodes[1].isUnresolvedParam())
      this.nodes[1].nodes[1].dataType = this.nodes[0].nodes[0].dataType;
    if ((!DTIType.isValidDatetimeRange(this.nodes[0].nodes[0].dataType, this.nodes[0].nodes[1].dataType)) || (!DTIType.isValidDatetimeRange(this.nodes[1].nodes[0].dataType, this.nodes[1].nodes[1].dataType)))
      throw Error.error(5563);
    if (!DTIType.isValidDatetimeRange(this.nodes[0].nodes[0].dataType, this.nodes[0].nodes[1].dataType))
      throw Error.error(5563);
    this.nodes[0].nodeDataTypes[0] = this.nodes[0].nodes[0].dataType;
    this.nodes[0].nodeDataTypes[1] = this.nodes[0].nodes[1].dataType;
    this.nodes[1].nodeDataTypes[0] = this.nodes[1].nodes[0].dataType;
    this.nodes[1].nodeDataTypes[1] = this.nodes[1].nodes[1].dataType;
  }

  void resolveTypesForAllAny(Session paramSession)
  {
    int i = this.nodes[0].getDegree();
    if ((i == 1) && (this.nodes[0].opType != 25))
      this.nodes[0] = new Expression(25, new Expression[] { this.nodes[0] });
    if (this.nodes[1].opType == 26)
    {
      this.nodes[1].prepareTable(paramSession, this.nodes[0], i);
      this.nodes[1].table.prepareTable();
    }
    if (this.nodes[1].nodeDataTypes == null)
      this.nodes[1].prepareTable(paramSession, this.nodes[0], i);
    if (i != this.nodes[1].nodeDataTypes.length)
      throw Error.error(5564);
    if ((this.nodes[1].opType != 26) || (this.nodes[0].nodeDataTypes == null))
      this.nodes[0].nodeDataTypes = new Type[this.nodes[0].nodes.length];
    for (int j = 0; j < this.nodes[0].nodeDataTypes.length; j++)
    {
      Type localType = this.nodes[0].nodes[j].dataType;
      if (localType == null)
        localType = this.nodes[1].nodeDataTypes[j];
      if (localType == null)
        throw Error.error(5567);
      if (localType.typeComparisonGroup != this.nodes[1].nodeDataTypes[j].typeComparisonGroup)
        throw Error.error(5563);
      this.nodes[0].nodeDataTypes[j] = localType;
      this.nodes[0].nodes[j].dataType = localType;
    }
  }

  void resolveTypesForIn(Session paramSession)
  {
    resolveTypesForAllAny(paramSession);
  }

  public Object getValue(Session paramSession)
  {
    Object localObject1;
    Object localObject2;
    switch (this.opType)
    {
    case 1:
      return this.valueData;
    case 5:
      localObject1 = paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex);
      return localObject1;
    case 31:
      return ((NumberType)this.dataType).negate(this.nodes[0].getValue(paramSession, this.nodes[0].dataType));
    case 47:
      return this.nodes[0].getValue(paramSession) == null ? Boolean.TRUE : Boolean.FALSE;
    case 56:
      localObject1 = this.nodes[0].getRowValue(paramSession);
      localObject2 = this.nodes[1].getRowValue(paramSession);
      return DateTimeType.overlaps(paramSession, (Object[])localObject1, this.nodes[0].nodeDataTypes, (Object[])localObject2, this.nodes[1].nodeDataTypes);
    case 54:
      return testInCondition(paramSession);
    case 59:
    case 60:
    case 61:
    case 62:
    case 63:
    case 64:
      return testMatchCondition(paramSession);
    case 58:
      return testNotDistinctCondition(paramSession);
    case 57:
      this.nodes[0].materialise(paramSession);
      return this.nodes[0].table.hasUniqueNotNullRows(paramSession) ? Boolean.TRUE : Boolean.FALSE;
    case 55:
      return testExistsCondition(paramSession);
    case 48:
      localObject1 = (Boolean)this.nodes[0].getValue(paramSession);
      return ((Boolean)localObject1).booleanValue() ? Boolean.FALSE : localObject1 == null ? null : Boolean.TRUE;
    case 49:
      localObject1 = (Boolean)this.nodes[0].getValue(paramSession);
      if (Boolean.FALSE.equals(localObject1))
        return Boolean.FALSE;
      localObject2 = (Boolean)this.nodes[1].getValue(paramSession);
      if (Boolean.FALSE.equals(localObject2))
        return Boolean.FALSE;
      if ((localObject1 == null) || (localObject2 == null))
        return null;
      return Boolean.TRUE;
    case 50:
      localObject1 = (Boolean)this.nodes[0].getValue(paramSession);
      if (Boolean.TRUE.equals(localObject1))
        return Boolean.TRUE;
      localObject2 = (Boolean)this.nodes[1].getValue(paramSession);
      if (Boolean.TRUE.equals(localObject2))
        return Boolean.TRUE;
      if ((localObject1 == null) || (localObject2 == null))
        return null;
      return Boolean.FALSE;
    case 41:
    case 42:
    case 43:
    case 44:
    case 45:
    case 46:
      if ((this.exprSubType == 52) || (this.exprSubType == 51))
        return testAllAnyCondition(paramSession);
      localObject1 = this.nodes[0].getValue(paramSession);
      localObject2 = this.nodes[1].getValue(paramSession);
      if ((this.nodes[0].dataType != null) && (this.nodes[0].dataType.isArrayType()))
        return compareValues(paramSession, localObject1, localObject2);
      if ((localObject1 instanceof Object[]))
      {
        if ((localObject2 != null) && (!(localObject2 instanceof Object[])))
          throw Error.runtimeError(201, "ExpressionLogical");
        return compareValues(paramSession, (Object[])localObject1, (Object[])localObject2);
      }
      if ((localObject2 instanceof Object[]))
        localObject2 = ((Object[])(Object[])localObject2)[0];
      return compareValues(paramSession, localObject1, localObject2);
    case 2:
    case 3:
    case 4:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 20:
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:
    case 27:
    case 28:
    case 29:
    case 30:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 39:
    case 40:
    case 51:
    case 52:
    case 53:
    }
    throw Error.runtimeError(201, "ExpressionLogical");
  }

  private Boolean compareValues(Session paramSession, Object paramObject1, Object paramObject2)
  {
    int i = 0;
    if ((paramObject1 == null) || (paramObject2 == null))
      return null;
    i = this.nodes[0].dataType.compare(paramSession, paramObject1, paramObject2);
    switch (this.opType)
    {
    case 41:
      return i == 0 ? Boolean.TRUE : Boolean.FALSE;
    case 46:
      return i != 0 ? Boolean.TRUE : Boolean.FALSE;
    case 43:
      return i > 0 ? Boolean.TRUE : Boolean.FALSE;
    case 42:
      return i >= 0 ? Boolean.TRUE : Boolean.FALSE;
    case 45:
      return i <= 0 ? Boolean.TRUE : Boolean.FALSE;
    case 44:
      return i < 0 ? Boolean.TRUE : Boolean.FALSE;
    }
    throw Error.runtimeError(201, "ExpressionLogical");
  }

  private Boolean compareValues(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    int i = 0;
    int j = 0;
    if ((paramArrayOfObject1 == null) || (paramArrayOfObject2 == null))
      return null;
    Object[] arrayOfObject1 = (Object[])paramArrayOfObject1;
    Object[] arrayOfObject2 = (Object[])paramArrayOfObject2;
    for (int k = 0; k < this.nodes[0].nodes.length; k++)
      if (arrayOfObject1[k] == null)
      {
        if ((this.opType != 60) && (this.opType != 63))
          j = 1;
      }
      else
      {
        if (arrayOfObject2[k] == null)
          j = 1;
        Object localObject1 = arrayOfObject1[k];
        Object localObject2 = arrayOfObject2[k];
        Type[] arrayOfType = this.nodes[0].nodeDataTypes;
        i = arrayOfType[k].compare(paramSession, localObject1, localObject2);
        if (i != 0)
          break;
      }
    switch (this.opType)
    {
    case 58:
    case 59:
    case 60:
    case 61:
    case 62:
    case 63:
    case 64:
      return i == 0 ? Boolean.TRUE : Boolean.FALSE;
    case 41:
    case 54:
      if (j != 0)
        return null;
      return i == 0 ? Boolean.TRUE : Boolean.FALSE;
    case 46:
      if (j != 0)
        return null;
      return i != 0 ? Boolean.TRUE : Boolean.FALSE;
    case 43:
      if (j != 0)
        return null;
      return i > 0 ? Boolean.TRUE : Boolean.FALSE;
    case 42:
      if (j != 0)
        return null;
      return i >= 0 ? Boolean.TRUE : Boolean.FALSE;
    case 45:
      if (j != 0)
        return null;
      return i <= 0 ? Boolean.TRUE : Boolean.FALSE;
    case 44:
      if (j != 0)
        return null;
      return i < 0 ? Boolean.TRUE : Boolean.FALSE;
    case 47:
    case 48:
    case 49:
    case 50:
    case 51:
    case 52:
    case 53:
    case 55:
    case 56:
    case 57:
    }
    throw Error.runtimeError(201, "ExpressionLogical");
  }

  private Boolean testInCondition(Session paramSession)
  {
    Object[] arrayOfObject1 = this.nodes[0].getRowValue(paramSession);
    if (arrayOfObject1 == null)
      return null;
    if (Expression.countNulls(arrayOfObject1) != 0)
      return null;
    if (this.nodes[1].opType == 26)
    {
      int i = this.nodes[1].nodes.length;
      for (int j = 0; j < i; j++)
      {
        Object[] arrayOfObject2 = this.nodes[1].nodes[j].getRowValue(paramSession);
        if (Boolean.TRUE.equals(compareValues(paramSession, arrayOfObject1, arrayOfObject2)))
          return Boolean.TRUE;
      }
      return Boolean.FALSE;
    }
    throw Error.runtimeError(201, "ExpressionLogical");
  }

  private Boolean testNotDistinctCondition(Session paramSession)
  {
    Object[] arrayOfObject1 = this.nodes[0].getRowValue(paramSession);
    Object[] arrayOfObject2 = this.nodes[1].getRowValue(paramSession);
    if ((arrayOfObject1 == null) || (arrayOfObject2 == null))
      return Boolean.valueOf(arrayOfObject1 == arrayOfObject2);
    return compareValues(paramSession, arrayOfObject1, arrayOfObject2);
  }

  private Boolean testMatchCondition(Session paramSession)
  {
    Object[] arrayOfObject1 = this.nodes[0].getRowValue(paramSession);
    if (arrayOfObject1 == null)
      return Boolean.TRUE;
    int i = countNulls(arrayOfObject1);
    if (i != 0)
      switch (this.opType)
      {
      case 59:
      case 62:
        return Boolean.TRUE;
      case 60:
      case 63:
        if (i == arrayOfObject1.length)
          return Boolean.TRUE;
        break;
      case 61:
      case 64:
        return i == arrayOfObject1.length ? Boolean.TRUE : Boolean.FALSE;
      }
    int k;
    Object[] arrayOfObject2;
    Boolean localBoolean;
    switch (this.nodes[1].opType)
    {
    case 26:
      int j = this.nodes[1].nodes.length;
      k = 0;
      for (int m = 0; m < j; m++)
      {
        arrayOfObject2 = this.nodes[1].nodes[m].getRowValue(paramSession);
        localBoolean = compareValues(paramSession, arrayOfObject1, arrayOfObject2);
        if ((localBoolean != null) && (localBoolean.booleanValue()))
          switch (this.opType)
          {
          case 59:
          case 60:
          case 61:
            return Boolean.TRUE;
          case 62:
          case 63:
          case 64:
            if (k != 0)
              return Boolean.FALSE;
            k = 1;
          }
      }
      return k != 0 ? Boolean.TRUE : Boolean.FALSE;
    case 23:
      PersistentStore localPersistentStore = this.nodes[1].getTable().getRowStore(paramSession);
      this.nodes[1].materialise(paramSession);
      convertToType(paramSession, arrayOfObject1, this.nodes[0].nodeDataTypes, this.nodes[1].nodeDataTypes);
      if ((i != 0) && ((this.opType == 60) || (this.opType == 63)))
      {
        k = 0;
        RowIterator localRowIterator2 = this.nodes[1].getTable().rowIterator(paramSession);
        while (localRowIterator2.hasNext())
        {
          arrayOfObject2 = localRowIterator2.getNextRow().getData();
          localBoolean = compareValues(paramSession, arrayOfObject1, arrayOfObject2);
          if (localBoolean != null)
            if (localBoolean.booleanValue())
            {
              if (this.opType == 60)
                return Boolean.TRUE;
              if (k != 0)
                return Boolean.FALSE;
              k = 1;
            }
        }
        return k != 0 ? Boolean.TRUE : Boolean.FALSE;
      }
      RowIterator localRowIterator1 = this.nodes[1].getTable().getPrimaryIndex().findFirstRow(paramSession, localPersistentStore, arrayOfObject1);
      boolean bool = localRowIterator1.hasNext();
      if (!bool)
        return Boolean.FALSE;
      switch (this.opType)
      {
      case 59:
      case 60:
      case 61:
        return Boolean.TRUE;
      }
      localRowIterator1.getNextRow();
      bool = localRowIterator1.hasNext();
      if (!bool)
        return Boolean.TRUE;
      arrayOfObject2 = localRowIterator1.getNextRow().getData();
      localBoolean = Boolean.TRUE.equals(compareValues(paramSession, arrayOfObject1, arrayOfObject2)) ? Boolean.FALSE : Boolean.TRUE;
      return localBoolean;
    }
    throw Error.runtimeError(201, "ExpressionLogical");
  }

  private Boolean testExistsCondition(Session paramSession)
  {
    this.nodes[0].materialise(paramSession);
    return this.nodes[0].getTable().isEmpty(paramSession) ? Boolean.FALSE : Boolean.TRUE;
  }

  private Boolean testAllAnyCondition(Session paramSession)
  {
    Object[] arrayOfObject = (Object[])this.nodes[0].getRowValue(paramSession);
    TableDerived localTableDerived = this.nodes[1].table;
    localTableDerived.materialiseCorrelated(paramSession);
    Boolean localBoolean = getAllAnyValue(paramSession, arrayOfObject, localTableDerived);
    return localBoolean;
  }

  private Boolean getAllAnyValue(Session paramSession, Object[] paramArrayOfObject, TableDerived paramTableDerived)
  {
    TableDerived localTableDerived = paramTableDerived;
    boolean bool1 = localTableDerived.isEmpty(paramSession);
    Index localIndex = localTableDerived.getFullIndex();
    PersistentStore localPersistentStore = localTableDerived.getRowStore(paramSession);
    boolean bool2 = false;
    for (int i = 0; i < localTableDerived.columnCount; i++)
      bool2 |= localPersistentStore.hasNull(i);
    RowIterator localRowIterator;
    Row localRow1;
    Object[] arrayOfObject1;
    Row localRow2;
    Object[] arrayOfObject2;
    Boolean localBoolean1;
    Boolean localBoolean2;
    switch (this.exprSubType)
    {
    case 52:
      if (bool1)
        return Boolean.FALSE;
      if (countNulls(paramArrayOfObject) == paramArrayOfObject.length)
        return null;
      convertToType(paramSession, paramArrayOfObject, this.nodes[0].nodeDataTypes, this.nodes[1].nodeDataTypes);
      if (this.opType == 41)
      {
        localRowIterator = localIndex.findFirstRow(paramSession, localPersistentStore, paramArrayOfObject);
        if (localRowIterator.hasNext())
          return Boolean.TRUE;
        if (bool2)
          return null;
        return Boolean.FALSE;
      }
      if (this.opType == 46)
        localRowIterator = localIndex.firstRow(paramSession, localPersistentStore);
      else
        localRowIterator = localIndex.findFirstRowNotNull(paramSession, localPersistentStore);
      localRow1 = localRowIterator.getNextRow();
      if (localRow1 == null)
        return null;
      arrayOfObject1 = localRow1.getData();
      localRow2 = localIndex.lastRow(paramSession, localPersistentStore).getNextRow();
      arrayOfObject2 = localRow2.getData();
      localBoolean1 = compareValues(paramSession, paramArrayOfObject, arrayOfObject1);
      localBoolean2 = compareValues(paramSession, paramArrayOfObject, arrayOfObject2);
      switch (this.opType)
      {
      case 46:
        if ((Boolean.TRUE.equals(localBoolean1)) || (Boolean.TRUE.equals(localBoolean2)))
          return Boolean.TRUE;
        if ((Boolean.FALSE.equals(localBoolean1)) && (Boolean.FALSE.equals(localBoolean2)))
        {
          localRowIterator = localIndex.findFirstRow(paramSession, localPersistentStore, paramArrayOfObject);
          return Boolean.FALSE;
        }
        return null;
      case 43:
        return localBoolean1;
      case 42:
        return localBoolean1;
      case 44:
        return localBoolean2;
      case 45:
        return localBoolean2;
      }
      break;
    case 51:
      if (bool1)
        return Boolean.TRUE;
      if (countNulls(paramArrayOfObject) == paramArrayOfObject.length)
        return null;
      localRowIterator = localIndex.firstRow(paramSession, localPersistentStore);
      localRow1 = localRowIterator.getNextRow();
      arrayOfObject1 = localRow1.getData();
      if (countNulls(arrayOfObject1) == paramArrayOfObject.length)
        return null;
      convertToType(paramSession, paramArrayOfObject, this.nodes[0].nodeDataTypes, this.nodes[1].nodeDataTypes);
      localRowIterator = localIndex.findFirstRow(paramSession, localPersistentStore, paramArrayOfObject);
      if (this.opType == 41)
      {
        if (localRowIterator.hasNext())
          return localPersistentStore.elementCount(paramSession) == 1L ? Boolean.TRUE : Boolean.FALSE;
        return Boolean.FALSE;
      }
      if (this.opType == 46)
        return localRowIterator.hasNext() ? Boolean.FALSE : Boolean.TRUE;
      localRow2 = localIndex.lastRow(paramSession, localPersistentStore).getNextRow();
      arrayOfObject2 = localRow2.getData();
      localBoolean1 = compareValues(paramSession, paramArrayOfObject, arrayOfObject1);
      localBoolean2 = compareValues(paramSession, paramArrayOfObject, arrayOfObject2);
      switch (this.opType)
      {
      case 43:
        return localBoolean2;
      case 42:
        return localBoolean2;
      case 44:
        return localBoolean1;
      case 45:
        return localBoolean1;
      }
      break;
    }
    return null;
  }

  void distributeOr()
  {
    if (this.opType != 50)
      return;
    Object localObject;
    if (this.nodes[0].opType == 49)
    {
      this.opType = 49;
      localObject = new ExpressionLogical(50, this.nodes[0].nodes[1], this.nodes[1]);
      this.nodes[0].opType = 50;
      this.nodes[0].nodes[1] = this.nodes[1];
      this.nodes[1] = localObject;
    }
    else if (this.nodes[1].opType == 49)
    {
      localObject = this.nodes[0];
      this.nodes[0] = this.nodes[1];
      this.nodes[1] = localObject;
      distributeOr();
      return;
    }
    ((ExpressionLogical)this.nodes[0]).distributeOr();
    ((ExpressionLogical)this.nodes[1]).distributeOr();
  }

  public boolean isIndexable(RangeVariable paramRangeVariable)
  {
    boolean bool;
    switch (this.opType)
    {
    case 49:
      bool = (this.nodes[0].isIndexable(paramRangeVariable)) || (this.nodes[1].isIndexable(paramRangeVariable));
      return bool;
    case 50:
      bool = (this.nodes[0].isIndexable(paramRangeVariable)) && (this.nodes[1].isIndexable(paramRangeVariable));
      return bool;
    }
    Expression localExpression = getIndexableExpression(paramRangeVariable);
    return localExpression != null;
  }

  Expression getIndexableExpression(RangeVariable paramRangeVariable)
  {
    switch (this.opType)
    {
    case 47:
      return (this.nodes[0].opType == 2) && (this.nodes[0].isIndexable(paramRangeVariable)) ? this : null;
    case 48:
      return (this.nodes[0].opType == 47) && (this.nodes[0].nodes[0].opType == 2) && (this.nodes[0].nodes[0].isIndexable(paramRangeVariable)) ? this : null;
    case 41:
      if (this.exprSubType == 52)
      {
        if (this.nodes[1].isCorrelated)
          return null;
        for (int i = 0; i < this.nodes[0].nodes.length; i++)
          if ((this.nodes[0].nodes[i].opType == 2) && (this.nodes[0].nodes[i].isIndexable(paramRangeVariable)))
            return this;
        return null;
      }
    case 42:
    case 43:
    case 44:
    case 45:
      if ((this.exprSubType != 0) && (this.exprSubType != 53))
        return null;
      if ((this.nodes[0].opType == 2) && (this.nodes[0].isIndexable(paramRangeVariable)))
      {
        if (this.nodes[1].hasReference(paramRangeVariable))
          return null;
        return this;
      }
      if (this.nodes[0].hasReference(paramRangeVariable))
        return null;
      if ((this.nodes[1].opType == 2) && (this.nodes[1].isIndexable(paramRangeVariable)))
      {
        swapCondition();
        return this;
      }
      return null;
    case 50:
      if (isIndexable(paramRangeVariable))
        return this;
      return null;
    case 46:
    case 49:
    }
    return null;
  }

  boolean isSimpleBound()
  {
    if (this.opType == 47)
      return true;
    if (this.nodes[1] != null)
    {
      if (this.nodes[1].opType == 1)
        return true;
      if ((this.nodes[1].opType == 28) && (((FunctionSQL)this.nodes[1]).isValueFunction()))
        return true;
    }
    return false;
  }

  boolean convertToSmaller()
  {
    switch (this.opType)
    {
    case 42:
    case 43:
      swapCondition();
      return true;
    case 44:
    case 45:
      return true;
    }
    return false;
  }

  void swapCondition()
  {
    int i = 41;
    switch (this.opType)
    {
    case 42:
      i = 45;
      break;
    case 45:
      i = 42;
      break;
    case 44:
      i = 43;
      break;
    case 43:
      i = 44;
      break;
    case 58:
      i = 58;
      break;
    case 41:
      break;
    case 46:
    case 47:
    case 48:
    case 49:
    case 50:
    case 51:
    case 52:
    case 53:
    case 54:
    case 55:
    case 56:
    case 57:
    default:
      throw Error.runtimeError(201, "ExpressionLogical");
    }
    this.opType = i;
    Expression localExpression = this.nodes[0];
    this.nodes[0] = this.nodes[1];
    this.nodes[1] = localExpression;
  }

  boolean reorderComparison(Session paramSession, Expression paramExpression)
  {
    Expression localExpression1 = null;
    Expression localExpression2 = null;
    int i = 0;
    int j = 0;
    int k = 0;
    if (this.nodes[0].opType == 32)
    {
      k = 33;
      i = 1;
    }
    else if (this.nodes[0].opType == 33)
    {
      k = 32;
      i = 1;
    }
    else if (this.nodes[1].opType == 32)
    {
      k = 33;
    }
    else if (this.nodes[1].opType == 33)
    {
      k = 32;
    }
    if (k == 0)
      return false;
    if (i != 0)
    {
      if (this.nodes[0].nodes[0].opType == 2)
      {
        localExpression1 = this.nodes[0].nodes[0];
        localExpression2 = this.nodes[0].nodes[1];
      }
      else if (this.nodes[0].nodes[1].opType == 2)
      {
        j = k == 32 ? 1 : 0;
        localExpression1 = this.nodes[0].nodes[1];
        localExpression2 = this.nodes[0].nodes[0];
      }
    }
    else if (this.nodes[1].nodes[0].opType == 2)
    {
      localExpression1 = this.nodes[1].nodes[0];
      localExpression2 = this.nodes[1].nodes[1];
    }
    else if (this.nodes[1].nodes[1].opType == 2)
    {
      j = k == 32 ? 1 : 0;
      localExpression1 = this.nodes[1].nodes[1];
      localExpression2 = this.nodes[1].nodes[0];
    }
    if (localExpression1 == null)
      return false;
    Expression localExpression3 = i != 0 ? this.nodes[1] : this.nodes[0];
    ExpressionArithmetic localExpressionArithmetic = null;
    if (j == 0)
    {
      localExpressionArithmetic = new ExpressionArithmetic(k, localExpression3, localExpression2);
      localExpressionArithmetic.resolveTypesForArithmetic(paramSession, paramExpression);
    }
    if (i != 0)
    {
      if (j != 0)
      {
        this.nodes[1] = localExpression1;
        this.nodes[0].nodes[1] = localExpression3;
        ((ExpressionArithmetic)this.nodes[0]).resolveTypesForArithmetic(paramSession, paramExpression);
      }
      else
      {
        this.nodes[0] = localExpression1;
        this.nodes[1] = localExpressionArithmetic;
      }
    }
    else if (j != 0)
    {
      this.nodes[0] = localExpression1;
      this.nodes[1].nodes[1] = localExpression3;
      ((ExpressionArithmetic)this.nodes[1]).resolveTypesForArithmetic(paramSession, paramExpression);
    }
    else
    {
      this.nodes[1] = localExpression1;
      this.nodes[0] = localExpressionArithmetic;
    }
    return true;
  }

  boolean isConditionRangeVariable(RangeVariable paramRangeVariable)
  {
    if (this.nodes[0].getRangeVariable() == paramRangeVariable)
      return true;
    return this.nodes[1].getRangeVariable() == paramRangeVariable;
  }

  RangeVariable[] getJoinRangeVariables(RangeVariable[] paramArrayOfRangeVariable)
  {
    OrderedHashSet localOrderedHashSet = collectRangeVariables(paramArrayOfRangeVariable, null);
    if (localOrderedHashSet != null)
    {
      this.rangeArray = new RangeVariable[localOrderedHashSet.size()];
      localOrderedHashSet.toArray(this.rangeArray);
    }
    return this.rangeArray;
  }

  double costFactor(Session paramSession, RangeVariable paramRangeVariable, int paramInt)
  {
    PersistentStore localPersistentStore;
    double d;
    switch (this.opType)
    {
    case 50:
      return this.nodes[0].costFactor(paramSession, paramRangeVariable, this.opType) + this.nodes[1].costFactor(paramSession, paramRangeVariable, this.opType);
    case 54:
    case 56:
    case 58:
    case 59:
    case 60:
    case 61:
    case 62:
    case 63:
    case 64:
      localPersistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
      d = localPersistentStore.elementCount();
      if (d < 16.0D)
        d = 16.0D;
      break;
    case 47:
    case 48:
      d = costFactorUnaryColumn(paramSession, paramRangeVariable);
      break;
    case 41:
      switch (this.exprSubType)
      {
      case 52:
        if ((this.nodes[0].opType == 2) && (this.nodes[0].getRangeVariable() == paramRangeVariable))
        {
          d = costFactorColumns(paramSession, paramRangeVariable);
          d *= 1024.0D;
        }
        break;
      case 51:
        localPersistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
        d = localPersistentStore.elementCount();
        if (d < 16.0D)
          d = 16.0D;
        d *= 1024.0D;
        break;
      }
      d = costFactorColumns(paramSession, paramRangeVariable);
      break;
    case 42:
    case 43:
    case 44:
    case 45:
      d = costFactorColumns(paramSession, paramRangeVariable);
      break;
    case 46:
    case 49:
    case 51:
    case 52:
    case 53:
    case 55:
    case 57:
    default:
      throw Error.runtimeError(201, "ExpressionLogical");
    }
    return d;
  }

  double costFactorUnaryColumn(Session paramSession, RangeVariable paramRangeVariable)
  {
    if ((this.nodes[0].opType == 2) && (this.nodes[0].getRangeVariable() == paramRangeVariable))
      return this.nodes[0].costFactor(paramSession, paramRangeVariable, this.opType);
    PersistentStore localPersistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
    double d = localPersistentStore.elementCount();
    return d < 16.0D ? 16.0D : d;
  }

  double costFactorColumns(Session paramSession, RangeVariable paramRangeVariable)
  {
    double d = 0.0D;
    PersistentStore localPersistentStore;
    if ((this.nodes[0].opType == 2) && (this.nodes[0].getRangeVariable() == paramRangeVariable))
    {
      if (!this.nodes[1].hasReference(paramRangeVariable))
        d = this.nodes[0].costFactor(paramSession, paramRangeVariable, this.opType);
    }
    else if ((this.nodes[1].opType == 2) && (this.nodes[1].getRangeVariable() == paramRangeVariable))
    {
      if (!this.nodes[0].hasReference(paramRangeVariable))
        d = this.nodes[1].costFactor(paramSession, paramRangeVariable, this.opType);
    }
    else
    {
      localPersistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
      d = localPersistentStore.elementCount();
    }
    if (d == 0.0D)
    {
      localPersistentStore = paramRangeVariable.rangeTable.getRowStore(paramSession);
      d = localPersistentStore.elementCount();
    }
    if (d < 16.0D)
      d = 16.0D;
    return d;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ExpressionLogical
 * JD-Core Version:    0.6.2
 */