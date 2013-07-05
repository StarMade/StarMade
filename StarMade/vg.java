/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.zip.Deflater;
/*      */ import java.util.zip.Inflater;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.game.common.controller.database.DatabaseIndex;
/*      */ import org.schema.game.common.data.world.Universe;
/*      */ import org.schema.game.network.objects.NetworkGameState;
/*      */ import org.schema.game.server.controller.GameServerController;
/*      */ import org.schema.game.server.data.PlayerNotFountException;
/*      */ import org.schema.schine.network.ChatSystem;
/*      */ import org.schema.schine.network.ClientIdNotFoundException;
/*      */ import org.schema.schine.network.NetworkProcessor;
/*      */ import org.schema.schine.network.RegisteredClientOnServer;
/*      */ import org.schema.schine.network.objects.Sendable;
/*      */ import org.schema.schine.network.objects.remote.RemoteFloat;
/*      */ import org.schema.schine.network.server.ServerState;
/*      */ 
/*      */ public final class vg extends ServerState
/*      */   implements cy, mu, vd, vf
/*      */ {
/*      */   private static final String jdField_g_of_type_JavaLangString;
/*      */   public static final String a;
/*      */   public static final String b;
/*      */   public static final String c;
/*      */   public static final String d;
/*      */   public static final String e;
/*      */   public static final String f;
/*      */   public static final byte[] a;
/*      */   public static Deflater a;
/*      */   public final IntOpenHashSet a;
/*      */   public static Inflater a;
/*      */   public static int a;
/*      */   public static int b;
/*      */   public static int c;
/*      */   public static boolean a;
/*      */   public static int d;
/*      */   public static int e;
/*      */   public static int f;
/*      */   public static vg a;
/*      */   private static byte[] jdField_b_of_type_ArrayOfByte;
/*      */   public static int g;
/*      */   public static int h;
/*      */   public static int i;
/*      */   public static int j;
/*      */   public final HashMap a;
/*  125 */   private final ObjectArrayList jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
/*      */ 
/*  127 */   private final HashSet jdField_a_of_type_JavaUtilHashSet = new HashSet();
/*  128 */   private final HashMap jdField_b_of_type_JavaUtilHashMap = new HashMap();
/*      */   private final Universe jdField_a_of_type_OrgSchemaGameCommonDataWorldUniverse;
/*      */   private ChatSystem jdField_a_of_type_OrgSchemaSchineNetworkChatSystem;
/*  131 */   private final ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*      */ 
/*  134 */   private final ArrayList jdField_c_of_type_JavaUtilArrayList = new ArrayList();
/*  135 */   private final ArrayList jdField_d_of_type_JavaUtilArrayList = new ArrayList();
/*  136 */   private final ArrayList jdField_e_of_type_JavaUtilArrayList = new ArrayList();
/*  137 */   private final ArrayList jdField_f_of_type_JavaUtilArrayList = new ArrayList();
/*  138 */   private final ArrayList jdField_g_of_type_JavaUtilArrayList = new ArrayList();
/*      */   private jW jdField_a_of_type_JW;
/*      */   private final vR jdField_a_of_type_VR;
/*  141 */   private final jm jdField_a_of_type_Jm = new jm("SERVER");
/*      */ 
/*  143 */   private final ObjectArrayFIFOQueue jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue = new ObjectArrayFIFOQueue();
/*      */   private kC jdField_a_of_type_KC;
/*  148 */   private final HashMap jdField_c_of_type_JavaUtilHashMap = new HashMap();
/*  149 */   private final HashMap jdField_d_of_type_JavaUtilHashMap = new HashMap();
/*  150 */   private final HashMap jdField_e_of_type_JavaUtilHashMap = new HashMap();
/*      */ 
/*  152 */   private ArrayList jdField_h_of_type_JavaUtilArrayList = new ArrayList();
/*      */ 
/*  154 */   private final ArrayList jdField_i_of_type_JavaUtilArrayList = new ArrayList();
/*      */ 
/*  156 */   private HashSet jdField_b_of_type_JavaUtilHashSet = new HashSet();
/*  157 */   private HashSet jdField_c_of_type_JavaUtilHashSet = new HashSet();
/*  158 */   private HashSet jdField_d_of_type_JavaUtilHashSet = new HashSet();
/*      */ 
/*  160 */   private final HashSet jdField_e_of_type_JavaUtilHashSet = new HashSet();
/*  161 */   private final HashSet jdField_f_of_type_JavaUtilHashSet = new HashSet();
/*      */ 
/*  163 */   private final HashSet jdField_g_of_type_JavaUtilHashSet = new HashSet();
/*  164 */   private final HashSet jdField_h_of_type_JavaUtilHashSet = new HashSet();
/*  165 */   private byte[] jdField_c_of_type_ArrayOfByte = new byte[1048576];
/*  166 */   private ByteBuffer jdField_a_of_type_JavaNioByteBuffer = BufferUtils.createByteBuffer(1048576);
/*      */   private lg jdField_a_of_type_Lg;
/*  173 */   private final HashSet jdField_i_of_type_JavaUtilHashSet = new HashSet();
/*      */   private long jdField_a_of_type_Long;
/*      */   private long jdField_b_of_type_Long;
/*      */   private long jdField_c_of_type_Long;
/*      */   private int k;
/*      */   private boolean jdField_b_of_type_Boolean;
/*  185 */   private final ArrayList j = new ArrayList();
/*      */   private final DatabaseIndex jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex;
/*      */   private final vi jdField_a_of_type_Vi;
/*      */   private final tJ jdField_a_of_type_TJ;
/*      */   public ObjectArrayFIFOQueue a;
/*  195 */   private final ObjectArrayList jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
/*      */   public final ArrayList a;
/*      */   private String jdField_h_of_type_JavaLangString;
/*      */   private String jdField_i_of_type_JavaLangString;
/*      */   private byte[] jdField_d_of_type_ArrayOfByte;
/*      */   private byte[] jdField_e_of_type_ArrayOfByte;
/*      */ 
/*      */   public vg()
/*      */   {
/*   84 */     this.jdField_a_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet = new IntOpenHashSet();
/*      */ 
/*  123 */     this.jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*      */ 
/*  197 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*      */ 
/*  209 */     this.jdField_a_of_type_Long = System.currentTimeMillis();
/*      */ 
/*  211 */     vo.a();
/*      */     try {
/*  213 */       vo.b();
/*      */     }
/*      */     catch (IOException localIOException1) {
/*  216 */       localIOException1.printStackTrace();
/*      */     }
/*      */ 
/*  217 */     this.jdField_a_of_type_Vi = new vi(this, (byte)0);
/*  218 */     this.jdField_a_of_type_Vi.setPriority(3);
/*  219 */     this.jdField_a_of_type_Vi.start();
/*      */ 
/*  221 */     this.jdField_a_of_type_KC = kC.jdField_a_of_type_KC;
/*      */ 
/*  223 */     this.jdField_a_of_type_JW = new jW(this);
/*      */ 
/*  225 */     this.jdField_a_of_type_TJ = new tJ(this);
/*      */ 
/*  227 */     this.jdField_a_of_type_OrgSchemaGameCommonDataWorldUniverse = new Universe(this, 3465436L);
/*  228 */     boolean bool = DatabaseIndex.a();
/*  229 */     this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex = new DatabaseIndex();
/*  230 */     if (!bool) {
/*  231 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex.a();
/*      */     } else {
/*  233 */       this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex.c();
/*      */       try {
/*  235 */         this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex.b();
/*      */       }
/*      */       catch (IOException localIOException2) {
/*  238 */         localIOException2.printStackTrace();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  241 */     this.jdField_a_of_type_VR = new vR(this);
/*      */ 
/*  243 */     jdField_a_of_type_Vg = this;
/*      */   }
/*      */ 
/*      */   public final void chat(ChatSystem paramChatSystem, String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/*  254 */     this.jdField_e_of_type_JavaUtilArrayList.add(paramChatSystem.getOwnerStateId() + ": " + paramString1);
/*      */   }
/*      */ 
/*      */   public final ArrayList a()
/*      */   {
/*  260 */     return this.jdField_h_of_type_JavaUtilArrayList;
/*      */   }
/*      */ 
/*      */   public final HashSet a()
/*      */   {
/*  266 */     return this.jdField_a_of_type_JavaUtilHashSet;
/*      */   }
/*      */ 
/*      */   public final HashMap a()
/*      */   {
/*  272 */     return this.jdField_b_of_type_JavaUtilHashMap;
/*      */   }
/*      */   public final HashSet b() {
/*  275 */     return this.jdField_e_of_type_JavaUtilHashSet;
/*      */   }
/*      */ 
/*      */   public final HashSet c() {
/*  279 */     return this.jdField_g_of_type_JavaUtilHashSet;
/*      */   }
/*      */ 
/*      */   public final ArrayList b()
/*      */   {
/*  286 */     return this.jdField_i_of_type_JavaUtilArrayList;
/*      */   }
/*      */ 
/*      */   public final ChatSystem getChat()
/*      */   {
/*  294 */     return this.jdField_a_of_type_OrgSchemaSchineNetworkChatSystem;
/*      */   }
/*      */ 
/*      */   public final String[] getCommandPrefixes() {
/*  298 */     return null;
/*      */   }
/*      */ 
/*      */   public final GameServerController a()
/*      */   {
/*  306 */     return (GameServerController)super.getController();
/*      */   }
/*      */ 
/*      */   public final byte[] getDataBuffer() {
/*  310 */     return this.jdField_c_of_type_ArrayOfByte;
/*      */   }
/*      */   public final ArrayList c() {
/*  313 */     return this.jdField_d_of_type_JavaUtilArrayList;
/*      */   }
/*      */ 
/*      */   public final kC a()
/*      */   {
/*  319 */     return this.jdField_a_of_type_KC;
/*      */   }
/*      */ 
/*      */   public final lg a()
/*      */   {
/*  325 */     return this.jdField_a_of_type_Lg;
/*      */   }
/*      */ 
/*      */   public final float getMaxMsBetweenUpdates() {
/*  329 */     return 100.0F;
/*      */   }
/*      */ 
/*      */   public final lE a(String paramString)
/*      */   {
/*      */     lE locallE;
/*  334 */     if ((
/*  334 */       locallE = (lE)this.jdField_d_of_type_JavaUtilHashMap.get(paramString)) != null)
/*      */     {
/*  335 */       return locallE;
/*      */     }
/*  337 */     throw new PlayerNotFountException(paramString);
/*      */   }
/*      */ 
/*      */   public final lE a(int paramInt)
/*      */   {
/*      */     lE locallE;
/*  341 */     if ((
/*  341 */       locallE = (lE)this.jdField_e_of_type_JavaUtilHashMap.get(Integer.valueOf(paramInt))) != null)
/*      */     {
/*  342 */       return locallE;
/*      */     }
/*  344 */     throw new PlayerNotFountException("CLIENT-ID(" + paramInt + ") ");
/*      */   }
/*      */ 
/*      */   public final NetworkProcessor getProcessor(int paramInt)
/*      */   {
/*  350 */     return ((RegisteredClientOnServer)getClients().get(Integer.valueOf(paramInt))).getProcessor();
/*      */   }
/*      */ 
/*      */   public final ArrayList d()
/*      */   {
/*  356 */     return this.jdField_f_of_type_JavaUtilArrayList;
/*      */   }
/*      */ 
/*      */   public final jW a()
/*      */   {
/*  361 */     return this.jdField_a_of_type_JW;
/*      */   }
/*      */ 
/*      */   public final ArrayList e()
/*      */   {
/*  368 */     return this.jdField_g_of_type_JavaUtilArrayList;
/*      */   }
/*      */ 
/*      */   public final HashSet d()
/*      */   {
/*  375 */     return this.jdField_i_of_type_JavaUtilHashSet;
/*      */   }
/*      */ 
/*      */   public final jm a()
/*      */   {
/*  383 */     return this.jdField_a_of_type_Jm;
/*      */   }
/*      */ 
/*      */   public final Universe a()
/*      */   {
/*  391 */     return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldUniverse;
/*      */   }
/*      */ 
/*      */   public final float getVersion()
/*      */   {
/*  396 */     return sG.a;
/*      */   }
/*      */ 
/*      */   public final void a() {
/*  400 */     long l1 = System.currentTimeMillis();
/*      */     Sendable localSendable3;
/*      */     vg localvg;
/*  401 */     if (!this.jdField_b_of_type_JavaUtilHashSet.isEmpty()) {
/*  402 */       HashSet localHashSet1 = new HashSet(this.jdField_b_of_type_JavaUtilHashSet.size());
/*  403 */       synchronized (this.jdField_b_of_type_JavaUtilHashSet) {
/*  404 */         localHashSet1.addAll(this.jdField_b_of_type_JavaUtilHashSet);
/*      */       }
/*  406 */       for (??? = localObject2.iterator(); ((Iterator)???).hasNext(); ) { Sendable localSendable2 = (Sendable)((Iterator)???).next();
/*      */ 
/*  408 */         localSendable3 = localSendable2; localvg = this; if ((localSendable3 instanceof lE)) { localvg.jdField_d_of_type_JavaUtilHashMap.put(((lE)localSendable3).getName(), (lE)localSendable3); localvg.jdField_e_of_type_JavaUtilHashMap.put(Integer.valueOf(((lE)localSendable3).a()), (lE)localSendable3); localvg.jdField_a_of_type_VR.a().b((lE)localSendable3); } if ((localSendable3 instanceof SegmentController)) localvg.jdField_c_of_type_JavaUtilHashMap.put(((SegmentController)localSendable3).getUniqueIdentifier(), (SegmentController)localSendable3); if (((localSendable3 instanceof mF)) && (((mF)localSendable3).isGravitySource())) localvg.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add((mF)localSendable3);
/*      */ 
/*  410 */         if ((localSendable2 instanceof kc))
/*      */         {
/*      */           ka localka;
/*  413 */           if ((
/*  413 */             localka = ((kc)localSendable2).a()) != null)
/*      */           {
/*  414 */             localka.setServerSendableSegmentController((kc)localSendable2);
/*      */ 
/*  416 */             ((kc)localSendable2).a();
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  421 */         setChanged();
/*  422 */         notifyObservers(localSendable2);
/*      */       }
/*      */ 
/*  426 */       this.jdField_b_of_type_JavaUtilHashSet.clear();
/*      */     }
/*      */     long l2;
/*  429 */     if ((
/*  429 */       l2 = System.currentTimeMillis() - l1) > 
/*  429 */       10L) {
/*  430 */       System.err.println("[SERVER][UPDATE] WARNING: handleAddedAndRemovedObjects update took " + l2);
/*      */     }
/*      */ 
/*  433 */     if (!this.jdField_c_of_type_JavaUtilHashSet.isEmpty()) {
/*  434 */       HashSet localHashSet2 = new HashSet(this.jdField_c_of_type_JavaUtilHashSet.size());
/*  435 */       synchronized (this.jdField_c_of_type_JavaUtilHashSet) {
/*  436 */         localHashSet2.addAll(this.jdField_c_of_type_JavaUtilHashSet);
/*      */       }
/*  438 */       for (??? = localHashSet2.iterator(); ((Iterator)???).hasNext(); )
/*      */       {
/*      */         Sendable localSendable1;
/*  439 */         if (((
/*  439 */           localSendable1 = (Sendable)((Iterator)???).next()) instanceof Ag))
/*      */         {
/*  441 */           ((GameServerController)super.getController()).a((Ag)localSendable1, true);
/*      */         }
/*  443 */         localSendable3 = localSendable1; localvg = this; if ((localSendable3 instanceof lE)) { localvg.jdField_d_of_type_JavaUtilHashMap.remove(((lE)localSendable3).getName()); localvg.jdField_e_of_type_JavaUtilHashMap.remove(Integer.valueOf(((lE)localSendable3).a())); localvg.jdField_a_of_type_VR.a().a((lE)localSendable3); } if ((localSendable3 instanceof SegmentController)) localvg.jdField_c_of_type_JavaUtilHashMap.remove(((SegmentController)localSendable3).getUniqueIdentifier()); if (((localSendable3 instanceof mF)) && (((mF)localSendable3).isGravitySource())) localvg.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(localSendable3); if (localSendable3.isMarkedForPermanentDelete()) { if ((localSendable3 instanceof Ag)) localvg.jdField_a_of_type_VR.a(((Ag)localSendable3).getUniqueIdentifier()); localSendable3.destroyPersistent();
/*      */         }
/*      */ 
/*  446 */         setChanged();
/*  447 */         notifyObservers(localSendable1);
/*      */       }
/*      */ 
/*  451 */       this.jdField_c_of_type_JavaUtilHashSet.clear();
/*      */     }
/*      */   }
/*      */ 
/*  455 */   public final void b() { long l1 = System.currentTimeMillis();
/*  456 */     if (!this.jdField_d_of_type_JavaUtilHashSet.isEmpty()) {
/*  457 */       HashSet localHashSet = new HashSet();
/*  458 */       synchronized (this.jdField_d_of_type_JavaUtilHashSet) {
/*  459 */         localHashSet.addAll(this.jdField_d_of_type_JavaUtilHashSet);
/*      */       }
/*      */ 
/*  462 */       for (??? = localObject1.iterator(); ((Iterator)???).hasNext(); ) { Sendable localSendable = (Sendable)((Iterator)???).next();
/*      */ 
/*  464 */         setChanged();
/*  465 */         notifyObservers(localSendable);
/*      */       }
/*      */ 
/*  468 */       this.jdField_d_of_type_JavaUtilHashSet.clear();
/*      */     }
/*      */     long l2;
/*  471 */     if ((
/*  471 */       l2 = System.currentTimeMillis() - l1) > 
/*  471 */       10L)
/*  472 */       System.err.println("[SERVER][UPDATE] WARNING: needsNotifyObjects update took " + l2);
/*      */   }
/*      */ 
/*      */   public final void notifyOfAddedObject(Sendable paramSendable)
/*      */   {
/*  533 */     if ((paramSendable instanceof SegmentController))
/*      */     {
/*      */       SegmentController localSegmentController;
/*  535 */       if ((
/*  535 */         localSegmentController = (SegmentController)paramSendable)
/*  535 */         .getCreatorThread() == null) {
/*  536 */         localSegmentController.startCreatorThread();
/*      */       }
/*      */     }
/*      */ 
/*  540 */     synchronized (this.jdField_b_of_type_JavaUtilHashSet) {
/*  541 */       this.jdField_b_of_type_JavaUtilHashSet.add(paramSendable);
/*      */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void notifyOfRemovedObject(Sendable paramSendable) {
/*  548 */     synchronized (this.jdField_c_of_type_JavaUtilHashSet) {
/*  549 */       this.jdField_c_of_type_JavaUtilHashSet.add(paramSendable);
/*  550 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final String onAutoComplete(String paramString1, wB paramwB, String paramString2)
/*      */   {
/*  556 */     System.err.println("NO AUTOCOMPLETE ON SERVER");
/*  557 */     return paramString1;
/*      */   }
/*      */ 
/*      */   public final void onStringCommand(String paramString1, wB paramwB, String paramString2)
/*      */   {
/*  562 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */   public final void a(ChatSystem paramChatSystem)
/*      */   {
/*  576 */     this.jdField_a_of_type_OrgSchemaSchineNetworkChatSystem = paramChatSystem;
/*      */   }
/*      */ 
/*      */   public final void a(lg paramlg)
/*      */   {
/*  590 */     this.jdField_a_of_type_Lg = paramlg;
/*      */   }
/*      */ 
/*      */   public final void a(int paramInt1, String paramString, int paramInt2, Transform paramTransform, int paramInt3, tH paramtH)
/*      */   {
/*  683 */     this.jdField_a_of_type_Vi.a(paramInt1, paramString, paramInt2, paramTransform, paramInt3, paramtH);
/*      */   }
/*      */ 
/*      */   public final long a()
/*      */   {
/*  693 */     return this.jdField_a_of_type_Long;
/*      */   }
/*      */ 
/*      */   public final long b()
/*      */   {
/*  711 */     return this.jdField_b_of_type_Long;
/*      */   }
/*      */ 
/*      */   public final void a(long paramLong)
/*      */   {
/*  724 */     this.jdField_b_of_type_Long = paramLong;
/*      */   }
/*      */ 
/*      */   public final int getClientIdByName(String paramString)
/*      */   {
/*      */     try
/*      */     {
/*  736 */       return a(paramString).a(); } catch (PlayerNotFountException localPlayerNotFountException) {
/*      */     }
/*  738 */     throw new ClientIdNotFoundException(paramString);
/*      */   }
/*      */ 
/*      */   public final void a(int paramInt)
/*      */   {
/*  749 */     if (paramInt >= 0) {
/*  750 */       this.jdField_c_of_type_Long = System.currentTimeMillis();
/*  751 */       this.k = paramInt; return;
/*      */     }
/*  753 */     this.jdField_c_of_type_Long = -1L;
/*  754 */     this.jdField_a_of_type_Lg.a().serverShutdown.set(Float.valueOf(-1.0F));
/*      */   }
/*      */ 
/*      */   public final long c()
/*      */   {
/*  768 */     return this.jdField_c_of_type_Long;
/*      */   }
/*      */ 
/*      */   public final int a()
/*      */   {
/*  794 */     return this.k;
/*      */   }
/*      */ 
/*      */   public final void a(t paramt, String paramString)
/*      */   {
/*  827 */     synchronized (this.jdField_c_of_type_JavaUtilArrayList) {
/*  828 */       this.jdField_c_of_type_JavaUtilArrayList.add(new vh(paramt, paramString));
/*  829 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final ArrayList f()
/*      */   {
/*  842 */     return this.jdField_c_of_type_JavaUtilArrayList;
/*      */   }
/*      */ 
/*      */   public final ArrayList g()
/*      */   {
/*  855 */     return this.jdField_b_of_type_JavaUtilArrayList;
/*      */   }
/*      */ 
/*      */   public final lT a()
/*      */   {
/*  866 */     return this.jdField_a_of_type_Lg.a();
/*      */   }
/*      */ 
/*      */   public final HashMap b()
/*      */   {
/*  879 */     return this.jdField_d_of_type_JavaUtilHashMap;
/*      */   }
/*      */ 
/*      */   public final boolean onChatTextEnterHook(ChatSystem paramChatSystem, String paramString, boolean paramBoolean)
/*      */   {
/*  892 */     return false;
/*      */   }
/*      */ 
/*      */   public final boolean a()
/*      */   {
/*  905 */     return this.jdField_b_of_type_Boolean;
/*      */   }
/*      */ 
/*      */   public final void a(boolean paramBoolean)
/*      */   {
/*  918 */     this.jdField_b_of_type_Boolean = paramBoolean;
/*      */   }
/*      */ 
/*      */   public final void a(Sendable paramSendable)
/*      */   {
/*  928 */     synchronized (this.j) {
/*  929 */       this.j.add(paramSendable);
/*  930 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final ArrayList h()
/*      */   {
/*  938 */     return this.j;
/*      */   }
/*      */ 
/*      */   public final lJ a()
/*      */   {
/*  949 */     return this.jdField_a_of_type_Lg.a();
/*      */   }
/*      */ 
/*      */   public final HashMap c()
/*      */   {
/*  962 */     return this.jdField_c_of_type_JavaUtilHashMap;
/*      */   }
/*      */ 
/*      */   public final ByteBuffer getDataByteBuffer()
/*      */   {
/*  971 */     return this.jdField_a_of_type_JavaNioByteBuffer;
/*      */   }
/*      */ 
/*      */   public final DatabaseIndex a()
/*      */   {
/*  978 */     return this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex;
/*      */   }
/*      */ 
/*      */   public final void needsNotify(Sendable paramSendable)
/*      */   {
/*  990 */     synchronized (this.jdField_d_of_type_JavaUtilHashSet) {
/*  991 */       this.jdField_d_of_type_JavaUtilHashSet.add(paramSendable);
/*  992 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static synchronized int b()
/*      */   {
/* 1002 */     return jdField_e_of_type_Int++;
/*      */   }
/*      */ 
/*      */   public final tJ a()
/*      */   {
/* 1015 */     return this.jdField_a_of_type_TJ;
/*      */   }
/*      */ 
/*      */   public final boolean a(String paramString)
/*      */   {
/* 1025 */     return (this.jdField_a_of_type_JavaUtilHashSet.isEmpty()) || (this.jdField_a_of_type_JavaUtilHashSet.contains(paramString));
/*      */   }
/*      */ 
/*      */   public static byte[] a(int paramInt)
/*      */   {
/* 1035 */     if (jdField_b_of_type_ArrayOfByte.length < paramInt) {
/* 1036 */       int m = jdField_b_of_type_ArrayOfByte.length;
/* 1037 */       while (m < paramInt) {
/* 1038 */         m <<= 1;
/*      */       }
/* 1040 */       jdField_b_of_type_ArrayOfByte = new byte[m];
/*      */     }
/* 1042 */     return jdField_b_of_type_ArrayOfByte;
/*      */   }
/*      */ 
/*      */   public final int getMaxClients()
/*      */   {
/* 1053 */     return ((Integer)vo.s.a()).intValue();
/*      */   }
/*      */ 
/*      */   public final long getStartTime()
/*      */   {
/* 1059 */     return this.jdField_a_of_type_Long;
/*      */   }
/*      */ 
/*      */   public final String getServerName()
/*      */   {
/* 1066 */     return this.jdField_a_of_type_Lg.b();
/*      */   }
/*      */ 
/*      */   public final String getServerDesc()
/*      */   {
/* 1072 */     return this.jdField_a_of_type_Lg.a();
/*      */   }
/*      */ 
/*      */   public final String executeAdminCommand(String paramString1, String paramString2)
/*      */   {
/* 1084 */     if (!vo.t.a()) {
/* 1085 */       return "ERROR: super admin not enabled on this server";
/*      */     }
/*      */ 
/* 1088 */     if ((paramString1 != null) && (paramString1.equals(vo.u.a()))) {
/*      */       try
/*      */       {
/* 1091 */         if ((
/* 1091 */           paramString1 = paramString2.split(" "))[
/* 1091 */           0].equals("shutdown")) {
/* 1092 */           a(Integer.parseInt(paramString1[1]));
/*      */         }
/* 1094 */         if (paramString1[0].equals("chat")) {
/* 1095 */           paramString1 = paramString2.split(" ", 2);
/* 1096 */           ((GameServerController)super.getController()).broadcastMessage(paramString1[1], 0);
/*      */         }
/* 1098 */         if (paramString1[0].equals("warn")) {
/* 1099 */           paramString1 = paramString2.split(" ", 2);
/* 1100 */           ((GameServerController)super.getController()).broadcastMessage(paramString1[1], 2);
/*      */         }
/* 1102 */         return "SUCCESS: Command executed";
/*      */       } catch (Exception localException) {
/* 1104 */         (
/* 1105 */           paramString1 = localException)
/* 1105 */           .printStackTrace();
/* 1106 */         return "ERROR: Failed to execute command " + paramString1.getClass().getSimpleName() + ": " + paramString1.getMessage();
/*      */       }
/*      */     }
/* 1109 */     return "ERROR: incorrect server password";
/*      */   }
/*      */ 
/*      */   public final vR a()
/*      */   {
/* 1125 */     return this.jdField_a_of_type_VR;
/*      */   }
/*      */ 
/*      */   public final ObjectArrayList b()
/*      */   {
/* 1135 */     return this.jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayList;
/*      */   }
/*      */ 
/*      */   public final void a(q paramq, RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
/*      */   {
/* 1145 */     paramq = new vl(paramq, paramRegisteredClientOnServer, paramString);
/* 1146 */     a(paramq);
/*      */   }
/*      */ 
/*      */   public final void b(q paramq, RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
/*      */   {
/* 1152 */     paramq = new vm(paramq, paramRegisteredClientOnServer, paramString);
/* 1153 */     a(paramq);
/*      */   }
/*      */ 
/*      */   public final void a(RegisteredClientOnServer paramRegisteredClientOnServer, String paramString) {
/* 1157 */     paramRegisteredClientOnServer = new vk(paramRegisteredClientOnServer, paramString, false);
/* 1158 */     a(paramRegisteredClientOnServer);
/*      */   }
/*      */ 
/*      */   public final void b(RegisteredClientOnServer paramRegisteredClientOnServer, String paramString) {
/* 1162 */     paramRegisteredClientOnServer = new vk(paramRegisteredClientOnServer, paramString, true);
/* 1163 */     a(paramRegisteredClientOnServer);
/*      */   }
/*      */ 
/*      */   private void a(vp paramvp)
/*      */   {
/* 1168 */     synchronized (this.jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue) {
/* 1169 */       this.jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(paramvp);
/* 1170 */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final ObjectArrayFIFOQueue a()
/*      */   {
/* 1180 */     return this.jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue;
/*      */   }
/*      */ 
/*      */   public final int getSocketBufferSize()
/*      */   {
/* 1191 */     return ((Integer)vo.w.a()).intValue();
/*      */   }
/*      */ 
/*      */   public final String getAcceptingIP()
/*      */   {
/* 1202 */     return (String)vo.v.a();
/*      */   }
/*      */ 
/*      */   public final ObjectArrayList a()
/*      */   {
/* 1215 */     return this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList;
/*      */   }
/*      */ 
/*      */   public final HashSet e()
/*      */   {
/* 1228 */     return this.jdField_f_of_type_JavaUtilHashSet;
/*      */   }
/*      */ 
/*      */   public final HashSet f()
/*      */   {
/* 1241 */     return this.jdField_h_of_type_JavaUtilHashSet;
/*      */   }
/*      */ 
/*      */   public final boolean filterJoinMessages()
/*      */   {
/* 1252 */     return vo.E.a();
/*      */   }
/*      */ 
/*      */   public final void a(String paramString)
/*      */   {
/* 1262 */     this.jdField_h_of_type_JavaLangString = paramString;
/*      */   }
/*      */ 
/*      */   public final void b(String paramString)
/*      */   {
/* 1272 */     this.jdField_i_of_type_JavaLangString = paramString;
/*      */   }
/*      */ 
/*      */   public final String a()
/*      */   {
/* 1285 */     return this.jdField_h_of_type_JavaLangString;
/*      */   }
/*      */ 
/*      */   public final String b()
/*      */   {
/* 1298 */     return this.jdField_i_of_type_JavaLangString;
/*      */   }
/*      */ 
/*      */   public final byte[] a()
/*      */   {
/* 1308 */     return this.jdField_d_of_type_ArrayOfByte;
/*      */   }
/*      */ 
/*      */   public final byte[] b() {
/* 1312 */     return this.jdField_e_of_type_ArrayOfByte;
/*      */   }
/*      */ 
/*      */   public final void a(byte[] paramArrayOfByte)
/*      */   {
/* 1325 */     this.jdField_d_of_type_ArrayOfByte = paramArrayOfByte;
/*      */   }
/*      */ 
/*      */   public final void b(byte[] paramArrayOfByte)
/*      */   {
/* 1338 */     this.jdField_e_of_type_ArrayOfByte = paramArrayOfByte;
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*   70 */     jdField_a_of_type_JavaLangString = vg.jdField_g_of_type_JavaLangString = "." + File.separator + "server-database" + File.separator;
/*      */ 
/*   72 */     jdField_b_of_type_JavaLangString = "." + File.separator + "blueprints" + File.separator;
/*   73 */     jdField_c_of_type_JavaLangString = "." + File.separator + "blueprints-default" + File.separator;
/*   74 */     jdField_d_of_type_JavaLangString = "." + File.separator + "blueprints" + File.separator + "DATA" + File.separator;
/*   75 */     jdField_e_of_type_JavaLangString = "." + File.separator + "blueprints-default" + File.separator + "DATA" + File.separator;
/*      */ 
/*   77 */     jdField_f_of_type_JavaLangString = jdField_g_of_type_JavaLangString + File.separator + "DATA" + File.separator;
/*      */ 
/*   79 */     jdField_a_of_type_ArrayOfByte = new byte[1048576];
/*   80 */     jdField_a_of_type_JavaUtilZipDeflater = new Deflater();
/*      */ 
/*   93 */     jdField_a_of_type_JavaUtilZipInflater = new Inflater();
/*      */ 
/*  107 */     jdField_f_of_type_Int = -1;
/*      */ 
/*  111 */     jdField_b_of_type_ArrayOfByte = new byte[16400];
/*      */ 
/*  121 */     new ByteArrayOutputStream(102400);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vg
 * JD-Core Version:    0.6.2
 */