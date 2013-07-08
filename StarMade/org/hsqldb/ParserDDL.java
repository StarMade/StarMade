package org.hsqldb;

import java.lang.reflect.Method;
import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.Right;
import org.hsqldb.rights.User;
import org.hsqldb.rights.UserManager;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;
import org.hsqldb.types.UserTypeModifier;

public class ParserDDL
  extends ParserRoutine
{
  static final int[] schemaCommands = { 55, 121 };
  static final short[] startStatementTokens = { 55, 121, 4, 88 };
  static final short[] startStatementTokensSchema = { 55, 121 };
  
  ParserDDL(Session paramSession, Scanner paramScanner)
  {
    super(paramSession, paramScanner);
  }
  
  void reset(String paramString)
  {
    super.reset(paramString);
  }
  
  StatementSchema compileCreate()
  {
    int i = 4;
    int j = 0;
    boolean bool = false;
    read();
    switch (this.token.tokenType)
    {
    case 120: 
      read();
      readThis(523);
      readIfThis(607);
      readThis(278);
      j = 1;
      i = 3;
      break;
    case 630: 
      read();
      readThis(278);
      j = 1;
      i = 3;
      break;
    case 523: 
      read();
      readThis(278);
      j = 1;
      i = 3;
      break;
    case 607: 
      read();
      readThis(278);
      j = 1;
      break;
    case 570: 
      read();
      readThis(278);
      j = 1;
      i = 5;
      break;
    case 631: 
      read();
      readThis(278);
      j = 1;
      i = 7;
      break;
    case 278: 
      read();
      j = 1;
      i = this.database.schemaManager.getDefaultTableType();
      break;
    case 197: 
      if (this.database.sqlSyntaxOra)
      {
        read();
        readThis(718);
        switch (this.token.tokenType)
        {
        case 117: 
        case 215: 
        case 291: 
        case 535: 
        case 545: 
          break;
        default: 
          throw unexpectedToken("OR");
        }
        bool = true;
      }
      break;
    }
    if (j != 0) {
      return compileCreateTable(i);
    }
    switch (this.token.tokenType)
    {
    case 558: 
      return compileCreateAlias();
    case 505: 
      return compileCreateSequence();
    case 497: 
      return compileCreateSchema();
    case 291: 
      return compileCreateTrigger(bool);
    case 305: 
      return compileCreateUser();
    case 490: 
      return compileCreateRole();
    case 545: 
      return compileCreateView(false, bool);
    case 393: 
      return compileCreateDomain();
    case 535: 
      return compileCreateType(bool);
    case 35: 
      return compileCreateCharacterSet();
    case 358: 
      return compileCreateCollation();
    case 299: 
      read();
      checkIsThis(597);
      return compileCreateIndex(true);
    case 597: 
      return compileCreateIndex(false);
    case 117: 
    case 215: 
    case 559: 
      return compileCreateProcedureOrFunction(bool);
    }
    throw unexpectedToken();
  }
  
  Statement compileAlter()
  {
    read();
    Object localObject;
    switch (this.token.tokenType)
    {
    case 597: 
      read();
      localObject = readNewSchemaObjectName(20, true);
      ((HsqlNameManager.HsqlName)localObject).setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
      if (this.token.tokenType == 622)
      {
        read();
        readThis(285);
        return compileRenameObject((HsqlNameManager.HsqlName)localObject, 20);
      }
      readThis(10);
      Index localIndex = (Index)this.database.schemaManager.getSchemaObject((HsqlNameManager.HsqlName)localObject);
      if (localIndex == null) {
        throw Error.error(5501);
      }
      Table localTable = (Table)this.database.schemaManager.getSchemaObject(localIndex.getName().parent);
      int[] arrayOfInt = readColumnList(localTable, true);
      String str = getLastPart();
      Object[] arrayOfObject = { localTable, arrayOfInt, localIndex.getName() };
      HsqlNameManager.HsqlName[] arrayOfHsqlName = { this.database.getCatalogName(), localTable.getName() };
      return new StatementSchema(str, 1069, arrayOfObject, null, arrayOfHsqlName);
    case 497: 
      read();
      localObject = readSchemaName();
      readThis(622);
      readThis(285);
      return compileRenameObject((HsqlNameManager.HsqlName)localObject, 2);
    case 348: 
      read();
      checkIsSimpleName();
      localObject = this.token.tokenString;
      checkValidCatalogName((String)localObject);
      read();
      readThis(622);
      readThis(285);
      return compileRenameObject(this.database.getCatalogName(), 1);
    case 505: 
      return compileAlterSequence();
    case 278: 
      return compileAlterTable();
    case 305: 
      return compileAlterUser();
    case 393: 
      return compileAlterDomain();
    case 545: 
      return compileCreateView(true, false);
    case 508: 
      return compileAlterSession();
    case 259: 
      return compileAlterSpecificRoutine();
    case 491: 
      return compileAlterRoutine();
    }
    throw unexpectedToken();
  }
  
  Statement compileAlterRoutine()
  {
    readThis(491);
    RoutineSchema localRoutineSchema = (RoutineSchema)readSchemaObjectName(18);
    readThis(622);
    readThis(285);
    return compileRenameObject(localRoutineSchema.getName(), localRoutineSchema.getName().type);
  }
  
  Statement compileDrop()
  {
    int m = 0;
    boolean bool1 = false;
    int n = 0;
    boolean bool2 = false;
    read();
    int i = this.token.tokenType;
    int k;
    int j;
    switch (i)
    {
    case 597: 
      read();
      k = 1077;
      j = 20;
      n = 1;
      break;
    case 339: 
      read();
      k = 24;
      j = 6;
      m = 1;
      break;
    case 259: 
      read();
      switch (this.token.tokenType)
      {
      case 117: 
      case 215: 
      case 491: 
        read();
        break;
      default: 
        throw unexpectedToken();
      }
      k = 30;
      j = 24;
      m = 1;
      n = 1;
      break;
    case 215: 
      read();
      k = 30;
      j = 17;
      m = 1;
      n = 1;
      break;
    case 117: 
      read();
      k = 30;
      j = 16;
      m = 1;
      n = 1;
      break;
    case 497: 
      read();
      k = 31;
      j = 2;
      m = 1;
      n = 1;
      break;
    case 505: 
      read();
      k = 135;
      j = 7;
      m = 1;
      n = 1;
      break;
    case 291: 
      read();
      k = 34;
      j = 8;
      m = 0;
      n = 1;
      break;
    case 305: 
      read();
      k = 1079;
      j = 11;
      m = 1;
      break;
    case 490: 
      read();
      k = 29;
      j = 11;
      m = 1;
      break;
    case 393: 
      read();
      k = 27;
      j = 13;
      m = 1;
      n = 1;
      break;
    case 535: 
      read();
      k = 35;
      j = 12;
      m = 1;
      n = 1;
      break;
    case 35: 
      read();
      readThis(254);
      k = 25;
      j = 14;
      m = 0;
      n = 1;
      break;
    case 358: 
      read();
      k = 26;
      j = 15;
      m = 0;
      n = 1;
      break;
    case 545: 
      read();
      k = 36;
      j = 4;
      m = 1;
      n = 1;
      break;
    case 278: 
      read();
      k = 32;
      j = 3;
      m = 1;
      n = 1;
      break;
    default: 
      throw unexpectedToken();
    }
    if ((n != 0) && (this.token.tokenType == 412))
    {
      int i1 = getPosition();
      read();
      if (this.token.tokenType == 101)
      {
        read();
        bool2 = true;
      }
      else
      {
        rewind(i1);
      }
    }
    checkIsIdentifier();
    HsqlNameManager.HsqlName localHsqlName = null;
    switch (i)
    {
    case 305: 
      checkIsSimpleName();
      checkDatabaseUpdateAuthorisation();
      localObject = this.database.getUserManager().get(this.token.tokenString);
      read();
      break;
    case 490: 
      checkIsSimpleName();
      checkDatabaseUpdateAuthorisation();
      localObject = this.database.getGranteeManager().getRole(this.token.tokenString);
      read();
      break;
    case 497: 
      localHsqlName = readNewSchemaName();
      localObject = this.database.schemaManager.findSchema(localHsqlName.name);
      break;
    case 278: 
      int i2 = (this.token.namePrePrefix == null) && (("MODULE".equals(this.token.namePrefix)) || ("SESSION".equals(this.token.namePrefix))) ? 1 : 0;
      if (i2 != 0)
      {
        localHsqlName = readNewSchemaObjectName(j, false);
        arrayOfObject = new Object[] { localHsqlName, Boolean.valueOf(bool2) };
        return new StatementSession(32, arrayOfObject);
      }
      break;
    }
    localHsqlName = readNewSchemaObjectName(j, false);
    localHsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    Object localObject = this.database.schemaManager.findSchemaObject(localHsqlName.name, localHsqlName.schema.name, localHsqlName.type);
    if ((!bool2) && (n != 0) && (this.token.tokenType == 412))
    {
      read();
      readThis(101);
      bool2 = true;
    }
    if (m != 0) {
      if (this.token.tokenType == 347)
      {
        bool1 = true;
        read();
      }
      else if (this.token.tokenType == 485)
      {
        read();
      }
    }
    HsqlNameManager.HsqlName[] arrayOfHsqlName;
    if (localObject == null)
    {
      arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
    }
    else
    {
      localHsqlName = ((SchemaObject)localObject).getName();
      arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(localHsqlName);
    }
    String str = getLastPart();
    Object[] arrayOfObject = { localHsqlName, new Integer(j), Boolean.valueOf(bool1), Boolean.valueOf(bool2) };
    StatementSchema localStatementSchema = new StatementSchema(str, k, arrayOfObject, null, arrayOfHsqlName);
    return localStatementSchema;
  }
  
  Statement compileAlterTable()
  {
    read();
    String str1 = this.token.tokenString;
    HsqlNameManager.HsqlName localHsqlName1 = this.session.getSchemaHsqlName(this.token.namePrefix);
    checkSchemaUpdateAuthorisation(localHsqlName1);
    Table localTable = this.database.schemaManager.getUserTable(this.session, str1, localHsqlName1.name);
    read();
    switch (this.token.tokenType)
    {
    case 622: 
      read();
      readThis(285);
      return compileRenameObject(localTable.getName(), 3);
    case 334: 
      read();
      HsqlNameManager.HsqlName localHsqlName2 = null;
      if (this.token.tokenType == 48)
      {
        read();
        localHsqlName2 = readNewDependentSchemaObjectName(localTable.getName(), 5);
      }
      switch (this.token.tokenType)
      {
      case 113: 
        read();
        readThis(427);
        return compileAlterTableAddForeignKeyConstraint(localTable, localHsqlName2);
      case 299: 
        read();
        return compileAlterTableAddUniqueConstraint(localTable, localHsqlName2);
      case 37: 
        read();
        return compileAlterTableAddCheckConstraint(localTable, localHsqlName2);
      case 214: 
        read();
        readThis(427);
        return compileAlterTableAddPrimaryKey(localTable, localHsqlName2);
      case 43: 
        if (localHsqlName2 != null) {
          throw unexpectedToken();
        }
        read();
        checkIsSimpleName();
        return compileAlterTableAddColumn(localTable);
      }
      if (localHsqlName2 != null) {
        throw unexpectedToken();
      }
      checkIsSimpleName();
      return compileAlterTableAddColumn(localTable);
    case 88: 
      read();
      switch (this.token.tokenType)
      {
      case 214: 
        int i = 0;
        read();
        readThis(427);
        return compileAlterTableDropPrimaryKey(localTable);
      case 48: 
        read();
        return compileAlterTableDropConstraint(localTable);
      case 43: 
        read();
      }
      checkIsSimpleName();
      String str2 = this.token.tokenString;
      boolean bool = false;
      read();
      if (this.token.tokenType == 485)
      {
        read();
      }
      else if (this.token.tokenType == 347)
      {
        read();
        bool = true;
      }
      return compileAlterTableDropColumn(localTable, str2, bool);
    case 4: 
      read();
      if (this.token.tokenType == 43) {
        read();
      }
      int j = localTable.getColumnIndex(this.token.tokenString);
      ColumnSchema localColumnSchema = localTable.getColumn(j);
      read();
      return compileAlterColumn(localTable, localColumnSchema, j);
    }
    throw unexpectedToken();
  }
  
  private Statement compileAlterTableDropConstraint(Table paramTable)
  {
    boolean bool = false;
    SchemaObject localSchemaObject = readSchemaObjectName(paramTable.getSchemaName(), 5);
    if (this.token.tokenType == 485)
    {
      read();
    }
    else if (this.token.tokenType == 347)
    {
      read();
      bool = true;
    }
    String str = getLastPart();
    Object[] arrayOfObject = { localSchemaObject.getName(), ValuePool.getInt(5), Boolean.valueOf(bool), Boolean.valueOf(false) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    HsqlNameManager.HsqlName localHsqlName = ((Constraint)localSchemaObject).getMainTableName();
    if ((localHsqlName != null) && (localHsqlName != paramTable.getName())) {
      arrayOfHsqlName = (HsqlNameManager.HsqlName[])ArrayUtil.toAdjustedArray(arrayOfHsqlName, localHsqlName, arrayOfHsqlName.length, 1);
    }
    StatementSchema localStatementSchema = new StatementSchema(str, 1078, arrayOfObject, null, arrayOfHsqlName);
    return localStatementSchema;
  }
  
  private Statement compileAlterTableDropPrimaryKey(Table paramTable)
  {
    boolean bool = false;
    if (this.token.tokenType == 485)
    {
      read();
    }
    else if (this.token.tokenType == 347)
    {
      read();
      bool = true;
    }
    if (!paramTable.hasPrimaryKey()) {
      throw Error.error(5501);
    }
    String str = getLastPart();
    Constraint localConstraint = paramTable.getPrimaryConstraint();
    Object[] arrayOfObject = { localConstraint.getName(), ValuePool.getInt(5), Boolean.valueOf(bool), Boolean.valueOf(false) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    StatementSchema localStatementSchema = new StatementSchema(str, 1078, arrayOfObject, null, arrayOfHsqlName);
    return localStatementSchema;
  }
  
  StatementSession compileDeclareLocalTableOrNull()
  {
    int i = super.getPosition();
    try
    {
      readThis(77);
      readThis(157);
      readThis(523);
      readThis(278);
    }
    catch (HsqlException localHsqlException)
    {
      this.lastError = localHsqlException;
      rewind(i);
      return null;
    }
    if ((this.token.namePrePrefix != null) || ((this.token.namePrefix != null) && (!"MODULE".equals(this.token.namePrefix)) && (!"SESSION".equals(this.token.namePrefix)))) {
      throw unexpectedToken();
    }
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(3, false);
    localHsqlName.schema = SqlInvariants.MODULE_HSQLNAME;
    Table localTable = new Table(this.database, localHsqlName, 3);
    StatementSchema localStatementSchema = compileCreateTableBody(localTable, false);
    HsqlArrayList localHsqlArrayList = (HsqlArrayList)localStatementSchema.arguments[1];
    for (int j = 0; j < localHsqlArrayList.size(); j++)
    {
      Constraint localConstraint = (Constraint)localHsqlArrayList.get(j);
      if (localConstraint.getConstraintType() == 0) {
        throw unexpectedToken("FOREIGN");
      }
    }
    StatementSession localStatementSession = new StatementSession(1068, localStatementSchema.arguments);
    return localStatementSession;
  }
  
  StatementSchema compileCreateTable(int paramInt)
  {
    boolean bool = false;
    if (this.token.tokenType == 412)
    {
      int i = getPosition();
      read();
      if (this.token.tokenType == 183)
      {
        read();
        readThis(101);
        bool = true;
      }
      else
      {
        rewind(i);
      }
    }
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(3, false);
    localHsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    Object localObject;
    switch (paramInt)
    {
    case 6: 
    case 7: 
      localObject = new TextTable(this.database, localHsqlName, paramInt);
      break;
    default: 
      localObject = new Table(this.database, localHsqlName, paramInt);
    }
    return compileCreateTableBody((Table)localObject, bool);
  }
  
  StatementSchema compileCreateTableBody(Table paramTable, boolean paramBoolean)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    if (this.token.tokenType == 10) {
      return readTableAsSubqueryDefinition(paramTable);
    }
    int i = getPosition();
    readThis(786);
    Constraint localConstraint = new Constraint(null, null, 5);
    localHsqlArrayList.add(localConstraint);
    int j = 1;
    int k = 1;
    int m = 0;
    while (m == 0) {
      switch (this.token.tokenType)
      {
      case 154: 
        localObject1 = readLikeTable(paramTable);
        for (int n = 0; n < localObject1.length; n++) {
          paramTable.addColumn(localObject1[n]);
        }
        j = 0;
        k = 0;
        break;
      case 37: 
      case 48: 
      case 113: 
      case 214: 
      case 299: 
        if (k == 0) {
          throw unexpectedToken();
        }
        readConstraint(paramTable, localHsqlArrayList);
        j = 0;
        k = 0;
        break;
      case 774: 
        if (k != 0) {
          throw unexpectedToken();
        }
        read();
        k = 1;
        break;
      case 772: 
        read();
        m = 1;
        break;
      default: 
        if (k == 0) {
          throw unexpectedToken();
        }
        checkIsSchemaObjectName();
        localObject1 = this.database.nameManager.newColumnHsqlName(paramTable.getName(), this.token.tokenString, isDelimitedIdentifier());
        read();
        ColumnSchema localColumnSchema = readColumnDefinitionOrNull(paramTable, (HsqlNameManager.HsqlName)localObject1, localHsqlArrayList);
        if (localColumnSchema == null)
        {
          if (j != 0)
          {
            rewind(i);
            return readTableAsSubqueryDefinition(paramTable);
          }
          throw Error.error(5000);
        }
        paramTable.addColumn(localColumnSchema);
        j = 0;
        k = 0;
      }
    }
    if (this.token.tokenType == 194)
    {
      if (!paramTable.isTemp()) {
        throw unexpectedToken();
      }
      read();
      readThis(44);
      if ((this.token.tokenType != 79) && (this.token.tokenType == 476)) {
        paramTable.persistenceScope = 23;
      }
      read();
      readThis(245);
    }
    Object localObject1 = new OrderedHashSet();
    ((OrderedHashSet)localObject1).add(this.database.getCatalogName());
    for (int i1 = 0; i1 < localHsqlArrayList.size(); i1++)
    {
      localObject2 = (Constraint)localHsqlArrayList.get(i1);
      localObject3 = ((Constraint)localObject2).getMainTableName();
      if (localObject3 != null)
      {
        Table localTable = this.database.schemaManager.findUserTable(null, ((HsqlNameManager.HsqlName)localObject3).name, ((HsqlNameManager.HsqlName)localObject3).schema.name);
        if ((localTable != null) && (!localTable.isTemp())) {
          ((OrderedHashSet)localObject1).add(paramTable.getName());
        }
      }
    }
    String str = getLastPart();
    Object localObject2 = { paramTable, localHsqlArrayList, null, Boolean.valueOf(paramBoolean) };
    Object localObject3 = new HsqlNameManager.HsqlName[((OrderedHashSet)localObject1).size()];
    ((OrderedHashSet)localObject1).toArray((Object[])localObject3);
    return new StatementSchema(str, 77, (Object[])localObject2, null, (HsqlNameManager.HsqlName[])localObject3);
  }
  
  private ColumnSchema[] readLikeTable(Table paramTable)
  {
    read();
    int i = 0;
    int j = 0;
    int k = 0;
    Table localTable = readTableName();
    OrderedIntHashSet localOrderedIntHashSet = new OrderedIntHashSet();
    for (;;)
    {
      int m = this.token.tokenType == 416 ? 1 : 0;
      if ((m == 0) && (this.token.tokenType != 399)) {
        break;
      }
      read();
      switch (this.token.tokenType)
      {
      case 407: 
        if (!localOrderedIntHashSet.add(this.token.tokenType)) {
          throw unexpectedToken();
        }
        i = m;
        break;
      case 128: 
        if (!localOrderedIntHashSet.add(this.token.tokenType)) {
          throw unexpectedToken();
        }
        j = m;
        break;
      case 381: 
        if (!localOrderedIntHashSet.add(this.token.tokenType)) {
          throw unexpectedToken();
        }
        k = m;
        break;
      default: 
        throw unexpectedToken();
      }
      read();
    }
    ColumnSchema[] arrayOfColumnSchema = new ColumnSchema[localTable.getColumnCount()];
    for (int n = 0; n < arrayOfColumnSchema.length; n++)
    {
      ColumnSchema localColumnSchema = localTable.getColumn(n).duplicate();
      HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newColumnSchemaHsqlName(paramTable.getName(), localColumnSchema.getName());
      localColumnSchema.setName(localHsqlName);
      localColumnSchema.setNullable(true);
      localColumnSchema.setPrimaryKey(false);
      if (j != 0)
      {
        if (localColumnSchema.isIdentity()) {
          localColumnSchema.setIdentity(localColumnSchema.getIdentitySequence().duplicate());
        }
      }
      else {
        localColumnSchema.setIdentity(null);
      }
      if (k == 0) {
        localColumnSchema.setDefaultExpression(null);
      }
      if (i == 0) {
        localColumnSchema.setGeneratingExpression(null);
      }
      arrayOfColumnSchema[n] = localColumnSchema;
    }
    return arrayOfColumnSchema;
  }
  
  StatementSchema readTableAsSubqueryDefinition(Table paramTable)
  {
    HsqlNameManager.HsqlName[] arrayOfHsqlName1 = null;
    int i = 1;
    HsqlNameManager.HsqlName[] arrayOfHsqlName2 = null;
    StatementQuery localStatementQuery = null;
    if (this.token.tokenType == 786) {
      arrayOfHsqlName2 = readColumnNames(paramTable.getName());
    }
    readThis(10);
    readThis(786);
    QueryExpression localQueryExpression = XreadQueryExpression();
    localQueryExpression.setReturningResult();
    localQueryExpression.resolve(this.session);
    readThis(772);
    readThis(319);
    if (this.token.tokenType == 180)
    {
      read();
      i = 0;
    }
    else if (paramTable.getTableType() == 7)
    {
      throw unexpectedTokenRequire("NO");
    }
    readThis(378);
    if (this.token.tokenType == 194)
    {
      if (!paramTable.isTemp()) {
        throw unexpectedToken();
      }
      read();
      readThis(44);
      if ((this.token.tokenType != 79) && (this.token.tokenType == 476)) {
        paramTable.persistenceScope = 23;
      }
      read();
      readThis(245);
    }
    if (arrayOfHsqlName2 == null) {
      arrayOfHsqlName2 = localQueryExpression.getResultColumnNames();
    } else if (arrayOfHsqlName2.length != localQueryExpression.getColumnCount()) {
      throw Error.error(5593);
    }
    TableUtil.setColumnsInSchemaTable(paramTable, arrayOfHsqlName2, localQueryExpression.getColumnTypes());
    paramTable.createPrimaryKey();
    if ((paramTable.isTemp()) && (paramTable.hasLobColumn())) {
      throw Error.error(5534);
    }
    if (i != 0)
    {
      localStatementQuery = new StatementQuery(this.session, localQueryExpression, this.compileContext);
      arrayOfHsqlName1 = localStatementQuery.getTableNamesForRead();
    }
    Object[] arrayOfObject = { paramTable, new HsqlArrayList(), localStatementQuery, Boolean.FALSE };
    String str = getLastPart();
    HsqlNameManager.HsqlName[] arrayOfHsqlName3 = this.database.schemaManager.catalogNameArray;
    StatementSchema localStatementSchema = new StatementSchema(str, 77, arrayOfObject, arrayOfHsqlName1, arrayOfHsqlName3);
    return localStatementSchema;
  }
  
  static Table addTableConstraintDefinitions(Session paramSession, Table paramTable, HsqlArrayList paramHsqlArrayList1, HsqlArrayList paramHsqlArrayList2, boolean paramBoolean)
  {
    Constraint localConstraint1 = (Constraint)paramHsqlArrayList1.get(0);
    String str = localConstraint1.getName() == null ? null : localConstraint1.getName().name;
    HsqlNameManager.HsqlName localHsqlName = paramSession.database.nameManager.newAutoName("IDX", str, paramTable.getSchemaName(), paramTable.getName(), 20);
    localConstraint1.setColumnsIndexes(paramTable);
    paramTable.createPrimaryKey(localHsqlName, localConstraint1.core.mainCols, true);
    if (localConstraint1.core.mainCols != null)
    {
      Constraint localConstraint2 = new Constraint(localConstraint1.getName(), paramTable, paramTable.getPrimaryIndex(), 4);
      paramTable.addConstraint(localConstraint2);
      if (paramBoolean) {
        paramSession.database.schemaManager.addSchemaObject(localConstraint2);
      }
    }
    for (int i = 1; i < paramHsqlArrayList1.size(); i++)
    {
      localConstraint1 = (Constraint)paramHsqlArrayList1.get(i);
      switch (localConstraint1.constType)
      {
      case 2: 
        localConstraint1.setColumnsIndexes(paramTable);
        if (paramTable.getUniqueConstraintForColumns(localConstraint1.core.mainCols) != null) {
          throw Error.error(5522);
        }
        localHsqlName = paramSession.database.nameManager.newAutoName("IDX", localConstraint1.getName().name, paramTable.getSchemaName(), paramTable.getName(), 20);
        Index localIndex = paramTable.createAndAddIndexStructure(localHsqlName, localConstraint1.core.mainCols, null, null, true, true, false);
        Constraint localConstraint3 = new Constraint(localConstraint1.getName(), paramTable, localIndex, 2);
        paramTable.addConstraint(localConstraint3);
        if (paramBoolean) {
          paramSession.database.schemaManager.addSchemaObject(localConstraint3);
        }
        break;
      case 0: 
        addForeignKey(paramSession, paramTable, localConstraint1, paramHsqlArrayList2);
        break;
      case 3: 
        try
        {
          localConstraint1.prepareCheckConstraint(paramSession, paramTable, false);
        }
        catch (HsqlException localHsqlException)
        {
          if (!paramSession.isProcessingScript()) {
            break label352;
          }
        }
        continue;
        label352:
        throw localHsqlException;
        paramTable.addConstraint(localConstraint1);
        if (localConstraint1.isNotNull())
        {
          ColumnSchema localColumnSchema = paramTable.getColumn(localConstraint1.notNullColumnIndex);
          localColumnSchema.setNullable(false);
          paramTable.setColumnTypeVars(localConstraint1.notNullColumnIndex);
        }
        if (paramBoolean) {
          paramSession.database.schemaManager.addSchemaObject(localConstraint1);
        }
        break;
      }
    }
    return paramTable;
  }
  
  static void addForeignKey(Session paramSession, Table paramTable, Constraint paramConstraint, HsqlArrayList paramHsqlArrayList)
  {
    HsqlNameManager.HsqlName localHsqlName1 = paramConstraint.getMainTableName();
    if (localHsqlName1 == paramTable.getName())
    {
      paramConstraint.core.mainTable = paramTable;
    }
    else
    {
      localObject = paramSession.database.schemaManager.findUserTable(paramSession, localHsqlName1.name, localHsqlName1.schema.name);
      if (localObject == null)
      {
        if (paramHsqlArrayList == null) {
          throw Error.error(5501, localHsqlName1.name);
        }
        paramHsqlArrayList.add(paramConstraint);
        return;
      }
      paramConstraint.core.mainTable = ((Table)localObject);
    }
    paramConstraint.setColumnsIndexes(paramTable);
    Object localObject = new TableWorks(paramSession, paramTable);
    ((TableWorks)localObject).checkCreateForeignKey(paramConstraint);
    Constraint localConstraint = paramConstraint.core.mainTable.getUniqueConstraintForColumns(paramConstraint.core.mainCols);
    if (localConstraint == null) {
      throw Error.error(5523);
    }
    Index localIndex1 = localConstraint.getMainIndex();
    boolean bool = paramConstraint.core.mainTable.getSchemaName() != paramTable.getSchemaName();
    int i = paramSession.database.schemaManager.getTableIndex(paramTable);
    if ((i != -1) && (i < paramSession.database.schemaManager.getTableIndex(paramConstraint.core.mainTable))) {
      bool = true;
    }
    HsqlNameManager.HsqlName localHsqlName2 = paramSession.database.nameManager.newAutoName("IDX", paramTable.getSchemaName(), paramTable.getName(), 20);
    Index localIndex2 = paramTable.createAndAddIndexStructure(localHsqlName2, paramConstraint.core.refCols, null, null, false, true, bool);
    HsqlNameManager.HsqlName localHsqlName3 = paramSession.database.nameManager.newAutoName("REF", paramConstraint.getName().name, paramTable.getSchemaName(), paramTable.getName(), 20);
    paramConstraint.core.uniqueName = localConstraint.getName();
    paramConstraint.core.mainName = localHsqlName3;
    paramConstraint.core.mainIndex = localIndex1;
    paramConstraint.core.refTable = paramTable;
    paramConstraint.core.refName = paramConstraint.getName();
    paramConstraint.core.refIndex = localIndex2;
    paramConstraint.isForward = bool;
    paramTable.addConstraint(paramConstraint);
    paramConstraint.core.mainTable.addConstraint(new Constraint(localHsqlName3, paramConstraint));
    paramSession.database.schemaManager.addSchemaObject(paramConstraint);
  }
  
  private Constraint readFKReferences(Table paramTable, HsqlNameManager.HsqlName paramHsqlName, OrderedHashSet paramOrderedHashSet)
  {
    OrderedHashSet localOrderedHashSet = null;
    readThis(222);
    HsqlNameManager.HsqlName localHsqlName2;
    if (this.token.namePrefix == null) {
      localHsqlName2 = paramTable.getSchemaName();
    } else {
      localHsqlName2 = this.database.schemaManager.getSchemaHsqlName(this.token.namePrefix);
    }
    HsqlNameManager.HsqlName localHsqlName1;
    if ((paramTable.getSchemaName() == localHsqlName2) && (paramTable.getName().name.equals(this.token.tokenString)))
    {
      localHsqlName1 = paramTable.getName();
      read();
    }
    else
    {
      localHsqlName1 = readFKTableName(localHsqlName2);
    }
    if (this.token.tokenType == 786) {
      localOrderedHashSet = readColumnNames(false);
    }
    int i = 59;
    if (this.token.tokenType == 162)
    {
      read();
      switch (this.token.tokenType)
      {
      case 511: 
        read();
        break;
      case 470: 
        throw super.unsupportedFeature();
      case 116: 
        read();
        i = 61;
        break;
      default: 
        throw unexpectedToken();
      }
    }
    int j = 3;
    int k = 3;
    OrderedIntHashSet localOrderedIntHashSet = new OrderedIntHashSet();
    while (this.token.tokenType == 194)
    {
      read();
      if (!localOrderedIntHashSet.add(this.token.tokenType)) {
        throw unexpectedToken();
      }
      if (this.token.tokenType == 79)
      {
        read();
        if (this.token.tokenType == 254)
        {
          read();
          switch (this.token.tokenType)
          {
          case 78: 
            read();
            j = 4;
            break;
          case 186: 
            read();
            j = 2;
            break;
          default: 
            throw unexpectedToken();
          }
        }
        else if (this.token.tokenType == 347)
        {
          read();
          j = 0;
        }
        else if (this.token.tokenType == 485)
        {
          read();
        }
        else
        {
          readThis(180);
          readThis(332);
        }
      }
      else if (this.token.tokenType == 303)
      {
        read();
        if (this.token.tokenType == 254)
        {
          read();
          switch (this.token.tokenType)
          {
          case 78: 
            read();
            k = 4;
            break;
          case 186: 
            read();
            k = 2;
            break;
          default: 
            throw unexpectedToken();
          }
        }
        else if (this.token.tokenType == 347)
        {
          read();
          k = 0;
        }
        else if (this.token.tokenType == 485)
        {
          read();
        }
        else
        {
          readThis(180);
          readThis(332);
        }
      }
      else
      {
        throw unexpectedToken();
      }
    }
    if (paramHsqlName == null) {
      paramHsqlName = this.database.nameManager.newAutoName("FK", paramTable.getSchemaName(), paramTable.getName(), 5);
    }
    return new Constraint(paramHsqlName, paramTable.getName(), paramOrderedHashSet, localHsqlName1, localOrderedHashSet, 0, j, k, i);
  }
  
  private HsqlNameManager.HsqlName readFKTableName(HsqlNameManager.HsqlName paramHsqlName)
  {
    checkIsSchemaObjectName();
    Table localTable = this.database.schemaManager.findUserTable(this.session, this.token.tokenString, paramHsqlName.name);
    HsqlNameManager.HsqlName localHsqlName;
    if (localTable == null) {
      localHsqlName = this.database.nameManager.newHsqlName(paramHsqlName, this.token.tokenString, isDelimitedIdentifier(), 3);
    } else {
      localHsqlName = localTable.getName();
    }
    read();
    return localHsqlName;
  }
  
  StatementSchema compileCreateView(boolean paramBoolean1, boolean paramBoolean2)
  {
    read();
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(4, true);
    localHsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    checkSchemaUpdateAuthorisation(localHsqlName.schema);
    HsqlNameManager.HsqlName[] arrayOfHsqlName1 = null;
    if (this.token.tokenType == 786) {
      try
      {
        arrayOfHsqlName1 = readColumnNames(localHsqlName);
      }
      catch (HsqlException localHsqlException1)
      {
        if ((this.session.isProcessingScript()) && (this.database.getProperties().isVersion18())) {}
        while (this.token.tokenType != 10)
        {
          read();
          continue;
          throw localHsqlException1;
        }
      }
    }
    readThis(10);
    startRecording();
    int i = getPosition();
    Object localObject;
    try
    {
      localObject = XreadQueryExpression();
    }
    catch (HsqlException localHsqlException2)
    {
      localObject = XreadJoinedTableAsView();
    }
    Token[] arrayOfToken = getRecordedStatement();
    int j = 0;
    if (this.token.tokenType == 319)
    {
      read();
      j = 2;
      if (readIfThis(157)) {
        j = 1;
      } else {
        readIfThis(28);
      }
      readThis(37);
      readThis(455);
    }
    View localView = new View(this.database, localHsqlName, arrayOfHsqlName1, j);
    ((QueryExpression)localObject).setView(localView);
    ((QueryExpression)localObject).resolve(this.session);
    localView.setStatement(Token.getSQL(arrayOfToken));
    StatementQuery localStatementQuery = new StatementQuery(this.session, (QueryExpression)localObject, this.compileContext);
    String str = getLastPart();
    Object[] arrayOfObject = { localView };
    int k = paramBoolean1 ? 1070 : 84;
    HsqlNameManager.HsqlName[] arrayOfHsqlName2 = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, k, arrayOfObject, localStatementQuery.readTableNames, arrayOfHsqlName2);
  }
  
  StatementSchema compileCreateSequence()
  {
    read();
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(7, false);
    NumberSequence localNumberSequence = new NumberSequence(localHsqlName, Type.SQL_INTEGER);
    readSequenceOptions(localNumberSequence, true, false, false);
    String str = getLastPart();
    Object[] arrayOfObject = { localNumberSequence };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, 133, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateDomain()
  {
    UserTypeModifier localUserTypeModifier = null;
    read();
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(13, false);
    readIfThis(10);
    Type localType = readTypeDefinition(false, false).duplicate();
    Expression localExpression = null;
    if (readIfThis(78)) {
      localExpression = readDefaultClause(localType);
    }
    localUserTypeModifier = new UserTypeModifier(localHsqlName, 13, localType);
    localUserTypeModifier.setDefaultClause(localExpression);
    localType.userTypeModifier = localUserTypeModifier;
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    this.compileContext.currentDomain = localType;
    for (;;)
    {
      i = 0;
      switch (this.token.tokenType)
      {
      case 37: 
      case 48: 
        readConstraint(localType, localHsqlArrayList);
        break;
      default: 
        i = 1;
      }
      if (i != 0) {
        break;
      }
    }
    this.compileContext.currentDomain = null;
    for (int i = 0; i < localHsqlArrayList.size(); i++)
    {
      localObject = (Constraint)localHsqlArrayList.get(i);
      ((Constraint)localObject).prepareCheckConstraint(this.session, null, false);
      localUserTypeModifier.addConstraint((Constraint)localObject);
    }
    String str = getLastPart();
    Object localObject = { localType };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, 23, (Object[])localObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateType(boolean paramBoolean)
  {
    read();
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(12, false);
    readThis(10);
    Type localType = readTypeDefinition(false, false).duplicate();
    readIfThis(400);
    UserTypeModifier localUserTypeModifier = new UserTypeModifier(localHsqlName, 12, localType);
    localType.userTypeModifier = localUserTypeModifier;
    String str = getLastPart();
    Object[] arrayOfObject = { localType };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, 83, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateCharacterSet()
  {
    read();
    readThis(254);
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(14, false);
    readIfThis(10);
    readThis(119);
    String str1 = this.session.getSchemaName(this.token.namePrefix);
    Charset localCharset1 = (Charset)this.database.schemaManager.getSchemaObject(this.token.tokenString, str1, 14);
    read();
    if (this.token.tokenType == 358)
    {
      read();
      readThis(115);
      readThis(78);
    }
    Charset localCharset2 = new Charset(localHsqlName);
    localCharset2.base = localCharset1.getName();
    String str2 = getLastPart();
    Object[] arrayOfObject = { localCharset2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str2, 8, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateCollation()
  {
    read();
    HsqlNameManager.HsqlName localHsqlName1 = readNewSchemaObjectName(15, false);
    localHsqlName1.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    readThis(112);
    HsqlNameManager.HsqlName localHsqlName2 = readNewSchemaObjectName(14, false);
    localHsqlName2.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    readThis(115);
    HsqlNameManager.HsqlName localHsqlName3 = readNewSchemaObjectName(15, false);
    localHsqlName3.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    Boolean localBoolean = null;
    if (readIfThis(180))
    {
      readThis(463);
      localBoolean = Boolean.FALSE;
    }
    else if (readIfThis(463))
    {
      readThis(514);
      localBoolean = Boolean.TRUE;
    }
    Charset localCharset = (Charset)this.database.schemaManager.getSchemaObject(localHsqlName2);
    if (localCharset == null) {
      throw Error.error(5501, localHsqlName2.getSchemaQualifiedStatementName());
    }
    Collation localCollation1;
    try
    {
      localCollation1 = Collation.getCollation(localHsqlName3.name);
    }
    catch (HsqlException localHsqlException)
    {
      localCollation1 = (Collation)this.database.schemaManager.getSchemaObject(localHsqlName3);
    }
    if (localCollation1 == null) {
      throw Error.error(5501, localHsqlName3.getSchemaQualifiedStatementName());
    }
    Collation localCollation2 = new Collation(localHsqlName1, localCollation1, localCharset, localBoolean);
    String str = getLastPart();
    Object[] arrayOfObject = { localCollation2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, 10, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateAlias()
  {
    HsqlNameManager.HsqlName localHsqlName = null;
    Routine[] arrayOfRoutine = null;
    String str2 = null;
    if (!this.session.isProcessingScript()) {
      throw super.unsupportedFeature();
    }
    read();
    String str1;
    try
    {
      str1 = this.token.tokenString;
      read();
      readThis(112);
      str2 = this.token.tokenString;
      read();
    }
    catch (HsqlException localHsqlException)
    {
      str1 = null;
    }
    if (str1 != null)
    {
      localObject1 = this.database.schemaManager.getDefaultSchemaHsqlName();
      localHsqlName = this.database.nameManager.newHsqlName((HsqlNameManager.HsqlName)localObject1, str1, 16);
      localObject2 = Routine.getMethods(str2);
      arrayOfRoutine = Routine.newRoutines(this.session, (Method[])localObject2);
    }
    Object localObject1 = getLastPart();
    Object localObject2 = { localHsqlName, arrayOfRoutine };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema((String)localObject1, 1072, (Object[])localObject2, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateTrigger(boolean paramBoolean)
  {
    Object localObject1 = null;
    boolean bool = false;
    int i = 0;
    int j = 0;
    HsqlNameManager.HsqlName localHsqlName2 = null;
    OrderedHashSet localOrderedHashSet = null;
    int[] arrayOfInt = null;
    read();
    HsqlNameManager.HsqlName localHsqlName1 = readNewSchemaObjectName(8, true);
    int k;
    switch (this.token.tokenType)
    {
    case 422: 
      k = TriggerDef.getTiming(422);
      read();
      readThis(191);
      break;
    case 336: 
    case 343: 
      k = TriggerDef.getTiming(this.token.tokenType);
      read();
      break;
    default: 
      throw unexpectedToken();
    }
    int m;
    switch (this.token.tokenType)
    {
    case 79: 
    case 135: 
      m = TriggerDef.getOperationType(this.token.tokenType);
      read();
      break;
    case 303: 
      m = TriggerDef.getOperationType(this.token.tokenType);
      read();
      if ((this.token.tokenType == 191) && (k != 6))
      {
        read();
        localOrderedHashSet = new OrderedHashSet();
        readColumnNameList(localOrderedHashSet, null, false);
      }
      break;
    default: 
      throw unexpectedToken();
    }
    readThis(194);
    Table localTable = readTableName();
    if (this.token.tokenType == 343)
    {
      read();
      checkIsSimpleName();
      localHsqlName2 = readNewSchemaObjectName(8, true);
    }
    localHsqlName1.setSchemaIfNull(localTable.getSchemaName());
    checkSchemaUpdateAuthorisation(localHsqlName1.schema);
    if (k == 6)
    {
      if ((!localTable.isView()) || (((View)localTable).getCheckOption() == 2)) {
        throw Error.error(5538, localHsqlName1.schema.name);
      }
    }
    else if (localTable.isView()) {
      throw Error.error(5538, localHsqlName1.schema.name);
    }
    if (localHsqlName1.schema != localTable.getSchemaName()) {
      throw Error.error(5505, localHsqlName1.schema.name);
    }
    localHsqlName1.parent = localTable.getName();
    this.database.schemaManager.checkSchemaObjectNotExists(localHsqlName1);
    if (localOrderedHashSet != null)
    {
      arrayOfInt = localTable.getColumnIndexes(localOrderedHashSet);
      for (int n = 0; n < arrayOfInt.length; n++) {
        if (arrayOfInt[n] == -1) {
          throw Error.error(5544, (String)localOrderedHashSet.get(n));
        }
      }
    }
    Expression localExpression = null;
    String str2 = null;
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject5 = null;
    Table[] arrayOfTable = new Table[4];
    RangeVariable[] arrayOfRangeVariable = new RangeVariable[4];
    String str3 = null;
    RangeGroup[] arrayOfRangeGroup = { new RangeGroup.RangeGroupSimple(arrayOfRangeVariable) };
    Object localObject7;
    Object localObject9;
    if (this.token.tokenType == 223)
    {
      read();
      if ((this.token.tokenType != 193) && (this.token.tokenType != 179)) {
        throw unexpectedToken();
      }
      for (;;)
      {
        Object localObject6;
        if (this.token.tokenType == 193)
        {
          if (m == 50) {
            throw unexpectedToken();
          }
          read();
          if (this.token.tokenType == 278)
          {
            if ((Boolean.TRUE.equals(localObject1)) || (str2 != null) || (k == 4)) {
              throw unexpectedToken();
            }
            read();
            readIfThis(10);
            checkIsSimpleName();
            read();
            str2 = this.token.tokenString;
            localObject6 = str2;
            if ((((String)localObject6).equals(localObject3)) || (((String)localObject6).equals(localObject4)) || (((String)localObject6).equals(localObject5))) {
              throw unexpectedToken();
            }
            localObject1 = Boolean.FALSE;
            localObject7 = this.database.nameManager.newHsqlName(localTable.getSchemaName(), (String)localObject6, isDelimitedIdentifier(), 10);
            localObject8 = new Table(localTable, (HsqlNameManager.HsqlName)localObject7);
            localObject9 = new RangeVariable((Table)localObject8, null, null, null, this.compileContext);
            arrayOfTable[2] = localObject8;
            arrayOfRangeVariable[2] = localObject9;
          }
          else
          {
            if ((Boolean.FALSE.equals(localObject1)) || (localObject4 != null)) {
              throw unexpectedToken();
            }
            readIfThis(243);
            readIfThis(10);
            checkIsSimpleName();
            localObject4 = HsqlNameManager.getSimpleName(this.token.tokenString, this.token.isDelimitedIdentifier);
            read();
            localObject6 = ((HsqlNameManager.SimpleName)localObject4).name;
            if ((((String)localObject6).equals(localObject3)) || (((String)localObject6).equals(str2)) || (((String)localObject6).equals(localObject5))) {
              throw unexpectedToken();
            }
            localObject1 = Boolean.TRUE;
            localObject7 = new RangeVariable(localTable.columnList, (HsqlNameManager.SimpleName)localObject4, false, 2);
            ((RangeVariable)localObject7).rangePosition = 0;
            arrayOfTable[0] = null;
            arrayOfRangeVariable[0] = localObject7;
          }
        }
        else
        {
          if (this.token.tokenType != 179) {
            break;
          }
          if (m == 19) {
            throw unexpectedToken();
          }
          read();
          if (this.token.tokenType == 278)
          {
            if ((Boolean.TRUE.equals(localObject1)) || (localObject3 != null) || (k == 4)) {
              throw unexpectedToken();
            }
            read();
            readIfThis(10);
            checkIsSimpleName();
            localObject3 = this.token.tokenString;
            read();
            localObject1 = Boolean.FALSE;
            localObject6 = localObject3;
            if ((((String)localObject6).equals(str2)) || (((String)localObject6).equals(localObject4)) || (((String)localObject6).equals(localObject5))) {
              throw unexpectedToken();
            }
            localObject7 = this.database.nameManager.newHsqlName(localTable.getSchemaName(), (String)localObject6, isDelimitedIdentifier(), 10);
            localObject8 = new Table(localTable, (HsqlNameManager.HsqlName)localObject7);
            localObject9 = new RangeVariable((Table)localObject8, null, null, null, this.compileContext);
            arrayOfTable[3] = localObject8;
            arrayOfRangeVariable[3] = localObject9;
          }
          else
          {
            if ((Boolean.FALSE.equals(localObject1)) || (localObject5 != null)) {
              throw unexpectedToken();
            }
            readIfThis(243);
            readIfThis(10);
            checkIsSimpleName();
            localObject5 = HsqlNameManager.getSimpleName(this.token.tokenString, this.token.isDelimitedIdentifier);
            read();
            localObject6 = ((HsqlNameManager.SimpleName)localObject5).name;
            if ((((String)localObject6).equals(str2)) || (((String)localObject6).equals(localObject3)) || (((String)localObject6).equals(localObject4))) {
              throw unexpectedToken();
            }
            localObject1 = Boolean.TRUE;
            localObject7 = new RangeVariable(localTable.columnList, (HsqlNameManager.SimpleName)localObject5, false, 2);
            ((RangeVariable)localObject7).rangePosition = 1;
            arrayOfTable[1] = null;
            arrayOfRangeVariable[1] = localObject7;
          }
        }
      }
    }
    if ((Boolean.TRUE.equals(localObject1)) && (this.token.tokenType != 112)) {
      throw unexpectedTokenRequire("FOR");
    }
    if (this.token.tokenType == 112)
    {
      read();
      readThis(90);
      if (this.token.tokenType == 243)
      {
        if (Boolean.FALSE.equals(localObject1)) {
          throw unexpectedToken();
        }
        localObject1 = Boolean.TRUE;
      }
      else if (this.token.tokenType == 517)
      {
        if ((Boolean.TRUE.equals(localObject1)) || (k == 4)) {
          throw unexpectedToken();
        }
        localObject1 = Boolean.FALSE;
      }
      else
      {
        throw unexpectedToken();
      }
      read();
    }
    if ((arrayOfRangeVariable[2] == null) || ((arrayOfRangeVariable[3] == null) || ("QUEUE".equals(this.token.tokenString))))
    {
      read();
      j = readInteger();
      i = 1;
    }
    if ("NOWAIT".equals(this.token.tokenString))
    {
      read();
      bool = true;
    }
    int i1;
    if ((this.token.tokenType == 314) && (k != 6))
    {
      read();
      readThis(786);
      i1 = getPosition();
      this.isCheckOrTriggerCondition = true;
      localExpression = XreadBooleanValueExpression();
      str3 = getLastPart(i1);
      this.isCheckOrTriggerCondition = false;
      readThis(772);
      localObject7 = localExpression.resolveColumnReferences(this.session, new RangeGroup.RangeGroupSimple(arrayOfRangeVariable), arrayOfRangeGroup, null);
      ExpressionColumn.checkColumnsResolved((HsqlList)localObject7);
      localExpression.resolveTypes(this.session, null);
      if (localExpression.getDataType() != Type.SQL_BOOLEAN) {
        throw Error.error(5568);
      }
    }
    if (localObject1 == null) {
      localObject1 = Boolean.FALSE;
    }
    if (this.token.tokenType == 25)
    {
      i1 = getPosition();
      try
      {
        read();
        checkIsSimpleName();
        checkIsDelimitedIdentifier();
        String str1 = this.token.tokenString;
        read();
        if (this.token.tokenType == 786) {
          throw unexpectedToken();
        }
        localObject2 = new TriggerDef(localHsqlName1, k, m, ((Boolean)localObject1).booleanValue(), localTable, arrayOfTable, arrayOfRangeVariable, localExpression, str3, arrayOfInt, str1, bool, j);
        localObject7 = getLastPart();
        localObject8 = new Object[] { localObject2, localHsqlName2 };
        localObject9 = new HsqlNameManager.HsqlName[] { this.database.getCatalogName(), localTable.getName() };
        return new StatementSchema((String)localObject7, 80, (Object[])localObject8, null, (HsqlNameManager.HsqlName[])localObject9);
      }
      catch (HsqlException localHsqlException)
      {
        rewind(i1);
      }
    }
    if (i != 0) {
      throw unexpectedToken("QUEUE");
    }
    if (bool) {
      throw unexpectedToken("NOWAIT");
    }
    Routine localRoutine = compileTriggerRoutine(localTable, arrayOfRangeVariable, k, m);
    Object localObject2 = new TriggerDefSQL(localHsqlName1, k, m, ((Boolean)localObject1).booleanValue(), localTable, arrayOfTable, arrayOfRangeVariable, localExpression, str3, arrayOfInt, localRoutine);
    String str4 = getLastPart();
    Object localObject8 = { localObject2, localHsqlName2 };
    return new StatementSchema(str4, 80, (Object[])localObject8, null, new HsqlNameManager.HsqlName[] { this.database.getCatalogName(), localTable.getName() });
  }
  
  Routine compileTriggerRoutine(Table paramTable, RangeVariable[] paramArrayOfRangeVariable, int paramInt1, int paramInt2)
  {
    int i = paramInt1 == 4 ? 3 : 4;
    Routine localRoutine = new Routine(paramTable, paramArrayOfRangeVariable, i, paramInt1, paramInt2);
    startRecording();
    StatementCompound localStatementCompound = new StatementCompound(12, null);
    localStatementCompound.rangeVariables = paramArrayOfRangeVariable;
    Statement localStatement = compileSQLProcedureStatementOrNull(localRoutine, null);
    if (localStatement == null) {
      throw unexpectedToken();
    }
    Token[] arrayOfToken = getRecordedStatement();
    String str = Token.getSQL(arrayOfToken);
    localStatement.setSQL(str);
    localRoutine.setProcedure(localStatement);
    localRoutine.resolve(this.session);
    return localRoutine;
  }
  
  ColumnSchema readColumnDefinitionOrNull(Table paramTable, HsqlNameManager.HsqlName paramHsqlName, HsqlArrayList paramHsqlArrayList)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    boolean bool1 = false;
    Expression localExpression1 = null;
    boolean bool2 = true;
    Expression localExpression2 = null;
    Object localObject = null;
    NumberSequence localNumberSequence = null;
    switch (this.token.tokenType)
    {
    case 407: 
      read();
      readThis(337);
      i = 1;
      bool1 = true;
      throw unexpectedToken("GENERATED");
    case 128: 
      read();
      j = 1;
      k = 1;
      localObject = Type.SQL_INTEGER;
      localNumberSequence = new NumberSequence(null, 0L, 1L, (Type)localObject);
      break;
    case 774: 
      return null;
    case 772: 
      return null;
    default: 
      if (this.token.isUndelimitedIdentifier) {
        if ("SERIAL".equals(this.token.tokenString))
        {
          if (this.database.sqlSyntaxMys)
          {
            read();
            j = 1;
            k = 1;
            localObject = Type.SQL_BIGINT;
            localNumberSequence = new NumberSequence(null, 1L, 1L, (Type)localObject);
            break;
          }
          if (this.database.sqlSyntaxPgs)
          {
            read();
            j = 1;
            localObject = Type.SQL_INTEGER;
            localNumberSequence = new NumberSequence(null, 1L, 1L, (Type)localObject);
            break;
          }
        }
        else if (("BIGSERIAL".equals(this.token.tokenString)) && (this.database.sqlSyntaxPgs))
        {
          read();
          j = 1;
          k = 1;
          localObject = Type.SQL_BIGINT;
          localNumberSequence = new NumberSequence(null, 1L, 1L, (Type)localObject);
          break;
        }
      }
      localObject = readTypeDefinition(true, true);
    }
    if ((i == 0) && (j == 0))
    {
      if (this.database.sqlSyntaxMys) {
        switch (this.token.tokenType)
        {
        case 186: 
          read();
          break;
        case 183: 
          read();
          readThis(186);
          bool2 = false;
          break;
        }
      }
      switch (this.token.tokenType)
      {
      case 319: 
        if (this.database.sqlSyntaxDb2) {
          read();
        } else {
          throw unexpectedToken();
        }
      case 78: 
        read();
        localExpression2 = readDefaultClause((Type)localObject);
        if ((localExpression2.opType == 12) && (this.database.sqlSyntaxPgs))
        {
          localNumberSequence = ((ExpressionColumn)localExpression2).sequence;
          localExpression2 = null;
          j = 1;
        }
        break;
      case 407: 
        read();
        if (this.token.tokenType == 24)
        {
          read();
          readThis(78);
        }
        else
        {
          readThis(337);
          bool1 = true;
        }
        readThis(10);
        if (this.token.tokenType == 128)
        {
          read();
          localNumberSequence = new NumberSequence(null, (Type)localObject);
          localNumberSequence.setAlways(bool1);
          if (this.token.tokenType == 786)
          {
            read();
            readSequenceOptions(localNumberSequence, false, false, true);
            readThis(772);
          }
          j = 1;
        }
        else if (this.token.tokenType == 786)
        {
          if (!bool1) {
            throw super.unexpectedTokenRequire("IDENTITY");
          }
          i = 1;
        }
        else if (this.token.tokenType == 505)
        {
          if (bool1) {
            throw unexpectedToken();
          }
          read();
          if ((this.token.namePrefix != null) && (!this.token.namePrefix.equals(paramTable.getSchemaName().name))) {
            throw super.unexpectedToken(this.token.namePrefix);
          }
          localNumberSequence = this.database.schemaManager.getSequence(this.token.tokenString, paramTable.getSchemaName().name, true);
          j = 1;
          read();
        }
        break;
      case 128: 
        read();
        j = 1;
        k = 1;
        localNumberSequence = new NumberSequence(null, 0L, 1L, (Type)localObject);
      }
    }
    if (i != 0)
    {
      readThis(786);
      localExpression1 = XreadValueExpression();
      readThis(772);
    }
    if ((i == 0) && (j == 0) && (this.database.sqlSyntaxMys) && (this.token.isUndelimitedIdentifier) && ("AUTO_INCREMENT".equals(this.token.tokenString)))
    {
      read();
      j = 1;
      localNumberSequence = new NumberSequence(null, 0L, 1L, (Type)localObject);
    }
    ColumnSchema localColumnSchema = new ColumnSchema(paramHsqlName, (Type)localObject, bool2, false, localExpression2);
    localColumnSchema.setGeneratingExpression(localExpression1);
    readColumnConstraints(paramTable, localColumnSchema, paramHsqlArrayList);
    if ((this.token.tokenType == 128) && (j == 0))
    {
      read();
      j = 1;
      k = 1;
      localNumberSequence = new NumberSequence(null, 0L, 1L, (Type)localObject);
    }
    if (j != 0) {
      localColumnSchema.setIdentity(localNumberSequence);
    }
    if ((k != 0) && (!localColumnSchema.isPrimaryKey()))
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      localOrderedHashSet.add(localColumnSchema.getName().name);
      HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newAutoName("PK", paramTable.getSchemaName(), paramTable.getName(), 5);
      Constraint localConstraint = new Constraint(localHsqlName, localOrderedHashSet, 4);
      paramHsqlArrayList.set(0, localConstraint);
      localColumnSchema.setPrimaryKey(true);
    }
    if ((this.database.sqlSyntaxPgs) && (this.token.tokenType == 78) && (localColumnSchema.getDefaultExpression() == null) && (localColumnSchema.getIdentitySequence() == null))
    {
      read();
      localExpression2 = readDefaultClause((Type)localObject);
      if (localExpression2.opType == 12)
      {
        localNumberSequence = ((ExpressionColumn)localExpression2).sequence;
        localExpression2 = null;
      }
      localColumnSchema.setDefaultExpression(localExpression2);
      localColumnSchema.setIdentity(localNumberSequence);
    }
    return localColumnSchema;
  }
  
  private void readSequenceOptions(NumberSequence paramNumberSequence, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    OrderedIntHashSet localOrderedIntHashSet = new OrderedIntHashSet();
    for (;;)
    {
      int i = 0;
      if (localOrderedIntHashSet.contains(this.token.tokenType)) {
        throw unexpectedToken();
      }
      long l;
      switch (this.token.tokenType)
      {
      case 10: 
        if (paramBoolean1)
        {
          localOrderedIntHashSet.add(this.token.tokenType);
          read();
          Type localType = readTypeDefinition(false, true);
          paramNumberSequence.setDefaults(paramNumberSequence.getName(), localType);
        }
        else
        {
          throw unexpectedToken();
        }
        break;
      case 267: 
        localOrderedIntHashSet.add(this.token.tokenType);
        read();
        readThis(319);
        l = readBigint();
        paramNumberSequence.setStartValueNoCheck(l);
        if (paramBoolean3) {
          readIfThis(774);
        }
        break;
      case 484: 
        if (!paramBoolean2)
        {
          i = 1;
        }
        else
        {
          localOrderedIntHashSet.add(this.token.tokenType);
          read();
          if (readIfThis(319))
          {
            l = readBigint();
            paramNumberSequence.setCurrentValueNoCheck(l);
          }
          else
          {
            paramNumberSequence.setStartValueDefault();
          }
        }
        break;
      case 417: 
        localOrderedIntHashSet.add(this.token.tokenType);
        read();
        readThis(24);
        l = readBigint();
        paramNumberSequence.setIncrement(l);
        break;
      case 180: 
        read();
        if (localOrderedIntHashSet.contains(this.token.tokenType)) {
          throw unexpectedToken();
        }
        if (this.token.tokenType == 438) {
          paramNumberSequence.setDefaultMaxValue();
        } else if (this.token.tokenType == 442) {
          paramNumberSequence.setDefaultMinValue();
        } else if (this.token.tokenType == 71) {
          paramNumberSequence.setCycle(false);
        } else {
          throw unexpectedToken();
        }
        localOrderedIntHashSet.add(this.token.tokenType);
        read();
        break;
      case 438: 
        localOrderedIntHashSet.add(this.token.tokenType);
        read();
        l = readBigint();
        paramNumberSequence.setMaxValueNoCheck(l);
        break;
      case 442: 
        localOrderedIntHashSet.add(this.token.tokenType);
        read();
        l = readBigint();
        paramNumberSequence.setMinValueNoCheck(l);
        break;
      case 71: 
        localOrderedIntHashSet.add(this.token.tokenType);
        read();
        paramNumberSequence.setCycle(true);
        break;
      default: 
        if ((this.database.sqlSyntaxOra) && (isSimpleName()))
        {
          if ((this.token.tokenString.equals("NOCACHE")) || (this.token.tokenString.equals("NOCYCLE")) || (this.token.tokenString.equals("NOMAXVALUE")) || (this.token.tokenString.equals("NOMINVALUE")) || (this.token.tokenString.equals("NOORDER")) || (this.token.tokenString.equals("ORDER")))
          {
            read();
            break;
          }
          if (this.token.tokenString.equals("CACHE"))
          {
            read();
            readBigint();
            break;
          }
        }
        i = 1;
      }
      if (i != 0) {
        break;
      }
    }
    paramNumberSequence.checkValues();
  }
  
  private void readConstraint(SchemaObject paramSchemaObject, HsqlArrayList paramHsqlArrayList)
  {
    HsqlNameManager.HsqlName localHsqlName = null;
    if (this.token.tokenType == 48)
    {
      read();
      localHsqlName = readNewDependentSchemaObjectName(paramSchemaObject.getName(), 5);
    }
    Object localObject1;
    Object localObject2;
    switch (this.token.tokenType)
    {
    case 214: 
      if (paramSchemaObject.getName().type != 3) {
        throw unexpectedTokenRequire("CHECK");
      }
      read();
      readThis(427);
      localObject1 = (Constraint)paramHsqlArrayList.get(0);
      if (((Constraint)localObject1).constType == 4) {
        throw Error.error(5532);
      }
      if (localHsqlName == null) {
        localHsqlName = this.database.nameManager.newAutoName("PK", paramSchemaObject.getSchemaName(), paramSchemaObject.getName(), 5);
      }
      localObject2 = readColumnNames(false);
      Constraint localConstraint = new Constraint(localHsqlName, (OrderedHashSet)localObject2, 4);
      paramHsqlArrayList.set(0, localConstraint);
      break;
    case 299: 
      if (paramSchemaObject.getName().type != 3) {
        throw unexpectedTokenRequire("CHECK");
      }
      read();
      localObject1 = readColumnNames(false);
      if (localHsqlName == null) {
        localHsqlName = this.database.nameManager.newAutoName("CT", paramSchemaObject.getSchemaName(), paramSchemaObject.getName(), 5);
      }
      localObject2 = new Constraint(localHsqlName, (OrderedHashSet)localObject1, 2);
      paramHsqlArrayList.add(localObject2);
      break;
    case 113: 
      if (paramSchemaObject.getName().type != 3) {
        throw unexpectedTokenRequire("CHECK");
      }
      read();
      readThis(427);
      localObject1 = readColumnNames(false);
      localObject2 = readFKReferences((Table)paramSchemaObject, localHsqlName, (OrderedHashSet)localObject1);
      paramHsqlArrayList.add(localObject2);
      break;
    case 37: 
      read();
      if (localHsqlName == null) {
        localHsqlName = this.database.nameManager.newAutoName("CT", paramSchemaObject.getSchemaName(), paramSchemaObject.getName(), 5);
      }
      localObject1 = new Constraint(localHsqlName, null, 3);
      readCheckConstraintCondition((Constraint)localObject1);
      paramHsqlArrayList.add(localObject1);
      break;
    default: 
      if (localHsqlName != null) {
        throw super.unexpectedToken();
      }
      break;
    }
  }
  
  void readColumnConstraints(Table paramTable, ColumnSchema paramColumnSchema, HsqlArrayList paramHsqlArrayList)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    for (;;)
    {
      HsqlNameManager.HsqlName localHsqlName = null;
      if (this.token.tokenType == 48)
      {
        read();
        localHsqlName = readNewDependentSchemaObjectName(paramTable.getName(), 5);
      }
      Object localObject1;
      Object localObject2;
      switch (this.token.tokenType)
      {
      case 214: 
        if ((k != 0) || (m != 0)) {
          throw unexpectedToken();
        }
        read();
        readThis(427);
        localObject1 = (Constraint)paramHsqlArrayList.get(0);
        if (((Constraint)localObject1).constType == 4) {
          throw Error.error(5532);
        }
        localObject2 = new OrderedHashSet();
        ((OrderedHashSet)localObject2).add(paramColumnSchema.getName().name);
        if (localHsqlName == null) {
          localHsqlName = this.database.nameManager.newAutoName("PK", paramTable.getSchemaName(), paramTable.getName(), 5);
        }
        Constraint localConstraint = new Constraint(localHsqlName, (OrderedHashSet)localObject2, 4);
        paramHsqlArrayList.set(0, localConstraint);
        paramColumnSchema.setPrimaryKey(true);
        m = 1;
        break;
      case 299: 
        read();
        localObject1 = new OrderedHashSet();
        ((OrderedHashSet)localObject1).add(paramColumnSchema.getName().name);
        if (localHsqlName == null) {
          localHsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5);
        }
        localObject2 = new Constraint(localHsqlName, (OrderedHashSet)localObject1, 2);
        paramHsqlArrayList.add(localObject2);
        break;
      case 113: 
        read();
        readThis(427);
      case 222: 
        localObject1 = new OrderedHashSet();
        ((OrderedHashSet)localObject1).add(paramColumnSchema.getName().name);
        localObject2 = readFKReferences(paramTable, localHsqlName, (OrderedHashSet)localObject1);
        paramHsqlArrayList.add(localObject2);
        break;
      case 37: 
        read();
        if (localHsqlName == null) {
          localHsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5);
        }
        localObject1 = new Constraint(localHsqlName, null, 3);
        readCheckConstraintCondition((Constraint)localObject1);
        localObject2 = ((Constraint)localObject1).getCheckColumnExpressions();
        for (int n = 0; n < ((OrderedHashSet)localObject2).size(); n++)
        {
          ExpressionColumn localExpressionColumn = (ExpressionColumn)((OrderedHashSet)localObject2).get(n);
          if (paramColumnSchema.getName().name.equals(localExpressionColumn.getColumnName()))
          {
            if ((localExpressionColumn.getSchemaName() != null) && (localExpressionColumn.getSchemaName() != paramTable.getSchemaName().name)) {
              throw Error.error(5505);
            }
          }
          else {
            throw Error.error(5501);
          }
        }
        paramHsqlArrayList.add(localObject1);
        break;
      case 183: 
        if ((j != 0) || (k != 0)) {
          throw unexpectedToken();
        }
        read();
        readThis(186);
        if (localHsqlName == null) {
          localHsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5);
        }
        localObject1 = new Constraint(localHsqlName, null, 3);
        ((Constraint)localObject1).check = new ExpressionLogical(paramColumnSchema);
        paramHsqlArrayList.add(localObject1);
        j = 1;
        break;
      case 186: 
        if ((j != 0) || (k != 0) || (m != 0)) {
          throw unexpectedToken();
        }
        if (localHsqlName != null) {
          throw unexpectedToken();
        }
        read();
        k = 1;
        break;
      default: 
        i = 1;
      }
      if (i != 0) {
        break;
      }
    }
  }
  
  void readCheckConstraintCondition(Constraint paramConstraint)
  {
    readThis(786);
    startRecording();
    this.isCheckOrTriggerCondition = true;
    Expression localExpression = XreadBooleanValueExpression();
    this.isCheckOrTriggerCondition = false;
    Token[] arrayOfToken = getRecordedStatement();
    readThis(772);
    paramConstraint.check = localExpression;
  }
  
  StatementSchema compileCreateIndex(boolean paramBoolean)
  {
    String[] arrayOfString = null;
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    read();
    HsqlNameManager.HsqlName localHsqlName1 = readNewSchemaObjectName(20, true);
    while (this.token.tokenType != 194)
    {
      checkIsIdentifier();
      localHsqlArrayList.add(this.token.tokenString);
      read();
    }
    arrayOfString = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfString);
    readThis(194);
    Table localTable = readTableName();
    HsqlNameManager.HsqlName localHsqlName2 = localTable.getSchemaName();
    localHsqlName1.setSchemaIfNull(localHsqlName2);
    localHsqlName1.parent = localTable.getName();
    if (localHsqlName1.schema != localHsqlName2) {
      throw Error.error(5505);
    }
    localHsqlName1.schema = localTable.getSchemaName();
    int[] arrayOfInt = readColumnList(localTable, true);
    String str = getLastPart();
    Object[] arrayOfObject = { localTable, arrayOfInt, localHsqlName1, Boolean.valueOf(paramBoolean), arrayOfString };
    return new StatementSchema(str, 1073, arrayOfObject, null, new HsqlNameManager.HsqlName[] { this.database.getCatalogName(), localTable.getName() });
  }
  
  StatementSchema compileCreateSchema()
  {
    HsqlNameManager.HsqlName localHsqlName1 = null;
    String str1 = null;
    HsqlNameManager.HsqlName localHsqlName2 = null;
    read();
    if (this.token.tokenType != 15) {
      localHsqlName1 = readNewSchemaName();
    }
    if (this.token.tokenType == 15)
    {
      read();
      checkIsSimpleName();
      str1 = this.token.tokenString;
      read();
      if (localHsqlName1 == null)
      {
        localGrantee = this.database.getGranteeManager().get(str1);
        if (localGrantee == null) {
          throw Error.error(4001, str1);
        }
        localHsqlName1 = this.database.nameManager.newHsqlName(localGrantee.getName().name, isDelimitedIdentifier(), 2);
        SqlInvariants.checkSchemaNameNotSystem(this.token.tokenString);
      }
    }
    if ("PUBLIC".equals(str1)) {
      throw Error.error(4002, str1);
    }
    Grantee localGrantee = str1 == null ? this.session.getGrantee() : this.database.getGranteeManager().get(str1);
    if (localGrantee == null) {
      throw Error.error(4001, str1);
    }
    if (!this.session.getGrantee().isSchemaCreator()) {
      throw Error.error(2051, this.session.getGrantee().getName().getNameString());
    }
    if (((localGrantee instanceof User)) && (((User)localGrantee).isExternalOnly)) {
      throw Error.error(2000, this.session.getGrantee().getName().getNameString());
    }
    if (this.database.schemaManager.schemaExists(localHsqlName1.name)) {
      throw Error.error(5504, localHsqlName1.name);
    }
    if (localHsqlName1.name.equals("SYSTEM_LOBS"))
    {
      localHsqlName1 = SqlInvariants.LOBS_SCHEMA_HSQLNAME;
      localGrantee = localHsqlName1.owner;
    }
    if (readIfThis(78))
    {
      readThis(35);
      readThis(254);
      localHsqlName2 = readNewSchemaObjectName(14, false);
    }
    String str2 = getLastPart();
    Object[] arrayOfObject = { localHsqlName1, localGrantee };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    StatementSchema localStatementSchema1 = new StatementSchema(str2, 64, arrayOfObject, null, arrayOfHsqlName);
    localStatementSchema1.setSchemaHsqlName(localHsqlName1);
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    localHsqlArrayList.add(localStatementSchema1);
    getCompiledStatementBody(localHsqlArrayList);
    StatementSchema[] arrayOfStatementSchema = new StatementSchema[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfStatementSchema);
    int i;
    do
    {
      i = 0;
      for (int j = 0; j < arrayOfStatementSchema.length - 1; j++) {
        if (arrayOfStatementSchema[j].order > arrayOfStatementSchema[(j + 1)].order)
        {
          StatementSchema localStatementSchema2 = arrayOfStatementSchema[(j + 1)];
          arrayOfStatementSchema[(j + 1)] = arrayOfStatementSchema[j];
          arrayOfStatementSchema[j] = localStatementSchema2;
          i = 1;
        }
      }
    } while (i != 0);
    return new StatementSchemaDefinition(arrayOfStatementSchema);
  }
  
  void getCompiledStatementBody(HsqlList paramHsqlList)
  {
    int k = 0;
    while (k == 0)
    {
      StatementSchema localStatementSchema = null;
      int i = getPosition();
      switch (this.token.tokenType)
      {
      case 55: 
        read();
        int j;
        String str;
        switch (this.token.tokenType)
        {
        case 299: 
        case 305: 
        case 497: 
          throw unexpectedToken();
        case 597: 
          j = 1073;
          str = getStatement(i, startStatementTokensSchema);
          localStatementSchema = new StatementSchema(str, j);
          break;
        case 505: 
          localStatementSchema = compileCreateSequence();
          localStatementSchema.sql = getLastPart(i);
          break;
        case 490: 
          localStatementSchema = compileCreateRole();
          localStatementSchema.sql = getLastPart(i);
          break;
        case 393: 
          j = 23;
          str = getStatement(i, startStatementTokensSchema);
          localStatementSchema = new StatementSchema(str, j);
          break;
        case 535: 
          localStatementSchema = compileCreateType(false);
          localStatementSchema.sql = getLastPart(i);
          break;
        case 35: 
          localStatementSchema = compileCreateCharacterSet();
          localStatementSchema.sql = getLastPart(i);
          break;
        case 339: 
          throw unexpectedToken();
        case 120: 
        case 278: 
        case 523: 
        case 570: 
        case 607: 
        case 630: 
        case 631: 
          j = 77;
          str = getStatement(i, startStatementTokensSchema);
          localStatementSchema = new StatementSchema(str, j);
          break;
        case 291: 
          j = 80;
          str = getStatement(i, startStatementTokensSchema);
          localStatementSchema = new StatementSchema(str, j);
          break;
        case 545: 
          j = 84;
          str = getStatement(i, startStatementTokensSchema);
          localStatementSchema = new StatementSchema(str, j);
          break;
        case 117: 
          j = 14;
          str = getStatementForRoutine(i, startStatementTokensSchema);
          localStatementSchema = new StatementSchema(str, j);
          break;
        case 215: 
          j = 14;
          str = getStatementForRoutine(i, startStatementTokensSchema);
          localStatementSchema = new StatementSchema(str, j);
          break;
        default: 
          throw unexpectedToken();
        }
        break;
      case 121: 
        localStatementSchema = compileGrantOrRevoke();
        localStatementSchema.sql = getLastPart(i);
        break;
      case 791: 
        read();
        k = 1;
        break;
      case 848: 
        k = 1;
        break;
      default: 
        throw unexpectedToken();
      }
      if (localStatementSchema != null)
      {
        localStatementSchema.isSchemaDefinition = true;
        paramHsqlList.add(localStatementSchema);
      }
    }
  }
  
  StatementSchema compileCreateRole()
  {
    read();
    HsqlNameManager.HsqlName localHsqlName = readNewUserIdentifier();
    String str = getLastPart();
    Object[] arrayOfObject = { localHsqlName };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str, 61, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileCreateUser()
  {
    Boolean localBoolean1 = Boolean.FALSE;
    Boolean localBoolean2 = Boolean.FALSE;
    Grantee localGrantee = this.session.getGrantee();
    read();
    HsqlNameManager.HsqlName localHsqlName = readNewUserIdentifier();
    readThis(615);
    if (readIfThis(586)) {
      localBoolean2 = Boolean.TRUE;
    }
    String str1 = readPassword();
    if (this.token.tokenType == 335)
    {
      read();
      localBoolean1 = Boolean.TRUE;
    }
    checkDatabaseUpdateAuthorisation();
    String str2 = getLastPart();
    Object[] arrayOfObject = { localHsqlName, str1, localGrantee, localBoolean1, localBoolean2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.catalogNameArray;
    return new StatementSchema(str2, 1074, arrayOfObject, null, arrayOfHsqlName);
  }
  
  HsqlNameManager.HsqlName readNewUserIdentifier()
  {
    checkIsSimpleName();
    String str = this.token.tokenString;
    boolean bool = isDelimitedIdentifier();
    if (str.equalsIgnoreCase("SA"))
    {
      str = "SA";
      bool = false;
    }
    HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newHsqlName(str, bool, 11);
    read();
    return localHsqlName;
  }
  
  String readPassword()
  {
    String str = this.token.tokenString;
    if ((isUndelimitedSimpleName()) || (isDelimitedSimpleName())) {
      read();
    } else {
      readQuotedString();
    }
    return str;
  }
  
  Statement compileRenameObject(HsqlNameManager.HsqlName paramHsqlName, int paramInt)
  {
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(paramInt, true);
    String str = getLastPart();
    switch (paramInt)
    {
    case 1: 
      break;
    case 2: 
      checkSchemaUpdateAuthorisation(this.session, paramHsqlName);
      break;
    default: 
      paramHsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
      checkSchemaUpdateAuthorisation(this.session, paramHsqlName.schema);
    }
    Object[] arrayOfObject = { paramHsqlName, localHsqlName };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
    return new StatementSchema(str, 1192, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableAddUniqueConstraint(Table paramTable, HsqlNameManager.HsqlName paramHsqlName)
  {
    if (paramHsqlName == null) {
      paramHsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5);
    }
    int[] arrayOfInt = readColumnList(paramTable, false);
    HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newAutoName("IDX", paramHsqlName.name, paramTable.getSchemaName(), paramTable.getName(), 20);
    Index localIndex = paramTable.createIndexStructure(localHsqlName, arrayOfInt, null, null, true, true, false);
    Constraint localConstraint = new Constraint(paramHsqlName, paramTable, localIndex, 2);
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1082), paramTable, localConstraint };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableAddForeignKeyConstraint(Table paramTable, HsqlNameManager.HsqlName paramHsqlName)
  {
    if (paramHsqlName == null) {
      paramHsqlName = this.database.nameManager.newAutoName("FK", paramTable.getSchemaName(), paramTable.getName(), 5);
    }
    OrderedHashSet localOrderedHashSet = readColumnNames(false);
    Constraint localConstraint = readFKReferences(paramTable, paramHsqlName, localOrderedHashSet);
    HsqlNameManager.HsqlName localHsqlName = localConstraint.getMainTableName();
    localConstraint.core.mainTable = this.database.schemaManager.getTable(this.session, localHsqlName.name, localHsqlName.schema.name);
    localConstraint.setColumnsIndexes(paramTable);
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1082), paramTable, localConstraint };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    if ((localHsqlName != null) && (localHsqlName != paramTable.getName())) {
      arrayOfHsqlName = (HsqlNameManager.HsqlName[])ArrayUtil.toAdjustedArray(arrayOfHsqlName, localHsqlName, arrayOfHsqlName.length, 1);
    }
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableAddCheckConstraint(Table paramTable, HsqlNameManager.HsqlName paramHsqlName)
  {
    if (paramHsqlName == null) {
      paramHsqlName = this.database.nameManager.newAutoName("CT", paramTable.getSchemaName(), paramTable.getName(), 5);
    }
    Constraint localConstraint = new Constraint(paramHsqlName, null, 3);
    readCheckConstraintCondition(localConstraint);
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1082), paramTable, localConstraint };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = { this.database.getCatalogName(), paramTable.getName() };
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableAddColumn(Table paramTable)
  {
    int i = paramTable.getColumnCount();
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Constraint localConstraint = new Constraint(null, null, 5);
    localHsqlArrayList.add(localConstraint);
    checkIsSchemaObjectName();
    HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newColumnHsqlName(paramTable.getName(), this.token.tokenString, isDelimitedIdentifier());
    read();
    ColumnSchema localColumnSchema = readColumnDefinitionOrNull(paramTable, localHsqlName, localHsqlArrayList);
    if (localColumnSchema == null) {
      throw Error.error(5000);
    }
    if (this.token.tokenType == 343)
    {
      read();
      i = paramTable.getColumnIndex(this.token.tokenString);
      read();
    }
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1081), paramTable, localColumnSchema, new Integer(i), localHsqlArrayList };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableAddPrimaryKey(Table paramTable, HsqlNameManager.HsqlName paramHsqlName)
  {
    if (paramHsqlName == null) {
      paramHsqlName = this.session.database.nameManager.newAutoName("PK", paramTable.getSchemaName(), paramTable.getName(), 5);
    }
    OrderedHashSet localOrderedHashSet = readColumnNames(false);
    Constraint localConstraint = new Constraint(paramHsqlName, localOrderedHashSet, 4);
    localConstraint.setColumnsIndexes(paramTable);
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1082), paramTable, localConstraint };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterTableDropColumn(Table paramTable, String paramString, boolean paramBoolean)
  {
    int i = paramTable.getColumnIndex(paramString);
    if (paramTable.getColumnCount() == 1) {
      throw Error.error(5591);
    }
    String str = getLastPart();
    Object[] arrayOfObject = { paramTable.getColumn(i).getName(), ValuePool.getInt(9), Boolean.valueOf(paramBoolean), Boolean.valueOf(false) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 1076, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterColumn(Table paramTable, ColumnSchema paramColumnSchema, int paramInt)
  {
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    int i = getPosition();
    Object localObject1;
    Object localObject2;
    switch (this.token.tokenType)
    {
    case 622: 
      read();
      readThis(285);
      return compileAlterColumnRename(paramTable, paramColumnSchema);
    case 88: 
      read();
      if (this.token.tokenType == 78)
      {
        read();
        localObject1 = getLastPart();
        localObject2 = new Object[] { Integer.valueOf(1088), paramTable, paramColumnSchema, Integer.valueOf(paramInt) };
        return new StatementSchema((String)localObject1, 4, (Object[])localObject2, null, arrayOfHsqlName);
      }
      if (this.token.tokenType == 407)
      {
        read();
        localObject1 = getLastPart();
        localObject2 = new Object[] { Integer.valueOf(1089), paramTable, paramColumnSchema, Integer.valueOf(paramInt) };
        return new StatementSchema((String)localObject1, 4, (Object[])localObject2, null, arrayOfHsqlName);
      }
      throw unexpectedToken();
    case 254: 
      read();
      switch (this.token.tokenType)
      {
      case 378: 
        read();
        readThis(535);
        return compileAlterColumnDataType(paramTable, paramColumnSchema);
      case 78: 
        read();
        localObject1 = paramColumnSchema.getDataType();
        localObject2 = readDefaultClause((Type)localObject1);
        String str = getLastPart();
        Object[] arrayOfObject = { Integer.valueOf(1087), paramTable, paramColumnSchema, Integer.valueOf(paramInt), localObject2 };
        return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
      case 183: 
        read();
        readThis(186);
        return compileAlterColumnSetNullability(paramTable, paramColumnSchema, false);
      case 186: 
        read();
        return compileAlterColumnSetNullability(paramTable, paramColumnSchema, true);
      case 407: 
        return compileAlterColumnAddSequence(paramTable, paramColumnSchema, paramInt);
      }
      rewind(i);
      read();
    case 407: 
      return compileAlterColumnAddSequence(paramTable, paramColumnSchema, paramInt);
    }
    if ((this.token.tokenType == 254) || (this.token.tokenType == 484))
    {
      if (!paramColumnSchema.isIdentity()) {
        throw Error.error(5535);
      }
      return compileAlterColumnSequenceOptions(paramTable, paramColumnSchema, paramInt);
    }
    return compileAlterColumnDataTypeIdentity(paramTable, paramColumnSchema);
  }
  
  private Statement compileAlterColumnDataTypeIdentity(Table paramTable, ColumnSchema paramColumnSchema)
  {
    if (paramColumnSchema.isGenerated()) {
      throw Error.error(5561);
    }
    NumberSequence localNumberSequence = paramColumnSchema.getIdentitySequence();
    Type localType = paramColumnSchema.getDataType();
    if (this.token.tokenType == 128)
    {
      read();
      if (!localType.isIntegralType()) {
        throw Error.error(5561);
      }
      if (localNumberSequence == null) {
        localNumberSequence = new NumberSequence(null, localType);
      }
    }
    else
    {
      localType = readTypeDefinition(false, true);
      switch (this.token.tokenType)
      {
      case 128: 
        if (!localType.isIntegralType()) {
          throw Error.error(5561);
        }
        read();
        if (localNumberSequence == null) {
          localNumberSequence = new NumberSequence(null, localType);
        }
        break;
      case 407: 
        localNumberSequence = readSequence(paramColumnSchema);
        break;
      default: 
        localNumberSequence = null;
      }
    }
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1090), paramTable, paramColumnSchema, localType, localNumberSequence };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  private Statement compileAlterColumnDataType(Table paramTable, ColumnSchema paramColumnSchema)
  {
    if (paramColumnSchema.isGenerated()) {
      throw Error.error(5561);
    }
    Type localType = readTypeDefinition(false, true);
    if ((paramColumnSchema.isIdentity()) && (!localType.isIntegralType())) {
      throw Error.error(5561);
    }
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1084), paramTable, paramColumnSchema, localType };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  private Statement compileAlterColumnSetNullability(Table paramTable, ColumnSchema paramColumnSchema, boolean paramBoolean)
  {
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1086), paramTable, paramColumnSchema, Boolean.valueOf(paramBoolean) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterSequence()
  {
    read();
    HsqlNameManager.HsqlName localHsqlName = this.session.getSchemaHsqlName(this.token.namePrefix);
    NumberSequence localNumberSequence1 = this.database.schemaManager.getSequence(this.token.tokenString, localHsqlName.name, true);
    read();
    if (this.token.tokenType == 622)
    {
      read();
      readThis(285);
      return compileRenameObject(localNumberSequence1.getName(), 7);
    }
    checkSchemaUpdateAuthorisation(this.session, localNumberSequence1.getName().schema);
    NumberSequence localNumberSequence2 = localNumberSequence1.duplicate();
    readSequenceOptions(localNumberSequence2, false, true, false);
    String str = getLastPart();
    Object[] arrayOfObject = { localNumberSequence1, localNumberSequence2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
    return new StatementSchema(str, 134, arrayOfObject, null, arrayOfHsqlName);
  }
  
  StatementSchema compileAlterColumnAddSequence(Table paramTable, ColumnSchema paramColumnSchema, int paramInt)
  {
    if (!paramColumnSchema.getDataType().isIntegralType()) {
      throw Error.error(5525);
    }
    if (paramColumnSchema.isIdentity()) {
      throw Error.error(5525);
    }
    NumberSequence localNumberSequence = readSequence(paramColumnSchema);
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1085), paramTable, paramColumnSchema, Integer.valueOf(paramInt), localNumberSequence };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(paramTable.getName());
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  NumberSequence readSequence(ColumnSchema paramColumnSchema)
  {
    readThis(407);
    NumberSequence localNumberSequence = new NumberSequence(null, paramColumnSchema.getDataType());
    boolean bool = false;
    if (this.token.tokenType == 24)
    {
      read();
      readThis(78);
    }
    else
    {
      readThis(337);
      bool = true;
    }
    readThis(10);
    readThis(128);
    localNumberSequence.setAlways(bool);
    if (this.token.tokenType == 786)
    {
      read();
      readSequenceOptions(localNumberSequence, false, false, false);
      readThis(772);
    }
    localNumberSequence.checkValues();
    return localNumberSequence;
  }
  
  StatementSchema compileAlterColumnSequenceOptions(Table paramTable, ColumnSchema paramColumnSchema, int paramInt)
  {
    OrderedIntHashSet localOrderedIntHashSet = new OrderedIntHashSet();
    NumberSequence localNumberSequence = paramColumnSchema.getIdentitySequence().duplicate();
    for (;;)
    {
      int i = 0;
      long l;
      switch (this.token.tokenType)
      {
      case 484: 
        if (!localOrderedIntHashSet.add(this.token.tokenType)) {
          throw unexpectedToken();
        }
        read();
        if (readIfThis(319))
        {
          l = readBigint();
          localNumberSequence.setCurrentValueNoCheck(l);
        }
        else
        {
          localNumberSequence.reset();
        }
        break;
      case 254: 
        read();
        switch (this.token.tokenType)
        {
        case 417: 
          if (!localOrderedIntHashSet.add(this.token.tokenType)) {
            throw unexpectedToken();
          }
          read();
          readThis(24);
          l = readBigint();
          localNumberSequence.setIncrement(l);
          break;
        case 180: 
          read();
          if (this.token.tokenType == 438) {
            localNumberSequence.setDefaultMaxValue();
          } else if (this.token.tokenType == 442) {
            localNumberSequence.setDefaultMinValue();
          } else if (this.token.tokenType == 71) {
            localNumberSequence.setCycle(false);
          } else {
            throw unexpectedToken();
          }
          if (!localOrderedIntHashSet.add(this.token.tokenType)) {
            throw unexpectedToken();
          }
          read();
          break;
        case 438: 
          if (!localOrderedIntHashSet.add(this.token.tokenType)) {
            throw unexpectedToken();
          }
          read();
          l = readBigint();
          localNumberSequence.setMaxValueNoCheck(l);
          break;
        case 442: 
          if (!localOrderedIntHashSet.add(this.token.tokenType)) {
            throw unexpectedToken();
          }
          read();
          l = readBigint();
          localNumberSequence.setMinValueNoCheck(l);
          break;
        case 71: 
          if (!localOrderedIntHashSet.add(this.token.tokenType)) {
            throw unexpectedToken();
          }
          read();
          localNumberSequence.setCycle(true);
          break;
        default: 
          throw super.unexpectedToken();
        }
        break;
      default: 
        i = 1;
      }
      if (i != 0) {
        break;
      }
    }
    localNumberSequence.checkValues();
    String str = getLastPart();
    Object[] arrayOfObject = { Integer.valueOf(1085), paramTable, paramColumnSchema, Integer.valueOf(paramInt), localNumberSequence };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = { this.database.getCatalogName(), paramTable.getName() };
    return new StatementSchema(str, 4, arrayOfObject, null, arrayOfHsqlName);
  }
  
  private Statement compileAlterColumnRename(Table paramTable, ColumnSchema paramColumnSchema)
  {
    checkIsSimpleName();
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(9, true);
    if (paramTable.findColumn(localHsqlName.name) > -1) {
      throw Error.error(5504, localHsqlName.name);
    }
    this.database.schemaManager.checkColumnIsReferenced(paramTable.getName(), paramColumnSchema.getName());
    String str = getLastPart();
    Object[] arrayOfObject = { paramColumnSchema.getName(), localHsqlName };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = { this.database.getCatalogName(), paramTable.getName() };
    return new StatementSchema(str, 1192, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterSchemaRename()
  {
    HsqlNameManager.HsqlName localHsqlName1 = readSchemaName();
    checkSchemaUpdateAuthorisation(localHsqlName1);
    readThis(622);
    readThis(285);
    HsqlNameManager.HsqlName localHsqlName2 = readNewSchemaName();
    String str = getLastPart();
    Object[] arrayOfObject = { localHsqlName1, localHsqlName2 };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
    return new StatementSchema(str, 1192, arrayOfObject, null, arrayOfHsqlName);
  }
  
  Statement compileAlterUser()
  {
    read();
    HsqlNameManager.HsqlName localHsqlName1 = readNewUserIdentifier();
    User localUser = this.database.getUserManager().get(localHsqlName1.name);
    if (localHsqlName1.name.equals("PUBLIC")) {
      throw Error.error(5503);
    }
    readThis(254);
    Object[] arrayOfObject;
    switch (this.token.tokenType)
    {
    case 157: 
      read();
      Boolean localBoolean = processTrueOrFalseObject();
      arrayOfObject = new Object[] { localUser, localBoolean };
      return new StatementCommand(1060, arrayOfObject);
    case 615: 
      read();
      boolean bool = false;
      if (readIfThis(586)) {
        bool = Boolean.TRUE.booleanValue();
      }
      String str1 = readPassword();
      arrayOfObject = new Object[] { localUser, str1, Boolean.valueOf(bool) };
      StatementCommand localStatementCommand = new StatementCommand(1062, arrayOfObject);
      String str2 = localUser.getSetPasswordDigestSQL();
      localStatementCommand.setSQL(str2);
      return localStatementCommand;
    case 598: 
      read();
      readThis(497);
      HsqlNameManager.HsqlName localHsqlName2;
      if (this.token.tokenType == 78) {
        localHsqlName2 = null;
      } else {
        localHsqlName2 = this.database.schemaManager.getSchemaHsqlName(this.token.tokenString);
      }
      read();
      arrayOfObject = new Object[] { localUser, localHsqlName2 };
      return new StatementCommand(1061, arrayOfObject);
    }
    throw unexpectedToken();
  }
  
  Statement compileAlterDomain()
  {
    read();
    HsqlNameManager.HsqlName localHsqlName = this.session.getSchemaHsqlName(this.token.namePrefix);
    checkSchemaUpdateAuthorisation(localHsqlName);
    Type localType = this.database.schemaManager.getDomain(this.token.tokenString, localHsqlName.name, true);
    read();
    Object localObject1;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    switch (this.token.tokenType)
    {
    case 622: 
      read();
      readThis(285);
      return compileRenameObject(localType.getName(), 13);
    case 88: 
      read();
      if (this.token.tokenType == 78)
      {
        read();
        localObject1 = getLastPart();
        localObject2 = new Object[] { Integer.valueOf(1080), localType };
        localObject3 = this.database.schemaManager.getCatalogAndBaseTableNames(localType.getName());
        return new StatementSchema((String)localObject1, 3, (Object[])localObject2, null, (HsqlNameManager.HsqlName[])localObject3);
      }
      if (this.token.tokenType == 48)
      {
        read();
        checkIsSchemaObjectName();
        localObject1 = this.database.schemaManager.getSchemaObjectName(localType.getSchemaName(), this.token.tokenString, 5, true);
        read();
        localObject2 = getLastPart();
        localObject3 = new Object[] { Integer.valueOf(1078), localType, localObject1 };
        localObject4 = this.database.schemaManager.getCatalogAndBaseTableNames(localType.getName());
        return new StatementSchema((String)localObject2, 3, (Object[])localObject3, null, (HsqlNameManager.HsqlName[])localObject4);
      }
      throw unexpectedToken();
    case 254: 
      read();
      readThis(78);
      localObject1 = readDefaultClause(localType);
      localObject2 = getLastPart();
      localObject3 = new Object[] { Integer.valueOf(1083), localType, localObject1 };
      localObject4 = this.database.schemaManager.getCatalogAndBaseTableNames(localType.getName());
      return new StatementSchema((String)localObject2, 3, (Object[])localObject3, null, (HsqlNameManager.HsqlName[])localObject4);
    case 334: 
      read();
      if ((this.token.tokenType == 48) || (this.token.tokenType == 37))
      {
        localObject1 = new HsqlArrayList();
        this.compileContext.currentDomain = localType;
        readConstraint(localType, (HsqlArrayList)localObject1);
        this.compileContext.currentDomain = null;
        localObject2 = (Constraint)((HsqlArrayList)localObject1).get(0);
        localObject3 = getLastPart();
        localObject4 = new Object[] { Integer.valueOf(1082), localType, localObject2 };
        HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames(localType.getName());
        return new StatementSchema((String)localObject3, 3, (Object[])localObject4, null, arrayOfHsqlName);
      }
      break;
    }
    throw unexpectedToken();
  }
  
  private boolean isGrantToken()
  {
    switch (this.token.tokenType)
    {
    case 2: 
    case 79: 
    case 100: 
    case 135: 
    case 222: 
    case 251: 
    case 303: 
    case 540: 
      return true;
    }
    return false;
  }
  
  StatementSchema compileGrantOrRevoke()
  {
    boolean bool = this.token.tokenType == 121;
    read();
    if ((isGrantToken()) || ((!bool) && ((this.token.tokenType == 121) || (this.token.tokenType == 411)))) {
      return compileRightGrantOrRevoke(bool);
    }
    return compileRoleGrantOrRevoke(bool);
  }
  
  private StatementSchema compileRightGrantOrRevoke(boolean paramBoolean)
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    Grantee localGrantee = null;
    Right localRight = null;
    HsqlNameManager.HsqlName localHsqlName = null;
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    boolean bool1 = false;
    boolean bool2 = false;
    if (!paramBoolean) {
      if (this.token.tokenType == 121)
      {
        read();
        readThis(455);
        readThis(112);
        bool1 = true;
      }
      else if (this.token.tokenType == 411)
      {
        throw unsupportedFeature();
      }
    }
    if (this.token.tokenType == 2)
    {
      read();
      if (this.token.tokenType == 478) {
        read();
      }
      localRight = Right.fullRights;
      m = 1;
    }
    else
    {
      localRight = new Right();
      n = 1;
      while (n != 0)
      {
        checkIsNotQuoted();
        int i1 = GranteeManager.getCheckSingleRight(this.token.tokenString);
        i2 = this.token.tokenType;
        localObject = null;
        read();
        switch (i2)
        {
        case 135: 
        case 222: 
        case 251: 
        case 303: 
          if (this.token.tokenType == 786) {
            localObject = readColumnNames(false);
          }
        case 291: 
          if (localRight == null) {
            localRight = new Right();
          }
          localRight.set(i1, (OrderedHashSet)localObject);
          i = 1;
          break;
        case 79: 
          if (localRight == null) {
            localRight = new Right();
          }
          localRight.set(i1, null);
          i = 1;
          break;
        case 540: 
          if (i != 0) {
            throw unexpectedToken();
          }
          localRight = Right.fullRights;
          j = 1;
          n = 0;
          break;
        case 100: 
          if (i != 0) {
            throw unexpectedToken();
          }
          localRight = Right.fullRights;
          k = 1;
          n = 0;
          break;
        default: 
          if (this.token.tokenType != 774) {
            break label405;
          }
          read();
        }
      }
    }
    label405:
    readThis(194);
    int n = 0;
    switch (this.token.tokenType)
    {
    case 574: 
      if ((k == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      read();
      if ((!isSimpleName()) || (!isDelimitedIdentifier())) {
        throw Error.error(5569);
      }
      n = 16;
      localHsqlName = readNewSchemaObjectName(16, false);
      break;
    case 259: 
      if ((k == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      read();
      switch (this.token.tokenType)
      {
      case 117: 
      case 215: 
      case 491: 
        read();
        break;
      default: 
        throw unexpectedToken();
      }
      n = 24;
      break;
    case 117: 
      if ((k == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      read();
      n = 16;
      break;
    case 215: 
      if ((k == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      read();
      n = 17;
      break;
    case 491: 
      if ((k == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      read();
      n = 18;
      break;
    case 535: 
      if ((j == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      read();
      n = 12;
      break;
    case 393: 
      if ((j == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      read();
      n = 13;
      break;
    case 505: 
      if ((j == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      read();
      n = 7;
      break;
    case 35: 
      if ((j == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      read();
      readThis(254);
      n = 14;
      break;
    case 278: 
    default: 
      if ((i == 0) && (m == 0)) {
        throw unexpectedToken();
      }
      readIfThis(278);
      n = 3;
    }
    localHsqlName = readNewSchemaObjectName(n, false);
    if (paramBoolean) {
      readThis(285);
    } else {
      readThis(115);
    }
    for (;;)
    {
      checkIsSimpleName();
      localOrderedHashSet.add(this.token.tokenString);
      read();
      if (this.token.tokenType != 774) {
        break;
      }
      read();
    }
    if (paramBoolean)
    {
      if (this.token.tokenType == 319)
      {
        read();
        readThis(121);
        readThis(455);
        bool1 = true;
      }
      if (this.token.tokenType == 410)
      {
        read();
        readThis(24);
        if (this.token.tokenType == 69)
        {
          read();
        }
        else
        {
          readThis(64);
          if (this.session.getRole() == null) {
            throw Error.error(2200);
          }
          localGrantee = this.session.getRole();
        }
      }
    }
    else if (this.token.tokenType == 347)
    {
      bool2 = true;
      read();
    }
    else
    {
      readThis(485);
    }
    String str = getLastPart();
    int i2 = paramBoolean ? 48 : 59;
    Object localObject = { localOrderedHashSet, localHsqlName, localRight, localGrantee, Boolean.valueOf(bool2), Boolean.valueOf(bool1) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
    StatementSchema localStatementSchema = new StatementSchema(str, i2, (Object[])localObject, null, arrayOfHsqlName);
    return localStatementSchema;
  }
  
  private StatementSchema compileRoleGrantOrRevoke(boolean paramBoolean)
  {
    Grantee localGrantee = this.session.getGrantee();
    OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
    OrderedHashSet localOrderedHashSet2 = new OrderedHashSet();
    boolean bool = false;
    if ((!paramBoolean) && (this.token.tokenType == 335)) {
      throw unsupportedFeature();
    }
    for (;;)
    {
      checkIsSimpleName();
      localOrderedHashSet1.add(this.token.tokenString);
      read();
      if (this.token.tokenType != 774) {
        break;
      }
      read();
    }
    if (paramBoolean) {
      readThis(285);
    } else {
      readThis(115);
    }
    for (;;)
    {
      checkIsSimpleName();
      localOrderedHashSet2.add(this.token.tokenString);
      read();
      if (this.token.tokenType != 774) {
        break;
      }
      read();
    }
    if ((paramBoolean) && (this.token.tokenType == 319)) {
      throw unsupportedFeature();
    }
    if (this.token.tokenType == 410)
    {
      read();
      readThis(24);
      if (this.token.tokenType == 69)
      {
        read();
      }
      else
      {
        readThis(64);
        if (this.session.getRole() == null) {
          throw Error.error(2200);
        }
        localGrantee = this.session.getRole();
      }
    }
    if (!paramBoolean) {
      if (this.token.tokenType == 347)
      {
        bool = true;
        read();
      }
      else
      {
        readThis(485);
      }
    }
    String str = getLastPart();
    int i = paramBoolean ? 49 : 129;
    Object[] arrayOfObject = { localOrderedHashSet2, localOrderedHashSet1, localGrantee, Boolean.valueOf(bool) };
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogNameArray();
    StatementSchema localStatementSchema = new StatementSchema(str, i, arrayOfObject, null, arrayOfHsqlName);
    return localStatementSchema;
  }
  
  void checkSchemaUpdateAuthorisation(HsqlNameManager.HsqlName paramHsqlName)
  {
    if (this.session.isProcessingLog) {
      return;
    }
    SqlInvariants.checkSchemaNameNotSystem(paramHsqlName.name);
    if (this.isSchemaDefinition)
    {
      if (paramHsqlName != this.session.getCurrentSchemaHsqlName()) {
        throw Error.error(5505);
      }
    }
    else {
      this.session.getGrantee().checkSchemaUpdateOrGrantRights(paramHsqlName.name);
    }
    this.session.checkDDLWrite();
  }
  
  void checkDatabaseUpdateAuthorisation()
  {
    this.session.checkAdmin();
    this.session.checkDDLWrite();
  }
  
  StatementSchema compileComment()
  {
    readThis(576);
    readThis(194);
    HsqlNameManager.HsqlName localHsqlName;
    switch (this.token.tokenType)
    {
    case 278: 
    case 491: 
      int i = this.token.tokenType == 491 ? 18 : 3;
      read();
      checkIsSchemaObjectName();
      localHsqlName = this.database.nameManager.newHsqlName(this.token.tokenString, this.token.isDelimitedIdentifier, i);
      if (this.token.namePrefix == null) {
        localHsqlName.schema = this.session.getCurrentSchemaHsqlName();
      } else {
        localHsqlName.schema = this.database.nameManager.newHsqlName(this.token.namePrefix, this.token.isDelimitedPrefix, 2);
      }
      read();
      break;
    case 43: 
      read();
      checkIsSchemaObjectName();
      localHsqlName = this.database.nameManager.newHsqlName(this.token.tokenString, this.token.isDelimitedIdentifier, 9);
      if (this.token.namePrefix == null) {
        throw Error.error(5501);
      }
      localHsqlName.parent = this.database.nameManager.newHsqlName(this.token.namePrefix, this.token.isDelimitedPrefix, 3);
      if (this.token.namePrePrefix == null) {
        localHsqlName.parent.schema = this.session.getCurrentSchemaHsqlName();
      } else {
        localHsqlName.parent.schema = this.database.nameManager.newHsqlName(this.token.namePrePrefix, this.token.isDelimitedPrePrefix, 3);
      }
      read();
      break;
    default: 
      throw unexpectedToken();
    }
    readThis(142);
    String str = readQuotedString();
    Object[] arrayOfObject = { localHsqlName, str };
    return new StatementSchema(null, 1071, arrayOfObject);
  }
  
  Statement compileAlterSession()
  {
    read();
    this.session.checkAdmin();
    if (this.token.tokenType == 623)
    {
      read();
      int i = this.token.tokenType;
      switch (this.token.tokenType)
      {
      case 2: 
        read();
        break;
      case 236: 
        read();
        readThis(510);
        break;
      case 278: 
        read();
        readThis(378);
        break;
      default: 
        throw unexpectedTokenRequire("ALL,RESULT,TABLE");
      }
      Object[] arrayOfObject1 = { Long.valueOf(this.session.getId()), Integer.valueOf(i) };
      return new StatementCommand(1005, arrayOfObject1);
    }
    long l = readBigint();
    Session localSession = this.database.sessionManager.getSession(l);
    if (localSession == null) {
      throw Error.error(4500);
    }
    int j = this.token.tokenType;
    switch (this.token.tokenType)
    {
    case 39: 
      read();
      break;
    case 233: 
      read();
      break;
    default: 
      throw unexpectedToken();
    }
    Object[] arrayOfObject2 = { Long.valueOf(l), Integer.valueOf(j) };
    return new StatementCommand(1005, arrayOfObject2);
  }
  
  boolean processTrueOrFalse()
  {
    if (this.token.namePrefix != null) {
      throw unexpectedToken();
    }
    if (this.token.tokenType == 294)
    {
      read();
      return true;
    }
    if (this.token.tokenType == 106)
    {
      read();
      return false;
    }
    throw unexpectedToken();
  }
  
  Boolean processTrueOrFalseObject()
  {
    return Boolean.valueOf(processTrueOrFalse());
  }
  
  void checkSchemaUpdateAuthorisation(Session paramSession, HsqlNameManager.HsqlName paramHsqlName)
  {
    if (paramSession.isProcessingLog) {
      return;
    }
    if (SqlInvariants.isSystemSchemaName(paramHsqlName.name)) {
      throw Error.error(5503);
    }
    if (paramSession.parser.isSchemaDefinition)
    {
      if (paramHsqlName == paramSession.getCurrentSchemaHsqlName()) {
        return;
      }
      Error.error(5505, paramHsqlName.name);
    }
    paramSession.getGrantee().checkSchemaUpdateOrGrantRights(paramHsqlName.name);
    paramSession.checkDDLWrite();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.ParserDDL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */