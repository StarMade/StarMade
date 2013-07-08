package org.hsqldb.types;

import java.math.BigDecimal;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.Scanner;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.Tokens;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;

public final class IntervalType
  extends DTIType
{
  public final boolean defaultPrecision;
  public final boolean isYearMonth;
  public static final NumberType factorType = NumberType.getNumberType(3, 40L, 9);
  
  private IntervalType(int paramInt1, int paramInt2, long paramLong, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean)
  {
    super(paramInt1, paramInt2, paramLong, paramInt3, paramInt4, paramInt5);
    if ((paramInt5 != 106) && (paramInt3 != 0)) {
      throw Error.error(3406);
    }
    switch (paramInt4)
    {
    case 101: 
    case 102: 
      this.isYearMonth = true;
      break;
    default: 
      this.isYearMonth = false;
    }
    this.defaultPrecision = paramBoolean;
  }
  
  public int displaySize()
  {
    switch (this.typeCode)
    {
    case 101: 
      return (int)this.precision + 1;
    case 107: 
      return (int)this.precision + 4;
    case 102: 
      return (int)this.precision + 1;
    case 103: 
      return (int)this.precision + 1;
    case 108: 
      return (int)this.precision + 4;
    case 109: 
      return (int)this.precision + 7;
    case 110: 
      return (int)this.precision + 10 + (this.scale == 0 ? 0 : this.scale + 1);
    case 104: 
      return (int)this.precision + 1;
    case 111: 
      return (int)this.precision + 4;
    case 112: 
      return (int)this.precision + 7 + (this.scale == 0 ? 0 : this.scale + 1);
    case 105: 
      return (int)this.precision + 1;
    case 113: 
      return (int)this.precision + 4 + (this.scale == 0 ? 0 : this.scale + 1);
    case 106: 
      return (int)this.precision + 1 + (this.scale == 0 ? 0 : this.scale + 1);
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public int getJDBCTypeCode()
  {
    return this.typeCode;
  }
  
  public Class getJDBCClass()
  {
    switch (this.typeCode)
    {
    case 101: 
    case 102: 
    case 107: 
      return IntervalMonthData.class;
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
      return IntervalSecondData.class;
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public String getJDBCClassName()
  {
    switch (this.typeCode)
    {
    case 101: 
    case 102: 
    case 107: 
      return IntervalMonthData.class.getName();
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
      return IntervalSecondData.class.getName();
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public int getJDBCPrecision()
  {
    return displaySize();
  }
  
  public int getSQLGenericTypeCode()
  {
    return 10;
  }
  
  public String getNameString()
  {
    return "INTERVAL " + getQualifier(this.typeCode);
  }
  
  public static String getQualifier(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
      return "YEAR";
    case 107: 
      return "YEAR TO MONTH";
    case 102: 
      return "MONTH";
    case 103: 
      return "DAY";
    case 108: 
      return "DAY TO HOUR";
    case 109: 
      return "DAY TO MINUTE";
    case 110: 
      return "DAY TO SECOND";
    case 104: 
      return "HOUR";
    case 111: 
      return "HOUR TO MINUTE";
    case 112: 
      return "HOUR TO SECOND";
    case 105: 
      return "MINUTE";
    case 113: 
      return "MINUTE TO SECOND";
    case 106: 
      return "SECOND";
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public String getDefinition()
  {
    if ((this.precision == 2L) && ((this.endIntervalType != 106) || (this.scale == 6))) {
      return getNameString();
    }
    StringBuffer localStringBuffer = new StringBuffer(32);
    localStringBuffer.append("INTERVAL").append(' ');
    localStringBuffer.append(getQualifier(this.startIntervalType));
    if (this.typeCode == 106)
    {
      localStringBuffer.append('(');
      localStringBuffer.append(this.precision);
      if (this.scale != 6)
      {
        localStringBuffer.append(',');
        localStringBuffer.append(this.scale);
      }
      localStringBuffer.append(')');
      return localStringBuffer.toString();
    }
    if (this.precision != 2L)
    {
      localStringBuffer.append('(');
      localStringBuffer.append(this.precision);
      localStringBuffer.append(')');
    }
    if (this.startIntervalType != this.endIntervalType)
    {
      localStringBuffer.append(' ');
      localStringBuffer.append("TO");
      localStringBuffer.append(' ');
      localStringBuffer.append(Tokens.SQL_INTERVAL_FIELD_NAMES[this.endPartIndex]);
      if ((this.endIntervalType == 106) && (this.scale != 6))
      {
        localStringBuffer.append('(');
        localStringBuffer.append(this.scale);
        localStringBuffer.append(')');
      }
    }
    return localStringBuffer.toString();
  }
  
  public boolean isIntervalType()
  {
    return true;
  }
  
  public boolean isYearMonthIntervalType()
  {
    switch (this.typeCode)
    {
    case 101: 
    case 102: 
    case 107: 
      return true;
    }
    return false;
  }
  
  public boolean isDaySecondIntervalType()
  {
    switch (this.typeCode)
    {
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
      return true;
    }
    return false;
  }
  
  public boolean acceptsPrecision()
  {
    return true;
  }
  
  public boolean acceptsFractionalPrecision()
  {
    return this.endIntervalType == 106;
  }
  
  public Type getAggregateType(Type paramType)
  {
    if (paramType == null) {
      return this;
    }
    if (paramType == SQL_ALL_TYPES) {
      return this;
    }
    if (this.typeCode == paramType.typeCode)
    {
      if ((this.precision >= paramType.precision) && (this.scale >= paramType.scale)) {
        return this;
      }
      if ((this.precision <= paramType.precision) && (this.scale <= paramType.scale)) {
        return paramType;
      }
    }
    if (paramType.isCharacterType()) {
      return paramType.getAggregateType(this);
    }
    if (!paramType.isIntervalType()) {
      throw Error.error(5562);
    }
    int i = ((IntervalType)paramType).startIntervalType > this.startIntervalType ? this.startIntervalType : ((IntervalType)paramType).startIntervalType;
    int j = ((IntervalType)paramType).endIntervalType > this.endIntervalType ? ((IntervalType)paramType).endIntervalType : this.endIntervalType;
    int k = getCombinedIntervalType(i, j);
    long l = this.precision > paramType.precision ? this.precision : paramType.precision;
    int m = this.scale > paramType.scale ? this.scale : paramType.scale;
    try
    {
      return getIntervalType(k, i, j, l, m, false);
    }
    catch (RuntimeException localRuntimeException)
    {
      throw Error.error(5562);
    }
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    IntervalType localIntervalType;
    switch (paramInt)
    {
    case 34: 
      if (paramType.isNumberType()) {
        return getIntervalType(this, 9L, this.scale);
      }
      break;
    case 35: 
      if (paramType.isNumberType()) {
        return this;
      }
      if (paramType.isIntervalType())
      {
        localIntervalType = (IntervalType)paramType;
        if (this.isYearMonth == localIntervalType.isYearMonth) {
          return this.isYearMonth ? Type.SQL_BIGINT : factorType;
        }
      }
      break;
    case 32: 
      if (paramType.isDateTimeType()) {
        return paramType.getCombinedType(paramSession, this, paramInt);
      }
      if (paramType.isIntervalType())
      {
        localIntervalType = (IntervalType)getAggregateType(paramType);
        return getIntervalType(localIntervalType, 9L, 0);
      }
      break;
    case 33: 
    default: 
      return getAggregateType(paramType);
    }
    throw Error.error(5562);
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2) {
      return 0;
    }
    if (paramObject1 == null) {
      return -1;
    }
    if (paramObject2 == null) {
      return 1;
    }
    switch (this.typeCode)
    {
    case 101: 
    case 102: 
    case 107: 
      return ((IntervalMonthData)paramObject1).compareTo((IntervalMonthData)paramObject2);
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
      return ((IntervalSecondData)paramObject1).compareTo((IntervalSecondData)paramObject2);
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    Object localObject;
    if ((paramObject instanceof IntervalMonthData))
    {
      localObject = (IntervalMonthData)paramObject;
      if (((IntervalMonthData)localObject).units > getIntervalValueLimit()) {
        throw Error.error(3435);
      }
    }
    else if ((paramObject instanceof IntervalSecondData))
    {
      localObject = (IntervalSecondData)paramObject;
      if (((IntervalSecondData)localObject).units > getIntervalValueLimit()) {
        throw Error.error(3435);
      }
    }
    return paramObject;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null) {
      return null;
    }
    long l;
    int i;
    switch (paramType.typeCode)
    {
    case 40: 
      paramObject = paramObject.toString();
    case 1: 
    case 12: 
    case 100: 
      return paramSessionInterface.getScanner().convertToDatetimeInterval(paramSessionInterface, (String)paramObject, this);
    case -6: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 25: 
      if (((paramObject instanceof BigDecimal)) && (!NumberType.isInLongLimits((BigDecimal)paramObject))) {
        throw Error.error(3435);
      }
      l = ((Number)paramObject).longValue();
      switch (this.endIntervalType)
      {
      case 101: 
        return IntervalMonthData.newIntervalYear(l, this);
      case 102: 
        return IntervalMonthData.newIntervalMonth(l, this);
      case 103: 
        return IntervalSecondData.newIntervalDay(l, this);
      case 104: 
        return IntervalSecondData.newIntervalHour(l, this);
      case 105: 
        return IntervalSecondData.newIntervalMinute(l, this);
      case 106: 
        i = 0;
        if ((this.scale > 0) && ((paramObject instanceof BigDecimal))) {
          i = (int)NumberType.scaledDecimal(paramObject, 9);
        }
        return new IntervalSecondData(l, i, this);
      }
      throw Error.error(5561);
    case 101: 
      l = ((IntervalMonthData)paramObject).units / 12L * 12L;
      return new IntervalMonthData(l, this);
    case 102: 
    case 107: 
      l = ((IntervalMonthData)paramObject).units;
      return new IntervalMonthData(l, this);
    case 103: 
      l = ((IntervalSecondData)paramObject).units;
      l = l / DTIType.yearToSecondFactors[2] * DTIType.yearToSecondFactors[2];
      return new IntervalSecondData(l, 0, this);
    case 104: 
    case 105: 
    case 108: 
    case 109: 
    case 111: 
      l = ((IntervalSecondData)paramObject).units;
      l = l / DTIType.yearToSecondFactors[this.endPartIndex] * DTIType.yearToSecondFactors[this.endPartIndex];
      return new IntervalSecondData(l, 0, this);
    case 106: 
    case 110: 
    case 112: 
    case 113: 
      l = ((IntervalSecondData)paramObject).units;
      i = ((IntervalSecondData)paramObject).nanos;
      if (this.scale == 0) {
        i = 0;
      } else {
        i = i / DTIType.nanoScaleFactors[this.scale] * DTIType.nanoScaleFactors[this.scale];
      }
      return new IntervalSecondData(l, i, this);
    }
    throw Error.error(5561);
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof String)) {
      return convertToType(paramSessionInterface, paramObject, Type.SQL_VARCHAR);
    }
    if ((paramObject instanceof Integer)) {
      return convertToType(paramSessionInterface, paramObject, Type.SQL_INTEGER);
    }
    if ((paramObject instanceof Long)) {
      return convertToType(paramSessionInterface, paramObject, Type.SQL_BIGINT);
    }
    if ((paramObject instanceof BigDecimal)) {
      return convertToType(paramSessionInterface, paramObject, Type.SQL_DECIMAL);
    }
    throw Error.error(5561);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject)
  {
    return convertToDefaultType(paramSessionInterface, paramObject);
  }
  
  public String convertToString(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    switch (this.typeCode)
    {
    case 101: 
    case 102: 
    case 107: 
      return intervalMonthToString(paramObject);
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
      return intervalSecondToString(paramObject);
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null) {
      return "NULL";
    }
    StringBuffer localStringBuffer = new StringBuffer(32);
    localStringBuffer.append("INTERVAL").append(' ');
    localStringBuffer.append('\'').append(convertToString(paramObject)).append('\'').append(' ');
    localStringBuffer.append(Tokens.SQL_INTERVAL_FIELD_NAMES[this.startPartIndex]);
    if (this.startPartIndex != this.endPartIndex)
    {
      localStringBuffer.append(' ');
      localStringBuffer.append("TO");
      localStringBuffer.append(' ');
      localStringBuffer.append(Tokens.SQL_INTERVAL_FIELD_NAMES[this.endPartIndex]);
    }
    return localStringBuffer.toString();
  }
  
  public boolean canConvertFrom(Type paramType)
  {
    if (paramType.typeCode == 0) {
      return true;
    }
    if (paramType.isCharacterType()) {
      return true;
    }
    if (paramType.isNumberType()) {
      return true;
    }
    if (!paramType.isIntervalType()) {
      return false;
    }
    return !(isYearMonthIntervalType() ^ ((IntervalType)paramType).isYearMonthIntervalType());
  }
  
  public int canMoveFrom(Type paramType)
  {
    if (paramType == this) {
      return 0;
    }
    if (this.typeCode == paramType.typeCode) {
      return this.scale >= paramType.scale ? 0 : -1;
    }
    if (!paramType.isIntervalType()) {
      return -1;
    }
    if (this.isYearMonth == ((IntervalType)paramType).isYearMonth)
    {
      if (this.scale < paramType.scale) {
        return -1;
      }
      if (this.endPartIndex >= ((IntervalType)paramType).endPartIndex)
      {
        if ((this.precision >= paramType.precision) && (this.startPartIndex <= ((IntervalType)paramType).startPartIndex)) {
          return 0;
        }
        return 1;
      }
    }
    return -1;
  }
  
  public int compareToTypeRange(Object paramObject)
  {
    long l1 = precisionLimits[((int)this.precision)];
    long l2;
    if ((paramObject instanceof IntervalMonthData)) {
      l2 = ((IntervalMonthData)paramObject).units;
    } else if ((paramObject instanceof IntervalSecondData)) {
      l2 = ((IntervalSecondData)paramObject).units;
    } else {
      return 0;
    }
    if (l2 >= l1) {
      return 1;
    }
    if ((l2 < 0L) && (-l2 >= l1)) {
      return -1;
    }
    return 0;
  }
  
  public Object absolute(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof IntervalMonthData))
    {
      if (((IntervalMonthData)paramObject).units < 0L) {
        return negate(paramObject);
      }
    }
    else if ((((IntervalSecondData)paramObject).units < 0L) || (((IntervalSecondData)paramObject).nanos < 0)) {
      return negate(paramObject);
    }
    return paramObject;
  }
  
  public Object negate(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof IntervalMonthData))
    {
      l = ((IntervalMonthData)paramObject).units;
      return new IntervalMonthData(-l, this);
    }
    long l = ((IntervalSecondData)paramObject).units;
    int i = ((IntervalSecondData)paramObject).nanos;
    return new IntervalSecondData(-l, -i, this, true);
  }
  
  public Object add(Object paramObject1, Object paramObject2, Type paramType)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    switch (this.typeCode)
    {
    case 101: 
    case 102: 
    case 107: 
      long l1 = ((IntervalMonthData)paramObject1).units + ((IntervalMonthData)paramObject2).units;
      return new IntervalMonthData(l1, this);
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
      long l2 = ((IntervalSecondData)paramObject1).units + ((IntervalSecondData)paramObject2).units;
      long l3 = ((IntervalSecondData)paramObject1).nanos + ((IntervalSecondData)paramObject2).nanos;
      return new IntervalSecondData(l2, l3, this, true);
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public Object subtract(Object paramObject1, Object paramObject2, Type paramType)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    switch (this.typeCode)
    {
    case 101: 
    case 102: 
    case 107: 
      if (((paramObject1 instanceof IntervalMonthData)) && ((paramObject2 instanceof IntervalMonthData)))
      {
        long l1 = ((IntervalMonthData)paramObject1).units - ((IntervalMonthData)paramObject2).units;
        return new IntervalMonthData(l1, this);
      }
      if (((paramObject1 instanceof TimestampData)) && ((paramObject2 instanceof TimestampData)))
      {
        boolean bool = this.typeCode == 101;
        long l3 = DateTimeType.subtractMonths((TimestampData)paramObject1, (TimestampData)paramObject2, bool);
        return new IntervalMonthData(l3, this);
      }
      throw Error.runtimeError(201, "IntervalType");
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
      long l2;
      long l4;
      if (((paramObject1 instanceof IntervalSecondData)) && ((paramObject2 instanceof IntervalSecondData)))
      {
        l2 = ((IntervalSecondData)paramObject1).units - ((IntervalSecondData)paramObject2).units;
        l4 = ((IntervalSecondData)paramObject1).nanos - ((IntervalSecondData)paramObject2).nanos;
        return new IntervalSecondData(l2, l4, this, true);
      }
      long l5;
      if (((paramObject1 instanceof TimeData)) && ((paramObject2 instanceof TimeData)))
      {
        l2 = ((TimeData)paramObject1).getSeconds();
        l4 = ((TimeData)paramObject2).getSeconds();
        l5 = ((TimeData)paramObject1).getNanos() - ((TimeData)paramObject2).getNanos();
        return subtract(l2, l4, l5);
      }
      if (((paramObject1 instanceof TimestampData)) && ((paramObject2 instanceof TimestampData)))
      {
        l2 = ((TimestampData)paramObject1).getSeconds();
        l4 = ((TimestampData)paramObject2).getSeconds();
        l5 = ((TimestampData)paramObject1).getNanos() - ((TimestampData)paramObject2).getNanos();
        return subtract(l2, l4, l5);
      }
      break;
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  private IntervalSecondData subtract(long paramLong1, long paramLong2, long paramLong3)
  {
    if (this.endIntervalType != 106)
    {
      paramLong1 = HsqlDateTime.getTruncatedPart(paramLong1 * 1000L, this.endIntervalType) / 1000L;
      paramLong2 = HsqlDateTime.getTruncatedPart(paramLong2 * 1000L, this.endIntervalType) / 1000L;
      paramLong3 = 0L;
    }
    return new IntervalSecondData(paramLong1 - paramLong2, paramLong3, this, true);
  }
  
  public Object multiply(Object paramObject1, Object paramObject2)
  {
    return multiplyOrDivide(paramObject1, paramObject2, false);
  }
  
  public Object divide(Session paramSession, Object paramObject1, Object paramObject2)
  {
    return multiplyOrDivide(paramObject1, paramObject2, true);
  }
  
  private Object multiplyOrDivide(Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    if ((paramObject1 instanceof Number))
    {
      Object localObject = paramObject1;
      paramObject1 = paramObject2;
      paramObject2 = localObject;
    }
    boolean bool = paramObject2 instanceof Number;
    if (paramBoolean) {
      if (bool)
      {
        if (NumberType.isZero(paramObject2)) {
          throw Error.error(3432);
        }
      }
      else if (this.isYearMonth)
      {
        if (((IntervalMonthData)paramObject2).units == 0L) {
          throw Error.error(3432);
        }
      }
      else if (((IntervalSecondData)paramObject2).units == 0L) {
        throw Error.error(3432);
      }
    }
    BigDecimal localBigDecimal1 = (BigDecimal)factorType.convertToDefaultType(null, paramObject2);
    BigDecimal localBigDecimal2;
    if (this.isYearMonth)
    {
      localBigDecimal2 = BigDecimal.valueOf(((IntervalMonthData)paramObject1).units);
    }
    else
    {
      long l = ((IntervalSecondData)paramObject1).units * DTIType.nanoScaleFactors[0] + ((IntervalSecondData)paramObject1).nanos;
      localBigDecimal2 = BigDecimal.valueOf(l, 9);
    }
    BigDecimal localBigDecimal3 = paramBoolean ? (BigDecimal)factorType.divide(null, localBigDecimal2, localBigDecimal1) : (BigDecimal)factorType.multiply(localBigDecimal2, localBigDecimal1);
    if (!NumberType.isInLongLimits(localBigDecimal3)) {
      throw Error.error(3435);
    }
    if (bool)
    {
      if (this.isYearMonth) {
        return new IntervalMonthData(localBigDecimal3.longValue(), this);
      }
      int i = (int)NumberType.scaledDecimal(localBigDecimal3, 9);
      return new IntervalSecondData(localBigDecimal3.longValue(), i, this, true);
    }
    if (this.isYearMonth) {
      return Long.valueOf(localBigDecimal3.longValue());
    }
    return localBigDecimal3;
  }
  
  String intervalMonthToString(Object paramObject)
  {
    StringBuffer localStringBuffer = new StringBuffer(8);
    long l1 = ((IntervalMonthData)paramObject).units;
    if (l1 < 0L)
    {
      l1 = -l1;
      localStringBuffer.append('-');
    }
    for (int i = this.startPartIndex; i <= this.endPartIndex; i++)
    {
      int j = DTIType.yearToSecondFactors[i];
      long l2 = l1 / j;
      if (i == this.startPartIndex) {
        int k = (int)this.precision - getPrecisionExponent(l2);
      } else if (l2 < 10L) {
        localStringBuffer.append('0');
      }
      localStringBuffer.append(l2);
      l1 %= j;
      if (i < this.endPartIndex) {
        localStringBuffer.append((char)DTIType.yearToSecondSeparators[i]);
      }
    }
    return localStringBuffer.toString();
  }
  
  String intervalSecondToString(Object paramObject)
  {
    long l = ((IntervalSecondData)paramObject).units;
    int i = ((IntervalSecondData)paramObject).nanos;
    return intervalSecondToString(l, i, false);
  }
  
  public int precedenceDegree(Type paramType)
  {
    if (paramType.isIntervalType())
    {
      int i = ((IntervalType)paramType).endPartIndex;
      return i - this.endPartIndex;
    }
    return -2147483648;
  }
  
  public int getStartIntervalType()
  {
    return this.startIntervalType;
  }
  
  public int getEndIntervalType()
  {
    return this.endIntervalType;
  }
  
  public static IntervalType newIntervalType(int paramInt1, long paramLong, int paramInt2)
  {
    int i = getStartIntervalType(paramInt1);
    int j = getEndIntervalType(paramInt1);
    int k = i > 102 ? 106 : 102;
    return new IntervalType(k, paramInt1, paramLong, paramInt2, i, j, false);
  }
  
  public static IntervalType getIntervalType(IntervalType paramIntervalType, long paramLong, int paramInt)
  {
    if ((paramIntervalType.precision >= paramLong) && (paramIntervalType.scale >= paramInt)) {
      return paramIntervalType;
    }
    return getIntervalType(paramIntervalType.typeCode, paramLong, paramInt);
  }
  
  public static IntervalType getIntervalType(int paramInt1, long paramLong, int paramInt2)
  {
    int i = getStartIntervalType(paramInt1);
    int j = getEndIntervalType(paramInt1);
    return getIntervalType(paramInt1, i, j, paramLong, paramInt2, false);
  }
  
  public static IntervalType getIntervalType(int paramInt1, int paramInt2, long paramLong, int paramInt3)
  {
    boolean bool = paramLong == -1L;
    if ((paramInt1 == -1) || (paramInt2 == -1)) {
      throw Error.error(3406);
    }
    if (paramInt1 > paramInt2) {
      throw Error.error(3406);
    }
    if ((paramInt1 <= 1) && (paramInt2 > 1)) {
      throw Error.error(3406);
    }
    int i = DTIType.intervalParts[paramInt1];
    int j = DTIType.intervalParts[paramInt2];
    int k = DTIType.intervalTypes[paramInt1][paramInt2];
    if ((paramLong == 0L) || (paramLong > 9L) || (paramInt3 > 9)) {
      throw Error.error(5592);
    }
    if (paramLong == -1L) {
      paramLong = 2L;
    }
    if (paramInt3 == -1) {
      paramInt3 = j == 106 ? 6 : 0;
    }
    return getIntervalType(k, i, j, paramLong, paramInt3, bool);
  }
  
  public static IntervalType getIntervalType(int paramInt1, int paramInt2, int paramInt3, long paramLong, int paramInt4, boolean paramBoolean)
  {
    int i = paramInt2 > 102 ? 106 : 102;
    if (paramBoolean) {
      return new IntervalType(i, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
    }
    switch (paramInt1)
    {
    case 101: 
      if (paramLong == 2L) {
        return SQL_INTERVAL_YEAR;
      }
      break;
    case 107: 
      if (paramLong == 2L) {
        return SQL_INTERVAL_YEAR_TO_MONTH;
      }
      break;
    case 102: 
      if (paramLong == 2L) {
        return SQL_INTERVAL_MONTH;
      }
      break;
    case 103: 
      if (paramLong == 2L) {
        return SQL_INTERVAL_DAY;
      }
      break;
    case 108: 
      if (paramLong == 2L) {
        return SQL_INTERVAL_DAY_TO_HOUR;
      }
      break;
    case 109: 
      if (paramLong == 2L) {
        return SQL_INTERVAL_DAY_TO_MINUTE;
      }
      break;
    case 110: 
      if ((paramLong == 2L) && (paramInt4 == 6)) {
        return SQL_INTERVAL_DAY_TO_SECOND;
      }
      break;
    case 104: 
      if (paramLong == 2L) {
        return SQL_INTERVAL_HOUR;
      }
      break;
    case 111: 
      if (paramLong == 2L) {
        return SQL_INTERVAL_HOUR_TO_MINUTE;
      }
      break;
    case 105: 
      if (paramLong == 2L) {
        return SQL_INTERVAL_MINUTE;
      }
      break;
    case 112: 
      if ((paramLong == 2L) && (paramInt4 == 6)) {
        return SQL_INTERVAL_HOUR_TO_SECOND;
      }
      break;
    case 113: 
      if ((paramLong == 2L) && (paramInt4 == 6)) {
        return SQL_INTERVAL_MINUTE_TO_SECOND;
      }
      break;
    case 106: 
      if ((paramLong == 2L) && (paramInt4 == 6)) {
        return SQL_INTERVAL_SECOND;
      }
      break;
    default: 
      throw Error.runtimeError(201, "IntervalType");
    }
    return new IntervalType(i, paramInt1, paramLong, paramInt4, paramInt2, paramInt3, paramBoolean);
  }
  
  public static int getStartIntervalType(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 101: 
    case 107: 
      i = 101;
      break;
    case 102: 
      i = 102;
      break;
    case 103: 
    case 108: 
    case 109: 
    case 110: 
      i = 103;
      break;
    case 104: 
    case 111: 
    case 112: 
      i = 104;
      break;
    case 105: 
    case 113: 
      i = 105;
      break;
    case 106: 
      i = 106;
      break;
    default: 
      throw Error.runtimeError(201, "IntervalType");
    }
    return i;
  }
  
  public static int getEndIntervalType(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 101: 
      i = 101;
      break;
    case 107: 
      i = 102;
      break;
    case 102: 
      i = 102;
      break;
    case 103: 
      i = 103;
      break;
    case 108: 
      i = 104;
      break;
    case 109: 
      i = 105;
      break;
    case 110: 
      i = 106;
      break;
    case 104: 
      i = 104;
      break;
    case 111: 
      i = 105;
      break;
    case 112: 
      i = 106;
      break;
    case 105: 
      i = 105;
      break;
    case 113: 
      i = 106;
      break;
    case 106: 
      i = 106;
      break;
    default: 
      throw Error.runtimeError(201, "IntervalType");
    }
    return i;
  }
  
  public static Type getCombinedIntervalType(IntervalType paramIntervalType1, IntervalType paramIntervalType2)
  {
    int i = paramIntervalType2.startIntervalType > paramIntervalType1.startIntervalType ? paramIntervalType1.startIntervalType : paramIntervalType2.startIntervalType;
    int j = paramIntervalType2.endIntervalType > paramIntervalType1.endIntervalType ? paramIntervalType2.endIntervalType : paramIntervalType1.endIntervalType;
    int k = getCombinedIntervalType(i, j);
    long l = paramIntervalType1.precision > paramIntervalType2.precision ? paramIntervalType1.precision : paramIntervalType2.precision;
    int m = paramIntervalType1.scale > paramIntervalType2.scale ? paramIntervalType1.scale : paramIntervalType2.scale;
    return getIntervalType(k, i, j, l, m, false);
  }
  
  public static int getCombinedIntervalType(int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2) {
      return paramInt1;
    }
    switch (paramInt1)
    {
    case 101: 
      if (paramInt2 == 102) {
        return 107;
      }
      break;
    case 103: 
      switch (paramInt2)
      {
      case 104: 
        return 108;
      case 105: 
        return 109;
      case 106: 
        return 110;
      }
      break;
    case 104: 
      switch (paramInt2)
      {
      case 105: 
        return 111;
      case 106: 
        return 112;
      }
      break;
    case 105: 
      if (paramInt2 == 106) {
        return 113;
      }
      break;
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public static int getIntervalType(String paramString)
  {
    int i = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_NAMES, paramString);
    if (i < 0) {
      throw Error.error(5562);
    }
    return intervalParts[i];
  }
  
  long getIntervalValueLimit()
  {
    long l;
    switch (this.typeCode)
    {
    case 101: 
      l = DTIType.precisionLimits[((int)this.precision)] * 12L;
      break;
    case 107: 
      l = DTIType.precisionLimits[((int)this.precision)] * 12L;
      l += 12L;
      break;
    case 102: 
      l = DTIType.precisionLimits[((int)this.precision)];
      break;
    case 103: 
      l = DTIType.precisionLimits[((int)this.precision)] * 24L * 60L * 60L;
      break;
    case 108: 
      l = DTIType.precisionLimits[((int)this.precision)] * 24L * 60L * 60L;
      break;
    case 109: 
      l = DTIType.precisionLimits[((int)this.precision)] * 24L * 60L * 60L;
      break;
    case 110: 
      l = DTIType.precisionLimits[((int)this.precision)] * 24L * 60L * 60L;
      break;
    case 104: 
      l = DTIType.precisionLimits[((int)this.precision)] * 60L * 60L;
      break;
    case 111: 
      l = DTIType.precisionLimits[((int)this.precision)] * 60L * 60L;
      break;
    case 112: 
      l = DTIType.precisionLimits[((int)this.precision)] * 60L * 60L;
      break;
    case 105: 
      l = DTIType.precisionLimits[((int)this.precision)] * 60L;
      break;
    case 113: 
      l = DTIType.precisionLimits[((int)this.precision)] * 60L;
      break;
    case 106: 
      l = DTIType.precisionLimits[((int)this.precision)];
      break;
    default: 
      throw Error.runtimeError(201, "IntervalType");
    }
    return l;
  }
  
  public int getPart(Session paramSession, Object paramObject, int paramInt)
  {
    long l;
    switch (paramInt)
    {
    case 101: 
      return (int)(((IntervalMonthData)paramObject).units / 12L);
    case 102: 
      l = ((IntervalMonthData)paramObject).units;
      return paramInt == this.startIntervalType ? (int)l : (int)(l % 12L);
    case 103: 
      return (int)(((IntervalSecondData)paramObject).units / 86400L);
    case 104: 
      l = ((IntervalSecondData)paramObject).units / 3600L;
      return paramInt == this.startIntervalType ? (int)l : (int)(l % 24L);
    case 105: 
      l = ((IntervalSecondData)paramObject).units / 60L;
      return paramInt == this.startIntervalType ? (int)l : (int)(l % 60L);
    case 106: 
      l = ((IntervalSecondData)paramObject).units;
      return paramInt == this.startIntervalType ? (int)l : (int)(l % 60L);
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public long getSeconds(Object paramObject)
  {
    return ((IntervalSecondData)paramObject).units;
  }
  
  public BigDecimal getSecondPart(Object paramObject)
  {
    long l = ((IntervalSecondData)paramObject).units;
    if (this.typeCode != 106) {
      l %= 60L;
    }
    int i = ((IntervalSecondData)paramObject).nanos;
    return getSecondPart(l, i);
  }
  
  public long convertToLong(Object paramObject)
  {
    switch (this.endIntervalType)
    {
    case 101: 
    case 102: 
      long l1 = ((IntervalMonthData)paramObject).units;
      return l1 / DTIType.yearToSecondFactors[this.endPartIndex];
    case 103: 
    case 104: 
    case 105: 
    case 106: 
      long l2 = ((IntervalSecondData)paramObject).units;
      return l2 / DTIType.yearToSecondFactors[this.endPartIndex];
    }
    throw Error.runtimeError(201, "IntervalType");
  }
  
  public CharacterType getCharacterType()
  {
    CharacterType localCharacterType = CharacterType.getCharacterType(12, displaySize());
    localCharacterType.nameString = getNameString();
    return localCharacterType;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.types.IntervalType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */