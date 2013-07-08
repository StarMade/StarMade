/*    1:     */import com.bulletphysics.linearmath.Transform;
/*    2:     */import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*    3:     */import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*    4:     */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*    5:     */import java.io.ByteArrayOutputStream;
/*    6:     */import java.io.File;
/*    7:     */import java.io.IOException;
/*    8:     */import java.io.PrintStream;
/*    9:     */import java.nio.ByteBuffer;
/*   10:     */import java.util.ArrayList;
/*   11:     */import java.util.HashMap;
/*   12:     */import java.util.HashSet;
/*   13:     */import java.util.Iterator;
/*   14:     */import java.util.zip.Deflater;
/*   15:     */import java.util.zip.Inflater;
/*   16:     */import org.lwjgl.BufferUtils;
/*   17:     */import org.schema.game.common.controller.SegmentController;
/*   18:     */import org.schema.game.common.controller.database.DatabaseIndex;
/*   19:     */import org.schema.game.common.data.world.Universe;
/*   20:     */import org.schema.game.network.objects.NetworkGameState;
/*   21:     */import org.schema.game.server.controller.GameServerController;
/*   22:     */import org.schema.game.server.controller.NoIPException;
/*   23:     */import org.schema.game.server.data.PlayerNotFountException;
/*   24:     */import org.schema.schine.network.ChatSystem;
/*   25:     */import org.schema.schine.network.ClientIdNotFoundException;
/*   26:     */import org.schema.schine.network.NetworkProcessor;
/*   27:     */import org.schema.schine.network.RegisteredClientOnServer;
/*   28:     */import org.schema.schine.network.objects.Sendable;
/*   29:     */import org.schema.schine.network.objects.remote.RemoteFloat;
/*   30:     */import org.schema.schine.network.server.ServerState;
/*   31:     */
/*   39:     */public final class vg
/*   40:     */  extends ServerState
/*   41:     */  implements cy, mu, vd, vf
/*   42:     */{
/*   43:     */  private static final String jdField_g_of_type_JavaLangString;
/*   44:     */  public static final String a;
/*   45:     */  public static final String b;
/*   46:     */  public static final String c;
/*   47:     */  public static final String d;
/*   48:     */  public static final String e;
/*   49:     */  public static final String f;
/*   50:     */  public static final byte[] a;
/*   51:     */  public static Deflater a;
/*   52:     */  public final IntOpenHashSet a;
/*   53:     */  public static Inflater a;
/*   54:     */  public static int a;
/*   55:     */  public static int b;
/*   56:     */  public static int c;
/*   57:     */  public static boolean a;
/*   58:     */  public static int d;
/*   59:     */  public static int e;
/*   60:     */  public static int f;
/*   61:     */  public static vg a;
/*   62:     */  private static byte[] jdField_b_of_type_ArrayOfByte;
/*   63:     */  public static int g;
/*   64:     */  public static int h;
/*   65:     */  public static int i;
/*   66:     */  public static int j;
/*   67:     */  public final HashMap a;
/*   68:     */  
/*   69:     */  static
/*   70:     */  {
/*   71:  71 */    jdField_a_of_type_JavaLangString = vg.jdField_g_of_type_JavaLangString = "." + File.separator + "server-database" + File.separator;
/*   72:     */    
/*   73:  73 */    jdField_b_of_type_JavaLangString = "." + File.separator + "blueprints" + File.separator;
/*   74:  74 */    jdField_c_of_type_JavaLangString = "." + File.separator + "blueprints-default" + File.separator;
/*   75:  75 */    jdField_d_of_type_JavaLangString = "." + File.separator + "blueprints" + File.separator + "DATA" + File.separator;
/*   76:  76 */    jdField_e_of_type_JavaLangString = "." + File.separator + "blueprints-default" + File.separator + "DATA" + File.separator;
/*   77:     */    
/*   78:  78 */    jdField_f_of_type_JavaLangString = jdField_g_of_type_JavaLangString + File.separator + "DATA" + File.separator;
/*   79:     */    
/*   80:  80 */    jdField_a_of_type_ArrayOfByte = new byte[1048576];
/*   81:  81 */    jdField_a_of_type_JavaUtilZipDeflater = new Deflater();
/*   82:     */    
/*   94:  94 */    jdField_a_of_type_JavaUtilZipInflater = new Inflater();
/*   95:     */    
/*  108: 108 */    jdField_f_of_type_Int = -1;
/*  109:     */    
/*  112: 112 */    jdField_b_of_type_ArrayOfByte = new byte[16400];
/*  113:     */    
/*  122: 122 */    new ByteArrayOutputStream(102400);
/*  123:     */  }
/*  124:     */  
/*  126: 126 */  private final ObjectArrayList jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
/*  127:     */  
/*  128: 128 */  private final HashSet jdField_a_of_type_JavaUtilHashSet = new HashSet();
/*  129: 129 */  private final HashMap jdField_b_of_type_JavaUtilHashMap = new HashMap();
/*  130:     */  private final Universe jdField_a_of_type_OrgSchemaGameCommonDataWorldUniverse;
/*  131:     */  private ChatSystem jdField_a_of_type_OrgSchemaSchineNetworkChatSystem;
/*  132: 132 */  private final ArrayList jdField_b_of_type_JavaUtilArrayList = new ArrayList();
/*  133:     */  
/*  135: 135 */  private final ArrayList jdField_c_of_type_JavaUtilArrayList = new ArrayList();
/*  136: 136 */  private final ArrayList jdField_d_of_type_JavaUtilArrayList = new ArrayList();
/*  137: 137 */  private final ArrayList jdField_e_of_type_JavaUtilArrayList = new ArrayList();
/*  138: 138 */  private final ArrayList jdField_f_of_type_JavaUtilArrayList = new ArrayList();
/*  139: 139 */  private final ArrayList jdField_g_of_type_JavaUtilArrayList = new ArrayList();
/*  140:     */  private jW jdField_a_of_type_JW;
/*  141:     */  private final vR jdField_a_of_type_VR;
/*  142: 142 */  private final jm jdField_a_of_type_Jm = new jm("SERVER");
/*  143:     */  
/*  144: 144 */  private final ObjectArrayFIFOQueue jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue = new ObjectArrayFIFOQueue();
/*  145:     */  
/*  147:     */  private kC jdField_a_of_type_KC;
/*  148:     */  
/*  149: 149 */  private final HashMap jdField_c_of_type_JavaUtilHashMap = new HashMap();
/*  150: 150 */  private final HashMap jdField_d_of_type_JavaUtilHashMap = new HashMap();
/*  151: 151 */  private final HashMap jdField_e_of_type_JavaUtilHashMap = new HashMap();
/*  152:     */  
/*  153: 153 */  private ArrayList jdField_h_of_type_JavaUtilArrayList = new ArrayList();
/*  154:     */  
/*  155: 155 */  private final ArrayList jdField_i_of_type_JavaUtilArrayList = new ArrayList();
/*  156:     */  
/*  157: 157 */  private HashSet jdField_b_of_type_JavaUtilHashSet = new HashSet();
/*  158: 158 */  private HashSet jdField_c_of_type_JavaUtilHashSet = new HashSet();
/*  159: 159 */  private HashSet jdField_d_of_type_JavaUtilHashSet = new HashSet();
/*  160:     */  
/*  161: 161 */  private final HashSet jdField_e_of_type_JavaUtilHashSet = new HashSet();
/*  162: 162 */  private final HashSet jdField_f_of_type_JavaUtilHashSet = new HashSet();
/*  163:     */  
/*  164: 164 */  private final HashSet jdField_g_of_type_JavaUtilHashSet = new HashSet();
/*  165: 165 */  private final HashSet jdField_h_of_type_JavaUtilHashSet = new HashSet();
/*  166: 166 */  private byte[] jdField_c_of_type_ArrayOfByte = new byte[1048576];
/*  167: 167 */  private ByteBuffer jdField_a_of_type_JavaNioByteBuffer = BufferUtils.createByteBuffer(1048576);
/*  168:     */  
/*  171:     */  private lg jdField_a_of_type_Lg;
/*  172:     */  
/*  174: 174 */  private final HashSet jdField_i_of_type_JavaUtilHashSet = new HashSet();
/*  175:     */  
/*  176:     */  private long jdField_a_of_type_Long;
/*  177:     */  
/*  178:     */  private long jdField_b_of_type_Long;
/*  179:     */  
/*  180:     */  private long jdField_c_of_type_Long;
/*  181:     */  
/*  182:     */  private int k;
/*  183:     */  
/*  184:     */  private boolean jdField_b_of_type_Boolean;
/*  185:     */  
/*  186: 186 */  private final ArrayList j = new ArrayList();
/*  187:     */  
/*  188:     */  private final DatabaseIndex jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex;
/*  189:     */  
/*  190:     */  private final vi jdField_a_of_type_Vi;
/*  191:     */  
/*  192:     */  private final tJ jdField_a_of_type_TJ;
/*  193:     */  
/*  194:     */  public ObjectArrayFIFOQueue a;
/*  195:     */  
/*  196: 196 */  private final ObjectArrayList jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
/*  197:     */  public final ArrayList a;
/*  198:     */  private String jdField_h_of_type_JavaLangString;
/*  199:     */  private String jdField_i_of_type_JavaLangString;
/*  200:     */  private byte[] jdField_d_of_type_ArrayOfByte;
/*  201:     */  private byte[] jdField_e_of_type_ArrayOfByte;
/*  202:     */  
/*  203:     */  public vg()
/*  204:     */  {
/*  205:  85 */    this.jdField_a_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet = new IntOpenHashSet();
/*  206:     */    
/*  244: 124 */    this.jdField_a_of_type_JavaUtilHashMap = new HashMap();
/*  245:     */    
/*  318: 198 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  319:     */    
/*  330: 210 */    this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  331:     */    
/*  332: 212 */    vo.a();
/*  333:     */    try {
/*  334: 214 */      vo.b();
/*  335: 215 */    } catch (IOException localIOException1) { 
/*  336:     */      
/*  337: 217 */        localIOException1;
/*  338:     */    }
/*  339:     */    
/*  340: 218 */    this.jdField_a_of_type_Vi = new vi(this, (byte)0);
/*  341: 219 */    this.jdField_a_of_type_Vi.setPriority(3);
/*  342: 220 */    this.jdField_a_of_type_Vi.start();
/*  343:     */    
/*  344: 222 */    this.jdField_a_of_type_KC = kC.jdField_a_of_type_KC;
/*  345:     */    
/*  346: 224 */    this.jdField_a_of_type_JW = new jW(this);
/*  347:     */    
/*  348: 226 */    this.jdField_a_of_type_TJ = new tJ(this);
/*  349:     */    
/*  350: 228 */    this.jdField_a_of_type_OrgSchemaGameCommonDataWorldUniverse = new Universe(this, 3465436L);
/*  351: 229 */    boolean bool = DatabaseIndex.a();
/*  352: 230 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex = new DatabaseIndex();
/*  353: 231 */    if (!bool) {
/*  354: 232 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex.a();
/*  355:     */    } else {
/*  356: 234 */      this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex.c();
/*  357:     */      try {
/*  358: 236 */        this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex.b();
/*  359: 237 */      } catch (IOException localIOException2) { 
/*  360:     */        
/*  361: 239 */          localIOException2;
/*  362:     */      }
/*  363:     */    }
/*  364:     */    
/*  366: 242 */    this.jdField_a_of_type_VR = new vR(this);
/*  367:     */    
/*  368: 244 */    jdField_a_of_type_Vg = this;
/*  369:     */  }
/*  370:     */  
/*  377:     */  public final void chat(ChatSystem paramChatSystem, String paramString1, String paramString2, boolean paramBoolean)
/*  378:     */  {
/*  379: 255 */    this.jdField_e_of_type_JavaUtilArrayList.add(paramChatSystem.getOwnerStateId() + ": " + paramString1);
/*  380:     */  }
/*  381:     */  
/*  383:     */  public final ArrayList a()
/*  384:     */  {
/*  385: 261 */    return this.jdField_h_of_type_JavaUtilArrayList;
/*  386:     */  }
/*  387:     */  
/*  389:     */  public final HashSet a()
/*  390:     */  {
/*  391: 267 */    return this.jdField_a_of_type_JavaUtilHashSet;
/*  392:     */  }
/*  393:     */  
/*  395:     */  public final HashMap a()
/*  396:     */  {
/*  397: 273 */    return this.jdField_b_of_type_JavaUtilHashMap;
/*  398:     */  }
/*  399:     */  
/*  400: 276 */  public final HashSet b() { return this.jdField_e_of_type_JavaUtilHashSet; }
/*  401:     */  
/*  402:     */  public final HashSet c()
/*  403:     */  {
/*  404: 280 */    return this.jdField_g_of_type_JavaUtilHashSet;
/*  405:     */  }
/*  406:     */  
/*  409:     */  public final ArrayList b()
/*  410:     */  {
/*  411: 287 */    return this.jdField_i_of_type_JavaUtilArrayList;
/*  412:     */  }
/*  413:     */  
/*  417:     */  public final ChatSystem getChat()
/*  418:     */  {
/*  419: 295 */    return this.jdField_a_of_type_OrgSchemaSchineNetworkChatSystem;
/*  420:     */  }
/*  421:     */  
/*  422:     */  public final String[] getCommandPrefixes() {
/*  423: 299 */    return null;
/*  424:     */  }
/*  425:     */  
/*  429:     */  public final GameServerController a()
/*  430:     */  {
/*  431: 307 */    return (GameServerController)super.getController();
/*  432:     */  }
/*  433:     */  
/*  434:     */  public final byte[] getDataBuffer() {
/*  435: 311 */    return this.jdField_c_of_type_ArrayOfByte;
/*  436:     */  }
/*  437:     */  
/*  438: 314 */  public final ArrayList c() { return this.jdField_d_of_type_JavaUtilArrayList; }
/*  439:     */  
/*  442:     */  public final kC a()
/*  443:     */  {
/*  444: 320 */    return this.jdField_a_of_type_KC;
/*  445:     */  }
/*  446:     */  
/*  448:     */  public final lg a()
/*  449:     */  {
/*  450: 326 */    return this.jdField_a_of_type_Lg;
/*  451:     */  }
/*  452:     */  
/*  453:     */  public final float getMaxMsBetweenUpdates() {
/*  454: 330 */    return 100.0F;
/*  455:     */  }
/*  456:     */  
/*  457:     */  public final lE a(String paramString) {
/*  458:     */    lE locallE;
/*  459: 335 */    if ((locallE = (lE)this.jdField_d_of_type_JavaUtilHashMap.get(paramString)) != null) {
/*  460: 336 */      return locallE;
/*  461:     */    }
/*  462: 338 */    throw new PlayerNotFountException(paramString);
/*  463:     */  }
/*  464:     */  
/*  465:     */  public final lE a(int paramInt) { lE locallE;
/*  466: 342 */    if ((locallE = (lE)this.jdField_e_of_type_JavaUtilHashMap.get(Integer.valueOf(paramInt))) != null) {
/*  467: 343 */      return locallE;
/*  468:     */    }
/*  469: 345 */    throw new PlayerNotFountException("CLIENT-ID(" + paramInt + ") ");
/*  470:     */  }
/*  471:     */  
/*  473:     */  public final NetworkProcessor getProcessor(int paramInt)
/*  474:     */  {
/*  475: 351 */    return ((RegisteredClientOnServer)getClients().get(Integer.valueOf(paramInt))).getProcessor();
/*  476:     */  }
/*  477:     */  
/*  479:     */  public final ArrayList d()
/*  480:     */  {
/*  481: 357 */    return this.jdField_f_of_type_JavaUtilArrayList;
/*  482:     */  }
/*  483:     */  
/*  484:     */  public final jW a()
/*  485:     */  {
/*  486: 362 */    return this.jdField_a_of_type_JW;
/*  487:     */  }
/*  488:     */  
/*  491:     */  public final ArrayList e()
/*  492:     */  {
/*  493: 369 */    return this.jdField_g_of_type_JavaUtilArrayList;
/*  494:     */  }
/*  495:     */  
/*  498:     */  public final HashSet d()
/*  499:     */  {
/*  500: 376 */    return this.jdField_i_of_type_JavaUtilHashSet;
/*  501:     */  }
/*  502:     */  
/*  506:     */  public final jm a()
/*  507:     */  {
/*  508: 384 */    return this.jdField_a_of_type_Jm;
/*  509:     */  }
/*  510:     */  
/*  514:     */  public final Universe a()
/*  515:     */  {
/*  516: 392 */    return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldUniverse;
/*  517:     */  }
/*  518:     */  
/*  519:     */  public final float getVersion()
/*  520:     */  {
/*  521: 397 */    return sG.a;
/*  522:     */  }
/*  523:     */  
/*  524:     */  public final void a() {
/*  525: 401 */    long l1 = System.currentTimeMillis();
/*  526: 402 */    Sendable localSendable3; vg localvg; if (!this.jdField_b_of_type_JavaUtilHashSet.isEmpty()) {
/*  527: 403 */      HashSet localHashSet1 = new HashSet(this.jdField_b_of_type_JavaUtilHashSet.size());
/*  528: 404 */      synchronized (this.jdField_b_of_type_JavaUtilHashSet) {
/*  529: 405 */        localHashSet1.addAll(this.jdField_b_of_type_JavaUtilHashSet);
/*  530:     */      }
/*  531: 407 */      for (??? = localObject2.iterator(); ((Iterator)???).hasNext();) { Sendable localSendable2 = (Sendable)((Iterator)???).next();
/*  532:     */        
/*  533: 409 */        localSendable3 = localSendable2;localvg = this; if ((localSendable3 instanceof lE)) { localvg.jdField_d_of_type_JavaUtilHashMap.put(((lE)localSendable3).getName(), (lE)localSendable3);localvg.jdField_e_of_type_JavaUtilHashMap.put(Integer.valueOf(((lE)localSendable3).a()), (lE)localSendable3);localvg.jdField_a_of_type_VR.a().b((lE)localSendable3); } if ((localSendable3 instanceof SegmentController)) localvg.jdField_c_of_type_JavaUtilHashMap.put(((SegmentController)localSendable3).getUniqueIdentifier(), (SegmentController)localSendable3); if (((localSendable3 instanceof mF)) && (((mF)localSendable3).isGravitySource())) { localvg.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add((mF)localSendable3);
/*  534:     */        }
/*  535: 411 */        if ((localSendable2 instanceof kc))
/*  536:     */        {
/*  537:     */          ka localka;
/*  538: 414 */          if ((localka = ((kc)localSendable2).a()) != null) {
/*  539: 415 */            localka.setServerSendableSegmentController((kc)localSendable2);
/*  540:     */            
/*  541: 417 */            ((kc)localSendable2).a();
/*  542:     */          }
/*  543:     */        }
/*  544:     */        
/*  546: 422 */        setChanged();
/*  547: 423 */        notifyObservers(localSendable2);
/*  548:     */      }
/*  549:     */      
/*  551: 427 */      this.jdField_b_of_type_JavaUtilHashSet.clear();
/*  552:     */    }
/*  553:     */    long l2;
/*  554: 430 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/*  555: 431 */      System.err.println("[SERVER][UPDATE] WARNING: handleAddedAndRemovedObjects update took " + l2);
/*  556:     */    }
/*  557:     */    
/*  558: 434 */    if (!this.jdField_c_of_type_JavaUtilHashSet.isEmpty()) {
/*  559: 435 */      HashSet localHashSet2 = new HashSet(this.jdField_c_of_type_JavaUtilHashSet.size());
/*  560: 436 */      synchronized (this.jdField_c_of_type_JavaUtilHashSet) {
/*  561: 437 */        localHashSet2.addAll(this.jdField_c_of_type_JavaUtilHashSet);
/*  562:     */      }
/*  563: 439 */      for (??? = localHashSet2.iterator(); ((Iterator)???).hasNext();) { Sendable localSendable1;
/*  564: 440 */        if (((localSendable1 = (Sendable)((Iterator)???).next()) instanceof Ak))
/*  565:     */        {
/*  566: 442 */          ((GameServerController)super.getController()).a((Ak)localSendable1, true);
/*  567:     */        }
/*  568: 444 */        localSendable3 = localSendable1;localvg = this; if ((localSendable3 instanceof lE)) { localvg.jdField_d_of_type_JavaUtilHashMap.remove(((lE)localSendable3).getName());localvg.jdField_e_of_type_JavaUtilHashMap.remove(Integer.valueOf(((lE)localSendable3).a()));localvg.jdField_a_of_type_VR.a().a((lE)localSendable3); } if ((localSendable3 instanceof SegmentController)) localvg.jdField_c_of_type_JavaUtilHashMap.remove(((SegmentController)localSendable3).getUniqueIdentifier()); if (((localSendable3 instanceof mF)) && (((mF)localSendable3).isGravitySource())) localvg.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(localSendable3); if (localSendable3.isMarkedForPermanentDelete()) { if ((localSendable3 instanceof Ak)) localvg.jdField_a_of_type_VR.a(((Ak)localSendable3).getUniqueIdentifier()); localSendable3.destroyPersistent();
/*  569:     */        }
/*  570:     */        
/*  571: 447 */        setChanged();
/*  572: 448 */        notifyObservers(localSendable1);
/*  573:     */      }
/*  574:     */      
/*  576: 452 */      this.jdField_c_of_type_JavaUtilHashSet.clear();
/*  577:     */    }
/*  578:     */  }
/*  579:     */  
/*  580: 456 */  public final void b() { long l1 = System.currentTimeMillis();
/*  581: 457 */    if (!this.jdField_d_of_type_JavaUtilHashSet.isEmpty()) {
/*  582: 458 */      HashSet localHashSet = new HashSet();
/*  583: 459 */      synchronized (this.jdField_d_of_type_JavaUtilHashSet) {
/*  584: 460 */        localHashSet.addAll(this.jdField_d_of_type_JavaUtilHashSet);
/*  585:     */      }
/*  586:     */      
/*  587: 463 */      for (??? = localObject1.iterator(); ((Iterator)???).hasNext();) { Sendable localSendable = (Sendable)((Iterator)???).next();
/*  588:     */        
/*  589: 465 */        setChanged();
/*  590: 466 */        notifyObservers(localSendable);
/*  591:     */      }
/*  592:     */      
/*  593: 469 */      this.jdField_d_of_type_JavaUtilHashSet.clear();
/*  594:     */    }
/*  595:     */    long l2;
/*  596: 472 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/*  597: 473 */      System.err.println("[SERVER][UPDATE] WARNING: needsNotifyObjects update took " + l2);
/*  598:     */    }
/*  599:     */  }
/*  600:     */  
/*  656:     */  public final void notifyOfAddedObject(Sendable paramSendable)
/*  657:     */  {
/*  658: 534 */    if ((paramSendable instanceof SegmentController)) {
/*  659:     */      SegmentController localSegmentController;
/*  660: 536 */      if ((localSegmentController = (SegmentController)paramSendable).getCreatorThread() == null) {
/*  661: 537 */        localSegmentController.startCreatorThread();
/*  662:     */      }
/*  663:     */    }
/*  664:     */    
/*  665: 541 */    synchronized (this.jdField_b_of_type_JavaUtilHashSet) {
/*  666: 542 */      this.jdField_b_of_type_JavaUtilHashSet.add(paramSendable); return;
/*  667:     */    }
/*  668:     */  }
/*  669:     */  
/*  671:     */  public final void notifyOfRemovedObject(Sendable paramSendable)
/*  672:     */  {
/*  673: 549 */    synchronized (this.jdField_c_of_type_JavaUtilHashSet) {
/*  674: 550 */      this.jdField_c_of_type_JavaUtilHashSet.add(paramSendable);
/*  675: 551 */      return;
/*  676:     */    }
/*  677:     */  }
/*  678:     */  
/*  679:     */  public final String onAutoComplete(String paramString1, wB paramwB, String paramString2)
/*  680:     */  {
/*  681: 557 */    System.err.println("NO AUTOCOMPLETE ON SERVER");
/*  682: 558 */    return paramString1;
/*  683:     */  }
/*  684:     */  
/*  685:     */  public final void onStringCommand(String paramString1, wB paramwB, String paramString2)
/*  686:     */  {
/*  687: 563 */    throw new IllegalArgumentException();
/*  688:     */  }
/*  689:     */  
/*  699:     */  public final void a(ChatSystem paramChatSystem)
/*  700:     */  {
/*  701: 577 */    this.jdField_a_of_type_OrgSchemaSchineNetworkChatSystem = paramChatSystem;
/*  702:     */  }
/*  703:     */  
/*  713:     */  public final void a(lg paramlg)
/*  714:     */  {
/*  715: 591 */    this.jdField_a_of_type_Lg = paramlg;
/*  716:     */  }
/*  717:     */  
/*  806:     */  public final void a(int paramInt1, String paramString, int paramInt2, Transform paramTransform, int paramInt3, tH paramtH)
/*  807:     */  {
/*  808: 684 */    this.jdField_a_of_type_Vi.a(paramInt1, paramString, paramInt2, paramTransform, paramInt3, paramtH);
/*  809:     */  }
/*  810:     */  
/*  816:     */  public final long a()
/*  817:     */  {
/*  818: 694 */    return this.jdField_a_of_type_Long;
/*  819:     */  }
/*  820:     */  
/*  834:     */  public final long b()
/*  835:     */  {
/*  836: 712 */    return this.jdField_b_of_type_Long;
/*  837:     */  }
/*  838:     */  
/*  847:     */  public final void a(long paramLong)
/*  848:     */  {
/*  849: 725 */    this.jdField_b_of_type_Long = paramLong;
/*  850:     */  }
/*  851:     */  
/*  857:     */  public final int getClientIdByName(String paramString)
/*  858:     */  {
/*  859:     */    try
/*  860:     */    {
/*  861: 737 */      return a(paramString).a();
/*  862:     */    } catch (PlayerNotFountException localPlayerNotFountException) {
/*  863: 739 */      throw new ClientIdNotFoundException(paramString);
/*  864:     */    }
/*  865:     */  }
/*  866:     */  
/*  872:     */  public final void a(int paramInt)
/*  873:     */  {
/*  874: 750 */    if (paramInt >= 0) {
/*  875: 751 */      this.jdField_c_of_type_Long = System.currentTimeMillis();
/*  876: 752 */      this.k = paramInt;return;
/*  877:     */    }
/*  878: 754 */    this.jdField_c_of_type_Long = -1L;
/*  879: 755 */    this.jdField_a_of_type_Lg.a().serverShutdown.set(Float.valueOf(-1.0F));
/*  880:     */  }
/*  881:     */  
/*  891:     */  public final long c()
/*  892:     */  {
/*  893: 769 */    return this.jdField_c_of_type_Long;
/*  894:     */  }
/*  895:     */  
/*  917:     */  public final int a()
/*  918:     */  {
/*  919: 795 */    return this.k;
/*  920:     */  }
/*  921:     */  
/*  950:     */  public final void a(t paramt, String paramString)
/*  951:     */  {
/*  952: 828 */    synchronized (this.jdField_c_of_type_JavaUtilArrayList) {
/*  953: 829 */      this.jdField_c_of_type_JavaUtilArrayList.add(new vh(paramt, paramString));
/*  954: 830 */      return;
/*  955:     */    }
/*  956:     */  }
/*  957:     */  
/*  965:     */  public final ArrayList f()
/*  966:     */  {
/*  967: 843 */    return this.jdField_c_of_type_JavaUtilArrayList;
/*  968:     */  }
/*  969:     */  
/*  978:     */  public final ArrayList g()
/*  979:     */  {
/*  980: 856 */    return this.jdField_b_of_type_JavaUtilArrayList;
/*  981:     */  }
/*  982:     */  
/*  989:     */  public final lT a()
/*  990:     */  {
/*  991: 867 */    return this.jdField_a_of_type_Lg.a();
/*  992:     */  }
/*  993:     */  
/* 1002:     */  public final HashMap b()
/* 1003:     */  {
/* 1004: 880 */    return this.jdField_d_of_type_JavaUtilHashMap;
/* 1005:     */  }
/* 1006:     */  
/* 1015:     */  public final boolean onChatTextEnterHook(ChatSystem paramChatSystem, String paramString, boolean paramBoolean)
/* 1016:     */  {
/* 1017: 893 */    return false;
/* 1018:     */  }
/* 1019:     */  
/* 1028:     */  public final boolean a()
/* 1029:     */  {
/* 1030: 906 */    return this.jdField_b_of_type_Boolean;
/* 1031:     */  }
/* 1032:     */  
/* 1041:     */  public final void a(boolean paramBoolean)
/* 1042:     */  {
/* 1043: 919 */    this.jdField_b_of_type_Boolean = paramBoolean;
/* 1044:     */  }
/* 1045:     */  
/* 1051:     */  public final void a(Sendable paramSendable)
/* 1052:     */  {
/* 1053: 929 */    synchronized (this.j) {
/* 1054: 930 */      this.j.add(paramSendable);
/* 1055: 931 */      return;
/* 1056:     */    }
/* 1057:     */  }
/* 1058:     */  
/* 1061:     */  public final ArrayList h()
/* 1062:     */  {
/* 1063: 939 */    return this.j;
/* 1064:     */  }
/* 1065:     */  
/* 1072:     */  public final lJ a()
/* 1073:     */  {
/* 1074: 950 */    return this.jdField_a_of_type_Lg.a();
/* 1075:     */  }
/* 1076:     */  
/* 1085:     */  public final HashMap c()
/* 1086:     */  {
/* 1087: 963 */    return this.jdField_c_of_type_JavaUtilHashMap;
/* 1088:     */  }
/* 1089:     */  
/* 1094:     */  public final ByteBuffer getDataByteBuffer()
/* 1095:     */  {
/* 1096: 972 */    return this.jdField_a_of_type_JavaNioByteBuffer;
/* 1097:     */  }
/* 1098:     */  
/* 1101:     */  public final DatabaseIndex a()
/* 1102:     */  {
/* 1103: 979 */    return this.jdField_a_of_type_OrgSchemaGameCommonControllerDatabaseDatabaseIndex;
/* 1104:     */  }
/* 1105:     */  
/* 1113:     */  public final void needsNotify(Sendable paramSendable)
/* 1114:     */  {
/* 1115: 991 */    synchronized (this.jdField_d_of_type_JavaUtilHashSet) {
/* 1116: 992 */      this.jdField_d_of_type_JavaUtilHashSet.add(paramSendable);
/* 1117: 993 */      return;
/* 1118:     */    }
/* 1119:     */  }
/* 1120:     */  
/* 1125:     */  public static synchronized int b()
/* 1126:     */  {
/* 1127:1003 */    return jdField_e_of_type_Int++;
/* 1128:     */  }
/* 1129:     */  
/* 1138:     */  public final tJ a()
/* 1139:     */  {
/* 1140:1016 */    return this.jdField_a_of_type_TJ;
/* 1141:     */  }
/* 1142:     */  
/* 1148:     */  public final boolean a(String paramString)
/* 1149:     */  {
/* 1150:1026 */    return (this.jdField_a_of_type_JavaUtilHashSet.isEmpty()) || (this.jdField_a_of_type_JavaUtilHashSet.contains(paramString));
/* 1151:     */  }
/* 1152:     */  
/* 1158:     */  public static byte[] a(int paramInt)
/* 1159:     */  {
/* 1160:1036 */    if (jdField_b_of_type_ArrayOfByte.length < paramInt) {
/* 1161:1037 */      int m = jdField_b_of_type_ArrayOfByte.length;
/* 1162:1038 */      while (m < paramInt) {
/* 1163:1039 */        m <<= 1;
/* 1164:     */      }
/* 1165:1041 */      jdField_b_of_type_ArrayOfByte = new byte[m];
/* 1166:     */    }
/* 1167:1043 */    return jdField_b_of_type_ArrayOfByte;
/* 1168:     */  }
/* 1169:     */  
/* 1176:     */  public final int getMaxClients()
/* 1177:     */  {
/* 1178:1054 */    return ((Integer)vo.t.a()).intValue();
/* 1179:     */  }
/* 1180:     */  
/* 1182:     */  public final long getStartTime()
/* 1183:     */  {
/* 1184:1060 */    return this.jdField_a_of_type_Long;
/* 1185:     */  }
/* 1186:     */  
/* 1189:     */  public final String getServerName()
/* 1190:     */  {
/* 1191:1067 */    return this.jdField_a_of_type_Lg.b();
/* 1192:     */  }
/* 1193:     */  
/* 1195:     */  public final String getServerDesc()
/* 1196:     */  {
/* 1197:1073 */    return this.jdField_a_of_type_Lg.a();
/* 1198:     */  }
/* 1199:     */  
/* 1207:     */  public final String executeAdminCommand(String paramString1, String paramString2)
/* 1208:     */  {
/* 1209:1085 */    if (!vo.u.a()) {
/* 1210:1086 */      return "ERROR: super admin not enabled on this server";
/* 1211:     */    }
/* 1212:     */    
/* 1213:1089 */    if ((paramString1 != null) && (paramString1.equals(vo.v.a()))) {
/* 1214:     */      try
/* 1215:     */      {
/* 1216:1092 */        if ((paramString1 = paramString2.split(" "))[0].equals("shutdown")) {
/* 1217:1093 */          a(Integer.parseInt(paramString1[1]));
/* 1218:     */        }
/* 1219:1095 */        if (paramString1[0].equals("chat")) {
/* 1220:1096 */          paramString1 = paramString2.split(" ", 2);
/* 1221:1097 */          ((GameServerController)super.getController()).broadcastMessage(paramString1[1], 0);
/* 1222:     */        }
/* 1223:1099 */        if (paramString1[0].equals("warn")) {
/* 1224:1100 */          paramString1 = paramString2.split(" ", 2);
/* 1225:1101 */          ((GameServerController)super.getController()).broadcastMessage(paramString1[1], 2);
/* 1226:     */        }
/* 1227:1103 */        return "SUCCESS: Command executed";
/* 1228:     */      }
/* 1229:     */      catch (Exception localException) {
/* 1230:1106 */        (paramString1 = localException).printStackTrace();
/* 1231:1107 */        return "ERROR: Failed to execute command " + paramString1.getClass().getSimpleName() + ": " + paramString1.getMessage();
/* 1232:     */      }
/* 1233:     */    }
/* 1234:1110 */    return "ERROR: incorrect server password";
/* 1235:     */  }
/* 1236:     */  
/* 1248:     */  public final vR a()
/* 1249:     */  {
/* 1250:1126 */    return this.jdField_a_of_type_VR;
/* 1251:     */  }
/* 1252:     */  
/* 1258:     */  public final ObjectArrayList b()
/* 1259:     */  {
/* 1260:1136 */    return this.jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayList;
/* 1261:     */  }
/* 1262:     */  
/* 1268:     */  public final void a(q paramq, RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
/* 1269:     */  {
/* 1270:1146 */    paramq = new vl(paramq, paramRegisteredClientOnServer, paramString);
/* 1271:1147 */    a(paramq);
/* 1272:     */  }
/* 1273:     */  
/* 1275:     */  public final void b(q paramq, RegisteredClientOnServer paramRegisteredClientOnServer, String paramString)
/* 1276:     */  {
/* 1277:1153 */    paramq = new vm(paramq, paramRegisteredClientOnServer, paramString);
/* 1278:1154 */    a(paramq);
/* 1279:     */  }
/* 1280:     */  
/* 1281:     */  public final void a(RegisteredClientOnServer paramRegisteredClientOnServer, String paramString) {
/* 1282:1158 */    paramRegisteredClientOnServer = new vk(paramRegisteredClientOnServer, paramString, false);
/* 1283:1159 */    a(paramRegisteredClientOnServer);
/* 1284:     */  }
/* 1285:     */  
/* 1286:     */  public final void b(RegisteredClientOnServer paramRegisteredClientOnServer, String paramString) {
/* 1287:1163 */    paramRegisteredClientOnServer = new vk(paramRegisteredClientOnServer, paramString, true);
/* 1288:1164 */    a(paramRegisteredClientOnServer);
/* 1289:     */  }
/* 1290:     */  
/* 1291:     */  private void a(vp paramvp)
/* 1292:     */  {
/* 1293:1169 */    synchronized (this.jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue) {
/* 1294:1170 */      this.jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue.enqueue(paramvp);
/* 1295:1171 */      return;
/* 1296:     */    }
/* 1297:     */  }
/* 1298:     */  
/* 1303:     */  public final ObjectArrayFIFOQueue a()
/* 1304:     */  {
/* 1305:1181 */    return this.jdField_b_of_type_ItUnimiDsiFastutilObjectsObjectArrayFIFOQueue;
/* 1306:     */  }
/* 1307:     */  
/* 1314:     */  public final int getSocketBufferSize()
/* 1315:     */  {
/* 1316:1192 */    return ((Integer)vo.x.a()).intValue();
/* 1317:     */  }
/* 1318:     */  
/* 1325:     */  public final String getAcceptingIP()
/* 1326:     */  {
/* 1327:1203 */    return (String)vo.w.a();
/* 1328:     */  }
/* 1329:     */  
/* 1338:     */  public final ObjectArrayList a()
/* 1339:     */  {
/* 1340:1216 */    return this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList;
/* 1341:     */  }
/* 1342:     */  
/* 1351:     */  public final HashSet e()
/* 1352:     */  {
/* 1353:1229 */    return this.jdField_f_of_type_JavaUtilHashSet;
/* 1354:     */  }
/* 1355:     */  
/* 1364:     */  public final HashSet f()
/* 1365:     */  {
/* 1366:1242 */    return this.jdField_h_of_type_JavaUtilHashSet;
/* 1367:     */  }
/* 1368:     */  
/* 1375:     */  public final boolean filterJoinMessages()
/* 1376:     */  {
/* 1377:1253 */    return vo.F.a();
/* 1378:     */  }
/* 1379:     */  
/* 1385:     */  public final void a(String paramString)
/* 1386:     */  {
/* 1387:1263 */    this.jdField_h_of_type_JavaLangString = paramString;
/* 1388:     */  }
/* 1389:     */  
/* 1395:     */  public final void b(String paramString)
/* 1396:     */  {
/* 1397:1273 */    this.jdField_i_of_type_JavaLangString = paramString;
/* 1398:     */  }
/* 1399:     */  
/* 1408:     */  public final String a()
/* 1409:     */  {
/* 1410:1286 */    return this.jdField_h_of_type_JavaLangString;
/* 1411:     */  }
/* 1412:     */  
/* 1421:     */  public final String b()
/* 1422:     */  {
/* 1423:1299 */    return this.jdField_i_of_type_JavaLangString;
/* 1424:     */  }
/* 1425:     */  
/* 1431:     */  public final byte[] a()
/* 1432:     */  {
/* 1433:1309 */    return this.jdField_d_of_type_ArrayOfByte;
/* 1434:     */  }
/* 1435:     */  
/* 1436:     */  public final byte[] b() {
/* 1437:1313 */    return this.jdField_e_of_type_ArrayOfByte;
/* 1438:     */  }
/* 1439:     */  
/* 1448:     */  public final void a(byte[] paramArrayOfByte)
/* 1449:     */  {
/* 1450:1326 */    this.jdField_d_of_type_ArrayOfByte = paramArrayOfByte;
/* 1451:     */  }
/* 1452:     */  
/* 1461:     */  public final void b(byte[] paramArrayOfByte)
/* 1462:     */  {
/* 1463:1339 */    this.jdField_e_of_type_ArrayOfByte = paramArrayOfByte;
/* 1464:     */  }
/* 1465:     */  
/* 1472:     */  public final boolean useUDP()
/* 1473:     */  {
/* 1474:1350 */    return vo.G.a();
/* 1475:     */  }
/* 1476:     */  
/* 1484:     */  public final void a(vc paramvc, String paramString)
/* 1485:     */  {
/* 1486:     */    try
/* 1487:     */    {
/* 1488:1364 */      if ((paramString = a(paramString)) != null) {
/* 1489:1365 */        if (vo.I.a()) {
/* 1490:1366 */          System.err.println("[SERVER] banning name for modified blueprint use " + paramString.getName());
/* 1491:1367 */          ((GameServerController)super.getController()).d(paramString.getName());
/* 1492:     */        }
/* 1493:     */        
/* 1494:1370 */        if (vo.J.a())
/* 1495:     */        {
/* 1496:1372 */          if (paramString.a() != null) {
/* 1497:1373 */            System.err.println("[SERVER] banning IP for modified blueprint use " + paramString.a());
/* 1498:     */            try {
/* 1499:1375 */              ((GameServerController)super.getController()).c(paramString.a());
/* 1500:1376 */            } catch (NoIPException localNoIPException) { 
/* 1501:     */              
/* 1502:1378 */                localNoIPException;
/* 1503:     */            }
/* 1504:     */          }
/* 1505:     */        }
/* 1506:     */        
/* 1508:1382 */        if (vo.H.a()) {
/* 1509:     */          try {
/* 1510:1384 */            System.err.println("[SERVER] kicking for modified blueprint use " + paramString.getName());
/* 1511:1385 */            ((GameServerController)super.getController()).sendLogout(paramString.getId(), "You have been kick for using a modified blueprint");
/* 1512:1386 */          } catch (IOException localIOException) { 
/* 1513:     */            
/* 1514:1388 */              localIOException;
/* 1515:     */          }
/* 1516:     */        }
/* 1517:     */        
/* 1520:1392 */        if (vo.K.a()) {
/* 1521:1393 */          System.err.println("[SERVER] removing modified blueprint " + paramvc.a());
/* 1522:1394 */          this.jdField_a_of_type_Lg.a().a(paramvc.a());
/* 1523:     */        }
/* 1524:     */      }
/* 1525:     */      return;
/* 1526:1398 */    } catch (PlayerNotFountException localPlayerNotFountException) { 
/* 1527:     */      
/* 1528:1400 */        localPlayerNotFountException;
/* 1529:     */    }
/* 1530:     */  }
/* 1531:     */  
/* 1539:     */  public final boolean tcpNoDelay()
/* 1540:     */  {
/* 1541:1411 */    return vo.L.a();
/* 1542:     */  }
/* 1543:     */  
/* 1544:     */  public final boolean flushPingImmediately()
/* 1545:     */  {
/* 1546:1416 */    return vo.M.a();
/* 1547:     */  }
/* 1548:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */