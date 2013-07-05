package org.jaxen.saxpath;

public abstract interface XPathHandler
{
  public abstract void startXPath()
    throws SAXPathException;

  public abstract void endXPath()
    throws SAXPathException;

  public abstract void startPathExpr()
    throws SAXPathException;

  public abstract void endPathExpr()
    throws SAXPathException;

  public abstract void startAbsoluteLocationPath()
    throws SAXPathException;

  public abstract void endAbsoluteLocationPath()
    throws SAXPathException;

  public abstract void startRelativeLocationPath()
    throws SAXPathException;

  public abstract void endRelativeLocationPath()
    throws SAXPathException;

  public abstract void startNameStep(int paramInt, String paramString1, String paramString2)
    throws SAXPathException;

  public abstract void endNameStep()
    throws SAXPathException;

  public abstract void startTextNodeStep(int paramInt)
    throws SAXPathException;

  public abstract void endTextNodeStep()
    throws SAXPathException;

  public abstract void startCommentNodeStep(int paramInt)
    throws SAXPathException;

  public abstract void endCommentNodeStep()
    throws SAXPathException;

  public abstract void startAllNodeStep(int paramInt)
    throws SAXPathException;

  public abstract void endAllNodeStep()
    throws SAXPathException;

  public abstract void startProcessingInstructionNodeStep(int paramInt, String paramString)
    throws SAXPathException;

  public abstract void endProcessingInstructionNodeStep()
    throws SAXPathException;

  public abstract void startPredicate()
    throws SAXPathException;

  public abstract void endPredicate()
    throws SAXPathException;

  public abstract void startFilterExpr()
    throws SAXPathException;

  public abstract void endFilterExpr()
    throws SAXPathException;

  public abstract void startOrExpr()
    throws SAXPathException;

  public abstract void endOrExpr(boolean paramBoolean)
    throws SAXPathException;

  public abstract void startAndExpr()
    throws SAXPathException;

  public abstract void endAndExpr(boolean paramBoolean)
    throws SAXPathException;

  public abstract void startEqualityExpr()
    throws SAXPathException;

  public abstract void endEqualityExpr(int paramInt)
    throws SAXPathException;

  public abstract void startRelationalExpr()
    throws SAXPathException;

  public abstract void endRelationalExpr(int paramInt)
    throws SAXPathException;

  public abstract void startAdditiveExpr()
    throws SAXPathException;

  public abstract void endAdditiveExpr(int paramInt)
    throws SAXPathException;

  public abstract void startMultiplicativeExpr()
    throws SAXPathException;

  public abstract void endMultiplicativeExpr(int paramInt)
    throws SAXPathException;

  public abstract void startUnaryExpr()
    throws SAXPathException;

  public abstract void endUnaryExpr(int paramInt)
    throws SAXPathException;

  public abstract void startUnionExpr()
    throws SAXPathException;

  public abstract void endUnionExpr(boolean paramBoolean)
    throws SAXPathException;

  public abstract void number(int paramInt)
    throws SAXPathException;

  public abstract void number(double paramDouble)
    throws SAXPathException;

  public abstract void literal(String paramString)
    throws SAXPathException;

  public abstract void variableReference(String paramString1, String paramString2)
    throws SAXPathException;

  public abstract void startFunction(String paramString1, String paramString2)
    throws SAXPathException;

  public abstract void endFunction()
    throws SAXPathException;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.XPathHandler
 * JD-Core Version:    0.6.2
 */