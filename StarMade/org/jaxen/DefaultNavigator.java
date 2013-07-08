/*   1:    */package org.jaxen;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import org.jaxen.util.AncestorAxisIterator;
/*   5:    */import org.jaxen.util.AncestorOrSelfAxisIterator;
/*   6:    */import org.jaxen.util.DescendantAxisIterator;
/*   7:    */import org.jaxen.util.DescendantOrSelfAxisIterator;
/*   8:    */import org.jaxen.util.FollowingAxisIterator;
/*   9:    */import org.jaxen.util.FollowingSiblingAxisIterator;
/*  10:    */import org.jaxen.util.PrecedingAxisIterator;
/*  11:    */import org.jaxen.util.PrecedingSiblingAxisIterator;
/*  12:    */import org.jaxen.util.SelfAxisIterator;
/*  13:    */
/*  89:    */public abstract class DefaultNavigator
/*  90:    */  implements Navigator
/*  91:    */{
/*  92:    */  public Iterator getChildAxisIterator(Object contextNode)
/*  93:    */    throws UnsupportedAxisException
/*  94:    */  {
/*  95: 95 */    throw new UnsupportedAxisException("child");
/*  96:    */  }
/*  97:    */  
/* 100:    */  public Iterator getDescendantAxisIterator(Object contextNode)
/* 101:    */    throws UnsupportedAxisException
/* 102:    */  {
/* 103:103 */    return new DescendantAxisIterator(contextNode, this);
/* 104:    */  }
/* 105:    */  
/* 112:    */  public Iterator getParentAxisIterator(Object contextNode)
/* 113:    */    throws UnsupportedAxisException
/* 114:    */  {
/* 115:115 */    throw new UnsupportedAxisException("parent");
/* 116:    */  }
/* 117:    */  
/* 118:    */  public Iterator getAncestorAxisIterator(Object contextNode) throws UnsupportedAxisException
/* 119:    */  {
/* 120:120 */    return new AncestorAxisIterator(contextNode, this);
/* 121:    */  }
/* 122:    */  
/* 124:    */  public Iterator getFollowingSiblingAxisIterator(Object contextNode)
/* 125:    */    throws UnsupportedAxisException
/* 126:    */  {
/* 127:127 */    return new FollowingSiblingAxisIterator(contextNode, this);
/* 128:    */  }
/* 129:    */  
/* 131:    */  public Iterator getPrecedingSiblingAxisIterator(Object contextNode)
/* 132:    */    throws UnsupportedAxisException
/* 133:    */  {
/* 134:134 */    return new PrecedingSiblingAxisIterator(contextNode, this);
/* 135:    */  }
/* 136:    */  
/* 137:    */  public Iterator getFollowingAxisIterator(Object contextNode)
/* 138:    */    throws UnsupportedAxisException
/* 139:    */  {
/* 140:140 */    return new FollowingAxisIterator(contextNode, this);
/* 141:    */  }
/* 142:    */  
/* 146:    */  public Iterator getPrecedingAxisIterator(Object contextNode)
/* 147:    */    throws UnsupportedAxisException
/* 148:    */  {
/* 149:149 */    return new PrecedingAxisIterator(contextNode, this);
/* 150:    */  }
/* 151:    */  
/* 161:    */  public Iterator getAttributeAxisIterator(Object contextNode)
/* 162:    */    throws UnsupportedAxisException
/* 163:    */  {
/* 164:164 */    throw new UnsupportedAxisException("attribute");
/* 165:    */  }
/* 166:    */  
/* 173:    */  public Iterator getNamespaceAxisIterator(Object contextNode)
/* 174:    */    throws UnsupportedAxisException
/* 175:    */  {
/* 176:176 */    throw new UnsupportedAxisException("namespace");
/* 177:    */  }
/* 178:    */  
/* 179:    */  public Iterator getSelfAxisIterator(Object contextNode) throws UnsupportedAxisException
/* 180:    */  {
/* 181:181 */    return new SelfAxisIterator(contextNode);
/* 182:    */  }
/* 183:    */  
/* 184:    */  public Iterator getDescendantOrSelfAxisIterator(Object contextNode) throws UnsupportedAxisException
/* 185:    */  {
/* 186:186 */    return new DescendantOrSelfAxisIterator(contextNode, this);
/* 187:    */  }
/* 188:    */  
/* 189:    */  public Iterator getAncestorOrSelfAxisIterator(Object contextNode)
/* 190:    */    throws UnsupportedAxisException
/* 191:    */  {
/* 192:192 */    return new AncestorOrSelfAxisIterator(contextNode, this);
/* 193:    */  }
/* 194:    */  
/* 196:    */  public Object getDocumentNode(Object contextNode)
/* 197:    */  {
/* 198:198 */    return null;
/* 199:    */  }
/* 200:    */  
/* 201:    */  public String translateNamespacePrefixToUri(String prefix, Object element)
/* 202:    */  {
/* 203:203 */    return null;
/* 204:    */  }
/* 205:    */  
/* 206:    */  public String getProcessingInstructionTarget(Object obj)
/* 207:    */  {
/* 208:208 */    return null;
/* 209:    */  }
/* 210:    */  
/* 211:    */  public String getProcessingInstructionData(Object obj)
/* 212:    */  {
/* 213:213 */    return null;
/* 214:    */  }
/* 215:    */  
/* 216:    */  public short getNodeType(Object node)
/* 217:    */  {
/* 218:218 */    if (isElement(node))
/* 219:    */    {
/* 220:220 */      return 1;
/* 221:    */    }
/* 222:222 */    if (isAttribute(node))
/* 223:    */    {
/* 224:224 */      return 2;
/* 225:    */    }
/* 226:226 */    if (isText(node))
/* 227:    */    {
/* 228:228 */      return 3;
/* 229:    */    }
/* 230:230 */    if (isComment(node))
/* 231:    */    {
/* 232:232 */      return 8;
/* 233:    */    }
/* 234:234 */    if (isDocument(node))
/* 235:    */    {
/* 236:236 */      return 9;
/* 237:    */    }
/* 238:238 */    if (isProcessingInstruction(node))
/* 239:    */    {
/* 240:240 */      return 7;
/* 241:    */    }
/* 242:242 */    if (isNamespace(node))
/* 243:    */    {
/* 244:244 */      return 13;
/* 245:    */    }
/* 246:    */    
/* 247:247 */    return 14;
/* 248:    */  }
/* 249:    */  
/* 258:    */  public Object getParentNode(Object contextNode)
/* 259:    */    throws UnsupportedAxisException
/* 260:    */  {
/* 261:261 */    Iterator iter = getParentAxisIterator(contextNode);
/* 262:262 */    if ((iter != null) && (iter.hasNext()))
/* 263:    */    {
/* 264:264 */      return iter.next();
/* 265:    */    }
/* 266:266 */    return null;
/* 267:    */  }
/* 268:    */  
/* 278:    */  public Object getDocument(String url)
/* 279:    */    throws FunctionCallException
/* 280:    */  {
/* 281:281 */    return null;
/* 282:    */  }
/* 283:    */  
/* 294:    */  public Object getElementById(Object contextNode, String elementId)
/* 295:    */  {
/* 296:296 */    return null;
/* 297:    */  }
/* 298:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.DefaultNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */