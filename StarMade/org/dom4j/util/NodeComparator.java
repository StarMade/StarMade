/*   1:    */package org.dom4j.util;
/*   2:    */
/*   3:    */import java.util.Comparator;
/*   4:    */import org.dom4j.Attribute;
/*   5:    */import org.dom4j.Branch;
/*   6:    */import org.dom4j.CDATA;
/*   7:    */import org.dom4j.CharacterData;
/*   8:    */import org.dom4j.Comment;
/*   9:    */import org.dom4j.Document;
/*  10:    */import org.dom4j.DocumentType;
/*  11:    */import org.dom4j.Element;
/*  12:    */import org.dom4j.Entity;
/*  13:    */import org.dom4j.Namespace;
/*  14:    */import org.dom4j.Node;
/*  15:    */import org.dom4j.ProcessingInstruction;
/*  16:    */import org.dom4j.QName;
/*  17:    */import org.dom4j.Text;
/*  18:    */
/*  74:    */public class NodeComparator
/*  75:    */  implements Comparator
/*  76:    */{
/*  77:    */  public int compare(Object o1, Object o2)
/*  78:    */  {
/*  79: 79 */    if (o1 == o2)
/*  80: 80 */      return 0;
/*  81: 81 */    if (o1 == null)
/*  82:    */    {
/*  83: 83 */      return -1; }
/*  84: 84 */    if (o2 == null) {
/*  85: 85 */      return 1;
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    if ((o1 instanceof Node)) {
/*  89: 89 */      if ((o2 instanceof Node)) {
/*  90: 90 */        return compare((Node)o1, (Node)o2);
/*  91:    */      }
/*  92:    */      
/*  93: 93 */      return 1;
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    if ((o2 instanceof Node))
/*  97:    */    {
/*  98: 98 */      return -1;
/*  99:    */    }
/* 100:100 */    if ((o1 instanceof Comparable)) {
/* 101:101 */      Comparable c1 = (Comparable)o1;
/* 102:    */      
/* 103:103 */      return c1.compareTo(o2);
/* 104:    */    }
/* 105:105 */    String name1 = o1.getClass().getName();
/* 106:106 */    String name2 = o2.getClass().getName();
/* 107:    */    
/* 108:108 */    return name1.compareTo(name2);
/* 109:    */  }
/* 110:    */  
/* 113:    */  public int compare(Node n1, Node n2)
/* 114:    */  {
/* 115:115 */    int nodeType1 = n1.getNodeType();
/* 116:116 */    int nodeType2 = n2.getNodeType();
/* 117:117 */    int answer = nodeType1 - nodeType2;
/* 118:    */    
/* 119:119 */    if (answer != 0) {
/* 120:120 */      return answer;
/* 121:    */    }
/* 122:122 */    switch (nodeType1) {
/* 123:    */    case 1: 
/* 124:124 */      return compare((Element)n1, (Element)n2);
/* 125:    */    
/* 126:    */    case 9: 
/* 127:127 */      return compare((Document)n1, (Document)n2);
/* 128:    */    
/* 129:    */    case 2: 
/* 130:130 */      return compare((Attribute)n1, (Attribute)n2);
/* 131:    */    
/* 132:    */    case 3: 
/* 133:133 */      return compare((Text)n1, (Text)n2);
/* 134:    */    
/* 135:    */    case 4: 
/* 136:136 */      return compare((CDATA)n1, (CDATA)n2);
/* 137:    */    
/* 138:    */    case 5: 
/* 139:139 */      return compare((Entity)n1, (Entity)n2);
/* 140:    */    
/* 141:    */    case 7: 
/* 142:142 */      return compare((ProcessingInstruction)n1, (ProcessingInstruction)n2);
/* 143:    */    
/* 145:    */    case 8: 
/* 146:146 */      return compare((Comment)n1, (Comment)n2);
/* 147:    */    
/* 148:    */    case 10: 
/* 149:149 */      return compare((DocumentType)n1, (DocumentType)n2);
/* 150:    */    
/* 151:    */    case 13: 
/* 152:152 */      return compare((Namespace)n1, (Namespace)n2);
/* 153:    */    }
/* 154:    */    
/* 155:155 */    throw new RuntimeException("Invalid node types. node1: " + n1 + " and node2: " + n2);
/* 156:    */  }
/* 157:    */  
/* 160:    */  public int compare(Document n1, Document n2)
/* 161:    */  {
/* 162:162 */    int answer = compare(n1.getDocType(), n2.getDocType());
/* 163:    */    
/* 164:164 */    if (answer == 0) {
/* 165:165 */      answer = compareContent(n1, n2);
/* 166:    */    }
/* 167:    */    
/* 168:168 */    return answer;
/* 169:    */  }
/* 170:    */  
/* 171:    */  public int compare(Element n1, Element n2) {
/* 172:172 */    int answer = compare(n1.getQName(), n2.getQName());
/* 173:    */    
/* 174:174 */    if (answer == 0)
/* 175:    */    {
/* 176:176 */      int c1 = n1.attributeCount();
/* 177:177 */      int c2 = n2.attributeCount();
/* 178:178 */      answer = c1 - c2;
/* 179:    */      
/* 180:180 */      if (answer == 0) {
/* 181:181 */        for (int i = 0; i < c1; i++) {
/* 182:182 */          Attribute a1 = n1.attribute(i);
/* 183:183 */          Attribute a2 = n2.attribute(a1.getQName());
/* 184:184 */          answer = compare(a1, a2);
/* 185:    */          
/* 186:186 */          if (answer != 0) {
/* 187:187 */            return answer;
/* 188:    */          }
/* 189:    */        }
/* 190:    */        
/* 191:191 */        answer = compareContent(n1, n2);
/* 192:    */      }
/* 193:    */    }
/* 194:    */    
/* 195:195 */    return answer;
/* 196:    */  }
/* 197:    */  
/* 198:    */  public int compare(Attribute n1, Attribute n2) {
/* 199:199 */    int answer = compare(n1.getQName(), n2.getQName());
/* 200:    */    
/* 201:201 */    if (answer == 0) {
/* 202:202 */      answer = compare(n1.getValue(), n2.getValue());
/* 203:    */    }
/* 204:    */    
/* 205:205 */    return answer;
/* 206:    */  }
/* 207:    */  
/* 208:    */  public int compare(QName n1, QName n2) {
/* 209:209 */    int answer = compare(n1.getNamespaceURI(), n2.getNamespaceURI());
/* 210:    */    
/* 211:211 */    if (answer == 0) {
/* 212:212 */      answer = compare(n1.getQualifiedName(), n2.getQualifiedName());
/* 213:    */    }
/* 214:    */    
/* 215:215 */    return answer;
/* 216:    */  }
/* 217:    */  
/* 218:    */  public int compare(Namespace n1, Namespace n2) {
/* 219:219 */    int answer = compare(n1.getURI(), n2.getURI());
/* 220:    */    
/* 221:221 */    if (answer == 0) {
/* 222:222 */      answer = compare(n1.getPrefix(), n2.getPrefix());
/* 223:    */    }
/* 224:    */    
/* 225:225 */    return answer;
/* 226:    */  }
/* 227:    */  
/* 228:    */  public int compare(CharacterData t1, CharacterData t2) {
/* 229:229 */    return compare(t1.getText(), t2.getText());
/* 230:    */  }
/* 231:    */  
/* 232:    */  public int compare(DocumentType o1, DocumentType o2) {
/* 233:233 */    if (o1 == o2)
/* 234:234 */      return 0;
/* 235:235 */    if (o1 == null)
/* 236:    */    {
/* 237:237 */      return -1; }
/* 238:238 */    if (o2 == null) {
/* 239:239 */      return 1;
/* 240:    */    }
/* 241:    */    
/* 242:242 */    int answer = compare(o1.getPublicID(), o2.getPublicID());
/* 243:    */    
/* 244:244 */    if (answer == 0) {
/* 245:245 */      answer = compare(o1.getSystemID(), o2.getSystemID());
/* 246:    */      
/* 247:247 */      if (answer == 0) {
/* 248:248 */        answer = compare(o1.getName(), o2.getName());
/* 249:    */      }
/* 250:    */    }
/* 251:    */    
/* 252:252 */    return answer;
/* 253:    */  }
/* 254:    */  
/* 255:    */  public int compare(Entity n1, Entity n2) {
/* 256:256 */    int answer = compare(n1.getName(), n2.getName());
/* 257:    */    
/* 258:258 */    if (answer == 0) {
/* 259:259 */      answer = compare(n1.getText(), n2.getText());
/* 260:    */    }
/* 261:    */    
/* 262:262 */    return answer;
/* 263:    */  }
/* 264:    */  
/* 265:    */  public int compare(ProcessingInstruction n1, ProcessingInstruction n2) {
/* 266:266 */    int answer = compare(n1.getTarget(), n2.getTarget());
/* 267:    */    
/* 268:268 */    if (answer == 0) {
/* 269:269 */      answer = compare(n1.getText(), n2.getText());
/* 270:    */    }
/* 271:    */    
/* 272:272 */    return answer;
/* 273:    */  }
/* 274:    */  
/* 275:    */  public int compareContent(Branch b1, Branch b2) {
/* 276:276 */    int c1 = b1.nodeCount();
/* 277:277 */    int c2 = b2.nodeCount();
/* 278:278 */    int answer = c1 - c2;
/* 279:    */    
/* 280:280 */    if (answer == 0) {
/* 281:281 */      for (int i = 0; i < c1; i++) {
/* 282:282 */        Node n1 = b1.node(i);
/* 283:283 */        Node n2 = b2.node(i);
/* 284:284 */        answer = compare(n1, n2);
/* 285:    */        
/* 286:286 */        if (answer != 0) {
/* 287:    */          break;
/* 288:    */        }
/* 289:    */      }
/* 290:    */    }
/* 291:    */    
/* 292:292 */    return answer;
/* 293:    */  }
/* 294:    */  
/* 295:    */  public int compare(String o1, String o2) {
/* 296:296 */    if (o1 == o2)
/* 297:297 */      return 0;
/* 298:298 */    if (o1 == null)
/* 299:    */    {
/* 300:300 */      return -1; }
/* 301:301 */    if (o2 == null) {
/* 302:302 */      return 1;
/* 303:    */    }
/* 304:    */    
/* 305:305 */    return o1.compareTo(o2);
/* 306:    */  }
/* 307:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.NodeComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */