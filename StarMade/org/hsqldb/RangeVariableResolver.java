package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.index.Index.IndexUse;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.IntKeyIntValueHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.MultiValueHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.persist.PersistentStore;

public class RangeVariableResolver
{
  Session session;
  QuerySpecification select;
  RangeVariable[] rangeVariables;
  Expression conditions;
  OrderedHashSet rangeVarSet = new OrderedHashSet();
  ParserDQL.CompileContext compileContext;
  SortAndSlice sortAndSlice = SortAndSlice.noSort;
  HsqlArrayList[] tempJoinExpressions;
  HsqlArrayList[] joinExpressions;
  HsqlArrayList[] whereExpressions;
  HsqlArrayList queryConditions = new HsqlArrayList();
  Expression[] inExpressions;
  boolean[] inInJoin;
  int inExpressionCount = 0;
  boolean expandInExpression = true;
  int firstLeftJoinIndex;
  int firstRightJoinIndex;
  int lastRightJoinIndex;
  int firstLateralJoinIndex;
  int firstOuterJoinIndex;
  int lastOuterJoinIndex;
  OrderedIntHashSet colIndexSetEqual = new OrderedIntHashSet();
  IntKeyIntValueHashMap colIndexSetOther = new IntKeyIntValueHashMap();
  OrderedHashSet tempSet = new OrderedHashSet();
  HashMap tempMap = new HashMap();
  MultiValueHashMap tempMultiMap = new MultiValueHashMap();
  
  RangeVariableResolver(QuerySpecification paramQuerySpecification)
  {
    this.select = paramQuerySpecification;
    this.rangeVariables = paramQuerySpecification.rangeVariables;
    this.conditions = paramQuerySpecification.queryCondition;
    this.compileContext = paramQuerySpecification.compileContext;
    this.sortAndSlice = paramQuerySpecification.sortAndSlice;
    initialise();
  }
  
  RangeVariableResolver(RangeVariable[] paramArrayOfRangeVariable, Expression paramExpression, ParserDQL.CompileContext paramCompileContext)
  {
    this.rangeVariables = paramArrayOfRangeVariable;
    this.conditions = paramExpression;
    this.compileContext = paramCompileContext;
    initialise();
  }
  
  private void initialise()
  {
    this.firstLeftJoinIndex = this.rangeVariables.length;
    this.firstRightJoinIndex = this.rangeVariables.length;
    this.firstLateralJoinIndex = this.rangeVariables.length;
    this.firstOuterJoinIndex = this.rangeVariables.length;
    this.inExpressions = new Expression[this.rangeVariables.length];
    this.inInJoin = new boolean[this.rangeVariables.length];
    this.tempJoinExpressions = new HsqlArrayList[this.rangeVariables.length];
    for (int i = 0; i < this.rangeVariables.length; i++) {
      this.tempJoinExpressions[i] = new HsqlArrayList();
    }
    this.joinExpressions = new HsqlArrayList[this.rangeVariables.length];
    for (i = 0; i < this.rangeVariables.length; i++) {
      this.joinExpressions[i] = new HsqlArrayList();
    }
    this.whereExpressions = new HsqlArrayList[this.rangeVariables.length];
    for (i = 0; i < this.rangeVariables.length; i++) {
      this.whereExpressions[i] = new HsqlArrayList();
    }
  }
  
  void processConditions(Session paramSession)
  {
    this.session = paramSession;
    decomposeAndConditions(paramSession, this.conditions, this.queryConditions);
    for (int i = 0; i < this.rangeVariables.length; i++)
    {
      this.rangeVarSet.add(this.rangeVariables[i]);
      if (this.rangeVariables[i].joinCondition != null) {
        decomposeAndConditions(paramSession, this.rangeVariables[i].joinCondition, this.tempJoinExpressions[i]);
      }
    }
    Object localObject;
    for (i = 0; i < this.queryConditions.size(); i++)
    {
      localObject = (Expression)this.queryConditions.get(i);
      if ((localObject != ExpressionLogical.EXPR_TRUE) && ((((Expression)localObject).isSingleColumnEqual) || (((Expression)localObject).isColumnCondition)))
      {
        RangeVariable localRangeVariable = ((Expression)localObject).getLeftNode().getRangeVariable();
        int k;
        if ((((Expression)localObject).getLeftNode().opType == 2) && (localRangeVariable != null))
        {
          k = this.rangeVarSet.getIndex(localRangeVariable);
          if (k > 0)
          {
            this.rangeVariables[k].isLeftJoin = false;
            this.rangeVariables[(k - 1)].isRightJoin = false;
          }
        }
        localRangeVariable = ((Expression)localObject).getRightNode().getRangeVariable();
        if ((((Expression)localObject).getRightNode().opType == 2) && (localRangeVariable != null))
        {
          k = this.rangeVarSet.getIndex(localRangeVariable);
          if (k > 0)
          {
            this.rangeVariables[k].isLeftJoin = false;
            this.rangeVariables[(k - 1)].isRightJoin = false;
          }
        }
      }
    }
    for (i = 0; i < this.rangeVariables.length; i++)
    {
      localObject = this.rangeVariables[i];
      int j = 0;
      if (((RangeVariable)localObject).isLeftJoin)
      {
        if (this.firstLeftJoinIndex == this.rangeVariables.length) {
          this.firstLeftJoinIndex = i;
        }
        j = 1;
      }
      if (((RangeVariable)localObject).isRightJoin)
      {
        if (this.firstRightJoinIndex == this.rangeVariables.length) {
          this.firstRightJoinIndex = i;
        }
        this.lastRightJoinIndex = i;
        j = 1;
      }
      if (((RangeVariable)localObject).isLateral)
      {
        if (this.firstLateralJoinIndex == this.rangeVariables.length) {
          this.firstLateralJoinIndex = i;
        }
        j = 1;
      }
      if (j != 0)
      {
        if (this.firstOuterJoinIndex == this.rangeVariables.length) {
          this.firstOuterJoinIndex = i;
        }
        this.lastOuterJoinIndex = i;
      }
    }
    expandConditions();
    this.conditions = null;
    reorder();
    assignToLists();
    assignToRangeVariables();
    if (this.select != null)
    {
      this.select.startInnerRange = 0;
      this.select.endInnerRange = this.rangeVariables.length;
      if (this.firstRightJoinIndex < this.rangeVariables.length) {
        this.select.startInnerRange = this.firstRightJoinIndex;
      }
      if (this.firstLeftJoinIndex < this.rangeVariables.length) {
        this.select.endInnerRange = this.firstLeftJoinIndex;
      }
    }
    for (i = 0; i < this.rangeVariables.length; i++) {
      this.rangeVariables[i].rangePositionInJoin = i;
    }
    if ((this.expandInExpression) && (this.inExpressionCount != 0)) {
      setInConditionsAsTables();
    }
  }
  
  static Expression decomposeAndConditions(Session paramSession, Expression paramExpression, HsqlList paramHsqlList)
  {
    if (paramExpression == null) {
      return Expression.EXPR_TRUE;
    }
    Expression localExpression1 = paramExpression.getLeftNode();
    Expression localExpression2 = paramExpression.getRightNode();
    int i = paramExpression.getType();
    if (i == 49)
    {
      localExpression1 = decomposeAndConditions(paramSession, localExpression1, paramHsqlList);
      localExpression2 = decomposeAndConditions(paramSession, localExpression2, paramHsqlList);
      if (localExpression1 == Expression.EXPR_TRUE) {
        return localExpression2;
      }
      if (localExpression2 == Expression.EXPR_TRUE) {
        return localExpression1;
      }
      paramExpression.setLeftNode(localExpression1);
      paramExpression.setRightNode(localExpression2);
      return paramExpression;
    }
    if ((i == 41) && (localExpression1.getType() == 25) && (localExpression2.getType() == 25))
    {
      for (int j = 0; j < localExpression1.nodes.length; j++)
      {
        ExpressionLogical localExpressionLogical = new ExpressionLogical(localExpression1.nodes[j], localExpression2.nodes[j]);
        localExpressionLogical.resolveTypes(paramSession, null);
        paramHsqlList.add(localExpressionLogical);
      }
      return Expression.EXPR_TRUE;
    }
    if (paramExpression != Expression.EXPR_TRUE) {
      paramHsqlList.add(paramExpression);
    }
    return Expression.EXPR_TRUE;
  }
  
  static Expression decomposeOrConditions(Expression paramExpression, HsqlList paramHsqlList)
  {
    if (paramExpression == null) {
      return Expression.EXPR_FALSE;
    }
    Expression localExpression1 = paramExpression.getLeftNode();
    Expression localExpression2 = paramExpression.getRightNode();
    int i = paramExpression.getType();
    if (i == 50)
    {
      localExpression1 = decomposeOrConditions(localExpression1, paramHsqlList);
      localExpression2 = decomposeOrConditions(localExpression2, paramHsqlList);
      if (localExpression1 == Expression.EXPR_FALSE) {
        return localExpression2;
      }
      if (localExpression2 == Expression.EXPR_FALSE) {
        return localExpression1;
      }
      paramExpression = new ExpressionLogical(50, localExpression1, localExpression2);
      return paramExpression;
    }
    if (paramExpression != Expression.EXPR_FALSE) {
      paramHsqlList.add(paramExpression);
    }
    return Expression.EXPR_FALSE;
  }
  
  void expandConditions()
  {
    HsqlArrayList[] arrayOfHsqlArrayList = this.tempJoinExpressions;
    if (this.firstRightJoinIndex == this.rangeVariables.length) {
      moveConditions(this.tempJoinExpressions, 0, this.firstOuterJoinIndex, this.queryConditions, -1);
    }
    if (this.firstOuterJoinIndex < 2) {
      return;
    }
    for (int i = 0; i < this.firstOuterJoinIndex; i++) {
      moveConditions(this.tempJoinExpressions, 0, this.firstOuterJoinIndex, this.tempJoinExpressions[i], i);
    }
    if (this.firstOuterJoinIndex < 3) {
      return;
    }
    for (i = 0; i < this.firstOuterJoinIndex; i++)
    {
      HsqlArrayList localHsqlArrayList = arrayOfHsqlArrayList[i];
      this.tempMultiMap.clear();
      this.tempSet.clear();
      this.tempMap.clear();
      int j = 0;
      int k = 0;
      int m = 0;
      Object localObject1;
      Object localObject3;
      for (int n = 0; n < localHsqlArrayList.size(); n++)
      {
        localObject1 = (Expression)localHsqlArrayList.get(n);
        if (localObject1 != ExpressionLogical.EXPR_TRUE) {
          if (((Expression)localObject1).isSingleColumnEqual)
          {
            j = 1;
            if (((Expression)localObject1).getLeftNode().opType == 2) {
              this.tempMap.put(((Expression)localObject1).getLeftNode().getColumn(), ((Expression)localObject1).getRightNode());
            } else if (((Expression)localObject1).getRightNode().opType == 2) {
              this.tempMap.put(((Expression)localObject1).getRightNode().getColumn(), ((Expression)localObject1).getLeftNode());
            }
          }
          else if ((((Expression)localObject1).isColumnEqual) && (((Expression)localObject1).getLeftNode().getRangeVariable() != ((Expression)localObject1).getRightNode().getRangeVariable()) && (((Expression)localObject1).getLeftNode().getRangeVariable() != null) && (((Expression)localObject1).getRightNode().getRangeVariable() != null))
          {
            int i1 = this.rangeVarSet.getIndex(((Expression)localObject1).getLeftNode().getRangeVariable());
            if (i1 < 0)
            {
              ((Expression)localObject1).isSingleColumnEqual = true;
              ((Expression)localObject1).isSingleColumnCondition = true;
              this.tempMap.put(((Expression)localObject1).getRightNode().getColumn(), ((Expression)localObject1).getLeftNode());
            }
            else if (i1 < this.firstOuterJoinIndex)
            {
              i1 = this.rangeVarSet.getIndex(((Expression)localObject1).getRightNode().getRangeVariable());
              if (i1 < 0)
              {
                ((Expression)localObject1).isSingleColumnEqual = true;
                ((Expression)localObject1).isSingleColumnCondition = true;
                this.tempMap.put(((Expression)localObject1).getRightNode().getColumn(), ((Expression)localObject1).getLeftNode());
              }
              else if (i1 < this.firstOuterJoinIndex)
              {
                k = 1;
                if (((Expression)localObject1).getLeftNode().getRangeVariable() == this.rangeVariables[i])
                {
                  localObject3 = ((Expression)localObject1).getLeftNode().getColumn();
                  this.tempMultiMap.put(localObject3, ((Expression)localObject1).getRightNode());
                  if (this.tempMultiMap.valueCount(localObject3) > 1) {
                    m = 1;
                  }
                }
                else if (((Expression)localObject1).getRightNode().getRangeVariable() == this.rangeVariables[i])
                {
                  localObject3 = ((Expression)localObject1).getRightNode().getColumn();
                  this.tempMultiMap.put(localObject3, ((Expression)localObject1).getLeftNode());
                  if (this.tempMultiMap.valueCount(localObject3) > 1) {
                    m = 1;
                  }
                }
              }
            }
          }
        }
      }
      Iterator localIterator;
      Object localObject2;
      Object localObject4;
      if (m != 0)
      {
        localIterator = this.tempMultiMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          localObject1 = localIterator.next();
          localObject2 = this.tempMultiMap.get(localObject1);
          this.tempSet.clear();
          while (((Iterator)localObject2).hasNext()) {
            this.tempSet.add(((Iterator)localObject2).next());
          }
          while (this.tempSet.size() > 1)
          {
            localObject3 = (Expression)this.tempSet.remove(this.tempSet.size() - 1);
            for (int i2 = 0; i2 < this.tempSet.size(); i2++)
            {
              localObject4 = (Expression)this.tempSet.get(i2);
              closeJoinChain(arrayOfHsqlArrayList, (Expression)localObject3, (Expression)localObject4);
            }
          }
        }
      }
      if ((k != 0) && (j != 0))
      {
        localIterator = this.tempMultiMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          localObject1 = localIterator.next();
          localObject2 = (Expression)this.tempMap.get(localObject1);
          if (localObject2 != null)
          {
            localObject3 = this.tempMultiMap.get(localObject1);
            while (((Iterator)localObject3).hasNext())
            {
              Expression localExpression = (Expression)((Iterator)localObject3).next();
              localObject4 = new ExpressionLogical((Expression)localObject2, localExpression);
              int i3 = this.rangeVarSet.getIndex(localExpression.getRangeVariable());
              arrayOfHsqlArrayList[i3].add(localObject4);
            }
          }
        }
      }
    }
  }
  
  void moveConditions(HsqlList[] paramArrayOfHsqlList, int paramInt1, int paramInt2, HsqlList paramHsqlList, int paramInt3)
  {
    for (int i = 0; i < paramHsqlList.size(); i++)
    {
      Expression localExpression = (Expression)paramHsqlList.get(i);
      this.tempSet.clear();
      localExpression.collectRangeVariables(this.rangeVariables, this.tempSet);
      int j = this.rangeVarSet.getSmallestIndex(this.tempSet);
      if (j >= paramInt1)
      {
        j = this.rangeVarSet.getLargestIndex(this.tempSet);
        if ((j < paramInt2) && (j != paramInt3))
        {
          paramHsqlList.remove(i);
          paramArrayOfHsqlList[j].add(localExpression);
          i--;
        }
      }
    }
  }
  
  void closeJoinChain(HsqlList[] paramArrayOfHsqlList, Expression paramExpression1, Expression paramExpression2)
  {
    int i = this.rangeVarSet.getIndex(paramExpression1.getRangeVariable());
    int j = this.rangeVarSet.getIndex(paramExpression2.getRangeVariable());
    int k = i > j ? i : j;
    if ((i == -1) || (j == -1)) {
      return;
    }
    ExpressionLogical localExpressionLogical = new ExpressionLogical(paramExpression1, paramExpression2);
    for (int m = 0; m < paramArrayOfHsqlList[k].size(); m++) {
      if (localExpressionLogical.equals(paramArrayOfHsqlList[k].get(m))) {
        return;
      }
    }
    paramArrayOfHsqlList[k].add(localExpressionLogical);
  }
  
  void reorder()
  {
    if ((this.rangeVariables.length == 1) || (this.firstRightJoinIndex != this.rangeVariables.length)) {
      return;
    }
    if (this.firstLeftJoinIndex == 1) {
      return;
    }
    if (this.firstLateralJoinIndex != this.rangeVariables.length) {
      return;
    }
    if ((this.sortAndSlice.usingIndex) && (this.sortAndSlice.primaryTableIndex != null)) {
      return;
    }
    HsqlArrayList localHsqlArrayList1 = new HsqlArrayList();
    HsqlArrayList localHsqlArrayList2 = new HsqlArrayList();
    for (int i = 0; i < this.firstLeftJoinIndex; i++)
    {
      HsqlArrayList localHsqlArrayList3 = this.tempJoinExpressions[i];
      for (int j = 0; j < localHsqlArrayList3.size(); j++)
      {
        Expression localExpression = (Expression)localHsqlArrayList3.get(j);
        if (localExpression.isColumnEqual) {
          localHsqlArrayList1.add(localExpression);
        } else if (localExpression.isSingleColumnCondition) {
          localHsqlArrayList2.add(localExpression);
        }
      }
    }
    reorderRanges(localHsqlArrayList2, localHsqlArrayList1);
  }
  
  void reorderRanges(HsqlArrayList paramHsqlArrayList1, HsqlArrayList paramHsqlArrayList2)
  {
    if (paramHsqlArrayList1.size() == 0) {
      return;
    }
    int i = -1;
    Object localObject1 = null;
    double d1 = 1024.0D;
    Object localObject2;
    for (int j = 0; j < this.firstLeftJoinIndex; j++)
    {
      Table localTable = this.rangeVariables[j].rangeTable;
      if (!(localTable instanceof TableDerived))
      {
        collectIndexableColumns(this.rangeVariables[j], paramHsqlArrayList1);
        Index.IndexUse[] arrayOfIndexUse1 = localTable.getIndexForColumns(this.session, this.colIndexSetEqual, 41, false);
        localObject2 = null;
        for (int n = 0; n < arrayOfIndexUse1.length; n++)
        {
          localObject2 = arrayOfIndexUse1[n].index;
          PersistentStore localPersistentStore = localTable.getRowStore(this.session);
          double d2 = localPersistentStore.searchCost(this.session, (Index)localObject2, arrayOfIndexUse1[n].columnCount, 41);
          if (d2 < d1)
          {
            d1 = d2;
            i = j;
          }
        }
        if (localObject2 == null)
        {
          Iterator localIterator = this.colIndexSetOther.keySet().iterator();
          while (localIterator.hasNext())
          {
            int i2 = localIterator.nextInt();
            localObject2 = localTable.getIndexForColumn(this.session, i2);
            if (localObject2 != null)
            {
              d1 = localTable.getRowStore(this.session).elementCount() / 2L;
              if (this.colIndexSetOther.get(i2, 0) <= 1) {
                break;
              }
              d1 /= 2.0D;
              break;
            }
          }
        }
        if ((localObject2 != null) && (j == 0))
        {
          i = 0;
          break;
        }
      }
    }
    if (i < 0) {
      return;
    }
    if ((i == 0) && (this.firstLeftJoinIndex == 2)) {
      return;
    }
    RangeVariable[] arrayOfRangeVariable = new RangeVariable[this.rangeVariables.length];
    ArrayUtil.copyArray(this.rangeVariables, arrayOfRangeVariable, this.rangeVariables.length);
    localObject1 = arrayOfRangeVariable[i];
    arrayOfRangeVariable[i] = arrayOfRangeVariable[0];
    arrayOfRangeVariable[0] = localObject1;
    for (i = 1; i < this.firstLeftJoinIndex; i++)
    {
      k = 0;
      for (int m = 0; m < paramHsqlArrayList2.size(); m++)
      {
        localObject2 = (Expression)paramHsqlArrayList2.get(m);
        if (localObject2 != null)
        {
          int i1 = getJoinedRangePosition((Expression)localObject2, i, arrayOfRangeVariable);
          if (i1 >= i)
          {
            localObject1 = arrayOfRangeVariable[i];
            arrayOfRangeVariable[i] = arrayOfRangeVariable[i1];
            arrayOfRangeVariable[i1] = localObject1;
            paramHsqlArrayList2.set(m, null);
            k = 1;
            break;
          }
        }
      }
      if (k == 0)
      {
        for (m = 0; m < paramHsqlArrayList1.size(); m++)
        {
          localObject2 = arrayOfRangeVariable[m].rangeTable;
          collectIndexableColumns(arrayOfRangeVariable[m], paramHsqlArrayList1);
          Index.IndexUse[] arrayOfIndexUse2 = ((Table)localObject2).getIndexForColumns(this.session, this.colIndexSetEqual, 41, false);
          if (arrayOfIndexUse2.length > 0)
          {
            k = 1;
            break;
          }
        }
        if (k == 0) {
          break;
        }
      }
    }
    if (i != this.firstLeftJoinIndex) {
      return;
    }
    ArrayUtil.copyArray(arrayOfRangeVariable, this.rangeVariables, this.rangeVariables.length);
    paramHsqlArrayList2.clear();
    for (int k = 0; k < this.firstLeftJoinIndex; k++)
    {
      HsqlArrayList localHsqlArrayList = this.tempJoinExpressions[k];
      paramHsqlArrayList2.addAll(localHsqlArrayList);
      localHsqlArrayList.clear();
    }
    this.tempJoinExpressions[(this.firstLeftJoinIndex - 1)].addAll(paramHsqlArrayList2);
    this.rangeVarSet.clear();
    for (k = 0; k < this.rangeVariables.length; k++) {
      this.rangeVarSet.add(this.rangeVariables[k]);
    }
  }
  
  int getJoinedRangePosition(Expression paramExpression, int paramInt, RangeVariable[] paramArrayOfRangeVariable)
  {
    int i = -1;
    RangeVariable[] arrayOfRangeVariable = paramExpression.getJoinRangeVariables(paramArrayOfRangeVariable);
    for (int j = 0; j < arrayOfRangeVariable.length; j++) {
      for (int k = 0; k < paramArrayOfRangeVariable.length; k++) {
        if ((arrayOfRangeVariable[j] == paramArrayOfRangeVariable[k]) && (k >= paramInt))
        {
          if (i > 0) {
            return -1;
          }
          i = k;
        }
      }
    }
    return i;
  }
  
  void assignToLists()
  {
    int i = -1;
    for (int j = 0; j < this.rangeVariables.length; j++)
    {
      if (this.rangeVariables[j].isLeftJoin) {
        i = j;
      }
      if (this.rangeVariables[j].isRightJoin) {
        i = j;
      }
      if (i == j)
      {
        this.joinExpressions[j].addAll(this.tempJoinExpressions[j]);
      }
      else
      {
        int k = i + 1;
        for (int m = 0; m < this.tempJoinExpressions[j].size(); m++)
        {
          Expression localExpression = (Expression)this.tempJoinExpressions[j].get(m);
          assignToJoinLists(localExpression, this.joinExpressions, k);
        }
      }
    }
    for (j = 0; j < this.queryConditions.size(); j++) {
      assignToJoinLists((Expression)this.queryConditions.get(j), this.whereExpressions, this.lastRightJoinIndex);
    }
  }
  
  void assignToJoinLists(Expression paramExpression, HsqlList[] paramArrayOfHsqlList, int paramInt)
  {
    if (paramExpression == null) {
      return;
    }
    this.tempSet.clear();
    paramExpression.collectRangeVariables(this.rangeVariables, this.tempSet);
    int i = this.rangeVarSet.getLargestIndex(this.tempSet);
    if (i == -1) {
      i = 0;
    }
    if (i < paramInt) {
      i = paramInt;
    }
    if (((paramExpression instanceof ExpressionLogical)) && (((ExpressionLogical)paramExpression).isTerminal)) {
      i = paramArrayOfHsqlList.length - 1;
    }
    paramArrayOfHsqlList[i].add(paramExpression);
  }
  
  void assignToRangeVariables()
  {
    for (int i = 0; i < this.rangeVariables.length; i++)
    {
      int j = 0;
      RangeVariable.RangeVariableConditions localRangeVariableConditions;
      if ((i < this.firstLeftJoinIndex) && (this.firstRightJoinIndex == this.rangeVariables.length))
      {
        localRangeVariableConditions = this.rangeVariables[i].joinConditions[0];
        this.joinExpressions[i].addAll(this.whereExpressions[i]);
        assignToRangeVariable(this.rangeVariables[i], localRangeVariableConditions, i, this.joinExpressions[i]);
        assignToRangeVariable(localRangeVariableConditions, this.joinExpressions[i]);
      }
      else
      {
        localRangeVariableConditions = this.rangeVariables[i].joinConditions[0];
        assignToRangeVariable(this.rangeVariables[i], localRangeVariableConditions, i, this.joinExpressions[i]);
        localRangeVariableConditions = this.rangeVariables[i].joinConditions[0];
        if (localRangeVariableConditions.hasIndex()) {
          j = 1;
        }
        assignToRangeVariable(localRangeVariableConditions, this.joinExpressions[i]);
        localRangeVariableConditions = this.rangeVariables[i].whereConditions[0];
        for (int k = i + 1; k < this.rangeVariables.length; k++) {
          if (this.rangeVariables[k].isRightJoin) {
            assignToRangeVariable(this.rangeVariables[k].whereConditions[0], this.whereExpressions[i]);
          }
        }
        if (j == 0) {
          assignToRangeVariable(this.rangeVariables[i], localRangeVariableConditions, i, this.whereExpressions[i]);
        }
        assignToRangeVariable(localRangeVariableConditions, this.whereExpressions[i]);
      }
    }
  }
  
  void assignToRangeVariable(RangeVariable.RangeVariableConditions paramRangeVariableConditions, HsqlList paramHsqlList)
  {
    int i = 0;
    int j = paramHsqlList.size();
    while (i < j)
    {
      Expression localExpression = (Expression)paramHsqlList.get(i);
      paramRangeVariableConditions.addCondition(localExpression);
      i++;
    }
  }
  
  private void collectIndexableColumns(RangeVariable paramRangeVariable, HsqlList paramHsqlList)
  {
    this.colIndexSetEqual.clear();
    this.colIndexSetOther.clear();
    int i = 0;
    int j = paramHsqlList.size();
    while (i < j)
    {
      Expression localExpression = (Expression)paramHsqlList.get(i);
      if (localExpression.isSingleColumnCondition)
      {
        int k;
        if (localExpression.getLeftNode().getRangeVariable() == paramRangeVariable)
        {
          k = localExpression.getLeftNode().getColumnIndex();
        }
        else
        {
          if (localExpression.getRightNode().getRangeVariable() != paramRangeVariable) {
            break label147;
          }
          k = localExpression.getRightNode().getColumnIndex();
        }
        if (localExpression.isSingleColumnEqual)
        {
          this.colIndexSetEqual.add(k);
        }
        else
        {
          int m = this.colIndexSetOther.get(k, 0);
          this.colIndexSetOther.put(k, m + 1);
        }
      }
      label147:
      i++;
    }
  }
  
  void assignToRangeVariable(RangeVariable paramRangeVariable, RangeVariable.RangeVariableConditions paramRangeVariableConditions, int paramInt, HsqlList paramHsqlList)
  {
    if (paramHsqlList.isEmpty()) {
      return;
    }
    setIndexConditions(paramRangeVariableConditions, paramHsqlList, paramInt, true);
  }
  
  private void setIndexConditions(RangeVariable.RangeVariableConditions paramRangeVariableConditions, HsqlList paramHsqlList, int paramInt, boolean paramBoolean)
  {
    this.colIndexSetEqual.clear();
    this.colIndexSetOther.clear();
    int i = 0;
    int j = paramHsqlList.size();
    while (i < j)
    {
      Expression localExpression1 = (Expression)paramHsqlList.get(i);
      if ((localExpression1 != null) && (localExpression1.isIndexable(paramRangeVariableConditions.rangeVar)))
      {
        int m = localExpression1.getType();
        int n;
        int i2;
        switch (m)
        {
        case 50: 
          break;
        case 2: 
          break;
        case 41: 
          if ((localExpression1.exprSubType != 52) && (localExpression1.exprSubType != 51) && (localExpression1.getLeftNode().getRangeVariable() == paramRangeVariableConditions.rangeVar))
          {
            n = localExpression1.getLeftNode().getColumnIndex();
            this.colIndexSetEqual.add(n);
          }
          break;
        case 47: 
          if ((localExpression1.getLeftNode().getRangeVariable() == paramRangeVariableConditions.rangeVar) && (!paramRangeVariableConditions.rangeVar.isLeftJoin))
          {
            n = localExpression1.getLeftNode().getColumnIndex();
            this.colIndexSetEqual.add(n);
          }
          break;
        case 48: 
          if ((localExpression1.getLeftNode().getLeftNode().getRangeVariable() == paramRangeVariableConditions.rangeVar) && (!paramRangeVariableConditions.rangeVar.isLeftJoin))
          {
            n = localExpression1.getLeftNode().getLeftNode().getColumnIndex();
            i2 = this.colIndexSetOther.get(n, 0);
            this.colIndexSetOther.put(n, i2 + 1);
          }
          break;
        case 42: 
        case 43: 
        case 44: 
        case 45: 
          if (localExpression1.getLeftNode().getRangeVariable() == paramRangeVariableConditions.rangeVar)
          {
            n = localExpression1.getLeftNode().getColumnIndex();
            i2 = this.colIndexSetOther.get(n, 0);
            this.colIndexSetOther.put(n, i2 + 1);
          }
          break;
        default: 
          Error.runtimeError(201, "RangeVariableResolver");
        }
      }
      i++;
    }
    setEqualityConditions(paramRangeVariableConditions, paramHsqlList, paramInt);
    boolean bool = paramRangeVariableConditions.hasIndex();
    if (!bool) {
      setNonEqualityConditions(paramRangeVariableConditions, paramHsqlList, paramInt);
    }
    if ((paramInt == 0) && (this.sortAndSlice.usingIndex)) {
      bool = true;
    } else {
      bool = paramRangeVariableConditions.hasIndex();
    }
    i = 0;
    Expression localExpression2;
    if ((!bool) && (paramBoolean))
    {
      j = 0;
      k = paramHsqlList.size();
      while (j < k)
      {
        localExpression2 = (Expression)paramHsqlList.get(j);
        if (localExpression2 != null) {
          if (localExpression2.getType() == 50)
          {
            bool = ((ExpressionLogical)localExpression2).isIndexable(paramRangeVariableConditions.rangeVar);
            if (bool) {
              bool = setOrConditions(paramRangeVariableConditions, (ExpressionLogical)localExpression2, paramInt);
            }
            if (bool)
            {
              paramHsqlList.set(j, null);
              i = 1;
              break;
            }
          }
          else if ((localExpression2.getType() == 41) && (localExpression2.exprSubType == 52) && (paramInt < this.firstLeftJoinIndex) && (this.firstRightJoinIndex == this.rangeVariables.length) && (!localExpression2.getRightNode().isCorrelated()))
          {
            OrderedIntHashSet localOrderedIntHashSet = new OrderedIntHashSet();
            ((ExpressionLogical)localExpression2).addLeftColumnsForAllAny(paramRangeVariableConditions.rangeVar, localOrderedIntHashSet);
            Index.IndexUse[] arrayOfIndexUse = paramRangeVariableConditions.rangeVar.rangeTable.getIndexForColumns(this.session, localOrderedIntHashSet, 41, false);
            if ((arrayOfIndexUse.length != 0) && (this.inExpressions[paramInt] == null))
            {
              this.inExpressions[paramInt] = localExpression2;
              this.inInJoin[paramInt] = paramRangeVariableConditions.isJoin;
              this.inExpressionCount += 1;
              paramHsqlList.set(j, null);
              break;
            }
          }
        }
        j++;
      }
    }
    j = 0;
    int k = paramHsqlList.size();
    while (j < k)
    {
      localExpression2 = (Expression)paramHsqlList.get(j);
      if (localExpression2 != null) {
        if (i != 0) {
          for (int i1 = 0; i1 < paramRangeVariableConditions.rangeVar.joinConditions.length; i1++) {
            if (paramRangeVariableConditions.isJoin) {
              paramRangeVariableConditions.rangeVar.joinConditions[i1].nonIndexCondition = ExpressionLogical.andExpressions(localExpression2, paramRangeVariableConditions.rangeVar.joinConditions[i1].nonIndexCondition);
            } else {
              paramRangeVariableConditions.rangeVar.whereConditions[i1].nonIndexCondition = ExpressionLogical.andExpressions(localExpression2, paramRangeVariableConditions.rangeVar.whereConditions[i1].nonIndexCondition);
            }
          }
        } else {
          paramRangeVariableConditions.addCondition(localExpression2);
        }
      }
      j++;
    }
  }
  
  private boolean setOrConditions(RangeVariable.RangeVariableConditions paramRangeVariableConditions, ExpressionLogical paramExpressionLogical, int paramInt)
  {
    HsqlArrayList localHsqlArrayList1 = new HsqlArrayList();
    decomposeOrConditions(paramExpressionLogical, localHsqlArrayList1);
    RangeVariable.RangeVariableConditions[] arrayOfRangeVariableConditions = new RangeVariable.RangeVariableConditions[localHsqlArrayList1.size()];
    Object localObject1;
    Object localObject2;
    for (int i = 0; i < localHsqlArrayList1.size(); i++)
    {
      HsqlArrayList localHsqlArrayList2 = new HsqlArrayList();
      localObject1 = (Expression)localHsqlArrayList1.get(i);
      decomposeAndConditions(this.session, (Expression)localObject1, localHsqlArrayList2);
      localObject2 = new RangeVariable.RangeVariableConditions(paramRangeVariableConditions);
      setIndexConditions((RangeVariable.RangeVariableConditions)localObject2, localHsqlArrayList2, paramInt, false);
      arrayOfRangeVariableConditions[i] = localObject2;
      if (!((RangeVariable.RangeVariableConditions)localObject2).hasIndex()) {
        return false;
      }
    }
    Expression localExpression = null;
    for (int j = 0; j < arrayOfRangeVariableConditions.length; j++)
    {
      localObject1 = arrayOfRangeVariableConditions[j];
      arrayOfRangeVariableConditions[j].excludeConditions = localExpression;
      if (j == arrayOfRangeVariableConditions.length - 1) {
        break;
      }
      localObject2 = null;
      if (((RangeVariable.RangeVariableConditions)localObject1).indexCond != null) {
        for (int k = 0; k < ((RangeVariable.RangeVariableConditions)localObject1).indexedColumnCount; k++) {
          localObject2 = ExpressionLogical.andExpressions((Expression)localObject2, localObject1.indexCond[k]);
        }
      }
      localObject2 = ExpressionLogical.andExpressions((Expression)localObject2, ((RangeVariable.RangeVariableConditions)localObject1).indexEndCondition);
      localObject2 = ExpressionLogical.andExpressions((Expression)localObject2, ((RangeVariable.RangeVariableConditions)localObject1).nonIndexCondition);
      localExpression = ExpressionLogical.orExpressions((Expression)localObject2, localExpression);
    }
    if ((localExpression == null) || (paramRangeVariableConditions.isJoin))
    {
      paramRangeVariableConditions.rangeVar.joinConditions = arrayOfRangeVariableConditions;
      arrayOfRangeVariableConditions = new RangeVariable.RangeVariableConditions[localHsqlArrayList1.size()];
      ArrayUtil.fillArray(arrayOfRangeVariableConditions, paramRangeVariableConditions.rangeVar.whereConditions[0]);
      paramRangeVariableConditions.rangeVar.whereConditions = arrayOfRangeVariableConditions;
    }
    else
    {
      paramRangeVariableConditions.rangeVar.whereConditions = arrayOfRangeVariableConditions;
      arrayOfRangeVariableConditions = new RangeVariable.RangeVariableConditions[localHsqlArrayList1.size()];
      ArrayUtil.fillArray(arrayOfRangeVariableConditions, paramRangeVariableConditions.rangeVar.joinConditions[0]);
      paramRangeVariableConditions.rangeVar.joinConditions = arrayOfRangeVariableConditions;
    }
    return true;
  }
  
  private void setEqualityConditions(RangeVariable.RangeVariableConditions paramRangeVariableConditions, HsqlList paramHsqlList, int paramInt)
  {
    Index localIndex = null;
    if ((paramInt == 0) && (this.sortAndSlice.usingIndex))
    {
      localIndex = this.sortAndSlice.primaryTableIndex;
      if (localIndex != null) {
        paramRangeVariableConditions.rangeIndex = localIndex;
      }
    }
    Object localObject2;
    if (localIndex == null)
    {
      localObject1 = paramRangeVariableConditions.rangeVar.rangeTable.getIndexForColumns(this.session, this.colIndexSetEqual, 41, false);
      if (localObject1.length == 0) {
        return;
      }
      localIndex = localObject1[0].index;
      double d1 = 1.7976931348623157E+308D;
      if (localObject1.length > 1) {
        for (j = 0; j < localObject1.length; j++)
        {
          localObject2 = paramRangeVariableConditions.rangeVar.rangeTable.getRowStore(this.session);
          double d2 = ((PersistentStore)localObject2).searchCost(this.session, localObject1[j].index, localObject1[j].columnCount, 41);
          if (d2 < d1)
          {
            d1 = d2;
            localIndex = localObject1[j].index;
          }
        }
      }
    }
    Object localObject1 = localIndex.getColumns();
    int i = localObject1.length;
    Expression[] arrayOfExpression = new Expression[localObject1.length];
    for (int j = 0; j < paramHsqlList.size(); j++)
    {
      localObject2 = (Expression)paramHsqlList.get(j);
      if (localObject2 != null)
      {
        int m = ((Expression)localObject2).getType();
        if (((m == 41) || (m == 47)) && (((Expression)localObject2).getLeftNode().getRangeVariable() == paramRangeVariableConditions.rangeVar) && (((Expression)localObject2).isIndexable(paramRangeVariableConditions.rangeVar)))
        {
          int n = ArrayUtil.find((int[])localObject1, ((Expression)localObject2).getLeftNode().getColumnIndex());
          if ((n != -1) && (arrayOfExpression[n] == null))
          {
            arrayOfExpression[n] = localObject2;
            paramHsqlList.set(j, null);
          }
        }
      }
    }
    j = 0;
    for (int k = 0; k < arrayOfExpression.length; k++)
    {
      Object localObject3 = arrayOfExpression[k];
      if (localObject3 == null)
      {
        if (i == localObject1.length) {
          i = k;
        }
        j = 1;
      }
      else if (j != 0)
      {
        paramHsqlList.add(localObject3);
        arrayOfExpression[k] = null;
      }
    }
    if (i > 0) {
      paramRangeVariableConditions.addIndexCondition(arrayOfExpression, localIndex, i);
    }
  }
  
  private void setNonEqualityConditions(RangeVariable.RangeVariableConditions paramRangeVariableConditions, HsqlList paramHsqlList, int paramInt)
  {
    if (this.colIndexSetOther.isEmpty()) {
      return;
    }
    int i = 0;
    Object localObject1 = null;
    if ((paramInt == 0) && (this.sortAndSlice.usingIndex)) {
      localObject1 = this.sortAndSlice.primaryTableIndex;
    }
    Object localObject2;
    if (localObject1 == null)
    {
      Iterator localIterator = this.colIndexSetOther.keySet().iterator();
      while (localIterator.hasNext())
      {
        int j = localIterator.nextInt();
        k = this.colIndexSetOther.get(j, 0);
        if (k > i)
        {
          localObject2 = paramRangeVariableConditions.rangeVar.rangeTable.getIndexForColumn(this.session, j);
          if (localObject2 != null)
          {
            localObject1 = localObject2;
            i = k;
          }
        }
      }
    }
    if (localObject1 == null) {
      return;
    }
    int[] arrayOfInt = ((Index)localObject1).getColumns();
    for (int k = 0; k < paramHsqlList.size(); k++)
    {
      localObject2 = (Expression)paramHsqlList.get(k);
      if (localObject2 != null)
      {
        int m = 0;
        switch (((Expression)localObject2).getType())
        {
        case 48: 
          if ((((Expression)localObject2).getLeftNode().getType() == 47) && (arrayOfInt[0] == ((Expression)localObject2).getLeftNode().getLeftNode().getColumnIndex())) {
            m = 1;
          }
          break;
        case 42: 
        case 43: 
        case 44: 
        case 45: 
          if ((arrayOfInt[0] == ((Expression)localObject2).getLeftNode().getColumnIndex()) && (((Expression)localObject2).getRightNode() != null) && (!((Expression)localObject2).getRightNode().isCorrelated())) {
            m = 1;
          }
          break;
        }
        if (m != 0)
        {
          Expression[] arrayOfExpression = new Expression[((Index)localObject1).getColumnCount()];
          arrayOfExpression[0] = localObject2;
          paramRangeVariableConditions.addIndexCondition(arrayOfExpression, (Index)localObject1, 1);
          paramHsqlList.set(k, null);
          break;
        }
      }
    }
  }
  
  void setInConditionsAsTables()
  {
    for (int i = this.rangeVariables.length - 1; i >= 0; i--)
    {
      RangeVariable localRangeVariable1 = this.rangeVariables[i];
      ExpressionLogical localExpressionLogical1 = (ExpressionLogical)this.inExpressions[i];
      if (localExpressionLogical1 != null)
      {
        OrderedIntHashSet localOrderedIntHashSet = new OrderedIntHashSet();
        localExpressionLogical1.addLeftColumnsForAllAny(localRangeVariable1, localOrderedIntHashSet);
        Index.IndexUse[] arrayOfIndexUse = localRangeVariable1.rangeTable.getIndexForColumns(this.session, localOrderedIntHashSet, 41, false);
        Index localIndex = arrayOfIndexUse[0].index;
        int j = 0;
        for (int k = 0; (k < localIndex.getColumnCount()) && (localOrderedIntHashSet.contains(localIndex.getColumns()[k])); k++) {
          j++;
        }
        RangeVariable localRangeVariable2 = new RangeVariable(localExpressionLogical1.getRightNode().getTable(), null, null, null, this.compileContext);
        localRangeVariable2.isGenerated = true;
        RangeVariable[] arrayOfRangeVariable = new RangeVariable[this.rangeVariables.length + 1];
        ArrayUtil.copyAdjustArray(this.rangeVariables, arrayOfRangeVariable, localRangeVariable2, i, 1);
        this.rangeVariables = arrayOfRangeVariable;
        Expression[] arrayOfExpression = new Expression[localIndex.getColumnCount()];
        for (int m = 0; m < j; m++)
        {
          int n = localIndex.getColumns()[m];
          i1 = localOrderedIntHashSet.getIndex(n);
          ExpressionLogical localExpressionLogical2 = new ExpressionLogical(localRangeVariable1, n, localRangeVariable2, i1);
          arrayOfExpression[m] = localExpressionLogical2;
        }
        m = (this.rangeVariables[i].isLeftJoin) || (this.rangeVariables[i].isRightJoin) ? 1 : 0;
        RangeVariable.RangeVariableConditions localRangeVariableConditions = (this.inInJoin[i] == 0) && (m != 0) ? localRangeVariable1.whereConditions[0] : localRangeVariable1.joinConditions[0];
        localRangeVariableConditions.addIndexCondition(arrayOfExpression, localIndex, j);
        for (int i1 = 0; i1 < localOrderedIntHashSet.size(); i1++)
        {
          int i2 = localOrderedIntHashSet.get(i1);
          int i3 = i1;
          ExpressionLogical localExpressionLogical3 = new ExpressionLogical(localRangeVariable1, i2, localRangeVariable2, i3);
          localRangeVariableConditions.addCondition(localExpressionLogical3);
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.RangeVariableResolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */