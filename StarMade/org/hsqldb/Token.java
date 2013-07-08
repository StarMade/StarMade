package org.hsqldb;

import org.hsqldb.types.Type;

public class Token
{
  String tokenString = "";
  int tokenType = -1;
  Type dataType;
  Object tokenValue;
  String namePrefix;
  String namePrePrefix;
  String namePrePrePrefix;
  String charsetSchema;
  String charsetName;
  String fullString;
  int lobMultiplierType = -1;
  boolean isDelimiter;
  boolean isDelimitedIdentifier;
  boolean isDelimitedPrefix;
  boolean isDelimitedPrePrefix;
  boolean isDelimitedPrePrePrefix;
  boolean isUndelimitedIdentifier;
  boolean hasIrregularChar;
  boolean isReservedIdentifier;
  boolean isCoreReservedIdentifier;
  boolean isHostParameter;
  boolean isMalformed;
  int position;
  Object expression;
  
  void reset()
  {
    this.tokenString = "";
    this.tokenType = -1;
    this.dataType = null;
    this.tokenValue = null;
    this.namePrefix = null;
    this.namePrePrefix = null;
    this.namePrePrePrefix = null;
    this.charsetSchema = null;
    this.charsetName = null;
    this.fullString = null;
    this.expression = null;
    this.lobMultiplierType = -1;
    this.isDelimiter = false;
    this.isDelimitedIdentifier = false;
    this.isDelimitedPrefix = false;
    this.isDelimitedPrePrefix = false;
    this.isDelimitedPrePrePrefix = false;
    this.isUndelimitedIdentifier = false;
    this.hasIrregularChar = false;
    this.isReservedIdentifier = false;
    this.isCoreReservedIdentifier = false;
    this.isHostParameter = false;
    this.isMalformed = false;
  }
  
  Token duplicate()
  {
    Token localToken = new Token();
    localToken.tokenString = this.tokenString;
    localToken.tokenType = this.tokenType;
    localToken.dataType = this.dataType;
    localToken.tokenValue = this.tokenValue;
    localToken.namePrefix = this.namePrefix;
    localToken.namePrePrefix = this.namePrePrefix;
    localToken.namePrePrePrefix = this.namePrePrePrefix;
    localToken.charsetSchema = this.charsetSchema;
    localToken.charsetName = this.charsetName;
    localToken.fullString = this.fullString;
    localToken.lobMultiplierType = this.lobMultiplierType;
    localToken.isDelimiter = this.isDelimiter;
    localToken.isDelimitedIdentifier = this.isDelimitedIdentifier;
    localToken.isDelimitedPrefix = this.isDelimitedPrefix;
    localToken.isDelimitedPrePrefix = this.isDelimitedPrePrefix;
    localToken.isDelimitedPrePrePrefix = this.isDelimitedPrePrePrefix;
    localToken.isUndelimitedIdentifier = this.isUndelimitedIdentifier;
    localToken.hasIrregularChar = this.hasIrregularChar;
    localToken.isReservedIdentifier = this.isReservedIdentifier;
    localToken.isCoreReservedIdentifier = this.isCoreReservedIdentifier;
    localToken.isHostParameter = this.isHostParameter;
    localToken.isMalformed = this.isMalformed;
    return localToken;
  }
  
  public String getFullString()
  {
    return this.fullString;
  }
  
  public void setExpression(Object paramObject)
  {
    this.expression = paramObject;
  }
  
  String getSQL()
  {
    if ((this.expression instanceof ExpressionColumn))
    {
      if (this.tokenType == 771)
      {
        localObject = new StringBuffer();
        Expression localExpression1 = (Expression)this.expression;
        if ((localExpression1 != null) && (localExpression1.opType == 97) && (localExpression1.nodes.length > 0))
        {
          ((StringBuffer)localObject).append(' ');
          for (int i = 0; i < localExpression1.nodes.length; i++)
          {
            Expression localExpression2 = localExpression1.nodes[i];
            ColumnSchema localColumnSchema = localExpression2.getColumn();
            if (localExpression2.opType == 3)
            {
              if (i > 0) {
                ((StringBuffer)localObject).append(',');
              }
              ((StringBuffer)localObject).append(localExpression2.getColumnName());
            }
            else
            {
              String str;
              if (localExpression2.getRangeVariable().tableAlias == null)
              {
                str = localColumnSchema.getName().getSchemaQualifiedStatementName();
              }
              else
              {
                RangeVariable localRangeVariable = localExpression2.getRangeVariable();
                str = localRangeVariable.tableAlias.getStatementName() + '.' + localColumnSchema.getName().statementName;
              }
              if (i > 0) {
                ((StringBuffer)localObject).append(',');
              }
              ((StringBuffer)localObject).append(str);
            }
          }
          ((StringBuffer)localObject).append(' ');
        }
        else
        {
          return this.tokenString;
        }
        return ((StringBuffer)localObject).toString();
      }
    }
    else
    {
      if ((this.expression instanceof Type))
      {
        this.isDelimiter = false;
        localObject = (Type)this.expression;
        if ((((Type)localObject).isDistinctType()) || (((Type)localObject).isDomainType())) {
          return ((Type)localObject).getName().getSchemaQualifiedStatementName();
        }
        return ((Type)localObject).getNameString();
      }
      if ((this.expression instanceof SchemaObject))
      {
        this.isDelimiter = false;
        return ((SchemaObject)this.expression).getName().getSchemaQualifiedStatementName();
      }
    }
    if ((this.namePrefix == null) && (this.isUndelimitedIdentifier)) {
      return this.tokenString;
    }
    if (this.tokenType == 845) {
      return this.dataType.convertToSQLString(this.tokenValue);
    }
    Object localObject = new StringBuffer();
    if (this.namePrePrefix != null)
    {
      if (this.isDelimitedPrePrefix)
      {
        ((StringBuffer)localObject).append('"');
        ((StringBuffer)localObject).append(this.namePrePrefix);
        ((StringBuffer)localObject).append('"');
      }
      else
      {
        ((StringBuffer)localObject).append(this.namePrePrefix);
      }
      ((StringBuffer)localObject).append('.');
    }
    if (this.namePrefix != null)
    {
      if (this.isDelimitedPrefix)
      {
        ((StringBuffer)localObject).append('"');
        ((StringBuffer)localObject).append(this.namePrefix);
        ((StringBuffer)localObject).append('"');
      }
      else
      {
        ((StringBuffer)localObject).append(this.namePrefix);
      }
      ((StringBuffer)localObject).append('.');
    }
    if (this.isDelimitedIdentifier)
    {
      ((StringBuffer)localObject).append('"');
      ((StringBuffer)localObject).append(this.tokenString);
      ((StringBuffer)localObject).append('"');
      this.isDelimiter = false;
    }
    else
    {
      ((StringBuffer)localObject).append(this.tokenString);
    }
    return ((StringBuffer)localObject).toString();
  }
  
  static String getSQL(Token[] paramArrayOfToken)
  {
    boolean bool = true;
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfToken.length; i++)
    {
      String str = paramArrayOfToken[i].getSQL();
      if ((!paramArrayOfToken[i].isDelimiter) && (!bool)) {
        localStringBuffer.append(' ');
      }
      localStringBuffer.append(str);
      bool = paramArrayOfToken[i].isDelimiter;
    }
    return localStringBuffer.toString();
  }
  
  static Object[] getSimplifiedTokens(Token[] paramArrayOfToken)
  {
    Object[] arrayOfObject = new Object[paramArrayOfToken.length];
    for (int i = 0; i < paramArrayOfToken.length; i++) {
      if (paramArrayOfToken[i].expression == null) {
        arrayOfObject[i] = paramArrayOfToken[i].getSQL();
      } else {
        arrayOfObject[i] = paramArrayOfToken[i].expression;
      }
    }
    return arrayOfObject;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.Token
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */