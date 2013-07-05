package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.types.Type;

public class ParserDML extends ParserDQL
{
  ParserDML(Session paramSession, Scanner paramScanner)
  {
    super(paramSession, paramScanner);
  }

  StatementDMQL compileInsertStatement(RangeGroup[] paramArrayOfRangeGroup)
  {
    read();
    readThis(141);
    int j = 0;
    int k = 0;
    int m = 0;
    RangeVariable localRangeVariable = readRangeVariableForDataChange(50);
    localRangeVariable.resolveRangeTableTypes(this.session, RangeVariable.emptyArray);
    Table localTable1 = localRangeVariable.getTable();
    boolean[] arrayOfBoolean = null;
    Object localObject1 = localTable1.getColumnMap();
    int i = localTable1.getColumnCount();
    int n = getPosition();
    Table localTable2 = localTable1.isTriggerInsertable() ? localTable1 : localTable1.getBaseTable();
    Object localObject2;
    Object localObject3;
    switch (this.token.tokenType)
    {
    case 78:
      read();
      readThis(308);
      Expression localExpression = new Expression(25, new Expression[0]);
      localExpression = new Expression(26, new Expression[] { localExpression });
      arrayOfBoolean = localTable1.getNewColumnCheckList();
      for (int i3 = 0; i3 < localTable1.colDefaults.length; i3++)
        if ((localTable1.colDefaults[i3] == null) && (localTable1.identityColumn != i3) && (!localTable1.getColumn(i3).isGenerated()))
          throw Error.error(5544);
      StatementInsert localStatementInsert = new StatementInsert(this.session, localTable1, (int[])localObject1, localExpression, arrayOfBoolean, this.compileContext);
      return localStatementInsert;
    case 786:
      int i1 = readOpenBrackets();
      if (i1 == 1)
      {
        int i4 = 0;
        switch (this.token.tokenType)
        {
        case 251:
        case 278:
        case 319:
          rewind(n);
          i4 = 1;
          break;
        }
        if (i4 != 0)
          break label908;
        localObject4 = new OrderedHashSet();
        boolean bool = this.database.sqlSyntaxOra;
        readSimpleColumnNames((OrderedHashSet)localObject4, localRangeVariable, bool);
        readThis(772);
        i = ((OrderedHashSet)localObject4).size();
        localObject1 = localTable1.getColumnIndexes((OrderedHashSet)localObject4);
        if ((this.token.tokenType != 308) && (this.token.tokenType != 461))
          break label908;
      }
      else
      {
        rewind(n);
      }
      break;
    case 461:
      if (this.token.tokenType == 461)
      {
        read();
        if (this.token.tokenType == 305)
        {
          read();
          j = 1;
        }
        else if (this.token.tokenType == 276)
        {
          read();
          k = 1;
        }
        else
        {
          unexpectedToken();
        }
        readThis(307);
        if (this.token.tokenType != 308)
          break label908;
      }
    case 308:
      read();
      arrayOfBoolean = localTable1.getColumnCheckList((int[])localObject1);
      localObject2 = XreadContextuallyTypedTable(i);
      localObject3 = ((Expression)localObject2).resolveColumnReferences(this.session, RangeGroup.emptyGroup, paramArrayOfRangeGroup, null);
      ExpressionColumn.checkColumnsResolved((HsqlList)localObject3);
      ((Expression)localObject2).resolveTypes(this.session, null);
      setParameterTypes((Expression)localObject2, localTable1, (int[])localObject1);
      if (localTable1 != localTable2)
      {
        localObject4 = localTable1.getBaseTableColumnMap();
        int[] arrayOfInt = new int[localObject1.length];
        ArrayUtil.projectRow((int[])localObject4, (int[])localObject1, arrayOfInt);
        localObject1 = arrayOfInt;
      }
      localObject4 = ((Expression)localObject2).nodes;
      for (int i6 = 0; i6 < localObject4.length; i6++)
      {
        localObject6 = localObject4[i6].nodes;
        for (int i7 = 0; i7 < localObject6.length; i7++)
        {
          Object localObject7 = localObject6[i7];
          ColumnSchema localColumnSchema = localTable2.getColumn(localObject1[i7]);
          if (localColumnSchema.isIdentity())
          {
            m = 1;
            if (localObject7.getType() != 4)
            {
              if ((localTable2.identitySequence.isAlways()) && (j == 0) && (k == 0))
                throw Error.error(5543);
              if (j != 0)
                localObject6[i7] = new ExpressionColumn(4);
            }
          }
          else if (!localColumnSchema.hasDefault())
          {
            if (localColumnSchema.isGenerated())
            {
              if (localObject7.getType() != 4)
                throw Error.error(5541);
            }
            else if (localObject7.getType() == 4)
              throw Error.error(5544);
          }
          if (localObject7.isUnresolvedParam())
            localObject7.setAttributesAsColumn(localColumnSchema, true);
        }
      }
      if ((m == 0) && ((j != 0) || (k != 0)))
        unexpectedTokenRequire("OVERRIDING");
      localObject5 = new StatementInsert(this.session, localTable1, (int[])localObject1, (Expression)localObject2, arrayOfBoolean, this.compileContext);
      return localObject5;
    case 251:
    case 278:
    case 319:
      break;
    }
    throw unexpectedToken();
    label908: arrayOfBoolean = localTable1.getColumnCheckList((int[])localObject1);
    if ((localTable2 != null) && (localTable1 != localTable2))
    {
      localObject2 = localTable1.getBaseTableColumnMap();
      localObject3 = new int[localObject1.length];
      ArrayUtil.projectRow((int[])localObject2, (int[])localObject1, (int[])localObject3);
      localObject1 = localObject3;
    }
    int i2 = localTable2.getIdentityColumnIndex();
    int i5 = -1;
    if ((i2 != -1) && (ArrayUtil.find((int[])localObject1, i2) > -1))
    {
      if ((localTable2.identitySequence.isAlways()) && (j == 0) && (k == 0))
        throw Error.error(5543);
      if (j != 0)
        i5 = i2;
    }
    else if ((j != 0) || (k != 0))
    {
      unexpectedTokenRequire("OVERRIDING");
    }
    Object localObject4 = new Type[localObject1.length];
    ArrayUtil.projectRow(localTable2.getColumnTypes(), (int[])localObject1, (Object[])localObject4);
    this.compileContext.setOuterRanges(paramArrayOfRangeGroup);
    Object localObject5 = XreadQueryExpression();
    ((QueryExpression)localObject5).setReturningResult();
    ((QueryExpression)localObject5).resolve(this.session, paramArrayOfRangeGroup, (Type[])localObject4);
    if (i != ((QueryExpression)localObject5).getColumnCount())
      throw Error.error(5546);
    Object localObject6 = new StatementInsert(this.session, localTable1, (int[])localObject1, arrayOfBoolean, (QueryExpression)localObject5, this.compileContext, i5);
    return localObject6;
  }

  private static void setParameterTypes(Expression paramExpression, Table paramTable, int[] paramArrayOfInt)
  {
    for (int i = 0; i < paramExpression.nodes.length; i++)
    {
      Expression[] arrayOfExpression = paramExpression.nodes[i].nodes;
      for (int j = 0; j < arrayOfExpression.length; j++)
        if (arrayOfExpression[j].isUnresolvedParam())
          arrayOfExpression[j].setAttributesAsColumn(paramTable.getColumn(paramArrayOfInt[j]), true);
    }
  }

  Statement compileTruncateStatement()
  {
    int i = 0;
    int j = 0;
    boolean bool1 = false;
    boolean bool2 = false;
    HsqlNameManager.HsqlName localHsqlName = null;
    RangeVariable[] arrayOfRangeVariable = null;
    Table localTable = null;
    HsqlNameManager.HsqlName[] arrayOfHsqlName = null;
    readThis(295);
    if (this.token.tokenType == 278)
    {
      readThis(278);
      arrayOfRangeVariable = new RangeVariable[] { readRangeVariableForDataChange(1205) };
      localTable = arrayOfRangeVariable[0].getTable();
      localHsqlName = localTable.getName();
      i = 1;
    }
    else
    {
      readThis(497);
      localHsqlName = readSchemaName();
    }
    switch (this.token.tokenType)
    {
    case 376:
      read();
      readThis(128);
      break;
    case 484:
      read();
      readThis(128);
      bool2 = true;
    }
    if (i == 0)
      checkIsThis(5);
    if (readIfThis(5))
    {
      readThis(44);
      j = 1;
      if (readIfThis(180))
      {
        readThis(37);
        bool1 = true;
      }
    }
    if (i != 0)
      arrayOfHsqlName = new HsqlNameManager.HsqlName[] { localTable.getName() };
    else
      arrayOfHsqlName = this.session.database.schemaManager.getCatalogAndBaseTableNames();
    if (j != 0)
    {
      localObject = new Object[] { localHsqlName, Boolean.valueOf(bool2), Boolean.valueOf(bool1) };
      return new StatementCommand(1205, (Object[])localObject, null, arrayOfHsqlName);
    }
    Object localObject = new StatementDML(this.session, localTable, arrayOfRangeVariable, this.compileContext, bool2, 1205);
    return localObject;
  }

  Statement compileDeleteStatement(RangeGroup[] paramArrayOfRangeGroup)
  {
    Expression localExpression1 = null;
    boolean bool = false;
    readThis(79);
    readThis(115);
    Object localObject1 = null;
    Table localTable = null;
    localObject1 = new RangeVariable[] { readRangeVariableForDataChange(19) };
    localTable = localObject1[0].getTable();
    this.compileContext.setOuterRanges(paramArrayOfRangeGroup);
    Object localObject3;
    if (this.token.tokenType == 316)
    {
      read();
      localExpression1 = XreadBooleanValueExpression();
      localObject2 = new RangeGroup.RangeGroupSimple((RangeVariable[])localObject1);
      localObject3 = localExpression1.resolveColumnReferences(this.session, (RangeGroup)localObject2, paramArrayOfRangeGroup, null);
      ExpressionColumn.checkColumnsResolved((HsqlList)localObject3);
      localExpression1.resolveTypes(this.session, null);
      if (localExpression1.isUnresolvedParam())
        localExpression1.dataType = Type.SQL_BOOLEAN;
      if (localExpression1.getDataType() != Type.SQL_BOOLEAN)
        throw Error.error(5568);
    }
    Object localObject2 = localTable.isTriggerDeletable() ? localTable : localTable.getBaseTable();
    if (localTable != localObject2)
    {
      localObject3 = ((TableDerived)localTable).getQueryExpression().getMainSelect();
      RangeVariable[] arrayOfRangeVariable = (RangeVariable[])ArrayUtil.duplicateArray(((QuerySpecification)localObject3).rangeVariables);
      arrayOfRangeVariable[0] = localObject3.rangeVariables[0].duplicate();
      Expression[] arrayOfExpression = new Expression[((QuerySpecification)localObject3).indexLimitData];
      for (int j = 0; j < ((QuerySpecification)localObject3).indexLimitData; j++)
      {
        Expression localExpression3 = localObject3.exprColumns[j].duplicate();
        arrayOfExpression[j] = localExpression3;
        localExpression3.replaceRangeVariables(((QuerySpecification)localObject3).rangeVariables, arrayOfRangeVariable);
      }
      Expression localExpression2 = ((QuerySpecification)localObject3).queryCondition;
      if (localExpression2 != null)
      {
        localExpression2 = localExpression2.duplicate();
        localExpression2.replaceRangeVariables((RangeVariable[])localObject1, arrayOfRangeVariable);
      }
      if (localExpression1 != null)
        localExpression1 = localExpression1.replaceColumnReferences(localObject1[0], arrayOfExpression);
      localObject1 = arrayOfRangeVariable;
      localExpression1 = ExpressionLogical.andExpressions(localExpression2, localExpression1);
    }
    if (localExpression1 != null)
    {
      localObject1[0].addJoinCondition(localExpression1);
      localObject3 = new RangeVariableResolver((RangeVariable[])localObject1, null, this.compileContext);
      ((RangeVariableResolver)localObject3).processConditions(this.session);
      localObject1 = ((RangeVariableResolver)localObject3).rangeVariables;
    }
    for (int i = 0; i < localObject1.length; i++)
      localObject1[i].resolveRangeTableTypes(this.session, RangeVariable.emptyArray);
    StatementDML localStatementDML = new StatementDML(this.session, localTable, (RangeVariable[])localObject1, this.compileContext, bool, 19);
    return localStatementDML;
  }

  StatementDMQL compileUpdateStatement(RangeGroup[] paramArrayOfRangeGroup)
  {
    read();
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    LongDeque localLongDeque = new LongDeque();
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Object localObject2 = { readRangeVariableForDataChange(82) };
    RangeGroup.RangeGroupSimple localRangeGroupSimple = new RangeGroup.RangeGroupSimple((RangeVariable[])localObject2);
    Table localTable1 = localObject2[0].rangeTable;
    Table localTable2 = localTable1.isTriggerUpdatable() ? localTable1 : localTable1.getBaseTable();
    readThis(254);
    readSetClauseList((RangeVariable[])localObject2, localOrderedHashSet, localLongDeque, localHsqlArrayList);
    Object localObject1 = new int[localLongDeque.size()];
    localLongDeque.toArray((int[])localObject1);
    Expression[] arrayOfExpression2 = new Expression[localOrderedHashSet.size()];
    localOrderedHashSet.toArray(arrayOfExpression2);
    for (int i = 0; i < arrayOfExpression2.length; i++)
      resolveOuterReferencesAndTypes(paramArrayOfRangeGroup, arrayOfExpression2[i]);
    boolean[] arrayOfBoolean = localTable1.getColumnCheckList((int[])localObject1);
    Expression[] arrayOfExpression1 = new Expression[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfExpression1);
    Expression localExpression1 = null;
    Object localObject3;
    if (this.token.tokenType == 316)
    {
      read();
      localExpression1 = XreadBooleanValueExpression();
      localObject3 = localExpression1.resolveColumnReferences(this.session, localRangeGroupSimple, paramArrayOfRangeGroup, null);
      ExpressionColumn.checkColumnsResolved((HsqlList)localObject3);
      localExpression1.resolveTypes(this.session, null);
      if (localExpression1.isUnresolvedParam())
        localExpression1.dataType = Type.SQL_BOOLEAN;
      if (localExpression1.getDataType() != Type.SQL_BOOLEAN)
        throw Error.error(5568);
    }
    resolveUpdateExpressions(localTable1, localRangeGroupSimple, (int[])localObject1, arrayOfExpression1, paramArrayOfRangeGroup);
    Object localObject5;
    if (localTable1 != localTable2)
    {
      localObject3 = ((TableDerived)localTable1).getQueryExpression().getMainSelect();
      localObject5 = (RangeVariable[])ArrayUtil.duplicateArray(((QuerySpecification)localObject3).rangeVariables);
      localObject5[0] = localObject3.rangeVariables[0].duplicate();
      Expression[] arrayOfExpression3 = new Expression[((QuerySpecification)localObject3).indexLimitData];
      for (int m = 0; m < ((QuerySpecification)localObject3).indexLimitData; m++)
      {
        Expression localExpression3 = localObject3.exprColumns[m].duplicate();
        arrayOfExpression3[m] = localExpression3;
        localExpression3.replaceRangeVariables(((QuerySpecification)localObject3).rangeVariables, (RangeVariable[])localObject5);
      }
      Expression localExpression2 = ((QuerySpecification)localObject3).queryCondition;
      if (localExpression2 != null)
      {
        localExpression2 = localExpression2.duplicate();
        localExpression2.replaceRangeVariables((RangeVariable[])localObject2, (RangeVariable[])localObject5);
      }
      if (localExpression1 != null)
        localExpression1 = localExpression1.replaceColumnReferences(localObject2[0], arrayOfExpression3);
      for (int n = 0; n < arrayOfExpression1.length; n++)
        arrayOfExpression1[n] = arrayOfExpression1[n].replaceColumnReferences(localObject2[0], arrayOfExpression3);
      localObject2 = localObject5;
      localExpression1 = ExpressionLogical.andExpressions(localExpression2, localExpression1);
    }
    if (localExpression1 != null)
    {
      localObject2[0].addJoinCondition(localExpression1);
      localObject3 = new RangeVariableResolver((RangeVariable[])localObject2, null, this.compileContext);
      ((RangeVariableResolver)localObject3).processConditions(this.session);
      localObject2 = ((RangeVariableResolver)localObject3).rangeVariables;
    }
    for (int j = 0; j < localObject2.length; j++)
      localObject2[j].resolveRangeTableTypes(this.session, RangeVariable.emptyArray);
    if (localTable1 != localTable2)
    {
      localObject4 = localTable1.getBaseTableColumnMap();
      localObject5 = new int[localObject1.length];
      ArrayUtil.projectRow((int[])localObject4, (int[])localObject1, (int[])localObject5);
      localObject1 = localObject5;
      for (int k = 0; k < localObject1.length; k++)
        if (localTable2.colGenerated[localObject1[k]] != 0)
          throw Error.error(5513);
    }
    Object localObject4 = new StatementDML(this.session, arrayOfExpression2, localTable1, (RangeVariable[])localObject2, (int[])localObject1, arrayOfExpression1, arrayOfBoolean, this.compileContext);
    return localObject4;
  }

  void resolveUpdateExpressions(Table paramTable, RangeGroup paramRangeGroup, int[] paramArrayOfInt, Expression[] paramArrayOfExpression, RangeGroup[] paramArrayOfRangeGroup)
  {
    HsqlList localHsqlList = null;
    int i = -1;
    if ((paramTable.hasIdentityColumn()) && (paramTable.identitySequence.isAlways()))
      i = paramTable.getIdentityColumnIndex();
    int j = 0;
    for (int k = 0; j < paramArrayOfInt.length; k++)
    {
      Expression localExpression1 = paramArrayOfExpression[k];
      if (paramTable.colGenerated[paramArrayOfInt[j]] != 0)
        throw Error.error(5513);
      int n;
      Expression localExpression2;
      if (localExpression1.getType() == 25)
      {
        Expression[] arrayOfExpression = localExpression1.nodes;
        n = 0;
        while (n < arrayOfExpression.length)
        {
          localExpression2 = arrayOfExpression[n];
          if ((i == paramArrayOfInt[j]) && (localExpression2.getType() != 4))
            throw Error.error(5541);
          if (localExpression2.isUnresolvedParam())
          {
            localExpression2.setAttributesAsColumn(paramTable.getColumn(paramArrayOfInt[j]), true);
          }
          else if (localExpression2.getType() == 4)
          {
            if ((paramTable.colDefaults[paramArrayOfInt[j]] == null) && (paramTable.identityColumn != paramArrayOfInt[j]))
              throw Error.error(5544);
          }
          else
          {
            localHsqlList = localExpression1.resolveColumnReferences(this.session, paramRangeGroup, paramArrayOfRangeGroup, null);
            ExpressionColumn.checkColumnsResolved(localHsqlList);
            localHsqlList = null;
            localExpression2.resolveTypes(this.session, null);
          }
          n++;
          j++;
        }
      }
      else if (localExpression1.getType() == 22)
      {
        localHsqlList = localExpression1.resolveColumnReferences(this.session, paramRangeGroup, paramArrayOfRangeGroup, null);
        ExpressionColumn.checkColumnsResolved(localHsqlList);
        localExpression1.resolveTypes(this.session, null);
        int m = localExpression1.table.queryExpression.getColumnCount();
        n = 0;
        while (n < m)
        {
          if (i == paramArrayOfInt[j])
            throw Error.error(5541);
          n++;
          j++;
        }
      }
      else
      {
        localExpression2 = localExpression1;
        if ((i == paramArrayOfInt[j]) && (localExpression2.getType() != 4))
          throw Error.error(5541);
        if (localExpression2.isUnresolvedParam())
        {
          localExpression2.setAttributesAsColumn(paramTable.getColumn(paramArrayOfInt[j]), true);
        }
        else if (localExpression2.getType() == 4)
        {
          if ((paramTable.colDefaults[paramArrayOfInt[j]] == null) && (paramTable.identityColumn != paramArrayOfInt[j]))
            throw Error.error(5544);
        }
        else
        {
          localHsqlList = localExpression1.resolveColumnReferences(this.session, paramRangeGroup, paramArrayOfRangeGroup, null);
          ExpressionColumn.checkColumnsResolved(localHsqlList);
          localExpression2.resolveTypes(this.session, null);
        }
        j++;
      }
    }
  }

  void readSetClauseList(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet, LongDeque paramLongDeque, HsqlArrayList paramHsqlArrayList)
  {
    while (true)
    {
      int i;
      if (this.token.tokenType == 786)
      {
        read();
        int j = paramOrderedHashSet.size();
        readTargetSpecificationList(paramOrderedHashSet, paramArrayOfRangeVariable, paramLongDeque);
        i = paramOrderedHashSet.size() - j;
        readThis(772);
      }
      else
      {
        Expression localExpression1 = XreadTargetSpecification(paramArrayOfRangeVariable, paramLongDeque);
        if (!paramOrderedHashSet.add(localExpression1))
        {
          ColumnSchema localColumnSchema = localExpression1.getColumn();
          throw Error.error(5579, localColumnSchema.getName().name);
        }
        i = 1;
      }
      readThis(396);
      int k = getPosition();
      int m = readOpenBrackets();
      Object localObject;
      if (this.token.tokenType == 251)
      {
        rewind(k);
        localObject = XreadSubqueryTableBody(22);
        if (i != ((TableDerived)localObject).queryExpression.getColumnCount())
          throw Error.error(5546);
        Expression localExpression2 = new Expression(22, (TableDerived)localObject);
        paramHsqlArrayList.add(localExpression2);
        if (this.token.tokenType != 774)
          break;
        read();
      }
      else
      {
        if (m > 0)
          rewind(k);
        if (i > 1)
        {
          readThis(786);
          localObject = readRow();
          readThis(772);
          int n = ((Expression)localObject).getType() == 25 ? ((Expression)localObject).nodes.length : 1;
          if (i != n)
            throw Error.error(5546);
          paramHsqlArrayList.add(localObject);
        }
        else
        {
          localObject = XreadValueExpressionWithContext();
          paramHsqlArrayList.add(localObject);
        }
        if (this.token.tokenType != 774)
          break;
        read();
      }
    }
  }

  void readGetClauseList(RangeVariable[] paramArrayOfRangeVariable, OrderedHashSet paramOrderedHashSet, LongDeque paramLongDeque, HsqlArrayList paramHsqlArrayList)
  {
    while (true)
    {
      Expression localExpression = XreadTargetSpecification(paramArrayOfRangeVariable, paramLongDeque);
      if (!paramOrderedHashSet.add(localExpression))
      {
        ColumnSchema localColumnSchema = localExpression.getColumn();
        throw Error.error(5579, localColumnSchema.getName().name);
      }
      readThis(396);
      switch (this.token.tokenType)
      {
      case 443:
      case 495:
        int i = ExpressionColumn.diagnosticsList.getIndex(this.token.tokenString);
        ExpressionColumn localExpressionColumn = new ExpressionColumn(10, i);
        paramHsqlArrayList.add(localExpressionColumn);
        read();
      }
      if (this.token.tokenType != 774)
        break;
      read();
    }
  }

  StatementDMQL compileMergeStatement(RangeGroup[] paramArrayOfRangeGroup)
  {
    int[] arrayOfInt1 = null;
    int[] arrayOfInt2 = null;
    Expression[] arrayOfExpression1 = null;
    HsqlArrayList localHsqlArrayList1 = new HsqlArrayList();
    Expression[] arrayOfExpression2 = Expression.emptyArray;
    HsqlArrayList localHsqlArrayList2 = new HsqlArrayList();
    Expression localExpression2 = null;
    read();
    readThis(141);
    RangeVariable localRangeVariable1 = readRangeVariableForDataChange(128);
    Table localTable = localRangeVariable1.rangeTable;
    readThis(306);
    this.compileContext.setOuterRanges(paramArrayOfRangeGroup);
    RangeVariable localRangeVariable2 = readTableOrSubquery();
    RangeVariable[] arrayOfRangeVariable1 = { localRangeVariable1 };
    localRangeVariable2.resolveRangeTable(this.session, new RangeGroup.RangeGroupSimple(arrayOfRangeVariable1), paramArrayOfRangeGroup);
    localRangeVariable2.resolveRangeTableTypes(this.session, arrayOfRangeVariable1);
    this.compileContext.setOuterRanges(RangeGroup.emptyArray);
    readThis(194);
    Expression localExpression1 = XreadBooleanValueExpression();
    RangeVariable[] arrayOfRangeVariable2 = { localRangeVariable2, localRangeVariable1 };
    RangeVariable[] arrayOfRangeVariable3 = { localRangeVariable2 };
    RangeVariable[] arrayOfRangeVariable4 = { localRangeVariable1 };
    RangeGroup.RangeGroupSimple localRangeGroupSimple1 = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable2);
    RangeGroup.RangeGroupSimple localRangeGroupSimple2 = new RangeGroup.RangeGroupSimple(arrayOfRangeVariable3);
    arrayOfInt1 = localTable.getColumnMap();
    boolean[] arrayOfBoolean = localTable.getNewColumnCheckList();
    OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
    OrderedHashSet localOrderedHashSet2 = new OrderedHashSet();
    LongDeque localLongDeque = new LongDeque();
    readMergeWhen(localLongDeque, localOrderedHashSet2, localOrderedHashSet1, localHsqlArrayList2, localHsqlArrayList1, arrayOfRangeVariable4, localRangeVariable2);
    int i;
    if (localHsqlArrayList2.size() > 0)
    {
      i = localOrderedHashSet2.size();
      if (i != 0)
      {
        arrayOfInt1 = localTable.getColumnIndexes(localOrderedHashSet2);
        arrayOfBoolean = localTable.getColumnCheckList(arrayOfInt1);
      }
      localExpression2 = (Expression)localHsqlArrayList2.get(0);
      setParameterTypes(localExpression2, localTable, arrayOfInt1);
    }
    if (localHsqlArrayList1.size() > 0)
    {
      arrayOfExpression1 = new Expression[localOrderedHashSet1.size()];
      localOrderedHashSet1.toArray(arrayOfExpression1);
      for (i = 0; i < arrayOfExpression1.length; i++)
        resolveOuterReferencesAndTypes(paramArrayOfRangeGroup, arrayOfExpression1[i]);
      arrayOfExpression2 = new Expression[localHsqlArrayList1.size()];
      localHsqlArrayList1.toArray(arrayOfExpression2);
      arrayOfInt2 = new int[localLongDeque.size()];
      localLongDeque.toArray(arrayOfInt2);
    }
    if (arrayOfExpression2.length != 0)
    {
      localObject = localTable.isTriggerUpdatable() ? localTable : localTable.getBaseTable();
      int[] arrayOfInt3 = arrayOfInt2;
      if (localTable != localObject)
      {
        arrayOfInt3 = new int[arrayOfInt2.length];
        ArrayUtil.projectRow(localTable.getBaseTableColumnMap(), arrayOfInt2, arrayOfInt3);
      }
      resolveUpdateExpressions(localTable, localRangeGroupSimple1, arrayOfInt2, arrayOfExpression2, paramArrayOfRangeGroup);
    }
    Object localObject = null;
    localObject = localExpression1.resolveColumnReferences(this.session, localRangeGroupSimple1, paramArrayOfRangeGroup, null);
    ExpressionColumn.checkColumnsResolved((HsqlList)localObject);
    localExpression1.resolveTypes(this.session, null);
    if (localExpression1.isUnresolvedParam())
      localExpression1.dataType = Type.SQL_BOOLEAN;
    if (localExpression1.getDataType() != Type.SQL_BOOLEAN)
      throw Error.error(5568);
    arrayOfRangeVariable2[1].addJoinCondition(localExpression1);
    RangeVariableResolver localRangeVariableResolver = new RangeVariableResolver(arrayOfRangeVariable2, null, this.compileContext);
    localRangeVariableResolver.processConditions(this.session);
    arrayOfRangeVariable2 = localRangeVariableResolver.rangeVariables;
    for (int j = 0; j < arrayOfRangeVariable2.length; j++)
      arrayOfRangeVariable2[j].resolveRangeTableTypes(this.session, RangeVariable.emptyArray);
    if (localExpression2 != null)
    {
      localObject = localExpression2.resolveColumnReferences(this.session, localRangeGroupSimple2, RangeGroup.emptyArray, null);
      localObject = Expression.resolveColumnSet(this.session, RangeVariable.emptyArray, paramArrayOfRangeGroup, (HsqlList)localObject);
      ExpressionColumn.checkColumnsResolved((HsqlList)localObject);
      localExpression2.resolveTypes(this.session, null);
    }
    StatementDML localStatementDML = new StatementDML(this.session, arrayOfExpression1, arrayOfRangeVariable2, arrayOfInt1, arrayOfInt2, arrayOfBoolean, localExpression1, localExpression2, arrayOfExpression2, this.compileContext);
    return localStatementDML;
  }

  private void readMergeWhen(LongDeque paramLongDeque, OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2, HsqlArrayList paramHsqlArrayList1, HsqlArrayList paramHsqlArrayList2, RangeVariable[] paramArrayOfRangeVariable, RangeVariable paramRangeVariable)
  {
    Table localTable = paramArrayOfRangeVariable[0].rangeTable;
    int i = localTable.getColumnCount();
    readThis(314);
    if (this.token.tokenType == 437)
    {
      if (paramHsqlArrayList2.size() != 0)
        throw Error.error(5547);
      read();
      readThis(280);
      readThis(303);
      readThis(254);
      readSetClauseList(paramArrayOfRangeVariable, paramOrderedHashSet2, paramLongDeque, paramHsqlArrayList2);
    }
    else if (this.token.tokenType == 183)
    {
      if (paramHsqlArrayList1.size() != 0)
        throw Error.error(5548);
      read();
      readThis(437);
      readThis(280);
      readThis(135);
      int j = readOpenBrackets();
      if (j == 1)
      {
        boolean bool = this.database.sqlSyntaxOra;
        readSimpleColumnNames(paramOrderedHashSet1, paramArrayOfRangeVariable[0], bool);
        i = paramOrderedHashSet1.size();
        readThis(772);
        j = 0;
      }
      readThis(308);
      Expression localExpression = XreadContextuallyTypedTable(i);
      if (localExpression.nodes.length != 1)
        throw Error.error(3201);
      paramHsqlArrayList1.add(localExpression);
    }
    else
    {
      throw unexpectedToken();
    }
    if (this.token.tokenType == 314)
      readMergeWhen(paramLongDeque, paramOrderedHashSet1, paramOrderedHashSet2, paramHsqlArrayList1, paramHsqlArrayList2, paramArrayOfRangeVariable, paramRangeVariable);
  }

  StatementDMQL compileCallStatement(RangeGroup[] paramArrayOfRangeGroup, boolean paramBoolean)
  {
    read();
    if (isIdentifier())
    {
      checkValidCatalogName(this.token.namePrePrefix);
      localObject1 = (RoutineSchema)this.database.schemaManager.findSchemaObject(this.token.tokenString, this.session.getSchemaName(this.token.namePrefix), 17);
      if (localObject1 != null)
      {
        read();
        localObject2 = new HsqlArrayList();
        readThis(786);
        if (this.token.tokenType == 772)
          read();
        else
          while (true)
          {
            localObject3 = XreadValueExpression();
            ((HsqlArrayList)localObject2).add(localObject3);
            if (this.token.tokenType == 774)
            {
              read();
            }
            else
            {
              readThis(772);
              break;
            }
          }
        localObject3 = new Expression[((HsqlArrayList)localObject2).size()];
        ((HsqlArrayList)localObject2).toArray(localObject3);
        Routine localRoutine = ((RoutineSchema)localObject1).getSpecificRoutine(localObject3.length);
        this.compileContext.addProcedureCall(localRoutine);
        HsqlList localHsqlList = null;
        for (int i = 0; i < localObject3.length; i++)
        {
          Object localObject4 = localObject3[i];
          if (localObject4.isUnresolvedParam())
          {
            localObject4.setAttributesAsColumn(localRoutine.getParameter(i), localRoutine.getParameter(i).isWriteable());
          }
          else
          {
            int j = localRoutine.getParameter(i).getParameterMode();
            localHsqlList = localObject3[i].resolveColumnReferences(this.session, RangeGroup.emptyGroup, paramArrayOfRangeGroup, localHsqlList);
            if ((j != 1) && (localObject4.getType() != 6))
              throw Error.error(5603);
          }
        }
        ExpressionColumn.checkColumnsResolved(localHsqlList);
        for (i = 0; i < localObject3.length; i++)
        {
          localObject3[i].resolveTypes(this.session, null);
          if (!localRoutine.getParameter(i).getDataType().canBeAssignedFrom(localObject3[i].getDataType()))
            throw Error.error(5561);
        }
        StatementProcedure localStatementProcedure = new StatementProcedure(this.session, localRoutine, (Expression[])localObject3, this.compileContext);
        return localStatementProcedure;
      }
    }
    if (paramBoolean)
      throw Error.error(5501, this.token.tokenString);
    Object localObject1 = XreadValueExpression();
    Object localObject2 = ((Expression)localObject1).resolveColumnReferences(this.session, RangeGroup.emptyGroup, paramArrayOfRangeGroup, null);
    ExpressionColumn.checkColumnsResolved((HsqlList)localObject2);
    ((Expression)localObject1).resolveTypes(this.session, null);
    Object localObject3 = new StatementProcedure(this.session, (Expression)localObject1, this.compileContext);
    return localObject3;
  }

  void resolveOuterReferencesAndTypes(RangeGroup[] paramArrayOfRangeGroup, Expression paramExpression)
  {
    HsqlList localHsqlList = paramExpression.resolveColumnReferences(this.session, RangeGroup.emptyGroup, 0, paramArrayOfRangeGroup, null, false);
    ExpressionColumn.checkColumnsResolved(localHsqlList);
    paramExpression.resolveTypes(this.session, null);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ParserDML
 * JD-Core Version:    0.6.2
 */