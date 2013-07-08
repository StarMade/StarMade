package org.jaxen.expr;

import org.jaxen.ContextSupport;
import org.jaxen.Navigator;
import org.jaxen.expr.iter.IterableAxis;

/**
 * @deprecated
 */
public class DefaultProcessingInstructionNodeStep
  extends DefaultStep
  implements ProcessingInstructionNodeStep
{
  private static final long serialVersionUID = -4825000697808126927L;
  private String name;
  
  public DefaultProcessingInstructionNodeStep(IterableAxis axis, String name, PredicateSet predicateSet)
  {
    super(axis, predicateSet);
    this.name = name;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getText()
  {
    StringBuffer buf = new StringBuffer();
    buf.append(getAxisName());
    buf.append("::processing-instruction(");
    String name = getName();
    if ((name != null) && (name.length() != 0))
    {
      buf.append("'");
      buf.append(name);
      buf.append("'");
    }
    buf.append(")");
    buf.append(super.getText());
    return buf.toString();
  }
  
  public boolean matches(Object node, ContextSupport support)
  {
    Navigator nav = support.getNavigator();
    if (nav.isProcessingInstruction(node))
    {
      String name = getName();
      if ((name == null) || (name.length() == 0)) {
        return true;
      }
      return name.equals(nav.getProcessingInstructionTarget(node));
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultProcessingInstructionNodeStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */