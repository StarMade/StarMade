/*   1:    */package org.schema.schine.network;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import it.unimi.dsi.fastutil.bytes.ByteArrayList;
/*   5:    */import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
/*   6:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   7:    */import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   9:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*  10:    */import org.schema.schine.network.objects.Sendable;
/*  11:    */import org.schema.schine.network.synchronization.GhostSendable;
/*  12:    */
/*  22:    */public class NetworkStateContainer
/*  23:    */{
/*  24:    */  private final Int2ObjectOpenHashMap localObjects;
/*  25:    */  private final Int2ObjectOpenHashMap localUpdatableObjects;
/*  26:    */  private final Int2ObjectOpenHashMap remoteObjects;
/*  27:    */  private final Object2ObjectOpenHashMap persistentObjects;
/*  28: 28 */  private final Int2ObjectOpenHashMap ghostObjects = new Int2ObjectOpenHashMap();
/*  29:    */  
/*  30:    */  private final boolean privateChannel;
/*  31: 31 */  public ByteArrayList debugReceivedClasses = new ByteArrayList();
/*  32: 32 */  public final ObjectArrayList updateSet = new ObjectArrayList();
/*  33: 33 */  public final Int2BooleanOpenHashMap newStatesBeforeForce = new Int2BooleanOpenHashMap();
/*  34:    */  
/*  35:    */  public NetworkStateContainer(boolean paramBoolean) {
/*  36: 36 */    this.localObjects = new Int2ObjectOpenHashMap();
/*  37: 37 */    this.remoteObjects = new Int2ObjectOpenHashMap();
/*  38: 38 */    this.persistentObjects = new Object2ObjectOpenHashMap();
/*  39: 39 */    this.localUpdatableObjects = new Int2ObjectOpenHashMap();
/*  40: 40 */    this.privateChannel = paramBoolean;
/*  41:    */  }
/*  42:    */  
/*  46:    */  public Int2ObjectOpenHashMap getLocalObjects()
/*  47:    */  {
/*  48: 48 */    return this.localObjects;
/*  49:    */  }
/*  50:    */  
/*  54:    */  public Int2ObjectOpenHashMap getRemoteObjects()
/*  55:    */  {
/*  56: 56 */    return this.remoteObjects;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public void putLocal(int paramInt, Sendable paramSendable)
/*  60:    */  {
/*  61: 61 */    assert (paramSendable != null);
/*  62: 62 */    this.localObjects.put(paramInt, paramSendable);
/*  63: 63 */    if (paramSendable.isUpdatable()) {
/*  64: 64 */      getLocalUpdatableObjects().put(paramInt, paramSendable);
/*  65:    */    }
/*  66:    */  }
/*  67:    */  
/*  70:    */  public Sendable removeLocal(int paramInt)
/*  71:    */  {
/*  72:    */    Sendable localSendable;
/*  73:    */    
/*  74: 74 */    if ((localSendable = (Sendable)this.localObjects.remove(paramInt)).isUpdatable()) {
/*  75: 75 */      getLocalUpdatableObjects().remove(paramInt);
/*  76:    */    }
/*  77: 77 */    return localSendable;
/*  78:    */  }
/*  79:    */  
/*  81:    */  public String toString()
/*  82:    */  {
/*  83: 83 */    return "(Local/Remote: " + this.localObjects + "/" + this.remoteObjects + ")";
/*  84:    */  }
/*  85:    */  
/*  86:    */  public Int2ObjectOpenHashMap getGhostObjects()
/*  87:    */  {
/*  88: 88 */    return this.ghostObjects;
/*  89:    */  }
/*  90:    */  
/*  95:    */  public boolean isPrivateChannel()
/*  96:    */  {
/*  97: 97 */    return this.privateChannel;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public void checkGhostObjects()
/* 101:    */  {
/* 102:102 */    if (!getGhostObjects().isEmpty()) {
/* 103:103 */      long l = System.currentTimeMillis();
/* 104:    */      
/* 105:105 */      synchronized (getGhostObjects()) {
/* 106:106 */        ObjectIterator localObjectIterator = getGhostObjects().values().iterator();
/* 107:107 */        while (localObjectIterator.hasNext()) {
/* 108:108 */          GhostSendable localGhostSendable = (GhostSendable)localObjectIterator.next();
/* 109:109 */          if (l - localGhostSendable.timeDeleted > 20000L) {
/* 110:110 */            localObjectIterator.remove();
/* 111:    */          }
/* 112:    */        }
/* 113:    */        
/* 114:114 */        return;
/* 115:    */      }
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 121:    */  public Int2ObjectOpenHashMap getLocalUpdatableObjects()
/* 122:    */  {
/* 123:123 */    return this.localUpdatableObjects;
/* 124:    */  }
/* 125:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.NetworkStateContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */