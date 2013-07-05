package org.hsqldb;

import java.math.BigDecimal;
import org.hsqldb.error.Error;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.rights.Grantee;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Type;

public final class NumberSequence
  implements SchemaObject
{
  public static final NumberSequence[] emptyArray = new NumberSequence[0];
  private HsqlNameManager.HsqlName name;
  private long currValue;
  private long lastValue;
  private boolean limitReached;
  private long startValue;
  private long minValue;
  private long maxValue;
  private long increment;
  private Type dataType;
  private boolean isCycle;
  private boolean isAlways;
  private boolean restartValueDefault;

  public NumberSequence()
  {
    try
    {
      setDefaults(null, Type.SQL_BIGINT);
    }
    catch (HsqlException localHsqlException)
    {
    }
  }

  public NumberSequence(HsqlNameManager.HsqlName paramHsqlName, Type paramType)
  {
    setDefaults(paramHsqlName, paramType);
  }

  public void setDefaults(HsqlNameManager.HsqlName paramHsqlName, Type paramType)
  {
    this.name = paramHsqlName;
    this.dataType = paramType;
    this.name = paramHsqlName;
    long l2;
    long l1;
    switch (this.dataType.typeCode)
    {
    case -6:
      l2 = 127L;
      l1 = -128L;
      break;
    case 5:
      l2 = 32767L;
      l1 = -32768L;
      break;
    case 4:
      l2 = 2147483647L;
      l1 = -2147483648L;
      break;
    case 25:
      l2 = 9223372036854775807L;
      l1 = -9223372036854775808L;
      break;
    case 2:
    case 3:
      if (paramType.scale == 0)
      {
        l2 = 9223372036854775807L;
        l1 = -9223372036854775808L;
      }
      break;
    }
    throw Error.error(5563);
    this.minValue = l1;
    this.maxValue = l2;
    this.increment = 1L;
  }

  public NumberSequence(HsqlNameManager.HsqlName paramHsqlName, long paramLong1, long paramLong2, Type paramType)
  {
    this(paramHsqlName, paramType);
    setStartValue(paramLong1);
    setIncrement(paramLong2);
  }

  public int getType()
  {
    return 7;
  }

  public HsqlNameManager.HsqlName getName()
  {
    return this.name;
  }

  public HsqlNameManager.HsqlName getCatalogName()
  {
    return this.name.schema.schema;
  }

  public HsqlNameManager.HsqlName getSchemaName()
  {
    return this.name.schema;
  }

  public Grantee getOwner()
  {
    return this.name.schema.owner;
  }

  public OrderedHashSet getReferences()
  {
    return new OrderedHashSet();
  }

  public OrderedHashSet getComponents()
  {
    return null;
  }

  public void compile(Session paramSession, SchemaObject paramSchemaObject)
  {
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append("CREATE").append(' ');
    localStringBuffer.append("SEQUENCE").append(' ');
    localStringBuffer.append(getName().getSchemaQualifiedStatementName()).append(' ');
    localStringBuffer.append("AS").append(' ');
    localStringBuffer.append(getDataType().getNameString()).append(' ');
    localStringBuffer.append("START").append(' ');
    localStringBuffer.append("WITH").append(' ');
    localStringBuffer.append(this.startValue);
    if (getIncrement() != 1L)
    {
      localStringBuffer.append(' ').append("INCREMENT").append(' ');
      localStringBuffer.append("BY").append(' ');
      localStringBuffer.append(getIncrement());
    }
    if (!hasDefaultMinMax())
    {
      localStringBuffer.append(' ').append("MINVALUE").append(' ');
      localStringBuffer.append(getMinValue());
      localStringBuffer.append(' ').append("MAXVALUE").append(' ');
      localStringBuffer.append(getMaxValue());
    }
    if (isCycle())
      localStringBuffer.append(' ').append("CYCLE");
    if (this.name == null)
      localStringBuffer.append(")");
    return localStringBuffer.toString();
  }

  public String getSQLColumnDefinition()
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append("GENERATED").append(' ');
    if (this.name == null)
    {
      if (isAlways())
        localStringBuffer.append("ALWAYS");
      else
        localStringBuffer.append("BY").append(' ').append("DEFAULT");
      localStringBuffer.append(' ').append("AS").append(' ').append("IDENTITY").append("(");
      localStringBuffer.append("START").append(' ');
      localStringBuffer.append("WITH").append(' ');
      localStringBuffer.append(this.startValue);
      if (getIncrement() != 1L)
      {
        localStringBuffer.append(' ').append("INCREMENT").append(' ');
        localStringBuffer.append("BY").append(' ');
        localStringBuffer.append(getIncrement());
      }
      if (!hasDefaultMinMax())
      {
        localStringBuffer.append(' ').append("MINVALUE").append(' ');
        localStringBuffer.append(getMinValue());
        localStringBuffer.append(' ').append("MAXVALUE").append(' ');
        localStringBuffer.append(getMaxValue());
      }
      if (isCycle())
        localStringBuffer.append(' ').append("CYCLE");
      if (this.name == null)
        localStringBuffer.append(")");
    }
    else
    {
      localStringBuffer.append("BY").append(' ').append("DEFAULT");
      localStringBuffer.append(' ').append("AS").append(' ');
      localStringBuffer.append("SEQUENCE").append(' ');
      localStringBuffer.append(getName().getSchemaQualifiedStatementName());
    }
    return localStringBuffer.toString();
  }

  public long getChangeTimestamp()
  {
    return 0L;
  }

  public String getRestartSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append("ALTER").append(' ');
    localStringBuffer.append("SEQUENCE");
    localStringBuffer.append(' ').append(this.name.getSchemaQualifiedStatementName());
    localStringBuffer.append(' ').append("RESTART");
    localStringBuffer.append(' ').append("WITH").append(' ').append(peek());
    return localStringBuffer.toString();
  }

  public static String getRestartSQL(Table paramTable)
  {
    String str = paramTable.getColumn(paramTable.identityColumn).getName().statementName;
    NumberSequence localNumberSequence = paramTable.identitySequence;
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append("ALTER").append(' ').append("TABLE");
    localStringBuffer.append(' ').append(paramTable.getName().getSchemaQualifiedStatementName());
    localStringBuffer.append(' ').append("ALTER").append(' ');
    localStringBuffer.append("COLUMN");
    localStringBuffer.append(' ').append(str);
    localStringBuffer.append(' ').append("RESTART");
    localStringBuffer.append(' ').append("WITH").append(' ').append(localNumberSequence.peek());
    return localStringBuffer.toString();
  }

  public Type getDataType()
  {
    return this.dataType;
  }

  public long getIncrement()
  {
    return this.increment;
  }

  public synchronized long getStartValue()
  {
    return this.startValue;
  }

  public synchronized long getMinValue()
  {
    return this.minValue;
  }

  public synchronized long getMaxValue()
  {
    return this.maxValue;
  }

  public synchronized boolean isCycle()
  {
    return this.isCycle;
  }

  public synchronized boolean isAlways()
  {
    return this.isAlways;
  }

  public synchronized boolean hasDefaultMinMax()
  {
    long l2;
    long l1;
    switch (this.dataType.typeCode)
    {
    case -6:
      l2 = 127L;
      l1 = -128L;
      break;
    case 5:
      l2 = 32767L;
      l1 = -32768L;
      break;
    case 4:
      l2 = 2147483647L;
      l1 = -2147483648L;
      break;
    case 25:
      l2 = 9223372036854775807L;
      l1 = -9223372036854775808L;
      break;
    case 2:
    case 3:
      l2 = 9223372036854775807L;
      l1 = -9223372036854775808L;
      break;
    default:
      throw Error.runtimeError(201, "NumberSequence");
    }
    return (this.minValue == l1) && (this.maxValue == l2);
  }

  synchronized void setStartValue(long paramLong)
  {
    if ((paramLong < this.minValue) || (paramLong > this.maxValue))
      throw Error.error(5597);
    this.startValue = paramLong;
    this.currValue = (this.lastValue = this.startValue);
  }

  synchronized void setMinValue(long paramLong)
  {
    checkInTypeRange(paramLong);
    if ((paramLong >= this.maxValue) || (this.currValue < paramLong))
      throw Error.error(5597);
    this.minValue = paramLong;
  }

  synchronized void setDefaultMinValue()
  {
    this.minValue = getDefaultMinOrMax(false);
  }

  synchronized void setMaxValue(long paramLong)
  {
    checkInTypeRange(paramLong);
    if ((paramLong <= this.minValue) || (this.currValue > paramLong))
      throw Error.error(5597);
    this.maxValue = paramLong;
  }

  synchronized void setDefaultMaxValue()
  {
    this.maxValue = getDefaultMinOrMax(true);
  }

  synchronized void setIncrement(long paramLong)
  {
    if ((paramLong < -16384L) || (paramLong > 16383L))
      throw Error.error(5597);
    this.increment = paramLong;
  }

  synchronized void setCurrentValueNoCheck(long paramLong)
  {
    checkInTypeRange(paramLong);
    this.currValue = (this.lastValue = paramLong);
  }

  synchronized void setStartValueNoCheck(long paramLong)
  {
    checkInTypeRange(paramLong);
    this.startValue = paramLong;
    this.currValue = (this.lastValue = this.startValue);
  }

  synchronized void setStartValueDefault()
  {
    this.restartValueDefault = true;
  }

  synchronized void setMinValueNoCheck(long paramLong)
  {
    checkInTypeRange(paramLong);
    this.minValue = paramLong;
  }

  synchronized void setMaxValueNoCheck(long paramLong)
  {
    checkInTypeRange(paramLong);
    this.maxValue = paramLong;
  }

  synchronized void setCycle(boolean paramBoolean)
  {
    this.isCycle = paramBoolean;
  }

  synchronized void setAlways(boolean paramBoolean)
  {
    this.isAlways = paramBoolean;
  }

  private long getDefaultMinOrMax(boolean paramBoolean)
  {
    long l2;
    long l1;
    switch (this.dataType.typeCode)
    {
    case -6:
      l2 = 127L;
      l1 = -128L;
      break;
    case 5:
      l2 = 32767L;
      l1 = -32768L;
      break;
    case 4:
      l2 = 2147483647L;
      l1 = -2147483648L;
      break;
    case 25:
      l2 = 9223372036854775807L;
      l1 = -9223372036854775808L;
      break;
    case 2:
    case 3:
      l2 = 9223372036854775807L;
      l1 = -9223372036854775808L;
      break;
    default:
      throw Error.runtimeError(201, "NumberSequence");
    }
    return paramBoolean ? l2 : l1;
  }

  private void checkInTypeRange(long paramLong)
  {
    long l2;
    long l1;
    switch (this.dataType.typeCode)
    {
    case -6:
      l2 = 127L;
      l1 = -128L;
      break;
    case 5:
      l2 = 32767L;
      l1 = -32768L;
      break;
    case 4:
      l2 = 2147483647L;
      l1 = -2147483648L;
      break;
    case 25:
      l2 = 9223372036854775807L;
      l1 = -9223372036854775808L;
      break;
    case 2:
    case 3:
      l2 = 9223372036854775807L;
      l1 = -9223372036854775808L;
      break;
    default:
      throw Error.runtimeError(201, "NumberSequence");
    }
    if ((paramLong < l1) || (paramLong > l2))
      throw Error.error(5597);
  }

  synchronized void checkValues()
  {
    if (this.restartValueDefault)
    {
      this.currValue = (this.lastValue = this.startValue);
      this.restartValueDefault = false;
    }
    if ((this.minValue >= this.maxValue) || (this.startValue < this.minValue) || (this.startValue > this.maxValue) || (this.currValue < this.minValue) || (this.currValue > this.maxValue))
      throw Error.error(5597);
  }

  synchronized NumberSequence duplicate()
  {
    NumberSequence localNumberSequence = new NumberSequence();
    localNumberSequence.name = this.name;
    localNumberSequence.startValue = this.startValue;
    localNumberSequence.currValue = this.currValue;
    localNumberSequence.lastValue = this.lastValue;
    localNumberSequence.increment = this.increment;
    localNumberSequence.dataType = this.dataType;
    localNumberSequence.minValue = this.minValue;
    localNumberSequence.maxValue = this.maxValue;
    localNumberSequence.isCycle = this.isCycle;
    localNumberSequence.isAlways = this.isAlways;
    return localNumberSequence;
  }

  synchronized void reset(NumberSequence paramNumberSequence)
  {
    this.name = paramNumberSequence.name;
    this.startValue = paramNumberSequence.startValue;
    this.currValue = paramNumberSequence.currValue;
    this.lastValue = paramNumberSequence.lastValue;
    this.increment = paramNumberSequence.increment;
    this.dataType = paramNumberSequence.dataType;
    this.minValue = paramNumberSequence.minValue;
    this.maxValue = paramNumberSequence.maxValue;
    this.isCycle = paramNumberSequence.isCycle;
    this.isAlways = paramNumberSequence.isAlways;
  }

  synchronized long userUpdate(long paramLong)
  {
    if (paramLong == this.currValue)
    {
      this.currValue += this.increment;
      return paramLong;
    }
    if (this.increment > 0L)
    {
      if (paramLong > this.currValue)
        this.currValue += (paramLong - this.currValue + this.increment) / this.increment * this.increment;
    }
    else if (paramLong < this.currValue)
      this.currValue += (paramLong - this.currValue + this.increment) / this.increment * this.increment;
    return paramLong;
  }

  synchronized long systemUpdate(long paramLong)
  {
    if (paramLong == this.currValue)
    {
      this.currValue += this.increment;
      return paramLong;
    }
    if (this.increment > 0L)
    {
      if (paramLong > this.currValue)
        this.currValue = (paramLong + this.increment);
    }
    else if (paramLong < this.currValue)
      this.currValue = (paramLong + this.increment);
    return paramLong;
  }

  synchronized Object getValueObject()
  {
    long l = getValue();
    Object localObject;
    switch (this.dataType.typeCode)
    {
    case 4:
    case 5:
    default:
      localObject = ValuePool.getInt((int)l);
      break;
    case 25:
      localObject = ValuePool.getLong(l);
      break;
    case 2:
    case 3:
      localObject = ValuePool.getBigDecimal(new BigDecimal(l));
    }
    return localObject;
  }

  public synchronized long getValue()
  {
    if (this.limitReached)
      throw Error.error(3416);
    long l1;
    if (this.increment > 0L)
    {
      if (this.currValue > this.maxValue - this.increment)
      {
        if (this.isCycle)
        {
          l1 = this.minValue;
        }
        else
        {
          this.limitReached = true;
          l1 = this.minValue;
        }
      }
      else
        l1 = this.currValue + this.increment;
    }
    else if (this.currValue < this.minValue - this.increment)
    {
      if (this.isCycle)
      {
        l1 = this.maxValue;
      }
      else
      {
        this.limitReached = true;
        l1 = this.minValue;
      }
    }
    else
      l1 = this.currValue + this.increment;
    long l2 = this.currValue;
    this.currValue = l1;
    return l2;
  }

  synchronized void reset()
  {
    this.lastValue = (this.currValue = this.startValue);
  }

  public synchronized long peek()
  {
    return this.currValue;
  }

  synchronized boolean resetWasUsed()
  {
    boolean bool = this.lastValue != this.currValue;
    this.lastValue = this.currValue;
    return bool;
  }

  public synchronized void reset(long paramLong)
  {
    if ((paramLong < this.minValue) || (paramLong > this.maxValue))
      throw Error.error(5597);
    this.startValue = (this.currValue = this.lastValue = paramLong);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.NumberSequence
 * JD-Core Version:    0.6.2
 */