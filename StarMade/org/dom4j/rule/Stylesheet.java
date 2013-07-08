package org.dom4j.rule;

import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

public class Stylesheet
{
  private RuleManager ruleManager = new RuleManager();
  private String modeName;
  
  public void addRule(Rule rule)
  {
    this.ruleManager.addRule(rule);
  }
  
  public void removeRule(Rule rule)
  {
    this.ruleManager.removeRule(rule);
  }
  
  public void run(Object input)
    throws Exception
  {
    run(input, this.modeName);
  }
  
  public void run(Object input, String mode)
    throws Exception
  {
    if ((input instanceof Node)) {
      run((Node)input, mode);
    } else if ((input instanceof List)) {
      run((List)input, mode);
    }
  }
  
  public void run(List list)
    throws Exception
  {
    run(list, this.modeName);
  }
  
  public void run(List list, String mode)
    throws Exception
  {
    int local_i = 0;
    int size = list.size();
    while (local_i < size)
    {
      Object object = list.get(local_i);
      if ((object instanceof Node)) {
        run((Node)object, mode);
      }
      local_i++;
    }
  }
  
  public void run(Node node)
    throws Exception
  {
    run(node, this.modeName);
  }
  
  public void run(Node node, String mode)
    throws Exception
  {
    Mode mod = this.ruleManager.getMode(mode);
    mod.fireRule(node);
  }
  
  public void applyTemplates(Object input, org.dom4j.XPath xpath)
    throws Exception
  {
    applyTemplates(input, xpath, this.modeName);
  }
  
  public void applyTemplates(Object input, org.dom4j.XPath xpath, String mode)
    throws Exception
  {
    Mode mod = this.ruleManager.getMode(mode);
    List list = xpath.selectNodes(input);
    Iterator local_it = list.iterator();
    while (local_it.hasNext())
    {
      Node current = (Node)local_it.next();
      mod.fireRule(current);
    }
  }
  
  /**
   * @deprecated
   */
  public void applyTemplates(Object input, org.jaxen.XPath xpath)
    throws Exception
  {
    applyTemplates(input, xpath, this.modeName);
  }
  
  /**
   * @deprecated
   */
  public void applyTemplates(Object input, org.jaxen.XPath xpath, String mode)
    throws Exception
  {
    Mode mod = this.ruleManager.getMode(mode);
    List list = xpath.selectNodes(input);
    Iterator local_it = list.iterator();
    while (local_it.hasNext())
    {
      Node current = (Node)local_it.next();
      mod.fireRule(current);
    }
  }
  
  public void applyTemplates(Object input)
    throws Exception
  {
    applyTemplates(input, this.modeName);
  }
  
  public void applyTemplates(Object input, String mode)
    throws Exception
  {
    Mode mod = this.ruleManager.getMode(mode);
    if ((input instanceof Element))
    {
      Element element = (Element)input;
      int local_i = 0;
      int size = element.nodeCount();
      while (local_i < size)
      {
        Node node = element.node(local_i);
        mod.fireRule(node);
        local_i++;
      }
    }
    else if ((input instanceof Document))
    {
      Document element = (Document)input;
      int local_i = 0;
      int size = element.nodeCount();
      while (local_i < size)
      {
        Node node = element.node(local_i);
        mod.fireRule(node);
        local_i++;
      }
    }
    else if ((input instanceof List))
    {
      List element = (List)input;
      int local_i = 0;
      int size = element.size();
      while (local_i < size)
      {
        Object node = element.get(local_i);
        if ((node instanceof Element)) {
          applyTemplates((Element)node, mode);
        } else if ((node instanceof Document)) {
          applyTemplates((Document)node, mode);
        }
        local_i++;
      }
    }
  }
  
  public void clear()
  {
    this.ruleManager.clear();
  }
  
  public String getModeName()
  {
    return this.modeName;
  }
  
  public void setModeName(String modeName)
  {
    this.modeName = modeName;
  }
  
  public Action getValueOfAction()
  {
    return this.ruleManager.getValueOfAction();
  }
  
  public void setValueOfAction(Action valueOfAction)
  {
    this.ruleManager.setValueOfAction(valueOfAction);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.rule.Stylesheet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */