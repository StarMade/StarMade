/*   1:    */package org.dom4j.rule;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.util.HashMap;
/*   5:    */import org.dom4j.Document;
/*   6:    */import org.dom4j.Element;
/*   7:    */import org.dom4j.Node;
/*   8:    */import org.dom4j.rule.pattern.NodeTypePattern;
/*   9:    */
/*  26:    */public class RuleManager
/*  27:    */{
/*  28: 28 */  private HashMap modes = new HashMap();
/*  29:    */  
/*  35:    */  private int appearenceCount;
/*  36:    */  
/*  42:    */  private Action valueOfAction;
/*  43:    */  
/*  50:    */  public Mode getMode(String modeName)
/*  51:    */  {
/*  52: 52 */    Mode mode = (Mode)this.modes.get(modeName);
/*  53:    */    
/*  54: 54 */    if (mode == null) {
/*  55: 55 */      mode = createMode();
/*  56: 56 */      this.modes.put(modeName, mode);
/*  57:    */    }
/*  58:    */    
/*  59: 59 */    return mode;
/*  60:    */  }
/*  61:    */  
/*  62:    */  public void addRule(Rule rule) {
/*  63: 63 */    rule.setAppearenceCount(++this.appearenceCount);
/*  64:    */    
/*  65: 65 */    Mode mode = getMode(rule.getMode());
/*  66: 66 */    Rule[] childRules = rule.getUnionRules();
/*  67:    */    
/*  68: 68 */    if (childRules != null) {
/*  69: 69 */      int i = 0; for (int size = childRules.length; i < size; i++) {
/*  70: 70 */        mode.addRule(childRules[i]);
/*  71:    */      }
/*  72:    */    } else {
/*  73: 73 */      mode.addRule(rule);
/*  74:    */    }
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void removeRule(Rule rule) {
/*  78: 78 */    Mode mode = getMode(rule.getMode());
/*  79: 79 */    Rule[] childRules = rule.getUnionRules();
/*  80:    */    
/*  81: 81 */    if (childRules != null) {
/*  82: 82 */      int i = 0; for (int size = childRules.length; i < size; i++) {
/*  83: 83 */        mode.removeRule(childRules[i]);
/*  84:    */      }
/*  85:    */    } else {
/*  86: 86 */      mode.removeRule(rule);
/*  87:    */    }
/*  88:    */  }
/*  89:    */  
/* 100:    */  public Rule getMatchingRule(String modeName, Node node)
/* 101:    */  {
/* 102:102 */    Mode mode = (Mode)this.modes.get(modeName);
/* 103:    */    
/* 104:104 */    if (mode != null) {
/* 105:105 */      return mode.getMatchingRule(node);
/* 106:    */    }
/* 107:107 */    System.out.println("Warning: No Mode for mode: " + mode);
/* 108:    */    
/* 109:109 */    return null;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public void clear()
/* 113:    */  {
/* 114:114 */    this.modes.clear();
/* 115:115 */    this.appearenceCount = 0;
/* 116:    */  }
/* 117:    */  
/* 126:    */  public Action getValueOfAction()
/* 127:    */  {
/* 128:128 */    return this.valueOfAction;
/* 129:    */  }
/* 130:    */  
/* 137:    */  public void setValueOfAction(Action valueOfAction)
/* 138:    */  {
/* 139:139 */    this.valueOfAction = valueOfAction;
/* 140:    */  }
/* 141:    */  
/* 150:    */  protected Mode createMode()
/* 151:    */  {
/* 152:152 */    Mode mode = new Mode();
/* 153:153 */    addDefaultRules(mode);
/* 154:    */    
/* 155:155 */    return mode;
/* 156:    */  }
/* 157:    */  
/* 164:    */  protected void addDefaultRules(Mode mode)
/* 165:    */  {
/* 166:166 */    Action applyTemplates = new Action() { private final Mode val$mode;
/* 167:    */      
/* 168:168 */      public void run(Node node) throws Exception { if ((node instanceof Element)) {
/* 169:169 */          this.val$mode.applyTemplates((Element)node);
/* 170:170 */        } else if ((node instanceof Document)) {
/* 171:171 */          this.val$mode.applyTemplates((Document)node);
/* 172:    */        }
/* 173:    */        
/* 174:    */      }
/* 175:175 */    };
/* 176:176 */    Action valueOf = getValueOfAction();
/* 177:    */    
/* 178:178 */    addDefaultRule(mode, NodeTypePattern.ANY_DOCUMENT, applyTemplates);
/* 179:179 */    addDefaultRule(mode, NodeTypePattern.ANY_ELEMENT, applyTemplates);
/* 180:    */    
/* 181:181 */    if (valueOf != null) {
/* 182:182 */      addDefaultRule(mode, NodeTypePattern.ANY_ATTRIBUTE, valueOf);
/* 183:183 */      addDefaultRule(mode, NodeTypePattern.ANY_TEXT, valueOf);
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 187:    */  protected void addDefaultRule(Mode mode, Pattern pattern, Action action) {
/* 188:188 */    Rule rule = createDefaultRule(pattern, action);
/* 189:189 */    mode.addRule(rule);
/* 190:    */  }
/* 191:    */  
/* 192:    */  protected Rule createDefaultRule(Pattern pattern, Action action) {
/* 193:193 */    Rule rule = new Rule(pattern, action);
/* 194:194 */    rule.setImportPrecedence(-1);
/* 195:    */    
/* 196:196 */    return rule;
/* 197:    */  }
/* 198:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.RuleManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */