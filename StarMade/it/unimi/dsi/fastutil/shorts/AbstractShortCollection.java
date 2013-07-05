/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterators;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class AbstractShortCollection extends AbstractCollection<Short>
/*     */   implements ShortCollection
/*     */ {
/*     */   public short[] toArray(short[] a)
/*     */   {
/*  56 */     return toShortArray(a);
/*     */   }
/*     */   public short[] toShortArray() {
/*  59 */     return toShortArray(null);
/*     */   }
/*     */   public short[] toShortArray(short[] a) {
/*  62 */     if ((a == null) || (a.length < size())) a = new short[size()];
/*  63 */     ShortIterators.unwrap(iterator(), a);
/*  64 */     return a;
/*     */   }
/*     */ 
/*     */   public boolean addAll(ShortCollection c)
/*     */   {
/*  72 */     boolean retVal = false;
/*  73 */     ShortIterator i = c.iterator();
/*  74 */     int n = c.size();
/*  75 */     while (n-- != 0) if (add(i.nextShort())) retVal = true;
/*  76 */     return retVal;
/*     */   }
/*     */ 
/*     */   public boolean containsAll(ShortCollection c)
/*     */   {
/*  84 */     ShortIterator i = c.iterator();
/*  85 */     int n = c.size();
/*  86 */     while (n-- != 0) if (!contains(i.nextShort())) return false;
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean retainAll(ShortCollection c)
/*     */   {
/*  95 */     boolean retVal = false;
/*  96 */     int n = size();
/*  97 */     ShortIterator i = iterator();
/*  98 */     while (n-- != 0) {
/*  99 */       if (!c.contains(i.nextShort())) {
/* 100 */         i.remove();
/* 101 */         retVal = true;
/*     */       }
/*     */     }
/* 104 */     return retVal;
/*     */   }
/*     */ 
/*     */   public boolean removeAll(ShortCollection c)
/*     */   {
/* 112 */     boolean retVal = false;
/* 113 */     int n = c.size();
/* 114 */     ShortIterator i = c.iterator();
/*     */ 
/* 116 */     while (n-- != 0) if (rem(i.nextShort())) retVal = true;
/*     */ 
/* 118 */     return retVal;
/*     */   }
/*     */ 
/*     */   public Object[] toArray()
/*     */   {
/* 124 */     Object[] a = new Object[size()];
/* 125 */     ObjectIterators.unwrap(iterator(), a);
/* 126 */     return a;
/*     */   }
/*     */ 
/*     */   public <T> T[] toArray(T[] a)
/*     */   {
/* 131 */     if (a.length < size()) a = (Object[])Array.newInstance(a.getClass().getComponentType(), size());
/* 132 */     ObjectIterators.unwrap(iterator(), a);
/* 133 */     return a;
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends Short> c)
/*     */   {
/* 143 */     boolean retVal = false;
/* 144 */     Iterator i = c.iterator();
/* 145 */     int n = c.size();
/*     */ 
/* 147 */     while (n-- != 0) if (add((Short)i.next())) retVal = true;
/* 148 */     return retVal;
/*     */   }
/*     */ 
/*     */   public boolean add(short k) {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ShortIterator shortIterator()
/*     */   {
/* 159 */     return iterator();
/*     */   }
/*     */ 
/*     */   public abstract ShortIterator iterator();
/*     */ 
/*     */   public boolean remove(Object ok)
/*     */   {
/* 168 */     return rem(((Short)ok).shortValue());
/*     */   }
/*     */ 
/*     */   public boolean add(Short o)
/*     */   {
/* 173 */     return add(o.shortValue());
/*     */   }
/*     */ 
/*     */   public boolean rem(Object o)
/*     */   {
/* 178 */     return rem(((Short)o).shortValue());
/*     */   }
/*     */ 
/*     */   public boolean contains(Object o)
/*     */   {
/* 183 */     return contains(((Short)o).shortValue());
/*     */   }
/*     */ 
/*     */   public boolean contains(short k) {
/* 187 */     ShortIterator iterator = iterator();
/* 188 */     while (iterator.hasNext()) if (k == iterator.nextShort()) return true;
/* 189 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean rem(short k) {
/* 193 */     ShortIterator iterator = iterator();
/* 194 */     while (iterator.hasNext())
/* 195 */       if (k == iterator.nextShort()) {
/* 196 */         iterator.remove();
/* 197 */         return true;
/*     */       }
/* 199 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean containsAll(Collection<?> c)
/*     */   {
/* 211 */     int n = c.size();
/*     */ 
/* 213 */     Iterator i = c.iterator();
/* 214 */     while (n-- != 0) if (!contains(i.next())) return false;
/*     */ 
/* 216 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean retainAll(Collection<?> c)
/*     */   {
/* 227 */     boolean retVal = false;
/* 228 */     int n = size();
/*     */ 
/* 230 */     Iterator i = iterator();
/* 231 */     while (n-- != 0) {
/* 232 */       if (!c.contains(i.next())) {
/* 233 */         i.remove();
/* 234 */         retVal = true;
/*     */       }
/*     */     }
/*     */ 
/* 238 */     return retVal;
/*     */   }
/*     */ 
/*     */   public boolean removeAll(Collection<?> c)
/*     */   {
/* 249 */     boolean retVal = false;
/* 250 */     int n = c.size();
/*     */ 
/* 252 */     Iterator i = c.iterator();
/* 253 */     while (n-- != 0) if (remove(i.next())) retVal = true;
/*     */ 
/* 255 */     return retVal;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty() {
/* 259 */     return size() == 0;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 263 */     StringBuilder s = new StringBuilder();
/* 264 */     ShortIterator i = iterator();
/* 265 */     int n = size();
/*     */ 
/* 267 */     boolean first = true;
/*     */ 
/* 269 */     s.append("{");
/*     */ 
/* 271 */     while (n-- != 0) {
/* 272 */       if (first) first = false; else
/* 273 */         s.append(", ");
/* 274 */       short k = i.nextShort();
/*     */ 
/* 278 */       s.append(String.valueOf(k));
/*     */     }
/*     */ 
/* 281 */     s.append("}");
/* 282 */     return s.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShortCollection
 * JD-Core Version:    0.6.2
 */