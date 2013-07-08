/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.lang.reflect.Array;
/*   5:    */import java.lang.reflect.InvocationTargetException;
/*   6:    */import java.lang.reflect.Method;
/*   7:    */import java.util.Collections;
/*   8:    */import java.util.Comparator;
/*   9:    */import java.util.HashMap;
/*  10:    */import java.util.Map.Entry;
/*  11:    */import java.util.TreeSet;
/*  12:    */import org.apache.commons.lang3.exception.CloneFailedException;
/*  13:    */import org.apache.commons.lang3.mutable.MutableInt;
/*  14:    */
/*  59:    */public class ObjectUtils
/*  60:    */{
/*  61: 61 */  public static final Null NULL = new Null();
/*  62:    */  
/*  92:    */  public static <T> T defaultIfNull(T object, T defaultValue)
/*  93:    */  {
/*  94: 94 */    return object != null ? object : defaultValue;
/*  95:    */  }
/*  96:    */  
/* 118:    */  public static <T> T firstNonNull(T... values)
/* 119:    */  {
/* 120:120 */    if (values != null) {
/* 121:121 */      for (T val : values) {
/* 122:122 */        if (val != null) {
/* 123:123 */          return val;
/* 124:    */        }
/* 125:    */      }
/* 126:    */    }
/* 127:127 */    return null;
/* 128:    */  }
/* 129:    */  
/* 150:    */  public static boolean equals(Object object1, Object object2)
/* 151:    */  {
/* 152:152 */    if (object1 == object2) {
/* 153:153 */      return true;
/* 154:    */    }
/* 155:155 */    if ((object1 == null) || (object2 == null)) {
/* 156:156 */      return false;
/* 157:    */    }
/* 158:158 */    return object1.equals(object2);
/* 159:    */  }
/* 160:    */  
/* 179:    */  public static boolean notEqual(Object object1, Object object2)
/* 180:    */  {
/* 181:181 */    return !equals(object1, object2);
/* 182:    */  }
/* 183:    */  
/* 197:    */  public static int hashCode(Object obj)
/* 198:    */  {
/* 199:199 */    return obj == null ? 0 : obj.hashCode();
/* 200:    */  }
/* 201:    */  
/* 221:    */  public static int hashCodeMulti(Object... objects)
/* 222:    */  {
/* 223:223 */    int hash = 1;
/* 224:224 */    if (objects != null) {
/* 225:225 */      for (Object object : objects) {
/* 226:226 */        hash = hash * 31 + hashCode(object);
/* 227:    */      }
/* 228:    */    }
/* 229:229 */    return hash;
/* 230:    */  }
/* 231:    */  
/* 249:    */  public static String identityToString(Object object)
/* 250:    */  {
/* 251:251 */    if (object == null) {
/* 252:252 */      return null;
/* 253:    */    }
/* 254:254 */    StringBuffer buffer = new StringBuffer();
/* 255:255 */    identityToString(buffer, object);
/* 256:256 */    return buffer.toString();
/* 257:    */  }
/* 258:    */  
/* 273:    */  public static void identityToString(StringBuffer buffer, Object object)
/* 274:    */  {
/* 275:275 */    if (object == null) {
/* 276:276 */      throw new NullPointerException("Cannot get the toString of a null identity");
/* 277:    */    }
/* 278:278 */    buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
/* 279:    */  }
/* 280:    */  
/* 301:    */  public static String toString(Object obj)
/* 302:    */  {
/* 303:303 */    return obj == null ? "" : obj.toString();
/* 304:    */  }
/* 305:    */  
/* 324:    */  public static String toString(Object obj, String nullStr)
/* 325:    */  {
/* 326:326 */    return obj == null ? nullStr : obj.toString();
/* 327:    */  }
/* 328:    */  
/* 343:    */  public static <T extends Comparable<? super T>> T min(T... values)
/* 344:    */  {
/* 345:345 */    T result = null;
/* 346:346 */    if (values != null) {
/* 347:347 */      for (T value : values) {
/* 348:348 */        if (compare(value, result, true) < 0) {
/* 349:349 */          result = value;
/* 350:    */        }
/* 351:    */      }
/* 352:    */    }
/* 353:353 */    return result;
/* 354:    */  }
/* 355:    */  
/* 368:    */  public static <T extends Comparable<? super T>> T max(T... values)
/* 369:    */  {
/* 370:370 */    T result = null;
/* 371:371 */    if (values != null) {
/* 372:372 */      for (T value : values) {
/* 373:373 */        if (compare(value, result, false) > 0) {
/* 374:374 */          result = value;
/* 375:    */        }
/* 376:    */      }
/* 377:    */    }
/* 378:378 */    return result;
/* 379:    */  }
/* 380:    */  
/* 390:    */  public static <T extends Comparable<? super T>> int compare(T c1, T c2)
/* 391:    */  {
/* 392:392 */    return compare(c1, c2, false);
/* 393:    */  }
/* 394:    */  
/* 407:    */  public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean nullGreater)
/* 408:    */  {
/* 409:409 */    if (c1 == c2)
/* 410:410 */      return 0;
/* 411:411 */    if (c1 == null)
/* 412:412 */      return nullGreater ? 1 : -1;
/* 413:413 */    if (c2 == null) {
/* 414:414 */      return nullGreater ? -1 : 1;
/* 415:    */    }
/* 416:416 */    return c1.compareTo(c2);
/* 417:    */  }
/* 418:    */  
/* 428:    */  public static <T extends Comparable<? super T>> T median(T... items)
/* 429:    */  {
/* 430:430 */    Validate.notEmpty(items);
/* 431:431 */    Validate.noNullElements(items);
/* 432:432 */    TreeSet<T> sort = new TreeSet();
/* 433:433 */    Collections.addAll(sort, items);
/* 434:    */    
/* 435:435 */    T result = (Comparable)sort.toArray()[((sort.size() - 1) / 2)];
/* 436:436 */    return result;
/* 437:    */  }
/* 438:    */  
/* 449:    */  public static <T> T median(Comparator<T> comparator, T... items)
/* 450:    */  {
/* 451:451 */    Validate.notEmpty(items, "null/empty items", new Object[0]);
/* 452:452 */    Validate.noNullElements(items);
/* 453:453 */    Validate.notNull(comparator, "null comparator", new Object[0]);
/* 454:454 */    TreeSet<T> sort = new TreeSet(comparator);
/* 455:455 */    Collections.addAll(sort, items);
/* 456:    */    
/* 457:457 */    T result = sort.toArray()[((sort.size() - 1) / 2)];
/* 458:458 */    return result;
/* 459:    */  }
/* 460:    */  
/* 470:    */  public static <T> T mode(T... items)
/* 471:    */  {
/* 472:472 */    if (ArrayUtils.isNotEmpty(items)) {
/* 473:473 */      HashMap<T, MutableInt> occurrences = new HashMap(items.length);
/* 474:474 */      for (T t : items) {
/* 475:475 */        MutableInt count = (MutableInt)occurrences.get(t);
/* 476:476 */        if (count == null) {
/* 477:477 */          occurrences.put(t, new MutableInt(1));
/* 478:    */        } else {
/* 479:479 */          count.increment();
/* 480:    */        }
/* 481:    */      }
/* 482:482 */      T result = null;
/* 483:483 */      int max = 0;
/* 484:484 */      for (Map.Entry<T, MutableInt> e : occurrences.entrySet()) {
/* 485:485 */        int cmp = ((MutableInt)e.getValue()).intValue();
/* 486:486 */        if (cmp == max) {
/* 487:487 */          result = null;
/* 488:488 */        } else if (cmp > max) {
/* 489:489 */          max = cmp;
/* 490:490 */          result = e.getKey();
/* 491:    */        }
/* 492:    */      }
/* 493:493 */      return result;
/* 494:    */    }
/* 495:495 */    return null;
/* 496:    */  }
/* 497:    */  
/* 508:    */  public static <T> T clone(T obj)
/* 509:    */  {
/* 510:510 */    if ((obj instanceof Cloneable)) {
/* 511:    */      Object result;
/* 512:512 */      if (obj.getClass().isArray()) {
/* 513:513 */        Class<?> componentType = obj.getClass().getComponentType();
/* 514:514 */        Object result; if (!componentType.isPrimitive()) {
/* 515:515 */          result = ((Object[])obj).clone();
/* 516:    */        } else {
/* 517:517 */          int length = Array.getLength(obj);
/* 518:518 */          Object result = Array.newInstance(componentType, length);
/* 519:519 */          while (length-- > 0) {
/* 520:520 */            Array.set(result, length, Array.get(obj, length));
/* 521:    */          }
/* 522:    */        }
/* 523:    */      } else {
/* 524:    */        try {
/* 525:525 */          Method clone = obj.getClass().getMethod("clone", new Class[0]);
/* 526:526 */          result = clone.invoke(obj, new Object[0]);
/* 527:    */        } catch (NoSuchMethodException e) {
/* 528:528 */          throw new CloneFailedException("Cloneable type " + obj.getClass().getName() + " has no clone method", e);
/* 529:    */        }
/* 530:    */        catch (IllegalAccessException e)
/* 531:    */        {
/* 532:532 */          throw new CloneFailedException("Cannot clone Cloneable type " + obj.getClass().getName(), e);
/* 533:    */        }
/* 534:    */        catch (InvocationTargetException e) {
/* 535:535 */          throw new CloneFailedException("Exception cloning Cloneable type " + obj.getClass().getName(), e.getCause());
/* 536:    */        }
/* 537:    */      }
/* 538:    */      
/* 540:540 */      T checked = result;
/* 541:541 */      return checked;
/* 542:    */    }
/* 543:    */    
/* 544:544 */    return null;
/* 545:    */  }
/* 546:    */  
/* 562:    */  public static <T> T cloneIfPossible(T obj)
/* 563:    */  {
/* 564:564 */    T clone = clone(obj);
/* 565:565 */    return clone == null ? obj : clone;
/* 566:    */  }
/* 567:    */  
/* 583:    */  public static class Null
/* 584:    */    implements Serializable
/* 585:    */  {
/* 586:    */    private static final long serialVersionUID = 7092611880189329093L;
/* 587:    */    
/* 602:    */    private Object readResolve()
/* 603:    */    {
/* 604:604 */      return ObjectUtils.NULL;
/* 605:    */    }
/* 606:    */  }
/* 607:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.ObjectUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */