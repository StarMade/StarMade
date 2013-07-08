package org.jaxen.expr;

import java.io.Serializable;
import java.util.List;

public abstract interface Predicated
  extends Serializable
{
  public abstract void addPredicate(Predicate paramPredicate);
  
  public abstract List getPredicates();
  
  public abstract PredicateSet getPredicateSet();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.Predicated
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */