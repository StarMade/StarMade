/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */
/*  51:    */public class Reference2ReferenceFunctions
/*  52:    */{
/*  53:    */  public static class EmptyFunction<K, V>
/*  54:    */    extends AbstractReference2ReferenceFunction<K, V>
/*  55:    */    implements Serializable, Cloneable
/*  56:    */  {
/*  57:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  58:    */    
/*  59: 59 */    public V get(Object k) { return null; }
/*  60: 60 */    public boolean containsKey(Object k) { return false; }
/*  61: 61 */    public V defaultReturnValue() { return null; }
/*  62: 62 */    public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); }
/*  63: 63 */    public int size() { return 0; }
/*  64:    */    public void clear() {}
/*  65: 65 */    private Object readResolve() { return Reference2ReferenceFunctions.EMPTY_FUNCTION; }
/*  66: 66 */    public Object clone() { return Reference2ReferenceFunctions.EMPTY_FUNCTION; }
/*  67:    */  }
/*  68:    */  
/*  70: 70 */  public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();
/*  71:    */  
/*  73:    */  public static class Singleton<K, V>
/*  74:    */    extends AbstractReference2ReferenceFunction<K, V>
/*  75:    */    implements Serializable, Cloneable
/*  76:    */  {
/*  77:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  78:    */    
/*  79:    */    protected final K key;
/*  80:    */    
/*  81:    */    protected final V value;
/*  82:    */    
/*  84:    */    protected Singleton(K key, V value)
/*  85:    */    {
/*  86: 86 */      this.key = key;
/*  87: 87 */      this.value = value;
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    public boolean containsKey(Object k) { return this.key == k; }
/*  91:    */    
/*  92: 92 */    public V get(Object k) { if (this.key == k) return this.value; return this.defRetValue; }
/*  93:    */    
/*  94: 94 */    public int size() { return 1; }
/*  95:    */    
/*  96: 96 */    public Object clone() { return this; }
/*  97:    */  }
/*  98:    */  
/* 109:109 */  public static <K, V> Reference2ReferenceFunction<K, V> singleton(K key, V value) { return new Singleton(key, value); }
/* 110:    */  
/* 111:    */  public static class SynchronizedFunction<K, V> extends AbstractReference2ReferenceFunction<K, V> implements Serializable {
/* 112:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 113:    */    protected final Reference2ReferenceFunction<K, V> function;
/* 114:    */    protected final Object sync;
/* 115:    */    
/* 116:    */    protected SynchronizedFunction(Reference2ReferenceFunction<K, V> f, Object sync) {
/* 117:117 */      if (f == null) throw new NullPointerException();
/* 118:118 */      this.function = f;
/* 119:119 */      this.sync = sync;
/* 120:    */    }
/* 121:    */    
/* 122:122 */    protected SynchronizedFunction(Reference2ReferenceFunction<K, V> f) { if (f == null) throw new NullPointerException();
/* 123:123 */      this.function = f;
/* 124:124 */      this.sync = this; }
/* 125:    */    
/* 126:126 */    public int size() { synchronized (this.sync) { return this.function.size(); } }
/* 127:127 */    public boolean containsKey(Object k) { synchronized (this.sync) { return this.function.containsKey(k); } }
/* 128:128 */    public V defaultReturnValue() { synchronized (this.sync) { return this.function.defaultReturnValue(); } }
/* 129:129 */    public void defaultReturnValue(V defRetValue) { synchronized (this.sync) { this.function.defaultReturnValue(defRetValue); } }
/* 130:130 */    public V put(K k, V v) { synchronized (this.sync) { return this.function.put(k, v); } }
/* 131:131 */    public void clear() { synchronized (this.sync) { this.function.clear(); } }
/* 132:132 */    public String toString() { synchronized (this.sync) { return this.function.toString(); } }
/* 133:133 */    public V remove(Object k) { synchronized (this.sync) { return this.function.remove(k); } }
/* 134:134 */    public V get(Object k) { synchronized (this.sync) { return this.function.get(k);
/* 135:    */      }
/* 136:    */    }
/* 137:    */  }
/* 138:    */  
/* 140:    */  public static <K, V> Reference2ReferenceFunction<K, V> synchronize(Reference2ReferenceFunction<K, V> f)
/* 141:    */  {
/* 142:142 */    return new SynchronizedFunction(f);
/* 143:    */  }
/* 144:    */  
/* 150:150 */  public static <K, V> Reference2ReferenceFunction<K, V> synchronize(Reference2ReferenceFunction<K, V> f, Object sync) { return new SynchronizedFunction(f, sync); }
/* 151:    */  
/* 152:    */  public static class UnmodifiableFunction<K, V> extends AbstractReference2ReferenceFunction<K, V> implements Serializable {
/* 153:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 154:    */    protected final Reference2ReferenceFunction<K, V> function;
/* 155:    */    
/* 156:156 */    protected UnmodifiableFunction(Reference2ReferenceFunction<K, V> f) { if (f == null) throw new NullPointerException();
/* 157:157 */      this.function = f; }
/* 158:    */    
/* 159:159 */    public int size() { return this.function.size(); }
/* 160:160 */    public boolean containsKey(Object k) { return this.function.containsKey(k); }
/* 161:161 */    public V defaultReturnValue() { return this.function.defaultReturnValue(); }
/* 162:162 */    public void defaultReturnValue(V defRetValue) { throw new UnsupportedOperationException(); }
/* 163:163 */    public V put(K k, V v) { throw new UnsupportedOperationException(); }
/* 164:164 */    public void clear() { throw new UnsupportedOperationException(); }
/* 165:165 */    public String toString() { return this.function.toString(); }
/* 166:166 */    public V remove(Object k) { throw new UnsupportedOperationException(); }
/* 167:167 */    public V get(Object k) { return this.function.get(k); }
/* 168:    */  }
/* 169:    */  
/* 173:    */  public static <K, V> Reference2ReferenceFunction<K, V> unmodifiable(Reference2ReferenceFunction<K, V> f)
/* 174:    */  {
/* 175:175 */    return new UnmodifiableFunction(f);
/* 176:    */  }
/* 177:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ReferenceFunctions
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */