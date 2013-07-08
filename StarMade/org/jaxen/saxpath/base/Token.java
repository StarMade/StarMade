/*   1:    */package org.jaxen.saxpath.base;
/*   2:    */
/*  14:    */class Token
/*  15:    */{
/*  16:    */  private int tokenType;
/*  17:    */  
/*  28:    */  private String parseText;
/*  29:    */  
/*  40:    */  private int tokenBegin;
/*  41:    */  
/*  52:    */  private int tokenEnd;
/*  53:    */  
/*  64:    */  Token(int tokenType, String parseText, int tokenBegin, int tokenEnd)
/*  65:    */  {
/*  66: 66 */    setTokenType(tokenType);
/*  67: 67 */    setParseText(parseText);
/*  68: 68 */    setTokenBegin(tokenBegin);
/*  69: 69 */    setTokenEnd(tokenEnd);
/*  70:    */  }
/*  71:    */  
/*  72:    */  private void setTokenType(int tokenType)
/*  73:    */  {
/*  74: 74 */    this.tokenType = tokenType;
/*  75:    */  }
/*  76:    */  
/*  77:    */  int getTokenType()
/*  78:    */  {
/*  79: 79 */    return this.tokenType;
/*  80:    */  }
/*  81:    */  
/*  82:    */  private void setParseText(String parseText)
/*  83:    */  {
/*  84: 84 */    this.parseText = parseText;
/*  85:    */  }
/*  86:    */  
/*  87:    */  String getTokenText()
/*  88:    */  {
/*  89: 89 */    return this.parseText.substring(getTokenBegin(), getTokenEnd());
/*  90:    */  }
/*  91:    */  
/*  93:    */  private void setTokenBegin(int tokenBegin)
/*  94:    */  {
/*  95: 95 */    this.tokenBegin = tokenBegin;
/*  96:    */  }
/*  97:    */  
/*  98:    */  int getTokenBegin()
/*  99:    */  {
/* 100:100 */    return this.tokenBegin;
/* 101:    */  }
/* 102:    */  
/* 103:    */  private void setTokenEnd(int tokenEnd)
/* 104:    */  {
/* 105:105 */    this.tokenEnd = tokenEnd;
/* 106:    */  }
/* 107:    */  
/* 108:    */  int getTokenEnd()
/* 109:    */  {
/* 110:110 */    return this.tokenEnd;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public String toString()
/* 114:    */  {
/* 115:115 */    return "[ (" + this.tokenType + ") (" + getTokenText() + ")";
/* 116:    */  }
/* 117:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.base.Token
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */