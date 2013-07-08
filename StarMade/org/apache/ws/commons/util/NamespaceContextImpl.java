/*   1:    */package org.apache.ws.commons.util;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Collections;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import javax.xml.namespace.NamespaceContext;
/*   8:    */
/*  36:    */public class NamespaceContextImpl
/*  37:    */  implements NamespaceContext
/*  38:    */{
/*  39:    */  private List prefixList;
/*  40:    */  private String cachedPrefix;
/*  41:    */  private String cachedURI;
/*  42:    */  
/*  43:    */  public void reset()
/*  44:    */  {
/*  45: 45 */    this.cachedURI = (this.cachedPrefix = null);
/*  46: 46 */    if (this.prefixList != null) {
/*  47: 47 */      this.prefixList.clear();
/*  48:    */    }
/*  49:    */  }
/*  50:    */  
/*  54:    */  public void startPrefixMapping(String pPrefix, String pURI)
/*  55:    */  {
/*  56: 56 */    if (pPrefix == null) {
/*  57: 57 */      throw new IllegalArgumentException("The namespace prefix must not be null.");
/*  58:    */    }
/*  59: 59 */    if (pURI == null) {
/*  60: 60 */      throw new IllegalArgumentException("The namespace prefix must not be null.");
/*  61:    */    }
/*  62: 62 */    if (this.cachedURI != null) {
/*  63: 63 */      if (this.prefixList == null) this.prefixList = new ArrayList();
/*  64: 64 */      this.prefixList.add(this.cachedPrefix);
/*  65: 65 */      this.prefixList.add(this.cachedURI);
/*  66:    */    }
/*  67: 67 */    this.cachedURI = pURI;
/*  68: 68 */    this.cachedPrefix = pPrefix;
/*  69:    */  }
/*  70:    */  
/*  79:    */  public void endPrefixMapping(String pPrefix)
/*  80:    */  {
/*  81: 81 */    if (pPrefix == null) {
/*  82: 82 */      throw new IllegalArgumentException("The namespace prefix must not be null.");
/*  83:    */    }
/*  84: 84 */    if (pPrefix.equals(this.cachedPrefix)) {
/*  85: 85 */      if ((this.prefixList != null) && (this.prefixList.size() > 0)) {
/*  86: 86 */        this.cachedURI = this.prefixList.remove(this.prefixList.size() - 1).toString();
/*  87: 87 */        this.cachedPrefix = this.prefixList.remove(this.prefixList.size() - 1).toString();
/*  88:    */      } else {
/*  89: 89 */        this.cachedPrefix = (this.cachedURI = null);
/*  90:    */      }
/*  91:    */    } else {
/*  92: 92 */      throw new IllegalStateException("The prefix " + pPrefix + " isn't the prefix, which has been defined last.");
/*  93:    */    }
/*  94:    */  }
/*  95:    */  
/* 102:    */  public String getNamespaceURI(String pPrefix)
/* 103:    */  {
/* 104:104 */    if (pPrefix == null) {
/* 105:105 */      throw new IllegalArgumentException("The namespace prefix must not be null.");
/* 106:    */    }
/* 107:107 */    if (this.cachedURI != null) {
/* 108:108 */      if (this.cachedPrefix.equals(pPrefix)) return this.cachedURI;
/* 109:109 */      if (this.prefixList != null) {
/* 110:110 */        for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 111:111 */          if (pPrefix.equals(this.prefixList.get(i - 2))) {
/* 112:112 */            return (String)this.prefixList.get(i - 1);
/* 113:    */          }
/* 114:    */        }
/* 115:    */      }
/* 116:    */    }
/* 117:117 */    if ("xml".equals(pPrefix))
/* 118:118 */      return "http://www.w3.org/XML/1998/namespace";
/* 119:119 */    if ("xmlns".equals(pPrefix)) {
/* 120:120 */      return "http://www.w3.org/2000/xmlns/";
/* 121:    */    }
/* 122:122 */    return null;
/* 123:    */  }
/* 124:    */  
/* 133:    */  public String getPrefix(String pURI)
/* 134:    */  {
/* 135:135 */    if (pURI == null) {
/* 136:136 */      throw new IllegalArgumentException("The namespace URI must not be null.");
/* 137:    */    }
/* 138:138 */    if (this.cachedURI != null) {
/* 139:139 */      if (this.cachedURI.equals(pURI)) return this.cachedPrefix;
/* 140:140 */      if (this.prefixList != null) {
/* 141:141 */        for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 142:142 */          if (pURI.equals(this.prefixList.get(i - 1))) {
/* 143:143 */            return (String)this.prefixList.get(i - 2);
/* 144:    */          }
/* 145:    */        }
/* 146:    */      }
/* 147:    */    }
/* 148:148 */    if ("http://www.w3.org/XML/1998/namespace".equals(pURI))
/* 149:149 */      return "xml";
/* 150:150 */    if ("http://www.w3.org/2000/xmlns/".equals(pURI)) {
/* 151:151 */      return "xmlns";
/* 152:    */    }
/* 153:153 */    return null;
/* 154:    */  }
/* 155:    */  
/* 162:    */  public String getAttributePrefix(String pURI)
/* 163:    */  {
/* 164:164 */    if (pURI == null) {
/* 165:165 */      throw new IllegalArgumentException("The namespace URI must not be null.");
/* 166:    */    }
/* 167:167 */    if (pURI.length() == 0) {
/* 168:168 */      return "";
/* 169:    */    }
/* 170:170 */    if (this.cachedURI != null) {
/* 171:171 */      if ((this.cachedURI.equals(pURI)) && (this.cachedPrefix.length() > 0)) {
/* 172:172 */        return this.cachedPrefix;
/* 173:    */      }
/* 174:174 */      if (this.prefixList != null) {
/* 175:175 */        for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 176:176 */          if (pURI.equals(this.prefixList.get(i - 1))) {
/* 177:177 */            String prefix = (String)this.prefixList.get(i - 2);
/* 178:178 */            if (prefix.length() > 0) {
/* 179:179 */              return prefix;
/* 180:    */            }
/* 181:    */          }
/* 182:    */        }
/* 183:    */      }
/* 184:    */    }
/* 185:185 */    if ("http://www.w3.org/XML/1998/namespace".equals(pURI))
/* 186:186 */      return "xml";
/* 187:187 */    if ("http://www.w3.org/2000/xmlns/".equals(pURI)) {
/* 188:188 */      return "xmlns";
/* 189:    */    }
/* 190:190 */    return null;
/* 191:    */  }
/* 192:    */  
/* 198:    */  public Iterator getPrefixes(String pURI)
/* 199:    */  {
/* 200:200 */    if (pURI == null) {
/* 201:201 */      throw new IllegalArgumentException("The namespace URI must not be null.");
/* 202:    */    }
/* 203:203 */    List list = new ArrayList();
/* 204:204 */    if (this.cachedURI != null) {
/* 205:205 */      if (this.cachedURI.equals(pURI)) list.add(this.cachedPrefix);
/* 206:206 */      if (this.prefixList != null) {
/* 207:207 */        for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 208:208 */          if (pURI.equals(this.prefixList.get(i - 1))) {
/* 209:209 */            list.add(this.prefixList.get(i - 2));
/* 210:    */          }
/* 211:    */        }
/* 212:    */      }
/* 213:    */    }
/* 214:214 */    if (pURI.equals("http://www.w3.org/2000/xmlns/")) {
/* 215:215 */      list.add("xmlns");
/* 216:216 */    } else if (pURI.equals("http://www.w3.org/XML/1998/namespace")) {
/* 217:217 */      list.add("xml");
/* 218:    */    }
/* 219:219 */    return list.iterator();
/* 220:    */  }
/* 221:    */  
/* 223:    */  public boolean isPrefixDeclared(String pPrefix)
/* 224:    */  {
/* 225:225 */    if (this.cachedURI != null) {
/* 226:226 */      if ((this.cachedPrefix != null) && (this.cachedPrefix.equals(pPrefix))) return true;
/* 227:227 */      if (this.prefixList != null) {
/* 228:228 */        for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 229:229 */          if (this.prefixList.get(i - 2).equals(pPrefix)) {
/* 230:230 */            return true;
/* 231:    */          }
/* 232:    */        }
/* 233:    */      }
/* 234:    */    }
/* 235:235 */    return "xml".equals(pPrefix);
/* 236:    */  }
/* 237:    */  
/* 248:    */  public int getContext()
/* 249:    */  {
/* 250:250 */    return (this.prefixList == null ? 0 : this.prefixList.size()) + (this.cachedURI == null ? 0 : 2);
/* 251:    */  }
/* 252:    */  
/* 275:    */  public String checkContext(int i)
/* 276:    */  {
/* 277:277 */    if (getContext() == i) {
/* 278:278 */      return null;
/* 279:    */    }
/* 280:280 */    String result = this.cachedPrefix;
/* 281:281 */    if ((this.prefixList != null) && (this.prefixList.size() > 0)) {
/* 282:282 */      this.cachedURI = this.prefixList.remove(this.prefixList.size() - 1).toString();
/* 283:283 */      this.cachedPrefix = this.prefixList.remove(this.prefixList.size() - 1).toString();
/* 284:    */    } else {
/* 285:285 */      this.cachedURI = null;
/* 286:286 */      this.cachedPrefix = null;
/* 287:    */    }
/* 288:288 */    return result;
/* 289:    */  }
/* 290:    */  
/* 295:    */  public List getPrefixes()
/* 296:    */  {
/* 297:297 */    if (this.cachedPrefix == null)
/* 298:298 */      return Collections.EMPTY_LIST;
/* 299:299 */    if (this.prefixList == null) {
/* 300:300 */      return Collections.singletonList(this.cachedPrefix);
/* 301:    */    }
/* 302:302 */    List result = new ArrayList(this.prefixList.size() + 1);
/* 303:303 */    for (int i = 0; i < this.prefixList.size(); i += 2) {
/* 304:304 */      result.add(this.prefixList.get(i));
/* 305:    */    }
/* 306:306 */    result.add(this.cachedPrefix);
/* 307:307 */    return result;
/* 308:    */  }
/* 309:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.util.NamespaceContextImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */