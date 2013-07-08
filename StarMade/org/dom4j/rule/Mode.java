package org.dom4j.rule;

import java.util.HashMap;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

public class Mode
{
  private RuleSet[] ruleSets = new RuleSet[14];
  private Map elementNameRuleSets;
  private Map attributeNameRuleSets;
  
  public void fireRule(Node node)
    throws Exception
  {
    if (node != null)
    {
      Rule rule = getMatchingRule(node);
      if (rule != null)
      {
        Action action = rule.getAction();
        if (action != null) {
          action.run(node);
        }
      }
    }
  }
  
  public void applyTemplates(Element element)
    throws Exception
  {
    int local_i = 0;
    int size = element.attributeCount();
    while (local_i < size)
    {
      Attribute attribute = element.attribute(local_i);
      fireRule(attribute);
      local_i++;
    }
    int local_i = 0;
    int size = element.nodeCount();
    while (local_i < size)
    {
      Node attribute = element.node(local_i);
      fireRule(attribute);
      local_i++;
    }
  }
  
  public void applyTemplates(Document document)
    throws Exception
  {
    int local_i = 0;
    int size = document.nodeCount();
    while (local_i < size)
    {
      Node node = document.node(local_i);
      fireRule(node);
      local_i++;
    }
  }
  
  public void addRule(Rule rule)
  {
    int matchType = rule.getMatchType();
    String name = rule.getMatchesNodeName();
    if (name != null) {
      if (matchType == 1) {
        this.elementNameRuleSets = addToNameMap(this.elementNameRuleSets, name, rule);
      } else if (matchType == 2) {
        this.attributeNameRuleSets = addToNameMap(this.attributeNameRuleSets, name, rule);
      }
    }
    if (matchType >= 14) {
      matchType = 0;
    }
    if (matchType == 0)
    {
      int local_i = 1;
      int size = this.ruleSets.length;
      while (local_i < size)
      {
        RuleSet ruleSet = this.ruleSets[local_i];
        if (ruleSet != null) {
          ruleSet.addRule(rule);
        }
        local_i++;
      }
    }
    getRuleSet(matchType).addRule(rule);
  }
  
  public void removeRule(Rule rule)
  {
    int matchType = rule.getMatchType();
    String name = rule.getMatchesNodeName();
    if (name != null) {
      if (matchType == 1) {
        removeFromNameMap(this.elementNameRuleSets, name, rule);
      } else if (matchType == 2) {
        removeFromNameMap(this.attributeNameRuleSets, name, rule);
      }
    }
    if (matchType >= 14) {
      matchType = 0;
    }
    getRuleSet(matchType).removeRule(rule);
    if (matchType != 0) {
      getRuleSet(0).removeRule(rule);
    }
  }
  
  public Rule getMatchingRule(Node node)
  {
    int matchType = node.getNodeType();
    if (matchType == 1)
    {
      if (this.elementNameRuleSets != null)
      {
        String name = node.getName();
        RuleSet ruleSet = (RuleSet)this.elementNameRuleSets.get(name);
        if (ruleSet != null)
        {
          Rule answer = ruleSet.getMatchingRule(node);
          if (answer != null) {
            return answer;
          }
        }
      }
    }
    else if ((matchType == 2) && (this.attributeNameRuleSets != null))
    {
      String name = node.getName();
      RuleSet ruleSet = (RuleSet)this.attributeNameRuleSets.get(name);
      if (ruleSet != null)
      {
        Rule answer = ruleSet.getMatchingRule(node);
        if (answer != null) {
          return answer;
        }
      }
    }
    if ((matchType < 0) || (matchType >= this.ruleSets.length)) {
      matchType = 0;
    }
    Rule name = null;
    RuleSet ruleSet = this.ruleSets[matchType];
    if (ruleSet != null) {
      name = ruleSet.getMatchingRule(node);
    }
    if ((name == null) && (matchType != 0))
    {
      ruleSet = this.ruleSets[0];
      if (ruleSet != null) {
        name = ruleSet.getMatchingRule(node);
      }
    }
    return name;
  }
  
  protected RuleSet getRuleSet(int matchType)
  {
    RuleSet ruleSet = this.ruleSets[matchType];
    if (ruleSet == null)
    {
      ruleSet = new RuleSet();
      this.ruleSets[matchType] = ruleSet;
      if (matchType != 0)
      {
        RuleSet allRules = this.ruleSets[0];
        if (allRules != null) {
          ruleSet.addAll(allRules);
        }
      }
    }
    return ruleSet;
  }
  
  protected Map addToNameMap(Map map, String name, Rule rule)
  {
    if (map == null) {
      map = new HashMap();
    }
    RuleSet ruleSet = (RuleSet)map.get(name);
    if (ruleSet == null)
    {
      ruleSet = new RuleSet();
      map.put(name, ruleSet);
    }
    ruleSet.addRule(rule);
    return map;
  }
  
  protected void removeFromNameMap(Map map, String name, Rule rule)
  {
    if (map != null)
    {
      RuleSet ruleSet = (RuleSet)map.get(name);
      if (ruleSet != null) {
        ruleSet.removeRule(rule);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.rule.Mode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */