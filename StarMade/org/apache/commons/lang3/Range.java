/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Comparator;
/*   5:    */
/*  64:    */public final class Range<T>
/*  65:    */  implements Serializable
/*  66:    */{
/*  67:    */  private static final long serialVersionUID = 1L;
/*  68:    */  private final Comparator<T> comparator;
/*  69:    */  private final T minimum;
/*  70:    */  private final T maximum;
/*  71:    */  private transient int hashCode;
/*  72:    */  private transient String toString;
/*  73:    */  
/*  74:    */  public static <T extends Comparable<T>> Range<T> is(T element)
/*  75:    */  {
/*  76: 76 */    return between(element, element, null);
/*  77:    */  }
/*  78:    */  
/*  92:    */  public static <T> Range<T> is(T element, Comparator<T> comparator)
/*  93:    */  {
/*  94: 94 */    return between(element, element, comparator);
/*  95:    */  }
/*  96:    */  
/* 112:    */  public static <T extends Comparable<T>> Range<T> between(T fromInclusive, T toInclusive)
/* 113:    */  {
/* 114:114 */    return between(fromInclusive, toInclusive, null);
/* 115:    */  }
/* 116:    */  
/* 133:    */  public static <T> Range<T> between(T fromInclusive, T toInclusive, Comparator<T> comparator)
/* 134:    */  {
/* 135:135 */    return new Range(fromInclusive, toInclusive, comparator);
/* 136:    */  }
/* 137:    */  
/* 145:    */  private Range(T element1, T element2, Comparator<T> comparator)
/* 146:    */  {
/* 147:147 */    if ((element1 == null) || (element2 == null)) {
/* 148:148 */      throw new IllegalArgumentException("Elements in a range must not be null: element1=" + element1 + ", element2=" + element2);
/* 149:    */    }
/* 150:    */    
/* 151:151 */    if (comparator == null) {
/* 152:152 */      comparator = ComparableComparator.INSTANCE;
/* 153:    */    }
/* 154:154 */    if (comparator.compare(element1, element2) < 1) {
/* 155:155 */      this.minimum = element1;
/* 156:156 */      this.maximum = element2;
/* 157:    */    } else {
/* 158:158 */      this.minimum = element2;
/* 159:159 */      this.maximum = element1;
/* 160:    */    }
/* 161:161 */    this.comparator = comparator;
/* 162:    */  }
/* 163:    */  
/* 171:    */  public T getMinimum()
/* 172:    */  {
/* 173:173 */    return this.minimum;
/* 174:    */  }
/* 175:    */  
/* 180:    */  public T getMaximum()
/* 181:    */  {
/* 182:182 */    return this.maximum;
/* 183:    */  }
/* 184:    */  
/* 192:    */  public Comparator<T> getComparator()
/* 193:    */  {
/* 194:194 */    return this.comparator;
/* 195:    */  }
/* 196:    */  
/* 204:    */  public boolean isNaturalOrdering()
/* 205:    */  {
/* 206:206 */    return this.comparator == ComparableComparator.INSTANCE;
/* 207:    */  }
/* 208:    */  
/* 217:    */  public boolean contains(T element)
/* 218:    */  {
/* 219:219 */    if (element == null) {
/* 220:220 */      return false;
/* 221:    */    }
/* 222:222 */    return (this.comparator.compare(element, this.minimum) > -1) && (this.comparator.compare(element, this.maximum) < 1);
/* 223:    */  }
/* 224:    */  
/* 230:    */  public boolean isAfter(T element)
/* 231:    */  {
/* 232:232 */    if (element == null) {
/* 233:233 */      return false;
/* 234:    */    }
/* 235:235 */    return this.comparator.compare(element, this.minimum) < 0;
/* 236:    */  }
/* 237:    */  
/* 243:    */  public boolean isStartedBy(T element)
/* 244:    */  {
/* 245:245 */    if (element == null) {
/* 246:246 */      return false;
/* 247:    */    }
/* 248:248 */    return this.comparator.compare(element, this.minimum) == 0;
/* 249:    */  }
/* 250:    */  
/* 256:    */  public boolean isEndedBy(T element)
/* 257:    */  {
/* 258:258 */    if (element == null) {
/* 259:259 */      return false;
/* 260:    */    }
/* 261:261 */    return this.comparator.compare(element, this.maximum) == 0;
/* 262:    */  }
/* 263:    */  
/* 269:    */  public boolean isBefore(T element)
/* 270:    */  {
/* 271:271 */    if (element == null) {
/* 272:272 */      return false;
/* 273:    */    }
/* 274:274 */    return this.comparator.compare(element, this.maximum) > 0;
/* 275:    */  }
/* 276:    */  
/* 286:    */  public int elementCompareTo(T element)
/* 287:    */  {
/* 288:288 */    if (element == null)
/* 289:    */    {
/* 290:290 */      throw new NullPointerException("Element is null");
/* 291:    */    }
/* 292:292 */    if (isAfter(element))
/* 293:293 */      return -1;
/* 294:294 */    if (isBefore(element)) {
/* 295:295 */      return 1;
/* 296:    */    }
/* 297:297 */    return 0;
/* 298:    */  }
/* 299:    */  
/* 312:    */  public boolean containsRange(Range<T> otherRange)
/* 313:    */  {
/* 314:314 */    if (otherRange == null) {
/* 315:315 */      return false;
/* 316:    */    }
/* 317:317 */    return (contains(otherRange.minimum)) && (contains(otherRange.maximum));
/* 318:    */  }
/* 319:    */  
/* 329:    */  public boolean isAfterRange(Range<T> otherRange)
/* 330:    */  {
/* 331:331 */    if (otherRange == null) {
/* 332:332 */      return false;
/* 333:    */    }
/* 334:334 */    return isAfter(otherRange.maximum);
/* 335:    */  }
/* 336:    */  
/* 348:    */  public boolean isOverlappedBy(Range<T> otherRange)
/* 349:    */  {
/* 350:350 */    if (otherRange == null) {
/* 351:351 */      return false;
/* 352:    */    }
/* 353:353 */    return (otherRange.contains(this.minimum)) || (otherRange.contains(this.maximum)) || (contains(otherRange.minimum));
/* 354:    */  }
/* 355:    */  
/* 366:    */  public boolean isBeforeRange(Range<T> otherRange)
/* 367:    */  {
/* 368:368 */    if (otherRange == null) {
/* 369:369 */      return false;
/* 370:    */    }
/* 371:371 */    return isBefore(otherRange.minimum);
/* 372:    */  }
/* 373:    */  
/* 380:    */  public Range<T> intersectionWith(Range<T> other)
/* 381:    */  {
/* 382:382 */    if (!isOverlappedBy(other)) {
/* 383:383 */      throw new IllegalArgumentException(String.format("Cannot calculate intersection with non-overlapping range %s", new Object[] { other }));
/* 384:    */    }
/* 385:    */    
/* 386:386 */    if (equals(other)) {
/* 387:387 */      return this;
/* 388:    */    }
/* 389:389 */    T min = getComparator().compare(this.minimum, other.minimum) < 0 ? other.minimum : this.minimum;
/* 390:390 */    T max = getComparator().compare(this.maximum, other.maximum) < 0 ? this.maximum : other.maximum;
/* 391:391 */    return between(min, max, getComparator());
/* 392:    */  }
/* 393:    */  
/* 406:    */  public boolean equals(Object obj)
/* 407:    */  {
/* 408:408 */    if (obj == this)
/* 409:409 */      return true;
/* 410:410 */    if ((obj == null) || (obj.getClass() != getClass())) {
/* 411:411 */      return false;
/* 412:    */    }
/* 413:    */    
/* 414:414 */    Range<T> range = (Range)obj;
/* 415:415 */    return (this.minimum.equals(range.minimum)) && (this.maximum.equals(range.maximum));
/* 416:    */  }
/* 417:    */  
/* 425:    */  public int hashCode()
/* 426:    */  {
/* 427:427 */    int result = this.hashCode;
/* 428:428 */    if (this.hashCode == 0) {
/* 429:429 */      result = 17;
/* 430:430 */      result = 37 * result + getClass().hashCode();
/* 431:431 */      result = 37 * result + this.minimum.hashCode();
/* 432:432 */      result = 37 * result + this.maximum.hashCode();
/* 433:433 */      this.hashCode = result;
/* 434:    */    }
/* 435:435 */    return result;
/* 436:    */  }
/* 437:    */  
/* 445:    */  public String toString()
/* 446:    */  {
/* 447:447 */    String result = this.toString;
/* 448:448 */    if (result == null) {
/* 449:449 */      StringBuilder buf = new StringBuilder(32);
/* 450:450 */      buf.append('[');
/* 451:451 */      buf.append(this.minimum);
/* 452:452 */      buf.append("..");
/* 453:453 */      buf.append(this.maximum);
/* 454:454 */      buf.append(']');
/* 455:455 */      result = buf.toString();
/* 456:456 */      this.toString = result;
/* 457:    */    }
/* 458:458 */    return result;
/* 459:    */  }
/* 460:    */  
/* 472:    */  public String toString(String format)
/* 473:    */  {
/* 474:474 */    return String.format(format, new Object[] { this.minimum, this.maximum, this.comparator });
/* 475:    */  }
/* 476:    */  
/* 477:    */  private static enum ComparableComparator
/* 478:    */    implements Comparator
/* 479:    */  {
/* 480:480 */    INSTANCE;
/* 481:    */    
/* 484:    */    private ComparableComparator() {}
/* 485:    */    
/* 487:    */    public int compare(Object obj1, Object obj2)
/* 488:    */    {
/* 489:489 */      return ((Comparable)obj1).compareTo(obj2);
/* 490:    */    }
/* 491:    */  }
/* 492:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.Range
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */