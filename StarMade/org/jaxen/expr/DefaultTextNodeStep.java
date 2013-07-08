package org.jaxen.expr;

import org.jaxen.ContextSupport;
import org.jaxen.Navigator;
import org.jaxen.expr.iter.IterableAxis;

/**
 * @deprecated
 */
public class DefaultTextNodeStep
  extends DefaultStep
  implements TextNodeStep
{
  private static final long serialVersionUID = -3821960984972022948L;
  
  public DefaultTextNodeStep(IterableAxis axis, PredicateSet predicateSet)
  {
    super(axis, predicateSet);
  }
  
  public boolean matches(Object node, ContextSupport support)
  {
    Navigator nav = support.getNavigator();
    return nav.isText(node);
  }
  
  public String getText()
  {
    return getAxisName() + "::text()" + super.getText();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultTextNodeStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */