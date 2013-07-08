package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayListIdentity;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.navigator.RangeIterator;
import org.hsqldb.navigator.RowSetNavigator;
import org.hsqldb.navigator.RowSetNavigatorData;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.result.Result;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Collation;
import org.hsqldb.types.NullType;
import org.hsqldb.types.Type;

public class Expression
  implements Cloneable
{
  public static final int LEFT = 0;
  public static final int RIGHT = 1;
  public static final int UNARY = 1;
  public static final int BINARY = 2;
  public static final int TERNARY = 3;
  static final Expression[] emptyArray = new Expression[0];
  static final Expression EXPR_TRUE = new ExpressionLogical(true);
  static final Expression EXPR_FALSE = new ExpressionLogical(false);
  static final OrderedIntHashSet aggregateFunctionSet = new OrderedIntHashSet();
  static final OrderedIntHashSet columnExpressionSet;
  static final OrderedIntHashSet subqueryExpressionSet;
  static final OrderedIntHashSet subqueryAggregateExpressionSet;
  static final OrderedIntHashSet functionExpressionSet;
  static final OrderedIntHashSet emptyExpressionSet = new OrderedIntHashSet();
  protected int opType;
  protected int exprSubType;
  HsqlNameManager.SimpleName alias;
  private boolean isAggregate;
  protected Object valueData;
  protected Expression[] nodes;
  Type[] nodeDataTypes;
  TableDerived table;
  boolean isCorrelated;
  int columnIndex = -1;
  protected Type dataType;
  int queryTableColumnIndex = -1;
  int parameterIndex = -1;
  int rangePosition = -1;
  boolean isColumnCondition;
  boolean isColumnEqual;
  boolean isSingleColumnCondition;
  boolean isSingleColumnEqual;
  boolean isSingleColumnNull;
  boolean isSingleColumnNotNull;
  byte nullability = 2;
  Collation collation;
  
  Expression(int paramInt)
  {
    this.opType = paramInt;
    this.nodes = emptyArray;
  }
  
  Expression(int paramInt, TableDerived paramTableDerived)
  {
    switch (paramInt)
    {
    case 19: 
      this.opType = 19;
      break;
    case 100: 
      this.opType = 100;
      break;
    case 23: 
      this.opType = 23;
      break;
    case 21: 
    case 22: 
      this.opType = 22;
      break;
    default: 
      throw Error.runtimeError(201, "Expression");
    }
    this.nodes = emptyArray;
    this.table = paramTableDerived;
  }
  
  Expression(int paramInt, Expression[] paramArrayOfExpression)
  {
    this(paramInt);
    this.nodes = paramArrayOfExpression;
  }
  
  static String getContextSQL(Expression paramExpression)
  {
    if (paramExpression == null) {
      return null;
    }
    String str = paramExpression.getSQL();
    switch (paramExpression.opType)
    {
    case 1: 
    case 2: 
    case 25: 
    case 27: 
    case 28: 
    case 91: 
    case 93: 
    case 96: 
      return str;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    str = localStringBuffer.append('(').append(str).append(')').toString();
    return str;
  }
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    int i;
    switch (this.opType)
    {
    case 1: 
      if (this.valueData == null) {
        return "NULL";
      }
      return this.dataType.convertToSQLString(this.valueData);
    case 25: 
      localStringBuffer.append('(');
      for (i = 0; i < this.nodes.length; i++)
      {
        if (i > 0) {
          localStringBuffer.append(',');
        }
        localStringBuffer.append(this.nodes[i].getSQL());
      }
      localStringBuffer.append(')');
      return localStringBuffer.toString();
    case 26: 
      for (i = 0; i < this.nodes.length; i++)
      {
        if (i > 0) {
          localStringBuffer.append(',');
        }
        localStringBuffer.append(this.nodes[i].getSQL());
      }
      return localStringBuffer.toString();
    }
    switch (this.opType)
    {
    case 19: 
      localStringBuffer.append("ARRAY").append('[');
      for (i = 0; i < this.nodes.length; i++)
      {
        if (i > 0) {
          localStringBuffer.append(',');
        }
        localStringBuffer.append(this.nodes[i].getSQL());
      }
      localStringBuffer.append(']');
      break;
    case 22: 
    case 23: 
    case 100: 
      localStringBuffer.append('(');
      localStringBuffer.append(')');
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
    for (int i = 0; i < paramInt; i++) {
      localStringBuffer.append(' ');
    }
    switch (this.opType)
    {
    case 1: 
      localStringBuffer.append("VALUE = ").append(this.dataType.convertToSQLString(this.valueData));
      localStringBuffer.append(", TYPE = ").append(this.dataType.getNameString());
      return localStringBuffer.toString();
    case 19: 
      localStringBuffer.append("ARRAY ");
      return localStringBuffer.toString();
    case 100: 
      localStringBuffer.append("ARRAY SUBQUERY");
      return localStringBuffer.toString();
    case 22: 
    case 23: 
      localStringBuffer.append("QUERY ");
      localStringBuffer.append(this.table.queryExpression.describe(paramSession, paramInt));
      return localStringBuffer.toString();
    case 25: 
      localStringBuffer.append("ROW = ");
      for (i = 0; i < this.nodes.length; i++)
      {
        localStringBuffer.append(this.nodes[i].describe(paramSession, paramInt + 1));
        localStringBuffer.append(' ');
      }
      break;
    case 26: 
      localStringBuffer.append("VALUELIST ");
      for (i = 0; i < this.nodes.length; i++)
      {
        localStringBuffer.append(this.nodes[i].describe(paramSession, paramInt + 1));
        localStringBuffer.append(' ');
      }
    }
    return localStringBuffer.toString();
  }
  
  void setDataType(Session paramSession, Type paramType)
  {
    if (this.opType == 1) {
      this.valueData = paramType.convertToType(paramSession, this.valueData, this.dataType);
    }
    this.dataType = paramType;
  }
  
  public boolean equals(Expression paramExpression)
  {
    if (paramExpression == this) {
      return true;
    }
    if (paramExpression == null) {
      return false;
    }
    if ((this.opType != paramExpression.opType) || (this.exprSubType != paramExpression.exprSubType) || (!equals(this.dataType, paramExpression.dataType))) {
      return false;
    }
    switch (this.opType)
    {
    case 5: 
      return this.columnIndex == paramExpression.columnIndex;
    case 1: 
      return equals(this.valueData, paramExpression.valueData);
    case 19: 
    case 22: 
    case 23: 
    case 100: 
      return this.table.queryExpression.isEquivalent(paramExpression.table.queryExpression);
    }
    return equals(this.nodes, paramExpression.nodes);
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if ((paramObject instanceof Expression)) {
      return equals((Expression)paramObject);
    }
    return false;
  }
  
  public int hashCode()
  {
    int i = this.opType + this.exprSubType;
    for (int j = 0; j < this.nodes.length; j++) {
      if (this.nodes[j] != null) {
        i += this.nodes[j].hashCode();
      }
    }
    return i;
  }
  
  static boolean equals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2) {
      return true;
    }
    return paramObject1 == null ? false : paramObject2 == null ? true : paramObject1.equals(paramObject2);
  }
  
  static boolean equals(Expression[] paramArrayOfExpression1, Expression[] paramArrayOfExpression2)
  {
    if (paramArrayOfExpression1 == paramArrayOfExpression2) {
      return true;
    }
    if (paramArrayOfExpression1.length != paramArrayOfExpression2.length) {
      return false;
    }
    int i = paramArrayOfExpression1.length;
    for (int j = 0; j < i; j++)
    {
      Expression localExpression1 = paramArrayOfExpression1[j];
      Expression localExpression2 = paramArrayOfExpression2[j];
      boolean bool = localExpression1 == null ? false : localExpression2 == null ? true : localExpression1.equals(localExpression2);
      if (!bool) {
        return false;
      }
    }
    return true;
  }
  
  boolean isComposedOf(Expression[] paramArrayOfExpression, int paramInt1, int paramInt2, OrderedIntHashSet paramOrderedIntHashSet)
  {
    switch (this.opType)
    {
    case 1: 
    case 6: 
    case 7: 
    case 8: 
      return true;
    }
    if (paramOrderedIntHashSet.contains(this.opType)) {
      return true;
    }
    for (int i = paramInt1; i < paramInt2; i++) {
      if (equals(paramArrayOfExpression[i])) {
        return true;
      }
    }
    switch (this.opType)
    {
    case 19: 
    case 23: 
    case 53: 
    case 55: 
    case 57: 
    case 59: 
    case 60: 
    case 61: 
    case 62: 
    case 63: 
    case 64: 
    case 71: 
    case 72: 
    case 73: 
    case 74: 
    case 75: 
    case 76: 
    case 77: 
    case 78: 
    case 79: 
    case 80: 
    case 81: 
    case 100: 
      return false;
    case 22: 
      if (this.table != null)
      {
        if (!(this.table.getQueryExpression() instanceof QuerySpecification)) {
          return false;
        }
        QuerySpecification localQuerySpecification = (QuerySpecification)this.table.getQueryExpression();
        OrderedHashSet localOrderedHashSet = new OrderedHashSet();
        for (int k = paramInt1; k < paramInt2; k++) {
          if (paramArrayOfExpression[k].opType == 2) {
            localOrderedHashSet.add(paramArrayOfExpression[k]);
          }
        }
        return localQuerySpecification.collectOuterColumnExpressions(null, localOrderedHashSet) == null;
      }
      break;
    }
    if (this.nodes.length == 0) {
      return false;
    }
    boolean bool = true;
    for (int j = 0; j < this.nodes.length; j++) {
      bool &= ((this.nodes[j] == null) || (this.nodes[j].isComposedOf(paramArrayOfExpression, paramInt1, paramInt2, paramOrderedIntHashSet)));
    }
    return bool;
  }
  
  boolean isComposedOf(OrderedHashSet paramOrderedHashSet, RangeGroup[] paramArrayOfRangeGroup, OrderedIntHashSet paramOrderedIntHashSet)
  {
    if ((this.opType == 1) || (this.opType == 8) || (this.opType == 7) || (this.opType == 6)) {
      return true;
    }
    if (paramOrderedIntHashSet.contains(this.opType)) {
      return true;
    }
    for (int i = 0; i < paramOrderedHashSet.size(); i++) {
      if (equals(paramOrderedHashSet.get(i))) {
        return true;
      }
    }
    if (this.opType == 2) {
      for (i = 0; i < paramArrayOfRangeGroup.length; i++)
      {
        RangeVariable[] arrayOfRangeVariable = paramArrayOfRangeGroup[i].getRangeVariables();
        for (int k = 0; k < arrayOfRangeVariable.length; k++) {
          if (arrayOfRangeVariable[k] == getRangeVariable()) {
            return true;
          }
        }
      }
    }
    switch (this.opType)
    {
    case 71: 
    case 72: 
    case 73: 
    case 74: 
    case 75: 
    case 76: 
    case 77: 
    case 78: 
    case 79: 
    case 80: 
    case 81: 
      return false;
    }
    if (this.nodes.length == 0) {
      return false;
    }
    i = 1;
    for (int j = 0; j < this.nodes.length; j++) {
      i &= ((this.nodes[j] == null) || (this.nodes[j].isComposedOf(paramOrderedHashSet, paramArrayOfRangeGroup, paramOrderedIntHashSet)) ? 1 : 0);
    }
    return i;
  }
  
  Expression replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i] = this.nodes[i].replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
      }
    }
    if ((this.table != null) && (this.table.queryExpression != null)) {
      this.table.queryExpression.replaceColumnReferences(paramRangeVariable, paramArrayOfExpression);
    }
    return this;
  }
  
  void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i].replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2);
      }
    }
    if ((this.table != null) && (this.table.queryExpression != null)) {
      this.table.queryExpression.replaceRangeVariables(paramArrayOfRangeVariable1, paramArrayOfRangeVariable2);
    }
  }
  
  void resetColumnReferences()
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i].resetColumnReferences();
      }
    }
  }
  
  void convertToSimpleColumn(OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2)
  {
    if (this.opType == 1) {
      return;
    }
    if (this.opType == 5) {
      return;
    }
    int i = paramOrderedHashSet1.getIndex(this);
    if (i != -1)
    {
      Expression localExpression1 = (Expression)paramOrderedHashSet2.get(i);
      this.nodes = emptyArray;
      this.opType = 5;
      this.columnIndex = localExpression1.columnIndex;
      this.rangePosition = localExpression1.rangePosition;
      return;
    }
    for (int j = 0; j < this.nodes.length; j++) {
      if (this.nodes[j] != null) {
        this.nodes[j].convertToSimpleColumn(paramOrderedHashSet1, paramOrderedHashSet2);
      }
    }
    if ((this.table != null) && (this.table.queryExpression != null))
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      this.table.queryExpression.collectAllExpressions(localOrderedHashSet, columnExpressionSet, emptyExpressionSet);
      for (int k = 0; k < localOrderedHashSet.size(); k++)
      {
        Expression localExpression2 = (Expression)localOrderedHashSet.get(k);
        localExpression2.convertToSimpleColumn(paramOrderedHashSet1, paramOrderedHashSet2);
      }
    }
  }
  
  boolean isAggregate()
  {
    return this.isAggregate;
  }
  
  void setAggregate()
  {
    this.isAggregate = true;
  }
  
  boolean isSelfAggregate()
  {
    return false;
  }
  
  void setAlias(HsqlNameManager.SimpleName paramSimpleName)
  {
    this.alias = paramSimpleName;
  }
  
  String getAlias()
  {
    if (this.alias != null) {
      return this.alias.name;
    }
    return "";
  }
  
  HsqlNameManager.SimpleName getSimpleName()
  {
    if (this.alias != null) {
      return this.alias;
    }
    return null;
  }
  
  public int getType()
  {
    return this.opType;
  }
  
  Expression getLeftNode()
  {
    return this.nodes.length > 0 ? this.nodes[0] : null;
  }
  
  Expression getRightNode()
  {
    return this.nodes.length > 1 ? this.nodes[1] : null;
  }
  
  void setLeftNode(Expression paramExpression)
  {
    this.nodes[0] = paramExpression;
  }
  
  void setRightNode(Expression paramExpression)
  {
    this.nodes[1] = paramExpression;
  }
  
  void setSubType(int paramInt)
  {
    this.exprSubType = paramInt;
  }
  
  RangeVariable getRangeVariable()
  {
    return null;
  }
  
  Expression replaceAliasInOrderBy(Expression[] paramArrayOfExpression, int paramInt)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i] = this.nodes[i].replaceAliasInOrderBy(paramArrayOfExpression, paramInt);
      }
    }
    return this;
  }
  
  OrderedHashSet collectRangeVariables(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        paramOrderedHashSet = this.nodes[i].collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet);
      }
    }
    if ((this.table != null) && (this.table.queryExpression != null)) {
      paramOrderedHashSet = this.table.queryExpression.collectRangeVariables(paramArrayOfRangeVariable, paramOrderedHashSet);
    }
    return paramOrderedHashSet;
  }
  
  void collectObjectNames(Set paramSet)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i].collectObjectNames(paramSet);
      }
    }
    if ((this.table != null) && (this.table.queryExpression != null)) {
      this.table.queryExpression.collectObjectNames(paramSet);
    }
  }
  
  boolean hasReference(RangeVariable paramRangeVariable)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if ((this.nodes[i] != null) && (this.nodes[i].hasReference(paramRangeVariable))) {
        return true;
      }
    }
    return (this.table != null) && (this.table.queryExpression != null) && (this.table.queryExpression.hasReference(paramRangeVariable));
  }
  
  boolean hasReference(RangeVariable[] paramArrayOfRangeVariable, int paramInt)
  {
    OrderedHashSet localOrderedHashSet = collectRangeVariables(paramArrayOfRangeVariable, null);
    if (localOrderedHashSet == null) {
      return false;
    }
    for (int i = 0; i < localOrderedHashSet.size(); i++) {
      if (localOrderedHashSet.get(i) != paramArrayOfRangeVariable[paramInt]) {
        return true;
      }
    }
    return false;
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList)
  {
    return resolveColumnReferences(paramSession, paramRangeGroup, paramRangeGroup.getRangeVariables().length, paramArrayOfRangeGroup, paramHsqlList, true);
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean)
  {
    if (this.opType == 1) {
      return paramHsqlList;
    }
    switch (this.opType)
    {
    case 26: 
    case 30: 
      if (this.table != null)
      {
        if (paramRangeGroup.getRangeVariables().length > paramInt)
        {
          RangeVariable[] arrayOfRangeVariable1 = (RangeVariable[])ArrayUtil.resizeArray(paramRangeGroup.getRangeVariables(), paramInt);
          paramRangeGroup = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable1, paramRangeGroup);
        }
        paramArrayOfRangeGroup = (RangeGroup[])ArrayUtil.toAdjustedArray(paramArrayOfRangeGroup, paramRangeGroup, paramArrayOfRangeGroup.length, 1);
        paramRangeGroup = new RangeGroup.RangeGroupSimple(this.table);
        paramInt = 0;
      }
      for (i = 0; i < this.nodes.length; i++) {
        if (this.nodes[i] != null) {
          paramHsqlList = this.nodes[i].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean);
        }
      }
      return paramHsqlList;
    }
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        paramHsqlList = this.nodes[i].resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean);
      }
    }
    switch (this.opType)
    {
    case 19: 
      break;
    case 22: 
    case 23: 
    case 100: 
      RangeVariable[] arrayOfRangeVariable2 = paramRangeGroup.getRangeVariables();
      if (arrayOfRangeVariable2.length > paramInt)
      {
        arrayOfRangeVariable2 = (RangeVariable[])ArrayUtil.resizeArray(arrayOfRangeVariable2, paramInt);
        paramRangeGroup = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable2, paramRangeGroup);
      }
      paramArrayOfRangeGroup = (RangeGroup[])ArrayUtil.toAdjustedArray(paramArrayOfRangeGroup, paramRangeGroup, paramArrayOfRangeGroup.length, 1);
      QueryExpression localQueryExpression = this.table.queryExpression;
      if (localQueryExpression != null)
      {
        localQueryExpression.resolveReferences(paramSession, paramArrayOfRangeGroup);
        if (!localQueryExpression.areColumnsResolved())
        {
          if (paramHsqlList == null) {
            paramHsqlList = new ArrayListIdentity();
          }
          paramHsqlList.addAll(localQueryExpression.getUnresolvedExpressions());
        }
      }
      Expression localExpression = this.table.dataExpression;
      if (localExpression != null) {
        paramHsqlList = localExpression.resolveColumnReferences(paramSession, paramRangeGroup, paramInt, paramArrayOfRangeGroup, paramHsqlList, paramBoolean);
      }
      break;
    }
    return paramHsqlList;
  }
  
  public OrderedHashSet getUnkeyedColumns(OrderedHashSet paramOrderedHashSet)
  {
    if (this.opType == 1) {
      return paramOrderedHashSet;
    }
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        paramOrderedHashSet = this.nodes[i].getUnkeyedColumns(paramOrderedHashSet);
      }
    }
    switch (this.opType)
    {
    case 19: 
    case 22: 
    case 23: 
    case 100: 
      if (this.table != null)
      {
        if (paramOrderedHashSet == null) {
          paramOrderedHashSet = new OrderedHashSet();
        }
        paramOrderedHashSet.add(this);
      }
      break;
    }
    return paramOrderedHashSet;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i].resolveTypes(paramSession, this);
      }
    }
    Object localObject;
    switch (this.opType)
    {
    case 1: 
      break;
    case 26: 
      break;
    case 25: 
      this.nodeDataTypes = new Type[this.nodes.length];
      for (i = 0; i < this.nodes.length; i++) {
        if (this.nodes[i] != null) {
          this.nodeDataTypes[i] = this.nodes[i].dataType;
        }
      }
      break;
    case 19: 
      localObject = null;
      for (int j = 0; j < this.nodes.length; j++) {
        localObject = Type.getAggregateType((Type)localObject, this.nodes[j].dataType);
      }
      for (j = 0; j < this.nodes.length; j++) {
        this.nodes[j].dataType = ((Type)localObject);
      }
      if (localObject != null) {
        for (j = 0; j < this.nodes.length; j++) {
          if (this.nodes[j].valueData != null) {
            this.nodes[j].valueData = ((Type)localObject).convertToDefaultType(paramSession, this.nodes[j].valueData);
          }
        }
      }
      this.dataType = new ArrayType((Type)localObject, this.nodes.length);
      return;
    case 100: 
      localObject = this.table.queryExpression;
      ((QueryExpression)localObject).resolveTypes(paramSession);
      this.table.prepareTable();
      this.nodeDataTypes = ((QueryExpression)localObject).getColumnTypes();
      this.dataType = this.nodeDataTypes[0];
      if (this.nodeDataTypes.length > 1) {
        throw Error.error(5564);
      }
      this.dataType = new ArrayType(this.dataType, this.nodes.length);
      break;
    case 22: 
    case 23: 
      localObject = this.table.queryExpression;
      if (localObject != null) {
        ((QueryExpression)localObject).resolveTypes(paramSession);
      }
      Expression localExpression = this.table.dataExpression;
      if (localExpression != null) {
        localExpression.resolveTypes(paramSession, null);
      }
      this.table.prepareTable();
      this.nodeDataTypes = this.table.getColumnTypes();
      this.dataType = this.nodeDataTypes[0];
      break;
    default: 
      throw Error.runtimeError(201, "Expression");
    }
  }
  
  void setAsConstantValue(Session paramSession)
  {
    this.valueData = getValue(paramSession);
    this.opType = 1;
    this.nodes = emptyArray;
  }
  
  void setAsConstantValue(Object paramObject)
  {
    this.valueData = paramObject;
    this.opType = 1;
    this.nodes = emptyArray;
  }
  
  void prepareTable(Session paramSession, Expression paramExpression, int paramInt)
  {
    if (this.nodeDataTypes != null) {
      return;
    }
    Object localObject;
    for (int i = 0; i < this.nodes.length; i++)
    {
      localObject = this.nodes[i];
      if (((Expression)localObject).opType == 25)
      {
        if (paramInt != ((Expression)localObject).nodes.length) {
          throw Error.error(5564);
        }
      }
      else if (paramInt == 1)
      {
        this.nodes[i] = new Expression(25);
        this.nodes[i].nodes = new Expression[] { localObject };
      }
      else
      {
        throw Error.error(5564);
      }
    }
    this.nodeDataTypes = new Type[paramInt];
    for (i = 0; i < paramInt; i++)
    {
      localObject = paramExpression == null ? null : paramExpression.nodes[i].dataType;
      boolean bool = paramExpression == null ? false : paramExpression.nodes[i].isUnresolvedParam();
      for (int j = 0; j < this.nodes.length; j++)
      {
        localObject = Type.getAggregateType(this.nodes[j].nodes[i].dataType, (Type)localObject);
        bool |= this.nodes[j].nodes[i].isUnresolvedParam();
      }
      if (localObject == null) {
        localObject = Type.SQL_VARCHAR_DEFAULT;
      }
      j = ((Type)localObject).typeCode;
      if ((bool) && (((Type)localObject).isCharacterType()) && ((j == 1) || (((Type)localObject).precision < Type.SQL_VARCHAR_DEFAULT.precision)))
      {
        if (j == 1) {
          j = 12;
        }
        long l = Math.max(Type.SQL_VARCHAR_DEFAULT.precision, ((Type)localObject).precision);
        localObject = CharacterType.getCharacterType(j, l, ((Type)localObject).getCollation());
      }
      this.nodeDataTypes[i] = localObject;
      if ((paramExpression != null) && (paramExpression.nodes[i].isUnresolvedParam())) {
        paramExpression.nodes[i].dataType = ((Type)localObject);
      }
      for (int k = 0; k < this.nodes.length; k++) {
        if (this.nodes[k].nodes[i].isUnresolvedParam()) {
          this.nodes[k].nodes[i].dataType = this.nodeDataTypes[i];
        } else if ((this.nodes[k].nodes[i].opType == 1) && (this.nodes[k].nodes[i].valueData == null)) {
          this.nodes[k].nodes[i].dataType = this.nodeDataTypes[i];
        }
      }
      if ((!this.nodeDataTypes[i].isCharacterType()) || (((CharacterType)this.nodeDataTypes[i]).isEqualIdentical())) {}
    }
  }
  
  void insertValuesIntoSubqueryTable(Session paramSession, PersistentStore paramPersistentStore)
  {
    for (int i = 0; i < this.nodes.length; i++)
    {
      Object[] arrayOfObject = this.nodes[i].getRowValue(paramSession);
      for (int j = 0; j < this.nodeDataTypes.length; j++) {
        arrayOfObject[j] = this.nodeDataTypes[j].convertToType(paramSession, arrayOfObject[j], this.nodes[i].nodes[j].dataType);
      }
      Row localRow = (Row)paramPersistentStore.getNewCachedObject(paramSession, arrayOfObject, false);
      try
      {
        paramPersistentStore.indexRow(paramSession, localRow);
      }
      catch (HsqlException localHsqlException) {}
    }
  }
  
  String getColumnName()
  {
    return getAlias();
  }
  
  public ColumnSchema getColumn()
  {
    return null;
  }
  
  int getColumnIndex()
  {
    return this.columnIndex;
  }
  
  Type getDataType()
  {
    return this.dataType;
  }
  
  byte getNullability()
  {
    return this.nullability;
  }
  
  Type getNodeDataType(int paramInt)
  {
    if (this.nodeDataTypes == null)
    {
      if (paramInt > 0) {
        throw Error.runtimeError(201, "Expression");
      }
      return this.dataType;
    }
    return this.nodeDataTypes[paramInt];
  }
  
  Type[] getNodeDataTypes()
  {
    if (this.nodeDataTypes == null) {
      return new Type[] { this.dataType };
    }
    return this.nodeDataTypes;
  }
  
  int getDegree()
  {
    switch (this.opType)
    {
    case 25: 
      return this.nodes.length;
    case 22: 
    case 23: 
    case 30: 
      if (this.table == null) {
        return this.nodeDataTypes.length;
      }
      return this.table.queryExpression.getColumnCount();
    }
    return 1;
  }
  
  public Table getTable()
  {
    return this.table;
  }
  
  public void materialise(Session paramSession)
  {
    if (this.table == null) {
      return;
    }
    if (this.table.isCorrelated()) {
      this.table.materialiseCorrelated(paramSession);
    } else {
      this.table.materialise(paramSession);
    }
  }
  
  Object getValue(Session paramSession, Type paramType)
  {
    Object localObject = getValue(paramSession);
    if ((localObject == null) || (this.dataType == paramType)) {
      return localObject;
    }
    return paramType.convertToType(paramSession, localObject, this.dataType);
  }
  
  public Object getConstantValueNoCheck(Session paramSession)
  {
    try
    {
      return getValue(paramSession);
    }
    catch (HsqlException localHsqlException) {}
    return null;
  }
  
  public Object[] getRowValue(Session paramSession)
  {
    switch (this.opType)
    {
    case 25: 
      Object[] arrayOfObject = new Object[this.nodes.length];
      for (int i = 0; i < this.nodes.length; i++) {
        arrayOfObject[i] = this.nodes[i].getValue(paramSession);
      }
      return arrayOfObject;
    case 22: 
    case 23: 
      return this.table.queryExpression.getValues(paramSession);
    }
    throw Error.runtimeError(201, "Expression");
  }
  
  public Object getValue(Session paramSession)
  {
    Object localObject;
    int i;
    switch (this.opType)
    {
    case 1: 
      return this.valueData;
    case 5: 
      localObject = paramSession.sessionContext.rangeIterators[this.rangePosition].getCurrent(this.columnIndex);
      return localObject;
    case 25: 
      if (this.nodes.length == 1) {
        return this.nodes[0].getValue(paramSession);
      }
      localObject = new Object[this.nodes.length];
      for (i = 0; i < this.nodes.length; i++) {
        localObject[i] = this.nodes[i].getValue(paramSession);
      }
      return localObject;
    case 19: 
      localObject = new Object[this.nodes.length];
      for (i = 0; i < this.nodes.length; i++) {
        localObject[i] = this.nodes[i].getValue(paramSession);
      }
      return localObject;
    case 100: 
      this.table.materialiseCorrelated(paramSession);
      localObject = this.table.getNavigator(paramSession);
      i = ((RowSetNavigatorData)localObject).getSize();
      Object[] arrayOfObject1 = new Object[i];
      ((RowSetNavigatorData)localObject).beforeFirst();
      for (int j = 0; ((RowSetNavigatorData)localObject).hasNext(); j++)
      {
        Object[] arrayOfObject2 = ((RowSetNavigatorData)localObject).getNextRowData();
        arrayOfObject1[j] = arrayOfObject2[0];
      }
      return arrayOfObject1;
    case 22: 
    case 23: 
      this.table.materialiseCorrelated(paramSession);
      localObject = this.table.getValues(paramSession);
      if (localObject.length == 1) {
        return ((Object[])localObject)[0];
      }
      return localObject;
    }
    throw Error.runtimeError(201, "Expression");
  }
  
  public Result getResult(Session paramSession)
  {
    Object localObject2;
    switch (this.opType)
    {
    case 19: 
      localObject1 = this.table.getNavigator(paramSession);
      localObject2 = new Object[((RowSetNavigatorData)localObject1).getSize()];
      ((RowSetNavigatorData)localObject1).beforeFirst();
      for (int i = 0; ((RowSetNavigatorData)localObject1).hasNext(); i++)
      {
        Object[] arrayOfObject = ((RowSetNavigatorData)localObject1).getNext();
        localObject2[i] = arrayOfObject[0];
      }
      return Result.newPSMResult(localObject2);
    case 23: 
      this.table.materialiseCorrelated(paramSession);
      localObject1 = this.table.getNavigator(paramSession);
      localObject2 = Result.newResult((RowSetNavigator)localObject1);
      ((Result)localObject2).metaData = this.table.queryExpression.getMetaData();
      return localObject2;
    }
    Object localObject1 = getValue(paramSession);
    return Result.newPSMResult(localObject1);
  }
  
  public boolean testCondition(Session paramSession)
  {
    return Boolean.TRUE.equals(getValue(paramSession));
  }
  
  static int countNulls(Object[] paramArrayOfObject)
  {
    int i = 0;
    for (int j = 0; j < paramArrayOfObject.length; j++) {
      if (paramArrayOfObject[j] == null) {
        i++;
      }
    }
    return i;
  }
  
  public boolean isIndexable(RangeVariable paramRangeVariable)
  {
    return false;
  }
  
  static void convertToType(Session paramSession, Object[] paramArrayOfObject, Type[] paramArrayOfType1, Type[] paramArrayOfType2)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      if (paramArrayOfType1[i].typeComparisonGroup != paramArrayOfType2[i].typeComparisonGroup) {
        paramArrayOfObject[i] = paramArrayOfType2[i].convertToType(paramSession, paramArrayOfObject[i], paramArrayOfType1[i]);
      }
    }
  }
  
  static QuerySpecification getCheckSelect(Session paramSession, Table paramTable, Expression paramExpression)
  {
    ParserDQL.CompileContext localCompileContext = new ParserDQL.CompileContext(paramSession, null);
    localCompileContext.reset(0);
    QuerySpecification localQuerySpecification = new QuerySpecification(localCompileContext);
    RangeVariable localRangeVariable = new RangeVariable(paramTable, null, null, null, localCompileContext);
    RangeVariable[] arrayOfRangeVariable = { localRangeVariable };
    RangeGroup.RangeGroupSimple localRangeGroupSimple = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable);
    paramExpression.resolveCheckOrGenExpression(paramSession, localRangeGroupSimple, true);
    if (Type.SQL_BOOLEAN != paramExpression.getDataType()) {
      throw Error.error(5568);
    }
    ExpressionLogical localExpressionLogical = new ExpressionLogical(48, paramExpression);
    localQuerySpecification.addSelectColumnExpression(EXPR_TRUE);
    localQuerySpecification.addRangeVariable(paramSession, localRangeVariable);
    localQuerySpecification.addQueryCondition(localExpressionLogical);
    localQuerySpecification.resolve(paramSession);
    return localQuerySpecification;
  }
  
  public void resolveCheckOrGenExpression(Session paramSession, RangeGroup paramRangeGroup, boolean paramBoolean)
  {
    int i = 0;
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    HsqlList localHsqlList = resolveColumnReferences(paramSession, paramRangeGroup, RangeGroup.emptyArray, null);
    ExpressionColumn.checkColumnsResolved(localHsqlList);
    resolveTypes(paramSession, null);
    collectAllExpressions(localOrderedHashSet, subqueryAggregateExpressionSet, emptyExpressionSet);
    if (!localOrderedHashSet.isEmpty()) {
      throw Error.error(5512);
    }
    collectAllExpressions(localOrderedHashSet, functionExpressionSet, emptyExpressionSet);
    for (int j = 0; j < localOrderedHashSet.size(); j++)
    {
      Expression localExpression1 = (Expression)localOrderedHashSet.get(j);
      if ((localExpression1.opType == 27) && (!((FunctionSQLInvoked)localExpression1).isDeterministic())) {
        throw Error.error(5512);
      }
      if ((localExpression1.opType == 28) && (!((FunctionSQL)localExpression1).isDeterministic())) {
        if (paramBoolean) {
          i = 1;
        } else {
          throw Error.error(5512);
        }
      }
    }
    Object localObject2;
    if ((paramBoolean) && (i != 0))
    {
      localObject1 = new HsqlArrayList();
      RangeVariableResolver.decomposeAndConditions(paramSession, this, (HsqlList)localObject1);
      for (k = 0; k < ((HsqlArrayList)localObject1).size(); k++)
      {
        i = 1;
        localObject2 = (Expression)((HsqlArrayList)localObject1).get(k);
        if (!(localObject2 instanceof ExpressionLogical)) {
          break;
        }
        boolean bool = ((ExpressionLogical)localObject2).convertToSmaller();
        if (!bool) {
          break;
        }
        Expression localExpression2 = ((Expression)localObject2).getRightNode();
        localObject2 = ((Expression)localObject2).getLeftNode();
        if (!((Expression)localObject2).dataType.isDateTimeType())
        {
          i = 1;
          break;
        }
        if (((Expression)localObject2).hasNonDeterministicFunction())
        {
          i = 1;
          break;
        }
        if ((localExpression2 instanceof ExpressionArithmetic))
        {
          if (this.opType == 32)
          {
            if (localExpression2.getRightNode().hasNonDeterministicFunction()) {
              localExpression2.swapLeftAndRightNodes();
            }
          }
          else {
            if (this.opType != 33) {
              break;
            }
          }
          if (localExpression2.getRightNode().hasNonDeterministicFunction()) {
            break;
          }
          localExpression2 = localExpression2.getLeftNode();
        }
        if (localExpression2.opType != 28) {
          break;
        }
        FunctionSQL localFunctionSQL = (FunctionSQL)localExpression2;
        switch (localFunctionSQL.funcType)
        {
        case 43: 
        case 50: 
        case 52: 
          i = 0;
          break;
        default: 
          break label435;
        }
      }
      label435:
      if (i != 0) {
        throw Error.error(5512);
      }
    }
    localOrderedHashSet.clear();
    collectObjectNames(localOrderedHashSet);
    Object localObject1 = paramRangeGroup.getRangeVariables();
    for (int k = 0; k < localOrderedHashSet.size(); k++)
    {
      localObject2 = (HsqlNameManager.HsqlName)localOrderedHashSet.get(k);
      switch (((HsqlNameManager.HsqlName)localObject2).type)
      {
      case 9: 
        if (!paramBoolean)
        {
          int m = localObject1[0].rangeTable.findColumn(((HsqlNameManager.HsqlName)localObject2).name);
          ColumnSchema localColumnSchema = localObject1[0].rangeTable.getColumn(m);
          if (localColumnSchema.isGenerated()) {
            throw Error.error(5512);
          }
        }
        break;
      case 7: 
        throw Error.error(5512);
      case 24: 
        Routine localRoutine = (Routine)paramSession.database.schemaManager.getSchemaObject((HsqlNameManager.HsqlName)localObject2);
        if (!localRoutine.isDeterministic()) {
          throw Error.error(5512);
        }
        int n = localRoutine.getDataImpact();
        if ((n == 3) || (n == 4)) {
          throw Error.error(5512);
        }
        break;
      }
    }
    localOrderedHashSet.clear();
  }
  
  boolean isUnresolvedParam()
  {
    return false;
  }
  
  boolean isDynamicParam()
  {
    return false;
  }
  
  boolean hasNonDeterministicFunction()
  {
    OrderedHashSet localOrderedHashSet = null;
    localOrderedHashSet = collectAllExpressions(localOrderedHashSet, functionExpressionSet, emptyExpressionSet);
    if (localOrderedHashSet == null) {
      return false;
    }
    for (int i = 0; i < localOrderedHashSet.size(); i++)
    {
      Expression localExpression = (Expression)localOrderedHashSet.get(i);
      if (localExpression.opType == 27)
      {
        if (!((FunctionSQLInvoked)localExpression).isDeterministic()) {
          return true;
        }
      }
      else if ((localExpression.opType == 28) && (!((FunctionSQL)localExpression).isDeterministic())) {
        return true;
      }
    }
    return false;
  }
  
  void swapLeftAndRightNodes()
  {
    Expression localExpression = this.nodes[0];
    this.nodes[0] = this.nodes[1];
    this.nodes[1] = localExpression;
  }
  
  void setAttributesAsColumn(ColumnSchema paramColumnSchema, boolean paramBoolean)
  {
    throw Error.runtimeError(201, "Expression");
  }
  
  String getValueClassName()
  {
    Type localType = this.dataType == null ? NullType.getNullType() : this.dataType;
    return localType.getJDBCClassName();
  }
  
  OrderedHashSet collectAllExpressions(OrderedHashSet paramOrderedHashSet, OrderedIntHashSet paramOrderedIntHashSet1, OrderedIntHashSet paramOrderedIntHashSet2)
  {
    if (paramOrderedIntHashSet2.contains(this.opType)) {
      return paramOrderedHashSet;
    }
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        paramOrderedHashSet = this.nodes[i].collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2);
      }
    }
    i = 0;
    if (paramOrderedIntHashSet1.contains(this.opType))
    {
      if (paramOrderedHashSet == null) {
        paramOrderedHashSet = new OrderedHashSet();
      }
      paramOrderedHashSet.add(this);
      i = 1;
    }
    if ((i == 0) && (this.table != null) && (this.table.queryExpression != null)) {
      paramOrderedHashSet = this.table.queryExpression.collectAllExpressions(paramOrderedHashSet, paramOrderedIntHashSet1, paramOrderedIntHashSet2);
    }
    return paramOrderedHashSet;
  }
  
  public OrderedHashSet getSubqueries()
  {
    return collectAllSubqueries(null);
  }
  
  OrderedHashSet collectAllSubqueries(OrderedHashSet paramOrderedHashSet)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        paramOrderedHashSet = this.nodes[i].collectAllSubqueries(paramOrderedHashSet);
      }
    }
    if (this.table != null)
    {
      OrderedHashSet localOrderedHashSet = null;
      if (this.table.queryExpression != null)
      {
        localOrderedHashSet = this.table.queryExpression.getSubqueries();
        paramOrderedHashSet = OrderedHashSet.addAll(paramOrderedHashSet, localOrderedHashSet);
      }
      if (paramOrderedHashSet == null) {
        paramOrderedHashSet = new OrderedHashSet();
      }
      paramOrderedHashSet.add(this.table);
    }
    return paramOrderedHashSet;
  }
  
  public boolean isCorrelated()
  {
    if (this.table == null) {
      return false;
    }
    return this.table.isCorrelated();
  }
  
  public void checkValidCheckConstraint()
  {
    OrderedHashSet localOrderedHashSet = null;
    localOrderedHashSet = collectAllExpressions(localOrderedHashSet, subqueryAggregateExpressionSet, emptyExpressionSet);
    if ((localOrderedHashSet != null) && (!localOrderedHashSet.isEmpty())) {
      throw Error.error(1500, "subquery in check constraint");
    }
  }
  
  static HsqlList resolveColumnSet(Session paramSession, RangeVariable[] paramArrayOfRangeVariable, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList)
  {
    return resolveColumnSet(paramSession, paramArrayOfRangeVariable, paramArrayOfRangeVariable.length, paramArrayOfRangeGroup, paramHsqlList, null);
  }
  
  static HsqlList resolveColumnSet(Session paramSession, RangeVariable[] paramArrayOfRangeVariable, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList1, HsqlList paramHsqlList2)
  {
    if (paramHsqlList1 == null) {
      return paramHsqlList2;
    }
    RangeGroup.RangeGroupSimple localRangeGroupSimple = new RangeGroup.RangeGroupSimple(paramArrayOfRangeVariable);
    for (int i = 0; i < paramHsqlList1.size(); i++)
    {
      Expression localExpression = (Expression)paramHsqlList1.get(i);
      paramHsqlList2 = localExpression.resolveColumnReferences(paramSession, localRangeGroupSimple, paramInt, paramArrayOfRangeGroup, paramHsqlList2, false);
    }
    return paramHsqlList2;
  }
  
  boolean isConditionRangeVariable(RangeVariable paramRangeVariable)
  {
    return false;
  }
  
  RangeVariable[] getJoinRangeVariables(RangeVariable[] paramArrayOfRangeVariable)
  {
    return RangeVariable.emptyArray;
  }
  
  double costFactor(Session paramSession, RangeVariable paramRangeVariable, int paramInt)
  {
    return 16.0D;
  }
  
  Expression getIndexableExpression(RangeVariable paramRangeVariable)
  {
    return null;
  }
  
  public Expression duplicate()
  {
    Expression localExpression = null;
    try
    {
      localExpression = (Expression)super.clone();
      localExpression.nodes = ((Expression[])this.nodes.clone());
      for (int i = 0; i < this.nodes.length; i++) {
        if (this.nodes[i] != null) {
          localExpression.nodes[i] = this.nodes[i].duplicate();
        }
      }
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw Error.runtimeError(201, "Expression");
    }
    return localExpression;
  }
  
  void replaceNode(Expression paramExpression1, Expression paramExpression2)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] == paramExpression1)
      {
        paramExpression2.alias = this.nodes[i].alias;
        this.nodes[i] = paramExpression2;
        return;
      }
    }
    throw Error.runtimeError(201, "Expression");
  }
  
  public Object updateAggregatingValue(Session paramSession, Object paramObject)
  {
    throw Error.runtimeError(201, "Expression");
  }
  
  public Object getAggregatedValue(Session paramSession, Object paramObject)
  {
    throw Error.runtimeError(201, "Expression");
  }
  
  public Expression getCondition()
  {
    return null;
  }
  
  public boolean hasCondition()
  {
    return false;
  }
  
  public void setCondition(Expression paramExpression)
  {
    throw Error.runtimeError(201, "Expression");
  }
  
  public void setCollation(Collation paramCollation)
  {
    this.collation = paramCollation;
  }
  
  static
  {
    aggregateFunctionSet.add(71);
    aggregateFunctionSet.add(72);
    aggregateFunctionSet.add(73);
    aggregateFunctionSet.add(74);
    aggregateFunctionSet.add(75);
    aggregateFunctionSet.add(76);
    aggregateFunctionSet.add(77);
    aggregateFunctionSet.add(78);
    aggregateFunctionSet.add(79);
    aggregateFunctionSet.add(80);
    aggregateFunctionSet.add(81);
    aggregateFunctionSet.add(83);
    aggregateFunctionSet.add(82);
    aggregateFunctionSet.add(85);
    aggregateFunctionSet.add(98);
    columnExpressionSet = new OrderedIntHashSet();
    columnExpressionSet.add(2);
    subqueryExpressionSet = new OrderedIntHashSet();
    subqueryExpressionSet.add(22);
    subqueryExpressionSet.add(23);
    subqueryAggregateExpressionSet = new OrderedIntHashSet();
    subqueryAggregateExpressionSet.add(71);
    subqueryAggregateExpressionSet.add(72);
    subqueryAggregateExpressionSet.add(73);
    subqueryAggregateExpressionSet.add(74);
    subqueryAggregateExpressionSet.add(75);
    subqueryAggregateExpressionSet.add(76);
    subqueryAggregateExpressionSet.add(77);
    subqueryAggregateExpressionSet.add(78);
    subqueryAggregateExpressionSet.add(79);
    subqueryAggregateExpressionSet.add(80);
    subqueryAggregateExpressionSet.add(81);
    subqueryAggregateExpressionSet.add(83);
    subqueryAggregateExpressionSet.add(82);
    subqueryAggregateExpressionSet.add(85);
    subqueryAggregateExpressionSet.add(98);
    subqueryAggregateExpressionSet.add(23);
    subqueryAggregateExpressionSet.add(22);
    functionExpressionSet = new OrderedIntHashSet();
    functionExpressionSet.add(28);
    functionExpressionSet.add(27);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.Expression
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */