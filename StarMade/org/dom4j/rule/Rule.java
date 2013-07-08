/*   1:    */package org.dom4j.rule;
/*   2:    */
/*   3:    */import org.dom4j.Node;
/*   4:    */
/*  29:    */public class Rule
/*  30:    */  implements Comparable
/*  31:    */{
/*  32:    */  private String mode;
/*  33:    */  private int importPrecedence;
/*  34:    */  private double priority;
/*  35:    */  private int appearenceCount;
/*  36:    */  private Pattern pattern;
/*  37:    */  private Action action;
/*  38:    */  
/*  39:    */  public Rule()
/*  40:    */  {
/*  41: 41 */    this.priority = 0.5D;
/*  42:    */  }
/*  43:    */  
/*  44:    */  public Rule(Pattern pattern) {
/*  45: 45 */    this.pattern = pattern;
/*  46: 46 */    this.priority = pattern.getPriority();
/*  47:    */  }
/*  48:    */  
/*  49:    */  public Rule(Pattern pattern, Action action) {
/*  50: 50 */    this(pattern);
/*  51: 51 */    this.action = action;
/*  52:    */  }
/*  53:    */  
/*  62:    */  public Rule(Rule that, Pattern pattern)
/*  63:    */  {
/*  64: 64 */    this.mode = that.mode;
/*  65: 65 */    this.importPrecedence = that.importPrecedence;
/*  66: 66 */    this.priority = that.priority;
/*  67: 67 */    this.appearenceCount = that.appearenceCount;
/*  68: 68 */    this.action = that.action;
/*  69: 69 */    this.pattern = pattern;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public boolean equals(Object that) {
/*  73: 73 */    if ((that instanceof Rule)) {
/*  74: 74 */      return compareTo((Rule)that) == 0;
/*  75:    */    }
/*  76:    */    
/*  77: 77 */    return false;
/*  78:    */  }
/*  79:    */  
/*  80:    */  public int hashCode() {
/*  81: 81 */    return this.importPrecedence + this.appearenceCount;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public int compareTo(Object that) {
/*  85: 85 */    if ((that instanceof Rule)) {
/*  86: 86 */      return compareTo((Rule)that);
/*  87:    */    }
/*  88:    */    
/*  89: 89 */    return getClass().getName().compareTo(that.getClass().getName());
/*  90:    */  }
/*  91:    */  
/* 100:    */  public int compareTo(Rule that)
/* 101:    */  {
/* 102:102 */    int answer = this.importPrecedence - that.importPrecedence;
/* 103:    */    
/* 104:104 */    if (answer == 0) {
/* 105:105 */      answer = (int)Math.round(this.priority - that.priority);
/* 106:    */      
/* 107:107 */      if (answer == 0) {
/* 108:108 */        answer = this.appearenceCount - that.appearenceCount;
/* 109:    */      }
/* 110:    */    }
/* 111:    */    
/* 112:112 */    return answer;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public String toString() {
/* 116:116 */    return super.toString() + "[ pattern: " + getPattern() + " action: " + getAction() + " ]";
/* 117:    */  }
/* 118:    */  
/* 127:    */  public final boolean matches(Node node)
/* 128:    */  {
/* 129:129 */    return this.pattern.matches(node);
/* 130:    */  }
/* 131:    */  
/* 139:    */  public Rule[] getUnionRules()
/* 140:    */  {
/* 141:141 */    Pattern[] patterns = this.pattern.getUnionPatterns();
/* 142:    */    
/* 143:143 */    if (patterns == null) {
/* 144:144 */      return null;
/* 145:    */    }
/* 146:    */    
/* 147:147 */    int size = patterns.length;
/* 148:148 */    Rule[] answer = new Rule[size];
/* 149:    */    
/* 150:150 */    for (int i = 0; i < size; i++) {
/* 151:151 */      answer[i] = new Rule(this, patterns[i]);
/* 152:    */    }
/* 153:    */    
/* 154:154 */    return answer;
/* 155:    */  }
/* 156:    */  
/* 162:    */  public final short getMatchType()
/* 163:    */  {
/* 164:164 */    return this.pattern.getMatchType();
/* 165:    */  }
/* 166:    */  
/* 176:    */  public final String getMatchesNodeName()
/* 177:    */  {
/* 178:178 */    return this.pattern.getMatchesNodeName();
/* 179:    */  }
/* 180:    */  
/* 185:    */  public String getMode()
/* 186:    */  {
/* 187:187 */    return this.mode;
/* 188:    */  }
/* 189:    */  
/* 195:    */  public void setMode(String mode)
/* 196:    */  {
/* 197:197 */    this.mode = mode;
/* 198:    */  }
/* 199:    */  
/* 204:    */  public int getImportPrecedence()
/* 205:    */  {
/* 206:206 */    return this.importPrecedence;
/* 207:    */  }
/* 208:    */  
/* 214:    */  public void setImportPrecedence(int importPrecedence)
/* 215:    */  {
/* 216:216 */    this.importPrecedence = importPrecedence;
/* 217:    */  }
/* 218:    */  
/* 223:    */  public double getPriority()
/* 224:    */  {
/* 225:225 */    return this.priority;
/* 226:    */  }
/* 227:    */  
/* 233:    */  public void setPriority(double priority)
/* 234:    */  {
/* 235:235 */    this.priority = priority;
/* 236:    */  }
/* 237:    */  
/* 242:    */  public int getAppearenceCount()
/* 243:    */  {
/* 244:244 */    return this.appearenceCount;
/* 245:    */  }
/* 246:    */  
/* 252:    */  public void setAppearenceCount(int appearenceCount)
/* 253:    */  {
/* 254:254 */    this.appearenceCount = appearenceCount;
/* 255:    */  }
/* 256:    */  
/* 261:    */  public Pattern getPattern()
/* 262:    */  {
/* 263:263 */    return this.pattern;
/* 264:    */  }
/* 265:    */  
/* 271:    */  public void setPattern(Pattern pattern)
/* 272:    */  {
/* 273:273 */    this.pattern = pattern;
/* 274:    */  }
/* 275:    */  
/* 280:    */  public Action getAction()
/* 281:    */  {
/* 282:282 */    return this.action;
/* 283:    */  }
/* 284:    */  
/* 290:    */  public void setAction(Action action)
/* 291:    */  {
/* 292:292 */    this.action = action;
/* 293:    */  }
/* 294:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.Rule
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */