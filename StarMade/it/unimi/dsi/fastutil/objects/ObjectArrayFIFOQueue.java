/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*   4:    */import java.util.Comparator;
/*   5:    */import java.util.NoSuchElementException;
/*   6:    */
/*  55:    */public class ObjectArrayFIFOQueue<K>
/*  56:    */  extends AbstractPriorityQueue<K>
/*  57:    */{
/*  58:    */  public static final int INITIAL_CAPACITY = 16;
/*  59: 59 */  protected K[] array = (Object[])ObjectArrays.EMPTY_ARRAY;
/*  60:    */  
/*  62:    */  protected int length;
/*  63:    */  
/*  65:    */  protected int start;
/*  66:    */  
/*  68:    */  protected int end;
/*  69:    */  
/*  72:    */  public ObjectArrayFIFOQueue(int capacity)
/*  73:    */  {
/*  74: 74 */    if (capacity > 0) this.array = ((Object[])new Object[capacity]);
/*  75: 75 */    this.length = this.array.length;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public ObjectArrayFIFOQueue()
/*  79:    */  {
/*  80: 80 */    this(16);
/*  81:    */  }
/*  82:    */  
/*  85:    */  public Comparator<? super K> comparator()
/*  86:    */  {
/*  87: 87 */    return null;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public K dequeue() {
/*  91: 91 */    if (this.start == this.end) throw new NoSuchElementException();
/*  92: 92 */    K t = this.array[this.start];
/*  93: 93 */    this.array[this.start] = null;
/*  94: 94 */    if (++this.start == this.length) this.start = 0;
/*  95: 95 */    return t;
/*  96:    */  }
/*  97:    */  
/* 101:    */  public K dequeueLast()
/* 102:    */  {
/* 103:103 */    if (this.start == this.end) throw new NoSuchElementException();
/* 104:104 */    if (this.end == 0) this.end = this.length;
/* 105:105 */    K t = this.array[(--this.end)];
/* 106:106 */    this.array[this.end] = null;
/* 107:107 */    return t;
/* 108:    */  }
/* 109:    */  
/* 110:110 */  private final void expand() { K[] newArray = ObjectArrays.grow(this.array, this.length + 1, 0);
/* 111:111 */    System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
/* 112:112 */    System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
/* 113:113 */    this.start = 0;
/* 114:114 */    this.end = this.length;
/* 115:115 */    this.length = (this.array = newArray).length;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public void enqueue(K x)
/* 119:    */  {
/* 120:120 */    this.array[(this.end++)] = x;
/* 121:121 */    if (this.end == this.length) this.end = 0;
/* 122:122 */    if (this.end == this.start) { expand();
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 126:    */  public void enqueueFirst(K x)
/* 127:    */  {
/* 128:128 */    if (this.start == 0) this.start = this.length;
/* 129:129 */    this.array[(--this.start)] = x;
/* 130:130 */    if (this.end == this.start) { expand();
/* 131:    */    }
/* 132:    */  }
/* 133:    */  
/* 135:    */  public K first()
/* 136:    */  {
/* 137:137 */    if (this.start == this.end) throw new NoSuchElementException();
/* 138:138 */    return this.array[this.start];
/* 139:    */  }
/* 140:    */  
/* 144:    */  public K last()
/* 145:    */  {
/* 146:146 */    if (this.start == this.end) throw new NoSuchElementException();
/* 147:147 */    return this.array[(this.end - 1)];
/* 148:    */  }
/* 149:    */  
/* 151:    */  public void clear()
/* 152:    */  {
/* 153:153 */    if (this.start <= this.end) { ObjectArrays.fill(this.array, this.start, this.end, null);
/* 154:    */    } else {
/* 155:155 */      ObjectArrays.fill(this.array, this.start, this.length, null);
/* 156:156 */      ObjectArrays.fill(this.array, 0, this.end, null);
/* 157:    */    }
/* 158:    */    
/* 159:159 */    this.start = (this.end = 0);
/* 160:    */  }
/* 161:    */  
/* 163:    */  public void trim()
/* 164:    */  {
/* 165:165 */    int size = size();
/* 166:166 */    K[] newArray = (Object[])new Object[size + 1];
/* 167:    */    
/* 172:172 */    if (this.start <= this.end) { System.arraycopy(this.array, this.start, newArray, 0, this.end - this.start);
/* 173:    */    } else {
/* 174:174 */      System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
/* 175:175 */      System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
/* 176:    */    }
/* 177:177 */    this.start = 0;
/* 178:178 */    this.length = ((this.end = size) + 1);
/* 179:179 */    this.array = newArray;
/* 180:    */  }
/* 181:    */  
/* 182:    */  public int size()
/* 183:    */  {
/* 184:184 */    int apparentLength = this.end - this.start;
/* 185:185 */    return apparentLength >= 0 ? apparentLength : this.length + apparentLength;
/* 186:    */  }
/* 187:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */