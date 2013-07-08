package org.jaxen.expr;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;

class DefaultPathExpr
  extends DefaultExpr
  implements PathExpr
{
  private static final long serialVersionUID = -6593934674727004281L;
  private Expr filterExpr;
  private LocationPath locationPath;
  
  DefaultPathExpr(Expr filterExpr, LocationPath locationPath)
  {
    this.filterExpr = filterExpr;
    this.locationPath = locationPath;
  }
  
  public Expr getFilterExpr()
  {
    return this.filterExpr;
  }
  
  public void setFilterExpr(Expr filterExpr)
  {
    this.filterExpr = filterExpr;
  }
  
  public LocationPath getLocationPath()
  {
    return this.locationPath;
  }
  
  public String toString()
  {
    if (getLocationPath() != null) {
      return "[(DefaultPathExpr): " + getFilterExpr() + ", " + getLocationPath() + "]";
    }
    return "[(DefaultPathExpr): " + getFilterExpr() + "]";
  }
  
  public String getText()
  {
    StringBuffer buf = new StringBuffer();
    if (getFilterExpr() != null) {
      buf.append(getFilterExpr().getText());
    }
    if (getLocationPath() != null)
    {
      if (!getLocationPath().getSteps().isEmpty()) {
        buf.append("/");
      }
      buf.append(getLocationPath().getText());
    }
    return buf.toString();
  }
  
  public Expr simplify()
  {
    if (getFilterExpr() != null) {
      setFilterExpr(getFilterExpr().simplify());
    }
    if (getLocationPath() != null) {
      getLocationPath().simplify();
    }
    if ((getFilterExpr() == null) && (getLocationPath() == null)) {
      return null;
    }
    if (getLocationPath() == null) {
      return getFilterExpr();
    }
    if (getFilterExpr() == null) {
      return getLocationPath();
    }
    return this;
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    Object results = null;
    Context pathContext = null;
    if (getFilterExpr() != null)
    {
      results = getFilterExpr().evaluate(context);
      pathContext = new Context(context.getContextSupport());
      pathContext.setNodeSet(convertToList(results));
    }
    if (getLocationPath() != null) {
      return getLocationPath().evaluate(pathContext);
    }
    return results;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultPathExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */