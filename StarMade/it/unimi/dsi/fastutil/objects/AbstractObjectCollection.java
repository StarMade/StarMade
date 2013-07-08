/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.lang.reflect.Array;
/*   4:    */import java.util.AbstractCollection;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.Iterator;
/*   7:    */
/*  49:    */public abstract class AbstractObjectCollection<K>
/*  50:    */  extends AbstractCollection<K>
/*  51:    */  implements ObjectCollection<K>
/*  52:    */{
/*  53:    */  public Object[] toArray()
/*  54:    */  {
/*  55: 55 */    Object[] a = new Object[size()];
/*  56: 56 */    ObjectIterators.unwrap(iterator(), a);
/*  57: 57 */    return a;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public <T> T[] toArray(T[] a) {
/*  61: 61 */    if (a.length < size()) a = (Object[])Array.newInstance(a.getClass().getComponentType(), size());
/*  62: 62 */    ObjectIterators.unwrap(iterator(), a);
/*  63: 63 */    return a;
/*  64:    */  }
/*  65:    */  
/*  69:    */  public boolean addAll(Collection<? extends K> c)
/*  70:    */  {
/*  71: 71 */    boolean retVal = false;
/*  72: 72 */    Iterator<? extends K> i = c.iterator();
/*  73: 73 */    int n = c.size();
/*  74: 74 */    while (n-- != 0) if (add(i.next())) retVal = true;
/*  75: 75 */    return retVal;
/*  76:    */  }
/*  77:    */  
/*  78: 78 */  public boolean add(K k) { throw new UnsupportedOperationException(); }
/*  79:    */  
/*  80:    */  @Deprecated
/*  81:    */  public ObjectIterator<K> objectIterator()
/*  82:    */  {
/*  83: 83 */    return iterator();
/*  84:    */  }
/*  85:    */  
/*  87:    */  public abstract ObjectIterator<K> iterator();
/*  88:    */  
/*  90:    */  public boolean containsAll(Collection<?> c)
/*  91:    */  {
/*  92: 92 */    int n = c.size();
/*  93: 93 */    Iterator<?> i = c.iterator();
/*  94: 94 */    while (n-- != 0) if (!contains(i.next())) return false;
/*  95: 95 */    return true;
/*  96:    */  }
/*  97:    */  
/* 101:    */  public boolean retainAll(Collection<?> c)
/* 102:    */  {
/* 103:103 */    boolean retVal = false;
/* 104:104 */    int n = size();
/* 105:105 */    Iterator<?> i = iterator();
/* 106:106 */    while (n-- != 0) {
/* 107:107 */      if (!c.contains(i.next())) {
/* 108:108 */        i.remove();
/* 109:109 */        retVal = true;
/* 110:    */      }
/* 111:    */    }
/* 112:112 */    return retVal;
/* 113:    */  }
/* 114:    */  
/* 119:    */  public boolean removeAll(Collection<?> c)
/* 120:    */  {
/* 121:121 */    boolean retVal = false;
/* 122:122 */    int n = c.size();
/* 123:123 */    Iterator<?> i = c.iterator();
/* 124:124 */    while (n-- != 0) if (remove(i.next())) retVal = true;
/* 125:125 */    return retVal;
/* 126:    */  }
/* 127:    */  
/* 128:128 */  public boolean isEmpty() { return size() == 0; }
/* 129:    */  
/* 130:    */  public String toString() {
/* 131:131 */    StringBuilder s = new StringBuilder();
/* 132:132 */    ObjectIterator<K> i = iterator();
/* 133:133 */    int n = size();
/* 134:    */    
/* 135:135 */    boolean first = true;
/* 136:136 */    s.append("{");
/* 137:137 */    while (n-- != 0) {
/* 138:138 */      if (first) first = false; else
/* 139:139 */        s.append(", ");
/* 140:140 */      Object k = i.next();
/* 141:141 */      if (this == k) s.append("(this collection)"); else
/* 142:142 */        s.append(String.valueOf(k));
/* 143:    */    }
/* 144:144 */    s.append("}");
/* 145:145 */    return s.toString();
/* 146:    */  }
/* 147:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */