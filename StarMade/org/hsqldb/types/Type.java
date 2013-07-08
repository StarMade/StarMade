package org.hsqldb.types;

import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.SortAndSlice;
import org.hsqldb.error.Error;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.ObjectComparator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.rights.Grantee;

public abstract class Type
  implements SchemaObject, Cloneable
{
  public static final Type[] emptyArray = new Type[0];
  public final int typeComparisonGroup;
  public final int typeCode;
  public final long precision;
  public final int scale;
  public UserTypeModifier userTypeModifier;
  public static final Type SQL_ALL_TYPES = NullType.getNullType();
  public static final CharacterType SQL_CHAR = new CharacterType(1, 1L);
  public static final CharacterType SQL_CHAR_16 = new CharacterType(1, 16L);
  public static final CharacterType SQL_CHAR_DEFAULT = new CharacterType(1, 256L);
  public static final CharacterType SQL_VARCHAR = new CharacterType(12, 0L);
  public static final CharacterType SQL_VARCHAR_DEFAULT = new CharacterType(12, 32768L);
  public static final ClobType SQL_CLOB = new ClobType(16777216L);
  public static final CharacterType VARCHAR_IGNORECASE = new CharacterType(100, 0L);
  public static final CharacterType VARCHAR_IGNORECASE_DEFAULT = new CharacterType(100, 32768L);
  public static final BitType SQL_BIT = new BitType(14, 1L);
  public static final BitType SQL_BIT_VARYING = new BitType(15, 1L);
  public static final BitType SQL_BIT_VARYING_MAX_LENGTH = new BitType(15, 1024L);
  public static final BinaryType SQL_BINARY = new BinaryType(60, 1L);
  public static final BinaryType SQL_BINARY_16 = new BinaryType(60, 16L);
  public static final BinaryType SQL_BINARY_DEFAULT = new BinaryType(60, 32768L);
  public static final BinaryType SQL_VARBINARY = new BinaryType(61, 0L);
  public static final BinaryType SQL_VARBINARY_DEFAULT = new BinaryType(61, 32768L);
  public static final BlobType SQL_BLOB = new BlobType(16777216L);
  public static final OtherType OTHER = OtherType.getOtherType();
  public static final BooleanType SQL_BOOLEAN = BooleanType.getBooleanType();
  public static final NumberType SQL_NUMERIC = new NumberType(2, 128L, 0);
  public static final NumberType SQL_DECIMAL = new NumberType(3, 128L, 0);
  public static final NumberType SQL_DECIMAL_DEFAULT = new NumberType(3, 128L, 32);
  public static final NumberType SQL_DECIMAL_BIGINT_SQR = new NumberType(3, 40L, 0);
  public static final NumberType SQL_DOUBLE = new NumberType(8, 0L, 0);
  public static final NumberType TINYINT = new NumberType(-6, 3L, 0);
  public static final NumberType SQL_SMALLINT = new NumberType(5, 5L, 0);
  public static final NumberType SQL_INTEGER = new NumberType(4, 10L, 0);
  public static final NumberType SQL_BIGINT = new NumberType(25, 19L, 0);
  public static final DateTimeType SQL_DATE = new DateTimeType(93, 91, 0);
  public static final DateTimeType SQL_TIME = new DateTimeType(92, 92, 0);
  public static final DateTimeType SQL_TIME_WITH_TIME_ZONE = new DateTimeType(92, 94, 0);
  public static final DateTimeType SQL_TIMESTAMP = new DateTimeType(93, 93, 6);
  public static final DateTimeType SQL_TIMESTAMP_WITH_TIME_ZONE = new DateTimeType(93, 95, 6);
  public static final DateTimeType SQL_TIMESTAMP_NO_FRACTION = new DateTimeType(93, 93, 0);
  public static final IntervalType SQL_INTERVAL_YEAR = IntervalType.newIntervalType(101, 2L, 0);
  public static final IntervalType SQL_INTERVAL_MONTH = IntervalType.newIntervalType(102, 2L, 0);
  public static final IntervalType SQL_INTERVAL_DAY = IntervalType.newIntervalType(103, 2L, 0);
  public static final IntervalType SQL_INTERVAL_HOUR = IntervalType.newIntervalType(104, 2L, 0);
  public static final IntervalType SQL_INTERVAL_MINUTE = IntervalType.newIntervalType(105, 2L, 0);
  public static final IntervalType SQL_INTERVAL_SECOND = IntervalType.newIntervalType(106, 2L, 6);
  public static final IntervalType SQL_INTERVAL_SECOND_MAX_FRACTION = IntervalType.newIntervalType(106, 2L, 9);
  public static final IntervalType SQL_INTERVAL_YEAR_TO_MONTH = IntervalType.newIntervalType(107, 2L, 0);
  public static final IntervalType SQL_INTERVAL_DAY_TO_HOUR = IntervalType.newIntervalType(108, 2L, 0);
  public static final IntervalType SQL_INTERVAL_DAY_TO_MINUTE = IntervalType.newIntervalType(109, 2L, 0);
  public static final IntervalType SQL_INTERVAL_DAY_TO_SECOND = IntervalType.newIntervalType(110, 2L, 6);
  public static final IntervalType SQL_INTERVAL_HOUR_TO_MINUTE = IntervalType.newIntervalType(111, 2L, 0);
  public static final IntervalType SQL_INTERVAL_HOUR_TO_SECOND = IntervalType.newIntervalType(112, 2L, 6);
  public static final IntervalType SQL_INTERVAL_MINUTE_TO_SECOND = IntervalType.newIntervalType(113, 2L, 6);
  public static final IntervalType SQL_INTERVAL_YEAR_MAX_PRECISION = IntervalType.newIntervalType(101, 9L, 0);
  public static final IntervalType SQL_INTERVAL_MONTH_MAX_PRECISION = IntervalType.newIntervalType(102, 9L, 0);
  public static final IntervalType SQL_INTERVAL_DAY_MAX_PRECISION = IntervalType.newIntervalType(103, 9L, 0);
  public static final IntervalType SQL_INTERVAL_HOUR_MAX_PRECISION = IntervalType.newIntervalType(104, 9L, 0);
  public static final IntervalType SQL_INTERVAL_MINUTE_MAX_PRECISION = IntervalType.newIntervalType(105, 9L, 0);
  public static final IntervalType SQL_INTERVAL_SECOND_MAX_PRECISION = IntervalType.newIntervalType(106, 9L, 6);
  public static final IntervalType SQL_INTERVAL_SECOND_MAX_FRACTION_MAX_PRECISION = IntervalType.newIntervalType(106, 9L, 9);
  public static final IntervalType SQL_INTERVAL_YEAR_TO_MONTH_MAX_PRECISION = IntervalType.newIntervalType(107, 9L, 0);
  public static final IntervalType SQL_INTERVAL_DAY_TO_SECOND_MAX_PRECISION = IntervalType.newIntervalType(110, 9L, 9);
  public static final ArrayType SQL_ARRAY_ALL_TYPES = new ArrayType(SQL_ALL_TYPES, 0);
  public static final IntValueHashMap typeAliases;
  public static final IntValueHashMap typeNames = new IntValueHashMap(37);
  public static final IntKeyHashMap jdbcConvertTypes;
  
  Type(int paramInt1, int paramInt2, long paramLong, int paramInt3)
  {
    this.typeComparisonGroup = paramInt1;
    this.typeCode = paramInt2;
    this.precision = paramLong;
    this.scale = paramInt3;
  }
  
  public final int getType()
  {
    if (this.userTypeModifier == null) {
      throw Error.runtimeError(201, "Type");
    }
    return this.userTypeModifier.getType();
  }
  
  public final HsqlNameManager.HsqlName getName()
  {
    if (this.userTypeModifier == null) {
      throw Error.runtimeError(201, "Type");
    }
    return this.userTypeModifier.getName();
  }
  
  public final HsqlNameManager.HsqlName getCatalogName()
  {
    if (this.userTypeModifier == null) {
      throw Error.runtimeError(201, "Type");
    }
    return this.userTypeModifier.getSchemaName().schema;
  }
  
  public final HsqlNameManager.HsqlName getSchemaName()
  {
    if (this.userTypeModifier == null) {
      throw Error.runtimeError(201, "Type");
    }
    return this.userTypeModifier.getSchemaName();
  }
  
  public final Grantee getOwner()
  {
    if (this.userTypeModifier == null) {
      throw Error.runtimeError(201, "Type");
    }
    return this.userTypeModifier.getOwner();
  }
  
  public final OrderedHashSet getReferences()
  {
    if (this.userTypeModifier == null) {
      throw Error.runtimeError(201, "Type");
    }
    return this.userTypeModifier.getReferences();
  }
  
  public final OrderedHashSet getComponents()
  {
    if (this.userTypeModifier == null) {
      throw Error.runtimeError(201, "Type");
    }
    return this.userTypeModifier.getComponents();
  }
  
  public final void compile(Session paramSession, SchemaObject paramSchemaObject)
  {
    if (this.userTypeModifier == null) {
      throw Error.runtimeError(201, "Type");
    }
    this.userTypeModifier.compile(paramSession);
  }
  
  public String getSQL()
  {
    if (this.userTypeModifier == null) {
      throw Error.runtimeError(201, "Type");
    }
    return this.userTypeModifier.getSQL();
  }
  
  public long getChangeTimestamp()
  {
    return 0L;
  }
  
  public Type duplicate()
  {
    try
    {
      return (Type)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw Error.runtimeError(201, "Type");
    }
  }
  
  public abstract int displaySize();
  
  public abstract int getJDBCTypeCode();
  
  public abstract String getJDBCClassName();
  
  public abstract Class getJDBCClass();
  
  public int getJDBCScale()
  {
    return this.scale;
  }
  
  public int getJDBCPrecision()
  {
    return this.precision > 2147483647L ? 2147483647 : (int)this.precision;
  }
  
  public int getSQLGenericTypeCode()
  {
    return this.typeCode;
  }
  
  public abstract String getNameString();
  
  public String getFullNameString()
  {
    return getNameString();
  }
  
  public abstract String getDefinition();
  
  public boolean hasCollation()
  {
    return false;
  }
  
  public String getCollationDefinition()
  {
    return "";
  }
  
  public Collation getCollation()
  {
    return null;
  }
  
  public Charset getCharacterSet()
  {
    return null;
  }
  
  public final String getTypeDefinition()
  {
    if (this.userTypeModifier == null) {
      return getDefinition();
    }
    return getName().getSchemaQualifiedStatementName();
  }
  
  public abstract int compare(Session paramSession, Object paramObject1, Object paramObject2);
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2, SortAndSlice paramSortAndSlice)
  {
    if (paramObject1 == paramObject2) {
      return 0;
    }
    if (paramObject1 == null) {
      return paramSortAndSlice.sortNullsLast[0] != 0 ? 1 : -1;
    }
    if (paramObject2 == null) {
      return paramSortAndSlice.sortNullsLast[0] != 0 ? -1 : 1;
    }
    int i = compare(paramSession, paramObject1, paramObject2);
    return paramSortAndSlice.sortDescending[0] != 0 ? -i : i;
  }
  
  public abstract Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject);
  
  public Object castToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    return convertToType(paramSessionInterface, paramObject, paramType);
  }
  
  public abstract Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType);
  
  public Object convertToTypeJDBC(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramType.isLobType()) {
      throw Error.error(5561);
    }
    return convertToType(paramSessionInterface, paramObject, paramType);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject)
  {
    return paramObject;
  }
  
  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject)
  {
    return paramObject;
  }
  
  public abstract Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject);
  
  public abstract String convertToString(Object paramObject);
  
  public abstract String convertToSQLString(Object paramObject);
  
  public abstract boolean canConvertFrom(Type paramType);
  
  public int canMoveFrom(Type paramType)
  {
    if (paramType == this) {
      return 0;
    }
    return -1;
  }
  
  public boolean canBeAssignedFrom(Type paramType)
  {
    if (paramType == null) {
      return true;
    }
    return (paramType.typeCode == 0) || (this.typeComparisonGroup == paramType.typeComparisonGroup);
  }
  
  public int arrayLimitCardinality()
  {
    return 0;
  }
  
  public Type collectionBaseType()
  {
    return null;
  }
  
  public boolean isArrayType()
  {
    return false;
  }
  
  public boolean isMultisetType()
  {
    return false;
  }
  
  public boolean isRowType()
  {
    return false;
  }
  
  public boolean isStructuredType()
  {
    return false;
  }
  
  public boolean isCharacterType()
  {
    return false;
  }
  
  public boolean isNumberType()
  {
    return false;
  }
  
  public boolean isIntegralType()
  {
    return false;
  }
  
  public boolean isExactNumberType()
  {
    return false;
  }
  
  public boolean isDecimalType()
  {
    return false;
  }
  
  public boolean isDateTimeType()
  {
    return false;
  }
  
  public boolean isDateTimeTypeWithZone()
  {
    return false;
  }
  
  public boolean isIntervalType()
  {
    return false;
  }
  
  public boolean isBinaryType()
  {
    return false;
  }
  
  public boolean isBooleanType()
  {
    return false;
  }
  
  public boolean isLobType()
  {
    return false;
  }
  
  public boolean isBitType()
  {
    return false;
  }
  
  public boolean isObjectType()
  {
    return false;
  }
  
  public boolean isDistinctType()
  {
    return this.userTypeModifier != null;
  }
  
  public boolean isDomainType()
  {
    return this.userTypeModifier != null;
  }
  
  public boolean acceptsPrecision()
  {
    return false;
  }
  
  public boolean requiresPrecision()
  {
    return false;
  }
  
  public long getMaxPrecision()
  {
    return 0L;
  }
  
  public int getMaxScale()
  {
    return 0;
  }
  
  public int getPrecisionRadix()
  {
    return 0;
  }
  
  public boolean acceptsFractionalPrecision()
  {
    return false;
  }
  
  public boolean acceptsScale()
  {
    return false;
  }
  
  public int precedenceDegree(Type paramType)
  {
    if (paramType.typeCode == this.typeCode)
    {
      if (this.typeCode == 50) {
        return collectionBaseType().precedenceDegree(paramType.collectionBaseType());
      }
      return 0;
    }
    return -2147483648;
  }
  
  public abstract Type getAggregateType(Type paramType);
  
  public abstract Type getCombinedType(Session paramSession, Type paramType, int paramInt);
  
  public int compareToTypeRange(Object paramObject)
  {
    return 0;
  }
  
  public Object absolute(Object paramObject)
  {
    throw Error.runtimeError(201, "Type");
  }
  
  public Object negate(Object paramObject)
  {
    throw Error.runtimeError(201, "Type");
  }
  
  public Object add(Object paramObject1, Object paramObject2, Type paramType)
  {
    throw Error.runtimeError(201, "Type");
  }
  
  public Object subtract(Object paramObject1, Object paramObject2, Type paramType)
  {
    throw Error.runtimeError(201, "Type");
  }
  
  public Object multiply(Object paramObject1, Object paramObject2)
  {
    throw Error.runtimeError(201, "Type");
  }
  
  public Object divide(Session paramSession, Object paramObject1, Object paramObject2)
  {
    throw Error.runtimeError(201, "Type");
  }
  
  public Object concat(Session paramSession, Object paramObject1, Object paramObject2)
  {
    throw Error.runtimeError(201, "Type");
  }
  
  public int cardinality(Session paramSession, Object paramObject)
  {
    return 0;
  }
  
  int hashCode(Object paramObject)
  {
    if (paramObject == null) {
      return 0;
    }
    return paramObject.hashCode();
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if ((paramObject instanceof Type))
    {
      if (((Type)paramObject).typeCode == 50) {
        return false;
      }
      if (((Type)paramObject).typeCode == 19) {
        return false;
      }
      return (((Type)paramObject).typeCode == this.typeCode) && (((Type)paramObject).precision == this.precision) && (((Type)paramObject).scale == this.scale) && (((Type)paramObject).userTypeModifier == this.userTypeModifier);
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.typeCode + (int)this.precision << 8 + this.scale << 16;
  }
  
  public static TypedComparator newComparator(Session paramSession)
  {
    return new TypedComparator(paramSession);
  }
  
  public static ArrayType getDefaultArrayType(int paramInt)
  {
    return new ArrayType(getDefaultType(paramInt), 1024);
  }
  
  public static Type getDefaultType(int paramInt)
  {
    try
    {
      return getType(paramInt, SQL_VARCHAR.getCharacterSet(), SQL_VARCHAR.getCollation(), 0L, 0);
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static Type getDefaultTypeWithSize(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return SQL_ALL_TYPES;
    case 50: 
      return SQL_ARRAY_ALL_TYPES;
    case 1: 
      return SQL_CHAR_DEFAULT;
    case 12: 
      return SQL_VARCHAR_DEFAULT;
    case 100: 
      return VARCHAR_IGNORECASE_DEFAULT;
    case 40: 
      return SQL_CLOB;
    case 4: 
      return SQL_INTEGER;
    case 5: 
      return SQL_SMALLINT;
    case 25: 
      return SQL_BIGINT;
    case -6: 
      return TINYINT;
    case 6: 
    case 7: 
    case 8: 
      return SQL_DOUBLE;
    case 2: 
      return SQL_NUMERIC;
    case 3: 
      return SQL_DECIMAL;
    case 16: 
      return SQL_BOOLEAN;
    case 60: 
      return SQL_BINARY_DEFAULT;
    case 61: 
      return SQL_VARBINARY_DEFAULT;
    case 30: 
      return SQL_BLOB;
    case 14: 
      return SQL_BIT;
    case 15: 
      return SQL_BIT_VARYING;
    case 91: 
      return SQL_DATE;
    case 92: 
      return SQL_TIME;
    case 94: 
      return SQL_TIME_WITH_TIME_ZONE;
    case 93: 
      return SQL_TIMESTAMP;
    case 95: 
      return SQL_TIMESTAMP_WITH_TIME_ZONE;
    case 101: 
      return SQL_INTERVAL_YEAR;
    case 107: 
      return SQL_INTERVAL_YEAR_TO_MONTH;
    case 102: 
      return SQL_INTERVAL_MONTH;
    case 103: 
      return SQL_INTERVAL_DAY;
    case 108: 
      return SQL_INTERVAL_DAY_TO_HOUR;
    case 109: 
      return SQL_INTERVAL_DAY_TO_MINUTE;
    case 110: 
      return SQL_INTERVAL_DAY_TO_SECOND;
    case 104: 
      return SQL_INTERVAL_HOUR;
    case 111: 
      return SQL_INTERVAL_HOUR_TO_MINUTE;
    case 112: 
      return SQL_INTERVAL_HOUR_TO_SECOND;
    case 105: 
      return SQL_INTERVAL_MINUTE;
    case 113: 
      return SQL_INTERVAL_MINUTE_TO_SECOND;
    case 106: 
      return SQL_INTERVAL_SECOND;
    case 1111: 
      return OTHER;
    }
    return null;
  }
  
  public static int getHSQLDBTypeCode(int paramInt)
  {
    switch (paramInt)
    {
    case -5: 
      return 25;
    case -1: 
      return 12;
    case 2005: 
      return 40;
    case -2: 
      return 60;
    case -7: 
      return 15;
    case -4: 
    case -3: 
      return 61;
    case 2004: 
      return 30;
    case 2003: 
      return 50;
    }
    return paramInt;
  }
  
  public static int getJDBCTypeCode(int paramInt)
  {
    switch (paramInt)
    {
    case 30: 
      return 2004;
    case 40: 
      return 2005;
    case 25: 
      return -5;
    case 60: 
      return -2;
    case 61: 
      return -3;
    case 14: 
    case 15: 
      return -7;
    case 50: 
      return 2003;
    }
    return paramInt;
  }
  
  public static Type getType(int paramInt1, Charset paramCharset, Collation paramCollation, long paramLong, int paramInt2)
  {
    switch (paramInt1)
    {
    case 0: 
      return SQL_ALL_TYPES;
    case 1: 
    case 12: 
    case 40: 
    case 100: 
      return CharacterType.getCharacterType(paramInt1, paramLong, paramCollation);
    case 4: 
      return SQL_INTEGER;
    case 5: 
      return SQL_SMALLINT;
    case 25: 
      return SQL_BIGINT;
    case -6: 
      return TINYINT;
    case 6: 
      if (paramLong > 53L) {
        throw Error.error(5592, "" + paramLong);
      }
    case 7: 
    case 8: 
      return SQL_DOUBLE;
    case 2: 
    case 3: 
      if (paramLong == 0L) {
        paramLong = 128L;
      }
      return NumberType.getNumberType(paramInt1, paramLong, paramInt2);
    case 16: 
      return SQL_BOOLEAN;
    case 30: 
    case 60: 
    case 61: 
      return BinaryType.getBinaryType(paramInt1, paramLong);
    case 14: 
    case 15: 
      return BitType.getBitType(paramInt1, paramLong);
    case 91: 
    case 92: 
    case 93: 
    case 94: 
    case 95: 
      return DateTimeType.getDateTimeType(paramInt1, paramInt2);
    case 101: 
    case 102: 
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 107: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
      return IntervalType.getIntervalType(paramInt1, paramLong, paramInt2);
    case 1111: 
      return OTHER;
    }
    throw Error.runtimeError(201, "Type");
  }
  
  public static Type getAggregateType(Type paramType1, Type paramType2)
  {
    if ((paramType2 == null) || (paramType2.typeCode == 0)) {
      return paramType1;
    }
    if ((paramType1 == null) || (paramType1.typeCode == 0)) {
      return paramType2;
    }
    return paramType2.getAggregateType(paramType1);
  }
  
  public static int getTypeNr(String paramString)
  {
    int i = typeNames.get(paramString, -2147483648);
    if (i == -2147483648) {
      i = typeAliases.get(paramString, -2147483648);
    }
    return i;
  }
  
  public static Type getTypeForJDBCConvertToken(int paramInt)
  {
    return (Type)jdbcConvertTypes.get(paramInt);
  }
  
  public static boolean isSupportedSQLType(int paramInt)
  {
    return getDefaultType(paramInt) != null;
  }
  
  public static boolean matches(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
  {
    for (int i = 0; i < paramArrayOfType1.length; i++) {
      if (paramArrayOfType1[i].typeCode != paramArrayOfType2[i].typeCode) {
        return false;
      }
    }
    return true;
  }
  
  static
  {
    typeNames.put("CHARACTER", 1);
    typeNames.put("VARCHAR", 12);
    typeNames.put("VARCHAR_IGNORECASE", 100);
    typeNames.put("NVARCHAR", 12);
    typeNames.put("DATE", 91);
    typeNames.put("TIME", 92);
    typeNames.put("TIMESTAMP", 93);
    typeNames.put("INTERVAL", 10);
    typeNames.put("TINYINT", -6);
    typeNames.put("SMALLINT", 5);
    typeNames.put("INTEGER", 4);
    typeNames.put("BIGINT", 25);
    typeNames.put("REAL", 7);
    typeNames.put("FLOAT", 6);
    typeNames.put("DOUBLE", 8);
    typeNames.put("NUMERIC", 2);
    typeNames.put("DECIMAL", 3);
    typeNames.put("BOOLEAN", 16);
    typeNames.put("BINARY", 60);
    typeNames.put("VARBINARY", 61);
    typeNames.put("CLOB", 40);
    typeNames.put("BLOB", 30);
    typeNames.put("BIT", 14);
    typeNames.put("OTHER", 1111);
    typeAliases = new IntValueHashMap(64);
    typeAliases.put("CHAR", 1);
    typeAliases.put("INT", 4);
    typeAliases.put("DEC", 3);
    typeAliases.put("LONGVARCHAR", -1);
    typeAliases.put("DATETIME", 93);
    typeAliases.put("LONGVARBINARY", -4);
    typeAliases.put("OBJECT", 1111);
    jdbcConvertTypes = new IntKeyHashMap(37);
    jdbcConvertTypes.put(806, SQL_CHAR_DEFAULT);
    jdbcConvertTypes.put(829, SQL_VARCHAR_DEFAULT);
    jdbcConvertTypes.put(816, SQL_VARCHAR_DEFAULT);
    jdbcConvertTypes.put(820, SQL_VARCHAR_DEFAULT);
    jdbcConvertTypes.put(808, SQL_DATE);
    jdbcConvertTypes.put(825, SQL_TIME);
    jdbcConvertTypes.put(826, SQL_TIMESTAMP);
    jdbcConvertTypes.put(827, TINYINT);
    jdbcConvertTypes.put(824, SQL_SMALLINT);
    jdbcConvertTypes.put(813, SQL_INTEGER);
    jdbcConvertTypes.put(801, SQL_BIGINT);
    jdbcConvertTypes.put(821, SQL_DOUBLE);
    jdbcConvertTypes.put(812, SQL_DOUBLE);
    jdbcConvertTypes.put(811, SQL_DOUBLE);
    jdbcConvertTypes.put(819, SQL_NUMERIC);
    jdbcConvertTypes.put(809, SQL_DECIMAL);
    jdbcConvertTypes.put(805, SQL_BOOLEAN);
    jdbcConvertTypes.put(802, SQL_BINARY_DEFAULT);
    jdbcConvertTypes.put(828, SQL_VARBINARY_DEFAULT);
    jdbcConvertTypes.put(814, SQL_VARBINARY_DEFAULT);
    jdbcConvertTypes.put(807, SQL_CLOB);
    jdbcConvertTypes.put(804, SQL_BLOB);
    jdbcConvertTypes.put(803, SQL_BIT);
  }
  
  public static class TypedComparator
    implements ObjectComparator
  {
    Session session;
    Type type;
    SortAndSlice sort;
    
    TypedComparator(Session paramSession)
    {
      this.session = paramSession;
      this.sort = this.sort;
    }
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      return this.type.compare(this.session, paramObject1, paramObject2, this.sort);
    }
    
    public int hashCode(Object paramObject)
    {
      return this.type.hashCode(paramObject);
    }
    
    public long longKey(Object paramObject)
    {
      return 0L;
    }
    
    public void setType(Type paramType, SortAndSlice paramSortAndSlice)
    {
      this.type = paramType;
      this.sort = paramSortAndSlice;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.Type
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */