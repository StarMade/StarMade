/*   1:    */package org.newdawn.slick.util.xml;
/*   2:    */
/*   3:    */import java.io.InputStream;
/*   4:    */import java.lang.reflect.Constructor;
/*   5:    */import java.lang.reflect.Field;
/*   6:    */import java.lang.reflect.InvocationTargetException;
/*   7:    */import java.lang.reflect.Method;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.HashMap;
/*  10:    */import org.newdawn.slick.util.Log;
/*  11:    */import org.newdawn.slick.util.ResourceLoader;
/*  12:    */
/*  48:    */public class ObjectTreeParser
/*  49:    */{
/*  50: 50 */  private HashMap nameToClass = new HashMap();
/*  51:    */  
/*  52:    */  private String defaultPackage;
/*  53:    */  
/*  54: 54 */  private ArrayList ignored = new ArrayList();
/*  55:    */  
/*  56: 56 */  private String addMethod = "add";
/*  57:    */  
/*  63:    */  public ObjectTreeParser() {}
/*  64:    */  
/*  69:    */  public ObjectTreeParser(String defaultPackage)
/*  70:    */  {
/*  71: 71 */    this.defaultPackage = defaultPackage;
/*  72:    */  }
/*  73:    */  
/*  79:    */  public void addElementMapping(String elementName, Class elementClass)
/*  80:    */  {
/*  81: 81 */    this.nameToClass.put(elementName, elementClass);
/*  82:    */  }
/*  83:    */  
/*  88:    */  public void addIgnoredElement(String elementName)
/*  89:    */  {
/*  90: 90 */    this.ignored.add(elementName);
/*  91:    */  }
/*  92:    */  
/*  99:    */  public void setAddMethodName(String methodName)
/* 100:    */  {
/* 101:101 */    this.addMethod = methodName;
/* 102:    */  }
/* 103:    */  
/* 109:    */  public void setDefaultPackage(String defaultPackage)
/* 110:    */  {
/* 111:111 */    this.defaultPackage = defaultPackage;
/* 112:    */  }
/* 113:    */  
/* 121:    */  public Object parse(String ref)
/* 122:    */    throws SlickXMLException
/* 123:    */  {
/* 124:124 */    return parse(ref, ResourceLoader.getResourceAsStream(ref));
/* 125:    */  }
/* 126:    */  
/* 134:    */  public Object parse(String name, InputStream in)
/* 135:    */    throws SlickXMLException
/* 136:    */  {
/* 137:137 */    XMLParser parser = new XMLParser();
/* 138:138 */    XMLElement root = parser.parse(name, in);
/* 139:    */    
/* 140:140 */    return traverse(root);
/* 141:    */  }
/* 142:    */  
/* 151:    */  public Object parseOnto(String ref, Object target)
/* 152:    */    throws SlickXMLException
/* 153:    */  {
/* 154:154 */    return parseOnto(ref, ResourceLoader.getResourceAsStream(ref), target);
/* 155:    */  }
/* 156:    */  
/* 165:    */  public Object parseOnto(String name, InputStream in, Object target)
/* 166:    */    throws SlickXMLException
/* 167:    */  {
/* 168:168 */    XMLParser parser = new XMLParser();
/* 169:169 */    XMLElement root = parser.parse(name, in);
/* 170:    */    
/* 171:171 */    return traverse(root, target);
/* 172:    */  }
/* 173:    */  
/* 180:    */  private Class getClassForElementName(String name)
/* 181:    */  {
/* 182:182 */    Class clazz = (Class)this.nameToClass.get(name);
/* 183:183 */    if (clazz != null) {
/* 184:184 */      return clazz;
/* 185:    */    }
/* 186:    */    
/* 187:187 */    if (this.defaultPackage != null) {
/* 188:    */      try {
/* 189:189 */        return Class.forName(this.defaultPackage + "." + name);
/* 190:    */      }
/* 191:    */      catch (ClassNotFoundException e) {}
/* 192:    */    }
/* 193:    */    
/* 195:195 */    return null;
/* 196:    */  }
/* 197:    */  
/* 204:    */  private Object traverse(XMLElement current)
/* 205:    */    throws SlickXMLException
/* 206:    */  {
/* 207:207 */    return traverse(current, null);
/* 208:    */  }
/* 209:    */  
/* 217:    */  private Object traverse(XMLElement current, Object instance)
/* 218:    */    throws SlickXMLException
/* 219:    */  {
/* 220:220 */    String name = current.getName();
/* 221:221 */    if (this.ignored.contains(name)) {
/* 222:222 */      return null;
/* 223:    */    }
/* 224:    */    
/* 225:    */    Class clazz;
/* 226:    */    Class clazz;
/* 227:227 */    if (instance == null) {
/* 228:228 */      clazz = getClassForElementName(name);
/* 229:    */    } else {
/* 230:230 */      clazz = instance.getClass();
/* 231:    */    }
/* 232:    */    
/* 233:233 */    if (clazz == null) {
/* 234:234 */      throw new SlickXMLException("Unable to map element " + name + " to a class, define the mapping");
/* 235:    */    }
/* 236:    */    try
/* 237:    */    {
/* 238:238 */      if (instance == null) {
/* 239:239 */        instance = clazz.newInstance();
/* 240:    */        
/* 241:241 */        Method elementNameMethod = getMethod(clazz, "setXMLElementName", new Class[] { String.class });
/* 242:242 */        if (elementNameMethod != null) {
/* 243:243 */          invoke(elementNameMethod, instance, new Object[] { name });
/* 244:    */        }
/* 245:245 */        Method contentMethod = getMethod(clazz, "setXMLElementContent", new Class[] { String.class });
/* 246:246 */        if (contentMethod != null) {
/* 247:247 */          invoke(contentMethod, instance, new Object[] { current.getContent() });
/* 248:    */        }
/* 249:    */      }
/* 250:    */      
/* 251:251 */      String[] attrs = current.getAttributeNames();
/* 252:252 */      for (int i = 0; i < attrs.length; i++) {
/* 253:253 */        String methodName = "set" + attrs[i];
/* 254:    */        
/* 255:255 */        Method method = findMethod(clazz, methodName);
/* 256:    */        
/* 257:257 */        if (method == null) {
/* 258:258 */          Field field = findField(clazz, attrs[i]);
/* 259:259 */          if (field != null) {
/* 260:260 */            String value = current.getAttribute(attrs[i]);
/* 261:261 */            Object typedValue = typeValue(value, field.getType());
/* 262:262 */            setField(field, instance, typedValue);
/* 263:    */          } else {
/* 264:264 */            Log.info("Unable to find property on: " + clazz + " for attribute: " + attrs[i]);
/* 265:    */          }
/* 266:    */        } else {
/* 267:267 */          String value = current.getAttribute(attrs[i]);
/* 268:268 */          Object typedValue = typeValue(value, method.getParameterTypes()[0]);
/* 269:269 */          invoke(method, instance, new Object[] { typedValue });
/* 270:    */        }
/* 271:    */      }
/* 272:    */      
/* 273:273 */      XMLElementList children = current.getChildren();
/* 274:274 */      for (int i = 0; i < children.size(); i++) {
/* 275:275 */        XMLElement element = children.get(i);
/* 276:    */        
/* 277:277 */        Object child = traverse(element);
/* 278:278 */        if (child != null) {
/* 279:279 */          String methodName = this.addMethod;
/* 280:    */          
/* 281:281 */          Method method = findMethod(clazz, methodName, child.getClass());
/* 282:282 */          if (method == null) {
/* 283:283 */            Log.info("Unable to find method to add: " + child + " to " + clazz);
/* 284:    */          } else {
/* 285:285 */            invoke(method, instance, new Object[] { child });
/* 286:    */          }
/* 287:    */        }
/* 288:    */      }
/* 289:    */      
/* 290:290 */      return instance;
/* 291:    */    } catch (InstantiationException e) {
/* 292:292 */      throw new SlickXMLException("Unable to instance " + clazz + " for element " + name + ", no zero parameter constructor?", e);
/* 293:    */    } catch (IllegalAccessException e) {
/* 294:294 */      throw new SlickXMLException("Unable to instance " + clazz + " for element " + name + ", no zero parameter constructor?", e);
/* 295:    */    }
/* 296:    */  }
/* 297:    */  
/* 304:    */  private Object typeValue(String value, Class clazz)
/* 305:    */    throws SlickXMLException
/* 306:    */  {
/* 307:307 */    if (clazz == String.class) {
/* 308:308 */      return value;
/* 309:    */    }
/* 310:    */    try
/* 311:    */    {
/* 312:312 */      clazz = mapPrimitive(clazz);
/* 313:313 */      return clazz.getConstructor(new Class[] { String.class }).newInstance(new Object[] { value });
/* 314:    */    } catch (Exception e) {
/* 315:315 */      throw new SlickXMLException("Failed to convert: " + value + " to the expected primitive type: " + clazz, e);
/* 316:    */    }
/* 317:    */  }
/* 318:    */  
/* 324:    */  private Class mapPrimitive(Class clazz)
/* 325:    */  {
/* 326:326 */    if (clazz == Integer.TYPE) {
/* 327:327 */      return Integer.class;
/* 328:    */    }
/* 329:329 */    if (clazz == Double.TYPE) {
/* 330:330 */      return Double.class;
/* 331:    */    }
/* 332:332 */    if (clazz == Float.TYPE) {
/* 333:333 */      return Float.class;
/* 334:    */    }
/* 335:335 */    if (clazz == Boolean.TYPE) {
/* 336:336 */      return Boolean.class;
/* 337:    */    }
/* 338:338 */    if (clazz == Long.TYPE) {
/* 339:339 */      return Long.class;
/* 340:    */    }
/* 341:    */    
/* 342:342 */    throw new RuntimeException("Unsupported primitive: " + clazz);
/* 343:    */  }
/* 344:    */  
/* 353:    */  private Field findField(Class clazz, String name)
/* 354:    */  {
/* 355:355 */    Field[] fields = clazz.getDeclaredFields();
/* 356:356 */    for (int i = 0; i < fields.length; i++) {
/* 357:357 */      if (fields[i].getName().equalsIgnoreCase(name)) {
/* 358:358 */        if (fields[i].getType().isPrimitive()) {
/* 359:359 */          return fields[i];
/* 360:    */        }
/* 361:361 */        if (fields[i].getType() == String.class) {
/* 362:362 */          return fields[i];
/* 363:    */        }
/* 364:    */      }
/* 365:    */    }
/* 366:    */    
/* 367:367 */    return null;
/* 368:    */  }
/* 369:    */  
/* 378:    */  private Method findMethod(Class clazz, String name)
/* 379:    */  {
/* 380:380 */    Method[] methods = clazz.getDeclaredMethods();
/* 381:381 */    for (int i = 0; i < methods.length; i++) {
/* 382:382 */      if (methods[i].getName().equalsIgnoreCase(name)) {
/* 383:383 */        Method method = methods[i];
/* 384:384 */        Class[] params = method.getParameterTypes();
/* 385:    */        
/* 386:386 */        if (params.length == 1) {
/* 387:387 */          return method;
/* 388:    */        }
/* 389:    */      }
/* 390:    */    }
/* 391:    */    
/* 392:392 */    return null;
/* 393:    */  }
/* 394:    */  
/* 402:    */  private Method findMethod(Class clazz, String name, Class parameter)
/* 403:    */  {
/* 404:404 */    Method[] methods = clazz.getDeclaredMethods();
/* 405:405 */    for (int i = 0; i < methods.length; i++) {
/* 406:406 */      if (methods[i].getName().equalsIgnoreCase(name)) {
/* 407:407 */        Method method = methods[i];
/* 408:408 */        Class[] params = method.getParameterTypes();
/* 409:    */        
/* 410:410 */        if ((params.length == 1) && 
/* 411:411 */          (method.getParameterTypes()[0].isAssignableFrom(parameter))) {
/* 412:412 */          return method;
/* 413:    */        }
/* 414:    */      }
/* 415:    */    }
/* 416:    */    
/* 418:418 */    return null;
/* 419:    */  }
/* 420:    */  
/* 426:    */  private void setField(Field field, Object instance, Object value)
/* 427:    */    throws SlickXMLException
/* 428:    */  {
/* 429:    */    try
/* 430:    */    {
/* 431:431 */      field.setAccessible(true);
/* 432:432 */      field.set(instance, value);
/* 433:    */    } catch (IllegalArgumentException e) {
/* 434:434 */      throw new SlickXMLException("Failed to set: " + field + " for an XML attribute, is it valid?", e);
/* 435:    */    } catch (IllegalAccessException e) {
/* 436:436 */      throw new SlickXMLException("Failed to set: " + field + " for an XML attribute, is it valid?", e);
/* 437:    */    } finally {
/* 438:438 */      field.setAccessible(false);
/* 439:    */    }
/* 440:    */  }
/* 441:    */  
/* 447:    */  private void invoke(Method method, Object instance, Object[] params)
/* 448:    */    throws SlickXMLException
/* 449:    */  {
/* 450:    */    try
/* 451:    */    {
/* 452:452 */      method.setAccessible(true);
/* 453:453 */      method.invoke(instance, params);
/* 454:    */    } catch (IllegalArgumentException e) {
/* 455:455 */      throw new SlickXMLException("Failed to invoke: " + method + " for an XML attribute, is it valid?", e);
/* 456:    */    } catch (IllegalAccessException e) {
/* 457:457 */      throw new SlickXMLException("Failed to invoke: " + method + " for an XML attribute, is it valid?", e);
/* 458:    */    } catch (InvocationTargetException e) {
/* 459:459 */      throw new SlickXMLException("Failed to invoke: " + method + " for an XML attribute, is it valid?", e);
/* 460:    */    } finally {
/* 461:461 */      method.setAccessible(false);
/* 462:    */    }
/* 463:    */  }
/* 464:    */  
/* 472:    */  private Method getMethod(Class clazz, String name, Class[] params)
/* 473:    */  {
/* 474:    */    try
/* 475:    */    {
/* 476:476 */      return clazz.getMethod(name, params);
/* 477:    */    } catch (SecurityException e) {
/* 478:478 */      return null;
/* 479:    */    } catch (NoSuchMethodException e) {}
/* 480:480 */    return null;
/* 481:    */  }
/* 482:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.xml.ObjectTreeParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */