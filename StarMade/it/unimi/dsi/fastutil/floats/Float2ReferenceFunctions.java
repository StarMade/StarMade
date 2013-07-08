/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  52:    */public class Float2ReferenceFunctions
/*  53:    */{
/*  54:    */  public static class EmptyFunction<V>
/*  55:    */    extends AbstractFloat2ReferenceFunction<V>
/*  56:    */    implements Serializable, Cloneable
/*  57:    */  {
/*  58:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  59:    */    
/*  60: 60 */    public V get(float k) { return null; }
/*  61: 61 */    public boolean containsKey(float k) { return false; }
/*  62: 62 */    public V defaultReturnValue() { return null; }
/*  63: 63 */    public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); }
/*  64: 64 */    public V get(Object k) { return null; }
/*  65: 65 */    public int size() { return 0; }
/*  66:    */    public void clear() {}
/*  67: 67 */    private Object readResolve() { return Float2ReferenceFunctions.EMPTY_FUNCTION; }
/*  68: 68 */    public Object clone() { return Float2ReferenceFunctions.EMPTY_FUNCTION; }
/*  69:    */  }
/*  70:    */  
/*  72: 72 */  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
/*  73:    */  
/*  75:    */  public static class Singleton<V>
/*  76:    */    extends AbstractFloat2ReferenceFunction<V>
/*  77:    */    implements Serializable, Cloneable
/*  78:    */  {
/*  79:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  80:    */    
/*  81:    */    protected final float key;
/*  82:    */    protected final V value;
/*  83:    */    
/*  84:    */    protected Singleton(float key, V value)
/*  85:    */    {
/*  86: 86 */      this.key = key;
/*  87: 87 */      this.value = value;
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    public boolean containsKey(float k) { return this.key == k; }
/*  91:    */    
/*  92: 92 */    public V get(float k) { if (this.key == k) return this.value; return this.defRetValue; }
/*  93:    */    
/*  94: 94 */    public int size() { return 1; }
/*  95:    */    
/*  96: 96 */    public Object clone() { return this; }
/*  97:    */  }
/*  98:    */  
/* 107:    */  public static <V> Float2ReferenceFunction<V> singleton(float key, V value)
/* 108:    */  {
/* 109:109 */    return new Singleton(key, value);
/* 110:    */  }
/* 111:    */  
/* 122:    */  public static <V> Float2ReferenceFunction<V> singleton(Float key, V value)
/* 123:    */  {
/* 124:124 */    return new Singleton(key.floatValue(), value);
/* 125:    */  }
/* 126:    */  
/* 128:    */  public static class SynchronizedFunction<V>
/* 129:    */    extends AbstractFloat2ReferenceFunction<V>
/* 130:    */    implements Serializable
/* 131:    */  {
/* 132:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 133:    */    
/* 134:    */    protected final Float2ReferenceFunction<V> function;
/* 135:    */    
/* 136:    */    protected final Object sync;
/* 137:    */    
/* 138:    */    protected SynchronizedFunction(Float2ReferenceFunction<V> f, Object sync)
/* 139:    */    {
/* 140:140 */      if (f == null) throw new NullPointerException();
/* 141:141 */      this.function = f;
/* 142:142 */      this.sync = sync;
/* 143:    */    }
/* 144:    */    
/* 145:    */    protected SynchronizedFunction(Float2ReferenceFunction<V> f) {
/* 146:146 */      if (f == null) throw new NullPointerException();
/* 147:147 */      this.function = f;
/* 148:148 */      this.sync = this;
/* 149:    */    }
/* 150:    */    
/* 151:151 */    public int size() { synchronized (this.sync) { return this.function.size(); } }
/* 152:152 */    public boolean containsKey(float k) { synchronized (this.sync) { return this.function.containsKey(k); } }
/* 153:    */    
/* 154:154 */    public V defaultReturnValue() { synchronized (this.sync) { return this.function.defaultReturnValue(); } }
/* 155:155 */    public void defaultReturnValue(V defRetValue) { synchronized (this.sync) { this.function.defaultReturnValue(defRetValue); } }
/* 156:    */    
/* 157:157 */    public V put(float k, V v) { synchronized (this.sync) { return this.function.put(k, v); } }
/* 158:    */    
/* 159:159 */    public void clear() { synchronized (this.sync) { this.function.clear(); } }
/* 160:160 */    public String toString() { synchronized (this.sync) { return this.function.toString();
/* 161:    */      } }
/* 162:    */    
/* 163:163 */    public V put(Float k, V v) { synchronized (this.sync) { return this.function.put(k, v); } }
/* 164:164 */    public V get(Object k) { synchronized (this.sync) { return this.function.get(k); } }
/* 165:165 */    public V remove(Object k) { synchronized (this.sync) { return this.function.remove(k);
/* 166:    */      }
/* 167:    */    }
/* 168:    */    
/* 169:169 */    public V remove(float k) { synchronized (this.sync) { return this.function.remove(k); } }
/* 170:170 */    public V get(float k) { synchronized (this.sync) { return this.function.get(k); } }
/* 171:171 */    public boolean containsKey(Object ok) { synchronized (this.sync) { return this.function.containsKey(ok);
/* 172:    */      }
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 185:    */  public static <V> Float2ReferenceFunction<V> synchronize(Float2ReferenceFunction<V> f)
/* 186:    */  {
/* 187:187 */    return new SynchronizedFunction(f);
/* 188:    */  }
/* 189:    */  
/* 195:    */  public static <V> Float2ReferenceFunction<V> synchronize(Float2ReferenceFunction<V> f, Object sync)
/* 196:    */  {
/* 197:197 */    return new SynchronizedFunction(f, sync);
/* 198:    */  }
/* 199:    */  
/* 201:    */  public static class UnmodifiableFunction<V>
/* 202:    */    extends AbstractFloat2ReferenceFunction<V>
/* 203:    */    implements Serializable
/* 204:    */  {
/* 205:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 206:    */    protected final Float2ReferenceFunction<V> function;
/* 207:    */    
/* 208:    */    protected UnmodifiableFunction(Float2ReferenceFunction<V> f)
/* 209:    */    {
/* 210:210 */      if (f == null) throw new NullPointerException();
/* 211:211 */      this.function = f;
/* 212:    */    }
/* 213:    */    
/* 214:214 */    public int size() { return this.function.size(); }
/* 215:215 */    public boolean containsKey(float k) { return this.function.containsKey(k); }
/* 216:    */    
/* 217:217 */    public V defaultReturnValue() { return this.function.defaultReturnValue(); }
/* 218:218 */    public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); }
/* 219:    */    
/* 220:220 */    public V put(float k, V v) { throw new UnsupportedOperationException(); }
/* 221:    */    
/* 222:222 */    public void clear() { throw new UnsupportedOperationException(); }
/* 223:223 */    public String toString() { return this.function.toString(); }
/* 224:    */    
/* 226:226 */    public V remove(float k) { throw new UnsupportedOperationException(); }
/* 227:227 */    public V get(float k) { return this.function.get(k); }
/* 228:228 */    public boolean containsKey(Object ok) { return this.function.containsKey(ok); }
/* 229:    */    
/* 232:232 */    public V remove(Object k) { throw new UnsupportedOperationException(); }
/* 233:233 */    public V get(Object k) { return this.function.get(k); }
/* 234:    */  }
/* 235:    */  
/* 242:    */  public static <V> Float2ReferenceFunction<V> unmodifiable(Float2ReferenceFunction<V> f)
/* 243:    */  {
/* 244:244 */    return new UnmodifiableFunction(f);
/* 245:    */  }
/* 246:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ReferenceFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */