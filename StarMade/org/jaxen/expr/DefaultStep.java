package org.jaxen.expr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.UnsupportedAxisException;
import org.jaxen.expr.iter.IterableAxis;
import org.jaxen.saxpath.Axis;

/**
 * @deprecated
 */
public abstract class DefaultStep
  implements Step
{
  private IterableAxis axis;
  private PredicateSet predicates;
  
  public DefaultStep(IterableAxis axis, PredicateSet predicates)
  {
    this.axis = axis;
    this.predicates = predicates;
  }
  
  public void addPredicate(Predicate predicate)
  {
    this.predicates.addPredicate(predicate);
  }
  
  public List getPredicates()
  {
    return this.predicates.getPredicates();
  }
  
  public PredicateSet getPredicateSet()
  {
    return this.predicates;
  }
  
  public int getAxis()
  {
    return this.axis.value();
  }
  
  public IterableAxis getIterableAxis()
  {
    return this.axis;
  }
  
  public String getAxisName()
  {
    return Axis.lookup(getAxis());
  }
  
  public String getText()
  {
    return this.predicates.getText();
  }
  
  public String toString()
  {
    return getIterableAxis() + " " + super.toString();
  }
  
  public void simplify()
  {
    this.predicates.simplify();
  }
  
  public Iterator axisIterator(Object contextNode, ContextSupport support)
    throws UnsupportedAxisException
  {
    return getIterableAxis().iterator(contextNode, support);
  }
  
  public List evaluate(Context context)
    throws JaxenException
  {
    List contextNodeSet = context.getNodeSet();
    IdentitySet unique = new IdentitySet();
    int contextSize = contextNodeSet.size();
    ArrayList interimSet = new ArrayList();
    ArrayList newNodeSet = new ArrayList();
    ContextSupport support = context.getContextSupport();
    for (int local_i = 0; local_i < contextSize; local_i++)
    {
      Object eachContextNode = contextNodeSet.get(local_i);
      Iterator axisNodeIter = this.axis.iterator(eachContextNode, support);
      while (axisNodeIter.hasNext())
      {
        Object eachAxisNode = axisNodeIter.next();
        if ((!unique.contains(eachAxisNode)) && (matches(eachAxisNode, support)))
        {
          unique.add(eachAxisNode);
          interimSet.add(eachAxisNode);
        }
      }
      newNodeSet.addAll(getPredicateSet().evaluatePredicates(interimSet, support));
      interimSet.clear();
    }
    return newNodeSet;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */