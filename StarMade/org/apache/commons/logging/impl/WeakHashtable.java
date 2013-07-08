/*   1:    */package org.apache.commons.logging.impl;
/*   2:    */
/*   3:    */import java.lang.ref.Reference;
/*   4:    */import java.lang.ref.ReferenceQueue;
/*   5:    */import java.lang.ref.WeakReference;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.Enumeration;
/*   8:    */import java.util.HashSet;
/*   9:    */import java.util.Hashtable;
/*  10:    */import java.util.Iterator;
/*  11:    */import java.util.Map;
/*  12:    */import java.util.Map.Entry;
/*  13:    */import java.util.Set;
/*  14:    */
/* 121:    */public final class WeakHashtable
/* 122:    */  extends Hashtable
/* 123:    */{
/* 124:    */  private static final int MAX_CHANGES_BEFORE_PURGE = 100;
/* 125:    */  private static final int PARTIAL_PURGE_COUNT = 10;
/* 126:126 */  private ReferenceQueue queue = new ReferenceQueue();
/* 127:    */  
/* 128:128 */  private int changeCount = 0;
/* 129:    */  
/* 140:    */  public boolean containsKey(Object key)
/* 141:    */  {
/* 142:142 */    Referenced referenced = new Referenced(key, null);
/* 143:143 */    return super.containsKey(referenced);
/* 144:    */  }
/* 145:    */  
/* 148:    */  public Enumeration elements()
/* 149:    */  {
/* 150:150 */    purge();
/* 151:151 */    return super.elements();
/* 152:    */  }
/* 153:    */  
/* 156:    */  public Set entrySet()
/* 157:    */  {
/* 158:158 */    purge();
/* 159:159 */    Set referencedEntries = super.entrySet();
/* 160:160 */    Set unreferencedEntries = new HashSet();
/* 161:161 */    for (Iterator it = referencedEntries.iterator(); it.hasNext();) {
/* 162:162 */      Map.Entry entry = (Map.Entry)it.next();
/* 163:163 */      Referenced referencedKey = (Referenced)entry.getKey();
/* 164:164 */      Object key = referencedKey.getValue();
/* 165:165 */      Object value = entry.getValue();
/* 166:166 */      if (key != null) {
/* 167:167 */        Entry dereferencedEntry = new Entry(key, value, null);
/* 168:168 */        unreferencedEntries.add(dereferencedEntry);
/* 169:    */      }
/* 170:    */    }
/* 171:171 */    return unreferencedEntries;
/* 172:    */  }
/* 173:    */  
/* 177:    */  public Object get(Object key)
/* 178:    */  {
/* 179:179 */    Referenced referenceKey = new Referenced(key, null);
/* 180:180 */    return super.get(referenceKey);
/* 181:    */  }
/* 182:    */  
/* 185:    */  public Enumeration keys()
/* 186:    */  {
/* 187:187 */    purge();
/* 188:188 */    Enumeration enumer = super.keys();
/* 189:189 */    new Enumeration() { private final Enumeration val$enumer;
/* 190:    */      
/* 191:191 */      public boolean hasMoreElements() { return this.val$enumer.hasMoreElements(); }
/* 192:    */      
/* 193:    */      public Object nextElement() {
/* 194:194 */        WeakHashtable.Referenced nextReference = (WeakHashtable.Referenced)this.val$enumer.nextElement();
/* 195:195 */        return nextReference.getValue();
/* 196:    */      }
/* 197:    */    };
/* 198:    */  }
/* 199:    */  
/* 203:    */  public Set keySet()
/* 204:    */  {
/* 205:205 */    purge();
/* 206:206 */    Set referencedKeys = super.keySet();
/* 207:207 */    Set unreferencedKeys = new HashSet();
/* 208:208 */    for (Iterator it = referencedKeys.iterator(); it.hasNext();) {
/* 209:209 */      Referenced referenceKey = (Referenced)it.next();
/* 210:210 */      Object keyValue = referenceKey.getValue();
/* 211:211 */      if (keyValue != null) {
/* 212:212 */        unreferencedKeys.add(keyValue);
/* 213:    */      }
/* 214:    */    }
/* 215:215 */    return unreferencedKeys;
/* 216:    */  }
/* 217:    */  
/* 221:    */  public Object put(Object key, Object value)
/* 222:    */  {
/* 223:223 */    if (key == null) {
/* 224:224 */      throw new NullPointerException("Null keys are not allowed");
/* 225:    */    }
/* 226:226 */    if (value == null) {
/* 227:227 */      throw new NullPointerException("Null values are not allowed");
/* 228:    */    }
/* 229:    */    
/* 232:232 */    if (this.changeCount++ > 100) {
/* 233:233 */      purge();
/* 234:234 */      this.changeCount = 0;
/* 236:    */    }
/* 237:237 */    else if (this.changeCount % 10 == 0) {
/* 238:238 */      purgeOne();
/* 239:    */    }
/* 240:    */    
/* 241:241 */    Referenced keyRef = new Referenced(key, this.queue, null);
/* 242:242 */    return super.put(keyRef, value);
/* 243:    */  }
/* 244:    */  
/* 246:    */  public void putAll(Map t)
/* 247:    */  {
/* 248:    */    Iterator it;
/* 249:249 */    if (t != null) {
/* 250:250 */      Set entrySet = t.entrySet();
/* 251:251 */      for (it = entrySet.iterator(); it.hasNext();) {
/* 252:252 */        Map.Entry entry = (Map.Entry)it.next();
/* 253:253 */        put(entry.getKey(), entry.getValue());
/* 254:    */      }
/* 255:    */    }
/* 256:    */  }
/* 257:    */  
/* 260:    */  public Collection values()
/* 261:    */  {
/* 262:262 */    purge();
/* 263:263 */    return super.values();
/* 264:    */  }
/* 265:    */  
/* 270:    */  public Object remove(Object key)
/* 271:    */  {
/* 272:272 */    if (this.changeCount++ > 100) {
/* 273:273 */      purge();
/* 274:274 */      this.changeCount = 0;
/* 276:    */    }
/* 277:277 */    else if (this.changeCount % 10 == 0) {
/* 278:278 */      purgeOne();
/* 279:    */    }
/* 280:280 */    return super.remove(new Referenced(key, null));
/* 281:    */  }
/* 282:    */  
/* 285:    */  public boolean isEmpty()
/* 286:    */  {
/* 287:287 */    purge();
/* 288:288 */    return super.isEmpty();
/* 289:    */  }
/* 290:    */  
/* 293:    */  public int size()
/* 294:    */  {
/* 295:295 */    purge();
/* 296:296 */    return super.size();
/* 297:    */  }
/* 298:    */  
/* 301:    */  public String toString()
/* 302:    */  {
/* 303:303 */    purge();
/* 304:304 */    return super.toString();
/* 305:    */  }
/* 306:    */  
/* 310:    */  protected void rehash()
/* 311:    */  {
/* 312:312 */    purge();
/* 313:313 */    super.rehash();
/* 314:    */  }
/* 315:    */  
/* 319:    */  private void purge()
/* 320:    */  {
/* 321:321 */    synchronized (this.queue) {
/* 322:    */      WeakKey key;
/* 323:323 */      while ((key = (WeakKey)this.queue.poll()) != null) {
/* 324:324 */        super.remove(key.getReferenced());
/* 325:    */      }
/* 326:    */    }
/* 327:    */  }
/* 328:    */  
/* 333:    */  private void purgeOne()
/* 334:    */  {
/* 335:335 */    synchronized (this.queue) {
/* 336:336 */      WeakKey key = (WeakKey)this.queue.poll();
/* 337:337 */      if (key != null)
/* 338:338 */        super.remove(key.getReferenced());
/* 339:    */    } }
/* 340:    */  
/* 341:    */  private static final class Entry implements Map.Entry { private final Object key;
/* 342:    */    private final Object value;
/* 343:    */    
/* 344:344 */    Entry(Object x0, Object x1, WeakHashtable.1 x2) { this(x0, x1); }
/* 345:    */    
/* 348:    */    private Entry(Object key, Object value)
/* 349:    */    {
/* 350:350 */      this.key = key;
/* 351:351 */      this.value = value;
/* 352:    */    }
/* 353:    */    
/* 354:    */    public boolean equals(Object o) {
/* 355:355 */      boolean result = false;
/* 356:356 */      if ((o != null) && ((o instanceof Map.Entry))) {
/* 357:357 */        Map.Entry entry = (Map.Entry)o;
/* 358:358 */        result = (getKey() == null ? entry.getKey() == null : getKey().equals(entry.getKey())) && (getValue() == null ? entry.getValue() == null : getValue().equals(entry.getValue()));
/* 359:    */      }
/* 360:    */      
/* 366:366 */      return result;
/* 367:    */    }
/* 368:    */    
/* 369:    */    public int hashCode()
/* 370:    */    {
/* 371:371 */      return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
/* 372:    */    }
/* 373:    */    
/* 374:    */    public Object setValue(Object value)
/* 375:    */    {
/* 376:376 */      throw new UnsupportedOperationException("Entry.setValue is not supported.");
/* 377:    */    }
/* 378:    */    
/* 379:    */    public Object getValue() {
/* 380:380 */      return this.value;
/* 381:    */    }
/* 382:    */    
/* 384:384 */    public Object getKey() { return this.key; }
/* 385:    */  }
/* 386:    */  
/* 387:    */  private static final class Referenced { private final WeakReference reference;
/* 388:    */    private final int hashCode;
/* 389:    */    
/* 390:390 */    Referenced(Object x0, WeakHashtable.1 x1) { this(x0); } Referenced(Object x0, ReferenceQueue x1, WeakHashtable.1 x2) { this(x0, x1); }
/* 391:    */    
/* 398:    */    private Referenced(Object referant)
/* 399:    */    {
/* 400:400 */      this.reference = new WeakReference(referant);
/* 401:    */      
/* 403:403 */      this.hashCode = referant.hashCode();
/* 404:    */    }
/* 405:    */    
/* 409:    */    private Referenced(Object key, ReferenceQueue queue)
/* 410:    */    {
/* 411:411 */      this.reference = new WeakHashtable.WeakKey(key, queue, this, null);
/* 412:    */      
/* 414:414 */      this.hashCode = key.hashCode();
/* 415:    */    }
/* 416:    */    
/* 417:    */    public int hashCode()
/* 418:    */    {
/* 419:419 */      return this.hashCode;
/* 420:    */    }
/* 421:    */    
/* 422:    */    private Object getValue() {
/* 423:423 */      return this.reference.get();
/* 424:    */    }
/* 425:    */    
/* 426:    */    public boolean equals(Object o) {
/* 427:427 */      boolean result = false;
/* 428:428 */      if ((o instanceof Referenced)) {
/* 429:429 */        Referenced otherKey = (Referenced)o;
/* 430:430 */        Object thisKeyValue = getValue();
/* 431:431 */        Object otherKeyValue = otherKey.getValue();
/* 432:432 */        if (thisKeyValue == null) {
/* 433:433 */          result = otherKeyValue == null;
/* 434:    */          
/* 441:441 */          if (result == true) {
/* 442:442 */            result = hashCode() == otherKey.hashCode();
/* 444:    */          }
/* 445:    */          
/* 447:    */        }
/* 448:    */        else
/* 449:    */        {
/* 451:451 */          result = thisKeyValue.equals(otherKeyValue);
/* 452:    */        }
/* 453:    */      }
/* 454:454 */      return result;
/* 455:    */    }
/* 456:    */  }
/* 457:    */  
/* 458:    */  private static final class WeakKey extends WeakReference
/* 459:    */  {
/* 460:    */    private final WeakHashtable.Referenced referenced;
/* 461:    */    
/* 462:    */    WeakKey(Object x0, ReferenceQueue x1, WeakHashtable.Referenced x2, WeakHashtable.1 x3) {
/* 463:463 */      this(x0, x1, x2);
/* 464:    */    }
/* 465:    */    
/* 468:    */    private WeakKey(Object key, ReferenceQueue queue, WeakHashtable.Referenced referenced)
/* 469:    */    {
/* 470:470 */      super(queue);
/* 471:471 */      this.referenced = referenced;
/* 472:    */    }
/* 473:    */    
/* 474:    */    private WeakHashtable.Referenced getReferenced() {
/* 475:475 */      return this.referenced;
/* 476:    */    }
/* 477:    */  }
/* 478:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.logging.impl.WeakHashtable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */