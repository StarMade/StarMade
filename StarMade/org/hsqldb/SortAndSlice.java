package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;

public final class SortAndSlice
{
  static final SortAndSlice noSort = new SortAndSlice();
  static final int[] defaultLimits = { 0, 2147483647, 2147483647 };
  public int[] sortOrder;
  public boolean[] sortDescending;
  public boolean[] sortNullsLast;
  public Collation[] collations;
  boolean sortUnion;
  HsqlArrayList exprList = new HsqlArrayList();
  ExpressionOp limitCondition;
  int columnCount;
  boolean hasNullsLast;
  boolean strictLimit;
  boolean zeroLimit;
  boolean usingIndex;
  boolean allDescending;
  public boolean skipSort = false;
  public boolean skipFullResult = false;
  public Index index;
  public Table primaryTable;
  public Index primaryTableIndex;
  public int[] colIndexes;
  public boolean isGenerated;

  public HsqlArrayList getExpressionList()
  {
    return this.exprList;
  }

  public boolean hasOrder()
  {
    return this.exprList.size() != 0;
  }

  public boolean hasLimit()
  {
    return this.limitCondition != null;
  }

  public int getOrderLength()
  {
    return this.exprList.size();
  }

  public void addOrderExpression(Expression paramExpression)
  {
    this.exprList.add(paramExpression);
  }

  public void addLimitCondition(ExpressionOp paramExpressionOp)
  {
    this.limitCondition = paramExpressionOp;
  }

  public void setStrictLimit()
  {
    this.strictLimit = true;
  }

  public void setZeroLimit()
  {
    this.zeroLimit = true;
  }

  public void setUsingIndex()
  {
    this.usingIndex = true;
  }

  public void prepareSingleColumn(int paramInt)
  {
    this.sortOrder = new int[1];
    this.sortDescending = new boolean[1];
    this.sortNullsLast = new boolean[1];
    this.sortOrder[0] = paramInt;
  }

  public void prepareMultiColumn(int paramInt)
  {
    this.sortOrder = new int[paramInt];
    this.sortDescending = new boolean[paramInt];
    this.sortNullsLast = new boolean[paramInt];
    for (int i = 0; i < paramInt; i++)
      this.sortOrder[i] = i;
  }

  public void prepare(int paramInt)
  {
    this.columnCount = this.exprList.size();
    if (this.columnCount == 0)
      return;
    this.sortOrder = new int[this.columnCount + paramInt];
    this.sortDescending = new boolean[this.columnCount + paramInt];
    this.sortNullsLast = new boolean[this.columnCount + paramInt];
    ArrayUtil.fillSequence(this.sortOrder);
    for (int i = 0; i < this.columnCount; i++)
    {
      ExpressionOrderBy localExpressionOrderBy = (ExpressionOrderBy)this.exprList.get(i);
      this.sortDescending[i] = localExpressionOrderBy.isDescending();
      this.sortNullsLast[i] = localExpressionOrderBy.isNullsLast();
      this.hasNullsLast |= this.sortNullsLast[i];
    }
  }

  public void prepare(QuerySpecification paramQuerySpecification)
  {
    this.columnCount = this.exprList.size();
    if (this.columnCount == 0)
      return;
    this.sortOrder = new int[this.columnCount];
    this.sortDescending = new boolean[this.columnCount];
    this.sortNullsLast = new boolean[this.columnCount];
    for (int i = 0; i < this.columnCount; i++)
    {
      ExpressionOrderBy localExpressionOrderBy = (ExpressionOrderBy)this.exprList.get(i);
      if (localExpressionOrderBy.getLeftNode().queryTableColumnIndex == -1)
        this.sortOrder[i] = (paramQuerySpecification.indexStartOrderBy + i);
      else
        this.sortOrder[i] = localExpressionOrderBy.getLeftNode().queryTableColumnIndex;
      this.sortDescending[i] = localExpressionOrderBy.isDescending();
      this.sortNullsLast[i] = localExpressionOrderBy.isNullsLast();
      this.hasNullsLast |= this.sortNullsLast[i];
      if (localExpressionOrderBy.collation != null)
      {
        if (this.collations == null)
          this.collations = new Collation[this.columnCount];
        this.collations[i] = localExpressionOrderBy.collation;
      }
    }
  }

  void setSortIndex(QuerySpecification paramQuerySpecification)
  {
    if (this.isGenerated)
      return;
    Object localObject;
    for (int i = 0; i < this.columnCount; i++)
    {
      ExpressionOrderBy localExpressionOrderBy = (ExpressionOrderBy)this.exprList.get(i);
      localObject = localExpressionOrderBy.getLeftNode().getDataType();
      if ((((Type)localObject).isArrayType()) || (((Type)localObject).isLobType()))
        throw Error.error(5534);
    }
    if ((paramQuerySpecification.isDistinctSelect) || (paramQuerySpecification.isGrouped) || (paramQuerySpecification.isAggregated))
      return;
    if (this.columnCount == 0)
    {
      if (this.limitCondition == null)
        return;
      this.skipFullResult = true;
      return;
    }
    if (paramQuerySpecification == null)
      return;
    if (this.collations != null)
      return;
    this.colIndexes = new int[this.columnCount];
    i = 0;
    for (int j = 0; j < this.columnCount; j++)
    {
      localObject = ((Expression)this.exprList.get(j)).getLeftNode();
      if (((Expression)localObject).getType() != 2)
        return;
      if (((ExpressionColumn)localObject).getRangeVariable() != paramQuerySpecification.rangeVariables[0])
        return;
      this.colIndexes[j] = ((Expression)localObject).columnIndex;
      if (((Expression)localObject).getColumn().getNullability() != 0)
        i = 1;
    }
    if ((this.hasNullsLast) && (i != 0))
      return;
    j = ArrayUtil.countTrueElements(this.sortDescending);
    this.allDescending = (j == this.columnCount);
    if ((!this.allDescending) && (j > 0))
      return;
    this.primaryTable = paramQuerySpecification.rangeVariables[0].getTable();
    this.primaryTableIndex = this.primaryTable.getFullIndexForColumns(this.colIndexes);
  }

  void setSortRange(QuerySpecification paramQuerySpecification)
  {
    if (this.primaryTableIndex == null)
      return;
    Index localIndex = paramQuerySpecification.rangeVariables[0].getSortIndex();
    if (localIndex == null)
      return;
    if (this.primaryTable != paramQuerySpecification.rangeVariables[0].rangeTable)
      return;
    if (localIndex == this.primaryTableIndex)
    {
      if (this.allDescending)
      {
        boolean bool = paramQuerySpecification.rangeVariables[0].reverseOrder();
        if (!bool)
          return;
      }
      this.skipSort = true;
      this.skipFullResult = true;
    }
    else if ((!paramQuerySpecification.rangeVariables[0].joinConditions[0].hasIndexCondition()) && (paramQuerySpecification.rangeVariables[0].setSortIndex(this.primaryTableIndex, this.allDescending)))
    {
      this.skipSort = true;
      this.skipFullResult = true;
    }
  }

  public boolean prepareSpecial(Session paramSession, QuerySpecification paramQuerySpecification)
  {
    Expression localExpression = paramQuerySpecification.exprColumns[paramQuerySpecification.indexStartAggregates];
    int i = localExpression.getType();
    localExpression = localExpression.getLeftNode();
    if (localExpression.getType() != 2)
      return false;
    if (((ExpressionColumn)localExpression).getRangeVariable() != paramQuerySpecification.rangeVariables[0])
      return false;
    Index localIndex1 = paramQuerySpecification.rangeVariables[0].getSortIndex();
    if (localIndex1 == null)
      return false;
    Object localObject;
    if (paramQuerySpecification.rangeVariables[0].hasSingleIndexCondition())
    {
      localObject = localIndex1.getColumns();
      if (localObject[0] != ((ExpressionColumn)localExpression).getColumnIndex())
        return false;
      if (i == 74)
        paramQuerySpecification.rangeVariables[0].reverseOrder();
    }
    else
    {
      if (paramQuerySpecification.rangeVariables[0].hasAnyIndexCondition())
        return false;
      localObject = paramQuerySpecification.rangeVariables[0].getTable();
      Index localIndex2 = ((Table)localObject).getIndexForColumn(paramSession, ((ExpressionColumn)localExpression).getColumnIndex());
      if (localIndex2 == null)
        return false;
      Expression[] arrayOfExpression = { ExpressionLogical.newNotNullCondition(localExpression) };
      paramQuerySpecification.rangeVariables[0].joinConditions[0].addIndexCondition(arrayOfExpression, localIndex2, 1);
      if (i == 74)
        paramQuerySpecification.rangeVariables[0].reverseOrder();
    }
    this.columnCount = 1;
    this.sortOrder = new int[this.columnCount];
    this.sortDescending = new boolean[this.columnCount];
    this.sortNullsLast = new boolean[this.columnCount];
    this.skipSort = true;
    this.skipFullResult = true;
    return true;
  }

  int[] getLimits(Session paramSession, QueryExpression paramQueryExpression, int paramInt)
  {
    int i = 0;
    int j = 2147483647;
    int k = 2147483647;
    int m = 0;
    if (hasLimit())
    {
      Integer localInteger = (Integer)this.limitCondition.getLeftNode().getValue(paramSession);
      if ((localInteger == null) || (localInteger.intValue() < 0))
        throw Error.error(3453);
      i = localInteger.intValue();
      m = i != 0 ? 1 : 0;
      if (this.limitCondition.getRightNode() != null)
      {
        localInteger = (Integer)this.limitCondition.getRightNode().getValue(paramSession);
        if ((localInteger == null) || (localInteger.intValue() < 0) || ((this.strictLimit) && (localInteger.intValue() == 0)))
          throw Error.error(3452);
        if ((localInteger.intValue() == 0) && (!this.zeroLimit))
        {
          j = 2147483647;
        }
        else
        {
          j = localInteger.intValue();
          m = 1;
        }
      }
    }
    if (paramInt != 0)
    {
      if (paramInt < j)
        j = paramInt;
      m = 1;
    }
    int n = 0;
    if ((paramQueryExpression instanceof QuerySpecification))
    {
      QuerySpecification localQuerySpecification = (QuerySpecification)paramQueryExpression;
      if ((!localQuerySpecification.isDistinctSelect) && (!localQuerySpecification.isGrouped))
        n = 1;
    }
    if (m != 0)
    {
      if ((n != 0) && ((!hasOrder()) || (this.skipSort)) && ((!hasLimit()) || (this.skipFullResult)) && (k - i > j))
        k = i + j;
      return new int[] { i, j, k };
    }
    return defaultLimits;
  }

  public void setIndex(Session paramSession, TableBase paramTableBase)
  {
    this.index = getNewIndex(paramSession, paramTableBase);
  }

  public Index getNewIndex(Session paramSession, TableBase paramTableBase)
  {
    if (hasOrder())
    {
      Index localIndex = paramTableBase.createAndAddIndexStructure(null, this.sortOrder, this.sortDescending, this.sortNullsLast, false, false, false);
      if (this.collations != null)
        for (int i = 0; i < this.columnCount; i++)
          if (this.collations[i] != null)
          {
            Type localType = localIndex.getColumnTypes()[i];
            localType = Type.getType(localType.typeCode, localType.getCharacterSet(), this.collations[i], localType.precision, localType.scale);
            localIndex.getColumnTypes()[i] = localType;
          }
      return localIndex;
    }
    return null;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.SortAndSlice
 * JD-Core Version:    0.6.2
 */