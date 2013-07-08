package org.hsqldb.types;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import org.hsqldb.Database;
import org.hsqldb.HsqlDateTime;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.persist.LobManager;

public class CharacterType
  extends Type
{
  static final int defaultCharPrecision = 256;
  static final int defaultVarcharPrecision = 32768;
  static final long maxCharPrecision = 2147483647L;
  Collation collation;
  Charset charset;
  boolean isEqualIdentical;
  String nameString;
  private static final int fixedTypesLength = 32;
  static CharacterType[] charArray = new CharacterType[32];
  
  public CharacterType(Collation paramCollation, int paramInt, long paramLong)
  {
    super(12, paramInt, paramLong, 0);
    if (paramCollation == null) {
      paramCollation = Collation.defaultCollation;
    }
    this.collation = paramCollation;
    this.charset = Charset.getDefaultInstance();
    this.isEqualIdentical = ((this.collation.isEqualAlwaysIdentical()) && (paramInt != 100));
    this.nameString = getNameStringPrivate();
  }
  
  public CharacterType(int paramInt, long paramLong)
  {
    super(12, paramInt, paramLong, 0);
    this.collation = Collation.getDefaultInstance();
    this.charset = Charset.getDefaultInstance();
    this.isEqualIdentical = (paramInt != 100);
    this.nameString = getNameStringPrivate();
  }
  
  public int displaySize()
  {
    return this.precision > 2147483647L ? 2147483647 : (int)this.precision;
  }
  
  public int getJDBCTypeCode()
  {
    switch (this.typeCode)
    {
    case 1: 
      return 1;
    case 12: 
    case 100: 
      return 12;
    case 40: 
      return 2005;
    }
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public Class getJDBCClass()
  {
    return String.class;
  }
  
  public String getJDBCClassName()
  {
    return "java.lang.String";
  }
  
  public int getSQLGenericTypeCode()
  {
    return this.typeCode == 1 ? this.typeCode : 12;
  }
  
  public String getNameString()
  {
    return this.nameString;
  }
  
  private String getNameStringPrivate()
  {
    switch (this.typeCode)
    {
    case 1: 
      return "CHARACTER";
    case 12: 
      return "VARCHAR";
    case 100: 
      return "VARCHAR_IGNORECASE";
    case 40: 
      return "CLOB";
    }
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public String getFullNameString()
  {
    switch (this.typeCode)
    {
    case 1: 
      return "CHARACTER";
    case 12: 
      return "CHARACTER VARYING";
    case 100: 
      return "VARCHAR_IGNORECASE";
    case 40: 
      return "CHARACTER LARGE OBJECT";
    }
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public String getDefinition()
  {
    if (this.precision == 0L) {
      return getNameString();
    }
    StringBuffer localStringBuffer = new StringBuffer(16);
    localStringBuffer.append(getNameString());
    localStringBuffer.append('(');
    localStringBuffer.append(this.precision);
    localStringBuffer.append(')');
    return localStringBuffer.toString();
  }
  
  public boolean hasCollation()
  {
    return this.collation != Collation.defaultCollation;
  }
  
  public String getCollationDefinition()
  {
    if (hasCollation()) {
      return this.collation.getName().getSchemaQualifiedStatementName();
    }
    return "";
  }
  
  public boolean isCharacterType()
  {
    return true;
  }
  
  public long getMaxPrecision()
  {
    return 2147483647L;
  }
  
  public boolean acceptsPrecision()
  {
    return true;
  }
  
  public boolean requiresPrecision()
  {
    return (this.typeCode == 12) || (this.typeCode == 100);
  }
  
  public int precedenceDegree(Type paramType)
  {
    if (paramType.typeCode == this.typeCode) {
      return 0;
    }
    if (!paramType.isCharacterType()) {
      return -2147483648;
    }
    switch (this.typeCode)
    {
    case 1: 
      return paramType.typeCode == 40 ? 4 : 2;
    case 12: 
    case 100: 
      if ((paramType.typeCode == 12) || (paramType.typeCode == 100)) {
        return 0;
      }
      return paramType.typeCode == 40 ? 4 : 2;
    case 40: 
      return paramType.typeCode == 1 ? -4 : -2;
    }
    throw Error.runtimeError(201, "CharacterType");
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
      return this.precision >= paramType.precision ? this : paramType;
    }
    switch (paramType.typeCode)
    {
    case 1: 
      return this.precision >= paramType.precision ? this : getCharacterType(this.typeCode, paramType.precision, paramType.getCollation());
    case 12: 
      if ((this.typeCode == 40) || (this.typeCode == 100)) {
        return this.precision >= paramType.precision ? this : getCharacterType(this.typeCode, paramType.precision, paramType.getCollation());
      }
      return paramType.precision >= this.precision ? paramType : getCharacterType(paramType.typeCode, this.precision, paramType.getCollation());
    case 100: 
      if (this.typeCode == 40) {
        return this.precision >= paramType.precision ? this : getCharacterType(this.typeCode, paramType.precision, getCollation());
      }
      return paramType.precision >= this.precision ? paramType : getCharacterType(paramType.typeCode, this.precision, paramType.getCollation());
    case 40: 
      return paramType.precision >= this.precision ? paramType : getCharacterType(paramType.typeCode, this.precision, paramType.getCollation());
    case 14: 
    case 15: 
    case 30: 
    case 60: 
    case 61: 
    case 1111: 
      throw Error.error(5562);
    }
    throw Error.error(5562);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt)
  {
    if (paramInt != 36) {
      return getAggregateType(paramType);
    }
    long l = this.precision + paramType.precision;
    Object localObject;
    switch (paramType.typeCode)
    {
    case 0: 
      return this;
    case 1: 
      localObject = this;
      break;
    case 12: 
      localObject = (this.typeCode == 40) || (this.typeCode == 100) ? this : paramType;
      break;
    case 100: 
      localObject = this.typeCode == 40 ? this : paramType;
      break;
    case 40: 
      localObject = paramType;
      break;
    default: 
      throw Error.error(5562);
    }
    if (l > 2147483647L) {
      if (this.typeCode == 60) {
        l = 2147483647L;
      } else if (this.typeCode == 1) {
        l = 2147483647L;
      }
    }
    return getCharacterType(((Type)localObject).typeCode, l);
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
    if ((paramObject2 instanceof ClobData)) {
      return -paramSession.database.lobManager.compare(this.collation, (ClobData)paramObject2, (String)paramObject1);
    }
    String str1 = (String)paramObject1;
    String str2 = (String)paramObject2;
    int i = str1.length();
    int j = str2.length();
    if (i != j)
    {
      char[] arrayOfChar;
      if (i > j)
      {
        if (this.collation.isPadSpace())
        {
          arrayOfChar = new char[i];
          str2.getChars(0, j, arrayOfChar, 0);
          ArrayUtil.fillArray(arrayOfChar, j, ' ');
          str2 = String.valueOf(arrayOfChar);
        }
      }
      else if (this.collation.isPadSpace())
      {
        arrayOfChar = new char[j];
        str1.getChars(0, i, arrayOfChar, 0);
        ArrayUtil.fillArray(arrayOfChar, i, ' ');
        str1 = String.valueOf(arrayOfChar);
      }
    }
    if (this.typeCode == 100) {
      return this.collation.compareIgnoreCase(str1, str2);
    }
    return this.collation.compare(str1, str2);
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return paramObject;
    }
    if (this.precision == 0L) {
      return paramObject;
    }
    int i;
    switch (this.typeCode)
    {
    case 1: 
      i = ((String)paramObject).length();
      if (i == this.precision) {
        return paramObject;
      }
      if (i > this.precision)
      {
        if (getRightTrimSise((String)paramObject, ' ') <= this.precision) {
          return ((String)paramObject).substring(0, (int)this.precision);
        }
        throw Error.error(3401);
      }
      char[] arrayOfChar = new char[(int)this.precision];
      ((String)paramObject).getChars(0, i, arrayOfChar, 0);
      for (int j = i; j < this.precision; j++) {
        arrayOfChar[j] = ' ';
      }
      return new String(arrayOfChar);
    case 12: 
    case 100: 
      i = ((String)paramObject).length();
      if (i > this.precision)
      {
        if (getRightTrimSise((String)paramObject, ' ') <= this.precision) {
          return ((String)paramObject).substring(0, (int)this.precision);
        }
        throw Error.error(3401);
      }
      return paramObject;
    case 40: 
      return paramObject;
    }
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public Object castToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null) {
      return paramObject;
    }
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, true);
  }
  
  public Object castOrConvertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType, boolean paramBoolean)
  {
    long l;
    switch (paramType.typeCode)
    {
    case 1: 
    case 12: 
    case 100: 
      int i = ((String)paramObject).length();
      if ((this.precision != 0L) && (i > this.precision))
      {
        if (StringUtil.rightTrimSize((String)paramObject) > this.precision)
        {
          if (!paramBoolean) {
            throw Error.error(3401);
          }
          paramSessionInterface.addWarning(Error.error(1004));
        }
        paramObject = ((String)paramObject).substring(0, (int)this.precision);
      }
      switch (this.typeCode)
      {
      case 1: 
        return convertToTypeLimits(paramSessionInterface, paramObject);
      case 12: 
      case 100: 
        return paramObject;
      case 40: 
        ClobDataID localClobDataID = paramSessionInterface.createClob(((String)paramObject).length());
        localClobDataID.setString(paramSessionInterface, 0L, (String)paramObject);
        return localClobDataID;
      }
      throw Error.runtimeError(201, "CharacterType");
    case 40: 
      l = ((ClobData)paramObject).length(paramSessionInterface);
      if ((this.precision != 0L) && (l > this.precision) && (((ClobData)paramObject).nonSpaceLength(paramSessionInterface) > this.precision))
      {
        if (!paramBoolean) {
          throw Error.error(3401);
        }
        paramSessionInterface.addWarning(Error.error(1004));
      }
      switch (this.typeCode)
      {
      case 1: 
      case 12: 
      case 100: 
        if (l > 2147483647L)
        {
          if (!paramBoolean) {
            throw Error.error(3401);
          }
          l = 2147483647L;
        }
        paramObject = ((ClobData)paramObject).getSubString(paramSessionInterface, 0L, (int)l);
        return convertToTypeLimits(paramSessionInterface, paramObject);
      case 40: 
        if ((this.precision != 0L) && (l > this.precision)) {
          return ((ClobData)paramObject).getClob(paramSessionInterface, 0L, this.precision);
        }
        return paramObject;
      }
      throw Error.runtimeError(201, "CharacterType");
    case 1111: 
      throw Error.error(5561);
    case 30: 
      l = ((BlobData)paramObject).length(paramSessionInterface);
      if ((this.precision != 0L) && (l * 2L > this.precision)) {
        throw Error.error(3401);
      }
      byte[] arrayOfByte = ((BlobData)paramObject).getBytes(paramSessionInterface, 0L, (int)l);
      paramObject = StringConverter.byteArrayToHexString(arrayOfByte);
      return convertToTypeLimits(paramSessionInterface, paramObject);
    }
    String str = paramType.convertToString(paramObject);
    if ((this.precision != 0L) && (str.length() > this.precision)) {
      throw Error.error(3401);
    }
    paramObject = str;
    return convertToTypeLimits(paramSessionInterface, paramObject);
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null) {
      return paramObject;
    }
    return castOrConvertToType(paramSessionInterface, paramObject, paramType, false);
  }
  
  public Object convertToTypeJDBC(SessionInterface paramSessionInterface, Object paramObject, Type paramType)
  {
    if (paramObject == null) {
      return paramObject;
    }
    if (paramType.typeCode == 30) {
      throw Error.error(5561);
    }
    return convertToType(paramSessionInterface, paramObject, paramType);
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (paramObject == null) {
      return paramObject;
    }
    String str;
    if ((paramObject instanceof Boolean)) {
      str = paramObject.toString();
    } else if ((paramObject instanceof BigDecimal)) {
      str = JavaSystem.toString((BigDecimal)paramObject);
    } else if ((paramObject instanceof Number)) {
      str = paramObject.toString();
    } else if ((paramObject instanceof String)) {
      str = (String)paramObject;
    } else if ((paramObject instanceof java.sql.Date)) {
      str = ((java.sql.Date)paramObject).toString();
    } else if ((paramObject instanceof Time)) {
      str = ((Time)paramObject).toString();
    } else if ((paramObject instanceof Timestamp)) {
      str = ((Timestamp)paramObject).toString();
    } else if ((paramObject instanceof java.util.Date)) {
      str = HsqlDateTime.getTimestampString(((java.sql.Date)paramObject).getTime());
    } else {
      throw Error.error(5561);
    }
    return str;
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
    case 1: 
      int i = ((String)paramObject).length();
      if ((this.precision == 0L) || (i == this.precision)) {
        return (String)paramObject;
      }
      char[] arrayOfChar = new char[(int)this.precision];
      ((String)paramObject).getChars(0, i, arrayOfChar, 0);
      for (int j = i; j < this.precision; j++) {
        arrayOfChar[j] = ' ';
      }
      return new String(arrayOfChar);
    case 12: 
    case 100: 
      return (String)paramObject;
    }
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public String convertToSQLString(Object paramObject)
  {
    if (paramObject == null) {
      return "NULL";
    }
    String str = convertToString(paramObject);
    return StringConverter.toQuotedString(str, '\'', true);
  }
  
  public boolean canConvertFrom(Type paramType)
  {
    return (!paramType.isObjectType()) && (!paramType.isArrayType());
  }
  
  public int canMoveFrom(Type paramType)
  {
    if (paramType == this) {
      return 0;
    }
    if (!paramType.isCharacterType()) {
      return -1;
    }
    switch (this.typeCode)
    {
    case 12: 
      if (paramType.typeCode == this.typeCode) {
        return this.precision >= paramType.precision ? 0 : 1;
      }
      if (paramType.typeCode == 1) {
        return this.precision >= paramType.precision ? 0 : -1;
      }
      return -1;
    case 40: 
      if (paramType.typeCode == 40) {
        return this.precision >= paramType.precision ? 0 : 1;
      }
      return -1;
    case 1: 
      return (paramType.typeCode == 1) && (this.precision == paramType.precision) ? 0 : -1;
    }
    return -1;
  }
  
  public Collation getCollation()
  {
    return this.collation;
  }
  
  public Charset getCharacterSet()
  {
    return this.charset;
  }
  
  public boolean isEqualIdentical()
  {
    return this.isEqualIdentical;
  }
  
  public boolean isCaseInsensitive()
  {
    return this.typeCode == 100;
  }
  
  public long position(SessionInterface paramSessionInterface, Object paramObject1, Object paramObject2, Type paramType, long paramLong)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return -1L;
    }
    long l;
    if (paramType.typeCode == 40)
    {
      l = ((ClobData)paramObject2).length(paramSessionInterface);
      if (paramLong + l > ((String)paramObject1).length()) {
        return -1L;
      }
      if (l > 2147483647L) {
        throw Error.error(3459);
      }
      String str = ((ClobData)paramObject2).getSubString(paramSessionInterface, 0L, (int)l);
      return ((String)paramObject1).indexOf(str, (int)paramLong);
    }
    if (paramType.isCharacterType())
    {
      l = ((String)paramObject2).length();
      if (paramLong + l > ((String)paramObject1).length()) {
        return -1L;
      }
      return ((String)paramObject1).indexOf((String)paramObject2, (int)paramLong);
    }
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public Object substring(SessionInterface paramSessionInterface, Object paramObject, long paramLong1, long paramLong2, boolean paramBoolean1, boolean paramBoolean2)
  {
    long l2 = this.typeCode == 40 ? ((ClobData)paramObject).length(paramSessionInterface) : ((String)paramObject).length();
    long l1;
    if (paramBoolean2)
    {
      l1 = l2;
      if (paramLong2 > l2)
      {
        paramLong1 = 0L;
        paramLong2 = l2;
      }
      else
      {
        paramLong1 = l2 - paramLong2;
      }
    }
    else if (paramBoolean1)
    {
      l1 = paramLong1 + paramLong2;
    }
    else
    {
      l1 = l2 > paramLong1 ? l2 : paramLong1;
    }
    if (l1 < paramLong1) {
      throw Error.error(3431);
    }
    if ((paramLong1 > l1) || (l1 < 0L))
    {
      paramLong1 = 0L;
      l1 = 0L;
    }
    if (paramLong1 < 0L) {
      paramLong1 = 0L;
    }
    if (l1 > l2) {
      l1 = l2;
    }
    paramLong2 = l1 - paramLong1;
    if ((paramObject instanceof String)) {
      return ((String)paramObject).substring((int)paramLong1, (int)(paramLong1 + paramLong2));
    }
    if ((paramObject instanceof ClobData))
    {
      ClobDataID localClobDataID = paramSessionInterface.createClob(paramLong2);
      if (paramLong2 > 2147483647L) {
        throw Error.error(3401);
      }
      String str = ((ClobData)paramObject).getSubString(paramSessionInterface, paramLong1, (int)paramLong2);
      localClobDataID.setString(paramSessionInterface, 0L, str);
      return localClobDataID;
    }
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public Object upper(Session paramSession, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if (this.typeCode == 40)
    {
      String str = ((ClobData)paramObject).getSubString(paramSession, 0L, (int)((ClobData)paramObject).length(paramSession));
      str = this.collation.toUpperCase(str);
      ClobDataID localClobDataID = paramSession.createClob(str.length());
      localClobDataID.setString(paramSession, 0L, str);
      return localClobDataID;
    }
    return this.collation.toUpperCase((String)paramObject);
  }
  
  public Object lower(Session paramSession, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if (this.typeCode == 40)
    {
      String str = ((ClobData)paramObject).getSubString(paramSession, 0L, (int)((ClobData)paramObject).length(paramSession));
      str = this.collation.toLowerCase(str);
      ClobDataID localClobDataID = paramSession.createClob(str.length());
      localClobDataID.setString(paramSession, 0L, str);
      return localClobDataID;
    }
    return this.collation.toLowerCase((String)paramObject);
  }
  
  public Object trim(SessionInterface paramSessionInterface, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramObject == null) {
      return null;
    }
    String str;
    if (this.typeCode == 40)
    {
      long l = ((ClobData)paramObject).length(paramSessionInterface);
      if (l > 2147483647L) {
        throw Error.error(3459);
      }
      str = ((ClobData)paramObject).getSubString(paramSessionInterface, 0L, (int)l);
    }
    else
    {
      str = (String)paramObject;
    }
    int i = str.length();
    if (paramBoolean2)
    {
      i--;
      while ((i >= 0) && (str.charAt(i) == paramInt)) {
        i--;
      }
      i++;
    }
    int j = 0;
    if (paramBoolean1) {
      while ((j < i) && (str.charAt(j) == paramInt)) {
        j++;
      }
    }
    if ((j != 0) || (i != str.length())) {
      str = str.substring(j, i);
    }
    if (this.typeCode == 40)
    {
      ClobDataID localClobDataID = paramSessionInterface.createClob(str.length());
      localClobDataID.setString(paramSessionInterface, 0L, str);
      return localClobDataID;
    }
    return str;
  }
  
  public Object overlay(SessionInterface paramSessionInterface, Object paramObject1, Object paramObject2, long paramLong1, long paramLong2, boolean paramBoolean)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    if (!paramBoolean) {
      paramLong2 = this.typeCode == 40 ? ((ClobData)paramObject2).length(paramSessionInterface) : ((String)paramObject2).length();
    }
    Object localObject = concat(null, substring(paramSessionInterface, paramObject1, 0L, paramLong1, true, false), paramObject2);
    return concat(null, localObject, substring(paramSessionInterface, paramObject1, paramLong1 + paramLong2, 0L, false, false));
  }
  
  public Object concat(Session paramSession, Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramObject2 == null)) {
      return null;
    }
    String str1;
    if ((paramObject1 instanceof ClobData)) {
      str1 = ((ClobData)paramObject1).getSubString(paramSession, 0L, (int)((ClobData)paramObject1).length(paramSession));
    } else {
      str1 = (String)paramObject1;
    }
    String str2;
    if ((paramObject2 instanceof ClobData)) {
      str2 = ((ClobData)paramObject2).getSubString(paramSession, 0L, (int)((ClobData)paramObject2).length(paramSession));
    } else {
      str2 = (String)paramObject2;
    }
    if (this.typeCode == 40)
    {
      ClobDataID localClobDataID = paramSession.createClob(str1.length() + str2.length());
      localClobDataID.setString(paramSession, 0L, str1);
      localClobDataID.setString(paramSession, str1.length(), str2);
      return localClobDataID;
    }
    return str1 + str2;
  }
  
  public long size(SessionInterface paramSessionInterface, Object paramObject)
  {
    if (this.typeCode == 40) {
      return ((ClobData)paramObject).length(paramSessionInterface);
    }
    return ((String)paramObject).length();
  }
  
  public Boolean match(Session paramSession, String paramString, String[] paramArrayOfString)
  {
    if ((paramString == null) || (paramArrayOfString == null)) {
      return null;
    }
    String str = null;
    int i = 0;
    int j = 1;
    for (int k = 0; k < paramArrayOfString.length; k++)
    {
      if (paramArrayOfString[k] == null)
      {
        i++;
        j = 1;
      }
      else if (paramArrayOfString[k].length() == 0)
      {
        j = 0;
      }
      if (j != 0)
      {
        if (i + paramArrayOfString[k].length() > paramString.length()) {
          return Boolean.FALSE;
        }
        str = paramString.substring(i, i + paramArrayOfString[k].length());
        if (this.collation.compare(str, paramArrayOfString[k]) != 0) {
          return Boolean.FALSE;
        }
        i += paramArrayOfString[k].length();
      }
      else
      {
        int m = paramString.indexOf(paramArrayOfString[k], i);
        if (m < 0) {
          return Boolean.FALSE;
        }
        i = m + paramArrayOfString[k].length();
        j = 1;
      }
    }
    return Boolean.TRUE;
  }
  
  public Type getCharacterType(long paramLong)
  {
    if (paramLong == this.precision) {
      return this;
    }
    return new CharacterType(this.collation, this.typeCode, paramLong);
  }
  
  public static int getRightTrimSise(String paramString, char paramChar)
  {
    int i = paramString.length();
    i--;
    while ((i >= 0) && (paramString.charAt(i) == paramChar)) {
      i--;
    }
    i++;
    return i;
  }
  
  public static CharacterType getCharacterType(int paramInt, long paramLong)
  {
    switch (paramInt)
    {
    case 1: 
      if (paramLong < 32L) {
        return charArray[((int)paramLong)];
      }
    case 12: 
    case 100: 
      return new CharacterType(paramInt, (int)paramLong);
    case 40: 
      return new ClobType(paramLong);
    }
    throw Error.runtimeError(201, "CharacterType");
  }
  
  public static CharacterType getCharacterType(int paramInt, long paramLong, Collation paramCollation)
  {
    if (paramCollation == null) {
      paramCollation = Collation.defaultCollation;
    }
    switch (paramInt)
    {
    case 1: 
    case 12: 
    case 100: 
      return new CharacterType(paramCollation, paramInt, (int)paramLong);
    case 40: 
      ClobType localClobType = new ClobType(paramLong);
      localClobType.collation = paramCollation;
      return localClobType;
    }
    throw Error.runtimeError(201, "CharacterType");
  }
  
  static
  {
    for (int i = 0; i < charArray.length; i++) {
      charArray[i] = new CharacterType(1, i);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.types.CharacterType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */