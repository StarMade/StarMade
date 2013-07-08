package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.ClobData;
import org.hsqldb.types.LobData;
import org.hsqldb.types.Type;

class Like
  implements Cloneable
{
  private static final BinaryData maxByteValue = new BinaryData(new byte[] { -128 }, false);
  private char[] cLike;
  private int[] wildCardType;
  private int iLen;
  private boolean isIgnoreCase;
  private int iFirstWildCard;
  private boolean isNull;
  int escapeChar;
  boolean hasCollation;
  static final int UNDERSCORE_CHAR = 1;
  static final int PERCENT_CHAR = 2;
  boolean isVariable = true;
  boolean isBinary = false;
  Type dataType;
  
  void setParams(boolean paramBoolean)
  {
    this.hasCollation = paramBoolean;
  }
  
  void setIgnoreCase(boolean paramBoolean)
  {
    this.isIgnoreCase = paramBoolean;
  }
  
  private Object getStartsWith()
  {
    if (this.iLen == 0) {
      return this.isBinary ? BinaryData.zeroLengthBinary : "";
    }
    StringBuffer localStringBuffer = null;
    HsqlByteArrayOutputStream localHsqlByteArrayOutputStream = null;
    if (this.isBinary) {
      localHsqlByteArrayOutputStream = new HsqlByteArrayOutputStream();
    } else {
      localStringBuffer = new StringBuffer();
    }
    for (int i = 0; (i < this.iLen) && (this.wildCardType[i] == 0); i++) {
      if (this.isBinary) {
        localHsqlByteArrayOutputStream.writeByte(this.cLike[i]);
      } else {
        localStringBuffer.append(this.cLike[i]);
      }
    }
    if (i == 0) {
      return null;
    }
    return this.isBinary ? new BinaryData(localHsqlByteArrayOutputStream.toByteArray(), false) : localStringBuffer.toString();
  }
  
  Boolean compare(Session paramSession, Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if (this.isNull) {
      return null;
    }
    int i = getLength(paramSession, paramObject);
    if (this.isIgnoreCase) {
      paramObject = ((CharacterType)this.dataType).upper(paramSession, paramObject);
    }
    if ((paramObject instanceof ClobData)) {
      paramObject = ((ClobData)paramObject).getChars(paramSession, 0L, (int)((ClobData)paramObject).length(paramSession));
    }
    return compareAt(paramObject, 0, 0, this.iLen, i, this.cLike, this.wildCardType) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  char getChar(Object paramObject, int paramInt)
  {
    char c;
    if (this.isBinary) {
      c = (char)((org.hsqldb.types.BlobData)paramObject).getBytes()[paramInt];
    } else if ((paramObject instanceof char[])) {
      c = ((char[])(char[])paramObject)[paramInt];
    } else {
      c = ((String)paramObject).charAt(paramInt);
    }
    return c;
  }
  
  int getLength(SessionInterface paramSessionInterface, Object paramObject)
  {
    int i;
    if ((paramObject instanceof LobData)) {
      i = (int)((LobData)paramObject).length(paramSessionInterface);
    } else {
      i = ((String)paramObject).length();
    }
    return i;
  }
  
  private boolean compareAt(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, char[] paramArrayOfChar, int[] paramArrayOfInt)
  {
    while (paramInt1 < paramInt3)
    {
      switch (paramArrayOfInt[paramInt1])
      {
      case 0: 
        if ((paramInt2 >= paramInt4) || (paramArrayOfChar[paramInt1] != getChar(paramObject, paramInt2++))) {
          return false;
        }
        break;
      case 1: 
        if (paramInt2++ >= paramInt4) {
          return false;
        }
        break;
      case 2: 
        paramInt1++;
        if (paramInt1 >= paramInt3) {
          return true;
        }
        while (paramInt2 < paramInt4)
        {
          if ((paramArrayOfChar[paramInt1] == getChar(paramObject, paramInt2)) && (compareAt(paramObject, paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfChar, paramArrayOfInt))) {
            return true;
          }
          paramInt2++;
        }
        return false;
      }
      paramInt1++;
    }
    return paramInt2 == paramInt4;
  }
  
  void setPattern(Session paramSession, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    this.isNull = (paramObject1 == null);
    if (!paramBoolean)
    {
      this.escapeChar = -1;
    }
    else
    {
      if (paramObject2 == null)
      {
        this.isNull = true;
        return;
      }
      i = getLength(paramSession, paramObject2);
      if (i != 1)
      {
        if (this.isBinary) {
          throw Error.error(3412);
        }
        throw Error.error(3439);
      }
      this.escapeChar = getChar(paramObject2, 0);
    }
    if (this.isNull) {
      return;
    }
    if (this.isIgnoreCase) {
      paramObject1 = (String)((CharacterType)this.dataType).upper(null, paramObject1);
    }
    this.iLen = 0;
    this.iFirstWildCard = -1;
    int i = getLength(paramSession, paramObject1);
    this.cLike = new char[i];
    this.wildCardType = new int[i];
    int j = 0;
    int k = 0;
    for (int m = 0; m < i; m++)
    {
      int n = getChar(paramObject1, m);
      if (j == 0)
      {
        if (this.escapeChar == n)
        {
          j = 1;
          continue;
        }
        if (n == 95)
        {
          this.wildCardType[this.iLen] = 1;
          if (this.iFirstWildCard == -1) {
            this.iFirstWildCard = this.iLen;
          }
        }
        else if (n == 37)
        {
          if (k != 0) {
            continue;
          }
          k = 1;
          this.wildCardType[this.iLen] = 2;
          if (this.iFirstWildCard == -1) {
            this.iFirstWildCard = this.iLen;
          }
        }
        else
        {
          k = 0;
        }
      }
      else if ((n == this.escapeChar) || (n == 95) || (n == 37))
      {
        k = 0;
        j = 0;
      }
      else
      {
        throw Error.error(3458);
      }
      this.cLike[(this.iLen++)] = n;
    }
    if (j != 0) {
      throw Error.error(3458);
    }
    for (m = 0; m < this.iLen - 1; m++) {
      if ((this.wildCardType[m] == 2) && (this.wildCardType[(m + 1)] == 1))
      {
        this.wildCardType[m] = 1;
        this.wildCardType[(m + 1)] = 2;
      }
    }
  }
  
  boolean isEquivalentToUnknownPredicate()
  {
    return (!this.isVariable) && (this.isNull);
  }
  
  boolean isEquivalentToEqualsPredicate()
  {
    return (!this.isVariable) && (this.iFirstWildCard == -1);
  }
  
  boolean isEquivalentToNotNullPredicate()
  {
    if ((this.isVariable) || (this.isNull) || (this.iFirstWildCard == -1)) {
      return false;
    }
    for (int i = 0; i < this.wildCardType.length; i++) {
      if (this.wildCardType[i] != 2) {
        return false;
      }
    }
    return true;
  }
  
  int getFirstWildCardIndex()
  {
    return this.iFirstWildCard;
  }
  
  Object getRangeLow()
  {
    return getStartsWith();
  }
  
  Object getRangeHigh(Session paramSession)
  {
    Object localObject = getStartsWith();
    if (localObject == null) {
      return null;
    }
    if (this.isBinary) {
      return new BinaryData(paramSession, (BinaryData)localObject, maxByteValue);
    }
    return this.dataType.concat(paramSession, localObject, "ð¿¿");
  }
  
  public String describe(Session paramSession)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(super.toString()).append("[\n");
    localStringBuffer.append("escapeChar=").append(this.escapeChar).append('\n');
    localStringBuffer.append("isNull=").append(this.isNull).append('\n');
    localStringBuffer.append("isIgnoreCase=").append(this.isIgnoreCase).append('\n');
    localStringBuffer.append("iLen=").append(this.iLen).append('\n');
    localStringBuffer.append("iFirstWildCard=").append(this.iFirstWildCard).append('\n');
    localStringBuffer.append("cLike=");
    localStringBuffer.append(StringUtil.arrayToString(this.cLike));
    localStringBuffer.append('\n');
    localStringBuffer.append("wildCardType=");
    localStringBuffer.append(StringUtil.arrayToString(this.wildCardType));
    localStringBuffer.append(']');
    return localStringBuffer.toString();
  }
  
  public Like duplicate()
  {
    try
    {
      return (Like)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw Error.runtimeError(201, "Expression");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.Like
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */