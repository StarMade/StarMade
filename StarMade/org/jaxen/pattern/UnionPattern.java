package org.jaxen.pattern;

import org.jaxen.Context;
import org.jaxen.JaxenException;

public class UnionPattern
  extends Pattern
{
  private Pattern lhs;
  private Pattern rhs;
  private short nodeType = 0;
  private String matchesNodeName = null;
  
  public UnionPattern() {}
  
  public UnionPattern(Pattern lhs, Pattern rhs)
  {
    this.lhs = lhs;
    this.rhs = rhs;
    init();
  }
  
  public Pattern getLHS()
  {
    return this.lhs;
  }
  
  public void setLHS(Pattern lhs)
  {
    this.lhs = lhs;
    init();
  }
  
  public Pattern getRHS()
  {
    return this.rhs;
  }
  
  public void setRHS(Pattern rhs)
  {
    this.rhs = rhs;
    init();
  }
  
  public boolean matches(Object node, Context context)
    throws JaxenException
  {
    return (this.lhs.matches(node, context)) || (this.rhs.matches(node, context));
  }
  
  public Pattern[] getUnionPatterns()
  {
    return new Pattern[] { this.lhs, this.rhs };
  }
  
  public short getMatchType()
  {
    return this.nodeType;
  }
  
  public String getMatchesNodeName()
  {
    return this.matchesNodeName;
  }
  
  public Pattern simplify()
  {
    this.lhs = this.lhs.simplify();
    this.rhs = this.rhs.simplify();
    init();
    return this;
  }
  
  public String getText()
  {
    return this.lhs.getText() + " | " + this.rhs.getText();
  }
  
  public String toString()
  {
    return super.toString() + "[ lhs: " + this.lhs + " rhs: " + this.rhs + " ]";
  }
  
  private void init()
  {
    short type1 = this.lhs.getMatchType();
    short type2 = this.rhs.getMatchType();
    this.nodeType = (type1 == type2 ? type1 : 0);
    String name1 = this.lhs.getMatchesNodeName();
    String name2 = this.rhs.getMatchesNodeName();
    this.matchesNodeName = null;
    if ((name1 != null) && (name2 != null) && (name1.equals(name2))) {
      this.matchesNodeName = name1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.pattern.UnionPattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */