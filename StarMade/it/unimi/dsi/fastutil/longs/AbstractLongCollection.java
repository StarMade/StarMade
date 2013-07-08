/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectIterators;
/*   4:    */import java.lang.reflect.Array;
/*   5:    */import java.util.AbstractCollection;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.Iterator;
/*   8:    */
/*  50:    */public abstract class AbstractLongCollection
/*  51:    */  extends AbstractCollection<Long>
/*  52:    */  implements LongCollection
/*  53:    */{
/*  54:    */  public long[] toArray(long[] a)
/*  55:    */  {
/*  56: 56 */    return toLongArray(a);
/*  57:    */  }
/*  58:    */  
/*  59: 59 */  public long[] toLongArray() { return toLongArray(null); }
/*  60:    */  
/*  61:    */  public long[] toLongArray(long[] a) {
/*  62: 62 */    if ((a == null) || (a.length < size())) a = new long[size()];
/*  63: 63 */    LongIterators.unwrap(iterator(), a);
/*  64: 64 */    return a;
/*  65:    */  }
/*  66:    */  
/*  70:    */  public boolean addAll(LongCollection c)
/*  71:    */  {
/*  72: 72 */    boolean retVal = false;
/*  73: 73 */    LongIterator i = c.iterator();
/*  74: 74 */    int n = c.size();
/*  75: 75 */    while (n-- != 0) if (add(i.nextLong())) retVal = true;
/*  76: 76 */    return retVal;
/*  77:    */  }
/*  78:    */  
/*  82:    */  public boolean containsAll(LongCollection c)
/*  83:    */  {
/*  84: 84 */    LongIterator i = c.iterator();
/*  85: 85 */    int n = c.size();
/*  86: 86 */    while (n-- != 0) if (!contains(i.nextLong())) return false;
/*  87: 87 */    return true;
/*  88:    */  }
/*  89:    */  
/*  93:    */  public boolean retainAll(LongCollection c)
/*  94:    */  {
/*  95: 95 */    boolean retVal = false;
/*  96: 96 */    int n = size();
/*  97: 97 */    LongIterator i = iterator();
/*  98: 98 */    while (n-- != 0) {
/*  99: 99 */      if (!c.contains(i.nextLong())) {
/* 100:100 */        i.remove();
/* 101:101 */        retVal = true;
/* 102:    */      }
/* 103:    */    }
/* 104:104 */    return retVal;
/* 105:    */  }
/* 106:    */  
/* 110:    */  public boolean removeAll(LongCollection c)
/* 111:    */  {
/* 112:112 */    boolean retVal = false;
/* 113:113 */    int n = c.size();
/* 114:114 */    LongIterator i = c.iterator();
/* 115:    */    
/* 116:116 */    while (n-- != 0) if (rem(i.nextLong())) { retVal = true;
/* 117:    */      }
/* 118:118 */    return retVal;
/* 119:    */  }
/* 120:    */  
/* 122:    */  public Object[] toArray()
/* 123:    */  {
/* 124:124 */    Object[] a = new Object[size()];
/* 125:125 */    ObjectIterators.unwrap(iterator(), a);
/* 126:126 */    return a;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public <T> T[] toArray(T[] a)
/* 130:    */  {
/* 131:131 */    if (a.length < size()) a = (Object[])Array.newInstance(a.getClass().getComponentType(), size());
/* 132:132 */    ObjectIterators.unwrap(iterator(), a);
/* 133:133 */    return a;
/* 134:    */  }
/* 135:    */  
/* 141:    */  public boolean addAll(Collection<? extends Long> c)
/* 142:    */  {
/* 143:143 */    boolean retVal = false;
/* 144:144 */    Iterator<? extends Long> i = c.iterator();
/* 145:145 */    int n = c.size();
/* 146:    */    
/* 147:147 */    while (n-- != 0) if (add((Long)i.next())) retVal = true;
/* 148:148 */    return retVal;
/* 149:    */  }
/* 150:    */  
/* 151:    */  public boolean add(long k) {
/* 152:152 */    throw new UnsupportedOperationException();
/* 153:    */  }
/* 154:    */  
/* 156:    */  @Deprecated
/* 157:    */  public LongIterator longIterator()
/* 158:    */  {
/* 159:159 */    return iterator();
/* 160:    */  }
/* 161:    */  
/* 163:    */  public abstract LongIterator iterator();
/* 164:    */  
/* 166:    */  public boolean remove(Object ok)
/* 167:    */  {
/* 168:168 */    return rem(((Long)ok).longValue());
/* 169:    */  }
/* 170:    */  
/* 171:    */  public boolean add(Long o)
/* 172:    */  {
/* 173:173 */    return add(o.longValue());
/* 174:    */  }
/* 175:    */  
/* 176:    */  public boolean rem(Object o)
/* 177:    */  {
/* 178:178 */    return rem(((Long)o).longValue());
/* 179:    */  }
/* 180:    */  
/* 181:    */  public boolean contains(Object o)
/* 182:    */  {
/* 183:183 */    return contains(((Long)o).longValue());
/* 184:    */  }
/* 185:    */  
/* 186:    */  public boolean contains(long k) {
/* 187:187 */    LongIterator iterator = iterator();
/* 188:188 */    while (iterator.hasNext()) if (k == iterator.nextLong()) return true;
/* 189:189 */    return false;
/* 190:    */  }
/* 191:    */  
/* 192:    */  public boolean rem(long k) {
/* 193:193 */    LongIterator iterator = iterator();
/* 194:194 */    while (iterator.hasNext())
/* 195:195 */      if (k == iterator.nextLong()) {
/* 196:196 */        iterator.remove();
/* 197:197 */        return true;
/* 198:    */      }
/* 199:199 */    return false;
/* 200:    */  }
/* 201:    */  
/* 209:    */  public boolean containsAll(Collection<?> c)
/* 210:    */  {
/* 211:211 */    int n = c.size();
/* 212:    */    
/* 213:213 */    Iterator<?> i = c.iterator();
/* 214:214 */    while (n-- != 0) if (!contains(i.next())) { return false;
/* 215:    */      }
/* 216:216 */    return true;
/* 217:    */  }
/* 218:    */  
/* 225:    */  public boolean retainAll(Collection<?> c)
/* 226:    */  {
/* 227:227 */    boolean retVal = false;
/* 228:228 */    int n = size();
/* 229:    */    
/* 230:230 */    Iterator<?> i = iterator();
/* 231:231 */    while (n-- != 0) {
/* 232:232 */      if (!c.contains(i.next())) {
/* 233:233 */        i.remove();
/* 234:234 */        retVal = true;
/* 235:    */      }
/* 236:    */    }
/* 237:    */    
/* 238:238 */    return retVal;
/* 239:    */  }
/* 240:    */  
/* 247:    */  public boolean removeAll(Collection<?> c)
/* 248:    */  {
/* 249:249 */    boolean retVal = false;
/* 250:250 */    int n = c.size();
/* 251:    */    
/* 252:252 */    Iterator<?> i = c.iterator();
/* 253:253 */    while (n-- != 0) if (remove(i.next())) { retVal = true;
/* 254:    */      }
/* 255:255 */    return retVal;
/* 256:    */  }
/* 257:    */  
/* 258:    */  public boolean isEmpty() {
/* 259:259 */    return size() == 0;
/* 260:    */  }
/* 261:    */  
/* 262:    */  public String toString() {
/* 263:263 */    StringBuilder s = new StringBuilder();
/* 264:264 */    LongIterator i = iterator();
/* 265:265 */    int n = size();
/* 266:    */    
/* 267:267 */    boolean first = true;
/* 268:    */    
/* 269:269 */    s.append("{");
/* 270:    */    
/* 271:271 */    while (n-- != 0) {
/* 272:272 */      if (first) first = false; else
/* 273:273 */        s.append(", ");
/* 274:274 */      long k = i.nextLong();
/* 275:    */      
/* 278:278 */      s.append(String.valueOf(k));
/* 279:    */    }
/* 280:    */    
/* 281:281 */    s.append("}");
/* 282:282 */    return s.toString();
/* 283:    */  }
/* 284:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLongCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */