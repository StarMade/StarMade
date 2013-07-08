package org.hsqldb.types;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.hsqldb.Database;
import org.hsqldb.Scanner;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.store.ValuePool;

public final class NumberType
  extends Type
{
  static final int tinyintPrecision = 3;
  static final int smallintPrecision = 5;
  static final int integerPrecision = 10;
  static final int bigintPrecision = 19;
  static final int doublePrecision = 0;
  public static final int defaultNumericPrecision = 128;
  public static final int defaultNumericScale = 32;
  public static final int maxNumericPrecision = 2147483647;
  static final int bigintSquareNumericPrecision = 40;
  public static final int TINYINT_WIDTH = 8;
  public static final int SMALLINT_WIDTH = 16;
  public static final int INTEGER_WIDTH = 32;
  public static final int BIGINT_WIDTH = 64;
  public static final int DOUBLE_WIDTH = 128;
  public static final int DECIMAL_WIDTH = 256;
  public static final Type SQL_NUMERIC_DEFAULT_INT = new NumberType(2, 128L, 0);
  public static final BigDecimal MAX_LONG = BigDecimal.valueOf(9223372036854775807L);
  public static final BigDecimal MIN_LONG = BigDecimal.valueOf(-9223372036854775808L);
  public static final BigDecimal MAX_INT = BigDecimal.valueOf(2147483647L);
  public static final BigDecimal MIN_INT = BigDecimal.valueOf(-2147483648L);
  public static final BigInteger MIN_LONG_BI = MIN_LONG.toBigInteger();
  public static final BigInteger MAX_LONG_BI = MAX_LONG.toBigInteger();
  final int typeWidth;
  
  public NumberType(int paramInt1, long paramLong, int paramInt2)
  {
    super(2, paramInt1, paramLong, paramInt2);
    switch (paramInt1)
    {
    case -6: 
      this.typeWidth = 8;
      break;
    case 5: 
      this.typeWidth = 16;
      break;
    case 4: 
      this.typeWidth = 32;
      break;
    case 25: 
      this.typeWidth = 64;
      break;
    case 6: 
    case 7: 
    case 8: 
      this.typeWidth = 128;
      break;
    case 2: 
    case 3: 
      this.typeWidth = 256;
      break;
    case -5: 
    case -4: 
    case -3: 
    case -2: 
    case -1: 
    case 0: 
    case 1: 
    case 9: 
    case 10: 
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
    default: 
      throw Error.runtimeError(201, "NumberType");
    }
  }
  
  public int getPrecision()
  {
    switch (this.typeCode)
    {
    case -6: 
    case 4: 
    case 5: 
    case 25: 
      return this.typeWidth;
    case 6: 
    case 7: 
    case 8: 
      return 64;
    case 2: 
    case 3: 
      return (int)this.precision;
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int getDecimalPrecision()
  {
    switch (this.typeCode)
    {
    case -6: 
      return 3;
    case 5: 
      return 5;
    case 4: 
      return 10;
    case 25: 
      return 19;
    case 2: 
    case 3: 
    case 6: 
    case 7: 
    case 8: 
      return displaySize() - 1;
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int displaySize()
  {
    switch (this.typeCode)
    {
    case 2: 
    case 3: 
      if (this.scale == 0)
      {
        if (this.precision == 0L) {
          return 646456995;
        }
        return (int)this.precision + 1;
      }
      if (this.precision == this.scale) {
        return (int)this.precision + 3;
      }
      return (int)this.precision + 2;
    case 6: 
    case 7: 
    case 8: 
      return 23;
    case 25: 
      return 20;
    case 4: 
      return 11;
    case 5: 
      return 6;
    case -6: 
      return 4;
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int getJDBCTypeCode()
  {
    return this.typeCode == 25 ? -5 : this.typeCode;
  }
  
  public Class getJDBCClass()
  {
    switch (this.typeCode)
    {
    case -6: 
    case 4: 
    case 5: 
      return Integer.class;
    case 25: 
      return Long.class;
    case 6: 
    case 7: 
    case 8: 
      return Double.class;
    case 2: 
    case 3: 
      return BigDecimal.class;
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public String getJDBCClassName()
  {
    switch (this.typeCode)
    {
    case -6: 
    case 4: 
    case 5: 
      return "java.lang.Integer";
    case 25: 
      return "java.lang.Long";
    case 6: 
    case 7: 
    case 8: 
      return "java.lang.Double";
    case 2: 
    case 3: 
      return "java.math.BigDecimal";
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int getJDBCPrecision()
  {
    return getPrecision();
  }
  
  public String getNameString()
  {
    switch (this.typeCode)
    {
    case -6: 
      return "TINYINT";
    case 5: 
      return "SMALLINT";
    case 4: 
      return "INTEGER";
    case 25: 
      return "BIGINT";
    case 7: 
      return "REAL";
    case 6: 
      return "FLOAT";
    case 8: 
      return "DOUBLE";
    case 2: 
      return "NUMERIC";
    case 3: 
      return "DECIMAL";
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public String getFullNameString()
  {
    switch (this.typeCode)
    {
    case 8: 
      return "DOUBLE PRECISION";
    }
    return getNameString();
  }
  
  public String getDefinition()
  {
    switch (this.typeCode)
    {
    case 2: 
    case 3: 
      StringBuffer localStringBuffer = new StringBuffer(16);
      localStringBuffer.append(getNameString());
      localStringBuffer.append('(');
      localStringBuffer.append(this.precision);
      if (this.scale != 0)
      {
        localStringBuffer.append(',');
        localStringBuffer.append(this.scale);
      }
      localStringBuffer.append(')');
      return localStringBuffer.toString();
    }
    return getNameString();
  }
  
  public long getMaxPrecision()
  {
    switch (this.typeCode)
    {
    case 2: 
    case 3: 
      return 2147483647L;
    }
    return getNumericPrecisionInRadix();
  }
  
  public int getMaxScale()
  {
    switch (this.typeCode)
    {
    case 2: 
    case 3: 
      return 32767;
    }
    return 0;
  }
  
  public boolean acceptsPrecision()
  {
    switch (this.typeCode)
    {
    case 2: 
    case 3: 
    case 6: 
      return true;
    }
    return false;
  }
  
  public boolean acceptsScale()
  {
    switch (this.typeCode)
    {
    case 2: 
    case 3: 
      return true;
    }
    return false;
  }
  
  public int getPrecisionRadix()
  {
    if ((this.typeCode == 3) || (this.typeCode == 2)) {
      return 10;
    }
    return 2;
  }
  
  public boolean isNumberType()
  {
    return true;
  }
  
  public boolean isIntegralType()
  {
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      return false;
    case 2: 
    case 3: 
      return this.scale == 0;
    }
    return true;
  }
  
  public boolean isExactNumberType()
  {
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      return false;
    }
    return true;
  }
  
  public boolean isDecimalType()
  {
    switch (this.typeCode)
    {
    case 2: 
    case 3: 
      return true;
    }
    return false;
  }
  
  public int getNominalWidth()
  {
    return this.typeWidth;
  }
  
  public int precedenceDegree(Type paramType)
  {
    if (paramType.isNumberType())
    {
      int i = ((NumberType)paramType).typeWidth;
      return i - this.typeWidth;
    }
    return -2147483648;
  }
  
  public Type getAggregateType(Type paramType)
  {
    if (paramType == null) {
      return this;
    }
    if (paramType == SQL_ALL_TYPES) {
      return this;
    }
    if (this == paramType) {
      return this;
    }
    if (paramType.isCharacterType()) {
      return paramType.getAggregateType(this);
    }
    switch (paramType.typeCode)
    {
    case -6: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 25: 
      break;
    case -5: 
    case -4: 
    case -3: 
    case -2: 
    case -1: 
    case 0: 
    case 1: 
    case 9: 
    case 10: 
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
    default: 
      throw Error.error(5562);
    }
    if (this.typeWidth == 128) {
      return this;
    }
    if (((NumberType)paramType).typeWidth == 128) {
      return paramType;
    }
    if ((this.typeWidth <= 64) && (((NumberType)paramType).typeWidth <= 64)) {
      return this.typeWidth > ((NumberType)paramType).typeWidth ? this : paramType;
    }
    int i = this.scale > paramType.scale ? this.scale : paramType.scale;
    long l = this.precision - this.scale > paramType.precision - paramType.scale ? this.precision - this.scale : paramType.precision - paramType.scale;
    return getNumberType(3, l + i, i);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    if (paramType.typeCode == 0) {
      paramType = this;
    }
    switch (paramInt)
    {
    case 32: 
    case 35: 
      break;
    case 34: 
      if (paramType.isIntervalType()) {
        return paramType.getCombinedType(paramSession, this, 34);
      }
      break;
    case 33: 
    default: 
      return getAggregateType(paramType);
    }
    if (!paramType.isNumberType()) {
      throw Error.error(5562);
    }
    if ((this.typeWidth == 128) || (((NumberType)paramType).typeWidth == 128)) {
      return Type.SQL_DOUBLE;
    }
    int i;
    if ((paramInt != 35) || (paramSession.database.sqlAvgScale == 0))
    {
      i = this.typeWidth + ((NumberType)paramType).typeWidth;
      if (i <= 32) {
        return Type.SQL_INTEGER;
      }
      if (i <= 64) {
        return Type.SQL_BIGINT;
      }
    }
    long l;
    switch (paramInt)
    {
    case 32: 
      i = this.scale > paramType.scale ? this.scale : paramType.scale;
      l = this.precision - this.scale > paramType.precision - paramType.scale ? this.precision - this.scale : paramType.precision - paramType.scale;
      l += 1L;
      break;
    case 35: 
      l = this.precision - this.scale + paramType.scale;
      i = this.scale > paramType.scale ? this.scale : paramType.scale;
      if (paramSession.database.sqlAvgScale > i) {
        i = paramSession.database.sqlAvgScale;
      }
      break;
    case 34: 
      l = this.precision - this.scale + paramType.precision - paramType.scale;
      i = this.scale + paramType.scale;
      break;
    case 33: 
    default: 
      throw Error.runtimeError(201, "NumberType");
    }
    return getNumberType(3, i + l, i);
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
    int k;
    switch (this.typeCode)
    {
    case -6: 
    case 4: 
    case 5: 
      int j;
      if ((paramObject2 instanceof Integer))
      {
        int i = ((Number)paramObject1).intValue();
        j = ((Number)paramObject2).intValue();
        return j > i ? -1 : i > j ? 1 : 0;
      }
      if ((paramObject2 instanceof Double))
      {
        double d1 = ((Number)paramObject1).doubleValue();
        double d3 = ((Number)paramObject2).doubleValue();
        return d3 > d1 ? -1 : d1 > d3 ? 1 : 0;
      }
      if ((paramObject2 instanceof BigDecimal))
      {
        BigDecimal localBigDecimal1 = convertToDecimal(paramObject1);
        j = localBigDecimal1.compareTo((BigDecimal)paramObject2);
        return j < 0 ? -1 : j == 0 ? 0 : 1;
      }
    case 25: 
      if ((paramObject2 instanceof Long))
      {
        long l1 = ((Number)paramObject1).longValue();
        long l2 = ((Number)paramObject2).longValue();
        return l2 > l1 ? -1 : l1 > l2 ? 1 : 0;
      }
      BigDecimal localBigDecimal2;
      if ((paramObject2 instanceof Double))
      {
        localBigDecimal2 = BigDecimal.valueOf(((Number)paramObject1).longValue());
        BigDecimal localBigDecimal4 = new BigDecimal(((Double)paramObject2).doubleValue());
        int m = localBigDecimal2.compareTo(localBigDecimal4);
        return m < 0 ? -1 : m == 0 ? 0 : 1;
      }
      if ((paramObject2 instanceof BigDecimal))
      {
        localBigDecimal2 = BigDecimal.valueOf(((Number)paramObject1).longValue());
        k = localBigDecimal2.compareTo((BigDecimal)paramObject2);
        return k < 0 ? -1 : k == 0 ? 0 : 1;
      }
    case 6: 
    case 7: 
    case 8: 
      double d2 = ((Number)paramObject1).doubleValue();
      double d4 = ((Number)paramObject2).doubleValue();
      if (Double.isNaN(d2)) {
        return Double.isNaN(d4) ? 0 : -1;
      }
      if (Double.isNaN(d4)) {
        return 1;
      }
      return d4 > d2 ? -1 : d2 > d4 ? 1 : 0;
    case 2: 
    case 3: 
      BigDecimal localBigDecimal3 = convertToDecimal(paramObject2);
      k = ((BigDecimal)paramObject1).compareTo(localBigDecimal3);
      return k < 0 ? -1 : k == 0 ? 0 : 1;
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    switch (this.typeCode)
    {
    case -6: 
    case 4: 
    case 5: 
    case 25: 
      return paramObject;
    case 6: 
    case 7: 
    case 8: 
      return paramObject;
    case 2: 
    case 3: 
      BigDecimal localBigDecimal = (BigDecimal)paramObject;
      if (this.scale != localBigDecimal.scale()) {
        localBigDecimal = localBigDecimal.setScale(this.scale, 5);
      }
      int i = JavaSystem.precision(localBigDecimal);
      if (i > this.precision) {
        throw Error.error(3403);
      }
      return localBigDecimal;
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null) {
      return paramObject;
    }
    if (paramType.typeCode == this.typeCode)
    {
      switch (this.typeCode)
      {
      case 2: 
      case 3: 
        BigDecimal localBigDecimal1 = (BigDecimal)paramObject;
        if (this.scale != localBigDecimal1.scale()) {
          localBigDecimal1 = localBigDecimal1.setScale(this.scale, 5);
        }
        if (JavaSystem.precision(localBigDecimal1) > this.precision) {
          throw Error.error(3403);
        }
        return localBigDecimal1;
      }
      return paramObject;
    }
    if (paramType.isIntervalType())
    {
      int i = ((IntervalType)paramType).endIntervalType;
      switch (i)
      {
      case 101: 
      case 102: 
      case 103: 
      case 104: 
      case 105: 
        Long localLong = ValuePool.getLong(((IntervalType)paramType).convertToLong(paramObject));
        return convertToType(paramSessionInterface, localLong, Type.SQL_BIGINT);
      case 106: 
        long l1 = ((IntervalSecondData)paramObject).units;
        long l2 = ((IntervalSecondData)paramObject).nanos;
        BigDecimal localBigDecimal3 = ((DTIType)paramType).getSecondPart(l1, l2);
        return localBigDecimal3;
      }
    }
    switch (paramType.typeCode)
    {
    case 40: 
      paramObject = ((ClobData)paramObject).getSubString(paramSessionInterface, 0L, (int)((ClobData)paramObject).length(paramSessionInterface));
    case 1: 
    case 12: 
    case 100: 
      paramObject = paramSessionInterface.getScanner().convertToNumber((String)paramObject, this);
      paramObject = convertToDefaultType(paramSessionInterface, paramObject);
      return convertToTypeLimits(paramSessionInterface, paramObject);
    case -6: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 25: 
      break;
    case 14: 
    case 15: 
      if (paramType.precision == 1L) {
        if (((BinaryData)paramObject).getBytes()[0] == 0) {
          paramObject = ValuePool.INTEGER_0;
        } else {
          paramObject = ValuePool.INTEGER_1;
        }
      }
      break;
    }
    throw Error.error(5561);
    switch (this.typeCode)
    {
    case -6: 
    case 4: 
    case 5: 
      return convertToInt(paramSessionInterface, paramObject, this.typeCode);
    case 25: 
      return convertToLong(paramSessionInterface, paramObject);
    case 6: 
    case 7: 
    case 8: 
      return convertToDouble(paramObject);
    case 2: 
    case 3: 
      BigDecimal localBigDecimal2 = null;
      if ((this.scale == 0) && ((paramObject instanceof Double)))
      {
        double d = ((Number)paramObject).doubleValue();
        if (((paramSessionInterface instanceof Session)) && (!((Session)paramSessionInterface).database.sqlConvertTruncate)) {
          d = Math.rint(d);
        }
        if ((Double.isInfinite(d)) || (Double.isNaN(d))) {
          throw Error.error(3403);
        }
        localBigDecimal2 = BigDecimal.valueOf(d);
      }
      if (localBigDecimal2 == null) {
        localBigDecimal2 = convertToDecimal(paramObject);
      }
      return convertToTypeLimits(paramSessionInterface, localBigDecimal2);
    }
    throw Error.error(5561);
  }
  
  public Object convertToTypeJDBC(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null) {
      return paramObject;
    }
    if (paramType.isLobType()) {
      throw Error.error(5561);
    }
    switch (paramType.typeCode)
    {
    case 16: 
      paramObject = ((Boolean)paramObject).booleanValue() ? ValuePool.INTEGER_1 : ValuePool.INTEGER_0;
      paramType = Type.SQL_INTEGER;
    }
    return convertToType(paramSessionInterface, paramObject, paramType);
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return paramObject;
    }
    Object localObject;
    if ((paramObject instanceof Number))
    {
      if ((paramObject instanceof BigInteger)) {
        paramObject = new BigDecimal((BigInteger)paramObject);
      } else if ((paramObject instanceof Float)) {
        paramObject = new Double(((Float)paramObject).doubleValue());
      } else if ((paramObject instanceof Byte)) {
        paramObject = ValuePool.getInt(((Byte)paramObject).intValue());
      } else if ((paramObject instanceof Short)) {
        paramObject = ValuePool.getInt(((Short)paramObject).intValue());
      }
      if ((paramObject instanceof Integer)) {
        localObject = Type.SQL_INTEGER;
      } else if ((paramObject instanceof Long)) {
        localObject = Type.SQL_BIGINT;
      } else if ((paramObject instanceof Double)) {
        localObject = Type.SQL_DOUBLE;
      } else if ((paramObject instanceof BigDecimal)) {
        localObject = Type.SQL_DECIMAL_DEFAULT;
      } else {
        throw Error.error(5561);
      }
      switch (this.typeCode)
      {
      case -6: 
      case 4: 
      case 5: 
        return convertToInt(paramSessionInterface, paramObject, 4);
      case 25: 
        return convertToLong(paramSessionInterface, paramObject);
      case 6: 
      case 7: 
      case 8: 
        return convertToDouble(paramObject);
      case 2: 
      case 3: 
        paramObject = convertToDecimal(paramObject);
        BigDecimal localBigDecimal = (BigDecimal)paramObject;
        if (this.scale != localBigDecimal.scale()) {
          localBigDecimal = localBigDecimal.setScale(this.scale, 5);
        }
        return localBigDecimal;
      }
      throw Error.error(5561);
    }
    if ((paramObject instanceof String)) {
      localObject = Type.SQL_VARCHAR;
    } else {
      throw Error.error(5561);
    }
    return convertToType(paramSessionInterface, paramObject, (Type)localObject);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject)
  {
    return convertToDefaultType(paramSessionInterface, paramObject);
  }
  
  static Integer convertToInt(SessionInterface paramSessionInterface, Object paramObject, int paramInt)
  {
    int i;
    if ((paramObject instanceof Integer))
    {
      if (paramInt == 4) {
        return (Integer)paramObject;
      }
      i = ((Integer)paramObject).intValue();
    }
    else if ((paramObject instanceof Long))
    {
      long l = ((Long)paramObject).longValue();
      if ((2147483647L < l) || (l < -2147483648L)) {
        throw Error.error(3403);
      }
      i = (int)l;
    }
    else if ((paramObject instanceof BigDecimal))
    {
      BigDecimal localBigDecimal = (BigDecimal)paramObject;
      if ((localBigDecimal.compareTo(MAX_INT) > 0) || (localBigDecimal.compareTo(MIN_INT) < 0)) {
        throw Error.error(3403);
      }
      i = localBigDecimal.intValue();
    }
    else if (((paramObject instanceof Double)) || ((paramObject instanceof Float)))
    {
      double d = ((Number)paramObject).doubleValue();
      if (((paramSessionInterface instanceof Session)) && (!((Session)paramSessionInterface).database.sqlConvertTruncate)) {
        d = Math.rint(d);
      }
      if ((Double.isInfinite(d)) || (Double.isNaN(d)) || (d >= 2147483648.0D) || (d <= -2147483649.0D)) {
        throw Error.error(3403);
      }
      i = (int)d;
    }
    else
    {
      throw Error.error(5561);
    }
    if (paramInt == -6)
    {
      if ((127 < i) || (i < -128)) {
        throw Error.error(3403);
      }
    }
    else if ((paramInt == 5) && ((32767 < i) || (i < -32768))) {
      throw Error.error(3403);
    }
    return Integer.valueOf(i);
  }
  
  static Long convertToLong(SessionInterface paramSessionInterface, Object paramObject)
  {
    if ((paramObject instanceof Integer)) {
      return ValuePool.getLong(((Integer)paramObject).intValue());
    }
    if ((paramObject instanceof Long)) {
      return (Long)paramObject;
    }
    if ((paramObject instanceof BigDecimal))
    {
      BigDecimal localBigDecimal = (BigDecimal)paramObject;
      if ((localBigDecimal.compareTo(MAX_LONG) > 0) || (localBigDecimal.compareTo(MIN_LONG) < 0)) {
        throw Error.error(3403);
      }
      return ValuePool.getLong(localBigDecimal.longValue());
    }
    if (((paramObject instanceof Double)) || ((paramObject instanceof Float)))
    {
      double d = ((Number)paramObject).doubleValue();
      if (((paramSessionInterface instanceof Session)) && (!((Session)paramSessionInterface).database.sqlConvertTruncate)) {
        d = Math.rint(d);
      }
      if ((Double.isInfinite(d)) || (Double.isNaN(d)) || (d >= 9.223372036854776E+018D) || (d <= -9.223372036854776E+018D)) {
        throw Error.error(3403);
      }
      return ValuePool.getLong(d);
    }
    throw Error.error(5561);
  }
  
  private static Double convertToDouble(Object paramObject)
  {
    if ((paramObject instanceof Double)) {
      return (Double)paramObject;
    }
    double d;
    if ((paramObject instanceof BigDecimal))
    {
      BigDecimal localBigDecimal1 = (BigDecimal)paramObject;
      d = localBigDecimal1.doubleValue();
      int i = localBigDecimal1.signum();
      BigDecimal localBigDecimal2 = new BigDecimal(d + i);
      if (localBigDecimal2.compareTo(localBigDecimal1) != i) {
        throw Error.error(3403);
      }
    }
    else
    {
      d = ((Number)paramObject).doubleValue();
    }
    return ValuePool.getDouble(Double.doubleToLongBits(d));
  }
  
  public static double toDouble(Object paramObject)
  {
    if ((paramObject instanceof Double)) {
      return ((Double)paramObject).doubleValue();
    }
    double d;
    if ((paramObject instanceof BigDecimal))
    {
      BigDecimal localBigDecimal1 = (BigDecimal)paramObject;
      d = localBigDecimal1.doubleValue();
      int i = localBigDecimal1.signum();
      BigDecimal localBigDecimal2 = new BigDecimal(d + i);
      if (localBigDecimal2.compareTo(localBigDecimal1) != i) {
        throw Error.error(3403);
      }
    }
    else if ((paramObject instanceof Number))
    {
      d = ((Number)paramObject).doubleValue();
    }
    else
    {
      throw Error.error(3471);
    }
    return d;
  }
  
  private static BigDecimal convertToDecimal(Object paramObject)
  {
    if ((paramObject instanceof BigDecimal)) {
      return (BigDecimal)paramObject;
    }
    if (((paramObject instanceof Integer)) || ((paramObject instanceof Long))) {
      return BigDecimal.valueOf(((Number)paramObject).longValue());
    }
    if ((paramObject instanceof Double))
    {
      double d = ((Number)paramObject).doubleValue();
      if ((Double.isInfinite(d)) || (Double.isNaN(d))) {
        throw Error.error(3403);
      }
      return BigDecimal.valueOf(d);
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public String convertToString(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    switch (this.typeCode)
    {
    case -6: 
    case 4: 
    case 5: 
    case 25: 
      return paramObject.toString();
    case 7: 
    case 8: 
      double d = ((Double)paramObject).doubleValue();
      if (d == (-1.0D / 0.0D)) {
        return "-1E0/0";
      }
      if (d == (1.0D / 0.0D)) {
        return "1E0/0";
      }
      if (Double.isNaN(d)) {
        return "0E0/0E0";
      }
      String str = Double.toString(d);
      if (str.indexOf(69) < 0) {
        str = str.concat("E0");
      }
      return str;
    case 2: 
    case 3: 
      return JavaSystem.toString((BigDecimal)paramObject);
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null) {
      return "NULL";
    }
    return convertToString(paramObject);
  }
  
  public boolean canConvertFrom(Type paramType)
  {
    if (paramType.typeCode == 0) {
      return true;
    }
    if (paramType.isNumberType()) {
      return true;
    }
    if (paramType.isIntervalType()) {
      return true;
    }
    if (paramType.isCharacterType()) {
      return true;
    }
    return (paramType.isBitType()) && (paramType.precision == 1L);
  }
  
  public int canMoveFrom(Type paramType)
  {
    if (paramType == this) {
      return 0;
    }
    switch (this.typeCode)
    {
    case -6: 
      if ((paramType.typeCode == 5) || (paramType.typeCode == 4)) {
        return 1;
      }
      break;
    case 5: 
      if (paramType.typeCode == -6) {
        return 0;
      }
      if (paramType.typeCode == 4) {
        return 1;
      }
      break;
    case 4: 
      if ((paramType.typeCode == 5) || (paramType.typeCode == -6)) {
        return 0;
      }
      break;
    case 25: 
      break;
    case 2: 
    case 3: 
      if (((paramType.typeCode == 3) || (paramType.typeCode == 2)) && (this.scale == paramType.scale))
      {
        if (this.precision >= paramType.precision) {
          return 0;
        }
        return 1;
      }
      break;
    case 6: 
    case 7: 
    case 8: 
      if ((paramType.typeCode == 7) || (paramType.typeCode == 6) || (paramType.typeCode == 8)) {
        return 0;
      }
      break;
    }
    return -1;
  }
  
  public int compareToTypeRange(Object paramObject)
  {
    if (!(paramObject instanceof Number)) {
      return 0;
    }
    if (((paramObject instanceof Integer)) || ((paramObject instanceof Long)))
    {
      long l = ((Number)paramObject).longValue();
      int i;
      int j;
      switch (this.typeCode)
      {
      case -6: 
        i = -128;
        j = 127;
        break;
      case 5: 
        i = -32768;
        j = 32767;
        break;
      case 4: 
        i = -2147483648;
        j = 2147483647;
        break;
      case 25: 
        return 0;
      case 2: 
      case 3: 
        if (this.precision - this.scale > 18L) {
          return 0;
        }
        if ((this.precision - this.scale > 9L) && ((paramObject instanceof Integer))) {
          return 0;
        }
        BigDecimal localBigDecimal = convertToDecimal(paramObject);
        int k = localBigDecimal.scale();
        int m = JavaSystem.precision(localBigDecimal);
        if (k < 0)
        {
          m -= k;
          k = 0;
        }
        return this.precision - this.scale >= m - k ? 0 : localBigDecimal.signum();
      default: 
        return 0;
      }
      if (j < l) {
        return 1;
      }
      if (l < i) {
        return -1;
      }
      return 0;
    }
    return 0;
  }
  
  public Object add(Object paramObject1, Object paramObject2, Type paramType)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      double d1 = ((Number)paramObject1).doubleValue();
      double d2 = ((Number)paramObject2).doubleValue();
      return ValuePool.getDouble(Double.doubleToLongBits(d1 + d2));
    case 2: 
    case 3: 
      paramObject1 = convertToDefaultType(null, paramObject1);
      paramObject2 = convertToDefaultType(null, paramObject2);
      BigDecimal localBigDecimal1 = (BigDecimal)paramObject1;
      BigDecimal localBigDecimal2 = (BigDecimal)paramObject2;
      localBigDecimal1 = localBigDecimal1.add(localBigDecimal2);
      return convertToTypeLimits(null, localBigDecimal1);
    case -6: 
    case 4: 
    case 5: 
      int i = ((Number)paramObject1).intValue();
      int j = ((Number)paramObject2).intValue();
      return ValuePool.getInt(i + j);
    case 25: 
      long l1 = ((Number)paramObject1).longValue();
      long l2 = ((Number)paramObject2).longValue();
      return ValuePool.getLong(l1 + l2);
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object subtract(Object paramObject1, Object paramObject2, Type paramType)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      double d1 = ((Number)paramObject1).doubleValue();
      double d2 = ((Number)paramObject2).doubleValue();
      return ValuePool.getDouble(Double.doubleToLongBits(d1 - d2));
    case 2: 
    case 3: 
      paramObject1 = convertToDefaultType(null, paramObject1);
      paramObject2 = convertToDefaultType(null, paramObject2);
      BigDecimal localBigDecimal1 = (BigDecimal)paramObject1;
      BigDecimal localBigDecimal2 = (BigDecimal)paramObject2;
      localBigDecimal1 = localBigDecimal1.subtract(localBigDecimal2);
      return convertToTypeLimits(null, localBigDecimal1);
    case -6: 
    case 4: 
    case 5: 
      int i = ((Number)paramObject1).intValue();
      int j = ((Number)paramObject2).intValue();
      return ValuePool.getInt(i - j);
    case 25: 
      long l1 = ((Number)paramObject1).longValue();
      long l2 = ((Number)paramObject2).longValue();
      return ValuePool.getLong(l1 - l2);
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object multiply(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      double d1 = ((Number)paramObject1).doubleValue();
      double d2 = ((Number)paramObject2).doubleValue();
      return ValuePool.getDouble(Double.doubleToLongBits(d1 * d2));
    case 2: 
    case 3: 
      if (!(paramObject1 instanceof BigDecimal)) {
        paramObject1 = convertToDefaultType(null, paramObject1);
      }
      if (!(paramObject2 instanceof BigDecimal)) {
        paramObject2 = convertToDefaultType(null, paramObject2);
      }
      BigDecimal localBigDecimal1 = (BigDecimal)paramObject1;
      BigDecimal localBigDecimal2 = (BigDecimal)paramObject2;
      BigDecimal localBigDecimal3 = localBigDecimal1.multiply(localBigDecimal2);
      return convertToTypeLimits(null, localBigDecimal3);
    case -6: 
    case 4: 
    case 5: 
      int i = ((Number)paramObject1).intValue();
      int j = ((Number)paramObject2).intValue();
      return ValuePool.getInt(i * j);
    case 25: 
      long l1 = ((Number)paramObject1).longValue();
      long l2 = ((Number)paramObject2).longValue();
      return ValuePool.getLong(l1 * l2);
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object divide(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      double d1 = ((Number)paramObject1).doubleValue();
      double d2 = ((Number)paramObject2).doubleValue();
      if ((d2 == 0.0D) && ((paramSession == null) || (paramSession.database.sqlDoubleNaN))) {
        throw Error.error(3432);
      }
      return ValuePool.getDouble(Double.doubleToLongBits(d1 / d2));
    case 2: 
    case 3: 
      if (!(paramObject1 instanceof BigDecimal)) {
        paramObject1 = convertToDefaultType(null, paramObject1);
      }
      if (!(paramObject2 instanceof BigDecimal)) {
        paramObject2 = convertToDefaultType(null, paramObject2);
      }
      BigDecimal localBigDecimal1 = (BigDecimal)paramObject1;
      BigDecimal localBigDecimal2 = (BigDecimal)paramObject2;
      if (localBigDecimal2.signum() == 0) {
        throw Error.error(3432);
      }
      BigDecimal localBigDecimal3 = localBigDecimal1.divide(localBigDecimal2, this.scale, 1);
      return convertToTypeLimits(null, localBigDecimal3);
    case -6: 
    case 4: 
    case 5: 
      int i = ((Number)paramObject1).intValue();
      int j = ((Number)paramObject2).intValue();
      if (j == 0) {
        throw Error.error(3432);
      }
      return ValuePool.getInt(i / j);
    case 25: 
      long l1 = ((Number)paramObject1).longValue();
      long l2 = ((Number)paramObject2).longValue();
      if (l2 == 0L) {
        throw Error.error(3432);
      }
      return ValuePool.getLong(l1 / l2);
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Object modulo(Object paramObject1, Object paramObject2, Type paramType)
  {
    if (!paramType.isNumberType()) {
      throw Error.error(5561);
    }
    Object localObject = divide(null, paramObject1, paramObject2);
    localObject = multiply(localObject, paramObject2);
    localObject = convertToDefaultType(null, localObject);
    localObject = subtract(paramObject1, localObject, this);
    return convertToTypeLimits(null, localObject);
  }
  
  public Object absolute(Object paramObject)
  {
    return isNegative(paramObject) ? negate(paramObject) : paramObject;
  }
  
  public Object negate(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    int i;
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      double d = -((Number)paramObject).doubleValue();
      return ValuePool.getDouble(Double.doubleToLongBits(d));
    case 2: 
    case 3: 
      return ((BigDecimal)paramObject).negate();
    case -6: 
      i = ((Number)paramObject).intValue();
      if (i == -128) {
        throw Error.error(3403);
      }
      return ValuePool.getInt(-i);
    case 5: 
      i = ((Number)paramObject).intValue();
      if (i == -32768) {
        throw Error.error(3403);
      }
      return ValuePool.getInt(-i);
    case 4: 
      i = ((Number)paramObject).intValue();
      if (i == -2147483648) {
        throw Error.error(3403);
      }
      return ValuePool.getInt(-i);
    case 25: 
      long l = ((Number)paramObject).longValue();
      if (l == -9223372036854775808L) {
        throw Error.error(3403);
      }
      return ValuePool.getLong(-l);
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int getNumericPrecisionInRadix()
  {
    switch (this.typeCode)
    {
    case -6: 
      return 8;
    case 5: 
      return 16;
    case 4: 
      return 32;
    case 25: 
      return 64;
    case 6: 
    case 7: 
    case 8: 
      return 64;
    case 2: 
    case 3: 
      return (int)this.precision;
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public Type getIntegralType()
  {
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      return SQL_NUMERIC_DEFAULT_INT;
    case 2: 
    case 3: 
      return this.scale == 0 ? this : new NumberType(this.typeCode, this.precision, 0);
    }
    return this;
  }
  
  public static boolean isZero(Object paramObject)
  {
    if ((paramObject instanceof BigDecimal)) {
      return ((BigDecimal)paramObject).signum() == 0;
    }
    if ((paramObject instanceof Double)) {
      return (((Double)paramObject).doubleValue() == 0.0D) || (((Double)paramObject).isNaN());
    }
    return ((Number)paramObject).longValue() == 0L;
  }
  
  public boolean isNegative(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      double d = ((Number)paramObject).doubleValue();
      return d < 0.0D;
    case 2: 
    case 3: 
      return ((BigDecimal)paramObject).signum() < 0;
    case -6: 
    case 4: 
    case 5: 
      return ((Number)paramObject).intValue() < 0;
    case 25: 
      return ((Number)paramObject).longValue() < 0L;
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public int compareToZero(Object paramObject)
  {
    if (paramObject == null) {
      return 0;
    }
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      double d = ((Number)paramObject).doubleValue();
      return d < 0.0D ? -1 : d == 0.0D ? 0 : 1;
    case 2: 
    case 3: 
      return ((BigDecimal)paramObject).signum();
    case -6: 
    case 4: 
    case 5: 
      int i = ((Number)paramObject).intValue();
      return i < 0 ? -1 : i == 0 ? 0 : 1;
    case 25: 
      long l = ((Number)paramObject).longValue();
      return l < 0L ? -1 : l == 0L ? 0 : 1;
    }
    throw Error.runtimeError(201, "NumberType");
  }
  
  public static long scaledDecimal(Object paramObject, int paramInt)
  {
    if (paramObject == null) {
      return 0L;
    }
    if (paramInt == 0) {
      return 0L;
    }
    BigDecimal localBigDecimal = (BigDecimal)paramObject;
    if (localBigDecimal.scale() == 0) {
      return 0L;
    }
    localBigDecimal = localBigDecimal.setScale(0, 3);
    localBigDecimal = ((BigDecimal)paramObject).subtract(localBigDecimal);
    return localBigDecimal.movePointRight(paramInt).longValue();
  }
  
  public static boolean isInLongLimits(BigDecimal paramBigDecimal)
  {
    return (MIN_LONG.compareTo(paramBigDecimal) <= 0) && (MAX_LONG.compareTo(paramBigDecimal) >= 0);
  }
  
  public static boolean isInLongLimits(BigInteger paramBigInteger)
  {
    return (MAX_LONG_BI.compareTo(paramBigInteger) >= 0) && (MIN_LONG_BI.compareTo(paramBigInteger) <= 0);
  }
  
  public Object ceiling(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      double d = Math.ceil(((Double)paramObject).doubleValue());
      if (Double.isInfinite(d)) {
        throw Error.error(3403);
      }
      return ValuePool.getDouble(Double.doubleToLongBits(d));
    case 2: 
    case 3: 
      BigDecimal localBigDecimal = ((BigDecimal)paramObject).setScale(0, 2);
      return localBigDecimal;
    }
    return paramObject;
  }
  
  public Object floor(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    switch (this.typeCode)
    {
    case 6: 
    case 7: 
    case 8: 
      double d = Math.floor(((Double)paramObject).doubleValue());
      if (Double.isInfinite(d)) {
        throw Error.error(3403);
      }
      return ValuePool.getDouble(Double.doubleToLongBits(d));
    case 2: 
    case 3: 
      BigDecimal localBigDecimal = ((BigDecimal)paramObject).setScale(0, 3);
      return localBigDecimal;
    }
    return paramObject;
  }
  
  public Object truncate(Object paramObject, int paramInt)
  {
    if (paramObject == null) {
      return null;
    }
    BigDecimal localBigDecimal = convertToDecimal(paramObject);
    localBigDecimal = localBigDecimal.setScale(paramInt, 1);
    if ((this.typeCode == 3) || (this.typeCode == 2)) {
      localBigDecimal = localBigDecimal.setScale(this.scale, 1);
    }
    paramObject = convertToDefaultType(null, localBigDecimal);
    return convertToTypeLimits(null, paramObject);
  }
  
  public Object round(Object paramObject, int paramInt)
  {
    if (paramObject == null) {
      return null;
    }
    BigDecimal localBigDecimal = convertToDecimal(paramObject);
    switch (this.typeCode)
    {
    case 8: 
      localBigDecimal = localBigDecimal.setScale(paramInt, 6);
      break;
    case 2: 
    case 3: 
    default: 
      localBigDecimal = localBigDecimal.setScale(paramInt, 4);
      localBigDecimal = localBigDecimal.setScale(this.scale, 1);
    }
    paramObject = convertToDefaultType(null, localBigDecimal);
    return convertToTypeLimits(null, paramObject);
  }
  
  public static NumberType getNumberType(int paramInt1, long paramLong, int paramInt2)
  {
    switch (paramInt1)
    {
    case 4: 
      return SQL_INTEGER;
    case 5: 
      return SQL_SMALLINT;
    case 25: 
      return SQL_BIGINT;
    case -6: 
      return TINYINT;
    case 7: 
    case 8: 
      return SQL_DOUBLE;
    case 2: 
    case 3: 
      return new NumberType(paramInt1, paramLong, paramInt2);
    }
    throw Error.runtimeError(201, "NumberType");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.NumberType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */