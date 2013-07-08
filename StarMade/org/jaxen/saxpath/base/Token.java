package org.jaxen.saxpath.base;

class Token
{
  private int tokenType;
  private String parseText;
  private int tokenBegin;
  private int tokenEnd;
  
  Token(int tokenType, String parseText, int tokenBegin, int tokenEnd)
  {
    setTokenType(tokenType);
    setParseText(parseText);
    setTokenBegin(tokenBegin);
    setTokenEnd(tokenEnd);
  }
  
  private void setTokenType(int tokenType)
  {
    this.tokenType = tokenType;
  }
  
  int getTokenType()
  {
    return this.tokenType;
  }
  
  private void setParseText(String parseText)
  {
    this.parseText = parseText;
  }
  
  String getTokenText()
  {
    return this.parseText.substring(getTokenBegin(), getTokenEnd());
  }
  
  private void setTokenBegin(int tokenBegin)
  {
    this.tokenBegin = tokenBegin;
  }
  
  int getTokenBegin()
  {
    return this.tokenBegin;
  }
  
  private void setTokenEnd(int tokenEnd)
  {
    this.tokenEnd = tokenEnd;
  }
  
  int getTokenEnd()
  {
    return this.tokenEnd;
  }
  
  public String toString()
  {
    return "[ (" + this.tokenType + ") (" + getTokenText() + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.saxpath.base.Token
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */