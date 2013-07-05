package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.HsqlList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.result.Result;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rights.User;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Type;

public class ParserCommand extends ParserDDL
{
  ParserCommand(Session paramSession, Scanner paramScanner)
  {
    super(paramSession, paramScanner);
  }

  Statement compileStatement(int paramInt)
  {
    Statement localStatement = compilePart(paramInt);
    if (this.token.tokenType == 848)
    {
      if (localStatement.getSchemaName() == null)
        localStatement.setSchemaHsqlName(this.session.getCurrentSchemaHsqlName());
      return localStatement;
    }
    throw unexpectedToken();
  }

  HsqlArrayList compileStatements(String paramString, Result paramResult)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Statement localStatement = null;
    reset(paramString);
    while (this.token.tokenType != 848)
    {
      try
      {
        this.lastError = null;
        localStatement = compilePart(paramResult.getExecuteProperties());
      }
      catch (HsqlException localHsqlException)
      {
        if ((this.lastError != null) && (this.lastError.getLevel() > localHsqlException.getLevel()))
          throw this.lastError;
        throw localHsqlException;
      }
      if ((!localStatement.isExplain) && (localStatement.getParametersMetaData().getColumnCount() > 0))
        throw Error.error(5575);
      localStatement.setCompileTimestamp(this.database.txManager.getGlobalChangeTimestamp());
      localHsqlArrayList.add(localStatement);
    }
    int i = paramResult.getStatementType();
    if (i != 0)
    {
      int j = localStatement.getGroup();
      if (j == 2003)
      {
        if (i == 1)
          throw Error.error(1253);
      }
      else if (i == 2)
        throw Error.error(1254);
    }
    return localHsqlArrayList;
  }

  private Statement compilePart(int paramInt)
  {
    this.compileContext.reset();
    setParsePosition(getPosition());
    if (this.token.tokenType == 849)
      read();
    Object localObject;
    switch (this.token.tokenType)
    {
    case 251:
    case 278:
    case 319:
    case 786:
      localObject = compileCursorSpecification(RangeGroup.emptyArray, paramInt, false);
      break;
    case 308:
      RangeGroup[] arrayOfRangeGroup = { new RangeGroup.RangeGroupSimple(this.session.sessionContext.sessionVariablesRange) };
      this.compileContext.setOuterRanges(arrayOfRangeGroup);
      localObject = compileShortCursorSpecification(paramInt);
      break;
    case 135:
      localObject = compileInsertStatement(RangeGroup.emptyArray);
      break;
    case 303:
      localObject = compileUpdateStatement(RangeGroup.emptyArray);
      break;
    case 166:
      localObject = compileMergeStatement(RangeGroup.emptyArray);
      break;
    case 79:
      localObject = compileDeleteStatement(RangeGroup.emptyArray);
      break;
    case 295:
      localObject = compileTruncateStatement();
      break;
    case 25:
      localObject = compileCallStatement(new RangeGroup[] { new RangeGroup.RangeGroupSimple(this.session.sessionContext.sessionVariablesRange) }, false);
      break;
    case 254:
      localObject = compileSet();
      break;
    case 119:
      localObject = compileGetStatement(this.session.sessionContext.sessionVariablesRange);
      break;
    case 267:
      localObject = compileStartTransaction();
      break;
    case 44:
      localObject = compileCommit();
      break;
    case 241:
      localObject = compileRollback();
      break;
    case 246:
      localObject = compileSavepoint();
      break;
    case 233:
      localObject = compileReleaseSavepoint();
      break;
    case 55:
      localObject = compileCreate();
      break;
    case 4:
      localObject = compileAlter();
      break;
    case 88:
      localObject = compileDrop();
      break;
    case 121:
    case 239:
      localObject = compileGrantOrRevoke();
      break;
    case 576:
      localObject = compileComment();
      break;
    case 601:
      localObject = compileLock();
      break;
    case 47:
      localObject = compileConnect();
      break;
    case 84:
      localObject = compileDisconnect();
      break;
    case 625:
      localObject = compileScript();
      break;
    case 627:
      localObject = compileShutdown();
      break;
    case 562:
      localObject = compileBackup();
      break;
    case 572:
      localObject = compileCheckpoint();
      break;
    case 588:
      int i = getPosition();
      localObject = compileExplainPlan();
      ((Statement)localObject).setSQL(getLastPart(i));
      break;
    case 77:
      localObject = compileDeclare();
      break;
    default:
      throw unexpectedToken();
    }
    switch (((Statement)localObject).type)
    {
    case 11:
    case 62:
    case 1062:
    case 1191:
      break;
    default:
      ((Statement)localObject).setSQL(getLastPart());
    }
    if (this.token.tokenType == 791)
      read();
    else if (this.token.tokenType != 848);
    return localObject;
  }

  private Statement compileDeclare()
  {
    Object localObject = compileDeclareLocalTableOrNull();
    if (localObject != null)
      return localObject;
    ColumnSchema[] arrayOfColumnSchema = readLocalVariableDeclarationOrNull();
    if (arrayOfColumnSchema != null)
    {
      Object[] arrayOfObject = { arrayOfColumnSchema };
      localObject = new StatementSession(1075, arrayOfObject);
      return localObject;
    }
    localObject = compileDeclareCursor(RangeGroup.emptyArray, false);
    if (localObject == null)
      throw (this.lastError == null ? unexpectedToken() : this.lastError);
    return localObject;
  }

  private Statement compileScript()
  {
    String str = null;
    read();
    if (this.token.tokenType == 845)
      str = readQuotedString();
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames();
    Object[] arrayOfObject = { str };
    return new StatementCommand(1004, arrayOfObject, null, arrayOfHsqlName);
  }

  private Statement compileConnect()
  {
    String str2 = null;
    read();
    readThis(305);
    checkIsSimpleName();
    String str1 = this.token.tokenString;
    read();
    if (!this.session.isProcessingLog)
    {
      readThis(615);
      str2 = readPassword();
    }
    Expression[] arrayOfExpression = { new ExpressionValue(str1, Type.SQL_VARCHAR), new ExpressionValue(str2, Type.SQL_VARCHAR) };
    StatementSession localStatementSession = new StatementSession(76, arrayOfExpression);
    return localStatementSession;
  }

  private StatementCommand compileSetDefault()
  {
    read();
    Object localObject;
    Object[] arrayOfObject;
    int i;
    switch (this.token.tokenType)
    {
    case 598:
      read();
      readThis(497);
      localObject = this.database.schemaManager.getSchemaHsqlName(this.token.tokenString);
      read();
      arrayOfObject = new Object[] { localObject };
      return new StatementCommand(1031, arrayOfObject);
    case 236:
      read();
      readThis(607);
      readThis(245);
      localObject = readIntegerObject();
      arrayOfObject = new Object[] { localObject };
      return new StatementCommand(1040, arrayOfObject);
    case 278:
      read();
      readThis(535);
      i = 4;
      switch (this.token.tokenType)
      {
      case 607:
        break;
      case 570:
        i = 5;
        break;
      default:
        throw unexpectedToken();
      }
      read();
      arrayOfObject = new Object[] { ValuePool.getInt(i) };
      return new StatementCommand(1032, arrayOfObject);
    case 424:
      read();
      readThis(432);
      switch (this.token.tokenType)
      {
      case 480:
        read();
        readThis(365);
        i = 2;
        break;
      case 506:
        read();
        i = 8;
        break;
      default:
        throw unexpectedToken();
      }
      arrayOfObject = new Object[] { ValuePool.getInt(i) };
      return new StatementCommand(1050, arrayOfObject);
    }
    throw unexpectedToken();
  }

  private StatementCommand compileSetProperty()
  {
    read();
    HsqlDatabaseProperties localHsqlDatabaseProperties = this.database.getProperties();
    checkIsSimpleName();
    checkIsDelimitedIdentifier();
    String str = this.token.tokenString;
    boolean bool1 = localHsqlDatabaseProperties.isBoolean(this.token.tokenString);
    boolean bool2 = localHsqlDatabaseProperties.isIntegral(this.token.tokenString);
    boolean bool3 = localHsqlDatabaseProperties.isString(this.token.tokenString);
    if ((!bool1) && (!bool2) && (!bool3))
      throw Error.error(5555, str);
    int i = bool2 ? 4 : bool1 ? 16 : 1;
    read();
    Object localObject;
    if (this.token.tokenType == 294)
    {
      localObject = Boolean.TRUE;
      if (!bool1)
        throw Error.error(5563, this.token.tokenString);
    }
    else if (this.token.tokenType == 106)
    {
      localObject = Boolean.FALSE;
      if (!bool1)
        throw Error.error(5563, this.token.tokenString);
    }
    else
    {
      checkIsValue();
      localObject = this.token.tokenValue;
      if (this.token.dataType.typeCode != i)
        throw Error.error(5563, this.token.tokenString);
    }
    read();
    Object[] arrayOfObject = { str, localObject };
    return new StatementCommand(1035, arrayOfObject);
  }

  private Statement compileSet()
  {
    int i = super.getPosition();
    this.session.setScripting(false);
    read();
    Object localObject1;
    Object localObject3;
    Object localObject4;
    Object localObject6;
    Object localObject7;
    Object localObject2;
    switch (this.token.tokenType)
    {
    case 348:
      read();
      localObject1 = XreadValueSpecificationOrNull();
      if (localObject1 == null)
      {
        localObject3 = readSchemaName();
        localObject4 = new Object[] { localObject3 };
        return new StatementSession(66, (Object[])localObject4);
      }
      if (!((Expression)localObject1).getDataType().isCharacterType())
        throw Error.error(2200);
      if ((((Expression)localObject1).getType() != 1) && ((((Expression)localObject1).getType() != 28) || (!((FunctionSQL)localObject1).isValueFunction())))
        throw Error.error(2200);
      localObject3 = new Expression[] { localObject1 };
      return new StatementSession(66, (Expression[])localObject3);
    case 497:
      read();
      localObject1 = XreadValueSpecificationOrNull();
      if (localObject1 == null)
      {
        localObject3 = readSchemaName();
        localObject4 = new Object[] { localObject3 };
        return new StatementSession(74, (Object[])localObject4);
      }
      if (!((Expression)localObject1).getDataType().isCharacterType())
        throw Error.error(2200);
      if ((((Expression)localObject1).getType() != 1) && ((((Expression)localObject1).getType() != 28) || (!((FunctionSQL)localObject1).isValueFunction())))
        throw Error.error(2200);
      localObject3 = new Expression[] { localObject1 };
      return new StatementSession(74, (Expression[])localObject3);
    case 180:
      read();
      readThis(358);
      localObject1 = null;
      if (readIfThis(112))
      {
        localObject1 = new HsqlArrayList();
        while (true)
        {
          localObject3 = readSchemaObjectName(14);
          ((HsqlArrayList)localObject1).add(localObject3);
          if (this.token.tokenType != 774)
            break;
          read();
        }
      }
      localObject3 = new Object[] { null, Boolean.FALSE, localObject1 };
      return new StatementSession(136, (Object[])localObject3);
    case 358:
      read();
      localObject1 = XreadValueSpecificationOrNull();
      if ((localObject1 == null) || (!((Expression)localObject1).getDataType().isCharacterType()))
        throw Error.error(4650);
      localObject3 = null;
      if (readIfThis(112))
      {
        localObject3 = new HsqlArrayList();
        while (true)
        {
          localObject4 = readSchemaObjectName(14);
          ((HsqlArrayList)localObject3).add(localObject4);
          if (this.token.tokenType != 774)
            break;
          read();
        }
      }
      localObject4 = new Object[] { localObject1, Boolean.TRUE, localObject3 };
      return new StatementSession(136, (Object[])localObject4);
    case 281:
      read();
      return compileSetTimeZone();
    case 490:
      read();
      return compileSetRole();
    case 508:
      read();
      return compileSessionSettings();
    case 526:
      read();
      localObject1 = processTransactionCharacteristics();
      if ((localObject1[0] == null) && (localObject1[1] == null))
        throw unexpectedToken();
      return new StatementSession(75, (Object[])localObject1);
    case 560:
      read();
      localObject1 = processTrueOrFalseObject();
      localObject3 = new Object[] { localObject1 };
      return new StatementSession(1064, (Object[])localObject3);
    case 619:
      read();
      localObject1 = processTrueOrFalseObject();
      localObject3 = new Object[] { localObject1 };
      return new StatementSession(109, (Object[])localObject3);
    case 594:
      read();
      localObject1 = processTrueOrFalseObject();
      localObject3 = new Object[] { localObject1 };
      return new StatementSession(1042, (Object[])localObject3);
    case 605:
      read();
      localObject1 = readIntegerObject();
      localObject3 = new Object[] { localObject1 };
      return new StatementSession(1065, (Object[])localObject3);
    case 78:
      read();
      readThis(278);
      readThis(535);
      int j = 4;
      switch (this.token.tokenType)
      {
      case 607:
        break;
      case 570:
        j = 5;
        break;
      default:
        throw unexpectedToken();
      }
      read();
      localObject3 = new Object[] { ValuePool.getInt(j) };
      return new StatementCommand(1032, (Object[])localObject3);
    case 278:
      read();
      Table localTable = readTableName();
      localObject3 = new Object[] { localTable.getName(), null };
      Object localObject5;
      switch (this.token.tokenType)
      {
      default:
        throw unexpectedToken();
      case 513:
        read();
        return compileTableSource(localTable);
      case 480:
        read();
        boolean bool2 = false;
        if (this.token.tokenType == 547)
        {
          read();
        }
        else
        {
          readThis(195);
          bool2 = true;
        }
        localObject3[1] = Boolean.valueOf(bool2);
        return new StatementCommand(1194, (Object[])localObject3, null, new HsqlNameManager.HsqlName[] { localTable.getName() });
      case 619:
        read();
        localObject5 = processTrueOrFalseObject();
        localObject3[1] = localObject5;
        return new StatementCommand(1194, (Object[])localObject3, null, new HsqlNameManager.HsqlName[] { localTable.getName() });
      case 597:
        read();
        checkIsValue();
        localObject5 = this.token.tokenString;
        read();
        localObject3[1] = localObject5;
        return new StatementCommand(1193, (Object[])localObject3, null, new HsqlNameManager.HsqlName[] { localTable.getName() });
      case 535:
        read();
        int m;
        if (this.token.tokenType == 570)
          m = 5;
        else if (this.token.tokenType == 607)
          m = 4;
        else
          throw super.unexpectedToken();
        read();
        localObject3[1] = new Integer(m);
        return new StatementCommand(1197, (Object[])localObject3, null, new HsqlNameManager.HsqlName[] { localTable.getName() });
      case 575:
      }
      read();
      readThis(194);
      localObject6 = new OrderedHashSet();
      readThis(786);
      readSimpleColumnNames((OrderedHashSet)localObject6, localTable, false);
      readThis(772);
      localObject7 = localTable.getColumnIndexes((OrderedHashSet)localObject6);
      localObject3[1] = localObject7;
      return new StatementCommand(1198, (Object[])localObject3, null, new HsqlNameManager.HsqlName[] { localTable.getName() });
    case 635:
      read();
      int k = 0;
      if (this.token.tokenType == 294)
      {
        k = this.database.getProperties().getDefaultWriteDelay();
        read();
      }
      else if (this.token.tokenType == 106)
      {
        k = 0;
        read();
      }
      else
      {
        k = readInteger();
        if (k < 0)
          k = 0;
        if (this.token.tokenType == 608)
          read();
        else
          k *= 1000;
      }
      localObject3 = new Object[] { new Integer(k) };
      return new StatementCommand(1026, (Object[])localObject3, null, null);
    case 615:
      boolean bool1 = false;
      read();
      if (readIfThis(586))
        bool1 = true;
      localObject2 = readPassword();
      localObject6 = new Object[] { null, localObject2, Boolean.valueOf(bool1) };
      localObject7 = new StatementCommand(1062, (Object[])localObject6);
      String str = User.getSetCurrentPasswordDigestSQL((String)localObject2, bool1);
      ((Statement)localObject7).setSQL(str);
      return localObject7;
    case 598:
      read();
      readThis(497);
      if (this.token.tokenType == 78)
        localObject2 = null;
      else
        localObject2 = this.database.schemaManager.getSchemaHsqlName(this.token.tokenString);
      read();
      Object[] arrayOfObject = { null, localObject2 };
      return new StatementCommand(1061, arrayOfObject);
    case 590:
      return compileSetFilesProperty();
    case 582:
      return compileSetDatabaseProperty();
    case 617:
      return compileSetProperty();
    }
    rewind(i);
    return compileSetStatement(this.session.sessionContext.sessionVariablesRange);
  }

  StatementCommand compileSetDatabaseProperty()
  {
    read();
    checkDatabaseUpdateAuthorisation();
    Object localObject1;
    Object localObject4;
    String str;
    Object localObject5;
    Object localObject2;
    Object localObject6;
    Object localObject3;
    switch (this.token.tokenType)
    {
    case 561:
      read();
      readThis(117);
      localObject1 = readCreateDatabaseAuthenticationFunction();
      localObject4 = new Object[] { localObject1 };
      return new StatementCommand(1033, (Object[])localObject4, null, null);
    case 358:
      localObject1 = null;
      read();
      checkIsSimpleName();
      str = this.token.tokenString;
      read();
      if (readIfThis(180))
      {
        readThis(463);
        localObject1 = Boolean.FALSE;
      }
      else if (readIfThis(463))
      {
        readThis(514);
        localObject1 = Boolean.TRUE;
      }
      if (localObject1 == null)
        if ((this.session.isProcessingScript()) && (this.database.getProperties().isVersion18()))
          localObject1 = Boolean.FALSE;
        else
          localObject1 = Boolean.TRUE;
      localObject4 = new Object[] { str, localObject1 };
      return new StatementCommand(1041, (Object[])localObject4, null, null);
    case 78:
      return compileSetDefault();
    case 587:
      read();
      readThis(697);
      boolean bool1 = readIfThis(261);
      readThis(432);
      localObject4 = readIntegerObject();
      localObject5 = new Object[] { localObject4, Boolean.valueOf(bool1) };
      return new StatementCommand(1015, (Object[])localObject5, null, null);
    case 592:
      read();
      localObject2 = readIntegerObject();
      localObject4 = new Object[] { localObject2 };
      return new StatementCommand(1034, (Object[])localObject4, null, null);
    case 615:
      read();
      readThis(37);
      readThis(117);
      localObject2 = readCreatePasswordCheckFunction();
      localObject4 = new Object[] { localObject2 };
      return new StatementCommand(1036, (Object[])localObject4, null, null);
    case 620:
      read();
      readThis(596);
      boolean bool2 = processTrueOrFalse();
      localObject4 = new Object[] { Boolean.valueOf(bool2) };
      return new StatementCommand(1046, (Object[])localObject4, null, null);
    case 261:
      read();
      int i = 1047;
      localObject4 = Boolean.TRUE;
      localObject5 = Integer.valueOf(0);
      localObject6 = null;
      switch (this.token.tokenType)
      {
      case 446:
        read();
        localObject6 = "sql.enforce_names";
        localObject4 = processTrueOrFalseObject();
        break;
      case 621:
        read();
        readThis(446);
        localObject6 = "sql.regular_names";
        localObject4 = processTrueOrFalseObject();
        break;
      case 222:
        read();
        localObject4 = processTrueOrFalseObject();
        localObject6 = "sql.enforce_refs";
        break;
      case 512:
        read();
        localObject4 = processTrueOrFalseObject();
        localObject6 = "sql.enforce_size";
        break;
      case 633:
        read();
        localObject4 = processTrueOrFalseObject();
        localObject6 = "sql.enforce_types";
        break;
      case 629:
        read();
        if (readIfThis(79))
        {
          localObject6 = "sql.enforce_tdc_delete";
        }
        else
        {
          readThis(303);
          localObject6 = "sql.enforce_tdc_update";
        }
        localObject4 = processTrueOrFalseObject();
        break;
      case 287:
        read();
        readThis(632);
        readThis(633);
        localObject4 = processTrueOrFalseObject();
        localObject6 = "jdbc.translate_tti_types";
        break;
      case 655:
        read();
        readThis(451);
        localObject4 = processTrueOrFalseObject();
        localObject6 = "sql.concat_nulls";
        break;
      case 451:
        read();
        readThis(401);
        localObject4 = processTrueOrFalseObject();
        localObject6 = "sql.nulls_first";
        break;
      case 299:
        read();
        readThis(451);
        localObject4 = processTrueOrFalseObject();
        localObject6 = "sql.unique_nulls";
        break;
      case 49:
        read();
        readThis(295);
        localObject4 = processTrueOrFalseObject();
        localObject6 = "sql.convert_trunc";
        break;
      case 16:
        read();
        readThis(496);
        localObject5 = readIntegerObject();
        localObject6 = "sql.avg_scale";
        break;
      case 87:
        read();
        readThis(610);
        localObject4 = processTrueOrFalseObject();
        localObject6 = "sql.double_nan";
        break;
      case 604:
        read();
        readThis(142);
        readThis(696);
        localObject4 = processTrueOrFalseObject();
        localObject6 = "sql.longvar_is_lob";
        break;
      case 628:
        read();
        if (this.token.tokenString.equals("DB2"))
        {
          read();
          localObject6 = "sql.syntax_db2";
        }
        else if (this.token.tokenString.equals("MSS"))
        {
          read();
          localObject6 = "sql.syntax_mss";
        }
        else if (this.token.tokenString.equals("MYS"))
        {
          read();
          localObject6 = "sql.syntax_mys";
        }
        else if (this.token.tokenString.equals("ORA"))
        {
          read();
          localObject6 = "sql.syntax_ora";
        }
        else if (this.token.tokenString.equals("PGS"))
        {
          read();
          localObject6 = "sql.syntax_pgs";
        }
        else
        {
          throw unexpectedToken();
        }
        localObject4 = processTrueOrFalseObject();
        break;
      default:
        throw unexpectedToken();
      }
      Object[] arrayOfObject2 = { localObject6, localObject4, localObject5 };
      return new StatementCommand(i, arrayOfObject2, null, null);
    case 631:
      read();
      readThis(278);
      readThis(381);
      localObject3 = readQuotedString();
      localObject4 = new Object[] { localObject3 };
      return new StatementCommand(1048, (Object[])localObject4, null, null);
    case 526:
      read();
      if (readIfThis(241))
      {
        readThis(194);
        if (!readIfThis(583))
          readThis(579);
        localObject3 = processTrueOrFalseObject();
        localObject4 = new StatementCommand(1051, new Object[] { localObject3 }, null, null);
        return localObject4;
      }
      readThis(580);
      int j = 0;
      switch (this.token.tokenType)
      {
      case 704:
        read();
        j = 2;
        break;
      case 705:
        read();
        j = 1;
        break;
      case 602:
        read();
        j = 0;
      }
      localObject4 = this.database.schemaManager.getCatalogAndBaseTableNames();
      localObject5 = new Object[] { ValuePool.getInt(j) };
      localObject6 = new StatementCommand(1049, (Object[])localObject5, null, (HsqlNameManager.HsqlName[])localObject4);
      return localObject6;
    case 299:
      read();
      readThis(445);
      isUndelimitedSimpleName();
      str = this.token.tokenString;
      read();
      if (str.length() != 16)
        throw Error.error(5555);
      if ((!Charset.isInSet(str, Charset.unquotedIdentifier)) || (!Charset.startsWith(str, Charset.uppercaseLetters)))
        throw Error.error(5501);
      Object[] arrayOfObject1 = { str };
      return new StatementCommand(1052, arrayOfObject1, null, null);
    }
    throw unexpectedToken();
  }

  StatementCommand compileSetFilesProperty()
  {
    read();
    int i = 0;
    Boolean localBoolean1 = null;
    Integer localInteger = null;
    Boolean localBoolean2 = null;
    checkDatabaseUpdateAuthorisation();
    switch (this.token.tokenType)
    {
    case 569:
      read();
      if (readIfThis(512))
      {
        localInteger = readIntegerObject();
        i = 1013;
      }
      else
      {
        readThis(245);
        localInteger = readIntegerObject();
        i = 1012;
      }
      if (readIfThis(180))
      {
        readThis(37);
        localBoolean2 = Boolean.TRUE;
      }
      break;
    case 496:
      read();
      localInteger = readIntegerObject();
      i = 1023;
      break;
    case 696:
      read();
      readThis(496);
      localInteger = readIntegerObject();
      i = 1016;
      break;
    case 584:
      read();
      i = 1014;
      localInteger = readIntegerObject();
      break;
    case 706:
      read();
      if (readIfThis(512))
        localInteger = readIntegerObject();
      else
        localBoolean1 = processTrueOrFalseObject();
      i = 1020;
      break;
    case 562:
      read();
      i = 1011;
      readThis(417);
      localBoolean1 = processTrueOrFalseObject();
      break;
    case 697:
      read();
      if (readIfThis(512))
      {
        i = 1019;
        localInteger = readIntegerObject();
      }
      else
      {
        i = 1018;
        localBoolean1 = processTrueOrFalseObject();
      }
      break;
    case 630:
      read();
      readThis(472);
      i = 1025;
      localInteger = readIntegerObject();
      break;
    case 547:
      read();
      readThis(585);
      i = 1026;
      int j = 0;
      if (this.token.tokenType == 294)
      {
        j = this.database.getProperties().getDefaultWriteDelay();
        read();
      }
      else if (this.token.tokenType == 106)
      {
        j = 0;
        read();
      }
      else
      {
        j = readInteger();
        if (j < 0)
          j = 0;
        if (this.token.tokenType == 608)
          read();
        else
          j *= 1000;
      }
      localInteger = new Integer(j);
      break;
    case 625:
      read();
      readThis(591);
      if (this.token.tokenType == 631)
      {
        read();
        localInteger = new Integer(0);
      }
      else
      {
        readThis(578);
        localInteger = new Integer(3);
      }
      i = 1024;
      break;
    default:
      throw unexpectedToken();
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = (localBoolean1 == null ? localInteger : localBoolean1);
    arrayOfObject[1] = localBoolean2;
    return new StatementCommand(i, arrayOfObject, null, this.database.schemaManager.getCatalogNameArray());
  }

  Object[] processTransactionCharacteristics()
  {
    int i = 0;
    boolean bool = false;
    Object[] arrayOfObject = new Object[2];
    while (true)
      switch (this.token.tokenType)
      {
      case 480:
        if (arrayOfObject[0] != null)
          throw unexpectedToken();
        read();
        if (this.token.tokenType == 195)
        {
          read();
          bool = true;
        }
        else
        {
          readThis(547);
          bool = false;
        }
        arrayOfObject[0] = Boolean.valueOf(bool);
        break;
      case 424:
        if (arrayOfObject[1] != null)
          throw unexpectedToken();
        read();
        readThis(432);
        switch (this.token.tokenType)
        {
        case 506:
          read();
          i = 8;
          break;
        case 480:
          read();
          if (this.token.tokenType == 365)
          {
            read();
            i = 2;
          }
          else if (this.token.tokenType == 537)
          {
            read();
            i = 1;
          }
          else
          {
            throw unexpectedToken();
          }
          break;
        case 482:
          read();
          readThis(480);
          i = 4;
          break;
        default:
          throw unexpectedToken();
        }
        arrayOfObject[1] = new Integer(i);
        break;
      case 774:
        if ((arrayOfObject[0] == null) && (arrayOfObject[1] == null))
          throw unexpectedToken();
        read();
      }
    if ((!bool) && (i == 1))
      throw unexpectedToken("WRITE");
    return arrayOfObject;
  }

  private Statement compileCommit()
  {
    boolean bool = false;
    read();
    readIfThis(546);
    if (this.token.tokenType == 5)
    {
      read();
      if (this.token.tokenType == 180)
        read();
      else
        bool = true;
      readThis(350);
    }
    String str = bool ? StatementSession.commitAndChainStatement.sql : StatementSession.commitNoChainStatement.sql;
    StatementSession localStatementSession = new StatementSession(11, new Object[] { Boolean.valueOf(bool) });
    localStatementSession.setSQL(str);
    return localStatementSession;
  }

  private Statement compileStartTransaction()
  {
    read();
    readThis(526);
    Object[] arrayOfObject = processTransactionCharacteristics();
    StatementSession localStatementSession = new StatementSession(111, arrayOfObject);
    return localStatementSession;
  }

  private Statement compileLock()
  {
    read();
    readThis(278);
    OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
    OrderedHashSet localOrderedHashSet2 = new OrderedHashSet();
    while (true)
    {
      localObject = readTableName();
      switch (this.token.tokenType)
      {
      case 480:
        read();
        localOrderedHashSet1.add(((Table)localObject).getName());
        break;
      case 547:
        read();
        localOrderedHashSet2.add(((Table)localObject).getName());
        break;
      default:
        throw unexpectedToken();
      }
      if (this.token.tokenType != 774)
        break;
      read();
    }
    Object localObject = new HsqlNameManager.HsqlName[localOrderedHashSet2.size()];
    localOrderedHashSet2.toArray((Object[])localObject);
    localOrderedHashSet1.removeAll((Object[])localObject);
    HsqlNameManager.HsqlName[] arrayOfHsqlName = new HsqlNameManager.HsqlName[localOrderedHashSet1.size()];
    localOrderedHashSet1.toArray(arrayOfHsqlName);
    StatementSession localStatementSession = new StatementSession(1063, arrayOfHsqlName, (HsqlNameManager.HsqlName[])localObject);
    return localStatementSession;
  }

  private Statement compileRollback()
  {
    boolean bool = false;
    String str1 = null;
    read();
    if (this.token.tokenType == 285)
    {
      read();
      readThis(246);
      checkIsSimpleName();
      str1 = this.token.tokenString;
      read();
      str2 = getLastPart();
      localObject = new Object[] { str1 };
      StatementSession localStatementSession = new StatementSession(1067, (Object[])localObject);
      return localStatementSession;
    }
    if (this.token.tokenType == 546)
      read();
    if (this.token.tokenType == 5)
    {
      read();
      if (this.token.tokenType == 180)
        read();
      else
        bool = true;
      readThis(350);
    }
    String str2 = bool ? StatementSession.rollbackAndChainStatement.sql : StatementSession.rollbackNoChainStatement.sql;
    Object localObject = new StatementSession(62, new Object[] { Boolean.valueOf(bool) });
    ((Statement)localObject).setSQL(str2);
    return localObject;
  }

  private Statement compileSavepoint()
  {
    read();
    checkIsSimpleName();
    String str1 = this.token.tokenString;
    read();
    String str2 = getLastPart();
    Object[] arrayOfObject = { str1 };
    return new StatementSession(63, arrayOfObject);
  }

  private Statement compileReleaseSavepoint()
  {
    read();
    readThis(246);
    String str1 = this.token.tokenString;
    read();
    String str2 = getLastPart();
    Object[] arrayOfObject = { str1 };
    return new StatementSession(57, arrayOfObject);
  }

  private Statement compileSessionSettings()
  {
    Object localObject1;
    Object localObject2;
    switch (this.token.tokenType)
    {
    case 354:
      read();
      readThis(10);
      readThis(526);
      localObject1 = processTransactionCharacteristics();
      return new StatementSession(109, (Object[])localObject1);
    case 15:
      read();
      localObject1 = XreadValueSpecificationOrNull();
      if (localObject1 == null)
        throw Error.error(5584);
      ((Expression)localObject1).resolveTypes(this.session, null);
      if (((Expression)localObject1).isUnresolvedParam())
        ((Expression)localObject1).dataType = Type.SQL_VARCHAR;
      if ((((Expression)localObject1).dataType == null) || (!((Expression)localObject1).dataType.isCharacterType()))
        throw Error.error(5563);
      localObject2 = new Expression[] { localObject1, null };
      return new StatementSession(76, (Expression[])localObject2);
    case 236:
      read();
      readThis(607);
      readThis(245);
      localObject1 = readIntegerObject();
      localObject2 = new Object[] { localObject1 };
      return new StatementSession(1066, (Object[])localObject2);
    }
    throw unexpectedToken();
  }

  private Statement compileSetRole()
  {
    Object localObject;
    if (this.token.tokenType == 181)
    {
      read();
      localObject = new ExpressionValue(null, Type.SQL_VARCHAR);
    }
    else
    {
      localObject = XreadValueSpecificationOrNull();
      if (localObject == null)
        throw Error.error(4100);
      if (!((Expression)localObject).getDataType().isCharacterType())
        throw Error.error(2200);
      if ((((Expression)localObject).getType() != 1) && ((((Expression)localObject).getType() != 28) || (!((FunctionSQL)localObject).isValueFunction())))
        throw Error.error(2200);
    }
    String str = getLastPart();
    return new StatementSession(73, new Expression[] { localObject });
  }

  private Statement compileSetTimeZone()
  {
    readThis(549);
    Object localObject1;
    if (this.token.tokenType == 157)
    {
      read();
      localObject1 = new ExpressionValue(null, Type.SQL_INTERVAL_HOUR_TO_MINUTE);
    }
    else
    {
      localObject1 = XreadIntervalValueExpression();
      localObject2 = ((Expression)localObject1).resolveColumnReferences(this.session, RangeGroup.emptyGroup, RangeGroup.emptyArray, null);
      ExpressionColumn.checkColumnsResolved((HsqlList)localObject2);
      ((Expression)localObject1).resolveTypes(this.session, null);
      if (((Expression)localObject1).dataType == null)
        throw Error.error(5563);
      if (((Expression)localObject1).dataType.typeCode != 111)
        throw Error.error(5563);
    }
    Object localObject2 = getLastPart();
    return new StatementSession(71, new Expression[] { localObject1 });
  }

  private Statement compileShutdown()
  {
    this.session.checkAdmin();
    int i = 2;
    read();
    switch (this.token.tokenType)
    {
    case 595:
      i = 1;
      read();
      break;
    case 577:
      i = 3;
      read();
      break;
    case 625:
      i = 4;
      read();
    }
    if (this.token.tokenType == 791)
      read();
    if (this.token.tokenType != 848)
      throw unexpectedToken();
    String str = getLastPart();
    Object[] arrayOfObject = { new Integer(i) };
    StatementCommand localStatementCommand = new StatementCommand(1003, arrayOfObject, null, null);
    return localStatementCommand;
  }

  private Statement compileBackup()
  {
    Boolean localBoolean1 = null;
    Boolean localBoolean2 = null;
    Boolean localBoolean3 = null;
    read();
    readThis(582);
    readThis(285);
    String str = readQuotedString();
    str = str.trim();
    if (str.length() == 0)
      throw unexpectedToken(str);
    while (true)
      switch (this.token.tokenType)
      {
      case 566:
        if (localBoolean1 != null)
          throw unexpectedToken();
        localBoolean1 = Boolean.TRUE;
        read();
        break;
      case 625:
        if (localBoolean2 != null)
          throw unexpectedToken();
        localBoolean2 = Boolean.TRUE;
        read();
        break;
      case 578:
        if (localBoolean3 != null)
          throw unexpectedToken();
        localBoolean3 = Boolean.TRUE;
        read();
        break;
      case 183:
        read();
        if (this.token.tokenType == 578)
        {
          if (localBoolean3 != null)
            throw unexpectedToken();
          localBoolean3 = Boolean.FALSE;
          read();
        }
        else
        {
          if (this.token.tokenType != 566)
            break label230;
          if (localBoolean1 != null)
            throw unexpectedToken();
          localBoolean1 = Boolean.FALSE;
          read();
        }
        break;
      }
    label230: throw unexpectedToken();
    if (localBoolean2 == null)
      localBoolean2 = Boolean.FALSE;
    if (localBoolean1 == null)
      localBoolean1 = Boolean.TRUE;
    if (localBoolean3 == null)
      localBoolean3 = Boolean.TRUE;
    if ((localBoolean2.booleanValue()) && (!localBoolean1.booleanValue()))
      throw unexpectedToken("NOT");
    HsqlNameManager.HsqlName[] arrayOfHsqlName = localBoolean1.booleanValue() ? this.database.schemaManager.getCatalogAndBaseTableNames() : HsqlNameManager.HsqlName.emptyArray;
    Object[] arrayOfObject = { str, localBoolean1, localBoolean2, localBoolean3 };
    StatementCommand localStatementCommand = new StatementCommand(1001, arrayOfObject, null, arrayOfHsqlName);
    return localStatementCommand;
  }

  private Statement compileCheckpoint()
  {
    boolean bool = false;
    read();
    if (this.token.tokenType == 584)
    {
      bool = true;
      read();
    }
    else if (this.token.tokenType == 791)
    {
      read();
    }
    if (this.token.tokenType != 848)
      throw unexpectedToken();
    HsqlNameManager.HsqlName[] arrayOfHsqlName = this.database.schemaManager.getCatalogAndBaseTableNames();
    Object[] arrayOfObject = { Boolean.valueOf(bool) };
    StatementCommand localStatementCommand = new StatementCommand(1002, arrayOfObject, null, arrayOfHsqlName);
    return localStatementCommand;
  }

  public static Statement getAutoCheckpointStatement(Database paramDatabase)
  {
    HsqlNameManager.HsqlName[] arrayOfHsqlName = paramDatabase.schemaManager.getCatalogAndBaseTableNames();
    Object[] arrayOfObject = { Boolean.FALSE };
    StatementCommand localStatementCommand = new StatementCommand(1002, arrayOfObject, null, arrayOfHsqlName);
    localStatementCommand.setCompileTimestamp(paramDatabase.txManager.getGlobalChangeTimestamp());
    localStatementCommand.setSQL("CHECKPOINT");
    return localStatementCommand;
  }

  private Statement compileDisconnect()
  {
    read();
    String str = "DISCONNECT";
    StatementSession localStatementSession = new StatementSession(22, (Object[])null);
    return localStatementSession;
  }

  private Statement compileExplainPlan()
  {
    read();
    readThis(616);
    readThis(112);
    Statement localStatement = compilePart(0);
    localStatement.setDescribe();
    return new StatementCommand(1191, new Object[] { localStatement });
  }

  private Statement compileTableSource(Table paramTable)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = paramTable.getName();
    if (!paramTable.isText())
      localObject = Error.error(321);
    if (this.token.tokenType == 194)
    {
      read();
      localObject = getLastPart();
      arrayOfObject[1] = Boolean.TRUE;
      return new StatementCommand(1195, arrayOfObject, null, new HsqlNameManager.HsqlName[] { paramTable.getName() });
    }
    if (this.token.tokenType == 614)
    {
      read();
      localObject = getLastPart();
      arrayOfObject[1] = Boolean.FALSE;
      return new StatementCommand(1195, arrayOfObject, null, new HsqlNameManager.HsqlName[] { paramTable.getName() });
    }
    if (this.token.tokenType == 593)
    {
      read();
      bool1 = true;
    }
    String str;
    if (this.token.tokenType == 847)
    {
      str = this.token.tokenString;
      read();
    }
    else
    {
      str = readQuotedString();
    }
    if ((!bool1) && (this.token.tokenType == 389))
    {
      bool2 = true;
      read();
    }
    Object localObject = getLastPart();
    arrayOfObject[2] = str;
    arrayOfObject[3] = Boolean.valueOf(bool2);
    arrayOfObject[4] = Boolean.valueOf(bool1);
    int i = bool1 ? 1196 : 1195;
    return new StatementCommand(i, arrayOfObject, null, new HsqlNameManager.HsqlName[] { paramTable.getName() });
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ParserCommand
 * JD-Core Version:    0.6.2
 */