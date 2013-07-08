/*   1:    */package it.unimi.dsi.fastutil.booleans;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.NoSuchElementException;
/*   5:    */
/*  53:    */public class BooleanBigListIterators
/*  54:    */{
/*  55:    */  public static class EmptyBigListIterator
/*  56:    */    extends AbstractBooleanBigListIterator
/*  57:    */    implements Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean hasNext() { return false; }
/*  62: 62 */    public boolean hasPrevious() { return false; }
/*  63: 63 */    public boolean nextBoolean() { throw new NoSuchElementException(); }
/*  64: 64 */    public boolean previousBoolean() { throw new NoSuchElementException(); }
/*  65: 65 */    public long nextIndex() { return 0L; }
/*  66: 66 */    public long previousIndex() { return -1L; }
/*  67: 67 */    public long skip(long n) { return 0L; }
/*  68: 68 */    public long back(long n) { return 0L; }
/*  69: 69 */    public Object clone() { return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*  70: 70 */    private Object readResolve() { return BooleanBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/*  71:    */  }
/*  72:    */  
/*  78: 78 */  public static final EmptyBigListIterator EMPTY_BIG_LIST_ITERATOR = new EmptyBigListIterator();
/*  79:    */  
/*  80:    */  private static class SingletonBigListIterator extends AbstractBooleanBigListIterator {
/*  81:    */    private final boolean element;
/*  82:    */    private int curr;
/*  83:    */    
/*  84: 84 */    public SingletonBigListIterator(boolean element) { this.element = element; }
/*  85:    */    
/*  86: 86 */    public boolean hasNext() { return this.curr == 0; }
/*  87: 87 */    public boolean hasPrevious() { return this.curr == 1; }
/*  88:    */    
/*  89: 89 */    public boolean nextBoolean() { if (!hasNext()) throw new NoSuchElementException();
/*  90: 90 */      this.curr = 1;
/*  91: 91 */      return this.element;
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    public boolean previousBoolean() { if (!hasPrevious()) throw new NoSuchElementException();
/*  95: 95 */      this.curr = 0;
/*  96: 96 */      return this.element;
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    public long nextIndex() { return this.curr; }
/* 100:    */    
/* 101:    */    public long previousIndex() {
/* 102:102 */      return this.curr - 1;
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 109:    */  public static BooleanBigListIterator singleton(boolean element)
/* 110:    */  {
/* 111:111 */    return new SingletonBigListIterator(element);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public static class UnmodifiableBigListIterator extends AbstractBooleanBigListIterator
/* 115:    */  {
/* 116:    */    protected final BooleanBigListIterator i;
/* 117:    */    
/* 118:    */    public UnmodifiableBigListIterator(BooleanBigListIterator i)
/* 119:    */    {
/* 120:120 */      this.i = i;
/* 121:    */    }
/* 122:    */    
/* 123:123 */    public boolean hasNext() { return this.i.hasNext(); }
/* 124:124 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 125:125 */    public boolean nextBoolean() { return this.i.nextBoolean(); }
/* 126:126 */    public boolean previousBoolean() { return this.i.previousBoolean(); }
/* 127:127 */    public long nextIndex() { return this.i.nextIndex(); }
/* 128:128 */    public long previousIndex() { return this.i.previousIndex(); }
/* 129:    */    
/* 130:130 */    public Boolean next() { return (Boolean)this.i.next(); }
/* 131:131 */    public Boolean previous() { return (Boolean)this.i.previous(); }
/* 132:    */  }
/* 133:    */  
/* 138:    */  public static BooleanBigListIterator unmodifiable(BooleanBigListIterator i)
/* 139:    */  {
/* 140:140 */    return new UnmodifiableBigListIterator(i);
/* 141:    */  }
/* 142:    */  
/* 143:    */  public static class BigListIteratorListIterator extends AbstractBooleanBigListIterator
/* 144:    */  {
/* 145:    */    protected final BooleanListIterator i;
/* 146:    */    
/* 147:    */    protected BigListIteratorListIterator(BooleanListIterator i)
/* 148:    */    {
/* 149:149 */      this.i = i;
/* 150:    */    }
/* 151:    */    
/* 152:    */    private int intDisplacement(long n) {
/* 153:153 */      if ((n < -2147483648L) || (n > 2147483647L)) throw new IndexOutOfBoundsException("This big iterator is restricted to 32-bit displacements");
/* 154:154 */      return (int)n;
/* 155:    */    }
/* 156:    */    
/* 157:157 */    public void set(boolean ok) { this.i.set(ok); }
/* 158:158 */    public void add(boolean ok) { this.i.add(ok); }
/* 159:159 */    public int back(int n) { return this.i.back(n); }
/* 160:160 */    public long back(long n) { return this.i.back(intDisplacement(n)); }
/* 161:161 */    public void remove() { this.i.remove(); }
/* 162:162 */    public int skip(int n) { return this.i.skip(n); }
/* 163:163 */    public long skip(long n) { return this.i.skip(intDisplacement(n)); }
/* 164:164 */    public boolean hasNext() { return this.i.hasNext(); }
/* 165:165 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 166:166 */    public boolean nextBoolean() { return this.i.nextBoolean(); }
/* 167:167 */    public boolean previousBoolean() { return this.i.previousBoolean(); }
/* 168:168 */    public long nextIndex() { return this.i.nextIndex(); }
/* 169:169 */    public long previousIndex() { return this.i.previousIndex(); }
/* 170:    */  }
/* 171:    */  
/* 175:    */  public static BooleanBigListIterator asBigListIterator(BooleanListIterator i)
/* 176:    */  {
/* 177:177 */    return new BigListIteratorListIterator(i);
/* 178:    */  }
/* 179:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBigListIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */