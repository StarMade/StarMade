package org.dom4j.rule;

import org.dom4j.Node;

public class Rule
  implements Comparable
{
  private String mode;
  private int importPrecedence;
  private double priority;
  private int appearenceCount;
  private Pattern pattern;
  private Action action;
  
  public Rule()
  {
    this.priority = 0.5D;
  }
  
  public Rule(Pattern pattern)
  {
    this.pattern = pattern;
    this.priority = pattern.getPriority();
  }
  
  public Rule(Pattern pattern, Action action)
  {
    this(pattern);
    this.action = action;
  }
  
  public Rule(Rule that, Pattern pattern)
  {
    this.mode = that.mode;
    this.importPrecedence = that.importPrecedence;
    this.priority = that.priority;
    this.appearenceCount = that.appearenceCount;
    this.action = that.action;
    this.pattern = pattern;
  }
  
  public boolean equals(Object that)
  {
    if ((that instanceof Rule)) {
      return compareTo((Rule)that) == 0;
    }
    return false;
  }
  
  public int hashCode()
  {
    return this.importPrecedence + this.appearenceCount;
  }
  
  public int compareTo(Object that)
  {
    if ((that instanceof Rule)) {
      return compareTo((Rule)that);
    }
    return getClass().getName().compareTo(that.getClass().getName());
  }
  
  public int compareTo(Rule that)
  {
    int answer = this.importPrecedence - that.importPrecedence;
    if (answer == 0)
    {
      answer = (int)Math.round(this.priority - that.priority);
      if (answer == 0) {
        answer = this.appearenceCount - that.appearenceCount;
      }
    }
    return answer;
  }
  
  public String toString()
  {
    return super.toString() + "[ pattern: " + getPattern() + " action: " + getAction() + " ]";
  }
  
  public final boolean matches(Node node)
  {
    return this.pattern.matches(node);
  }
  
  public Rule[] getUnionRules()
  {
    Pattern[] patterns = this.pattern.getUnionPatterns();
    if (patterns == null) {
      return null;
    }
    int size = patterns.length;
    Rule[] answer = new Rule[size];
    for (int local_i = 0; local_i < size; local_i++) {
      answer[local_i] = new Rule(this, patterns[local_i]);
    }
    return answer;
  }
  
  public final short getMatchType()
  {
    return this.pattern.getMatchType();
  }
  
  public final String getMatchesNodeName()
  {
    return this.pattern.getMatchesNodeName();
  }
  
  public String getMode()
  {
    return this.mode;
  }
  
  public void setMode(String mode)
  {
    this.mode = mode;
  }
  
  public int getImportPrecedence()
  {
    return this.importPrecedence;
  }
  
  public void setImportPrecedence(int importPrecedence)
  {
    this.importPrecedence = importPrecedence;
  }
  
  public double getPriority()
  {
    return this.priority;
  }
  
  public void setPriority(double priority)
  {
    this.priority = priority;
  }
  
  public int getAppearenceCount()
  {
    return this.appearenceCount;
  }
  
  public void setAppearenceCount(int appearenceCount)
  {
    this.appearenceCount = appearenceCount;
  }
  
  public Pattern getPattern()
  {
    return this.pattern;
  }
  
  public void setPattern(Pattern pattern)
  {
    this.pattern = pattern;
  }
  
  public Action getAction()
  {
    return this.action;
  }
  
  public void setAction(Action action)
  {
    this.action = action;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.rule.Rule
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */