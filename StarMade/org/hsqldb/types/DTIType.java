package org.hsqldb.types;

import java.math.BigDecimal;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.lib.IntKeyIntValueHashMap;

public abstract class DTIType
  extends Type
{
  public static final byte[] yearToSecondSeparators = { 45, 45, 32, 58, 58, 46 };
  public static final int[] yearToSecondFactors = { 12, 1, 86400, 3600, 60, 1, 0 };
  public static final int[] yearToSecondLimits = { 0, 12, 0, 24, 60, 60, 1000000000 };
  public static final int INTERVAL_MONTH_INDEX = 1;
  public static final int INTERVAL_FRACTION_PART_INDEX = 6;
  public static final long[] precisionLimits = { 1L, 10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L, 1000000000000L };
  public static final int[] precisionFactors = { 100000000, 10000000, 1000000, 100000, 10000, 1000, 100, 10, 1 };
  public static final int[] nanoScaleFactors = { 1000000000, 100000000, 10000000, 1000000, 100000, 10000, 1000, 100, 10, 1 };
  public static final int timezoneSecondsLimit = 50400;
  static final int[] intervalParts = { 101, 102, 103, 104, 105, 106 };
  static final int[][] intervalTypes = { { 101, 107, 0, 0, 0, 0 }, { 0, 102, 0, 0, 0, 0 }, { 0, 0, 103, 108, 109, 110 }, { 0, 0, 0, 104, 111, 112 }, { 0, 0, 0, 0, 105, 113 }, { 0, 0, 0, 0, 0, 106 } };
  static final IntKeyIntValueHashMap intervalIndexMap = new IntKeyIntValueHashMap();
  public static final int TIMEZONE_HOUR = 257;
  public static final int TIMEZONE_MINUTE = 258;
  public static final int DAY_OF_WEEK = 259;
  public static final int DAY_OF_MONTH = 260;
  public static final int DAY_OF_YEAR = 261;
  public static final int WEEK_OF_YEAR = 262;
  public static final int QUARTER = 263;
  public static final int DAY_NAME = 264;
  public static final int MONTH_NAME = 265;
  public static final int SECONDS_MIDNIGHT = 266;
  public static final int ISO_YEAR = 267;
  public final int startIntervalType;
  public final int endIntervalType;
  public final int startPartIndex;
  public final int endPartIndex;
  public static final int defaultTimeFractionPrecision = 0;
  public static final int defaultTimestampFractionPrecision = 6;
  public static final int defaultIntervalPrecision = 2;
  public static final int defaultIntervalFractionPrecision = 6;
  public static final int maxIntervalPrecision = 9;
  public static final int maxFractionPrecision = 9;
  public static final int limitNanoseconds = 1000000000;
  
  protected DTIType(int paramInt1, int paramInt2, long paramLong, int paramInt3, int paramInt4, int paramInt5)
  {
    super(paramInt1, paramInt2, paramLong, paramInt3);
    this.startIntervalType = paramInt4;
    this.endIntervalType = paramInt5;
    this.startPartIndex = intervalIndexMap.get(paramInt4);
    this.endPartIndex = intervalIndexMap.get(paramInt5);
  }
  
  protected DTIType(int paramInt1, int paramInt2, long paramLong, int paramInt3)
  {
    super(paramInt1, paramInt2, paramLong, paramInt3);
    switch (paramInt2)
    {
    case 91: 
      this.startIntervalType = 101;
      this.endIntervalType = 103;
      break;
    case 92: 
    case 94: 
      this.startIntervalType = 104;
      this.endIntervalType = 106;
      break;
    case 93: 
    case 95: 
      this.startIntervalType = 101;
      this.endIntervalType = 106;
      break;
    default: 
      throw Error.runtimeError(201, "DTIType");
    }
    this.startPartIndex = intervalIndexMap.get(this.startIntervalType);
    this.endPartIndex = intervalIndexMap.get(this.endIntervalType);
  }
  
  String intervalSecondToString(long paramLong, int paramInt, boolean paramBoolean)
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    if (paramLong < 0L)
    {
      paramLong = -paramLong;
      localStringBuffer.append('-');
    }
    else if (paramBoolean)
    {
      localStringBuffer.append('+');
    }
    int j;
    for (int i = this.startPartIndex; i <= this.endPartIndex; i++)
    {
      j = yearToSecondFactors[i];
      long l = paramLong / j;
      if (i == this.startPartIndex)
      {
        int k = this.precision == 0L ? 2 : (int)this.precision;
        int m = k - getPrecisionExponent(l);
      }
      else if (l < 10L)
      {
        localStringBuffer.append('0');
      }
      localStringBuffer.append(l);
      paramLong %= j;
      if (i < this.endPartIndex) {
        localStringBuffer.append((char)yearToSecondSeparators[i]);
      }
    }
    if (this.scale != 0) {
      localStringBuffer.append((char)yearToSecondSeparators[5]);
    }
    if (paramInt < 0) {
      paramInt = -paramInt;
    }
    for (i = 0; i < this.scale; i++)
    {
      j = paramInt / precisionFactors[i];
      paramInt -= j * precisionFactors[i];
      localStringBuffer.append(j);
    }
    return localStringBuffer.toString();
  }
  
  public int getStartIntervalType()
  {
    return this.startIntervalType;
  }
  
  public int getEndIntervalType()
  {
    return this.endIntervalType;
  }
  
  public Type getExtractType(int paramInt)
  {
    switch (paramInt)
    {
    case 259: 
    case 260: 
    case 261: 
    case 262: 
    case 263: 
    case 264: 
    case 265: 
      if ((!isDateTimeType()) || (this.startIntervalType != 101)) {
        throw Error.error(5561);
      }
      if ((paramInt == 264) || (paramInt == 265)) {
        return Type.SQL_VARCHAR;
      }
      return Type.SQL_INTEGER;
    case 106: 
      if (paramInt == this.startIntervalType)
      {
        if (this.scale != 0) {
          return new NumberType(3, this.precision + this.scale, this.scale);
        }
      }
      else if ((paramInt == this.endIntervalType) && (this.scale != 0)) {
        return new NumberType(3, 9 + this.scale, this.scale);
      }
    case 101: 
    case 102: 
    case 103: 
    case 104: 
    case 105: 
      if ((paramInt < this.startIntervalType) || (paramInt > this.endIntervalType)) {
        throw Error.error(5561);
      }
      return Type.SQL_INTEGER;
    case 266: 
      if ((!isDateTimeType()) || (this.endIntervalType < 106)) {
        throw Error.error(5561);
      }
      return Type.SQL_INTEGER;
    case 257: 
    case 258: 
      if ((this.typeCode != 95) && (this.typeCode != 94)) {
        throw Error.error(5561);
      }
      return Type.SQL_INTEGER;
    }
    throw Error.runtimeError(201, "DTIType");
  }
  
  public static int normaliseFraction(int paramInt1, int paramInt2)
  {
    return paramInt1 / nanoScaleFactors[paramInt2] * nanoScaleFactors[paramInt2];
  }
  
  static int getPrecisionExponent(long paramLong)
  {
    for (int i = 1; (i < precisionLimits.length) && (paramLong >= precisionLimits[i]); i++) {}
    return i;
  }
  
  public static int getFieldNameTypeForToken(int paramInt)
  {
    switch (paramInt)
    {
    case 323: 
      return 101;
    case 173: 
      return 102;
    case 73: 
      return 103;
    case 127: 
      return 104;
    case 169: 
      return 105;
    case 250: 
      return 106;
    case 283: 
      return 257;
    case 284: 
      return 258;
    case 669: 
      return 264;
    case 702: 
      return 265;
    case 711: 
      return 263;
    case 670: 
      return 260;
    case 671: 
      return 259;
    case 672: 
      return 261;
    case 765: 
      return 262;
    case 724: 
      return 266;
    }
    throw Error.runtimeError(201, "DTIType");
  }
  
  public static String getFieldNameTokenForType(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
      return "YEAR";
    case 102: 
      return "MONTH";
    case 103: 
      return "DAY";
    case 104: 
      return "HOUR";
    case 105: 
      return "MINUTE";
    case 106: 
      return "SECOND";
    case 257: 
      return "TIMEZONE_HOUR";
    case 258: 
      return "TIMEZONE_MINUTE";
    case 264: 
      return "DAY_NAME";
    case 265: 
      return "MONTH_NAME";
    case 263: 
      return "QUARTER";
    case 260: 
      return "DAY_OF_MONTH";
    case 259: 
      return "DAY_OF_WEEK";
    case 261: 
      return "DAY_OF_YEAR";
    case 262: 
      return "WEEK_OF_YEAR";
    case 266: 
      return "SECONDS_SINCE_MIDNIGHT";
    }
    throw Error.runtimeError(201, "DTIType");
  }
  
  public static boolean isValidDatetimeRange(Type paramType1, Type paramType2)
  {
    if (!paramType1.isDateTimeType()) {
      return false;
    }
    if (paramType2.isDateTimeType()) {
      return ((paramType1.typeCode != 92) || (paramType2.typeCode != 91)) && ((paramType1.typeCode != 91) || (paramType2.typeCode != 92));
    }
    if (paramType2.isIntervalType()) {
      return ((DateTimeType)paramType1).canAdd((IntervalType)paramType2);
    }
    return false;
  }
  
  public abstract int getPart(Session paramSession, Object paramObject, int paramInt);
  
  public abstract BigDecimal getSecondPart(Object paramObject);
  
  BigDecimal getSecondPart(long paramLong1, long paramLong2)
  {
    paramLong1 *= precisionLimits[this.scale];
    paramLong1 += paramLong2 / nanoScaleFactors[this.scale];
    return BigDecimal.valueOf(paramLong1, this.scale);
  }
  
  static
  {
    intervalIndexMap.put(101, 0);
    intervalIndexMap.put(102, 1);
    intervalIndexMap.put(103, 2);
    intervalIndexMap.put(104, 3);
    intervalIndexMap.put(105, 4);
    intervalIndexMap.put(106, 5);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.types.DTIType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */