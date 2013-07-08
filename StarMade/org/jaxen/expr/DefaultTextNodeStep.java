/*  1:   */package org.jaxen.expr;
/*  2:   */
/*  3:   */import org.jaxen.ContextSupport;
/*  4:   */import org.jaxen.Navigator;
/*  5:   */import org.jaxen.expr.iter.IterableAxis;
/*  6:   */
/* 58:   *//**
/* 59:   */ * @deprecated
/* 60:   */ */
/* 61:   */public class DefaultTextNodeStep
/* 62:   */  extends DefaultStep
/* 63:   */  implements TextNodeStep
/* 64:   */{
/* 65:   */  private static final long serialVersionUID = -3821960984972022948L;
/* 66:   */  
/* 67:   */  public DefaultTextNodeStep(IterableAxis axis, PredicateSet predicateSet)
/* 68:   */  {
/* 69:69 */    super(axis, predicateSet);
/* 70:   */  }
/* 71:   */  
/* 73:   */  public boolean matches(Object node, ContextSupport support)
/* 74:   */  {
/* 75:75 */    Navigator nav = support.getNavigator();
/* 76:   */    
/* 77:77 */    return nav.isText(node);
/* 78:   */  }
/* 79:   */  
/* 80:   */  public String getText()
/* 81:   */  {
/* 82:82 */    return getAxisName() + "::text()" + super.getText();
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultTextNodeStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */