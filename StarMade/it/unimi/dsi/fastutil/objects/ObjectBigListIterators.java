/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  52:    */public class ObjectBigListIterators
/*  53:    */{
/*  54:    */  public static class EmptyBigListIterator<K>
/*  55:    */    extends AbstractObjectBigListIterator<K>
/*  56:    */    implements Serializable, Cloneable
/*  57:    */  {
/*  58:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  59:    */    
/*  60: 60 */    public boolean hasNext() { return false; }
/*  61: 61 */    public boolean hasPrevious() { return false; }
/*  62: 62 */    public K next() { throw new NoSuchElementException(); }
/*  63: 63 */    public K previous() { throw new NoSuchElementException(); }
/*  64: 64 */    public long nextIndex() { return 0L; }
/*  65: 65 */    public long previousIndex() { return -1L; }
/*  66: 66 */    public long skip(long n) { return 0L; }
/*  67: 67 */    public long back(long n) { return 0L; }
/*  68: 68 */    public Object clone() { return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*  69: 69 */    private Object readResolve() { return ObjectBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*  70:    */  }
/*  71:    */  
/*  77: 77 */  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
/*  78:    */  
/*  79:    */  private static class SingletonBigListIterator<K> extends AbstractObjectBigListIterator<K> {
/*  80:    */    private final K element;
/*  81:    */    private int curr;
/*  82:    */    
/*  83: 83 */    public SingletonBigListIterator(K element) { this.element = element; }
/*  84:    */    
/*  85: 85 */    public boolean hasNext() { return this.curr == 0; }
/*  86: 86 */    public boolean hasPrevious() { return this.curr == 1; }
/*  87:    */    
/*  88: 88 */    public K next() { if (!hasNext()) throw new NoSuchElementException();
/*  89: 89 */      this.curr = 1;
/*  90: 90 */      return this.element;
/*  91:    */    }
/*  92:    */    
/*  93: 93 */    public K previous() { if (!hasPrevious()) throw new NoSuchElementException();
/*  94: 94 */      this.curr = 0;
/*  95: 95 */      return this.element;
/*  96:    */    }
/*  97:    */    
/*  98: 98 */    public long nextIndex() { return this.curr; }
/*  99:    */    
/* 100:    */    public long previousIndex() {
/* 101:101 */      return this.curr - 1;
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 108:    */  public static <K> ObjectBigListIterator<K> singleton(K element)
/* 109:    */  {
/* 110:110 */    return new SingletonBigListIterator(element);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public static class UnmodifiableBigListIterator<K>
/* 114:    */    extends AbstractObjectBigListIterator<K>
/* 115:    */  {
/* 116:    */    protected final ObjectBigListIterator<K> i;
/* 117:    */    
/* 118:    */    public UnmodifiableBigListIterator(ObjectBigListIterator<K> i)
/* 119:    */    {
/* 120:120 */      this.i = i;
/* 121:    */    }
/* 122:    */    
/* 123:123 */    public boolean hasNext() { return this.i.hasNext(); }
/* 124:124 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 125:125 */    public K next() { return this.i.next(); }
/* 126:126 */    public K previous() { return this.i.previous(); }
/* 127:127 */    public long nextIndex() { return this.i.nextIndex(); }
/* 128:128 */    public long previousIndex() { return this.i.previousIndex(); }
/* 129:    */  }
/* 130:    */  
/* 138:    */  public static <K> ObjectBigListIterator<K> unmodifiable(ObjectBigListIterator<K> i)
/* 139:    */  {
/* 140:140 */    return new UnmodifiableBigListIterator(i);
/* 141:    */  }
/* 142:    */  
/* 143:    */  public static class BigListIteratorListIterator<K> extends AbstractObjectBigListIterator<K>
/* 144:    */  {
/* 145:    */    protected final ObjectListIterator<K> i;
/* 146:    */    
/* 147:    */    protected BigListIteratorListIterator(ObjectListIterator<K> i)
/* 148:    */    {
/* 149:149 */      this.i = i;
/* 150:    */    }
/* 151:    */    
/* 152:    */    private int intDisplacement(long n) {
/* 153:153 */      if ((n < -2147483648L) || (n > 2147483647L)) throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
/* 154:154 */      return (int)n;
/* 155:    */    }
/* 156:    */    
/* 157:157 */    public void set(K ok) { this.i.set(ok); }
/* 158:158 */    public void add(K ok) { this.i.add(ok); }
/* 159:159 */    public int back(int n) { return this.i.back(n); }
/* 160:160 */    public long back(long n) { return this.i.back(intDisplacement(n)); }
/* 161:161 */    public void remove() { this.i.remove(); }
/* 162:162 */    public int skip(int n) { return this.i.skip(n); }
/* 163:163 */    public long skip(long n) { return this.i.skip(intDisplacement(n)); }
/* 164:164 */    public boolean hasNext() { return this.i.hasNext(); }
/* 165:165 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 166:166 */    public K next() { return this.i.next(); }
/* 167:167 */    public K previous() { return this.i.previous(); }
/* 168:168 */    public long nextIndex() { return this.i.nextIndex(); }
/* 169:169 */    public long previousIndex() { return this.i.previousIndex(); }
/* 170:    */  }
/* 171:    */  
/* 175:    */  public static <K> ObjectBigListIterator<K> asBigListIterator(ObjectListIterator<K> i)
/* 176:    */  {
/* 177:177 */    return new BigListIteratorListIterator(i);
/* 178:    */  }
/* 179:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */