package org.jaxen.pattern;

import java.io.PrintStream;
import java.util.LinkedList;
import org.jaxen.JaxenException;
import org.jaxen.JaxenHandler;
import org.jaxen.expr.Expr;
import org.jaxen.expr.FilterExpr;
import org.jaxen.expr.XPathFactory;

public class PatternHandler
  extends JaxenHandler
{
  private Pattern pattern;
  
  public Pattern getPattern()
  {
    return getPattern(true);
  }
  
  public Pattern getPattern(boolean shouldSimplify)
  {
    if ((shouldSimplify) && (!this.simplified))
    {
      this.pattern.simplify();
      this.simplified = true;
    }
    return this.pattern;
  }
  
  public void endXPath()
  {
    this.pattern = ((Pattern)pop());
    System.out.println("stack is: " + this.stack);
    popFrame();
  }
  
  public void endPathExpr()
  {
    LinkedList frame = popFrame();
    System.out.println("endPathExpr(): " + frame);
    push(frame.removeFirst());
  }
  
  public void startAbsoluteLocationPath()
  {
    pushFrame();
    push(createAbsoluteLocationPath());
  }
  
  public void endAbsoluteLocationPath()
    throws JaxenException
  {
    endLocationPath();
  }
  
  public void startRelativeLocationPath()
  {
    pushFrame();
    push(createRelativeLocationPath());
  }
  
  public void endRelativeLocationPath()
    throws JaxenException
  {
    endLocationPath();
  }
  
  protected void endLocationPath()
    throws JaxenException
  {
    LinkedList list = popFrame();
    System.out.println("endLocationPath: " + list);
    LocationPathPattern locationPath = (LocationPathPattern)list.removeFirst();
    push(locationPath);
    boolean doneNodeTest = false;
    while (!list.isEmpty())
    {
      Object filter = list.removeFirst();
      if ((filter instanceof NodeTest))
      {
        if (doneNodeTest)
        {
          LocationPathPattern parent = new LocationPathPattern((NodeTest)filter);
          locationPath.setParentPattern(parent);
          locationPath = parent;
          doneNodeTest = false;
        }
        else
        {
          locationPath.setNodeTest((NodeTest)filter);
        }
      }
      else if ((filter instanceof FilterExpr))
      {
        locationPath.addFilter((FilterExpr)filter);
      }
      else if ((filter instanceof LocationPathPattern))
      {
        LocationPathPattern parent = (LocationPathPattern)filter;
        locationPath.setParentPattern(parent);
        locationPath = parent;
        doneNodeTest = false;
      }
    }
  }
  
  public void startNameStep(int axis, String prefix, String localName)
  {
    pushFrame();
    short nodeType = 1;
    switch (axis)
    {
    case 9: 
      nodeType = 2;
      break;
    case 10: 
      nodeType = 13;
    }
    if ((prefix != null) && (prefix.length() > 0) && (!prefix.equals("*"))) {
      push(new NamespaceTest(prefix, nodeType));
    }
    if ((localName != null) && (localName.length() > 0) && (!localName.equals("*"))) {
      push(new NameTest(localName, nodeType));
    }
  }
  
  public void startTextNodeStep(int axis)
  {
    pushFrame();
    push(new NodeTypeTest((short)3));
  }
  
  public void startCommentNodeStep(int axis)
  {
    pushFrame();
    push(new NodeTypeTest((short)8));
  }
  
  public void startAllNodeStep(int axis)
  {
    pushFrame();
    push(AnyNodeTest.getInstance());
  }
  
  public void startProcessingInstructionNodeStep(int axis, String name)
  {
    pushFrame();
    push(new NodeTypeTest((short)7));
  }
  
  protected void endStep()
  {
    LinkedList list = popFrame();
    if (!list.isEmpty())
    {
      push(list.removeFirst());
      if (!list.isEmpty()) {
        System.out.println("List should now be empty!" + list);
      }
    }
  }
  
  public void startUnionExpr() {}
  
  public void endUnionExpr(boolean create)
    throws JaxenException
  {
    if (create)
    {
      Expr rhs = (Expr)pop();
      Expr lhs = (Expr)pop();
      push(getXPathFactory().createUnionExpr(lhs, rhs));
    }
  }
  
  protected Pattern createAbsoluteLocationPath()
  {
    return new LocationPathPattern(NodeTypeTest.DOCUMENT_TEST);
  }
  
  protected Pattern createRelativeLocationPath()
  {
    return new LocationPathPattern();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.pattern.PatternHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */