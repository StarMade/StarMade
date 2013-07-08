package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntKeyHashMap;
import org.hsqldb.lib.Set;
import org.hsqldb.result.ResultProperties;
import org.hsqldb.store.BitMap;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.Type;
import org.hsqldb.types.Types;

public class ParserDQL
  extends ParserBase
{
  protected Database database;
  protected Session session;
  protected final CompileContext compileContext;
  HsqlException lastError;
  
  ParserDQL(Session paramSession, Scanner paramScanner)
  {
    super(paramScanner);
    this.session = paramSession;
    this.database = paramSession.getDatabase();
    this.compileContext = new CompileContext(paramSession, this);
  }
  
  void reset(String paramString)
  {
    reset(paramString, 1);
  }
  
  void reset(String paramString, int paramInt)
  {
    super.reset(paramString);
    this.compileContext.reset(paramInt);
    this.lastError = null;
  }
  
  void checkIsSchemaObjectName()
  {
    if (this.database.sqlEnforceNames) {
      checkIsNonReservedIdentifier();
    } else {
      checkIsNonCoreReservedIdentifier();
    }
    if (this.database.sqlRegularNames) {
      checkIsIrregularCharInIdentifier();
    }
  }
  
  Type readTypeDefinition(boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = -2147483648;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    checkIsIdentifier();
    if (this.token.namePrefix == null) {
      i = Type.getTypeNr(this.token.tokenString);
    }
    if ((this.database.sqlSyntaxOra) && (!this.session.isProcessingScript()) && (i == 91))
    {
      read();
      return Type.SQL_TIMESTAMP_NO_FRACTION;
    }
    if (i == -2147483648)
    {
      if (paramBoolean2)
      {
        checkIsSchemaObjectName();
        String str1 = this.session.getSchemaName(this.token.namePrefix);
        Type localType = this.database.schemaManager.getDomainOrUDT(this.token.tokenString, str1, false);
        if (localType != null)
        {
          getRecordedToken().setExpression(localType);
          this.compileContext.addSchemaObject(localType);
          read();
          return localType;
        }
      }
      if (this.token.namePrefix != null) {
        throw Error.error(5509, this.token.tokenString);
      }
      if (this.database.sqlSyntaxOra) {
        switch (this.token.tokenType)
        {
        case 563: 
        case 564: 
          read();
          return Type.SQL_DOUBLE;
        case 603: 
          read();
          if (this.token.tokenType == 618)
          {
            read();
            return Type.getType(61, null, null, 16777216L, 0);
          }
          return Type.getType(12, null, this.database.collation, 16777216L, 0);
        case 452: 
          read();
          if (this.token.tokenType == 786)
          {
            read();
            int i1 = 0;
            int i2 = readInteger();
            i3 = 0;
            if (this.token.tokenType == 774)
            {
              read();
              i3 = readInteger();
            }
            else if (i2 < 10)
            {
              i1 = 1;
            }
            readThis(772);
            return i1 != 0 ? Type.SQL_INTEGER : Type.getType(3, null, null, i2, i3);
          }
          return Type.SQL_DOUBLE;
        case 618: 
          i = 61;
          break;
        case 612: 
        case 634: 
          i = 12;
          n = 1;
        }
      }
      if ((this.database.sqlSyntaxMys) || (this.database.sqlSyntaxPgs)) {
        switch (this.token.tokenType)
        {
        case 631: 
          i = -1;
          n = 1;
          break;
        case 573: 
          if (this.database.sqlSyntaxPgs) {
            i = 100;
          }
          break;
        }
      }
      if (i == -2147483648) {
        throw Error.error(5509, this.token.tokenString);
      }
    }
    read();
    switch (i)
    {
    case 1: 
      if (this.token.tokenType == 313)
      {
        read();
        i = 12;
      }
      else if (this.token.tokenType == 147)
      {
        read();
        readThis(453);
        i = 40;
      }
      break;
    case 8: 
      if (this.token.tokenType == 212) {
        read();
      }
      break;
    case 60: 
      if (this.token.tokenType == 313)
      {
        read();
        i = 61;
      }
      else if (this.token.tokenType == 147)
      {
        read();
        readThis(453);
        i = 30;
      }
      break;
    case 14: 
      if (this.token.tokenType == 313)
      {
        read();
        i = 15;
      }
      break;
    case 10: 
      return readIntervalType(false);
    }
    long l = i == 93 ? 6L : 0L;
    int i3 = 0;
    if ((Types.requiresPrecision(i)) && (this.token.tokenType != 786) && (this.database.sqlEnforceSize) && (!this.session.isProcessingScript)) {
      throw Error.error(5599, Type.getDefaultType(i).getNameString());
    }
    if (Types.acceptsPrecision(i))
    {
      if (this.token.tokenType == 786)
      {
        int i4 = 1;
        read();
        switch (this.token.tokenType)
        {
        case 845: 
          if ((this.token.dataType.typeCode != 4) && (this.token.dataType.typeCode != 25)) {
            throw unexpectedToken();
          }
          break;
        case 852: 
          if ((i == 30) || (i == 40)) {}
          switch (this.token.lobMultiplierType)
          {
          case 426: 
            i4 = 1024;
            break;
          case 435: 
            i4 = 1048576;
            break;
          case 405: 
            i4 = 1073741824;
            break;
          case 462: 
          case 521: 
          default: 
            throw unexpectedToken();
            throw unexpectedToken(this.token.getFullString());
          }
          break;
        default: 
          throw unexpectedToken();
        }
        j = 1;
        l = ((Number)this.token.tokenValue).longValue();
        if ((l < 0L) || ((l == 0L) && (!Types.acceptsZeroPrecision(i)))) {
          throw Error.error(5592);
        }
        l *= i4;
        read();
        if ((i == 1) || (i == 12) || (i == 40)) {
          if (this.token.tokenType == 355)
          {
            read();
          }
          else if (this.token.tokenType == 454)
          {
            read();
            l /= 2L;
          }
        }
        if ((Types.acceptsScaleCreateParam(i)) && (this.token.tokenType == 774))
        {
          read();
          i3 = readInteger();
          if (i3 < 0) {
            throw Error.error(5592);
          }
          k = 1;
        }
        if ((n != 0) && (!readIfThis(33))) {
          readIfThis(568);
        }
        readThis(772);
      }
      else if (i == 14)
      {
        l = 1L;
      }
      else if ((i == 30) || (i == 40))
      {
        l = 16777216L;
      }
      else if ((this.database.sqlEnforceSize) && ((i == 1) || (i == 60)))
      {
        l = 1L;
      }
      if ((i == 93) || (i == 92))
      {
        if (l > 9L) {
          throw Error.error(5592);
        }
        i3 = (int)l;
        l = 0L;
        if (this.token.tokenType == 319)
        {
          read();
          readThis(281);
          readThis(549);
          if (i == 93) {
            i = 95;
          } else {
            i = 94;
          }
        }
        else if (this.token.tokenType == 321)
        {
          read();
          readThis(281);
          readThis(549);
        }
      }
    }
    switch (i)
    {
    case -1: 
      if (this.database.sqlLongvarIsLob) {
        i = 40;
      } else {
        i = 12;
      }
      if (j == 0) {
        l = 16777216L;
      }
      break;
    case -4: 
      if (this.database.sqlLongvarIsLob) {
        i = 30;
      } else {
        i = 61;
      }
      if (j == 0) {
        l = 16777216L;
      }
      break;
    case 1: 
      if ((this.database.sqlSyntaxDb2) && (readIfThis(112)))
      {
        readThis(565);
        readThis(378);
        i = 60;
      }
      break;
    case 40: 
      m = 1;
      break;
    case 100: 
      if (j == 0) {
        l = 32768L;
      }
      m = 1;
      break;
    case 12: 
      if ((this.database.sqlSyntaxDb2) && (readIfThis(112)))
      {
        readThis(565);
        readThis(378);
        i = 61;
        if (j == 0) {
          l = 32768L;
        }
      }
      else
      {
        m = 1;
        if (j == 0) {
          l = 32768L;
        }
      }
      break;
    case 60: 
      break;
    case 61: 
      if (j == 0) {
        l = 32768L;
      }
      break;
    case 2: 
    case 3: 
      if ((j == 0) && (k == 0) && (!this.database.sqlEnforceSize))
      {
        l = 128L;
        i3 = 32;
      }
      break;
    }
    if ((this.session.ignoreCase) && (i == 12)) {
      i = 100;
    }
    Collation localCollation = this.database.collation;
    Charset localCharset = null;
    if (m != 0)
    {
      if (this.token.tokenType == 35)
      {
        read();
        readThis(254);
        checkIsSchemaObjectName();
        String str2 = this.session.getSchemaName(this.token.namePrefix);
        localCharset = (Charset)this.database.schemaManager.getSchemaObject(this.token.tokenString, str2, 14);
        read();
      }
      if (this.token.tokenType == 41)
      {
        read();
        checkIsSimpleName();
        try
        {
          localCollation = Collation.getCollation(this.token.tokenString);
        }
        catch (HsqlException localHsqlException)
        {
          String str3 = this.session.getSchemaName(this.token.namePrefix);
          localCollation = (Collation)this.database.schemaManager.getSchemaObject(this.token.tokenString, str3, 15);
        }
        read();
      }
    }
    Object localObject = Type.getType(i, localCharset, localCollation, l, i3);
    if (this.token.tokenType == 8)
    {
      if (((Type)localObject).isLobType()) {
        throw unexpectedToken();
      }
      read();
      int i5 = 1024;
      if (this.token.tokenType == 781)
      {
        read();
        i5 = readInteger();
        if (i3 < 0) {
          throw Error.error(5592);
        }
        readThis(790);
      }
      localObject = new ArrayType((Type)localObject, i5);
    }
    return localObject;
  }
  
  void readSimpleColumnNames(OrderedHashSet paramOrderedHashSet, RangeVariable paramRangeVariable, boolean paramBoolean)
  {
    do
    {
      ColumnSchema localColumnSchema = readSimpleColumnName(paramRangeVariable, paramBoolean);
      if (!paramOrderedHashSet.add(localColumnSchema.getName().name)) {
        throw Error.error(5579, localColumnSchema.getName().name);
      }
    } while (readIfThis(774));
    if (this.token.tokenType != 772) {
      throw unexpectedToken();
    }
  }
  
  void readTargetSpecificationList(OrderedHashSet paramOrderedHashSet, RangeVariable[] paramArrayOfRangeVariable, LongDeque paramLongDeque)
  {
    do
    {
      Expression localExpression = XreadTargetSpecification(paramArrayOfRangeVariable, paramLongDeque);
      if (!paramOrderedHashSet.add(localExpression))
      {
        ColumnSchema localColumnSchema = localExpression.getColumn();
        throw Error.error(5579, localColumnSchema.getName().name);
      }
    } while (readIfThis(774));
    if ((this.token.tokenType != 772) && (this.token.tokenType != 115)) {
      throw unexpectedToken();
    }
  }
  
  int[] readColumnList(Table paramTable, boolean paramBoolean)
  {
    OrderedHashSet localOrderedHashSet = readColumnNames(paramBoolean);
    return paramTable.getColumnIndexes(localOrderedHashSet);
  }
  
  void readSimpleColumnNames(OrderedHashSet paramOrderedHashSet, Table paramTable, boolean paramBoolean)
  {
    do
    {
      ColumnSchema localColumnSchema = readSimpleColumnName(paramTable, paramBoolean);
      if (!paramOrderedHashSet.add(localColumnSchema.getName().name)) {
        throw Error.error(5577, localColumnSchema.getName().name);
      }
    } while (readIfThis(774));
    if (this.token.tokenType != 772) {
      throw unexpectedToken();
    }
  }
  
  HsqlNameManager.HsqlName[] readColumnNames(HsqlNameManager.HsqlName paramHsqlName)
  {
    BitMap localBitMap = new BitMap(32);
    OrderedHashSet localOrderedHashSet = readColumnNames(localBitMap, false);
    HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[localOrderedHashSet.size()];
    for (int i = 0; i < arrayOfHsqlName.length; i++)
    {
      String str = (String)localOrderedHashSet.get(i);
      boolean bool = localBitMap.isSet(i);
      arrayOfHsqlName[i] = this.database.nameManager.newHsqlName(paramHsqlName.schema, str, bool, 9, paramHsqlName);
    }
    return arrayOfHsqlName;
  }
  
  OrderedHashSet readColumnNames(boolean paramBoolean)
  {
    return readColumnNames(null, paramBoolean);
  }
  
  OrderedHashSet readColumnNames(BitMap paramBitMap, boolean paramBoolean)
  {
    readThis(786);
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    readColumnNameList(localOrderedHashSet, paramBitMap, paramBoolean);
    readThis(772);
    return localOrderedHashSet;
  }
  
  void readColumnNameList(OrderedHashSet paramOrderedHashSet, BitMap paramBitMap, boolean paramBoolean)
  {
    int i = 0;
    do
    {
      if (this.session.isProcessingScript)
      {
        if (!isSimpleName()) {
          this.token.isDelimitedIdentifier = true;
        }
      }
      else {
        checkIsSimpleName();
      }
      if (!paramOrderedHashSet.add(this.token.tokenString)) {
        throw Error.error(5577, this.token.tokenString);
      }
      if ((paramBitMap != null) && (isDelimitedIdentifier())) {
        paramBitMap.set(i);
      }
      read();
      i++;
      if ((paramBoolean) && ((this.token.tokenType == 338) || (this.token.tokenType == 389))) {
        read();
      }
    } while (readIfThis(774));
  }
  
  HsqlNameManager.SimpleName[] readColumnNameList(OrderedHashSet paramOrderedHashSet)
  {
    BitMap localBitMap = new BitMap(32);
    readThis(786);
    readColumnNameList(paramOrderedHashSet, localBitMap, false);
    readThis(772);
    HsqlNameManager.SimpleName[] arrayOfSimpleName = new HsqlNameManager.SimpleName[paramOrderedHashSet.size()];
    for (int i = 0; i < paramOrderedHashSet.size(); i++)
    {
      HsqlNameManager.SimpleName localSimpleName = HsqlNameManager.getSimpleName((String)paramOrderedHashSet.get(i), localBitMap.isSet(i));
      arrayOfSimpleName[i] = localSimpleName;
    }
    return arrayOfSimpleName;
  }
  
  int XreadUnionType()
  {
    int i = 0;
    switch (this.token.tokenType)
    {
    case 298: 
      read();
      i = 1;
      if (this.token.tokenType == 2)
      {
        i = 2;
        read();
      }
      else if (this.token.tokenType == 85)
      {
        read();
      }
      break;
    case 138: 
      read();
      i = 3;
      if (this.token.tokenType == 2)
      {
        i = 4;
        read();
      }
      else if (this.token.tokenType == 85)
      {
        read();
      }
      break;
    case 98: 
    case 609: 
      read();
      i = 6;
      if (this.token.tokenType == 2)
      {
        i = 5;
        read();
      }
      else if (this.token.tokenType == 85)
      {
        read();
      }
      break;
    }
    return i;
  }
  
  void XreadUnionCorrespondingClause(QueryExpression paramQueryExpression)
  {
    if (this.token.tokenType == 51)
    {
      read();
      paramQueryExpression.setUnionCorresoponding();
      if (this.token.tokenType == 24)
      {
        read();
        OrderedHashSet localOrderedHashSet = readColumnNames(false);
        paramQueryExpression.setUnionCorrespondingColumns(localOrderedHashSet);
      }
    }
  }
  
  QueryExpression XreadQueryExpression()
  {
    Object localObject2;
    if (this.token.tokenType == 319)
    {
      read();
      boolean bool = readIfThis(220);
      this.compileContext.initSubqueryNames();
      for (;;)
      {
        checkIsSimpleName();
        localObject1 = null;
        HsqlNameManager.HsqlName localHsqlName1 = this.database.nameManager.newHsqlName(this.token.tokenString, isDelimitedIdentifier(), 27);
        localHsqlName1.schema = SqlInvariants.SYSTEM_SUBQUERY_HSQLNAME;
        read();
        if (this.token.tokenType == 786) {
          localObject1 = readColumnNames(localHsqlName1);
        } else if (bool) {
          super.unexpectedTokenRequire("(");
        }
        readThis(10);
        readThis(786);
        localObject2 = XreadTableNamedSubqueryBody(localHsqlName1, (HsqlNameManager.HsqlName[])localObject1, bool ? 24 : 23);
        readThis(772);
        if (this.token.tokenType == 71) {
          throw super.unsupportedFeature();
        }
        if ((bool) && (this.token.tokenType == 71))
        {
          Object localObject3 = localObject2;
          int[] arrayOfInt = readColumnList(localObject3, false);
          readThis(254);
          checkIsSimpleName();
          HsqlNameManager.HsqlName localHsqlName2 = this.database.nameManager.newColumnHsqlName(localObject3.getName(), this.token.tokenString, this.token.isDelimitedIdentifier);
          ColumnSchema localColumnSchema1 = new ColumnSchema(localHsqlName2, null, true, false, null);
          if (localObject3.getColumnIndex(localHsqlName2.name) != -1) {
            throw Error.error(5578, this.token.tokenString);
          }
          read();
          readThis(285);
          String str1 = readQuotedString();
          if (str1.length() != 1) {
            throw unexpectedToken(str1);
          }
          readThis(78);
          String str2 = readQuotedString();
          if (str2.length() != 1) {
            throw unexpectedToken(str2);
          }
          if (str1.equals(str2)) {
            throw unexpectedToken(str1);
          }
          readThis(306);
          checkIsSimpleName();
          checkIsSimpleName();
          localHsqlName2 = this.database.nameManager.newColumnHsqlName(localObject3.getName(), this.token.tokenString, this.token.isDelimitedIdentifier);
          if (localObject3.getColumnIndex(localHsqlName2.name) != -1) {
            throw Error.error(5578, this.token.tokenString);
          }
          read();
          ColumnSchema localColumnSchema2 = new ColumnSchema(localHsqlName2, null, true, false, null);
        }
        this.compileContext.registerSubquery(localHsqlName1.name, (TableDerived)localObject2);
        if (this.token.tokenType != 774) {
          break;
        }
        read();
      }
    }
    QueryExpression localQueryExpression = XreadQueryExpressionBody();
    Object localObject1 = XreadOrderByExpression();
    if (localQueryExpression.sortAndSlice == null)
    {
      localQueryExpression.addSortAndSlice((SortAndSlice)localObject1);
    }
    else if (localQueryExpression.sortAndSlice.hasLimit())
    {
      if (((SortAndSlice)localObject1).hasLimit()) {
        throw Error.error(5549);
      }
      for (int i = 0; i < ((SortAndSlice)localObject1).exprList.size(); i++)
      {
        localObject2 = (Expression)((SortAndSlice)localObject1).exprList.get(i);
        localQueryExpression.sortAndSlice.addOrderExpression((Expression)localObject2);
      }
    }
    else
    {
      localQueryExpression.addSortAndSlice((SortAndSlice)localObject1);
    }
    this.compileContext.unregisterSubqueries();
    return localQueryExpression;
  }
  
  QueryExpression XreadQueryExpressionBody()
  {
    for (QueryExpression localQueryExpression = XreadQueryTerm();; localQueryExpression = XreadSetOperation(localQueryExpression)) {
      switch (this.token.tokenType)
      {
      }
    }
    return localQueryExpression;
  }
  
  QueryExpression XreadQueryTerm()
  {
    for (QueryExpression localQueryExpression = XreadQueryPrimary(); this.token.tokenType == 138; localQueryExpression = XreadSetOperation(localQueryExpression)) {}
    return localQueryExpression;
  }
  
  private QueryExpression XreadSetOperation(QueryExpression paramQueryExpression)
  {
    paramQueryExpression = new QueryExpression(this.compileContext, paramQueryExpression);
    int i = XreadUnionType();
    XreadUnionCorrespondingClause(paramQueryExpression);
    QueryExpression localQueryExpression = XreadQueryTerm();
    paramQueryExpression.addUnion(localQueryExpression, i);
    return paramQueryExpression;
  }
  
  QueryExpression XreadQueryPrimary()
  {
    Object localObject;
    switch (this.token.tokenType)
    {
    case 251: 
    case 278: 
    case 308: 
      localObject = XreadSimpleTable();
      return localObject;
    case 786: 
      read();
      localObject = XreadQueryExpressionBody();
      SortAndSlice localSortAndSlice = XreadOrderByExpression();
      readThis(772);
      if (((QueryExpression)localObject).sortAndSlice == null)
      {
        ((QueryExpression)localObject).addSortAndSlice(localSortAndSlice);
      }
      else if (((QueryExpression)localObject).sortAndSlice.hasLimit())
      {
        if (localSortAndSlice.hasLimit()) {
          throw Error.error(5549);
        }
        for (int i = 0; i < localSortAndSlice.exprList.size(); i++)
        {
          Expression localExpression = (Expression)localSortAndSlice.exprList.get(i);
          ((QueryExpression)localObject).sortAndSlice.addOrderExpression(localExpression);
        }
      }
      else
      {
        ((QueryExpression)localObject).addSortAndSlice(localSortAndSlice);
      }
      return localObject;
    }
    throw unexpectedToken();
  }
  
  QuerySpecification XreadSimpleTable()
  {
    Object localObject;
    QuerySpecification localQuerySpecification;
    switch (this.token.tokenType)
    {
    case 278: 
      read();
      localObject = readTableName();
      if (((Table)localObject).isView()) {
        localObject = ((View)localObject).newDerivedTable(this.session);
      }
      localQuerySpecification = new QuerySpecification(this.session, (Table)localObject, this.compileContext, false);
      break;
    case 308: 
      read();
      localObject = XreadRowValueExpressionList();
      localQuerySpecification = new QuerySpecification(this.session, (Table)localObject, this.compileContext, true);
      break;
    case 251: 
      localQuerySpecification = XreadQuerySpecification();
      break;
    default: 
      throw unexpectedToken();
    }
    return localQuerySpecification;
  }
  
  QuerySpecification XreadQuerySpecification()
  {
    QuerySpecification localQuerySpecification = XreadSelect();
    if (!localQuerySpecification.isValueList) {
      XreadTableExpression(localQuerySpecification);
    }
    return localQuerySpecification;
  }
  
  void XreadTableExpression(QuerySpecification paramQuerySpecification)
  {
    XreadFromClause(paramQuerySpecification);
    readWhereGroupHaving(paramQuerySpecification);
  }
  
  QuerySpecification XreadSelect()
  {
    QuerySpecification localQuerySpecification = new QuerySpecification(this.compileContext);
    readThis(251);
    Object localObject;
    if ((this.token.tokenType == 756) || (this.token.tokenType == 600))
    {
      localObject = XreadTopOrLimit();
      if (localObject != null) {
        localQuerySpecification.addSortAndSlice((SortAndSlice)localObject);
      }
    }
    if (this.token.tokenType == 85)
    {
      localQuerySpecification.isDistinctSelect = true;
      read();
    }
    else if (this.token.tokenType == 2)
    {
      read();
    }
    do
    {
      localObject = XreadValueExpression();
      if (this.token.tokenType == 10)
      {
        read();
        checkIsNonCoreReservedIdentifier();
      }
      if (isNonCoreReservedIdentifier())
      {
        ((Expression)localObject).setAlias(HsqlNameManager.getSimpleName(this.token.tokenString, isDelimitedIdentifier()));
        read();
      }
      localQuerySpecification.addSelectColumnExpression((Expression)localObject);
      if ((this.token.tokenType == 115) || (this.token.tokenType == 141)) {
        break;
      }
    } while (readIfThis(774));
    if (((this.token.tokenType == 772) || (this.token.tokenType == 848)) && ((this.database.sqlSyntaxMss) || (this.database.sqlSyntaxMys) || (this.database.sqlSyntaxPgs)))
    {
      Expression[] arrayOfExpression = new Expression[localQuerySpecification.exprColumnList.size()];
      localQuerySpecification.exprColumnList.toArray(arrayOfExpression);
      Expression localExpression = new Expression(26, arrayOfExpression);
      for (int i = 0; i < localExpression.nodes.length; i++) {
        if (localExpression.nodes[i].opType != 25) {
          localExpression.nodes[i] = new Expression(25, new Expression[] { localExpression.nodes[i] });
        }
      }
      this.compileContext.incrementDepth();
      TableDerived localTableDerived = prepareSubqueryTable(localExpression, 26);
      localQuerySpecification = new QuerySpecification(this.session, localTableDerived, this.compileContext, true);
      this.compileContext.decrementDepth();
      return localQuerySpecification;
    }
    throw unexpectedToken();
    return localQuerySpecification;
  }
  
  void XreadFromClause(QuerySpecification paramQuerySpecification)
  {
    readThis(115);
    do
    {
      XreadTableReference(paramQuerySpecification);
    } while (readIfThis(774));
  }
  
  void XreadTableReference(QuerySpecification paramQuerySpecification)
  {
    int i = 0;
    RangeVariable localRangeVariable = readTableOrSubquery();
    paramQuerySpecification.addRangeVariable(this.session, localRangeVariable);
    for (;;)
    {
      boolean bool1 = false;
      boolean bool2 = false;
      int k = 0;
      int j = this.token.tokenType;
      switch (this.token.tokenType)
      {
      case 176: 
        if (i != 0) {
          throw unexpectedToken();
        }
        read();
        i = 1;
        break;
      case 132: 
        read();
        readThis(144);
        break;
      case 56: 
        if (i != 0) {
          throw unexpectedToken();
        }
        read();
        readThis(144);
        break;
      case 298: 
        if (i != 0) {
          throw unexpectedToken();
        }
        int m = getPosition();
        read();
        if (this.token.tokenType == 144)
        {
          read();
        }
        else
        {
          rewind(m);
          k = 1;
        }
        break;
      case 153: 
        read();
        readIfThis(200);
        readThis(144);
        bool1 = true;
        break;
      case 240: 
        read();
        readIfThis(200);
        readThis(144);
        bool2 = true;
        break;
      case 116: 
        read();
        readIfThis(200);
        readThis(144);
        bool1 = true;
        bool2 = true;
        break;
      case 144: 
        read();
        j = 132;
        break;
      case 774: 
      default: 
        if (i != 0) {
          throw unexpectedToken();
        }
        k = 1;
        if (k != 0) {
          return;
        }
        localRangeVariable = readTableOrSubquery();
        Expression localExpression = null;
        switch (j)
        {
        case 56: 
          paramQuerySpecification.addRangeVariable(this.session, localRangeVariable);
          break;
        case 298: 
          localExpression = Expression.EXPR_FALSE;
          localRangeVariable.addJoinCondition(localExpression);
          localRangeVariable.setJoinType(true, true);
          paramQuerySpecification.addRangeVariable(this.session, localRangeVariable);
          break;
        case 116: 
        case 132: 
        case 153: 
        case 240: 
          localRangeVariable.setJoinType(bool1, bool2);
          OrderedHashSet localOrderedHashSet;
          if (i != 0)
          {
            localOrderedHashSet = localRangeVariable.getUniqueColumnNameSet();
            localExpression = paramQuerySpecification.getEquiJoinExpressions(localOrderedHashSet, localRangeVariable, false);
            localRangeVariable.addJoinCondition(localExpression);
            paramQuerySpecification.addRangeVariable(this.session, localRangeVariable);
          }
          else if (this.token.tokenType == 306)
          {
            read();
            localOrderedHashSet = new OrderedHashSet();
            readThis(786);
            readSimpleColumnNames(localOrderedHashSet, localRangeVariable, false);
            readThis(772);
            localExpression = paramQuerySpecification.getEquiJoinExpressions(localOrderedHashSet, localRangeVariable, true);
            localRangeVariable.addJoinCondition(localExpression);
            paramQuerySpecification.addRangeVariable(this.session, localRangeVariable);
          }
          else if (this.token.tokenType == 194)
          {
            read();
            localExpression = XreadBooleanValueExpression();
            localRangeVariable.addJoinCondition(localExpression);
            paramQuerySpecification.addRangeVariable(this.session, localRangeVariable);
          }
          else
          {
            throw unexpectedToken();
          }
          break;
        }
        i = 0;
      }
    }
  }
  
  Expression getRowExpression(OrderedHashSet paramOrderedHashSet)
  {
    Expression[] arrayOfExpression = new Expression[paramOrderedHashSet.size()];
    for (int i = 0; i < arrayOfExpression.length; i++)
    {
      String str = (String)paramOrderedHashSet.get(i);
      arrayOfExpression[i] = new ExpressionColumn(null, null, str);
    }
    return new Expression(25, arrayOfExpression);
  }
  
  void readWhereGroupHaving(QuerySpecification paramQuerySpecification)
  {
    Expression localExpression;
    if (this.token.tokenType == 316)
    {
      read();
      localExpression = XreadBooleanValueExpression();
      paramQuerySpecification.addQueryCondition(localExpression);
    }
    if (this.token.tokenType == 122)
    {
      read();
      readThis(24);
      for (;;)
      {
        localExpression = XreadValueExpression();
        paramQuerySpecification.addGroupByColumnExpression(localExpression);
        if (this.token.tokenType != 774) {
          break;
        }
        read();
      }
    }
    if (this.token.tokenType == 125)
    {
      read();
      localExpression = XreadBooleanValueExpression();
      paramQuerySpecification.addHavingExpression(localExpression);
    }
  }
  
  SortAndSlice XreadOrderByExpression()
  {
    SortAndSlice localSortAndSlice = null;
    if (this.token.tokenType == 198)
    {
      read();
      readThis(24);
      localSortAndSlice = XreadOrderBy();
    }
    if ((this.token.tokenType == 600) || (this.token.tokenType == 107) || (this.token.tokenType == 192))
    {
      if (localSortAndSlice == null) {
        localSortAndSlice = new SortAndSlice();
      }
      XreadLimit(localSortAndSlice);
    }
    return localSortAndSlice == null ? SortAndSlice.noSort : localSortAndSlice;
  }
  
  private SortAndSlice XreadTopOrLimit()
  {
    Object localObject = null;
    Expression localExpression = null;
    if (this.token.tokenType == 600)
    {
      i = getPosition();
      read();
      localObject = XreadSimpleValueSpecificationOrNull();
      if (localObject == null)
      {
        rewind(i);
        return null;
      }
      readIfThis(774);
      localExpression = XreadSimpleValueSpecificationOrNull();
      if (localExpression == null) {
        throw Error.error(5563, 81);
      }
    }
    else if (this.token.tokenType == 756)
    {
      i = getPosition();
      read();
      localExpression = XreadSimpleValueSpecificationOrNull();
      if (localExpression == null)
      {
        rewind(i);
        return null;
      }
      localObject = new ExpressionValue(ValuePool.INTEGER_0, Type.SQL_INTEGER);
    }
    int i = 1;
    if (((Expression)localObject).isUnresolvedParam()) {
      ((Expression)localObject).setDataType(this.session, Type.SQL_INTEGER);
    } else if (((Expression)localObject).opType == 1) {
      i = (((Expression)localObject).getDataType().typeCode == 4) && (((Integer)((Expression)localObject).getValue(null)).intValue() >= 0) ? 1 : 0;
    } else {
      throw Error.error(5563, 81);
    }
    if (localExpression.isUnresolvedParam()) {
      localExpression.setDataType(this.session, Type.SQL_INTEGER);
    } else if (localExpression.opType == 1) {
      i &= ((localExpression.getDataType().typeCode == 4) && (((Integer)localExpression.getValue(null)).intValue() >= 0) ? 1 : 0);
    } else {
      throw Error.error(5563, 81);
    }
    if (i != 0)
    {
      SortAndSlice localSortAndSlice = new SortAndSlice();
      localSortAndSlice.addLimitCondition(new ExpressionOp(95, (Expression)localObject, localExpression));
      return localSortAndSlice;
    }
    throw Error.error(5563, 81);
  }
  
  private void XreadLimit(SortAndSlice paramSortAndSlice)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    if (this.token.tokenType == 192)
    {
      read();
      localObject1 = XreadSimpleValueSpecificationOrNull();
      if (localObject1 == null) {
        throw Error.error(5563, 81);
      }
      if ((this.token.tokenType == 243) || (this.token.tokenType == 245)) {
        read();
      }
    }
    if (this.token.tokenType == 600)
    {
      read();
      localObject2 = XreadSimpleValueSpecificationOrNull();
      if (localObject2 == null) {
        throw Error.error(5563, 81);
      }
      if (localObject1 == null) {
        if (this.token.tokenType == 774)
        {
          read();
          localObject1 = localObject2;
          localObject2 = XreadSimpleValueSpecificationOrNull();
        }
        else if (this.token.tokenType == 192)
        {
          read();
          localObject1 = XreadSimpleValueSpecificationOrNull();
        }
      }
      if ((this.database.sqlSyntaxPgs) || (this.database.sqlSyntaxMys)) {
        paramSortAndSlice.setZeroLimit();
      }
    }
    else if (this.token.tokenType == 107)
    {
      read();
      if ((this.token.tokenType == 401) || (this.token.tokenType == 448)) {
        read();
      }
      localObject2 = XreadSimpleValueSpecificationOrNull();
      if (localObject2 == null) {
        localObject2 = new ExpressionValue(ValuePool.INTEGER_1, Type.SQL_INTEGER);
      }
      if ((this.token.tokenType == 243) || (this.token.tokenType == 245)) {
        read();
      }
      readThis(195);
      paramSortAndSlice.setStrictLimit();
    }
    if ((paramSortAndSlice.hasOrder()) && (this.token.tokenType == 306))
    {
      read();
      readThis(597);
      paramSortAndSlice.setUsingIndex();
    }
    if (localObject1 == null) {
      localObject1 = new ExpressionValue(ValuePool.INTEGER_0, Type.SQL_INTEGER);
    }
    int i = 1;
    if (((Expression)localObject1).isUnresolvedParam()) {
      ((Expression)localObject1).setDataType(this.session, Type.SQL_INTEGER);
    }
    if ((localObject2 != null) && (((Expression)localObject2).isUnresolvedParam())) {
      ((Expression)localObject2).setDataType(this.session, Type.SQL_INTEGER);
    }
    if (i != 0)
    {
      paramSortAndSlice.addLimitCondition(new ExpressionOp(95, (Expression)localObject1, (Expression)localObject2));
      return;
    }
    throw Error.error(5563, 81);
  }
  
  private SortAndSlice XreadOrderBy()
  {
    SortAndSlice localSortAndSlice = new SortAndSlice();
    for (;;)
    {
      Expression localExpression = XreadValueExpression();
      ExpressionOrderBy localExpressionOrderBy = new ExpressionOrderBy(localExpression);
      if (this.token.tokenType == 389)
      {
        localExpressionOrderBy.setDescending();
        read();
      }
      else if (this.token.tokenType == 338)
      {
        read();
      }
      if (!this.database.sqlNullsFirst) {
        localExpressionOrderBy.setNullsLast(true);
      }
      if (this.token.tokenType == 451)
      {
        read();
        if (this.token.tokenType == 401)
        {
          read();
          localExpressionOrderBy.setNullsLast(false);
        }
        else if (this.token.tokenType == 430)
        {
          read();
          localExpressionOrderBy.setNullsLast(true);
        }
        else
        {
          throw unexpectedToken();
        }
      }
      localSortAndSlice.addOrderExpression(localExpressionOrderBy);
      if (this.token.tokenType != 774) {
        break;
      }
      read();
    }
    return localSortAndSlice;
  }
  
  protected RangeVariable readRangeVariableForDataChange(int paramInt)
  {
    Object localObject = readTableName();
    HsqlNameManager.SimpleName localSimpleName = null;
    if (paramInt != 1205)
    {
      if (this.token.tokenType == 10)
      {
        read();
        checkIsNonCoreReservedIdentifier();
      }
      if (isNonCoreReservedIdentifier())
      {
        localSimpleName = HsqlNameManager.getSimpleName(this.token.tokenString, isDelimitedIdentifier());
        read();
      }
    }
    if (((Table)localObject).isView)
    {
      switch (paramInt)
      {
      case 128: 
        if (((!((Table)localObject).isTriggerUpdatable()) || (!((Table)localObject).isTriggerInsertable())) && ((((Table)localObject).isTriggerUpdatable()) || (((Table)localObject).isTriggerInsertable()) || (!((Table)localObject).isUpdatable()) || (!((Table)localObject).isInsertable()))) {
          throw Error.error(5545);
        }
        break;
      case 82: 
        if ((!((Table)localObject).isTriggerUpdatable()) && (!((Table)localObject).isUpdatable())) {
          throw Error.error(5545);
        }
        break;
      case 19: 
        if ((!((Table)localObject).isTriggerDeletable()) && (!((Table)localObject).isUpdatable())) {
          throw Error.error(5545);
        }
        break;
      case 50: 
        if ((!((Table)localObject).isTriggerInsertable()) && (!((Table)localObject).isInsertable()) && (!this.session.isProcessingScript)) {
          throw Error.error(5545);
        }
        break;
      case 1205: 
        throw Error.error(5545);
      }
      localObject = ((View)localObject).newDerivedTable(this.session);
    }
    RangeVariable localRangeVariable = new RangeVariable((Table)localObject, localSimpleName, null, null, this.compileContext);
    return localRangeVariable;
  }
  
  protected Table readNamedSubqueryOrNull()
  {
    if (!isSimpleName()) {
      return null;
    }
    TableDerived localTableDerived = this.compileContext.getNamedSubQuery(this.token.tokenString);
    if (localTableDerived == null) {
      return null;
    }
    read();
    if (localTableDerived.isRecompiled()) {
      localTableDerived = localTableDerived.newDerivedTable(this.session);
    } else {
      localTableDerived.canRecompile = true;
    }
    return localTableDerived;
  }
  
  protected RangeVariable readTableOrSubquery()
  {
    Object localObject1 = null;
    HsqlNameManager.SimpleName localSimpleName = null;
    HsqlNameManager.SimpleName[] arrayOfSimpleName = null;
    OrderedHashSet localOrderedHashSet = null;
    int i = 0;
    int j = 0;
    Expression localExpression;
    switch (this.token.tokenType)
    {
    case 786: 
      localObject1 = XreadTableSubqueryOrNull(false);
      if (localObject1 == null)
      {
        localObject1 = XreadJoinedTableAsSubqueryOrNull();
        if (localObject1 == null) {
          localObject1 = XreadTableSubqueryOrNull(true);
        } else {
          i = 1;
        }
      }
      break;
    case 301: 
      localExpression = XreadCollectionDerivedTable(23);
      localObject1 = localExpression.getTable();
      j = 1;
      break;
    case 149: 
      localExpression = XreadLateralDerivedTable();
      localObject1 = localExpression.getTable();
      j = 1;
      break;
    case 278: 
      localExpression = XreadTableFunctionDerivedTable();
      localObject1 = localExpression.getTable();
      break;
    default: 
      localObject1 = readNamedSubqueryOrNull();
      if (localObject1 == null) {
        localObject1 = readTableName();
      }
      if (((Table)localObject1).isView()) {
        localObject1 = ((View)localObject1).newDerivedTable(this.session);
      }
      break;
    }
    int k = 0;
    if (this.token.tokenType == 10)
    {
      read();
      checkIsNonCoreReservedIdentifier();
      k = 1;
    }
    if (isNonCoreReservedIdentifier())
    {
      int m = (this.token.tokenType == 600) || (this.token.tokenType == 192) || (this.token.tokenType == 107) ? 1 : 0;
      int n = this.token.tokenType == 609 ? 1 : 0;
      int i1 = getPosition();
      localSimpleName = HsqlNameManager.getSimpleName(this.token.tokenString, isDelimitedIdentifier());
      read();
      if (this.token.tokenType == 786)
      {
        localOrderedHashSet = new OrderedHashSet();
        arrayOfSimpleName = readColumnNameList(localOrderedHashSet);
      }
      else if ((k == 0) && (m != 0))
      {
        if ((this.token.tokenType == 773) || (this.token.tokenType == 788) || (this.token.tokenType == 845))
        {
          localSimpleName = null;
          rewind(i1);
        }
      }
      else if ((k == 0) && (n != 0))
      {
        rewind(i1);
      }
    }
    Object localObject2;
    if (i != 0) {
      localObject2 = new RangeVariableJoined((Table)localObject1, localSimpleName, localOrderedHashSet, arrayOfSimpleName, this.compileContext);
    } else {
      localObject2 = new RangeVariable((Table)localObject1, localSimpleName, localOrderedHashSet, arrayOfSimpleName, this.compileContext);
    }
    if (j != 0) {
      ((RangeVariable)localObject2).isLateral = true;
    }
    return localObject2;
  }
  
  private Expression readAggregate()
  {
    int i = this.token.tokenType;
    read();
    readThis(786);
    Expression localExpression = readAggregateExpression(i);
    readThis(772);
    readFilterClause(localExpression);
    return localExpression;
  }
  
  private void readFilterClause(Expression paramExpression)
  {
    int i = getPosition();
    if (this.token.tokenType == 108)
    {
      read();
      if (this.token.tokenType != 786)
      {
        rewind(i);
        return;
      }
      readThis(786);
      readThis(316);
      Expression localExpression = XreadBooleanValueExpression();
      paramExpression.setCondition(localExpression);
      readThis(772);
    }
  }
  
  private Expression readAggregateExpression(int paramInt)
  {
    int i = getExpressionType(paramInt);
    boolean bool = false;
    int j = 0;
    SortAndSlice localSortAndSlice = null;
    String str = null;
    if (this.token.tokenType == 85)
    {
      bool = true;
      read();
    }
    else if (this.token.tokenType == 2)
    {
      j = 1;
      read();
    }
    int k = getPosition();
    Expression localExpression = XreadValueExpression();
    switch (i)
    {
    case 71: 
      if (localExpression.getType() == 97)
      {
        if (((ExpressionColumn)localExpression).tableName != null) {
          throw unexpectedToken();
        }
        if ((j != 0) || (bool)) {
          throw unexpectedToken();
        }
        localExpression.opType = 11;
      }
      else if (this.token.tokenType == 774)
      {
        rewind(k);
        localExpression = XreadRowElementList(false);
      }
      break;
    case 78: 
    case 79: 
    case 80: 
    case 81: 
      if ((j != 0) || (bool)) {
        throw unexpectedToken(j != 0 ? "ALL" : "DISTINCT");
      }
      break;
    case 82: 
    case 83: 
      if (this.token.tokenType == 198)
      {
        read();
        readThis(24);
        localSortAndSlice = XreadOrderBy();
      }
      if ((i == 83) && (this.token.tokenType == 626))
      {
        read();
        super.checkIsValue(1);
        str = (String)this.token.tokenValue;
        read();
      }
      return new ExpressionArrayAggregate(i, bool, localExpression, localSortAndSlice, str);
    case 85: 
      return new ExpressionArrayAggregate(i, bool, localExpression, localSortAndSlice, str);
    case 72: 
    case 73: 
    case 74: 
    case 75: 
    case 76: 
    case 77: 
    case 84: 
    default: 
      if (localExpression.getType() == 11) {
        throw unexpectedToken();
      }
      break;
    }
    ExpressionAggregate localExpressionAggregate = new ExpressionAggregate(i, bool, localExpression);
    return localExpressionAggregate;
  }
  
  Expression XreadValueSpecificationOrNull()
  {
    Object localObject = null;
    int i = 0;
    switch (this.token.tokenType)
    {
    case 787: 
      read();
      break;
    case 784: 
      read();
      i = 1;
    }
    localObject = XreadUnsignedValueSpecificationOrNull();
    if (localObject == null) {
      return null;
    }
    if (i != 0) {
      localObject = new ExpressionArithmetic(31, (Expression)localObject);
    }
    return localObject;
  }
  
  Expression XreadUnsignedValueSpecificationOrNull()
  {
    Object localObject;
    switch (this.token.tokenType)
    {
    case 294: 
      read();
      return Expression.EXPR_TRUE;
    case 106: 
      read();
      return Expression.EXPR_FALSE;
    case 78: 
      if (this.compileContext.contextuallyTypedExpression)
      {
        read();
        localObject = new ExpressionColumn(4);
        return localObject;
      }
      break;
    case 186: 
      localObject = new ExpressionValue(null, null);
      read();
      return localObject;
    case 845: 
      localObject = new ExpressionValue(this.token.tokenValue, this.token.dataType);
      read();
      return localObject;
    case 846: 
    case 847: 
      if (!this.token.isHostParameter) {
        return null;
      }
      return null;
    case 773: 
      read();
      if ((this.token.tokenType != 847) && (this.token.tokenType != 846)) {
        throw unexpectedToken(":");
      }
    case 788: 
      ExpressionColumn localExpressionColumn = new ExpressionColumn(8);
      this.compileContext.addParameter(localExpressionColumn, getPosition());
      read();
      return localExpressionColumn;
    case 358: 
      return XreadCurrentCollationSpec();
    case 60: 
    case 62: 
    case 63: 
    case 64: 
    case 65: 
    case 68: 
    case 69: 
    case 253: 
    case 277: 
    case 305: 
    case 307: 
      FunctionSQL localFunctionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
      if (localFunctionSQL == null) {
        return null;
      }
      return readSQLFunction(localFunctionSQL);
    }
    return null;
  }
  
  Expression XreadSimpleValueSpecificationOrNull()
  {
    Object localObject;
    switch (this.token.tokenType)
    {
    case 845: 
      localObject = new ExpressionValue(this.token.tokenValue, this.token.dataType);
      read();
      return localObject;
    case 773: 
      read();
      if ((this.token.tokenType != 847) && (this.token.tokenType != 846)) {
        throw unexpectedToken(":");
      }
    case 788: 
      ExpressionColumn localExpressionColumn = new ExpressionColumn(8);
      this.compileContext.addParameter(localExpressionColumn, getPosition());
      read();
      return localExpressionColumn;
    case 846: 
    case 847: 
      checkValidCatalogName(this.token.namePrePrePrefix);
      localObject = new ExpressionColumn(this.token.namePrePrefix, this.token.namePrefix, this.token.tokenString);
      read();
      return localObject;
    }
    return null;
  }
  
  Expression XreadAllTypesValueExpressionPrimary(boolean paramBoolean)
  {
    Expression localExpression = null;
    switch (this.token.tokenType)
    {
    case 101: 
    case 299: 
      if (paramBoolean) {
        return XreadPredicate();
      }
      break;
    case 243: 
      if (!paramBoolean)
      {
        read();
        readThis(786);
        localExpression = XreadRowElementList(true);
        readThis(772);
      }
      break;
    default: 
      localExpression = XreadSimpleValueExpressionPrimary();
      if (localExpression != null) {
        localExpression = XreadArrayElementReference(localExpression);
      }
      break;
    }
    if (localExpression == null)
    {
      int i = 0;
      if (this.token.tokenType == 243)
      {
        read();
        checkIsThis(786);
        i = 1;
      }
      if (this.token.tokenType == 786)
      {
        read();
        localExpression = XreadRowElementList(true);
        readThis(772);
      }
    }
    if ((paramBoolean) && (localExpression != null)) {
      localExpression = XreadPredicateRightPart(localExpression);
    }
    return localExpression;
  }
  
  Expression XreadValueExpressionPrimary()
  {
    Expression localExpression = XreadSimpleValueExpressionPrimary();
    if (localExpression != null)
    {
      localExpression = XreadArrayElementReference(localExpression);
      return localExpression;
    }
    if (this.token.tokenType == 786)
    {
      read();
      localExpression = XreadValueExpression();
      readThis(772);
    }
    else
    {
      return null;
    }
    return localExpression;
  }
  
  Expression XreadSimpleValueExpressionPrimary()
  {
    Object localObject1 = XreadUnsignedValueSpecificationOrNull();
    if (localObject1 != null) {
      return localObject1;
    }
    int i = getPosition();
    Object localObject2;
    switch (this.token.tokenType)
    {
    case 786: 
      read();
      int j = getPosition();
      int k = readOpenBrackets();
      switch (this.token.tokenType)
      {
      case 251: 
      case 278: 
      case 308: 
        localObject2 = null;
        rewind(j);
        try
        {
          localObject2 = XreadSubqueryTableBody(21);
          readThis(772);
        }
        catch (HsqlException localHsqlException)
        {
          localHsqlException.setLevel(this.compileContext.subqueryDepth);
          if ((this.lastError == null) || (this.lastError.getLevel() < localHsqlException.getLevel())) {
            this.lastError = localHsqlException;
          }
          rewind(i);
          return null;
        }
        if (((TableDerived)localObject2).queryExpression.isSingleColumn()) {
          localObject1 = new Expression(21, (TableDerived)localObject2);
        } else {
          localObject1 = new Expression(22, (TableDerived)localObject2);
        }
        if (readCloseBrackets(k) != k) {
          throw unexpectedToken();
        }
        return localObject1;
      }
      rewind(i);
      return null;
    case 771: 
      localObject1 = new ExpressionColumn(this.token.namePrePrefix, this.token.namePrefix);
      getRecordedToken().setExpression(localObject1);
      read();
      return localObject1;
    case 693: 
      localObject1 = readLeastExpressionOrNull();
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 683: 
      localObject1 = readGreatestExpressionOrNull();
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 678: 
      localObject1 = readDecodeExpressionOrNull();
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 655: 
      localObject1 = readConcatExpressionOrNull();
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 571: 
      localObject1 = readCaseWhenExpressionOrNull();
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 29: 
      return readCaseExpression();
    case 187: 
      return readNullIfExpression();
    case 40: 
      return readCoalesceExpression();
    case 685: 
    case 691: 
      localObject1 = readIfNullExpressionOrNull();
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 613: 
      localObject1 = readIfNull2ExpressionOrNull();
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 30: 
    case 49: 
      localObject1 = readCastExpressionOrNull();
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 72: 
    case 140: 
    case 281: 
    case 282: 
      localObject1 = readDateTimeIntervalLiteral(this.session);
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 8: 
      return readCollection(19);
    case 6: 
    case 9: 
    case 16: 
    case 52: 
    case 97: 
    case 163: 
    case 168: 
    case 258: 
    case 269: 
    case 270: 
    case 274: 
    case 309: 
    case 310: 
    case 606: 
    case 684: 
      return readAggregate();
    case 448: 
      localObject1 = readSequenceExpressionOrNull(12);
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 59: 
      localObject1 = readSequenceExpressionOrNull(13);
      if (localObject1 != null) {
        return localObject1;
      }
      break;
    case 581: 
      if (this.database.sqlSyntaxPgs)
      {
        read();
        readThis(786);
        localObject2 = readQuotedString();
        Scanner localScanner = this.session.getScanner();
        localScanner.reset((String)localObject2);
        localScanner.scanNext();
        String str = this.session.getSchemaName(localScanner.token.namePrefix);
        NumberSequence localNumberSequence = this.database.schemaManager.getSequence(localScanner.token.tokenString, str, true);
        localObject1 = new ExpressionColumn(localNumberSequence, 13);
        readThis(772);
        return localObject1;
      }
      break;
    case 599: 
      if (this.database.sqlSyntaxPgs)
      {
        read();
        readThis(786);
        readThis(772);
        return FunctionCustom.newCustomFunction("IDENTITY", 128);
      }
      break;
    case 611: 
      if (this.database.sqlSyntaxPgs) {
        return readNextvalFunction();
      }
      break;
    case 244: 
      read();
      if (this.token.tokenType == 786)
      {
        read();
        readThis(772);
        readThis(201);
        readThis(786);
        readThis(772);
      }
      else
      {
        rewind(i);
        break;
      }
      return new ExpressionColumn(14);
    case 624: 
      read();
      if (this.token.tokenType == 786)
      {
        read();
        if (this.token.tokenType == 772)
        {
          read();
        }
        else
        {
          rewind(i);
          break;
        }
      }
      else if ((!this.database.sqlSyntaxOra) && (!this.database.sqlSyntaxDb2))
      {
        rewind(i);
        break;
      }
      return new ExpressionColumn(14);
    case 153: 
    case 240: 
      break;
    case 278: 
      read();
      readThis(786);
      localObject2 = XreadSubqueryTableBody(23);
      readThis(772);
      return new Expression(23, (TableDerived)localObject2);
    default: 
      if (isCoreReservedKey()) {
        throw unexpectedToken();
      }
      break;
    }
    localObject1 = readColumnOrFunctionExpression();
    if (((Expression)localObject1).isAggregate()) {
      readFilterClause((Expression)localObject1);
    }
    return localObject1;
  }
  
  Expression readNextvalFunction()
  {
    read();
    readThis(786);
    String str1 = readQuotedString();
    Scanner localScanner = this.session.getScanner();
    localScanner.reset(str1);
    localScanner.scanNext();
    String str2 = this.session.getSchemaName(localScanner.token.namePrefix);
    NumberSequence localNumberSequence = this.database.schemaManager.getSequence(localScanner.token.tokenString, str2, true);
    ExpressionColumn localExpressionColumn = new ExpressionColumn(localNumberSequence, 12);
    readThis(772);
    return localExpressionColumn;
  }
  
  Expression XreadAllTypesPrimary(boolean paramBoolean)
  {
    Expression localExpression = null;
    switch (this.token.tokenType)
    {
    case 1: 
    case 27: 
    case 31: 
    case 32: 
    case 34: 
    case 36: 
    case 103: 
    case 105: 
    case 111: 
    case 156: 
    case 161: 
    case 170: 
    case 182: 
    case 189: 
    case 190: 
    case 203: 
    case 209: 
    case 210: 
    case 211: 
    case 265: 
    case 272: 
    case 273: 
    case 288: 
    case 292: 
    case 304: 
    case 317: 
      FunctionSQL localFunctionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
      if (localFunctionSQL == null) {
        throw unsupportedFeature();
      }
      localExpression = readSQLFunction(localFunctionSQL);
      if (localExpression != null) {
        break;
      }
    default: 
      localExpression = XreadAllTypesValueExpressionPrimary(paramBoolean);
    }
    localExpression = XreadModifier(localExpression);
    return localExpression;
  }
  
  Expression XreadModifier(Expression paramExpression)
  {
    Object localObject;
    switch (this.token.tokenType)
    {
    case 13: 
      read();
      localObject = null;
      if (this.token.tokenType == 157)
      {
        read();
      }
      else
      {
        readThis(281);
        readThis(549);
        localObject = XreadValueExpressionPrimary();
        switch (this.token.tokenType)
        {
        case 73: 
        case 127: 
        case 169: 
        case 173: 
        case 250: 
        case 323: 
          IntervalType localIntervalType = readIntervalType(false);
          if (((Expression)localObject).getType() == 33) {
            ((Expression)localObject).dataType = localIntervalType;
          } else {
            localObject = new ExpressionOp((Expression)localObject, localIntervalType);
          }
          break;
        }
      }
      paramExpression = new ExpressionOp(92, paramExpression, (Expression)localObject);
      break;
    case 73: 
    case 127: 
    case 169: 
    case 173: 
    case 250: 
    case 323: 
      localObject = readIntervalType(true);
      if (paramExpression.getType() == 33) {
        paramExpression.dataType = ((Type)localObject);
      } else {
        paramExpression = new ExpressionOp(paramExpression, (Type)localObject);
      }
      break;
    case 41: 
      read();
      if (this.token.namePrefix == null)
      {
        try
        {
          localObject = Collation.getCollation(this.token.tokenString);
        }
        catch (HsqlException localHsqlException)
        {
          localObject = (Collation)this.database.schemaManager.getSchemaObject(this.session.getSchemaName(this.token.namePrefix), this.token.tokenString, 15);
        }
        paramExpression.setCollation((Collation)localObject);
        read();
      }
      break;
    }
    return paramExpression;
  }
  
  Expression XreadValueExpressionWithContext()
  {
    this.compileContext.contextuallyTypedExpression = true;
    Expression localExpression = XreadValueExpressionOrNull();
    this.compileContext.contextuallyTypedExpression = false;
    return localExpression;
  }
  
  Expression XreadValueExpressionOrNull()
  {
    Expression localExpression = XreadAllTypesCommonValueExpression(true);
    if (localExpression == null) {
      return null;
    }
    return localExpression;
  }
  
  Expression XreadValueExpression()
  {
    Object localObject = XreadAllTypesCommonValueExpression(true);
    if (this.token.tokenType == 781)
    {
      read();
      Expression localExpression = XreadNumericValueExpression();
      readThis(790);
      localObject = new ExpressionAccessor((Expression)localObject, localExpression);
    }
    return localObject;
  }
  
  Expression XreadRowOrCommonValueExpression()
  {
    return XreadAllTypesCommonValueExpression(false);
  }
  
  Expression XreadAllTypesCommonValueExpression(boolean paramBoolean)
  {
    Object localObject1 = XreadAllTypesTerm(paramBoolean);
    int i = 0;
    int j = 0;
    for (;;)
    {
      switch (this.token.tokenType)
      {
      case 787: 
        i = 32;
        paramBoolean = false;
        break;
      case 784: 
        i = 33;
        paramBoolean = false;
        break;
      case 775: 
        i = 36;
        paramBoolean = false;
        break;
      case 197: 
        if (paramBoolean) {
          i = 50;
        }
        break;
      }
      j = 1;
      if (j != 0) {
        break;
      }
      read();
      Object localObject2 = localObject1;
      localObject1 = XreadAllTypesTerm(paramBoolean);
      localObject1 = paramBoolean ? new ExpressionLogical(i, localObject2, (Expression)localObject1) : new ExpressionArithmetic(i, localObject2, (Expression)localObject1);
    }
    return localObject1;
  }
  
  Expression XreadAllTypesTerm(boolean paramBoolean)
  {
    Object localObject1 = XreadAllTypesFactor(paramBoolean);
    int i = 0;
    int j = 0;
    for (;;)
    {
      switch (this.token.tokenType)
      {
      case 771: 
        i = 34;
        paramBoolean = false;
        break;
      case 776: 
        i = 35;
        paramBoolean = false;
        break;
      case 5: 
        if (paramBoolean) {
          i = 49;
        }
        break;
      }
      j = 1;
      if (j != 0) {
        break;
      }
      read();
      Object localObject2 = localObject1;
      localObject1 = XreadAllTypesFactor(paramBoolean);
      if (localObject1 == null) {
        throw unexpectedToken();
      }
      localObject1 = paramBoolean ? new ExpressionLogical(i, localObject2, (Expression)localObject1) : new ExpressionArithmetic(i, localObject2, (Expression)localObject1);
    }
    return localObject1;
  }
  
  Expression XreadAllTypesFactor(boolean paramBoolean)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    switch (this.token.tokenType)
    {
    case 787: 
      read();
      paramBoolean = false;
      break;
    case 784: 
      read();
      paramBoolean = false;
      i = 1;
      break;
    case 183: 
      if (paramBoolean)
      {
        read();
        j = 1;
      }
      break;
    }
    Object localObject = XreadAllTypesPrimary(paramBoolean);
    if ((paramBoolean) && (this.token.tokenType == 142))
    {
      read();
      if (this.token.tokenType == 183)
      {
        read();
        j = j == 0 ? 1 : 0;
      }
      if (this.token.tokenType == 294)
      {
        read();
      }
      else if (this.token.tokenType == 106)
      {
        read();
        j = j == 0 ? 1 : 0;
      }
      else if (this.token.tokenType == 300)
      {
        read();
        k = 1;
      }
      else
      {
        throw unexpectedToken();
      }
    }
    if (k != 0) {
      localObject = new ExpressionLogical(47, (Expression)localObject);
    } else if (i != 0) {
      localObject = new ExpressionArithmetic(31, (Expression)localObject);
    } else if (j != 0) {
      localObject = new ExpressionLogical(48, (Expression)localObject);
    }
    return localObject;
  }
  
  Expression XreadStringValueExpression()
  {
    return XreadCharacterValueExpression();
  }
  
  Expression XreadCharacterValueExpression()
  {
    Object localObject1 = XreadCharacterPrimary();
    SchemaObject localSchemaObject = readCollateClauseOrNull();
    while (this.token.tokenType == 775)
    {
      read();
      Object localObject2 = localObject1;
      localObject1 = XreadCharacterPrimary();
      localSchemaObject = readCollateClauseOrNull();
      localObject1 = new ExpressionArithmetic(36, localObject2, (Expression)localObject1);
    }
    return localObject1;
  }
  
  Expression XreadCharacterPrimary()
  {
    switch (this.token.tokenType)
    {
    case 161: 
    case 203: 
    case 272: 
    case 292: 
    case 304: 
      FunctionSQL localFunctionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
      Expression localExpression = readSQLFunction(localFunctionSQL);
      if (localExpression != null) {
        return localExpression;
      }
      break;
    }
    return XreadValueExpressionPrimary();
  }
  
  Expression XreadNumericPrimary()
  {
    switch (this.token.tokenType)
    {
    case 1: 
    case 27: 
    case 31: 
    case 32: 
    case 34: 
    case 36: 
    case 103: 
    case 105: 
    case 111: 
    case 156: 
    case 170: 
    case 190: 
    case 209: 
    case 211: 
    case 265: 
    case 317: 
      FunctionSQL localFunctionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
      if (localFunctionSQL == null) {
        throw super.unexpectedToken();
      }
      Expression localExpression = readSQLFunction(localFunctionSQL);
      if (localExpression != null) {
        return localExpression;
      }
      break;
    }
    return XreadValueExpressionPrimary();
  }
  
  Expression XreadNumericValueExpression()
  {
    int i;
    Object localObject2;
    for (Object localObject1 = XreadTerm();; localObject1 = new ExpressionArithmetic(i, localObject2, (Expression)localObject1))
    {
      if (this.token.tokenType == 787)
      {
        i = 32;
      }
      else
      {
        if (this.token.tokenType != 784) {
          break;
        }
        i = 33;
      }
      read();
      localObject2 = localObject1;
      localObject1 = XreadTerm();
    }
    return localObject1;
  }
  
  Expression XreadTerm()
  {
    int i;
    Object localObject2;
    for (Object localObject1 = XreadFactor();; localObject1 = new ExpressionArithmetic(i, localObject2, (Expression)localObject1))
    {
      if (this.token.tokenType == 771)
      {
        i = 34;
      }
      else
      {
        if (this.token.tokenType != 776) {
          break;
        }
        i = 35;
      }
      read();
      localObject2 = localObject1;
      localObject1 = XreadFactor();
      if (localObject1 == null) {
        throw unexpectedToken();
      }
    }
    return localObject1;
  }
  
  Expression XreadFactor()
  {
    int i = 0;
    if (this.token.tokenType == 787)
    {
      read();
    }
    else if (this.token.tokenType == 784)
    {
      read();
      i = 1;
    }
    Object localObject = XreadNumericPrimary();
    if (localObject == null) {
      return null;
    }
    if (i != 0) {
      localObject = new ExpressionArithmetic(31, (Expression)localObject);
    }
    return localObject;
  }
  
  Expression XreadDatetimeValueExpression()
  {
    int i;
    Object localObject2;
    for (Object localObject1 = XreadDateTimeIntervalTerm();; localObject1 = new ExpressionArithmetic(i, localObject2, (Expression)localObject1))
    {
      if (this.token.tokenType == 787)
      {
        i = 32;
      }
      else
      {
        if (this.token.tokenType != 784) {
          break;
        }
        i = 33;
      }
      read();
      localObject2 = localObject1;
      localObject1 = XreadDateTimeIntervalTerm();
    }
    return localObject1;
  }
  
  Expression XreadIntervalValueExpression()
  {
    int i;
    Object localObject2;
    for (Object localObject1 = XreadDateTimeIntervalTerm();; localObject1 = new ExpressionArithmetic(i, localObject2, (Expression)localObject1))
    {
      if (this.token.tokenType == 787)
      {
        i = 32;
      }
      else
      {
        if (this.token.tokenType != 784) {
          break;
        }
        i = 33;
      }
      read();
      localObject2 = localObject1;
      localObject1 = XreadDateTimeIntervalTerm();
    }
    return localObject1;
  }
  
  Expression XreadDateTimeIntervalTerm()
  {
    switch (this.token.tokenType)
    {
    case 1: 
    case 61: 
    case 66: 
    case 67: 
    case 158: 
    case 159: 
      FunctionSQL localFunctionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
      if (localFunctionSQL == null) {
        throw super.unexpectedToken();
      }
      return readSQLFunction(localFunctionSQL);
    }
    return XreadValueExpressionPrimary();
  }
  
  Expression XreadDateTimeValueFunctionOrNull()
  {
    FunctionSQL localFunctionSQL = null;
    switch (this.token.tokenType)
    {
    case 61: 
    case 66: 
    case 67: 
    case 158: 
    case 159: 
      localFunctionSQL = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
      break;
    case 736: 
      if (!this.database.sqlSyntaxOra) {
        return null;
      }
    case 707: 
    case 735: 
    case 755: 
      localFunctionSQL = FunctionCustom.newCustomFunction(this.token.tokenString, this.token.tokenType);
      break;
    }
    return null;
    if (localFunctionSQL == null) {
      throw super.unexpectedToken();
    }
    return readSQLFunction(localFunctionSQL);
  }
  
  Expression XreadBooleanValueExpression()
  {
    try
    {
      Object localObject1 = XreadBooleanTermOrNull();
      if (localObject1 == null) {
        throw Error.error(5568);
      }
      while (this.token.tokenType == 197)
      {
        int i = 50;
        read();
        Object localObject2 = localObject1;
        localObject1 = XreadBooleanTermOrNull();
        if (localObject1 == null) {
          throw Error.error(5568);
        }
        localObject1 = new ExpressionLogical(i, localObject2, (Expression)localObject1);
      }
      if (localObject1 == null) {
        throw Error.error(5568);
      }
      return localObject1;
    }
    catch (HsqlException localHsqlException1)
    {
      localHsqlException1.setLevel(this.compileContext.subqueryDepth);
      HsqlException localHsqlException2;
      if ((this.lastError != null) && (this.lastError.getLevel() >= localHsqlException1.getLevel()))
      {
        localHsqlException2 = this.lastError;
        this.lastError = null;
      }
      throw localHsqlException2;
    }
  }
  
  Expression XreadBooleanTermOrNull()
  {
    Object localObject1 = XreadBooleanFactorOrNull();
    if (localObject1 == null) {
      return null;
    }
    while (this.token.tokenType == 5)
    {
      int i = 49;
      read();
      Object localObject2 = localObject1;
      localObject1 = XreadBooleanFactorOrNull();
      if (localObject1 == null) {
        throw unexpectedToken();
      }
      localObject1 = new ExpressionLogical(i, localObject2, (Expression)localObject1);
    }
    return localObject1;
  }
  
  Expression XreadBooleanFactorOrNull()
  {
    int i = 0;
    if (this.token.tokenType == 183)
    {
      read();
      i = 1;
    }
    Object localObject = XreadBooleanTestOrNull();
    if (localObject == null) {
      return null;
    }
    if (i != 0) {
      localObject = new ExpressionLogical(48, (Expression)localObject);
    }
    return localObject;
  }
  
  Expression XreadBooleanTestOrNull()
  {
    int i = 0;
    int j = 0;
    Object localObject = XreadBooleanPrimaryOrNull();
    if (localObject == null) {
      return localObject;
    }
    if (this.token.tokenType == 142)
    {
      read();
      if (this.token.tokenType == 183)
      {
        read();
        j = 1;
      }
      if (this.token.tokenType == 294)
      {
        read();
      }
      else if (this.token.tokenType == 106)
      {
        read();
        j = j == 0 ? 1 : 0;
      }
      else if (this.token.tokenType == 300)
      {
        read();
        i = 1;
      }
      else
      {
        throw unexpectedToken();
      }
    }
    if (i != 0) {
      localObject = new ExpressionLogical(47, (Expression)localObject);
    }
    if (j != 0) {
      localObject = new ExpressionLogical(48, (Expression)localObject);
    }
    return localObject;
  }
  
  Expression XreadBooleanPrimaryOrNull()
  {
    Expression localExpression = null;
    int i;
    switch (this.token.tokenType)
    {
    case 101: 
    case 299: 
      return XreadPredicate();
    case 243: 
      read();
      readThis(786);
      localExpression = XreadRowElementList(true);
      readThis(772);
      break;
    default: 
      i = getPosition();
      try
      {
        localExpression = XreadAllTypesCommonValueExpression(false);
      }
      catch (HsqlException localHsqlException1)
      {
        localHsqlException1.setLevel(this.compileContext.subqueryDepth);
        if ((this.lastError == null) || (this.lastError.getLevel() < localHsqlException1.getLevel())) {
          this.lastError = localHsqlException1;
        }
        rewind(i);
      }
    }
    if ((localExpression == null) && (this.token.tokenType == 786))
    {
      read();
      i = getPosition();
      try
      {
        localExpression = XreadRowElementList(true);
        readThis(772);
      }
      catch (HsqlException localHsqlException2)
      {
        localHsqlException2.setLevel(this.compileContext.subqueryDepth);
        if ((this.lastError == null) || (this.lastError.getLevel() < localHsqlException2.getLevel())) {
          this.lastError = localHsqlException2;
        }
        rewind(i);
        localExpression = XreadBooleanValueExpression();
        readThis(772);
      }
    }
    if (localExpression != null) {
      localExpression = XreadPredicateRightPart(localExpression);
    }
    return localExpression;
  }
  
  Expression XreadBooleanPredicand()
  {
    if (this.token.tokenType == 786)
    {
      read();
      localExpression = XreadBooleanValueExpression();
      readThis(772);
      return localExpression;
    }
    Expression localExpression = XreadSimpleValueExpressionPrimary();
    if (localExpression != null) {
      localExpression = XreadArrayElementReference(localExpression);
    }
    return localExpression;
  }
  
  Expression XreadPredicate()
  {
    switch (this.token.tokenType)
    {
    case 101: 
      read();
      localExpression = XreadTableSubquery(55);
      return new ExpressionLogical(55, localExpression);
    case 299: 
      read();
      localExpression = XreadTableSubquery(57);
      return new ExpressionLogical(57, localExpression);
    }
    Expression localExpression = XreadRowValuePredicand();
    return XreadPredicateRightPart(localExpression);
  }
  
  Expression XreadPredicateRightPart(Expression paramExpression)
  {
    int i = 0;
    ExpressionLogical localExpressionLogical = null;
    int j = getPosition();
    if (this.token.tokenType == 183)
    {
      read();
      i = 1;
    }
    switch (this.token.tokenType)
    {
    case 142: 
      if (i != 0) {
        throw unexpectedToken();
      }
      read();
      if (this.token.tokenType == 183)
      {
        i = 1;
        read();
      }
      if (this.token.tokenType == 186)
      {
        read();
        localExpressionLogical = new ExpressionLogical(47, paramExpression);
      }
      else if (this.token.tokenType == 85)
      {
        read();
        readThis(115);
        Expression localExpression1 = XreadRowValuePredicand();
        localExpressionLogical = new ExpressionLogical(58, paramExpression, localExpression1);
        i = i == 0 ? 1 : 0;
      }
      else
      {
        rewind(j);
        return paramExpression;
      }
      break;
    case 154: 
      localExpressionLogical = XreadLikePredicateRightPart(paramExpression);
      localExpressionLogical.noOptimisation = this.isCheckOrTriggerCondition;
      break;
    case 18: 
      localExpressionLogical = XreadBetweenPredicateRightPart(paramExpression);
      break;
    case 130: 
      localExpressionLogical = XreadInPredicateRightPart(paramExpression);
      localExpressionLogical.noOptimisation = this.isCheckOrTriggerCondition;
      break;
    case 202: 
      if (i != 0) {
        throw unexpectedToken();
      }
      localExpressionLogical = XreadOverlapsPredicateRightPart(paramExpression);
      break;
    case 396: 
    case 779: 
    case 780: 
    case 782: 
    case 783: 
    case 785: 
      if (i != 0) {
        throw unexpectedToken();
      }
      int k = getExpressionType(this.token.tokenType);
      read();
      switch (this.token.tokenType)
      {
      case 2: 
      case 6: 
      case 258: 
        localExpressionLogical = XreadQuantifiedComparisonRightPart(k, paramExpression);
        break;
      default: 
        Expression localExpression2 = XreadRowValuePredicand();
        localExpressionLogical = new ExpressionLogical(k, paramExpression, localExpression2);
      }
      break;
    case 162: 
      localExpressionLogical = XreadMatchPredicateRightPart(paramExpression);
      break;
    default: 
      if (i != 0) {
        throw unexpectedToken();
      }
      return paramExpression;
    }
    if (i != 0) {
      localExpressionLogical = new ExpressionLogical(48, localExpressionLogical);
    }
    return localExpressionLogical;
  }
  
  private ExpressionLogical XreadBetweenPredicateRightPart(Expression paramExpression)
  {
    int i = 0;
    read();
    if (this.token.tokenType == 12)
    {
      read();
    }
    else if (this.token.tokenType == 275)
    {
      i = 1;
      read();
    }
    Expression localExpression1 = XreadRowValuePredicand();
    readThis(5);
    Expression localExpression2 = XreadRowValuePredicand();
    ExpressionLogical localExpressionLogical1 = new ExpressionLogical(42, paramExpression, localExpression1);
    ExpressionLogical localExpressionLogical2 = new ExpressionLogical(45, paramExpression, localExpression2);
    ExpressionLogical localExpressionLogical3 = new ExpressionLogical(49, localExpressionLogical1, localExpressionLogical2);
    if (i != 0)
    {
      localExpressionLogical1 = new ExpressionLogical(45, paramExpression, localExpression1);
      localExpressionLogical2 = new ExpressionLogical(42, paramExpression, localExpression2);
      ExpressionLogical localExpressionLogical4 = new ExpressionLogical(49, localExpressionLogical1, localExpressionLogical2);
      return new ExpressionLogical(50, localExpressionLogical3, localExpressionLogical4);
    }
    return localExpressionLogical3;
  }
  
  private ExpressionLogical XreadQuantifiedComparisonRightPart(int paramInt, Expression paramExpression)
  {
    int i = this.token.tokenType;
    int j = 0;
    switch (this.token.tokenType)
    {
    case 6: 
    case 258: 
      j = 52;
      break;
    case 2: 
      j = 51;
      break;
    default: 
      throw Error.runtimeError(201, "ParserDQL");
    }
    read();
    readThis(786);
    int k = getPosition();
    readOpenBrackets();
    Expression localExpression;
    switch (this.token.tokenType)
    {
    case 251: 
    case 278: 
    case 308: 
    case 319: 
      rewind(k);
      localObject = XreadSubqueryTableBody(54);
      localExpression = new Expression(23, (TableDerived)localObject);
      readThis(772);
      break;
    default: 
      rewind(k);
      localExpression = readAggregateExpression(i);
      readThis(772);
      readFilterClause(localExpression);
    }
    Object localObject = new ExpressionLogical(paramInt, paramExpression, localExpression);
    ((ExpressionLogical)localObject).setSubType(j);
    return localObject;
  }
  
  private ExpressionLogical XreadInPredicateRightPart(Expression paramExpression)
  {
    int i = paramExpression.getDegree();
    Expression localExpression = null;
    read();
    readThis(786);
    int j = getPosition();
    int k = readOpenBrackets();
    Object localObject;
    switch (this.token.tokenType)
    {
    case 301: 
      localExpression = XreadCollectionDerivedTable(54);
      readThis(772);
      readCloseBrackets(k);
      break;
    case 251: 
    case 278: 
    case 308: 
    case 319: 
      rewind(j);
      localObject = XreadSubqueryTableBody(54);
      localExpression = new Expression(23, (TableDerived)localObject);
      readThis(772);
      break;
    default: 
      rewind(j);
      localExpression = XreadInValueListConstructor(i);
      readThis(772);
    }
    if (this.isCheckOrTriggerCondition)
    {
      localObject = new ExpressionLogical(54, paramExpression, localExpression);
    }
    else
    {
      localObject = new ExpressionLogical(41, paramExpression, localExpression);
      ((ExpressionLogical)localObject).setSubType(52);
    }
    return localObject;
  }
  
  Expression XreadInValueList(int paramInt)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    for (;;)
    {
      localObject = XreadValueExpression();
      if (((Expression)localObject).getType() != 25) {
        localObject = new Expression(25, new Expression[] { localObject });
      }
      localHsqlArrayList.add(localObject);
      if (this.token.tokenType != 774) {
        break;
      }
      read();
    }
    Object localObject = new Expression[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(localObject);
    Expression localExpression = new Expression(26, (Expression[])localObject);
    for (int i = 0; i < localObject.length; i++)
    {
      if (localObject[i].getType() != 25) {
        localObject[i] = new Expression(25, new Expression[] { localObject[i] });
      }
      Expression[] arrayOfExpression = localObject[i].nodes;
      if (arrayOfExpression.length != paramInt) {
        throw unexpectedToken();
      }
      for (int j = 0; j < paramInt; j++) {
        if (arrayOfExpression[j].getType() == 25) {
          throw unexpectedToken();
        }
      }
    }
    return localExpression;
  }
  
  private ExpressionLogical XreadLikePredicateRightPart(Expression paramExpression)
  {
    read();
    Expression localExpression1 = XreadStringValueExpression();
    Expression localExpression2 = null;
    if (this.token.tokenString.equals("ESCAPE"))
    {
      read();
      localExpression2 = XreadStringValueExpression();
    }
    return new ExpressionLike(paramExpression, localExpression1, localExpression2, this.isCheckOrTriggerCondition);
  }
  
  private ExpressionLogical XreadMatchPredicateRightPart(Expression paramExpression)
  {
    int i = 0;
    int j = 59;
    read();
    if (this.token.tokenType == 299)
    {
      read();
      i = 1;
    }
    if (this.token.tokenType == 511)
    {
      read();
      j = i != 0 ? 62 : 59;
    }
    else if (this.token.tokenType == 470)
    {
      read();
      j = i != 0 ? 63 : 60;
    }
    else if (this.token.tokenType == 116)
    {
      read();
      j = i != 0 ? 64 : 61;
    }
    int k = i != 0 ? 23 : 54;
    Expression localExpression = XreadTableSubquery(k);
    return new ExpressionLogical(j, paramExpression, localExpression);
  }
  
  private ExpressionLogical XreadOverlapsPredicateRightPart(Expression paramExpression)
  {
    if (paramExpression.getType() != 25) {
      throw Error.error(5564);
    }
    if (paramExpression.nodes.length != 2) {
      throw Error.error(5564);
    }
    read();
    if (this.token.tokenType != 786) {
      throw unexpectedToken();
    }
    Expression localExpression = XreadRowValuePredicand();
    if (localExpression.nodes.length != 2) {
      throw Error.error(5564);
    }
    return new ExpressionLogical(56, paramExpression, localExpression);
  }
  
  Expression XreadRowValueExpression()
  {
    Expression localExpression = XreadExplicitRowValueConstructorOrNull();
    if (localExpression != null) {
      return localExpression;
    }
    return XreadRowValueSpecialCase();
  }
  
  Expression XreadTableRowValueConstructor()
  {
    Expression localExpression = XreadExplicitRowValueConstructorOrNull();
    if (localExpression != null) {
      return localExpression;
    }
    return XreadRowValueSpecialCase();
  }
  
  Expression XreadRowValuePredicand()
  {
    return XreadRowOrCommonValueExpression();
  }
  
  Expression XreadRowValueSpecialCase()
  {
    Expression localExpression = XreadSimpleValueExpressionPrimary();
    if (localExpression != null) {
      localExpression = XreadArrayElementReference(localExpression);
    }
    return localExpression;
  }
  
  Expression XreadRowValueConstructor()
  {
    Expression localExpression = XreadExplicitRowValueConstructorOrNull();
    if (localExpression != null) {
      return localExpression;
    }
    localExpression = XreadRowOrCommonValueExpression();
    if (localExpression != null) {
      return localExpression;
    }
    return XreadBooleanValueExpression();
  }
  
  Expression XreadExplicitRowValueConstructorOrNull()
  {
    Expression localExpression;
    switch (this.token.tokenType)
    {
    case 786: 
      read();
      int i = getPosition();
      int j = readOpenBrackets();
      switch (this.token.tokenType)
      {
      case 251: 
      case 278: 
      case 308: 
        rewind(i);
        TableDerived localTableDerived = XreadSubqueryTableBody(22);
        readThis(772);
        return new Expression(22, localTableDerived);
      }
      rewind(i);
      localExpression = XreadRowElementList(true);
      readThis(772);
      return localExpression;
    case 243: 
      read();
      readThis(786);
      localExpression = XreadRowElementList(false);
      readThis(772);
      return localExpression;
    }
    return null;
  }
  
  Expression XreadRowElementList(boolean paramBoolean)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Expression localExpression;
    for (;;)
    {
      localExpression = XreadValueExpression();
      localHsqlArrayList.add(localExpression);
      if (this.token.tokenType != 774) {
        break;
      }
      read();
    }
    if ((paramBoolean) && (localHsqlArrayList.size() == 1)) {
      return localExpression;
    }
    Expression[] arrayOfExpression = new Expression[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfExpression);
    return new Expression(25, arrayOfExpression);
  }
  
  Expression XreadCurrentCollationSpec()
  {
    throw Error.error(1500);
  }
  
  Expression XreadTableSubquery(int paramInt)
  {
    readThis(786);
    TableDerived localTableDerived = XreadSubqueryTableBody(paramInt);
    readThis(772);
    return new Expression(23, localTableDerived);
  }
  
  Table XreadTableSubqueryOrNull(boolean paramBoolean)
  {
    int i = 0;
    int j = getPosition();
    readThis(786);
    switch (this.token.tokenType)
    {
    case 251: 
    case 278: 
    case 308: 
    case 319: 
      break;
    case 786: 
      if (paramBoolean) {
        break;
      }
    default: 
      i = 1;
    }
    if (i != 0)
    {
      rewind(j);
      return null;
    }
    TableDerived localTableDerived = XreadSubqueryTableBody(23);
    readThis(772);
    return localTableDerived;
  }
  
  TableDerived XreadJoinedTableAsSubqueryOrNull()
  {
    int i = getPosition();
    readThis(786);
    this.compileContext.incrementDepth();
    QuerySpecification localQuerySpecification = XreadJoinedTableAsView();
    localQuerySpecification.resolveReferences(this.session, this.compileContext.getOuterRanges());
    if (localQuerySpecification.rangeVariables.length < 2)
    {
      this.compileContext.decrementDepth();
      rewind(i);
      return null;
    }
    localQuerySpecification.resolveTypesPartOne(this.session);
    localQuerySpecification.resolveTypesPartTwo(this.session);
    TableDerived localTableDerived = newSubQueryTable(localQuerySpecification, 23);
    readThis(772);
    localTableDerived.setSQL(getLastPart(i));
    localTableDerived.prepareTable();
    this.compileContext.decrementDepth();
    return localTableDerived;
  }
  
  QuerySpecification XreadJoinedTableAsView()
  {
    QuerySpecification localQuerySpecification = new QuerySpecification(this.compileContext);
    ExpressionColumn localExpressionColumn = new ExpressionColumn(97);
    localQuerySpecification.addSelectColumnExpression(localExpressionColumn);
    XreadTableReference(localQuerySpecification);
    return localQuerySpecification;
  }
  
  TableDerived XreadTableNamedSubqueryBody(HsqlNameManager.HsqlName paramHsqlName, HsqlNameManager.HsqlName[] paramArrayOfHsqlName, int paramInt)
  {
    TableDerived localTableDerived;
    switch (paramInt)
    {
    case 24: 
      localTableDerived = XreadRecursiveSubqueryBody(paramHsqlName, paramArrayOfHsqlName);
      return localTableDerived;
    case 23: 
      localTableDerived = XreadSubqueryTableBody(paramHsqlName, paramInt);
      if (localTableDerived.queryExpression != null) {
        localTableDerived.queryExpression.resolve(this.session);
      }
      localTableDerived.prepareTable(paramArrayOfHsqlName);
      return localTableDerived;
    }
    throw super.unexpectedToken();
  }
  
  TableDerived XreadRecursiveSubqueryBody(HsqlNameManager.HsqlName paramHsqlName, HsqlNameManager.HsqlName[] paramArrayOfHsqlName)
  {
    int i = getPosition();
    this.compileContext.incrementDepth();
    this.compileContext.incrementDepth();
    QuerySpecification localQuerySpecification1 = XreadSimpleTable();
    localQuerySpecification1.resolve(this.session);
    TableDerived localTableDerived1 = newSubQueryTable(paramHsqlName, localQuerySpecification1, 23);
    this.compileContext.decrementDepth();
    localTableDerived1.prepareTable(paramArrayOfHsqlName);
    this.compileContext.initSubqueryNames();
    this.compileContext.registerSubquery(paramHsqlName.name, localTableDerived1);
    checkIsThis(298);
    int j = XreadUnionType();
    QuerySpecification localQuerySpecification2 = XreadSimpleTable();
    QueryExpression localQueryExpression = new QueryExpression(this.compileContext, localQuerySpecification1);
    localQuerySpecification2.resolveReferences(this.session, RangeGroup.emptyArray);
    localQueryExpression.addUnion(localQuerySpecification2, j);
    localQueryExpression.isRecursive = true;
    localQueryExpression.recursiveTable = localTableDerived1;
    localQueryExpression.resolve(this.session);
    TableDerived localTableDerived2 = newSubQueryTable(paramHsqlName, localQueryExpression, 23);
    localTableDerived2.prepareTable(paramArrayOfHsqlName);
    localTableDerived2.setSQL(getLastPart(i));
    this.compileContext.decrementDepth();
    return localTableDerived2;
  }
  
  TableDerived newSubQueryTable(Expression paramExpression, int paramInt)
  {
    HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.getSubqueryTableName();
    TableDerived localTableDerived = new TableDerived(this.database, localHsqlName, 2, null, paramExpression, paramInt, this.compileContext.getDepth());
    return localTableDerived;
  }
  
  TableDerived newSubQueryTable(QueryExpression paramQueryExpression, int paramInt)
  {
    return newSubQueryTable(null, paramQueryExpression, paramInt);
  }
  
  TableDerived newSubQueryTable(HsqlNameManager.HsqlName paramHsqlName, QueryExpression paramQueryExpression, int paramInt)
  {
    if (paramHsqlName == null) {
      paramHsqlName = this.database.nameManager.getSubqueryTableName();
    }
    TableDerived localTableDerived = new TableDerived(this.database, paramHsqlName, 2, paramQueryExpression, null, paramInt, this.compileContext.getDepth());
    return localTableDerived;
  }
  
  TableDerived XreadSubqueryTableBody(int paramInt)
  {
    return XreadSubqueryTableBody(null, paramInt);
  }
  
  TableDerived XreadSubqueryTableBody(HsqlNameManager.HsqlName paramHsqlName, int paramInt)
  {
    int i = getPosition();
    this.compileContext.incrementDepth();
    QueryExpression localQueryExpression = XreadQueryExpression();
    TableDerived localTableDerived = null;
    if (localQueryExpression.isValueList) {
      localTableDerived = ((QuerySpecification)localQueryExpression).getValueListTable();
    }
    if (localTableDerived == null) {
      localTableDerived = newSubQueryTable(paramHsqlName, localQueryExpression, paramInt);
    }
    localTableDerived.setSQL(getLastPart(i));
    this.compileContext.decrementDepth();
    return localTableDerived;
  }
  
  TableDerived XreadViewSubqueryTable(View paramView, boolean paramBoolean)
  {
    this.compileContext.incrementDepth();
    Object localObject;
    try
    {
      localObject = XreadQueryExpression();
    }
    catch (HsqlException localHsqlException)
    {
      localObject = XreadJoinedTableAsView();
    }
    ((QueryExpression)localObject).setView(paramView);
    ((QueryExpression)localObject).resolveReferences(this.session, RangeGroup.emptyArray);
    ((QueryExpression)localObject).resolveTypesPartOne(this.session);
    ((QueryExpression)localObject).resolveTypesPartTwo(this.session);
    if (paramBoolean) {
      ((QueryExpression)localObject).resolveTypesPartThree(this.session);
    }
    TableDerived localTableDerived = new TableDerived(this.database, paramView.getName(), 8, (QueryExpression)localObject, null, 0, this.compileContext.getDepth());
    localTableDerived.view = paramView;
    localTableDerived.columnList = paramView.columnList;
    localTableDerived.columnCount = paramView.columnList.size();
    localTableDerived.createPrimaryKey();
    localTableDerived.triggerList = paramView.triggerList;
    localTableDerived.triggerLists = paramView.triggerLists;
    this.compileContext.decrementDepth();
    return localTableDerived;
  }
  
  QueryExpression XreadViewQueryExpression(View paramView)
  {
    this.compileContext.incrementDepth();
    Object localObject;
    try
    {
      localObject = XreadQueryExpression();
    }
    catch (HsqlException localHsqlException)
    {
      localObject = XreadJoinedTableAsView();
    }
    ((QueryExpression)localObject).setView(paramView);
    ((QueryExpression)localObject).resolveReferences(this.session, RangeGroup.emptyArray);
    ((QueryExpression)localObject).resolveTypesPartOne(this.session);
    ((QueryExpression)localObject).resolveTypesPartTwo(this.session);
    this.compileContext.decrementDepth();
    return localObject;
  }
  
  Expression XreadContextuallyTypedTable(int paramInt)
  {
    Expression localExpression = readRow();
    Expression[] arrayOfExpression1 = localExpression.nodes;
    int i = 0;
    if (paramInt == 1)
    {
      if (localExpression.getType() == 25)
      {
        localExpression.opType = 26;
        for (j = 0; j < arrayOfExpression1.length; j++) {
          if (arrayOfExpression1[j].getType() != 25) {
            arrayOfExpression1[j] = new Expression(25, new Expression[] { arrayOfExpression1[j] });
          } else if (arrayOfExpression1[j].nodes.length != paramInt) {
            throw Error.error(5564);
          }
        }
        return localExpression;
      }
      localExpression = new Expression(25, new Expression[] { localExpression });
      localExpression = new Expression(26, new Expression[] { localExpression });
      return localExpression;
    }
    if (localExpression.getType() != 25) {
      throw Error.error(5564);
    }
    for (int j = 0; j < arrayOfExpression1.length; j++) {
      if (arrayOfExpression1[j].getType() == 25)
      {
        i = 1;
        break;
      }
    }
    if (i != 0)
    {
      localExpression.opType = 26;
      for (j = 0; j < arrayOfExpression1.length; j++)
      {
        if (arrayOfExpression1[j].getType() != 25) {
          throw Error.error(5564);
        }
        Expression[] arrayOfExpression2 = arrayOfExpression1[j].nodes;
        if (arrayOfExpression2.length != paramInt) {
          throw Error.error(5564);
        }
        for (int k = 0; k < paramInt; k++) {
          if (arrayOfExpression2[k].getType() == 25) {
            throw Error.error(5564);
          }
        }
      }
    }
    else
    {
      if (arrayOfExpression1.length != paramInt) {
        throw Error.error(5564);
      }
      localExpression = new Expression(26, new Expression[] { localExpression });
    }
    return localExpression;
  }
  
  private Expression XreadInValueListConstructor(int paramInt)
  {
    int i = getPosition();
    this.compileContext.incrementDepth();
    Expression localExpression = XreadInValueList(paramInt);
    TableDerived localTableDerived = newSubQueryTable(localExpression, 54);
    localTableDerived.setSQL(getLastPart(i));
    localExpression.table = localTableDerived;
    this.compileContext.decrementDepth();
    return localExpression;
  }
  
  private TableDerived XreadRowValueExpressionList()
  {
    this.compileContext.incrementDepth();
    Expression localExpression = XreadRowValueExpressionListBody();
    TableDerived localTableDerived = prepareSubqueryTable(localExpression, 26);
    this.compileContext.decrementDepth();
    return localTableDerived;
  }
  
  private TableDerived prepareSubqueryTable(Expression paramExpression, int paramInt)
  {
    HsqlList localHsqlList = paramExpression.resolveColumnReferences(this.session, RangeGroup.emptyGroup, this.compileContext.getOuterRanges(), null);
    ExpressionColumn.checkColumnsResolved(localHsqlList);
    paramExpression.resolveTypes(this.session, null);
    paramExpression.prepareTable(this.session, null, paramExpression.nodes[0].nodes.length);
    TableDerived localTableDerived = newSubQueryTable(paramExpression, paramInt);
    localTableDerived.prepareTable();
    return localTableDerived;
  }
  
  Expression XreadRowValueExpressionListBody()
  {
    Expression localExpression1 = null;
    for (;;)
    {
      int i = readOpenBrackets();
      Expression localExpression2 = readRow();
      readCloseBrackets(i);
      if (localExpression1 == null)
      {
        localExpression1 = new Expression(25, new Expression[] { localExpression2 });
      }
      else
      {
        localExpression1.nodes = ((Expression[])ArrayUtil.resizeArray(localExpression1.nodes, localExpression1.nodes.length + 1));
        localExpression1.nodes[(localExpression1.nodes.length - 1)] = localExpression2;
      }
      if (this.token.tokenType != 774) {
        break;
      }
      read();
    }
    Expression[] arrayOfExpression = localExpression1.nodes;
    int j = 1;
    if (arrayOfExpression[0].getType() == 25) {
      j = arrayOfExpression[0].nodes.length;
    }
    localExpression1.opType = 26;
    for (int k = 0; k < arrayOfExpression.length; k++) {
      if (arrayOfExpression[k].getType() == 25)
      {
        if (arrayOfExpression[k].nodes.length != j) {
          throw Error.error(5564);
        }
      }
      else
      {
        if (j != 1) {
          throw Error.error(5564);
        }
        arrayOfExpression[k] = new Expression(25, new Expression[] { arrayOfExpression[k] });
      }
    }
    return localExpression1;
  }
  
  Expression XreadTargetSpecification(RangeVariable[] paramArrayOfRangeVariable, LongDeque paramLongDeque)
  {
    ColumnSchema localColumnSchema = null;
    int i = -1;
    checkIsIdentifier();
    if (this.token.namePrePrePrefix != null) {
      checkValidCatalogName(this.token.namePrePrePrefix);
    }
    for (int j = 0; j < paramArrayOfRangeVariable.length; j++) {
      if (paramArrayOfRangeVariable[j] != null)
      {
        i = paramArrayOfRangeVariable[j].findColumn(this.token.namePrePrefix, this.token.namePrefix, this.token.tokenString);
        if (i > -1)
        {
          localColumnSchema = paramArrayOfRangeVariable[j].getColumn(i);
          read();
          break;
        }
      }
    }
    if (localColumnSchema == null) {
      throw Error.error(5501, this.token.tokenString);
    }
    paramLongDeque.add(i);
    if (this.token.tokenType == 781)
    {
      if (!localColumnSchema.getDataType().isArrayType()) {
        throw unexpectedToken();
      }
      read();
      Object localObject = XreadNumericValueExpression();
      if (localObject == null) {
        throw Error.error(5501, this.token.tokenString);
      }
      localObject = new ExpressionAccessor(localColumnSchema.getAccessor(), (Expression)localObject);
      readThis(790);
      return localObject;
    }
    return localColumnSchema.getAccessor();
  }
  
  Expression XreadCollectionDerivedTable(int paramInt)
  {
    boolean bool = false;
    int i = getPosition();
    readThis(301);
    readThis(786);
    this.compileContext.incrementDepth();
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    for (;;)
    {
      localObject = XreadValueExpression();
      localHsqlArrayList.add(localObject);
      if (this.token.tokenType != 774) {
        break;
      }
      read();
    }
    Object localObject = new Expression[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(localObject);
    readThis(772);
    if (this.token.tokenType == 319)
    {
      read();
      readThis(458);
      bool = true;
    }
    ExpressionTable localExpressionTable = new ExpressionTable((Expression[])localObject, bool);
    TableDerived localTableDerived = newSubQueryTable(localExpressionTable, paramInt);
    localTableDerived.setSQL(getLastPart(i));
    this.compileContext.decrementDepth();
    return localExpressionTable;
  }
  
  Expression XreadTableFunctionDerivedTable()
  {
    int i = getPosition();
    readThis(278);
    readThis(786);
    this.compileContext.incrementDepth();
    Object localObject = XreadValueExpression();
    if ((((Expression)localObject).getType() != 27) && (((Expression)localObject).getType() != 28))
    {
      this.compileContext.decrementDepth();
      throw unexpectedToken("TABLE");
    }
    readThis(772);
    localObject = new ExpressionTable(new Expression[] { localObject }, false);
    TableDerived localTableDerived = newSubQueryTable((Expression)localObject, 23);
    localTableDerived.setSQL(getLastPart(i));
    this.compileContext.decrementDepth();
    return localObject;
  }
  
  Expression XreadLateralDerivedTable()
  {
    readThis(149);
    readThis(786);
    TableDerived localTableDerived = XreadSubqueryTableBody(23);
    readThis(772);
    return new Expression(23, localTableDerived);
  }
  
  Expression XreadArrayConstructor()
  {
    readThis(786);
    TableDerived localTableDerived = XreadSubqueryTableBody(23);
    readThis(772);
    return new Expression(100, localTableDerived);
  }
  
  SchemaObject readCollateClauseOrNull()
  {
    if (this.token.tokenType == 41)
    {
      read();
      SchemaObject localSchemaObject = this.database.schemaManager.getSchemaObject(this.token.namePrefix, this.token.tokenString, 15);
      return localSchemaObject;
    }
    return null;
  }
  
  Expression XreadArrayElementReference(Expression paramExpression)
  {
    if (this.token.tokenType == 781)
    {
      read();
      Expression localExpression = XreadNumericValueExpression();
      readThis(790);
      paramExpression = new ExpressionAccessor(paramExpression, localExpression);
    }
    return paramExpression;
  }
  
  Expression readRow()
  {
    Object localObject = null;
    for (;;)
    {
      Expression localExpression = XreadValueExpressionWithContext();
      if (localObject == null) {
        localObject = localExpression;
      } else if (((Expression)localObject).getType() == 25)
      {
        if ((localExpression.getType() == 25) && (localObject.nodes[0].getType() != 25))
        {
          localObject = new Expression(25, new Expression[] { localObject, localExpression });
        }
        else
        {
          ((Expression)localObject).nodes = ((Expression[])ArrayUtil.resizeArray(((Expression)localObject).nodes, ((Expression)localObject).nodes.length + 1));
          ((Expression)localObject).nodes[(localObject.nodes.length - 1)] = localExpression;
        }
      }
      else {
        localObject = new Expression(25, new Expression[] { localObject, localExpression });
      }
      if (this.token.tokenType != 774) {
        break;
      }
      read();
    }
    return localObject;
  }
  
  Expression readCaseExpression()
  {
    Expression localExpression = null;
    read();
    if (this.token.tokenType != 314) {
      localExpression = XreadRowValuePredicand();
    }
    return readCaseWhen(localExpression);
  }
  
  private Expression readCaseWhen(Expression paramExpression)
  {
    readThis(314);
    Object localObject1 = null;
    if (paramExpression == null) {
      localObject1 = XreadBooleanValueExpression();
    } else {
      for (;;)
      {
        localObject2 = XreadPredicateRightPart(paramExpression);
        if (paramExpression == localObject2) {
          localObject2 = new ExpressionLogical(paramExpression, XreadRowValuePredicand());
        }
        if (localObject1 == null) {
          localObject1 = localObject2;
        } else {
          localObject1 = new ExpressionLogical(50, (Expression)localObject1, (Expression)localObject2);
        }
        if (this.token.tokenType != 774) {
          break;
        }
        read();
      }
    }
    readThis(280);
    Object localObject2 = XreadValueExpression();
    Object localObject3 = null;
    if (this.token.tokenType == 314)
    {
      localObject3 = readCaseWhen(paramExpression);
    }
    else if (this.token.tokenType == 92)
    {
      read();
      localObject3 = XreadValueExpression();
      readThis(94);
      readIfThis(29);
    }
    else
    {
      localObject3 = new ExpressionValue((Object)null, Type.SQL_ALL_TYPES);
      readThis(94);
      readIfThis(29);
    }
    ExpressionOp localExpressionOp1 = new ExpressionOp(96, (Expression)localObject2, (Expression)localObject3);
    ExpressionOp localExpressionOp2 = new ExpressionOp(93, (Expression)localObject1, localExpressionOp1);
    return localExpressionOp2;
  }
  
  private Expression readCaseWhenExpressionOrNull()
  {
    Object localObject = null;
    int i = getPosition();
    read();
    if (!readIfThis(786))
    {
      rewind(i);
      return null;
    }
    localObject = XreadBooleanValueExpression();
    readThis(774);
    Expression localExpression = XreadValueExpression();
    readThis(774);
    ExpressionOp localExpressionOp = new ExpressionOp(96, localExpression, XreadValueExpression());
    localObject = new ExpressionOp(93, (Expression)localObject, localExpressionOp);
    readThis(772);
    return localObject;
  }
  
  private Expression readCastExpressionOrNull()
  {
    int i = this.token.tokenType == 49 ? 1 : 0;
    int j = getPosition();
    read();
    Type localType;
    Object localObject;
    if (i != 0)
    {
      if (!readIfThis(786))
      {
        rewind(j);
        return null;
      }
      if (this.database.sqlSyntaxMss)
      {
        localType = readTypeDefinition(false, true);
        readThis(774);
        localObject = XreadValueExpressionOrNull();
      }
      else
      {
        localObject = XreadValueExpressionOrNull();
        readThis(774);
        localType = Type.getTypeForJDBCConvertToken(this.token.tokenType);
        if (localType == null) {
          localType = readTypeDefinition(false, true);
        } else {
          read();
        }
      }
    }
    else
    {
      readThis(786);
      localObject = XreadValueExpressionOrNull();
      readThis(10);
      localType = readTypeDefinition(false, true);
    }
    if (((Expression)localObject).isUnresolvedParam()) {
      ((Expression)localObject).setDataType(this.session, localType);
    } else {
      localObject = new ExpressionOp((Expression)localObject, localType);
    }
    readThis(772);
    return localObject;
  }
  
  private Expression readColumnOrFunctionExpression()
  {
    String str1 = this.token.tokenString;
    boolean bool = isDelimitedSimpleName();
    String str2 = this.token.namePrefix;
    String str3 = this.token.namePrePrefix;
    String str4 = this.token.namePrePrePrefix;
    Token localToken = getRecordedToken();
    checkIsIdentifier();
    if (isUndelimitedSimpleName())
    {
      int i = this.token.tokenType;
      localObject2 = FunctionCustom.newCustomFunction(this.token.tokenString, this.token.tokenType);
      if ((localObject2 != null) && (i == 736) && (!this.database.sqlSyntaxOra)) {
        localObject2 = null;
      }
      if (localObject2 != null)
      {
        int j = getPosition();
        try
        {
          Expression localExpression = readSQLFunction((FunctionSQL)localObject2);
          if (localExpression != null) {
            return localExpression;
          }
        }
        catch (HsqlException localHsqlException)
        {
          localHsqlException.setLevel(this.compileContext.subqueryDepth);
          if ((this.lastError == null) || (this.lastError.getLevel() < localHsqlException.getLevel())) {
            this.lastError = localHsqlException;
          }
          rewind(j);
        }
      }
      else if (isReservedKey())
      {
        localObject2 = FunctionSQL.newSQLFunction(str1, this.compileContext);
        if (localObject2 != null)
        {
          localObject3 = readSQLFunction((FunctionSQL)localObject2);
          if (localObject3 != null) {
            return localObject3;
          }
        }
      }
    }
    read();
    if (this.token.tokenType != 786)
    {
      checkValidCatalogName(str4);
      localObject1 = new ExpressionColumn(str3, str2, str1);
      return localObject1;
    }
    if (str4 != null) {
      throw Error.error(5551, str4);
    }
    checkValidCatalogName(str3);
    str2 = this.session.getSchemaName(str2);
    Object localObject1 = (RoutineSchema)this.database.schemaManager.findSchemaObject(str1, str2, 16);
    if ((localObject1 == null) && (bool))
    {
      localObject2 = this.database.schemaManager.getDefaultSchemaHsqlName();
      localObject1 = (RoutineSchema)this.database.schemaManager.findSchemaObject(str1, ((HsqlNameManager.HsqlName)localObject2).name, 16);
      if (localObject1 == null)
      {
        Routine.createRoutines(this.session, (HsqlNameManager.HsqlName)localObject2, str1);
        localObject1 = (RoutineSchema)this.database.schemaManager.findSchemaObject(str1, ((HsqlNameManager.HsqlName)localObject2).name, 16);
      }
    }
    if (localObject1 == null)
    {
      if (this.lastError != null) {
        throw this.lastError;
      }
      throw Error.error(5501, str1);
    }
    Object localObject2 = new HsqlArrayList();
    readThis(786);
    if (this.token.tokenType == 772) {
      read();
    } else {
      for (;;)
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
    }
    Object localObject3 = new FunctionSQLInvoked((RoutineSchema)localObject1);
    Expression[] arrayOfExpression = new Expression[((HsqlArrayList)localObject2).size()];
    ((HsqlArrayList)localObject2).toArray(arrayOfExpression);
    ((FunctionSQLInvoked)localObject3).setArguments(arrayOfExpression);
    this.compileContext.addFunctionCall((FunctionSQLInvoked)localObject3);
    localToken.setExpression(localObject1);
    return localObject3;
  }
  
  Expression readCollection(int paramInt)
  {
    read();
    if (this.token.tokenType == 786) {
      return XreadArrayConstructor();
    }
    readThis(781);
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    for (int i = 0;; i++)
    {
      if (this.token.tokenType == 790)
      {
        read();
        break;
      }
      if (i > 0) {
        readThis(774);
      }
      Expression localExpression = XreadValueExpressionOrNull();
      localHsqlArrayList.add(localExpression);
    }
    Expression[] arrayOfExpression = new Expression[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfExpression);
    return new Expression(19, arrayOfExpression);
  }
  
  private Expression readDecodeExpressionOrNull()
  {
    int i = getPosition();
    read();
    if (!readIfThis(786))
    {
      rewind(i);
      return null;
    }
    Object localObject1 = null;
    Object localObject2 = null;
    Expression localExpression1 = XreadValueExpression();
    readThis(774);
    for (;;)
    {
      Expression localExpression2 = XreadValueExpression();
      if (this.token.tokenType == 774)
      {
        readThis(774);
      }
      else
      {
        if (localObject2 == null) {
          throw unexpectedToken();
        }
        localObject2.setRightNode(localExpression2);
        break;
      }
      ExpressionLogical localExpressionLogical = new ExpressionLogical(58, localExpression1, localExpression2);
      Expression localExpression3 = XreadValueExpression();
      ExpressionOp localExpressionOp1 = new ExpressionOp(96, localExpression3, null);
      ExpressionOp localExpressionOp2 = new ExpressionOp(93, localExpressionLogical, localExpressionOp1);
      if (localObject1 == null) {
        localObject1 = localExpressionOp2;
      } else {
        localObject2.setRightNode(localExpressionOp2);
      }
      localObject2 = localExpressionOp1;
      if (this.token.tokenType == 774)
      {
        readThis(774);
      }
      else
      {
        localObject2.setRightNode(new ExpressionValue(null, null));
        break;
      }
    }
    readThis(772);
    return localObject1;
  }
  
  private Expression readConcatExpressionOrNull()
  {
    int i = getPosition();
    read();
    if (!readIfThis(786))
    {
      rewind(i);
      return null;
    }
    Object localObject = XreadValueExpression();
    readThis(774);
    do
    {
      for (;;)
      {
        Expression localExpression = XreadValueExpression();
        localObject = new ExpressionArithmetic(36, (Expression)localObject, localExpression);
        if (this.token.tokenType != 774) {
          break;
        }
        readThis(774);
      }
    } while (this.token.tokenType != 772);
    readThis(772);
    return localObject;
  }
  
  private Expression readLeastExpressionOrNull()
  {
    int i = getPosition();
    read();
    if (!readIfThis(786))
    {
      rewind(i);
      return null;
    }
    Expression localExpression = null;
    for (;;)
    {
      localExpression = readValue(localExpression, 44);
      if (this.token.tokenType != 774) {
        break;
      }
      readThis(774);
    }
    readThis(772);
    return localExpression;
  }
  
  private Expression readGreatestExpressionOrNull()
  {
    int i = getPosition();
    read();
    if (!readIfThis(786))
    {
      rewind(i);
      return null;
    }
    Expression localExpression = null;
    for (;;)
    {
      localExpression = readValue(localExpression, 43);
      if (this.token.tokenType != 774) {
        break;
      }
      readThis(774);
    }
    readThis(772);
    return localExpression;
  }
  
  private Expression readValue(Expression paramExpression, int paramInt)
  {
    Expression localExpression = XreadValueExpression();
    if (paramExpression == null) {
      return localExpression;
    }
    ExpressionLogical localExpressionLogical = new ExpressionLogical(paramInt, paramExpression, localExpression);
    ExpressionOp localExpressionOp = new ExpressionOp(96, paramExpression, localExpression);
    return new ExpressionOp(93, localExpressionLogical, localExpressionOp);
  }
  
  private Expression readNullIfExpression()
  {
    read();
    readThis(786);
    Object localObject = XreadValueExpression();
    readThis(774);
    ExpressionOp localExpressionOp = new ExpressionOp(96, new ExpressionValue((Object)null, (Type)null), (Expression)localObject);
    localObject = new ExpressionLogical((Expression)localObject, XreadValueExpression());
    localObject = new ExpressionOp(93, (Expression)localObject, localExpressionOp);
    readThis(772);
    return localObject;
  }
  
  private Expression readIfNullExpressionOrNull()
  {
    int i = getPosition();
    read();
    if (!readIfThis(786))
    {
      rewind(i);
      return null;
    }
    Object localObject = XreadValueExpression();
    readThis(774);
    Expression localExpression = XreadValueExpression();
    ExpressionLogical localExpressionLogical = new ExpressionLogical(47, (Expression)localObject);
    ExpressionOp localExpressionOp = new ExpressionOp(96, localExpression, (Expression)localObject);
    localObject = new ExpressionOp(93, localExpressionLogical, localExpressionOp);
    ((Expression)localObject).setSubType(91);
    readThis(772);
    return localObject;
  }
  
  private Expression readIfNull2ExpressionOrNull()
  {
    int i = getPosition();
    read();
    if (!readIfThis(786))
    {
      rewind(i);
      return null;
    }
    Object localObject = XreadValueExpression();
    readThis(774);
    Expression localExpression1 = XreadValueExpression();
    readThis(774);
    Expression localExpression2 = XreadValueExpression();
    ExpressionLogical localExpressionLogical = new ExpressionLogical(47, (Expression)localObject);
    ExpressionOp localExpressionOp = new ExpressionOp(96, localExpression2, localExpression1);
    localObject = new ExpressionOp(93, localExpressionLogical, localExpressionOp);
    ((Expression)localObject).setSubType(91);
    readThis(772);
    return localObject;
  }
  
  private Expression readCoalesceExpression()
  {
    Object localObject1 = null;
    read();
    readThis(786);
    Object localObject2 = null;
    for (;;)
    {
      Expression localExpression = XreadValueExpression();
      if ((localObject2 != null) && (this.token.tokenType == 772))
      {
        readThis(772);
        localObject2.setLeftNode(localExpression);
        break;
      }
      ExpressionLogical localExpressionLogical = new ExpressionLogical(47, localExpression);
      ExpressionOp localExpressionOp1 = new ExpressionOp(96, new ExpressionValue((Object)null, (Type)null), localExpression);
      ExpressionOp localExpressionOp2 = new ExpressionOp(93, localExpressionLogical, localExpressionOp1);
      if (localObject1 == null) {
        localObject1 = localExpressionOp2;
      } else {
        localObject2.setLeftNode(localExpressionOp2);
      }
      localObject2 = localExpressionOp1;
      readThis(774);
    }
    return localObject1;
  }
  
  Expression readSQLFunction(FunctionSQL paramFunctionSQL)
  {
    int i = getPosition();
    read();
    short[] arrayOfShort = paramFunctionSQL.parseList;
    if (arrayOfShort.length == 0) {
      return paramFunctionSQL;
    }
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    int j = this.token.tokenType == 786 ? 1 : 0;
    try
    {
      readExpression(localHsqlArrayList, arrayOfShort, 0, arrayOfShort.length, false);
      this.lastError = null;
    }
    catch (HsqlException localHsqlException)
    {
      if (j == 0)
      {
        rewind(i);
        return null;
      }
      if (paramFunctionSQL.parseListAlt == null) {
        throw localHsqlException;
      }
      rewind(i);
      read();
      arrayOfShort = paramFunctionSQL.parseListAlt;
      localHsqlArrayList = new HsqlArrayList();
      readExpression(localHsqlArrayList, arrayOfShort, 0, arrayOfShort.length, false);
      this.lastError = null;
    }
    Expression[] arrayOfExpression = new Expression[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfExpression);
    paramFunctionSQL.setArguments(arrayOfExpression);
    return paramFunctionSQL.getFunctionExpression();
  }
  
  void readExpression(HsqlArrayList paramHsqlArrayList, short[] paramArrayOfShort, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
    {
      int j = paramArrayOfShort[i];
      Object localObject;
      int k;
      int m;
      int n;
      switch (j)
      {
      case 788: 
        localObject = null;
        localObject = XreadAllTypesCommonValueExpression(false);
        paramHsqlArrayList.add(localObject);
        break;
      case 844: 
        localObject = null;
        Integer localInteger = readIntegerObject();
        if (localInteger.intValue() < 0) {
          throw Error.error(5592);
        }
        localObject = new ExpressionValue(localInteger, Type.SQL_INTEGER);
        paramHsqlArrayList.add(localObject);
        break;
      case 842: 
        i++;
        k = paramHsqlArrayList.size();
        m = getPosition();
        n = paramArrayOfShort[(i++)];
        int i1 = paramHsqlArrayList.size();
        try
        {
          readExpression(paramHsqlArrayList, paramArrayOfShort, i, n, true);
        }
        catch (HsqlException localHsqlException)
        {
          localHsqlException.setLevel(this.compileContext.subqueryDepth);
          if ((this.lastError == null) || (this.lastError.getLevel() < localHsqlException.getLevel())) {
            this.lastError = localHsqlException;
          }
          rewind(m);
          paramHsqlArrayList.setSize(k);
          for (int i2 = i; i2 < i + n; i2++) {
            if ((paramArrayOfShort[i2] == 788) || (paramArrayOfShort[i2] == 841) || (paramArrayOfShort[i2] == 844)) {
              paramHsqlArrayList.add(null);
            }
          }
          i += n - 1;
          continue;
        }
        if (i1 == paramHsqlArrayList.size()) {
          paramHsqlArrayList.add(null);
        }
        i += n - 1;
        break;
      case 843: 
        i++;
        k = paramArrayOfShort[(i++)];
        m = i;
        for (;;)
        {
          n = paramHsqlArrayList.size();
          readExpression(paramHsqlArrayList, paramArrayOfShort, m, k, true);
          if (paramHsqlArrayList.size() == n) {
            break;
          }
        }
        i += k - 1;
        break;
      case 841: 
        k = paramArrayOfShort[(++i)];
        ExpressionValue localExpressionValue = null;
        if (ArrayUtil.find(paramArrayOfShort, this.token.tokenType, i + 1, k) == -1)
        {
          if (!paramBoolean) {
            throw unexpectedToken();
          }
        }
        else
        {
          localExpressionValue = new ExpressionValue(ValuePool.getInt(this.token.tokenType), Type.SQL_INTEGER);
          read();
        }
        paramHsqlArrayList.add(localExpressionValue);
        i += k;
        break;
      case 772: 
      case 774: 
      case 786: 
      default: 
        if (this.token.tokenType != j) {
          throw unexpectedToken();
        }
        read();
      }
    }
  }
  
  private Expression readSequenceExpressionOrNull(int paramInt)
  {
    int i = getPosition();
    read();
    if (this.token.tokenType != 307)
    {
      rewind(i);
      return null;
    }
    readThis(307);
    readThis(112);
    checkIsSchemaObjectName();
    String str = this.session.getSchemaName(this.token.namePrefix);
    NumberSequence localNumberSequence = this.database.schemaManager.getSequence(this.token.tokenString, str, true);
    Token localToken = getRecordedToken();
    read();
    ExpressionColumn localExpressionColumn = new ExpressionColumn(localNumberSequence, paramInt);
    localToken.setExpression(localNumberSequence);
    this.compileContext.addSequence(localNumberSequence);
    return localExpressionColumn;
  }
  
  HsqlNameManager.SimpleName readSimpleName()
  {
    checkIsSimpleName();
    HsqlNameManager.SimpleName localSimpleName = HsqlNameManager.getSimpleName(this.token.tokenString, isDelimitedIdentifier());
    read();
    return localSimpleName;
  }
  
  HsqlNameManager.HsqlName readNewSchemaName()
  {
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(2, false);
    SqlInvariants.checkSchemaNameNotSystem(localHsqlName.name);
    return localHsqlName;
  }
  
  HsqlNameManager.HsqlName readNewSchemaObjectName(int paramInt, boolean paramBoolean)
  {
    checkIsSchemaObjectName();
    HsqlNameManager.HsqlName localHsqlName1 = this.database.nameManager.newHsqlName(this.token.tokenString, isDelimitedIdentifier(), paramInt);
    if (this.token.namePrefix != null) {
      switch (paramInt)
      {
      case 1: 
      case 11: 
      case 21: 
      case 22: 
        throw unexpectedToken();
      case 19: 
        if ((this.token.namePrePrefix != null) || (this.token.isDelimitedPrefix) || (!"MODULE".equals(this.token.namePrefix))) {
          throw unexpectedTokenRequire("MODULE");
        }
        break;
      case 2: 
        checkValidCatalogName(this.token.namePrefix);
        if (this.token.namePrePrefix != null) {
          throw tooManyIdentifiers();
        }
        break;
      case 25: 
      case 26: 
        checkValidCatalogName(this.token.namePrefix);
        if (this.token.namePrePrefix != null) {
          throw tooManyIdentifiers();
        }
        break;
      case 9: 
        if (this.token.namePrefix != null) {
          throw tooManyIdentifiers();
        }
        break;
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
      case 8: 
      case 10: 
      case 12: 
      case 13: 
      case 14: 
      case 15: 
      case 16: 
      case 17: 
      case 18: 
      case 20: 
      case 23: 
      case 24: 
      default: 
        checkValidCatalogName(this.token.namePrePrefix);
        HsqlNameManager.HsqlName localHsqlName2;
        if (paramBoolean)
        {
          localHsqlName2 = this.session.getSchemaHsqlName(this.token.namePrefix);
        }
        else
        {
          localHsqlName2 = this.session.database.schemaManager.findSchemaHsqlName(this.token.namePrefix);
          if (localHsqlName2 == null) {
            localHsqlName2 = this.database.nameManager.newHsqlName(this.token.namePrefix, isDelimitedIdentifier(), 2);
          }
        }
        localHsqlName1.setSchemaIfNull(localHsqlName2);
        break;
      }
    }
    read();
    return localHsqlName1;
  }
  
  HsqlNameManager.HsqlName readNewDependentSchemaObjectName(HsqlNameManager.HsqlName paramHsqlName, int paramInt)
  {
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(paramInt, true);
    localHsqlName.parent = paramHsqlName;
    localHsqlName.setSchemaIfNull(paramHsqlName.schema);
    if ((localHsqlName.schema != null) && (paramHsqlName.schema != null) && (localHsqlName.schema != paramHsqlName.schema)) {
      throw Error.error(5505, this.token.namePrefix);
    }
    return localHsqlName;
  }
  
  HsqlNameManager.HsqlName readSchemaName()
  {
    checkIsSchemaObjectName();
    checkValidCatalogName(this.token.namePrefix);
    HsqlNameManager.HsqlName localHsqlName = this.session.getSchemaHsqlName(this.token.tokenString);
    read();
    return localHsqlName;
  }
  
  SchemaObject readSchemaObjectName(int paramInt)
  {
    checkIsSchemaObjectName();
    checkValidCatalogName(this.token.namePrePrefix);
    String str = this.session.getSchemaName(this.token.namePrefix);
    SchemaObject localSchemaObject = this.database.schemaManager.getSchemaObject(this.token.tokenString, str, paramInt);
    read();
    return localSchemaObject;
  }
  
  SchemaObject readSchemaObjectName(HsqlNameManager.HsqlName paramHsqlName, int paramInt)
  {
    checkIsSchemaObjectName();
    SchemaObject localSchemaObject = this.database.schemaManager.getSchemaObject(this.token.tokenString, paramHsqlName.name, paramInt);
    if (this.token.namePrefix != null)
    {
      if (!this.token.namePrefix.equals(paramHsqlName.name)) {
        throw Error.error(5505, this.token.namePrefix);
      }
      if ((this.token.namePrePrefix != null) && (!this.token.namePrePrefix.equals(this.database.getCatalogName().name))) {
        throw Error.error(5505, this.token.namePrefix);
      }
    }
    read();
    return localSchemaObject;
  }
  
  Table readTableName()
  {
    checkIsIdentifier();
    if (this.token.namePrePrefix != null) {
      checkValidCatalogName(this.token.namePrePrefix);
    }
    Table localTable = this.database.schemaManager.getTable(this.session, this.token.tokenString, this.token.namePrefix);
    getRecordedToken().setExpression(localTable);
    read();
    return localTable;
  }
  
  ColumnSchema readSimpleColumnName(RangeVariable paramRangeVariable, boolean paramBoolean)
  {
    ColumnSchema localColumnSchema = null;
    checkIsIdentifier();
    if ((!paramBoolean) && (this.token.namePrefix != null)) {
      throw tooManyIdentifiers();
    }
    int i = paramRangeVariable.findColumn(this.token.namePrePrefix, this.token.namePrefix, this.token.tokenString);
    if (i == -1) {
      throw Error.error(5501, this.token.tokenString);
    }
    localColumnSchema = paramRangeVariable.getTable().getColumn(i);
    read();
    return localColumnSchema;
  }
  
  ColumnSchema readSimpleColumnName(Table paramTable, boolean paramBoolean)
  {
    checkIsIdentifier();
    if (paramBoolean)
    {
      if ((this.token.namePrefix != null) && (!paramTable.getName().name.equals(this.token.namePrefix))) {
        throw Error.error(5501, this.token.namePrefix);
      }
    }
    else if (this.token.namePrefix != null) {
      throw tooManyIdentifiers();
    }
    int i = paramTable.findColumn(this.token.tokenString);
    if (i == -1) {
      throw Error.error(5501, this.token.tokenString);
    }
    ColumnSchema localColumnSchema = paramTable.getColumn(i);
    read();
    return localColumnSchema;
  }
  
  StatementQuery compileDeclareCursor(RangeGroup[] paramArrayOfRangeGroup, boolean paramBoolean)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = super.getPosition();
    readThis(77);
    HsqlNameManager.SimpleName localSimpleName = readSimpleName();
    switch (this.token.tokenType)
    {
    case 252: 
      read();
      i = 2;
      break;
    case 134: 
      read();
      i = 1;
      break;
    case 11: 
      read();
    }
    if (this.token.tokenType == 180)
    {
      readThis(248);
    }
    else if (this.token.tokenType == 248)
    {
      read();
      j = 1;
    }
    if (this.token.tokenType != 70)
    {
      rewind(n);
      return null;
    }
    readThis(70);
    for (int i1 = 0; i1 < 2; i1++) {
      if (this.token.tokenType == 319)
      {
        read();
        if ((i1 == 0) && (this.token.tokenType == 126))
        {
          read();
          k = 1;
        }
        else
        {
          readThis(237);
          i1++;
          m = 1;
        }
      }
      else if (this.token.tokenType == 321)
      {
        read();
        if ((i1 == 0) && (this.token.tokenType == 126))
        {
          read();
        }
        else
        {
          readThis(237);
          i1++;
        }
      }
    }
    readThis(112);
    i1 = ResultProperties.getProperties(i, 1, j, k, m);
    StatementQuery localStatementQuery = compileCursorSpecification(paramArrayOfRangeGroup, i1, paramBoolean);
    localStatementQuery.setCursorName(localSimpleName);
    return localStatementQuery;
  }
  
  StatementQuery compileCursorSpecification(RangeGroup[] paramArrayOfRangeGroup, int paramInt, boolean paramBoolean)
  {
    OrderedHashSet localOrderedHashSet = null;
    QueryExpression localQueryExpression = XreadQueryExpression();
    if (this.token.tokenType == 112)
    {
      read();
      if ((this.token.tokenType == 480) || (this.token.tokenType == 107))
      {
        read();
        readThis(195);
        paramInt = ResultProperties.addUpdatable(paramInt, false);
      }
      else
      {
        readThis(303);
        paramInt = ResultProperties.addUpdatable(paramInt, true);
        if (this.token.tokenType == 191)
        {
          readThis(191);
          localOrderedHashSet = new OrderedHashSet();
          readColumnNameList(localOrderedHashSet, null, false);
        }
      }
    }
    if (ResultProperties.isUpdatable(paramInt)) {
      localQueryExpression.isUpdatable = true;
    }
    localQueryExpression.setReturningResult();
    localQueryExpression.resolve(this.session, paramArrayOfRangeGroup, null);
    StatementQuery localStatementQuery = paramBoolean ? new StatementCursor(this.session, localQueryExpression, this.compileContext) : new StatementQuery(this.session, localQueryExpression, this.compileContext);
    return localStatementQuery;
  }
  
  StatementDMQL compileShortCursorSpecification(int paramInt)
  {
    QuerySpecification localQuerySpecification = XreadSimpleTable();
    if (ResultProperties.isUpdatable(paramInt)) {
      localQuerySpecification.isUpdatable = true;
    }
    localQuerySpecification.setReturningResult();
    localQuerySpecification.resolve(this.session);
    StatementQuery localStatementQuery = new StatementQuery(this.session, localQuerySpecification, this.compileContext);
    return localStatementQuery;
  }
  
  int readCloseBrackets(int paramInt)
  {
    for (int i = 0; (i < paramInt) && (this.token.tokenType == 772); i++) {
      read();
    }
    return i;
  }
  
  int readOpenBrackets()
  {
    int i = 0;
    while (this.token.tokenType == 786)
    {
      i++;
      read();
    }
    return i;
  }
  
  void checkValidCatalogName(String paramString)
  {
    if ((paramString != null) && (!paramString.equals(this.database.getCatalogName().name))) {
      throw Error.error(5501, paramString);
    }
  }
  
  void rewind(int paramInt)
  {
    super.rewind(paramInt);
    this.compileContext.rewind(paramInt);
  }
  
  public static final class CompileContext
  {
    final Session session;
    final ParserBase parser;
    private int subqueryDepth;
    private int maxSubqueryDepth;
    private HsqlArrayList namedSubqueries;
    private OrderedIntKeyHashMap parameters = new OrderedIntKeyHashMap();
    private HsqlArrayList usedSequences = new HsqlArrayList(8, true);
    private HsqlArrayList usedRoutines = new HsqlArrayList(8, true);
    private HsqlArrayList rangeVariables = new HsqlArrayList(8, true);
    private HsqlArrayList usedObjects = new HsqlArrayList(8, true);
    Type currentDomain;
    boolean contextuallyTypedExpression;
    Routine callProcedure;
    private RangeGroup[] outerRangeGroups;
    private int rangeVarIndex = 0;
    
    public CompileContext(Session paramSession, ParserBase paramParserBase)
    {
      this.session = paramSession;
      this.parser = paramParserBase;
      reset();
    }
    
    public void reset()
    {
      reset(1);
    }
    
    public void reset(int paramInt)
    {
      this.rangeVarIndex = paramInt;
      this.rangeVariables.clear();
      this.subqueryDepth = 0;
      this.maxSubqueryDepth = 0;
      this.parameters.clear();
      this.usedSequences.clear();
      this.usedRoutines.clear();
      this.callProcedure = null;
      this.usedObjects.clear();
      this.outerRangeGroups = RangeGroup.emptyArray;
      this.currentDomain = null;
      this.contextuallyTypedExpression = false;
    }
    
    public int getDepth()
    {
      return this.subqueryDepth;
    }
    
    public int getMaxDepth()
    {
      return this.maxSubqueryDepth;
    }
    
    public void incrementDepth()
    {
      this.subqueryDepth += 1;
      this.maxSubqueryDepth = this.subqueryDepth;
    }
    
    public void decrementDepth()
    {
      this.subqueryDepth -= 1;
    }
    
    public void rewind(int paramInt)
    {
      for (int i = this.rangeVariables.size() - 1; i >= 0; i--)
      {
        RangeVariable localRangeVariable = (RangeVariable)this.rangeVariables.get(i);
        if (localRangeVariable.parsePosition > paramInt) {
          this.rangeVariables.remove(i);
        }
      }
      Iterator localIterator = this.parameters.keySet().iterator();
      while (localIterator.hasNext())
      {
        int j = localIterator.nextInt();
        if (j >= paramInt) {
          localIterator.remove();
        }
      }
    }
    
    public void registerRangeVariable(RangeVariable paramRangeVariable)
    {
      paramRangeVariable.parsePosition = (this.parser == null ? 0 : this.parser.getPosition());
      paramRangeVariable.rangePosition = getNextRangeVarIndex();
      paramRangeVariable.level = this.subqueryDepth;
      this.rangeVariables.add(paramRangeVariable);
    }
    
    public void setNextRangeVarIndex(int paramInt)
    {
      this.rangeVarIndex = paramInt;
    }
    
    public int getNextRangeVarIndex()
    {
      return this.rangeVarIndex++;
    }
    
    public int getRangeVarCount()
    {
      return this.rangeVarIndex;
    }
    
    public RangeVariable[] getAllRangeVariables()
    {
      RangeVariable[] arrayOfRangeVariable = new RangeVariable[this.rangeVariables.size()];
      this.rangeVariables.toArray(arrayOfRangeVariable);
      return arrayOfRangeVariable;
    }
    
    public RangeGroup[] getOuterRanges()
    {
      return this.outerRangeGroups;
    }
    
    public void setOuterRanges(RangeGroup[] paramArrayOfRangeGroup)
    {
      this.outerRangeGroups = paramArrayOfRangeGroup;
    }
    
    public NumberSequence[] getSequences()
    {
      if (this.usedSequences.size() == 0) {
        return NumberSequence.emptyArray;
      }
      NumberSequence[] arrayOfNumberSequence = new NumberSequence[this.usedSequences.size()];
      this.usedSequences.toArray(arrayOfNumberSequence);
      return arrayOfNumberSequence;
    }
    
    public Routine[] getRoutines()
    {
      if ((this.callProcedure == null) && (this.usedRoutines.size() == 0)) {
        return Routine.emptyArray;
      }
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      for (int i = 0; i < this.usedRoutines.size(); i++)
      {
        FunctionSQLInvoked localFunctionSQLInvoked = (FunctionSQLInvoked)this.usedRoutines.get(i);
        localOrderedHashSet.add(localFunctionSQLInvoked.routine);
      }
      if (this.callProcedure != null) {
        localOrderedHashSet.add(this.callProcedure);
      }
      Routine[] arrayOfRoutine = new Routine[localOrderedHashSet.size()];
      localOrderedHashSet.toArray(arrayOfRoutine);
      return arrayOfRoutine;
    }
    
    private void initSubqueryNames()
    {
      if (this.namedSubqueries == null) {
        this.namedSubqueries = new HsqlArrayList();
      }
      if (this.namedSubqueries.size() <= this.subqueryDepth) {
        this.namedSubqueries.setSize(this.subqueryDepth + 1);
      }
      HashMappedList localHashMappedList = (HashMappedList)this.namedSubqueries.get(this.subqueryDepth);
      if (localHashMappedList == null)
      {
        localHashMappedList = new HashMappedList();
        this.namedSubqueries.set(this.subqueryDepth, localHashMappedList);
      }
      else
      {
        localHashMappedList.clear();
      }
    }
    
    private void registerSubquery(String paramString, TableDerived paramTableDerived)
    {
      HashMappedList localHashMappedList = (HashMappedList)this.namedSubqueries.get(this.subqueryDepth);
      boolean bool = localHashMappedList.add(paramString, paramTableDerived);
      if (!bool) {
        throw Error.error(5504);
      }
    }
    
    private void unregisterSubqueries()
    {
      if (this.namedSubqueries == null) {
        return;
      }
      for (int i = this.subqueryDepth; i < this.namedSubqueries.size(); i++) {
        this.namedSubqueries.set(i, null);
      }
    }
    
    private TableDerived getNamedSubQuery(String paramString)
    {
      if (this.namedSubqueries == null) {
        return null;
      }
      for (int i = this.subqueryDepth; i >= 0; i--) {
        if (this.namedSubqueries.size() > i)
        {
          HashMappedList localHashMappedList = (HashMappedList)this.namedSubqueries.get(i);
          if (localHashMappedList != null)
          {
            TableDerived localTableDerived = (TableDerived)localHashMappedList.get(paramString);
            if (localTableDerived != null) {
              return localTableDerived;
            }
          }
        }
      }
      return null;
    }
    
    private void addParameter(ExpressionColumn paramExpressionColumn, int paramInt)
    {
      paramExpressionColumn.parameterIndex = this.parameters.size();
      this.parameters.put(paramInt, paramExpressionColumn);
    }
    
    private void addSchemaObject(SchemaObject paramSchemaObject)
    {
      this.usedObjects.add(paramSchemaObject);
    }
    
    private void addSequence(SchemaObject paramSchemaObject)
    {
      this.usedSequences.add(paramSchemaObject);
    }
    
    void addFunctionCall(FunctionSQLInvoked paramFunctionSQLInvoked)
    {
      this.usedRoutines.add(paramFunctionSQLInvoked);
    }
    
    void addProcedureCall(Routine paramRoutine)
    {
      this.callProcedure = paramRoutine;
    }
    
    ExpressionColumn[] getParameters()
    {
      if (this.parameters.size() == 0) {
        return ExpressionColumn.emptyArray;
      }
      ExpressionColumn[] arrayOfExpressionColumn = new ExpressionColumn[this.parameters.size()];
      this.parameters.valuesToArray(arrayOfExpressionColumn);
      this.parameters.clear();
      return arrayOfExpressionColumn;
    }
    
    void clearParameters()
    {
      this.parameters.clear();
    }
    
    public OrderedHashSet getSchemaObjectNames()
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      Object localObject;
      for (int i = 0; i < this.usedSequences.size(); i++)
      {
        localObject = (SchemaObject)this.usedSequences.get(i);
        localOrderedHashSet.add(((SchemaObject)localObject).getName());
      }
      for (i = 0; i < this.usedObjects.size(); i++)
      {
        localObject = (SchemaObject)this.usedObjects.get(i);
        localOrderedHashSet.add(((SchemaObject)localObject).getName());
      }
      for (i = 0; i < this.rangeVariables.size(); i++)
      {
        localObject = (RangeVariable)this.rangeVariables.get(i);
        HsqlNameManager.HsqlName localHsqlName = ((RangeVariable)localObject).rangeTable.getName();
        if (localHsqlName.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME)
        {
          localOrderedHashSet.add(((RangeVariable)localObject).rangeTable.getName());
          localOrderedHashSet.addAll(((RangeVariable)localObject).getColumnNames());
        }
        else if (localHsqlName.type == 10)
        {
          localOrderedHashSet.addAll(((RangeVariable)localObject).getColumnNames());
        }
      }
      Routine[] arrayOfRoutine = getRoutines();
      for (int j = 0; j < arrayOfRoutine.length; j++) {
        localOrderedHashSet.add(arrayOfRoutine[j].getSpecificName());
      }
      return localOrderedHashSet;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ParserDQL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */