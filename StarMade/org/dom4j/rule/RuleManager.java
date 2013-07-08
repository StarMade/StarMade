package org.dom4j.rule;

import java.io.PrintStream;
import java.util.HashMap;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.rule.pattern.NodeTypePattern;

public class RuleManager
{
  private HashMap modes = new HashMap();
  private int appearenceCount;
  private Action valueOfAction;
  
  public Mode getMode(String modeName)
  {
    Mode mode = (Mode)this.modes.get(modeName);
    if (mode == null)
    {
      mode = createMode();
      this.modes.put(modeName, mode);
    }
    return mode;
  }
  
  public void addRule(Rule rule)
  {
    rule.setAppearenceCount(++this.appearenceCount);
    Mode mode = getMode(rule.getMode());
    Rule[] childRules = rule.getUnionRules();
    if (childRules != null)
    {
      int local_i = 0;
      int size = childRules.length;
      while (local_i < size)
      {
        mode.addRule(childRules[local_i]);
        local_i++;
      }
    }
    else
    {
      mode.addRule(rule);
    }
  }
  
  public void removeRule(Rule rule)
  {
    Mode mode = getMode(rule.getMode());
    Rule[] childRules = rule.getUnionRules();
    if (childRules != null)
    {
      int local_i = 0;
      int size = childRules.length;
      while (local_i < size)
      {
        mode.removeRule(childRules[local_i]);
        local_i++;
      }
    }
    else
    {
      mode.removeRule(rule);
    }
  }
  
  public Rule getMatchingRule(String modeName, Node node)
  {
    Mode mode = (Mode)this.modes.get(modeName);
    if (mode != null) {
      return mode.getMatchingRule(node);
    }
    System.out.println("Warning: No Mode for mode: " + mode);
    return null;
  }
  
  public void clear()
  {
    this.modes.clear();
    this.appearenceCount = 0;
  }
  
  public Action getValueOfAction()
  {
    return this.valueOfAction;
  }
  
  public void setValueOfAction(Action valueOfAction)
  {
    this.valueOfAction = valueOfAction;
  }
  
  protected Mode createMode()
  {
    Mode mode = new Mode();
    addDefaultRules(mode);
    return mode;
  }
  
  protected void addDefaultRules(Mode mode)
  {
    Action applyTemplates = new Action()
    {
      private final Mode val$mode;
      
      public void run(Node node)
        throws Exception
      {
        if ((node instanceof Element)) {
          this.val$mode.applyTemplates((Element)node);
        } else if ((node instanceof Document)) {
          this.val$mode.applyTemplates((Document)node);
        }
      }
    };
    Action valueOf = getValueOfAction();
    addDefaultRule(mode, NodeTypePattern.ANY_DOCUMENT, applyTemplates);
    addDefaultRule(mode, NodeTypePattern.ANY_ELEMENT, applyTemplates);
    if (valueOf != null)
    {
      addDefaultRule(mode, NodeTypePattern.ANY_ATTRIBUTE, valueOf);
      addDefaultRule(mode, NodeTypePattern.ANY_TEXT, valueOf);
    }
  }
  
  protected void addDefaultRule(Mode mode, Pattern pattern, Action action)
  {
    Rule rule = createDefaultRule(pattern, action);
    mode.addRule(rule);
  }
  
  protected Rule createDefaultRule(Pattern pattern, Action action)
  {
    Rule rule = new Rule(pattern, action);
    rule.setImportPrecedence(-1);
    return rule;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.rule.RuleManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */