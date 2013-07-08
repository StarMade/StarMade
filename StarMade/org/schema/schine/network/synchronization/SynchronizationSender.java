/*   1:    */package org.schema.schine.network.synchronization;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
/*   5:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   7:    */import java.io.DataOutputStream;
/*   8:    */import java.io.PrintStream;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.List;
/*  11:    */import org.schema.schine.network.NetworkStateContainer;
/*  12:    */import org.schema.schine.network.StateInterface;
/*  13:    */import org.schema.schine.network.exception.SynchronizationException;
/*  14:    */import org.schema.schine.network.objects.NetworkObject;
/*  15:    */import org.schema.schine.network.objects.Sendable;
/*  16:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  18:    */
/*  35:    */public class SynchronizationSender
/*  36:    */{
/*  37:    */  public static final int RETURN_CODE_CHANGED_OBJECT = 1;
/*  38:    */  private static final int RETURN_CODE_NOTHING_CHANGED = 0;
/*  39:    */  private static int debugId;
/*  40:    */  public static boolean clientDebug;
/*  41:    */  
/*  42:    */  private static void checkIfNeedsUpdateOrRemoved(Sendable paramSendable, NetworkObject paramNetworkObject, List paramList)
/*  43:    */  {
/*  44: 44 */    if (paramSendable.isMarkedForDeleteVolatile()) {
/*  45: 45 */      paramNetworkObject.markedDeleted.set(Boolean.valueOf(paramSendable.isMarkedForDeleteVolatile()), true);
/*  46: 46 */      System.err.println("[DELETE] " + paramSendable.getState() + " MARKING FOR DELETE " + paramSendable);
/*  47: 47 */      paramNetworkObject.setChanged(true);
/*  48: 48 */      paramList.add(paramSendable);return; }
/*  49: 49 */    if ((paramNetworkObject.newObject) || (paramNetworkObject.isChanged())) {
/*  50: 50 */      if ((((Boolean)paramNetworkObject.markedDeleted.get()).booleanValue()) && (!paramSendable.isMarkedForDeleteVolatile()))
/*  51:    */      {
/*  52: 52 */        paramNetworkObject.newObject = false;
/*  53: 53 */        paramNetworkObject.setChanged(false);return;
/*  54:    */      }
/*  55:    */      
/*  56: 56 */      paramList.add(paramSendable);
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  64:    */  private static void encodeFullObject(Sendable paramSendable, NetworkObject paramNetworkObject, DataOutputStream paramDataOutputStream, boolean paramBoolean1, boolean paramBoolean2)
/*  65:    */  {
/*  66: 66 */    paramNetworkObject = paramSendable.getNetworkObject();
/*  67: 67 */    if (paramSendable.getId() < 0) {
/*  68: 68 */      throw new IllegalArgumentException("[SENDER] Exception writing negative id for " + paramSendable + ": " + paramSendable.getId() + " " + paramSendable.getState());
/*  69:    */    }
/*  70: 70 */    synchronized (paramNetworkObject) {
/*  71: 71 */      paramSendable.updateToFullNetworkObject();
/*  72:    */      
/*  75: 75 */      int i = paramDataOutputStream.size();
/*  76: 76 */      paramBoolean2 = NetworkObject.encode(paramSendable, paramNetworkObject, true, paramDataOutputStream, paramBoolean1, paramBoolean2);
/*  77:    */      
/*  82: 82 */      if ((paramDataOutputStream = paramDataOutputStream.size() - i) > 5120) {
/*  83: 83 */        System.err.println("[NT] Big FullUpdate: " + paramDataOutputStream + " bytes: " + paramSendable);
/*  84:    */      }
/*  85:    */      
/*  86: 86 */      if (!paramBoolean1)
/*  87:    */      {
/*  88: 88 */        paramNetworkObject.setChanged(paramBoolean2);
/*  89: 89 */        paramNetworkObject.newObject = false;
/*  90:    */      }
/*  91:    */      
/*  99:    */      return;
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 111:    */  public static int encodeNetworkObjects(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, DataOutputStream paramDataOutputStream, boolean paramBoolean)
/* 112:    */  {
/* 113:113 */    Object localObject1 = paramNetworkStateContainer.getLocalObjects();
/* 114:114 */    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap = paramNetworkStateContainer.getRemoteObjects();
/* 115:    */    
/* 122:122 */    synchronized (localObject1) {
/* 123:123 */      synchronized (localInt2ObjectOpenHashMap)
/* 124:    */      {
/* 125:125 */        ObjectArrayList localObjectArrayList = paramNetworkStateContainer.updateSet;
/* 126:126 */        Int2BooleanOpenHashMap localInt2BooleanOpenHashMap = paramNetworkStateContainer.newStatesBeforeForce;
/* 127:127 */        localObjectArrayList.clear();
/* 128:128 */        localInt2BooleanOpenHashMap.clear();
/* 129:    */        
/* 131:131 */        int i = 0;
/* 132:132 */        int j = 0;
/* 133:133 */        if (paramBoolean) {
/* 134:134 */          System.err.println("[SYNC_SENDER] SENDING ALL OBJECTS: " + ((Int2ObjectOpenHashMap)localObject1).size());
/* 135:135 */          for (localObject2 = localInt2ObjectOpenHashMap.values().iterator(); ((Iterator)localObject2).hasNext();) { localObject3 = (NetworkObject)((Iterator)localObject2).next();
/* 136:    */            
/* 137:137 */            localInt2BooleanOpenHashMap.put(((Integer)((NetworkObject)localObject3).id.get()).intValue(), ((NetworkObject)localObject3).newObject);
/* 138:138 */            ((NetworkObject)localObject3).newObject = true;
/* 139:139 */            j++;
/* 140:    */          }
/* 141:    */        }
/* 142:142 */        for (Object localObject3 = ((Int2ObjectOpenHashMap)localObject1).values().iterator(); ((Iterator)localObject3).hasNext();)
/* 143:    */        {
/* 144:144 */          if ((localObject2 = (Sendable)((Iterator)localObject3).next()).getId() < 0) {
/* 145:145 */            throw new IllegalArgumentException("[SENDER] Exception writing negative id for " + localObject2 + ": " + ((Sendable)localObject2).getId() + " " + ((Sendable)localObject2).getState());
/* 146:    */          }
/* 147:    */          
/* 148:148 */          ((Sendable)localObject2).isMarkedForDeleteVolatile();
/* 149:    */          
/* 154:154 */          if ((localObject4 = (NetworkObject)localInt2ObjectOpenHashMap.get(((Sendable)localObject2).getId())) == null) {
/* 155:    */            try {
/* 156:156 */              throw new SynchronizationException("!!!!!!!!!! sendingState(" + paramStateInterface + ")FATAL-ERROR: " + ((Sendable)localObject2).getId() + " does not exist: " + localInt2ObjectOpenHashMap + ", LOCAL: " + localObject1);
/* 157:    */            }
/* 158:    */            catch (SynchronizationException localSynchronizationException) {
/* 159:159 */              localSynchronizationException;
/* 160:    */              
/* 161:161 */              if (!$assertionsDisabled) { throw new AssertionError();
/* 162:    */              }
/* 163:    */            }
/* 164:    */          }
/* 165:165 */          assert ((!paramBoolean) || (((NetworkObject)localObject4).newObject)) : (" failed: forceAll -> objbectNew: " + localObject4 + ": " + ((NetworkObject)localObject4).newObject);
/* 166:    */          
/* 168:168 */          checkIfNeedsUpdateOrRemoved((Sendable)localObject2, (NetworkObject)localObject4, localObjectArrayList);
/* 169:    */        }
/* 170:    */        
/* 173:    */        Object localObject4;
/* 174:    */        
/* 177:177 */        if (localObjectArrayList.isEmpty())
/* 178:    */        {
/* 182:182 */          return 0;
/* 183:    */        }
/* 184:    */        
/* 185:185 */        assert ((!paramBoolean) || (j == localObjectArrayList.size())) : (" force all " + j + ": " + localObjectArrayList.size());
/* 186:    */        
/* 191:191 */        paramDataOutputStream.writeInt(localObjectArrayList.size());
/* 192:    */        
/* 193:193 */        int k = 0;
/* 194:194 */        for (Object localObject2 = localObjectArrayList.iterator(); ((Iterator)localObject2).hasNext();) { localObject4 = (Sendable)((Iterator)localObject2).next();
/* 195:    */          
/* 200:    */          boolean bool;
/* 201:    */          
/* 205:205 */          if (!(localObject1 = (NetworkObject)localInt2ObjectOpenHashMap.get(((Sendable)localObject4).getId())).newObject)
/* 206:    */          {
/* 212:212 */            if ((bool = encodePartialObjectIfChanged((Sendable)localObject4, (NetworkObject)localObject1, paramDataOutputStream, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer()))) {
/* 213:213 */              k++;
/* 214:    */            }
/* 215:    */            
/* 216:216 */            i = (i != 0) || (bool) ? 1 : 0;
/* 220:    */          }
/* 221:    */          else
/* 222:    */          {
/* 225:225 */            assert ((!((Boolean)bool.markedDeleted.get()).booleanValue()) || (((Sendable)localObject4).isMarkedForDeleteVolatile()));
/* 226:226 */            i = 1;
/* 227:227 */            encodeFullObject((Sendable)localObject4, bool, paramDataOutputStream, false, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer());
/* 228:228 */            k++;
/* 229:    */          }
/* 230:    */          
/* 234:234 */          if (((Sendable)localObject4).isMarkedForDeleteVolatile())
/* 235:    */          {
/* 236:236 */            ((Sendable)localObject4).setMarkedForDeleteVolatileSent(true);
/* 237:    */          }
/* 238:238 */          clientDebug = false;
/* 239:    */        }
/* 240:    */        
/* 242:242 */        assert (localObjectArrayList.size() == k) : (" WRONG NUMBER OF OBJECTS WRITTEN: " + localObjectArrayList.size() + " / " + k);
/* 243:    */        
/* 245:245 */        if (paramBoolean) {
/* 246:246 */          for (localObject2 = localInt2ObjectOpenHashMap.values().iterator(); ((Iterator)localObject2).hasNext();) {
/* 247:247 */            (localObject4 = (NetworkObject)((Iterator)localObject2).next()).newObject = localInt2BooleanOpenHashMap.get((Integer)((NetworkObject)localObject4).id.get()).booleanValue();
/* 248:    */          }
/* 249:    */        }
/* 250:    */        
/* 251:251 */        if (i != 0) {
/* 252:252 */          return 1;
/* 253:    */        }
/* 254:254 */        return 0;
/* 255:    */      }
/* 256:    */    }
/* 257:    */  }
/* 258:    */  
/* 259:    */  private static boolean encodePartialObjectIfChanged(Sendable paramSendable, NetworkObject paramNetworkObject, DataOutputStream paramDataOutputStream, boolean paramBoolean)
/* 260:    */  {
/* 261:261 */    boolean bool = false;
/* 262:262 */    synchronized (paramNetworkObject) {
/* 263:263 */      if (paramNetworkObject.isChanged())
/* 264:    */      {
/* 265:265 */        assert ((!((Boolean)paramNetworkObject.markedDeleted.get()).booleanValue()) || (paramSendable.isMarkedForDeleteVolatile()));
/* 266:    */        
/* 268:268 */        bool = true;
/* 269:    */        
/* 272:272 */        paramSendable = paramNetworkObject.encodeChange(paramSendable, paramDataOutputStream, paramBoolean);
/* 273:    */        
/* 276:276 */        paramNetworkObject.setChanged(paramSendable);
/* 277:277 */        paramNetworkObject.newObject = false;
/* 278:    */      }
/* 279:    */    }
/* 280:280 */    return bool;
/* 281:    */  }
/* 282:    */  
/* 298:    */  public static void writeObjectForcedWithoutStateChange(NetworkStateContainer paramNetworkStateContainer, StateInterface paramStateInterface, DataOutputStream paramDataOutputStream, boolean paramBoolean)
/* 299:    */  {
/* 300:300 */    paramBoolean = paramNetworkStateContainer.getLocalObjects();
/* 301:301 */    Int2ObjectOpenHashMap localInt2ObjectOpenHashMap = paramNetworkStateContainer.getRemoteObjects();
/* 302:    */    
/* 308:308 */    synchronized (paramBoolean) {
/* 309:309 */      synchronized (localInt2ObjectOpenHashMap)
/* 310:    */      {
/* 311:311 */        paramDataOutputStream.writeInt(paramBoolean.size());
/* 312:312 */        int i = 0;
/* 313:313 */        for (Iterator localIterator = paramBoolean.values().iterator(); localIterator.hasNext();) {
/* 314:    */          Sendable localSendable;
/* 315:315 */          if ((localSendable = (Sendable)localIterator.next()).getId() < 0) {
/* 316:316 */            throw new IllegalArgumentException("[SENDER] Exception writing negative id for " + localSendable + ": " + localSendable.getId() + " " + localSendable.getState());
/* 317:    */          }
/* 318:318 */          NetworkObject localNetworkObject = (NetworkObject)localInt2ObjectOpenHashMap.get(localSendable.getId());
/* 319:    */          
/* 320:320 */          assert (localNetworkObject != null) : (localSendable.getId() + " does not exist: " + localInt2ObjectOpenHashMap + ", LOCAL: " + paramBoolean);
/* 321:    */          
/* 324:324 */          encodeFullObject(localSendable, localNetworkObject, paramDataOutputStream, true, paramNetworkStateContainer != paramStateInterface.getLocalAndRemoteObjectContainer());
/* 325:325 */          i++;
/* 326:    */        }
/* 327:    */        
/* 332:332 */        if ((!$assertionsDisabled) && (paramBoolean.size() != i)) throw new AssertionError(" WRONG NUMBER OF OBJECTS WRITTEN: " + paramBoolean.size() + " / " + i);
/* 333:    */      }
/* 334:    */      return;
/* 335:    */    }
/* 336:    */  }
/* 337:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.synchronization.SynchronizationSender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */