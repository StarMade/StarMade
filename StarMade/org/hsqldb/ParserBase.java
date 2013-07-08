package org.hsqldb;

import java.math.BigDecimal;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.IntKeyIntValueHashMap;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

public class ParserBase
{
  private Scanner scanner;
  protected Token token;
  protected boolean isRecording;
  protected HsqlArrayList recordedStatement;
  private final Token dummyToken = new Token();
  protected boolean isCheckOrTriggerCondition;
  protected boolean isSchemaDefinition;
  protected int parsePosition;
  static final BigDecimal LONG_MAX_VALUE_INCREMENT = BigDecimal.valueOf(9223372036854775807L).add(BigDecimal.valueOf(1L));
  private static final IntKeyIntValueHashMap expressionTypeMap = new IntKeyIntValueHashMap(37);
  
  ParserBase(Scanner paramScanner)
  {
    this.scanner = paramScanner;
    this.token = this.scanner.token;
  }
  
  public Scanner getScanner()
  {
    return this.scanner;
  }
  
  public int getParsePosition()
  {
    return this.parsePosition;
  }
  
  public void setParsePosition(int paramInt)
  {
    this.parsePosition = paramInt;
  }
  
  void reset(String paramString)
  {
    this.scanner.reset(paramString);
    this.parsePosition = 0;
    this.isCheckOrTriggerCondition = false;
    this.isSchemaDefinition = false;
    this.isRecording = false;
    this.recordedStatement = null;
  }
  
  int getPosition()
  {
    return this.scanner.getTokenPosition();
  }
  
  void rewind(int paramInt)
  {
    if (paramInt == this.scanner.getTokenPosition()) {
      return;
    }
    this.scanner.position(paramInt);
    if (this.isRecording)
    {
      for (int i = this.recordedStatement.size() - 1; i >= 0; i--)
      {
        Token localToken = (Token)this.recordedStatement.get(i);
        if (localToken.position < paramInt) {
          break;
        }
      }
      this.recordedStatement.setSize(i + 1);
    }
    read();
  }
  
  String getLastPart()
  {
    return this.scanner.getPart(this.parsePosition, this.scanner.getTokenPosition());
  }
  
  String getLastPart(int paramInt)
  {
    return this.scanner.getPart(paramInt, this.scanner.getTokenPosition());
  }
  
  String getLastPartAndCurrent(int paramInt)
  {
    return this.scanner.getPart(paramInt, this.scanner.getPosition());
  }
  
  String getStatement(int paramInt, short[] paramArrayOfShort)
  {
    while ((this.token.tokenType != 791) && (this.token.tokenType != 848) && (ArrayUtil.find(paramArrayOfShort, this.token.tokenType) == -1)) {
      read();
    }
    String str = this.scanner.getPart(paramInt, this.scanner.getTokenPosition());
    return str;
  }
  
  String getStatementForRoutine(int paramInt, short[] paramArrayOfShort)
  {
    int i = 0;
    int j = -1;
    int k = -1;
    for (;;)
    {
      if (this.token.tokenType == 791)
      {
        k = this.scanner.getTokenPosition();
        j = i;
      }
      else if (this.token.tokenType == 848)
      {
        if ((j > 0) && (j == i - 1)) {
          rewind(k);
        }
      }
      else
      {
        if (ArrayUtil.find(paramArrayOfShort, this.token.tokenType) != -1) {
          break;
        }
      }
      read();
      i++;
    }
    String str = this.scanner.getPart(paramInt, this.scanner.getTokenPosition());
    return str;
  }
  
  void startRecording()
  {
    this.recordedStatement = new HsqlArrayList();
    this.recordedStatement.add(this.token.duplicate());
    this.isRecording = true;
  }
  
  Token getRecordedToken()
  {
    if (this.isRecording) {
      return (Token)this.recordedStatement.get(this.recordedStatement.size() - 1);
    }
    return this.dummyToken;
  }
  
  Token[] getRecordedStatement()
  {
    this.isRecording = false;
    this.recordedStatement.remove(this.recordedStatement.size() - 1);
    Token[] arrayOfToken = new Token[this.recordedStatement.size()];
    this.recordedStatement.toArray(arrayOfToken);
    this.recordedStatement = null;
    return arrayOfToken;
  }
  
  void read()
  {
    this.scanner.scanNext();
    if (this.token.isMalformed)
    {
      int i = -1;
      switch (this.token.tokenType)
      {
      case 856: 
        i = 5587;
        break;
      case 855: 
        i = 5588;
        break;
      case 857: 
        i = 5586;
        break;
      case 853: 
        i = 5584;
        break;
      case -1: 
        i = 5582;
        break;
      case 854: 
        i = 5585;
        break;
      case 858: 
        i = 5589;
        break;
      case 859: 
        i = 5583;
      }
      throw Error.error(i, this.token.getFullString());
    }
    if (this.isRecording)
    {
      Token localToken = this.token.duplicate();
      localToken.position = this.scanner.getTokenPosition();
      this.recordedStatement.add(localToken);
    }
  }
  
  boolean isReservedKey()
  {
    return this.scanner.token.isReservedIdentifier;
  }
  
  boolean isCoreReservedKey()
  {
    return this.scanner.token.isCoreReservedIdentifier;
  }
  
  boolean isNonReservedIdentifier()
  {
    return (!this.scanner.token.isReservedIdentifier) && ((this.scanner.token.isUndelimitedIdentifier) || (this.scanner.token.isDelimitedIdentifier));
  }
  
  void checkIsNonReservedIdentifier()
  {
    if (!isNonReservedIdentifier()) {
      throw unexpectedToken();
    }
  }
  
  boolean isNonCoreReservedIdentifier()
  {
    return (!this.scanner.token.isCoreReservedIdentifier) && ((this.scanner.token.isUndelimitedIdentifier) || (this.scanner.token.isDelimitedIdentifier));
  }
  
  void checkIsNonCoreReservedIdentifier()
  {
    if (!isNonCoreReservedIdentifier()) {
      throw unexpectedToken();
    }
  }
  
  void checkIsIrregularCharInIdentifier()
  {
    if (this.scanner.token.hasIrregularChar) {
      throw unexpectedToken();
    }
  }
  
  boolean isIdentifier()
  {
    return (this.scanner.token.isUndelimitedIdentifier) || (this.scanner.token.isDelimitedIdentifier);
  }
  
  void checkIsIdentifier()
  {
    if (!isIdentifier()) {
      throw unexpectedToken();
    }
  }
  
  boolean isDelimitedIdentifier()
  {
    return this.scanner.token.isDelimitedIdentifier;
  }
  
  void checkIsDelimitedIdentifier()
  {
    if (this.token.tokenType != 847) {
      throw Error.error(5569);
    }
  }
  
  void checkIsNotQuoted()
  {
    if (this.token.tokenType == 847) {
      throw unexpectedToken();
    }
  }
  
  void checkIsValue()
  {
    if (this.token.tokenType != 845) {
      throw unexpectedToken();
    }
  }
  
  void checkIsValue(int paramInt)
  {
    if ((this.token.tokenType != 845) || (this.token.dataType.typeCode != paramInt)) {
      throw unexpectedToken();
    }
  }
  
  void checkIsThis(int paramInt)
  {
    if (this.token.tokenType != paramInt)
    {
      String str = Tokens.getKeyword(paramInt);
      throw unexpectedTokenRequire(str);
    }
  }
  
  boolean isUndelimitedSimpleName()
  {
    return (this.token.isUndelimitedIdentifier) && (this.token.namePrefix == null);
  }
  
  boolean isDelimitedSimpleName()
  {
    return (this.token.isDelimitedIdentifier) && (this.token.namePrefix == null);
  }
  
  boolean isSimpleName()
  {
    return (isNonCoreReservedIdentifier()) && (this.token.namePrefix == null);
  }
  
  void checkIsSimpleName()
  {
    if (!isSimpleName()) {
      throw unexpectedToken();
    }
  }
  
  void readUnquotedIdentifier(String paramString)
  {
    checkIsSimpleName();
    if (!this.token.tokenString.equals(paramString)) {
      throw unexpectedToken();
    }
    read();
  }
  
  String readQuotedString()
  {
    checkIsValue();
    if (this.token.dataType.typeCode != 1) {
      throw Error.error(5563);
    }
    String str = this.token.tokenString;
    read();
    return str;
  }
  
  void readThis(int paramInt)
  {
    if (this.token.tokenType != paramInt)
    {
      String str = Tokens.getKeyword(paramInt);
      throw unexpectedTokenRequire(str);
    }
    read();
  }
  
  boolean readIfThis(int paramInt)
  {
    if (this.token.tokenType == paramInt)
    {
      read();
      return true;
    }
    return false;
  }
  
  Integer readIntegerObject()
  {
    int i = readInteger();
    return ValuePool.getInt(i);
  }
  
  int readInteger()
  {
    int i = 0;
    if (this.token.tokenType == 784)
    {
      i = 1;
      read();
    }
    checkIsValue();
    if ((i != 0) && (this.token.dataType.typeCode == 25) && (((Number)this.token.tokenValue).longValue() == 2147483648L))
    {
      read();
      return -2147483648;
    }
    if (this.token.dataType.typeCode != 4) {
      throw Error.error(5563);
    }
    int j = ((Number)this.token.tokenValue).intValue();
    if (i != 0) {
      j = -j;
    }
    read();
    return j;
  }
  
  long readBigint()
  {
    int i = 0;
    if (this.token.tokenType == 784)
    {
      i = 1;
      read();
    }
    checkIsValue();
    if ((i != 0) && (this.token.dataType.typeCode == 2) && (LONG_MAX_VALUE_INCREMENT.equals(this.token.tokenValue)))
    {
      read();
      return -9223372036854775808L;
    }
    if ((this.token.dataType.typeCode != 4) && (this.token.dataType.typeCode != 25)) {
      throw Error.error(5563);
    }
    long l = ((Number)this.token.tokenValue).longValue();
    if (i != 0) {
      l = -l;
    }
    read();
    return l;
  }
  
  Expression readDateTimeIntervalLiteral(Session paramSession)
  {
    int i = getPosition();
    String str;
    Object localObject1;
    Object localObject2;
    switch (this.token.tokenType)
    {
    case 72: 
      read();
      if ((this.token.tokenType == 845) && (this.token.dataType.typeCode == 1))
      {
        str = this.token.tokenString;
        read();
        localObject1 = this.scanner.newDate(str);
        return new ExpressionValue(localObject1, Type.SQL_DATE);
      }
      break;
    case 281: 
      read();
      if ((this.token.tokenType == 845) && (this.token.dataType.typeCode == 1))
      {
        str = this.token.tokenString;
        read();
        localObject1 = this.scanner.newTime(str);
        localObject2 = this.scanner.dateTimeType;
        return new ExpressionValue(localObject1, (Type)localObject2);
      }
      break;
    case 282: 
      read();
      if ((this.token.tokenType == 845) && (this.token.dataType.typeCode == 1))
      {
        str = this.token.tokenString;
        read();
        localObject1 = this.scanner.newTimestamp(str);
        localObject2 = this.scanner.dateTimeType;
        return new ExpressionValue(localObject1, (Type)localObject2);
      }
      break;
    case 140: 
      int j = 0;
      read();
      if (this.token.tokenType == 784)
      {
        read();
        j = 1;
      }
      else if (this.token.tokenType == 787)
      {
        read();
      }
      if (this.token.tokenType == 845)
      {
        localObject1 = this.token.tokenString;
        if ((this.token.dataType.typeCode == 4) || (this.token.dataType.typeCode == 1))
        {
          read();
          localObject2 = readIntervalType(false);
          Object localObject3 = this.scanner.newInterval((String)localObject1, (IntervalType)localObject2);
          localObject2 = (IntervalType)this.scanner.dateTimeType;
          if (j != 0) {
            localObject3 = ((IntervalType)localObject2).negate(localObject3);
          }
          return new ExpressionValue(localObject3, (Type)localObject2);
        }
      }
      break;
    default: 
      throw Error.runtimeError(201, "ParserBase");
    }
    rewind(i);
    return null;
  }
  
  IntervalType readIntervalType(boolean paramBoolean)
  {
    int i = paramBoolean ? 9 : -1;
    int j = -1;
    int m;
    int k = m = this.token.tokenType;
    read();
    if (this.token.tokenType == 786)
    {
      read();
      i = readInteger();
      if (i <= 0) {
        throw Error.error(5592);
      }
      if (this.token.tokenType == 774)
      {
        if (k != 250) {
          throw unexpectedToken();
        }
        read();
        j = readInteger();
        if (j < 0) {
          throw Error.error(5592);
        }
      }
      readThis(772);
    }
    if (this.token.tokenType == 285)
    {
      read();
      m = this.token.tokenType;
      read();
    }
    if (this.token.tokenType == 786)
    {
      if ((m != 250) || (m == k)) {
        throw unexpectedToken();
      }
      read();
      j = readInteger();
      if (j < 0) {
        throw Error.error(5592);
      }
      readThis(772);
    }
    int n = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_CODES, k);
    int i1 = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_CODES, m);
    return IntervalType.getIntervalType(n, i1, i, j);
  }
  
  static int getExpressionType(int paramInt)
  {
    int i = expressionTypeMap.get(paramInt, -1);
    if (i == -1) {
      throw Error.runtimeError(201, "ParserBase");
    }
    return i;
  }
  
  HsqlException unexpectedToken(String paramString)
  {
    return Error.parseError(5581, paramString, this.scanner.getLineNumber());
  }
  
  HsqlException unexpectedTokenRequire(String paramString)
  {
    if (this.token.tokenType == 848) {
      return Error.parseError(5590, 1, this.scanner.getLineNumber(), new Object[] { "", paramString });
    }
    String str;
    if (this.token.charsetSchema != null) {
      str = this.token.charsetSchema;
    } else if (this.token.charsetName != null) {
      str = this.token.charsetName;
    } else if (this.token.namePrePrefix != null) {
      str = this.token.namePrePrefix;
    } else if (this.token.namePrefix != null) {
      str = this.token.namePrefix;
    } else {
      str = this.token.tokenString;
    }
    return Error.parseError(5581, 1, this.scanner.getLineNumber(), new Object[] { str, paramString });
  }
  
  HsqlException unexpectedToken()
  {
    if (this.token.tokenType == 848) {
      return Error.parseError(5590, null, this.scanner.getLineNumber());
    }
    String str;
    if (this.token.charsetSchema != null) {
      str = this.token.charsetSchema;
    } else if (this.token.charsetName != null) {
      str = this.token.charsetName;
    } else if (this.token.namePrePrefix != null) {
      str = this.token.namePrePrefix;
    } else if (this.token.namePrefix != null) {
      str = this.token.namePrefix;
    } else {
      str = this.token.tokenString;
    }
    return Error.parseError(5581, str, this.scanner.getLineNumber());
  }
  
  HsqlException tooManyIdentifiers()
  {
    String str;
    if (this.token.namePrePrePrefix != null) {
      str = this.token.namePrePrePrefix;
    } else if (this.token.namePrePrefix != null) {
      str = this.token.namePrePrefix;
    } else if (this.token.namePrefix != null) {
      str = this.token.namePrefix;
    } else {
      str = this.token.tokenString;
    }
    return Error.parseError(5551, str, this.scanner.getLineNumber());
  }
  
  HsqlException unsupportedFeature()
  {
    return Error.error(1551, this.token.tokenString);
  }
  
  HsqlException unsupportedFeature(String paramString)
  {
    return Error.error(1551, paramString);
  }
  
  public Number convertToNumber(String paramString, NumberType paramNumberType)
  {
    return this.scanner.convertToNumber(paramString, paramNumberType);
  }
  
  static
  {
    expressionTypeMap.put(396, 41);
    expressionTypeMap.put(779, 43);
    expressionTypeMap.put(782, 44);
    expressionTypeMap.put(780, 42);
    expressionTypeMap.put(783, 45);
    expressionTypeMap.put(785, 46);
    expressionTypeMap.put(52, 71);
    expressionTypeMap.put(163, 74);
    expressionTypeMap.put(168, 73);
    expressionTypeMap.put(274, 72);
    expressionTypeMap.put(16, 75);
    expressionTypeMap.put(97, 76);
    expressionTypeMap.put(6, 77);
    expressionTypeMap.put(258, 77);
    expressionTypeMap.put(269, 78);
    expressionTypeMap.put(270, 79);
    expressionTypeMap.put(309, 80);
    expressionTypeMap.put(310, 81);
    expressionTypeMap.put(9, 82);
    expressionTypeMap.put(684, 83);
    expressionTypeMap.put(606, 85);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ParserBase
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */