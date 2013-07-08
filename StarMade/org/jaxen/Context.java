/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.ArrayList;
/*   5:    */import java.util.Collections;
/*   6:    */import java.util.List;
/*   7:    */
/*  88:    */public class Context
/*  89:    */  implements Serializable
/*  90:    */{
/*  91:    */  private static final long serialVersionUID = 2315979994685591055L;
/*  92:    */  private ContextSupport contextSupport;
/*  93:    */  private List nodeSet;
/*  94:    */  private int size;
/*  95:    */  private int position;
/*  96:    */  
/*  97:    */  public Context(ContextSupport contextSupport)
/*  98:    */  {
/*  99: 99 */    this.contextSupport = contextSupport;
/* 100:100 */    this.nodeSet = Collections.EMPTY_LIST;
/* 101:101 */    this.size = 0;
/* 102:102 */    this.position = 0;
/* 103:    */  }
/* 104:    */  
/* 125:    */  public void setNodeSet(List nodeSet)
/* 126:    */  {
/* 127:127 */    this.nodeSet = nodeSet;
/* 128:128 */    this.size = nodeSet.size();
/* 129:129 */    if (this.position >= this.size) { this.position = 0;
/* 130:    */    }
/* 131:    */  }
/* 132:    */  
/* 138:    */  public List getNodeSet()
/* 139:    */  {
/* 140:140 */    return this.nodeSet;
/* 141:    */  }
/* 142:    */  
/* 147:    */  public void setContextSupport(ContextSupport contextSupport)
/* 148:    */  {
/* 149:149 */    this.contextSupport = contextSupport;
/* 150:    */  }
/* 151:    */  
/* 156:    */  public ContextSupport getContextSupport()
/* 157:    */  {
/* 158:158 */    return this.contextSupport;
/* 159:    */  }
/* 160:    */  
/* 165:    */  public Navigator getNavigator()
/* 166:    */  {
/* 167:167 */    return getContextSupport().getNavigator();
/* 168:    */  }
/* 169:    */  
/* 176:    */  public String translateNamespacePrefixToUri(String prefix)
/* 177:    */  {
/* 178:178 */    return getContextSupport().translateNamespacePrefixToUri(prefix);
/* 179:    */  }
/* 180:    */  
/* 193:    */  public Object getVariableValue(String namespaceURI, String prefix, String localName)
/* 194:    */    throws UnresolvableException
/* 195:    */  {
/* 196:196 */    return getContextSupport().getVariableValue(namespaceURI, prefix, localName);
/* 197:    */  }
/* 198:    */  
/* 213:    */  public Function getFunction(String namespaceURI, String prefix, String localName)
/* 214:    */    throws UnresolvableException
/* 215:    */  {
/* 216:216 */    return getContextSupport().getFunction(namespaceURI, prefix, localName);
/* 217:    */  }
/* 218:    */  
/* 229:    */  public void setSize(int size)
/* 230:    */  {
/* 231:231 */    this.size = size;
/* 232:    */  }
/* 233:    */  
/* 238:    */  public int getSize()
/* 239:    */  {
/* 240:240 */    return this.size;
/* 241:    */  }
/* 242:    */  
/* 247:    */  public void setPosition(int position)
/* 248:    */  {
/* 249:249 */    this.position = position;
/* 250:    */  }
/* 251:    */  
/* 256:    */  public int getPosition()
/* 257:    */  {
/* 258:258 */    return this.position;
/* 259:    */  }
/* 260:    */  
/* 269:    */  public Context duplicate()
/* 270:    */  {
/* 271:271 */    Context dupe = new Context(getContextSupport());
/* 272:    */    
/* 273:273 */    List thisNodeSet = getNodeSet();
/* 274:    */    
/* 275:275 */    if (thisNodeSet != null)
/* 276:    */    {
/* 277:277 */      List dupeNodeSet = new ArrayList(thisNodeSet.size());
/* 278:278 */      dupeNodeSet.addAll(thisNodeSet);
/* 279:279 */      dupe.setNodeSet(dupeNodeSet);
/* 280:280 */      dupe.setPosition(this.position);
/* 281:    */    }
/* 282:    */    
/* 283:283 */    return dupe;
/* 284:    */  }
/* 285:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.Context
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */