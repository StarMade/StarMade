package org.jaxen.expr;

import org.jaxen.JaxenException;

public abstract interface XPathFactory
{
  public abstract XPathExpr createXPath(Expr paramExpr)
    throws JaxenException;
  
  public abstract PathExpr createPathExpr(FilterExpr paramFilterExpr, LocationPath paramLocationPath)
    throws JaxenException;
  
  public abstract LocationPath createRelativeLocationPath()
    throws JaxenException;
  
  public abstract LocationPath createAbsoluteLocationPath()
    throws JaxenException;
  
  public abstract BinaryExpr createOrExpr(Expr paramExpr1, Expr paramExpr2)
    throws JaxenException;
  
  public abstract BinaryExpr createAndExpr(Expr paramExpr1, Expr paramExpr2)
    throws JaxenException;
  
  public abstract BinaryExpr createEqualityExpr(Expr paramExpr1, Expr paramExpr2, int paramInt)
    throws JaxenException;
  
  public abstract BinaryExpr createRelationalExpr(Expr paramExpr1, Expr paramExpr2, int paramInt)
    throws JaxenException;
  
  public abstract BinaryExpr createAdditiveExpr(Expr paramExpr1, Expr paramExpr2, int paramInt)
    throws JaxenException;
  
  public abstract BinaryExpr createMultiplicativeExpr(Expr paramExpr1, Expr paramExpr2, int paramInt)
    throws JaxenException;
  
  public abstract Expr createUnaryExpr(Expr paramExpr, int paramInt)
    throws JaxenException;
  
  public abstract UnionExpr createUnionExpr(Expr paramExpr1, Expr paramExpr2)
    throws JaxenException;
  
  public abstract FilterExpr createFilterExpr(Expr paramExpr)
    throws JaxenException;
  
  public abstract FunctionCallExpr createFunctionCallExpr(String paramString1, String paramString2)
    throws JaxenException;
  
  public abstract NumberExpr createNumberExpr(int paramInt)
    throws JaxenException;
  
  public abstract NumberExpr createNumberExpr(double paramDouble)
    throws JaxenException;
  
  public abstract LiteralExpr createLiteralExpr(String paramString)
    throws JaxenException;
  
  public abstract VariableReferenceExpr createVariableReferenceExpr(String paramString1, String paramString2)
    throws JaxenException;
  
  public abstract Step createNameStep(int paramInt, String paramString1, String paramString2)
    throws JaxenException;
  
  public abstract Step createAllNodeStep(int paramInt)
    throws JaxenException;
  
  public abstract Step createCommentNodeStep(int paramInt)
    throws JaxenException;
  
  public abstract Step createTextNodeStep(int paramInt)
    throws JaxenException;
  
  public abstract Step createProcessingInstructionNodeStep(int paramInt, String paramString)
    throws JaxenException;
  
  public abstract Predicate createPredicate(Expr paramExpr)
    throws JaxenException;
  
  public abstract PredicateSet createPredicateSet()
    throws JaxenException;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.XPathFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */