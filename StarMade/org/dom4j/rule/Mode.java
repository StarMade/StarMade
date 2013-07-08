/*   1:    */package org.dom4j.rule;
/*   2:    */
/*   3:    */import java.util.HashMap;
/*   4:    */import java.util.Map;
/*   5:    */import org.dom4j.Attribute;
/*   6:    */import org.dom4j.Document;
/*   7:    */import org.dom4j.Element;
/*   8:    */import org.dom4j.Node;
/*   9:    */
/*  28:    */public class Mode
/*  29:    */{
/*  30: 30 */  private RuleSet[] ruleSets = new RuleSet[14];
/*  31:    */  
/*  36:    */  private Map elementNameRuleSets;
/*  37:    */  
/*  42:    */  private Map attributeNameRuleSets;
/*  43:    */  
/*  48:    */  public void fireRule(Node node)
/*  49:    */    throws Exception
/*  50:    */  {
/*  51: 51 */    if (node != null) {
/*  52: 52 */      Rule rule = getMatchingRule(node);
/*  53:    */      
/*  54: 54 */      if (rule != null) {
/*  55: 55 */        Action action = rule.getAction();
/*  56:    */        
/*  57: 57 */        if (action != null) {
/*  58: 58 */          action.run(node);
/*  59:    */        }
/*  60:    */      }
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  64:    */  public void applyTemplates(Element element) throws Exception {
/*  65: 65 */    int i = 0; for (int size = element.attributeCount(); i < size; i++) {
/*  66: 66 */      Attribute attribute = element.attribute(i);
/*  67: 67 */      fireRule(attribute);
/*  68:    */    }
/*  69:    */    
/*  70: 70 */    int i = 0; for (int size = element.nodeCount(); i < size; i++) {
/*  71: 71 */      Node node = element.node(i);
/*  72: 72 */      fireRule(node);
/*  73:    */    }
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void applyTemplates(Document document) throws Exception {
/*  77: 77 */    int i = 0; for (int size = document.nodeCount(); i < size; i++) {
/*  78: 78 */      Node node = document.node(i);
/*  79: 79 */      fireRule(node);
/*  80:    */    }
/*  81:    */  }
/*  82:    */  
/*  83:    */  public void addRule(Rule rule) {
/*  84: 84 */    int matchType = rule.getMatchType();
/*  85: 85 */    String name = rule.getMatchesNodeName();
/*  86:    */    
/*  87: 87 */    if (name != null) {
/*  88: 88 */      if (matchType == 1) {
/*  89: 89 */        this.elementNameRuleSets = addToNameMap(this.elementNameRuleSets, name, rule);
/*  90:    */      }
/*  91: 91 */      else if (matchType == 2) {
/*  92: 92 */        this.attributeNameRuleSets = addToNameMap(this.attributeNameRuleSets, name, rule);
/*  93:    */      }
/*  94:    */    }
/*  95:    */    
/*  97: 97 */    if (matchType >= 14) {
/*  98: 98 */      matchType = 0;
/*  99:    */    }
/* 100:    */    
/* 101:101 */    if (matchType == 0)
/* 102:    */    {
/* 103:103 */      int i = 1; for (int size = this.ruleSets.length; i < size; i++) {
/* 104:104 */        RuleSet ruleSet = this.ruleSets[i];
/* 105:    */        
/* 106:106 */        if (ruleSet != null) {
/* 107:107 */          ruleSet.addRule(rule);
/* 108:    */        }
/* 109:    */      }
/* 110:    */    }
/* 111:    */    
/* 112:112 */    getRuleSet(matchType).addRule(rule);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public void removeRule(Rule rule) {
/* 116:116 */    int matchType = rule.getMatchType();
/* 117:117 */    String name = rule.getMatchesNodeName();
/* 118:    */    
/* 119:119 */    if (name != null) {
/* 120:120 */      if (matchType == 1) {
/* 121:121 */        removeFromNameMap(this.elementNameRuleSets, name, rule);
/* 122:122 */      } else if (matchType == 2) {
/* 123:123 */        removeFromNameMap(this.attributeNameRuleSets, name, rule);
/* 124:    */      }
/* 125:    */    }
/* 126:    */    
/* 127:127 */    if (matchType >= 14) {
/* 128:128 */      matchType = 0;
/* 129:    */    }
/* 130:    */    
/* 131:131 */    getRuleSet(matchType).removeRule(rule);
/* 132:    */    
/* 133:133 */    if (matchType != 0) {
/* 134:134 */      getRuleSet(0).removeRule(rule);
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 146:    */  public Rule getMatchingRule(Node node)
/* 147:    */  {
/* 148:148 */    int matchType = node.getNodeType();
/* 149:    */    
/* 150:150 */    if (matchType == 1) {
/* 151:151 */      if (this.elementNameRuleSets != null) {
/* 152:152 */        String name = node.getName();
/* 153:153 */        RuleSet ruleSet = (RuleSet)this.elementNameRuleSets.get(name);
/* 154:    */        
/* 155:155 */        if (ruleSet != null) {
/* 156:156 */          Rule answer = ruleSet.getMatchingRule(node);
/* 157:    */          
/* 158:158 */          if (answer != null) {
/* 159:159 */            return answer;
/* 160:    */          }
/* 161:    */        }
/* 162:    */      }
/* 163:163 */    } else if ((matchType == 2) && 
/* 164:164 */      (this.attributeNameRuleSets != null)) {
/* 165:165 */      String name = node.getName();
/* 166:166 */      RuleSet ruleSet = (RuleSet)this.attributeNameRuleSets.get(name);
/* 167:    */      
/* 168:168 */      if (ruleSet != null) {
/* 169:169 */        Rule answer = ruleSet.getMatchingRule(node);
/* 170:    */        
/* 171:171 */        if (answer != null) {
/* 172:172 */          return answer;
/* 173:    */        }
/* 174:    */      }
/* 175:    */    }
/* 176:    */    
/* 178:178 */    if ((matchType < 0) || (matchType >= this.ruleSets.length)) {
/* 179:179 */      matchType = 0;
/* 180:    */    }
/* 181:    */    
/* 182:182 */    Rule answer = null;
/* 183:183 */    RuleSet ruleSet = this.ruleSets[matchType];
/* 184:    */    
/* 185:185 */    if (ruleSet != null)
/* 186:    */    {
/* 187:187 */      answer = ruleSet.getMatchingRule(node);
/* 188:    */    }
/* 189:    */    
/* 190:190 */    if ((answer == null) && (matchType != 0))
/* 191:    */    {
/* 192:192 */      ruleSet = this.ruleSets[0];
/* 193:    */      
/* 194:194 */      if (ruleSet != null) {
/* 195:195 */        answer = ruleSet.getMatchingRule(node);
/* 196:    */      }
/* 197:    */    }
/* 198:    */    
/* 199:199 */    return answer;
/* 200:    */  }
/* 201:    */  
/* 210:    */  protected RuleSet getRuleSet(int matchType)
/* 211:    */  {
/* 212:212 */    RuleSet ruleSet = this.ruleSets[matchType];
/* 213:    */    
/* 214:214 */    if (ruleSet == null) {
/* 215:215 */      ruleSet = new RuleSet();
/* 216:216 */      this.ruleSets[matchType] = ruleSet;
/* 217:    */      
/* 219:219 */      if (matchType != 0) {
/* 220:220 */        RuleSet allRules = this.ruleSets[0];
/* 221:    */        
/* 222:222 */        if (allRules != null) {
/* 223:223 */          ruleSet.addAll(allRules);
/* 224:    */        }
/* 225:    */      }
/* 226:    */    }
/* 227:    */    
/* 228:228 */    return ruleSet;
/* 229:    */  }
/* 230:    */  
/* 242:    */  protected Map addToNameMap(Map map, String name, Rule rule)
/* 243:    */  {
/* 244:244 */    if (map == null) {
/* 245:245 */      map = new HashMap();
/* 246:    */    }
/* 247:    */    
/* 248:248 */    RuleSet ruleSet = (RuleSet)map.get(name);
/* 249:    */    
/* 250:250 */    if (ruleSet == null) {
/* 251:251 */      ruleSet = new RuleSet();
/* 252:252 */      map.put(name, ruleSet);
/* 253:    */    }
/* 254:    */    
/* 255:255 */    ruleSet.addRule(rule);
/* 256:    */    
/* 257:257 */    return map;
/* 258:    */  }
/* 259:    */  
/* 260:    */  protected void removeFromNameMap(Map map, String name, Rule rule) {
/* 261:261 */    if (map != null) {
/* 262:262 */      RuleSet ruleSet = (RuleSet)map.get(name);
/* 263:    */      
/* 264:264 */      if (ruleSet != null) {
/* 265:265 */        ruleSet.removeRule(rule);
/* 266:    */      }
/* 267:    */    }
/* 268:    */  }
/* 269:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.Mode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */