/*   1:    */package org.dom4j.util;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import java.util.Map;
/*   8:    */import org.dom4j.Attribute;
/*   9:    */import org.dom4j.Element;
/*  10:    */import org.dom4j.Node;
/*  11:    */import org.dom4j.QName;
/*  12:    */import org.dom4j.tree.BackedList;
/*  13:    */import org.dom4j.tree.DefaultElement;
/*  14:    */
/*  33:    */public class IndexedElement
/*  34:    */  extends DefaultElement
/*  35:    */{
/*  36:    */  private Map elementIndex;
/*  37:    */  private Map attributeIndex;
/*  38:    */  
/*  39:    */  public IndexedElement(String name)
/*  40:    */  {
/*  41: 41 */    super(name);
/*  42:    */  }
/*  43:    */  
/*  44:    */  public IndexedElement(QName qname) {
/*  45: 45 */    super(qname);
/*  46:    */  }
/*  47:    */  
/*  48:    */  public IndexedElement(QName qname, int attributeCount) {
/*  49: 49 */    super(qname, attributeCount);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public Attribute attribute(String name) {
/*  53: 53 */    return (Attribute)attributeIndex().get(name);
/*  54:    */  }
/*  55:    */  
/*  56:    */  public Attribute attribute(QName qName) {
/*  57: 57 */    return (Attribute)attributeIndex().get(qName);
/*  58:    */  }
/*  59:    */  
/*  60:    */  public Element element(String name) {
/*  61: 61 */    return asElement(elementIndex().get(name));
/*  62:    */  }
/*  63:    */  
/*  64:    */  public Element element(QName qName) {
/*  65: 65 */    return asElement(elementIndex().get(qName));
/*  66:    */  }
/*  67:    */  
/*  68:    */  public List elements(String name) {
/*  69: 69 */    return asElementList(elementIndex().get(name));
/*  70:    */  }
/*  71:    */  
/*  72:    */  public List elements(QName qName) {
/*  73: 73 */    return asElementList(elementIndex().get(qName));
/*  74:    */  }
/*  75:    */  
/*  77:    */  protected Element asElement(Object object)
/*  78:    */  {
/*  79: 79 */    if ((object instanceof Element))
/*  80: 80 */      return (Element)object;
/*  81: 81 */    if (object != null) {
/*  82: 82 */      List list = (List)object;
/*  83:    */      
/*  84: 84 */      if (list.size() >= 1) {
/*  85: 85 */        return (Element)list.get(0);
/*  86:    */      }
/*  87:    */    }
/*  88:    */    
/*  89: 89 */    return null;
/*  90:    */  }
/*  91:    */  
/*  92:    */  protected List asElementList(Object object) {
/*  93: 93 */    if ((object instanceof Element))
/*  94: 94 */      return createSingleResultList(object);
/*  95: 95 */    if (object != null) {
/*  96: 96 */      List list = (List)object;
/*  97: 97 */      BackedList answer = createResultList();
/*  98:    */      
/*  99: 99 */      int i = 0; for (int size = list.size(); i < size; i++) {
/* 100:100 */        answer.addLocal(list.get(i));
/* 101:    */      }
/* 102:    */      
/* 103:103 */      return answer;
/* 104:    */    }
/* 105:    */    
/* 106:106 */    return createEmptyList();
/* 107:    */  }
/* 108:    */  
/* 115:    */  /**
/* 116:    */   * @deprecated
/* 117:    */   */
/* 118:    */  protected Iterator asElementIterator(Object object)
/* 119:    */  {
/* 120:120 */    return asElementList(object).iterator();
/* 121:    */  }
/* 122:    */  
/* 123:    */  protected void addNode(Node node)
/* 124:    */  {
/* 125:125 */    super.addNode(node);
/* 126:    */    
/* 127:127 */    if ((this.elementIndex != null) && ((node instanceof Element))) {
/* 128:128 */      addToElementIndex((Element)node);
/* 129:129 */    } else if ((this.attributeIndex != null) && ((node instanceof Attribute))) {
/* 130:130 */      addToAttributeIndex((Attribute)node);
/* 131:    */    }
/* 132:    */  }
/* 133:    */  
/* 134:    */  protected boolean removeNode(Node node) {
/* 135:135 */    if (super.removeNode(node)) {
/* 136:136 */      if ((this.elementIndex != null) && ((node instanceof Element))) {
/* 137:137 */        removeFromElementIndex((Element)node);
/* 138:138 */      } else if ((this.attributeIndex != null) && ((node instanceof Attribute))) {
/* 139:139 */        removeFromAttributeIndex((Attribute)node);
/* 140:    */      }
/* 141:    */      
/* 142:142 */      return true;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    return false;
/* 146:    */  }
/* 147:    */  
/* 148:    */  protected Map attributeIndex() { Iterator iter;
/* 149:149 */    if (this.attributeIndex == null) {
/* 150:150 */      this.attributeIndex = createAttributeIndex();
/* 151:    */      
/* 152:152 */      for (iter = attributeIterator(); iter.hasNext();) {
/* 153:153 */        addToAttributeIndex((Attribute)iter.next());
/* 154:    */      }
/* 155:    */    }
/* 156:    */    
/* 157:157 */    return this.attributeIndex;
/* 158:    */  }
/* 159:    */  
/* 160:    */  protected Map elementIndex() { Iterator iter;
/* 161:161 */    if (this.elementIndex == null) {
/* 162:162 */      this.elementIndex = createElementIndex();
/* 163:    */      
/* 164:164 */      for (iter = elementIterator(); iter.hasNext();) {
/* 165:165 */        addToElementIndex((Element)iter.next());
/* 166:    */      }
/* 167:    */    }
/* 168:    */    
/* 169:169 */    return this.elementIndex;
/* 170:    */  }
/* 171:    */  
/* 176:    */  protected Map createAttributeIndex()
/* 177:    */  {
/* 178:178 */    Map answer = createIndex();
/* 179:    */    
/* 180:180 */    return answer;
/* 181:    */  }
/* 182:    */  
/* 187:    */  protected Map createElementIndex()
/* 188:    */  {
/* 189:189 */    Map answer = createIndex();
/* 190:    */    
/* 191:191 */    return answer;
/* 192:    */  }
/* 193:    */  
/* 194:    */  protected void addToElementIndex(Element element) {
/* 195:195 */    QName qName = element.getQName();
/* 196:196 */    String name = qName.getName();
/* 197:197 */    addToElementIndex(qName, element);
/* 198:198 */    addToElementIndex(name, element);
/* 199:    */  }
/* 200:    */  
/* 201:    */  protected void addToElementIndex(Object key, Element value) {
/* 202:202 */    Object oldValue = this.elementIndex.get(key);
/* 203:    */    
/* 204:204 */    if (oldValue == null) {
/* 205:205 */      this.elementIndex.put(key, value);
/* 206:    */    }
/* 207:207 */    else if ((oldValue instanceof List)) {
/* 208:208 */      List list = (List)oldValue;
/* 209:209 */      list.add(value);
/* 210:    */    } else {
/* 211:211 */      List list = createList();
/* 212:212 */      list.add(oldValue);
/* 213:213 */      list.add(value);
/* 214:214 */      this.elementIndex.put(key, list);
/* 215:    */    }
/* 216:    */  }
/* 217:    */  
/* 218:    */  protected void removeFromElementIndex(Element element)
/* 219:    */  {
/* 220:220 */    QName qName = element.getQName();
/* 221:221 */    String name = qName.getName();
/* 222:222 */    removeFromElementIndex(qName, element);
/* 223:223 */    removeFromElementIndex(name, element);
/* 224:    */  }
/* 225:    */  
/* 226:    */  protected void removeFromElementIndex(Object key, Element value) {
/* 227:227 */    Object oldValue = this.elementIndex.get(key);
/* 228:    */    
/* 229:229 */    if ((oldValue instanceof List)) {
/* 230:230 */      List list = (List)oldValue;
/* 231:231 */      list.remove(value);
/* 232:    */    } else {
/* 233:233 */      this.elementIndex.remove(key);
/* 234:    */    }
/* 235:    */  }
/* 236:    */  
/* 237:    */  protected void addToAttributeIndex(Attribute attribute) {
/* 238:238 */    QName qName = attribute.getQName();
/* 239:239 */    String name = qName.getName();
/* 240:240 */    addToAttributeIndex(qName, attribute);
/* 241:241 */    addToAttributeIndex(name, attribute);
/* 242:    */  }
/* 243:    */  
/* 244:    */  protected void addToAttributeIndex(Object key, Attribute value) {
/* 245:245 */    Object oldValue = this.attributeIndex.get(key);
/* 246:    */    
/* 247:247 */    if (oldValue != null) {
/* 248:248 */      this.attributeIndex.put(key, value);
/* 249:    */    }
/* 250:    */  }
/* 251:    */  
/* 252:    */  protected void removeFromAttributeIndex(Attribute attribute) {
/* 253:253 */    QName qName = attribute.getQName();
/* 254:254 */    String name = qName.getName();
/* 255:255 */    removeFromAttributeIndex(qName, attribute);
/* 256:256 */    removeFromAttributeIndex(name, attribute);
/* 257:    */  }
/* 258:    */  
/* 259:    */  protected void removeFromAttributeIndex(Object key, Attribute value) {
/* 260:260 */    Object oldValue = this.attributeIndex.get(key);
/* 261:    */    
/* 262:262 */    if ((oldValue != null) && (oldValue.equals(value))) {
/* 263:263 */      this.attributeIndex.remove(key);
/* 264:    */    }
/* 265:    */  }
/* 266:    */  
/* 271:    */  protected Map createIndex()
/* 272:    */  {
/* 273:273 */    return new HashMap();
/* 274:    */  }
/* 275:    */  
/* 280:    */  protected List createList()
/* 281:    */  {
/* 282:282 */    return new ArrayList();
/* 283:    */  }
/* 284:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.IndexedElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */