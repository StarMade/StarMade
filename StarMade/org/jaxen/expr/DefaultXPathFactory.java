package org.jaxen.expr;

import org.jaxen.JaxenException;
import org.jaxen.expr.iter.IterableAncestorAxis;
import org.jaxen.expr.iter.IterableAncestorOrSelfAxis;
import org.jaxen.expr.iter.IterableAttributeAxis;
import org.jaxen.expr.iter.IterableAxis;
import org.jaxen.expr.iter.IterableChildAxis;
import org.jaxen.expr.iter.IterableDescendantAxis;
import org.jaxen.expr.iter.IterableDescendantOrSelfAxis;
import org.jaxen.expr.iter.IterableFollowingAxis;
import org.jaxen.expr.iter.IterableFollowingSiblingAxis;
import org.jaxen.expr.iter.IterableNamespaceAxis;
import org.jaxen.expr.iter.IterableParentAxis;
import org.jaxen.expr.iter.IterablePrecedingAxis;
import org.jaxen.expr.iter.IterablePrecedingSiblingAxis;
import org.jaxen.expr.iter.IterableSelfAxis;

public class DefaultXPathFactory
  implements XPathFactory
{
  public XPathExpr createXPath(Expr rootExpr)
    throws JaxenException
  {
    return new DefaultXPathExpr(rootExpr);
  }
  
  public PathExpr createPathExpr(FilterExpr filterExpr, LocationPath locationPath)
    throws JaxenException
  {
    return new DefaultPathExpr(filterExpr, locationPath);
  }
  
  public LocationPath createRelativeLocationPath()
    throws JaxenException
  {
    return new DefaultRelativeLocationPath();
  }
  
  public LocationPath createAbsoluteLocationPath()
    throws JaxenException
  {
    return new DefaultAbsoluteLocationPath();
  }
  
  public BinaryExpr createOrExpr(Expr lhs, Expr rhs)
    throws JaxenException
  {
    return new DefaultOrExpr(lhs, rhs);
  }
  
  public BinaryExpr createAndExpr(Expr lhs, Expr rhs)
    throws JaxenException
  {
    return new DefaultAndExpr(lhs, rhs);
  }
  
  public BinaryExpr createEqualityExpr(Expr lhs, Expr rhs, int equalityOperator)
    throws JaxenException
  {
    switch (equalityOperator)
    {
    case 1: 
      return new DefaultEqualsExpr(lhs, rhs);
    case 2: 
      return new DefaultNotEqualsExpr(lhs, rhs);
    }
    throw new JaxenException("Unhandled operator in createEqualityExpr(): " + equalityOperator);
  }
  
  public BinaryExpr createRelationalExpr(Expr lhs, Expr rhs, int relationalOperator)
    throws JaxenException
  {
    switch (relationalOperator)
    {
    case 3: 
      return new DefaultLessThanExpr(lhs, rhs);
    case 5: 
      return new DefaultGreaterThanExpr(lhs, rhs);
    case 4: 
      return new DefaultLessThanEqualExpr(lhs, rhs);
    case 6: 
      return new DefaultGreaterThanEqualExpr(lhs, rhs);
    }
    throw new JaxenException("Unhandled operator in createRelationalExpr(): " + relationalOperator);
  }
  
  public BinaryExpr createAdditiveExpr(Expr lhs, Expr rhs, int additiveOperator)
    throws JaxenException
  {
    switch (additiveOperator)
    {
    case 7: 
      return new DefaultPlusExpr(lhs, rhs);
    case 8: 
      return new DefaultMinusExpr(lhs, rhs);
    }
    throw new JaxenException("Unhandled operator in createAdditiveExpr(): " + additiveOperator);
  }
  
  public BinaryExpr createMultiplicativeExpr(Expr lhs, Expr rhs, int multiplicativeOperator)
    throws JaxenException
  {
    switch (multiplicativeOperator)
    {
    case 9: 
      return new DefaultMultiplyExpr(lhs, rhs);
    case 11: 
      return new DefaultDivExpr(lhs, rhs);
    case 10: 
      return new DefaultModExpr(lhs, rhs);
    }
    throw new JaxenException("Unhandled operator in createMultiplicativeExpr(): " + multiplicativeOperator);
  }
  
  public Expr createUnaryExpr(Expr expr, int unaryOperator)
    throws JaxenException
  {
    switch (unaryOperator)
    {
    case 12: 
      return new DefaultUnaryExpr(expr);
    }
    return expr;
  }
  
  public UnionExpr createUnionExpr(Expr lhs, Expr rhs)
    throws JaxenException
  {
    return new DefaultUnionExpr(lhs, rhs);
  }
  
  public FilterExpr createFilterExpr(Expr expr)
    throws JaxenException
  {
    return new DefaultFilterExpr(expr, createPredicateSet());
  }
  
  public FunctionCallExpr createFunctionCallExpr(String prefix, String functionName)
    throws JaxenException
  {
    return new DefaultFunctionCallExpr(prefix, functionName);
  }
  
  public NumberExpr createNumberExpr(int number)
    throws JaxenException
  {
    return new DefaultNumberExpr(new Double(number));
  }
  
  public NumberExpr createNumberExpr(double number)
    throws JaxenException
  {
    return new DefaultNumberExpr(new Double(number));
  }
  
  public LiteralExpr createLiteralExpr(String literal)
    throws JaxenException
  {
    return new DefaultLiteralExpr(literal);
  }
  
  public VariableReferenceExpr createVariableReferenceExpr(String prefix, String variable)
    throws JaxenException
  {
    return new DefaultVariableReferenceExpr(prefix, variable);
  }
  
  public Step createNameStep(int axis, String prefix, String localName)
    throws JaxenException
  {
    IterableAxis iter = getIterableAxis(axis);
    return new DefaultNameStep(iter, prefix, localName, createPredicateSet());
  }
  
  public Step createTextNodeStep(int axis)
    throws JaxenException
  {
    IterableAxis iter = getIterableAxis(axis);
    return new DefaultTextNodeStep(iter, createPredicateSet());
  }
  
  public Step createCommentNodeStep(int axis)
    throws JaxenException
  {
    IterableAxis iter = getIterableAxis(axis);
    return new DefaultCommentNodeStep(iter, createPredicateSet());
  }
  
  public Step createAllNodeStep(int axis)
    throws JaxenException
  {
    IterableAxis iter = getIterableAxis(axis);
    return new DefaultAllNodeStep(iter, createPredicateSet());
  }
  
  public Step createProcessingInstructionNodeStep(int axis, String piName)
    throws JaxenException
  {
    IterableAxis iter = getIterableAxis(axis);
    return new DefaultProcessingInstructionNodeStep(iter, piName, createPredicateSet());
  }
  
  public Predicate createPredicate(Expr predicateExpr)
    throws JaxenException
  {
    return new DefaultPredicate(predicateExpr);
  }
  
  protected IterableAxis getIterableAxis(int axis)
    throws JaxenException
  {
    switch (axis)
    {
    case 1: 
      return new IterableChildAxis(axis);
    case 2: 
      return new IterableDescendantAxis(axis);
    case 3: 
      return new IterableParentAxis(axis);
    case 5: 
      return new IterableFollowingSiblingAxis(axis);
    case 6: 
      return new IterablePrecedingSiblingAxis(axis);
    case 7: 
      return new IterableFollowingAxis(axis);
    case 8: 
      return new IterablePrecedingAxis(axis);
    case 9: 
      return new IterableAttributeAxis(axis);
    case 10: 
      return new IterableNamespaceAxis(axis);
    case 11: 
      return new IterableSelfAxis(axis);
    case 12: 
      return new IterableDescendantOrSelfAxis(axis);
    case 13: 
      return new IterableAncestorOrSelfAxis(axis);
    case 4: 
      return new IterableAncestorAxis(axis);
    }
    throw new JaxenException("Unrecognized axis code: " + axis);
  }
  
  public PredicateSet createPredicateSet()
    throws JaxenException
  {
    return new PredicateSet();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultXPathFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */