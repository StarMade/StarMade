/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Collection;
/*   5:    */
/*  54:    */public class ReferenceCollections
/*  55:    */{
/*  56:    */  public static abstract class EmptyCollection<K>
/*  57:    */    extends AbstractReferenceCollection<K>
/*  58:    */  {
/*  59: 59 */    public boolean add(K k) { throw new UnsupportedOperationException(); }
/*  60: 60 */    public boolean contains(Object k) { return false; }
/*  61: 61 */    public Object[] toArray() { return ObjectArrays.EMPTY_ARRAY; }
/*  62: 62 */    public boolean remove(Object k) { throw new UnsupportedOperationException(); }
/*  63: 63 */    public <T> T[] toArray(T[] a) { return a; }
/*  64:    */    
/*  67: 67 */    public ObjectBidirectionalIterator<K> iterator() { return ObjectIterators.EMPTY_ITERATOR; }
/*  68:    */    
/*  69: 69 */    public int size() { return 0; }
/*  70:    */    
/*  71:    */    public void clear() {}
/*  72: 72 */    public int hashCode() { return 0; }
/*  73:    */    
/*  74: 74 */    public boolean equals(Object o) { if (o == this) return true;
/*  75: 75 */      if (!(o instanceof Collection)) return false;
/*  76: 76 */      return ((Collection)o).isEmpty();
/*  77:    */    }
/*  78:    */  }
/*  79:    */  
/*  81:    */  public static class SynchronizedCollection<K>
/*  82:    */    implements ReferenceCollection<K>, Serializable
/*  83:    */  {
/*  84:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  85:    */    
/*  86:    */    protected final ReferenceCollection<K> collection;
/*  87:    */    protected final Object sync;
/*  88:    */    
/*  89:    */    protected SynchronizedCollection(ReferenceCollection<K> c, Object sync)
/*  90:    */    {
/*  91: 91 */      if (c == null) throw new NullPointerException();
/*  92: 92 */      this.collection = c;
/*  93: 93 */      this.sync = sync;
/*  94:    */    }
/*  95:    */    
/*  96:    */    protected SynchronizedCollection(ReferenceCollection<K> c) {
/*  97: 97 */      if (c == null) throw new NullPointerException();
/*  98: 98 */      this.collection = c;
/*  99: 99 */      this.sync = this;
/* 100:    */    }
/* 101:    */    
/* 102:102 */    public int size() { synchronized (this.sync) { return this.collection.size(); } }
/* 103:103 */    public boolean isEmpty() { synchronized (this.sync) { return this.collection.isEmpty(); } }
/* 104:104 */    public boolean contains(Object o) { synchronized (this.sync) { return this.collection.contains(o); } }
/* 105:    */    
/* 106:106 */    public Object[] toArray() { synchronized (this.sync) { return this.collection.toArray(); } }
/* 107:107 */    public <T> T[] toArray(T[] a) { synchronized (this.sync) { return this.collection.toArray(a); } }
/* 108:108 */    public ObjectIterator<K> iterator() { return this.collection.iterator(); }
/* 109:    */    @Deprecated
/* 110:110 */    public ObjectIterator<K> objectIterator() { return iterator(); }
/* 111:111 */    public boolean add(K k) { synchronized (this.sync) { return this.collection.add(k); } }
/* 112:112 */    public boolean rem(Object k) { synchronized (this.sync) { return this.collection.remove(k); } }
/* 113:113 */    public boolean remove(Object ok) { synchronized (this.sync) { return this.collection.remove(ok); } }
/* 114:114 */    public boolean addAll(Collection<? extends K> c) { synchronized (this.sync) { return this.collection.addAll(c); } }
/* 115:115 */    public boolean containsAll(Collection<?> c) { synchronized (this.sync) { return this.collection.containsAll(c); } }
/* 116:116 */    public boolean removeAll(Collection<?> c) { synchronized (this.sync) { return this.collection.removeAll(c); } }
/* 117:117 */    public boolean retainAll(Collection<?> c) { synchronized (this.sync) { return this.collection.retainAll(c); } }
/* 118:118 */    public void clear() { synchronized (this.sync) { this.collection.clear(); } }
/* 119:119 */    public String toString() { synchronized (this.sync) { return this.collection.toString();
/* 120:    */      }
/* 121:    */    }
/* 122:    */  }
/* 123:    */  
/* 125:    */  public static <K> ReferenceCollection<K> synchronize(ReferenceCollection<K> c)
/* 126:    */  {
/* 127:127 */    return new SynchronizedCollection(c);
/* 128:    */  }
/* 129:    */  
/* 135:135 */  public static <K> ReferenceCollection<K> synchronize(ReferenceCollection<K> c, Object sync) { return new SynchronizedCollection(c, sync); }
/* 136:    */  
/* 137:    */  public static class UnmodifiableCollection<K> implements ReferenceCollection<K>, Serializable {
/* 138:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 139:    */    protected final ReferenceCollection<K> collection;
/* 140:    */    
/* 141:141 */    protected UnmodifiableCollection(ReferenceCollection<K> c) { if (c == null) throw new NullPointerException();
/* 142:142 */      this.collection = c; }
/* 143:    */    
/* 144:144 */    public int size() { return this.collection.size(); }
/* 145:145 */    public boolean isEmpty() { return this.collection.isEmpty(); }
/* 146:146 */    public boolean contains(Object o) { return this.collection.contains(o); }
/* 147:147 */    public ObjectIterator<K> iterator() { return ObjectIterators.unmodifiable(this.collection.iterator()); }
/* 148:    */    @Deprecated
/* 149:149 */    public ObjectIterator<K> objectIterator() { return iterator(); }
/* 150:150 */    public boolean add(K k) { throw new UnsupportedOperationException(); }
/* 151:151 */    public boolean remove(Object ok) { throw new UnsupportedOperationException(); }
/* 152:152 */    public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); }
/* 153:153 */    public boolean containsAll(Collection<?> c) { return this.collection.containsAll(c); }
/* 154:154 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/* 155:155 */    public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/* 156:156 */    public void clear() { throw new UnsupportedOperationException(); }
/* 157:157 */    public String toString() { return this.collection.toString(); }
/* 158:158 */    public <T> T[] toArray(T[] a) { return this.collection.toArray(a); }
/* 159:159 */    public Object[] toArray() { return this.collection.toArray(); }
/* 160:    */  }
/* 161:    */  
/* 167:167 */  public static <K> ReferenceCollection<K> unmodifiable(ReferenceCollection<K> c) { return new UnmodifiableCollection(c); }
/* 168:    */  
/* 169:    */  public static class IterableCollection<K> extends AbstractReferenceCollection<K> implements Serializable {
/* 170:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 171:    */    protected final ObjectIterable<K> iterable;
/* 172:    */    
/* 173:173 */    protected IterableCollection(ObjectIterable<K> iterable) { if (iterable == null) throw new NullPointerException();
/* 174:174 */      this.iterable = iterable;
/* 175:    */    }
/* 176:    */    
/* 177:177 */    public int size() { int c = 0;
/* 178:178 */      ObjectIterator<K> iterator = iterator();
/* 179:179 */      while (iterator.hasNext()) {
/* 180:180 */        iterator.next();
/* 181:181 */        c++;
/* 182:    */      }
/* 183:183 */      return c; }
/* 184:    */    
/* 185:185 */    public boolean isEmpty() { return this.iterable.iterator().hasNext(); }
/* 186:186 */    public ObjectIterator<K> iterator() { return this.iterable.iterator(); }
/* 187:    */    @Deprecated
/* 188:188 */    public ObjectIterator<K> objectIterator() { return iterator(); }
/* 189:    */  }
/* 190:    */  
/* 195:    */  public static <K> ReferenceCollection<K> asCollection(ObjectIterable<K> iterable)
/* 196:    */  {
/* 197:197 */    if ((iterable instanceof ReferenceCollection)) return (ReferenceCollection)iterable;
/* 198:198 */    return new IterableCollection(iterable);
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceCollections
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */