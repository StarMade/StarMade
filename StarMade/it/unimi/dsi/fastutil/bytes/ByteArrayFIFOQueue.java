/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import java.util.NoSuchElementException;
/*   4:    */
/*  54:    */public class ByteArrayFIFOQueue
/*  55:    */  extends AbstractBytePriorityQueue
/*  56:    */{
/*  57:    */  public static final int INITIAL_CAPACITY = 16;
/*  58: 58 */  protected byte[] array = ByteArrays.EMPTY_ARRAY;
/*  59:    */  
/*  61:    */  protected int length;
/*  62:    */  
/*  64:    */  protected int start;
/*  65:    */  
/*  67:    */  protected int end;
/*  68:    */  
/*  71:    */  public ByteArrayFIFOQueue(int capacity)
/*  72:    */  {
/*  73: 73 */    if (capacity > 0) this.array = new byte[capacity];
/*  74: 74 */    this.length = this.array.length;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public ByteArrayFIFOQueue()
/*  78:    */  {
/*  79: 79 */    this(16);
/*  80:    */  }
/*  81:    */  
/*  84:    */  public ByteComparator comparator()
/*  85:    */  {
/*  86: 86 */    return null;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public byte dequeueByte() {
/*  90: 90 */    if (this.start == this.end) throw new NoSuchElementException();
/*  91: 91 */    byte t = this.array[this.start];
/*  92: 92 */    if (++this.start == this.length) this.start = 0;
/*  93: 93 */    return t;
/*  94:    */  }
/*  95:    */  
/*  99:    */  public byte dequeueLastByte()
/* 100:    */  {
/* 101:101 */    if (this.start == this.end) throw new NoSuchElementException();
/* 102:102 */    if (this.end == 0) this.end = this.length;
/* 103:103 */    byte t = this.array[(--this.end)];
/* 104:    */    
/* 106:106 */    return t;
/* 107:    */  }
/* 108:    */  
/* 109:    */  private final void expand() {
/* 110:110 */    byte[] newArray = ByteArrays.grow(this.array, this.length + 1, 0);
/* 111:111 */    System.arraycopy(this.array, this.start, newArray, 0, this.length - this.start);
/* 112:112 */    System.arraycopy(this.array, 0, newArray, this.length - this.start, this.end);
/* 113:113 */    this.start = 0;
/* 114:114 */    this.end = this.length;
/* 115:115 */    this.length = (this.array = newArray).length;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public void enqueue(byte x)
/* 119:    */  {
/* 120:120 */    this.array[(this.end++)] = x;
/* 121:121 */    if (this.end == this.length) this.end = 0;
/* 122:122 */    if (this.end == this.start) { expand();
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 126:    */  public void enqueueFirst(byte x)
/* 127:    */  {
/* 128:128 */    if (this.start == 0) this.start = this.length;
/* 129:129 */    this.array[(--this.start)] = x;
/* 130:130 */    if (this.end == this.start) { expand();
/* 131:    */    }
/* 132:    */  }
/* 133:    */  
/* 135:    */  public byte firstByte()
/* 136:    */  {
/* 137:137 */    if (this.start == this.end) throw new NoSuchElementException();
/* 138:138 */    return this.array[this.start];
/* 139:    */  }
/* 140:    */  
/* 144:    */  public byte lastByte()
/* 145:    */  {
/* 146:146 */    if (this.start == this.end) throw new NoSuchElementException();
/* 147:147 */    return this.array[(this.end - 1)];
/* 148:    */  }
/* 149:    */  
/* 157:    */  public void clear()
/* 158:    */  {
/* 159:159 */    this.start = (this.end = 0);
/* 160:    */  }
/* 161:    */  
/* 163:    */  public void trim()
/* 164:    */  {
/* 165:165 */    int size = size();
/* 166:166 */    byte[] newArray = new byte[size + 1];
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
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArrayFIFOQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */