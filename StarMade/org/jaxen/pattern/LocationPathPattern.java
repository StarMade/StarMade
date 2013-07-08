/*   1:    */package org.jaxen.pattern;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.List;
/*   6:    */import org.jaxen.Context;
/*   7:    */import org.jaxen.JaxenException;
/*   8:    */import org.jaxen.Navigator;
/*   9:    */import org.jaxen.expr.FilterExpr;
/*  10:    */import org.jaxen.util.SingletonList;
/*  11:    */
/*  68:    */public class LocationPathPattern
/*  69:    */  extends Pattern
/*  70:    */{
/*  71: 71 */  private NodeTest nodeTest = AnyNodeTest.getInstance();
/*  72:    */  
/*  74:    */  private Pattern parentPattern;
/*  75:    */  
/*  77:    */  private Pattern ancestorPattern;
/*  78:    */  
/*  80:    */  private List filters;
/*  81:    */  
/*  83:    */  private boolean absolute;
/*  84:    */  
/*  87:    */  public LocationPathPattern() {}
/*  88:    */  
/*  90:    */  public LocationPathPattern(NodeTest nodeTest)
/*  91:    */  {
/*  92: 92 */    this.nodeTest = nodeTest;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public Pattern simplify()
/*  96:    */  {
/*  97: 97 */    if (this.parentPattern != null)
/*  98:    */    {
/*  99: 99 */      this.parentPattern = this.parentPattern.simplify();
/* 100:    */    }
/* 101:101 */    if (this.ancestorPattern != null)
/* 102:    */    {
/* 103:103 */      this.ancestorPattern = this.ancestorPattern.simplify();
/* 104:    */    }
/* 105:105 */    if (this.filters == null)
/* 106:    */    {
/* 107:107 */      if ((this.parentPattern == null) && (this.ancestorPattern == null))
/* 108:    */      {
/* 109:109 */        return this.nodeTest;
/* 110:    */      }
/* 111:111 */      if ((this.parentPattern != null) && (this.ancestorPattern == null))
/* 112:    */      {
/* 113:113 */        if ((this.nodeTest instanceof AnyNodeTest))
/* 114:    */        {
/* 115:115 */          return this.parentPattern;
/* 116:    */        }
/* 117:    */      }
/* 118:    */    }
/* 119:119 */    return this;
/* 120:    */  }
/* 121:    */  
/* 124:    */  public void addFilter(FilterExpr filter)
/* 125:    */  {
/* 126:126 */    if (this.filters == null)
/* 127:    */    {
/* 128:128 */      this.filters = new ArrayList();
/* 129:    */    }
/* 130:130 */    this.filters.add(filter);
/* 131:    */  }
/* 132:    */  
/* 136:    */  public void setParentPattern(Pattern parentPattern)
/* 137:    */  {
/* 138:138 */    this.parentPattern = parentPattern;
/* 139:    */  }
/* 140:    */  
/* 144:    */  public void setAncestorPattern(Pattern ancestorPattern)
/* 145:    */  {
/* 146:146 */    this.ancestorPattern = ancestorPattern;
/* 147:    */  }
/* 148:    */  
/* 150:    */  public void setNodeTest(NodeTest nodeTest)
/* 151:    */    throws JaxenException
/* 152:    */  {
/* 153:153 */    if ((this.nodeTest instanceof AnyNodeTest))
/* 154:    */    {
/* 155:155 */      this.nodeTest = nodeTest;
/* 156:    */    }
/* 157:    */    else
/* 158:    */    {
/* 159:159 */      throw new JaxenException("Attempt to overwrite nodeTest: " + this.nodeTest + " with: " + nodeTest);
/* 160:    */    }
/* 161:    */  }
/* 162:    */  
/* 164:    */  public boolean matches(Object node, Context context)
/* 165:    */    throws JaxenException
/* 166:    */  {
/* 167:167 */    Navigator navigator = context.getNavigator();
/* 168:    */    
/* 175:175 */    if (!this.nodeTest.matches(node, context))
/* 176:    */    {
/* 177:177 */      return false;
/* 178:    */    }
/* 179:    */    
/* 180:180 */    if (this.parentPattern != null)
/* 181:    */    {
/* 182:182 */      Object parent = navigator.getParentNode(node);
/* 183:183 */      if (parent == null)
/* 184:    */      {
/* 185:185 */        return false;
/* 186:    */      }
/* 187:187 */      if (!this.parentPattern.matches(parent, context))
/* 188:    */      {
/* 189:189 */        return false;
/* 190:    */      }
/* 191:    */    }
/* 192:    */    
/* 193:193 */    if (this.ancestorPattern != null) {
/* 194:194 */      Object ancestor = navigator.getParentNode(node);
/* 195:    */      
/* 197:197 */      while (!this.ancestorPattern.matches(ancestor, context))
/* 198:    */      {
/* 201:201 */        if (ancestor == null)
/* 202:    */        {
/* 203:203 */          return false;
/* 204:    */        }
/* 205:205 */        if (navigator.isDocument(ancestor))
/* 206:    */        {
/* 207:207 */          return false;
/* 208:    */        }
/* 209:209 */        ancestor = navigator.getParentNode(ancestor);
/* 210:    */      }
/* 211:    */    }
/* 212:    */    
/* 213:213 */    if (this.filters != null)
/* 214:    */    {
/* 215:215 */      List list = new SingletonList(node);
/* 216:    */      
/* 217:217 */      context.setNodeSet(list);
/* 218:    */      
/* 221:221 */      boolean answer = true;
/* 222:    */      
/* 223:223 */      for (Iterator iter = this.filters.iterator(); iter.hasNext();)
/* 224:    */      {
/* 225:225 */        FilterExpr filter = (FilterExpr)iter.next();
/* 226:    */        
/* 227:227 */        if (!filter.asBoolean(context))
/* 228:    */        {
/* 229:229 */          answer = false;
/* 230:230 */          break;
/* 231:    */        }
/* 232:    */      }
/* 233:    */      
/* 235:235 */      context.setNodeSet(list);
/* 236:    */      
/* 237:237 */      return answer;
/* 238:    */    }
/* 239:239 */    return true;
/* 240:    */  }
/* 241:    */  
/* 242:    */  public double getPriority()
/* 243:    */  {
/* 244:244 */    if (this.filters != null)
/* 245:    */    {
/* 246:246 */      return 0.5D;
/* 247:    */    }
/* 248:248 */    return this.nodeTest.getPriority();
/* 249:    */  }
/* 250:    */  
/* 252:    */  public short getMatchType()
/* 253:    */  {
/* 254:254 */    return this.nodeTest.getMatchType();
/* 255:    */  }
/* 256:    */  
/* 257:    */  public String getText()
/* 258:    */  {
/* 259:259 */    StringBuffer buffer = new StringBuffer();
/* 260:260 */    if (this.absolute)
/* 261:    */    {
/* 262:262 */      buffer.append("/");
/* 263:    */    }
/* 264:264 */    if (this.ancestorPattern != null)
/* 265:    */    {
/* 266:266 */      String text = this.ancestorPattern.getText();
/* 267:267 */      if (text.length() > 0)
/* 268:    */      {
/* 269:269 */        buffer.append(text);
/* 270:270 */        buffer.append("//");
/* 271:    */      }
/* 272:    */    }
/* 273:273 */    if (this.parentPattern != null)
/* 274:    */    {
/* 275:275 */      String text = this.parentPattern.getText();
/* 276:276 */      if (text.length() > 0)
/* 277:    */      {
/* 278:278 */        buffer.append(text);
/* 279:279 */        buffer.append("/");
/* 280:    */      }
/* 281:    */    }
/* 282:282 */    buffer.append(this.nodeTest.getText());
/* 283:    */    
/* 284:284 */    if (this.filters != null)
/* 285:    */    {
/* 286:286 */      buffer.append("[");
/* 287:287 */      for (Iterator iter = this.filters.iterator(); iter.hasNext();)
/* 288:    */      {
/* 289:289 */        FilterExpr filter = (FilterExpr)iter.next();
/* 290:290 */        buffer.append(filter.getText());
/* 291:    */      }
/* 292:292 */      buffer.append("]");
/* 293:    */    }
/* 294:294 */    return buffer.toString();
/* 295:    */  }
/* 296:    */  
/* 297:    */  public String toString()
/* 298:    */  {
/* 299:299 */    return super.toString() + "[ absolute: " + this.absolute + " parent: " + this.parentPattern + " ancestor: " + this.ancestorPattern + " filters: " + this.filters + " nodeTest: " + this.nodeTest + " ]";
/* 300:    */  }
/* 301:    */  
/* 304:    */  public boolean isAbsolute()
/* 305:    */  {
/* 306:306 */    return this.absolute;
/* 307:    */  }
/* 308:    */  
/* 309:    */  public void setAbsolute(boolean absolute)
/* 310:    */  {
/* 311:311 */    this.absolute = absolute;
/* 312:    */  }
/* 313:    */  
/* 314:    */  public boolean hasAnyNodeTest()
/* 315:    */  {
/* 316:316 */    return this.nodeTest instanceof AnyNodeTest;
/* 317:    */  }
/* 318:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.LocationPathPattern
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */