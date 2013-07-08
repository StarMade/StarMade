/*   1:    */package org.dom4j;
/*   2:    */
/*   3:    */import org.dom4j.tree.AbstractNode;
/*   4:    */import org.dom4j.tree.DefaultNamespace;
/*   5:    */import org.dom4j.tree.NamespaceCache;
/*   6:    */
/*  22:    */public class Namespace
/*  23:    */  extends AbstractNode
/*  24:    */{
/*  25: 25 */  protected static final NamespaceCache CACHE = new NamespaceCache();
/*  26:    */  
/*  28: 28 */  public static final Namespace XML_NAMESPACE = CACHE.get("xml", "http://www.w3.org/XML/1998/namespace");
/*  29:    */  
/*  32: 32 */  public static final Namespace NO_NAMESPACE = CACHE.get("", "");
/*  33:    */  
/*  37:    */  private String prefix;
/*  38:    */  
/*  41:    */  private String uri;
/*  42:    */  
/*  45:    */  private int hashCode;
/*  46:    */  
/*  50:    */  public Namespace(String prefix, String uri)
/*  51:    */  {
/*  52: 52 */    this.prefix = (prefix != null ? prefix : "");
/*  53: 53 */    this.uri = (uri != null ? uri : "");
/*  54:    */  }
/*  55:    */  
/*  66:    */  public static Namespace get(String prefix, String uri)
/*  67:    */  {
/*  68: 68 */    return CACHE.get(prefix, uri);
/*  69:    */  }
/*  70:    */  
/*  79:    */  public static Namespace get(String uri)
/*  80:    */  {
/*  81: 81 */    return CACHE.get(uri);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public short getNodeType() {
/*  85: 85 */    return 13;
/*  86:    */  }
/*  87:    */  
/*  93:    */  public int hashCode()
/*  94:    */  {
/*  95: 95 */    if (this.hashCode == 0) {
/*  96: 96 */      this.hashCode = createHashCode();
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    return this.hashCode;
/* 100:    */  }
/* 101:    */  
/* 107:    */  protected int createHashCode()
/* 108:    */  {
/* 109:109 */    int result = this.uri.hashCode() ^ this.prefix.hashCode();
/* 110:    */    
/* 111:111 */    if (result == 0) {
/* 112:112 */      result = 47806;
/* 113:    */    }
/* 114:    */    
/* 115:115 */    return result;
/* 116:    */  }
/* 117:    */  
/* 126:    */  public boolean equals(Object object)
/* 127:    */  {
/* 128:128 */    if (this == object)
/* 129:129 */      return true;
/* 130:130 */    if ((object instanceof Namespace)) {
/* 131:131 */      Namespace that = (Namespace)object;
/* 132:    */      
/* 134:134 */      if (hashCode() == that.hashCode()) {
/* 135:135 */        return (this.uri.equals(that.getURI())) && (this.prefix.equals(that.getPrefix()));
/* 136:    */      }
/* 137:    */    }
/* 138:    */    
/* 140:140 */    return false;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public String getText() {
/* 144:144 */    return this.uri;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public String getStringValue() {
/* 148:148 */    return this.uri;
/* 149:    */  }
/* 150:    */  
/* 155:    */  public String getPrefix()
/* 156:    */  {
/* 157:157 */    return this.prefix;
/* 158:    */  }
/* 159:    */  
/* 164:    */  public String getURI()
/* 165:    */  {
/* 166:166 */    return this.uri;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public String getXPathNameStep() {
/* 170:170 */    if ((this.prefix != null) && (!"".equals(this.prefix))) {
/* 171:171 */      return "namespace::" + this.prefix;
/* 172:    */    }
/* 173:    */    
/* 174:174 */    return "namespace::*[name()='']";
/* 175:    */  }
/* 176:    */  
/* 177:    */  public String getPath(Element context) {
/* 178:178 */    StringBuffer path = new StringBuffer(10);
/* 179:179 */    Element parent = getParent();
/* 180:    */    
/* 181:181 */    if ((parent != null) && (parent != context)) {
/* 182:182 */      path.append(parent.getPath(context));
/* 183:183 */      path.append('/');
/* 184:    */    }
/* 185:    */    
/* 186:186 */    path.append(getXPathNameStep());
/* 187:    */    
/* 188:188 */    return path.toString();
/* 189:    */  }
/* 190:    */  
/* 191:    */  public String getUniquePath(Element context) {
/* 192:192 */    StringBuffer path = new StringBuffer(10);
/* 193:193 */    Element parent = getParent();
/* 194:    */    
/* 195:195 */    if ((parent != null) && (parent != context)) {
/* 196:196 */      path.append(parent.getUniquePath(context));
/* 197:197 */      path.append('/');
/* 198:    */    }
/* 199:    */    
/* 200:200 */    path.append(getXPathNameStep());
/* 201:    */    
/* 202:202 */    return path.toString();
/* 203:    */  }
/* 204:    */  
/* 205:    */  public String toString() {
/* 206:206 */    return super.toString() + " [Namespace: prefix " + getPrefix() + " mapped to URI \"" + getURI() + "\"]";
/* 207:    */  }
/* 208:    */  
/* 209:    */  public String asXML()
/* 210:    */  {
/* 211:211 */    StringBuffer asxml = new StringBuffer(10);
/* 212:212 */    String pref = getPrefix();
/* 213:    */    
/* 214:214 */    if ((pref != null) && (pref.length() > 0)) {
/* 215:215 */      asxml.append("xmlns:");
/* 216:216 */      asxml.append(pref);
/* 217:217 */      asxml.append("=\"");
/* 218:    */    } else {
/* 219:219 */      asxml.append("xmlns=\"");
/* 220:    */    }
/* 221:    */    
/* 222:222 */    asxml.append(getURI());
/* 223:223 */    asxml.append("\"");
/* 224:    */    
/* 225:225 */    return asxml.toString();
/* 226:    */  }
/* 227:    */  
/* 228:    */  public void accept(Visitor visitor) {
/* 229:229 */    visitor.visit(this);
/* 230:    */  }
/* 231:    */  
/* 232:    */  protected Node createXPathResult(Element parent) {
/* 233:233 */    return new DefaultNamespace(parent, getPrefix(), getURI());
/* 234:    */  }
/* 235:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.Namespace
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */