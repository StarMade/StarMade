/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.HashCommon;
/*   4:    */import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*   5:    */import it.unimi.dsi.fastutil.floats.AbstractFloatIterator;
/*   6:    */import it.unimi.dsi.fastutil.floats.FloatCollection;
/*   7:    */import it.unimi.dsi.fastutil.floats.FloatIterator;
/*   8:    */import java.io.Serializable;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.Map;
/*  11:    */import java.util.Map.Entry;
/*  12:    */import java.util.Set;
/*  13:    */
/*  60:    */public abstract class AbstractReference2FloatMap<K>
/*  61:    */  extends AbstractReference2FloatFunction<K>
/*  62:    */  implements Reference2FloatMap<K>, Serializable
/*  63:    */{
/*  64:    */  public static final long serialVersionUID = -4940583368468432370L;
/*  65:    */  
/*  66:    */  public boolean containsValue(Object ov)
/*  67:    */  {
/*  68: 68 */    return containsValue(((Float)ov).floatValue());
/*  69:    */  }
/*  70:    */  
/*  71:    */  public boolean containsValue(float v) {
/*  72: 72 */    return values().contains(v);
/*  73:    */  }
/*  74:    */  
/*  75:    */  public boolean containsKey(Object k) {
/*  76: 76 */    return keySet().contains(k);
/*  77:    */  }
/*  78:    */  
/*  84:    */  public void putAll(Map<? extends K, ? extends Float> m)
/*  85:    */  {
/*  86: 86 */    int n = m.size();
/*  87: 87 */    Iterator<? extends Map.Entry<? extends K, ? extends Float>> i = m.entrySet().iterator();
/*  88: 88 */    if ((m instanceof Reference2FloatMap))
/*  89:    */    {
/*  90: 90 */      while (n-- != 0) {
/*  91: 91 */        Reference2FloatMap.Entry<? extends K> e = (Reference2FloatMap.Entry)i.next();
/*  92: 92 */        put(e.getKey(), e.getFloatValue());
/*  93:    */      }
/*  94:    */      
/*  95:    */    }
/*  96:    */    else
/*  97: 97 */      while (n-- != 0) {
/*  98: 98 */        Map.Entry<? extends K, ? extends Float> e = (Map.Entry)i.next();
/*  99: 99 */        put(e.getKey(), (Float)e.getValue());
/* 100:    */      }
/* 101:    */  }
/* 102:    */  
/* 103:    */  public boolean isEmpty() {
/* 104:104 */    return size() == 0;
/* 105:    */  }
/* 106:    */  
/* 108:    */  public static class BasicEntry<K>
/* 109:    */    implements Reference2FloatMap.Entry<K>
/* 110:    */  {
/* 111:    */    protected K key;
/* 112:    */    protected float value;
/* 113:    */    
/* 114:    */    public BasicEntry(K key, Float value)
/* 115:    */    {
/* 116:116 */      this.key = key;
/* 117:117 */      this.value = value.floatValue();
/* 118:    */    }
/* 119:    */    
/* 120:    */    public BasicEntry(K key, float value) {
/* 121:121 */      this.key = key;
/* 122:122 */      this.value = value;
/* 123:    */    }
/* 124:    */    
/* 126:    */    public K getKey()
/* 127:    */    {
/* 128:128 */      return this.key;
/* 129:    */    }
/* 130:    */    
/* 136:    */    public Float getValue()
/* 137:    */    {
/* 138:138 */      return Float.valueOf(this.value);
/* 139:    */    }
/* 140:    */    
/* 141:    */    public float getFloatValue()
/* 142:    */    {
/* 143:143 */      return this.value;
/* 144:    */    }
/* 145:    */    
/* 146:    */    public float setValue(float value)
/* 147:    */    {
/* 148:148 */      throw new UnsupportedOperationException();
/* 149:    */    }
/* 150:    */    
/* 152:    */    public Float setValue(Float value)
/* 153:    */    {
/* 154:154 */      return Float.valueOf(setValue(value.floatValue()));
/* 155:    */    }
/* 156:    */    
/* 158:    */    public boolean equals(Object o)
/* 159:    */    {
/* 160:160 */      if (!(o instanceof Map.Entry)) return false;
/* 161:161 */      Map.Entry<?, ?> e = (Map.Entry)o;
/* 162:    */      
/* 163:163 */      return (this.key == e.getKey()) && (this.value == ((Float)e.getValue()).floatValue());
/* 164:    */    }
/* 165:    */    
/* 166:    */    public int hashCode() {
/* 167:167 */      return (this.key == null ? 0 : System.identityHashCode(this.key)) ^ HashCommon.float2int(this.value);
/* 168:    */    }
/* 169:    */    
/* 170:    */    public String toString()
/* 171:    */    {
/* 172:172 */      return this.key + "->" + this.value;
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 189:    */  public ReferenceSet<K> keySet()
/* 190:    */  {
/* 191:191 */    new AbstractReferenceSet()
/* 192:    */    {
/* 193:193 */      public boolean contains(Object k) { return AbstractReference2FloatMap.this.containsKey(k); }
/* 194:    */      
/* 195:195 */      public int size() { return AbstractReference2FloatMap.this.size(); }
/* 196:196 */      public void clear() { AbstractReference2FloatMap.this.clear(); }
/* 197:    */      
/* 198:    */      public ObjectIterator<K> iterator() {
/* 199:199 */        new AbstractObjectIterator() {
/* 200:200 */          final ObjectIterator<Map.Entry<K, Float>> i = AbstractReference2FloatMap.this.entrySet().iterator();
/* 201:    */          
/* 202:202 */          public K next() { return ((Reference2FloatMap.Entry)this.i.next()).getKey(); }
/* 203:    */          
/* 204:204 */          public boolean hasNext() { return this.i.hasNext(); }
/* 205:    */        };
/* 206:    */      }
/* 207:    */    };
/* 208:    */  }
/* 209:    */  
/* 222:    */  public FloatCollection values()
/* 223:    */  {
/* 224:224 */    new AbstractFloatCollection()
/* 225:    */    {
/* 226:226 */      public boolean contains(float k) { return AbstractReference2FloatMap.this.containsValue(k); }
/* 227:    */      
/* 228:228 */      public int size() { return AbstractReference2FloatMap.this.size(); }
/* 229:229 */      public void clear() { AbstractReference2FloatMap.this.clear(); }
/* 230:    */      
/* 231:    */      public FloatIterator iterator() {
/* 232:232 */        new AbstractFloatIterator() {
/* 233:233 */          final ObjectIterator<Map.Entry<K, Float>> i = AbstractReference2FloatMap.this.entrySet().iterator();
/* 234:    */          
/* 235:235 */          public float nextFloat() { return ((Reference2FloatMap.Entry)this.i.next()).getFloatValue(); }
/* 236:    */          
/* 237:237 */          public boolean hasNext() { return this.i.hasNext(); }
/* 238:    */        };
/* 239:    */      }
/* 240:    */    };
/* 241:    */  }
/* 242:    */  
/* 244:    */  public ObjectSet<Map.Entry<K, Float>> entrySet()
/* 245:    */  {
/* 246:246 */    return reference2FloatEntrySet();
/* 247:    */  }
/* 248:    */  
/* 257:    */  public int hashCode()
/* 258:    */  {
/* 259:259 */    int h = 0;int n = size();
/* 260:260 */    ObjectIterator<? extends Map.Entry<K, Float>> i = entrySet().iterator();
/* 261:    */    
/* 262:262 */    while (n-- != 0) h += ((Map.Entry)i.next()).hashCode();
/* 263:263 */    return h;
/* 264:    */  }
/* 265:    */  
/* 266:    */  public boolean equals(Object o) {
/* 267:267 */    if (o == this) return true;
/* 268:268 */    if (!(o instanceof Map)) { return false;
/* 269:    */    }
/* 270:270 */    Map<?, ?> m = (Map)o;
/* 271:271 */    if (m.size() != size()) return false;
/* 272:272 */    return entrySet().containsAll(m.entrySet());
/* 273:    */  }
/* 274:    */  
/* 275:    */  public String toString()
/* 276:    */  {
/* 277:277 */    StringBuilder s = new StringBuilder();
/* 278:278 */    ObjectIterator<? extends Map.Entry<K, Float>> i = entrySet().iterator();
/* 279:279 */    int n = size();
/* 280:    */    
/* 281:281 */    boolean first = true;
/* 282:    */    
/* 283:283 */    s.append("{");
/* 284:    */    
/* 285:285 */    while (n-- != 0) {
/* 286:286 */      if (first) first = false; else {
/* 287:287 */        s.append(", ");
/* 288:    */      }
/* 289:289 */      Reference2FloatMap.Entry<K> e = (Reference2FloatMap.Entry)i.next();
/* 290:    */      
/* 292:292 */      if (this == e.getKey()) { s.append("(this map)");
/* 293:    */      } else
/* 294:294 */        s.append(String.valueOf(e.getKey()));
/* 295:295 */      s.append("=>");
/* 296:    */      
/* 299:299 */      s.append(String.valueOf(e.getFloatValue()));
/* 300:    */    }
/* 301:    */    
/* 302:302 */    s.append("}");
/* 303:303 */    return s.toString();
/* 304:    */  }
/* 305:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2FloatMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */