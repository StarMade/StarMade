/*   1:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   2:    */import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectSet;
/*   4:    */import java.io.File;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.util.HashMap;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.Map.Entry;
/*  10:    */import java.util.Observable;
/*  11:    */import org.schema.game.common.controller.elements.missile.MissileController;
/*  12:    */import org.schema.game.network.objects.remote.RemoteFactionNewsPost;
/*  13:    */import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
/*  14:    */import org.schema.game.network.objects.remote.RemoteMapEntryRequest;
/*  15:    */import org.schema.game.network.objects.remote.RemoteMapEntryRequestBuffer;
/*  16:    */import org.schema.game.server.controller.GameServerController;
/*  17:    */import org.schema.schine.network.NetworkStateContainer;
/*  18:    */import org.schema.schine.network.StateInterface;
/*  19:    */import org.schema.schine.network.client.ClientState;
/*  20:    */import org.schema.schine.network.objects.NetworkObject;
/*  21:    */import org.schema.schine.network.objects.Sendable;
/*  22:    */import org.schema.schine.network.objects.remote.RemoteArray;
/*  23:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  24:    */import org.schema.schine.network.objects.remote.RemoteBoolean;
/*  25:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  26:    */import org.schema.schine.network.objects.remote.RemoteLong;
/*  27:    */import org.schema.schine.network.objects.remote.RemoteString;
/*  28:    */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*  29:    */import org.schema.schine.network.server.ServerStateInterface;
/*  30:    */
/*  31:    */public class t extends Observable implements Sendable
/*  32:    */{
/*  33:    */  private StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  34:    */  private final boolean jdField_a_of_type_Boolean;
/*  35: 35 */  private int jdField_a_of_type_Int = -121212;
/*  36:    */  
/*  37:    */  private org.schema.game.network.objects.NetworkClientChannel jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel;
/*  38:    */  
/*  39: 39 */  private final java.util.TreeSet jdField_a_of_type_JavaUtilTreeSet = new java.util.TreeSet();
/*  40:    */  
/*  42: 42 */  private final java.util.ArrayList jdField_a_of_type_JavaUtilArrayList = new java.util.ArrayList();
/*  43:    */  
/*  44:    */  private final lx jdField_a_of_type_Lx;
/*  45:    */  
/*  46: 46 */  private int jdField_b_of_type_Int = -12312323;
/*  47:    */  
/*  48:    */  private boolean jdField_b_of_type_Boolean;
/*  49:    */  
/*  50:    */  private boolean jdField_c_of_type_Boolean;
/*  51:    */  
/*  52:    */  private int jdField_c_of_type_Int;
/*  53:    */  private final cJ jdField_a_of_type_CJ;
/*  54:    */  
/*  55:    */  public t(StateInterface paramStateInterface)
/*  56:    */  {
/*  57: 57 */    this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  58: 58 */    this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*  59:    */    
/*  60: 60 */    this.jdField_a_of_type_CJ = new cJ(this);
/*  61: 61 */    this.jdField_a_of_type_Lx = new lx(this);
/*  62:    */  }
/*  63:    */  
/*  66:    */  public void cleanUpOnEntityDelete() {}
/*  67:    */  
/*  69:    */  public int getId()
/*  70:    */  {
/*  71: 71 */    return this.jdField_b_of_type_Int;
/*  72:    */  }
/*  73:    */  
/*  75:    */  public final org.schema.game.network.objects.NetworkClientChannel a()
/*  76:    */  {
/*  77: 77 */    return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel;
/*  78:    */  }
/*  79:    */  
/*  81:    */  public StateInterface getState()
/*  82:    */  {
/*  83: 83 */    return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  84:    */  }
/*  85:    */  
/*  88:    */  public void initFromNetworkObject(NetworkObject paramNetworkObject)
/*  89:    */  {
/*  90: 90 */    setId(((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.id.get()).intValue());
/*  91: 91 */    this.jdField_a_of_type_Int = ((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.playerId.get()).intValue();
/*  92:    */  }
/*  93:    */  
/*  96:    */  public void initialize() {}
/*  97:    */  
/*  99:    */  public final boolean a()
/* 100:    */  {
/* 101:101 */    return (this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel != null) && (((Boolean)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.connectionReady.get()).booleanValue());
/* 102:    */  }
/* 103:    */  
/* 104:    */  public boolean isMarkedForDeleteVolatile()
/* 105:    */  {
/* 106:106 */    return this.jdField_b_of_type_Boolean;
/* 107:    */  }
/* 108:    */  
/* 109:    */  public boolean isMarkedForDeleteVolatileSent()
/* 110:    */  {
/* 111:111 */    return this.jdField_c_of_type_Boolean;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public boolean isOnServer()
/* 115:    */  {
/* 116:116 */    return this.jdField_a_of_type_Boolean;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public void newNetworkObject()
/* 120:    */  {
/* 121:121 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel = new org.schema.game.network.objects.NetworkClientChannel(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface);
/* 122:    */  }
/* 123:    */  
/* 128:    */  public void setId(int paramInt)
/* 129:    */  {
/* 130:130 */    this.jdField_b_of_type_Int = paramInt;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public void setMarkedForDeleteVolatile(boolean paramBoolean)
/* 134:    */  {
/* 135:135 */    this.jdField_b_of_type_Boolean = paramBoolean;
/* 136:    */  }
/* 137:    */  
/* 139:    */  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/* 140:    */  {
/* 141:141 */    this.jdField_c_of_type_Boolean = paramBoolean;
/* 142:    */  }
/* 143:    */  
/* 147:    */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/* 148:    */  {
/* 149:149 */    setId(((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.id.get()).intValue());
/* 150:    */    
/* 151:151 */    this.jdField_a_of_type_Lx.c();
/* 152:    */    
/* 153:153 */    if (!isOnServer()) {
/* 154:154 */      for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsPosts.getReceiveBuffer().size(); paramNetworkObject++) {
/* 155:155 */        RemoteFactionNewsPost localRemoteFactionNewsPost = (RemoteFactionNewsPost)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsPosts.getReceiveBuffer().get(paramNetworkObject);
/* 156:156 */        synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 157:157 */          System.err.println("[FACTIONMANAGER] received news on vlient channel " + this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface + ": " + localRemoteFactionNewsPost.get());
/* 158:158 */          this.jdField_a_of_type_JavaUtilArrayList.add(localRemoteFactionNewsPost.get());
/* 159:    */        }
/* 160:    */      }
/* 161:161 */      this.jdField_a_of_type_CJ.a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel);
/* 162:    */    }
/* 163:    */    
/* 165:    */    Object localObject3;
/* 166:    */    
/* 167:167 */    if (isOnServer())
/* 168:    */    {
/* 169:169 */      ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a(this);
/* 170:    */      Object localObject2;
/* 171:171 */      for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.getReceiveBuffer().size(); paramNetworkObject++) {
/* 172:172 */        long l = ((Long)((RemoteLong)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.getReceiveBuffer().get(paramNetworkObject)).get()).longValue();
/* 173:    */        
/* 175:175 */        if (((localObject2 = (Sendable)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(this.jdField_a_of_type_Int)) != null) && ((localObject2 instanceof lE))) {
/* 176:176 */          ((lE)localObject2).a().a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel, l);
/* 177:    */        } else {
/* 178:178 */          System.err.println("[SERVER][ClientChannel] player not found: " + this.jdField_a_of_type_Int);
/* 179:    */        }
/* 180:    */      }
/* 181:    */      
/* 182:182 */      for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampRequests.getReceiveBuffer().size(); paramNetworkObject++) {
/* 183:183 */        localObject3 = (String)((RemoteString)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampRequests.getReceiveBuffer().get(paramNetworkObject)).get();
/* 184:    */        
/* 185:185 */        ??? = new File("./server-skins/" + (String)localObject3 + ".png");
/* 186:186 */        (
/* 187:187 */          localObject2 = new RemoteStringArray(2, this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel)).set(0, (String)localObject3);
/* 188:188 */        if (((File)???).exists()) {
/* 189:189 */          ((RemoteStringArray)localObject2).set(1, String.valueOf(((File)???).lastModified()));
/* 190:    */        } else {
/* 191:191 */          ((RemoteStringArray)localObject2).set(1, "0");
/* 192:    */        }
/* 193:193 */        this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampResponses.add((RemoteArray)localObject2);
/* 194:    */      }
/* 195:    */      
/* 197:197 */      for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.fileRequests.getReceiveBuffer().size(); paramNetworkObject++) {
/* 198:198 */        localObject3 = (String)((RemoteString)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.fileRequests.getReceiveBuffer().get(paramNetworkObject)).get();
/* 199:199 */        System.err.println("[SERVER] received File request for " + (String)localObject3);
/* 200:200 */        ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a(this, (String)localObject3);
/* 201:    */      }
/* 202:    */      
/* 204:204 */      ((GameServerController)getState().getController()).a().fromNetwork(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel);return;
/* 205:    */    }
/* 206:206 */    for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampResponses.getReceiveBuffer().size(); paramNetworkObject++) {
/* 207:207 */      localObject3 = (RemoteStringArray)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampResponses.getReceiveBuffer().get(paramNetworkObject);
/* 208:    */      
/* 209:209 */      ((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a().a((RemoteStringArray)localObject3);
/* 210:    */    }
/* 211:    */    
/* 212:212 */    ((x)getState().getController()).a().a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel);
/* 213:    */  }
/* 214:    */  
/* 221:    */  public void updateLocal(xq arg1)
/* 222:    */  {
/* 223:    */    Map.Entry localEntry;
/* 224:    */    
/* 229:229 */    if (!this.jdField_a_of_type_Boolean)
/* 230:    */    {
/* 231:231 */      if (((??? = (ct)getState()).a().h() != 0) && (???.a().h() != this.jdField_c_of_type_Int)) {
/* 232:232 */        this.jdField_a_of_type_JavaUtilTreeSet.clear();
/* 233:    */        
/* 234:234 */        a();
/* 235:235 */        this.jdField_c_of_type_Int = ???.a().h();
/* 236:    */      }
/* 237:    */      
/* 238:238 */      Object localObject4 = null; Object localObject5; if (!(??? = this.jdField_a_of_type_CJ).jdField_a_of_type_JavaUtilArrayList.isEmpty()) synchronized (???.jdField_a_of_type_JavaUtilArrayList) { for (; !???.jdField_a_of_type_JavaUtilArrayList.isEmpty(); ???.jdField_a_of_type_T.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.mapRequests.add(new RemoteMapEntryRequest((cI)localObject5, false))) localObject5 = (cI)???.jdField_a_of_type_JavaUtilArrayList.remove(0); } Object localObject2; if (!???.b.isEmpty()) synchronized (???.b) { for (; !???.b.isEmpty(); ((cH)localObject5).jdField_a_of_type_ArrayOfCD = null) { localObject5 = (cH)???.b.remove(0);localObject4 = null; if ((localObject2 = (cA)???.jdField_a_of_type_JavaUtilHashMap.get(((cH)localObject5).jdField_a_of_type_Q)) == null) { (localObject2 = new cB()).jdField_a_of_type_Q = ((cH)localObject5).jdField_a_of_type_Q;???.jdField_a_of_type_JavaUtilHashMap.put(((cH)localObject5).jdField_a_of_type_Q, localObject2); } ((cA)localObject2).jdField_a_of_type_ArrayOfCD = ((cH)localObject5).jdField_a_of_type_ArrayOfCD;System.err.println("MAP DATA RECEIVED SIZE: " + ((cH)localObject5).jdField_a_of_type_ArrayOfCD.length); for (int i = 0; i < ((cH)localObject5).jdField_a_of_type_ArrayOfCD.length; i++) System.err.println("MAP DATA RECEIVED " + localObject5.jdField_a_of_type_ArrayOfCD[i]); } } long l; if ((???.jdField_a_of_type_T.getState() instanceof ClientState)) { localEntry = null; if ((((ct)???.jdField_a_of_type_T.getState()).a().a.a.a.jdField_c_of_type_Boolean) && ((l = System.currentTimeMillis()) > 60000L)) for (localObject2 = ???.jdField_a_of_type_ItUnimiDsiFastutilObjectsObject2LongOpenHashMap.entrySet().iterator(); ((Iterator)localObject2).hasNext();) { localEntry = (Map.Entry)((Iterator)localObject2).next(); if (l - ((Long)localEntry.getValue()).longValue() > 300000L) { ???.a(new q((q)localEntry.getKey()));???.jdField_a_of_type_ItUnimiDsiFastutilObjectsObject2LongOpenHashMap.put(localEntry.getKey(), l);
/* 239:    */            } } }
/* 240:240 */      if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 241:241 */        synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 242:242 */          while (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty())
/* 243:    */          {
/* 245:245 */            localObject2 = (lW)this.jdField_a_of_type_JavaUtilArrayList.remove(0);
/* 246:    */            
/* 247:247 */            if ((!this.jdField_a_of_type_JavaUtilTreeSet.isEmpty()) && 
/* 248:248 */              (((lW)this.jdField_a_of_type_JavaUtilTreeSet.iterator().next()).b() != ((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().h())) {
/* 249:249 */              System.err.println("[CLIENT] RECEIVED NEWS OF OTHER FACTION -> cleaning news");
/* 250:250 */              this.jdField_a_of_type_JavaUtilTreeSet.clear();
/* 251:    */            }
/* 252:    */            
/* 253:253 */            this.jdField_a_of_type_JavaUtilTreeSet.add(localObject2);
/* 254:    */            
/* 255:255 */            setChanged();
/* 256:256 */            notifyObservers();
/* 257:    */          }
/* 258:    */        }
/* 259:    */      }
/* 260:    */    }
/* 261:    */    
/* 269:    */    try
/* 270:    */    {
/* 271:271 */      this.jdField_a_of_type_Lx.d();
/* 272:272 */    } catch (IOException localIOException) { 
/* 273:    */      
/* 275:275 */        localIOException.printStackTrace();this.jdField_a_of_type_Lx.b_();
/* 276:    */    }
/* 277:276 */    if ((!this.jdField_a_of_type_Boolean) && 
/* 278:277 */      (!getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Int))) {
/* 279:278 */      setMarkedForDeleteVolatile(true);
/* 280:279 */      System.err.println("[SERVER][CLIENTCHANNEL] DELETING: no more player attached");
/* 281:280 */      localEntry = null;this.jdField_a_of_type_Lx.b_();
/* 282:    */    }
/* 283:    */  }
/* 284:    */  
/* 288:    */  public final void a()
/* 289:    */  {
/* 290:289 */    if (this.jdField_a_of_type_JavaUtilTreeSet.isEmpty()) {
/* 291:290 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.add(new RemoteLong(Long.valueOf(System.currentTimeMillis()), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));return;
/* 292:    */    }
/* 293:    */    
/* 294:293 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.add(new RemoteLong(Long.valueOf(((lW)this.jdField_a_of_type_JavaUtilTreeSet.iterator().next()).a()), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));
/* 295:    */  }
/* 296:    */  
/* 297:    */  public void updateToFullNetworkObject()
/* 298:    */  {
/* 299:298 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.playerId.set(Integer.valueOf(this.jdField_a_of_type_Int));
/* 300:299 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.id.set(Integer.valueOf(getId()));
/* 301:    */    
/* 302:301 */    updateToNetworkObject();
/* 303:    */  }
/* 304:    */  
/* 305:    */  public void updateToNetworkObject() {
/* 306:305 */    if (isOnServer())
/* 307:306 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.connectionReady.set(Boolean.valueOf(true));
/* 308:    */  }
/* 309:    */  
/* 310:    */  public final void a(String paramString) {
/* 311:310 */    this.jdField_a_of_type_Lx.b(paramString);
/* 312:311 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.fileRequests.add(new RemoteString(paramString, this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));
/* 313:    */  }
/* 314:    */  
/* 315:314 */  public final void b(String paramString) { this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampRequests.add(new RemoteString(paramString, this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel)); }
/* 316:    */  
/* 319:    */  public final int a()
/* 320:    */  {
/* 321:320 */    return this.jdField_a_of_type_Int;
/* 322:    */  }
/* 323:    */  
/* 325:    */  public final void a(int paramInt)
/* 326:    */  {
/* 327:326 */    this.jdField_a_of_type_Int = paramInt;
/* 328:    */  }
/* 329:    */  
/* 331:    */  public final lx a()
/* 332:    */  {
/* 333:332 */    return this.jdField_a_of_type_Lx;
/* 334:    */  }
/* 335:    */  
/* 337:    */  public final java.util.TreeSet a()
/* 338:    */  {
/* 339:338 */    return this.jdField_a_of_type_JavaUtilTreeSet;
/* 340:    */  }
/* 341:    */  
/* 343:    */  public void destroyPersistent() {}
/* 344:    */  
/* 346:    */  public boolean isMarkedForPermanentDelete()
/* 347:    */  {
/* 348:347 */    return false;
/* 349:    */  }
/* 350:    */  
/* 353:    */  public void markedForPermanentDelete(boolean paramBoolean) {}
/* 354:    */  
/* 357:    */  public final cJ a()
/* 358:    */  {
/* 359:358 */    return this.jdField_a_of_type_CJ;
/* 360:    */  }
/* 361:    */  
/* 362:    */  public boolean isUpdatable() {
/* 363:362 */    return true;
/* 364:    */  }
/* 365:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     t
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */