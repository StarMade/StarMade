/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  52:    */public class Reference2IntFunctions
/*  53:    */{
/*  54:    */  public static class EmptyFunction<K>
/*  55:    */    extends AbstractReference2IntFunction<K>
/*  56:    */    implements Serializable, Cloneable
/*  57:    */  {
/*  58:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  59:    */    
/*  60: 60 */    public int getInt(Object k) { return 0; }
/*  61: 61 */    public boolean containsKey(Object k) { return false; }
/*  62: 62 */    public int defaultReturnValue() { return 0; }
/*  63: 63 */    public void defaultReturnValue(int defRetValue) { throw new UnsupportedOperationException(); }
/*  64: 64 */    public int size() { return 0; }
/*  65:    */    public void clear() {}
/*  66: 66 */    private Object readResolve() { return Reference2IntFunctions.EMPTY_FUNCTION; }
/*  67: 67 */    public Object clone() { return Reference2IntFunctions.EMPTY_FUNCTION; }
/*  68:    */  }
/*  69:    */  
/*  71: 71 */  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
/*  72:    */  
/*  74:    */  public static class Singleton<K>
/*  75:    */    extends AbstractReference2IntFunction<K>
/*  76:    */    implements Serializable, Cloneable
/*  77:    */  {
/*  78:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  79:    */    
/*  80:    */    protected final K key;
/*  81:    */    
/*  82:    */    protected final int value;
/*  83:    */    
/*  84:    */    protected Singleton(K key, int value)
/*  85:    */    {
/*  86: 86 */      this.key = key;
/*  87: 87 */      this.value = value;
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    public boolean containsKey(Object k) { return this.key == k; }
/*  91:    */    
/*  92: 92 */    public int getInt(Object k) { if (this.key == k) return this.value; return this.defRetValue; }
/*  93:    */    
/*  94: 94 */    public int size() { return 1; }
/*  95:    */    
/*  96: 96 */    public Object clone() { return this; }
/*  97:    */  }
/*  98:    */  
/* 107:    */  public static <K> Reference2IntFunction<K> singleton(K key, int value)
/* 108:    */  {
/* 109:109 */    return new Singleton(key, value);
/* 110:    */  }
/* 111:    */  
/* 122:    */  public static <K> Reference2IntFunction<K> singleton(K key, Integer value)
/* 123:    */  {
/* 124:124 */    return new Singleton(key, value.intValue());
/* 125:    */  }
/* 126:    */  
/* 128:    */  public static class SynchronizedFunction<K>
/* 129:    */    extends AbstractReference2IntFunction<K>
/* 130:    */    implements Serializable
/* 131:    */  {
/* 132:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 133:    */    
/* 134:    */    protected final Reference2IntFunction<K> function;
/* 135:    */    
/* 136:    */    protected final Object sync;
/* 137:    */    
/* 138:    */    protected SynchronizedFunction(Reference2IntFunction<K> f, Object sync)
/* 139:    */    {
/* 140:140 */      if (f == null) throw new NullPointerException();
/* 141:141 */      this.function = f;
/* 142:142 */      this.sync = sync;
/* 143:    */    }
/* 144:    */    
/* 145:    */    protected SynchronizedFunction(Reference2IntFunction<K> f) {
/* 146:146 */      if (f == null) throw new NullPointerException();
/* 147:147 */      this.function = f;
/* 148:148 */      this.sync = this;
/* 149:    */    }
/* 150:    */    
/* 151:151 */    public int size() { synchronized (this.sync) { return this.function.size(); } }
/* 152:152 */    public boolean containsKey(Object k) { synchronized (this.sync) { return this.function.containsKey(k); } }
/* 153:    */    
/* 154:154 */    public int defaultReturnValue() { synchronized (this.sync) { return this.function.defaultReturnValue(); } }
/* 155:155 */    public void defaultReturnValue(int defRetValue) { synchronized (this.sync) { this.function.defaultReturnValue(defRetValue); } }
/* 156:    */    
/* 157:157 */    public int put(K k, int v) { synchronized (this.sync) { return this.function.put(k, v); } }
/* 158:    */    
/* 159:159 */    public void clear() { synchronized (this.sync) { this.function.clear(); } }
/* 160:160 */    public String toString() { synchronized (this.sync) { return this.function.toString();
/* 161:    */      } }
/* 162:    */    
/* 163:163 */    public Integer put(K k, Integer v) { synchronized (this.sync) { return (Integer)this.function.put(k, v); } }
/* 164:164 */    public Integer get(Object k) { synchronized (this.sync) { return (Integer)this.function.get(k); } }
/* 165:165 */    public Integer remove(Object k) { synchronized (this.sync) { return (Integer)this.function.remove(k); } }
/* 166:166 */    public int removeInt(Object k) { synchronized (this.sync) { return this.function.removeInt(k); } }
/* 167:167 */    public int getInt(Object k) { synchronized (this.sync) { return this.function.getInt(k);
/* 168:    */      }
/* 169:    */    }
/* 170:    */  }
/* 171:    */  
/* 173:    */  public static <K> Reference2IntFunction<K> synchronize(Reference2IntFunction<K> f)
/* 174:    */  {
/* 175:175 */    return new SynchronizedFunction(f);
/* 176:    */  }
/* 177:    */  
/* 183:183 */  public static <K> Reference2IntFunction<K> synchronize(Reference2IntFunction<K> f, Object sync) { return new SynchronizedFunction(f, sync); }
/* 184:    */  
/* 185:    */  public static class UnmodifiableFunction<K> extends AbstractReference2IntFunction<K> implements Serializable {
/* 186:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 187:    */    protected final Reference2IntFunction<K> function;
/* 188:    */    
/* 189:189 */    protected UnmodifiableFunction(Reference2IntFunction<K> f) { if (f == null) throw new NullPointerException();
/* 190:190 */      this.function = f; }
/* 191:    */    
/* 192:192 */    public int size() { return this.function.size(); }
/* 193:193 */    public boolean containsKey(Object k) { return this.function.containsKey(k); }
/* 194:194 */    public int defaultReturnValue() { return this.function.defaultReturnValue(); }
/* 195:195 */    public void defaultReturnValue(int defRetValue) { throw new UnsupportedOperationException(); }
/* 196:196 */    public int put(K k, int v) { throw new UnsupportedOperationException(); }
/* 197:197 */    public void clear() { throw new UnsupportedOperationException(); }
/* 198:198 */    public String toString() { return this.function.toString(); }
/* 199:199 */    public int removeInt(Object k) { throw new UnsupportedOperationException(); }
/* 200:200 */    public int getInt(Object k) { return this.function.getInt(k); }
/* 201:    */  }
/* 202:    */  
/* 206:    */  public static <K> Reference2IntFunction<K> unmodifiable(Reference2IntFunction<K> f)
/* 207:    */  {
/* 208:208 */    return new UnmodifiableFunction(f);
/* 209:    */  }
/* 210:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2IntFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */