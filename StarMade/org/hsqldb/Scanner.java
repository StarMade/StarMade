package org.hsqldb;

import java.math.BigDecimal;
import java.util.Locale;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.CharArrayWriter;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.store.BitMap;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BinaryType;
import org.hsqldb.types.BitType;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.DTIType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalMonthData;
import org.hsqldb.types.IntervalSecondData;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.TimeData;
import org.hsqldb.types.TimestampData;
import org.hsqldb.types.Type;

public class Scanner
{
  static final char[] specials = { '"', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '\\', ':', ';', '<', '=', '>', '?', '[', ']', '^', '_', '|', '{', '}' };
  static final String[] multi = { "??(", "??)", "<>", ">=", "<=", "||", "->", "::", "..", "--", "/*", "*/" };
  static final char[] whitespace = { '\t', '\n', '\013', '\f', '\r', ' ', '', ' ', ' ', ' ', '᠎', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '　', ' ', ' ' };
  static final OrderedIntHashSet whiteSpaceSet = new OrderedIntHashSet(32);
  String sqlString;
  int currentPosition;
  int tokenPosition;
  int limit;
  Token token = new Token();
  boolean nullAndBooleanAsValue;
  private boolean hasNonSpaceSeparator;
  private int eolPosition;
  private int lineNumber;
  private int eolCode;
  private static final int maxPooledStringLength = ValuePool.getMaxStringLength();
  char[] charBuffer = new char[256];
  CharArrayWriter charWriter = new CharArrayWriter(this.charBuffer);
  byte[] byteBuffer = new byte[256];
  HsqlByteArrayOutputStream byteOutputStream = new HsqlByteArrayOutputStream(this.byteBuffer);
  private String intervalString;
  private int intervalPosition;
  private int intervalPrecision;
  private int fractionPrecision;
  Type dateTimeType;

  public Scanner()
  {
  }

  Scanner(String paramString)
  {
    reset(paramString);
  }

  public void reset(String paramString)
  {
    this.sqlString = paramString;
    this.currentPosition = 0;
    this.tokenPosition = 0;
    this.limit = this.sqlString.length();
    this.hasNonSpaceSeparator = false;
    this.eolPosition = -1;
    this.lineNumber = 1;
    this.token.reset();
    this.token.tokenType = 849;
  }

  void resetState()
  {
    this.tokenPosition = this.currentPosition;
    this.token.reset();
  }

  public void setNullAndBooleanAsValue()
  {
    this.nullAndBooleanAsValue = true;
  }

  public void scanNext()
  {
    if (this.currentPosition == this.limit)
    {
      resetState();
      this.token.tokenType = 848;
      return;
    }
    if ((!scanSeparator()) || (this.currentPosition == this.limit))
    {
      resetState();
      this.token.tokenType = 848;
      return;
    }
    int i = !this.token.isDelimiter ? 1 : 0;
    scanToken();
    if (((i == 0) || (this.token.isDelimiter)) || (this.token.isMalformed))
      this.token.fullString = getPart(this.tokenPosition, this.currentPosition);
  }

  public void scanEnd()
  {
    if (this.currentPosition == this.limit)
    {
      resetState();
      this.token.tokenType = 848;
    }
  }

  public Token getToken()
  {
    return this.token;
  }

  public String getString()
  {
    return this.token.tokenString;
  }

  public int getTokenType()
  {
    return this.token.tokenType;
  }

  public Object getValue()
  {
    return this.token.tokenValue;
  }

  public Type getDataType()
  {
    return this.token.dataType;
  }

  public int getLineNumber()
  {
    return this.lineNumber;
  }

  int getTokenPosition()
  {
    return this.tokenPosition;
  }

  int getPosition()
  {
    return this.tokenPosition;
  }

  void position(int paramInt)
  {
    this.currentPosition = (this.tokenPosition = paramInt);
  }

  String getPart(int paramInt1, int paramInt2)
  {
    return this.sqlString.substring(paramInt1, paramInt2);
  }

  private int charAt(int paramInt)
  {
    if (paramInt >= this.limit)
      return -1;
    return this.sqlString.charAt(paramInt);
  }

  void scanBinaryString()
  {
    this.byteOutputStream.reset(this.byteBuffer);
    do
    {
      scanBinaryStringPart();
      if (this.token.isMalformed)
        return;
    }
    while ((scanSeparator()) && (charAt(this.currentPosition) == 39));
    this.token.tokenValue = new BinaryData(this.byteOutputStream.toByteArray(), false);
    this.byteOutputStream.reset(this.byteBuffer);
  }

  static int getHexValue(int paramInt)
  {
    if ((paramInt >= 48) && (paramInt <= 57))
      paramInt -= 48;
    else if (paramInt > 122)
      paramInt = 16;
    else if (paramInt >= 97)
      paramInt -= 87;
    else if (paramInt > 90)
      paramInt = 16;
    else if (paramInt >= 65)
      paramInt -= 55;
    else
      paramInt = -1;
    return paramInt;
  }

  public void scanBinaryStringWithQuote()
  {
    resetState();
    scanSeparator();
    if (charAt(this.currentPosition) != 39)
    {
      this.token.tokenType = 856;
      this.token.isMalformed = true;
      return;
    }
    scanBinaryString();
  }

  void scanBinaryStringPart()
  {
    int i = 0;
    int j = 1;
    int k = 0;
    for (this.currentPosition += 1; this.currentPosition < this.limit; this.currentPosition += 1)
    {
      int m = this.sqlString.charAt(this.currentPosition);
      if (m != 32)
      {
        if (m == 39)
        {
          i = 1;
          this.currentPosition += 1;
          break;
        }
        m = getHexValue(m);
        if (m == -1)
        {
          this.token.tokenType = 856;
          this.token.isMalformed = true;
          return;
        }
        if (j != 0)
        {
          k = (byte)(m << 4);
          j = 0;
        }
        else
        {
          k = (byte)(k + (byte)m);
          this.byteOutputStream.writeByte(k);
          j = 1;
        }
      }
    }
    if (j == 0)
    {
      this.token.tokenType = 856;
      this.token.isMalformed = true;
      return;
    }
    if (i == 0)
    {
      this.token.tokenType = 856;
      this.token.isMalformed = true;
      return;
    }
  }

  void scanBitString()
  {
    BitMap localBitMap = new BitMap(32);
    do
    {
      scanBitStringPart(localBitMap);
      if (this.token.isMalformed)
        return;
    }
    while ((scanSeparator()) && (charAt(this.currentPosition) == 39));
    this.token.tokenValue = BinaryData.getBitData(localBitMap.getBytes(), localBitMap.size());
  }

  public void scanBitStringWithQuote()
  {
    resetState();
    scanSeparator();
    if (charAt(this.currentPosition) != 39)
    {
      this.token.tokenType = 855;
      this.token.isMalformed = true;
      return;
    }
    scanBitString();
  }

  void scanBitStringPart(BitMap paramBitMap)
  {
    int i = 0;
    int j = paramBitMap.size();
    for (this.currentPosition += 1; this.currentPosition < this.limit; this.currentPosition += 1)
    {
      int k = this.sqlString.charAt(this.currentPosition);
      if (k != 32)
      {
        if (k == 39)
        {
          i = 1;
          this.currentPosition += 1;
          break;
        }
        if (k == 48)
        {
          j++;
        }
        else if (k == 49)
        {
          paramBitMap.set(j);
          j++;
        }
        else
        {
          this.token.tokenType = 855;
          this.token.isMalformed = true;
          return;
        }
      }
    }
    if (i == 0)
    {
      this.token.tokenType = 855;
      this.token.isMalformed = true;
      return;
    }
    paramBitMap.setSize(j);
  }

  void convertUnicodeString(int paramInt)
  {
    this.charWriter.reset(this.charBuffer);
    int i = 0;
    while (true)
    {
      int j = this.token.tokenString.indexOf(paramInt, i);
      if (j < 0)
        j = this.token.tokenString.length();
      this.charWriter.write(this.token.tokenString, i, j - i);
      if (j == this.token.tokenString.length())
        break;
      j++;
      if (j == this.token.tokenString.length())
      {
        this.token.tokenType = 857;
        this.token.isMalformed = true;
        return;
      }
      if (this.token.tokenString.charAt(j) == paramInt)
      {
        this.charWriter.write(paramInt);
        j++;
        i = j;
      }
      else
      {
        if (j > this.token.tokenString.length() - 4)
        {
          this.token.tokenType = 857;
          this.token.isMalformed = true;
          return;
        }
        int k = 4;
        int m = 0;
        int n = 0;
        if (this.token.tokenString.charAt(j) == '+')
        {
          j++;
          if (j > this.token.tokenString.length() - 6)
          {
            this.token.tokenType = 857;
            this.token.isMalformed = true;
            return;
          }
          m = 2;
          k = 8;
        }
        while (m < k)
        {
          int i1 = this.token.tokenString.charAt(i++);
          i1 = getHexValue(i1);
          if (i1 == -1)
          {
            this.token.tokenType = 857;
            this.token.isMalformed = true;
            return;
          }
          n |= i1 << (k - m - 1) * 4;
          m++;
        }
        if (k == 8)
          this.charWriter.write(n >>> 16);
        this.charWriter.write(n & (n & 0xFFFF));
        this.token.tokenValue = this.charWriter.toString();
      }
    }
  }

  public boolean scanSpecialIdentifier(String paramString)
  {
    int i = paramString.length();
    if (this.limit - this.currentPosition < i)
      return false;
    for (int j = 0; j < i; j++)
    {
      int k = paramString.charAt(j);
      if ((k != this.sqlString.charAt(this.currentPosition + j)) && (k != Character.toUpperCase(this.sqlString.charAt(this.currentPosition + j))))
        return false;
    }
    this.currentPosition += i;
    return true;
  }

  private int scanEscapeDefinition()
  {
    int i = charAt(this.currentPosition);
    if (i == 39)
    {
      this.currentPosition += 1;
      if (!scanWhitespace())
      {
        i = charAt(this.currentPosition);
        if ((getHexValue(i) == -1) && (i != 43) && (i != 39) && (i != 34))
        {
          int j = i;
          this.currentPosition += 1;
          i = charAt(this.currentPosition);
          if (i == 39)
          {
            this.currentPosition += 1;
            return j;
          }
        }
      }
    }
    return -1;
  }

  private void scanUnicodeString()
  {
    int i = 92;
    scanCharacterString();
    scanSeparator();
    int j = charAt(this.currentPosition);
    if (((j == 117) || (j == 85)) && (scanSpecialIdentifier("UESCAPE")))
    {
      scanSeparator();
      i = scanEscapeDefinition();
      if (i == -1)
      {
        this.token.tokenType = 860;
        this.token.isMalformed = true;
        return;
      }
    }
    convertUnicodeString(i);
  }

  private boolean scanUnicodeIdentifier()
  {
    int i = 92;
    scanStringPart('"');
    if (this.token.isMalformed)
      return false;
    this.token.tokenString = this.charWriter.toString();
    int j = charAt(this.currentPosition);
    if (((j == 117) || (j == 85)) && (scanSpecialIdentifier("UESCAPE")))
    {
      scanSeparator();
      i = scanEscapeDefinition();
      if (i == -1)
      {
        this.token.tokenType = 860;
        this.token.isMalformed = true;
        return false;
      }
    }
    convertUnicodeString(i);
    return !this.token.isMalformed;
  }

  boolean shiftPrefixes()
  {
    if (this.token.namePrePrePrefix != null)
      return false;
    this.token.namePrePrePrefix = this.token.namePrePrefix;
    this.token.isDelimitedPrePrePrefix = this.token.isDelimitedPrePrefix;
    this.token.namePrePrefix = this.token.namePrefix;
    this.token.isDelimitedPrePrefix = this.token.isDelimitedPrefix;
    this.token.namePrefix = this.token.tokenString;
    this.token.isDelimitedPrefix = (this.token.tokenType == 847);
    return true;
  }

  private void scanIdentifierChain()
  {
    int i = charAt(this.currentPosition);
    switch (i)
    {
    case 34:
      this.charWriter.reset(this.charBuffer);
      scanStringPart('"');
      if (this.token.isMalformed)
        return;
      this.token.tokenType = 847;
      this.token.tokenString = this.charWriter.toString();
      this.token.isDelimiter = true;
      break;
    case 85:
    case 117:
      if ((charAt(this.currentPosition + 1) == 38) && (charAt(this.currentPosition + 1) == 34))
      {
        this.currentPosition += 3;
        bool = scanUnicodeIdentifier();
        if (!bool)
          return;
        this.token.tokenType = 847;
        this.token.isDelimiter = false;
      }
      break;
    }
    boolean bool = scanUndelimitedIdentifier();
    if (!bool)
      return;
    this.token.tokenType = 846;
    this.token.isDelimiter = false;
    bool = scanWhitespace();
    i = charAt(this.currentPosition);
    if (i == 46)
    {
      if (bool)
      {
        int j = charAt(this.currentPosition + 1);
        if ((j >= 48) && (j <= 57))
          return;
      }
      this.currentPosition += 1;
      scanWhitespace();
      i = charAt(this.currentPosition);
      if (i == 42)
      {
        this.currentPosition += 1;
        shiftPrefixes();
        this.token.tokenString = "*";
        this.token.tokenType = 771;
      }
      else
      {
        shiftPrefixes();
        scanIdentifierChain();
      }
    }
  }

  public boolean scanUndelimitedIdentifier()
  {
    if (this.currentPosition == this.limit)
      return false;
    char c1 = this.sqlString.charAt(this.currentPosition);
    int i = (c1 == '_') || (c1 == '$') ? 1 : 0;
    if ((i == 0) && (!Character.isLetter(c1)))
    {
      this.token.tokenString = Character.toString(c1);
      this.token.tokenType = -1;
      this.token.isMalformed = true;
      return false;
    }
    for (int j = this.currentPosition + 1; j < this.limit; j++)
    {
      char c2 = this.sqlString.charAt(j);
      if (c2 == '$')
        i = 1;
      else if (c2 != '_')
        if (!Character.isLetterOrDigit(c2))
          break;
    }
    this.token.tokenString = this.sqlString.substring(this.currentPosition, j).toUpperCase(Locale.ENGLISH);
    this.currentPosition = j;
    if (this.nullAndBooleanAsValue)
    {
      int k = this.currentPosition - this.tokenPosition;
      if ((k == 4) || (k == 5))
        switch (c1)
        {
        case 'T':
        case 't':
          if ("TRUE".equals(this.token.tokenString))
          {
            this.token.tokenString = "TRUE";
            this.token.tokenType = 845;
            this.token.tokenValue = Boolean.TRUE;
            this.token.dataType = Type.SQL_BOOLEAN;
            return false;
          }
          break;
        case 'F':
        case 'f':
          if ("FALSE".equals(this.token.tokenString))
          {
            this.token.tokenString = "FALSE";
            this.token.tokenType = 845;
            this.token.tokenValue = Boolean.FALSE;
            this.token.dataType = Type.SQL_BOOLEAN;
            return false;
          }
          break;
        case 'N':
        case 'n':
          if ("NULL".equals(this.token.tokenString))
          {
            this.token.tokenString = "NULL";
            this.token.tokenType = 845;
            this.token.tokenValue = null;
            return false;
          }
          break;
        }
    }
    if (i != 0)
      this.token.hasIrregularChar = true;
    return true;
  }

  void scanNumber()
  {
    int j = 0;
    int k = 0;
    int m = -1;
    this.token.tokenType = 845;
    this.token.dataType = Type.SQL_INTEGER;
    int n = this.currentPosition;
    while (this.currentPosition < this.limit)
    {
      int i1 = 0;
      int i = charAt(this.currentPosition);
      switch (i)
      {
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
        j = 1;
        break;
      case 46:
        this.token.dataType = Type.SQL_NUMERIC;
        if ((k != 0) || (m != -1))
        {
          this.token.tokenString = this.sqlString.substring(n, this.currentPosition + 1);
          this.token.tokenType = 854;
          this.token.isMalformed = true;
          return;
        }
        k = 1;
        break;
      case 69:
      case 101:
        this.token.dataType = Type.SQL_DOUBLE;
        if ((m != -1) || (j == 0))
        {
          this.token.tokenString = this.sqlString.substring(n, this.currentPosition + 1);
          this.token.tokenType = 854;
          this.token.isMalformed = true;
          return;
        }
        k = 1;
        m = this.currentPosition;
        break;
      case 43:
      case 45:
        if (m != this.currentPosition - 1)
          i1 = 1;
        break;
      case 71:
      case 75:
      case 77:
      case 80:
      case 84:
      case 103:
      case 107:
      case 109:
      case 112:
      case 116:
        if ((j == 0) || (k != 0))
        {
          this.token.tokenType = 854;
          this.token.isMalformed = true;
          return;
        }
        String str = Character.toString((char)i).toUpperCase(Locale.ENGLISH);
        this.token.lobMultiplierType = Tokens.getNonKeywordID(str, 854);
        if (this.token.lobMultiplierType == 854)
        {
          this.token.tokenType = 854;
          this.token.isMalformed = true;
          return;
        }
        try
        {
          this.token.tokenValue = ValuePool.getInt(Integer.parseInt(this.sqlString.substring(n, this.currentPosition)));
          this.token.tokenType = 852;
          this.currentPosition += 1;
          this.token.fullString = getPart(this.tokenPosition, this.currentPosition);
        }
        catch (NumberFormatException localNumberFormatException)
        {
          this.token.tokenType = 854;
          this.token.isMalformed = true;
        }
        return;
      case 44:
      case 47:
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
      case 70:
      case 72:
      case 73:
      case 74:
      case 76:
      case 78:
      case 79:
      case 81:
      case 82:
      case 83:
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
      case 98:
      case 99:
      case 100:
      case 102:
      case 104:
      case 105:
      case 106:
      case 108:
      case 110:
      case 111:
      case 113:
      case 114:
      case 115:
      default:
        i1 = 1;
      }
      if (i1 != 0)
        break;
      this.currentPosition += 1;
    }
    this.token.tokenString = this.sqlString.substring(n, this.currentPosition);
    switch (this.token.dataType.typeCode)
    {
    case 4:
      if (this.token.tokenString.length() < 11)
        try
        {
          this.token.tokenValue = ValuePool.getInt(Integer.parseInt(this.token.tokenString));
          return;
        }
        catch (Exception localException1)
        {
        }
      if (this.token.tokenString.length() < 20)
        try
        {
          this.token.dataType = Type.SQL_BIGINT;
          this.token.tokenValue = ValuePool.getLong(Long.parseLong(this.token.tokenString));
          return;
        }
        catch (Exception localException2)
        {
        }
      this.token.dataType = Type.SQL_NUMERIC;
    case 2:
      try
      {
        BigDecimal localBigDecimal = new BigDecimal(this.token.tokenString);
        this.token.tokenValue = localBigDecimal;
        this.token.dataType = NumberType.getNumberType(2, JavaSystem.precision(localBigDecimal), localBigDecimal.scale());
      }
      catch (Exception localException3)
      {
        this.token.tokenType = 854;
        this.token.isMalformed = true;
        return;
      }
      return;
    case 8:
      try
      {
        double d = JavaSystem.parseDouble(this.token.tokenString);
        long l = Double.doubleToLongBits(d);
        this.token.tokenValue = ValuePool.getDouble(l);
      }
      catch (Exception localException4)
      {
        this.token.tokenType = 854;
        this.token.isMalformed = true;
        return;
      }
      return;
    }
  }

  boolean scanSeparator()
  {
    boolean bool1 = false;
    while (true)
    {
      boolean bool2 = scanWhitespace();
      bool1 |= bool2;
      if (!scanCommentAsInlineSeparator())
        break;
      bool1 = true;
      this.hasNonSpaceSeparator = true;
    }
    return bool1;
  }

  boolean scanCommentAsInlineSeparator()
  {
    int i = charAt(this.currentPosition);
    int j;
    if ((i == 45) && (charAt(this.currentPosition + 1) == 45))
    {
      j = this.sqlString.indexOf(13, this.currentPosition + 2);
      if (j == -1)
        j = this.sqlString.indexOf(10, this.currentPosition + 2);
      else if (charAt(j + 1) == 10)
        j++;
      if (j == -1)
        this.currentPosition = this.limit;
      else
        this.currentPosition = (j + 1);
      return true;
    }
    if ((i == 47) && (charAt(this.currentPosition + 1) == 42))
    {
      j = this.sqlString.indexOf("*/", this.currentPosition + 2);
      if (j == -1)
      {
        this.token.tokenString = this.sqlString.substring(this.currentPosition, this.currentPosition + 2);
        this.token.tokenType = 858;
        this.token.isMalformed = true;
        return false;
      }
      this.currentPosition = (j + 2);
      return true;
    }
    return false;
  }

  public boolean scanWhitespace()
  {
    boolean bool = false;
    while (this.currentPosition < this.limit)
    {
      int i = this.sqlString.charAt(this.currentPosition);
      if (i == 32)
      {
        bool = true;
      }
      else
      {
        if (!whiteSpaceSet.contains(i))
          break;
        this.hasNonSpaceSeparator = true;
        bool = true;
        setLineNumber(i);
      }
      this.currentPosition += 1;
    }
    return bool;
  }

  private void setLineNumber(int paramInt)
  {
    if ((paramInt == 13) || (paramInt == 10))
    {
      if (this.currentPosition == this.eolPosition + 1)
      {
        if ((paramInt != 10) || (this.eolCode == paramInt))
          this.lineNumber += 1;
      }
      else
        this.lineNumber += 1;
      this.eolPosition = this.currentPosition;
      this.eolCode = paramInt;
    }
  }

  void scanCharacterString()
  {
    this.charWriter.reset(this.charBuffer);
    do
    {
      scanStringPart('\'');
      if (this.token.isMalformed)
        return;
    }
    while ((scanSeparator()) && (charAt(this.currentPosition) == 39));
    this.token.tokenString = this.charWriter.toString();
    this.token.tokenValue = this.token.tokenString;
  }

  public void scanStringPart(char paramChar)
  {
    int i;
    for (this.currentPosition += 1; ; this.currentPosition = (i + 1))
    {
      i = this.sqlString.indexOf(paramChar, this.currentPosition);
      if (i < 0)
      {
        this.token.tokenString = this.sqlString.substring(this.currentPosition, this.limit);
        this.token.tokenType = (paramChar == '\'' ? 853 : 859);
        this.token.isMalformed = true;
        return;
      }
      if (charAt(i + 1) != paramChar)
        break;
      i++;
      this.charWriter.write(this.sqlString, this.currentPosition, i - this.currentPosition);
    }
    this.charWriter.write(this.sqlString, this.currentPosition, i - this.currentPosition);
    this.currentPosition = (i + 1);
  }

  void scanToken()
  {
    int i = charAt(this.currentPosition);
    resetState();
    this.token.tokenType = 846;
    int j;
    switch (i)
    {
    case 91:
      this.token.tokenString = "[";
      this.token.tokenType = 781;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 93:
      this.token.tokenString = "]";
      this.token.tokenType = 790;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 40:
      this.token.tokenString = "(";
      this.token.tokenType = 786;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 41:
      this.token.tokenString = ")";
      this.token.tokenType = 772;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 44:
      this.token.tokenString = ",";
      this.token.tokenType = 774;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 42:
      this.token.tokenString = "*";
      this.token.tokenType = 771;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 61:
      this.token.tokenString = "=";
      this.token.tokenType = 396;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 59:
      this.token.tokenString = ";";
      this.token.tokenType = 791;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 43:
      this.token.tokenString = "+";
      this.token.tokenType = 787;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 58:
      if (charAt(this.currentPosition + 1) == 58)
      {
        this.currentPosition += 2;
        this.token.tokenString = "::";
        this.token.tokenType = 773;
        this.token.isDelimiter = true;
        return;
      }
      this.token.tokenString = ":";
      this.token.tokenType = 773;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 63:
      if (charAt(this.currentPosition + 1) == 63)
      {
        if (charAt(this.currentPosition + 2) == 40)
        {
          this.token.tokenString = "[";
          this.token.tokenType = 781;
          this.currentPosition += 3;
          this.token.isDelimiter = true;
          return;
        }
        if (charAt(this.currentPosition + 2) == 41)
        {
          this.token.tokenString = "]";
          this.token.tokenType = 790;
          this.currentPosition += 3;
          this.token.isDelimiter = true;
          return;
        }
      }
      this.token.tokenString = "?";
      this.token.tokenType = 788;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 33:
      if (charAt(this.currentPosition + 1) == 61)
      {
        this.token.tokenString = "<>";
        this.token.tokenType = 785;
        this.currentPosition += 2;
        this.token.isDelimiter = true;
        return;
      }
      this.token.tokenString = this.sqlString.substring(this.currentPosition, this.currentPosition + 2);
      this.token.tokenType = -1;
      this.token.isDelimiter = true;
      return;
    case 60:
      if (charAt(this.currentPosition + 1) == 62)
      {
        this.token.tokenString = "<>";
        this.token.tokenType = 785;
        this.currentPosition += 2;
        this.token.isDelimiter = true;
        return;
      }
      if (charAt(this.currentPosition + 1) == 61)
      {
        this.token.tokenString = "<=";
        this.token.tokenType = 783;
        this.currentPosition += 2;
        this.token.isDelimiter = true;
        return;
      }
      this.token.tokenString = "<";
      this.token.tokenType = 782;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 62:
      if (charAt(this.currentPosition + 1) == 61)
      {
        this.token.tokenString = ">=";
        this.token.tokenType = 780;
        this.currentPosition += 2;
        this.token.isDelimiter = true;
        return;
      }
      this.token.tokenString = ">";
      this.token.tokenType = 779;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 124:
      if (charAt(this.currentPosition + 1) == 124)
      {
        this.token.tokenString = "||";
        this.token.tokenType = 775;
        this.currentPosition += 2;
        this.token.isDelimiter = true;
        return;
      }
      this.token.tokenString = this.sqlString.substring(this.currentPosition, this.currentPosition + 2);
      this.token.tokenType = -1;
      this.token.isDelimiter = true;
      return;
    case 47:
      if (charAt(this.currentPosition + 1) == 47)
      {
        j = this.sqlString.indexOf(13, this.currentPosition + 2);
        if (j == -1)
          j = this.sqlString.indexOf(10, this.currentPosition + 2);
        if (j == -1)
          j = this.limit;
        this.token.tokenString = this.sqlString.substring(this.currentPosition + 2, j);
        this.token.tokenType = 850;
        this.token.isDelimiter = true;
        return;
      }
      if (charAt(this.currentPosition + 1) == 42)
      {
        j = this.sqlString.indexOf("*/", this.currentPosition + 2);
        if (j == -1)
        {
          this.token.tokenString = this.sqlString.substring(this.currentPosition, this.currentPosition + 2);
          this.token.tokenType = -1;
          this.token.isDelimiter = true;
          return;
        }
        this.token.tokenString = this.sqlString.substring(this.currentPosition + 2, j);
        this.token.tokenType = 850;
        this.token.isDelimiter = true;
        return;
      }
      this.token.tokenString = "/";
      this.token.tokenType = 776;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 45:
      if (charAt(this.currentPosition + 1) == 45)
      {
        j = this.sqlString.indexOf(13, this.currentPosition + 2);
        if (j == -1)
          j = this.sqlString.indexOf(10, this.currentPosition + 2);
        if (j == -1)
          j = this.limit;
        this.token.tokenString = this.sqlString.substring(this.currentPosition + 2, j);
        this.token.tokenType = 850;
        this.token.isDelimiter = true;
        return;
      }
      this.token.tokenString = "-";
      this.token.tokenType = 784;
      this.currentPosition += 1;
      this.token.isDelimiter = true;
      return;
    case 34:
      this.token.tokenType = 847;
      break;
    case 39:
      scanCharacterString();
      if (this.token.isMalformed)
        return;
      this.token.dataType = CharacterType.getCharacterType(1, this.token.tokenString.length());
      this.token.tokenType = 845;
      this.token.isDelimiter = true;
      return;
    case 88:
    case 120:
      if (charAt(this.currentPosition + 1) == 39)
      {
        this.currentPosition += 1;
        scanBinaryString();
        if (this.token.isMalformed)
          return;
        this.token.dataType = BinaryType.getBinaryType(61, ((BinaryData)this.token.tokenValue).length(null));
        this.token.tokenType = 845;
        return;
      }
      break;
    case 66:
    case 98:
      if (charAt(this.currentPosition + 1) == 39)
      {
        this.currentPosition += 1;
        scanBitString();
        if (this.token.isMalformed)
          return;
        this.token.dataType = BitType.getBitType(14, ((BinaryData)this.token.tokenValue).bitLength(null));
        this.token.tokenType = 845;
        return;
      }
      break;
    case 78:
    case 110:
      if (charAt(this.currentPosition + 1) == 39)
      {
        this.currentPosition += 1;
        scanCharacterString();
        if (this.token.isMalformed)
          return;
        this.token.dataType = CharacterType.getCharacterType(1, this.token.tokenString.length());
        this.token.tokenType = 845;
        return;
      }
      break;
    case 85:
    case 117:
      if ((charAt(this.currentPosition + 1) == 38) && (charAt(this.currentPosition + 2) == 39))
      {
        this.currentPosition += 2;
        this.token.dataType = Type.SQL_CHAR;
        this.token.tokenType = 845;
        scanUnicodeString();
        if (this.token.isMalformed)
          return;
        this.token.dataType = CharacterType.getCharacterType(1, ((String)this.token.tokenValue).length());
        return;
      }
      break;
    case 95:
      j = this.currentPosition;
      this.currentPosition += 1;
      scanIdentifierChain();
      if (this.token.isMalformed)
        return;
      if (this.token.tokenType != 846)
      {
        this.token.tokenType = 853;
        this.token.isMalformed = true;
        return;
      }
      scanSeparator();
      if (charAt(this.currentPosition) == 39)
      {
        if (this.token.namePrePrefix != null)
        {
          this.token.tokenType = 853;
          this.token.isMalformed = true;
          return;
        }
        this.token.charsetSchema = this.token.namePrefix;
        this.token.charsetName = this.token.tokenString;
        scanCharacterString();
        this.token.tokenType = 845;
        this.token.dataType = CharacterType.getCharacterType(1, this.token.tokenString.length());
        this.token.isDelimiter = true;
        return;
      }
      position(j);
      resetState();
      break;
    case 46:
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
      this.token.tokenType = 845;
      scanNumber();
      return;
    case 35:
    case 36:
    case 37:
    case 38:
    case 64:
    case 65:
    case 67:
    case 68:
    case 69:
    case 70:
    case 71:
    case 72:
    case 73:
    case 74:
    case 75:
    case 76:
    case 77:
    case 79:
    case 80:
    case 81:
    case 82:
    case 83:
    case 84:
    case 86:
    case 87:
    case 89:
    case 90:
    case 92:
    case 94:
    case 96:
    case 97:
    case 99:
    case 100:
    case 101:
    case 102:
    case 103:
    case 104:
    case 105:
    case 106:
    case 107:
    case 108:
    case 109:
    case 111:
    case 112:
    case 113:
    case 114:
    case 115:
    case 116:
    case 118:
    case 119:
    case 121:
    case 122:
    case 123:
    }
    scanIdentifierChain();
    setIdentifierProperties();
  }

  private void setIdentifierProperties()
  {
    if (this.token.tokenType == 846)
    {
      this.token.isUndelimitedIdentifier = true;
      if (this.token.namePrefix == null)
      {
        this.token.tokenType = Tokens.getKeywordID(this.token.tokenString, 846);
        if (this.token.tokenType == 846)
        {
          this.token.tokenType = Tokens.getNonKeywordID(this.token.tokenString, 846);
        }
        else
        {
          this.token.isReservedIdentifier = true;
          this.token.isCoreReservedIdentifier = Tokens.isCoreKeyword(this.token.tokenType);
        }
      }
    }
    else if (this.token.tokenType == 847)
    {
      this.token.isDelimitedIdentifier = true;
    }
  }

  public boolean scanNull()
  {
    scanSeparator();
    int i = charAt(this.currentPosition);
    return ((i == 78) || (i == 110)) && (scanSpecialIdentifier("NULL"));
  }

  private void scanNext(int paramInt)
  {
    scanNext();
    if (this.token.isMalformed)
      throw Error.error(paramInt);
  }

  IntervalType scanIntervalType()
  {
    int i = -1;
    int j = -1;
    int m;
    int k = m = this.token.tokenType;
    scanNext(3406);
    if (this.token.tokenType == 786)
    {
      scanNext(3406);
      if ((this.token.dataType == null) || (this.token.dataType.typeCode != 4))
        throw Error.error(3406);
      i = ((Number)this.token.tokenValue).intValue();
      scanNext(3406);
      if (this.token.tokenType == 774)
      {
        if (k != 250)
          throw Error.error(3406);
        scanNext(3406);
        if ((this.token.dataType == null) || (this.token.dataType.typeCode != 4))
          throw Error.error(3406);
        j = ((Number)this.token.tokenValue).intValue();
        scanNext(3406);
      }
      if (this.token.tokenType != 772)
        throw Error.error(3406);
      scanNext(3406);
    }
    if (this.token.tokenType == 285)
    {
      scanNext(3406);
      m = this.token.tokenType;
      scanNext(3406);
    }
    if (this.token.tokenType == 786)
    {
      if ((m != 250) || (m == k))
        throw Error.error(3406);
      scanNext(3406);
      if ((this.token.dataType == null) || (this.token.dataType.typeCode != 4))
        throw Error.error(3406);
      j = ((Number)this.token.tokenValue).intValue();
      scanNext(3406);
      if (this.token.tokenType != 772)
        throw Error.error(3406);
      scanNext(3406);
    }
    int n = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_CODES, k);
    int i1 = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_CODES, m);
    return IntervalType.getIntervalType(n, i1, i, j);
  }

  public TimestampData newDate(String paramString)
  {
    this.intervalPosition = 0;
    this.fractionPrecision = 0;
    this.dateTimeType = null;
    this.intervalString = paramString;
    scanDateParts(2);
    if (this.intervalPosition != paramString.length())
      throw Error.error(3407);
    long l = HsqlDateTime.getDateSeconds(paramString);
    return new TimestampData(l);
  }

  public TimestampData newTimestamp(String paramString)
  {
    long l1 = 0L;
    int i = 0;
    int j = paramString.length();
    int k = 0;
    this.intervalPosition = 0;
    this.fractionPrecision = 0;
    this.dateTimeType = null;
    this.intervalString = paramString;
    scanDateParts(5);
    long l2;
    try
    {
      l2 = HsqlDateTime.getTimestampSeconds(paramString.substring(0, this.intervalPosition));
    }
    catch (Throwable localThrowable)
    {
      throw Error.error(3407);
    }
    i = scanIntervalFraction(9);
    int m = this.intervalPosition;
    boolean bool = scanIntervalSign();
    if ((bool) || (m != this.intervalPosition))
    {
      l1 = scanIntervalValue(Type.SQL_INTERVAL_HOUR_TO_MINUTE);
      k = 1;
      if (bool)
        l1 = -l1;
    }
    if ((l1 >= DTIType.yearToSecondFactors[2]) || (l1 > 50400L) || (-l1 > 50400L))
      throw Error.error(3409);
    if (this.intervalPosition != j)
      throw Error.error(3407);
    int n = k != 0 ? 95 : 93;
    this.dateTimeType = DateTimeType.getDateTimeType(n, this.fractionPrecision);
    if (k != 0)
      l2 -= l1;
    return new TimestampData(l2, i, (int)l1);
  }

  void scanDateParts(int paramInt)
  {
    byte[] arrayOfByte = DTIType.yearToSecondSeparators;
    int i = this.intervalPosition;
    int j = 0;
    int k = 0;
    while (j <= paramInt)
    {
      int m = 0;
      if (i == this.intervalString.length())
      {
        if (j == paramInt)
          m = 1;
        else
          throw Error.error(3407);
      }
      else
      {
        int n = this.intervalString.charAt(i);
        if ((n >= 48) && (n <= 57))
        {
          k++;
          i++;
        }
        else if (n == arrayOfByte[j])
        {
          m = 1;
          if (j != paramInt)
            i++;
        }
        else if (j == paramInt)
        {
          m = 1;
        }
        else
        {
          throw Error.error(3407);
        }
      }
      if (m != 0)
      {
        if (j == 0)
        {
          if (k != 4)
            throw Error.error(3407);
        }
        else if ((k == 0) || (k > 2))
          throw Error.error(3407);
        j++;
        k = 0;
        if (i == this.intervalString.length())
          break;
      }
    }
    this.intervalPosition = i;
  }

  public TimeData newTime(String paramString)
  {
    this.intervalPosition = 0;
    this.fractionPrecision = 0;
    this.dateTimeType = null;
    this.intervalString = paramString;
    long l1 = scanIntervalValue(Type.SQL_INTERVAL_HOUR_TO_SECOND);
    int i = scanIntervalFraction(9);
    long l2 = 0L;
    int j = this.intervalPosition;
    int k = 0;
    boolean bool = scanIntervalSign();
    if (j != this.intervalPosition)
    {
      l2 = scanIntervalValue(Type.SQL_INTERVAL_HOUR_TO_MINUTE);
      k = 1;
    }
    if (this.intervalPosition != paramString.length())
      throw Error.error(3409);
    if (l1 >= DTIType.yearToSecondFactors[2])
      throw Error.error(3408);
    if (l2 > 50400L)
      throw Error.error(3409);
    if (bool)
      l2 = -l2;
    int m = k != 0 ? 94 : 92;
    this.dateTimeType = DateTimeType.getDateTimeType(m, this.fractionPrecision);
    if (k != 0)
      l1 -= l2;
    return new TimeData((int)l1, i, (int)l2);
  }

  public Object newInterval(String paramString, IntervalType paramIntervalType)
  {
    this.intervalPosition = 0;
    this.fractionPrecision = 0;
    this.intervalString = paramString;
    boolean bool = scanIntervalSign();
    long l = scanIntervalValue(paramIntervalType);
    int i = 0;
    if (paramIntervalType.endIntervalType == 106)
      i = scanIntervalFraction(paramIntervalType.scale);
    if (this.intervalPosition != paramString.length())
      throw Error.error(3406);
    if (bool)
    {
      l = -l;
      i = -i;
    }
    this.dateTimeType = paramIntervalType;
    if (paramIntervalType.defaultPrecision)
      this.dateTimeType = IntervalType.getIntervalType(paramIntervalType.typeCode, paramIntervalType.startIntervalType, paramIntervalType.endIntervalType, this.intervalPrecision, this.fractionPrecision, false);
    if (paramIntervalType.endPartIndex <= 1)
      return new IntervalMonthData(l);
    return new IntervalSecondData(l, i);
  }

  public long scanIntervalValue(IntervalType paramIntervalType)
  {
    byte[] arrayOfByte = DTIType.yearToSecondSeparators;
    int[] arrayOfInt1 = DTIType.yearToSecondFactors;
    int[] arrayOfInt2 = DTIType.yearToSecondLimits;
    int i = paramIntervalType.startPartIndex;
    int j = paramIntervalType.endPartIndex;
    long l = 0L;
    int k = 0;
    int m = this.intervalPosition;
    int n = i;
    int i1 = 0;
    while (n <= j)
    {
      int i2 = 0;
      int i3;
      if (m == this.intervalString.length())
      {
        if (n == j)
          i2 = 1;
        else
          throw Error.error(3406);
      }
      else
      {
        i3 = this.intervalString.charAt(m);
        if ((i3 >= 48) && (i3 <= 57))
        {
          int i4 = i3 - 48;
          k *= 10;
          k += i4;
          i1++;
          m++;
        }
        else if (i3 == arrayOfByte[n])
        {
          i2 = 1;
          if (n != j)
            m++;
        }
        else if (n == j)
        {
          i2 = 1;
        }
        else
        {
          throw Error.error(3406);
        }
      }
      if (i2 != 0)
      {
        if (n == i)
        {
          if ((!paramIntervalType.defaultPrecision) && (i1 > paramIntervalType.precision))
            throw Error.error(3435);
          if (i1 == 0)
            throw Error.error(3406);
          i3 = arrayOfInt1[n];
          l += k * i3;
          this.intervalPrecision = i1;
        }
        else
        {
          if (k >= arrayOfInt2[n])
            throw Error.error(3435);
          if ((i1 == 0) || (i1 > 2))
            throw Error.error(3406);
          l += k * arrayOfInt1[n];
        }
        n++;
        k = 0;
        i1 = 0;
        if (m == this.intervalString.length())
          break;
      }
    }
    this.intervalPosition = m;
    return l;
  }

  boolean scanIntervalSign()
  {
    boolean bool = false;
    if (this.intervalPosition == this.intervalString.length())
      return false;
    if (this.intervalString.charAt(this.intervalPosition) == '-')
    {
      bool = true;
      this.intervalPosition += 1;
    }
    else if (this.intervalString.charAt(this.intervalPosition) == '+')
    {
      this.intervalPosition += 1;
    }
    return bool;
  }

  int scanIntervalFraction(int paramInt)
  {
    if (this.intervalPosition == this.intervalString.length())
      return 0;
    if (this.intervalString.charAt(this.intervalPosition) != '.')
      return 0;
    this.intervalPosition += 1;
    int i = 0;
    int j = 0;
    while (this.intervalPosition < this.intervalString.length())
    {
      int k = this.intervalString.charAt(this.intervalPosition);
      if ((k < 48) || (k > 57))
        break;
      int m = k - 48;
      i *= 10;
      i += m;
      this.intervalPosition += 1;
      j++;
      if (j == 9)
        break;
    }
    this.fractionPrecision = j;
    i *= DTIType.nanoScaleFactors[j];
    i = DTIType.normaliseFraction(i, paramInt);
    return i;
  }

  void scanIntervalSpaces()
  {
    while ((this.intervalPosition < this.intervalString.length()) && (this.intervalString.charAt(this.intervalPosition) == ' '))
      this.intervalPosition += 1;
  }

  public synchronized Number convertToNumber(String paramString, NumberType paramNumberType)
  {
    int i = 0;
    reset(paramString);
    resetState();
    scanWhitespace();
    scanToken();
    scanWhitespace();
    if (this.token.tokenType == 787)
    {
      scanToken();
      scanWhitespace();
    }
    else if (this.token.tokenType == 784)
    {
      i = 1;
      scanToken();
      scanWhitespace();
    }
    if ((!this.hasNonSpaceSeparator) && (this.token.tokenType == 845) && ((this.token.tokenValue instanceof Number)))
    {
      Number localNumber = (Number)this.token.tokenValue;
      Type localType = this.token.dataType;
      if (i != 0)
        localNumber = (Number)this.token.dataType.negate(localNumber);
      scanEnd();
      if (this.token.tokenType == 848)
      {
        localNumber = (Number)paramNumberType.convertToType(null, localNumber, localType);
        return localNumber;
      }
    }
    throw Error.error(3438);
  }

  public synchronized BinaryData convertToBinary(String paramString)
  {
    int i = 1;
    int j = 0;
    reset(paramString);
    resetState();
    this.byteOutputStream.reset(this.byteBuffer);
    while (this.currentPosition < this.limit)
    {
      int k = this.sqlString.charAt(this.currentPosition);
      k = getHexValue(k);
      if (k == -1)
      {
        this.token.tokenType = 856;
        this.token.isMalformed = true;
        break;
      }
      if (i != 0)
      {
        j = (byte)(k << 4);
      }
      else
      {
        j = (byte)(j + (byte)k);
        this.byteOutputStream.writeByte(j);
      }
      this.currentPosition += 1;
      i = i == 0 ? 1 : 0;
    }
    if (i == 0)
    {
      this.token.tokenType = 856;
      this.token.isMalformed = true;
    }
    if (this.token.isMalformed)
      throw Error.error(3438);
    BinaryData localBinaryData = new BinaryData(this.byteOutputStream.toByteArray(), false);
    this.byteOutputStream.reset(this.byteBuffer);
    return localBinaryData;
  }

  public synchronized BinaryData convertToBit(String paramString)
  {
    BitMap localBitMap = new BitMap(32);
    int i = localBitMap.size();
    reset(paramString);
    resetState();
    this.byteOutputStream.reset(this.byteBuffer);
    while (this.currentPosition < this.limit)
    {
      int j = this.sqlString.charAt(this.currentPosition);
      if (j == 48)
      {
        i++;
      }
      else if (j == 49)
      {
        localBitMap.set(i);
        i++;
      }
      else
      {
        this.token.tokenType = 855;
        this.token.isMalformed = true;
        throw Error.error(3438);
      }
      this.currentPosition += 1;
    }
    localBitMap.setSize(i);
    return BinaryData.getBitData(localBitMap.getBytes(), localBitMap.size());
  }

  public synchronized Object convertToDatetimeInterval(SessionInterface paramSessionInterface, String paramString, DTIType paramDTIType)
  {
    IntervalType localIntervalType = null;
    int i = -1;
    int j = paramDTIType.isDateTimeType() ? 3407 : 3406;
    reset(paramString);
    resetState();
    scanToken();
    scanWhitespace();
    switch (this.token.tokenType)
    {
    case 72:
    case 140:
    case 281:
    case 282:
      i = this.token.tokenType;
      scanToken();
      if ((this.token.tokenType != 845) || (this.token.dataType.typeCode != 1))
        throw Error.error(j);
      paramString = this.token.tokenString;
      scanNext(3407);
      if (paramDTIType.isIntervalType())
        localIntervalType = scanIntervalType();
      if (this.token.tokenType != 848)
        throw Error.error(j);
      break;
    }
    Object localObject;
    switch (paramDTIType.typeCode)
    {
    case 91:
      if ((i != -1) && (i != 72))
        throw Error.error(j);
      localObject = newDate(paramString);
      return paramDTIType.convertToType(paramSessionInterface, localObject, Type.SQL_DATE);
    case 92:
    case 94:
      if ((i != -1) && (i != 281))
        throw Error.error(j);
      TimeData localTimeData = newTime(paramString);
      return paramDTIType.convertToType(paramSessionInterface, localTimeData, this.dateTimeType);
    case 93:
    case 95:
      if ((i != -1) && (i != 282))
        throw Error.error(j);
      localObject = newTimestamp(paramString);
      return paramDTIType.convertToType(paramSessionInterface, localObject, this.dateTimeType);
    }
    if ((i != -1) && (i != 140))
      throw Error.error(j);
    if (paramDTIType.isIntervalType())
    {
      localObject = newInterval(paramString, (IntervalType)paramDTIType);
      if ((localIntervalType != null) && ((localIntervalType.startIntervalType != paramDTIType.startIntervalType) || (localIntervalType.endIntervalType != paramDTIType.endIntervalType)))
        throw Error.error(j);
      return paramDTIType.convertToType(paramSessionInterface, localObject, this.dateTimeType);
    }
    throw Error.runtimeError(201, "Scanner");
  }

  static
  {
    for (int i = 0; i < whitespace.length; i++)
      whiteSpaceSet.add(whitespace[i]);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.Scanner
 * JD-Core Version:    0.6.2
 */