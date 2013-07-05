/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class AbstractObjectCollection<K> extends AbstractCollection<K>
/*     */   implements ObjectCollection<K>
/*     */ {
/*     */   public Object[] toArray()
/*     */   {
/*  55 */     Object[] a = new Object[size()];
/*  56 */     ObjectIterators.unwrap(iterator(), a);
/*  57 */     return a;
/*     */   }
/*     */ 
/*     */   public <T> T[] toArray(T[] a) {
/*  61 */     if (a.length < size()) a = (Object[])Array.newInstance(a.getClass().getComponentType(), size());
/*  62 */     ObjectIterators.unwrap(iterator(), a);
/*  63 */     return a;
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection<? extends K> c)
/*     */   {
/*  71 */     boolean retVal = false;
/*  72 */     Iterator i = c.iterator();
/*  73 */     int n = c.size();
/*  74 */     while (n-- != 0) if (add(i.next())) retVal = true;
/*  75 */     return retVal;
/*     */   }
/*     */   public boolean add(K k) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ObjectIterator<K> objectIterator() {
/*  83 */     return iterator();
/*     */   }
/*     */ 
/*     */   public abstract ObjectIterator<K> iterator();
/*     */ 
/*     */   public boolean containsAll(Collection<?> c)
/*     */   {
/*  92 */     int n = c.size();
/*  93 */     Iterator i = c.iterator();
/*  94 */     while (n-- != 0) if (!contains(i.next())) return false;
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean retainAll(Collection<?> c)
/*     */   {
/* 103 */     boolean retVal = false;
/* 104 */     int n = size();
/* 105 */     Iterator i = iterator();
/* 106 */     while (n-- != 0) {
/* 107 */       if (!c.contains(i.next())) {
/* 108 */         i.remove();
/* 109 */         retVal = true;
/*     */       }
/*     */     }
/* 112 */     return retVal;
/*     */   }
/*     */ 
/*     */   public boolean removeAll(Collection<?> c)
/*     */   {
/* 121 */     boolean retVal = false;
/* 122 */     int n = c.size();
/* 123 */     Iterator i = c.iterator();
/* 124 */     while (n-- != 0) if (remove(i.next())) retVal = true;
/* 125 */     return retVal;
/*     */   }
/*     */   public boolean isEmpty() {
/* 128 */     return size() == 0;
/*     */   }
/*     */   public String toString() {
/* 131 */     StringBuilder s = new StringBuilder();
/* 132 */     ObjectIterator i = iterator();
/* 133 */     int n = size();
/*     */ 
/* 135 */     boolean first = true;
/* 136 */     s.append("{");
/* 137 */     while (n-- != 0) {
/* 138 */       if (first) first = false; else
/* 139 */         s.append(", ");
/* 140 */       Object k = i.next();
/* 141 */       if (this == k) s.append("(this collection)"); else
/* 142 */         s.append(String.valueOf(k));
/*     */     }
/* 144 */     s.append("}");
/* 145 */     return s.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectCollection
 * JD-Core Version:    0.6.2
 */