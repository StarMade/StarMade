/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.ContextSupport;
/*  4:   */import org.jaxen.expr.iter.IterableAxis;
/*  5:   */
/* 57:   *//**
/* 58:   */ * @deprecated
/* 59:   */ */
/* 60:   */public class DefaultAllNodeStep
/* 61:   */  extends DefaultStep
/* 62:   */  implements AllNodeStep
/* 63:   */{
/* 64:   */  private static final long serialVersionUID = 292886316770123856L;
/* 65:   */  
/* 66:   */  public DefaultAllNodeStep(IterableAxis axis, PredicateSet predicateSet)
/* 67:   */  {
/* 68:68 */    super(axis, predicateSet);
/* 69:   */  }
/* 70:   */  
/* 71:   */  public String toString()
/* 72:   */  {
/* 73:73 */    return "[(DefaultAllNodeStep): " + getAxisName() + "]";
/* 74:   */  }
/* 75:   */  
/* 76:   */  public String getText()
/* 77:   */  {
/* 78:78 */    return getAxisName() + "::node()" + super.getText();
/* 79:   */  }
/* 80:   */  
/* 82:   */  public boolean matches(Object node, ContextSupport contextSupport)
/* 83:   */  {
/* 84:84 */    return true;
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultAllNodeStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */