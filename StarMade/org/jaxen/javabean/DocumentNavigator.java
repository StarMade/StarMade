/*   1:    */package org.jaxen.javabean;
/*   2:    */
/*   3:    */import java.lang.reflect.InvocationTargetException;
/*   4:    */import java.lang.reflect.Method;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.Iterator;
/*   7:    */import org.jaxen.DefaultNavigator;
/*   8:    */import org.jaxen.FunctionCallException;
/*   9:    */import org.jaxen.JaxenConstants;
/*  10:    */import org.jaxen.NamedAccessNavigator;
/*  11:    */import org.jaxen.Navigator;
/*  12:    */import org.jaxen.XPath;
/*  13:    */import org.jaxen.saxpath.SAXPathException;
/*  14:    */import org.jaxen.util.SingleObjectIterator;
/*  15:    */
/*  67:    */public class DocumentNavigator
/*  68:    */  extends DefaultNavigator
/*  69:    */  implements NamedAccessNavigator
/*  70:    */{
/*  71:    */  private static final long serialVersionUID = -1768605107626726499L;
/*  72: 72 */  private static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
/*  73:    */  
/*  75: 75 */  private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
/*  76:    */  
/*  79: 79 */  private static final DocumentNavigator instance = new DocumentNavigator();
/*  80:    */  
/*  83:    */  public static Navigator getInstance()
/*  84:    */  {
/*  85: 85 */    return instance;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public boolean isElement(Object obj)
/*  89:    */  {
/*  90: 90 */    return obj instanceof Element;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public boolean isComment(Object obj)
/*  94:    */  {
/*  95: 95 */    return false;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public boolean isText(Object obj)
/*  99:    */  {
/* 100:100 */    return obj instanceof String;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean isAttribute(Object obj)
/* 104:    */  {
/* 105:105 */    return false;
/* 106:    */  }
/* 107:    */  
/* 108:    */  public boolean isProcessingInstruction(Object obj)
/* 109:    */  {
/* 110:110 */    return false;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public boolean isDocument(Object obj)
/* 114:    */  {
/* 115:115 */    return false;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public boolean isNamespace(Object obj)
/* 119:    */  {
/* 120:120 */    return false;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public String getElementName(Object obj)
/* 124:    */  {
/* 125:125 */    return ((Element)obj).getName();
/* 126:    */  }
/* 127:    */  
/* 128:    */  public String getElementNamespaceUri(Object obj)
/* 129:    */  {
/* 130:130 */    return "";
/* 131:    */  }
/* 132:    */  
/* 133:    */  public String getElementQName(Object obj)
/* 134:    */  {
/* 135:135 */    return "";
/* 136:    */  }
/* 137:    */  
/* 138:    */  public String getAttributeName(Object obj)
/* 139:    */  {
/* 140:140 */    return "";
/* 141:    */  }
/* 142:    */  
/* 143:    */  public String getAttributeNamespaceUri(Object obj)
/* 144:    */  {
/* 145:145 */    return "";
/* 146:    */  }
/* 147:    */  
/* 148:    */  public String getAttributeQName(Object obj)
/* 149:    */  {
/* 150:150 */    return "";
/* 151:    */  }
/* 152:    */  
/* 153:    */  public Iterator getChildAxisIterator(Object contextNode)
/* 154:    */  {
/* 155:155 */    return JaxenConstants.EMPTY_ITERATOR;
/* 156:    */  }
/* 157:    */  
/* 171:    */  public Iterator getChildAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/* 172:    */  {
/* 173:173 */    Class cls = ((Element)contextNode).getObject().getClass();
/* 174:    */    
/* 175:175 */    String methodName = javacase(localName);
/* 176:    */    
/* 177:177 */    Method method = null;
/* 178:    */    
/* 179:    */    try
/* 180:    */    {
/* 181:181 */      method = cls.getMethod("get" + methodName, EMPTY_CLASS_ARRAY);
/* 182:    */    }
/* 183:    */    catch (NoSuchMethodException e)
/* 184:    */    {
/* 185:    */      try
/* 186:    */      {
/* 187:187 */        method = cls.getMethod("get" + methodName + "s", EMPTY_CLASS_ARRAY);
/* 188:    */      }
/* 189:    */      catch (NoSuchMethodException ee)
/* 190:    */      {
/* 191:    */        try
/* 192:    */        {
/* 193:193 */          method = cls.getMethod(localName, EMPTY_CLASS_ARRAY);
/* 194:    */        }
/* 195:    */        catch (NoSuchMethodException eee)
/* 196:    */        {
/* 197:197 */          method = null;
/* 198:    */        }
/* 199:    */      }
/* 200:    */    }
/* 201:    */    
/* 202:202 */    if (method == null)
/* 203:    */    {
/* 204:204 */      return JaxenConstants.EMPTY_ITERATOR;
/* 205:    */    }
/* 206:    */    
/* 207:    */    try
/* 208:    */    {
/* 209:209 */      Object result = method.invoke(((Element)contextNode).getObject(), EMPTY_OBJECT_ARRAY);
/* 210:    */      
/* 211:211 */      if (result == null)
/* 212:    */      {
/* 213:213 */        return JaxenConstants.EMPTY_ITERATOR;
/* 214:    */      }
/* 215:    */      
/* 216:216 */      if ((result instanceof Collection))
/* 217:    */      {
/* 218:218 */        return new ElementIterator((Element)contextNode, localName, ((Collection)result).iterator());
/* 219:    */      }
/* 220:    */      
/* 221:221 */      if (result.getClass().isArray())
/* 222:    */      {
/* 223:223 */        return JaxenConstants.EMPTY_ITERATOR;
/* 224:    */      }
/* 225:    */      
/* 226:226 */      return new SingleObjectIterator(new Element((Element)contextNode, localName, result));
/* 227:    */    }
/* 228:    */    catch (IllegalAccessException e) {}catch (InvocationTargetException e) {}
/* 229:    */    
/* 237:237 */    return JaxenConstants.EMPTY_ITERATOR;
/* 238:    */  }
/* 239:    */  
/* 240:    */  public Iterator getParentAxisIterator(Object contextNode)
/* 241:    */  {
/* 242:242 */    if ((contextNode instanceof Element))
/* 243:    */    {
/* 244:244 */      return new SingleObjectIterator(((Element)contextNode).getParent());
/* 245:    */    }
/* 246:    */    
/* 247:247 */    return JaxenConstants.EMPTY_ITERATOR;
/* 248:    */  }
/* 249:    */  
/* 250:    */  public Iterator getAttributeAxisIterator(Object contextNode)
/* 251:    */  {
/* 252:252 */    return JaxenConstants.EMPTY_ITERATOR;
/* 253:    */  }
/* 254:    */  
/* 267:    */  public Iterator getAttributeAxisIterator(Object contextNode, String localName, String namespacePrefix, String namespaceURI)
/* 268:    */  {
/* 269:269 */    return JaxenConstants.EMPTY_ITERATOR;
/* 270:    */  }
/* 271:    */  
/* 272:    */  public Iterator getNamespaceAxisIterator(Object contextNode)
/* 273:    */  {
/* 274:274 */    return JaxenConstants.EMPTY_ITERATOR;
/* 275:    */  }
/* 276:    */  
/* 277:    */  public Object getDocumentNode(Object contextNode)
/* 278:    */  {
/* 279:279 */    return null;
/* 280:    */  }
/* 281:    */  
/* 282:    */  public Object getParentNode(Object contextNode)
/* 283:    */  {
/* 284:284 */    if ((contextNode instanceof Element))
/* 285:    */    {
/* 286:286 */      return ((Element)contextNode).getParent();
/* 287:    */    }
/* 288:    */    
/* 289:289 */    return JaxenConstants.EMPTY_ITERATOR;
/* 290:    */  }
/* 291:    */  
/* 292:    */  public String getTextStringValue(Object obj)
/* 293:    */  {
/* 294:294 */    if ((obj instanceof Element))
/* 295:    */    {
/* 296:296 */      return ((Element)obj).getObject().toString();
/* 297:    */    }
/* 298:298 */    return obj.toString();
/* 299:    */  }
/* 300:    */  
/* 301:    */  public String getElementStringValue(Object obj)
/* 302:    */  {
/* 303:303 */    if ((obj instanceof Element))
/* 304:    */    {
/* 305:305 */      return ((Element)obj).getObject().toString();
/* 306:    */    }
/* 307:307 */    return obj.toString();
/* 308:    */  }
/* 309:    */  
/* 310:    */  public String getAttributeStringValue(Object obj)
/* 311:    */  {
/* 312:312 */    return obj.toString();
/* 313:    */  }
/* 314:    */  
/* 315:    */  public String getNamespaceStringValue(Object obj)
/* 316:    */  {
/* 317:317 */    return obj.toString();
/* 318:    */  }
/* 319:    */  
/* 320:    */  public String getNamespacePrefix(Object obj)
/* 321:    */  {
/* 322:322 */    return null;
/* 323:    */  }
/* 324:    */  
/* 325:    */  public String getCommentStringValue(Object obj)
/* 326:    */  {
/* 327:327 */    return null;
/* 328:    */  }
/* 329:    */  
/* 330:    */  public String translateNamespacePrefixToUri(String prefix, Object context)
/* 331:    */  {
/* 332:332 */    return null;
/* 333:    */  }
/* 334:    */  
/* 335:    */  public short getNodeType(Object node)
/* 336:    */  {
/* 337:337 */    return 0;
/* 338:    */  }
/* 339:    */  
/* 340:    */  public Object getDocument(String uri) throws FunctionCallException
/* 341:    */  {
/* 342:342 */    return null;
/* 343:    */  }
/* 344:    */  
/* 345:    */  public String getProcessingInstructionTarget(Object obj)
/* 346:    */  {
/* 347:347 */    return null;
/* 348:    */  }
/* 349:    */  
/* 350:    */  public String getProcessingInstructionData(Object obj)
/* 351:    */  {
/* 352:352 */    return null;
/* 353:    */  }
/* 354:    */  
/* 355:    */  public XPath parseXPath(String xpath)
/* 356:    */    throws SAXPathException
/* 357:    */  {
/* 358:358 */    return new JavaBeanXPath(xpath);
/* 359:    */  }
/* 360:    */  
/* 361:    */  protected String javacase(String name)
/* 362:    */  {
/* 363:363 */    if (name.length() == 0)
/* 364:    */    {
/* 365:365 */      return name;
/* 366:    */    }
/* 367:367 */    if (name.length() == 1)
/* 368:    */    {
/* 369:369 */      return name.toUpperCase();
/* 370:    */    }
/* 371:    */    
/* 372:372 */    return name.substring(0, 1).toUpperCase() + name.substring(1);
/* 373:    */  }
/* 374:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.javabean.DocumentNavigator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */