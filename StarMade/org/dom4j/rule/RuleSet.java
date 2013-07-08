/*  1:   */package org.dom4j.rule;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import java.util.Collections;
/*  5:   */import org.dom4j.Node;
/*  6:   */
/* 26:   */public class RuleSet
/* 27:   */{
/* 28:28 */  private ArrayList rules = new ArrayList();
/* 29:   */  
/* 31:   */  private Rule[] ruleArray;
/* 32:   */  
/* 35:   */  public String toString()
/* 36:   */  {
/* 37:37 */    return super.toString() + " [RuleSet: " + this.rules + " ]";
/* 38:   */  }
/* 39:   */  
/* 48:   */  public Rule getMatchingRule(Node node)
/* 49:   */  {
/* 50:50 */    Rule[] matches = getRuleArray();
/* 51:   */    
/* 52:52 */    for (int i = matches.length - 1; i >= 0; i--) {
/* 53:53 */      Rule rule = matches[i];
/* 54:   */      
/* 55:55 */      if (rule.matches(node)) {
/* 56:56 */        return rule;
/* 57:   */      }
/* 58:   */    }
/* 59:   */    
/* 60:60 */    return null;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public void addRule(Rule rule) {
/* 64:64 */    this.rules.add(rule);
/* 65:65 */    this.ruleArray = null;
/* 66:   */  }
/* 67:   */  
/* 68:   */  public void removeRule(Rule rule) {
/* 69:69 */    this.rules.remove(rule);
/* 70:70 */    this.ruleArray = null;
/* 71:   */  }
/* 72:   */  
/* 78:   */  public void addAll(RuleSet that)
/* 79:   */  {
/* 80:80 */    this.rules.addAll(that.rules);
/* 81:81 */    this.ruleArray = null;
/* 82:   */  }
/* 83:   */  
/* 89:   */  protected Rule[] getRuleArray()
/* 90:   */  {
/* 91:91 */    if (this.ruleArray == null) {
/* 92:92 */      Collections.sort(this.rules);
/* 93:   */      
/* 94:94 */      int size = this.rules.size();
/* 95:95 */      this.ruleArray = new Rule[size];
/* 96:96 */      this.rules.toArray(this.ruleArray);
/* 97:   */    }
/* 98:   */    
/* 99:99 */    return this.ruleArray;
/* 100:   */  }
/* 101:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.RuleSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */