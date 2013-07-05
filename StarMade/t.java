/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Observable;
/*     */ import java.util.TreeSet;
/*     */ import org.schema.game.common.controller.elements.missile.MissileController;
/*     */ import org.schema.game.network.objects.NetworkClientChannel;
/*     */ import org.schema.game.network.objects.remote.RemoteFactionNewsPost;
/*     */ import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
/*     */ import org.schema.game.network.objects.remote.RemoteMapEntryRequest;
/*     */ import org.schema.game.network.objects.remote.RemoteMapEntryRequestBuffer;
/*     */ import org.schema.game.server.controller.GameServerController;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBoolean;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ import org.schema.schine.network.objects.remote.RemoteLong;
/*     */ import org.schema.schine.network.objects.remote.RemoteString;
/*     */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public class t extends Observable
/*     */   implements Sendable
/*     */ {
/*     */   private StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   private final boolean jdField_a_of_type_Boolean;
/*  35 */   private int jdField_a_of_type_Int = -121212;
/*     */   private NetworkClientChannel jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel;
/*  39 */   private final TreeSet jdField_a_of_type_JavaUtilTreeSet = new TreeSet();
/*     */ 
/*  42 */   private final ArrayList jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*     */   private final lx jdField_a_of_type_Lx;
/*  46 */   private int jdField_b_of_type_Int = -12312323;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private boolean jdField_c_of_type_Boolean;
/*     */   private int jdField_c_of_type_Int;
/*     */   private final cJ jdField_a_of_type_CJ;
/*     */ 
/*     */   public t(StateInterface paramStateInterface)
/*     */   {
/*  57 */     this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  58 */     this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*     */ 
/*  60 */     this.jdField_a_of_type_CJ = new cJ(this);
/*  61 */     this.jdField_a_of_type_Lx = new lx(this);
/*     */   }
/*     */ 
/*     */   public void cleanUpOnEntityDelete()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  71 */     return this.jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final NetworkClientChannel a()
/*     */   {
/*  77 */     return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel;
/*     */   }
/*     */ 
/*     */   public StateInterface getState()
/*     */   {
/*  83 */     return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   }
/*     */ 
/*     */   public void initFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/*  90 */     setId(((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.id.get()).intValue());
/*  91 */     this.jdField_a_of_type_Int = ((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.playerId.get()).intValue();
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 101 */     return (this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel != null) && (((Boolean)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.connectionReady.get()).booleanValue());
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatile()
/*     */   {
/* 106 */     return this.jdField_b_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatileSent()
/*     */   {
/* 111 */     return this.jdField_c_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public boolean isOnServer()
/*     */   {
/* 116 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject()
/*     */   {
/* 121 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel = new NetworkClientChannel(this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface);
/*     */   }
/*     */ 
/*     */   public void setId(int paramInt)
/*     */   {
/* 130 */     this.jdField_b_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatile(boolean paramBoolean)
/*     */   {
/* 135 */     this.jdField_b_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/*     */   {
/* 141 */     this.jdField_c_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 149 */     setId(((Integer)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.id.get()).intValue());
/*     */ 
/* 151 */     this.jdField_a_of_type_Lx.c();
/*     */ 
/* 153 */     if (!isOnServer()) {
/* 154 */       for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsPosts.getReceiveBuffer().size(); paramNetworkObject++) {
/* 155 */         RemoteFactionNewsPost localRemoteFactionNewsPost = (RemoteFactionNewsPost)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsPosts.getReceiveBuffer().get(paramNetworkObject);
/* 156 */         synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 157 */           System.err.println("[FACTIONMANAGER] received news on vlient channel " + this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface + ": " + localRemoteFactionNewsPost.get());
/* 158 */           this.jdField_a_of_type_JavaUtilArrayList.add(localRemoteFactionNewsPost.get());
/*     */         }
/*     */       }
/* 161 */       this.jdField_a_of_type_CJ.a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel);
/*     */     }
/*     */     Object localObject3;
/* 167 */     if (isOnServer())
/*     */     {
/* 169 */       ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a(this);
/*     */       Object localObject2;
/* 171 */       for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.getReceiveBuffer().size(); paramNetworkObject++) {
/* 172 */         long l = ((Long)((RemoteLong)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.getReceiveBuffer().get(paramNetworkObject)).get()).longValue();
/*     */ 
/* 175 */         if (((
/* 175 */           localObject2 = (Sendable)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(this.jdField_a_of_type_Int)) != null) && 
/* 175 */           ((localObject2 instanceof lE)))
/* 176 */           ((lE)localObject2).a().a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel, l);
/*     */         else {
/* 178 */           System.err.println("[SERVER][ClientChannel] player not found: " + this.jdField_a_of_type_Int);
/*     */         }
/*     */       }
/*     */ 
/* 182 */       for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampRequests.getReceiveBuffer().size(); paramNetworkObject++) {
/* 183 */         localObject3 = (String)((RemoteString)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampRequests.getReceiveBuffer().get(paramNetworkObject)).get();
/*     */ 
/* 185 */         ??? = new File("./server-skins/" + (String)localObject3 + ".png");
/* 186 */         (
/* 187 */           localObject2 = new RemoteStringArray(2, this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel))
/* 187 */           .set(0, (String)localObject3);
/* 188 */         if (((File)???).exists())
/* 189 */           ((RemoteStringArray)localObject2).set(1, String.valueOf(((File)???).lastModified()));
/*     */         else {
/* 191 */           ((RemoteStringArray)localObject2).set(1, "0");
/*     */         }
/* 193 */         this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampResponses.add((RemoteArray)localObject2);
/*     */       }
/*     */ 
/* 197 */       for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.fileRequests.getReceiveBuffer().size(); paramNetworkObject++) {
/* 198 */         localObject3 = (String)((RemoteString)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.fileRequests.getReceiveBuffer().get(paramNetworkObject)).get();
/* 199 */         System.err.println("[SERVER] received File request for " + (String)localObject3);
/* 200 */         ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a(this, (String)localObject3);
/*     */       }
/*     */ 
/* 204 */       ((GameServerController)getState().getController()).a().fromNetwork(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel); return;
/*     */     }
/* 206 */     for (paramNetworkObject = 0; paramNetworkObject < this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampResponses.getReceiveBuffer().size(); paramNetworkObject++) {
/* 207 */       localObject3 = (RemoteStringArray)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampResponses.getReceiveBuffer().get(paramNetworkObject);
/*     */ 
/* 209 */       ((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a().a((RemoteStringArray)localObject3);
/*     */     }
/*     */ 
/* 212 */     ((x)getState().getController()).a().a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel);
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq arg1)
/*     */   {
/*     */     Map.Entry localEntry;
/* 229 */     if (!this.jdField_a_of_type_Boolean)
/*     */     {
/* 231 */       if ((( = (ct)getState())
/* 231 */         .a().h() != 0) && (???.a().h() != this.jdField_c_of_type_Int)) {
/* 232 */         this.jdField_a_of_type_JavaUtilTreeSet.clear();
/*     */ 
/* 234 */         a();
/* 235 */         this.jdField_c_of_type_Int = ???.a().h();
/*     */       }
/*     */ 
/* 238 */       Object localObject4 = null;
/*     */       Object localObject5;
/* 238 */       if (!( = this.jdField_a_of_type_CJ).jdField_a_of_type_JavaUtilArrayList.isEmpty()) synchronized (???.jdField_a_of_type_JavaUtilArrayList) { for (; !???.jdField_a_of_type_JavaUtilArrayList.isEmpty(); ???.jdField_a_of_type_T.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.mapRequests.add(new RemoteMapEntryRequest((cI)localObject5, false))) localObject5 = (cI)???.jdField_a_of_type_JavaUtilArrayList.remove(0);
/*     */         }
/* 238 */       Object localObject2;
/* 238 */       if (!???.b.isEmpty()) synchronized (???.b) { for (; !???.b.isEmpty(); ((cH)localObject5).jdField_a_of_type_ArrayOfCD = null) { localObject5 = (cH)???.b.remove(0); localObject4 = null; if ((localObject2 = (cA)???.jdField_a_of_type_JavaUtilHashMap.get(((cH)localObject5).jdField_a_of_type_Q)) == null) { (localObject2 = new cB()).jdField_a_of_type_Q = ((cH)localObject5).jdField_a_of_type_Q; ???.jdField_a_of_type_JavaUtilHashMap.put(((cH)localObject5).jdField_a_of_type_Q, localObject2); } ((cA)localObject2).jdField_a_of_type_ArrayOfCD = ((cH)localObject5).jdField_a_of_type_ArrayOfCD; System.err.println("MAP DATA RECEIVED SIZE: " + ((cH)localObject5).jdField_a_of_type_ArrayOfCD.length); for (int i = 0; i < ((cH)localObject5).jdField_a_of_type_ArrayOfCD.length; i++) System.err.println("MAP DATA RECEIVED " + localObject5.jdField_a_of_type_ArrayOfCD[i]);
/*     */           }
/*     */         }
/* 238 */       long l;
/* 238 */       if ((???.jdField_a_of_type_T.getState() instanceof ClientState)) { localEntry = null; if ((((ct)???.jdField_a_of_type_T.getState()).a().a.a.a.jdField_c_of_type_Boolean) && ((l = System.currentTimeMillis()) > 60000L)) for (localObject2 = ???.jdField_a_of_type_ItUnimiDsiFastutilObjectsObject2LongOpenHashMap.entrySet().iterator(); ((Iterator)localObject2).hasNext(); ) { localEntry = (Map.Entry)((Iterator)localObject2).next(); if (l - ((Long)localEntry.getValue()).longValue() > 300000L) { ???.a(new q((q)localEntry.getKey())); ???.jdField_a_of_type_ItUnimiDsiFastutilObjectsObject2LongOpenHashMap.put(localEntry.getKey(), l); } }
/*     */       }
/* 240 */       if (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty()) {
/* 241 */         synchronized (this.jdField_a_of_type_JavaUtilArrayList) {
/* 242 */           while (!this.jdField_a_of_type_JavaUtilArrayList.isEmpty())
/*     */           {
/* 245 */             localObject2 = (lW)this.jdField_a_of_type_JavaUtilArrayList.remove(0);
/*     */ 
/* 247 */             if ((!this.jdField_a_of_type_JavaUtilTreeSet.isEmpty()) && 
/* 248 */               (((lW)this.jdField_a_of_type_JavaUtilTreeSet.iterator().next()).b() != ((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().h())) {
/* 249 */               System.err.println("[CLIENT] RECEIVED NEWS OF OTHER FACTION -> cleaning news");
/* 250 */               this.jdField_a_of_type_JavaUtilTreeSet.clear();
/*     */             }
/*     */ 
/* 253 */             this.jdField_a_of_type_JavaUtilTreeSet.add(localObject2);
/*     */ 
/* 255 */             setChanged();
/* 256 */             notifyObservers();
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 271 */       this.jdField_a_of_type_Lx.d();
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 275 */       localIOException.printStackTrace();
/*     */ 
/* 274 */       this.jdField_a_of_type_Lx.b_();
/*     */     }
/* 276 */     if ((!this.jdField_a_of_type_Boolean) && 
/* 277 */       (!getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_a_of_type_Int))) {
/* 278 */       setMarkedForDeleteVolatile(true);
/* 279 */       System.err.println("[SERVER][CLIENTCHANNEL] DELETING: no more player attached");
/* 280 */       localEntry = null; this.jdField_a_of_type_Lx.b_();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 289 */     if (this.jdField_a_of_type_JavaUtilTreeSet.isEmpty()) {
/* 290 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.add(new RemoteLong(Long.valueOf(System.currentTimeMillis()), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel)); return;
/*     */     }
/*     */ 
/* 293 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.factionNewsRequests.add(new RemoteLong(Long.valueOf(((lW)this.jdField_a_of_type_JavaUtilTreeSet.iterator().next()).a()), this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));
/*     */   }
/*     */ 
/*     */   public void updateToFullNetworkObject()
/*     */   {
/* 298 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.playerId.set(Integer.valueOf(this.jdField_a_of_type_Int));
/* 299 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.id.set(Integer.valueOf(getId()));
/*     */ 
/* 301 */     updateToNetworkObject();
/*     */   }
/*     */ 
/*     */   public void updateToNetworkObject() {
/* 305 */     if (isOnServer())
/* 306 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.connectionReady.set(Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public final void a(String paramString) {
/* 310 */     this.jdField_a_of_type_Lx.b(paramString);
/* 311 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.fileRequests.add(new RemoteString(paramString, this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));
/*     */   }
/*     */   public final void b(String paramString) {
/* 314 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel.timeStampRequests.add(new RemoteString(paramString, this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel));
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/* 320 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt)
/*     */   {
/* 326 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final lx a()
/*     */   {
/* 332 */     return this.jdField_a_of_type_Lx;
/*     */   }
/*     */ 
/*     */   public final TreeSet a()
/*     */   {
/* 338 */     return this.jdField_a_of_type_JavaUtilTreeSet;
/*     */   }
/*     */ 
/*     */   public void destroyPersistent()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForPermanentDelete()
/*     */   {
/* 347 */     return false;
/*     */   }
/*     */ 
/*     */   public void markedForPermanentDelete(boolean paramBoolean)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final cJ a()
/*     */   {
/* 358 */     return this.jdField_a_of_type_CJ;
/*     */   }
/*     */ 
/*     */   public boolean isUpdatable() {
/* 362 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     t
 * JD-Core Version:    0.6.2
 */