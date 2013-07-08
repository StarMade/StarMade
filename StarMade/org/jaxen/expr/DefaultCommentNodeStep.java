/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.ContextSupport;
/*  4:   */import org.jaxen.Navigator;
/*  5:   */import org.jaxen.expr.iter.IterableAxis;
/*  6:   */
/* 57:   *//**
/* 58:   */ * @deprecated
/* 59:   */ */
/* 60:   */public class DefaultCommentNodeStep
/* 61:   */  extends DefaultStep
/* 62:   */  implements CommentNodeStep
/* 63:   */{
/* 64:   */  private static final long serialVersionUID = 4340788283861875606L;
/* 65:   */  
/* 66:   */  public DefaultCommentNodeStep(IterableAxis axis, PredicateSet predicateSet)
/* 67:   */  {
/* 68:68 */    super(axis, predicateSet);
/* 69:   */  }
/* 70:   */  
/* 71:   */  public String toString()
/* 72:   */  {
/* 73:73 */    return "[(DefaultCommentNodeStep): " + getAxis() + "]";
/* 74:   */  }
/* 75:   */  
/* 76:   */  public String getText()
/* 77:   */  {
/* 78:78 */    return getAxisName() + "::comment()";
/* 79:   */  }
/* 80:   */  
/* 82:   */  public boolean matches(Object node, ContextSupport contextSupport)
/* 83:   */  {
/* 84:84 */    Navigator nav = contextSupport.getNavigator();
/* 85:   */    
/* 86:86 */    return nav.isComment(node);
/* 87:   */  }
/* 88:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultCommentNodeStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */