package org.jaxen.saxpath.base;

import java.util.ArrayList;
import org.jaxen.saxpath.Axis;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.saxpath.XPathHandler;
import org.jaxen.saxpath.XPathSyntaxException;
import org.jaxen.saxpath.helpers.DefaultXPathHandler;

public class XPathReader
  implements org.jaxen.saxpath.XPathReader
{
  private ArrayList tokens;
  private XPathLexer lexer;
  private XPathHandler handler;
  private static XPathHandler defaultHandler = new DefaultXPathHandler();
  
  public XPathReader()
  {
    setXPathHandler(defaultHandler);
  }
  
  public void setXPathHandler(XPathHandler handler)
  {
    this.handler = handler;
  }
  
  public XPathHandler getXPathHandler()
  {
    return this.handler;
  }
  
  public void parse(String xpath)
    throws SAXPathException
  {
    setUpParse(xpath);
    getXPathHandler().startXPath();
    expr();
    getXPathHandler().endXPath();
    if (LA(1) != -1)
    {
      XPathSyntaxException local_ex = createSyntaxException("Unexpected '" + LT(1).getTokenText() + "'");
      throw local_ex;
    }
    this.lexer = null;
    this.tokens = null;
  }
  
  void setUpParse(String xpath)
  {
    this.tokens = new ArrayList();
    this.lexer = new XPathLexer(xpath);
  }
  
  private void pathExpr()
    throws SAXPathException
  {
    getXPathHandler().startPathExpr();
    switch (LA(1))
    {
    case 26: 
    case 29: 
      filterExpr();
      if ((LA(1) == 12) || (LA(1) == 13))
      {
        XPathSyntaxException local_ex = createSyntaxException("Node-set expected");
        throw local_ex;
      }
      break;
    case 23: 
    case 25: 
      filterExpr();
      if ((LA(1) == 12) || (LA(1) == 13)) {
        locationPath(false);
      }
      break;
    case 16: 
      if (((LA(2) == 23) && (!isNodeTypeName(LT(1)))) || ((LA(2) == 19) && (LA(4) == 23)))
      {
        filterExpr();
        if ((LA(1) == 12) || (LA(1) == 13)) {
          locationPath(false);
        }
      }
      else
      {
        locationPath(false);
      }
      break;
    case 9: 
    case 14: 
    case 15: 
    case 17: 
      locationPath(false);
      break;
    case 12: 
    case 13: 
      locationPath(true);
      break;
    case 10: 
    case 11: 
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 22: 
    case 24: 
    case 27: 
    case 28: 
    default: 
      XPathSyntaxException local_ex = createSyntaxException("Unexpected '" + LT(1).getTokenText() + "'");
      throw local_ex;
    }
    getXPathHandler().endPathExpr();
  }
  
  private void literal()
    throws SAXPathException
  {
    Token token = match(26);
    getXPathHandler().literal(token.getTokenText());
  }
  
  private void functionCall()
    throws SAXPathException
  {
    String prefix = null;
    String functionName = null;
    if (LA(2) == 19)
    {
      prefix = match(16).getTokenText();
      match(19);
    }
    else
    {
      prefix = "";
    }
    functionName = match(16).getTokenText();
    getXPathHandler().startFunction(prefix, functionName);
    match(23);
    arguments();
    match(24);
    getXPathHandler().endFunction();
  }
  
  private void arguments()
    throws SAXPathException
  {
    while (LA(1) != 24)
    {
      expr();
      if (LA(1) != 30) {
        break;
      }
      match(30);
    }
  }
  
  private void filterExpr()
    throws SAXPathException
  {
    getXPathHandler().startFilterExpr();
    switch (LA(1))
    {
    case 29: 
      Token token = match(29);
      getXPathHandler().number(Double.parseDouble(token.getTokenText()));
      break;
    case 26: 
      literal();
      break;
    case 23: 
      match(23);
      expr();
      match(24);
      break;
    case 16: 
      functionCall();
      break;
    case 25: 
      variableReference();
    }
    predicates();
    getXPathHandler().endFilterExpr();
  }
  
  private void variableReference()
    throws SAXPathException
  {
    match(25);
    String prefix = null;
    String variableName = null;
    if (LA(2) == 19)
    {
      prefix = match(16).getTokenText();
      match(19);
    }
    else
    {
      prefix = "";
    }
    variableName = match(16).getTokenText();
    getXPathHandler().variableReference(prefix, variableName);
  }
  
  void locationPath(boolean isAbsolute)
    throws SAXPathException
  {
    switch (LA(1))
    {
    case 12: 
    case 13: 
      if (isAbsolute) {
        absoluteLocationPath();
      } else {
        relativeLocationPath();
      }
      break;
    case 9: 
    case 14: 
    case 15: 
    case 16: 
    case 17: 
      relativeLocationPath();
      break;
    case 10: 
    case 11: 
    default: 
      XPathSyntaxException local_ex = createSyntaxException("Unexpected '" + LT(1).getTokenText() + "'");
      throw local_ex;
    }
  }
  
  private void absoluteLocationPath()
    throws SAXPathException
  {
    getXPathHandler().startAbsoluteLocationPath();
    switch (LA(1))
    {
    case 12: 
      match(12);
      switch (LA(1))
      {
      case 9: 
      case 14: 
      case 15: 
      case 16: 
      case 17: 
        steps();
      }
      break;
    case 13: 
      getXPathHandler().startAllNodeStep(12);
      getXPathHandler().endAllNodeStep();
      match(13);
      switch (LA(1))
      {
      case 9: 
      case 14: 
      case 15: 
      case 16: 
      case 17: 
        steps();
        break;
      case 10: 
      case 11: 
      case 12: 
      case 13: 
      default: 
        XPathSyntaxException local_ex = createSyntaxException("Location path cannot end with //");
        throw local_ex;
      }
      break;
    }
    getXPathHandler().endAbsoluteLocationPath();
  }
  
  private void relativeLocationPath()
    throws SAXPathException
  {
    getXPathHandler().startRelativeLocationPath();
    switch (LA(1))
    {
    case 12: 
      match(12);
      break;
    case 13: 
      getXPathHandler().startAllNodeStep(12);
      getXPathHandler().endAllNodeStep();
      match(13);
    }
    steps();
    getXPathHandler().endRelativeLocationPath();
  }
  
  private void steps()
    throws SAXPathException
  {
    switch (LA(1))
    {
    case 9: 
    case 14: 
    case 15: 
    case 16: 
    case 17: 
      step();
      break;
    case -1: 
      return;
    case 0: 
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
    default: 
      XPathSyntaxException local_ex = createSyntaxException("Expected one of '.', '..', '@', '*', <QName>");
      throw local_ex;
    }
    for (;;)
    {
      if ((LA(1) == 12) || (LA(1) == 13)) {
        switch (LA(1))
        {
        case 12: 
          match(12);
          break;
        case 13: 
          getXPathHandler().startAllNodeStep(12);
          getXPathHandler().endAllNodeStep();
          match(13);
        }
      } else {
        return;
      }
      switch (LA(1))
      {
      case 9: 
      case 14: 
      case 15: 
      case 16: 
      case 17: 
        step();
      }
    }
    XPathSyntaxException local_ex = createSyntaxException("Expected one of '.', '..', '@', '*', <QName>");
    throw local_ex;
  }
  
  void step()
    throws SAXPathException
  {
    int axis = 0;
    switch (LA(1))
    {
    case 14: 
    case 15: 
      abbrStep();
      return;
    case 17: 
      axis = axisSpecifier();
      break;
    case 16: 
      if (LA(2) == 20) {
        axis = axisSpecifier();
      } else {
        axis = 1;
      }
      break;
    case 9: 
      axis = 1;
    }
    nodeTest(axis);
  }
  
  private int axisSpecifier()
    throws SAXPathException
  {
    int axis = 0;
    switch (LA(1))
    {
    case 17: 
      match(17);
      axis = 9;
      break;
    case 16: 
      Token token = LT(1);
      axis = Axis.lookup(token.getTokenText());
      if (axis == 0) {
        throwInvalidAxis(token.getTokenText());
      }
      match(16);
      match(20);
      break;
    }
    return axis;
  }
  
  private void nodeTest(int axis)
    throws SAXPathException
  {
    switch (LA(1))
    {
    case 16: 
      switch (LA(2))
      {
      case 23: 
        nodeTypeTest(axis);
        break;
      default: 
        nameTest(axis);
      }
      break;
    case 9: 
      nameTest(axis);
      break;
    default: 
      XPathSyntaxException local_ex = createSyntaxException("Expected <QName> or *");
      throw local_ex;
    }
  }
  
  private void nodeTypeTest(int axis)
    throws SAXPathException
  {
    Token nodeTypeToken = match(16);
    String nodeType = nodeTypeToken.getTokenText();
    match(23);
    if ("processing-instruction".equals(nodeType))
    {
      String piName = "";
      if (LA(1) == 26) {
        piName = match(26).getTokenText();
      }
      match(24);
      getXPathHandler().startProcessingInstructionNodeStep(axis, piName);
      predicates();
      getXPathHandler().endProcessingInstructionNodeStep();
    }
    else if ("node".equals(nodeType))
    {
      match(24);
      getXPathHandler().startAllNodeStep(axis);
      predicates();
      getXPathHandler().endAllNodeStep();
    }
    else if ("text".equals(nodeType))
    {
      match(24);
      getXPathHandler().startTextNodeStep(axis);
      predicates();
      getXPathHandler().endTextNodeStep();
    }
    else if ("comment".equals(nodeType))
    {
      match(24);
      getXPathHandler().startCommentNodeStep(axis);
      predicates();
      getXPathHandler().endCommentNodeStep();
    }
    else
    {
      XPathSyntaxException piName = createSyntaxException("Expected node-type");
      throw piName;
    }
  }
  
  private void nameTest(int axis)
    throws SAXPathException
  {
    String prefix = null;
    String localName = null;
    switch (LA(2))
    {
    case 19: 
      switch (LA(1))
      {
      case 16: 
        prefix = match(16).getTokenText();
        match(19);
      }
      break;
    }
    switch (LA(1))
    {
    case 16: 
      localName = match(16).getTokenText();
      break;
    case 9: 
      match(9);
      localName = "*";
    }
    if (prefix == null) {
      prefix = "";
    }
    getXPathHandler().startNameStep(axis, prefix, localName);
    predicates();
    getXPathHandler().endNameStep();
  }
  
  private void abbrStep()
    throws SAXPathException
  {
    switch (LA(1))
    {
    case 14: 
      match(14);
      getXPathHandler().startAllNodeStep(11);
      predicates();
      getXPathHandler().endAllNodeStep();
      break;
    case 15: 
      match(15);
      getXPathHandler().startAllNodeStep(3);
      predicates();
      getXPathHandler().endAllNodeStep();
    }
  }
  
  private void predicates()
    throws SAXPathException
  {
    while (LA(1) == 21) {
      predicate();
    }
  }
  
  void predicate()
    throws SAXPathException
  {
    getXPathHandler().startPredicate();
    match(21);
    predicateExpr();
    match(22);
    getXPathHandler().endPredicate();
  }
  
  private void predicateExpr()
    throws SAXPathException
  {
    expr();
  }
  
  private void expr()
    throws SAXPathException
  {
    orExpr();
  }
  
  private void orExpr()
    throws SAXPathException
  {
    getXPathHandler().startOrExpr();
    andExpr();
    boolean create = false;
    switch (LA(1))
    {
    case 28: 
      create = true;
      match(28);
      orExpr();
    }
    getXPathHandler().endOrExpr(create);
  }
  
  private void andExpr()
    throws SAXPathException
  {
    getXPathHandler().startAndExpr();
    equalityExpr();
    boolean create = false;
    switch (LA(1))
    {
    case 27: 
      create = true;
      match(27);
      andExpr();
    }
    getXPathHandler().endAndExpr(create);
  }
  
  private void equalityExpr()
    throws SAXPathException
  {
    relationalExpr();
    for (int local_la = LA(1); (local_la == 1) || (local_la == 2); local_la = LA(1)) {
      switch (local_la)
      {
      case 1: 
        match(1);
        getXPathHandler().startEqualityExpr();
        relationalExpr();
        getXPathHandler().endEqualityExpr(1);
        break;
      case 2: 
        match(2);
        getXPathHandler().startEqualityExpr();
        relationalExpr();
        getXPathHandler().endEqualityExpr(2);
      }
    }
  }
  
  private void relationalExpr()
    throws SAXPathException
  {
    additiveExpr();
    for (int local_la = LA(1); (local_la == 3) || (local_la == 5) || (local_la == 4) || (local_la == 6); local_la = LA(1)) {
      switch (local_la)
      {
      case 3: 
        match(3);
        getXPathHandler().startRelationalExpr();
        additiveExpr();
        getXPathHandler().endRelationalExpr(3);
        break;
      case 5: 
        match(5);
        getXPathHandler().startRelationalExpr();
        additiveExpr();
        getXPathHandler().endRelationalExpr(5);
        break;
      case 6: 
        match(6);
        getXPathHandler().startRelationalExpr();
        additiveExpr();
        getXPathHandler().endRelationalExpr(6);
        break;
      case 4: 
        match(4);
        getXPathHandler().startRelationalExpr();
        additiveExpr();
        getXPathHandler().endRelationalExpr(4);
      }
    }
  }
  
  private void additiveExpr()
    throws SAXPathException
  {
    multiplicativeExpr();
    for (int local_la = LA(1); (local_la == 7) || (local_la == 8); local_la = LA(1)) {
      switch (local_la)
      {
      case 7: 
        match(7);
        getXPathHandler().startAdditiveExpr();
        multiplicativeExpr();
        getXPathHandler().endAdditiveExpr(7);
        break;
      case 8: 
        match(8);
        getXPathHandler().startAdditiveExpr();
        multiplicativeExpr();
        getXPathHandler().endAdditiveExpr(8);
      }
    }
  }
  
  private void multiplicativeExpr()
    throws SAXPathException
  {
    unaryExpr();
    for (int local_la = LA(1); (local_la == 31) || (local_la == 11) || (local_la == 10); local_la = LA(1)) {
      switch (local_la)
      {
      case 9: 
      case 31: 
        match(31);
        getXPathHandler().startMultiplicativeExpr();
        unaryExpr();
        getXPathHandler().endMultiplicativeExpr(9);
        break;
      case 11: 
        match(11);
        getXPathHandler().startMultiplicativeExpr();
        unaryExpr();
        getXPathHandler().endMultiplicativeExpr(11);
        break;
      case 10: 
        match(10);
        getXPathHandler().startMultiplicativeExpr();
        unaryExpr();
        getXPathHandler().endMultiplicativeExpr(10);
      }
    }
  }
  
  private void unaryExpr()
    throws SAXPathException
  {
    switch (LA(1))
    {
    case 8: 
      getXPathHandler().startUnaryExpr();
      match(8);
      unaryExpr();
      getXPathHandler().endUnaryExpr(12);
      break;
    default: 
      unionExpr();
    }
  }
  
  private void unionExpr()
    throws SAXPathException
  {
    getXPathHandler().startUnionExpr();
    pathExpr();
    boolean create = false;
    switch (LA(1))
    {
    case 18: 
      match(18);
      create = true;
      expr();
    }
    getXPathHandler().endUnionExpr(create);
  }
  
  private Token match(int tokenType)
    throws XPathSyntaxException
  {
    LT(1);
    Token token = (Token)this.tokens.get(0);
    if (token.getTokenType() == tokenType)
    {
      this.tokens.remove(0);
      return token;
    }
    XPathSyntaxException local_ex = createSyntaxException("Expected: " + TokenTypes.getTokenText(tokenType));
    throw local_ex;
  }
  
  private int LA(int position)
  {
    return LT(position).getTokenType();
  }
  
  private Token LT(int position)
  {
    if (this.tokens.size() <= position - 1) {
      for (int local_i = 0; local_i < position; local_i++) {
        this.tokens.add(this.lexer.nextToken());
      }
    }
    return (Token)this.tokens.get(position - 1);
  }
  
  private boolean isNodeTypeName(Token name)
  {
    String text = name.getTokenText();
    return ("node".equals(text)) || ("comment".equals(text)) || ("text".equals(text)) || ("processing-instruction".equals(text));
  }
  
  private XPathSyntaxException createSyntaxException(String message)
  {
    String xpath = this.lexer.getXPath();
    int position = LT(1).getTokenBegin();
    return new XPathSyntaxException(xpath, position, message);
  }
  
  private void throwInvalidAxis(String invalidAxis)
    throws SAXPathException
  {
    String xpath = this.lexer.getXPath();
    int position = LT(1).getTokenBegin();
    String message = "Expected valid axis name instead of [" + invalidAxis + "]";
    throw new XPathSyntaxException(xpath, position, message);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.saxpath.base.XPathReader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */