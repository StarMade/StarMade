/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.ObjectInputStream;
/*   5:    */import java.io.ObjectOutputStream;
/*   6:    */import java.io.Serializable;
/*   7:    */import java.util.Collection;
/*   8:    */
/*  54:    */public class ObjectArraySet<K>
/*  55:    */  extends AbstractObjectSet<K>
/*  56:    */  implements Serializable, Cloneable
/*  57:    */{
/*  58:    */  private static final long serialVersionUID = 1L;
/*  59:    */  private transient Object[] a;
/*  60:    */  private int size;
/*  61:    */  
/*  62:    */  public ObjectArraySet(Object[] a)
/*  63:    */  {
/*  64: 64 */    this.a = a;
/*  65: 65 */    this.size = a.length;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public ObjectArraySet()
/*  69:    */  {
/*  70: 70 */    this.a = ObjectArrays.EMPTY_ARRAY;
/*  71:    */  }
/*  72:    */  
/*  75:    */  public ObjectArraySet(int capacity)
/*  76:    */  {
/*  77: 77 */    this.a = new Object[capacity];
/*  78:    */  }
/*  79:    */  
/*  81:    */  public ObjectArraySet(ObjectCollection<K> c)
/*  82:    */  {
/*  83: 83 */    this(c.size());
/*  84: 84 */    addAll(c);
/*  85:    */  }
/*  86:    */  
/*  88:    */  public ObjectArraySet(Collection<? extends K> c)
/*  89:    */  {
/*  90: 90 */    this(c.size());
/*  91: 91 */    addAll(c);
/*  92:    */  }
/*  93:    */  
/*  99:    */  public ObjectArraySet(Object[] a, int size)
/* 100:    */  {
/* 101:101 */    this.a = a;
/* 102:102 */    this.size = size;
/* 103:103 */    if (size > a.length) throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + a.length + ")");
/* 104:    */  }
/* 105:    */  
/* 106:106 */  private int findKey(Object o) { for (int i = this.size; i-- != 0; return i) label5: if (this.a[i] == null ? o != null : !this.a[i].equals(o)) break label5;
/* 107:107 */    return -1;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public ObjectIterator<K> iterator()
/* 111:    */  {
/* 112:112 */    return ObjectIterators.wrap((Object[])this.a, 0, this.size);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public boolean contains(Object k) {
/* 116:116 */    return findKey(k) != -1;
/* 117:    */  }
/* 118:    */  
/* 119:119 */  public int size() { return this.size; }
/* 120:    */  
/* 122:    */  public boolean remove(Object k)
/* 123:    */  {
/* 124:124 */    int pos = findKey(k);
/* 125:125 */    if (pos == -1) return false;
/* 126:126 */    int tail = this.size - pos - 1;
/* 127:127 */    for (int i = 0; i < tail; i++) this.a[(pos + i)] = this.a[(pos + i + 1)];
/* 128:128 */    this.size -= 1;
/* 129:129 */    this.a[this.size] = null;
/* 130:130 */    return true;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public boolean add(K k) {
/* 134:134 */    int pos = findKey(k);
/* 135:135 */    if (pos != -1) return false;
/* 136:136 */    if (this.size == this.a.length) {
/* 137:137 */      Object[] b = new Object[this.size == 0 ? 2 : this.size * 2];
/* 138:138 */      for (int i = this.size; i-- != 0; b[i] = this.a[i]) {}
/* 139:139 */      this.a = b;
/* 140:    */    }
/* 141:141 */    this.a[(this.size++)] = k;
/* 142:142 */    return true;
/* 143:    */  }
/* 144:    */  
/* 145:    */  public void clear() {
/* 146:146 */    for (int i = this.size; i-- != 0; this.a[i] = null) {}
/* 147:147 */    this.size = 0;
/* 148:    */  }
/* 149:    */  
/* 150:    */  public boolean isEmpty() {
/* 151:151 */    return this.size == 0;
/* 152:    */  }
/* 153:    */  
/* 158:    */  public ObjectArraySet<K> clone()
/* 159:    */  {
/* 160:    */    ObjectArraySet<K> c;
/* 161:    */    
/* 164:    */    try
/* 165:    */    {
/* 166:166 */      c = (ObjectArraySet)super.clone();
/* 167:    */    }
/* 168:    */    catch (CloneNotSupportedException cantHappen) {
/* 169:169 */      throw new InternalError();
/* 170:    */    }
/* 171:171 */    c.a = ((Object[])this.a.clone());
/* 172:172 */    return c;
/* 173:    */  }
/* 174:    */  
/* 175:    */  private void writeObject(ObjectOutputStream s) throws IOException {
/* 176:176 */    s.defaultWriteObject();
/* 177:177 */    for (int i = 0; i < this.size; i++) s.writeObject(this.a[i]);
/* 178:    */  }
/* 179:    */  
/* 180:    */  private void readObject(ObjectInputStream s)
/* 181:    */    throws IOException, ClassNotFoundException
/* 182:    */  {
/* 183:183 */    s.defaultReadObject();
/* 184:184 */    this.a = new Object[this.size];
/* 185:185 */    for (int i = 0; i < this.size; i++) this.a[i] = s.readObject();
/* 186:    */  }
/* 187:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */