/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.Map;
/*   6:    */import java.util.Map.Entry;
/*   7:    */import java.util.Set;
/*   8:    */
/*  60:    */public abstract class AbstractObject2ObjectMap<K, V>
/*  61:    */  extends AbstractObject2ObjectFunction<K, V>
/*  62:    */  implements Object2ObjectMap<K, V>, Serializable
/*  63:    */{
/*  64:    */  public static final long serialVersionUID = -4940583368468432370L;
/*  65:    */  
/*  66:    */  public boolean containsValue(Object v)
/*  67:    */  {
/*  68: 68 */    return values().contains(v);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public boolean containsKey(Object k) {
/*  72: 72 */    return keySet().contains(k);
/*  73:    */  }
/*  74:    */  
/*  80:    */  public void putAll(Map<? extends K, ? extends V> m)
/*  81:    */  {
/*  82: 82 */    int n = m.size();
/*  83: 83 */    Iterator<? extends Map.Entry<? extends K, ? extends V>> i = m.entrySet().iterator();
/*  84: 84 */    if ((m instanceof Object2ObjectMap))
/*  85:    */    {
/*  86: 86 */      while (n-- != 0) {
/*  87: 87 */        Object2ObjectMap.Entry<? extends K, ? extends V> e = (Object2ObjectMap.Entry)i.next();
/*  88: 88 */        put(e.getKey(), e.getValue());
/*  89:    */      }
/*  90:    */      
/*  91:    */    }
/*  92:    */    else
/*  93: 93 */      while (n-- != 0) {
/*  94: 94 */        Map.Entry<? extends K, ? extends V> e = (Map.Entry)i.next();
/*  95: 95 */        put(e.getKey(), e.getValue());
/*  96:    */      }
/*  97:    */  }
/*  98:    */  
/*  99:    */  public boolean isEmpty() {
/* 100:100 */    return size() == 0;
/* 101:    */  }
/* 102:    */  
/* 104:    */  public static class BasicEntry<K, V>
/* 105:    */    implements Object2ObjectMap.Entry<K, V>
/* 106:    */  {
/* 107:    */    protected K key;
/* 108:    */    
/* 109:    */    protected V value;
/* 110:    */    
/* 112:    */    public BasicEntry(K key, V value)
/* 113:    */    {
/* 114:114 */      this.key = key;
/* 115:115 */      this.value = value;
/* 116:    */    }
/* 117:    */    
/* 118:118 */    public K getKey() { return this.key; }
/* 119:    */    
/* 120:    */    public V getValue() {
/* 121:121 */      return this.value;
/* 122:    */    }
/* 123:    */    
/* 124:124 */    public V setValue(V value) { throw new UnsupportedOperationException(); }
/* 125:    */    
/* 126:    */    public boolean equals(Object o) {
/* 127:127 */      if (!(o instanceof Map.Entry)) return false;
/* 128:128 */      Map.Entry<?, ?> e = (Map.Entry)o;
/* 129:129 */      return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == null ? e.getValue() == null : this.value.equals(e.getValue()));
/* 130:    */    }
/* 131:    */    
/* 132:132 */    public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode()); }
/* 133:    */    
/* 134:    */    public String toString() {
/* 135:135 */      return this.key + "->" + this.value;
/* 136:    */    }
/* 137:    */  }
/* 138:    */  
/* 148:    */  public ObjectSet<K> keySet()
/* 149:    */  {
/* 150:150 */    new AbstractObjectSet() {
/* 151:151 */      public boolean contains(Object k) { return AbstractObject2ObjectMap.this.containsKey(k); }
/* 152:152 */      public int size() { return AbstractObject2ObjectMap.this.size(); }
/* 153:153 */      public void clear() { AbstractObject2ObjectMap.this.clear(); }
/* 154:    */      
/* 155:155 */      public ObjectIterator<K> iterator() { new AbstractObjectIterator() {
/* 156:156 */          final ObjectIterator<Map.Entry<K, V>> i = AbstractObject2ObjectMap.this.entrySet().iterator();
/* 157:157 */          public K next() { return ((Object2ObjectMap.Entry)this.i.next()).getKey(); }
/* 158:158 */          public boolean hasNext() { return this.i.hasNext(); }
/* 159:    */        }; }
/* 160:    */    };
/* 161:    */  }
/* 162:    */  
/* 173:    */  public ObjectCollection<V> values()
/* 174:    */  {
/* 175:175 */    new AbstractObjectCollection() {
/* 176:176 */      public boolean contains(Object k) { return AbstractObject2ObjectMap.this.containsValue(k); }
/* 177:177 */      public int size() { return AbstractObject2ObjectMap.this.size(); }
/* 178:178 */      public void clear() { AbstractObject2ObjectMap.this.clear(); }
/* 179:    */      
/* 180:180 */      public ObjectIterator<V> iterator() { new AbstractObjectIterator() {
/* 181:181 */          final ObjectIterator<Map.Entry<K, V>> i = AbstractObject2ObjectMap.this.entrySet().iterator();
/* 182:182 */          public V next() { return ((Object2ObjectMap.Entry)this.i.next()).getValue(); }
/* 183:183 */          public boolean hasNext() { return this.i.hasNext(); }
/* 184:    */        }; }
/* 185:    */    };
/* 186:    */  }
/* 187:    */  
/* 188:    */  public ObjectSet<Map.Entry<K, V>> entrySet()
/* 189:    */  {
/* 190:190 */    return object2ObjectEntrySet();
/* 191:    */  }
/* 192:    */  
/* 197:    */  public int hashCode()
/* 198:    */  {
/* 199:199 */    int h = 0;int n = size();
/* 200:200 */    ObjectIterator<? extends Map.Entry<K, V>> i = entrySet().iterator();
/* 201:201 */    while (n-- != 0) h += ((Map.Entry)i.next()).hashCode();
/* 202:202 */    return h;
/* 203:    */  }
/* 204:    */  
/* 205:205 */  public boolean equals(Object o) { if (o == this) return true;
/* 206:206 */    if (!(o instanceof Map)) return false;
/* 207:207 */    Map<?, ?> m = (Map)o;
/* 208:208 */    if (m.size() != size()) return false;
/* 209:209 */    return entrySet().containsAll(m.entrySet());
/* 210:    */  }
/* 211:    */  
/* 212:212 */  public String toString() { StringBuilder s = new StringBuilder();
/* 213:213 */    ObjectIterator<? extends Map.Entry<K, V>> i = entrySet().iterator();
/* 214:214 */    int n = size();
/* 215:    */    
/* 216:216 */    boolean first = true;
/* 217:217 */    s.append("{");
/* 218:218 */    while (n-- != 0) {
/* 219:219 */      if (first) first = false; else
/* 220:220 */        s.append(", ");
/* 221:221 */      Object2ObjectMap.Entry<K, V> e = (Object2ObjectMap.Entry)i.next();
/* 222:222 */      if (this == e.getKey()) s.append("(this map)"); else
/* 223:223 */        s.append(String.valueOf(e.getKey()));
/* 224:224 */      s.append("=>");
/* 225:225 */      if (this == e.getValue()) s.append("(this map)"); else
/* 226:226 */        s.append(String.valueOf(e.getValue()));
/* 227:    */    }
/* 228:228 */    s.append("}");
/* 229:229 */    return s.toString();
/* 230:    */  }
/* 231:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2ObjectMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */