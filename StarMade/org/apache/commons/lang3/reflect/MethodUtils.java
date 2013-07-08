/*   1:    */package org.apache.commons.lang3.reflect;
/*   2:    */
/*   3:    */import java.lang.reflect.InvocationTargetException;
/*   4:    */import java.lang.reflect.Method;
/*   5:    */import java.lang.reflect.Modifier;
/*   6:    */import org.apache.commons.lang3.ArrayUtils;
/*   7:    */import org.apache.commons.lang3.ClassUtils;
/*   8:    */
/*  81:    */public class MethodUtils
/*  82:    */{
/*  83:    */  public static Object invokeMethod(Object object, String methodName, Object... args)
/*  84:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/*  85:    */  {
/*  86: 86 */    if (args == null) {
/*  87: 87 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/*  88:    */    }
/*  89: 89 */    int arguments = args.length;
/*  90: 90 */    Class<?>[] parameterTypes = new Class[arguments];
/*  91: 91 */    for (int i = 0; i < arguments; i++) {
/*  92: 92 */      parameterTypes[i] = args[i].getClass();
/*  93:    */    }
/*  94: 94 */    return invokeMethod(object, methodName, args, parameterTypes);
/*  95:    */  }
/*  96:    */  
/* 117:    */  public static Object invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes)
/* 118:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/* 119:    */  {
/* 120:120 */    if (parameterTypes == null) {
/* 121:121 */      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/* 122:    */    }
/* 123:123 */    if (args == null) {
/* 124:124 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 125:    */    }
/* 126:126 */    Method method = getMatchingAccessibleMethod(object.getClass(), methodName, parameterTypes);
/* 127:    */    
/* 128:128 */    if (method == null) {
/* 129:129 */      throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
/* 130:    */    }
/* 131:    */    
/* 133:133 */    return method.invoke(object, args);
/* 134:    */  }
/* 135:    */  
/* 154:    */  public static Object invokeExactMethod(Object object, String methodName, Object... args)
/* 155:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/* 156:    */  {
/* 157:157 */    if (args == null) {
/* 158:158 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 159:    */    }
/* 160:160 */    int arguments = args.length;
/* 161:161 */    Class<?>[] parameterTypes = new Class[arguments];
/* 162:162 */    for (int i = 0; i < arguments; i++) {
/* 163:163 */      parameterTypes[i] = args[i].getClass();
/* 164:    */    }
/* 165:165 */    return invokeExactMethod(object, methodName, args, parameterTypes);
/* 166:    */  }
/* 167:    */  
/* 188:    */  public static Object invokeExactMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes)
/* 189:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/* 190:    */  {
/* 191:191 */    if (args == null) {
/* 192:192 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 193:    */    }
/* 194:194 */    if (parameterTypes == null) {
/* 195:195 */      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/* 196:    */    }
/* 197:197 */    Method method = getAccessibleMethod(object.getClass(), methodName, parameterTypes);
/* 198:    */    
/* 199:199 */    if (method == null) {
/* 200:200 */      throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
/* 201:    */    }
/* 202:    */    
/* 204:204 */    return method.invoke(object, args);
/* 205:    */  }
/* 206:    */  
/* 227:    */  public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes)
/* 228:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/* 229:    */  {
/* 230:230 */    if (args == null) {
/* 231:231 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 232:    */    }
/* 233:233 */    if (parameterTypes == null) {
/* 234:234 */      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/* 235:    */    }
/* 236:236 */    Method method = getAccessibleMethod(cls, methodName, parameterTypes);
/* 237:237 */    if (method == null) {
/* 238:238 */      throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
/* 239:    */    }
/* 240:    */    
/* 241:241 */    return method.invoke(null, args);
/* 242:    */  }
/* 243:    */  
/* 268:    */  public static Object invokeStaticMethod(Class<?> cls, String methodName, Object... args)
/* 269:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/* 270:    */  {
/* 271:271 */    if (args == null) {
/* 272:272 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 273:    */    }
/* 274:274 */    int arguments = args.length;
/* 275:275 */    Class<?>[] parameterTypes = new Class[arguments];
/* 276:276 */    for (int i = 0; i < arguments; i++) {
/* 277:277 */      parameterTypes[i] = args[i].getClass();
/* 278:    */    }
/* 279:279 */    return invokeStaticMethod(cls, methodName, args, parameterTypes);
/* 280:    */  }
/* 281:    */  
/* 305:    */  public static Object invokeStaticMethod(Class<?> cls, String methodName, Object[] args, Class<?>[] parameterTypes)
/* 306:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/* 307:    */  {
/* 308:308 */    if (parameterTypes == null) {
/* 309:309 */      parameterTypes = ArrayUtils.EMPTY_CLASS_ARRAY;
/* 310:    */    }
/* 311:311 */    if (args == null) {
/* 312:312 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 313:    */    }
/* 314:314 */    Method method = getMatchingAccessibleMethod(cls, methodName, parameterTypes);
/* 315:    */    
/* 316:316 */    if (method == null) {
/* 317:317 */      throw new NoSuchMethodException("No such accessible method: " + methodName + "() on class: " + cls.getName());
/* 318:    */    }
/* 319:    */    
/* 320:320 */    return method.invoke(null, args);
/* 321:    */  }
/* 322:    */  
/* 341:    */  public static Object invokeExactStaticMethod(Class<?> cls, String methodName, Object... args)
/* 342:    */    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
/* 343:    */  {
/* 344:344 */    if (args == null) {
/* 345:345 */      args = ArrayUtils.EMPTY_OBJECT_ARRAY;
/* 346:    */    }
/* 347:347 */    int arguments = args.length;
/* 348:348 */    Class<?>[] parameterTypes = new Class[arguments];
/* 349:349 */    for (int i = 0; i < arguments; i++) {
/* 350:350 */      parameterTypes[i] = args[i].getClass();
/* 351:    */    }
/* 352:352 */    return invokeExactStaticMethod(cls, methodName, args, parameterTypes);
/* 353:    */  }
/* 354:    */  
/* 366:    */  public static Method getAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes)
/* 367:    */  {
/* 368:    */    try
/* 369:    */    {
/* 370:370 */      return getAccessibleMethod(cls.getMethod(methodName, parameterTypes));
/* 371:    */    }
/* 372:    */    catch (NoSuchMethodException e) {}
/* 373:373 */    return null;
/* 374:    */  }
/* 375:    */  
/* 384:    */  public static Method getAccessibleMethod(Method method)
/* 385:    */  {
/* 386:386 */    if (!MemberUtils.isAccessible(method)) {
/* 387:387 */      return null;
/* 388:    */    }
/* 389:    */    
/* 390:390 */    Class<?> cls = method.getDeclaringClass();
/* 391:391 */    if (Modifier.isPublic(cls.getModifiers())) {
/* 392:392 */      return method;
/* 393:    */    }
/* 394:394 */    String methodName = method.getName();
/* 395:395 */    Class<?>[] parameterTypes = method.getParameterTypes();
/* 396:    */    
/* 398:398 */    method = getAccessibleMethodFromInterfaceNest(cls, methodName, parameterTypes);
/* 399:    */    
/* 402:402 */    if (method == null) {
/* 403:403 */      method = getAccessibleMethodFromSuperclass(cls, methodName, parameterTypes);
/* 404:    */    }
/* 405:    */    
/* 406:406 */    return method;
/* 407:    */  }
/* 408:    */  
/* 419:    */  private static Method getAccessibleMethodFromSuperclass(Class<?> cls, String methodName, Class<?>... parameterTypes)
/* 420:    */  {
/* 421:421 */    Class<?> parentClass = cls.getSuperclass();
/* 422:422 */    while (parentClass != null) {
/* 423:423 */      if (Modifier.isPublic(parentClass.getModifiers())) {
/* 424:    */        try {
/* 425:425 */          return parentClass.getMethod(methodName, parameterTypes);
/* 426:    */        } catch (NoSuchMethodException e) {
/* 427:427 */          return null;
/* 428:    */        }
/* 429:    */      }
/* 430:430 */      parentClass = parentClass.getSuperclass();
/* 431:    */    }
/* 432:432 */    return null;
/* 433:    */  }
/* 434:    */  
/* 450:    */  private static Method getAccessibleMethodFromInterfaceNest(Class<?> cls, String methodName, Class<?>... parameterTypes)
/* 451:    */  {
/* 452:452 */    Method method = null;
/* 453:455 */    for (; 
/* 454:    */        
/* 455:455 */        cls != null; cls = cls.getSuperclass())
/* 456:    */    {
/* 458:458 */      Class<?>[] interfaces = cls.getInterfaces();
/* 459:459 */      for (int i = 0; i < interfaces.length; i++)
/* 460:    */      {
/* 461:461 */        if (Modifier.isPublic(interfaces[i].getModifiers()))
/* 462:    */        {
/* 464:    */          try
/* 465:    */          {
/* 466:466 */            method = interfaces[i].getDeclaredMethod(methodName, parameterTypes);
/* 467:    */          }
/* 468:    */          catch (NoSuchMethodException e) {}
/* 469:    */          
/* 474:474 */          if (method != null) {
/* 475:    */            break;
/* 476:    */          }
/* 477:    */          
/* 478:478 */          method = getAccessibleMethodFromInterfaceNest(interfaces[i], methodName, parameterTypes);
/* 479:    */          
/* 480:480 */          if (method != null)
/* 481:    */            break;
/* 482:    */        }
/* 483:    */      }
/* 484:    */    }
/* 485:485 */    return method;
/* 486:    */  }
/* 487:    */  
/* 507:    */  public static Method getMatchingAccessibleMethod(Class<?> cls, String methodName, Class<?>... parameterTypes)
/* 508:    */  {
/* 509:    */    try
/* 510:    */    {
/* 511:511 */      Method method = cls.getMethod(methodName, parameterTypes);
/* 512:512 */      MemberUtils.setAccessibleWorkaround(method);
/* 513:513 */      return method;
/* 514:    */    }
/* 515:    */    catch (NoSuchMethodException e)
/* 516:    */    {
/* 517:517 */      Method bestMatch = null;
/* 518:518 */      Method[] methods = cls.getMethods();
/* 519:519 */      for (Method method : methods)
/* 520:    */      {
/* 521:521 */        if ((method.getName().equals(methodName)) && (ClassUtils.isAssignable(parameterTypes, method.getParameterTypes(), true)))
/* 522:    */        {
/* 523:523 */          Method accessibleMethod = getAccessibleMethod(method);
/* 524:524 */          if ((accessibleMethod != null) && ((bestMatch == null) || (MemberUtils.compareParameterTypes(accessibleMethod.getParameterTypes(), bestMatch.getParameterTypes(), parameterTypes) < 0)))
/* 525:    */          {
/* 528:528 */            bestMatch = accessibleMethod;
/* 529:    */          }
/* 530:    */        }
/* 531:    */      }
/* 532:532 */      if (bestMatch != null) {
/* 533:533 */        MemberUtils.setAccessibleWorkaround(bestMatch);
/* 534:    */      }
/* 535:535 */      return bestMatch;
/* 536:    */    }
/* 537:    */  }
/* 538:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.reflect.MethodUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */