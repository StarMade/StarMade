/*     */ package org.schema.schine.network.objects.remote;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.pool.PrimitiveBufferPool;
/*     */ 
/*     */ public class RemoteBuffer extends RemoteField
/*     */   implements List
/*     */ {
/*     */   public Class clazz;
/*     */   protected ObjectArrayList receiveBuffer;
/*     */   private PrimitiveBufferPool pool;
/*     */   protected static final int MAX_BATCH = 8;
/*     */ 
/*     */   public RemoteBuffer(Class paramClass, NetworkObject paramNetworkObject)
/*     */   {
/*  36 */     super(new ObjectArrayList(), false, paramNetworkObject);
/*  37 */     this.clazz = paramClass;
/*     */ 
/*  39 */     setReceiveBuffer(new ObjectArrayList());
/*  40 */     cacheConstructor();
/*     */   }
/*     */   public RemoteBuffer(Class paramClass, boolean paramBoolean) {
/*  43 */     super(new ObjectArrayList(), false, paramBoolean);
/*  44 */     this.clazz = paramClass;
/*     */ 
/*  46 */     setReceiveBuffer(new ObjectArrayList());
/*  47 */     cacheConstructor();
/*     */   }
/*     */ 
/*     */   public boolean add(Streamable paramStreamable)
/*     */   {
/*  56 */     paramStreamable = ((ObjectArrayList)get()).add(paramStreamable);
/*  57 */     setChanged(paramStreamable);
/*  58 */     this.observer.update(this);
/*  59 */     return paramStreamable;
/*     */   }
/*     */ 
/*     */   public void add(int paramInt, Streamable paramStreamable)
/*     */   {
/*  69 */     ((ObjectArrayList)get()).add(paramInt, paramStreamable);
/*  70 */     setChanged(true);
/*  71 */     this.observer.update(this);
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection paramCollection)
/*     */   {
/*  86 */     if ((
/*  86 */       paramCollection = ((ObjectArrayList)get()).addAll(paramCollection)))
/*     */     {
/*  87 */       setChanged(paramCollection);
/*  88 */       this.observer.update(this);
/*     */     }
/*  90 */     return paramCollection;
/*     */   }
/*     */ 
/*     */   public boolean addAll(int paramInt, Collection paramCollection)
/*     */   {
/* 104 */     if ((
/* 104 */       paramInt = ((ObjectArrayList)get()).addAll(paramInt, paramCollection)))
/*     */     {
/* 105 */       setChanged(paramInt);
/* 106 */       this.observer.update(this);
/*     */     }
/* 108 */     return paramInt;
/*     */   }
/*     */ 
/*     */   public int byteLength()
/*     */   {
/* 117 */     return 4;
/*     */   }
/*     */ 
/*     */   public void cacheConstructor()
/*     */   {
/* 125 */     this.pool = PrimitiveBufferPool.get(this.clazz);
/* 126 */     assert (this.pool != null) : (" pool is null for " + this.clazz);
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 134 */     ((ObjectArrayList)get()).clear();
/*     */   }
/*     */ 
/*     */   public void clearReceiveBuffer()
/*     */   {
/* 139 */     for (int i = 0; i < getReceiveBuffer().size(); i++) {
/* 140 */       assert (getReceiveBuffer() != null) : "ReceiveBuffer null";
/* 141 */       assert (this.pool != null) : ("pool null for " + this.clazz);
/* 142 */       assert (getReceiveBuffer().get(i) != null) : ("element null " + i);
/* 143 */       this.pool.release((Streamable)getReceiveBuffer().get(i));
/*     */     }
/* 145 */     getReceiveBuffer().clear();
/*     */   }
/*     */ 
/*     */   public boolean contains(Object paramObject)
/*     */   {
/* 150 */     return ((ObjectArrayList)get()).contains(paramObject);
/*     */   }
/*     */ 
/*     */   public boolean containsAll(Collection paramCollection)
/*     */   {
/* 156 */     return ((ObjectArrayList)get()).containsAll(paramCollection);
/*     */   }
/*     */ 
/*     */   public Streamable get(int paramInt)
/*     */   {
/* 162 */     return (Streamable)((ObjectArrayList)get()).get(paramInt);
/*     */   }
/*     */ 
/*     */   public ObjectArrayList getReceiveBuffer()
/*     */   {
/* 169 */     return this.receiveBuffer;
/*     */   }
/*     */ 
/*     */   public int indexOf(Object paramObject)
/*     */   {
/* 174 */     return ((ObjectArrayList)get()).indexOf(paramObject);
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 180 */     return ((ObjectArrayList)get()).isEmpty();
/*     */   }
/*     */ 
/*     */   public Iterator iterator()
/*     */   {
/* 185 */     return ((ObjectArrayList)get()).iterator();
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(Object paramObject) {
/* 189 */     return ((ObjectArrayList)get()).lastIndexOf(paramObject);
/*     */   }
/*     */ 
/*     */   public ListIterator listIterator() {
/* 193 */     return ((ObjectArrayList)get()).listIterator();
/*     */   }
/*     */ 
/*     */   public ListIterator listIterator(int paramInt) {
/* 197 */     return ((ObjectArrayList)get()).listIterator(paramInt);
/*     */   }
/*     */ 
/*     */   public Streamable remove(int paramInt)
/*     */   {
/* 204 */     return (Streamable)((ObjectArrayList)get()).remove(paramInt);
/*     */   }
/*     */ 
/*     */   public boolean remove(Object paramObject)
/*     */   {
/* 213 */     return ((ObjectArrayList)get()).remove(paramObject);
/*     */   }
/*     */ 
/*     */   public boolean removeAll(Collection paramCollection)
/*     */   {
/* 222 */     return ((ObjectArrayList)get()).removeAll(paramCollection);
/*     */   }
/*     */ 
/*     */   public boolean retainAll(Collection paramCollection)
/*     */   {
/* 232 */     return ((ObjectArrayList)get()).retainAll(paramCollection);
/*     */   }
/*     */ 
/*     */   public Streamable set(int paramInt, Streamable paramStreamable)
/*     */   {
/* 241 */     paramInt = (Streamable)((ObjectArrayList)get()).set(paramInt, paramStreamable);
/* 242 */     setChanged(true);
/* 243 */     this.observer.update(this);
/* 244 */     return paramInt;
/*     */   }
/*     */ 
/*     */   public void setReceiveBuffer(ObjectArrayList paramObjectArrayList)
/*     */   {
/* 252 */     this.receiveBuffer = paramObjectArrayList;
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 257 */     return ((ObjectArrayList)get()).size();
/*     */   }
/*     */ 
/*     */   public List subList(int paramInt1, int paramInt2)
/*     */   {
/* 262 */     return ((ObjectArrayList)get()).subList(paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public Object[] toArray()
/*     */   {
/* 267 */     return ((ObjectArrayList)get()).toArray();
/*     */   }
/*     */ 
/*     */   public Object[] toArray(Object[] paramArrayOfObject)
/*     */   {
/* 272 */     return ((ObjectArrayList)get()).toArray(paramArrayOfObject);
/*     */   }
/*     */ 
/*     */   public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*     */   {
/* 278 */     int i = paramDataInputStream.readInt();
/*     */ 
/* 281 */     for (int j = 0; j < i; j++)
/*     */     {
/*     */       Streamable localStreamable;
/* 283 */       (
/* 284 */         localStreamable = this.pool.get(this.onServer))
/* 284 */         .fromByteStream(paramDataInputStream, paramInt);
/* 285 */       this.receiveBuffer.add(localStreamable);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int toByteStream(DataOutputStream paramDataOutputStream)
/*     */   {
/* 294 */     int i = Math.min(8, ((ObjectArrayList)get()).size());
/*     */ 
/* 296 */     paramDataOutputStream.writeInt(i);
/*     */ 
/* 298 */     int j = 0;
/*     */ 
/* 300 */     for (int k = 0; k < i; k++) {
/* 301 */       Streamable localStreamable = (Streamable)((ObjectArrayList)get()).remove(0);
/*     */ 
/* 305 */       j += localStreamable.toByteStream(paramDataOutputStream);
/* 306 */       localStreamable.setChanged(false);
/*     */     }
/* 308 */     this.keepChanged = (!((ObjectArrayList)get()).isEmpty());
/*     */ 
/* 314 */     return j + 4;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteBuffer
 * JD-Core Version:    0.6.2
 */