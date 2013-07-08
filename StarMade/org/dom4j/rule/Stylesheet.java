/*   1:    */package org.dom4j.rule;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.List;
/*   5:    */import org.dom4j.Document;
/*   6:    */import org.dom4j.Element;
/*   7:    */import org.dom4j.Node;
/*   8:    */
/*  27:    */public class Stylesheet
/*  28:    */{
/*  29: 29 */  private RuleManager ruleManager = new RuleManager();
/*  30:    */  
/*  37:    */  private String modeName;
/*  38:    */  
/*  45:    */  public void addRule(Rule rule)
/*  46:    */  {
/*  47: 47 */    this.ruleManager.addRule(rule);
/*  48:    */  }
/*  49:    */  
/*  55:    */  public void removeRule(Rule rule)
/*  56:    */  {
/*  57: 57 */    this.ruleManager.removeRule(rule);
/*  58:    */  }
/*  59:    */  
/*  68:    */  public void run(Object input)
/*  69:    */    throws Exception
/*  70:    */  {
/*  71: 71 */    run(input, this.modeName);
/*  72:    */  }
/*  73:    */  
/*  74:    */  public void run(Object input, String mode) throws Exception {
/*  75: 75 */    if ((input instanceof Node)) {
/*  76: 76 */      run((Node)input, mode);
/*  77: 77 */    } else if ((input instanceof List)) {
/*  78: 78 */      run((List)input, mode);
/*  79:    */    }
/*  80:    */  }
/*  81:    */  
/*  82:    */  public void run(List list) throws Exception {
/*  83: 83 */    run(list, this.modeName);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public void run(List list, String mode) throws Exception {
/*  87: 87 */    int i = 0; for (int size = list.size(); i < size; i++) {
/*  88: 88 */      Object object = list.get(i);
/*  89:    */      
/*  90: 90 */      if ((object instanceof Node)) {
/*  91: 91 */        run((Node)object, mode);
/*  92:    */      }
/*  93:    */    }
/*  94:    */  }
/*  95:    */  
/*  96:    */  public void run(Node node) throws Exception {
/*  97: 97 */    run(node, this.modeName);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public void run(Node node, String mode) throws Exception {
/* 101:101 */    Mode mod = this.ruleManager.getMode(mode);
/* 102:102 */    mod.fireRule(node);
/* 103:    */  }
/* 104:    */  
/* 114:    */  public void applyTemplates(Object input, org.dom4j.XPath xpath)
/* 115:    */    throws Exception
/* 116:    */  {
/* 117:117 */    applyTemplates(input, xpath, this.modeName);
/* 118:    */  }
/* 119:    */  
/* 132:    */  public void applyTemplates(Object input, org.dom4j.XPath xpath, String mode)
/* 133:    */    throws Exception
/* 134:    */  {
/* 135:135 */    Mode mod = this.ruleManager.getMode(mode);
/* 136:    */    
/* 137:137 */    List list = xpath.selectNodes(input);
/* 138:138 */    Iterator it = list.iterator();
/* 139:139 */    while (it.hasNext()) {
/* 140:140 */      Node current = (Node)it.next();
/* 141:141 */      mod.fireRule(current);
/* 142:    */    }
/* 143:    */  }
/* 144:    */  
/* 153:    */  /**
/* 154:    */   * @deprecated
/* 155:    */   */
/* 156:    */  public void applyTemplates(Object input, org.jaxen.XPath xpath)
/* 157:    */    throws Exception
/* 158:    */  {
/* 159:159 */    applyTemplates(input, xpath, this.modeName);
/* 160:    */  }
/* 161:    */  
/* 173:    */  /**
/* 174:    */   * @deprecated
/* 175:    */   */
/* 176:    */  public void applyTemplates(Object input, org.jaxen.XPath xpath, String mode)
/* 177:    */    throws Exception
/* 178:    */  {
/* 179:179 */    Mode mod = this.ruleManager.getMode(mode);
/* 180:    */    
/* 181:181 */    List list = xpath.selectNodes(input);
/* 182:182 */    Iterator it = list.iterator();
/* 183:183 */    while (it.hasNext()) {
/* 184:184 */      Node current = (Node)it.next();
/* 185:185 */      mod.fireRule(current);
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 199:    */  public void applyTemplates(Object input)
/* 200:    */    throws Exception
/* 201:    */  {
/* 202:202 */    applyTemplates(input, this.modeName);
/* 203:    */  }
/* 204:    */  
/* 217:    */  public void applyTemplates(Object input, String mode)
/* 218:    */    throws Exception
/* 219:    */  {
/* 220:220 */    Mode mod = this.ruleManager.getMode(mode);
/* 221:    */    
/* 222:222 */    if ((input instanceof Element))
/* 223:    */    {
/* 224:224 */      Element element = (Element)input;
/* 225:225 */      int i = 0; for (int size = element.nodeCount(); i < size; i++) {
/* 226:226 */        Node node = element.node(i);
/* 227:227 */        mod.fireRule(node);
/* 228:    */      }
/* 229:229 */    } else if ((input instanceof Document))
/* 230:    */    {
/* 231:231 */      Document document = (Document)input;
/* 232:232 */      int i = 0; for (int size = document.nodeCount(); i < size; i++) {
/* 233:233 */        Node node = document.node(i);
/* 234:234 */        mod.fireRule(node);
/* 235:    */      }
/* 236:236 */    } else if ((input instanceof List)) {
/* 237:237 */      List list = (List)input;
/* 238:    */      
/* 239:239 */      int i = 0; for (int size = list.size(); i < size; i++) {
/* 240:240 */        Object object = list.get(i);
/* 241:    */        
/* 242:242 */        if ((object instanceof Element)) {
/* 243:243 */          applyTemplates((Element)object, mode);
/* 244:244 */        } else if ((object instanceof Document)) {
/* 245:245 */          applyTemplates((Document)object, mode);
/* 246:    */        }
/* 247:    */      }
/* 248:    */    }
/* 249:    */  }
/* 250:    */  
/* 251:    */  public void clear() {
/* 252:252 */    this.ruleManager.clear();
/* 253:    */  }
/* 254:    */  
/* 262:    */  public String getModeName()
/* 263:    */  {
/* 264:264 */    return this.modeName;
/* 265:    */  }
/* 266:    */  
/* 272:    */  public void setModeName(String modeName)
/* 273:    */  {
/* 274:274 */    this.modeName = modeName;
/* 275:    */  }
/* 276:    */  
/* 282:    */  public Action getValueOfAction()
/* 283:    */  {
/* 284:284 */    return this.ruleManager.getValueOfAction();
/* 285:    */  }
/* 286:    */  
/* 293:    */  public void setValueOfAction(Action valueOfAction)
/* 294:    */  {
/* 295:295 */    this.ruleManager.setValueOfAction(valueOfAction);
/* 296:    */  }
/* 297:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.rule.Stylesheet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */