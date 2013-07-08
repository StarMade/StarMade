package org.dom4j.rule;

import java.util.ArrayList;
import java.util.Collections;
import org.dom4j.Node;

public class RuleSet
{
  private ArrayList rules = new ArrayList();
  private Rule[] ruleArray;
  
  public String toString()
  {
    return super.toString() + " [RuleSet: " + this.rules + " ]";
  }
  
  public Rule getMatchingRule(Node node)
  {
    Rule[] matches = getRuleArray();
    for (int local_i = matches.length - 1; local_i >= 0; local_i--)
    {
      Rule rule = matches[local_i];
      if (rule.matches(node)) {
        return rule;
      }
    }
    return null;
  }
  
  public void addRule(Rule rule)
  {
    this.rules.add(rule);
    this.ruleArray = null;
  }
  
  public void removeRule(Rule rule)
  {
    this.rules.remove(rule);
    this.ruleArray = null;
  }
  
  public void addAll(RuleSet that)
  {
    this.rules.addAll(that.rules);
    this.ruleArray = null;
  }
  
  protected Rule[] getRuleArray()
  {
    if (this.ruleArray == null)
    {
      Collections.sort(this.rules);
      int size = this.rules.size();
      this.ruleArray = new Rule[size];
      this.rules.toArray(this.ruleArray);
    }
    return this.ruleArray;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.rule.RuleSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */