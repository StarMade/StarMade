package org.jaxen.pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.expr.FilterExpr;
import org.jaxen.util.SingletonList;

public class LocationPathPattern
  extends Pattern
{
  private NodeTest nodeTest = AnyNodeTest.getInstance();
  private Pattern parentPattern;
  private Pattern ancestorPattern;
  private List filters;
  private boolean absolute;
  
  public LocationPathPattern() {}
  
  public LocationPathPattern(NodeTest nodeTest)
  {
    this.nodeTest = nodeTest;
  }
  
  public Pattern simplify()
  {
    if (this.parentPattern != null) {
      this.parentPattern = this.parentPattern.simplify();
    }
    if (this.ancestorPattern != null) {
      this.ancestorPattern = this.ancestorPattern.simplify();
    }
    if (this.filters == null)
    {
      if ((this.parentPattern == null) && (this.ancestorPattern == null)) {
        return this.nodeTest;
      }
      if ((this.parentPattern != null) && (this.ancestorPattern == null) && ((this.nodeTest instanceof AnyNodeTest))) {
        return this.parentPattern;
      }
    }
    return this;
  }
  
  public void addFilter(FilterExpr filter)
  {
    if (this.filters == null) {
      this.filters = new ArrayList();
    }
    this.filters.add(filter);
  }
  
  public void setParentPattern(Pattern parentPattern)
  {
    this.parentPattern = parentPattern;
  }
  
  public void setAncestorPattern(Pattern ancestorPattern)
  {
    this.ancestorPattern = ancestorPattern;
  }
  
  public void setNodeTest(NodeTest nodeTest)
    throws JaxenException
  {
    if ((this.nodeTest instanceof AnyNodeTest)) {
      this.nodeTest = nodeTest;
    } else {
      throw new JaxenException("Attempt to overwrite nodeTest: " + this.nodeTest + " with: " + nodeTest);
    }
  }
  
  public boolean matches(Object node, Context context)
    throws JaxenException
  {
    Navigator navigator = context.getNavigator();
    if (!this.nodeTest.matches(node, context)) {
      return false;
    }
    if (this.parentPattern != null)
    {
      Object parent = navigator.getParentNode(node);
      if (parent == null) {
        return false;
      }
      if (!this.parentPattern.matches(parent, context)) {
        return false;
      }
    }
    if (this.ancestorPattern != null) {
      for (Object parent = navigator.getParentNode(node); !this.ancestorPattern.matches(parent, context); parent = navigator.getParentNode(parent))
      {
        if (parent == null) {
          return false;
        }
        if (navigator.isDocument(parent)) {
          return false;
        }
      }
    }
    if (this.filters != null)
    {
      List parent = new SingletonList(node);
      context.setNodeSet(parent);
      boolean answer = true;
      Iterator iter = this.filters.iterator();
      while (iter.hasNext())
      {
        FilterExpr filter = (FilterExpr)iter.next();
        if (!filter.asBoolean(context))
        {
          answer = false;
          break;
        }
      }
      context.setNodeSet(parent);
      return answer;
    }
    return true;
  }
  
  public double getPriority()
  {
    if (this.filters != null) {
      return 0.5D;
    }
    return this.nodeTest.getPriority();
  }
  
  public short getMatchType()
  {
    return this.nodeTest.getMatchType();
  }
  
  public String getText()
  {
    StringBuffer buffer = new StringBuffer();
    if (this.absolute) {
      buffer.append("/");
    }
    if (this.ancestorPattern != null)
    {
      String text = this.ancestorPattern.getText();
      if (text.length() > 0)
      {
        buffer.append(text);
        buffer.append("//");
      }
    }
    if (this.parentPattern != null)
    {
      String text = this.parentPattern.getText();
      if (text.length() > 0)
      {
        buffer.append(text);
        buffer.append("/");
      }
    }
    buffer.append(this.nodeTest.getText());
    if (this.filters != null)
    {
      buffer.append("[");
      Iterator text = this.filters.iterator();
      while (text.hasNext())
      {
        FilterExpr filter = (FilterExpr)text.next();
        buffer.append(filter.getText());
      }
      buffer.append("]");
    }
    return buffer.toString();
  }
  
  public String toString()
  {
    return super.toString() + "[ absolute: " + this.absolute + " parent: " + this.parentPattern + " ancestor: " + this.ancestorPattern + " filters: " + this.filters + " nodeTest: " + this.nodeTest + " ]";
  }
  
  public boolean isAbsolute()
  {
    return this.absolute;
  }
  
  public void setAbsolute(boolean absolute)
  {
    this.absolute = absolute;
  }
  
  public boolean hasAnyNodeTest()
  {
    return this.nodeTest instanceof AnyNodeTest;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.pattern.LocationPathPattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */