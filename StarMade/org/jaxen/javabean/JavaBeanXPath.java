package org.jaxen.javabean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jaxen.BaseXPath;
import org.jaxen.Context;
import org.jaxen.JaxenException;

public class JavaBeanXPath
  extends BaseXPath
{
  private static final long serialVersionUID = -1567521943360266313L;
  
  public JavaBeanXPath(String xpathExpr)
    throws JaxenException
  {
    super(xpathExpr, DocumentNavigator.getInstance());
  }
  
  protected Context getContext(Object node)
  {
    if ((node instanceof Context)) {
      return (Context)node;
    }
    if ((node instanceof Element)) {
      return super.getContext(node);
    }
    if ((node instanceof List))
    {
      List newList = new ArrayList();
      Iterator listIter = ((List)node).iterator();
      while (listIter.hasNext()) {
        newList.add(new Element(null, "root", listIter.next()));
      }
      return super.getContext(newList);
    }
    return super.getContext(new Element(null, "root", node));
  }
  
  public Object evaluate(Object node)
    throws JaxenException
  {
    Object result = super.evaluate(node);
    if ((result instanceof Element)) {
      return ((Element)result).getObject();
    }
    if ((result instanceof Collection))
    {
      List newList = new ArrayList();
      Iterator listIter = ((Collection)result).iterator();
      while (listIter.hasNext())
      {
        Object member = listIter.next();
        if ((member instanceof Element)) {
          newList.add(((Element)member).getObject());
        } else {
          newList.add(member);
        }
      }
      return newList;
    }
    return result;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.javabean.JavaBeanXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */