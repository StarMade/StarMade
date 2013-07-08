/*    1:     */package org.lwjgl.openal;
/*    2:     */
/*    3:     */import java.nio.ByteBuffer;
/*    4:     */import java.nio.DoubleBuffer;
/*    5:     */import java.nio.FloatBuffer;
/*    6:     */import java.nio.IntBuffer;
/*    7:     */import java.nio.ShortBuffer;
/*    8:     */import org.lwjgl.BufferChecks;
/*    9:     */import org.lwjgl.LWJGLException;
/*   10:     */import org.lwjgl.MemoryUtil;
/*   11:     */
/*  271:     */public final class AL10
/*  272:     */{
/*  273:     */  public static final int AL_INVALID = -1;
/*  274:     */  public static final int AL_NONE = 0;
/*  275:     */  public static final int AL_FALSE = 0;
/*  276:     */  public static final int AL_TRUE = 1;
/*  277:     */  public static final int AL_SOURCE_TYPE = 4135;
/*  278:     */  public static final int AL_SOURCE_ABSOLUTE = 513;
/*  279:     */  public static final int AL_SOURCE_RELATIVE = 514;
/*  280:     */  public static final int AL_CONE_INNER_ANGLE = 4097;
/*  281:     */  public static final int AL_CONE_OUTER_ANGLE = 4098;
/*  282:     */  public static final int AL_PITCH = 4099;
/*  283:     */  public static final int AL_POSITION = 4100;
/*  284:     */  public static final int AL_DIRECTION = 4101;
/*  285:     */  public static final int AL_VELOCITY = 4102;
/*  286:     */  public static final int AL_LOOPING = 4103;
/*  287:     */  public static final int AL_BUFFER = 4105;
/*  288:     */  public static final int AL_GAIN = 4106;
/*  289:     */  public static final int AL_MIN_GAIN = 4109;
/*  290:     */  public static final int AL_MAX_GAIN = 4110;
/*  291:     */  public static final int AL_ORIENTATION = 4111;
/*  292:     */  public static final int AL_REFERENCE_DISTANCE = 4128;
/*  293:     */  public static final int AL_ROLLOFF_FACTOR = 4129;
/*  294:     */  public static final int AL_CONE_OUTER_GAIN = 4130;
/*  295:     */  public static final int AL_MAX_DISTANCE = 4131;
/*  296:     */  public static final int AL_CHANNEL_MASK = 12288;
/*  297:     */  public static final int AL_SOURCE_STATE = 4112;
/*  298:     */  public static final int AL_INITIAL = 4113;
/*  299:     */  public static final int AL_PLAYING = 4114;
/*  300:     */  public static final int AL_PAUSED = 4115;
/*  301:     */  public static final int AL_STOPPED = 4116;
/*  302:     */  public static final int AL_BUFFERS_QUEUED = 4117;
/*  303:     */  public static final int AL_BUFFERS_PROCESSED = 4118;
/*  304:     */  public static final int AL_FORMAT_MONO8 = 4352;
/*  305:     */  public static final int AL_FORMAT_MONO16 = 4353;
/*  306:     */  public static final int AL_FORMAT_STEREO8 = 4354;
/*  307:     */  public static final int AL_FORMAT_STEREO16 = 4355;
/*  308:     */  public static final int AL_FORMAT_VORBIS_EXT = 65539;
/*  309:     */  public static final int AL_FREQUENCY = 8193;
/*  310:     */  public static final int AL_BITS = 8194;
/*  311:     */  public static final int AL_CHANNELS = 8195;
/*  312:     */  public static final int AL_SIZE = 8196;
/*  313:     */  /**
/*  314:     */   * @deprecated
/*  315:     */   */
/*  316:     */  public static final int AL_DATA = 8197;
/*  317:     */  public static final int AL_UNUSED = 8208;
/*  318:     */  public static final int AL_PENDING = 8209;
/*  319:     */  public static final int AL_PROCESSED = 8210;
/*  320:     */  public static final int AL_NO_ERROR = 0;
/*  321:     */  public static final int AL_INVALID_NAME = 40961;
/*  322:     */  public static final int AL_INVALID_ENUM = 40962;
/*  323:     */  public static final int AL_INVALID_VALUE = 40963;
/*  324:     */  public static final int AL_INVALID_OPERATION = 40964;
/*  325:     */  public static final int AL_OUT_OF_MEMORY = 40965;
/*  326:     */  public static final int AL_VENDOR = 45057;
/*  327:     */  public static final int AL_VERSION = 45058;
/*  328:     */  public static final int AL_RENDERER = 45059;
/*  329:     */  public static final int AL_EXTENSIONS = 45060;
/*  330:     */  public static final int AL_DOPPLER_FACTOR = 49152;
/*  331:     */  public static final int AL_DOPPLER_VELOCITY = 49153;
/*  332:     */  public static final int AL_DISTANCE_MODEL = 53248;
/*  333:     */  public static final int AL_INVERSE_DISTANCE = 53249;
/*  334:     */  public static final int AL_INVERSE_DISTANCE_CLAMPED = 53250;
/*  335:     */  
/*  336:     */  static native void initNativeStubs()
/*  337:     */    throws LWJGLException;
/*  338:     */  
/*  339:     */  public static void alEnable(int capability)
/*  340:     */  {
/*  341: 341 */    nalEnable(capability);
/*  342:     */  }
/*  343:     */  
/*  347:     */  static native void nalEnable(int paramInt);
/*  348:     */  
/*  351:     */  public static void alDisable(int capability)
/*  352:     */  {
/*  353: 353 */    nalDisable(capability);
/*  354:     */  }
/*  355:     */  
/*  362:     */  static native void nalDisable(int paramInt);
/*  363:     */  
/*  370:     */  public static boolean alIsEnabled(int capability)
/*  371:     */  {
/*  372: 372 */    boolean __result = nalIsEnabled(capability);
/*  373: 373 */    return __result;
/*  374:     */  }
/*  375:     */  
/*  382:     */  static native boolean nalIsEnabled(int paramInt);
/*  383:     */  
/*  389:     */  public static boolean alGetBoolean(int pname)
/*  390:     */  {
/*  391: 391 */    boolean __result = nalGetBoolean(pname);
/*  392: 392 */    return __result;
/*  393:     */  }
/*  394:     */  
/*  401:     */  static native boolean nalGetBoolean(int paramInt);
/*  402:     */  
/*  408:     */  public static int alGetInteger(int pname)
/*  409:     */  {
/*  410: 410 */    int __result = nalGetInteger(pname);
/*  411: 411 */    return __result;
/*  412:     */  }
/*  413:     */  
/*  420:     */  static native int nalGetInteger(int paramInt);
/*  421:     */  
/*  427:     */  public static float alGetFloat(int pname)
/*  428:     */  {
/*  429: 429 */    float __result = nalGetFloat(pname);
/*  430: 430 */    return __result;
/*  431:     */  }
/*  432:     */  
/*  439:     */  static native float nalGetFloat(int paramInt);
/*  440:     */  
/*  446:     */  public static double alGetDouble(int pname)
/*  447:     */  {
/*  448: 448 */    double __result = nalGetDouble(pname);
/*  449: 449 */    return __result;
/*  450:     */  }
/*  451:     */  
/*  458:     */  static native double nalGetDouble(int paramInt);
/*  459:     */  
/*  466:     */  public static void alGetInteger(int pname, IntBuffer data)
/*  467:     */  {
/*  468: 468 */    BufferChecks.checkBuffer(data, 1);
/*  469: 469 */    nalGetIntegerv(pname, MemoryUtil.getAddress(data));
/*  470:     */  }
/*  471:     */  
/*  478:     */  static native void nalGetIntegerv(int paramInt, long paramLong);
/*  479:     */  
/*  486:     */  public static void alGetFloat(int pname, FloatBuffer data)
/*  487:     */  {
/*  488: 488 */    BufferChecks.checkBuffer(data, 1);
/*  489: 489 */    nalGetFloatv(pname, MemoryUtil.getAddress(data));
/*  490:     */  }
/*  491:     */  
/*  498:     */  static native void nalGetFloatv(int paramInt, long paramLong);
/*  499:     */  
/*  506:     */  public static void alGetDouble(int pname, DoubleBuffer data)
/*  507:     */  {
/*  508: 508 */    BufferChecks.checkBuffer(data, 1);
/*  509: 509 */    nalGetDoublev(pname, MemoryUtil.getAddress(data));
/*  510:     */  }
/*  511:     */  
/*  516:     */  static native void nalGetDoublev(int paramInt, long paramLong);
/*  517:     */  
/*  522:     */  public static String alGetString(int pname)
/*  523:     */  {
/*  524: 524 */    String __result = nalGetString(pname);
/*  525: 525 */    return __result;
/*  526:     */  }
/*  527:     */  
/*  566:     */  static native String nalGetString(int paramInt);
/*  567:     */  
/*  605:     */  public static int alGetError()
/*  606:     */  {
/*  607: 607 */    int __result = nalGetError();
/*  608: 608 */    return __result;
/*  609:     */  }
/*  610:     */  
/*  616:     */  static native int nalGetError();
/*  617:     */  
/*  622:     */  public static boolean alIsExtensionPresent(String fname)
/*  623:     */  {
/*  624: 624 */    BufferChecks.checkNotNull(fname);
/*  625: 625 */    boolean __result = nalIsExtensionPresent(fname);
/*  626: 626 */    return __result;
/*  627:     */  }
/*  628:     */  
/*  637:     */  static native boolean nalIsExtensionPresent(String paramString);
/*  638:     */  
/*  647:     */  public static int alGetEnumValue(String ename)
/*  648:     */  {
/*  649: 649 */    BufferChecks.checkNotNull(ename);
/*  650: 650 */    int __result = nalGetEnumValue(ename);
/*  651: 651 */    return __result;
/*  652:     */  }
/*  653:     */  
/*  656:     */  static native int nalGetEnumValue(String paramString);
/*  657:     */  
/*  660:     */  public static void alListeneri(int pname, int value)
/*  661:     */  {
/*  662: 662 */    nalListeneri(pname, value);
/*  663:     */  }
/*  664:     */  
/*  667:     */  static native void nalListeneri(int paramInt1, int paramInt2);
/*  668:     */  
/*  671:     */  public static void alListenerf(int pname, float value)
/*  672:     */  {
/*  673: 673 */    nalListenerf(pname, value);
/*  674:     */  }
/*  675:     */  
/*  678:     */  static native void nalListenerf(int paramInt, float paramFloat);
/*  679:     */  
/*  682:     */  public static void alListener(int pname, FloatBuffer value)
/*  683:     */  {
/*  684: 684 */    BufferChecks.checkBuffer(value, 1);
/*  685: 685 */    nalListenerfv(pname, MemoryUtil.getAddress(value));
/*  686:     */  }
/*  687:     */  
/*  691:     */  static native void nalListenerfv(int paramInt, long paramLong);
/*  692:     */  
/*  696:     */  public static void alListener3f(int pname, float v1, float v2, float v3)
/*  697:     */  {
/*  698: 698 */    nalListener3f(pname, v1, v2, v3);
/*  699:     */  }
/*  700:     */  
/*  704:     */  static native void nalListener3f(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3);
/*  705:     */  
/*  708:     */  public static int alGetListeneri(int pname)
/*  709:     */  {
/*  710: 710 */    int __result = nalGetListeneri(pname);
/*  711: 711 */    return __result;
/*  712:     */  }
/*  713:     */  
/*  717:     */  static native int nalGetListeneri(int paramInt);
/*  718:     */  
/*  721:     */  public static float alGetListenerf(int pname)
/*  722:     */  {
/*  723: 723 */    float __result = nalGetListenerf(pname);
/*  724: 724 */    return __result;
/*  725:     */  }
/*  726:     */  
/*  730:     */  static native float nalGetListenerf(int paramInt);
/*  731:     */  
/*  734:     */  public static void alGetListener(int pname, FloatBuffer floatdata)
/*  735:     */  {
/*  736: 736 */    BufferChecks.checkBuffer(floatdata, 1);
/*  737: 737 */    nalGetListenerfv(pname, MemoryUtil.getAddress(floatdata));
/*  738:     */  }
/*  739:     */  
/*  742:     */  static native void nalGetListenerfv(int paramInt, long paramLong);
/*  743:     */  
/*  745:     */  public static void alGenSources(IntBuffer sources)
/*  746:     */  {
/*  747: 747 */    BufferChecks.checkDirect(sources);
/*  748: 748 */    nalGenSources(sources.remaining(), MemoryUtil.getAddress(sources));
/*  749:     */  }
/*  750:     */  
/*  751:     */  static native void nalGenSources(int paramInt, long paramLong);
/*  752:     */  
/*  753:     */  public static int alGenSources() {
/*  754: 754 */    int __result = nalGenSources2(1);
/*  755: 755 */    return __result;
/*  756:     */  }
/*  757:     */  
/*  760:     */  static native int nalGenSources2(int paramInt);
/*  761:     */  
/*  763:     */  public static void alDeleteSources(IntBuffer sources)
/*  764:     */  {
/*  765: 765 */    BufferChecks.checkDirect(sources);
/*  766: 766 */    nalDeleteSources(sources.remaining(), MemoryUtil.getAddress(sources));
/*  767:     */  }
/*  768:     */  
/*  769:     */  static native void nalDeleteSources(int paramInt, long paramLong);
/*  770:     */  
/*  771:     */  public static void alDeleteSources(int source) {
/*  772: 772 */    nalDeleteSources2(1, source);
/*  773:     */  }
/*  774:     */  
/*  777:     */  static native void nalDeleteSources2(int paramInt1, int paramInt2);
/*  778:     */  
/*  781:     */  public static boolean alIsSource(int id)
/*  782:     */  {
/*  783: 783 */    boolean __result = nalIsSource(id);
/*  784: 784 */    return __result;
/*  785:     */  }
/*  786:     */  
/*  790:     */  static native boolean nalIsSource(int paramInt);
/*  791:     */  
/*  795:     */  public static void alSourcei(int source, int pname, int value)
/*  796:     */  {
/*  797: 797 */    nalSourcei(source, pname, value);
/*  798:     */  }
/*  799:     */  
/*  803:     */  static native void nalSourcei(int paramInt1, int paramInt2, int paramInt3);
/*  804:     */  
/*  808:     */  public static void alSourcef(int source, int pname, float value)
/*  809:     */  {
/*  810: 810 */    nalSourcef(source, pname, value);
/*  811:     */  }
/*  812:     */  
/*  816:     */  static native void nalSourcef(int paramInt1, int paramInt2, float paramFloat);
/*  817:     */  
/*  821:     */  public static void alSource(int source, int pname, FloatBuffer value)
/*  822:     */  {
/*  823: 823 */    BufferChecks.checkBuffer(value, 1);
/*  824: 824 */    nalSourcefv(source, pname, MemoryUtil.getAddress(value));
/*  825:     */  }
/*  826:     */  
/*  831:     */  static native void nalSourcefv(int paramInt1, int paramInt2, long paramLong);
/*  832:     */  
/*  837:     */  public static void alSource3f(int source, int pname, float v1, float v2, float v3)
/*  838:     */  {
/*  839: 839 */    nalSource3f(source, pname, v1, v2, v3);
/*  840:     */  }
/*  841:     */  
/*  846:     */  static native void nalSource3f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3);
/*  847:     */  
/*  851:     */  public static int alGetSourcei(int source, int pname)
/*  852:     */  {
/*  853: 853 */    int __result = nalGetSourcei(source, pname);
/*  854: 854 */    return __result;
/*  855:     */  }
/*  856:     */  
/*  861:     */  static native int nalGetSourcei(int paramInt1, int paramInt2);
/*  862:     */  
/*  866:     */  public static float alGetSourcef(int source, int pname)
/*  867:     */  {
/*  868: 868 */    float __result = nalGetSourcef(source, pname);
/*  869: 869 */    return __result;
/*  870:     */  }
/*  871:     */  
/*  876:     */  static native float nalGetSourcef(int paramInt1, int paramInt2);
/*  877:     */  
/*  881:     */  public static void alGetSource(int source, int pname, FloatBuffer floatdata)
/*  882:     */  {
/*  883: 883 */    BufferChecks.checkBuffer(floatdata, 1);
/*  884: 884 */    nalGetSourcefv(source, pname, MemoryUtil.getAddress(floatdata));
/*  885:     */  }
/*  886:     */  
/*  892:     */  static native void nalGetSourcefv(int paramInt1, int paramInt2, long paramLong);
/*  893:     */  
/*  899:     */  public static void alSourcePlay(IntBuffer sources)
/*  900:     */  {
/*  901: 901 */    BufferChecks.checkDirect(sources);
/*  902: 902 */    nalSourcePlayv(sources.remaining(), MemoryUtil.getAddress(sources));
/*  903:     */  }
/*  904:     */  
/*  908:     */  static native void nalSourcePlayv(int paramInt, long paramLong);
/*  909:     */  
/*  913:     */  public static void alSourcePause(IntBuffer sources)
/*  914:     */  {
/*  915: 915 */    BufferChecks.checkDirect(sources);
/*  916: 916 */    nalSourcePausev(sources.remaining(), MemoryUtil.getAddress(sources));
/*  917:     */  }
/*  918:     */  
/*  923:     */  static native void nalSourcePausev(int paramInt, long paramLong);
/*  924:     */  
/*  928:     */  public static void alSourceStop(IntBuffer sources)
/*  929:     */  {
/*  930: 930 */    BufferChecks.checkDirect(sources);
/*  931: 931 */    nalSourceStopv(sources.remaining(), MemoryUtil.getAddress(sources));
/*  932:     */  }
/*  933:     */  
/*  939:     */  static native void nalSourceStopv(int paramInt, long paramLong);
/*  940:     */  
/*  945:     */  public static void alSourceRewind(IntBuffer sources)
/*  946:     */  {
/*  947: 947 */    BufferChecks.checkDirect(sources);
/*  948: 948 */    nalSourceRewindv(sources.remaining(), MemoryUtil.getAddress(sources));
/*  949:     */  }
/*  950:     */  
/*  956:     */  static native void nalSourceRewindv(int paramInt, long paramLong);
/*  957:     */  
/*  963:     */  public static void alSourcePlay(int source)
/*  964:     */  {
/*  965: 965 */    nalSourcePlay(source);
/*  966:     */  }
/*  967:     */  
/*  971:     */  static native void nalSourcePlay(int paramInt);
/*  972:     */  
/*  976:     */  public static void alSourcePause(int source)
/*  977:     */  {
/*  978: 978 */    nalSourcePause(source);
/*  979:     */  }
/*  980:     */  
/*  985:     */  static native void nalSourcePause(int paramInt);
/*  986:     */  
/*  990:     */  public static void alSourceStop(int source)
/*  991:     */  {
/*  992: 992 */    nalSourceStop(source);
/*  993:     */  }
/*  994:     */  
/* 1000:     */  static native void nalSourceStop(int paramInt);
/* 1001:     */  
/* 1006:     */  public static void alSourceRewind(int source)
/* 1007:     */  {
/* 1008:1008 */    nalSourceRewind(source);
/* 1009:     */  }
/* 1010:     */  
/* 1013:     */  static native void nalSourceRewind(int paramInt);
/* 1014:     */  
/* 1016:     */  public static void alGenBuffers(IntBuffer buffers)
/* 1017:     */  {
/* 1018:1018 */    BufferChecks.checkDirect(buffers);
/* 1019:1019 */    nalGenBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers));
/* 1020:     */  }
/* 1021:     */  
/* 1022:     */  static native void nalGenBuffers(int paramInt, long paramLong);
/* 1023:     */  
/* 1024:     */  public static int alGenBuffers() {
/* 1025:1025 */    int __result = nalGenBuffers2(1);
/* 1026:1026 */    return __result;
/* 1027:     */  }
/* 1028:     */  
/* 1036:     */  static native int nalGenBuffers2(int paramInt);
/* 1037:     */  
/* 1045:     */  public static void alDeleteBuffers(IntBuffer buffers)
/* 1046:     */  {
/* 1047:1047 */    BufferChecks.checkDirect(buffers);
/* 1048:1048 */    nalDeleteBuffers(buffers.remaining(), MemoryUtil.getAddress(buffers));
/* 1049:     */  }
/* 1050:     */  
/* 1051:     */  static native void nalDeleteBuffers(int paramInt, long paramLong);
/* 1052:     */  
/* 1053:     */  public static void alDeleteBuffers(int buffer) {
/* 1054:1054 */    nalDeleteBuffers2(1, buffer);
/* 1055:     */  }
/* 1056:     */  
/* 1059:     */  static native void nalDeleteBuffers2(int paramInt1, int paramInt2);
/* 1060:     */  
/* 1063:     */  public static boolean alIsBuffer(int buffer)
/* 1064:     */  {
/* 1065:1065 */    boolean __result = nalIsBuffer(buffer);
/* 1066:1066 */    return __result;
/* 1067:     */  }
/* 1068:     */  
/* 1082:     */  static native boolean nalIsBuffer(int paramInt);
/* 1083:     */  
/* 1096:     */  public static void alBufferData(int buffer, int format, ByteBuffer data, int freq)
/* 1097:     */  {
/* 1098:1098 */    BufferChecks.checkDirect(data);
/* 1099:1099 */    nalBufferData(buffer, format, MemoryUtil.getAddress(data), data.remaining(), freq);
/* 1100:     */  }
/* 1101:     */  
/* 1127:     */  public static void alBufferData(int buffer, int format, IntBuffer data, int freq)
/* 1128:     */  {
/* 1129:1129 */    BufferChecks.checkDirect(data);
/* 1130:1130 */    nalBufferData(buffer, format, MemoryUtil.getAddress(data), data.remaining() << 2, freq);
/* 1131:     */  }
/* 1132:     */  
/* 1158:     */  public static void alBufferData(int buffer, int format, ShortBuffer data, int freq)
/* 1159:     */  {
/* 1160:1160 */    BufferChecks.checkDirect(data);
/* 1161:1161 */    nalBufferData(buffer, format, MemoryUtil.getAddress(data), data.remaining() << 1, freq);
/* 1162:     */  }
/* 1163:     */  
/* 1167:     */  static native void nalBufferData(int paramInt1, int paramInt2, long paramLong, int paramInt3, int paramInt4);
/* 1168:     */  
/* 1172:     */  public static int alGetBufferi(int buffer, int pname)
/* 1173:     */  {
/* 1174:1174 */    int __result = nalGetBufferi(buffer, pname);
/* 1175:1175 */    return __result;
/* 1176:     */  }
/* 1177:     */  
/* 1182:     */  static native int nalGetBufferi(int paramInt1, int paramInt2);
/* 1183:     */  
/* 1187:     */  public static float alGetBufferf(int buffer, int pname)
/* 1188:     */  {
/* 1189:1189 */    float __result = nalGetBufferf(buffer, pname);
/* 1190:1190 */    return __result;
/* 1191:     */  }
/* 1192:     */  
/* 1200:     */  static native float nalGetBufferf(int paramInt1, int paramInt2);
/* 1201:     */  
/* 1208:     */  public static void alSourceQueueBuffers(int source, IntBuffer buffers)
/* 1209:     */  {
/* 1210:1210 */    BufferChecks.checkDirect(buffers);
/* 1211:1211 */    nalSourceQueueBuffers(source, buffers.remaining(), MemoryUtil.getAddress(buffers));
/* 1212:     */  }
/* 1213:     */  
/* 1214:     */  static native void nalSourceQueueBuffers(int paramInt1, int paramInt2, long paramLong);
/* 1215:     */  
/* 1216:     */  public static void alSourceQueueBuffers(int source, int buffer) {
/* 1217:1217 */    nalSourceQueueBuffers2(source, 1, buffer);
/* 1218:     */  }
/* 1219:     */  
/* 1229:     */  static native void nalSourceQueueBuffers2(int paramInt1, int paramInt2, int paramInt3);
/* 1230:     */  
/* 1239:     */  public static void alSourceUnqueueBuffers(int source, IntBuffer buffers)
/* 1240:     */  {
/* 1241:1241 */    BufferChecks.checkDirect(buffers);
/* 1242:1242 */    nalSourceUnqueueBuffers(source, buffers.remaining(), MemoryUtil.getAddress(buffers));
/* 1243:     */  }
/* 1244:     */  
/* 1245:     */  static native void nalSourceUnqueueBuffers(int paramInt1, int paramInt2, long paramLong);
/* 1246:     */  
/* 1247:     */  public static int alSourceUnqueueBuffers(int source) {
/* 1248:1248 */    int __result = nalSourceUnqueueBuffers2(source, 1);
/* 1249:1249 */    return __result;
/* 1250:     */  }
/* 1251:     */  
/* 1276:     */  static native int nalSourceUnqueueBuffers2(int paramInt1, int paramInt2);
/* 1277:     */  
/* 1301:     */  public static void alDistanceModel(int value)
/* 1302:     */  {
/* 1303:1303 */    nalDistanceModel(value);
/* 1304:     */  }
/* 1305:     */  
/* 1331:     */  static native void nalDistanceModel(int paramInt);
/* 1332:     */  
/* 1358:     */  public static void alDopplerFactor(float value)
/* 1359:     */  {
/* 1360:1360 */    nalDopplerFactor(value);
/* 1361:     */  }
/* 1362:     */  
/* 1388:     */  static native void nalDopplerFactor(float paramFloat);
/* 1389:     */  
/* 1415:     */  public static void alDopplerVelocity(float value)
/* 1416:     */  {
/* 1417:1417 */    nalDopplerVelocity(value);
/* 1418:     */  }
/* 1419:     */  
/* 1420:     */  static native void nalDopplerVelocity(float paramFloat);
/* 1421:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.AL10
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */