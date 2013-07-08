package org.hsqldb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.IntKeyIntValueHashMap;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.persist.Crypto;
import org.hsqldb.store.BitMap;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BinaryType;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.LobData;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class FunctionCustom
  extends FunctionSQL
{
  public static final String[] openGroupNumericFunctions = { "ABS", "ACOS", "ASIN", "ATAN", "ATAN2", "BITAND", "BITOR", "BITXOR", "CEILING", "COS", "COT", "DEGREES", "EXP", "FLOOR", "LOG", "LOG10", "MOD", "PI", "POWER", "RADIANS", "RAND", "ROUND", "ROUNDMAGIC", "SIGN", "SIN", "SQRT", "TAN", "TRUNCATE" };
  public static final String[] openGroupStringFunctions = { "ASCII", "CHAR", "CONCAT", "DIFFERENCE", "HEXTORAW", "INSERT", "LCASE", "LEFT", "LENGTH", "LOCATE", "LTRIM", "RAWTOHEX", "REPEAT", "REPLACE", "RIGHT", "RTRIM", "SOUNDEX", "SPACE", "SUBSTR", "UCASE" };
  public static final String[] openGroupDateTimeFunctions = { "CURDATE", "CURTIME", "DATEDIFF", "DAYNAME", "DAYOFMONTH", "DAYOFWEEK", "DAYOFYEAR", "HOUR", "MINUTE", "MONTH", "MONTHNAME", "NOW", "QUARTER", "SECOND", "SECONDS_SINCE_MIDNIGHT", "TIMESTAMPADD", "TIMESTAMPDIFF", "TO_CHAR", "WEEK", "YEAR" };
  public static final String[] openGroupSystemFunctions = { "DATABASE", "IFNULL", "USER" };
  private static final int FUNC_ACOS = 71;
  private static final int FUNC_ACTION_ID = 72;
  private static final int FUNC_ASCII = 73;
  private static final int FUNC_ASIN = 74;
  private static final int FUNC_ATAN = 75;
  private static final int FUNC_ATAN2 = 76;
  private static final int FUNC_BITAND = 77;
  private static final int FUNC_BITANDNOT = 78;
  private static final int FUNC_BITNOT = 79;
  private static final int FUNC_BITOR = 80;
  private static final int FUNC_BITXOR = 81;
  private static final int FUNC_CHAR = 82;
  private static final int FUNC_CONCAT = 83;
  private static final int FUNC_COS = 84;
  private static final int FUNC_COT = 85;
  private static final int FUNC_CRYPT_KEY = 86;
  private static final int FUNC_DATABASE = 87;
  private static final int FUNC_DATABASE_ISOLATION_LEVEL = 88;
  private static final int FUNC_DATABASE_NAME = 89;
  private static final int FUNC_DATABASE_TIMEZONE = 90;
  private static final int FUNC_DATABASE_VERSION = 91;
  private static final int FUNC_DATE_ADD = 92;
  private static final int FUNC_DATE_SUB = 93;
  private static final int FUNC_DATEADD = 94;
  private static final int FUNC_DATEDIFF = 95;
  private static final int FUNC_DAYS = 96;
  private static final int FUNC_DEGREES = 97;
  private static final int FUNC_DIAGNOSTICS = 98;
  private static final int FUNC_DIFFERENCE = 99;
  private static final int FUNC_HEXTORAW = 100;
  private static final int FUNC_IDENTITY = 101;
  private static final int FUNC_ISAUTOCOMMIT = 102;
  private static final int FUNC_ISOLATION_LEVEL = 103;
  private static final int FUNC_ISREADONLYDATABASE = 104;
  private static final int FUNC_ISREADONLYDATABASEFILES = 105;
  private static final int FUNC_ISREADONLYSESSION = 106;
  private static final int FUNC_LEFT = 107;
  private static final int FUNC_LOAD_FILE = 108;
  private static final int FUNC_LOB_ID = 109;
  private static final int FUNC_LOCATE = 110;
  private static final int FUNC_LOG10 = 111;
  private static final int FUNC_LPAD = 112;
  private static final int FUNC_LTRIM = 113;
  private static final int FUNC_PI = 114;
  private static final int FUNC_POSITION_ARRAY = 115;
  private static final int FUNC_RADIANS = 116;
  private static final int FUNC_RAND = 117;
  private static final int FUNC_RAWTOHEX = 118;
  private static final int FUNC_REGEXP_MATCHES = 119;
  private static final int FUNC_REGEXP_SUBSTRING = 120;
  private static final int FUNC_REGEXP_SUBSTRING_ARRAY = 121;
  private static final int FUNC_REPEAT = 122;
  private static final int FUNC_REPLACE = 123;
  private static final int FUNC_REVERSE = 124;
  private static final int FUNC_RIGHT = 125;
  private static final int FUNC_ROUND = 126;
  private static final int FUNC_ROUNDMAGIC = 127;
  private static final int FUNC_RPAD = 128;
  private static final int FUNC_RTRIM = 129;
  private static final int FUNC_SECONDS_MIDNIGHT = 130;
  private static final int FUNC_SEQUENCE_ARRAY = 131;
  private static final int FUNC_SESSION_ID = 132;
  private static final int FUNC_SESSION_ISOLATION_LEVEL = 133;
  private static final int FUNC_SESSION_TIMEZONE = 134;
  private static final int FUNC_SIGN = 135;
  private static final int FUNC_SIN = 136;
  private static final int FUNC_SOUNDEX = 137;
  private static final int FUNC_SORT_ARRAY = 138;
  private static final int FUNC_SPACE = 139;
  private static final int FUNC_SUBSTR = 140;
  private static final int FUNC_SYSDATE = 141;
  private static final int FUNC_TAN = 142;
  private static final int FUNC_TIMESTAMP = 143;
  private static final int FUNC_TIMESTAMPADD = 144;
  private static final int FUNC_TIMESTAMPDIFF = 145;
  private static final int FUNC_TIMEZONE = 146;
  private static final int FUNC_TO_CHAR = 147;
  private static final int FUNC_TO_DATE = 148;
  private static final int FUNC_TO_NUMBER = 149;
  private static final int FUNC_TO_TIMESTAMP = 150;
  private static final int FUNC_TRANSACTION_CONTROL = 151;
  private static final int FUNC_TRANSACTION_ID = 152;
  private static final int FUNC_TRANSACTION_SIZE = 153;
  private static final int FUNC_TRUNC = 154;
  private static final int FUNC_TRUNCATE = 155;
  private static final int FUNC_UUID = 156;
  private static final int FUNC_UNIX_TIMESTAMP = 157;
  static final IntKeyIntValueHashMap customRegularFuncMap = new IntKeyIntValueHashMap();
  static final IntKeyIntValueHashMap customValueFuncMap;
  private int extractSpec;
  private Pattern pattern;
  
  public static FunctionSQL newCustomFunction(String paramString, int paramInt)
  {
    int i = customRegularFuncMap.get(paramInt, -1);
    if (i == -1) {
      i = customValueFuncMap.get(paramInt, -1);
    }
    if (i == -1) {
      return null;
    }
    switch (paramInt)
    {
    case 431: 
    case 650: 
    case 692: 
    case 697: 
    case 708: 
    case 761: 
      return new FunctionSQL(i);
    case 659: 
    case 660: 
    case 707: 
    case 735: 
    case 736: 
    case 755: 
      localObject = new FunctionSQL(i);
      ((FunctionSQL)localObject).parseList = optionalNoParamList;
      return localObject;
    case 734: 
      localObject = new FunctionSQL(i);
      ((FunctionSQL)localObject).parseList = tripleParamList;
      return localObject;
    }
    Object localObject = new FunctionCustom(i);
    if (i == 31) {
      switch (paramInt)
      {
      case 700: 
        ((FunctionCustom)localObject).extractSpec = 151;
        break;
      case 722: 
        ((FunctionCustom)localObject).extractSpec = 286;
      }
    }
    if (i == 5) {
      switch (paramInt)
      {
      case 673: 
        ((FunctionCustom)localObject).extractSpec = 669;
        break;
      case 703: 
        ((FunctionCustom)localObject).extractSpec = 702;
        break;
      case 674: 
        ((FunctionCustom)localObject).extractSpec = 670;
        break;
      case 675: 
        ((FunctionCustom)localObject).extractSpec = 671;
        break;
      case 676: 
        ((FunctionCustom)localObject).extractSpec = 672;
        break;
      case 764: 
        ((FunctionCustom)localObject).extractSpec = 765;
        break;
      default: 
        ((FunctionCustom)localObject).extractSpec = paramInt;
      }
    }
    if (((FunctionCustom)localObject).name == null) {
      ((FunctionCustom)localObject).name = paramString;
    }
    return localObject;
  }
  
  public static boolean isRegularFunction(int paramInt)
  {
    return customRegularFuncMap.get(paramInt, -1) != -1;
  }
  
  public static boolean isValueFunction(int paramInt)
  {
    return customValueFuncMap.get(paramInt, -1) != -1;
  }
  
  private FunctionCustom(int paramInt)
  {
    this.funcType = paramInt;
    this.isDeterministic = (!nonDeterministicFuncSet.contains(paramInt));
    switch (paramInt)
    {
    case 72: 
    case 87: 
    case 88: 
    case 89: 
    case 90: 
    case 91: 
    case 102: 
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 114: 
    case 132: 
    case 133: 
    case 134: 
    case 146: 
    case 151: 
    case 152: 
    case 153: 
      this.parseList = emptyParamList;
      break;
    case 71: 
    case 73: 
    case 74: 
    case 75: 
    case 79: 
    case 82: 
    case 84: 
    case 85: 
    case 96: 
    case 97: 
    case 100: 
    case 109: 
    case 111: 
    case 116: 
    case 118: 
    case 124: 
    case 127: 
    case 135: 
    case 136: 
    case 137: 
    case 139: 
    case 142: 
    case 149: 
      this.parseList = singleParamList;
      break;
    case 76: 
    case 77: 
    case 78: 
    case 80: 
    case 81: 
    case 83: 
    case 86: 
    case 99: 
    case 107: 
    case 119: 
    case 120: 
    case 121: 
    case 122: 
    case 125: 
    case 147: 
    case 148: 
    case 150: 
      this.parseList = doubleParamList;
      break;
    case 95: 
      this.parseList = new short[] { 786, 788, 774, 788, 842, 2, 774, 788, 772 };
      break;
    case 92: 
    case 93: 
      this.parseList = doubleParamList;
      break;
    case 94: 
    case 131: 
      this.parseList = tripleParamList;
      break;
    case 1: 
    case 112: 
    case 123: 
    case 128: 
      this.parseList = new short[] { 786, 788, 774, 788, 842, 2, 774, 788, 772 };
      break;
    case 156: 
    case 157: 
      this.parseList = optionalSingleParamList;
      break;
    case 5: 
      this.name = "EXTRACT";
      this.parseList = singleParamList;
      break;
    case 31: 
      this.name = "TRIM";
      this.parseList = singleParamList;
      break;
    case 32: 
      this.name = "OVERLAY";
      this.parseList = quadParamList;
      break;
    case 101: 
      this.name = "IDENTITY";
      this.parseList = emptyParamList;
      break;
    case 98: 
      this.parseList = new short[] { 786, 495, 772 };
      break;
    case 115: 
      this.parseList = new short[] { 786, 788, 130, 788, 842, 2, 115, 788, 772 };
      break;
    case 138: 
      this.parseList = new short[] { 786, 788, 842, 4, 841, 2, 338, 389, 842, 5, 451, 841, 2, 401, 430, 772 };
      break;
    case 144: 
      this.name = "TIMESTAMPADD";
      this.parseList = new short[] { 786, 841, 10, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 774, 788, 774, 788, 772 };
      break;
    case 145: 
      this.name = "TIMESTAMPDIFF";
      this.parseList = new short[] { 786, 841, 10, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 774, 788, 774, 788, 772 };
      break;
    case 108: 
    case 126: 
    case 143: 
    case 154: 
    case 155: 
      this.parseList = new short[] { 786, 788, 842, 2, 774, 788, 772 };
      break;
    case 117: 
      this.parseList = optionalSingleParamList;
      break;
    case 2: 
    case 3: 
    case 4: 
    case 6: 
    case 7: 
    case 8: 
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
    case 25: 
    case 26: 
    case 27: 
    case 28: 
    case 29: 
    case 30: 
    case 33: 
    case 34: 
    case 35: 
    case 36: 
    case 37: 
    case 38: 
    case 39: 
    case 40: 
    case 41: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
    case 47: 
    case 48: 
    case 49: 
    case 50: 
    case 51: 
    case 52: 
    case 53: 
    case 54: 
    case 55: 
    case 56: 
    case 57: 
    case 58: 
    case 59: 
    case 60: 
    case 61: 
    case 62: 
    case 63: 
    case 64: 
    case 65: 
    case 66: 
    case 67: 
    case 68: 
    case 69: 
    case 70: 
    case 110: 
    case 113: 
    case 129: 
    case 130: 
    case 140: 
    case 141: 
    default: 
      throw Error.runtimeError(201, "FunctionCustom");
    }
  }
  
  public void setArguments(Expression[] paramArrayOfExpression)
  {
    Object localObject;
    switch (this.funcType)
    {
    case 1: 
      localObject = new Expression[4];
      localObject[0] = paramArrayOfExpression[0];
      localObject[1] = paramArrayOfExpression[1];
      localObject[3] = paramArrayOfExpression[2];
      paramArrayOfExpression = (Expression[])localObject;
      break;
    case 32: 
      localObject = paramArrayOfExpression[1];
      Expression localExpression = paramArrayOfExpression[2];
      paramArrayOfExpression[1] = paramArrayOfExpression[3];
      paramArrayOfExpression[2] = localObject;
      paramArrayOfExpression[3] = localExpression;
      break;
    case 5: 
      localObject = new Expression[2];
      localObject[0] = new ExpressionValue(ValuePool.getInt(this.extractSpec), Type.SQL_INTEGER);
      localObject[1] = paramArrayOfExpression[0];
      paramArrayOfExpression = (Expression[])localObject;
      break;
    case 31: 
      localObject = new Expression[3];
      localObject[0] = new ExpressionValue(ValuePool.getInt(this.extractSpec), Type.SQL_INTEGER);
      localObject[1] = new ExpressionValue(" ", Type.SQL_CHAR);
      localObject[2] = paramArrayOfExpression[0];
      paramArrayOfExpression = (Expression[])localObject;
    }
    super.setArguments(paramArrayOfExpression);
  }
  
  public Expression getFunctionExpression()
  {
    switch (this.funcType)
    {
    case 83: 
      return new ExpressionArithmetic(36, this.nodes[0], this.nodes[1]);
    }
    return super.getFunctionExpression();
  }
  
  Object getValue(Session paramSession, Object[] paramArrayOfObject)
  {
    Object localObject1;
    Object localObject7;
    Object localObject15;
    Object localObject14;
    int k;
    Object localObject11;
    long l9;
    Object localObject4;
    int m;
    SimpleDateFormat localSimpleDateFormat;
    long l7;
    Object localObject2;
    long l6;
    double d1;
    double d3;
    Object localObject8;
    Object localObject5;
    int i16;
    Object localObject12;
    Object localObject9;
    int i8;
    Object localObject6;
    Object localObject13;
    Object localObject10;
    switch (this.funcType)
    {
    case 1: 
    case 5: 
    case 31: 
    case 32: 
      return super.getValue(paramSession, paramArrayOfObject);
    case 87: 
      return paramSession.getDatabase().getPath();
    case 89: 
      return paramSession.getDatabase().getUniqueName();
    case 102: 
      return paramSession.isAutoCommit() ? Boolean.TRUE : Boolean.FALSE;
    case 106: 
      return paramSession.isReadOnlyDefault() ? Boolean.TRUE : Boolean.FALSE;
    case 104: 
      return paramSession.getDatabase().databaseReadOnly ? Boolean.TRUE : Boolean.FALSE;
    case 105: 
      return paramSession.getDatabase().isFilesReadOnly() ? Boolean.TRUE : Boolean.FALSE;
    case 103: 
      return Session.getIsolationString(paramSession.isolationLevel);
    case 133: 
      return Session.getIsolationString(paramSession.isolationLevelDefault);
    case 88: 
      return Session.getIsolationString(paramSession.database.defaultIsolationLevel);
    case 151: 
      switch (paramSession.database.txManager.getTransactionControl())
      {
      case 2: 
        return "MVCC";
      case 1: 
        return "MVLOCKS";
      }
      return "LOCKS";
    case 146: 
      return new IntervalSecondData(paramSession.getZoneSeconds(), 0);
    case 134: 
      return new IntervalSecondData(paramSession.sessionTimeZoneSeconds, 0);
    case 90: 
      int i = HsqlDateTime.getZoneSeconds(HsqlDateTime.tempCalDefault);
      return new IntervalSecondData(i, 0);
    case 91: 
      return "2.2.9";
    case 132: 
      return Long.valueOf(paramSession.getId());
    case 72: 
      return Long.valueOf(paramSession.actionTimestamp);
    case 152: 
      return Long.valueOf(paramSession.transactionTimestamp);
    case 153: 
      return Long.valueOf(paramSession.actionIndex);
    case 109: 
      localObject1 = (LobData)paramArrayOfObject[0];
      if (localObject1 == null) {
        return null;
      }
      return Long.valueOf(((LobData)localObject1).getId());
    case 101: 
      localObject1 = paramSession.getLastIdentity();
      if ((localObject1 instanceof Long)) {
        return localObject1;
      }
      return ValuePool.getLong(((Number)localObject1).longValue());
    case 98: 
      return paramSession.sessionContext.diagnosticsVariables[this.exprSubType];
    case 131: 
      for (int j = 0; j < paramArrayOfObject.length; j++) {
        if (paramArrayOfObject[j] == null) {
          return null;
        }
      }
      HsqlArrayList localHsqlArrayList = new HsqlArrayList();
      Object localObject3 = paramArrayOfObject[0];
      localObject7 = this.nodes[0].getDataType();
      int i12 = ((Type)localObject7).compare(paramSession, paramArrayOfObject[1], paramArrayOfObject[0]) >= 0 ? 1 : 0;
      for (;;)
      {
        int i14 = ((Type)localObject7).compare(paramSession, localObject3, paramArrayOfObject[1]);
        if (i12 != 0 ? i14 > 0 : i14 < 0) {
          break;
        }
        localHsqlArrayList.add(localObject3);
        localObject15 = ((Type)localObject7).add(localObject3, paramArrayOfObject[2], this.nodes[2].getDataType());
        i14 = ((Type)localObject7).compare(paramSession, localObject3, localObject15);
        if (i12 != 0 ? i14 >= 0 : i14 <= 0) {
          break;
        }
        localObject3 = localObject15;
      }
      localObject14 = localHsqlArrayList.toArray();
      return localObject14;
    case 144: 
      if ((paramArrayOfObject[1] == null) || (paramArrayOfObject[2] == null)) {
        return null;
      }
      paramArrayOfObject[1] = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].getDataType());
      k = ((Number)this.nodes[0].valueData).intValue();
      long l2 = ((Number)paramArrayOfObject[1]).longValue();
      localObject11 = (TimestampData)paramArrayOfObject[2];
      int i18;
      switch (k)
      {
      case 831: 
        l9 = l2 / 1000000000L;
        i18 = (int)(l2 % 1000000000L);
        localObject14 = Type.SQL_INTERVAL_SECOND_MAX_FRACTION;
        localObject15 = new IntervalSecondData(l9, i18, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      case 832: 
        l9 = l2 / 1000L;
        i18 = (int)(l2 % 1000L) * 1000000;
        localObject14 = Type.SQL_INTERVAL_SECOND_MAX_FRACTION;
        localObject15 = new IntervalSecondData(l9, i18, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      case 833: 
        localObject14 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION;
        localObject15 = IntervalSecondData.newIntervalSeconds(l2, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      case 834: 
        localObject14 = Type.SQL_INTERVAL_MINUTE_MAX_PRECISION;
        localObject15 = IntervalSecondData.newIntervalMinute(l2, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      case 835: 
        localObject14 = Type.SQL_INTERVAL_HOUR_MAX_PRECISION;
        localObject15 = IntervalSecondData.newIntervalHour(l2, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      case 836: 
        localObject14 = Type.SQL_INTERVAL_DAY_MAX_PRECISION;
        localObject15 = IntervalSecondData.newIntervalDay(l2, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      case 837: 
        localObject14 = Type.SQL_INTERVAL_DAY_MAX_PRECISION;
        localObject15 = IntervalSecondData.newIntervalDay(l2 * 7L, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      case 838: 
        localObject14 = Type.SQL_INTERVAL_MONTH_MAX_PRECISION;
        localObject15 = IntervalMonthData.newIntervalMonth(l2, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      case 839: 
        localObject14 = Type.SQL_INTERVAL_MONTH_MAX_PRECISION;
        localObject15 = IntervalMonthData.newIntervalMonth(l2 * 3L, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      case 840: 
        localObject14 = Type.SQL_INTERVAL_YEAR_MAX_PRECISION;
        localObject15 = IntervalMonthData.newIntervalMonth(l2 * 12L, (IntervalType)localObject14);
        return this.dataType.add(localObject11, localObject15, (Type)localObject14);
      }
      throw Error.runtimeError(201, "FunctionCustom");
    case 145: 
      if ((paramArrayOfObject[1] == null) || (paramArrayOfObject[2] == null)) {
        return null;
      }
      k = ((Number)this.nodes[0].valueData).intValue();
      localObject4 = (TimestampData)paramArrayOfObject[2];
      localObject7 = (TimestampData)paramArrayOfObject[1];
      if (this.nodes[2].dataType.isDateTimeTypeWithZone()) {
        localObject4 = (TimestampData)Type.SQL_TIMESTAMP.convertToType(paramSession, localObject4, Type.SQL_TIMESTAMP_WITH_TIME_ZONE);
      }
      if (this.nodes[1].dataType.isDateTimeTypeWithZone()) {
        localObject7 = (TimestampData)Type.SQL_TIMESTAMP.convertToType(paramSession, localObject7, Type.SQL_TIMESTAMP_WITH_TIME_ZONE);
      }
      switch (k)
      {
      case 831: 
        localObject11 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION;
        localObject14 = (IntervalSecondData)((IntervalType)localObject11).subtract(localObject4, localObject7, null);
        return new Long(1000000000L * ((IntervalSecondData)localObject14).getSeconds() + ((IntervalSecondData)localObject14).getNanos());
      case 832: 
        localObject11 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION;
        localObject14 = (IntervalSecondData)((IntervalType)localObject11).subtract(localObject4, localObject7, null);
        return new Long(1000L * ((IntervalSecondData)localObject14).getSeconds() + ((IntervalSecondData)localObject14).getNanos() / 1000000);
      case 833: 
        localObject11 = Type.SQL_INTERVAL_SECOND_MAX_PRECISION;
        return new Long(((IntervalType)localObject11).convertToLong(((IntervalType)localObject11).subtract(localObject4, localObject7, null)));
      case 834: 
        localObject11 = Type.SQL_INTERVAL_MINUTE_MAX_PRECISION;
        return new Long(((IntervalType)localObject11).convertToLong(((IntervalType)localObject11).subtract(localObject4, localObject7, null)));
      case 835: 
        localObject11 = Type.SQL_INTERVAL_HOUR_MAX_PRECISION;
        return new Long(((IntervalType)localObject11).convertToLong(((IntervalType)localObject11).subtract(localObject4, localObject7, null)));
      case 836: 
        localObject11 = Type.SQL_INTERVAL_DAY_MAX_PRECISION;
        return new Long(((IntervalType)localObject11).convertToLong(((IntervalType)localObject11).subtract(localObject4, localObject7, null)));
      case 837: 
        localObject11 = Type.SQL_INTERVAL_DAY_MAX_PRECISION;
        return new Long(((IntervalType)localObject11).convertToLong(((IntervalType)localObject11).subtract(localObject4, localObject7, null)) / 7L);
      case 838: 
        localObject11 = Type.SQL_INTERVAL_MONTH_MAX_PRECISION;
        return new Long(((IntervalType)localObject11).convertToLong(((IntervalType)localObject11).subtract(localObject4, localObject7, null)));
      case 839: 
        localObject11 = Type.SQL_INTERVAL_MONTH_MAX_PRECISION;
        return new Long(((IntervalType)localObject11).convertToLong(((IntervalType)localObject11).subtract(localObject4, localObject7, null)) / 3L);
      case 840: 
        localObject11 = Type.SQL_INTERVAL_YEAR_MAX_PRECISION;
        return new Long(((IntervalType)localObject11).convertToLong(((IntervalType)localObject11).subtract(localObject4, localObject7, null)));
      }
      throw Error.runtimeError(201, "FunctionCustom");
    case 92: 
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null)) {
        return null;
      }
      return this.dataType.add(paramArrayOfObject[0], paramArrayOfObject[1], this.nodes[1].dataType);
    case 93: 
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null)) {
        return null;
      }
      return this.dataType.subtract(paramArrayOfObject[0], paramArrayOfObject[1], this.nodes[1].dataType);
    case 96: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      IntervalSecondData localIntervalSecondData = (IntervalSecondData)Type.SQL_INTERVAL_DAY_MAX_PRECISION.subtract(paramArrayOfObject[0], DateTimeType.epochTimestamp, Type.SQL_DATE);
      return ValuePool.getInt((int)(localIntervalSecondData.getSeconds() / 86400L + 1L));
    case 126: 
    case 154: 
      m = 103;
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      if (this.dataType.isDateTimeType())
      {
        localObject4 = (DateTimeType)this.dataType;
        if ((this.nodes.length > 1) && (this.nodes[1] != null))
        {
          if (paramArrayOfObject[1] == null) {
            return null;
          }
          m = HsqlDateTime.toStandardIntervalPart((String)paramArrayOfObject[1]);
        }
        if (m < 0) {
          throw Error.error(5566, (String)paramArrayOfObject[1]);
        }
        return this.funcType == 126 ? ((DateTimeType)localObject4).round(paramArrayOfObject[0], m) : ((DateTimeType)localObject4).truncate(paramArrayOfObject[0], m);
      }
    case 155: 
      m = 0;
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      if (this.nodes.length > 1)
      {
        if (paramArrayOfObject[1] == null) {
          return null;
        }
        paramArrayOfObject[1] = Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].getDataType());
        m = ((Number)paramArrayOfObject[1]).intValue();
      }
      return this.funcType == 126 ? ((NumberType)this.dataType).round(paramArrayOfObject[0], m) : ((NumberType)this.dataType).truncate(paramArrayOfObject[0], m);
    case 147: 
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null)) {
        return null;
      }
      localSimpleDateFormat = paramSession.getSimpleDateFormatGMT();
      localObject4 = (Date)((DateTimeType)this.nodes[0].dataType).convertSQLToJavaGMT(paramSession, paramArrayOfObject[0]);
      return HsqlDateTime.toFormattedDate((Date)localObject4, (String)paramArrayOfObject[1], localSimpleDateFormat);
    case 149: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      return this.dataType.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].dataType);
    case 148: 
    case 150: 
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null)) {
        return null;
      }
      localSimpleDateFormat = paramSession.getSimpleDateFormatGMT();
      localObject4 = HsqlDateTime.toDate((String)paramArrayOfObject[0], (String)paramArrayOfObject[1], localSimpleDateFormat);
      long l5 = ((Date)localObject4).getTime();
      l7 = 0;
      if (this.funcType == 150) {
        l7 = (int)(l5 % 1000L) * 1000000;
      }
      return new TimestampData(l5 / 1000L, l7);
    case 143: 
      int n = this.nodes[1] == null ? 1 : 0;
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      if (n != 0)
      {
        if (this.nodes[0].dataType.isNumberType()) {
          return new TimestampData(((Number)paramArrayOfObject[0]).longValue());
        }
        try
        {
          return Type.SQL_TIMESTAMP.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].dataType);
        }
        catch (HsqlException localHsqlException)
        {
          return Type.SQL_DATE.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].dataType);
        }
      }
      if (paramArrayOfObject[1] == null) {
        return null;
      }
      TimestampData localTimestampData = (TimestampData)Type.SQL_DATE.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].dataType);
      TimeData localTimeData = (TimeData)Type.SQL_TIME.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].dataType);
      return new TimestampData(localTimestampData.getSeconds() + localTimeData.getSeconds(), localTimeData.getNanos());
    case 114: 
      return new Double(3.141592653589793D);
    case 117: 
      if (this.nodes[0] == null) {
        return new Double(paramSession.random());
      }
      paramArrayOfObject[0] = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].getDataType());
      long l1 = ((Number)paramArrayOfObject[0]).longValue();
      return new Double(paramSession.random(l1));
    case 156: 
      if (this.nodes[0] == null)
      {
        localObject2 = UUID.randomUUID();
        long l3 = ((UUID)localObject2).getMostSignificantBits();
        l6 = ((UUID)localObject2).getLeastSignificantBits();
        return new BinaryData(ArrayUtil.toByteArray(l3, l6), false);
      }
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      if (this.dataType.isBinaryType()) {
        return new BinaryData(StringConverter.toBinaryUUID((String)paramArrayOfObject[0]), false);
      }
      return StringConverter.toStringUUID(((BinaryData)paramArrayOfObject[0]).getBytes());
    case 157: 
      if (this.nodes[0] == null)
      {
        localObject2 = paramSession.getCurrentTimestamp(true);
        return Long.valueOf(((TimestampData)localObject2).getSeconds());
      }
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      return Long.valueOf(((TimestampData)paramArrayOfObject[0]).getSeconds());
    case 71: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      return new Double(Math.acos(d1));
    case 74: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      return new Double(Math.asin(d1));
    case 75: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      return new Double(Math.atan(d1));
    case 84: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      return new Double(Math.cos(d1));
    case 85: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      d3 = 1.0D / Math.tan(d1);
      return new Double(d3);
    case 97: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      return new Double(Math.toDegrees(d1));
    case 136: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      return new Double(Math.sin(d1));
    case 142: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      return new Double(Math.tan(d1));
    case 111: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      return new Double(Math.log10(d1));
    case 116: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      d1 = NumberType.toDouble(paramArrayOfObject[0]);
      return new Double(Math.toRadians(d1));
    case 135: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      int i1 = ((NumberType)this.nodes[0].dataType).compareToZero(paramArrayOfObject[0]);
      return ValuePool.getInt(i1);
    case 76: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      double d2 = NumberType.toDouble(paramArrayOfObject[0]);
      d3 = NumberType.toDouble(paramArrayOfObject[1]);
      return new Double(Math.atan2(d2, d3));
    case 73: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      String str1;
      if (this.nodes[0].dataType.isLobType()) {
        str1 = ((ClobData)paramArrayOfObject[0]).getSubString(paramSession, 0L, 1);
      } else {
        str1 = (String)paramArrayOfObject[0];
      }
      if (str1.length() == 0) {
        return null;
      }
      return ValuePool.getInt(str1.charAt(0));
    case 82: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      paramArrayOfObject[0] = Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].getDataType());
      int i2 = ((Number)paramArrayOfObject[0]).intValue();
      if ((Character.isValidCodePoint(i2)) && (Character.isValidCodePoint((char)i2))) {
        return String.valueOf((char)i2);
      }
      throw Error.error(3472);
    case 127: 
      int i3 = 0;
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      if (this.nodes.length > 1)
      {
        if (paramArrayOfObject[1] == null) {
          return null;
        }
        i3 = ((Number)paramArrayOfObject[1]).intValue();
      }
      return ((NumberType)this.dataType).round(paramArrayOfObject[0], i3);
    case 137: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      String str2 = (String)paramArrayOfObject[0];
      return new String(soundex(str2), 0, 4);
    case 77: 
    case 78: 
    case 79: 
    case 80: 
    case 81: 
      for (int i4 = 0; i4 < paramArrayOfObject.length; i4++) {
        if (paramArrayOfObject[i4] == null) {
          return null;
        }
      }
      if (this.dataType.isNumberType())
      {
        long l4 = 0L;
        long l8 = 0L;
        paramArrayOfObject[0] = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].getDataType());
        l6 = ((Number)paramArrayOfObject[0]).longValue();
        if (this.funcType != 79)
        {
          paramArrayOfObject[1] = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].getDataType());
          l8 = ((Number)paramArrayOfObject[1]).longValue();
        }
        switch (this.funcType)
        {
        case 77: 
          l4 = l6 & l8;
          break;
        case 78: 
          l4 = l6 & (l8 ^ 0xFFFFFFFF);
          break;
        case 79: 
          l4 = l6 ^ 0xFFFFFFFF;
          break;
        case 80: 
          l4 = l6 | l8;
          break;
        case 81: 
          l4 = l6 ^ l8;
        }
        switch (this.dataType.typeCode)
        {
        case 2: 
        case 3: 
          return BigDecimal.valueOf(l4);
        case 25: 
          return ValuePool.getLong(l4);
        case -6: 
        case 4: 
        case 5: 
          return ValuePool.getInt((int)l4);
        }
        throw Error.error(5561);
      }
      byte[] arrayOfByte1 = ((BinaryData)paramArrayOfObject[0]).getBytes();
      localObject8 = null;
      if (this.funcType != 79) {
        localObject8 = ((BinaryData)paramArrayOfObject[1]).getBytes();
      }
      byte[] arrayOfByte2;
      switch (this.funcType)
      {
      case 77: 
        arrayOfByte2 = BitMap.and(arrayOfByte1, (byte[])localObject8);
        break;
      case 78: 
        localObject8 = BitMap.not((byte[])localObject8);
        arrayOfByte2 = BitMap.and(arrayOfByte1, (byte[])localObject8);
        break;
      case 79: 
        arrayOfByte2 = BitMap.not(arrayOfByte1);
        break;
      case 80: 
        arrayOfByte2 = BitMap.or1(arrayOfByte1, (byte[])localObject8);
        break;
      case 81: 
        arrayOfByte2 = BitMap.xor(arrayOfByte1, (byte[])localObject8);
        break;
      default: 
        throw Error.error(5561);
      }
      return new BinaryData(arrayOfByte2, this.dataType.precision);
    case 99: 
      for (int i5 = 0; i5 < paramArrayOfObject.length; i5++) {
        if (paramArrayOfObject[i5] == null) {
          return null;
        }
      }
      localObject5 = soundex((String)paramArrayOfObject[0]);
      localObject8 = soundex((String)paramArrayOfObject[1]);
      int i13 = 0;
      if (localObject5[0] == localObject8[0]) {
        i13++;
      }
      l7 = 1;
      for (i16 = 1; i16 < 4; i16++) {
        for (l9 = l7; l9 < 4; l9++) {
          if (localObject5[l9] == localObject8[i16])
          {
            i13++;
            l7 = l9 + 1;
            break;
          }
        }
      }
      return ValuePool.getInt(i13);
    case 100: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      return this.dataType.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].dataType);
    case 118: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      localObject5 = (BlobData)paramArrayOfObject[0];
      localObject8 = ((BlobData)localObject5).getBytes(paramSession, 0L, (int)((BlobData)localObject5).length(paramSession));
      return StringConverter.byteArrayToHexString((byte[])localObject8);
    case 122: 
      for (int i6 = 0; i6 < paramArrayOfObject.length; i6++) {
        if (paramArrayOfObject[i6] == null) {
          return null;
        }
      }
      paramArrayOfObject[1] = Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].getDataType());
      String str3 = (String)paramArrayOfObject[0];
      int i10 = ((Number)paramArrayOfObject[1]).intValue();
      localObject12 = new StringBuffer(str3.length() * i10);
      while (i10-- > 0) {
        ((StringBuffer)localObject12).append(str3);
      }
      return ((StringBuffer)localObject12).toString();
    case 123: 
      for (int i7 = 0; i7 < paramArrayOfObject.length; i7++) {
        if (paramArrayOfObject[i7] == null) {
          return null;
        }
      }
      String str4 = (String)paramArrayOfObject[0];
      localObject9 = (String)paramArrayOfObject[1];
      localObject12 = (String)paramArrayOfObject[2];
      StringBuffer localStringBuffer2 = new StringBuffer();
      int i17;
      for (i16 = 0;; i16 = i17 + ((String)localObject9).length())
      {
        i17 = str4.indexOf((String)localObject9, i16);
        if (i17 == -1)
        {
          localStringBuffer2.append(str4.substring(i16));
          break;
        }
        localStringBuffer2.append(str4.substring(i16, i17));
        localStringBuffer2.append((String)localObject12);
      }
      return localStringBuffer2.toString();
    case 107: 
    case 125: 
      for (i8 = 0; i8 < paramArrayOfObject.length; i8++) {
        if (paramArrayOfObject[i8] == null) {
          return null;
        }
      }
      i8 = ((Number)paramArrayOfObject[1]).intValue();
      return ((CharacterType)this.dataType).substring(paramSession, paramArrayOfObject[0], 0L, i8, true, this.funcType == 125);
    case 139: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      paramArrayOfObject[0] = Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[0], this.nodes[0].getDataType());
      i8 = ((Number)paramArrayOfObject[0]).intValue();
      localObject9 = new char[i8];
      ArrayUtil.fillArray((char[])localObject9, 0, ' ');
      return String.valueOf((char[])localObject9);
    case 124: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      StringBuffer localStringBuffer1 = new StringBuffer((String)paramArrayOfObject[0]);
      localStringBuffer1 = localStringBuffer1.reverse();
      return localStringBuffer1.toString();
    case 119: 
    case 120: 
    case 121: 
      for (int i9 = 0; i9 < paramArrayOfObject.length; i9++) {
        if (paramArrayOfObject[i9] == null) {
          return null;
        }
      }
      localObject6 = this.pattern;
      if (localObject6 == null)
      {
        localObject9 = (String)paramArrayOfObject[1];
        localObject6 = Pattern.compile((String)localObject9);
      }
      localObject9 = ((Pattern)localObject6).matcher((String)paramArrayOfObject[0]);
      boolean bool;
      switch (this.funcType)
      {
      case 119: 
        bool = ((Matcher)localObject9).matches();
        return Boolean.valueOf(bool);
      case 120: 
        bool = ((Matcher)localObject9).find();
        if (bool) {
          return ((Matcher)localObject9).group();
        }
        return null;
      case 121: 
        localObject13 = new HsqlArrayList();
        while (((Matcher)localObject9).find()) {
          ((HsqlArrayList)localObject13).add(((Matcher)localObject9).group());
        }
        return ((HsqlArrayList)localObject13).toArray();
      }
    case 86: 
      localObject6 = Crypto.getNewKey((String)paramArrayOfObject[0], (String)paramArrayOfObject[1]);
      return StringConverter.byteArrayToHexString((byte[])localObject6);
    case 108: 
      localObject6 = (String)paramArrayOfObject[0];
      if (localObject6 == null) {
        return null;
      }
      switch (this.dataType.typeCode)
      {
      case 40: 
        return paramSession.sessionData.createClobFromFile((String)localObject6, (String)paramArrayOfObject[1]);
      }
      return paramSession.sessionData.createBlobFromFile((String)localObject6);
    case 112: 
    case 128: 
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null)) {
        return null;
      }
      if ((this.nodes[0].dataType.isCharacterType()) && (!this.nodes[0].dataType.isLobType())) {
        localObject6 = (String)paramArrayOfObject[0];
      } else {
        localObject6 = this.nodes[0].dataType.convertToString(paramArrayOfObject[0]);
      }
      int i11 = ((Integer)Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].dataType)).intValue();
      localObject13 = " ";
      if (this.nodes[2] != null)
      {
        localObject13 = this.nodes[2].dataType.convertToString(paramArrayOfObject[2]);
        if (((String)localObject13).length() == 0) {
          localObject13 = " ";
        }
      }
      localObject6 = (String)Type.SQL_VARCHAR.trim(paramSession, localObject6, 32, true, true);
      localObject6 = StringUtil.toPaddedString((String)localObject6, i11, (String)localObject13, this.funcType == 128);
      if (this.dataType.isLobType()) {
        return this.dataType.convertToType(paramSession, localObject6, Type.SQL_VARCHAR);
      }
      return localObject6;
    case 115: 
      if (paramArrayOfObject[1] == null) {
        return null;
      }
      if (paramArrayOfObject[2] == null) {
        return null;
      }
      localObject6 = (Object[])paramArrayOfObject[1];
      localObject10 = (ArrayType)this.nodes[1].dataType;
      localObject13 = ((ArrayType)localObject10).collectionBaseType();
      int i15 = ((Number)Type.SQL_INTEGER.convertToType(paramSession, paramArrayOfObject[2], this.nodes[2].dataType)).intValue();
      if (i15 <= 0) {
        throw Error.error(3403);
      }
      i15--;
      for (i16 = i15; i16 < localObject6.length; i16++) {
        if (((Type)localObject13).compare(paramSession, paramArrayOfObject[0], localObject6[i16]) == 0) {
          return ValuePool.getInt(i16 + 1);
        }
      }
      return ValuePool.INTEGER_0;
    case 138: 
      if (paramArrayOfObject[0] == null) {
        return null;
      }
      localObject6 = (ArrayType)this.dataType;
      localObject10 = new SortAndSlice();
      ((SortAndSlice)localObject10).prepareSingleColumn(1);
      ((SortAndSlice)localObject10).sortDescending[0] = (((Number)paramArrayOfObject[1]).intValue() == 389 ? 1 : false);
      ((SortAndSlice)localObject10).sortNullsLast[0] = (((Number)paramArrayOfObject[2]).intValue() == 430 ? 1 : false);
      localObject13 = ArrayUtil.duplicateArray(paramArrayOfObject[0]);
      ((ArrayType)localObject6).sort(paramSession, localObject13, (SortAndSlice)localObject10);
      return localObject13;
    }
    throw Error.runtimeError(201, "FunctionCustom");
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    for (int i = 0; i < this.nodes.length; i++) {
      if (this.nodes[i] != null) {
        this.nodes[i].resolveTypes(paramSession, this);
      }
    }
    Object localObject;
    int k;
    switch (this.funcType)
    {
    case 1: 
    case 5: 
    case 31: 
    case 32: 
      super.resolveTypes(paramSession, paramExpression);
      return;
    case 87: 
      this.dataType = Type.SQL_VARCHAR_DEFAULT;
      return;
    case 89: 
      this.dataType = Type.SQL_VARCHAR_DEFAULT;
      return;
    case 102: 
    case 104: 
    case 105: 
    case 106: 
      this.dataType = Type.SQL_BOOLEAN;
      return;
    case 88: 
    case 91: 
    case 103: 
    case 133: 
    case 151: 
      this.dataType = Type.SQL_VARCHAR_DEFAULT;
      return;
    case 90: 
    case 134: 
    case 146: 
      this.dataType = Type.SQL_INTERVAL_HOUR_TO_MINUTE;
      return;
    case 72: 
    case 101: 
    case 109: 
    case 132: 
    case 152: 
    case 153: 
      this.dataType = Type.SQL_BIGINT;
      return;
    case 98: 
      this.exprSubType = 2;
      this.dataType = Type.SQL_INTEGER;
      return;
    case 131: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = this.nodes[1].dataType;
      }
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = this.nodes[0].dataType;
      }
      if (this.nodes[0].dataType == null)
      {
        this.nodes[0].dataType = Type.SQL_INTEGER;
        this.nodes[1].dataType = Type.SQL_INTEGER;
      }
      if (this.nodes[0].dataType.isNumberType())
      {
        if (this.nodes[2].dataType == null) {
          this.nodes[2].dataType = this.nodes[0].dataType;
        }
      }
      else if (this.nodes[0].dataType.isDateTimeType())
      {
        if (this.nodes[2].dataType == null) {
          throw Error.error(5561);
        }
        if (!this.nodes[2].dataType.isIntervalType()) {
          throw Error.error(5561);
        }
      }
      this.dataType = new ArrayType(this.nodes[0].getDataType(), 2147483647);
      return;
    case 94: 
      if (this.nodes[0].dataType == null) {
        throw Error.error(5575);
      }
      if (!this.nodes[0].dataType.isCharacterType()) {
        throw Error.error(5561);
      }
      i = getTSIToken((String)this.nodes[0].valueData);
      this.nodes[0].valueData = ValuePool.getInt(i);
      this.nodes[0].dataType = Type.SQL_INTEGER;
      this.funcType = 144;
    case 144: 
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = Type.SQL_BIGINT;
      }
      if (this.nodes[2].dataType == null) {
        this.nodes[2].dataType = Type.SQL_TIMESTAMP;
      }
      if (!this.nodes[1].dataType.isNumberType()) {
        throw Error.error(5561);
      }
      if ((this.nodes[2].dataType.typeCode != 91) && (this.nodes[2].dataType.typeCode != 93) && (this.nodes[2].dataType.typeCode != 95)) {
        throw Error.error(5561);
      }
      this.dataType = this.nodes[2].dataType;
      return;
    case 95: 
      if (this.nodes[2] == null)
      {
        this.nodes[2] = this.nodes[0];
        this.nodes[0] = new ExpressionValue(ValuePool.getInt(836), Type.SQL_INTEGER);
      }
      else
      {
        if (!this.nodes[0].dataType.isCharacterType()) {
          throw Error.error(5563);
        }
        i = getTSIToken((String)this.nodes[0].valueData);
        this.nodes[0].valueData = ValuePool.getInt(i);
        this.nodes[0].dataType = Type.SQL_INTEGER;
      }
      this.funcType = 145;
    case 145: 
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = this.nodes[2].dataType;
      }
      if (this.nodes[2].dataType == null) {
        this.nodes[2].dataType = this.nodes[1].dataType;
      }
      if (this.nodes[1].dataType == null)
      {
        this.nodes[1].dataType = Type.SQL_TIMESTAMP;
        this.nodes[2].dataType = Type.SQL_TIMESTAMP;
      }
      switch (this.nodes[1].dataType.typeCode)
      {
      case 91: 
        if ((this.nodes[2].dataType.typeCode == 92) || (this.nodes[2].dataType.typeCode == 94)) {
          throw Error.error(5563);
        }
        switch (((Integer)this.nodes[0].valueData).intValue())
        {
        case 836: 
        case 837: 
        case 838: 
        case 839: 
        case 840: 
          break;
        default: 
          throw Error.error(5563);
        }
        break;
      case 93: 
      case 95: 
        if ((this.nodes[2].dataType.typeCode == 92) || (this.nodes[2].dataType.typeCode == 94)) {
          throw Error.error(5563);
        }
        break;
      case 92: 
      case 94: 
      default: 
        throw Error.error(5563);
      }
      this.dataType = Type.SQL_BIGINT;
      return;
    case 92: 
    case 93: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_DATE;
      }
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = Type.SQL_INTEGER;
      }
      if (this.nodes[0].dataType.isCharacterType()) {
        this.nodes[0] = new ExpressionOp(this.nodes[0], Type.SQL_TIMESTAMP);
      }
      if (this.nodes[1].dataType.isIntegralType()) {
        this.nodes[1] = new ExpressionOp(this.nodes[1], Type.SQL_INTERVAL_DAY);
      }
      this.nodes[0].resolveTypes(paramSession, this);
      this.nodes[1].resolveTypes(paramSession, this);
      this.dataType = this.nodes[0].dataType;
      return;
    case 96: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_DATE;
      }
      switch (this.nodes[0].dataType.typeCode)
      {
      case 91: 
      case 93: 
      case 95: 
        break;
      case 92: 
      case 94: 
      default: 
        throw Error.error(5563);
      }
      this.dataType = Type.SQL_INTEGER;
      return;
    case 126: 
    case 154: 
      i = (this.nodes.length == 1) || (this.nodes[1] == null) ? 1 : 0;
      if (this.nodes[0].dataType == null) {
        if (i != 0)
        {
          this.nodes[0].dataType = Type.SQL_DECIMAL;
        }
        else
        {
          if (this.nodes[1].dataType == null) {
            this.nodes[1].dataType = Type.SQL_INTEGER;
          }
          if (this.nodes[1].dataType.isNumberType()) {
            this.nodes[0].dataType = Type.SQL_DECIMAL;
          } else {
            this.nodes[0].dataType = Type.SQL_TIMESTAMP;
          }
        }
      }
      if (this.nodes[0].dataType.isDateTimeType())
      {
        if ((i == 0) && (!this.nodes[1].dataType.isCharacterType())) {
          throw Error.error(5566);
        }
        this.dataType = this.nodes[0].dataType;
        return;
      }
      if (!this.nodes[0].dataType.isNumberType()) {
        throw Error.error(5561);
      }
    case 155: 
      localObject = null;
      if (this.nodes[0].dataType == null) {
        throw Error.error(5567);
      }
      if (!this.nodes[0].dataType.isNumberType()) {
        throw Error.error(5563);
      }
      if (this.nodes[1] == null)
      {
        this.nodes[1] = new ExpressionValue(ValuePool.INTEGER_0, Type.SQL_INTEGER);
        localObject = ValuePool.INTEGER_0;
      }
      else
      {
        if (this.nodes[1].dataType == null) {
          this.nodes[1].dataType = Type.SQL_INTEGER;
        } else if (!this.nodes[1].dataType.isIntegralType()) {
          throw Error.error(5563);
        }
        if (this.nodes[1].opType == 1) {
          localObject = (Number)this.nodes[1].getValue(paramSession);
        }
      }
      this.dataType = this.nodes[0].dataType;
      if (localObject != null)
      {
        int n = ((Number)localObject).intValue();
        if (n < 0) {
          n = 0;
        } else if (n > this.dataType.scale) {
          n = this.dataType.scale;
        }
        if (((this.dataType.typeCode == 3) || (this.dataType.typeCode == 2)) && (n != this.dataType.scale)) {
          this.dataType = new NumberType(this.dataType.typeCode, this.dataType.precision - this.dataType.scale + n, n);
        }
      }
      return;
    case 147: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_TIMESTAMP;
      }
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if (!this.nodes[1].dataType.isCharacterType()) {
        throw Error.error(5563);
      }
      if (!this.nodes[0].dataType.isDateTimeType()) {
        throw Error.error(5563);
      }
      this.dataType = CharacterType.getCharacterType(12, 64L);
      return;
    case 149: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if (!this.nodes[0].dataType.isCharacterType()) {
        throw Error.error(5563);
      }
      this.dataType = Type.SQL_DECIMAL_DEFAULT;
      return;
    case 148: 
    case 150: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if ((!this.nodes[0].dataType.isCharacterType()) || (!this.nodes[1].dataType.isCharacterType())) {
        throw Error.error(5567);
      }
      this.dataType = (this.funcType == 148 ? Type.SQL_TIMESTAMP_NO_FRACTION : Type.SQL_TIMESTAMP);
      return;
    case 143: 
      localObject = this.nodes[0].dataType;
      if (this.nodes[1] == null)
      {
        if (localObject == null) {
          localObject = this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
        }
        if ((!((Type)localObject).isCharacterType()) && (((Type)localObject).typeCode != 93) && (((Type)localObject).typeCode != 95) && (!((Type)localObject).isNumberType())) {
          throw Error.error(5561);
        }
      }
      else
      {
        if (localObject == null) {
          if (this.nodes[1].dataType == null) {
            localObject = this.nodes[0].dataType = this.nodes[1].dataType = Type.SQL_VARCHAR_DEFAULT;
          } else if (this.nodes[1].dataType.isCharacterType()) {
            localObject = this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
          } else {
            localObject = this.nodes[0].dataType = Type.SQL_DATE;
          }
        }
        if (this.nodes[1].dataType == null) {
          if (((Type)localObject).isCharacterType()) {
            this.nodes[1].dataType = Type.SQL_VARCHAR_DEFAULT;
          } else if (((Type)localObject).typeCode == 91) {
            this.nodes[1].dataType = Type.SQL_TIME;
          }
        }
        if (((((Type)localObject).typeCode != 91) || (this.nodes[1].dataType.typeCode != 92)) && ((!((Type)localObject).isCharacterType()) || (!this.nodes[1].dataType.isCharacterType()))) {
          throw Error.error(5561);
        }
      }
      this.dataType = Type.SQL_TIMESTAMP;
      return;
    case 114: 
      this.dataType = Type.SQL_DOUBLE;
      break;
    case 156: 
      if (this.nodes[0] == null)
      {
        this.dataType = Type.SQL_BINARY_16;
        return;
      }
      if (this.nodes[0].dataType == null)
      {
        this.nodes[0].dataType = Type.SQL_VARCHAR;
        this.dataType = Type.SQL_BINARY_16;
        return;
      }
      if (this.nodes[0].dataType.isCharacterType())
      {
        this.dataType = Type.SQL_BINARY_16;
        return;
      }
      if ((this.nodes[0].dataType.isBinaryType()) && (!this.nodes[0].dataType.isLobType()))
      {
        this.dataType = Type.SQL_CHAR_16;
        return;
      }
      throw Error.error(5563);
    case 157: 
      if (this.nodes[0] != null) {
        if (this.nodes[0].dataType == null) {
          this.nodes[0].dataType = Type.SQL_TIMESTAMP;
        } else if ((!this.nodes[0].dataType.isDateTimeType()) || (this.nodes[0].dataType.typeCode == 92) || (this.nodes[0].dataType.typeCode == 94)) {
          throw Error.error(5563);
        }
      }
      this.dataType = Type.SQL_BIGINT;
      break;
    case 117: 
      if (this.nodes[0] != null) {
        if (this.nodes[0].dataType == null) {
          this.nodes[0].dataType = Type.SQL_BIGINT;
        } else if (!this.nodes[0].dataType.isExactNumberType()) {
          throw Error.error(5563);
        }
      }
      this.dataType = Type.SQL_DOUBLE;
      break;
    case 71: 
    case 74: 
    case 75: 
    case 84: 
    case 85: 
    case 97: 
    case 111: 
    case 116: 
    case 127: 
    case 136: 
    case 142: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_DOUBLE;
      }
      if (!this.nodes[0].dataType.isNumberType()) {
        throw Error.error(5561);
      }
      this.dataType = Type.SQL_DOUBLE;
      break;
    case 135: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_DOUBLE;
      }
      if (!this.nodes[0].dataType.isNumberType()) {
        throw Error.error(5561);
      }
      this.dataType = Type.SQL_INTEGER;
      break;
    case 76: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_DOUBLE;
      }
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = Type.SQL_DOUBLE;
      }
      if ((!this.nodes[0].dataType.isNumberType()) || (!this.nodes[1].dataType.isNumberType())) {
        throw Error.error(5561);
      }
      this.dataType = Type.SQL_DOUBLE;
      break;
    case 137: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR;
      }
      if (!this.nodes[0].dataType.isCharacterType()) {
        throw Error.error(5561);
      }
      this.dataType = CharacterType.getCharacterType(12, 4L);
      break;
    case 77: 
    case 78: 
    case 79: 
    case 80: 
    case 81: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = this.nodes[1].dataType;
      }
      if (this.funcType == 79)
      {
        if (this.nodes[0].dataType == null) {
          this.nodes[0].dataType = Type.SQL_INTEGER;
        }
        this.dataType = this.nodes[0].dataType;
      }
      else
      {
        this.dataType = this.nodes[0].dataType;
        if (this.nodes[1].dataType == null) {
          this.nodes[1].dataType = this.nodes[0].dataType;
        }
        for (int j = 0; j < this.nodes.length; j++) {
          if (this.nodes[j].dataType == null) {
            this.nodes[j].dataType = Type.SQL_INTEGER;
          }
        }
        this.dataType = this.nodes[0].dataType.getAggregateType(this.nodes[1].dataType);
      }
      switch (this.dataType.typeCode)
      {
      case -6: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 8: 
      case 25: 
        break;
      case 14: 
      case 15: 
        break;
      case -5: 
      case -4: 
      case -3: 
      case -2: 
      case -1: 
      case 0: 
      case 1: 
      case 6: 
      case 7: 
      case 9: 
      case 10: 
      case 11: 
      case 12: 
      case 13: 
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
        throw Error.error(5561);
      }
      break;
    case 73: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR;
      }
      if (!this.nodes[0].dataType.isCharacterType()) {
        throw Error.error(5561);
      }
      this.dataType = Type.SQL_INTEGER;
      break;
    case 82: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_INTEGER;
      }
      if (!this.nodes[0].dataType.isExactNumberType()) {
        throw Error.error(5561);
      }
      this.dataType = CharacterType.getCharacterType(12, 1L);
      break;
    case 99: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR;
      }
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = Type.SQL_VARCHAR;
      }
      this.dataType = Type.SQL_INTEGER;
      break;
    case 100: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR;
      }
      if (!this.nodes[0].dataType.isCharacterType()) {
        throw Error.error(5561);
      }
      this.dataType = (this.nodes[0].dataType.precision == 0L ? Type.SQL_VARBINARY_DEFAULT : BinaryType.getBinaryType(61, this.nodes[0].dataType.precision / 2L));
      break;
    case 118: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARBINARY;
      }
      if (!this.nodes[0].dataType.isBinaryType()) {
        throw Error.error(5561);
      }
      this.dataType = (this.nodes[0].dataType.precision == 0L ? Type.SQL_VARCHAR_DEFAULT : CharacterType.getCharacterType(12, this.nodes[0].dataType.precision * 2L));
      break;
    case 122: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR;
      }
      k = this.nodes[0].dataType.isCharacterType();
      if ((k == 0) && (!this.nodes[0].dataType.isBinaryType())) {
        throw Error.error(5561);
      }
      if (!this.nodes[1].dataType.isExactNumberType()) {
        throw Error.error(5561);
      }
      this.dataType = (k != 0 ? Type.SQL_VARCHAR_DEFAULT : Type.SQL_VARBINARY_DEFAULT);
      break;
    case 123: 
      if (this.nodes[2] == null) {
        this.nodes[2] = new ExpressionValue("", Type.SQL_VARCHAR);
      }
      for (k = 0; k < this.nodes.length; k++) {
        if (this.nodes[k].dataType == null) {
          this.nodes[k].dataType = Type.SQL_VARCHAR;
        } else if (!this.nodes[k].dataType.isCharacterType()) {
          throw Error.error(5561);
        }
      }
      this.dataType = Type.SQL_VARCHAR_DEFAULT;
      break;
    case 107: 
    case 125: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR;
      }
      if (!this.nodes[0].dataType.isCharacterType()) {
        throw Error.error(5561);
      }
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = Type.SQL_INTEGER;
      }
      if (!this.nodes[1].dataType.isExactNumberType()) {
        throw Error.error(5561);
      }
      this.dataType = (this.nodes[0].dataType.precision == 0L ? Type.SQL_VARCHAR_DEFAULT : ((CharacterType)this.nodes[0].dataType).getCharacterType(this.nodes[0].dataType.precision));
      break;
    case 139: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_INTEGER;
      }
      if (!this.nodes[0].dataType.isIntegralType()) {
        throw Error.error(5561);
      }
      this.dataType = Type.SQL_VARCHAR_DEFAULT;
      break;
    case 124: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      this.dataType = this.nodes[0].dataType;
      if ((this.dataType.isCharacterType()) && (!this.dataType.isLobType())) {
        return;
      }
      throw Error.error(5561);
    case 119: 
    case 120: 
    case 121: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if ((!this.nodes[0].dataType.isCharacterType()) || (!this.nodes[1].dataType.isCharacterType()) || (this.nodes[1].dataType.isLobType())) {
        throw Error.error(5561);
      }
      if (this.nodes[1].exprSubType == 1)
      {
        String str = (String)this.nodes[1].getValue(paramSession);
        this.pattern = Pattern.compile(str);
      }
      switch (this.funcType)
      {
      case 119: 
        this.dataType = Type.SQL_BOOLEAN;
        break;
      case 120: 
        this.dataType = Type.SQL_VARCHAR_DEFAULT;
        break;
      case 121: 
        this.dataType = Type.getDefaultArrayType(12);
      }
      break;
    case 86: 
      for (int m = 0; m < this.nodes.length; m++) {
        if (this.nodes[m].dataType == null) {
          this.nodes[m].dataType = Type.SQL_VARCHAR;
        } else if (!this.nodes[m].dataType.isCharacterType()) {
          throw Error.error(5561);
        }
      }
      this.dataType = Type.SQL_VARCHAR_DEFAULT;
      break;
    case 108: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if (!this.nodes[0].dataType.isCharacterType()) {
        throw Error.error(5561);
      }
      if (this.nodes[1] == null)
      {
        this.dataType = Type.SQL_BLOB;
        return;
      }
      this.dataType = Type.SQL_CLOB;
      if ((this.nodes[1].dataType != null) && (this.nodes[1].dataType.isCharacterType())) {
        return;
      }
      throw Error.error(5561);
    case 112: 
    case 128: 
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if (this.nodes[1].dataType == null) {
        this.nodes[1].dataType = Type.SQL_INTEGER;
      }
      if (!this.nodes[1].dataType.isIntegralType()) {
        throw Error.error(5561);
      }
      if (this.nodes[2] != null)
      {
        if (this.nodes[2].dataType == null) {
          this.nodes[2].dataType = Type.SQL_VARCHAR_DEFAULT;
        }
        if (!this.nodes[2].dataType.isCharacterType()) {
          throw Error.error(5561);
        }
      }
      this.dataType = this.nodes[0].dataType;
      if (this.dataType.typeCode != 40) {
        this.dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if (this.nodes[1].opType != 1) {
        return;
      }
      Number localNumber = (Number)this.nodes[1].getValue(paramSession);
      if (localNumber != null) {
        this.dataType = ((CharacterType)this.dataType).getCharacterType(localNumber.longValue());
      }
      break;
    case 115: 
      if (this.nodes[1].dataType == null) {
        throw Error.error(5567);
      }
      if (!this.nodes[1].dataType.isArrayType()) {
        throw Error.error(5563);
      }
      if (this.nodes[0].dataType == null) {
        this.nodes[0].dataType = ((ArrayType)this.nodes[1].dataType).collectionBaseType();
      }
      if (((ArrayType)this.nodes[1].dataType).collectionBaseType().typeComparisonGroup != this.nodes[0].dataType.typeComparisonGroup) {
        throw Error.error(5563);
      }
      if (this.nodes[2] == null) {
        this.nodes[2] = new ExpressionValue(ValuePool.INTEGER_1, Type.SQL_INTEGER);
      }
      if (this.nodes[2].dataType == null) {
        this.nodes[2].dataType = Type.SQL_INTEGER;
      }
      if (!this.nodes[2].dataType.isIntegralType()) {
        throw Error.error(5563);
      }
      this.dataType = Type.SQL_INTEGER;
      break;
    case 138: 
      if (this.nodes[0].dataType == null) {
        throw Error.error(5567);
      }
      if (!this.nodes[0].dataType.isArrayType()) {
        throw Error.error(5563);
      }
      if (this.nodes[1] == null) {
        this.nodes[1] = new ExpressionValue(ValuePool.getInt(338), Type.SQL_INTEGER);
      }
      if (this.nodes[2] == null) {
        this.nodes[2] = new ExpressionValue(ValuePool.getInt(401), Type.SQL_INTEGER);
      }
      this.dataType = this.nodes[0].dataType;
      break;
    }
    throw Error.runtimeError(201, "FunctionCustom");
  }
  
  public String getSQL()
  {
    Object localObject;
    switch (this.funcType)
    {
    case 1: 
      localObject = new StringBuffer("LOCATE").append("(").append(this.nodes[0].getSQL()).append(",").append(this.nodes[1].getSQL());
      if ((this.nodes.length > 3) && (this.nodes[3] != null)) {
        ((StringBuffer)localObject).append(",").append(this.nodes[3].getSQL());
      }
      ((StringBuffer)localObject).append(")").toString();
      return ((StringBuffer)localObject).toString();
    case 112: 
    case 128: 
      localObject = new StringBuffer(this.name);
      ((StringBuffer)localObject).append("(").append(this.nodes[0].getSQL());
      ((StringBuffer)localObject).append(",").append(this.nodes[1].getSQL());
      if (this.nodes[2] != null) {
        ((StringBuffer)localObject).append(",").append(this.nodes[2].getSQL());
      }
      ((StringBuffer)localObject).append(")").toString();
      return ((StringBuffer)localObject).toString();
    case 5: 
    case 31: 
    case 32: 
      return super.getSQL();
    case 115: 
      localObject = new StringBuffer(this.name).append('(');
      ((StringBuffer)localObject).append(this.nodes[0].getSQL()).append(' ').append("IN");
      ((StringBuffer)localObject).append(' ').append(this.nodes[1].getSQL());
      if (((Number)this.nodes[1].valueData).intValue() == 389)
      {
        ((StringBuffer)localObject).append(' ').append("FROM");
        ((StringBuffer)localObject).append(' ').append(this.nodes[2].getSQL());
      }
      ((StringBuffer)localObject).append(')');
      return ((StringBuffer)localObject).toString();
    case 138: 
      localObject = new StringBuffer(this.name).append('(');
      ((StringBuffer)localObject).append(this.nodes[0].getSQL());
      if (((Number)this.nodes[1].valueData).intValue() == 389) {
        ((StringBuffer)localObject).append(' ').append("DESC");
      }
      if (((Number)this.nodes[2].valueData).intValue() == 430)
      {
        ((StringBuffer)localObject).append(' ').append("NULLS").append(' ');
        ((StringBuffer)localObject).append("LAST");
      }
      ((StringBuffer)localObject).append(')');
      return ((StringBuffer)localObject).toString();
    case 72: 
    case 87: 
    case 88: 
    case 89: 
    case 90: 
    case 91: 
    case 101: 
    case 102: 
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 114: 
    case 132: 
    case 133: 
    case 134: 
    case 146: 
    case 151: 
    case 152: 
    case 153: 
      return this.name + "(" + ")";
    case 144: 
      localObject = Tokens.getSQLTSIString(((Number)this.nodes[0].getValue(null)).intValue());
      return "TIMESTAMPADD" + "(" + (String)localObject + "," + this.nodes[1].getSQL() + "," + this.nodes[2].getSQL() + ")";
    case 145: 
      localObject = Tokens.getSQLTSIString(((Number)this.nodes[0].getValue(null)).intValue());
      return "TIMESTAMPDIFF" + "(" + (String)localObject + "," + this.nodes[1].getSQL() + "," + this.nodes[2].getSQL() + ")";
    case 92: 
      return this.nodes[0].getSQL() + ' ' + '+' + this.nodes[1].getSQL();
    case 93: 
      return this.nodes[0].getSQL() + ' ' + '-' + this.nodes[1].getSQL();
    case 117: 
    case 157: 
      localObject = new StringBuffer(this.name).append('(');
      if (this.nodes[0] != null) {
        ((StringBuffer)localObject).append(this.nodes[0].getSQL());
      }
      ((StringBuffer)localObject).append(')');
      return ((StringBuffer)localObject).toString();
    case 108: 
    case 126: 
    case 149: 
    case 154: 
    case 155: 
      localObject = new StringBuffer(this.name).append('(');
      ((StringBuffer)localObject).append(this.nodes[0].getSQL());
      if (this.nodes[1] != null) {
        ((StringBuffer)localObject).append(',').append(this.nodes[1].getSQL());
      }
      ((StringBuffer)localObject).append(')');
      return ((StringBuffer)localObject).toString();
    case 71: 
    case 73: 
    case 74: 
    case 75: 
    case 82: 
    case 84: 
    case 85: 
    case 96: 
    case 97: 
    case 100: 
    case 109: 
    case 111: 
    case 116: 
    case 118: 
    case 124: 
    case 127: 
    case 135: 
    case 136: 
    case 137: 
    case 139: 
    case 142: 
      return this.name + '(' + this.nodes[0].getSQL() + ')';
    case 76: 
    case 77: 
    case 78: 
    case 79: 
    case 80: 
    case 81: 
    case 86: 
    case 99: 
    case 107: 
    case 119: 
    case 120: 
    case 121: 
    case 122: 
    case 125: 
    case 143: 
    case 147: 
    case 148: 
    case 150: 
      return this.name + '(' + this.nodes[0].getSQL() + "," + this.nodes[1].getSQL() + ')';
    case 98: 
      localObject = new StringBuffer(this.name).append('(');
      ((StringBuffer)localObject).append("ROW_COUNT");
      ((StringBuffer)localObject).append(')');
      return ((StringBuffer)localObject).toString();
    case 123: 
    case 131: 
      return this.name + '(' + this.nodes[0].getSQL() + "," + this.nodes[1].getSQL() + "," + this.nodes[2].getSQL() + ')';
    }
    return super.getSQL();
  }
  
  public static char[] soundex(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = paramString.toUpperCase(Locale.ENGLISH);
    int i = paramString.length();
    char[] arrayOfChar = { '0', '0', '0', '0' };
    int j = 48;
    int k = 0;
    int m = 0;
    while ((k < i) && (m < 4))
    {
      int n = paramString.charAt(k);
      int i1;
      if ("AEIOUY".indexOf(n) != -1)
      {
        i1 = 55;
      }
      else if ((n == 72) || (n == 87))
      {
        i1 = 56;
      }
      else if ("BFPV".indexOf(n) != -1)
      {
        i1 = 49;
      }
      else if ("CGJKQSXZ".indexOf(n) != -1)
      {
        i1 = 50;
      }
      else if ((n == 68) || (n == 84))
      {
        i1 = 51;
      }
      else if (n == 76)
      {
        i1 = 52;
      }
      else if ((n == 77) || (n == 78))
      {
        i1 = 53;
      }
      else
      {
        if (n != 82) {
          break label275;
        }
        i1 = 54;
      }
      if (m == 0)
      {
        arrayOfChar[(m++)] = n;
        j = i1;
      }
      else if (i1 <= 54)
      {
        if (i1 != j)
        {
          arrayOfChar[(m++)] = i1;
          j = i1;
        }
      }
      else if (i1 == 55)
      {
        j = i1;
      }
      label275:
      k++;
    }
    return arrayOfChar;
  }
  
  int getTSIToken(String paramString)
  {
    int i;
    if (("yy".equalsIgnoreCase(paramString)) || ("year".equalsIgnoreCase(paramString))) {
      i = 840;
    } else if (("mm".equalsIgnoreCase(paramString)) || ("month".equalsIgnoreCase(paramString))) {
      i = 838;
    } else if (("dd".equalsIgnoreCase(paramString)) || ("day".equalsIgnoreCase(paramString))) {
      i = 836;
    } else if (("hh".equalsIgnoreCase(paramString)) || ("hour".equalsIgnoreCase(paramString))) {
      i = 835;
    } else if (("mi".equalsIgnoreCase(paramString)) || ("minute".equalsIgnoreCase(paramString))) {
      i = 834;
    } else if (("ss".equalsIgnoreCase(paramString)) || ("second".equalsIgnoreCase(paramString))) {
      i = 833;
    } else if (("ms".equalsIgnoreCase(paramString)) || ("millisecond".equalsIgnoreCase(paramString))) {
      i = 832;
    } else {
      throw Error.error(5566, paramString);
    }
    return i;
  }
  
  static
  {
    nonDeterministicFuncSet.add(72);
    nonDeterministicFuncSet.add(86);
    nonDeterministicFuncSet.add(87);
    nonDeterministicFuncSet.add(88);
    nonDeterministicFuncSet.add(90);
    nonDeterministicFuncSet.add(101);
    nonDeterministicFuncSet.add(102);
    nonDeterministicFuncSet.add(106);
    nonDeterministicFuncSet.add(104);
    nonDeterministicFuncSet.add(105);
    nonDeterministicFuncSet.add(103);
    nonDeterministicFuncSet.add(132);
    nonDeterministicFuncSet.add(133);
    nonDeterministicFuncSet.add(134);
    nonDeterministicFuncSet.add(143);
    nonDeterministicFuncSet.add(146);
    nonDeterministicFuncSet.add(151);
    nonDeterministicFuncSet.add(152);
    nonDeterministicFuncSet.add(153);
    nonDeterministicFuncSet.add(156);
    nonDeterministicFuncSet.add(157);
    customRegularFuncMap.put(640, 71);
    customRegularFuncMap.put(641, 72);
    customRegularFuncMap.put(642, 138);
    customRegularFuncMap.put(643, 73);
    customRegularFuncMap.put(644, 74);
    customRegularFuncMap.put(645, 75);
    customRegularFuncMap.put(646, 76);
    customRegularFuncMap.put(648, 77);
    customRegularFuncMap.put(649, 78);
    customRegularFuncMap.put(650, 6);
    customRegularFuncMap.put(651, 79);
    customRegularFuncMap.put(652, 80);
    customRegularFuncMap.put(653, 81);
    customRegularFuncMap.put(33, 82);
    customRegularFuncMap.put(654, 82);
    customRegularFuncMap.put(655, 83);
    customRegularFuncMap.put(656, 84);
    customRegularFuncMap.put(657, 85);
    customRegularFuncMap.put(658, 86);
    customRegularFuncMap.put(659, 43);
    customRegularFuncMap.put(660, 51);
    customRegularFuncMap.put(582, 87);
    customRegularFuncMap.put(662, 89);
    customRegularFuncMap.put(661, 88);
    customRegularFuncMap.put(663, 90);
    customRegularFuncMap.put(664, 91);
    customRegularFuncMap.put(665, 92);
    customRegularFuncMap.put(666, 93);
    customRegularFuncMap.put(667, 94);
    customRegularFuncMap.put(668, 95);
    customRegularFuncMap.put(73, 5);
    customRegularFuncMap.put(673, 5);
    customRegularFuncMap.put(674, 5);
    customRegularFuncMap.put(675, 5);
    customRegularFuncMap.put(676, 5);
    customRegularFuncMap.put(677, 96);
    customRegularFuncMap.put(679, 97);
    customRegularFuncMap.put(391, 98);
    customRegularFuncMap.put(680, 99);
    customRegularFuncMap.put(682, 100);
    customRegularFuncMap.put(127, 5);
    customRegularFuncMap.put(128, 101);
    customRegularFuncMap.put(135, 32);
    customRegularFuncMap.put(686, 102);
    customRegularFuncMap.put(687, 104);
    customRegularFuncMap.put(688, 105);
    customRegularFuncMap.put(689, 106);
    customRegularFuncMap.put(690, 103);
    customRegularFuncMap.put(692, 26);
    customRegularFuncMap.put(153, 107);
    customRegularFuncMap.put(431, 7);
    customRegularFuncMap.put(694, 108);
    customRegularFuncMap.put(701, 109);
    customRegularFuncMap.put(695, 1);
    customRegularFuncMap.put(697, 14);
    customRegularFuncMap.put(698, 111);
    customRegularFuncMap.put(699, 112);
    customRegularFuncMap.put(700, 31);
    customRegularFuncMap.put(169, 5);
    customRegularFuncMap.put(173, 5);
    customRegularFuncMap.put(703, 5);
    customRegularFuncMap.put(708, 8);
    customRegularFuncMap.put(709, 114);
    customRegularFuncMap.put(710, 115);
    customRegularFuncMap.put(711, 5);
    customRegularFuncMap.put(712, 116);
    customRegularFuncMap.put(713, 117);
    customRegularFuncMap.put(714, 118);
    customRegularFuncMap.put(715, 119);
    customRegularFuncMap.put(716, 120);
    customRegularFuncMap.put(717, 121);
    customRegularFuncMap.put(234, 122);
    customRegularFuncMap.put(718, 123);
    customRegularFuncMap.put(719, 124);
    customRegularFuncMap.put(240, 125);
    customRegularFuncMap.put(720, 126);
    customRegularFuncMap.put(721, 127);
    customRegularFuncMap.put(723, 128);
    customRegularFuncMap.put(722, 31);
    customRegularFuncMap.put(250, 5);
    customRegularFuncMap.put(724, 5);
    customRegularFuncMap.put(725, 131);
    customRegularFuncMap.put(726, 132);
    customRegularFuncMap.put(727, 133);
    customRegularFuncMap.put(728, 134);
    customRegularFuncMap.put(729, 135);
    customRegularFuncMap.put(730, 136);
    customRegularFuncMap.put(731, 138);
    customRegularFuncMap.put(732, 137);
    customRegularFuncMap.put(514, 139);
    customRegularFuncMap.put(734, 23);
    customRegularFuncMap.put(737, 142);
    customRegularFuncMap.put(282, 143);
    customRegularFuncMap.put(738, 144);
    customRegularFuncMap.put(739, 145);
    customRegularFuncMap.put(740, 146);
    customRegularFuncMap.put(741, 147);
    customRegularFuncMap.put(742, 148);
    customRegularFuncMap.put(743, 149);
    customRegularFuncMap.put(744, 150);
    customRegularFuncMap.put(757, 151);
    customRegularFuncMap.put(758, 152);
    customRegularFuncMap.put(759, 153);
    customRegularFuncMap.put(760, 154);
    customRegularFuncMap.put(295, 155);
    customRegularFuncMap.put(761, 27);
    customRegularFuncMap.put(762, 157);
    customRegularFuncMap.put(763, 156);
    customRegularFuncMap.put(764, 5);
    customRegularFuncMap.put(323, 5);
    customValueFuncMap = new IntKeyIntValueHashMap();
    customValueFuncMap.put(735, 52);
    customValueFuncMap.put(736, 50);
    customValueFuncMap.put(755, 43);
    customValueFuncMap.put(707, 52);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.FunctionCustom
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */