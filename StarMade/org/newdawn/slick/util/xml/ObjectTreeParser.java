/*     */ package org.newdawn.slick.util.xml;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import org.newdawn.slick.util.Log;
/*     */ import org.newdawn.slick.util.ResourceLoader;
/*     */ 
/*     */ public class ObjectTreeParser
/*     */ {
/*  50 */   private HashMap nameToClass = new HashMap();
/*     */   private String defaultPackage;
/*  54 */   private ArrayList ignored = new ArrayList();
/*     */ 
/*  56 */   private String addMethod = "add";
/*     */ 
/*     */   public ObjectTreeParser()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ObjectTreeParser(String defaultPackage)
/*     */   {
/*  71 */     this.defaultPackage = defaultPackage;
/*     */   }
/*     */ 
/*     */   public void addElementMapping(String elementName, Class elementClass)
/*     */   {
/*  81 */     this.nameToClass.put(elementName, elementClass);
/*     */   }
/*     */ 
/*     */   public void addIgnoredElement(String elementName)
/*     */   {
/*  90 */     this.ignored.add(elementName);
/*     */   }
/*     */ 
/*     */   public void setAddMethodName(String methodName)
/*     */   {
/* 101 */     this.addMethod = methodName;
/*     */   }
/*     */ 
/*     */   public void setDefaultPackage(String defaultPackage)
/*     */   {
/* 111 */     this.defaultPackage = defaultPackage;
/*     */   }
/*     */ 
/*     */   public Object parse(String ref)
/*     */     throws SlickXMLException
/*     */   {
/* 124 */     return parse(ref, ResourceLoader.getResourceAsStream(ref));
/*     */   }
/*     */ 
/*     */   public Object parse(String name, InputStream in)
/*     */     throws SlickXMLException
/*     */   {
/* 137 */     XMLParser parser = new XMLParser();
/* 138 */     XMLElement root = parser.parse(name, in);
/*     */ 
/* 140 */     return traverse(root);
/*     */   }
/*     */ 
/*     */   public Object parseOnto(String ref, Object target)
/*     */     throws SlickXMLException
/*     */   {
/* 154 */     return parseOnto(ref, ResourceLoader.getResourceAsStream(ref), target);
/*     */   }
/*     */ 
/*     */   public Object parseOnto(String name, InputStream in, Object target)
/*     */     throws SlickXMLException
/*     */   {
/* 168 */     XMLParser parser = new XMLParser();
/* 169 */     XMLElement root = parser.parse(name, in);
/*     */ 
/* 171 */     return traverse(root, target);
/*     */   }
/*     */ 
/*     */   private Class getClassForElementName(String name)
/*     */   {
/* 182 */     Class clazz = (Class)this.nameToClass.get(name);
/* 183 */     if (clazz != null) {
/* 184 */       return clazz;
/*     */     }
/*     */ 
/* 187 */     if (this.defaultPackage != null) {
/*     */       try {
/* 189 */         return Class.forName(this.defaultPackage + "." + name);
/*     */       }
/*     */       catch (ClassNotFoundException e)
/*     */       {
/*     */       }
/*     */     }
/* 195 */     return null;
/*     */   }
/*     */ 
/*     */   private Object traverse(XMLElement current)
/*     */     throws SlickXMLException
/*     */   {
/* 207 */     return traverse(current, null);
/*     */   }
/*     */ 
/*     */   private Object traverse(XMLElement current, Object instance)
/*     */     throws SlickXMLException
/*     */   {
/* 220 */     String name = current.getName();
/* 221 */     if (this.ignored.contains(name))
/* 222 */       return null;
/*     */     Class clazz;
/*     */     Class clazz;
/* 227 */     if (instance == null)
/* 228 */       clazz = getClassForElementName(name);
/*     */     else {
/* 230 */       clazz = instance.getClass();
/*     */     }
/*     */ 
/* 233 */     if (clazz == null) {
/* 234 */       throw new SlickXMLException("Unable to map element " + name + " to a class, define the mapping");
/*     */     }
/*     */     try
/*     */     {
/* 238 */       if (instance == null) {
/* 239 */         instance = clazz.newInstance();
/*     */ 
/* 241 */         Method elementNameMethod = getMethod(clazz, "setXMLElementName", new Class[] { String.class });
/* 242 */         if (elementNameMethod != null) {
/* 243 */           invoke(elementNameMethod, instance, new Object[] { name });
/*     */         }
/* 245 */         Method contentMethod = getMethod(clazz, "setXMLElementContent", new Class[] { String.class });
/* 246 */         if (contentMethod != null) {
/* 247 */           invoke(contentMethod, instance, new Object[] { current.getContent() });
/*     */         }
/*     */       }
/*     */ 
/* 251 */       String[] attrs = current.getAttributeNames();
/* 252 */       for (int i = 0; i < attrs.length; i++) {
/* 253 */         String methodName = "set" + attrs[i];
/*     */ 
/* 255 */         Method method = findMethod(clazz, methodName);
/*     */ 
/* 257 */         if (method == null) {
/* 258 */           Field field = findField(clazz, attrs[i]);
/* 259 */           if (field != null) {
/* 260 */             String value = current.getAttribute(attrs[i]);
/* 261 */             Object typedValue = typeValue(value, field.getType());
/* 262 */             setField(field, instance, typedValue);
/*     */           } else {
/* 264 */             Log.info("Unable to find property on: " + clazz + " for attribute: " + attrs[i]);
/*     */           }
/*     */         } else {
/* 267 */           String value = current.getAttribute(attrs[i]);
/* 268 */           Object typedValue = typeValue(value, method.getParameterTypes()[0]);
/* 269 */           invoke(method, instance, new Object[] { typedValue });
/*     */         }
/*     */       }
/*     */ 
/* 273 */       XMLElementList children = current.getChildren();
/* 274 */       for (int i = 0; i < children.size(); i++) {
/* 275 */         XMLElement element = children.get(i);
/*     */ 
/* 277 */         Object child = traverse(element);
/* 278 */         if (child != null) {
/* 279 */           String methodName = this.addMethod;
/*     */ 
/* 281 */           Method method = findMethod(clazz, methodName, child.getClass());
/* 282 */           if (method == null)
/* 283 */             Log.info("Unable to find method to add: " + child + " to " + clazz);
/*     */           else {
/* 285 */             invoke(method, instance, new Object[] { child });
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 290 */       return instance;
/*     */     } catch (InstantiationException e) {
/* 292 */       throw new SlickXMLException("Unable to instance " + clazz + " for element " + name + ", no zero parameter constructor?", e);
/*     */     } catch (IllegalAccessException e) {
/* 294 */       throw new SlickXMLException("Unable to instance " + clazz + " for element " + name + ", no zero parameter constructor?", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Object typeValue(String value, Class clazz)
/*     */     throws SlickXMLException
/*     */   {
/* 307 */     if (clazz == String.class) {
/* 308 */       return value;
/*     */     }
/*     */     try
/*     */     {
/* 312 */       clazz = mapPrimitive(clazz);
/* 313 */       return clazz.getConstructor(new Class[] { String.class }).newInstance(new Object[] { value });
/*     */     } catch (Exception e) {
/* 315 */       throw new SlickXMLException("Failed to convert: " + value + " to the expected primitive type: " + clazz, e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Class mapPrimitive(Class clazz)
/*     */   {
/* 326 */     if (clazz == Integer.TYPE) {
/* 327 */       return Integer.class;
/*     */     }
/* 329 */     if (clazz == Double.TYPE) {
/* 330 */       return Double.class;
/*     */     }
/* 332 */     if (clazz == Float.TYPE) {
/* 333 */       return Float.class;
/*     */     }
/* 335 */     if (clazz == Boolean.TYPE) {
/* 336 */       return Boolean.class;
/*     */     }
/* 338 */     if (clazz == Long.TYPE) {
/* 339 */       return Long.class;
/*     */     }
/*     */ 
/* 342 */     throw new RuntimeException("Unsupported primitive: " + clazz);
/*     */   }
/*     */ 
/*     */   private Field findField(Class clazz, String name)
/*     */   {
/* 355 */     Field[] fields = clazz.getDeclaredFields();
/* 356 */     for (int i = 0; i < fields.length; i++) {
/* 357 */       if (fields[i].getName().equalsIgnoreCase(name)) {
/* 358 */         if (fields[i].getType().isPrimitive()) {
/* 359 */           return fields[i];
/*     */         }
/* 361 */         if (fields[i].getType() == String.class) {
/* 362 */           return fields[i];
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 367 */     return null;
/*     */   }
/*     */ 
/*     */   private Method findMethod(Class clazz, String name)
/*     */   {
/* 380 */     Method[] methods = clazz.getDeclaredMethods();
/* 381 */     for (int i = 0; i < methods.length; i++) {
/* 382 */       if (methods[i].getName().equalsIgnoreCase(name)) {
/* 383 */         Method method = methods[i];
/* 384 */         Class[] params = method.getParameterTypes();
/*     */ 
/* 386 */         if (params.length == 1) {
/* 387 */           return method;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 392 */     return null;
/*     */   }
/*     */ 
/*     */   private Method findMethod(Class clazz, String name, Class parameter)
/*     */   {
/* 404 */     Method[] methods = clazz.getDeclaredMethods();
/* 405 */     for (int i = 0; i < methods.length; i++) {
/* 406 */       if (methods[i].getName().equalsIgnoreCase(name)) {
/* 407 */         Method method = methods[i];
/* 408 */         Class[] params = method.getParameterTypes();
/*     */ 
/* 410 */         if ((params.length == 1) && 
/* 411 */           (method.getParameterTypes()[0].isAssignableFrom(parameter))) {
/* 412 */           return method;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 418 */     return null;
/*     */   }
/*     */ 
/*     */   private void setField(Field field, Object instance, Object value)
/*     */     throws SlickXMLException
/*     */   {
/*     */     try
/*     */     {
/* 431 */       field.setAccessible(true);
/* 432 */       field.set(instance, value);
/*     */     } catch (IllegalArgumentException e) {
/* 434 */       throw new SlickXMLException("Failed to set: " + field + " for an XML attribute, is it valid?", e);
/*     */     } catch (IllegalAccessException e) {
/* 436 */       throw new SlickXMLException("Failed to set: " + field + " for an XML attribute, is it valid?", e);
/*     */     } finally {
/* 438 */       field.setAccessible(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void invoke(Method method, Object instance, Object[] params)
/*     */     throws SlickXMLException
/*     */   {
/*     */     try
/*     */     {
/* 452 */       method.setAccessible(true);
/* 453 */       method.invoke(instance, params);
/*     */     } catch (IllegalArgumentException e) {
/* 455 */       throw new SlickXMLException("Failed to invoke: " + method + " for an XML attribute, is it valid?", e);
/*     */     } catch (IllegalAccessException e) {
/* 457 */       throw new SlickXMLException("Failed to invoke: " + method + " for an XML attribute, is it valid?", e);
/*     */     } catch (InvocationTargetException e) {
/* 459 */       throw new SlickXMLException("Failed to invoke: " + method + " for an XML attribute, is it valid?", e);
/*     */     } finally {
/* 461 */       method.setAccessible(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Method getMethod(Class clazz, String name, Class[] params)
/*     */   {
/*     */     try
/*     */     {
/* 476 */       return clazz.getMethod(name, params);
/*     */     } catch (SecurityException e) {
/* 478 */       return null; } catch (NoSuchMethodException e) {
/*     */     }
/* 480 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.xml.ObjectTreeParser
 * JD-Core Version:    0.6.2
 */