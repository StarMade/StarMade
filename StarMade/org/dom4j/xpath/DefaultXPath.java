/*   1:    */package org.dom4j.xpath;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Collections;
/*   5:    */import java.util.Comparator;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.HashSet;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.List;
/*  10:    */import java.util.Map;
/*  11:    */import org.dom4j.InvalidXPathException;
/*  12:    */import org.dom4j.Node;
/*  13:    */import org.dom4j.NodeFilter;
/*  14:    */import org.dom4j.XPathException;
/*  15:    */import org.jaxen.FunctionContext;
/*  16:    */import org.jaxen.JaxenException;
/*  17:    */import org.jaxen.NamespaceContext;
/*  18:    */import org.jaxen.SimpleNamespaceContext;
/*  19:    */import org.jaxen.VariableContext;
/*  20:    */import org.jaxen.dom4j.Dom4jXPath;
/*  21:    */
/*  48:    */public class DefaultXPath
/*  49:    */  implements org.dom4j.XPath, NodeFilter, Serializable
/*  50:    */{
/*  51:    */  private String text;
/*  52:    */  private org.jaxen.XPath xpath;
/*  53:    */  private NamespaceContext namespaceContext;
/*  54:    */  
/*  55:    */  public DefaultXPath(String text)
/*  56:    */    throws InvalidXPathException
/*  57:    */  {
/*  58: 58 */    this.text = text;
/*  59: 59 */    this.xpath = parse(text);
/*  60:    */  }
/*  61:    */  
/*  62:    */  public String toString() {
/*  63: 63 */    return "[XPath: " + this.xpath + "]";
/*  64:    */  }
/*  65:    */  
/*  72:    */  public String getText()
/*  73:    */  {
/*  74: 74 */    return this.text;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public FunctionContext getFunctionContext() {
/*  78: 78 */    return this.xpath.getFunctionContext();
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void setFunctionContext(FunctionContext functionContext) {
/*  82: 82 */    this.xpath.setFunctionContext(functionContext);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public NamespaceContext getNamespaceContext() {
/*  86: 86 */    return this.namespaceContext;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void setNamespaceURIs(Map map) {
/*  90: 90 */    setNamespaceContext(new SimpleNamespaceContext(map));
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void setNamespaceContext(NamespaceContext namespaceContext) {
/*  94: 94 */    this.namespaceContext = namespaceContext;
/*  95: 95 */    this.xpath.setNamespaceContext(namespaceContext);
/*  96:    */  }
/*  97:    */  
/*  98:    */  public VariableContext getVariableContext() {
/*  99: 99 */    return this.xpath.getVariableContext();
/* 100:    */  }
/* 101:    */  
/* 102:    */  public void setVariableContext(VariableContext variableContext) {
/* 103:103 */    this.xpath.setVariableContext(variableContext);
/* 104:    */  }
/* 105:    */  
/* 106:    */  public Object evaluate(Object context) {
/* 107:    */    try {
/* 108:108 */      setNSContext(context);
/* 109:    */      
/* 110:110 */      List answer = this.xpath.selectNodes(context);
/* 111:    */      
/* 112:112 */      if ((answer != null) && (answer.size() == 1)) {
/* 113:113 */        return answer.get(0);
/* 114:    */      }
/* 115:    */      
/* 116:116 */      return answer;
/* 117:    */    } catch (JaxenException e) {
/* 118:118 */      handleJaxenException(e);
/* 119:    */    }
/* 120:120 */    return null;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public Object selectObject(Object context)
/* 124:    */  {
/* 125:125 */    return evaluate(context);
/* 126:    */  }
/* 127:    */  
/* 128:    */  public List selectNodes(Object context) {
/* 129:    */    try {
/* 130:130 */      setNSContext(context);
/* 131:    */      
/* 132:132 */      return this.xpath.selectNodes(context);
/* 133:    */    } catch (JaxenException e) {
/* 134:134 */      handleJaxenException(e);
/* 135:    */    }
/* 136:136 */    return Collections.EMPTY_LIST;
/* 137:    */  }
/* 138:    */  
/* 139:    */  public List selectNodes(Object context, org.dom4j.XPath sortXPath)
/* 140:    */  {
/* 141:141 */    List answer = selectNodes(context);
/* 142:142 */    sortXPath.sort(answer);
/* 143:    */    
/* 144:144 */    return answer;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public List selectNodes(Object context, org.dom4j.XPath sortXPath, boolean distinct)
/* 148:    */  {
/* 149:149 */    List answer = selectNodes(context);
/* 150:150 */    sortXPath.sort(answer, distinct);
/* 151:    */    
/* 152:152 */    return answer;
/* 153:    */  }
/* 154:    */  
/* 155:    */  public Node selectSingleNode(Object context) {
/* 156:    */    try {
/* 157:157 */      setNSContext(context);
/* 158:    */      
/* 159:159 */      Object answer = this.xpath.selectSingleNode(context);
/* 160:    */      
/* 161:161 */      if ((answer instanceof Node)) {
/* 162:162 */        return (Node)answer;
/* 163:    */      }
/* 164:    */      
/* 165:165 */      if (answer == null) {
/* 166:166 */        return null;
/* 167:    */      }
/* 168:    */      
/* 169:169 */      throw new XPathException("The result of the XPath expression is not a Node. It was: " + answer + " of type: " + answer.getClass().getName());
/* 170:    */    }
/* 171:    */    catch (JaxenException e)
/* 172:    */    {
/* 173:173 */      handleJaxenException(e);
/* 174:    */    }
/* 175:175 */    return null;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public String valueOf(Object context)
/* 179:    */  {
/* 180:    */    try {
/* 181:181 */      setNSContext(context);
/* 182:    */      
/* 183:183 */      return this.xpath.stringValueOf(context);
/* 184:    */    } catch (JaxenException e) {
/* 185:185 */      handleJaxenException(e);
/* 186:    */    }
/* 187:187 */    return "";
/* 188:    */  }
/* 189:    */  
/* 190:    */  public Number numberValueOf(Object context)
/* 191:    */  {
/* 192:    */    try {
/* 193:193 */      setNSContext(context);
/* 194:    */      
/* 195:195 */      return this.xpath.numberValueOf(context);
/* 196:    */    } catch (JaxenException e) {
/* 197:197 */      handleJaxenException(e);
/* 198:    */    }
/* 199:199 */    return null;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public boolean booleanValueOf(Object context)
/* 203:    */  {
/* 204:    */    try {
/* 205:205 */      setNSContext(context);
/* 206:    */      
/* 207:207 */      return this.xpath.booleanValueOf(context);
/* 208:    */    } catch (JaxenException e) {
/* 209:209 */      handleJaxenException(e);
/* 210:    */    }
/* 211:211 */    return false;
/* 212:    */  }
/* 213:    */  
/* 223:    */  public void sort(List list)
/* 224:    */  {
/* 225:225 */    sort(list, false);
/* 226:    */  }
/* 227:    */  
/* 239:    */  public void sort(List list, boolean distinct)
/* 240:    */  {
/* 241:241 */    if ((list != null) && (!list.isEmpty())) {
/* 242:242 */      int size = list.size();
/* 243:243 */      HashMap sortValues = new HashMap(size);
/* 244:    */      
/* 245:245 */      for (int i = 0; i < size; i++) {
/* 246:246 */        Object object = list.get(i);
/* 247:    */        
/* 248:248 */        if ((object instanceof Node)) {
/* 249:249 */          Node node = (Node)object;
/* 250:250 */          Object expression = getCompareValue(node);
/* 251:251 */          sortValues.put(node, expression);
/* 252:    */        }
/* 253:    */      }
/* 254:    */      
/* 255:255 */      sort(list, sortValues);
/* 256:    */      
/* 257:257 */      if (distinct) {
/* 258:258 */        removeDuplicates(list, sortValues);
/* 259:    */      }
/* 260:    */    }
/* 261:    */  }
/* 262:    */  
/* 263:    */  public boolean matches(Node node) {
/* 264:    */    try {
/* 265:265 */      setNSContext(node);
/* 266:    */      
/* 267:267 */      List answer = this.xpath.selectNodes(node);
/* 268:    */      
/* 269:269 */      if ((answer != null) && (answer.size() > 0)) {
/* 270:270 */        Object item = answer.get(0);
/* 271:    */        
/* 272:272 */        if ((item instanceof Boolean)) {
/* 273:273 */          return ((Boolean)item).booleanValue();
/* 274:    */        }
/* 275:    */        
/* 276:276 */        return answer.contains(node);
/* 277:    */      }
/* 278:    */      
/* 279:279 */      return false;
/* 280:    */    } catch (JaxenException e) {
/* 281:281 */      handleJaxenException(e);
/* 282:    */    }
/* 283:283 */    return false;
/* 284:    */  }
/* 285:    */  
/* 294:    */  protected void sort(List list, Map sortValues)
/* 295:    */  {
/* 296:296 */    Collections.sort(list, new Comparator() { private final Map val$sortValues;
/* 297:    */      
/* 298:298 */      public int compare(Object o1, Object o2) { o1 = this.val$sortValues.get(o1);
/* 299:299 */        o2 = this.val$sortValues.get(o2);
/* 300:    */        
/* 301:301 */        if (o1 == o2)
/* 302:302 */          return 0;
/* 303:303 */        if ((o1 instanceof Comparable)) {
/* 304:304 */          Comparable c1 = (Comparable)o1;
/* 305:    */          
/* 306:306 */          return c1.compareTo(o2); }
/* 307:307 */        if (o1 == null)
/* 308:308 */          return 1;
/* 309:309 */        if (o2 == null) {
/* 310:310 */          return -1;
/* 311:    */        }
/* 312:312 */        return o1.equals(o2) ? 0 : -1;
/* 313:    */      }
/* 314:    */    });
/* 315:    */  }
/* 316:    */  
/* 328:    */  protected void removeDuplicates(List list, Map sortValues)
/* 329:    */  {
/* 330:330 */    HashSet distinctValues = new HashSet();
/* 331:    */    
/* 332:332 */    for (Iterator iter = list.iterator(); iter.hasNext();) {
/* 333:333 */      Object node = iter.next();
/* 334:334 */      Object value = sortValues.get(node);
/* 335:    */      
/* 336:336 */      if (distinctValues.contains(value)) {
/* 337:337 */        iter.remove();
/* 338:    */      } else {
/* 339:339 */        distinctValues.add(value);
/* 340:    */      }
/* 341:    */    }
/* 342:    */  }
/* 343:    */  
/* 351:    */  protected Object getCompareValue(Node node)
/* 352:    */  {
/* 353:353 */    return valueOf(node);
/* 354:    */  }
/* 355:    */  
/* 356:    */  protected static org.jaxen.XPath parse(String text) {
/* 357:    */    try {
/* 358:358 */      return new Dom4jXPath(text);
/* 359:    */    } catch (JaxenException e) {
/* 360:360 */      throw new InvalidXPathException(text, e.getMessage());
/* 361:    */    } catch (Throwable t) {
/* 362:362 */      throw new InvalidXPathException(text, t);
/* 363:    */    }
/* 364:    */  }
/* 365:    */  
/* 366:    */  protected void setNSContext(Object context) {
/* 367:367 */    if (this.namespaceContext == null) {
/* 368:368 */      this.xpath.setNamespaceContext(DefaultNamespaceContext.create(context));
/* 369:    */    }
/* 370:    */  }
/* 371:    */  
/* 372:    */  protected void handleJaxenException(JaxenException exception) throws XPathException
/* 373:    */  {
/* 374:374 */    throw new XPathException(this.text, exception);
/* 375:    */  }
/* 376:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.xpath.DefaultXPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */