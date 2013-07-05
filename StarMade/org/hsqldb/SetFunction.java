package org.hsqldb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashSet;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.RowType;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;
import org.hsqldb.types.Type.TypedComparator;

public class SetFunction
  implements Serializable
{
  private HashSet distinctValues;
  private boolean isDistinct;
  private int setType;
  private int typeCode;
  private Type type;
  private ArrayType arrayType;
  private Type returnType;
  private long count;
  private boolean hasNull;
  private boolean every = true;
  private boolean some = false;
  private long currentLong;
  private double currentDouble;
  private BigDecimal currentBigDecimal;
  private Object currentValue;
  static final BigInteger multiplier = BigInteger.valueOf(4294967296L);
  long hi;
  long lo;
  private double sk;
  private double vk;
  private long n;
  private boolean initialized;
  private boolean sample;

  SetFunction(Session paramSession, int paramInt, Type paramType1, Type paramType2, boolean paramBoolean, ArrayType paramArrayType)
  {
    this.setType = paramInt;
    this.type = paramType1;
    this.returnType = paramType2;
    if (paramBoolean)
    {
      this.isDistinct = true;
      this.arrayType = paramArrayType;
      this.distinctValues = new HashSet();
      if ((paramType1.isRowType()) || (paramType1.isArrayType()))
      {
        Type.TypedComparator localTypedComparator = Type.newComparator(paramSession);
        SortAndSlice localSortAndSlice = new SortAndSlice();
        int i = paramType1.isRowType() ? ((RowType)paramType1).getTypesArray().length : 1;
        localSortAndSlice.prepareMultiColumn(i);
        localTypedComparator.setType(paramType1, localSortAndSlice);
        this.distinctValues.setComparator(localTypedComparator);
      }
    }
    if ((paramInt == 81) || (paramInt == 79))
      this.sample = true;
    if (paramType1 != null)
    {
      this.typeCode = paramType1.typeCode;
      if (paramType1.isIntervalType())
        this.typeCode = 10;
    }
  }

  void add(Session paramSession, Object paramObject)
  {
    if (paramObject == null)
    {
      this.hasNull = true;
      return;
    }
    if ((this.isDistinct) && (!this.distinctValues.add(paramObject)))
      return;
    this.count += 1L;
    switch (this.setType)
    {
    case 71:
      return;
    case 72:
    case 75:
      switch (this.typeCode)
      {
      case -6:
      case 4:
      case 5:
        this.currentLong += ((Number)paramObject).intValue();
        return;
      case 10:
        if ((paramObject instanceof IntervalSecondData))
        {
          addLong(((IntervalSecondData)paramObject).getSeconds());
          this.currentLong += ((IntervalSecondData)paramObject).getNanos();
          if (Math.abs(this.currentLong) >= org.hsqldb.types.DTIType.nanoScaleFactors[0])
          {
            addLong(this.currentLong / org.hsqldb.types.DTIType.nanoScaleFactors[0]);
            this.currentLong %= org.hsqldb.types.DTIType.nanoScaleFactors[0];
          }
        }
        else if ((paramObject instanceof IntervalMonthData))
        {
          addLong(((IntervalMonthData)paramObject).units);
        }
        return;
      case 91:
      case 93:
      case 95:
        addLong(((TimestampData)paramObject).getSeconds());
        this.currentLong += ((TimestampData)paramObject).getNanos();
        if (Math.abs(this.currentLong) >= org.hsqldb.types.DTIType.nanoScaleFactors[0])
        {
          addLong(this.currentLong / org.hsqldb.types.DTIType.nanoScaleFactors[0]);
          this.currentLong %= org.hsqldb.types.DTIType.nanoScaleFactors[0];
        }
        this.currentDouble = ((TimestampData)paramObject).getZone();
        return;
      case 25:
        addLong(((Number)paramObject).longValue());
        return;
      case 6:
      case 7:
      case 8:
        this.currentDouble += ((Number)paramObject).doubleValue();
        return;
      case 2:
      case 3:
        if (this.currentBigDecimal == null)
          this.currentBigDecimal = ((BigDecimal)paramObject);
        else
          this.currentBigDecimal = this.currentBigDecimal.add((BigDecimal)paramObject);
        return;
      }
      throw Error.error(5563);
    case 73:
      if (this.currentValue == null)
      {
        this.currentValue = paramObject;
        return;
      }
      if (this.type.compare(paramSession, this.currentValue, paramObject) > 0)
        this.currentValue = paramObject;
      return;
    case 74:
      if (this.currentValue == null)
      {
        this.currentValue = paramObject;
        return;
      }
      if (this.type.compare(paramSession, this.currentValue, paramObject) < 0)
        this.currentValue = paramObject;
      return;
    case 76:
      if (!(paramObject instanceof Boolean))
        throw Error.error(5563);
      this.every = ((this.every) && (((Boolean)paramObject).booleanValue()));
      return;
    case 77:
      if (!(paramObject instanceof Boolean))
        throw Error.error(5563);
      this.some = ((this.some) || (((Boolean)paramObject).booleanValue()));
      return;
    case 78:
    case 79:
    case 80:
    case 81:
      addDataPoint((Number)paramObject);
      return;
    case 98:
      this.currentValue = paramObject;
      return;
    case 82:
    case 83:
    case 84:
    case 85:
    case 86:
    case 87:
    case 88:
    case 89:
    case 90:
    case 91:
    case 92:
    case 93:
    case 94:
    case 95:
    case 96:
    case 97:
    }
    throw Error.runtimeError(201, "SetFunction");
  }

  Object getValue(Session paramSession)
  {
    if (this.hasNull)
      paramSession.addWarning(Error.error(1003));
    if (this.setType == 71)
    {
      if ((this.count > 0L) && (this.isDistinct) && (this.type.isCharacterType()))
      {
        Object[] arrayOfObject = new Object[this.distinctValues.size()];
        this.distinctValues.toArray(arrayOfObject);
        SortAndSlice localSortAndSlice = new SortAndSlice();
        localSortAndSlice.prepareSingleColumn(0);
        this.arrayType.sort(paramSession, arrayOfObject, localSortAndSlice);
        this.count = this.arrayType.deDuplicate(paramSession, arrayOfObject, localSortAndSlice);
      }
      return ValuePool.getLong(this.count);
    }
    if (this.count == 0L)
      return null;
    BigInteger localBigInteger;
    switch (this.setType)
    {
    case 75:
      switch (this.typeCode)
      {
      case -6:
      case 4:
      case 5:
        if (this.returnType.scale != 0)
          return this.returnType.divide(paramSession, Long.valueOf(this.currentLong), Long.valueOf(this.count));
        return new Long(this.currentLong / this.count);
      case 25:
        long l = getLongSum().divide(BigInteger.valueOf(this.count)).longValue();
        return new Long(l);
      case 6:
      case 7:
      case 8:
        return new Double(this.currentDouble / this.count);
      case 2:
      case 3:
        if (this.returnType.scale == this.type.scale)
          return this.currentBigDecimal.divide(new BigDecimal(this.count), 1);
        return this.returnType.divide(paramSession, this.currentBigDecimal, Long.valueOf(this.count));
      case 10:
        localBigInteger = getLongSum().divide(BigInteger.valueOf(this.count));
        if (!NumberType.isInLongLimits(localBigInteger))
          throw Error.error(3435);
        if (((IntervalType)this.type).isDaySecondIntervalType())
          return new IntervalSecondData(localBigInteger.longValue(), this.currentLong, (IntervalType)this.type, true);
        return IntervalMonthData.newIntervalMonth(localBigInteger.longValue(), (IntervalType)this.type);
      case 91:
      case 93:
      case 95:
        localBigInteger = getLongSum().divide(BigInteger.valueOf(this.count));
        if (!NumberType.isInLongLimits(localBigInteger))
          throw Error.error(3435);
        return new TimestampData(localBigInteger.longValue(), (int)this.currentLong, (int)this.currentDouble);
      }
      throw Error.runtimeError(201, "SetFunction");
    case 72:
      switch (this.typeCode)
      {
      case -6:
      case 4:
      case 5:
        return new Long(this.currentLong);
      case 25:
        return new BigDecimal(getLongSum());
      case 6:
      case 7:
      case 8:
        return new Double(this.currentDouble);
      case 2:
      case 3:
        return this.currentBigDecimal;
      case 10:
        localBigInteger = getLongSum();
        if (!NumberType.isInLongLimits(localBigInteger))
          throw Error.error(3435);
        if (((IntervalType)this.type).isDaySecondIntervalType())
          return new IntervalSecondData(localBigInteger.longValue(), this.currentLong, (IntervalType)this.type, true);
        return IntervalMonthData.newIntervalMonth(localBigInteger.longValue(), (IntervalType)this.type);
      case -5:
      case -4:
      case -3:
      case -2:
      case -1:
      case 0:
      case 1:
      case 9:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      }
      throw Error.runtimeError(201, "SetFunction");
    case 73:
    case 74:
      return this.currentValue;
    case 76:
      return this.every ? Boolean.TRUE : Boolean.FALSE;
    case 77:
      return this.some ? Boolean.TRUE : Boolean.FALSE;
    case 78:
    case 79:
      return getStdDev();
    case 80:
    case 81:
      return getVariance();
    case 98:
      return this.currentValue;
    case 82:
    case 83:
    case 84:
    case 85:
    case 86:
    case 87:
    case 88:
    case 89:
    case 90:
    case 91:
    case 92:
    case 93:
    case 94:
    case 95:
    case 96:
    case 97:
    }
    throw Error.runtimeError(201, "SetFunction");
  }

  static Type getType(Session paramSession, int paramInt, Type paramType)
  {
    if (paramInt == 71)
      return Type.SQL_BIGINT;
    int i = paramType.isIntervalType() ? 10 : paramType.typeCode;
    switch (paramInt)
    {
    case 75:
    case 85:
      switch (i)
      {
      case -6:
      case 2:
      case 3:
      case 4:
      case 5:
      case 25:
        int j = paramSession.database.sqlAvgScale;
        if (j <= paramType.scale)
          return paramType;
        int k = ((NumberType)paramType).getDecimalPrecision();
        return NumberType.getNumberType(3, k + j, j);
      case 6:
      case 7:
      case 8:
      case 10:
      case 91:
      case 93:
      case 95:
        return paramType;
      }
      throw Error.error(5563);
    case 72:
      switch (i)
      {
      case -6:
      case 4:
      case 5:
        return Type.SQL_BIGINT;
      case 25:
        return Type.SQL_DECIMAL_BIGINT_SQR;
      case 6:
      case 7:
      case 8:
        return Type.SQL_DOUBLE;
      case 2:
      case 3:
        return Type.getType(paramType.typeCode, null, null, paramType.precision * 2L, paramType.scale);
      case 10:
        return IntervalType.newIntervalType(paramType.typeCode, 9L, paramType.scale);
      case -5:
      case -4:
      case -3:
      case -2:
      case -1:
      case 0:
      case 1:
      case 9:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
      }
      throw Error.error(5563);
    case 73:
    case 74:
      if ((paramType.isArrayType()) || (paramType.isLobType()))
        throw Error.error(5563);
      return paramType;
    case 76:
    case 77:
      if (paramType.isBooleanType())
        return Type.SQL_BOOLEAN;
      break;
    case 78:
    case 79:
    case 80:
    case 81:
      if (paramType.isNumberType())
        return Type.SQL_DOUBLE;
      break;
    case 98:
      return paramType;
    case 82:
    case 83:
    case 84:
    case 86:
    case 87:
    case 88:
    case 89:
    case 90:
    case 91:
    case 92:
    case 93:
    case 94:
    case 95:
    case 96:
    case 97:
    default:
      throw Error.runtimeError(201, "SetFunction");
    }
    throw Error.error(5563);
  }

  void addLong(long paramLong)
  {
    if (paramLong != 0L)
      if (paramLong > 0L)
      {
        this.hi += (paramLong >> 32);
        this.lo += (paramLong & 0xFFFFFFFF);
      }
      else if (paramLong == -9223372036854775808L)
      {
        this.hi -= 2147483648L;
      }
      else
      {
        long l = (paramLong ^ 0xFFFFFFFF) + 1L;
        this.hi -= (l >> 32);
        this.lo -= (l & 0xFFFFFFFF);
      }
  }

  BigInteger getLongSum()
  {
    BigInteger localBigInteger1 = BigInteger.valueOf(this.lo);
    BigInteger localBigInteger2 = BigInteger.valueOf(this.hi);
    BigInteger localBigInteger3 = localBigInteger2.multiply(multiplier).add(localBigInteger1);
    return localBigInteger3;
  }

  private void addDataPoint(Number paramNumber)
  {
    if (paramNumber == null)
      return;
    double d1 = paramNumber.doubleValue();
    if (!this.initialized)
    {
      this.n = 1L;
      this.sk = d1;
      this.vk = 0.0D;
      this.initialized = true;
      return;
    }
    this.n += 1L;
    long l = this.n - 1L;
    double d2 = this.sk - d1 * l;
    this.vk += d2 * d2 / this.n / l;
    this.sk += d1;
  }

  private Number getVariance()
  {
    if (!this.initialized)
      return null;
    return this.sample ? new Double(this.vk / (this.n - 1L)) : this.n == 1L ? null : new Double(this.vk / this.n);
  }

  private Number getStdDev()
  {
    if (!this.initialized)
      return null;
    return this.sample ? new Double(Math.sqrt(this.vk / (this.n - 1L))) : this.n == 1L ? null : new Double(Math.sqrt(this.vk / this.n));
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.SetFunction
 * JD-Core Version:    0.6.2
 */