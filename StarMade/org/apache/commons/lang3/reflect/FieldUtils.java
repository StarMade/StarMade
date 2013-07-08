/*   1:    */package org.apache.commons.lang3.reflect;
/*   2:    */
/*   3:    */import java.lang.reflect.Field;
/*   4:    */import java.lang.reflect.Modifier;
/*   5:    */import org.apache.commons.lang3.ClassUtils;
/*   6:    */
/*  53:    */public class FieldUtils
/*  54:    */{
/*  55:    */  public static Field getField(Class<?> cls, String fieldName)
/*  56:    */  {
/*  57: 57 */    Field field = getField(cls, fieldName, false);
/*  58: 58 */    MemberUtils.setAccessibleWorkaround(field);
/*  59: 59 */    return field;
/*  60:    */  }
/*  61:    */  
/*  73:    */  public static Field getField(Class<?> cls, String fieldName, boolean forceAccess)
/*  74:    */  {
/*  75: 75 */    if (cls == null) {
/*  76: 76 */      throw new IllegalArgumentException("The class must not be null");
/*  77:    */    }
/*  78: 78 */    if (fieldName == null) {
/*  79: 79 */      throw new IllegalArgumentException("The field name must not be null");
/*  80:    */    }
/*  81:    */    
/*  95: 95 */    for (Class<?> acls = cls; acls != null; acls = acls.getSuperclass()) {
/*  96:    */      try {
/*  97: 97 */        Field field = acls.getDeclaredField(fieldName);
/*  98:    */        
/* 100:100 */        if (!Modifier.isPublic(field.getModifiers())) {
/* 101:101 */          if (forceAccess) {
/* 102:102 */            field.setAccessible(true);
/* 103:    */          } else {
/* 104:104 */            continue;
/* 105:    */          }
/* 106:    */        }
/* 107:107 */        return field;
/* 108:    */      }
/* 109:    */      catch (NoSuchFieldException ex) {}
/* 110:    */    }
/* 111:    */    
/* 115:115 */    Field match = null;
/* 116:116 */    for (Class<?> class1 : ClassUtils.getAllInterfaces(cls)) {
/* 117:    */      try {
/* 118:118 */        Field test = class1.getField(fieldName);
/* 119:119 */        if (match != null) {
/* 120:120 */          throw new IllegalArgumentException("Reference to field " + fieldName + " is ambiguous relative to " + cls + "; a matching field exists on two or more implemented interfaces.");
/* 121:    */        }
/* 122:    */        
/* 123:123 */        match = test;
/* 124:    */      }
/* 125:    */      catch (NoSuchFieldException ex) {}
/* 126:    */    }
/* 127:    */    
/* 128:128 */    return match;
/* 129:    */  }
/* 130:    */  
/* 139:    */  public static Field getDeclaredField(Class<?> cls, String fieldName)
/* 140:    */  {
/* 141:141 */    return getDeclaredField(cls, fieldName, false);
/* 142:    */  }
/* 143:    */  
/* 154:    */  public static Field getDeclaredField(Class<?> cls, String fieldName, boolean forceAccess)
/* 155:    */  {
/* 156:156 */    if (cls == null) {
/* 157:157 */      throw new IllegalArgumentException("The class must not be null");
/* 158:    */    }
/* 159:159 */    if (fieldName == null) {
/* 160:160 */      throw new IllegalArgumentException("The field name must not be null");
/* 161:    */    }
/* 162:    */    try
/* 163:    */    {
/* 164:164 */      Field field = cls.getDeclaredField(fieldName);
/* 165:165 */      if (!MemberUtils.isAccessible(field)) {
/* 166:166 */        if (forceAccess) {
/* 167:167 */          field.setAccessible(true);
/* 168:    */        } else {
/* 169:169 */          return null;
/* 170:    */        }
/* 171:    */      }
/* 172:172 */      return field;
/* 173:    */    }
/* 174:    */    catch (NoSuchFieldException e) {}
/* 175:    */    
/* 176:176 */    return null;
/* 177:    */  }
/* 178:    */  
/* 184:    */  public static Object readStaticField(Field field)
/* 185:    */    throws IllegalAccessException
/* 186:    */  {
/* 187:187 */    return readStaticField(field, false);
/* 188:    */  }
/* 189:    */  
/* 197:    */  public static Object readStaticField(Field field, boolean forceAccess)
/* 198:    */    throws IllegalAccessException
/* 199:    */  {
/* 200:200 */    if (field == null) {
/* 201:201 */      throw new IllegalArgumentException("The field must not be null");
/* 202:    */    }
/* 203:203 */    if (!Modifier.isStatic(field.getModifiers())) {
/* 204:204 */      throw new IllegalArgumentException("The field '" + field.getName() + "' is not static");
/* 205:    */    }
/* 206:206 */    return readField(field, (Object)null, forceAccess);
/* 207:    */  }
/* 208:    */  
/* 215:    */  public static Object readStaticField(Class<?> cls, String fieldName)
/* 216:    */    throws IllegalAccessException
/* 217:    */  {
/* 218:218 */    return readStaticField(cls, fieldName, false);
/* 219:    */  }
/* 220:    */  
/* 231:    */  public static Object readStaticField(Class<?> cls, String fieldName, boolean forceAccess)
/* 232:    */    throws IllegalAccessException
/* 233:    */  {
/* 234:234 */    Field field = getField(cls, fieldName, forceAccess);
/* 235:235 */    if (field == null) {
/* 236:236 */      throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls);
/* 237:    */    }
/* 238:    */    
/* 239:239 */    return readStaticField(field, false);
/* 240:    */  }
/* 241:    */  
/* 250:    */  public static Object readDeclaredStaticField(Class<?> cls, String fieldName)
/* 251:    */    throws IllegalAccessException
/* 252:    */  {
/* 253:253 */    return readDeclaredStaticField(cls, fieldName, false);
/* 254:    */  }
/* 255:    */  
/* 268:    */  public static Object readDeclaredStaticField(Class<?> cls, String fieldName, boolean forceAccess)
/* 269:    */    throws IllegalAccessException
/* 270:    */  {
/* 271:271 */    Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 272:272 */    if (field == null) {
/* 273:273 */      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/* 274:    */    }
/* 275:    */    
/* 276:276 */    return readStaticField(field, false);
/* 277:    */  }
/* 278:    */  
/* 285:    */  public static Object readField(Field field, Object target)
/* 286:    */    throws IllegalAccessException
/* 287:    */  {
/* 288:288 */    return readField(field, target, false);
/* 289:    */  }
/* 290:    */  
/* 299:    */  public static Object readField(Field field, Object target, boolean forceAccess)
/* 300:    */    throws IllegalAccessException
/* 301:    */  {
/* 302:302 */    if (field == null) {
/* 303:303 */      throw new IllegalArgumentException("The field must not be null");
/* 304:    */    }
/* 305:305 */    if ((forceAccess) && (!field.isAccessible())) {
/* 306:306 */      field.setAccessible(true);
/* 307:    */    } else {
/* 308:308 */      MemberUtils.setAccessibleWorkaround(field);
/* 309:    */    }
/* 310:310 */    return field.get(target);
/* 311:    */  }
/* 312:    */  
/* 319:    */  public static Object readField(Object target, String fieldName)
/* 320:    */    throws IllegalAccessException
/* 321:    */  {
/* 322:322 */    return readField(target, fieldName, false);
/* 323:    */  }
/* 324:    */  
/* 334:    */  public static Object readField(Object target, String fieldName, boolean forceAccess)
/* 335:    */    throws IllegalAccessException
/* 336:    */  {
/* 337:337 */    if (target == null) {
/* 338:338 */      throw new IllegalArgumentException("target object must not be null");
/* 339:    */    }
/* 340:340 */    Class<?> cls = target.getClass();
/* 341:341 */    Field field = getField(cls, fieldName, forceAccess);
/* 342:342 */    if (field == null) {
/* 343:343 */      throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls);
/* 344:    */    }
/* 345:    */    
/* 346:346 */    return readField(field, target);
/* 347:    */  }
/* 348:    */  
/* 355:    */  public static Object readDeclaredField(Object target, String fieldName)
/* 356:    */    throws IllegalAccessException
/* 357:    */  {
/* 358:358 */    return readDeclaredField(target, fieldName, false);
/* 359:    */  }
/* 360:    */  
/* 373:    */  public static Object readDeclaredField(Object target, String fieldName, boolean forceAccess)
/* 374:    */    throws IllegalAccessException
/* 375:    */  {
/* 376:376 */    if (target == null) {
/* 377:377 */      throw new IllegalArgumentException("target object must not be null");
/* 378:    */    }
/* 379:379 */    Class<?> cls = target.getClass();
/* 380:380 */    Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 381:381 */    if (field == null) {
/* 382:382 */      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/* 383:    */    }
/* 384:    */    
/* 385:385 */    return readField(field, target);
/* 386:    */  }
/* 387:    */  
/* 393:    */  public static void writeStaticField(Field field, Object value)
/* 394:    */    throws IllegalAccessException
/* 395:    */  {
/* 396:396 */    writeStaticField(field, value, false);
/* 397:    */  }
/* 398:    */  
/* 407:    */  public static void writeStaticField(Field field, Object value, boolean forceAccess)
/* 408:    */    throws IllegalAccessException
/* 409:    */  {
/* 410:410 */    if (field == null) {
/* 411:411 */      throw new IllegalArgumentException("The field must not be null");
/* 412:    */    }
/* 413:413 */    if (!Modifier.isStatic(field.getModifiers())) {
/* 414:414 */      throw new IllegalArgumentException("The field '" + field.getName() + "' is not static");
/* 415:    */    }
/* 416:416 */    writeField(field, (Object)null, value, forceAccess);
/* 417:    */  }
/* 418:    */  
/* 425:    */  public static void writeStaticField(Class<?> cls, String fieldName, Object value)
/* 426:    */    throws IllegalAccessException
/* 427:    */  {
/* 428:428 */    writeStaticField(cls, fieldName, value, false);
/* 429:    */  }
/* 430:    */  
/* 441:    */  public static void writeStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess)
/* 442:    */    throws IllegalAccessException
/* 443:    */  {
/* 444:444 */    Field field = getField(cls, fieldName, forceAccess);
/* 445:445 */    if (field == null) {
/* 446:446 */      throw new IllegalArgumentException("Cannot locate field " + fieldName + " on " + cls);
/* 447:    */    }
/* 448:    */    
/* 449:449 */    writeStaticField(field, value);
/* 450:    */  }
/* 451:    */  
/* 459:    */  public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value)
/* 460:    */    throws IllegalAccessException
/* 461:    */  {
/* 462:462 */    writeDeclaredStaticField(cls, fieldName, value, false);
/* 463:    */  }
/* 464:    */  
/* 475:    */  public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess)
/* 476:    */    throws IllegalAccessException
/* 477:    */  {
/* 478:478 */    Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 479:479 */    if (field == null) {
/* 480:480 */      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/* 481:    */    }
/* 482:    */    
/* 483:483 */    writeField(field, (Object)null, value);
/* 484:    */  }
/* 485:    */  
/* 492:    */  public static void writeField(Field field, Object target, Object value)
/* 493:    */    throws IllegalAccessException
/* 494:    */  {
/* 495:495 */    writeField(field, target, value, false);
/* 496:    */  }
/* 497:    */  
/* 508:    */  public static void writeField(Field field, Object target, Object value, boolean forceAccess)
/* 509:    */    throws IllegalAccessException
/* 510:    */  {
/* 511:511 */    if (field == null) {
/* 512:512 */      throw new IllegalArgumentException("The field must not be null");
/* 513:    */    }
/* 514:514 */    if ((forceAccess) && (!field.isAccessible())) {
/* 515:515 */      field.setAccessible(true);
/* 516:    */    } else {
/* 517:517 */      MemberUtils.setAccessibleWorkaround(field);
/* 518:    */    }
/* 519:519 */    field.set(target, value);
/* 520:    */  }
/* 521:    */  
/* 528:    */  public static void writeField(Object target, String fieldName, Object value)
/* 529:    */    throws IllegalAccessException
/* 530:    */  {
/* 531:531 */    writeField(target, fieldName, value, false);
/* 532:    */  }
/* 533:    */  
/* 544:    */  public static void writeField(Object target, String fieldName, Object value, boolean forceAccess)
/* 545:    */    throws IllegalAccessException
/* 546:    */  {
/* 547:547 */    if (target == null) {
/* 548:548 */      throw new IllegalArgumentException("target object must not be null");
/* 549:    */    }
/* 550:550 */    Class<?> cls = target.getClass();
/* 551:551 */    Field field = getField(cls, fieldName, forceAccess);
/* 552:552 */    if (field == null) {
/* 553:553 */      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/* 554:    */    }
/* 555:    */    
/* 556:556 */    writeField(field, target, value);
/* 557:    */  }
/* 558:    */  
/* 565:    */  public static void writeDeclaredField(Object target, String fieldName, Object value)
/* 566:    */    throws IllegalAccessException
/* 567:    */  {
/* 568:568 */    writeDeclaredField(target, fieldName, value, false);
/* 569:    */  }
/* 570:    */  
/* 581:    */  public static void writeDeclaredField(Object target, String fieldName, Object value, boolean forceAccess)
/* 582:    */    throws IllegalAccessException
/* 583:    */  {
/* 584:584 */    if (target == null) {
/* 585:585 */      throw new IllegalArgumentException("target object must not be null");
/* 586:    */    }
/* 587:587 */    Class<?> cls = target.getClass();
/* 588:588 */    Field field = getDeclaredField(cls, fieldName, forceAccess);
/* 589:589 */    if (field == null) {
/* 590:590 */      throw new IllegalArgumentException("Cannot locate declared field " + cls.getName() + "." + fieldName);
/* 591:    */    }
/* 592:    */    
/* 593:593 */    writeField(field, target, value);
/* 594:    */  }
/* 595:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.reflect.FieldUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */