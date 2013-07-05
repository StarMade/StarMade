/*     */ package it.unimi.dsi.fastutil.booleans;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterators;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class AbstractBooleanCollection extends AbstractCollection<Boolean>
/*     */   implements BooleanCollection
/*     */ {
/*     */   public boolean[] toArray(boolean[] a)
/*     */   {
/*  56 */     return toBooleanArray(a);
/*     */   }
/*     */   public boolean[] toBooleanArray() {
/*  59 */     return toBooleanArray(null);
/*     */   }
/*     */   public boolean[] toBooleanArray(boolean[] a) {
/*  62 */     if ((a == null) || (a.length < size())) a = new boolean[size()];
/*  63 */     BooleanIterators.unwrap(iterator(), a);
/*  64 */     return a;
/*     */   }
/*     */ 
/*     */   public boolean addAll(BooleanCollection c)
/*     */   {
/*  72 */     boolean retVal = false;
/*  73 */     BooleanIterator i = c.iterator();
/*  74 */     int n = c.size();
/*  75 */     while (n-- != 0) if (add(i.nextBoolean())) retVal = true;
/*  76 */     return retVal;
/*     */   }
/*     */ 
/*     */   public boolean containsAll(BooleanCollection c)
/*     */   {
/*  84 */     BooleanIterator i = c.iterator();
/*  85 */     int n = c.size();
/*  86 */     while (n-- != 0) if (!contains(i.nextBoolean())) return false;
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean retainAll(BooleanCollection c)
/*     */   {
/*  95 */     boolean retVal = false;
/*  96 */     int n = size();
/*  97 */     BooleanIterator i = iterator();
/*  98 */     while (n-- != 0) {
/*  99 */       if (!c.contains(i.nextBoolean())) {
/* 100 */         i.remove();
/* 101 */         retVal = true;
/*     */       }
/*     */     }
/* 104 */     return retVal;
/*     */   }
/*     */ 
/*     */   public boolean removeAll(BooleanCollection c)
/*     */   {
/* 112 */     boolean retVal = false;
/* 113 */     int n = c.size();
/* 114 */     BooleanIterator i = c.iterator();
/*     */ 
/* 116 */     while (n-- != 0) if (rem(i.nextBoolean())) retVal = true;
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
/*     */   public boolean addAll(Collection<? extends Boolean> c)
/*     */   {
/* 143 */     boolean retVal = false;
/* 144 */     Iterator i = c.iterator();
/* 145 */     int n = c.size();
/*     */ 
/* 147 */     while (n-- != 0) if (add((Boolean)i.next())) retVal = true;
/* 148 */     return retVal;
/*     */   }
/*     */ 
/*     */   public boolean add(boolean k) {
/* 152 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public BooleanIterator booleanIterator()
/*     */   {
/* 159 */     return iterator();
/*     */   }
/*     */ 
/*     */   public abstract BooleanIterator iterator();
/*     */ 
/*     */   public boolean remove(Object ok)
/*     */   {
/* 168 */     return rem(((Boolean)ok).booleanValue());
/*     */   }
/*     */ 
/*     */   public boolean add(Boolean o)
/*     */   {
/* 173 */     return add(o.booleanValue());
/*     */   }
/*     */ 
/*     */   public boolean rem(Object o)
/*     */   {
/* 178 */     return rem(((Boolean)o).booleanValue());
/*     */   }
/*     */ 
/*     */   public boolean contains(Object o)
/*     */   {
/* 183 */     return contains(((Boolean)o).booleanValue());
/*     */   }
/*     */ 
/*     */   public boolean contains(boolean k) {
/* 187 */     BooleanIterator iterator = iterator();
/* 188 */     while (iterator.hasNext()) if (k == iterator.nextBoolean()) return true;
/* 189 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean rem(boolean k) {
/* 193 */     BooleanIterator iterator = iterator();
/* 194 */     while (iterator.hasNext())
/* 195 */       if (k == iterator.nextBoolean()) {
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
/* 264 */     BooleanIterator i = iterator();
/* 265 */     int n = size();
/*     */ 
/* 267 */     boolean first = true;
/*     */ 
/* 269 */     s.append("{");
/*     */ 
/* 271 */     while (n-- != 0) {
/* 272 */       if (first) first = false; else
/* 273 */         s.append(", ");
/* 274 */       boolean k = i.nextBoolean();
/*     */ 
/* 278 */       s.append(String.valueOf(k));
/*     */     }
/*     */ 
/* 281 */     s.append("}");
/* 282 */     return s.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection
 * JD-Core Version:    0.6.2
 */