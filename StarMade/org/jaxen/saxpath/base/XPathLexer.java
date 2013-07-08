package org.jaxen.saxpath.base;

class XPathLexer
{
  private String xpath;
  private int currentPosition;
  private int endPosition;
  private boolean expectOperator = false;
  
  XPathLexer(String xpath)
  {
    setXPath(xpath);
  }
  
  private void setXPath(String xpath)
  {
    this.xpath = xpath;
    this.currentPosition = 0;
    this.endPosition = xpath.length();
  }
  
  String getXPath()
  {
    return this.xpath;
  }
  
  Token nextToken()
  {
    Token token = null;
    do
    {
      token = null;
      switch (LA(1))
      {
      case '$': 
        token = dollar();
        break;
      case '"': 
      case '\'': 
        token = literal();
        break;
      case '/': 
        token = slashes();
        break;
      case ',': 
        token = comma();
        break;
      case '(': 
        token = leftParen();
        break;
      case ')': 
        token = rightParen();
        break;
      case '[': 
        token = leftBracket();
        break;
      case ']': 
        token = rightBracket();
        break;
      case '+': 
        token = plus();
        break;
      case '-': 
        token = minus();
        break;
      case '<': 
      case '>': 
        token = relationalOperator();
        break;
      case '=': 
        token = equals();
        break;
      case '!': 
        if (LA(2) == '=') {
          token = notEquals();
        }
        break;
      case '|': 
        token = pipe();
        break;
      case '@': 
        token = at();
        break;
      case ':': 
        if (LA(2) == ':') {
          token = doubleColon();
        } else {
          token = colon();
        }
        break;
      case '*': 
        token = star();
        break;
      case '.': 
        switch (LA(2))
        {
        case '0': 
        case '1': 
        case '2': 
        case '3': 
        case '4': 
        case '5': 
        case '6': 
        case '7': 
        case '8': 
        case '9': 
          token = number();
          break;
        default: 
          token = dots();
        }
        break;
      case '0': 
      case '1': 
      case '2': 
      case '3': 
      case '4': 
      case '5': 
      case '6': 
      case '7': 
      case '8': 
      case '9': 
        token = number();
        break;
      case '\t': 
      case '\n': 
      case '\r': 
      case ' ': 
        token = whitespace();
        break;
      case '\013': 
      case '\f': 
      case '\016': 
      case '\017': 
      case '\020': 
      case '\021': 
      case '\022': 
      case '\023': 
      case '\024': 
      case '\025': 
      case '\026': 
      case '\027': 
      case '\030': 
      case '\031': 
      case '\032': 
      case '\033': 
      case '\034': 
      case '\035': 
      case '\036': 
      case '\037': 
      case '#': 
      case '%': 
      case '&': 
      case ';': 
      case '?': 
      case 'A': 
      case 'B': 
      case 'C': 
      case 'D': 
      case 'E': 
      case 'F': 
      case 'G': 
      case 'H': 
      case 'I': 
      case 'J': 
      case 'K': 
      case 'L': 
      case 'M': 
      case 'N': 
      case 'O': 
      case 'P': 
      case 'Q': 
      case 'R': 
      case 'S': 
      case 'T': 
      case 'U': 
      case 'V': 
      case 'W': 
      case 'X': 
      case 'Y': 
      case 'Z': 
      case '\\': 
      case '^': 
      case '_': 
      case '`': 
      case 'a': 
      case 'b': 
      case 'c': 
      case 'd': 
      case 'e': 
      case 'f': 
      case 'g': 
      case 'h': 
      case 'i': 
      case 'j': 
      case 'k': 
      case 'l': 
      case 'm': 
      case 'n': 
      case 'o': 
      case 'p': 
      case 'q': 
      case 'r': 
      case 's': 
      case 't': 
      case 'u': 
      case 'v': 
      case 'w': 
      case 'x': 
      case 'y': 
      case 'z': 
      case '{': 
      default: 
        if (Verifier.isXMLNCNameStartCharacter(LA(1))) {
          token = identifierOrOperatorName();
        }
        break;
      }
      if (token == null) {
        if (!hasMoreChars()) {
          token = new Token(-1, getXPath(), this.currentPosition, this.endPosition);
        } else {
          token = new Token(-3, getXPath(), this.currentPosition, this.endPosition);
        }
      }
    } while (token.getTokenType() == -2);
    switch (token.getTokenType())
    {
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 23: 
    case 25: 
    case 27: 
    case 28: 
    case 30: 
    case 31: 
      this.expectOperator = false;
      break;
    case 9: 
    case 14: 
    case 15: 
    case 16: 
    case 22: 
    case 24: 
    case 26: 
    case 29: 
    default: 
      this.expectOperator = true;
    }
    return token;
  }
  
  private Token identifierOrOperatorName()
  {
    Token token = null;
    if (this.expectOperator) {
      token = operatorName();
    } else {
      token = identifier();
    }
    return token;
  }
  
  private Token identifier()
  {
    Token token = null;
    int start = this.currentPosition;
    while ((hasMoreChars()) && (Verifier.isXMLNCNameCharacter(LA(1)))) {
      consume();
    }
    token = new Token(16, getXPath(), start, this.currentPosition);
    return token;
  }
  
  private Token operatorName()
  {
    Token token = null;
    switch (LA(1))
    {
    case 'a': 
      token = and();
      break;
    case 'o': 
      token = or();
      break;
    case 'm': 
      token = mod();
      break;
    case 'd': 
      token = div();
    }
    return token;
  }
  
  private Token mod()
  {
    Token token = null;
    if ((LA(1) == 'm') && (LA(2) == 'o') && (LA(3) == 'd'))
    {
      token = new Token(10, getXPath(), this.currentPosition, this.currentPosition + 3);
      consume();
      consume();
      consume();
    }
    return token;
  }
  
  private Token div()
  {
    Token token = null;
    if ((LA(1) == 'd') && (LA(2) == 'i') && (LA(3) == 'v'))
    {
      token = new Token(11, getXPath(), this.currentPosition, this.currentPosition + 3);
      consume();
      consume();
      consume();
    }
    return token;
  }
  
  private Token and()
  {
    Token token = null;
    if ((LA(1) == 'a') && (LA(2) == 'n') && (LA(3) == 'd'))
    {
      token = new Token(27, getXPath(), this.currentPosition, this.currentPosition + 3);
      consume();
      consume();
      consume();
    }
    return token;
  }
  
  private Token or()
  {
    Token token = null;
    if ((LA(1) == 'o') && (LA(2) == 'r'))
    {
      token = new Token(28, getXPath(), this.currentPosition, this.currentPosition + 2);
      consume();
      consume();
    }
    return token;
  }
  
  private Token number()
  {
    int start = this.currentPosition;
    boolean periodAllowed = true;
    for (;;)
    {
      switch (LA(1))
      {
      case '.': 
        if (!periodAllowed) {
          break label99;
        }
        periodAllowed = false;
        consume();
        break;
      case '0': 
      case '1': 
      case '2': 
      case '3': 
      case '4': 
      case '5': 
      case '6': 
      case '7': 
      case '8': 
      case '9': 
        consume();
      }
    }
    label99:
    return new Token(29, getXPath(), start, this.currentPosition);
  }
  
  private Token whitespace()
  {
    consume();
    while (hasMoreChars()) {
      switch (LA(1))
      {
      case '\t': 
      case '\n': 
      case '\r': 
      case ' ': 
        consume();
        break;
      }
    }
    return new Token(-2, getXPath(), 0, 0);
  }
  
  private Token comma()
  {
    Token token = new Token(30, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token equals()
  {
    Token token = new Token(1, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token minus()
  {
    Token token = new Token(8, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token plus()
  {
    Token token = new Token(7, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token dollar()
  {
    Token token = new Token(25, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token pipe()
  {
    Token token = new Token(18, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token at()
  {
    Token token = new Token(17, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token colon()
  {
    Token token = new Token(19, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token doubleColon()
  {
    Token token = new Token(20, getXPath(), this.currentPosition, this.currentPosition + 2);
    consume();
    consume();
    return token;
  }
  
  private Token notEquals()
  {
    Token token = new Token(2, getXPath(), this.currentPosition, this.currentPosition + 2);
    consume();
    consume();
    return token;
  }
  
  private Token relationalOperator()
  {
    Token token = null;
    switch (LA(1))
    {
    case '<': 
      if (LA(2) == '=')
      {
        token = new Token(4, getXPath(), this.currentPosition, this.currentPosition + 2);
        consume();
      }
      else
      {
        token = new Token(3, getXPath(), this.currentPosition, this.currentPosition + 1);
      }
      consume();
      break;
    case '>': 
      if (LA(2) == '=')
      {
        token = new Token(6, getXPath(), this.currentPosition, this.currentPosition + 2);
        consume();
      }
      else
      {
        token = new Token(5, getXPath(), this.currentPosition, this.currentPosition + 1);
      }
      consume();
    }
    return token;
  }
  
  private Token star()
  {
    int tokenType = this.expectOperator ? 31 : 9;
    Token token = new Token(tokenType, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token literal()
  {
    Token token = null;
    char match = LA(1);
    consume();
    int start = this.currentPosition;
    while ((token == null) && (hasMoreChars()))
    {
      if (LA(1) == match) {
        token = new Token(26, getXPath(), start, this.currentPosition);
      }
      consume();
    }
    return token;
  }
  
  private Token dots()
  {
    Token token = null;
    switch (LA(2))
    {
    case '.': 
      token = new Token(15, getXPath(), this.currentPosition, this.currentPosition + 2);
      consume();
      consume();
      break;
    default: 
      token = new Token(14, getXPath(), this.currentPosition, this.currentPosition + 1);
      consume();
    }
    return token;
  }
  
  private Token leftBracket()
  {
    Token token = new Token(21, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token rightBracket()
  {
    Token token = new Token(22, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token leftParen()
  {
    Token token = new Token(23, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token rightParen()
  {
    Token token = new Token(24, getXPath(), this.currentPosition, this.currentPosition + 1);
    consume();
    return token;
  }
  
  private Token slashes()
  {
    Token token = null;
    switch (LA(2))
    {
    case '/': 
      token = new Token(13, getXPath(), this.currentPosition, this.currentPosition + 2);
      consume();
      consume();
      break;
    default: 
      token = new Token(12, getXPath(), this.currentPosition, this.currentPosition + 1);
      consume();
    }
    return token;
  }
  
  private char LA(int local_i)
  {
    if (this.currentPosition + (local_i - 1) >= this.endPosition) {
      return 65535;
    }
    return getXPath().charAt(this.currentPosition + (local_i - 1));
  }
  
  private void consume()
  {
    this.currentPosition += 1;
  }
  
  private boolean hasMoreChars()
  {
    return this.currentPosition < this.endPosition;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.saxpath.base.XPathLexer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */