package org.dom4j.xpath;

import java.util.ArrayList;
import org.dom4j.InvalidXPathException;
import org.dom4j.Node;
import org.dom4j.XPathException;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.SimpleVariableContext;
import org.jaxen.VariableContext;
import org.jaxen.XPathFunctionContext;
import org.jaxen.dom4j.DocumentNavigator;
import org.jaxen.pattern.PatternParser;
import org.jaxen.saxpath.SAXPathException;

public class XPathPattern
  implements org.dom4j.rule.Pattern
{
  private String text;
  private org.jaxen.pattern.Pattern pattern;
  private Context context;
  
  public XPathPattern(org.jaxen.pattern.Pattern pattern)
  {
    this.pattern = pattern;
    this.text = pattern.getText();
    this.context = new Context(getContextSupport());
  }
  
  public XPathPattern(String text)
  {
    this.text = text;
    this.context = new Context(getContextSupport());
    try
    {
      this.pattern = PatternParser.parse(text);
    }
    catch (SAXPathException local_e)
    {
      throw new InvalidXPathException(text, local_e.getMessage());
    }
    catch (Throwable local_e)
    {
      throw new InvalidXPathException(text, local_e);
    }
  }
  
  public boolean matches(Node node)
  {
    try
    {
      ArrayList list = new ArrayList(1);
      list.add(node);
      this.context.setNodeSet(list);
      return this.pattern.matches(node, this.context);
    }
    catch (JaxenException list)
    {
      handleJaxenException(list);
    }
    return false;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public double getPriority()
  {
    return this.pattern.getPriority();
  }
  
  public org.dom4j.rule.Pattern[] getUnionPatterns()
  {
    org.jaxen.pattern.Pattern[] patterns = this.pattern.getUnionPatterns();
    if (patterns != null)
    {
      int size = patterns.length;
      XPathPattern[] answer = new XPathPattern[size];
      for (int local_i = 0; local_i < size; local_i++) {
        answer[local_i] = new XPathPattern(patterns[local_i]);
      }
      return answer;
    }
    return null;
  }
  
  public short getMatchType()
  {
    return this.pattern.getMatchType();
  }
  
  public String getMatchesNodeName()
  {
    return this.pattern.getMatchesNodeName();
  }
  
  public void setVariableContext(VariableContext variableContext)
  {
    this.context.getContextSupport().setVariableContext(variableContext);
  }
  
  public String toString()
  {
    return "[XPathPattern: text: " + this.text + " Pattern: " + this.pattern + "]";
  }
  
  protected ContextSupport getContextSupport()
  {
    return new ContextSupport(new SimpleNamespaceContext(), XPathFunctionContext.getInstance(), new SimpleVariableContext(), DocumentNavigator.getInstance());
  }
  
  protected void handleJaxenException(JaxenException exception)
    throws XPathException
  {
    throw new XPathException(this.text, exception);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.xpath.XPathPattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */