package org.jaxen.expr;

import org.jaxen.ContextSupport;
import org.jaxen.expr.iter.IterableAxis;

/**
 * @deprecated
 */
public class DefaultAllNodeStep
  extends DefaultStep
  implements AllNodeStep
{
  private static final long serialVersionUID = 292886316770123856L;
  
  public DefaultAllNodeStep(IterableAxis axis, PredicateSet predicateSet)
  {
    super(axis, predicateSet);
  }
  
  public String toString()
  {
    return "[(DefaultAllNodeStep): " + getAxisName() + "]";
  }
  
  public String getText()
  {
    return getAxisName() + "::node()" + super.getText();
  }
  
  public boolean matches(Object node, ContextSupport contextSupport)
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultAllNodeStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */