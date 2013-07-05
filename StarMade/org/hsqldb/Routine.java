package org.hsqldb;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.ResultSet;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCResultSet;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.store.BitMap;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.RowType;
import org.hsqldb.types.Type;
import org.hsqldb.types.Types;

public class Routine
  implements SchemaObject, RangeGroup, Cloneable
{
  public static final int NO_SQL = 1;
  public static final int CONTAINS_SQL = 2;
  public static final int READS_SQL = 3;
  public static final int MODIFIES_SQL = 4;
  public static final int LANGUAGE_JAVA = 1;
  public static final int LANGUAGE_SQL = 2;
  public static final int PARAM_STYLE_JAVA = 1;
  public static final int PARAM_STYLE_SQL = 2;
  static final Routine[] emptyArray = new Routine[0];
  RoutineSchema routineSchema;
  private HsqlNameManager.HsqlName name;
  private HsqlNameManager.HsqlName specificName;
  Type[] parameterTypes;
  int typeGroups;
  Type returnType;
  Type[] tableType;
  Table returnTable;
  final int routineType;
  int language = 2;
  int dataImpact = 2;
  int parameterStyle;
  boolean isDeterministic;
  boolean isNullInputOutput;
  boolean isNewSavepointLevel = true;
  int maxDynamicResults = 0;
  boolean isRecursive;
  boolean returnsTable;
  Statement statement;
  boolean isAggregate;
  private String methodName;
  Method javaMethod;
  boolean javaMethodWithConnection;
  private boolean isLibraryRoutine;
  HashMappedList parameterList = new HashMappedList();
  RangeVariable[] ranges = RangeVariable.emptyArray;
  int variableCount;
  OrderedHashSet references;
  Table triggerTable;
  int triggerType;
  int triggerOperation;

  public Routine(int paramInt)
  {
    this.routineType = paramInt;
    this.returnType = Type.SQL_ALL_TYPES;
    this.ranges = new RangeVariable[] { new RangeVariable(this.parameterList, null, false, 3) };
  }

  public Routine(Table paramTable, RangeVariable[] paramArrayOfRangeVariable, int paramInt1, int paramInt2, int paramInt3)
  {
    this.routineType = 8;
    this.returnType = Type.SQL_ALL_TYPES;
    this.dataImpact = paramInt1;
    this.ranges = paramArrayOfRangeVariable;
    this.triggerTable = paramTable;
    this.triggerType = paramInt2;
    this.triggerOperation = paramInt3;
  }

  public int getType()
  {
    return this.routineType;
  }

  public HsqlNameManager.HsqlName getName()
  {
    return this.name;
  }

  public HsqlNameManager.HsqlName getSchemaName()
  {
    if (this.routineType == 8)
      return this.triggerTable.getSchemaName();
    return this.name.schema;
  }

  public HsqlNameManager.HsqlName getCatalogName()
  {
    return this.name.schema.schema;
  }

  public Grantee getOwner()
  {
    return this.name.schema.owner;
  }

  public OrderedHashSet getReferences()
  {
    return this.references;
  }

  public OrderedHashSet getComponents()
  {
    return null;
  }

  public void compile(Session paramSession, SchemaObject paramSchemaObject)
  {
    ParserRoutine localParserRoutine = new ParserRoutine(paramSession, new Scanner(this.statement.getSQL()));
    localParserRoutine.read();
    localParserRoutine.startRecording();
    Statement localStatement = localParserRoutine.compileSQLProcedureStatementOrNull(this, null);
    Token[] arrayOfToken = localParserRoutine.getRecordedStatement();
    String str = Token.getSQL(arrayOfToken);
    localStatement.setSQL(str);
    setProcedure(localStatement);
    localStatement.resolve(paramSession);
    setReferences();
  }

  public String getSQL()
  {
    return getDefinitionSQL(true);
  }

  public String getSQLAlter()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("ALTER").append(' ').append("SPECIFIC");
    localStringBuffer.append(' ').append("ROUTINE").append(' ');
    localStringBuffer.append(this.specificName.getSchemaQualifiedStatementName());
    localStringBuffer.append(' ').append("BODY");
    localStringBuffer.append(' ').append(this.statement.getSQL());
    return localStringBuffer.toString();
  }

  public String getSQLDeclaration()
  {
    return getDefinitionSQL(false);
  }

  private String getDefinitionSQL(boolean paramBoolean)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CREATE").append(' ');
    if (this.isAggregate)
      localStringBuffer.append("AGGREGATE").append(' ');
    if (this.routineType == 17)
      localStringBuffer.append("PROCEDURE");
    else
      localStringBuffer.append("FUNCTION");
    localStringBuffer.append(' ');
    localStringBuffer.append(this.name.getSchemaQualifiedStatementName());
    localStringBuffer.append('(');
    for (int i = 0; i < this.parameterList.size(); i++)
    {
      if (i > 0)
        localStringBuffer.append(',');
      ColumnSchema localColumnSchema = (ColumnSchema)this.parameterList.get(i);
      localStringBuffer.append(localColumnSchema.getSQL());
    }
    localStringBuffer.append(')');
    localStringBuffer.append(' ');
    if (this.routineType == 16)
    {
      localStringBuffer.append("RETURNS");
      localStringBuffer.append(' ');
      if (this.returnsTable)
      {
        localStringBuffer.append("TABLE");
        localStringBuffer.append(this.returnTable.getColumnListWithTypeSQL());
      }
      else
      {
        localStringBuffer.append(this.returnType.getTypeDefinition());
      }
      localStringBuffer.append(' ');
    }
    if (this.specificName != null)
    {
      localStringBuffer.append("SPECIFIC");
      localStringBuffer.append(' ');
      localStringBuffer.append(this.specificName.getStatementName());
      localStringBuffer.append(' ');
    }
    localStringBuffer.append("LANGUAGE");
    localStringBuffer.append(' ');
    if (this.language == 1)
      localStringBuffer.append("JAVA");
    else
      localStringBuffer.append("SQL");
    localStringBuffer.append(' ');
    if (!this.isDeterministic)
    {
      localStringBuffer.append("NOT");
      localStringBuffer.append(' ');
    }
    localStringBuffer.append("DETERMINISTIC");
    localStringBuffer.append(' ');
    localStringBuffer.append(getDataImpactString());
    localStringBuffer.append(' ');
    if (this.routineType == 16)
    {
      if (this.isNullInputOutput)
        localStringBuffer.append("RETURNS").append(' ').append("NULL");
      else
        localStringBuffer.append("CALLED");
      localStringBuffer.append(' ').append("ON").append(' ');
      localStringBuffer.append("NULL").append(' ').append("INPUT");
      localStringBuffer.append(' ');
    }
    else
    {
      if (this.isNewSavepointLevel)
        localStringBuffer.append("NEW");
      else
        localStringBuffer.append("OLD");
      localStringBuffer.append(' ').append("SAVEPOINT").append(' ');
      localStringBuffer.append("LEVEL").append(' ');
      if (this.maxDynamicResults != 0)
      {
        localStringBuffer.append(' ').append("DYNAMIC").append(' ');
        localStringBuffer.append("RESULT").append(' ').append("SETS");
        localStringBuffer.append(' ').append(this.maxDynamicResults).append(' ');
      }
    }
    if (this.language == 1)
    {
      localStringBuffer.append("EXTERNAL").append(' ').append("NAME");
      localStringBuffer.append(' ').append('\'').append(this.methodName).append('\'');
    }
    else if (paramBoolean)
    {
      localStringBuffer.append(this.statement.getSQL());
    }
    else
    {
      localStringBuffer.append("SIGNAL").append(' ');
      localStringBuffer.append("SQLSTATE").append(' ');
      localStringBuffer.append('\'').append("45000").append('\'');
    }
    return localStringBuffer.toString();
  }

  public String getSQLBodyDefinition()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this.language == 1)
    {
      localStringBuffer.append("EXTERNAL").append(' ').append("NAME");
      localStringBuffer.append(' ').append('\'').append(this.methodName).append('\'');
    }
    else
    {
      localStringBuffer.append(this.statement.getSQL());
    }
    return localStringBuffer.toString();
  }

  public String getExternalName()
  {
    if (this.language == 1)
      return this.methodName;
    return null;
  }

  public long getChangeTimestamp()
  {
    return 0L;
  }

  public void addParameter(ColumnSchema paramColumnSchema)
  {
    HsqlNameManager.HsqlName localHsqlName = paramColumnSchema.getName();
    String str = localHsqlName == null ? HsqlNameManager.getAutoNoNameColumnString(this.parameterList.size()) : localHsqlName.name;
    this.parameterList.add(str, paramColumnSchema);
  }

  public void setLanguage(int paramInt)
  {
    this.language = paramInt;
  }

  public int getLanguage()
  {
    return this.language;
  }

  boolean isPSM()
  {
    return this.language == 2;
  }

  public void setDataImpact(int paramInt)
  {
    this.dataImpact = paramInt;
  }

  public int getDataImpact()
  {
    return this.dataImpact;
  }

  public String getDataImpactString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    switch (this.dataImpact)
    {
    case 1:
      localStringBuffer.append("NO").append(' ').append("SQL");
      break;
    case 2:
      localStringBuffer.append("CONTAINS").append(' ').append("SQL");
      break;
    case 3:
      localStringBuffer.append("READS").append(' ').append("SQL").append(' ').append("DATA");
      break;
    case 4:
      localStringBuffer.append("MODIFIES").append(' ').append("SQL").append(' ').append("DATA");
    }
    return localStringBuffer.toString();
  }

  public void setReturnType(Type paramType)
  {
    this.returnType = paramType;
  }

  public Type getReturnType()
  {
    return this.returnType;
  }

  public void setTableType(Type[] paramArrayOfType)
  {
    this.tableType = paramArrayOfType;
  }

  public Type[] getTableType()
  {
    return this.tableType;
  }

  public Table getTable()
  {
    return this.returnTable;
  }

  public void setProcedure(Statement paramStatement)
  {
    this.statement = paramStatement;
  }

  public Statement getProcedure()
  {
    return this.statement;
  }

  public void setSpecificName(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.specificName = paramHsqlName;
  }

  public int getMaxDynamicResults()
  {
    return this.maxDynamicResults;
  }

  public void setName(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.name = paramHsqlName;
  }

  public HsqlNameManager.HsqlName getSpecificName()
  {
    return this.specificName;
  }

  public void setDeterministic(boolean paramBoolean)
  {
    this.isDeterministic = paramBoolean;
  }

  public boolean isDeterministic()
  {
    return this.isDeterministic;
  }

  public void setNullInputOutput(boolean paramBoolean)
  {
    this.isNullInputOutput = paramBoolean;
  }

  public boolean isNullInputOutput()
  {
    return this.isNullInputOutput;
  }

  public void setNewSavepointLevel(boolean paramBoolean)
  {
    this.isNewSavepointLevel = paramBoolean;
  }

  public void setMaxDynamicResults(int paramInt)
  {
    this.maxDynamicResults = paramInt;
  }

  public void setParameterStyle(int paramInt)
  {
    this.parameterStyle = paramInt;
  }

  public void setMethodURL(String paramString)
  {
    this.methodName = paramString;
  }

  public Method getMethod()
  {
    return this.javaMethod;
  }

  public void setMethod(Method paramMethod)
  {
    this.javaMethod = paramMethod;
  }

  public void setReturnTable(TableDerived paramTableDerived)
  {
    this.returnTable = paramTableDerived;
    this.returnsTable = true;
    HsqlNameManager.SimpleName[] arrayOfSimpleName = new HsqlNameManager.SimpleName[paramTableDerived.getColumnCount()];
    Type[] arrayOfType = paramTableDerived.getColumnTypes();
    this.returnType = new RowType(arrayOfType);
  }

  public boolean returnsTable()
  {
    return this.returnsTable;
  }

  public void setAggregate(boolean paramBoolean)
  {
    this.isAggregate = paramBoolean;
  }

  public boolean isAggregate()
  {
    return this.isAggregate;
  }

  public void resolve(Session paramSession)
  {
    setLanguage(this.language);
    if (this.language == 2)
    {
      if (this.dataImpact == 1)
        throw Error.error(5604, "CONTAINS SQL");
      if (this.parameterStyle == 1)
        throw Error.error(5604, "PARAMETER STYLE");
    }
    if ((this.language == 2) && (this.parameterStyle != 0) && (this.parameterStyle != 2))
      throw Error.error(5604, "PARAMETER STYLE");
    this.parameterTypes = new Type[this.parameterList.size()];
    this.typeGroups = 0;
    ColumnSchema localColumnSchema;
    for (int i = 0; i < this.parameterTypes.length; i++)
    {
      localColumnSchema = (ColumnSchema)this.parameterList.get(i);
      this.parameterTypes[i] = localColumnSchema.dataType;
      if (i < 4)
        BitMap.setByte(this.typeGroups, (byte)localColumnSchema.dataType.typeComparisonGroup, i * 8);
    }
    if (this.isAggregate)
    {
      if (this.parameterTypes.length != 4)
        throw Error.error(5610);
      i = this.parameterTypes[1].typeCode == 16 ? 1 : 0;
      localColumnSchema = (ColumnSchema)this.parameterList.get(0);
      i &= (localColumnSchema.getParameterMode() == 1 ? 1 : 0);
      localColumnSchema = (ColumnSchema)this.parameterList.get(1);
      i &= (localColumnSchema.getParameterMode() == 1 ? 1 : 0);
      localColumnSchema = (ColumnSchema)this.parameterList.get(2);
      i &= (localColumnSchema.getParameterMode() == 2 ? 1 : 0);
      localColumnSchema = (ColumnSchema)this.parameterList.get(3);
      i &= (localColumnSchema.getParameterMode() == 2 ? 1 : 0);
      if (i == 0)
        throw Error.error(5610);
    }
    resolveReferences(paramSession);
  }

  void resolveReferences(Session paramSession)
  {
    if (this.statement != null)
    {
      this.statement.resolve(paramSession);
      checkSQLData(paramSession);
    }
    if ((this.methodName != null) && (this.javaMethod == null))
    {
      boolean[] arrayOfBoolean = new boolean[1];
      this.javaMethod = getMethod(this.methodName, this, arrayOfBoolean, this.returnsTable);
      if (this.javaMethod == null)
        throw Error.error(6013);
      this.javaMethodWithConnection = arrayOfBoolean[0];
      String str = this.javaMethod.getDeclaringClass().getName();
      if (str.equals("java.lang.Math"))
        this.isLibraryRoutine = true;
    }
    setReferences();
  }

  private void setReferences()
  {
    OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
    for (int i = 0; i < this.parameterTypes.length; i++)
    {
      ColumnSchema localColumnSchema = (ColumnSchema)this.parameterList.get(i);
      OrderedHashSet localOrderedHashSet2 = localColumnSchema.getReferences();
      if (localOrderedHashSet2 != null)
        localOrderedHashSet1.addAll(localOrderedHashSet2);
    }
    if (this.statement != null)
      localOrderedHashSet1.addAll(this.statement.getReferences());
    this.isRecursive = false;
    if (localOrderedHashSet1.contains(getSpecificName()))
    {
      localOrderedHashSet1.remove(getSpecificName());
      this.isRecursive = true;
    }
    this.references = localOrderedHashSet1;
  }

  void checkSQLData(Session paramSession)
  {
    OrderedHashSet localOrderedHashSet = this.statement.getReferences();
    for (int i = 0; i < localOrderedHashSet.size(); i++)
    {
      HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localOrderedHashSet.get(i);
      if (localHsqlName.type == 24)
      {
        Routine localRoutine = (Routine)paramSession.database.schemaManager.getSchemaObject(localHsqlName);
        if (localRoutine.dataImpact == 3)
        {
          if (this.dataImpact == 2)
            throw Error.error(5608, "READS SQL");
        }
        else if ((localRoutine.dataImpact == 4) && ((this.dataImpact == 2) || (this.dataImpact == 3)))
          throw Error.error(5608, "MODIFIES SQL");
      }
    }
    HsqlNameManager.HsqlName[] arrayOfHsqlName;
    int j;
    if ((this.dataImpact == 2) || (this.dataImpact == 3))
    {
      arrayOfHsqlName = this.statement.getTableNamesForWrite();
      for (j = 0; j < arrayOfHsqlName.length; j++)
        if (arrayOfHsqlName[j].schema != SqlInvariants.MODULE_HSQLNAME)
          throw Error.error(5608, "MODIFIES SQL");
    }
    if (this.dataImpact == 2)
    {
      arrayOfHsqlName = this.statement.getTableNamesForRead();
      for (j = 0; j < arrayOfHsqlName.length; j++)
        if (arrayOfHsqlName[j].schema != SqlInvariants.MODULE_HSQLNAME)
          throw Error.error(5608, "READS SQL");
    }
  }

  public boolean isTrigger()
  {
    return this.routineType == 8;
  }

  public boolean isProcedure()
  {
    return this.routineType == 17;
  }

  public boolean isFunction()
  {
    return this.routineType == 16;
  }

  public ColumnSchema getParameter(int paramInt)
  {
    return (ColumnSchema)this.parameterList.get(paramInt);
  }

  Type[] getParameterTypes()
  {
    return this.parameterTypes;
  }

  int getParameterSignature()
  {
    return this.typeGroups;
  }

  public int getParameterCount()
  {
    return this.parameterTypes.length;
  }

  public int getParameterCount(int paramInt)
  {
    int i = 0;
    for (int j = 0; j < this.parameterList.size(); j++)
    {
      ColumnSchema localColumnSchema = (ColumnSchema)this.parameterList.get(j);
      if (localColumnSchema.getParameterMode() == paramInt)
        i++;
    }
    return i;
  }

  public int getParameterIndex(String paramString)
  {
    return this.parameterList.getIndex(paramString);
  }

  public RangeVariable[] getRangeVariables()
  {
    return this.ranges;
  }

  public void setCorrelated()
  {
  }

  public int getVariableCount()
  {
    return this.variableCount;
  }

  public boolean isLibraryRoutine()
  {
    return this.isLibraryRoutine;
  }

  public HsqlNameManager.HsqlName[] getTableNamesForRead()
  {
    if (this.statement == null)
      return HsqlNameManager.HsqlName.emptyArray;
    return this.statement.getTableNamesForRead();
  }

  public HsqlNameManager.HsqlName[] getTableNamesForWrite()
  {
    if (this.statement == null)
      return HsqlNameManager.HsqlName.emptyArray;
    return this.statement.getTableNamesForWrite();
  }

  public void resetAlteredRoutineSettings()
  {
    if (isPSM())
    {
      this.methodName = null;
      this.javaMethod = null;
      this.javaMethodWithConnection = false;
      this.parameterStyle = 2;
      if (this.dataImpact == 1)
        this.dataImpact = 2;
    }
    else
    {
      this.statement = null;
      this.references = null;
      this.variableCount = 0;
      this.ranges = RangeVariable.emptyArray;
    }
  }

  public void setAsAlteredRoutine(Routine paramRoutine)
  {
    this.language = paramRoutine.language;
    this.dataImpact = paramRoutine.dataImpact;
    this.parameterStyle = paramRoutine.parameterStyle;
    this.isDeterministic = paramRoutine.isDeterministic;
    this.isNullInputOutput = paramRoutine.isNullInputOutput;
    this.maxDynamicResults = paramRoutine.maxDynamicResults;
    this.isRecursive = paramRoutine.isRecursive;
    this.javaMethod = paramRoutine.javaMethod;
    this.isRecursive = paramRoutine.isRecursive;
    this.javaMethodWithConnection = paramRoutine.javaMethodWithConnection;
    this.methodName = paramRoutine.methodName;
    this.statement = paramRoutine.statement;
    this.references = paramRoutine.references;
    this.variableCount = paramRoutine.variableCount;
    this.ranges = paramRoutine.ranges;
  }

  Object[] convertArgsToJava(Session paramSession, Object[] paramArrayOfObject)
  {
    int i = this.javaMethodWithConnection ? 1 : 0;
    Object[] arrayOfObject = new Object[this.javaMethod.getParameterTypes().length];
    Type[] arrayOfType = getParameterTypes();
    for (int j = 0; j < arrayOfType.length; j++)
    {
      Object localObject1 = paramArrayOfObject[j];
      ColumnSchema localColumnSchema = getParameter(j);
      if (localColumnSchema.parameterMode == 1)
      {
        arrayOfObject[(j + i)] = arrayOfType[j].convertSQLToJava(paramSession, localObject1);
      }
      else
      {
        Object localObject2 = arrayOfType[j].convertSQLToJava(paramSession, localObject1);
        Class localClass = arrayOfType[j].getJDBCClass();
        Object localObject3 = Array.newInstance(localClass, 1);
        Array.set(localObject3, 0, localObject2);
        arrayOfObject[(j + i)] = localObject3;
      }
    }
    while (j + i < arrayOfObject.length)
    {
      arrayOfObject[(j + i)] = new ResultSet[1];
      j++;
    }
    return arrayOfObject;
  }

  void convertArgsToSQL(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    int i = this.javaMethodWithConnection ? 1 : 0;
    Type[] arrayOfType = getParameterTypes();
    Object localObject2;
    for (int j = 0; j < arrayOfType.length; j++)
    {
      localObject1 = paramArrayOfObject2[(j + i)];
      localObject2 = getParameter(j);
      if (((ColumnSchema)localObject2).parameterMode != 1)
        localObject1 = Array.get(localObject1, 0);
      paramArrayOfObject1[j] = arrayOfType[j].convertJavaToSQL(paramSession, localObject1);
    }
    Object localObject1 = null;
    while (j + i < paramArrayOfObject2.length)
    {
      localObject2 = ((ResultSet[])(ResultSet[])paramArrayOfObject2[(j + i)])[0];
      if (localObject2 != null)
        if ((localObject2 instanceof JDBCResultSet))
        {
          Result localResult = ((JDBCResultSet)localObject2).result;
          if (localObject1 == null)
          {
            paramArrayOfObject1[j] = localResult;
            localObject1 = localResult;
          }
          else
          {
            localObject1.addChainedResult(localResult);
          }
        }
        else
        {
          Error.error(6000, "ResultSet not native");
        }
      j++;
    }
  }

  public Result invokeJavaMethodDirect(Object[] paramArrayOfObject)
  {
    Result localResult;
    try
    {
      Object localObject = this.javaMethod.invoke(null, paramArrayOfObject);
      localObject = this.returnType.convertJavaToSQL(null, localObject);
      localResult = Result.newPSMResult(localObject);
    }
    catch (Throwable localThrowable)
    {
      localResult = Result.newErrorResult(Error.error(localThrowable, 6000, getName().name), null);
    }
    return localResult;
  }

  Result invokeJavaMethod(Session paramSession, Object[] paramArrayOfObject)
  {
    HsqlNameManager.HsqlName localHsqlName = paramSession.getCurrentSchemaHsqlName();
    Result localResult;
    try
    {
      if (this.dataImpact == 1)
      {
        paramSession.sessionContext.isReadOnly = Boolean.TRUE;
        paramSession.setNoSQL();
      }
      else if (this.dataImpact == 2)
      {
        paramSession.sessionContext.isReadOnly = Boolean.TRUE;
      }
      else if (this.dataImpact == 3)
      {
        paramSession.sessionContext.isReadOnly = Boolean.TRUE;
      }
      paramSession.setCurrentSchemaHsqlName(getSchemaName());
      Object localObject = this.javaMethod.invoke(null, paramArrayOfObject);
      if (returnsTable())
      {
        if ((localObject instanceof JDBCResultSet))
          localResult = ((JDBCResultSet)localObject).result;
        else
          throw Error.runtimeError(201, "FunctionSQLInvoked");
      }
      else
      {
        localObject = this.returnType.convertJavaToSQL(paramSession, localObject);
        localResult = Result.newPSMResult(localObject);
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localResult = Result.newErrorResult(Error.error(localInvocationTargetException, 6000, getName().name), null);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localResult = Result.newErrorResult(Error.error(localIllegalAccessException, 6000, getName().name), null);
    }
    catch (Throwable localThrowable)
    {
      localResult = Result.newErrorResult(Error.error(localThrowable, 6000, getName().name), null);
    }
    paramSession.setCurrentSchemaHsqlName(localHsqlName);
    return localResult;
  }

  public Result invoke(Session paramSession, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, boolean paramBoolean)
  {
    if (paramBoolean)
      paramSession.sessionContext.push();
    Result localResult;
    if (isPSM())
    {
      try
      {
        paramSession.sessionContext.routineArguments = paramArrayOfObject1;
        paramSession.sessionContext.routineVariables = ValuePool.emptyObjectArray;
        if (this.variableCount > 0)
          paramSession.sessionContext.routineVariables = new Object[this.variableCount];
        localResult = this.statement.execute(paramSession);
        if (paramArrayOfObject2 != null)
          for (int i = 0; i < paramArrayOfObject2.length; i++)
            paramArrayOfObject2[i] = paramArrayOfObject1[(i + 1)];
      }
      catch (Throwable localThrowable)
      {
        localResult = Result.newErrorResult(localThrowable);
      }
    }
    else
    {
      if (this.isAggregate)
        paramArrayOfObject1 = convertArgsToJava(paramSession, paramArrayOfObject1);
      localResult = invokeJavaMethod(paramSession, paramArrayOfObject1);
      if (this.isAggregate)
      {
        Object[] arrayOfObject = new Object[paramArrayOfObject1.length];
        convertArgsToSQL(paramSession, arrayOfObject, paramArrayOfObject1);
        for (int j = 0; j < paramArrayOfObject2.length; j++)
          paramArrayOfObject2[j] = arrayOfObject[(j + 1)];
      }
    }
    if (paramBoolean)
      paramSession.sessionContext.pop();
    return localResult;
  }

  public Routine duplicate()
  {
    try
    {
      return (Routine)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    throw Error.runtimeError(201, "Type");
  }

  static Method getMethod(String paramString, Routine paramRoutine, boolean[] paramArrayOfBoolean, boolean paramBoolean)
  {
    int i = paramString.indexOf(':');
    if (i != -1)
    {
      if (!paramString.substring(0, i).equals("CLASSPATH"))
        throw Error.error(6012, paramString);
      paramString = paramString.substring(i + 1);
    }
    Method[] arrayOfMethod = getMethods(paramString);
    int j = -1;
    for (i = 0; i < arrayOfMethod.length; i++)
    {
      int k = 0;
      paramArrayOfBoolean[0] = false;
      Method localMethod = arrayOfMethod[i];
      Class[] arrayOfClass = localMethod.getParameterTypes();
      if ((arrayOfClass.length > 0) && (arrayOfClass[0].equals(Connection.class)))
      {
        k = 1;
        paramArrayOfBoolean[0] = true;
      }
      int m = arrayOfClass.length - k;
      if (paramRoutine.isProcedure())
        for (int n = k; n < arrayOfClass.length; n++)
          if ((arrayOfClass[n].isArray()) && (ResultSet.class.isAssignableFrom(arrayOfClass[n].getComponentType())))
          {
            m = n - k;
            break;
          }
      if (m == paramRoutine.parameterTypes.length)
      {
        if (paramBoolean)
        {
          if (!ResultSet.class.isAssignableFrom(localMethod.getReturnType()))
            continue;
        }
        else
        {
          Type localType1 = Types.getParameterSQLType(localMethod.getReturnType());
          if ((localType1 == null) || (!paramRoutine.returnType.canBeAssignedFrom(localType1)) || (((localType1.isLobType()) || ((!localType1.isBinaryType()) && (!localType1.isCharacterType()))) && (localType1.typeCode != paramRoutine.returnType.typeCode)))
            continue;
        }
        for (int i1 = 0; i1 < paramRoutine.parameterTypes.length; i1++)
        {
          int i2 = 0;
          Class localClass = arrayOfClass[(i1 + k)];
          if ((localClass.isArray()) && (![B.class.equals(localClass)))
          {
            localClass = localClass.getComponentType();
            if (localClass.isPrimitive())
            {
              localMethod = null;
              break;
            }
            i2 = 1;
          }
          Type localType2 = Types.getParameterSQLType(localClass);
          if (localType2 == null)
          {
            localMethod = null;
            break;
          }
          int i3 = paramRoutine.parameterTypes[i1].typeComparisonGroup == localType2.typeComparisonGroup ? 1 : 0;
          if ((i3 != 0) && (paramRoutine.parameterTypes[i1].isNumberType()))
            i3 = paramRoutine.parameterTypes[i1].typeCode == localType2.typeCode ? 1 : 0;
          if ((i2 != 0) && (paramRoutine.getParameter(i1).parameterMode == 1))
            i3 = 0;
          if (i3 == 0)
          {
            localMethod = null;
            if (i1 + k <= j)
              break;
            j = i1 + k;
            break;
          }
        }
        if (localMethod != null)
        {
          for (i1 = 0; i1 < paramRoutine.parameterTypes.length; i1++)
            paramRoutine.getParameter(i1).setNullable(!arrayOfClass[(i1 + k)].isPrimitive());
          return localMethod;
        }
      }
    }
    if (j >= 0)
    {
      ColumnSchema localColumnSchema = paramRoutine.getParameter(j);
      throw Error.error(6021, localColumnSchema.getNameString());
    }
    return null;
  }

  static Method[] getMethods(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    if (i == -1)
      throw Error.error(5501, paramString);
    String str1 = paramString.substring(0, i);
    String str2 = paramString.substring(i + 1);
    Method[] arrayOfMethod = null;
    if (!HsqlDatabaseProperties.supportsJavaMethod(paramString))
      throw Error.error(5501, str1);
    Class localClass1;
    try
    {
      localClass1 = Class.forName(str1, true, Thread.currentThread().getContextClassLoader());
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        localClass1 = Class.forName(str1);
      }
      catch (Throwable localThrowable3)
      {
        throw Error.error(localThrowable3, 5501, 26, new Object[] { localThrowable3.toString(), str1 });
      }
    }
    try
    {
      arrayOfMethod = localClass1.getMethods();
    }
    catch (Throwable localThrowable2)
    {
      throw Error.error(localThrowable2, 5501, 26, new Object[] { localThrowable2.toString(), str1 });
    }
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    for (i = 0; i < arrayOfMethod.length; i++)
    {
      int j = 0;
      int k = 2147483647;
      Method localMethod = arrayOfMethod[i];
      int m = localMethod.getModifiers();
      if ((localMethod.getName().equals(str2)) && (Modifier.isStatic(m)) && (Modifier.isPublic(m)))
      {
        Class[] arrayOfClass = arrayOfMethod[i].getParameterTypes();
        if ((arrayOfClass.length > 0) && (arrayOfClass[0].equals(Connection.class)))
          j = 1;
        for (int n = j; n < arrayOfClass.length; n++)
        {
          Class localClass2 = arrayOfClass[n];
          if (localClass2.isArray())
          {
            if (![B.class.equals(localClass2))
            {
              localClass2 = localClass2.getComponentType();
              if (localClass2.isPrimitive())
              {
                localMethod = null;
                break;
              }
              if ((ResultSet.class.isAssignableFrom(localClass2)) && (k > n))
                k = n;
            }
            if (n >= k)
            {
              if (ResultSet.class.isAssignableFrom(localClass2))
                continue;
              localMethod = null;
              break;
            }
          }
          else if (n > k)
          {
            localMethod = null;
            break;
          }
          Type localType2 = Types.getParameterSQLType(localClass2);
          if (localType2 == null)
          {
            localMethod = null;
            break;
          }
        }
        if (localMethod != null)
          if (ResultSet.class.isAssignableFrom(localMethod.getReturnType()))
          {
            localHsqlArrayList.add(arrayOfMethod[i]);
          }
          else
          {
            Type localType1 = Types.getParameterSQLType(localMethod.getReturnType());
            if (localType1 != null)
              localHsqlArrayList.add(arrayOfMethod[i]);
          }
      }
    }
    arrayOfMethod = new Method[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfMethod);
    return arrayOfMethod;
  }

  public static Routine[] newRoutines(Session paramSession, Method[] paramArrayOfMethod)
  {
    Routine[] arrayOfRoutine = new Routine[paramArrayOfMethod.length];
    for (int i = 0; i < paramArrayOfMethod.length; i++)
    {
      Method localMethod = paramArrayOfMethod[i];
      arrayOfRoutine[i] = newRoutine(paramSession, localMethod);
    }
    return arrayOfRoutine;
  }

  public static Routine newRoutine(Session paramSession, Method paramMethod)
  {
    Routine localRoutine = new Routine(16);
    int i = 0;
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    String str1 = paramMethod.getDeclaringClass().getName();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CLASSPATH:");
    localStringBuffer.append(paramMethod.getDeclaringClass().getName()).append('.');
    localStringBuffer.append(paramMethod.getName());
    if ((arrayOfClass.length > 0) && (arrayOfClass[0].equals(Connection.class)))
      i = 1;
    String str2 = localStringBuffer.toString();
    if (str1.equals("java.lang.Math"))
      localRoutine.isLibraryRoutine = true;
    for (int j = i; j < arrayOfClass.length; j++)
    {
      Type localType2 = Types.getParameterSQLType(arrayOfClass[j]);
      ColumnSchema localColumnSchema = new ColumnSchema(null, localType2, !arrayOfClass[j].isPrimitive(), false, null);
      localRoutine.addParameter(localColumnSchema);
    }
    localRoutine.setLanguage(1);
    localRoutine.setMethod(paramMethod);
    localRoutine.setMethodURL(str2);
    localRoutine.setDataImpact(1);
    Type localType1 = Types.getParameterSQLType(paramMethod.getReturnType());
    localRoutine.javaMethodWithConnection = (i == 1);
    localRoutine.setReturnType(localType1);
    localRoutine.resolve(paramSession);
    return localRoutine;
  }

  public static void createRoutines(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, String paramString)
  {
    Method[] arrayOfMethod = getMethods(paramString);
    Routine[] arrayOfRoutine = newRoutines(paramSession, arrayOfMethod);
    HsqlNameManager.HsqlName localHsqlName = paramSession.database.nameManager.newHsqlName(paramHsqlName, paramString, true, 16);
    for (int i = 0; i < arrayOfRoutine.length; i++)
    {
      arrayOfRoutine[i].setName(localHsqlName);
      paramSession.database.schemaManager.addSchemaObject(arrayOfRoutine[i]);
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.Routine
 * JD-Core Version:    0.6.2
 */