package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.result.Result;

public class StatementHandler
  extends Statement
{
  public static final int NONE = 0;
  public static final int SQL_EXCEPTION = 1;
  public static final int SQL_WARNING = 2;
  public static final int SQL_NOT_FOUND = 3;
  public static final int SQL_STATE = 4;
  public static final int CONTINUE = 5;
  public static final int EXIT = 6;
  public static final int UNDO = 7;
  public final int handlerType;
  OrderedIntHashSet conditionGroups = new OrderedIntHashSet();
  OrderedHashSet conditionStates = new OrderedHashSet();
  Statement statement;
  public static final StatementHandler[] emptyExceptionHandlerArray = new StatementHandler[0];
  
  StatementHandler(int paramInt)
  {
    super(1202, 2007);
    this.handlerType = paramInt;
  }
  
  public void addConditionState(String paramString)
  {
    boolean bool = this.conditionStates.add(paramString);
    bool &= this.conditionGroups.isEmpty();
    if (!bool) {
      throw Error.error(5604);
    }
  }
  
  public void addConditionType(int paramInt)
  {
    boolean bool = this.conditionGroups.add(paramInt);
    bool &= this.conditionStates.isEmpty();
    if (!bool) {
      throw Error.error(5604);
    }
  }
  
  public void addStatement(Statement paramStatement)
  {
    this.statement = paramStatement;
  }
  
  public boolean handlesConditionType(int paramInt)
  {
    return this.conditionGroups.contains(paramInt);
  }
  
  public boolean handlesCondition(String paramString)
  {
    if (this.conditionStates.contains(paramString)) {
      return true;
    }
    String str = paramString.substring(0, 2);
    if (this.conditionStates.contains(str)) {
      return true;
    }
    if (str.equals("01")) {
      return this.conditionGroups.contains(2);
    }
    if (str.equals("02")) {
      return this.conditionGroups.contains(3);
    }
    return this.conditionGroups.contains(1);
  }
  
  public int[] getConditionTypes()
  {
    return this.conditionGroups.toArray();
  }
  
  public String[] getConditionStates()
  {
    String[] arrayOfString = new String[this.conditionStates.size()];
    this.conditionStates.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public void resolve(Session paramSession)
  {
    if (this.statement != null)
    {
      this.statement.resolve(paramSession);
      this.readTableNames = this.statement.getTableNamesForRead();
      this.writeTableNames = this.statement.getTableNamesForWrite();
    }
  }
  
  public Result execute(Session paramSession)
  {
    if (this.statement != null) {
      return this.statement.execute(paramSession);
    }
    return Result.updateZeroResult;
  }
  
  public String describe(Session paramSession)
  {
    return "";
  }
  
  public OrderedHashSet getReferences()
  {
    if (this.statement == null) {
      return new OrderedHashSet();
    }
    return this.statement.getReferences();
  }
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    String str = this.handlerType == 6 ? "EXIT" : this.handlerType == 5 ? "CONTINUE" : "UNDO";
    localStringBuffer.append("DECLARE").append(' ').append(str).append(' ');
    localStringBuffer.append("HANDLER").append(' ').append("FOR");
    localStringBuffer.append(' ');
    for (int i = 0; i < this.conditionStates.size(); i++)
    {
      if (i > 0) {
        localStringBuffer.append(',');
      }
      localStringBuffer.append("SQLSTATE").append(' ');
      localStringBuffer.append('\'').append(this.conditionStates.get(i)).append('\'');
    }
    for (i = 0; i < this.conditionGroups.size(); i++)
    {
      if (i > 0) {
        localStringBuffer.append(',');
      }
      switch (this.conditionGroups.get(i))
      {
      case 1: 
        localStringBuffer.append("SQLEXCEPTION");
        break;
      case 2: 
        localStringBuffer.append("SQLWARNING");
        break;
      case 3: 
        localStringBuffer.append("NOT").append(' ').append(404);
      }
    }
    if (this.statement != null) {
      localStringBuffer.append(' ').append(this.statement.getSQL());
    }
    return localStringBuffer.toString();
  }
  
  public boolean isCatalogLock()
  {
    return false;
  }
  
  public boolean isCatalogChange()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.StatementHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */