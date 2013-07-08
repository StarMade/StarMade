import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.game.network.objects.NetworkGameState;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.network.objects.remote.RemoteFaction;
import org.schema.game.network.objects.remote.RemoteFactionBuffer;
import org.schema.game.network.objects.remote.RemoteFactionNewsPost;
import org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.server.ServerMessage;

public class class_850
  extends Observable
  implements class_80, Observer
{
  private int jdField_field_136_of_type_Int;
  private final class_748 jdField_field_136_of_type_Class_748;
  private final ArrayList jdField_field_136_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_139_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_182_of_type_JavaUtilArrayList = new ArrayList();
  private final HashSet jdField_field_136_of_type_JavaUtilHashSet = new HashSet();
  private final ArrayList jdField_field_183_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_184_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_185_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList field_186 = new ArrayList();
  private int jdField_field_139_of_type_Int = -1;
  private int jdField_field_182_of_type_Int = -1;
  private int jdField_field_183_of_type_Int = -1;
  private int jdField_field_184_of_type_Int;
  private long jdField_field_136_of_type_Long;
  private boolean jdField_field_136_of_type_Boolean = true;
  private class_765 jdField_field_136_of_type_Class_765;
  private int jdField_field_185_of_type_Int;
  private boolean jdField_field_139_of_type_Boolean = true;
  private String jdField_field_136_of_type_JavaLangString = "";
  private boolean jdField_field_182_of_type_Boolean;
  private final ArrayList field_192 = new ArrayList();
  private boolean jdField_field_183_of_type_Boolean;
  
  public class_850(class_748 paramclass_748)
  {
    this.jdField_field_136_of_type_Class_748 = paramclass_748;
  }
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public final void a13()
  {
    if (this.jdField_field_136_of_type_Class_748.isOnServer())
    {
      ((class_1041)this.jdField_field_136_of_type_Class_748.getState()).a12().a51().deleteObserver(this);
      return;
    }
    ((class_371)this.jdField_field_136_of_type_Class_748.getState()).a12().a51().deleteObserver(this);
  }
  
  /* Error */
  public final void b4()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   4: ifnonnull +48 -> 52
    //   7: aload_0
    //   8: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   11: invokevirtual 84	class_748:getState	()Lorg/schema/schine/network/StateInterface;
    //   14: checkcast 112	class_1039
    //   17: invokeinterface 115 1 0
    //   22: ifnull +30 -> 52
    //   25: aload_0
    //   26: aload_0
    //   27: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   30: invokevirtual 84	class_748:getState	()Lorg/schema/schine/network/StateInterface;
    //   33: checkcast 112	class_1039
    //   36: invokeinterface 115 1 0
    //   41: putfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   44: aload_0
    //   45: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   48: aload_0
    //   49: invokevirtual 118	class_765:addObserver	(Ljava/util/Observer;)V
    //   52: aload_0
    //   53: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   56: ifnull +305 -> 361
    //   59: aload_0
    //   60: getfield 60	class_850:jdField_field_136_of_type_Boolean	Z
    //   63: ifeq +298 -> 361
    //   66: aload_0
    //   67: dup
    //   68: astore 4
    //   70: getfield 46	class_850:jdField_field_183_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   73: invokevirtual 121	java/util/ArrayList:clear	()V
    //   76: aload 4
    //   78: getfield 48	class_850:jdField_field_184_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   81: invokevirtual 121	java/util/ArrayList:clear	()V
    //   84: aload 4
    //   86: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   89: invokevirtual 125	class_765:a143	()Ljava/util/HashSet;
    //   92: invokevirtual 129	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   95: astore 5
    //   97: aload 5
    //   99: invokeinterface 136 1 0
    //   104: ifeq +242 -> 346
    //   107: aload 5
    //   109: invokeinterface 140 1 0
    //   114: checkcast 142	class_777
    //   117: dup
    //   118: astore 6
    //   120: invokevirtual 144	class_777:a	()Ljava/lang/String;
    //   123: aload 4
    //   125: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   128: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   131: invokevirtual 153	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   134: ifeq +153 -> 287
    //   137: aload 4
    //   139: getfield 46	class_850:jdField_field_183_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   142: aload 6
    //   144: invokevirtual 156	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   147: pop
    //   148: aload 4
    //   150: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   153: invokevirtual 159	class_748:a7	()Z
    //   156: ifeq +131 -> 287
    //   159: aload 4
    //   161: getfield 44	class_850:jdField_field_136_of_type_JavaUtilHashSet	Ljava/util/HashSet;
    //   164: aload 6
    //   166: invokevirtual 162	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   169: ifne +118 -> 287
    //   172: aload 4
    //   174: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   177: aload 6
    //   179: invokevirtual 166	class_777:a3	()I
    //   182: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   185: astore 7
    //   187: aload 4
    //   189: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   192: invokevirtual 84	class_748:getState	()Lorg/schema/schine/network/StateInterface;
    //   195: checkcast 104	class_371
    //   198: invokevirtual 174	class_371:a4	()Lclass_52;
    //   201: new 176	java/lang/StringBuilder
    //   204: dup
    //   205: ldc 178
    //   207: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   210: aload 6
    //   212: invokevirtual 184	class_777:b	()Ljava/lang/String;
    //   215: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: ldc 190
    //   220: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: aload 7
    //   225: ifnull +11 -> 236
    //   228: aload 7
    //   230: invokevirtual 193	class_773:a	()Ljava/lang/String;
    //   233: goto +28 -> 261
    //   236: new 176	java/lang/StringBuilder
    //   239: dup
    //   240: ldc 199
    //   242: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   245: aload 6
    //   247: invokevirtual 166	class_777:a3	()I
    //   250: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   253: ldc 204
    //   255: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   261: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: ldc 209
    //   266: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   272: invokevirtual 215	class_52:d1	(Ljava/lang/String;)Lclass_110;
    //   275: pop
    //   276: aload 4
    //   278: getfield 44	class_850:jdField_field_136_of_type_JavaUtilHashSet	Ljava/util/HashSet;
    //   281: aload 6
    //   283: invokevirtual 216	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   286: pop
    //   287: aload 6
    //   289: invokevirtual 184	class_777:b	()Ljava/lang/String;
    //   292: aload 4
    //   294: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   297: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   300: invokevirtual 153	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   303: ifeq +40 -> 343
    //   306: aload 6
    //   308: invokevirtual 166	class_777:a3	()I
    //   311: aload 4
    //   313: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   316: if_icmpne +17 -> 333
    //   319: aload 4
    //   321: getfield 48	class_850:jdField_field_184_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   324: aload 6
    //   326: invokevirtual 156	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   329: pop
    //   330: goto -233 -> 97
    //   333: aload 4
    //   335: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   338: aload 6
    //   340: invokevirtual 222	class_765:a154	(Lclass_777;)V
    //   343: goto -246 -> 97
    //   346: aload 4
    //   348: invokevirtual 225	class_850:setChanged	()V
    //   351: aload 4
    //   353: invokevirtual 228	class_850:notifyObservers	()V
    //   356: aload_0
    //   357: iconst_0
    //   358: putfield 60	class_850:jdField_field_136_of_type_Boolean	Z
    //   361: aload_0
    //   362: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   365: ifnull +19 -> 384
    //   368: aload_0
    //   369: getfield 62	class_850:jdField_field_139_of_type_Boolean	Z
    //   372: ifeq +12 -> 384
    //   375: aload_0
    //   376: invokespecial 231	class_850:f1	()V
    //   379: aload_0
    //   380: iconst_0
    //   381: putfield 62	class_850:jdField_field_139_of_type_Boolean	Z
    //   384: aload_0
    //   385: getfield 233	class_850:jdField_field_182_of_type_Boolean	Z
    //   388: ifeq +68 -> 456
    //   391: aload_0
    //   392: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   395: ifnull +61 -> 456
    //   398: aload_0
    //   399: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   402: aload_0
    //   403: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   406: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   409: dup
    //   410: astore_1
    //   411: ifnonnull +12 -> 423
    //   414: aload_0
    //   415: ldc 64
    //   417: putfield 66	class_850:jdField_field_136_of_type_JavaLangString	Ljava/lang/String;
    //   420: goto +31 -> 451
    //   423: aload_0
    //   424: new 176	java/lang/StringBuilder
    //   427: dup
    //   428: ldc 237
    //   430: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   433: aload_1
    //   434: invokevirtual 193	class_773:a	()Ljava/lang/String;
    //   437: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: ldc 239
    //   442: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   448: putfield 66	class_850:jdField_field_136_of_type_JavaLangString	Ljava/lang/String;
    //   451: aload_0
    //   452: iconst_0
    //   453: putfield 233	class_850:jdField_field_182_of_type_Boolean	Z
    //   456: aload_0
    //   457: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   460: invokevirtual 80	class_748:isOnServer	()Z
    //   463: ifeq +2362 -> 2825
    //   466: aload_0
    //   467: getfield 68	class_850:field_192	Ljava/util/ArrayList;
    //   470: invokevirtual 242	java/util/ArrayList:isEmpty	()Z
    //   473: ifne +246 -> 719
    //   476: aload_0
    //   477: getfield 68	class_850:field_192	Ljava/util/ArrayList;
    //   480: dup
    //   481: astore_1
    //   482: monitorenter
    //   483: aload_0
    //   484: getfield 68	class_850:field_192	Ljava/util/ArrayList;
    //   487: invokevirtual 242	java/util/ArrayList:isEmpty	()Z
    //   490: ifne +219 -> 709
    //   493: aload_0
    //   494: getfield 68	class_850:field_192	Ljava/util/ArrayList;
    //   497: iconst_0
    //   498: invokevirtual 246	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   501: checkcast 248	class_848
    //   504: astore_2
    //   505: aload_0
    //   506: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   509: invokevirtual 252	class_765:a162	()Ljava/util/HashMap;
    //   512: aload_0
    //   513: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   516: invokestatic 258	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   519: invokevirtual 264	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   522: checkcast 266	java/util/TreeSet
    //   525: astore_3
    //   526: iconst_0
    //   527: istore 4
    //   529: aload_3
    //   530: ifnull +131 -> 661
    //   533: aload_3
    //   534: invokevirtual 269	java/util/TreeSet:size	()I
    //   537: ifle +124 -> 661
    //   540: aload_3
    //   541: invokevirtual 273	java/util/TreeSet:descendingSet	()Ljava/util/NavigableSet;
    //   544: invokeinterface 276 1 0
    //   549: astore 5
    //   551: aload 5
    //   553: invokeinterface 136 1 0
    //   558: ifeq +100 -> 658
    //   561: aload 5
    //   563: invokeinterface 140 1 0
    //   568: checkcast 280	class_771
    //   571: astore 6
    //   573: iload 4
    //   575: iconst_5
    //   576: if_icmpge +82 -> 658
    //   579: aload_2
    //   580: getfield 283	class_848:jdField_field_1095_of_type_Long	J
    //   583: lconst_0
    //   584: lcmp
    //   585: ifge +30 -> 615
    //   588: aload_2
    //   589: getfield 286	class_848:jdField_field_1095_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel	Lorg/schema/game/network/objects/NetworkClientChannel;
    //   592: getfield 292	org/schema/game/network/objects/NetworkClientChannel:factionNewsPosts	Lorg/schema/game/network/objects/remote/RemoteFactionNewsPostBuffer;
    //   595: new 294	org/schema/game/network/objects/remote/RemoteFactionNewsPost
    //   598: dup
    //   599: aload 6
    //   601: aload_2
    //   602: getfield 286	class_848:jdField_field_1095_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel	Lorg/schema/game/network/objects/NetworkClientChannel;
    //   605: invokespecial 297	org/schema/game/network/objects/remote/RemoteFactionNewsPost:<init>	(Lclass_771;Lorg/schema/schine/network/objects/NetworkObject;)V
    //   608: invokevirtual 302	org/schema/game/network/objects/remote/RemoteFactionNewsPostBuffer:add	(Lorg/schema/schine/network/objects/remote/Streamable;)Z
    //   611: pop
    //   612: goto -61 -> 551
    //   615: aload 6
    //   617: invokevirtual 308	class_771:a28	()J
    //   620: aload_2
    //   621: getfield 283	class_848:jdField_field_1095_of_type_Long	J
    //   624: lcmp
    //   625: ifge +30 -> 655
    //   628: aload_2
    //   629: getfield 286	class_848:jdField_field_1095_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel	Lorg/schema/game/network/objects/NetworkClientChannel;
    //   632: getfield 292	org/schema/game/network/objects/NetworkClientChannel:factionNewsPosts	Lorg/schema/game/network/objects/remote/RemoteFactionNewsPostBuffer;
    //   635: new 294	org/schema/game/network/objects/remote/RemoteFactionNewsPost
    //   638: dup
    //   639: aload 6
    //   641: aload_2
    //   642: getfield 286	class_848:jdField_field_1095_of_type_OrgSchemaGameNetworkObjectsNetworkClientChannel	Lorg/schema/game/network/objects/NetworkClientChannel;
    //   645: invokespecial 297	org/schema/game/network/objects/remote/RemoteFactionNewsPost:<init>	(Lclass_771;Lorg/schema/schine/network/objects/NetworkObject;)V
    //   648: invokevirtual 302	org/schema/game/network/objects/remote/RemoteFactionNewsPostBuffer:add	(Lorg/schema/schine/network/objects/remote/Streamable;)Z
    //   651: pop
    //   652: iinc 4 1
    //   655: goto -104 -> 551
    //   658: goto -175 -> 483
    //   661: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   664: new 176	java/lang/StringBuilder
    //   667: dup
    //   668: ldc_w 316
    //   671: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   674: aload_0
    //   675: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   678: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   681: ldc_w 321
    //   684: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: aload_0
    //   688: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   691: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   694: ldc_w 323
    //   697: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   703: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   706: goto -223 -> 483
    //   709: aload_1
    //   710: monitorexit
    //   711: goto +8 -> 719
    //   714: astore_2
    //   715: aload_1
    //   716: monitorexit
    //   717: aload_2
    //   718: athrow
    //   719: aload_0
    //   720: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   723: ifeq +195 -> 918
    //   726: aload_0
    //   727: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   730: istore_1
    //   731: aload_0
    //   732: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   735: invokevirtual 84	class_748:getState	()Lorg/schema/schine/network/StateInterface;
    //   738: checkcast 86	class_1041
    //   741: invokevirtual 333	class_1041:a45	()Lclass_765;
    //   744: aload_0
    //   745: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   748: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   751: dup
    //   752: astore_2
    //   753: ifnonnull +72 -> 825
    //   756: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   759: new 176	java/lang/StringBuilder
    //   762: dup
    //   763: ldc_w 335
    //   766: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   769: aload_0
    //   770: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   773: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   776: ldc_w 337
    //   779: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   782: iload_1
    //   783: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   786: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   789: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   792: aload_0
    //   793: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   796: new 339	org/schema/schine/network/server/ServerMessage
    //   799: dup
    //   800: ldc_w 341
    //   803: iconst_3
    //   804: aload_0
    //   805: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   808: invokevirtual 344	class_748:getId	()I
    //   811: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   814: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   817: aload_0
    //   818: iconst_0
    //   819: invokevirtual 355	class_850:a36	(I)V
    //   822: goto +96 -> 918
    //   825: aload_2
    //   826: invokevirtual 356	class_773:a162	()Ljava/util/HashMap;
    //   829: aload_0
    //   830: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   833: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   836: invokevirtual 359	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   839: ifne +79 -> 918
    //   842: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   845: new 176	java/lang/StringBuilder
    //   848: dup
    //   849: ldc_w 361
    //   852: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   855: aload_2
    //   856: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   859: ldc_w 363
    //   862: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   865: aload_0
    //   866: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   869: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   872: ldc_w 365
    //   875: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   878: iload_1
    //   879: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   882: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   885: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   888: aload_0
    //   889: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   892: new 339	org/schema/schine/network/server/ServerMessage
    //   895: dup
    //   896: ldc_w 367
    //   899: iconst_3
    //   900: aload_0
    //   901: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   904: invokevirtual 344	class_748:getId	()I
    //   907: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   910: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   913: aload_0
    //   914: iconst_0
    //   915: invokevirtual 355	class_850:a36	(I)V
    //   918: aload_0
    //   919: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   922: invokevirtual 84	class_748:getState	()Lorg/schema/schine/network/StateInterface;
    //   925: checkcast 86	class_1041
    //   928: astore_1
    //   929: aload_0
    //   930: getfield 35	class_850:jdField_field_136_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   933: invokevirtual 242	java/util/ArrayList:isEmpty	()Z
    //   936: ifne +252 -> 1188
    //   939: aload_0
    //   940: getfield 35	class_850:jdField_field_136_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   943: dup
    //   944: astore_2
    //   945: monitorenter
    //   946: aload_0
    //   947: getfield 35	class_850:jdField_field_136_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   950: invokevirtual 242	java/util/ArrayList:isEmpty	()Z
    //   953: ifne +225 -> 1178
    //   956: aload_0
    //   957: getfield 35	class_850:jdField_field_136_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   960: iconst_0
    //   961: invokevirtual 246	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   964: checkcast 149	java/lang/String
    //   967: astore_3
    //   968: aload_0
    //   969: dup
    //   970: astore 4
    //   972: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   975: invokevirtual 84	class_748:getState	()Lorg/schema/schine/network/StateInterface;
    //   978: checkcast 86	class_1041
    //   981: aconst_null
    //   982: astore 5
    //   984: invokevirtual 333	class_1041:a45	()Lclass_765;
    //   987: aload 4
    //   989: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   992: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   995: dup
    //   996: astore 6
    //   998: ifnull +76 -> 1074
    //   1001: aload 6
    //   1003: invokevirtual 356	class_773:a162	()Ljava/util/HashMap;
    //   1006: aload 4
    //   1008: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1011: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   1014: invokevirtual 264	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1017: checkcast 371	class_758
    //   1020: dup
    //   1021: astore 7
    //   1023: ifnull +8 -> 1031
    //   1026: aload 7
    //   1028: goto +89 -> 1117
    //   1031: aload 4
    //   1033: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1036: new 339	org/schema/schine/network/server/ServerMessage
    //   1039: dup
    //   1040: ldc_w 373
    //   1043: iconst_1
    //   1044: aload 4
    //   1046: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1049: invokevirtual 344	class_748:getId	()I
    //   1052: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   1055: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   1058: new 108	org/schema/game/common/data/player/faction/FactionNotFoundException
    //   1061: dup
    //   1062: aload 4
    //   1064: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   1067: invokestatic 258	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1070: invokespecial 376	org/schema/game/common/data/player/faction/FactionNotFoundException:<init>	(Ljava/lang/Integer;)V
    //   1073: athrow
    //   1074: aload 4
    //   1076: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1079: new 339	org/schema/schine/network/server/ServerMessage
    //   1082: dup
    //   1083: ldc_w 378
    //   1086: iconst_1
    //   1087: aload 4
    //   1089: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1092: invokevirtual 344	class_748:getId	()I
    //   1095: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   1098: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   1101: new 108	org/schema/game/common/data/player/faction/FactionNotFoundException
    //   1104: dup
    //   1105: aload 4
    //   1107: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   1110: invokestatic 258	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1113: invokespecial 376	org/schema/game/common/data/player/faction/FactionNotFoundException:<init>	(Ljava/lang/Integer;)V
    //   1116: athrow
    //   1117: aload_1
    //   1118: invokevirtual 333	class_1041:a45	()Lclass_765;
    //   1121: aload_0
    //   1122: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   1125: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   1128: invokevirtual 384	class_758:a148	(Lclass_773;)Z
    //   1131: ifeq +38 -> 1169
    //   1134: aload_1
    //   1135: invokevirtual 333	class_1041:a45	()Lclass_765;
    //   1138: aload_0
    //   1139: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   1142: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   1145: dup
    //   1146: astore 5
    //   1148: aload_3
    //   1149: invokevirtual 387	class_773:c18	(Ljava/lang/String;)V
    //   1152: aload 5
    //   1154: aload_0
    //   1155: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1158: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   1161: aload_3
    //   1162: aload_1
    //   1163: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   1166: invokevirtual 391	class_773:b35	(Ljava/lang/String;Ljava/lang/String;Lclass_800;)V
    //   1169: goto -223 -> 946
    //   1172: invokevirtual 394	org/schema/game/common/data/player/faction/FactionNotFoundException:printStackTrace	()V
    //   1175: goto -229 -> 946
    //   1178: aload_2
    //   1179: monitorexit
    //   1180: goto +8 -> 1188
    //   1183: astore_1
    //   1184: aload_2
    //   1185: monitorexit
    //   1186: aload_1
    //   1187: athrow
    //   1188: aload_0
    //   1189: getfield 37	class_850:jdField_field_139_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   1192: invokevirtual 242	java/util/ArrayList:isEmpty	()Z
    //   1195: ifne +42 -> 1237
    //   1198: aload_0
    //   1199: getfield 37	class_850:jdField_field_139_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   1202: dup
    //   1203: astore_2
    //   1204: monitorenter
    //   1205: aload_0
    //   1206: getfield 37	class_850:jdField_field_139_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   1209: invokevirtual 242	java/util/ArrayList:isEmpty	()Z
    //   1212: ifne +15 -> 1227
    //   1215: aload_0
    //   1216: getfield 37	class_850:jdField_field_139_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   1219: iconst_0
    //   1220: invokevirtual 246	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   1223: pop
    //   1224: goto -19 -> 1205
    //   1227: aload_2
    //   1228: monitorexit
    //   1229: goto +8 -> 1237
    //   1232: astore_1
    //   1233: aload_2
    //   1234: monitorexit
    //   1235: aload_1
    //   1236: athrow
    //   1237: aload_0
    //   1238: getfield 39	class_850:jdField_field_182_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   1241: invokevirtual 242	java/util/ArrayList:isEmpty	()Z
    //   1244: ifne +96 -> 1340
    //   1247: aload_0
    //   1248: getfield 39	class_850:jdField_field_182_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   1251: dup
    //   1252: astore_2
    //   1253: monitorenter
    //   1254: aload_0
    //   1255: getfield 39	class_850:jdField_field_182_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   1258: invokevirtual 242	java/util/ArrayList:isEmpty	()Z
    //   1261: ifne +69 -> 1330
    //   1264: aload_0
    //   1265: getfield 39	class_850:jdField_field_182_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   1268: iconst_0
    //   1269: invokevirtual 246	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   1272: checkcast 192	class_773
    //   1275: dup
    //   1276: astore_3
    //   1277: invokestatic 395	class_765:a3	()I
    //   1280: invokevirtual 396	class_773:a36	(I)V
    //   1283: aload_1
    //   1284: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   1287: invokevirtual 96	class_800:a51	()Lclass_765;
    //   1290: aload_3
    //   1291: invokevirtual 400	class_765:a153	(Lclass_773;)V
    //   1294: aload_0
    //   1295: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   1298: ifeq +21 -> 1319
    //   1301: aload_1
    //   1302: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   1305: invokevirtual 96	class_800:a51	()Lclass_765;
    //   1308: aload_0
    //   1309: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   1312: aload_0
    //   1313: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1316: invokevirtual 404	class_765:a160	(ILclass_748;)V
    //   1319: aload_0
    //   1320: aload_3
    //   1321: invokevirtual 405	class_773:a3	()I
    //   1324: invokevirtual 355	class_850:a36	(I)V
    //   1327: goto -73 -> 1254
    //   1330: aload_2
    //   1331: monitorexit
    //   1332: goto +8 -> 1340
    //   1335: astore_1
    //   1336: aload_2
    //   1337: monitorexit
    //   1338: aload_1
    //   1339: athrow
    //   1340: aload_0
    //   1341: getfield 54	class_850:jdField_field_139_of_type_Int	I
    //   1344: iflt +273 -> 1617
    //   1347: aload_1
    //   1348: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   1351: invokevirtual 96	class_800:a51	()Lclass_765;
    //   1354: aload_0
    //   1355: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   1358: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   1361: dup
    //   1362: astore_2
    //   1363: ifnull +198 -> 1561
    //   1366: aload_2
    //   1367: invokevirtual 356	class_773:a162	()Ljava/util/HashMap;
    //   1370: aload_0
    //   1371: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1374: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   1377: invokevirtual 264	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1380: checkcast 371	class_758
    //   1383: dup
    //   1384: astore_3
    //   1385: ifnull +122 -> 1507
    //   1388: aload_3
    //   1389: aload_2
    //   1390: invokevirtual 384	class_758:a148	(Lclass_773;)Z
    //   1393: ifeq +60 -> 1453
    //   1396: aload_2
    //   1397: aload_0
    //   1398: getfield 54	class_850:jdField_field_139_of_type_Int	I
    //   1401: iconst_1
    //   1402: if_icmpne +7 -> 1409
    //   1405: iconst_1
    //   1406: goto +4 -> 1410
    //   1409: iconst_0
    //   1410: invokevirtual 409	class_773:c6	(Z)V
    //   1413: aload_2
    //   1414: aload_0
    //   1415: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1418: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   1421: aload_0
    //   1422: getfield 54	class_850:jdField_field_139_of_type_Int	I
    //   1425: iconst_1
    //   1426: if_icmpne +7 -> 1433
    //   1429: iconst_1
    //   1430: goto +4 -> 1434
    //   1433: iconst_0
    //   1434: aload_0
    //   1435: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1438: invokevirtual 84	class_748:getState	()Lorg/schema/schine/network/StateInterface;
    //   1441: checkcast 86	class_1041
    //   1444: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   1447: invokevirtual 413	class_773:a178	(Ljava/lang/String;ZLclass_800;)V
    //   1450: goto +162 -> 1612
    //   1453: aload_0
    //   1454: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1457: new 339	org/schema/schine/network/server/ServerMessage
    //   1460: dup
    //   1461: ldc_w 415
    //   1464: iconst_1
    //   1465: aload_0
    //   1466: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1469: invokevirtual 344	class_748:getId	()I
    //   1472: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   1475: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   1478: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   1481: new 176	java/lang/StringBuilder
    //   1484: dup
    //   1485: ldc_w 417
    //   1488: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1491: aload_0
    //   1492: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1495: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1498: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1501: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1504: goto +108 -> 1612
    //   1507: aload_0
    //   1508: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1511: new 339	org/schema/schine/network/server/ServerMessage
    //   1514: dup
    //   1515: ldc_w 419
    //   1518: iconst_1
    //   1519: aload_0
    //   1520: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1523: invokevirtual 344	class_748:getId	()I
    //   1526: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   1529: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   1532: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   1535: new 176	java/lang/StringBuilder
    //   1538: dup
    //   1539: ldc_w 421
    //   1542: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1545: aload_0
    //   1546: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1549: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1552: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1555: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1558: goto +54 -> 1612
    //   1561: aload_0
    //   1562: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1565: new 339	org/schema/schine/network/server/ServerMessage
    //   1568: dup
    //   1569: ldc_w 423
    //   1572: iconst_1
    //   1573: aload_0
    //   1574: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1577: invokevirtual 344	class_748:getId	()I
    //   1580: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   1583: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   1586: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   1589: new 176	java/lang/StringBuilder
    //   1592: dup
    //   1593: ldc_w 425
    //   1596: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1599: aload_0
    //   1600: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1603: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1606: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1609: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1612: aload_0
    //   1613: iconst_m1
    //   1614: putfield 54	class_850:jdField_field_139_of_type_Int	I
    //   1617: aload_0
    //   1618: getfield 56	class_850:jdField_field_182_of_type_Int	I
    //   1621: iflt +273 -> 1894
    //   1624: aload_1
    //   1625: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   1628: invokevirtual 96	class_800:a51	()Lclass_765;
    //   1631: aload_0
    //   1632: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   1635: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   1638: dup
    //   1639: astore_2
    //   1640: ifnull +198 -> 1838
    //   1643: aload_2
    //   1644: invokevirtual 356	class_773:a162	()Ljava/util/HashMap;
    //   1647: aload_0
    //   1648: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1651: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   1654: invokevirtual 264	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1657: checkcast 371	class_758
    //   1660: dup
    //   1661: astore_3
    //   1662: ifnull +122 -> 1784
    //   1665: aload_3
    //   1666: aload_2
    //   1667: invokevirtual 384	class_758:a148	(Lclass_773;)Z
    //   1670: ifeq +60 -> 1730
    //   1673: aload_2
    //   1674: aload_0
    //   1675: getfield 56	class_850:jdField_field_182_of_type_Int	I
    //   1678: iconst_1
    //   1679: if_icmpne +7 -> 1686
    //   1682: iconst_1
    //   1683: goto +4 -> 1687
    //   1686: iconst_0
    //   1687: invokevirtual 428	class_773:b13	(Z)V
    //   1690: aload_2
    //   1691: aload_0
    //   1692: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1695: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   1698: aload_0
    //   1699: getfield 56	class_850:jdField_field_182_of_type_Int	I
    //   1702: iconst_1
    //   1703: if_icmpne +7 -> 1710
    //   1706: iconst_1
    //   1707: goto +4 -> 1711
    //   1710: iconst_0
    //   1711: aload_0
    //   1712: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1715: invokevirtual 84	class_748:getState	()Lorg/schema/schine/network/StateInterface;
    //   1718: checkcast 86	class_1041
    //   1721: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   1724: invokevirtual 431	class_773:b34	(Ljava/lang/String;ZLclass_800;)V
    //   1727: goto +162 -> 1889
    //   1730: aload_0
    //   1731: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1734: new 339	org/schema/schine/network/server/ServerMessage
    //   1737: dup
    //   1738: ldc_w 415
    //   1741: iconst_1
    //   1742: aload_0
    //   1743: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1746: invokevirtual 344	class_748:getId	()I
    //   1749: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   1752: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   1755: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   1758: new 176	java/lang/StringBuilder
    //   1761: dup
    //   1762: ldc_w 417
    //   1765: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1768: aload_0
    //   1769: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1772: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1775: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1778: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1781: goto +108 -> 1889
    //   1784: aload_0
    //   1785: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1788: new 339	org/schema/schine/network/server/ServerMessage
    //   1791: dup
    //   1792: ldc_w 419
    //   1795: iconst_1
    //   1796: aload_0
    //   1797: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1800: invokevirtual 344	class_748:getId	()I
    //   1803: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   1806: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   1809: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   1812: new 176	java/lang/StringBuilder
    //   1815: dup
    //   1816: ldc_w 421
    //   1819: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1822: aload_0
    //   1823: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1826: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1829: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1832: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1835: goto +54 -> 1889
    //   1838: aload_0
    //   1839: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1842: new 339	org/schema/schine/network/server/ServerMessage
    //   1845: dup
    //   1846: ldc_w 423
    //   1849: iconst_1
    //   1850: aload_0
    //   1851: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1854: invokevirtual 344	class_748:getId	()I
    //   1857: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   1860: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   1863: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   1866: new 176	java/lang/StringBuilder
    //   1869: dup
    //   1870: ldc_w 425
    //   1873: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1876: aload_0
    //   1877: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1880: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1883: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1886: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   1889: aload_0
    //   1890: iconst_m1
    //   1891: putfield 56	class_850:jdField_field_182_of_type_Int	I
    //   1894: aload_0
    //   1895: getfield 58	class_850:jdField_field_183_of_type_Int	I
    //   1898: iflt +273 -> 2171
    //   1901: aload_1
    //   1902: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   1905: invokevirtual 96	class_800:a51	()Lclass_765;
    //   1908: aload_0
    //   1909: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   1912: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   1915: dup
    //   1916: astore_2
    //   1917: ifnull +198 -> 2115
    //   1920: aload_2
    //   1921: invokevirtual 356	class_773:a162	()Ljava/util/HashMap;
    //   1924: aload_0
    //   1925: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1928: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   1931: invokevirtual 264	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1934: checkcast 371	class_758
    //   1937: dup
    //   1938: astore_3
    //   1939: ifnull +122 -> 2061
    //   1942: aload_3
    //   1943: aload_2
    //   1944: invokevirtual 384	class_758:a148	(Lclass_773;)Z
    //   1947: ifeq +60 -> 2007
    //   1950: aload_2
    //   1951: aload_0
    //   1952: getfield 58	class_850:jdField_field_183_of_type_Int	I
    //   1955: iconst_1
    //   1956: if_icmpne +7 -> 1963
    //   1959: iconst_1
    //   1960: goto +4 -> 1964
    //   1963: iconst_0
    //   1964: invokevirtual 434	class_773:d11	(Z)V
    //   1967: aload_2
    //   1968: aload_0
    //   1969: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1972: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   1975: aload_0
    //   1976: getfield 58	class_850:jdField_field_183_of_type_Int	I
    //   1979: iconst_1
    //   1980: if_icmpne +7 -> 1987
    //   1983: iconst_1
    //   1984: goto +4 -> 1988
    //   1987: iconst_0
    //   1988: aload_0
    //   1989: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   1992: invokevirtual 84	class_748:getState	()Lorg/schema/schine/network/StateInterface;
    //   1995: checkcast 86	class_1041
    //   1998: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   2001: invokevirtual 437	class_773:c20	(Ljava/lang/String;ZLclass_800;)V
    //   2004: goto +162 -> 2166
    //   2007: aload_0
    //   2008: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2011: new 339	org/schema/schine/network/server/ServerMessage
    //   2014: dup
    //   2015: ldc_w 415
    //   2018: iconst_1
    //   2019: aload_0
    //   2020: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2023: invokevirtual 344	class_748:getId	()I
    //   2026: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   2029: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   2032: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   2035: new 176	java/lang/StringBuilder
    //   2038: dup
    //   2039: ldc_w 417
    //   2042: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2045: aload_0
    //   2046: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2049: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2052: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2055: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2058: goto +108 -> 2166
    //   2061: aload_0
    //   2062: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2065: new 339	org/schema/schine/network/server/ServerMessage
    //   2068: dup
    //   2069: ldc_w 419
    //   2072: iconst_1
    //   2073: aload_0
    //   2074: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2077: invokevirtual 344	class_748:getId	()I
    //   2080: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   2083: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   2086: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   2089: new 176	java/lang/StringBuilder
    //   2092: dup
    //   2093: ldc_w 421
    //   2096: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2099: aload_0
    //   2100: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2103: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2106: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2109: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2112: goto +54 -> 2166
    //   2115: aload_0
    //   2116: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2119: new 339	org/schema/schine/network/server/ServerMessage
    //   2122: dup
    //   2123: ldc_w 423
    //   2126: iconst_1
    //   2127: aload_0
    //   2128: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2131: invokevirtual 344	class_748:getId	()I
    //   2134: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   2137: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   2140: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   2143: new 176	java/lang/StringBuilder
    //   2146: dup
    //   2147: ldc_w 425
    //   2150: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2153: aload_0
    //   2154: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2157: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2160: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2163: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2166: aload_0
    //   2167: iconst_m1
    //   2168: putfield 58	class_850:jdField_field_183_of_type_Int	I
    //   2171: aload_0
    //   2172: getfield 439	class_850:jdField_field_184_of_type_Int	I
    //   2175: ifeq +228 -> 2403
    //   2178: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   2181: new 176	java/lang/StringBuilder
    //   2184: dup
    //   2185: ldc_w 441
    //   2188: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2191: aload_0
    //   2192: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2195: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2198: ldc_w 443
    //   2201: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2204: aload_0
    //   2205: getfield 439	class_850:jdField_field_184_of_type_Int	I
    //   2208: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2211: ldc_w 445
    //   2214: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2217: aload_0
    //   2218: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   2221: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2224: ldc 204
    //   2226: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2229: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2232: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2235: aload_0
    //   2236: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   2239: aload_0
    //   2240: getfield 439	class_850:jdField_field_184_of_type_Int	I
    //   2243: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   2246: dup
    //   2247: astore_2
    //   2248: ifnull +10 -> 2258
    //   2251: aload_2
    //   2252: invokevirtual 193	class_773:a	()Ljava/lang/String;
    //   2255: goto +28 -> 2283
    //   2258: new 176	java/lang/StringBuilder
    //   2261: dup
    //   2262: ldc_w 447
    //   2265: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2268: aload_0
    //   2269: getfield 439	class_850:jdField_field_184_of_type_Int	I
    //   2272: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2275: ldc 204
    //   2277: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2280: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2283: astore_3
    //   2284: aload_0
    //   2285: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2288: new 339	org/schema/schine/network/server/ServerMessage
    //   2291: dup
    //   2292: new 176	java/lang/StringBuilder
    //   2295: dup
    //   2296: ldc_w 449
    //   2299: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2302: aload_3
    //   2303: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2306: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2309: iconst_1
    //   2310: aload_0
    //   2311: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2314: invokevirtual 344	class_748:getId	()I
    //   2317: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   2320: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   2323: getstatic 451	class_850:jdField_field_184_of_type_Boolean	Z
    //   2326: ifne +52 -> 2378
    //   2329: aload_0
    //   2330: getfield 439	class_850:jdField_field_184_of_type_Int	I
    //   2333: aload_0
    //   2334: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   2337: if_icmpeq +41 -> 2378
    //   2340: new 453	java/lang/AssertionError
    //   2343: dup
    //   2344: new 176	java/lang/StringBuilder
    //   2347: dup
    //   2348: invokespecial 454	java/lang/StringBuilder:<init>	()V
    //   2351: aload_0
    //   2352: getfield 439	class_850:jdField_field_184_of_type_Int	I
    //   2355: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2358: ldc_w 456
    //   2361: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2364: aload_0
    //   2365: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   2368: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2371: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2374: invokespecial 459	java/lang/AssertionError:<init>	(Ljava/lang/Object;)V
    //   2377: athrow
    //   2378: aload_1
    //   2379: invokevirtual 333	class_1041:a45	()Lclass_765;
    //   2382: aload_0
    //   2383: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   2386: aload_0
    //   2387: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2390: invokevirtual 404	class_765:a160	(ILclass_748;)V
    //   2393: aload_0
    //   2394: iconst_0
    //   2395: invokevirtual 355	class_850:a36	(I)V
    //   2398: aload_0
    //   2399: iconst_0
    //   2400: putfield 439	class_850:jdField_field_184_of_type_Int	I
    //   2403: aload_0
    //   2404: getfield 461	class_850:jdField_field_185_of_type_Int	I
    //   2407: ifeq +418 -> 2825
    //   2410: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   2413: new 176	java/lang/StringBuilder
    //   2416: dup
    //   2417: ldc_w 463
    //   2420: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2423: aload_0
    //   2424: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2427: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2430: ldc_w 443
    //   2433: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2436: aload_0
    //   2437: getfield 461	class_850:jdField_field_185_of_type_Int	I
    //   2440: invokevirtual 202	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2443: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2446: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2449: aload_0
    //   2450: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   2453: aload_0
    //   2454: getfield 461	class_850:jdField_field_185_of_type_Int	I
    //   2457: invokevirtual 170	class_765:a156	(I)Lclass_773;
    //   2460: astore_2
    //   2461: aconst_null
    //   2462: astore_3
    //   2463: aload_2
    //   2464: ifnull +326 -> 2790
    //   2467: iconst_1
    //   2468: istore 4
    //   2470: aload_2
    //   2471: invokevirtual 466	class_773:c3	()Z
    //   2474: ifne +6 -> 2480
    //   2477: iconst_0
    //   2478: istore 4
    //   2480: aload_0
    //   2481: getfield 46	class_850:jdField_field_183_of_type_JavaUtilArrayList	Ljava/util/ArrayList;
    //   2484: invokevirtual 467	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   2487: astore 5
    //   2489: aload 5
    //   2491: invokeinterface 136 1 0
    //   2496: ifeq +62 -> 2558
    //   2499: aload 5
    //   2501: invokeinterface 140 1 0
    //   2506: checkcast 142	class_777
    //   2509: dup
    //   2510: astore 6
    //   2512: invokevirtual 166	class_777:a3	()I
    //   2515: aload_2
    //   2516: invokevirtual 405	class_773:a3	()I
    //   2519: if_icmpne +36 -> 2555
    //   2522: iconst_1
    //   2523: istore 4
    //   2525: aload 6
    //   2527: astore_3
    //   2528: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   2531: new 176	java/lang/StringBuilder
    //   2534: dup
    //   2535: ldc_w 469
    //   2538: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2541: aload 6
    //   2543: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2546: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2549: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2552: goto +6 -> 2558
    //   2555: goto -66 -> 2489
    //   2558: iload 4
    //   2560: ifne +10 -> 2570
    //   2563: aload_0
    //   2564: getfield 471	class_850:jdField_field_183_of_type_Boolean	Z
    //   2567: ifeq +195 -> 2762
    //   2570: aload_0
    //   2571: getfield 461	class_850:jdField_field_185_of_type_Int	I
    //   2574: aload_0
    //   2575: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   2578: if_icmpeq +102 -> 2680
    //   2581: aload_0
    //   2582: getfield 218	class_850:jdField_field_136_of_type_Int	I
    //   2585: istore 5
    //   2587: aload_2
    //   2588: aload_0
    //   2589: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2592: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   2595: aload_0
    //   2596: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2599: invokevirtual 147	class_748:getName	()Ljava/lang/String;
    //   2602: iconst_0
    //   2603: aload_0
    //   2604: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   2607: invokevirtual 474	class_765:a161	()Lclass_800;
    //   2610: invokevirtual 478	class_773:a175	(Ljava/lang/String;Ljava/lang/String;BLclass_800;)V
    //   2613: aload_0
    //   2614: aload_0
    //   2615: getfield 461	class_850:jdField_field_185_of_type_Int	I
    //   2618: invokevirtual 355	class_850:a36	(I)V
    //   2621: aload_3
    //   2622: ifnull +34 -> 2656
    //   2625: getstatic 314	java/lang/System:err	Ljava/io/PrintStream;
    //   2628: new 176	java/lang/StringBuilder
    //   2631: dup
    //   2632: ldc_w 480
    //   2635: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2638: aload_3
    //   2639: invokevirtual 319	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2642: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2645: invokevirtual 328	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   2648: aload_0
    //   2649: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   2652: aload_3
    //   2653: invokevirtual 222	class_765:a154	(Lclass_777;)V
    //   2656: iload 5
    //   2658: ifeq +19 -> 2677
    //   2661: aload_1
    //   2662: invokevirtual 90	class_1041:a12	()Lclass_800;
    //   2665: invokevirtual 96	class_800:a51	()Lclass_765;
    //   2668: iload 5
    //   2670: aload_0
    //   2671: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2674: invokevirtual 404	class_765:a160	(ILclass_748;)V
    //   2677: goto +40 -> 2717
    //   2680: aload_0
    //   2681: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2684: new 339	org/schema/schine/network/server/ServerMessage
    //   2687: dup
    //   2688: ldc_w 482
    //   2691: iconst_3
    //   2692: aload_0
    //   2693: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2696: invokevirtual 344	class_748:getId	()I
    //   2699: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   2702: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   2705: aload_3
    //   2706: ifnull +11 -> 2717
    //   2709: aload_0
    //   2710: getfield 110	class_850:jdField_field_136_of_type_Class_765	Lclass_765;
    //   2713: aload_3
    //   2714: invokevirtual 222	class_765:a154	(Lclass_777;)V
    //   2717: aload_0
    //   2718: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2721: new 339	org/schema/schine/network/server/ServerMessage
    //   2724: dup
    //   2725: new 176	java/lang/StringBuilder
    //   2728: dup
    //   2729: ldc_w 484
    //   2732: invokespecial 181	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2735: aload_2
    //   2736: invokevirtual 193	class_773:a	()Ljava/lang/String;
    //   2739: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2742: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2745: iconst_1
    //   2746: aload_0
    //   2747: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2750: invokevirtual 344	class_748:getId	()I
    //   2753: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   2756: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   2759: goto +56 -> 2815
    //   2762: aload_0
    //   2763: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2766: new 339	org/schema/schine/network/server/ServerMessage
    //   2769: dup
    //   2770: ldc_w 486
    //   2773: iconst_3
    //   2774: aload_0
    //   2775: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2778: invokevirtual 344	class_748:getId	()I
    //   2781: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   2784: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   2787: goto +28 -> 2815
    //   2790: aload_0
    //   2791: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2794: new 339	org/schema/schine/network/server/ServerMessage
    //   2797: dup
    //   2798: ldc_w 488
    //   2801: iconst_3
    //   2802: aload_0
    //   2803: getfield 70	class_850:jdField_field_136_of_type_Class_748	Lclass_748;
    //   2806: invokevirtual 344	class_748:getId	()I
    //   2809: invokespecial 347	org/schema/schine/network/server/ServerMessage:<init>	(Ljava/lang/String;II)V
    //   2812: invokevirtual 351	class_748:a129	(Lorg/schema/schine/network/server/ServerMessage;)V
    //   2815: aload_0
    //   2816: iconst_0
    //   2817: putfield 461	class_850:jdField_field_185_of_type_Int	I
    //   2820: aload_0
    //   2821: iconst_0
    //   2822: putfield 471	class_850:jdField_field_183_of_type_Boolean	Z
    //   2825: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2826	0	this	class_850
    //   410	24	1	localclass_773	class_773
    //   481	235	1	Ljava/lang/Object;	Object
    //   730	149	1	i	int
    //   928	190	1	localclass_1041	class_1041
    //   504	138	2	localclass_848	class_848
    //   714	4	2	localObject1	Object
    //   752	193	2	localObject2	Object
    //   525	443	3	localObject3	Object
    //   68	284	4	localclass_8501	class_850
    //   527	126	4	j	int
    //   970	136	4	localclass_8502	class_850
    //   95	888	5	localIterator	Iterator
    //   118	884	6	localObject4	Object
    //   185	842	7	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   483	711	714	finally
    //   968	1169	1172	org/schema/game/common/data/player/faction/FactionNotFoundException
    //   946	1180	1183	finally
    //   1205	1229	1232	finally
    //   1254	1332	1335	finally
  }
  
  private void f1()
  {
    this.jdField_field_185_of_type_JavaUtilArrayList.clear();
    this.field_186.clear();
    Iterator localIterator = this.jdField_field_136_of_type_Class_765.a163().values().iterator();
    while (localIterator.hasNext())
    {
      class_629 localclass_629;
      if ((localclass_629 = (class_629)localIterator.next()).jdField_field_139_of_type_Int == this.jdField_field_136_of_type_Int) {
        this.jdField_field_185_of_type_JavaUtilArrayList.add(localclass_629);
      }
      if (localclass_629.jdField_field_136_of_type_Int == this.jdField_field_136_of_type_Int) {
        this.field_186.add(localclass_629);
      }
    }
    System.err.println("[PlayerFactionManager] reorganized offers in " + this.jdField_field_185_of_type_JavaUtilArrayList.size() + ", out " + this.field_186.size() + " on " + this.jdField_field_136_of_type_Class_748.getState());
    setChanged();
    notifyObservers();
  }
  
  public class_69 toTagStructure()
  {
    class_69[] arrayOfclass_69;
    (arrayOfclass_69 = new class_69[2])[0] = new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_136_of_type_Int));
    arrayOfclass_69[1] = new class_69(class_79.field_548, null, null);
    return new class_69(class_79.field_561, "pFac-v0", arrayOfclass_69);
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if ("pFac-v0".equals(paramclass_69.a2()))
    {
      paramclass_69 = (class_69[])paramclass_69.a4();
      this.jdField_field_136_of_type_Int = ((Integer)paramclass_69[0].a4()).intValue();
      System.err.println("[SERVER] loaded faction for " + this.jdField_field_136_of_type_Class_748 + " -> " + this.jdField_field_136_of_type_Int);
      return;
    }
    System.err.println("[Player Tag Controller][ERROR] Unknown tag version " + paramclass_69.a2());
  }
  
  public final int a3()
  {
    return this.jdField_field_136_of_type_Int;
  }
  
  public final void a36(int paramInt)
  {
    int i = this.jdField_field_136_of_type_Int;
    this.jdField_field_136_of_type_Int = paramInt;
    paramInt = null;
    if ((!this.jdField_field_136_of_type_Class_748.isOnServer()) && (i != this.jdField_field_136_of_type_Int))
    {
      paramInt = (class_371)this.jdField_field_136_of_type_Class_748.getState();
      if ((this.jdField_field_136_of_type_Class_748 != paramInt.a20()) && (this.jdField_field_136_of_type_Int == paramInt.a20().h1())) {
        paramInt.a4().c1(this.jdField_field_136_of_type_Class_748.getName() + "\njoined your faction");
      }
      if ((this.jdField_field_136_of_type_Class_748 != paramInt.a20()) && (i == paramInt.a20().h1())) {
        paramInt.a4().c1(this.jdField_field_136_of_type_Class_748.getName() + "\nleft your faction");
      }
      this.jdField_field_182_of_type_Boolean = true;
    }
    this.jdField_field_136_of_type_Boolean = true;
    this.jdField_field_139_of_type_Boolean = true;
  }
  
  public final void a130(NetworkPlayer paramNetworkPlayer)
  {
    this.jdField_field_136_of_type_Int = ((Integer)paramNetworkPlayer.factionId.get()).intValue();
  }
  
  public final void b6(int paramInt)
  {
    this.jdField_field_136_of_type_Class_748.a127().factionJoinBuffer.add(new RemoteInteger(Integer.valueOf(paramInt), this.jdField_field_136_of_type_Class_748.a127()));
  }
  
  public final void c1()
  {
    if (this.jdField_field_136_of_type_Int != 0) {
      this.jdField_field_136_of_type_Class_748.a127().factionLeaveBuffer.add(new RemoteInteger(Integer.valueOf(this.jdField_field_136_of_type_Int), this.jdField_field_136_of_type_Class_748.a127()));
    }
  }
  
  public final void a203(String paramString1, String paramString2, String paramString3)
  {
    if ((!jdField_field_184_of_type_Boolean) && (this.jdField_field_136_of_type_Class_748.isOnServer())) {
      throw new AssertionError();
    }
    if (System.currentTimeMillis() - this.jdField_field_136_of_type_Long > 10000L)
    {
      paramString1 = new class_773(0, paramString1, paramString2, paramString3);
      this.jdField_field_136_of_type_Class_748.a127().factionCreateBuffer.add(new RemoteFaction(paramString1, this.jdField_field_136_of_type_Class_748.a127()));
      this.jdField_field_136_of_type_Long = System.currentTimeMillis();
      return;
    }
    ((class_371)this.jdField_field_136_of_type_Class_748.getState()).a4().b1("Error:\nSpam protection:\nplease wait 10 seconds\nuntil you can create\nanother faction");
  }
  
  public final void b21(NetworkPlayer paramNetworkPlayer)
  {
    class_773 localclass_773 = null;
    if (this.jdField_field_136_of_type_Class_748.isOnServer())
    {
      if (this.jdField_field_136_of_type_Int != 0) {
        for (paramNetworkPlayer = 0; paramNetworkPlayer < this.jdField_field_136_of_type_Class_748.a127().factionLeaveBuffer.getReceiveBuffer().size(); paramNetworkPlayer++)
        {
          int i = ((Integer)((RemoteInteger)this.jdField_field_136_of_type_Class_748.a127().factionLeaveBuffer.getReceiveBuffer().get(paramNetworkPlayer)).get()).intValue();
          this.jdField_field_184_of_type_Int = i;
        }
      }
      boolean bool;
      for (paramNetworkPlayer = 0; paramNetworkPlayer < this.jdField_field_136_of_type_Class_748.a127().requestFactionOpenToJoin.getReceiveBuffer().size(); paramNetworkPlayer++)
      {
        bool = ((Boolean)((RemoteBoolean)this.jdField_field_136_of_type_Class_748.a127().requestFactionOpenToJoin.getReceiveBuffer().get(paramNetworkPlayer)).get()).booleanValue();
        this.jdField_field_139_of_type_Int = (bool ? 1 : 0);
      }
      for (paramNetworkPlayer = 0; paramNetworkPlayer < this.jdField_field_136_of_type_Class_748.a127().requestAttackNeutral.getReceiveBuffer().size(); paramNetworkPlayer++)
      {
        bool = ((Boolean)((RemoteBoolean)this.jdField_field_136_of_type_Class_748.a127().requestAttackNeutral.getReceiveBuffer().get(paramNetworkPlayer)).get()).booleanValue();
        this.jdField_field_182_of_type_Int = (bool ? 1 : 0);
      }
      for (paramNetworkPlayer = 0; paramNetworkPlayer < this.jdField_field_136_of_type_Class_748.a127().requestAutoDeclareWar.getReceiveBuffer().size(); paramNetworkPlayer++)
      {
        bool = ((Boolean)((RemoteBoolean)this.jdField_field_136_of_type_Class_748.a127().requestAutoDeclareWar.getReceiveBuffer().get(paramNetworkPlayer)).get()).booleanValue();
        this.jdField_field_183_of_type_Int = (bool ? 1 : 0);
      }
      for (paramNetworkPlayer = 0; paramNetworkPlayer < this.jdField_field_136_of_type_Class_748.a127().factionJoinBuffer.getReceiveBuffer().size(); paramNetworkPlayer++)
      {
        int j = ((Integer)((RemoteInteger)this.jdField_field_136_of_type_Class_748.a127().factionJoinBuffer.getReceiveBuffer().get(paramNetworkPlayer)).get()).intValue();
        this.jdField_field_185_of_type_Int = j;
      }
      for (paramNetworkPlayer = 0; paramNetworkPlayer < this.jdField_field_136_of_type_Class_748.a127().factionChatRequests.getReceiveBuffer().size(); paramNetworkPlayer++)
      {
        String str1 = (String)((RemoteString)this.jdField_field_136_of_type_Class_748.a127().factionChatRequests.getReceiveBuffer().get(paramNetworkPlayer)).get();
        synchronized (this.jdField_field_139_of_type_JavaUtilArrayList)
        {
          this.jdField_field_139_of_type_JavaUtilArrayList.add(str1);
        }
      }
      for (paramNetworkPlayer = 0; paramNetworkPlayer < this.jdField_field_136_of_type_Class_748.a127().factionDescriptionEditRequest.getReceiveBuffer().size(); paramNetworkPlayer++)
      {
        String str2 = (String)((RemoteString)this.jdField_field_136_of_type_Class_748.a127().factionDescriptionEditRequest.getReceiveBuffer().get(paramNetworkPlayer)).get();
        synchronized (this.jdField_field_136_of_type_JavaUtilArrayList)
        {
          this.jdField_field_136_of_type_JavaUtilArrayList.add(str2);
        }
      }
      for (paramNetworkPlayer = 0; paramNetworkPlayer < this.jdField_field_136_of_type_Class_748.a127().factionCreateBuffer.getReceiveBuffer().size(); paramNetworkPlayer++)
      {
        (??? = (class_773)((RemoteFaction)this.jdField_field_136_of_type_Class_748.a127().factionCreateBuffer.getReceiveBuffer().get(paramNetworkPlayer)).get()).a162().put(this.jdField_field_136_of_type_Class_748.getName(), new class_758(this.jdField_field_136_of_type_Class_748));
        synchronized (this.jdField_field_182_of_type_JavaUtilArrayList)
        {
          this.jdField_field_182_of_type_JavaUtilArrayList.add(???);
        }
        ((class_773)???).a36(class_765.a3());
      }
      for (paramNetworkPlayer = 0; paramNetworkPlayer < this.jdField_field_136_of_type_Class_748.a127().factionEntityIdChangeBuffer.getReceiveBuffer().size(); paramNetworkPlayer++)
      {
        int m = ((Integer)(??? = (RemoteIntegerArray)this.jdField_field_136_of_type_Class_748.a127().factionEntityIdChangeBuffer.getReceiveBuffer().get(paramNetworkPlayer)).get(0).get()).intValue();
        int k = ((Integer)((RemoteIntegerArray)???).get(1).get()).intValue();
        if ((m == this.jdField_field_136_of_type_Int) || (m == 0))
        {
          Object localObject3 = (Sendable)this.jdField_field_136_of_type_Class_748.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(k);
          String str3 = "Neutral";
          localclass_773 = this.jdField_field_136_of_type_Class_765.a156(m);
          if ((m != 0) && (localclass_773 == null))
          {
            System.err.println("[PlayerFactionController][SERVER][ERROR] target factionId dos not exist " + localObject3 + "; target: " + m);
            m = 0;
          }
          if (localclass_773 != null) {
            str3 = localclass_773.a();
          }
          if ((localObject3 != null) && ((localObject3 instanceof class_797)))
          {
            if (((localObject3 = (class_797)localObject3) instanceof class_365)) {
              ((class_365)localObject3).a26().contains(this.jdField_field_136_of_type_Class_748);
            }
            if ((((class_797)localObject3).getFactionId() == 0) || (this.jdField_field_136_of_type_Int == ((class_797)localObject3).getFactionId()) || (this.jdField_field_136_of_type_Class_765.a156(((class_797)localObject3).getFactionId()) != null))
            {
              this.jdField_field_136_of_type_Class_748.a129(new ServerMessage("Faction Module:\nchanged Faction of " + ((class_797)localObject3).toNiceString() + "\n to\n" + str3, 1, this.jdField_field_136_of_type_Class_748.getId()));
              ((class_797)localObject3).setFactionId(m);
            }
          }
          else
          {
            System.err.println("[PlayerFactionController][SERVER][ERROR] cant change faction id of etity " + localObject3);
          }
        }
        else
        {
          System.err.println("[PlayerFactionController][SERVER][ERROR] cant change faction id of entity " + m + "/" + this.jdField_field_136_of_type_Int);
        }
      }
      return;
    }
    paramNetworkPlayer = ((Integer)paramNetworkPlayer.factionId.get()).intValue();
    if (this.jdField_field_136_of_type_Int != paramNetworkPlayer) {
      a36(paramNetworkPlayer);
    }
  }
  
  public final void d2()
  {
    this.jdField_field_136_of_type_Class_748.a127().factionId.set(Integer.valueOf(this.jdField_field_136_of_type_Int));
  }
  
  public final void e2()
  {
    if (this.jdField_field_136_of_type_Class_748.isOnServer()) {
      this.jdField_field_136_of_type_Class_748.a127().factionId.set(Integer.valueOf(this.jdField_field_136_of_type_Int));
    }
  }
  
  public final String a()
  {
    if (this.jdField_field_136_of_type_Int == 0) {
      return "NO FACTION";
    }
    if (!((class_1039)this.jdField_field_136_of_type_Class_748.getState()).a().a18(this.jdField_field_136_of_type_Int)) {
      return "...CALCULATING(" + this.jdField_field_136_of_type_Int + ")";
    }
    return ((class_1039)this.jdField_field_136_of_type_Class_748.getState()).a().a156(this.jdField_field_136_of_type_Int).a();
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if ("INVITATION_UPDATE".equals(paramObject))
    {
      this.jdField_field_136_of_type_Boolean = true;
      return;
    }
    if ("RELATIONSHIP_OFFER".equals(paramObject)) {
      this.jdField_field_139_of_type_Boolean = true;
    }
  }
  
  public final boolean a204(String paramString)
  {
    this.jdField_field_136_of_type_Class_748.a127().factionDescriptionEditRequest.add(new RemoteString(paramString, this.jdField_field_136_of_type_Class_748.a127()));
    return true;
  }
  
  public final boolean b39(String paramString)
  {
    class_771 localclass_771;
    (localclass_771 = new class_771()).a170(this.jdField_field_136_of_type_Int, this.jdField_field_136_of_type_Class_748.getName(), System.currentTimeMillis(), paramString, 0);
    ((class_371)this.jdField_field_136_of_type_Class_748.getState()).a12().a52().factionNewsPosts.add(new RemoteFactionNewsPost(localclass_771, ((class_371)this.jdField_field_136_of_type_Class_748.getState()).a12().a52()));
    return true;
  }
  
  public final ArrayList a75()
  {
    return this.jdField_field_183_of_type_JavaUtilArrayList;
  }
  
  public final ArrayList b40()
  {
    return this.jdField_field_184_of_type_JavaUtilArrayList;
  }
  
  public final ArrayList c23()
  {
    return this.jdField_field_185_of_type_JavaUtilArrayList;
  }
  
  public final class_762 a142(int paramInt)
  {
    if (this.jdField_field_136_of_type_Class_765 != null) {
      return this.jdField_field_136_of_type_Class_765.a159(this.jdField_field_136_of_type_Int, paramInt);
    }
    return class_762.field_1022;
  }
  
  public final void a205(int paramInt, class_797 paramclass_797)
  {
    if ((!jdField_field_184_of_type_Boolean) && (this.jdField_field_136_of_type_Class_748.isOnServer())) {
      throw new AssertionError();
    }
    RemoteIntegerArray localRemoteIntegerArray;
    (localRemoteIntegerArray = new RemoteIntegerArray(2, this.jdField_field_136_of_type_Class_748.a127())).set(0, Integer.valueOf(paramInt));
    localRemoteIntegerArray.set(1, Integer.valueOf(paramclass_797.getId()));
    this.jdField_field_136_of_type_Class_748.a127().factionEntityIdChangeBuffer.add(localRemoteIntegerArray);
  }
  
  public final String b()
  {
    return this.jdField_field_136_of_type_JavaLangString;
  }
  
  public final void a206(NetworkClientChannel paramNetworkClientChannel, long arg2)
  {
    paramNetworkClientChannel = new class_848(paramNetworkClientChannel, ???);
    synchronized (this.field_192)
    {
      this.field_192.add(paramNetworkClientChannel);
      return;
    }
  }
  
  public final void c13(int paramInt)
  {
    this.jdField_field_185_of_type_Int = paramInt;
    this.jdField_field_183_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_850
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */