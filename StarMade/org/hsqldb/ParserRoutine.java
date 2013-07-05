package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.LongDeque;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.Type;

public class ParserRoutine extends ParserDML
{
  ParserRoutine(Session paramSession, Scanner paramScanner)
  {
    super(paramSession, paramScanner);
  }

  Expression readDefaultClause(Type paramType)
  {
    Expression localExpression = null;
    int i = 0;
    if (this.token.tokenType == 186)
    {
      read();
      return new ExpressionValue(null, paramType);
    }
    if ((paramType.isDateTimeType()) || (paramType.isIntervalType()));
    Object localObject1;
    switch (this.token.tokenType)
    {
    case 72:
    case 140:
    case 281:
    case 282:
      localExpression = readDateTimeIntervalLiteral(this.session);
      if (localExpression.dataType.typeCode != paramType.typeCode)
        throw unexpectedToken();
      localObject1 = localExpression.getValue(this.session, paramType);
      return new ExpressionValue(localObject1, paramType);
    case 845:
      break;
    default:
      localExpression = XreadDateTimeValueFunctionOrNull();
      if (localExpression != null)
      {
        localExpression = XreadModifier(localExpression);
        break;
        if (paramType.isNumberType())
        {
          if (this.token.tokenType == 784)
          {
            read();
            i = 1;
          }
          else if ((this.database.sqlSyntaxPgs) && (this.token.tokenType == 611))
          {
            return readNextvalFunction();
          }
        }
        else if (paramType.isCharacterType())
        {
          switch (this.token.tokenType)
          {
          case 60:
          case 63:
          case 64:
          case 65:
          case 69:
          case 253:
          case 277:
          case 305:
            localObject1 = FunctionSQL.newSQLFunction(this.token.tokenString, this.compileContext);
            localExpression = readSQLFunction((FunctionSQL)localObject1);
            break;
          }
        }
        else if (paramType.isBooleanType())
        {
          switch (this.token.tokenType)
          {
          case 294:
            read();
            return Expression.EXPR_TRUE;
          case 106:
            read();
            return Expression.EXPR_FALSE;
          }
        }
        else if (paramType.isBitType())
        {
          switch (this.token.tokenType)
          {
          case 294:
            read();
            return new ExpressionValue(BinaryData.singleBitOne, paramType);
          case 106:
            read();
            return new ExpressionValue(BinaryData.singleBitZero, paramType);
          }
        }
        else if (paramType.isArrayType())
        {
          localExpression = readCollection(19);
          if (localExpression.nodes.length > 0)
            throw Error.error(5562);
          localExpression.dataType = paramType;
          return localExpression;
        }
      }
      break;
    }
    if (localExpression != null)
    {
      localExpression.resolveTypes(this.session, null);
      if (paramType.typeComparisonGroup != localExpression.getDataType().typeComparisonGroup)
        throw Error.error(5562);
      return localExpression;
    }
    int j = 0;
    if ((this.database.sqlSyntaxMss) && (this.token.tokenType == 786))
    {
      read();
      j = 1;
    }
    Object localObject2;
    Object localObject3;
    if (this.token.tokenType == 845)
    {
      localObject2 = this.token.tokenValue;
      localObject3 = this.token.dataType;
      Type localType = paramType;
      if (paramType.typeCode == 40)
        localType = Type.getType(12, null, this.database.collation, paramType.precision, 0);
      else if (paramType.typeCode == 30)
        localType = Type.getType(61, null, null, paramType.precision, 0);
      localObject2 = localType.convertToType(this.session, localObject2, (Type)localObject3);
      read();
      if (i != 0)
        localObject2 = paramType.negate(localObject2);
      if (j != 0)
        readThis(772);
      return new ExpressionValue(localObject2, localType);
    }
    if (this.database.sqlSyntaxDb2)
    {
      localObject2 = null;
      switch (paramType.typeComparisonGroup)
      {
      case 12:
        localObject2 = "";
        break;
      case 61:
        localObject2 = BinaryData.zeroLengthBinary;
        break;
      case 2:
        localObject2 = Integer.valueOf(0);
        break;
      case 16:
        localObject2 = Boolean.FALSE;
        break;
      case 40:
        localObject2 = "";
        return new ExpressionValue(localObject2, Type.SQL_VARCHAR_DEFAULT);
      case 30:
        localObject2 = BinaryData.zeroLengthBinary;
        return new ExpressionValue(localObject2, Type.SQL_VARBINARY_DEFAULT);
      case 92:
        localObject3 = FunctionSQL.newSQLFunction("CURRENT_TIME", this.compileContext);
        ((FunctionSQL)localObject3).resolveTypes(this.session, null);
        return localObject3;
      case 91:
        localObject3 = FunctionSQL.newSQLFunction("CURRENT_DATE", this.compileContext);
        ((FunctionSQL)localObject3).resolveTypes(this.session, null);
        return localObject3;
      case 93:
        localObject3 = FunctionSQL.newSQLFunction("CURRENT_TIMESTAMP", this.compileContext);
        ((FunctionSQL)localObject3).resolveTypes(this.session, null);
        return localObject3;
      }
      localObject2 = paramType.convertToDefaultType(this.session, localObject2);
      return new ExpressionValue(localObject2, paramType);
    }
    throw unexpectedToken();
  }

  Statement compileOpenCursorStatement(StatementCompound paramStatementCompound)
  {
    readThis(196);
    checkIsSimpleName();
    String str = this.token.tokenString;
    read();
    for (int i = 0; i < paramStatementCompound.cursors.length; i++)
      if (paramStatementCompound.cursors[i].getCursorName().name.equals(str))
        return paramStatementCompound.cursors[i];
    throw Error.error(4680);
  }

  Statement compileSelectSingleRowStatement(RangeGroup[] paramArrayOfRangeGroup)
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    LongDeque localLongDeque = new LongDeque();
    this.compileContext.setOuterRanges(paramArrayOfRangeGroup);
    QuerySpecification localQuerySpecification = XreadSelect();
    readThis(141);
    RangeVariable[] arrayOfRangeVariable = paramArrayOfRangeGroup[0].getRangeVariables();
    readTargetSpecificationList(localOrderedHashSet, arrayOfRangeVariable, localLongDeque);
    XreadTableExpression(localQuerySpecification);
    localQuerySpecification.setReturningResult();
    int[] arrayOfInt = new int[localLongDeque.size()];
    localLongDeque.toArray(arrayOfInt);
    Expression[] arrayOfExpression = new Expression[localOrderedHashSet.size()];
    localOrderedHashSet.toArray(arrayOfExpression);
    Type[] arrayOfType = new Type[arrayOfExpression.length];
    for (int i = 0; i < arrayOfExpression.length; i++)
    {
      if (arrayOfExpression[i].getColumn().getParameterMode() == 1)
        throw Error.error(2500);
      arrayOfType[i] = arrayOfExpression[i].getDataType();
    }
    localQuerySpecification.setReturningResult();
    localQuerySpecification.resolve(this.session, paramArrayOfRangeGroup, arrayOfType);
    if (localQuerySpecification.getColumnCount() != arrayOfExpression.length)
      throw Error.error(5564, "INTO");
    StatementSet localStatementSet = new StatementSet(this.session, arrayOfExpression, localQuerySpecification, arrayOfInt, this.compileContext);
    return localStatementSet;
  }

  Statement compileGetStatement(RangeVariable[] paramArrayOfRangeVariable)
  {
    read();
    readThis(391);
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    LongDeque localLongDeque = new LongDeque();
    RangeGroup[] arrayOfRangeGroup = { new RangeGroup.RangeGroupSimple(paramArrayOfRangeVariable) };
    readGetClauseList(paramArrayOfRangeVariable, localOrderedHashSet, localLongDeque, localHsqlArrayList);
    if (localHsqlArrayList.size() > 1)
      throw Error.error(5602);
    Expression localExpression = (Expression)localHsqlArrayList.get(0);
    if (localExpression.getDegree() != localOrderedHashSet.size())
      throw Error.error(5546, "SET");
    int[] arrayOfInt = new int[localLongDeque.size()];
    localLongDeque.toArray(arrayOfInt);
    Expression[] arrayOfExpression = new Expression[localOrderedHashSet.size()];
    localOrderedHashSet.toArray(arrayOfExpression);
    for (int i = 0; i < arrayOfExpression.length; i++)
      resolveOuterReferencesAndTypes(arrayOfRangeGroup, arrayOfExpression[i]);
    resolveOuterReferencesAndTypes(arrayOfRangeGroup, localExpression);
    for (i = 0; i < arrayOfExpression.length; i++)
    {
      if (arrayOfExpression[i].getColumn().getParameterMode() == 1)
        throw Error.error(2500);
      if (!arrayOfExpression[i].getDataType().canBeAssignedFrom(localExpression.getNodeDataType(i)))
        throw Error.error(5561);
    }
    StatementSet localStatementSet = new StatementSet(this.session, arrayOfExpression, localExpression, arrayOfInt, this.compileContext);
    return localStatementSet;
  }

  StatementSet compileSetStatement(RangeVariable[] paramArrayOfRangeVariable)
  {
    read();
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    LongDeque localLongDeque = new LongDeque();
    readSetClauseList(paramArrayOfRangeVariable, localOrderedHashSet, localLongDeque, localHsqlArrayList);
    if (localHsqlArrayList.size() > 1)
      throw Error.error(5602);
    Expression localExpression = (Expression)localHsqlArrayList.get(0);
    if (localExpression.getDegree() != localOrderedHashSet.size())
      throw Error.error(5546, "SET");
    int[] arrayOfInt = new int[localLongDeque.size()];
    localLongDeque.toArray(arrayOfInt);
    Expression[] arrayOfExpression = new Expression[localOrderedHashSet.size()];
    localOrderedHashSet.toArray(arrayOfExpression);
    for (int i = 0; i < arrayOfExpression.length; i++)
      resolveOuterReferencesAndTypes(paramArrayOfRangeVariable, arrayOfExpression[i]);
    resolveOuterReferencesAndTypes(paramArrayOfRangeVariable, localExpression);
    for (i = 0; i < arrayOfExpression.length; i++)
    {
      ColumnSchema localColumnSchema = arrayOfExpression[i].getColumn();
      if (localColumnSchema.getParameterMode() == 1)
        throw Error.error(2500, localColumnSchema.getName().statementName);
      if (!arrayOfExpression[i].getDataType().canBeAssignedFrom(localExpression.getNodeDataType(i)))
        throw Error.error(5561);
    }
    StatementSet localStatementSet = new StatementSet(this.session, arrayOfExpression, localExpression, arrayOfInt, this.compileContext);
    return localStatementSet;
  }

  StatementDMQL compileTriggerSetStatement(Table paramTable, RangeGroup[] paramArrayOfRangeGroup)
  {
    read();
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    RangeVariable[] arrayOfRangeVariable = { paramArrayOfRangeGroup[0].getRangeVariables()[1] };
    LongDeque localLongDeque = new LongDeque();
    readSetClauseList(arrayOfRangeVariable, localOrderedHashSet, localLongDeque, localHsqlArrayList);
    int[] arrayOfInt = new int[localLongDeque.size()];
    localLongDeque.toArray(arrayOfInt);
    Expression[] arrayOfExpression2 = new Expression[localOrderedHashSet.size()];
    localOrderedHashSet.toArray(arrayOfExpression2);
    for (int i = 0; i < arrayOfExpression2.length; i++)
      resolveOuterReferencesAndTypes(RangeGroup.emptyArray, arrayOfExpression2[i]);
    Expression[] arrayOfExpression1 = new Expression[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfExpression1);
    resolveUpdateExpressions(paramTable, RangeGroup.emptyGroup, arrayOfInt, arrayOfExpression1, paramArrayOfRangeGroup);
    StatementSet localStatementSet = new StatementSet(this.session, arrayOfExpression2, paramTable, paramArrayOfRangeGroup[0].getRangeVariables(), arrayOfInt, arrayOfExpression1, this.compileContext);
    return localStatementSet;
  }

  StatementSchema compileAlterSpecificRoutine()
  {
    boolean bool = false;
    readThis(259);
    readThis(491);
    Routine localRoutine = (Routine)readSchemaObjectName(24);
    localRoutine = localRoutine.duplicate();
    readRoutineCharacteristics(localRoutine);
    bool = readIfThis(485);
    if (bool)
    {
      localObject = this.database.schemaManager.getReferencesTo(localRoutine.getSpecificName());
      if (!((OrderedHashSet)localObject).isEmpty())
        throw Error.error(5502);
    }
    if (this.token.tokenType == 567)
      read();
    else if (this.token.tokenType == 445)
      read();
    readRoutineBody(localRoutine);
    localRoutine.resetAlteredRoutineSettings();
    localRoutine.resolve(this.session);
    Object localObject = { localRoutine };
    String str = getLastPart();
    StatementSchema localStatementSchema = new StatementSchema(str, 17, (Object[])localObject, null, this.database.schemaManager.getCatalogNameArray());
    return localStatementSchema;
  }

  StatementSchema compileCreateProcedureOrFunction(boolean paramBoolean)
  {
    boolean bool = false;
    if (this.token.tokenType == 559)
    {
      bool = true;
      read();
      if (this.token.tokenType == 215)
        throw super.unexpectedToken();
    }
    int i = this.token.tokenType == 215 ? 17 : 16;
    read();
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(i, true);
    localHsqlName.setSchemaIfNull(this.session.getCurrentSchemaHsqlName());
    Routine localRoutine = new Routine(i);
    localRoutine.setName(localHsqlName);
    localRoutine.setAggregate(bool);
    readThis(786);
    if (this.token.tokenType == 772)
      read();
    else
      while (true)
      {
        localObject = readRoutineParameter(localRoutine, true);
        localRoutine.addParameter((ColumnSchema)localObject);
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
    if (i != 17)
    {
      readThis(238);
      if (this.token.tokenType == 278)
      {
        read();
        localObject = new TableDerived(this.database, SqlInvariants.MODULE_HSQLNAME, 11);
        readTableDefinition(localRoutine, (Table)localObject);
        localRoutine.setReturnTable((TableDerived)localObject);
      }
      else
      {
        localObject = readTypeDefinition(false, true);
        localRoutine.setReturnType((Type)localObject);
      }
    }
    readRoutineCharacteristics(localRoutine);
    readRoutineBody(localRoutine);
    Object localObject = { localRoutine };
    String str = getLastPart();
    StatementSchema localStatementSchema = new StatementSchema(str, 14, (Object[])localObject, null, this.database.schemaManager.getCatalogNameArray());
    return localStatementSchema;
  }

  Routine readCreatePasswordCheckFunction()
  {
    Routine localRoutine = new Routine(16);
    if (this.token.tokenType == 181)
    {
      read();
      return null;
    }
    if (this.token.tokenType == 104)
    {
      localRoutine.setLanguage(1);
      localRoutine.setDataImpact(1);
    }
    else
    {
      localRoutine.setLanguage(2);
      localRoutine.setDataImpact(2);
    }
    HsqlNameManager.HsqlName localHsqlName = this.database.nameManager.newHsqlName("PASSWORD", false, 16);
    localHsqlName.setSchemaIfNull(SqlInvariants.SYSTEM_SCHEMA_HSQLNAME);
    localRoutine.setName(localHsqlName);
    localHsqlName = this.database.nameManager.newHsqlName("PASSWORD", false, 23);
    ColumnSchema localColumnSchema = new ColumnSchema(localHsqlName, Type.SQL_VARCHAR, false, false, null);
    localRoutine.addParameter(localColumnSchema);
    localRoutine.setReturnType(Type.SQL_BOOLEAN);
    readRoutineBody(localRoutine);
    localRoutine.resolve(this.session);
    return localRoutine;
  }

  Routine readCreateDatabaseAuthenticationFunction()
  {
    Routine localRoutine = new Routine(16);
    if (this.token.tokenType == 181)
    {
      read();
      return null;
    }
    checkIsThis(104);
    localRoutine.setLanguage(1);
    localRoutine.setDataImpact(1);
    localRoutine.setName(this.database.nameManager.newHsqlName("AUTHENTICATION", false, 16));
    for (int i = 0; i < 3; i++)
    {
      ColumnSchema localColumnSchema = new ColumnSchema(null, Type.SQL_VARCHAR, false, false, null);
      localRoutine.addParameter(localColumnSchema);
    }
    localRoutine.setReturnType(new ArrayType(Type.SQL_VARCHAR_DEFAULT, 1024));
    readRoutineBody(localRoutine);
    localRoutine.resolve(this.session);
    return localRoutine;
  }

  private void readTableDefinition(Routine paramRoutine, Table paramTable)
    throws HsqlException
  {
    readThis(786);
    for (int i = 0; ; i++)
    {
      ColumnSchema localColumnSchema = readRoutineParameter(paramRoutine, false);
      if (localColumnSchema.getName() == null)
        throw super.unexpectedToken();
      paramTable.addColumn(localColumnSchema);
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
    paramTable.createPrimaryKey();
  }

  private void readRoutineCharacteristics(Routine paramRoutine)
  {
    OrderedIntHashSet localOrderedIntHashSet = new OrderedIntHashSet();
    int i = 0;
    while (i == 0)
      switch (this.token.tokenType)
      {
      case 146:
        if (!localOrderedIntHashSet.add(146))
          throw unexpectedToken();
        read();
        if (this.token.tokenType == 425)
        {
          read();
          paramRoutine.setLanguage(1);
        }
        else if (this.token.tokenType == 261)
        {
          read();
          paramRoutine.setLanguage(2);
        }
        else
        {
          throw unexpectedToken();
        }
        break;
      case 204:
        if (!localOrderedIntHashSet.add(204))
          throw unexpectedToken();
        read();
        readThis(519);
        if (this.token.tokenType == 425)
        {
          read();
          paramRoutine.setParameterStyle(1);
        }
        else
        {
          readThis(261);
          paramRoutine.setParameterStyle(2);
        }
        break;
      case 259:
        if (!localOrderedIntHashSet.add(259))
          throw unexpectedToken();
        read();
        HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(24, false);
        paramRoutine.setSpecificName(localHsqlName);
        break;
      case 83:
        if (!localOrderedIntHashSet.add(83))
          throw unexpectedToken();
        read();
        paramRoutine.setDeterministic(true);
        break;
      case 183:
        if (!localOrderedIntHashSet.add(83))
          throw unexpectedToken();
        read();
        readThis(83);
        paramRoutine.setDeterministic(false);
        break;
      case 171:
        if (!localOrderedIntHashSet.add(261))
          throw unexpectedToken();
        if (paramRoutine.getType() == 16)
          throw unexpectedToken();
        read();
        readThis(261);
        readThis(378);
        paramRoutine.setDataImpact(4);
        break;
      case 180:
        if (!localOrderedIntHashSet.add(261))
          throw unexpectedToken();
        read();
        readThis(261);
        paramRoutine.setDataImpact(1);
        break;
      case 218:
        if (!localOrderedIntHashSet.add(261))
          throw unexpectedToken();
        read();
        readThis(261);
        readThis(378);
        paramRoutine.setDataImpact(3);
        break;
      case 375:
        if (!localOrderedIntHashSet.add(261))
          throw unexpectedToken();
        read();
        readThis(261);
        paramRoutine.setDataImpact(2);
        break;
      case 238:
        if ((!localOrderedIntHashSet.add(186)) || (paramRoutine.isProcedure()))
          throw unexpectedToken();
        if (paramRoutine.isAggregate())
          throw Error.error(5604, this.token.tokenString);
        read();
        readThis(186);
        readThis(194);
        readThis(186);
        readThis(419);
        paramRoutine.setNullInputOutput(true);
        break;
      case 26:
        if ((!localOrderedIntHashSet.add(186)) || (paramRoutine.isProcedure()))
          throw unexpectedToken();
        read();
        readThis(194);
        readThis(186);
        readThis(419);
        paramRoutine.setNullInputOutput(false);
        break;
      case 89:
        if ((!localOrderedIntHashSet.add(236)) || (paramRoutine.isFunction()))
          throw unexpectedToken();
        read();
        readThis(236);
        readThis(510);
        int j = readInteger();
        if ((j < 0) || (j > 16))
          throw Error.error(5604, String.valueOf(j));
        paramRoutine.setMaxDynamicResults(j);
        break;
      case 179:
        if ((paramRoutine.getType() == 16) || (!localOrderedIntHashSet.add(246)))
          throw unexpectedToken();
        read();
        readThis(246);
        readThis(432);
        paramRoutine.setNewSavepointLevel(true);
        break;
      case 193:
        if ((paramRoutine.getType() == 16) || (!localOrderedIntHashSet.add(246)))
          throw unexpectedToken();
        read();
        readThis(246);
        readThis(432);
        paramRoutine.setNewSavepointLevel(false);
        throw super.unsupportedFeature("OLD");
      default:
        i = 1;
      }
  }

  void readRoutineBody(Routine paramRoutine)
  {
    if (this.token.tokenType == 104)
    {
      if (paramRoutine.getLanguage() != 1)
        throw unexpectedToken();
      read();
      readThis(445);
      checkIsValue(1);
      paramRoutine.setMethodURL((String)this.token.tokenValue);
      read();
      if (this.token.tokenType == 204)
      {
        read();
        readThis(519);
        readThis(425);
      }
    }
    else
    {
      startRecording();
      Statement localStatement = compileSQLProcedureStatementOrNull(paramRoutine, null);
      if (localStatement == null)
        throw unexpectedToken();
      Token[] arrayOfToken = getRecordedStatement();
      String str = Token.getSQL(arrayOfToken);
      localStatement.setSQL(str);
      paramRoutine.setProcedure(localStatement);
    }
  }

  private Object[] readLocalDeclarationList(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    int i = 0;
    RangeGroup[] arrayOfRangeGroup = new RangeGroup[1];
    arrayOfRangeGroup[0] = (paramStatementCompound == null ? paramRoutine : paramStatementCompound);
    this.compileContext.setOuterRanges(arrayOfRangeGroup);
    while (this.token.tokenType == 77)
    {
      localObject = null;
      if (i == 0)
      {
        localObject = readLocalTableVariableDeclarationOrNull(paramRoutine);
        if (localObject == null)
        {
          i = 1;
        }
        else
        {
          localHsqlArrayList.add(localObject);
          readThis(791);
        }
      }
      else if (i == 1)
      {
        localObject = readLocalVariableDeclarationOrNull();
        if (localObject == null)
          i = 2;
        else
          localHsqlArrayList.addAll((Object[])localObject);
      }
      else if (i == 2)
      {
        localObject = compileDeclareCursor(arrayOfRangeGroup, true);
        if (localObject == null)
        {
          i = 3;
        }
        else
        {
          localHsqlArrayList.add(localObject);
          readThis(791);
        }
      }
      else if (i == 3)
      {
        localObject = compileLocalHandlerDeclaration(paramRoutine, paramStatementCompound);
        localHsqlArrayList.add(localObject);
      }
    }
    Object localObject = new Object[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(localObject);
    return localObject;
  }

  Table readLocalTableVariableDeclarationOrNull(Routine paramRoutine)
  {
    int i = super.getPosition();
    readThis(77);
    if (this.token.tokenType == 278)
    {
      read();
      HsqlNameManager.HsqlName localHsqlName = super.readNewSchemaObjectName(3, false);
      localHsqlName.schema = SqlInvariants.MODULE_HSQLNAME;
      Table localTable = new Table(this.database, localHsqlName, 11);
      readTableDefinition(paramRoutine, localTable);
      return localTable;
    }
    rewind(i);
    return null;
  }

  ColumnSchema[] readLocalVariableDeclarationOrNull()
  {
    int i = super.getPosition();
    HsqlNameManager.HsqlName[] arrayOfHsqlName = HsqlNameManager.HsqlName.emptyArray;
    Type localType;
    try
    {
      readThis(77);
      if (isReservedKey())
      {
        rewind(i);
        return null;
      }
      while (true)
      {
        arrayOfHsqlName = (HsqlNameManager.HsqlName[])ArrayUtil.resizeArray(arrayOfHsqlName, arrayOfHsqlName.length + 1);
        arrayOfHsqlName[(arrayOfHsqlName.length - 1)] = super.readNewSchemaObjectName(22, false);
        if (this.token.tokenType != 774)
          break;
        read();
      }
      localType = readTypeDefinition(false, true);
    }
    catch (HsqlException localHsqlException)
    {
      rewind(i);
      return null;
    }
    Expression localExpression = null;
    if (this.token.tokenType == 78)
    {
      read();
      localExpression = readDefaultClause(localType);
    }
    ColumnSchema[] arrayOfColumnSchema = new ColumnSchema[arrayOfHsqlName.length];
    for (int j = 0; j < arrayOfHsqlName.length; j++)
    {
      arrayOfColumnSchema[j] = new ColumnSchema(arrayOfHsqlName[j], localType, true, false, localExpression);
      arrayOfColumnSchema[j].setParameterMode((byte)2);
    }
    readThis(791);
    return arrayOfColumnSchema;
  }

  private StatementHandler compileLocalHandlerDeclaration(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    readThis(77);
    int i;
    switch (this.token.tokenType)
    {
    case 376:
      read();
      i = 5;
      break;
    case 102:
      read();
      i = 6;
      break;
    case 297:
      read();
      i = 7;
      break;
    default:
      throw unexpectedToken();
    }
    readThis(124);
    readThis(112);
    StatementHandler localStatementHandler = new StatementHandler(i);
    int j = 0;
    int k = 1;
    label301: 
    while (j == 0)
    {
      int m = 0;
      switch (this.token.tokenType)
      {
      case 774:
        if (k != 0)
          throw unexpectedToken();
        read();
        k = 1;
        break;
      case 263:
        m = 4;
      case 262:
        if (m == 0)
          m = 1;
      case 264:
        if (m == 0)
          m = 2;
      case 183:
        if (m == 0)
          m = 3;
        if (k == 0)
          throw unexpectedToken();
        k = 0;
        read();
        if (m == 3)
        {
          readThis(404);
        }
        else if (m == 4)
        {
          String str = parseSQLStateValue();
          localStatementHandler.addConditionState(str);
          break label301;
        }
        localStatementHandler.addConditionType(m);
        break;
      }
      if (k != 0)
        throw unexpectedToken();
      j = 1;
    }
    if (this.token.tokenType == 791)
    {
      read();
    }
    else
    {
      Statement localStatement = compileSQLProcedureStatementOrNull(paramRoutine, paramStatementCompound);
      if (localStatement == null)
        throw unexpectedToken();
      readThis(791);
      localStatementHandler.addStatement(localStatement);
    }
    return localStatementHandler;
  }

  String parseSQLStateValue()
  {
    readIfThis(307);
    checkIsValue(1);
    String str = this.token.tokenString;
    if (this.token.tokenString.length() != 5)
      throw Error.error(5607);
    read();
    return str;
  }

  private Statement compileCompoundStatement(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName)
  {
    readThis(17);
    readThis(14);
    StatementCompound localStatementCompound = new StatementCompound(12, paramHsqlName);
    localStatementCompound.setAtomic(true);
    localStatementCompound.setRoot(paramRoutine);
    localStatementCompound.setParent(paramStatementCompound);
    Object[] arrayOfObject = readLocalDeclarationList(paramRoutine, paramStatementCompound);
    localStatementCompound.setLocalDeclarations(arrayOfObject);
    this.session.sessionContext.pushRoutineTables(localStatementCompound.scopeTables);
    try
    {
      Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, localStatementCompound);
      localStatementCompound.setStatements(arrayOfStatement);
    }
    finally
    {
      this.session.sessionContext.popRoutineTables();
    }
    readThis(94);
    if ((isSimpleName()) && (!isReservedKey()))
    {
      if (paramHsqlName == null)
        throw unexpectedToken();
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString);
      read();
    }
    return localStatementCompound;
  }

  private Statement[] compileSQLProcedureStatementList(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    while (true)
    {
      Statement localStatement = compileSQLProcedureStatementOrNull(paramRoutine, paramStatementCompound);
      if (localStatement == null)
        break;
      readThis(791);
      localHsqlArrayList.add(localStatement);
    }
    if (localHsqlArrayList.size() == 0)
      throw unexpectedToken();
    Statement[] arrayOfStatement = new Statement[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfStatement);
    return arrayOfStatement;
  }

  Statement compileSQLProcedureStatementOrNull(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    Object localObject1 = null;
    HsqlNameManager.HsqlName localHsqlName1 = null;
    StatementCompound localStatementCompound = paramStatementCompound == null ? paramRoutine : paramStatementCompound;
    RangeVariable[] arrayOfRangeVariable = localStatementCompound.getRangeVariables();
    RangeGroup[] arrayOfRangeGroup = { localStatementCompound };
    if ((!paramRoutine.isTrigger()) && (isSimpleName()) && (!isReservedKey()))
    {
      localHsqlName1 = readNewSchemaObjectName(21, false);
      if (this.token.tokenType != 773)
        throw unexpectedToken(localHsqlName1.getNameString());
      readThis(773);
    }
    this.compileContext.reset();
    HsqlNameManager.HsqlName localHsqlName2 = this.session.getCurrentSchemaHsqlName();
    this.session.setCurrentSchemaHsqlName(paramRoutine.getSchemaName());
    try
    {
      switch (this.token.tokenType)
      {
      case 196:
        if (paramRoutine.dataImpact == 2)
          throw Error.error(5602, paramRoutine.getDataImpactString());
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileOpenCursorStatement(paramStatementCompound);
        break;
      case 251:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileSelectSingleRowStatement(arrayOfRangeGroup);
        break;
      case 135:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileInsertStatement(arrayOfRangeGroup);
        break;
      case 303:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileUpdateStatement(arrayOfRangeGroup);
        break;
      case 79:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileDeleteStatement(arrayOfRangeGroup);
        break;
      case 295:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileTruncateStatement();
        break;
      case 166:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileMergeStatement(arrayOfRangeGroup);
        break;
      case 254:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        if (paramRoutine.isTrigger())
        {
          if ((paramRoutine.triggerType == 4) && (paramRoutine.triggerOperation != 19))
          {
            int i = super.getPosition();
            try
            {
              localObject1 = compileTriggerSetStatement(paramRoutine.triggerTable, arrayOfRangeGroup);
            }
            catch (HsqlException localHsqlException)
            {
              rewind(i);
              localObject1 = compileSetStatement(arrayOfRangeVariable);
              break label551;
            }
          }
          else
          {
            localObject1 = compileSetStatement(arrayOfRangeVariable);
          }
          ((StatementSet)localObject1).checkIsNotColumnTarget();
        }
        else
        {
          localObject1 = compileSetStatement(arrayOfRangeVariable);
        }
        break;
      case 119:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileGetStatement(arrayOfRangeVariable);
        break;
      case 25:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileCallStatement(arrayOfRangeGroup, true);
        localObject2 = ((StatementProcedure)localObject1).procedure;
        if (localObject2 != null)
          switch (paramRoutine.dataImpact)
          {
          case 2:
            if ((((Routine)localObject2).dataImpact == 3) || (((Routine)localObject2).dataImpact == 4))
              throw Error.error(5602, paramRoutine.getDataImpactString());
            break;
          case 3:
            if (((Routine)localObject2).dataImpact == 4)
              throw Error.error(5602, paramRoutine.getDataImpactString());
            break;
          }
        break;
      case 237:
        if ((paramRoutine.isTrigger()) || (localHsqlName1 != null))
          throw unexpectedToken();
        read();
        localObject1 = compileReturnValue(paramRoutine, paramStatementCompound);
        break;
      case 17:
        localObject1 = compileCompoundStatement(paramRoutine, paramStatementCompound, localHsqlName1);
        break;
      case 322:
        if (paramRoutine.isTrigger())
          throw unexpectedToken();
        localObject1 = compileWhile(paramRoutine, paramStatementCompound, localHsqlName1);
        break;
      case 234:
        localObject1 = compileRepeat(paramRoutine, paramStatementCompound, localHsqlName1);
        break;
      case 160:
        localObject1 = compileLoop(paramRoutine, paramStatementCompound, localHsqlName1);
        break;
      case 112:
        localObject1 = compileFor(paramRoutine, paramStatementCompound, localHsqlName1);
        break;
      case 143:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileIterate();
        break;
      case 152:
        if (localHsqlName1 != null)
          throw unexpectedToken();
        localObject1 = compileLeave(paramRoutine, paramStatementCompound);
        break;
      case 412:
        localObject1 = compileIf(paramRoutine, paramStatementCompound);
        break;
      case 29:
        localObject1 = compileCase(paramRoutine, paramStatementCompound);
        break;
      case 255:
        localObject1 = compileSignal(paramRoutine, paramStatementCompound, localHsqlName1);
        break;
      case 235:
        localObject1 = compileResignal(paramRoutine, paramStatementCompound, localHsqlName1);
        break;
      default:
        label551: localObject2 = null;
        return localObject2;
      }
      ((Statement)localObject1).setRoot(paramRoutine);
      ((Statement)localObject1).setParent(paramStatementCompound);
      Object localObject2 = localObject1;
      return localObject2;
    }
    finally
    {
      this.session.setCurrentSchemaHsqlName(localHsqlName2);
    }
  }

  private Statement compileReturnValue(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    RangeGroup[] arrayOfRangeGroup = new RangeGroup[1];
    arrayOfRangeGroup[0] = (paramStatementCompound == null ? paramRoutine : paramStatementCompound);
    this.compileContext.setOuterRanges(arrayOfRangeGroup);
    Object localObject = XreadValueExpressionOrNull();
    if (localObject == null)
    {
      checkIsValue();
      if (this.token.tokenValue == null)
        localObject = new ExpressionValue(null, null);
    }
    resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, (Expression)localObject);
    if (paramRoutine.isProcedure())
      throw Error.error(5602);
    return new StatementExpression(this.session, this.compileContext, 58, (Expression)localObject);
  }

  private Statement compileIterate()
  {
    readThis(143);
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(21, false);
    return new StatementSimple(102, localHsqlName);
  }

  private Statement compileLeave(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    readThis(152);
    HsqlNameManager.HsqlName localHsqlName = readNewSchemaObjectName(21, false);
    return new StatementSimple(89, localHsqlName);
  }

  private Statement compileWhile(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName)
  {
    readThis(322);
    Expression localExpression = XreadBooleanValueExpression();
    resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, localExpression);
    StatementExpression localStatementExpression = new StatementExpression(this.session, this.compileContext, 1201, localExpression);
    readThis(86);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
    readThis(94);
    readThis(322);
    if ((isSimpleName()) && (!isReservedKey()))
    {
      if (paramHsqlName == null)
        throw unexpectedToken();
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString);
      read();
    }
    StatementCompound localStatementCompound = new StatementCompound(97, paramHsqlName);
    localStatementCompound.setStatements(arrayOfStatement);
    localStatementCompound.setCondition(localStatementExpression);
    return localStatementCompound;
  }

  private Statement compileRepeat(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName)
  {
    readThis(234);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
    readThis(302);
    Expression localExpression = XreadBooleanValueExpression();
    resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, localExpression);
    StatementExpression localStatementExpression = new StatementExpression(this.session, this.compileContext, 1201, localExpression);
    readThis(94);
    readThis(234);
    if ((isSimpleName()) && (!isReservedKey()))
    {
      if (paramHsqlName == null)
        throw unexpectedToken();
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString);
      read();
    }
    StatementCompound localStatementCompound = new StatementCompound(95, paramHsqlName);
    localStatementCompound.setStatements(arrayOfStatement);
    localStatementCompound.setCondition(localStatementExpression);
    return localStatementCompound;
  }

  private Statement compileLoop(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName)
  {
    readThis(160);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
    readThis(94);
    readThis(160);
    if ((isSimpleName()) && (!isReservedKey()))
    {
      if (paramHsqlName == null)
        throw unexpectedToken();
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString);
      read();
    }
    StatementCompound localStatementCompound = new StatementCompound(90, paramHsqlName);
    localStatementCompound.setStatements(arrayOfStatement);
    return localStatementCompound;
  }

  private Statement compileFor(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName)
  {
    RangeGroup[] arrayOfRangeGroup = new RangeGroup[1];
    arrayOfRangeGroup[0] = (paramStatementCompound == null ? paramRoutine : paramStatementCompound);
    this.compileContext.setOuterRanges(arrayOfRangeGroup);
    readThis(112);
    StatementQuery localStatementQuery = compileCursorSpecification(arrayOfRangeGroup, 0, false);
    readThis(86);
    StatementCompound localStatementCompound = new StatementCompound(46, paramHsqlName);
    localStatementCompound.setAtomic(true);
    localStatementCompound.setRoot(paramRoutine);
    localStatementCompound.setParent(paramStatementCompound);
    localStatementCompound.setLoopStatement(localStatementQuery);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, localStatementCompound);
    readThis(94);
    readThis(112);
    if ((isSimpleName()) && (!isReservedKey()))
    {
      if (paramHsqlName == null)
        throw unexpectedToken();
      if (!paramHsqlName.name.equals(this.token.tokenString))
        throw Error.error(5508, this.token.tokenString);
      read();
    }
    localStatementCompound.setStatements(arrayOfStatement);
    return localStatementCompound;
  }

  private Statement compileIf(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    readThis(412);
    Expression localExpression = XreadBooleanValueExpression();
    resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, localExpression);
    StatementExpression localStatementExpression = new StatementExpression(this.session, this.compileContext, 1201, localExpression);
    localHsqlArrayList.add(localStatementExpression);
    readThis(280);
    Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
    for (int i = 0; i < arrayOfStatement.length; i++)
      localHsqlArrayList.add(arrayOfStatement[i]);
    while (this.token.tokenType == 93)
    {
      read();
      localExpression = XreadBooleanValueExpression();
      resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, localExpression);
      localStatementExpression = new StatementExpression(this.session, this.compileContext, 1201, localExpression);
      localHsqlArrayList.add(localStatementExpression);
      readThis(280);
      arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
      for (i = 0; i < arrayOfStatement.length; i++)
        localHsqlArrayList.add(arrayOfStatement[i]);
    }
    if (this.token.tokenType == 92)
    {
      read();
      localExpression = Expression.EXPR_TRUE;
      localStatementExpression = new StatementExpression(this.session, this.compileContext, 1201, localExpression);
      localHsqlArrayList.add(localStatementExpression);
      arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
      for (i = 0; i < arrayOfStatement.length; i++)
        localHsqlArrayList.add(arrayOfStatement[i]);
    }
    readThis(94);
    readThis(412);
    arrayOfStatement = new Statement[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfStatement);
    StatementCompound localStatementCompound = new StatementCompound(88, null);
    localStatementCompound.setStatements(arrayOfStatement);
    return localStatementCompound;
  }

  private Statement compileCase(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Expression localExpression = null;
    readThis(29);
    if (this.token.tokenType == 314)
      localHsqlArrayList = readCaseWhen(paramRoutine, paramStatementCompound);
    else
      localHsqlArrayList = readSimpleCaseWhen(paramRoutine, paramStatementCompound);
    if (this.token.tokenType == 92)
    {
      read();
      localExpression = Expression.EXPR_TRUE;
      StatementExpression localStatementExpression = new StatementExpression(this.session, this.compileContext, 1201, localExpression);
      localHsqlArrayList.add(localStatementExpression);
      arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
      for (int i = 0; i < arrayOfStatement.length; i++)
        localHsqlArrayList.add(arrayOfStatement[i]);
    }
    readThis(94);
    readThis(29);
    Statement[] arrayOfStatement = new Statement[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfStatement);
    StatementCompound localStatementCompound = new StatementCompound(88, null);
    localStatementCompound.setStatements(arrayOfStatement);
    return localStatementCompound;
  }

  private HsqlArrayList readSimpleCaseWhen(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Object localObject1 = null;
    Expression localExpression = XreadRowValuePredicand();
    while (true)
    {
      readThis(314);
      while (true)
      {
        Object localObject2 = XreadPredicateRightPart(localExpression);
        if (localExpression == localObject2)
          localObject2 = new ExpressionLogical(localExpression, XreadRowValuePredicand());
        resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, (Expression)localObject2);
        if (localObject1 == null)
          localObject1 = localObject2;
        else
          localObject1 = new ExpressionLogical(50, (Expression)localObject1, (Expression)localObject2);
        if (this.token.tokenType != 774)
          break;
        read();
      }
      StatementExpression localStatementExpression = new StatementExpression(this.session, this.compileContext, 1201, (Expression)localObject1);
      localHsqlArrayList.add(localStatementExpression);
      readThis(280);
      Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
      for (int i = 0; i < arrayOfStatement.length; i++)
        localHsqlArrayList.add(arrayOfStatement[i]);
      if (this.token.tokenType != 314)
        break;
    }
    return localHsqlArrayList;
  }

  private HsqlArrayList readCaseWhen(Routine paramRoutine, StatementCompound paramStatementCompound)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Expression localExpression = null;
    while (true)
    {
      readThis(314);
      localExpression = XreadBooleanValueExpression();
      resolveOuterReferencesAndTypes(paramRoutine, paramStatementCompound, localExpression);
      StatementExpression localStatementExpression = new StatementExpression(this.session, this.compileContext, 1201, localExpression);
      localHsqlArrayList.add(localStatementExpression);
      readThis(280);
      Statement[] arrayOfStatement = compileSQLProcedureStatementList(paramRoutine, paramStatementCompound);
      for (int i = 0; i < arrayOfStatement.length; i++)
        localHsqlArrayList.add(arrayOfStatement[i]);
      if (this.token.tokenType != 314)
        break;
    }
    return localHsqlArrayList;
  }

  private Statement compileSignal(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName)
  {
    String str2 = null;
    readThis(255);
    readThis(263);
    String str1 = parseSQLStateValue();
    if (readIfThis(254))
    {
      readThis(441);
      readThis(396);
      str2 = readQuotedString();
    }
    StatementSimple localStatementSimple = new StatementSimple(92, str1, str2);
    return localStatementSimple;
  }

  private Statement compileResignal(Routine paramRoutine, StatementCompound paramStatementCompound, HsqlNameManager.HsqlName paramHsqlName)
  {
    String str1 = null;
    String str2 = null;
    readThis(235);
    if (readIfThis(263))
    {
      str1 = parseSQLStateValue();
      if (readIfThis(254))
      {
        readThis(441);
        readThis(396);
        str2 = readQuotedString();
      }
    }
    StatementSimple localStatementSimple = new StatementSimple(91, str1, str2);
    return localStatementSimple;
  }

  private ColumnSchema readRoutineParameter(Routine paramRoutine, boolean paramBoolean)
  {
    HsqlNameManager.HsqlName localHsqlName = null;
    byte b = 1;
    if (paramBoolean)
      switch (this.token.tokenType)
      {
      case 130:
        read();
        break;
      case 199:
        if (paramRoutine.getType() != 17)
          throw unexpectedToken();
        read();
        b = 4;
        break;
      case 133:
        if ((paramRoutine.getType() != 17) && (!paramRoutine.isAggregate()))
          throw unexpectedToken();
        read();
        b = 2;
        break;
      }
    if (!isReservedKey())
      localHsqlName = readNewDependentSchemaObjectName(paramRoutine.getName(), 23);
    Type localType = readTypeDefinition(false, true);
    ColumnSchema localColumnSchema = new ColumnSchema(localHsqlName, localType, true, false, null);
    if (paramBoolean)
      localColumnSchema.setParameterMode(b);
    return localColumnSchema;
  }

  void resolveOuterReferencesAndTypes(Routine paramRoutine, StatementCompound paramStatementCompound, Expression paramExpression)
  {
    StatementCompound localStatementCompound = paramStatementCompound == null ? paramRoutine : paramStatementCompound;
    resolveOuterReferencesAndTypes(new RangeGroup[] { localStatementCompound }, paramExpression);
  }

  void resolveOuterReferencesAndTypes(RangeVariable[] paramArrayOfRangeVariable, Expression paramExpression)
  {
    RangeGroup.RangeGroupSimple localRangeGroupSimple = new RangeGroup.RangeGroupSimple(paramArrayOfRangeVariable);
    resolveOuterReferencesAndTypes(new RangeGroup[] { localRangeGroupSimple }, paramExpression);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ParserRoutine
 * JD-Core Version:    0.6.2
 */