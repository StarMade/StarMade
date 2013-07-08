/*   1:    */package org.dom4j;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.ObjectInputStream;
/*   5:    */import java.io.ObjectOutputStream;
/*   6:    */import java.io.Serializable;
/*   7:    */import org.dom4j.tree.QNameCache;
/*   8:    */import org.dom4j.util.SingletonStrategy;
/*   9:    */
/*  26:    */public class QName
/*  27:    */  implements Serializable
/*  28:    */{
/*  29: 29 */  private static SingletonStrategy singleton = null;
/*  30:    */  private String name;
/*  31:    */  private String qualifiedName;
/*  32:    */  
/*  33: 33 */  static { try { String defaultSingletonClass = "org.dom4j.util.SimpleSingleton";
/*  34: 34 */      Class clazz = null;
/*  35:    */      try {
/*  36: 36 */        String singletonClass = defaultSingletonClass;
/*  37: 37 */        singletonClass = System.getProperty("org.dom4j.QName.singleton.strategy", singletonClass);
/*  38:    */        
/*  39: 39 */        clazz = Class.forName(singletonClass);
/*  40:    */      } catch (Exception exc1) {
/*  41:    */        try {
/*  42: 42 */          String singletonClass = defaultSingletonClass;
/*  43: 43 */          clazz = Class.forName(singletonClass);
/*  44:    */        }
/*  45:    */        catch (Exception exc2) {}
/*  46:    */      }
/*  47: 47 */      singleton = (SingletonStrategy)clazz.newInstance();
/*  48: 48 */      singleton.setSingletonClassName(QNameCache.class.getName());
/*  49:    */    }
/*  50:    */    catch (Exception exc3) {}
/*  51:    */  }
/*  52:    */  
/*  56:    */  private transient Namespace namespace;
/*  57:    */  
/*  60:    */  private int hashCode;
/*  61:    */  
/*  64:    */  private DocumentFactory documentFactory;
/*  65:    */  
/*  67:    */  public QName(String name)
/*  68:    */  {
/*  69: 69 */    this(name, Namespace.NO_NAMESPACE);
/*  70:    */  }
/*  71:    */  
/*  72:    */  public QName(String name, Namespace namespace) {
/*  73: 73 */    this.name = (name == null ? "" : name);
/*  74: 74 */    this.namespace = (namespace == null ? Namespace.NO_NAMESPACE : namespace);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public QName(String name, Namespace namespace, String qualifiedName)
/*  78:    */  {
/*  79: 79 */    this.name = (name == null ? "" : name);
/*  80: 80 */    this.qualifiedName = qualifiedName;
/*  81: 81 */    this.namespace = (namespace == null ? Namespace.NO_NAMESPACE : namespace);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public static QName get(String name)
/*  85:    */  {
/*  86: 86 */    return getCache().get(name);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public static QName get(String name, Namespace namespace) {
/*  90: 90 */    return getCache().get(name, namespace);
/*  91:    */  }
/*  92:    */  
/*  93:    */  public static QName get(String name, String prefix, String uri) {
/*  94: 94 */    if (((prefix == null) || (prefix.length() == 0)) && (uri == null))
/*  95: 95 */      return get(name);
/*  96: 96 */    if ((prefix == null) || (prefix.length() == 0))
/*  97: 97 */      return getCache().get(name, Namespace.get(uri));
/*  98: 98 */    if (uri == null) {
/*  99: 99 */      return get(name);
/* 100:    */    }
/* 101:101 */    return getCache().get(name, Namespace.get(prefix, uri));
/* 102:    */  }
/* 103:    */  
/* 104:    */  public static QName get(String qualifiedName, String uri)
/* 105:    */  {
/* 106:106 */    if (uri == null) {
/* 107:107 */      return getCache().get(qualifiedName);
/* 108:    */    }
/* 109:109 */    return getCache().get(qualifiedName, uri);
/* 110:    */  }
/* 111:    */  
/* 113:    */  public static QName get(String localName, Namespace namespace, String qualifiedName)
/* 114:    */  {
/* 115:115 */    return getCache().get(localName, namespace, qualifiedName);
/* 116:    */  }
/* 117:    */  
/* 122:    */  public String getName()
/* 123:    */  {
/* 124:124 */    return this.name;
/* 125:    */  }
/* 126:    */  
/* 131:    */  public String getQualifiedName()
/* 132:    */  {
/* 133:133 */    if (this.qualifiedName == null) {
/* 134:134 */      String prefix = getNamespacePrefix();
/* 135:    */      
/* 136:136 */      if ((prefix != null) && (prefix.length() > 0)) {
/* 137:137 */        this.qualifiedName = (prefix + ":" + this.name);
/* 138:    */      } else {
/* 139:139 */        this.qualifiedName = this.name;
/* 140:    */      }
/* 141:    */    }
/* 142:    */    
/* 143:143 */    return this.qualifiedName;
/* 144:    */  }
/* 145:    */  
/* 150:    */  public Namespace getNamespace()
/* 151:    */  {
/* 152:152 */    return this.namespace;
/* 153:    */  }
/* 154:    */  
/* 159:    */  public String getNamespacePrefix()
/* 160:    */  {
/* 161:161 */    if (this.namespace == null) {
/* 162:162 */      return "";
/* 163:    */    }
/* 164:    */    
/* 165:165 */    return this.namespace.getPrefix();
/* 166:    */  }
/* 167:    */  
/* 172:    */  public String getNamespaceURI()
/* 173:    */  {
/* 174:174 */    if (this.namespace == null) {
/* 175:175 */      return "";
/* 176:    */    }
/* 177:    */    
/* 178:178 */    return this.namespace.getURI();
/* 179:    */  }
/* 180:    */  
/* 186:    */  public int hashCode()
/* 187:    */  {
/* 188:188 */    if (this.hashCode == 0) {
/* 189:189 */      this.hashCode = (getName().hashCode() ^ getNamespaceURI().hashCode());
/* 190:    */      
/* 191:191 */      if (this.hashCode == 0) {
/* 192:192 */        this.hashCode = 47806;
/* 193:    */      }
/* 194:    */    }
/* 195:    */    
/* 196:196 */    return this.hashCode;
/* 197:    */  }
/* 198:    */  
/* 199:    */  public boolean equals(Object object) {
/* 200:200 */    if (this == object)
/* 201:201 */      return true;
/* 202:202 */    if ((object instanceof QName)) {
/* 203:203 */      QName that = (QName)object;
/* 204:    */      
/* 206:206 */      if (hashCode() == that.hashCode()) {
/* 207:207 */        return (getName().equals(that.getName())) && (getNamespaceURI().equals(that.getNamespaceURI()));
/* 208:    */      }
/* 209:    */    }
/* 210:    */    
/* 212:212 */    return false;
/* 213:    */  }
/* 214:    */  
/* 215:    */  public String toString() {
/* 216:216 */    return super.toString() + " [name: " + getName() + " namespace: \"" + getNamespace() + "\"]";
/* 217:    */  }
/* 218:    */  
/* 224:    */  public DocumentFactory getDocumentFactory()
/* 225:    */  {
/* 226:226 */    return this.documentFactory;
/* 227:    */  }
/* 228:    */  
/* 229:    */  public void setDocumentFactory(DocumentFactory documentFactory) {
/* 230:230 */    this.documentFactory = documentFactory;
/* 231:    */  }
/* 232:    */  
/* 233:    */  private void writeObject(ObjectOutputStream out)
/* 234:    */    throws IOException
/* 235:    */  {
/* 236:236 */    out.writeObject(this.namespace.getPrefix());
/* 237:237 */    out.writeObject(this.namespace.getURI());
/* 238:    */    
/* 239:239 */    out.defaultWriteObject();
/* 240:    */  }
/* 241:    */  
/* 242:    */  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
/* 243:    */  {
/* 244:244 */    String prefix = (String)in.readObject();
/* 245:245 */    String uri = (String)in.readObject();
/* 246:    */    
/* 247:247 */    in.defaultReadObject();
/* 248:    */    
/* 249:249 */    this.namespace = Namespace.get(prefix, uri);
/* 250:    */  }
/* 251:    */  
/* 252:    */  private static QNameCache getCache() {
/* 253:253 */    QNameCache cache = (QNameCache)singleton.instance();
/* 254:254 */    return cache;
/* 255:    */  }
/* 256:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.QName
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */