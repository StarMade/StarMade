/*   1:    */package org.schema.schine.network.objects.remote;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   4:    */import java.io.DataInputStream;
/*   5:    */import java.io.DataOutputStream;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.List;
/*   9:    */import java.util.ListIterator;
/*  10:    */import org.schema.schine.network.objects.NetworkObject;
/*  11:    */import org.schema.schine.network.objects.remote.pool.PrimitiveBufferPool;
/*  12:    */
/*  25:    */public class RemoteBuffer
/*  26:    */  extends RemoteField
/*  27:    */  implements List
/*  28:    */{
/*  29:    */  public Class clazz;
/*  30:    */  protected ObjectArrayList receiveBuffer;
/*  31:    */  private PrimitiveBufferPool pool;
/*  32:    */  protected static final int MAX_BATCH = 8;
/*  33:    */  
/*  34:    */  public RemoteBuffer(Class paramClass, NetworkObject paramNetworkObject)
/*  35:    */  {
/*  36: 36 */    super(new ObjectArrayList(), false, paramNetworkObject);
/*  37: 37 */    this.clazz = paramClass;
/*  38:    */    
/*  39: 39 */    setReceiveBuffer(new ObjectArrayList());
/*  40: 40 */    cacheConstructor();
/*  41:    */  }
/*  42:    */  
/*  43: 43 */  public RemoteBuffer(Class paramClass, boolean paramBoolean) { super(new ObjectArrayList(), false, paramBoolean);
/*  44: 44 */    this.clazz = paramClass;
/*  45:    */    
/*  46: 46 */    setReceiveBuffer(new ObjectArrayList());
/*  47: 47 */    cacheConstructor();
/*  48:    */  }
/*  49:    */  
/*  54:    */  public boolean add(Streamable paramStreamable)
/*  55:    */  {
/*  56: 56 */    paramStreamable = ((ObjectArrayList)get()).add(paramStreamable);
/*  57: 57 */    setChanged(paramStreamable);
/*  58: 58 */    this.observer.update(this);
/*  59: 59 */    return paramStreamable;
/*  60:    */  }
/*  61:    */  
/*  67:    */  public void add(int paramInt, Streamable paramStreamable)
/*  68:    */  {
/*  69: 69 */    ((ObjectArrayList)get()).add(paramInt, paramStreamable);
/*  70: 70 */    setChanged(true);
/*  71: 71 */    this.observer.update(this);
/*  72:    */  }
/*  73:    */  
/*  84:    */  public boolean addAll(Collection paramCollection)
/*  85:    */  {
/*  86: 86 */    if ((paramCollection = ((ObjectArrayList)get()).addAll(paramCollection))) {
/*  87: 87 */      setChanged(paramCollection);
/*  88: 88 */      this.observer.update(this);
/*  89:    */    }
/*  90: 90 */    return paramCollection;
/*  91:    */  }
/*  92:    */  
/* 102:    */  public boolean addAll(int paramInt, Collection paramCollection)
/* 103:    */  {
/* 104:104 */    if ((paramInt = ((ObjectArrayList)get()).addAll(paramInt, paramCollection))) {
/* 105:105 */      setChanged(paramInt);
/* 106:106 */      this.observer.update(this);
/* 107:    */    }
/* 108:108 */    return paramInt;
/* 109:    */  }
/* 110:    */  
/* 115:    */  public int byteLength()
/* 116:    */  {
/* 117:117 */    return 4;
/* 118:    */  }
/* 119:    */  
/* 123:    */  public void cacheConstructor()
/* 124:    */  {
/* 125:125 */    this.pool = PrimitiveBufferPool.get(this.clazz);
/* 126:126 */    assert (this.pool != null) : (" pool is null for " + this.clazz);
/* 127:    */  }
/* 128:    */  
/* 132:    */  public void clear()
/* 133:    */  {
/* 134:134 */    ((ObjectArrayList)get()).clear();
/* 135:    */  }
/* 136:    */  
/* 137:    */  public void clearReceiveBuffer()
/* 138:    */  {
/* 139:139 */    for (int i = 0; i < getReceiveBuffer().size(); i++) {
/* 140:140 */      assert (getReceiveBuffer() != null) : "ReceiveBuffer null";
/* 141:141 */      assert (this.pool != null) : ("pool null for " + this.clazz);
/* 142:142 */      assert (getReceiveBuffer().get(i) != null) : ("element null " + i);
/* 143:143 */      this.pool.release((Streamable)getReceiveBuffer().get(i));
/* 144:    */    }
/* 145:145 */    getReceiveBuffer().clear();
/* 146:    */  }
/* 147:    */  
/* 148:    */  public boolean contains(Object paramObject)
/* 149:    */  {
/* 150:150 */    return ((ObjectArrayList)get()).contains(paramObject);
/* 151:    */  }
/* 152:    */  
/* 154:    */  public boolean containsAll(Collection paramCollection)
/* 155:    */  {
/* 156:156 */    return ((ObjectArrayList)get()).containsAll(paramCollection);
/* 157:    */  }
/* 158:    */  
/* 160:    */  public Streamable get(int paramInt)
/* 161:    */  {
/* 162:162 */    return (Streamable)((ObjectArrayList)get()).get(paramInt);
/* 163:    */  }
/* 164:    */  
/* 167:    */  public ObjectArrayList getReceiveBuffer()
/* 168:    */  {
/* 169:169 */    return this.receiveBuffer;
/* 170:    */  }
/* 171:    */  
/* 172:    */  public int indexOf(Object paramObject)
/* 173:    */  {
/* 174:174 */    return ((ObjectArrayList)get()).indexOf(paramObject);
/* 175:    */  }
/* 176:    */  
/* 178:    */  public boolean isEmpty()
/* 179:    */  {
/* 180:180 */    return ((ObjectArrayList)get()).isEmpty();
/* 181:    */  }
/* 182:    */  
/* 183:    */  public Iterator iterator()
/* 184:    */  {
/* 185:185 */    return ((ObjectArrayList)get()).iterator();
/* 186:    */  }
/* 187:    */  
/* 188:    */  public int lastIndexOf(Object paramObject) {
/* 189:189 */    return ((ObjectArrayList)get()).lastIndexOf(paramObject);
/* 190:    */  }
/* 191:    */  
/* 192:    */  public ListIterator listIterator() {
/* 193:193 */    return ((ObjectArrayList)get()).listIterator();
/* 194:    */  }
/* 195:    */  
/* 196:    */  public ListIterator listIterator(int paramInt) {
/* 197:197 */    return ((ObjectArrayList)get()).listIterator(paramInt);
/* 198:    */  }
/* 199:    */  
/* 202:    */  public Streamable remove(int paramInt)
/* 203:    */  {
/* 204:204 */    return (Streamable)((ObjectArrayList)get()).remove(paramInt);
/* 205:    */  }
/* 206:    */  
/* 211:    */  public boolean remove(Object paramObject)
/* 212:    */  {
/* 213:213 */    return ((ObjectArrayList)get()).remove(paramObject);
/* 214:    */  }
/* 215:    */  
/* 220:    */  public boolean removeAll(Collection paramCollection)
/* 221:    */  {
/* 222:222 */    return ((ObjectArrayList)get()).removeAll(paramCollection);
/* 223:    */  }
/* 224:    */  
/* 230:    */  public boolean retainAll(Collection paramCollection)
/* 231:    */  {
/* 232:232 */    return ((ObjectArrayList)get()).retainAll(paramCollection);
/* 233:    */  }
/* 234:    */  
/* 239:    */  public Streamable set(int paramInt, Streamable paramStreamable)
/* 240:    */  {
/* 241:241 */    paramInt = (Streamable)((ObjectArrayList)get()).set(paramInt, paramStreamable);
/* 242:242 */    setChanged(true);
/* 243:243 */    this.observer.update(this);
/* 244:244 */    return paramInt;
/* 245:    */  }
/* 246:    */  
/* 250:    */  public void setReceiveBuffer(ObjectArrayList paramObjectArrayList)
/* 251:    */  {
/* 252:252 */    this.receiveBuffer = paramObjectArrayList;
/* 253:    */  }
/* 254:    */  
/* 255:    */  public int size()
/* 256:    */  {
/* 257:257 */    return ((ObjectArrayList)get()).size();
/* 258:    */  }
/* 259:    */  
/* 260:    */  public List subList(int paramInt1, int paramInt2)
/* 261:    */  {
/* 262:262 */    return ((ObjectArrayList)get()).subList(paramInt1, paramInt2);
/* 263:    */  }
/* 264:    */  
/* 265:    */  public Object[] toArray()
/* 266:    */  {
/* 267:267 */    return ((ObjectArrayList)get()).toArray();
/* 268:    */  }
/* 269:    */  
/* 270:    */  public Object[] toArray(Object[] paramArrayOfObject)
/* 271:    */  {
/* 272:272 */    return ((ObjectArrayList)get()).toArray(paramArrayOfObject);
/* 273:    */  }
/* 274:    */  
/* 276:    */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 277:    */  {
/* 278:278 */    int i = paramDataInputStream.readInt();
/* 279:    */    
/* 281:281 */    for (int j = 0; j < i; j++)
/* 282:    */    {
/* 283:    */      Streamable localStreamable;
/* 284:284 */      (localStreamable = this.pool.get(this.onServer)).fromByteStream(paramDataInputStream, paramInt);
/* 285:285 */      this.receiveBuffer.add(localStreamable);
/* 286:    */    }
/* 287:    */  }
/* 288:    */  
/* 292:    */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 293:    */  {
/* 294:294 */    int i = Math.min(8, ((ObjectArrayList)get()).size());
/* 295:    */    
/* 296:296 */    paramDataOutputStream.writeInt(i);
/* 297:    */    
/* 298:298 */    int j = 0;
/* 299:    */    
/* 300:300 */    for (int k = 0; k < i; k++) {
/* 301:301 */      Streamable localStreamable = (Streamable)((ObjectArrayList)get()).remove(0);
/* 302:    */      
/* 305:305 */      j += localStreamable.toByteStream(paramDataOutputStream);
/* 306:306 */      localStreamable.setChanged(false);
/* 307:    */    }
/* 308:308 */    this.keepChanged = (!((ObjectArrayList)get()).isEmpty());
/* 309:    */    
/* 314:314 */    return j + 4;
/* 315:    */  }
/* 316:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */