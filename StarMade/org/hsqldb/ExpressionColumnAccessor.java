package org.hsqldb;

import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.types.Type;

public class ExpressionColumnAccessor
  extends Expression
{
  ColumnSchema column;
  
  ExpressionColumnAccessor(ColumnSchema paramColumnSchema)
  {
    super(2);
    this.column = paramColumnSchema;
    this.dataType = paramColumnSchema.getDataType();
  }
  
  String getAlias()
  {
    return this.column.getNameString();
  }
  
  void collectObjectNames(Set paramSet)
  {
    paramSet.add(this.column.getName());
    if (this.column.getName().parent != null) {
      paramSet.add(this.column.getName().parent);
    }
  }
  
  String getColumnName()
  {
    return this.column.getNameString();
  }
  
  public ColumnSchema getColumn()
  {
    return this.column;
  }
  
  RangeVariable getRangeVariable()
  {
    return null;
  }
  
  public HsqlList resolveColumnReferences(Session paramSession, RangeGroup paramRangeGroup, int paramInt, RangeGroup[] paramArrayOfRangeGroup, HsqlList paramHsqlList, boolean paramBoolean)
  {
    return paramHsqlList;
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression) {}
  
  public Object getValue(Session paramSession)
  {
    return null;
  }
  
  public String getSQL()
  {
    return this.column.getName().statementName;
  }
  
  protected String describe(Session paramSession, int paramInt)
  {
    return this.column.getName().name;
  }
  
  public OrderedHashSet getUnkeyedColumns(OrderedHashSet paramOrderedHashSet)
  {
    return paramOrderedHashSet;
  }
  
  OrderedHashSet collectRangeVariables(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet)
  {
    return paramOrderedHashSet;
  }
  
  Expression replaceAliasInOrderBy(Expression[] paramArrayOfExpression, int paramInt)
  {
    return this;
  }
  
  Expression replaceColumnReferences(RangeVariable paramRangeVariable, Expression[] paramArrayOfExpression)
  {
    return this;
  }
  
  boolean hasReference(RangeVariable paramRangeVariable)
  {
    return false;
  }
  
  public boolean equals(Expression paramExpression)
  {
    if (paramExpression == this) {
      return true;
    }
    if (paramExpression == null) {
      return false;
    }
    if (this.opType != paramExpression.opType) {
      return false;
    }
    return this.column == paramExpression.getColumn();
  }
  
  void replaceRangeVariables(RangeVariable[] paramArrayOfRangeVariable1, RangeVariable[] paramArrayOfRangeVariable2) {}
  
  void resetColumnReferences() {}
  
  public boolean isIndexable(RangeVariable paramRangeVariable)
  {
    return false;
  }
  
  public boolean isUnresolvedParam()
  {
    return false;
  }
  
  boolean isDynamicParam()
  {
    return false;
  }
  
  public Type getDataType()
  {
    return this.column.getDataType();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.ExpressionColumnAccessor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */