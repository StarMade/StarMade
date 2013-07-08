package org.jaxen.expr;

import java.util.Collections;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.util.SingletonList;

/**
 * @deprecated
 */
public class DefaultAbsoluteLocationPath
  extends DefaultLocationPath
{
  private static final long serialVersionUID = 2174836928310146874L;
  
  public String toString()
  {
    return "[(DefaultAbsoluteLocationPath): " + super.toString() + "]";
  }
  
  public boolean isAbsolute()
  {
    return true;
  }
  
  public String getText()
  {
    return "/" + super.getText();
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    ContextSupport support = context.getContextSupport();
    Navigator nav = support.getNavigator();
    Context absContext = new Context(support);
    List contextNodes = context.getNodeSet();
    if (contextNodes.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    Object firstNode = contextNodes.get(0);
    Object docNode = nav.getDocumentNode(firstNode);
    if (docNode == null) {
      return Collections.EMPTY_LIST;
    }
    List list = new SingletonList(docNode);
    absContext.setNodeSet(list);
    return super.evaluate(absContext);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultAbsoluteLocationPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */