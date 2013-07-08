/*    1:     */package org.lwjgl.util.mapped;
/*    2:     */
/*    3:     */import java.io.PrintStream;
/*    4:     */import java.io.PrintWriter;
/*    5:     */import java.io.StringWriter;
/*    6:     */import java.lang.reflect.Field;
/*    7:     */import java.lang.reflect.Modifier;
/*    8:     */import java.nio.Buffer;
/*    9:     */import java.nio.ByteBuffer;
/*   10:     */import java.util.HashMap;
/*   11:     */import java.util.List;
/*   12:     */import java.util.Map;
/*   13:     */import org.lwjgl.BufferUtils;
/*   14:     */import org.lwjgl.LWJGLUtil;
/*   15:     */import org.lwjgl.MemoryUtil;
/*   16:     */import org.objectweb.asm.ClassAdapter;
/*   17:     */import org.objectweb.asm.ClassReader;
/*   18:     */import org.objectweb.asm.ClassVisitor;
/*   19:     */import org.objectweb.asm.ClassWriter;
/*   20:     */import org.objectweb.asm.FieldVisitor;
/*   21:     */import org.objectweb.asm.MethodVisitor;
/*   22:     */import org.objectweb.asm.Opcodes;
/*   23:     */import org.objectweb.asm.Type;
/*   24:     */import org.objectweb.asm.tree.AbstractInsnNode;
/*   25:     */import org.objectweb.asm.tree.AnnotationNode;
/*   26:     */import org.objectweb.asm.tree.FieldInsnNode;
/*   27:     */import org.objectweb.asm.tree.FieldNode;
/*   28:     */import org.objectweb.asm.tree.InsnList;
/*   29:     */import org.objectweb.asm.tree.InsnNode;
/*   30:     */import org.objectweb.asm.tree.IntInsnNode;
/*   31:     */import org.objectweb.asm.tree.LdcInsnNode;
/*   32:     */import org.objectweb.asm.tree.MethodInsnNode;
/*   33:     */import org.objectweb.asm.tree.MethodNode;
/*   34:     */import org.objectweb.asm.tree.TypeInsnNode;
/*   35:     */import org.objectweb.asm.tree.VarInsnNode;
/*   36:     */import org.objectweb.asm.tree.analysis.Analyzer;
/*   37:     */import org.objectweb.asm.tree.analysis.AnalyzerException;
/*   38:     */import org.objectweb.asm.tree.analysis.BasicValue;
/*   39:     */import org.objectweb.asm.tree.analysis.Frame;
/*   40:     */import org.objectweb.asm.tree.analysis.SimpleVerifier;
/*   41:     */import org.objectweb.asm.util.TraceClassVisitor;
/*   42:     */import sun.misc.Unsafe;
/*   43:     */
/*   44:     */public class MappedObjectTransformer
/*   45:     */{
/*   46:     */  static final boolean PRINT_ACTIVITY;
/*   47:     */  static final boolean PRINT_TIMING;
/*   48:     */  static final boolean PRINT_BYTECODE;
/*   49:     */  static final Map<String, MappedSubtypeInfo> className_to_subtype;
/*   50:     */  static final String MAPPED_OBJECT_JVM;
/*   51:     */  static final String MAPPED_HELPER_JVM;
/*   52:     */  static final String MAPPEDSET_PREFIX;
/*   53:     */  static final String MAPPED_SET2_JVM;
/*   54:     */  static final String MAPPED_SET3_JVM;
/*   55:     */  static final String MAPPED_SET4_JVM;
/*   56:     */  static final String CACHE_LINE_PAD_JVM;
/*   57:     */  static final String VIEWADDRESS_METHOD_NAME = "getViewAddress";
/*   58:     */  static final String NEXT_METHOD_NAME = "next";
/*   59:     */  static final String ALIGN_METHOD_NAME = "getAlign";
/*   60:     */  static final String SIZEOF_METHOD_NAME = "getSizeof";
/*   61:     */  static final String CAPACITY_METHOD_NAME = "capacity";
/*   62:     */  static final String VIEW_CONSTRUCTOR_NAME = "constructView$LWJGL";
/*   63:     */  static final Map<Integer, String> OPCODE_TO_NAME;
/*   64:     */  static final Map<Integer, String> INSNTYPE_TO_NAME;
/*   65:     */  static boolean is_currently_computing_frames;
/*   66:     */  
/*   67:     */  static
/*   68:     */  {
/*   69:  69 */    PRINT_ACTIVITY = (LWJGLUtil.DEBUG) && (LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.PrintActivity"));
/*   70:  70 */    PRINT_TIMING = (PRINT_ACTIVITY) && (LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.PrintTiming"));
/*   71:  71 */    PRINT_BYTECODE = (LWJGLUtil.DEBUG) && (LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.PrintBytecode"));
/*   72:     */    
/*   75:  75 */    MAPPED_OBJECT_JVM = jvmClassName(MappedObject.class);
/*   76:  76 */    MAPPED_HELPER_JVM = jvmClassName(MappedHelper.class);
/*   77:     */    
/*   78:  78 */    MAPPEDSET_PREFIX = jvmClassName(MappedSet.class);
/*   79:  79 */    MAPPED_SET2_JVM = jvmClassName(MappedSet2.class);
/*   80:  80 */    MAPPED_SET3_JVM = jvmClassName(MappedSet3.class);
/*   81:  81 */    MAPPED_SET4_JVM = jvmClassName(MappedSet4.class);
/*   82:     */    
/*   83:  83 */    CACHE_LINE_PAD_JVM = "L" + jvmClassName(CacheLinePad.class) + ";";
/*   84:     */    
/*   95:  95 */    OPCODE_TO_NAME = new HashMap();
/*   96:  96 */    INSNTYPE_TO_NAME = new HashMap();
/*   97:     */    
/*  101: 101 */    getClassEnums(Opcodes.class, OPCODE_TO_NAME, new String[] { "V1_", "ACC_", "T_", "F_", "MH_" });
/*  102: 102 */    getClassEnums(AbstractInsnNode.class, INSNTYPE_TO_NAME, new String[0]);
/*  103:     */    
/*  104: 104 */    className_to_subtype = new HashMap();
/*  105:     */    
/*  121: 121 */    className_to_subtype.put(MAPPED_OBJECT_JVM, new MappedSubtypeInfo(MAPPED_OBJECT_JVM, null, -1, -1, -1, false));
/*  122:     */    
/*  124: 124 */    String vmName = System.getProperty("java.vm.name");
/*  125: 125 */    if ((vmName != null) && (!vmName.contains("Server"))) {
/*  126: 126 */      System.err.println("Warning: " + MappedObject.class.getSimpleName() + "s have inferiour performance on Client VMs, please consider switching to a Server VM.");
/*  127:     */    }
/*  128:     */  }
/*  129:     */  
/*  135:     */  public static void register(Class<? extends MappedObject> type)
/*  136:     */  {
/*  137: 137 */    if (MappedObjectClassLoader.FORKED) {
/*  138: 138 */      return;
/*  139:     */    }
/*  140: 140 */    MappedType mapped = (MappedType)type.getAnnotation(MappedType.class);
/*  141:     */    
/*  142: 142 */    if ((mapped != null) && (mapped.padding() < 0)) {
/*  143: 143 */      throw new ClassFormatError("Invalid mapped type padding: " + mapped.padding());
/*  144:     */    }
/*  145: 145 */    if ((type.getEnclosingClass() != null) && (!Modifier.isStatic(type.getModifiers()))) {
/*  146: 146 */      throw new InternalError("only top-level or static inner classes are allowed");
/*  147:     */    }
/*  148: 148 */    String className = jvmClassName(type);
/*  149: 149 */    Map<String, FieldInfo> fields = new HashMap();
/*  150:     */    
/*  151: 151 */    long sizeof = 0L;
/*  152: 152 */    for (Field field : type.getDeclaredFields()) {
/*  153: 153 */      FieldInfo fieldInfo = registerField((mapped == null) || (mapped.autoGenerateOffsets()), className, sizeof, field);
/*  154: 154 */      if (fieldInfo != null)
/*  155:     */      {
/*  157: 157 */        fields.put(field.getName(), fieldInfo);
/*  158:     */        
/*  159: 159 */        sizeof = Math.max(sizeof, fieldInfo.offset + fieldInfo.lengthPadded);
/*  160:     */      }
/*  161:     */    }
/*  162: 162 */    int align = 4;
/*  163: 163 */    int padding = 0;
/*  164: 164 */    boolean cacheLinePadded = false;
/*  165:     */    
/*  166: 166 */    if (mapped != null) {
/*  167: 167 */      align = mapped.align();
/*  168: 168 */      if (mapped.cacheLinePadding()) {
/*  169: 169 */        if (mapped.padding() != 0) {
/*  170: 170 */          throw new ClassFormatError("Mapped type padding cannot be specified together with cacheLinePadding.");
/*  171:     */        }
/*  172: 172 */        int cacheLineMod = (int)(sizeof % CacheUtil.getCacheLineSize());
/*  173: 173 */        if (cacheLineMod != 0) {
/*  174: 174 */          padding = CacheUtil.getCacheLineSize() - cacheLineMod;
/*  175:     */        }
/*  176: 176 */        cacheLinePadded = true;
/*  177:     */      } else {
/*  178: 178 */        padding = mapped.padding();
/*  179:     */      }
/*  180:     */    }
/*  181: 181 */    sizeof += padding;
/*  182:     */    
/*  183: 183 */    MappedSubtypeInfo mappedType = new MappedSubtypeInfo(className, fields, (int)sizeof, align, padding, cacheLinePadded);
/*  184: 184 */    if (className_to_subtype.put(className, mappedType) != null)
/*  185: 185 */      throw new InternalError("duplicate mapped type: " + mappedType.className);
/*  186:     */  }
/*  187:     */  
/*  188:     */  private static FieldInfo registerField(boolean autoGenerateOffsets, String className, long advancingOffset, Field field) {
/*  189: 189 */    if (Modifier.isStatic(field.getModifiers())) {
/*  190: 190 */      return null;
/*  191:     */    }
/*  192:     */    
/*  193: 193 */    if ((!field.getType().isPrimitive()) && (field.getType() != ByteBuffer.class)) {
/*  194: 194 */      throw new ClassFormatError("field '" + className + "." + field.getName() + "' not supported: " + field.getType());
/*  195:     */    }
/*  196: 196 */    MappedField meta = (MappedField)field.getAnnotation(MappedField.class);
/*  197: 197 */    if ((meta == null) && (!autoGenerateOffsets)) {
/*  198: 198 */      throw new ClassFormatError("field '" + className + "." + field.getName() + "' missing annotation " + MappedField.class.getName() + ": " + className);
/*  199:     */    }
/*  200: 200 */    Pointer pointer = (Pointer)field.getAnnotation(Pointer.class);
/*  201: 201 */    if ((pointer != null) && (field.getType() != Long.TYPE)) {
/*  202: 202 */      throw new ClassFormatError("The @Pointer annotation can only be used on long fields. @Pointer field found: " + className + "." + field.getName() + ": " + field.getType());
/*  203:     */    }
/*  204: 204 */    if ((Modifier.isVolatile(field.getModifiers())) && ((pointer != null) || (field.getType() == ByteBuffer.class))) {
/*  205: 205 */      throw new ClassFormatError("The volatile keyword is not supported for @Pointer or ByteBuffer fields. Volatile field found: " + className + "." + field.getName() + ": " + field.getType());
/*  206:     */    }
/*  207:     */    
/*  208:     */    long byteLength;
/*  209: 209 */    if ((field.getType() == Long.TYPE) || (field.getType() == Double.TYPE)) { long byteLength;
/*  210: 210 */      if (pointer == null) {
/*  211: 211 */        byteLength = 8L;
/*  212:     */      } else
/*  213: 213 */        byteLength = MappedObjectUnsafe.INSTANCE.addressSize(); } else { long byteLength;
/*  214: 214 */      if (field.getType() == Double.TYPE) {
/*  215: 215 */        byteLength = 8L; } else { long byteLength;
/*  216: 216 */        if ((field.getType() == Integer.TYPE) || (field.getType() == Float.TYPE)) {
/*  217: 217 */          byteLength = 4L; } else { long byteLength;
/*  218: 218 */          if ((field.getType() == Character.TYPE) || (field.getType() == Short.TYPE)) {
/*  219: 219 */            byteLength = 2L; } else { long byteLength;
/*  220: 220 */            if (field.getType() == Byte.TYPE) {
/*  221: 221 */              byteLength = 1L;
/*  222: 222 */            } else if (field.getType() == ByteBuffer.class) {
/*  223: 223 */              long byteLength = meta.byteLength();
/*  224: 224 */              if (byteLength < 0L)
/*  225: 225 */                throw new IllegalStateException("invalid byte length for mapped ByteBuffer field: " + className + "." + field.getName() + " [length=" + byteLength + "]");
/*  226:     */            } else {
/*  227: 227 */              throw new ClassFormatError(field.getType().getName()); } } } } }
/*  228:     */    long byteLength;
/*  229: 229 */    if ((field.getType() != ByteBuffer.class) && (advancingOffset % byteLength != 0L)) {
/*  230: 230 */      throw new IllegalStateException("misaligned mapped type: " + className + "." + field.getName());
/*  231:     */    }
/*  232: 232 */    CacheLinePad pad = (CacheLinePad)field.getAnnotation(CacheLinePad.class);
/*  233:     */    
/*  234: 234 */    long byteOffset = advancingOffset;
/*  235: 235 */    if ((meta != null) && (meta.byteOffset() != -1L)) {
/*  236: 236 */      if (meta.byteOffset() < 0L)
/*  237: 237 */        throw new ClassFormatError("Invalid field byte offset: " + className + "." + field.getName() + " [byteOffset=" + meta.byteOffset() + "]");
/*  238: 238 */      if (pad != null) {
/*  239: 239 */        throw new ClassFormatError("A field byte offset cannot be specified together with cache-line padding: " + className + "." + field.getName());
/*  240:     */      }
/*  241: 241 */      byteOffset = meta.byteOffset();
/*  242:     */    }
/*  243:     */    
/*  244: 244 */    long byteLengthPadded = byteLength;
/*  245: 245 */    if (pad != null)
/*  246:     */    {
/*  247: 247 */      if ((pad.before()) && (byteOffset % CacheUtil.getCacheLineSize() != 0L)) {
/*  248: 248 */        byteOffset += CacheUtil.getCacheLineSize() - (byteOffset & CacheUtil.getCacheLineSize() - 1);
/*  249:     */      }
/*  250:     */      
/*  251: 251 */      if ((pad.after()) && ((byteOffset + byteLength) % CacheUtil.getCacheLineSize() != 0L)) {
/*  252: 252 */        byteLengthPadded += CacheUtil.getCacheLineSize() - (byteOffset + byteLength) % CacheUtil.getCacheLineSize();
/*  253:     */      }
/*  254: 254 */      assert ((!pad.before()) || (byteOffset % CacheUtil.getCacheLineSize() == 0L));
/*  255: 255 */      assert ((!pad.after()) || ((byteOffset + byteLengthPadded) % CacheUtil.getCacheLineSize() == 0L));
/*  256:     */    }
/*  257:     */    
/*  258: 258 */    if (PRINT_ACTIVITY) {
/*  259: 259 */      LWJGLUtil.log(MappedObjectTransformer.class.getSimpleName() + ": " + className + "." + field.getName() + " [type=" + field.getType().getSimpleName() + ", offset=" + byteOffset + "]");
/*  260:     */    }
/*  261: 261 */    return new FieldInfo(byteOffset, byteLength, byteLengthPadded, Type.getType(field.getType()), Modifier.isVolatile(field.getModifiers()), pointer != null);
/*  262:     */  }
/*  263:     */  
/*  264:     */  static byte[] transformMappedObject(byte[] bytecode)
/*  265:     */  {
/*  266: 266 */    ClassWriter cw = new ClassWriter(0);
/*  267:     */    
/*  268: 268 */    ClassVisitor cv = new ClassAdapter(cw)
/*  269:     */    {
/*  270: 270 */      private final String[] DEFINALIZE_LIST = { "getViewAddress", "next", "getAlign", "getSizeof", "capacity" };
/*  271:     */      
/*  277:     */      public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
/*  278:     */      {
/*  279: 279 */        for (String method : this.DEFINALIZE_LIST) {
/*  280: 280 */          if (name.equals(method)) {
/*  281: 281 */            access &= -17;
/*  282: 282 */            break;
/*  283:     */          }
/*  284:     */        }
/*  285: 285 */        return super.visitMethod(access, name, desc, signature, exceptions);
/*  286:     */      }
/*  287:     */      
/*  288: 288 */    };
/*  289: 289 */    new ClassReader(bytecode).accept(cv, 0);
/*  290: 290 */    return cw.toByteArray();
/*  291:     */  }
/*  292:     */  
/*  293:     */  static byte[] transformMappedAPI(String className, byte[] bytecode) {
/*  294: 294 */    ClassWriter cw = new ClassWriter(2)
/*  295:     */    {
/*  297:     */      protected String getCommonSuperClass(String a, String b)
/*  298:     */      {
/*  299: 299 */        if (((MappedObjectTransformer.is_currently_computing_frames) && (!a.startsWith("java/"))) || (!b.startsWith("java/"))) {
/*  300: 300 */          return "java/lang/Object";
/*  301:     */        }
/*  302: 302 */        return super.getCommonSuperClass(a, b);
/*  303:     */      }
/*  304:     */      
/*  306: 306 */    };
/*  307: 307 */    TransformationAdapter ta = new TransformationAdapter(cw, className);
/*  308:     */    
/*  309: 309 */    ClassVisitor cv = ta;
/*  310: 310 */    if (className_to_subtype.containsKey(className)) {
/*  311: 311 */      cv = getMethodGenAdapter(className, cv);
/*  312:     */    }
/*  313: 313 */    new ClassReader(bytecode).accept(cv, 4);
/*  314:     */    
/*  315: 315 */    if (!ta.transformed) {
/*  316: 316 */      return bytecode;
/*  317:     */    }
/*  318: 318 */    bytecode = cw.toByteArray();
/*  319: 319 */    if (PRINT_BYTECODE) {
/*  320: 320 */      printBytecode(bytecode);
/*  321:     */    }
/*  322: 322 */    return bytecode;
/*  323:     */  }
/*  324:     */  
/*  325:     */  private static ClassAdapter getMethodGenAdapter(final String className, ClassVisitor cv) {
/*  326: 326 */    new ClassAdapter(cv)
/*  327:     */    {
/*  328:     */      public void visitEnd()
/*  329:     */      {
/*  330: 330 */        MappedObjectTransformer.MappedSubtypeInfo mappedSubtype = (MappedObjectTransformer.MappedSubtypeInfo)MappedObjectTransformer.className_to_subtype.get(className);
/*  331:     */        
/*  332: 332 */        generateViewAddressGetter();
/*  333: 333 */        generateCapacity();
/*  334: 334 */        generateAlignGetter(mappedSubtype);
/*  335: 335 */        generateSizeofGetter();
/*  336: 336 */        generateNext();
/*  337:     */        
/*  338: 338 */        for (String fieldName : mappedSubtype.fields.keySet()) {
/*  339: 339 */          MappedObjectTransformer.FieldInfo field = (MappedObjectTransformer.FieldInfo)mappedSubtype.fields.get(fieldName);
/*  340:     */          
/*  341: 341 */          if (field.type.getDescriptor().length() > 1) {
/*  342: 342 */            generateByteBufferGetter(fieldName, field);
/*  343:     */          } else {
/*  344: 344 */            generateFieldGetter(fieldName, field);
/*  345: 345 */            generateFieldSetter(fieldName, field);
/*  346:     */          }
/*  347:     */        }
/*  348:     */        
/*  349: 349 */        super.visitEnd();
/*  350:     */      }
/*  351:     */      
/*  352:     */      private void generateViewAddressGetter() {
/*  353: 353 */        MethodVisitor mv = super.visitMethod(1, "getViewAddress", "(I)J", null, null);
/*  354: 354 */        mv.visitCode();
/*  355: 355 */        mv.visitVarInsn(25, 0);
/*  356: 356 */        mv.visitFieldInsn(180, MappedObjectTransformer.MAPPED_OBJECT_JVM, "baseAddress", "J");
/*  357: 357 */        mv.visitVarInsn(21, 1);
/*  358: 358 */        mv.visitFieldInsn(178, className, "SIZEOF", "I");
/*  359: 359 */        mv.visitInsn(104);
/*  360: 360 */        mv.visitInsn(133);
/*  361: 361 */        mv.visitInsn(97);
/*  362: 362 */        if (MappedObject.CHECKS) {
/*  363: 363 */          mv.visitInsn(92);
/*  364: 364 */          mv.visitVarInsn(25, 0);
/*  365: 365 */          mv.visitMethodInsn(184, MappedObjectTransformer.MAPPED_HELPER_JVM, "checkAddress", "(JL" + MappedObjectTransformer.MAPPED_OBJECT_JVM + ";)V");
/*  366:     */        }
/*  367: 367 */        mv.visitInsn(173);
/*  368: 368 */        mv.visitMaxs(3, 2);
/*  369: 369 */        mv.visitEnd();
/*  370:     */      }
/*  371:     */      
/*  372:     */      private void generateCapacity()
/*  373:     */      {
/*  374: 374 */        MethodVisitor mv = super.visitMethod(1, "capacity", "()I", null, null);
/*  375: 375 */        mv.visitCode();
/*  376: 376 */        mv.visitVarInsn(25, 0);
/*  377: 377 */        mv.visitMethodInsn(182, MappedObjectTransformer.MAPPED_OBJECT_JVM, "backingByteBuffer", "()L" + MappedObjectTransformer.jvmClassName(ByteBuffer.class) + ";");
/*  378: 378 */        mv.visitInsn(89);
/*  379: 379 */        mv.visitMethodInsn(182, MappedObjectTransformer.jvmClassName(ByteBuffer.class), "capacity", "()I");
/*  380: 380 */        mv.visitInsn(95);
/*  381: 381 */        mv.visitMethodInsn(184, MappedObjectTransformer.jvmClassName(MemoryUtil.class), "getAddress0", "(L" + MappedObjectTransformer.jvmClassName(Buffer.class) + ";)J");
/*  382: 382 */        mv.visitVarInsn(25, 0);
/*  383: 383 */        mv.visitFieldInsn(180, MappedObjectTransformer.MAPPED_OBJECT_JVM, "baseAddress", "J");
/*  384: 384 */        mv.visitInsn(101);
/*  385: 385 */        mv.visitInsn(136);
/*  386: 386 */        mv.visitInsn(96);
/*  387: 387 */        mv.visitFieldInsn(178, className, "SIZEOF", "I");
/*  388: 388 */        mv.visitInsn(108);
/*  389: 389 */        mv.visitInsn(172);
/*  390: 390 */        mv.visitMaxs(3, 1);
/*  391: 391 */        mv.visitEnd();
/*  392:     */      }
/*  393:     */      
/*  394:     */      private void generateAlignGetter(MappedObjectTransformer.MappedSubtypeInfo mappedSubtype) {
/*  395: 395 */        MethodVisitor mv = super.visitMethod(1, "getAlign", "()I", null, null);
/*  396: 396 */        mv.visitCode();
/*  397: 397 */        MappedObjectTransformer.visitIntNode(mv, mappedSubtype.sizeof);
/*  398: 398 */        mv.visitInsn(172);
/*  399: 399 */        mv.visitMaxs(1, 1);
/*  400: 400 */        mv.visitEnd();
/*  401:     */      }
/*  402:     */      
/*  403:     */      private void generateSizeofGetter() {
/*  404: 404 */        MethodVisitor mv = super.visitMethod(1, "getSizeof", "()I", null, null);
/*  405: 405 */        mv.visitCode();
/*  406: 406 */        mv.visitFieldInsn(178, className, "SIZEOF", "I");
/*  407: 407 */        mv.visitInsn(172);
/*  408: 408 */        mv.visitMaxs(1, 1);
/*  409: 409 */        mv.visitEnd();
/*  410:     */      }
/*  411:     */      
/*  412:     */      private void generateNext() {
/*  413: 413 */        MethodVisitor mv = super.visitMethod(1, "next", "()V", null, null);
/*  414: 414 */        mv.visitCode();
/*  415: 415 */        mv.visitVarInsn(25, 0);
/*  416: 416 */        mv.visitInsn(89);
/*  417: 417 */        mv.visitFieldInsn(180, MappedObjectTransformer.MAPPED_OBJECT_JVM, "viewAddress", "J");
/*  418: 418 */        mv.visitFieldInsn(178, className, "SIZEOF", "I");
/*  419: 419 */        mv.visitInsn(133);
/*  420: 420 */        mv.visitInsn(97);
/*  421: 421 */        mv.visitMethodInsn(182, className, "setViewAddress", "(J)V");
/*  422: 422 */        mv.visitInsn(177);
/*  423: 423 */        mv.visitMaxs(3, 1);
/*  424: 424 */        mv.visitEnd();
/*  425:     */      }
/*  426:     */      
/*  427:     */      private void generateByteBufferGetter(String fieldName, MappedObjectTransformer.FieldInfo field) {
/*  428: 428 */        MethodVisitor mv = super.visitMethod(9, MappedObjectTransformer.getterName(fieldName), "(L" + className + ";I)" + field.type.getDescriptor(), null, null);
/*  429: 429 */        mv.visitCode();
/*  430: 430 */        mv.visitVarInsn(25, 0);
/*  431: 431 */        mv.visitVarInsn(21, 1);
/*  432: 432 */        mv.visitMethodInsn(182, className, "getViewAddress", "(I)J");
/*  433: 433 */        MappedObjectTransformer.visitIntNode(mv, (int)field.offset);
/*  434: 434 */        mv.visitInsn(133);
/*  435: 435 */        mv.visitInsn(97);
/*  436: 436 */        MappedObjectTransformer.visitIntNode(mv, (int)field.length);
/*  437: 437 */        mv.visitMethodInsn(184, MappedObjectTransformer.MAPPED_HELPER_JVM, "newBuffer", "(JI)L" + MappedObjectTransformer.jvmClassName(ByteBuffer.class) + ";");
/*  438: 438 */        mv.visitInsn(176);
/*  439: 439 */        mv.visitMaxs(3, 2);
/*  440: 440 */        mv.visitEnd();
/*  441:     */      }
/*  442:     */      
/*  443:     */      private void generateFieldGetter(String fieldName, MappedObjectTransformer.FieldInfo field) {
/*  444: 444 */        MethodVisitor mv = super.visitMethod(9, MappedObjectTransformer.getterName(fieldName), "(L" + className + ";I)" + field.type.getDescriptor(), null, null);
/*  445: 445 */        mv.visitCode();
/*  446: 446 */        mv.visitVarInsn(25, 0);
/*  447: 447 */        mv.visitVarInsn(21, 1);
/*  448: 448 */        mv.visitMethodInsn(182, className, "getViewAddress", "(I)J");
/*  449: 449 */        MappedObjectTransformer.visitIntNode(mv, (int)field.offset);
/*  450: 450 */        mv.visitInsn(133);
/*  451: 451 */        mv.visitInsn(97);
/*  452: 452 */        mv.visitMethodInsn(184, MappedObjectTransformer.MAPPED_HELPER_JVM, field.getAccessType() + "get", "(J)" + field.type.getDescriptor());
/*  453: 453 */        mv.visitInsn(field.type.getOpcode(172));
/*  454: 454 */        mv.visitMaxs(3, 2);
/*  455: 455 */        mv.visitEnd();
/*  456:     */      }
/*  457:     */      
/*  458:     */      private void generateFieldSetter(String fieldName, MappedObjectTransformer.FieldInfo field) {
/*  459: 459 */        MethodVisitor mv = super.visitMethod(9, MappedObjectTransformer.setterName(fieldName), "(L" + className + ";I" + field.type.getDescriptor() + ")V", null, null);
/*  460: 460 */        mv.visitCode();
/*  461: 461 */        int load = 0;
/*  462: 462 */        switch (field.type.getSort()) {
/*  463:     */        case 1: 
/*  464:     */        case 2: 
/*  465:     */        case 3: 
/*  466:     */        case 4: 
/*  467:     */        case 5: 
/*  468: 468 */          load = 21;
/*  469: 469 */          break;
/*  470:     */        case 6: 
/*  471: 471 */          load = 23;
/*  472: 472 */          break;
/*  473:     */        case 7: 
/*  474: 474 */          load = 22;
/*  475: 475 */          break;
/*  476:     */        case 8: 
/*  477: 477 */          load = 24;
/*  478:     */        }
/*  479:     */        
/*  480: 480 */        mv.visitVarInsn(load, 2);
/*  481: 481 */        mv.visitVarInsn(25, 0);
/*  482: 482 */        mv.visitVarInsn(21, 1);
/*  483: 483 */        mv.visitMethodInsn(182, className, "getViewAddress", "(I)J");
/*  484: 484 */        MappedObjectTransformer.visitIntNode(mv, (int)field.offset);
/*  485: 485 */        mv.visitInsn(133);
/*  486: 486 */        mv.visitInsn(97);
/*  487: 487 */        mv.visitMethodInsn(184, MappedObjectTransformer.MAPPED_HELPER_JVM, field.getAccessType() + "put", "(" + field.type.getDescriptor() + "J)V");
/*  488: 488 */        mv.visitInsn(177);
/*  489: 489 */        mv.visitMaxs(4, 4);
/*  490: 490 */        mv.visitEnd();
/*  491:     */      }
/*  492:     */    };
/*  493:     */  }
/*  494:     */  
/*  495:     */  private static class TransformationAdapter
/*  496:     */    extends ClassAdapter
/*  497:     */  {
/*  498:     */    final String className;
/*  499:     */    boolean transformed;
/*  500:     */    
/*  501:     */    TransformationAdapter(ClassVisitor cv, String className)
/*  502:     */    {
/*  503: 503 */      super();
/*  504: 504 */      this.className = className;
/*  505:     */    }
/*  506:     */    
/*  508:     */    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
/*  509:     */    {
/*  510: 510 */      MappedObjectTransformer.MappedSubtypeInfo mappedSubtype = (MappedObjectTransformer.MappedSubtypeInfo)MappedObjectTransformer.className_to_subtype.get(this.className);
/*  511: 511 */      if ((mappedSubtype != null) && (mappedSubtype.fields.containsKey(name))) {
/*  512: 512 */        if (MappedObjectTransformer.PRINT_ACTIVITY)
/*  513: 513 */          LWJGLUtil.log(MappedObjectTransformer.class.getSimpleName() + ": discarding field: " + this.className + "." + name + ":" + desc);
/*  514: 514 */        return null;
/*  515:     */      }
/*  516:     */      
/*  517: 517 */      if ((access & 0x8) == 0) {
/*  518: 518 */        new FieldNode(access, name, desc, signature, value) {
/*  519:     */          public void visitEnd() {
/*  520: 520 */            if (this.visibleAnnotations == null) {
/*  521: 521 */              accept(MappedObjectTransformer.TransformationAdapter.this.cv);
/*  522: 522 */              return;
/*  523:     */            }
/*  524:     */            
/*  525: 525 */            boolean before = false;
/*  526: 526 */            boolean after = false;
/*  527: 527 */            int byteLength = 0;
/*  528: 528 */            for (AnnotationNode pad : this.visibleAnnotations) {
/*  529: 529 */              if (MappedObjectTransformer.CACHE_LINE_PAD_JVM.equals(pad.desc)) {
/*  530: 530 */                if (("J".equals(this.desc)) || ("D".equals(this.desc))) {
/*  531: 531 */                  byteLength = 8;
/*  532: 532 */                } else if (("I".equals(this.desc)) || ("F".equals(this.desc))) {
/*  533: 533 */                  byteLength = 4;
/*  534: 534 */                } else if (("S".equals(this.desc)) || ("C".equals(this.desc))) {
/*  535: 535 */                  byteLength = 2;
/*  536: 536 */                } else if (("B".equals(this.desc)) || ("Z".equals(this.desc))) {
/*  537: 537 */                  byteLength = 1;
/*  538:     */                } else {
/*  539: 539 */                  throw new ClassFormatError("The @CacheLinePad annotation cannot be used on non-primitive fields: " + MappedObjectTransformer.TransformationAdapter.this.className + "." + this.name);
/*  540:     */                }
/*  541: 541 */                MappedObjectTransformer.TransformationAdapter.this.transformed = true;
/*  542:     */                
/*  543: 543 */                after = true;
/*  544: 544 */                if (pad.values == null) break;
/*  545: 545 */                for (int i = 0; i < pad.values.size(); i += 2) {
/*  546: 546 */                  boolean value = pad.values.get(i + 1).equals(Boolean.TRUE);
/*  547: 547 */                  if ("before".equals(pad.values.get(i))) {
/*  548: 548 */                    before = value;
/*  549:     */                  } else {
/*  550: 550 */                    after = value;
/*  551:     */                  }
/*  552:     */                }
/*  553: 545 */                break;
/*  554:     */              }
/*  555:     */            }
/*  556:     */            
/*  573: 565 */            if (before) {
/*  574: 566 */              int count = CacheUtil.getCacheLineSize() / byteLength - 1;
/*  575: 567 */              for (int i = count; i >= 1; i--) {
/*  576: 568 */                MappedObjectTransformer.TransformationAdapter.this.cv.visitField(this.access | 0x1 | 0x1000, this.name + "$PAD_" + i, this.desc, this.signature, null);
/*  577:     */              }
/*  578:     */            }
/*  579: 571 */            accept(MappedObjectTransformer.TransformationAdapter.this.cv);
/*  580:     */            
/*  581: 573 */            if (after) {
/*  582: 574 */              int count = CacheUtil.getCacheLineSize() / byteLength - 1;
/*  583: 575 */              for (int i = 1; i <= count; i++)
/*  584: 576 */                MappedObjectTransformer.TransformationAdapter.this.cv.visitField(this.access | 0x1 | 0x1000, this.name + "$PAD" + i, this.desc, this.signature, null);
/*  585:     */            }
/*  586:     */          }
/*  587:     */        };
/*  588:     */      }
/*  589: 581 */      return super.visitField(access, name, desc, signature, value);
/*  590:     */    }
/*  591:     */    
/*  593:     */    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
/*  594:     */    {
/*  595: 587 */      if ("<init>".equals(name)) {
/*  596: 588 */        MappedObjectTransformer.MappedSubtypeInfo mappedSubtype = (MappedObjectTransformer.MappedSubtypeInfo)MappedObjectTransformer.className_to_subtype.get(this.className);
/*  597: 589 */        if (mappedSubtype != null) {
/*  598: 590 */          if (!"()V".equals(desc)) {
/*  599: 591 */            throw new ClassFormatError(this.className + " can only have a default constructor, found: " + desc);
/*  600:     */          }
/*  601: 593 */          MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
/*  602: 594 */          mv.visitVarInsn(25, 0);
/*  603: 595 */          mv.visitMethodInsn(183, MappedObjectTransformer.MAPPED_OBJECT_JVM, "<init>", "()V");
/*  604: 596 */          mv.visitInsn(177);
/*  605: 597 */          mv.visitMaxs(0, 0);
/*  606:     */          
/*  608: 600 */          name = "constructView$LWJGL";
/*  609:     */        }
/*  610:     */      }
/*  611:     */      
/*  612: 604 */      final MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
/*  613: 605 */      new MethodNode(access, name, desc, signature, exceptions)
/*  614:     */      {
/*  615:     */        boolean needsTransformation;
/*  616:     */        
/*  618:     */        public void visitMaxs(int a, int b)
/*  619:     */        {
/*  620:     */          try
/*  621:     */          {
/*  622: 614 */            MappedObjectTransformer.is_currently_computing_frames = true;
/*  623: 615 */            super.visitMaxs(a, b);
/*  624:     */          } finally {
/*  625: 617 */            MappedObjectTransformer.is_currently_computing_frames = false;
/*  626:     */          }
/*  627:     */        }
/*  628:     */        
/*  629:     */        public void visitFieldInsn(int opcode, String owner, String name, String desc)
/*  630:     */        {
/*  631: 623 */          if ((MappedObjectTransformer.className_to_subtype.containsKey(owner)) || (owner.startsWith(MappedObjectTransformer.MAPPEDSET_PREFIX))) {
/*  632: 624 */            this.needsTransformation = true;
/*  633:     */          }
/*  634: 626 */          super.visitFieldInsn(opcode, owner, name, desc);
/*  635:     */        }
/*  636:     */        
/*  637:     */        public void visitMethodInsn(int opcode, String owner, String name, String desc)
/*  638:     */        {
/*  639: 631 */          if (MappedObjectTransformer.className_to_subtype.containsKey(owner)) {
/*  640: 632 */            this.needsTransformation = true;
/*  641:     */          }
/*  642: 634 */          super.visitMethodInsn(opcode, owner, name, desc);
/*  643:     */        }
/*  644:     */        
/*  645:     */        public void visitEnd()
/*  646:     */        {
/*  647: 639 */          if (this.needsTransformation)
/*  648:     */          {
/*  649: 641 */            MappedObjectTransformer.TransformationAdapter.this.transformed = true;
/*  650:     */            try {
/*  651: 643 */              transformMethod(analyse());
/*  652:     */            } catch (Exception e) {
/*  653: 645 */              throw new RuntimeException(e);
/*  654:     */            }
/*  655:     */          }
/*  656:     */          
/*  658: 650 */          accept(mv);
/*  659:     */        }
/*  660:     */        
/*  661:     */        private Frame<BasicValue>[] analyse() throws AnalyzerException {
/*  662: 654 */          Analyzer<BasicValue> a = new Analyzer(new SimpleVerifier());
/*  663: 655 */          a.analyze(MappedObjectTransformer.TransformationAdapter.this.className, this);
/*  664: 656 */          return a.getFrames();
/*  665:     */        }
/*  666:     */        
/*  667:     */        private void transformMethod(Frame<BasicValue>[] frames) {
/*  668: 660 */          InsnList instructions = this.instructions;
/*  669:     */          
/*  670: 662 */          Map<Integer, MappedObjectTransformer.MappedSubtypeInfo> arrayVars = new HashMap();
/*  671:     */          
/*  677: 669 */          Map<AbstractInsnNode, Frame<BasicValue>> frameMap = new HashMap();
/*  678: 670 */          for (int i = 0; i < frames.length; i++) {
/*  679: 671 */            frameMap.put(instructions.get(i), frames[i]);
/*  680:     */          }
/*  681: 673 */          for (int i = 0; i < instructions.size(); i++) {
/*  682: 674 */            AbstractInsnNode instruction = instructions.get(i);
/*  683:     */            
/*  686: 678 */            switch (instruction.getType()) {
/*  687:     */            case 2: 
/*  688: 680 */              if (instruction.getOpcode() == 25) {
/*  689: 681 */                VarInsnNode varInsn = (VarInsnNode)instruction;
/*  690: 682 */                MappedObjectTransformer.MappedSubtypeInfo mappedSubtype = (MappedObjectTransformer.MappedSubtypeInfo)arrayVars.get(Integer.valueOf(varInsn.var));
/*  691: 683 */                if (mappedSubtype != null)
/*  692: 684 */                  i = MappedObjectTransformer.transformArrayAccess(instructions, i, frameMap, varInsn, mappedSubtype, varInsn.var); }
/*  693: 685 */              break;
/*  694:     */            
/*  695:     */            case 4: 
/*  696: 688 */              FieldInsnNode fieldInsn = (FieldInsnNode)instruction;
/*  697:     */              
/*  698: 690 */              InsnList list = MappedObjectTransformer.transformFieldAccess(fieldInsn);
/*  699: 691 */              if (list != null)
/*  700: 692 */                i = MappedObjectTransformer.replace(instructions, i, instruction, list); break;
/*  701:     */            
/*  703:     */            case 5: 
/*  704: 696 */              MethodInsnNode methodInsn = (MethodInsnNode)instruction;
/*  705: 697 */              MappedObjectTransformer.MappedSubtypeInfo mappedType = (MappedObjectTransformer.MappedSubtypeInfo)MappedObjectTransformer.className_to_subtype.get(methodInsn.owner);
/*  706: 698 */              if (mappedType != null)
/*  707: 699 */                i = MappedObjectTransformer.transformMethodCall(instructions, i, frameMap, methodInsn, mappedType, arrayVars);
/*  708:     */              break;
/*  709:     */            }
/*  710:     */          }
/*  711:     */        }
/*  712:     */      };
/*  713:     */    }
/*  714:     */  }
/*  715:     */  
/*  716:     */  static int transformMethodCall(InsnList instructions, int i, Map<AbstractInsnNode, Frame<BasicValue>> frameMap, MethodInsnNode methodInsn, MappedSubtypeInfo mappedType, Map<Integer, MappedSubtypeInfo> arrayVars) {
/*  717: 709 */    switch (methodInsn.getOpcode()) {
/*  718:     */    case 182: 
/*  719: 711 */      if (("asArray".equals(methodInsn.name)) && (methodInsn.desc.equals("()[L" + MAPPED_OBJECT_JVM + ";")))
/*  720:     */      {
/*  721:     */        AbstractInsnNode nextInstruction;
/*  722:     */        
/*  724: 716 */        checkInsnAfterIsArray(nextInstruction = methodInsn.getNext(), 192);
/*  725: 717 */        checkInsnAfterIsArray(nextInstruction = nextInstruction.getNext(), 58);
/*  726:     */        
/*  727: 719 */        Frame<BasicValue> frame = (Frame)frameMap.get(nextInstruction);
/*  728: 720 */        String targetType = ((BasicValue)frame.getStack(frame.getStackSize() - 1)).getType().getElementType().getInternalName();
/*  729: 721 */        if (!methodInsn.owner.equals(targetType))
/*  730:     */        {
/*  736: 728 */          throw new ClassCastException("Source: " + methodInsn.owner + " - Target: " + targetType);
/*  737:     */        }
/*  738:     */        
/*  739: 731 */        VarInsnNode varInstruction = (VarInsnNode)nextInstruction;
/*  740:     */        
/*  741: 733 */        arrayVars.put(Integer.valueOf(varInstruction.var), mappedType);
/*  742:     */        
/*  743: 735 */        instructions.remove(methodInsn.getNext());
/*  744: 736 */        instructions.remove(methodInsn);
/*  745:     */      }
/*  746:     */      
/*  747: 739 */      if (("dup".equals(methodInsn.name)) && (methodInsn.desc.equals("()L" + MAPPED_OBJECT_JVM + ";"))) {
/*  748: 740 */        i = replace(instructions, i, methodInsn, generateDupInstructions(methodInsn));
/*  751:     */      }
/*  752: 744 */      else if (("slice".equals(methodInsn.name)) && (methodInsn.desc.equals("()L" + MAPPED_OBJECT_JVM + ";"))) {
/*  753: 745 */        i = replace(instructions, i, methodInsn, generateSliceInstructions(methodInsn));
/*  756:     */      }
/*  757: 749 */      else if (("runViewConstructor".equals(methodInsn.name)) && ("()V".equals(methodInsn.desc))) {
/*  758: 750 */        i = replace(instructions, i, methodInsn, generateRunViewConstructorInstructions(methodInsn));
/*  761:     */      }
/*  762: 754 */      else if (("copyTo".equals(methodInsn.name)) && (methodInsn.desc.equals("(L" + MAPPED_OBJECT_JVM + ";)V"))) {
/*  763: 755 */        i = replace(instructions, i, methodInsn, generateCopyToInstructions(mappedType));
/*  766:     */      }
/*  767: 759 */      else if (("copyRange".equals(methodInsn.name)) && (methodInsn.desc.equals("(L" + MAPPED_OBJECT_JVM + ";I)V")))
/*  768: 760 */        i = replace(instructions, i, methodInsn, generateCopyRangeInstructions(mappedType));
/*  769: 761 */      break;
/*  770:     */    
/*  774:     */    case 183: 
/*  775: 767 */      if ((methodInsn.owner.equals(MAPPED_OBJECT_JVM)) && ("<init>".equals(methodInsn.name)) && ("()V".equals(methodInsn.desc))) {
/*  776: 768 */        instructions.remove(methodInsn.getPrevious());
/*  777: 769 */        instructions.remove(methodInsn);
/*  778:     */        
/*  779: 771 */        i -= 2; } break;
/*  780:     */    
/*  782:     */    case 184: 
/*  783: 775 */      boolean isMapDirectMethod = ("map".equals(methodInsn.name)) && (methodInsn.desc.equals("(JI)L" + MAPPED_OBJECT_JVM + ";"));
/*  784: 776 */      boolean isMapBufferMethod = ("map".equals(methodInsn.name)) && (methodInsn.desc.equals("(Ljava/nio/ByteBuffer;)L" + MAPPED_OBJECT_JVM + ";"));
/*  785: 777 */      boolean isMallocMethod = ("malloc".equals(methodInsn.name)) && (methodInsn.desc.equals("(I)L" + MAPPED_OBJECT_JVM + ";"));
/*  786:     */      
/*  787: 779 */      if ((isMapDirectMethod) || (isMapBufferMethod) || (isMallocMethod)) {
/*  788: 780 */        i = replace(instructions, i, methodInsn, generateMapInstructions(mappedType, methodInsn.owner, isMapDirectMethod, isMallocMethod));
/*  789:     */      }
/*  790:     */      break;
/*  791:     */    }
/*  792: 784 */    return i;
/*  793:     */  }
/*  794:     */  
/*  795:     */  private static InsnList generateCopyRangeInstructions(MappedSubtypeInfo mappedType) {
/*  796: 788 */    InsnList list = new InsnList();
/*  797:     */    
/*  799: 791 */    list.add(getIntNode(mappedType.sizeof));
/*  800:     */    
/*  801: 793 */    list.add(new InsnNode(104));
/*  802:     */    
/*  803: 795 */    list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "copy", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";I)V"));
/*  804:     */    
/*  806: 798 */    return list;
/*  807:     */  }
/*  808:     */  
/*  809:     */  private static InsnList generateCopyToInstructions(MappedSubtypeInfo mappedType) {
/*  810: 802 */    InsnList list = new InsnList();
/*  811:     */    
/*  813: 805 */    list.add(getIntNode(mappedType.sizeof - mappedType.padding));
/*  814:     */    
/*  815: 807 */    list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "copy", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";I)V"));
/*  816:     */    
/*  818: 810 */    return list;
/*  819:     */  }
/*  820:     */  
/*  821:     */  private static InsnList generateRunViewConstructorInstructions(MethodInsnNode methodInsn) {
/*  822: 814 */    InsnList list = new InsnList();
/*  823:     */    
/*  825: 817 */    list.add(new InsnNode(89));
/*  826:     */    
/*  827: 819 */    list.add(new MethodInsnNode(182, methodInsn.owner, "constructView$LWJGL", "()V"));
/*  828:     */    
/*  830: 822 */    return list;
/*  831:     */  }
/*  832:     */  
/*  833:     */  private static InsnList generateSliceInstructions(MethodInsnNode methodInsn) {
/*  834: 826 */    InsnList list = new InsnList();
/*  835:     */    
/*  837: 829 */    list.add(new TypeInsnNode(187, methodInsn.owner));
/*  838:     */    
/*  839: 831 */    list.add(new InsnNode(89));
/*  840:     */    
/*  841: 833 */    list.add(new MethodInsnNode(183, methodInsn.owner, "<init>", "()V"));
/*  842:     */    
/*  843: 835 */    list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "slice", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";)L" + MAPPED_OBJECT_JVM + ";"));
/*  844:     */    
/*  846: 838 */    return list;
/*  847:     */  }
/*  848:     */  
/*  849:     */  private static InsnList generateDupInstructions(MethodInsnNode methodInsn) {
/*  850: 842 */    InsnList list = new InsnList();
/*  851:     */    
/*  853: 845 */    list.add(new TypeInsnNode(187, methodInsn.owner));
/*  854:     */    
/*  855: 847 */    list.add(new InsnNode(89));
/*  856:     */    
/*  857: 849 */    list.add(new MethodInsnNode(183, methodInsn.owner, "<init>", "()V"));
/*  858:     */    
/*  859: 851 */    list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "dup", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";)L" + MAPPED_OBJECT_JVM + ";"));
/*  860:     */    
/*  862: 854 */    return list;
/*  863:     */  }
/*  864:     */  
/*  865:     */  private static InsnList generateMapInstructions(MappedSubtypeInfo mappedType, String className, boolean mapDirectMethod, boolean mallocMethod) {
/*  866: 858 */    InsnList trg = new InsnList();
/*  867:     */    
/*  868: 860 */    if (mallocMethod)
/*  869:     */    {
/*  870: 862 */      trg.add(getIntNode(mappedType.sizeof));
/*  871:     */      
/*  872: 864 */      trg.add(new InsnNode(104));
/*  873:     */      
/*  874: 866 */      trg.add(new MethodInsnNode(184, mappedType.cacheLinePadded ? jvmClassName(CacheUtil.class) : jvmClassName(BufferUtils.class), "createByteBuffer", "(I)L" + jvmClassName(ByteBuffer.class) + ";"));
/*  875:     */    }
/*  876: 868 */    else if (mapDirectMethod)
/*  877:     */    {
/*  878: 870 */      trg.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "newBuffer", "(JI)L" + jvmClassName(ByteBuffer.class) + ";"));
/*  879:     */    }
/*  880:     */    
/*  883: 875 */    trg.add(new TypeInsnNode(187, className));
/*  884:     */    
/*  885: 877 */    trg.add(new InsnNode(89));
/*  886:     */    
/*  887: 879 */    trg.add(new MethodInsnNode(183, className, "<init>", "()V"));
/*  888:     */    
/*  889: 881 */    trg.add(new InsnNode(90));
/*  890:     */    
/*  891: 883 */    trg.add(new InsnNode(95));
/*  892:     */    
/*  893: 885 */    trg.add(getIntNode(mappedType.align));
/*  894:     */    
/*  895: 887 */    trg.add(getIntNode(mappedType.sizeof));
/*  896:     */    
/*  897: 889 */    trg.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "setup", "(L" + MAPPED_OBJECT_JVM + ";Ljava/nio/ByteBuffer;II)V"));
/*  898:     */    
/*  900: 892 */    return trg;
/*  901:     */  }
/*  902:     */  
/*  903:     */  static InsnList transformFieldAccess(FieldInsnNode fieldInsn)
/*  904:     */  {
/*  905: 897 */    MappedSubtypeInfo mappedSubtype = (MappedSubtypeInfo)className_to_subtype.get(fieldInsn.owner);
/*  906: 898 */    if (mappedSubtype == null)
/*  907:     */    {
/*  909: 901 */      if (("view".equals(fieldInsn.name)) && (fieldInsn.owner.startsWith(MAPPEDSET_PREFIX))) {
/*  910: 902 */        return generateSetViewInstructions(fieldInsn);
/*  911:     */      }
/*  912: 904 */      return null;
/*  913:     */    }
/*  914:     */    
/*  915: 907 */    if ("SIZEOF".equals(fieldInsn.name)) {
/*  916: 908 */      return generateSIZEOFInstructions(fieldInsn, mappedSubtype);
/*  917:     */    }
/*  918: 910 */    if ("view".equals(fieldInsn.name)) {
/*  919: 911 */      return generateViewInstructions(fieldInsn, mappedSubtype);
/*  920:     */    }
/*  921: 913 */    if (("baseAddress".equals(fieldInsn.name)) || ("viewAddress".equals(fieldInsn.name))) {
/*  922: 914 */      return generateAddressInstructions(fieldInsn);
/*  923:     */    }
/*  924:     */    
/*  925: 917 */    FieldInfo field = (FieldInfo)mappedSubtype.fields.get(fieldInsn.name);
/*  926: 918 */    if (field == null) {
/*  927: 919 */      return null;
/*  928:     */    }
/*  929:     */    
/*  930: 922 */    if (fieldInsn.desc.equals("L" + jvmClassName(ByteBuffer.class) + ";")) {
/*  931: 923 */      return generateByteBufferInstructions(fieldInsn, mappedSubtype, field.offset);
/*  932:     */    }
/*  933:     */    
/*  934: 926 */    return generateFieldInstructions(fieldInsn, field);
/*  935:     */  }
/*  936:     */  
/*  937:     */  private static InsnList generateSetViewInstructions(FieldInsnNode fieldInsn) {
/*  938: 930 */    if (fieldInsn.getOpcode() == 180)
/*  939: 931 */      throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
/*  940: 932 */    if (fieldInsn.getOpcode() != 181) {
/*  941: 933 */      throw new InternalError();
/*  942:     */    }
/*  943: 935 */    InsnList list = new InsnList();
/*  944:     */    
/*  946: 938 */    if (MAPPED_SET2_JVM.equals(fieldInsn.owner)) {
/*  947: 939 */      list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_views", "(L" + MAPPED_SET2_JVM + ";I)V"));
/*  948: 940 */    } else if (MAPPED_SET3_JVM.equals(fieldInsn.owner)) {
/*  949: 941 */      list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_views", "(L" + MAPPED_SET3_JVM + ";I)V"));
/*  950: 942 */    } else if (MAPPED_SET4_JVM.equals(fieldInsn.owner)) {
/*  951: 943 */      list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_views", "(L" + MAPPED_SET4_JVM + ";I)V"));
/*  952:     */    } else {
/*  953: 945 */      throw new InternalError();
/*  954:     */    }
/*  955:     */    
/*  956: 948 */    return list;
/*  957:     */  }
/*  958:     */  
/*  959:     */  private static InsnList generateSIZEOFInstructions(FieldInsnNode fieldInsn, MappedSubtypeInfo mappedSubtype) {
/*  960: 952 */    if (!"I".equals(fieldInsn.desc)) {
/*  961: 953 */      throw new InternalError();
/*  962:     */    }
/*  963: 955 */    InsnList list = new InsnList();
/*  964:     */    
/*  965: 957 */    if (fieldInsn.getOpcode() == 178) {
/*  966: 958 */      list.add(getIntNode(mappedSubtype.sizeof));
/*  967: 959 */      return list;
/*  968:     */    }
/*  969:     */    
/*  970: 962 */    if (fieldInsn.getOpcode() == 179) {
/*  971: 963 */      throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
/*  972:     */    }
/*  973: 965 */    throw new InternalError();
/*  974:     */  }
/*  975:     */  
/*  976:     */  private static InsnList generateViewInstructions(FieldInsnNode fieldInsn, MappedSubtypeInfo mappedSubtype) {
/*  977: 969 */    if (!"I".equals(fieldInsn.desc)) {
/*  978: 970 */      throw new InternalError();
/*  979:     */    }
/*  980: 972 */    InsnList list = new InsnList();
/*  981:     */    
/*  982: 974 */    if (fieldInsn.getOpcode() == 180) {
/*  983: 975 */      if (mappedSubtype.sizeof_shift != 0)
/*  984:     */      {
/*  985: 977 */        list.add(getIntNode(mappedSubtype.sizeof_shift));
/*  986:     */        
/*  987: 979 */        list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "get_view_shift", "(L" + MAPPED_OBJECT_JVM + ";I)I"));
/*  988:     */      }
/*  989:     */      else
/*  990:     */      {
/*  991: 983 */        list.add(getIntNode(mappedSubtype.sizeof));
/*  992:     */        
/*  993: 985 */        list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "get_view", "(L" + MAPPED_OBJECT_JVM + ";I)I"));
/*  994:     */      }
/*  995:     */      
/*  996: 988 */      return list;
/*  997:     */    }
/*  998:     */    
/*  999: 991 */    if (fieldInsn.getOpcode() == 181) {
/* 1000: 992 */      if (mappedSubtype.sizeof_shift != 0)
/* 1001:     */      {
/* 1002: 994 */        list.add(getIntNode(mappedSubtype.sizeof_shift));
/* 1003:     */        
/* 1004: 996 */        list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_view_shift", "(L" + MAPPED_OBJECT_JVM + ";II)V"));
/* 1005:     */      }
/* 1006:     */      else
/* 1007:     */      {
/* 1008:1000 */        list.add(getIntNode(mappedSubtype.sizeof));
/* 1009:     */        
/* 1010:1002 */        list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_view", "(L" + MAPPED_OBJECT_JVM + ";II)V"));
/* 1011:     */      }
/* 1012:     */      
/* 1013:1005 */      return list;
/* 1014:     */    }
/* 1015:     */    
/* 1016:1008 */    throw new InternalError();
/* 1017:     */  }
/* 1018:     */  
/* 1019:     */  private static InsnList generateAddressInstructions(FieldInsnNode fieldInsn) {
/* 1020:1012 */    if (!"J".equals(fieldInsn.desc)) {
/* 1021:1013 */      throw new IllegalStateException();
/* 1022:     */    }
/* 1023:1015 */    if (fieldInsn.getOpcode() == 180) {
/* 1024:1016 */      return null;
/* 1025:     */    }
/* 1026:1018 */    if (fieldInsn.getOpcode() == 181) {
/* 1027:1019 */      throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
/* 1028:     */    }
/* 1029:1021 */    throw new InternalError();
/* 1030:     */  }
/* 1031:     */  
/* 1032:     */  private static InsnList generateByteBufferInstructions(FieldInsnNode fieldInsn, MappedSubtypeInfo mappedSubtype, long fieldOffset) {
/* 1033:1025 */    if (fieldInsn.getOpcode() == 181) {
/* 1034:1026 */      throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
/* 1035:     */    }
/* 1036:1028 */    if (fieldInsn.getOpcode() == 180) {
/* 1037:1029 */      InsnList list = new InsnList();
/* 1038:     */      
/* 1040:1032 */      list.add(new FieldInsnNode(180, mappedSubtype.className, "viewAddress", "J"));
/* 1041:     */      
/* 1042:1034 */      list.add(new LdcInsnNode(Long.valueOf(fieldOffset)));
/* 1043:     */      
/* 1044:1036 */      list.add(new InsnNode(97));
/* 1045:     */      
/* 1046:1038 */      list.add(new LdcInsnNode(Long.valueOf(((FieldInfo)mappedSubtype.fields.get(fieldInsn.name)).length)));
/* 1047:     */      
/* 1048:1040 */      list.add(new InsnNode(136));
/* 1049:     */      
/* 1050:1042 */      list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "newBuffer", "(JI)L" + jvmClassName(ByteBuffer.class) + ";"));
/* 1051:     */      
/* 1053:1045 */      return list;
/* 1054:     */    }
/* 1055:     */    
/* 1056:1048 */    throw new InternalError();
/* 1057:     */  }
/* 1058:     */  
/* 1059:     */  private static InsnList generateFieldInstructions(FieldInsnNode fieldInsn, FieldInfo field) {
/* 1060:1052 */    InsnList list = new InsnList();
/* 1061:     */    
/* 1062:1054 */    if (fieldInsn.getOpcode() == 181)
/* 1063:     */    {
/* 1064:1056 */      list.add(getIntNode((int)field.offset));
/* 1065:     */      
/* 1066:1058 */      list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, field.getAccessType() + "put", "(L" + MAPPED_OBJECT_JVM + ";" + fieldInsn.desc + "I)V"));
/* 1067:     */      
/* 1068:1060 */      return list;
/* 1069:     */    }
/* 1070:     */    
/* 1071:1063 */    if (fieldInsn.getOpcode() == 180)
/* 1072:     */    {
/* 1073:1065 */      list.add(getIntNode((int)field.offset));
/* 1074:     */      
/* 1075:1067 */      list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, field.getAccessType() + "get", "(L" + MAPPED_OBJECT_JVM + ";I)" + fieldInsn.desc));
/* 1076:     */      
/* 1077:1069 */      return list;
/* 1078:     */    }
/* 1079:     */    
/* 1080:1072 */    throw new InternalError();
/* 1081:     */  }
/* 1082:     */  
/* 1083:     */  static int transformArrayAccess(InsnList instructions, int i, Map<AbstractInsnNode, Frame<BasicValue>> frameMap, VarInsnNode loadInsn, MappedSubtypeInfo mappedSubtype, int var)
/* 1084:     */  {
/* 1085:1077 */    int loadStackSize = ((Frame)frameMap.get(loadInsn)).getStackSize() + 1;
/* 1086:     */    
/* 1087:1079 */    AbstractInsnNode nextInsn = loadInsn;
/* 1088:     */    for (;;)
/* 1089:     */    {
/* 1090:1082 */      nextInsn = nextInsn.getNext();
/* 1091:1083 */      if (nextInsn == null) {
/* 1092:1084 */        throw new InternalError();
/* 1093:     */      }
/* 1094:1086 */      Frame<BasicValue> frame = (Frame)frameMap.get(nextInsn);
/* 1095:1087 */      if (frame != null)
/* 1096:     */      {
/* 1098:1090 */        int stackSize = frame.getStackSize();
/* 1099:     */        
/* 1100:1092 */        if ((stackSize == loadStackSize + 1) && (nextInsn.getOpcode() == 50)) {
/* 1101:1093 */          AbstractInsnNode aaLoadInsn = nextInsn;
/* 1102:     */          do {
/* 1103:     */            for (;;) {
/* 1104:1096 */              nextInsn = nextInsn.getNext();
/* 1105:1097 */              if (nextInsn == null) {
/* 1106:     */                break label537;
/* 1107:     */              }
/* 1108:1100 */              frame = (Frame)frameMap.get(nextInsn);
/* 1109:1101 */              if (frame != null)
/* 1110:     */              {
/* 1111:1103 */                stackSize = frame.getStackSize();
/* 1112:     */                
/* 1113:1105 */                if ((stackSize == loadStackSize + 1) && (nextInsn.getOpcode() == 181)) {
/* 1114:1106 */                  FieldInsnNode fieldInsn = (FieldInsnNode)nextInsn;
/* 1115:     */                  
/* 1117:1109 */                  instructions.insert(nextInsn, new MethodInsnNode(184, mappedSubtype.className, setterName(fieldInsn.name), "(L" + mappedSubtype.className + ";I" + fieldInsn.desc + ")V"));
/* 1118:     */                  
/* 1119:1111 */                  instructions.remove(nextInsn);
/* 1120:     */                  break label537;
/* 1121:     */                }
/* 1122:1114 */                if ((stackSize == loadStackSize) && (nextInsn.getOpcode() == 180)) {
/* 1123:1115 */                  FieldInsnNode fieldInsn = (FieldInsnNode)nextInsn;
/* 1124:     */                  
/* 1126:1118 */                  instructions.insert(nextInsn, new MethodInsnNode(184, mappedSubtype.className, getterName(fieldInsn.name), "(L" + mappedSubtype.className + ";I)" + fieldInsn.desc));
/* 1127:     */                  
/* 1128:1120 */                  instructions.remove(nextInsn);
/* 1129:     */                  break label537;
/* 1130:     */                }
/* 1131:1123 */                if ((stackSize != loadStackSize) || (nextInsn.getOpcode() != 89) || (nextInsn.getNext().getOpcode() != 180))
/* 1132:     */                  break;
/* 1133:1125 */                FieldInsnNode fieldInsn = (FieldInsnNode)nextInsn.getNext();
/* 1134:     */                
/* 1135:1127 */                MethodInsnNode getter = new MethodInsnNode(184, mappedSubtype.className, getterName(fieldInsn.name), "(L" + mappedSubtype.className + ";I)" + fieldInsn.desc);
/* 1136:     */                
/* 1138:1130 */                instructions.insert(nextInsn, new InsnNode(92));
/* 1139:     */                
/* 1140:1132 */                instructions.insert(nextInsn.getNext(), getter);
/* 1141:     */                
/* 1143:1135 */                instructions.remove(nextInsn);
/* 1144:1136 */                instructions.remove(fieldInsn);
/* 1145:     */                
/* 1146:1138 */                nextInsn = getter;
/* 1147:     */              }
/* 1148:1140 */            } } while (stackSize >= loadStackSize);
/* 1149:1141 */          throw new ClassFormatError("Invalid " + mappedSubtype.className + " view array usage detected: " + getOpcodeName(nextInsn));
/* 1150:     */          
/* 1151:     */          label537:
/* 1152:1144 */          instructions.remove(aaLoadInsn);
/* 1153:     */          
/* 1154:1146 */          return i; }
/* 1155:1147 */        if ((stackSize == loadStackSize) && (nextInsn.getOpcode() == 190)) {
/* 1156:1148 */          if ((LWJGLUtil.DEBUG) && (loadInsn.getNext() != nextInsn)) {
/* 1157:1149 */            throw new InternalError();
/* 1158:     */          }
/* 1159:1151 */          instructions.remove(nextInsn);
/* 1160:1152 */          loadInsn.var = var;
/* 1161:1153 */          instructions.insert(loadInsn, new MethodInsnNode(182, mappedSubtype.className, "capacity", "()I"));
/* 1162:     */          
/* 1163:1155 */          return i + 1; }
/* 1164:1156 */        if (stackSize < loadStackSize)
/* 1165:1157 */          throw new ClassFormatError("Invalid " + mappedSubtype.className + " view array usage detected: " + getOpcodeName(nextInsn));
/* 1166:     */      }
/* 1167:     */    }
/* 1168:     */  }
/* 1169:     */  
/* 1170:     */  private static class FieldInfo {
/* 1171:     */    final long offset;
/* 1172:     */    final long length;
/* 1173:     */    final long lengthPadded;
/* 1174:     */    final Type type;
/* 1175:     */    final boolean isVolatile;
/* 1176:     */    final boolean isPointer;
/* 1177:     */    
/* 1178:     */    FieldInfo(long offset, long length, long lengthPadded, Type type, boolean isVolatile, boolean isPointer) {
/* 1179:1171 */      this.offset = offset;
/* 1180:1172 */      this.length = length;
/* 1181:1173 */      this.lengthPadded = lengthPadded;
/* 1182:1174 */      this.type = type;
/* 1183:1175 */      this.isVolatile = isVolatile;
/* 1184:1176 */      this.isPointer = isPointer;
/* 1185:     */    }
/* 1186:     */    
/* 1187:     */    String getAccessType() {
/* 1188:1180 */      return this.type.getDescriptor().toLowerCase() + (this.isVolatile ? "v" : "");
/* 1189:     */    }
/* 1190:     */  }
/* 1191:     */  
/* 1193:     */  private static class MappedSubtypeInfo
/* 1194:     */  {
/* 1195:     */    final String className;
/* 1196:     */    
/* 1197:     */    final int sizeof;
/* 1198:     */    final int sizeof_shift;
/* 1199:     */    final int align;
/* 1200:     */    final int padding;
/* 1201:     */    final boolean cacheLinePadded;
/* 1202:     */    final Map<String, MappedObjectTransformer.FieldInfo> fields;
/* 1203:     */    
/* 1204:     */    MappedSubtypeInfo(String className, Map<String, MappedObjectTransformer.FieldInfo> fields, int sizeof, int align, int padding, boolean cacheLinePadded)
/* 1205:     */    {
/* 1206:1198 */      this.className = className;
/* 1207:     */      
/* 1208:1200 */      this.sizeof = sizeof;
/* 1209:1201 */      if ((sizeof - 1 & sizeof) == 0) {
/* 1210:1202 */        this.sizeof_shift = getPoT(sizeof);
/* 1211:     */      } else
/* 1212:1204 */        this.sizeof_shift = 0;
/* 1213:1205 */      this.align = align;
/* 1214:1206 */      this.padding = padding;
/* 1215:1207 */      this.cacheLinePadded = cacheLinePadded;
/* 1216:     */      
/* 1217:1209 */      this.fields = fields;
/* 1218:     */    }
/* 1219:     */    
/* 1220:     */    private static int getPoT(int value) {
/* 1221:1213 */      int pot = -1;
/* 1222:1214 */      while (value > 0) {
/* 1223:1215 */        pot++;
/* 1224:1216 */        value >>= 1;
/* 1225:     */      }
/* 1226:1218 */      return pot;
/* 1227:     */    }
/* 1228:     */  }
/* 1229:     */  
/* 1232:     */  private static void getClassEnums(Class clazz, Map<Integer, String> map, String... prefixFilters)
/* 1233:     */  {
/* 1234:     */    try
/* 1235:     */    {
/* 1236:     */      label128:
/* 1237:     */      
/* 1238:1230 */      for (Field field : clazz.getFields())
/* 1239:1231 */        if ((Modifier.isStatic(field.getModifiers())) && (field.getType() == Integer.TYPE))
/* 1240:     */        {
/* 1242:1234 */          for (String filter : prefixFilters) {
/* 1243:1235 */            if (field.getName().startsWith(filter)) {
/* 1244:     */              break label128;
/* 1245:     */            }
/* 1246:     */          }
/* 1247:1239 */          if (map.put((Integer)field.get(null), field.getName()) != null)
/* 1248:1240 */            throw new IllegalStateException();
/* 1249:     */        }
/* 1250:     */    } catch (Exception e) {
/* 1251:1243 */      e.printStackTrace();
/* 1252:     */    }
/* 1253:     */  }
/* 1254:     */  
/* 1255:     */  static String getOpcodeName(AbstractInsnNode insn) {
/* 1256:1248 */    String op = (String)OPCODE_TO_NAME.get(Integer.valueOf(insn.getOpcode()));
/* 1257:1249 */    return (String)INSNTYPE_TO_NAME.get(Integer.valueOf(insn.getType())) + ": " + insn.getOpcode() + (op == null ? "" : new StringBuilder().append(" [").append((String)OPCODE_TO_NAME.get(Integer.valueOf(insn.getOpcode()))).append("]").toString());
/* 1258:     */  }
/* 1259:     */  
/* 1260:     */  static String jvmClassName(Class<?> type) {
/* 1261:1253 */    return type.getName().replace('.', '/');
/* 1262:     */  }
/* 1263:     */  
/* 1264:     */  static String getterName(String fieldName) {
/* 1265:1257 */    return "get$" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) + "$LWJGL";
/* 1266:     */  }
/* 1267:     */  
/* 1268:     */  static String setterName(String fieldName) {
/* 1269:1261 */    return "set$" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) + "$LWJGL";
/* 1270:     */  }
/* 1271:     */  
/* 1272:     */  private static void checkInsnAfterIsArray(AbstractInsnNode instruction, int opcode) {
/* 1273:1265 */    if (instruction == null) {
/* 1274:1266 */      throw new ClassFormatError("Unexpected end of instructions after .asArray() method.");
/* 1275:     */    }
/* 1276:1268 */    if (instruction.getOpcode() != opcode)
/* 1277:1269 */      throw new ClassFormatError("The result of .asArray() must be stored to a local variable. Found: " + getOpcodeName(instruction));
/* 1278:     */  }
/* 1279:     */  
/* 1280:     */  static AbstractInsnNode getIntNode(int value) {
/* 1281:1273 */    if ((value <= 5) && (-1 <= value)) {
/* 1282:1274 */      return new InsnNode(2 + value + 1);
/* 1283:     */    }
/* 1284:1276 */    if ((value >= -128) && (value <= 127)) {
/* 1285:1277 */      return new IntInsnNode(16, value);
/* 1286:     */    }
/* 1287:1279 */    if ((value >= -32768) && (value <= 32767)) {
/* 1288:1280 */      return new IntInsnNode(17, value);
/* 1289:     */    }
/* 1290:1282 */    return new LdcInsnNode(Integer.valueOf(value));
/* 1291:     */  }
/* 1292:     */  
/* 1293:     */  static void visitIntNode(MethodVisitor mv, int value) {
/* 1294:1286 */    if ((value <= 5) && (-1 <= value)) {
/* 1295:1287 */      mv.visitInsn(2 + value + 1);
/* 1296:1288 */    } else if ((value >= -128) && (value <= 127)) {
/* 1297:1289 */      mv.visitIntInsn(16, value);
/* 1298:1290 */    } else if ((value >= -32768) && (value <= 32767)) {
/* 1299:1291 */      mv.visitIntInsn(17, value);
/* 1300:     */    } else {
/* 1301:1293 */      mv.visitLdcInsn(Integer.valueOf(value));
/* 1302:     */    }
/* 1303:     */  }
/* 1304:     */  
/* 1305:     */  static int replace(InsnList instructions, int i, AbstractInsnNode location, InsnList list) {
/* 1306:1298 */    int size = list.size();
/* 1307:     */    
/* 1308:1300 */    instructions.insert(location, list);
/* 1309:1301 */    instructions.remove(location);
/* 1310:     */    
/* 1311:1303 */    return i + (size - 1);
/* 1312:     */  }
/* 1313:     */  
/* 1314:     */  private static void throwAccessErrorOnReadOnlyField(String className, String fieldName) {
/* 1315:1307 */    throw new IllegalAccessError("The " + className + "." + fieldName + " field is final.");
/* 1316:     */  }
/* 1317:     */  
/* 1318:     */  private static void printBytecode(byte[] bytecode) {
/* 1319:1311 */    StringWriter sw = new StringWriter();
/* 1320:1312 */    ClassVisitor tracer = new TraceClassVisitor(new ClassWriter(0), new PrintWriter(sw));
/* 1321:1313 */    new ClassReader(bytecode).accept(tracer, 0);
/* 1322:1314 */    String dump = sw.toString();
/* 1323:     */    
/* 1324:1316 */    LWJGLUtil.log(dump);
/* 1325:     */  }
/* 1326:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedObjectTransformer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */