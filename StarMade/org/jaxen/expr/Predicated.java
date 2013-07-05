package org.jaxen.expr;

import java.io.Serializable;
import java.util.List;

public abstract interface Predicated extends Serializable
{
  public abstract void addPredicate(Predicate paramPredicate);

  public abstract List getPredicates();

  public abstract PredicateSet getPredicateSet();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.Predicated
 * JD-Core Version:    0.6.2
 */