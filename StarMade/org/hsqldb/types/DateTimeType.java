package org.hsqldb.types;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlException;
import org.hsqldb.Scanner;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;

public final class DateTimeType
  extends DTIType
{
  public final boolean withTimeZone;
  private String nameString;
  public static final long epochSeconds = HsqlDateTime.getDateSeconds("1-01-01");
  public static final TimestampData epochTimestamp = new TimestampData(epochSeconds);
  
  public DateTimeType(int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramInt1, paramInt2, 0L, paramInt3);
    this.withTimeZone = ((paramInt2 == 94) || (paramInt2 == 95));
    this.nameString = getNameStringPrivate();
  }
  
  public int displaySize()
  {
    switch (this.typeCode)
    {
    case 91: 
      return 10;
    case 92: 
      return 8 + (this.scale == 0 ? 0 : this.scale + 1);
    case 94: 
      return 8 + (this.scale == 0 ? 0 : this.scale + 1) + 6;
    case 93: 
      return 19 + (this.scale == 0 ? 0 : this.scale + 1);
    case 95: 
      return 19 + (this.scale == 0 ? 0 : this.scale + 1) + 6;
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public int getJDBCTypeCode()
  {
    return this.typeCode;
  }
  
  public Class getJDBCClass()
  {
    switch (this.typeCode)
    {
    case 91: 
      return java.sql.Date.class;
    case 92: 
    case 94: 
      return Time.class;
    case 93: 
    case 95: 
      return Timestamp.class;
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public String getJDBCClassName()
  {
    switch (this.typeCode)
    {
    case 91: 
      return "java.sql.Date";
    case 92: 
    case 94: 
      return "java.sql.Time";
    case 93: 
    case 95: 
      return "java.sql.Timestamp";
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public int getJDBCPrecision()
  {
    return displaySize();
  }
  
  public int getSQLGenericTypeCode()
  {
    return 9;
  }
  
  public String getNameString()
  {
    return this.nameString;
  }
  
  private String getNameStringPrivate()
  {
    switch (this.typeCode)
    {
    case 91: 
      return "DATE";
    case 92: 
      return "TIME";
    case 94: 
      return "TIME WITH TIME ZONE";
    case 93: 
      return "TIMESTAMP";
    case 95: 
      return "TIMESTAMP WITH TIME ZONE";
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public String getDefinition()
  {
    String str;
    switch (this.typeCode)
    {
    case 91: 
      return "DATE";
    case 92: 
    case 94: 
      if (this.scale == 0) {
        return getNameString();
      }
      str = "TIME";
      break;
    case 93: 
    case 95: 
      if (this.scale == 6) {
        return getNameString();
      }
      str = "TIMESTAMP";
      break;
    default: 
      throw Error.runtimeError(201, "DateTimeType");
    }
    StringBuffer localStringBuffer = new StringBuffer(16);
    localStringBuffer.append(str);
    localStringBuffer.append('(');
    localStringBuffer.append(this.scale);
    localStringBuffer.append(')');
    if (this.withTimeZone) {
      localStringBuffer.append(" WITH TIME ZONE");
    }
    return localStringBuffer.toString();
  }
  
  public boolean isDateTimeType()
  {
    return true;
  }
  
  public boolean isDateTimeTypeWithZone()
  {
    return this.withTimeZone;
  }
  
  public boolean acceptsFractionalPrecision()
  {
    return this.typeCode != 91;
  }
  
  public Type getAggregateType(Type paramType)
  {
    if (paramType == null) {
      return this;
    }
    if (paramType == SQL_ALL_TYPES) {
      return this;
    }
    if (this.typeCode == paramType.typeCode) {
      return this.scale >= paramType.scale ? this : paramType;
    }
    if (paramType.typeCode == 0) {
      return this;
    }
    if (paramType.isCharacterType()) {
      return paramType.getAggregateType(this);
    }
    if (!paramType.isDateTimeType()) {
      throw Error.error(5562);
    }
    DateTimeType localDateTimeType = (DateTimeType)paramType;
    if ((localDateTimeType.startIntervalType > this.endIntervalType) || (this.startIntervalType > localDateTimeType.endIntervalType)) {
      throw Error.error(5562);
    }
    int i = this.typeCode;
    int j = this.scale > localDateTimeType.scale ? this.scale : localDateTimeType.scale;
    int k = (this.withTimeZone) || (localDateTimeType.withTimeZone) ? 1 : 0;
    int m = localDateTimeType.startIntervalType > this.startIntervalType ? this.startIntervalType : localDateTimeType.startIntervalType;
    if (m == 104) {
      i = k != 0 ? 94 : 92;
    } else {
      i = k != 0 ? 95 : 93;
    }
    return getDateTimeType(i, j);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    switch (paramInt)
    {
    case 41: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
      if (this.typeCode == paramType.typeCode) {
        return this;
      }
      if (paramType.typeCode == 0) {
        return this;
      }
      if (!paramType.isDateTimeType()) {
        throw Error.error(5562);
      }
      DateTimeType localDateTimeType = (DateTimeType)paramType;
      if ((localDateTimeType.startIntervalType > this.endIntervalType) || (this.startIntervalType > localDateTimeType.endIntervalType)) {
        throw Error.error(5562);
      }
      int i = this.typeCode;
      int j = this.scale > localDateTimeType.scale ? this.scale : localDateTimeType.scale;
      int k = (this.withTimeZone) || (localDateTimeType.withTimeZone) ? 1 : 0;
      int m = localDateTimeType.startIntervalType > this.startIntervalType ? this.startIntervalType : localDateTimeType.startIntervalType;
      if (m == 104) {
        i = k != 0 ? 94 : 92;
      } else {
        i = k != 0 ? 95 : 93;
      }
      return getDateTimeType(i, j);
    case 32: 
    case 33: 
      if (paramType.isIntervalType())
      {
        if ((this.typeCode != 91) && (paramType.scale > this.scale)) {
          return getDateTimeType(this.typeCode, paramType.scale);
        }
        return this;
      }
      if (paramType.isDateTimeType())
      {
        if ((paramInt == 33) && (paramType.typeComparisonGroup == this.typeComparisonGroup))
        {
          if (this.typeCode == 91) {
            return Type.SQL_INTERVAL_DAY_MAX_PRECISION;
          }
          return Type.SQL_INTERVAL_SECOND_MAX_FRACTION_MAX_PRECISION;
        }
      }
      else if (paramType.isNumberType()) {
        return this;
      }
      break;
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
    long l;
    switch (this.typeCode)
    {
    case 92: 
    case 94: 
      l = ((TimeData)paramObject1).getSeconds() - ((TimeData)paramObject2).getSeconds();
      if (l == 0L) {
        l = ((TimeData)paramObject1).getNanos() - ((TimeData)paramObject2).getNanos();
      }
      return l > 0L ? 1 : l == 0L ? 0 : -1;
    case 91: 
    case 93: 
    case 95: 
      l = ((TimestampData)paramObject1).getSeconds() - ((TimestampData)paramObject2).getSeconds();
      if (l == 0L) {
        l = ((TimestampData)paramObject1).getNanos() - ((TimestampData)paramObject2).getNanos();
      }
      return l > 0L ? 1 : l == 0L ? 0 : -1;
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if (this.scale == 9) {
      return paramObject;
    }
    Object localObject;
    int i;
    int j;
    switch (this.typeCode)
    {
    case 91: 
      return paramObject;
    case 92: 
    case 94: 
      localObject = (TimeData)paramObject;
      i = ((TimeData)localObject).getNanos();
      j = scaleNanos(i);
      if (j == i) {
        return localObject;
      }
      return new TimeData(((TimeData)localObject).getSeconds(), j, ((TimeData)localObject).getZone());
    case 93: 
    case 95: 
      localObject = (TimestampData)paramObject;
      i = ((TimestampData)localObject).getNanos();
      j = scaleNanos(i);
      if (j == i) {
        return localObject;
      }
      return new TimestampData(((TimestampData)localObject).getSeconds(), j, ((TimestampData)localObject).getZone());
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  int scaleNanos(int paramInt)
  {
    int i = nanoScaleFactors[this.scale];
    return paramInt / i * i;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null) {
      return paramObject;
    }
    switch (paramType.typeCode)
    {
    case 40: 
      paramObject = paramObject.toString();
    case 1: 
    case 12: 
    case 100: 
      switch (this.typeCode)
      {
      case 91: 
      case 92: 
      case 93: 
      case 94: 
      case 95: 
        return paramSessionInterface.getScanner().convertToDatetimeInterval(paramSessionInterface, (String)paramObject, this);
      }
      break;
    case 91: 
    case 92: 
    case 93: 
    case 94: 
    case 95: 
      break;
    default: 
      throw Error.error(5561);
    }
    Object localObject;
    long l2;
    switch (this.typeCode)
    {
    case 91: 
      long l1;
      switch (paramType.typeCode)
      {
      case 91: 
        return paramObject;
      case 95: 
        l1 = ((TimestampData)paramObject).getSeconds() + ((TimestampData)paramObject).getZone();
        long l3 = HsqlDateTime.getNormalisedDate(l1 * 1000L);
        return new TimestampData(l3 / 1000L);
      case 93: 
        l1 = HsqlDateTime.getNormalisedDate(((TimestampData)paramObject).getSeconds() * 1000L);
        return new TimestampData(l1 / 1000L);
      }
      throw Error.error(5561);
    case 94: 
      switch (paramType.typeCode)
      {
      case 94: 
        return convertToTypeLimits(paramSessionInterface, paramObject);
      case 92: 
        localObject = (TimeData)paramObject;
        return new TimeData(((TimeData)localObject).getSeconds() - paramSessionInterface.getZoneSeconds(), scaleNanos(((TimeData)localObject).getNanos()), paramSessionInterface.getZoneSeconds());
      case 95: 
        localObject = (TimestampData)paramObject;
        l2 = HsqlDateTime.convertToNormalisedTime(((TimestampData)localObject).getSeconds() * 1000L) / 1000L;
        return new TimeData((int)l2, scaleNanos(((TimestampData)localObject).getNanos()), ((TimestampData)localObject).getZone());
      case 93: 
        localObject = (TimestampData)paramObject;
        l2 = ((TimestampData)localObject).getSeconds() - paramSessionInterface.getZoneSeconds();
        l2 = HsqlDateTime.convertToNormalisedTime(l2 * 1000L) / 1000L;
        return new TimeData((int)l2, scaleNanos(((TimestampData)localObject).getNanos()), paramSessionInterface.getZoneSeconds());
      }
      throw Error.error(5561);
    case 92: 
      switch (paramType.typeCode)
      {
      case 94: 
        localObject = (TimeData)paramObject;
        return new TimeData(((TimeData)localObject).getSeconds() + ((TimeData)localObject).getZone(), scaleNanos(((TimeData)localObject).getNanos()), 0);
      case 92: 
        return convertToTypeLimits(paramSessionInterface, paramObject);
      case 95: 
        localObject = (TimestampData)paramObject;
        l2 = ((TimestampData)localObject).getSeconds() + ((TimestampData)localObject).getZone();
        l2 = HsqlDateTime.convertToNormalisedTime(l2 * 1000L) / 1000L;
        return new TimeData((int)l2, scaleNanos(((TimestampData)localObject).getNanos()), 0);
      case 93: 
        localObject = (TimestampData)paramObject;
        l2 = HsqlDateTime.convertToNormalisedTime(((TimestampData)localObject).getSeconds() * 1000L) / 1000L;
        return new TimeData((int)l2, scaleNanos(((TimestampData)localObject).getNanos()));
      }
      throw Error.error(5561);
    case 95: 
      switch (paramType.typeCode)
      {
      case 94: 
        localObject = (TimeData)paramObject;
        l2 = paramSessionInterface.getCurrentDate().getSeconds() + ((TimeData)localObject).getSeconds();
        return new TimestampData(l2, scaleNanos(((TimeData)localObject).getNanos()), ((TimeData)localObject).getZone());
      case 92: 
        localObject = (TimeData)paramObject;
        l2 = paramSessionInterface.getCurrentDate().getSeconds() + ((TimeData)localObject).getSeconds() - paramSessionInterface.getZoneSeconds();
        return new TimestampData(l2, scaleNanos(((TimeData)localObject).getNanos()), paramSessionInterface.getZoneSeconds());
      case 95: 
        return convertToTypeLimits(paramSessionInterface, paramObject);
      case 93: 
        localObject = (TimestampData)paramObject;
        l2 = ((TimestampData)localObject).getSeconds() - paramSessionInterface.getZoneSeconds();
        return new TimestampData(l2, scaleNanos(((TimestampData)localObject).getNanos()), paramSessionInterface.getZoneSeconds());
      case 91: 
        localObject = (TimestampData)paramObject;
        return new TimestampData(((TimestampData)localObject).getSeconds(), 0, paramSessionInterface.getZoneSeconds());
      }
      throw Error.error(5561);
    case 93: 
      switch (paramType.typeCode)
      {
      case 94: 
        localObject = (TimeData)paramObject;
        l2 = paramSessionInterface.getCurrentDate().getSeconds() + ((TimeData)localObject).getSeconds() - paramSessionInterface.getZoneSeconds();
        return new TimestampData(l2, scaleNanos(((TimeData)localObject).getNanos()), paramSessionInterface.getZoneSeconds());
      case 92: 
        localObject = (TimeData)paramObject;
        l2 = paramSessionInterface.getCurrentDate().getSeconds() + ((TimeData)localObject).getSeconds();
        return new TimestampData(l2, scaleNanos(((TimeData)localObject).getNanos()));
      case 95: 
        localObject = (TimestampData)paramObject;
        l2 = ((TimestampData)localObject).getSeconds() + ((TimestampData)localObject).getZone();
        return new TimestampData(l2, scaleNanos(((TimestampData)localObject).getNanos()));
      case 93: 
        return convertToTypeLimits(paramSessionInterface, paramObject);
      case 91: 
        return paramObject;
      }
      throw Error.error(5561);
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    DateTimeType localDateTimeType = (paramObject instanceof TimeData) ? Type.SQL_TIME : Type.SQL_TIMESTAMP;
    return convertToType(paramSessionInterface, paramObject, localDateTimeType);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    int i;
    int j;
    long l;
    switch (this.typeCode)
    {
    case 92: 
    case 94: 
      if ((!(paramObject instanceof java.sql.Date)) && ((paramObject instanceof java.util.Date)))
      {
        i = 0;
        j = 0;
        if (this.typeCode == 92)
        {
          l = HsqlDateTime.convertMillisFromCalendar(paramSessionInterface.getCalendar(), ((java.util.Date)paramObject).getTime());
        }
        else
        {
          l = ((java.util.Date)paramObject).getTime();
          j = paramSessionInterface.getZoneSeconds();
        }
        l = HsqlDateTime.getNormalisedTime(l);
        if ((paramObject instanceof Timestamp))
        {
          i = ((Timestamp)paramObject).getNanos();
          i = normaliseFraction(i, this.scale);
        }
        return new TimeData((int)l / 1000, i, j);
      }
      break;
    case 91: 
      if ((!(paramObject instanceof Time)) && ((paramObject instanceof java.util.Date)))
      {
        l = HsqlDateTime.convertMillisFromCalendar(paramSessionInterface.getCalendar(), ((java.util.Date)paramObject).getTime());
        l = HsqlDateTime.getNormalisedDate(l);
        return new TimestampData(l / 1000L);
      }
      break;
    case 93: 
    case 95: 
      if ((!(paramObject instanceof Time)) && ((paramObject instanceof java.util.Date)))
      {
        i = 0;
        j = 0;
        if (this.typeCode == 93)
        {
          l = HsqlDateTime.convertMillisFromCalendar(paramSessionInterface.getCalendar(), ((java.util.Date)paramObject).getTime());
        }
        else
        {
          l = ((java.util.Date)paramObject).getTime();
          j = HsqlDateTime.getZoneMillis(paramSessionInterface.getCalendar(), l) / 1000;
        }
        if ((paramObject instanceof Timestamp))
        {
          i = ((Timestamp)paramObject).getNanos();
          i = normaliseFraction(i, this.scale);
        }
        return new TimestampData(l / 1000L, i, j);
      }
      break;
    }
    throw Error.error(5561);
  }
  
  public Object convertSQLToJavaGMT(SessionInterface paramSessionInterface, Object paramObject)
  {
    long l;
    switch (this.typeCode)
    {
    case 92: 
    case 94: 
      l = ((TimeData)paramObject).getSeconds() * 1000;
      l += ((TimeData)paramObject).getNanos() / 1000000;
      return new Time(l);
    case 91: 
      l = ((TimestampData)paramObject).getSeconds() * 1000L;
      return new java.sql.Date(l);
    case 93: 
    case 95: 
      l = ((TimestampData)paramObject).getSeconds() * 1000L;
      Timestamp localTimestamp = new Timestamp(l);
      localTimestamp.setNanos(((TimestampData)paramObject).getNanos());
      return localTimestamp;
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    long l2;
    Object localObject;
    Calendar localCalendar2;
    switch (this.typeCode)
    {
    case 92: 
      Calendar localCalendar1 = paramSessionInterface.getCalendar();
      l2 = HsqlDateTime.convertMillisToCalendar(localCalendar1, ((TimeData)paramObject).getSeconds() * 1000);
      l2 = HsqlDateTime.getNormalisedTime(localCalendar1, l2);
      localObject = new Time(l2);
      return localObject;
    case 94: 
      int i = ((TimeData)paramObject).getSeconds();
      return new Time(i * 1000);
    case 91: 
      localCalendar2 = paramSessionInterface.getCalendar();
      l2 = HsqlDateTime.convertMillisToCalendar(localCalendar2, ((TimestampData)paramObject).getSeconds() * 1000L);
      localObject = new java.sql.Date(l2);
      return localObject;
    case 93: 
      localCalendar2 = paramSessionInterface.getCalendar();
      l2 = HsqlDateTime.convertMillisToCalendar(localCalendar2, ((TimestampData)paramObject).getSeconds() * 1000L);
      localObject = new Timestamp(l2);
      ((Timestamp)localObject).setNanos(((TimestampData)paramObject).getNanos());
      return localObject;
    case 95: 
      long l1 = ((TimestampData)paramObject).getSeconds();
      Timestamp localTimestamp = new Timestamp(l1 * 1000L);
      localTimestamp.setNanos(((TimestampData)paramObject).getNanos());
      return localTimestamp;
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public static int normaliseTime(int paramInt)
  {
    while (paramInt < 0) {
      paramInt += 86400;
    }
    if (paramInt > 86400) {
      paramInt %= 86400;
    }
    return paramInt;
  }
  
  public String convertToString(Object paramObject)
  {
    int i = 0;
    if (paramObject == null) {
      return null;
    }
    Object localObject;
    String str;
    StringBuffer localStringBuffer;
    switch (this.typeCode)
    {
    case 91: 
      return HsqlDateTime.getDateString(((TimestampData)paramObject).getSeconds());
    case 92: 
    case 94: 
      localObject = (TimeData)paramObject;
      int j = normaliseTime(((TimeData)localObject).getSeconds() + ((TimeData)localObject).getZone());
      str = intervalSecondToString(j, ((TimeData)localObject).getNanos(), false);
      if (!this.withTimeZone) {
        return str;
      }
      localStringBuffer = new StringBuffer(str);
      str = Type.SQL_INTERVAL_HOUR_TO_MINUTE.intervalSecondToString(((TimeData)paramObject).getZone(), 0, true);
      localStringBuffer.append(str);
      return localStringBuffer.toString();
    case 93: 
    case 95: 
      localObject = (TimestampData)paramObject;
      localStringBuffer = new StringBuffer();
      HsqlDateTime.getTimestampString(localStringBuffer, ((TimestampData)localObject).getSeconds() + ((TimestampData)localObject).getZone(), ((TimestampData)localObject).getNanos(), this.scale);
      if (!this.withTimeZone) {
        return localStringBuffer.toString();
      }
      str = Type.SQL_INTERVAL_HOUR_TO_MINUTE.intervalSecondToString(((TimestampData)paramObject).getZone(), 0, true);
      localStringBuffer.append(str);
      return localStringBuffer.toString();
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null) {
      return "NULL";
    }
    StringBuffer localStringBuffer = new StringBuffer(32);
    switch (this.typeCode)
    {
    case 91: 
      localStringBuffer.append("DATE");
      break;
    case 92: 
    case 94: 
      localStringBuffer.append("TIME");
      break;
    case 93: 
    case 95: 
      localStringBuffer.append("TIMESTAMP");
    }
    localStringBuffer.append(StringConverter.toQuotedString(convertToString(paramObject), '\'', false));
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
    if (!paramType.isDateTimeType()) {
      return false;
    }
    if (paramType.typeCode == 91) {
      return this.typeCode != 92;
    }
    if (paramType.typeCode == 92) {
      return this.typeCode != 91;
    }
    return true;
  }
  
  public int canMoveFrom(Type paramType)
  {
    if (paramType == this) {
      return 0;
    }
    if (this.typeCode == paramType.typeCode) {
      return this.scale >= paramType.scale ? 0 : -1;
    }
    return -1;
  }
  
  public Object add(Object paramObject1, Object paramObject2, Type paramType)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    if (paramType.isNumberType())
    {
      if (this.typeCode == 91) {
        paramObject2 = ((NumberType)paramType).floor(paramObject2);
      }
      paramObject2 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION.multiply(IntervalSecondData.oneDay, paramObject2);
    }
    switch (this.typeCode)
    {
    case 92: 
    case 94: 
      if ((paramObject2 instanceof IntervalMonthData)) {
        throw Error.runtimeError(201, "DateTimeType");
      }
      if ((paramObject2 instanceof IntervalSecondData)) {
        return addSeconds((TimeData)paramObject1, ((IntervalSecondData)paramObject2).units, ((IntervalSecondData)paramObject2).nanos);
      }
      break;
    case 91: 
    case 93: 
    case 95: 
      if ((paramObject2 instanceof IntervalMonthData)) {
        return addMonths((TimestampData)paramObject1, (int)((IntervalMonthData)paramObject2).units);
      }
      if ((paramObject2 instanceof IntervalSecondData)) {
        return addSeconds((TimestampData)paramObject1, ((IntervalSecondData)paramObject2).units, ((IntervalSecondData)paramObject2).nanos);
      }
      break;
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object subtract(Object paramObject1, Object paramObject2, Type paramType)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    if (paramType.isNumberType())
    {
      if (this.typeCode == 91) {
        paramObject2 = ((NumberType)paramType).floor(paramObject2);
      }
      paramObject2 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION.multiply(IntervalSecondData.oneDay, paramObject2);
    }
    switch (this.typeCode)
    {
    case 92: 
    case 94: 
      if ((paramObject2 instanceof IntervalMonthData)) {
        throw Error.runtimeError(201, "DateTimeType");
      }
      if ((paramObject2 instanceof IntervalSecondData)) {
        return addSeconds((TimeData)paramObject1, -((IntervalSecondData)paramObject2).units, -((IntervalSecondData)paramObject2).nanos);
      }
      break;
    case 91: 
    case 93: 
    case 95: 
      if ((paramObject2 instanceof IntervalMonthData)) {
        return addMonths((TimestampData)paramObject1, -(int)((IntervalMonthData)paramObject2).units);
      }
      if ((paramObject2 instanceof IntervalSecondData)) {
        return addSeconds((TimestampData)paramObject1, -((IntervalSecondData)paramObject2).units, -((IntervalSecondData)paramObject2).nanos);
      }
      break;
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object truncate(Object paramObject, int paramInt)
  {
    if (paramObject == null) {
      return null;
    }
    long l = getMillis(paramObject);
    l = HsqlDateTime.getTruncatedPart(l, paramInt);
    l -= getZoneMillis(paramObject);
    switch (this.typeCode)
    {
    case 94: 
      l = HsqlDateTime.getNormalisedTime(l);
    case 92: 
      return new TimeData((int)(l / 1000L), 0, ((TimeData)paramObject).getZone());
    case 91: 
    case 93: 
    case 95: 
      return new TimestampData(l / 1000L, 0, ((TimestampData)paramObject).getZone());
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object round(Object paramObject, int paramInt)
  {
    if (paramObject == null) {
      return null;
    }
    long l = getMillis(paramObject);
    l = HsqlDateTime.getRoundedPart(l, paramInt);
    l -= getZoneMillis(paramObject);
    switch (this.typeCode)
    {
    case 92: 
    case 94: 
      l = HsqlDateTime.getNormalisedTime(l);
      return new TimeData((int)(l / 1000L), 0, ((TimeData)paramObject).getZone());
    case 91: 
    case 93: 
    case 95: 
      return new TimestampData(l / 1000L, 0, ((TimestampData)paramObject).getZone());
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Type)) {
      return (super.equals(paramObject)) && (((DateTimeType)paramObject).withTimeZone == this.withTimeZone);
    }
    return false;
  }
  
  public int getPart(Session paramSession, Object paramObject, int paramInt)
  {
    int j = 0;
    int k = 1;
    int i;
    switch (paramInt)
    {
    case 101: 
      i = 1;
      break;
    case 102: 
      j = 1;
      i = 2;
      break;
    case 103: 
    case 260: 
      i = 5;
      break;
    case 104: 
      i = 11;
      break;
    case 105: 
      i = 12;
      break;
    case 106: 
      i = 13;
      break;
    case 259: 
      i = 7;
      break;
    case 262: 
      i = 3;
      break;
    case 266: 
      if ((this.typeCode != 92) && (this.typeCode != 94)) {
        try
        {
          DateTimeType localDateTimeType = this.withTimeZone ? Type.SQL_TIME_WITH_TIME_ZONE : Type.SQL_TIME;
          paramObject = localDateTimeType.castToType(paramSession, paramObject, this);
        }
        catch (HsqlException localHsqlException) {}
      }
      return ((TimeData)paramObject).getSeconds();
    case 257: 
      if (this.typeCode == 95) {
        return ((TimestampData)paramObject).getZone() / 3600;
      }
      return ((TimeData)paramObject).getZone() / 3600;
    case 258: 
      if (this.typeCode == 95) {
        return ((TimestampData)paramObject).getZone() / 60 % 60;
      }
      return ((TimeData)paramObject).getZone() / 60 % 60;
    case 263: 
      j = 1;
      k = 3;
      i = 2;
      break;
    case 261: 
      i = 6;
      break;
    default: 
      throw Error.runtimeError(201, "DateTimeType - " + paramInt);
    }
    long l = getMillis(paramObject);
    return HsqlDateTime.getDateTimePart(l, i) / k + j;
  }
  
  long getMillis(Object paramObject)
  {
    long l;
    if ((this.typeCode == 92) || (this.typeCode == 94)) {
      l = (((TimeData)paramObject).getSeconds() + ((TimeData)paramObject).getZone()) * 1000;
    } else {
      l = (((TimestampData)paramObject).getSeconds() + ((TimestampData)paramObject).getZone()) * 1000L;
    }
    return l;
  }
  
  long getZoneMillis(Object paramObject)
  {
    long l;
    if ((this.typeCode == 92) || (this.typeCode == 94)) {
      l = ((TimeData)paramObject).getZone() * 1000;
    } else {
      l = ((TimestampData)paramObject).getZone() * 1000;
    }
    return l;
  }
  
  public BigDecimal getSecondPart(Object paramObject)
  {
    long l = getPart(null, paramObject, 106);
    int i = 0;
    if (this.typeCode == 93) {
      i = ((TimestampData)paramObject).getNanos();
    } else if (this.typeCode == 92) {
      i = ((TimeData)paramObject).getNanos();
    }
    return getSecondPart(l, i);
  }
  
  public String getPartString(Session paramSession, Object paramObject, int paramInt)
  {
    String str = "";
    switch (paramInt)
    {
    case 264: 
      str = "EEEE";
      break;
    case 265: 
      str = "MMMM";
    }
    SimpleDateFormat localSimpleDateFormat = paramSession.getSimpleDateFormatGMT();
    try
    {
      localSimpleDateFormat.applyPattern(str);
    }
    catch (Exception localException) {}
    java.util.Date localDate = (java.util.Date)convertSQLToJavaGMT(paramSession, paramObject);
    return localSimpleDateFormat.format(localDate);
  }
  
  public Object getValue(long paramLong, int paramInt1, int paramInt2)
  {
    switch (this.typeCode)
    {
    case 91: 
      paramLong = HsqlDateTime.getNormalisedDate((paramLong + paramInt2) * 1000L) / 1000L;
      return new TimestampData(paramLong);
    case 94: 
      paramLong = HsqlDateTime.getNormalisedDate(paramLong * 1000L) / 1000L;
      return new TimeData((int)paramLong, paramInt1, paramInt2);
    case 92: 
      paramLong = HsqlDateTime.getNormalisedTime((paramLong + paramInt2) * 1000L) / 1000L;
      return new TimeData((int)paramLong, paramInt1);
    case 95: 
      return new TimestampData(paramLong, paramInt1, paramInt2);
    case 93: 
      return new TimestampData(paramLong + paramInt2, paramInt1);
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public DateTimeType getDateTimeTypeWithoutZone()
  {
    if (this.withTimeZone)
    {
      DateTimeType localDateTimeType;
      switch (this.typeCode)
      {
      case 94: 
        localDateTimeType = new DateTimeType(92, 92, this.scale);
        break;
      case 95: 
        localDateTimeType = new DateTimeType(93, 93, this.scale);
        break;
      default: 
        throw Error.runtimeError(201, "DateTimeType");
      }
      localDateTimeType.nameString = this.nameString;
      return localDateTimeType;
    }
    return this;
  }
  
  public static DateTimeType getDateTimeType(int paramInt1, int paramInt2)
  {
    if (paramInt2 > 9) {
      throw Error.error(5592);
    }
    switch (paramInt1)
    {
    case 91: 
      return SQL_DATE;
    case 92: 
      if (paramInt2 == 0) {
        return SQL_TIME;
      }
      return new DateTimeType(92, paramInt1, paramInt2);
    case 94: 
      if (paramInt2 == 0) {
        return SQL_TIME_WITH_TIME_ZONE;
      }
      return new DateTimeType(92, paramInt1, paramInt2);
    case 93: 
      if (paramInt2 == 6) {
        return SQL_TIMESTAMP;
      }
      return new DateTimeType(93, paramInt1, paramInt2);
    case 95: 
      if (paramInt2 == 6) {
        return SQL_TIMESTAMP_WITH_TIME_ZONE;
      }
      return new DateTimeType(93, paramInt1, paramInt2);
    }
    throw Error.runtimeError(201, "DateTimeType");
  }
  
  public Object changeZone(Object paramObject, Type paramType, int paramInt1, int paramInt2)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramType.typeCode == 95) || (paramType.typeCode == 94)) {
      paramInt2 = 0;
    }
    if ((paramInt1 > 50400) || (-paramInt1 > 50400)) {
      throw Error.error(3409);
    }
    Object localObject;
    switch (this.typeCode)
    {
    case 94: 
      localObject = (TimeData)paramObject;
      if ((paramInt2 != 0) || (((TimeData)localObject).zone != paramInt1)) {
        return new TimeData(((TimeData)localObject).getSeconds() - paramInt2, ((TimeData)localObject).getNanos(), paramInt1);
      }
      break;
    case 95: 
      localObject = (TimestampData)paramObject;
      if ((paramInt2 != 0) || (((TimestampData)localObject).zone != paramInt1)) {
        return new TimestampData(((TimestampData)localObject).getSeconds() - paramInt2, ((TimestampData)localObject).getNanos(), paramInt1);
      }
      break;
    }
    return paramObject;
  }
  
  public boolean canAdd(IntervalType paramIntervalType)
  {
    return (paramIntervalType.startPartIndex >= this.startPartIndex) && (paramIntervalType.endPartIndex <= this.endPartIndex);
  }
  
  public int getSqlDateTimeSub()
  {
    switch (this.typeCode)
    {
    case 91: 
      return 1;
    case 92: 
      return 2;
    case 93: 
      return 3;
    }
    return 0;
  }
  
  public static Boolean overlaps(Session paramSession, Object[] paramArrayOfObject1, Type[] paramArrayOfType1, Object[] paramArrayOfObject2, Type[] paramArrayOfType2)
  {
    if ((paramArrayOfObject1 == null) || (paramArrayOfObject2 == null)) {
      return null;
    }
    if ((paramArrayOfObject1[0] == null) || (paramArrayOfObject2[0] == null)) {
      return null;
    }
    if (paramArrayOfObject1[1] == null) {
      paramArrayOfObject1[1] = paramArrayOfObject1[0];
    }
    if (paramArrayOfObject2[1] == null) {
      paramArrayOfObject2[1] = paramArrayOfObject2[0];
    }
    Type localType = paramArrayOfType1[0].getCombinedType(paramSession, paramArrayOfType2[0], 41);
    paramArrayOfObject1[0] = localType.castToType(paramSession, paramArrayOfObject1[0], paramArrayOfType1[0]);
    paramArrayOfObject2[0] = localType.castToType(paramSession, paramArrayOfObject2[0], paramArrayOfType2[0]);
    if (paramArrayOfType1[1].isIntervalType()) {
      paramArrayOfObject1[1] = localType.add(paramArrayOfObject1[0], paramArrayOfObject1[1], paramArrayOfType1[1]);
    } else {
      paramArrayOfObject1[1] = localType.castToType(paramSession, paramArrayOfObject1[1], paramArrayOfType1[1]);
    }
    if (paramArrayOfType2[1].isIntervalType()) {
      paramArrayOfObject2[1] = localType.add(paramArrayOfObject2[0], paramArrayOfObject2[1], paramArrayOfType2[1]);
    } else {
      paramArrayOfObject2[1] = localType.castToType(paramSession, paramArrayOfObject2[1], paramArrayOfType2[1]);
    }
    Object localObject;
    if (localType.compare(paramSession, paramArrayOfObject1[0], paramArrayOfObject1[1]) > 0)
    {
      localObject = paramArrayOfObject1[0];
      paramArrayOfObject1[0] = paramArrayOfObject1[1];
      paramArrayOfObject1[1] = localObject;
    }
    if (localType.compare(paramSession, paramArrayOfObject2[0], paramArrayOfObject2[1]) > 0)
    {
      localObject = paramArrayOfObject2[0];
      paramArrayOfObject2[0] = paramArrayOfObject2[1];
      paramArrayOfObject2[1] = localObject;
    }
    if (localType.compare(paramSession, paramArrayOfObject1[0], paramArrayOfObject2[0]) > 0)
    {
      localObject = paramArrayOfObject1;
      paramArrayOfObject1 = paramArrayOfObject2;
      paramArrayOfObject2 = (Object[])localObject;
    }
    if (localType.compare(paramSession, paramArrayOfObject1[1], paramArrayOfObject2[0]) > 0) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public static int subtractMonths(TimestampData paramTimestampData1, TimestampData paramTimestampData2, boolean paramBoolean)
  {
    synchronized (HsqlDateTime.tempCalGMT)
    {
      int i = 0;
      if (paramTimestampData2.getSeconds() > paramTimestampData1.getSeconds())
      {
        i = 1;
        TimestampData localTimestampData = paramTimestampData1;
        paramTimestampData1 = paramTimestampData2;
        paramTimestampData2 = localTimestampData;
      }
      HsqlDateTime.setTimeInMillis(HsqlDateTime.tempCalGMT, paramTimestampData1.getSeconds() * 1000L);
      int j = HsqlDateTime.tempCalGMT.get(2);
      int k = HsqlDateTime.tempCalGMT.get(1);
      HsqlDateTime.setTimeInMillis(HsqlDateTime.tempCalGMT, paramTimestampData2.getSeconds() * 1000L);
      j -= HsqlDateTime.tempCalGMT.get(2);
      k -= HsqlDateTime.tempCalGMT.get(1);
      if (paramBoolean)
      {
        j = k * 12;
      }
      else
      {
        if (j < 0)
        {
          j += 12;
          k--;
        }
        j += k * 12;
      }
      if (i != 0) {
        j = -j;
      }
      return j;
    }
  }
  
  public static TimeData addSeconds(TimeData paramTimeData, long paramLong, int paramInt)
  {
    paramInt += paramTimeData.getNanos();
    paramLong += paramInt / 1000000000;
    paramInt %= 1000000000;
    if (paramInt < 0)
    {
      paramInt += 1000000000;
      paramLong -= 1L;
    }
    paramLong += paramTimeData.getSeconds();
    paramLong %= 86400L;
    TimeData localTimeData = new TimeData((int)paramLong, paramInt, paramTimeData.getZone());
    return localTimeData;
  }
  
  public static TimestampData addMonths(TimestampData paramTimestampData, int paramInt)
  {
    int i = paramTimestampData.getNanos();
    synchronized (HsqlDateTime.tempCalGMT)
    {
      HsqlDateTime.setTimeInMillis(HsqlDateTime.tempCalGMT, paramTimestampData.getSeconds() * 1000L);
      HsqlDateTime.tempCalGMT.add(2, paramInt);
      TimestampData localTimestampData = new TimestampData(HsqlDateTime.tempCalGMT.getTimeInMillis() / 1000L, i, paramTimestampData.getZone());
      return localTimestampData;
    }
  }
  
  public static TimestampData addSeconds(TimestampData paramTimestampData, long paramLong, int paramInt)
  {
    paramInt += paramTimestampData.getNanos();
    paramLong += paramInt / 1000000000;
    paramInt %= 1000000000;
    if (paramInt < 0)
    {
      paramInt += 1000000000;
      paramLong -= 1L;
    }
    long l = paramTimestampData.getSeconds() + paramLong;
    TimestampData localTimestampData = new TimestampData(l, paramInt, paramTimestampData.getZone());
    return localTimestampData;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.types.DateTimeType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */