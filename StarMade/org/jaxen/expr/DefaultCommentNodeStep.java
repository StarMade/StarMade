package org.jaxen.expr;

import org.jaxen.ContextSupport;
import org.jaxen.Navigator;
import org.jaxen.expr.iter.IterableAxis;

/**
 * @deprecated
 */
public class DefaultCommentNodeStep
  extends DefaultStep
  implements CommentNodeStep
{
  private static final long serialVersionUID = 4340788283861875606L;
  
  public DefaultCommentNodeStep(IterableAxis axis, PredicateSet predicateSet)
  {
    super(axis, predicateSet);
  }
  
  public String toString()
  {
    return "[(DefaultCommentNodeStep): " + getAxis() + "]";
  }
  
  public String getText()
  {
    return getAxisName() + "::comment()";
  }
  
  public boolean matches(Object node, ContextSupport contextSupport)
  {
    Navigator nav = contextSupport.getNavigator();
    return nav.isComment(node);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultCommentNodeStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */