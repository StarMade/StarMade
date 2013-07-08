/*   1:    */package org.jaxen.pattern;
/*   2:    */
/*   3:    */import org.jaxen.Context;
/*   4:    */import org.jaxen.JaxenException;
/*   5:    */
/*  57:    */public class UnionPattern
/*  58:    */  extends Pattern
/*  59:    */{
/*  60:    */  private Pattern lhs;
/*  61:    */  private Pattern rhs;
/*  62: 62 */  private short nodeType = 0;
/*  63: 63 */  private String matchesNodeName = null;
/*  64:    */  
/*  67:    */  public UnionPattern() {}
/*  68:    */  
/*  70:    */  public UnionPattern(Pattern lhs, Pattern rhs)
/*  71:    */  {
/*  72: 72 */    this.lhs = lhs;
/*  73: 73 */    this.rhs = rhs;
/*  74: 74 */    init();
/*  75:    */  }
/*  76:    */  
/*  78:    */  public Pattern getLHS()
/*  79:    */  {
/*  80: 80 */    return this.lhs;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void setLHS(Pattern lhs)
/*  84:    */  {
/*  85: 85 */    this.lhs = lhs;
/*  86: 86 */    init();
/*  87:    */  }
/*  88:    */  
/*  89:    */  public Pattern getRHS()
/*  90:    */  {
/*  91: 91 */    return this.rhs;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public void setRHS(Pattern rhs)
/*  95:    */  {
/*  96: 96 */    this.rhs = rhs;
/*  97: 97 */    init();
/*  98:    */  }
/*  99:    */  
/* 105:    */  public boolean matches(Object node, Context context)
/* 106:    */    throws JaxenException
/* 107:    */  {
/* 108:108 */    return (this.lhs.matches(node, context)) || (this.rhs.matches(node, context));
/* 109:    */  }
/* 110:    */  
/* 111:    */  public Pattern[] getUnionPatterns()
/* 112:    */  {
/* 113:113 */    return new Pattern[] { this.lhs, this.rhs };
/* 114:    */  }
/* 115:    */  
/* 117:    */  public short getMatchType()
/* 118:    */  {
/* 119:119 */    return this.nodeType;
/* 120:    */  }
/* 121:    */  
/* 123:    */  public String getMatchesNodeName()
/* 124:    */  {
/* 125:125 */    return this.matchesNodeName;
/* 126:    */  }
/* 127:    */  
/* 129:    */  public Pattern simplify()
/* 130:    */  {
/* 131:131 */    this.lhs = this.lhs.simplify();
/* 132:132 */    this.rhs = this.rhs.simplify();
/* 133:133 */    init();
/* 134:134 */    return this;
/* 135:    */  }
/* 136:    */  
/* 137:    */  public String getText()
/* 138:    */  {
/* 139:139 */    return this.lhs.getText() + " | " + this.rhs.getText();
/* 140:    */  }
/* 141:    */  
/* 142:    */  public String toString()
/* 143:    */  {
/* 144:144 */    return super.toString() + "[ lhs: " + this.lhs + " rhs: " + this.rhs + " ]";
/* 145:    */  }
/* 146:    */  
/* 149:    */  private void init()
/* 150:    */  {
/* 151:151 */    short type1 = this.lhs.getMatchType();
/* 152:152 */    short type2 = this.rhs.getMatchType();
/* 153:153 */    this.nodeType = (type1 == type2 ? type1 : 0);
/* 154:    */    
/* 155:155 */    String name1 = this.lhs.getMatchesNodeName();
/* 156:156 */    String name2 = this.rhs.getMatchesNodeName();
/* 157:    */    
/* 158:158 */    this.matchesNodeName = null;
/* 159:159 */    if ((name1 != null) && (name2 != null) && (name1.equals(name2)))
/* 160:    */    {
/* 161:161 */      this.matchesNodeName = name1;
/* 162:    */    }
/* 163:    */  }
/* 164:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.UnionPattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */