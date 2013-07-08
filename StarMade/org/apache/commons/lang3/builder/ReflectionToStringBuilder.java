/*   1:    */package org.apache.commons.lang3.builder;
/*   2:    */
/*   3:    */import java.lang.reflect.AccessibleObject;
/*   4:    */import java.lang.reflect.Field;
/*   5:    */import java.lang.reflect.Modifier;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.Arrays;
/*   8:    */import java.util.Collection;
/*   9:    */import java.util.List;
/*  10:    */import org.apache.commons.lang3.ArrayUtils;
/*  11:    */
/* 108:    */public class ReflectionToStringBuilder
/* 109:    */  extends ToStringBuilder
/* 110:    */{
/* 111:    */  public static String toString(Object object)
/* 112:    */  {
/* 113:113 */    return toString(object, null, false, false, null);
/* 114:    */  }
/* 115:    */  
/* 143:    */  public static String toString(Object object, ToStringStyle style)
/* 144:    */  {
/* 145:145 */    return toString(object, style, false, false, null);
/* 146:    */  }
/* 147:    */  
/* 181:    */  public static String toString(Object object, ToStringStyle style, boolean outputTransients)
/* 182:    */  {
/* 183:183 */    return toString(object, style, outputTransients, false, null);
/* 184:    */  }
/* 185:    */  
/* 227:    */  public static String toString(Object object, ToStringStyle style, boolean outputTransients, boolean outputStatics)
/* 228:    */  {
/* 229:229 */    return toString(object, style, outputTransients, outputStatics, null);
/* 230:    */  }
/* 231:    */  
/* 280:    */  public static <T> String toString(T object, ToStringStyle style, boolean outputTransients, boolean outputStatics, Class<? super T> reflectUpToClass)
/* 281:    */  {
/* 282:282 */    return new ReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients, outputStatics).toString();
/* 283:    */  }
/* 284:    */  
/* 294:    */  public static String toStringExclude(Object object, Collection<String> excludeFieldNames)
/* 295:    */  {
/* 296:296 */    return toStringExclude(object, toNoNullStringArray(excludeFieldNames));
/* 297:    */  }
/* 298:    */  
/* 307:    */  static String[] toNoNullStringArray(Collection<String> collection)
/* 308:    */  {
/* 309:309 */    if (collection == null) {
/* 310:310 */      return ArrayUtils.EMPTY_STRING_ARRAY;
/* 311:    */    }
/* 312:312 */    return toNoNullStringArray(collection.toArray());
/* 313:    */  }
/* 314:    */  
/* 323:    */  static String[] toNoNullStringArray(Object[] array)
/* 324:    */  {
/* 325:325 */    List<String> list = new ArrayList(array.length);
/* 326:326 */    for (Object e : array) {
/* 327:327 */      if (e != null) {
/* 328:328 */        list.add(e.toString());
/* 329:    */      }
/* 330:    */    }
/* 331:331 */    return (String[])list.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
/* 332:    */  }
/* 333:    */  
/* 343:    */  public static String toStringExclude(Object object, String... excludeFieldNames)
/* 344:    */  {
/* 345:345 */    return new ReflectionToStringBuilder(object).setExcludeFieldNames(excludeFieldNames).toString();
/* 346:    */  }
/* 347:    */  
/* 351:351 */  private boolean appendStatics = false;
/* 352:    */  
/* 356:356 */  private boolean appendTransients = false;
/* 357:    */  
/* 362:    */  protected String[] excludeFieldNames;
/* 363:    */  
/* 368:368 */  private Class<?> upToClass = null;
/* 369:    */  
/* 383:    */  public ReflectionToStringBuilder(Object object)
/* 384:    */  {
/* 385:385 */    super(object);
/* 386:    */  }
/* 387:    */  
/* 403:    */  public ReflectionToStringBuilder(Object object, ToStringStyle style)
/* 404:    */  {
/* 405:405 */    super(object, style);
/* 406:    */  }
/* 407:    */  
/* 429:    */  public ReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer)
/* 430:    */  {
/* 431:431 */    super(object, style, buffer);
/* 432:    */  }
/* 433:    */  
/* 454:    */  public <T> ReflectionToStringBuilder(T object, ToStringStyle style, StringBuffer buffer, Class<? super T> reflectUpToClass, boolean outputTransients, boolean outputStatics)
/* 455:    */  {
/* 456:456 */    super(object, style, buffer);
/* 457:457 */    setUpToClass(reflectUpToClass);
/* 458:458 */    setAppendTransients(outputTransients);
/* 459:459 */    setAppendStatics(outputStatics);
/* 460:    */  }
/* 461:    */  
/* 473:    */  protected boolean accept(Field field)
/* 474:    */  {
/* 475:475 */    if (field.getName().indexOf('$') != -1)
/* 476:    */    {
/* 477:477 */      return false;
/* 478:    */    }
/* 479:479 */    if ((Modifier.isTransient(field.getModifiers())) && (!isAppendTransients()))
/* 480:    */    {
/* 481:481 */      return false;
/* 482:    */    }
/* 483:483 */    if ((Modifier.isStatic(field.getModifiers())) && (!isAppendStatics()))
/* 484:    */    {
/* 485:485 */      return false;
/* 486:    */    }
/* 487:487 */    if ((this.excludeFieldNames != null) && (Arrays.binarySearch(this.excludeFieldNames, field.getName()) >= 0))
/* 488:    */    {
/* 490:490 */      return false;
/* 491:    */    }
/* 492:492 */    return true;
/* 493:    */  }
/* 494:    */  
/* 507:    */  protected void appendFieldsIn(Class<?> clazz)
/* 508:    */  {
/* 509:509 */    if (clazz.isArray()) {
/* 510:510 */      reflectionAppendArray(getObject());
/* 511:511 */      return;
/* 512:    */    }
/* 513:513 */    Field[] fields = clazz.getDeclaredFields();
/* 514:514 */    AccessibleObject.setAccessible(fields, true);
/* 515:515 */    for (Field field : fields) {
/* 516:516 */      String fieldName = field.getName();
/* 517:517 */      if (accept(field))
/* 518:    */      {
/* 519:    */        try
/* 520:    */        {
/* 521:521 */          Object fieldValue = getValue(field);
/* 522:522 */          append(fieldName, fieldValue);
/* 524:    */        }
/* 525:    */        catch (IllegalAccessException ex)
/* 526:    */        {
/* 528:528 */          throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
/* 529:    */        }
/* 530:    */      }
/* 531:    */    }
/* 532:    */  }
/* 533:    */  
/* 536:    */  public String[] getExcludeFieldNames()
/* 537:    */  {
/* 538:538 */    return (String[])this.excludeFieldNames.clone();
/* 539:    */  }
/* 540:    */  
/* 547:    */  public Class<?> getUpToClass()
/* 548:    */  {
/* 549:549 */    return this.upToClass;
/* 550:    */  }
/* 551:    */  
/* 566:    */  protected Object getValue(Field field)
/* 567:    */    throws IllegalArgumentException, IllegalAccessException
/* 568:    */  {
/* 569:569 */    return field.get(getObject());
/* 570:    */  }
/* 571:    */  
/* 579:    */  public boolean isAppendStatics()
/* 580:    */  {
/* 581:581 */    return this.appendStatics;
/* 582:    */  }
/* 583:    */  
/* 590:    */  public boolean isAppendTransients()
/* 591:    */  {
/* 592:592 */    return this.appendTransients;
/* 593:    */  }
/* 594:    */  
/* 603:    */  public ReflectionToStringBuilder reflectionAppendArray(Object array)
/* 604:    */  {
/* 605:605 */    getStyle().reflectionAppendArrayDetail(getStringBuffer(), null, array);
/* 606:606 */    return this;
/* 607:    */  }
/* 608:    */  
/* 617:    */  public void setAppendStatics(boolean appendStatics)
/* 618:    */  {
/* 619:619 */    this.appendStatics = appendStatics;
/* 620:    */  }
/* 621:    */  
/* 629:    */  public void setAppendTransients(boolean appendTransients)
/* 630:    */  {
/* 631:631 */    this.appendTransients = appendTransients;
/* 632:    */  }
/* 633:    */  
/* 640:    */  public ReflectionToStringBuilder setExcludeFieldNames(String... excludeFieldNamesParam)
/* 641:    */  {
/* 642:642 */    if (excludeFieldNamesParam == null) {
/* 643:643 */      this.excludeFieldNames = null;
/* 644:    */    }
/* 645:    */    else {
/* 646:646 */      this.excludeFieldNames = toNoNullStringArray(excludeFieldNamesParam);
/* 647:647 */      Arrays.sort(this.excludeFieldNames);
/* 648:    */    }
/* 649:649 */    return this;
/* 650:    */  }
/* 651:    */  
/* 659:    */  public void setUpToClass(Class<?> clazz)
/* 660:    */  {
/* 661:661 */    if (clazz != null) {
/* 662:662 */      Object object = getObject();
/* 663:663 */      if ((object != null) && (!clazz.isInstance(object))) {
/* 664:664 */        throw new IllegalArgumentException("Specified class is not a superclass of the object");
/* 665:    */      }
/* 666:    */    }
/* 667:667 */    this.upToClass = clazz;
/* 668:    */  }
/* 669:    */  
/* 677:    */  public String toString()
/* 678:    */  {
/* 679:679 */    if (getObject() == null) {
/* 680:680 */      return getStyle().getNullText();
/* 681:    */    }
/* 682:682 */    Class<?> clazz = getObject().getClass();
/* 683:683 */    appendFieldsIn(clazz);
/* 684:684 */    while ((clazz.getSuperclass() != null) && (clazz != getUpToClass())) {
/* 685:685 */      clazz = clazz.getSuperclass();
/* 686:686 */      appendFieldsIn(clazz);
/* 687:    */    }
/* 688:688 */    return super.toString();
/* 689:    */  }
/* 690:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.ReflectionToStringBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */