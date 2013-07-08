/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Collection;
/*   5:    */import java.util.Collections;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.List;
/*   9:    */import java.util.Map;
/*  10:    */import java.util.WeakHashMap;
/*  11:    */import org.dom4j.DocumentFactory;
/*  12:    */import org.dom4j.Namespace;
/*  13:    */import org.dom4j.QName;
/*  14:    */
/*  34:    */public class QNameCache
/*  35:    */{
/*  36: 36 */  protected Map noNamespaceCache = Collections.synchronizedMap(new WeakHashMap());
/*  37:    */  
/*  43: 43 */  protected Map namespaceCache = Collections.synchronizedMap(new WeakHashMap());
/*  44:    */  
/*  46:    */  private DocumentFactory documentFactory;
/*  47:    */  
/*  50:    */  public QNameCache() {}
/*  51:    */  
/*  54:    */  public QNameCache(DocumentFactory documentFactory)
/*  55:    */  {
/*  56: 56 */    this.documentFactory = documentFactory;
/*  57:    */  }
/*  58:    */  
/*  63:    */  public List getQNames()
/*  64:    */  {
/*  65: 65 */    List answer = new ArrayList();
/*  66: 66 */    answer.addAll(this.noNamespaceCache.values());
/*  67:    */    
/*  68: 68 */    for (Iterator it = this.namespaceCache.values().iterator(); it.hasNext();) {
/*  69: 69 */      Map map = (Map)it.next();
/*  70: 70 */      answer.addAll(map.values());
/*  71:    */    }
/*  72:    */    
/*  73: 73 */    return answer;
/*  74:    */  }
/*  75:    */  
/*  83:    */  public QName get(String name)
/*  84:    */  {
/*  85: 85 */    QName answer = null;
/*  86:    */    
/*  87: 87 */    if (name != null) {
/*  88: 88 */      answer = (QName)this.noNamespaceCache.get(name);
/*  89:    */    } else {
/*  90: 90 */      name = "";
/*  91:    */    }
/*  92:    */    
/*  93: 93 */    if (answer == null) {
/*  94: 94 */      answer = createQName(name);
/*  95: 95 */      answer.setDocumentFactory(this.documentFactory);
/*  96: 96 */      this.noNamespaceCache.put(name, answer);
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    return answer;
/* 100:    */  }
/* 101:    */  
/* 111:    */  public QName get(String name, Namespace namespace)
/* 112:    */  {
/* 113:113 */    Map cache = getNamespaceCache(namespace);
/* 114:114 */    QName answer = null;
/* 115:    */    
/* 116:116 */    if (name != null) {
/* 117:117 */      answer = (QName)cache.get(name);
/* 118:    */    } else {
/* 119:119 */      name = "";
/* 120:    */    }
/* 121:    */    
/* 122:122 */    if (answer == null) {
/* 123:123 */      answer = createQName(name, namespace);
/* 124:124 */      answer.setDocumentFactory(this.documentFactory);
/* 125:125 */      cache.put(name, answer);
/* 126:    */    }
/* 127:    */    
/* 128:128 */    return answer;
/* 129:    */  }
/* 130:    */  
/* 142:    */  public QName get(String localName, Namespace namespace, String qName)
/* 143:    */  {
/* 144:144 */    Map cache = getNamespaceCache(namespace);
/* 145:145 */    QName answer = null;
/* 146:    */    
/* 147:147 */    if (localName != null) {
/* 148:148 */      answer = (QName)cache.get(localName);
/* 149:    */    } else {
/* 150:150 */      localName = "";
/* 151:    */    }
/* 152:    */    
/* 153:153 */    if (answer == null) {
/* 154:154 */      answer = createQName(localName, namespace, qName);
/* 155:155 */      answer.setDocumentFactory(this.documentFactory);
/* 156:156 */      cache.put(localName, answer);
/* 157:    */    }
/* 158:    */    
/* 159:159 */    return answer;
/* 160:    */  }
/* 161:    */  
/* 162:    */  public QName get(String qualifiedName, String uri) {
/* 163:163 */    int index = qualifiedName.indexOf(':');
/* 164:    */    
/* 165:165 */    if (index < 0) {
/* 166:166 */      return get(qualifiedName, Namespace.get(uri));
/* 167:    */    }
/* 168:168 */    String name = qualifiedName.substring(index + 1);
/* 169:169 */    String prefix = qualifiedName.substring(0, index);
/* 170:    */    
/* 171:171 */    return get(name, Namespace.get(prefix, uri));
/* 172:    */  }
/* 173:    */  
/* 183:    */  public QName intern(QName qname)
/* 184:    */  {
/* 185:185 */    return get(qname.getName(), qname.getNamespace(), qname.getQualifiedName());
/* 186:    */  }
/* 187:    */  
/* 197:    */  protected Map getNamespaceCache(Namespace namespace)
/* 198:    */  {
/* 199:199 */    if (namespace == Namespace.NO_NAMESPACE) {
/* 200:200 */      return this.noNamespaceCache;
/* 201:    */    }
/* 202:    */    
/* 203:203 */    Map answer = null;
/* 204:    */    
/* 205:205 */    if (namespace != null) {
/* 206:206 */      answer = (Map)this.namespaceCache.get(namespace);
/* 207:    */    }
/* 208:    */    
/* 209:209 */    if (answer == null) {
/* 210:210 */      answer = createMap();
/* 211:211 */      this.namespaceCache.put(namespace, answer);
/* 212:    */    }
/* 213:    */    
/* 214:214 */    return answer;
/* 215:    */  }
/* 216:    */  
/* 221:    */  protected Map createMap()
/* 222:    */  {
/* 223:223 */    return Collections.synchronizedMap(new HashMap());
/* 224:    */  }
/* 225:    */  
/* 234:    */  protected QName createQName(String name)
/* 235:    */  {
/* 236:236 */    return new QName(name);
/* 237:    */  }
/* 238:    */  
/* 249:    */  protected QName createQName(String name, Namespace namespace)
/* 250:    */  {
/* 251:251 */    return new QName(name, namespace);
/* 252:    */  }
/* 253:    */  
/* 267:    */  protected QName createQName(String name, Namespace namespace, String qualifiedName)
/* 268:    */  {
/* 269:269 */    return new QName(name, namespace, qualifiedName);
/* 270:    */  }
/* 271:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.QNameCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */