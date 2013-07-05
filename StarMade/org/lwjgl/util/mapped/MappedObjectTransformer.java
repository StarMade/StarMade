/*      */ package org.lwjgl.util.mapped;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.StringWriter;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.nio.Buffer;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.LWJGLUtil;
/*      */ import org.lwjgl.MemoryUtil;
/*      */ import org.objectweb.asm.ClassAdapter;
/*      */ import org.objectweb.asm.ClassReader;
/*      */ import org.objectweb.asm.ClassVisitor;
/*      */ import org.objectweb.asm.ClassWriter;
/*      */ import org.objectweb.asm.FieldVisitor;
/*      */ import org.objectweb.asm.MethodVisitor;
/*      */ import org.objectweb.asm.Opcodes;
/*      */ import org.objectweb.asm.Type;
/*      */ import org.objectweb.asm.tree.AbstractInsnNode;
/*      */ import org.objectweb.asm.tree.AnnotationNode;
/*      */ import org.objectweb.asm.tree.FieldInsnNode;
/*      */ import org.objectweb.asm.tree.FieldNode;
/*      */ import org.objectweb.asm.tree.InsnList;
/*      */ import org.objectweb.asm.tree.InsnNode;
/*      */ import org.objectweb.asm.tree.IntInsnNode;
/*      */ import org.objectweb.asm.tree.LdcInsnNode;
/*      */ import org.objectweb.asm.tree.MethodInsnNode;
/*      */ import org.objectweb.asm.tree.MethodNode;
/*      */ import org.objectweb.asm.tree.TypeInsnNode;
/*      */ import org.objectweb.asm.tree.VarInsnNode;
/*      */ import org.objectweb.asm.tree.analysis.Analyzer;
/*      */ import org.objectweb.asm.tree.analysis.AnalyzerException;
/*      */ import org.objectweb.asm.tree.analysis.BasicValue;
/*      */ import org.objectweb.asm.tree.analysis.Frame;
/*      */ import org.objectweb.asm.tree.analysis.SimpleVerifier;
/*      */ import org.objectweb.asm.util.TraceClassVisitor;
/*      */ import sun.misc.Unsafe;
/*      */ 
/*      */ public class MappedObjectTransformer
/*      */ {
/*      */   static final boolean PRINT_ACTIVITY;
/*      */   static final boolean PRINT_TIMING;
/*      */   static final boolean PRINT_BYTECODE;
/*      */   static final Map<String, MappedSubtypeInfo> className_to_subtype;
/*      */   static final String MAPPED_OBJECT_JVM;
/*      */   static final String MAPPED_HELPER_JVM;
/*      */   static final String MAPPEDSET_PREFIX;
/*      */   static final String MAPPED_SET2_JVM;
/*      */   static final String MAPPED_SET3_JVM;
/*      */   static final String MAPPED_SET4_JVM;
/*      */   static final String CACHE_LINE_PAD_JVM;
/*      */   static final String VIEWADDRESS_METHOD_NAME = "getViewAddress";
/*      */   static final String NEXT_METHOD_NAME = "next";
/*      */   static final String ALIGN_METHOD_NAME = "getAlign";
/*      */   static final String SIZEOF_METHOD_NAME = "getSizeof";
/*      */   static final String CAPACITY_METHOD_NAME = "capacity";
/*      */   static final String VIEW_CONSTRUCTOR_NAME = "constructView$LWJGL";
/*      */   static final Map<Integer, String> OPCODE_TO_NAME;
/*      */   static final Map<Integer, String> INSNTYPE_TO_NAME;
/*      */   static boolean is_currently_computing_frames;
/*      */ 
/*      */   public static void register(Class<? extends MappedObject> type)
/*      */   {
/*  137 */     if (MappedObjectClassLoader.FORKED) {
/*  138 */       return;
/*      */     }
/*  140 */     MappedType mapped = (MappedType)type.getAnnotation(MappedType.class);
/*      */ 
/*  142 */     if ((mapped != null) && (mapped.padding() < 0)) {
/*  143 */       throw new ClassFormatError("Invalid mapped type padding: " + mapped.padding());
/*      */     }
/*  145 */     if ((type.getEnclosingClass() != null) && (!Modifier.isStatic(type.getModifiers()))) {
/*  146 */       throw new InternalError("only top-level or static inner classes are allowed");
/*      */     }
/*  148 */     String className = jvmClassName(type);
/*  149 */     Map fields = new HashMap();
/*      */ 
/*  151 */     long sizeof = 0L;
/*  152 */     for (Field field : type.getDeclaredFields()) {
/*  153 */       FieldInfo fieldInfo = registerField((mapped == null) || (mapped.autoGenerateOffsets()), className, sizeof, field);
/*  154 */       if (fieldInfo != null)
/*      */       {
/*  157 */         fields.put(field.getName(), fieldInfo);
/*      */ 
/*  159 */         sizeof = Math.max(sizeof, fieldInfo.offset + fieldInfo.lengthPadded);
/*      */       }
/*      */     }
/*  162 */     int align = 4;
/*  163 */     int padding = 0;
/*  164 */     boolean cacheLinePadded = false;
/*      */ 
/*  166 */     if (mapped != null) {
/*  167 */       align = mapped.align();
/*  168 */       if (mapped.cacheLinePadding()) {
/*  169 */         if (mapped.padding() != 0) {
/*  170 */           throw new ClassFormatError("Mapped type padding cannot be specified together with cacheLinePadding.");
/*      */         }
/*  172 */         int cacheLineMod = (int)(sizeof % CacheUtil.getCacheLineSize());
/*  173 */         if (cacheLineMod != 0) {
/*  174 */           padding = CacheUtil.getCacheLineSize() - cacheLineMod;
/*      */         }
/*  176 */         cacheLinePadded = true;
/*      */       } else {
/*  178 */         padding = mapped.padding();
/*      */       }
/*      */     }
/*  181 */     sizeof += padding;
/*      */ 
/*  183 */     MappedSubtypeInfo mappedType = new MappedSubtypeInfo(className, fields, (int)sizeof, align, padding, cacheLinePadded);
/*  184 */     if (className_to_subtype.put(className, mappedType) != null)
/*  185 */       throw new InternalError("duplicate mapped type: " + mappedType.className);
/*      */   }
/*      */ 
/*      */   private static FieldInfo registerField(boolean autoGenerateOffsets, String className, long advancingOffset, Field field) {
/*  189 */     if (Modifier.isStatic(field.getModifiers())) {
/*  190 */       return null;
/*      */     }
/*      */ 
/*  193 */     if ((!field.getType().isPrimitive()) && (field.getType() != ByteBuffer.class)) {
/*  194 */       throw new ClassFormatError("field '" + className + "." + field.getName() + "' not supported: " + field.getType());
/*      */     }
/*  196 */     MappedField meta = (MappedField)field.getAnnotation(MappedField.class);
/*  197 */     if ((meta == null) && (!autoGenerateOffsets)) {
/*  198 */       throw new ClassFormatError("field '" + className + "." + field.getName() + "' missing annotation " + MappedField.class.getName() + ": " + className);
/*      */     }
/*  200 */     Pointer pointer = (Pointer)field.getAnnotation(Pointer.class);
/*  201 */     if ((pointer != null) && (field.getType() != Long.TYPE)) {
/*  202 */       throw new ClassFormatError("The @Pointer annotation can only be used on long fields. @Pointer field found: " + className + "." + field.getName() + ": " + field.getType());
/*      */     }
/*  204 */     if ((Modifier.isVolatile(field.getModifiers())) && ((pointer != null) || (field.getType() == ByteBuffer.class)))
/*  205 */       throw new ClassFormatError("The volatile keyword is not supported for @Pointer or ByteBuffer fields. Volatile field found: " + className + "." + field.getName() + ": " + field.getType());
/*      */     long byteLength;
/*  209 */     if ((field.getType() == Long.TYPE) || (field.getType() == Double.TYPE))
/*      */     {
/*      */       long byteLength;
/*  210 */       if (pointer == null)
/*  211 */         byteLength = 8L;
/*      */       else
/*  213 */         byteLength = MappedObjectUnsafe.INSTANCE.addressSize();
/*      */     }
/*      */     else
/*      */     {
/*      */       long byteLength;
/*  214 */       if (field.getType() == Double.TYPE) {
/*  215 */         byteLength = 8L;
/*      */       }
/*      */       else
/*      */       {
/*      */         long byteLength;
/*  216 */         if ((field.getType() == Integer.TYPE) || (field.getType() == Float.TYPE)) {
/*  217 */           byteLength = 4L;
/*      */         }
/*      */         else
/*      */         {
/*      */           long byteLength;
/*  218 */           if ((field.getType() == Character.TYPE) || (field.getType() == Short.TYPE)) {
/*  219 */             byteLength = 2L;
/*      */           }
/*      */           else
/*      */           {
/*      */             long byteLength;
/*  220 */             if (field.getType() == Byte.TYPE) {
/*  221 */               byteLength = 1L;
/*  222 */             } else if (field.getType() == ByteBuffer.class) {
/*  223 */               long byteLength = meta.byteLength();
/*  224 */               if (byteLength < 0L)
/*  225 */                 throw new IllegalStateException("invalid byte length for mapped ByteBuffer field: " + className + "." + field.getName() + " [length=" + byteLength + "]");
/*      */             } else {
/*  227 */               throw new ClassFormatError(field.getType().getName());
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     long byteLength;
/*  229 */     if ((field.getType() != ByteBuffer.class) && (advancingOffset % byteLength != 0L)) {
/*  230 */       throw new IllegalStateException("misaligned mapped type: " + className + "." + field.getName());
/*      */     }
/*  232 */     CacheLinePad pad = (CacheLinePad)field.getAnnotation(CacheLinePad.class);
/*      */ 
/*  234 */     long byteOffset = advancingOffset;
/*  235 */     if ((meta != null) && (meta.byteOffset() != -1L)) {
/*  236 */       if (meta.byteOffset() < 0L)
/*  237 */         throw new ClassFormatError("Invalid field byte offset: " + className + "." + field.getName() + " [byteOffset=" + meta.byteOffset() + "]");
/*  238 */       if (pad != null) {
/*  239 */         throw new ClassFormatError("A field byte offset cannot be specified together with cache-line padding: " + className + "." + field.getName());
/*      */       }
/*  241 */       byteOffset = meta.byteOffset();
/*      */     }
/*      */ 
/*  244 */     long byteLengthPadded = byteLength;
/*  245 */     if (pad != null)
/*      */     {
/*  247 */       if ((pad.before()) && (byteOffset % CacheUtil.getCacheLineSize() != 0L)) {
/*  248 */         byteOffset += CacheUtil.getCacheLineSize() - (byteOffset & CacheUtil.getCacheLineSize() - 1);
/*      */       }
/*      */ 
/*  251 */       if ((pad.after()) && ((byteOffset + byteLength) % CacheUtil.getCacheLineSize() != 0L)) {
/*  252 */         byteLengthPadded += CacheUtil.getCacheLineSize() - (byteOffset + byteLength) % CacheUtil.getCacheLineSize();
/*      */       }
/*  254 */       assert ((!pad.before()) || (byteOffset % CacheUtil.getCacheLineSize() == 0L));
/*  255 */       assert ((!pad.after()) || ((byteOffset + byteLengthPadded) % CacheUtil.getCacheLineSize() == 0L));
/*      */     }
/*      */ 
/*  258 */     if (PRINT_ACTIVITY) {
/*  259 */       LWJGLUtil.log(MappedObjectTransformer.class.getSimpleName() + ": " + className + "." + field.getName() + " [type=" + field.getType().getSimpleName() + ", offset=" + byteOffset + "]");
/*      */     }
/*  261 */     return new FieldInfo(byteOffset, byteLength, byteLengthPadded, Type.getType(field.getType()), Modifier.isVolatile(field.getModifiers()), pointer != null);
/*      */   }
/*      */ 
/*      */   static byte[] transformMappedObject(byte[] bytecode)
/*      */   {
/*  266 */     ClassWriter cw = new ClassWriter(0);
/*      */ 
/*  268 */     ClassVisitor cv = new ClassAdapter(cw)
/*      */     {
/*  270 */       private final String[] DEFINALIZE_LIST = { "getViewAddress", "next", "getAlign", "getSizeof", "capacity" };
/*      */ 
/*      */       public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
/*      */       {
/*  279 */         for (String method : this.DEFINALIZE_LIST) {
/*  280 */           if (name.equals(method)) {
/*  281 */             access &= -17;
/*  282 */             break;
/*      */           }
/*      */         }
/*  285 */         return super.visitMethod(access, name, desc, signature, exceptions);
/*      */       }
/*      */     };
/*  289 */     new ClassReader(bytecode).accept(cv, 0);
/*  290 */     return cw.toByteArray();
/*      */   }
/*      */ 
/*      */   static byte[] transformMappedAPI(String className, byte[] bytecode) {
/*  294 */     ClassWriter cw = new ClassWriter(2)
/*      */     {
/*      */       protected String getCommonSuperClass(String a, String b)
/*      */       {
/*  299 */         if (((MappedObjectTransformer.is_currently_computing_frames) && (!a.startsWith("java/"))) || (!b.startsWith("java/"))) {
/*  300 */           return "java/lang/Object";
/*      */         }
/*  302 */         return super.getCommonSuperClass(a, b);
/*      */       }
/*      */     };
/*  307 */     TransformationAdapter ta = new TransformationAdapter(cw, className);
/*      */ 
/*  309 */     ClassVisitor cv = ta;
/*  310 */     if (className_to_subtype.containsKey(className)) {
/*  311 */       cv = getMethodGenAdapter(className, cv);
/*      */     }
/*  313 */     new ClassReader(bytecode).accept(cv, 4);
/*      */ 
/*  315 */     if (!ta.transformed) {
/*  316 */       return bytecode;
/*      */     }
/*  318 */     bytecode = cw.toByteArray();
/*  319 */     if (PRINT_BYTECODE) {
/*  320 */       printBytecode(bytecode);
/*      */     }
/*  322 */     return bytecode;
/*      */   }
/*      */ 
/*      */   private static ClassAdapter getMethodGenAdapter(final String className, ClassVisitor cv) {
/*  326 */     return new ClassAdapter(cv)
/*      */     {
/*      */       public void visitEnd()
/*      */       {
/*  330 */         MappedObjectTransformer.MappedSubtypeInfo mappedSubtype = (MappedObjectTransformer.MappedSubtypeInfo)MappedObjectTransformer.className_to_subtype.get(className);
/*      */ 
/*  332 */         generateViewAddressGetter();
/*  333 */         generateCapacity();
/*  334 */         generateAlignGetter(mappedSubtype);
/*  335 */         generateSizeofGetter();
/*  336 */         generateNext();
/*      */ 
/*  338 */         for (String fieldName : mappedSubtype.fields.keySet()) {
/*  339 */           MappedObjectTransformer.FieldInfo field = (MappedObjectTransformer.FieldInfo)mappedSubtype.fields.get(fieldName);
/*      */ 
/*  341 */           if (field.type.getDescriptor().length() > 1) {
/*  342 */             generateByteBufferGetter(fieldName, field);
/*      */           } else {
/*  344 */             generateFieldGetter(fieldName, field);
/*  345 */             generateFieldSetter(fieldName, field);
/*      */           }
/*      */         }
/*      */ 
/*  349 */         super.visitEnd();
/*      */       }
/*      */ 
/*      */       private void generateViewAddressGetter() {
/*  353 */         MethodVisitor mv = super.visitMethod(1, "getViewAddress", "(I)J", null, null);
/*  354 */         mv.visitCode();
/*  355 */         mv.visitVarInsn(25, 0);
/*  356 */         mv.visitFieldInsn(180, MappedObjectTransformer.MAPPED_OBJECT_JVM, "baseAddress", "J");
/*  357 */         mv.visitVarInsn(21, 1);
/*  358 */         mv.visitFieldInsn(178, className, "SIZEOF", "I");
/*  359 */         mv.visitInsn(104);
/*  360 */         mv.visitInsn(133);
/*  361 */         mv.visitInsn(97);
/*  362 */         if (MappedObject.CHECKS) {
/*  363 */           mv.visitInsn(92);
/*  364 */           mv.visitVarInsn(25, 0);
/*  365 */           mv.visitMethodInsn(184, MappedObjectTransformer.MAPPED_HELPER_JVM, "checkAddress", "(JL" + MappedObjectTransformer.MAPPED_OBJECT_JVM + ";)V");
/*      */         }
/*  367 */         mv.visitInsn(173);
/*  368 */         mv.visitMaxs(3, 2);
/*  369 */         mv.visitEnd();
/*      */       }
/*      */ 
/*      */       private void generateCapacity()
/*      */       {
/*  374 */         MethodVisitor mv = super.visitMethod(1, "capacity", "()I", null, null);
/*  375 */         mv.visitCode();
/*  376 */         mv.visitVarInsn(25, 0);
/*  377 */         mv.visitMethodInsn(182, MappedObjectTransformer.MAPPED_OBJECT_JVM, "backingByteBuffer", "()L" + MappedObjectTransformer.jvmClassName(ByteBuffer.class) + ";");
/*  378 */         mv.visitInsn(89);
/*  379 */         mv.visitMethodInsn(182, MappedObjectTransformer.jvmClassName(ByteBuffer.class), "capacity", "()I");
/*  380 */         mv.visitInsn(95);
/*  381 */         mv.visitMethodInsn(184, MappedObjectTransformer.jvmClassName(MemoryUtil.class), "getAddress0", "(L" + MappedObjectTransformer.jvmClassName(Buffer.class) + ";)J");
/*  382 */         mv.visitVarInsn(25, 0);
/*  383 */         mv.visitFieldInsn(180, MappedObjectTransformer.MAPPED_OBJECT_JVM, "baseAddress", "J");
/*  384 */         mv.visitInsn(101);
/*  385 */         mv.visitInsn(136);
/*  386 */         mv.visitInsn(96);
/*  387 */         mv.visitFieldInsn(178, className, "SIZEOF", "I");
/*  388 */         mv.visitInsn(108);
/*  389 */         mv.visitInsn(172);
/*  390 */         mv.visitMaxs(3, 1);
/*  391 */         mv.visitEnd();
/*      */       }
/*      */ 
/*      */       private void generateAlignGetter(MappedObjectTransformer.MappedSubtypeInfo mappedSubtype) {
/*  395 */         MethodVisitor mv = super.visitMethod(1, "getAlign", "()I", null, null);
/*  396 */         mv.visitCode();
/*  397 */         MappedObjectTransformer.visitIntNode(mv, mappedSubtype.sizeof);
/*  398 */         mv.visitInsn(172);
/*  399 */         mv.visitMaxs(1, 1);
/*  400 */         mv.visitEnd();
/*      */       }
/*      */ 
/*      */       private void generateSizeofGetter() {
/*  404 */         MethodVisitor mv = super.visitMethod(1, "getSizeof", "()I", null, null);
/*  405 */         mv.visitCode();
/*  406 */         mv.visitFieldInsn(178, className, "SIZEOF", "I");
/*  407 */         mv.visitInsn(172);
/*  408 */         mv.visitMaxs(1, 1);
/*  409 */         mv.visitEnd();
/*      */       }
/*      */ 
/*      */       private void generateNext() {
/*  413 */         MethodVisitor mv = super.visitMethod(1, "next", "()V", null, null);
/*  414 */         mv.visitCode();
/*  415 */         mv.visitVarInsn(25, 0);
/*  416 */         mv.visitInsn(89);
/*  417 */         mv.visitFieldInsn(180, MappedObjectTransformer.MAPPED_OBJECT_JVM, "viewAddress", "J");
/*  418 */         mv.visitFieldInsn(178, className, "SIZEOF", "I");
/*  419 */         mv.visitInsn(133);
/*  420 */         mv.visitInsn(97);
/*  421 */         mv.visitMethodInsn(182, className, "setViewAddress", "(J)V");
/*  422 */         mv.visitInsn(177);
/*  423 */         mv.visitMaxs(3, 1);
/*  424 */         mv.visitEnd();
/*      */       }
/*      */ 
/*      */       private void generateByteBufferGetter(String fieldName, MappedObjectTransformer.FieldInfo field) {
/*  428 */         MethodVisitor mv = super.visitMethod(9, MappedObjectTransformer.getterName(fieldName), "(L" + className + ";I)" + field.type.getDescriptor(), null, null);
/*  429 */         mv.visitCode();
/*  430 */         mv.visitVarInsn(25, 0);
/*  431 */         mv.visitVarInsn(21, 1);
/*  432 */         mv.visitMethodInsn(182, className, "getViewAddress", "(I)J");
/*  433 */         MappedObjectTransformer.visitIntNode(mv, (int)field.offset);
/*  434 */         mv.visitInsn(133);
/*  435 */         mv.visitInsn(97);
/*  436 */         MappedObjectTransformer.visitIntNode(mv, (int)field.length);
/*  437 */         mv.visitMethodInsn(184, MappedObjectTransformer.MAPPED_HELPER_JVM, "newBuffer", "(JI)L" + MappedObjectTransformer.jvmClassName(ByteBuffer.class) + ";");
/*  438 */         mv.visitInsn(176);
/*  439 */         mv.visitMaxs(3, 2);
/*  440 */         mv.visitEnd();
/*      */       }
/*      */ 
/*      */       private void generateFieldGetter(String fieldName, MappedObjectTransformer.FieldInfo field) {
/*  444 */         MethodVisitor mv = super.visitMethod(9, MappedObjectTransformer.getterName(fieldName), "(L" + className + ";I)" + field.type.getDescriptor(), null, null);
/*  445 */         mv.visitCode();
/*  446 */         mv.visitVarInsn(25, 0);
/*  447 */         mv.visitVarInsn(21, 1);
/*  448 */         mv.visitMethodInsn(182, className, "getViewAddress", "(I)J");
/*  449 */         MappedObjectTransformer.visitIntNode(mv, (int)field.offset);
/*  450 */         mv.visitInsn(133);
/*  451 */         mv.visitInsn(97);
/*  452 */         mv.visitMethodInsn(184, MappedObjectTransformer.MAPPED_HELPER_JVM, field.getAccessType() + "get", "(J)" + field.type.getDescriptor());
/*  453 */         mv.visitInsn(field.type.getOpcode(172));
/*  454 */         mv.visitMaxs(3, 2);
/*  455 */         mv.visitEnd();
/*      */       }
/*      */ 
/*      */       private void generateFieldSetter(String fieldName, MappedObjectTransformer.FieldInfo field) {
/*  459 */         MethodVisitor mv = super.visitMethod(9, MappedObjectTransformer.setterName(fieldName), "(L" + className + ";I" + field.type.getDescriptor() + ")V", null, null);
/*  460 */         mv.visitCode();
/*  461 */         int load = 0;
/*  462 */         switch (field.type.getSort()) {
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/*  468 */           load = 21;
/*  469 */           break;
/*      */         case 6:
/*  471 */           load = 23;
/*  472 */           break;
/*      */         case 7:
/*  474 */           load = 22;
/*  475 */           break;
/*      */         case 8:
/*  477 */           load = 24;
/*      */         }
/*      */ 
/*  480 */         mv.visitVarInsn(load, 2);
/*  481 */         mv.visitVarInsn(25, 0);
/*  482 */         mv.visitVarInsn(21, 1);
/*  483 */         mv.visitMethodInsn(182, className, "getViewAddress", "(I)J");
/*  484 */         MappedObjectTransformer.visitIntNode(mv, (int)field.offset);
/*  485 */         mv.visitInsn(133);
/*  486 */         mv.visitInsn(97);
/*  487 */         mv.visitMethodInsn(184, MappedObjectTransformer.MAPPED_HELPER_JVM, field.getAccessType() + "put", "(" + field.type.getDescriptor() + "J)V");
/*  488 */         mv.visitInsn(177);
/*  489 */         mv.visitMaxs(4, 4);
/*  490 */         mv.visitEnd();
/*      */       }
/*      */     };
/*      */   }
/*      */ 
/*      */   static int transformMethodCall(InsnList instructions, int i, Map<AbstractInsnNode, Frame<BasicValue>> frameMap, MethodInsnNode methodInsn, MappedSubtypeInfo mappedType, Map<Integer, MappedSubtypeInfo> arrayVars)
/*      */   {
/*  709 */     switch (methodInsn.getOpcode()) {
/*      */     case 182:
/*  711 */       if (("asArray".equals(methodInsn.name)) && (methodInsn.desc.equals("()[L" + MAPPED_OBJECT_JVM + ";")))
/*      */       {
/*      */         AbstractInsnNode nextInstruction;
/*  716 */         checkInsnAfterIsArray(nextInstruction = methodInsn.getNext(), 192);
/*  717 */         checkInsnAfterIsArray(nextInstruction = nextInstruction.getNext(), 58);
/*      */ 
/*  719 */         Frame frame = (Frame)frameMap.get(nextInstruction);
/*  720 */         String targetType = ((BasicValue)frame.getStack(frame.getStackSize() - 1)).getType().getElementType().getInternalName();
/*  721 */         if (!methodInsn.owner.equals(targetType))
/*      */         {
/*  728 */           throw new ClassCastException("Source: " + methodInsn.owner + " - Target: " + targetType);
/*      */         }
/*      */ 
/*  731 */         VarInsnNode varInstruction = (VarInsnNode)nextInstruction;
/*      */ 
/*  733 */         arrayVars.put(Integer.valueOf(varInstruction.var), mappedType);
/*      */ 
/*  735 */         instructions.remove(methodInsn.getNext());
/*  736 */         instructions.remove(methodInsn);
/*      */       }
/*      */ 
/*  739 */       if (("dup".equals(methodInsn.name)) && (methodInsn.desc.equals("()L" + MAPPED_OBJECT_JVM + ";"))) {
/*  740 */         i = replace(instructions, i, methodInsn, generateDupInstructions(methodInsn));
/*      */       }
/*  744 */       else if (("slice".equals(methodInsn.name)) && (methodInsn.desc.equals("()L" + MAPPED_OBJECT_JVM + ";"))) {
/*  745 */         i = replace(instructions, i, methodInsn, generateSliceInstructions(methodInsn));
/*      */       }
/*  749 */       else if (("runViewConstructor".equals(methodInsn.name)) && ("()V".equals(methodInsn.desc))) {
/*  750 */         i = replace(instructions, i, methodInsn, generateRunViewConstructorInstructions(methodInsn));
/*      */       }
/*  754 */       else if (("copyTo".equals(methodInsn.name)) && (methodInsn.desc.equals("(L" + MAPPED_OBJECT_JVM + ";)V"))) {
/*  755 */         i = replace(instructions, i, methodInsn, generateCopyToInstructions(mappedType));
/*      */       }
/*  759 */       else if (("copyRange".equals(methodInsn.name)) && (methodInsn.desc.equals("(L" + MAPPED_OBJECT_JVM + ";I)V")))
/*  760 */         i = replace(instructions, i, methodInsn, generateCopyRangeInstructions(mappedType));
/*  761 */       break;
/*      */     case 183:
/*  767 */       if ((methodInsn.owner.equals(MAPPED_OBJECT_JVM)) && ("<init>".equals(methodInsn.name)) && ("()V".equals(methodInsn.desc))) {
/*  768 */         instructions.remove(methodInsn.getPrevious());
/*  769 */         instructions.remove(methodInsn);
/*      */ 
/*  771 */         i -= 2; } break;
/*      */     case 184:
/*  775 */       boolean isMapDirectMethod = ("map".equals(methodInsn.name)) && (methodInsn.desc.equals("(JI)L" + MAPPED_OBJECT_JVM + ";"));
/*  776 */       boolean isMapBufferMethod = ("map".equals(methodInsn.name)) && (methodInsn.desc.equals("(Ljava/nio/ByteBuffer;)L" + MAPPED_OBJECT_JVM + ";"));
/*  777 */       boolean isMallocMethod = ("malloc".equals(methodInsn.name)) && (methodInsn.desc.equals("(I)L" + MAPPED_OBJECT_JVM + ";"));
/*      */ 
/*  779 */       if ((isMapDirectMethod) || (isMapBufferMethod) || (isMallocMethod)) {
/*  780 */         i = replace(instructions, i, methodInsn, generateMapInstructions(mappedType, methodInsn.owner, isMapDirectMethod, isMallocMethod));
/*      */       }
/*      */       break;
/*      */     }
/*  784 */     return i;
/*      */   }
/*      */ 
/*      */   private static InsnList generateCopyRangeInstructions(MappedSubtypeInfo mappedType) {
/*  788 */     InsnList list = new InsnList();
/*      */ 
/*  791 */     list.add(getIntNode(mappedType.sizeof));
/*      */ 
/*  793 */     list.add(new InsnNode(104));
/*      */ 
/*  795 */     list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "copy", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";I)V"));
/*      */ 
/*  798 */     return list;
/*      */   }
/*      */ 
/*      */   private static InsnList generateCopyToInstructions(MappedSubtypeInfo mappedType) {
/*  802 */     InsnList list = new InsnList();
/*      */ 
/*  805 */     list.add(getIntNode(mappedType.sizeof - mappedType.padding));
/*      */ 
/*  807 */     list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "copy", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";I)V"));
/*      */ 
/*  810 */     return list;
/*      */   }
/*      */ 
/*      */   private static InsnList generateRunViewConstructorInstructions(MethodInsnNode methodInsn) {
/*  814 */     InsnList list = new InsnList();
/*      */ 
/*  817 */     list.add(new InsnNode(89));
/*      */ 
/*  819 */     list.add(new MethodInsnNode(182, methodInsn.owner, "constructView$LWJGL", "()V"));
/*      */ 
/*  822 */     return list;
/*      */   }
/*      */ 
/*      */   private static InsnList generateSliceInstructions(MethodInsnNode methodInsn) {
/*  826 */     InsnList list = new InsnList();
/*      */ 
/*  829 */     list.add(new TypeInsnNode(187, methodInsn.owner));
/*      */ 
/*  831 */     list.add(new InsnNode(89));
/*      */ 
/*  833 */     list.add(new MethodInsnNode(183, methodInsn.owner, "<init>", "()V"));
/*      */ 
/*  835 */     list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "slice", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";)L" + MAPPED_OBJECT_JVM + ";"));
/*      */ 
/*  838 */     return list;
/*      */   }
/*      */ 
/*      */   private static InsnList generateDupInstructions(MethodInsnNode methodInsn) {
/*  842 */     InsnList list = new InsnList();
/*      */ 
/*  845 */     list.add(new TypeInsnNode(187, methodInsn.owner));
/*      */ 
/*  847 */     list.add(new InsnNode(89));
/*      */ 
/*  849 */     list.add(new MethodInsnNode(183, methodInsn.owner, "<init>", "()V"));
/*      */ 
/*  851 */     list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "dup", "(L" + MAPPED_OBJECT_JVM + ";L" + MAPPED_OBJECT_JVM + ";)L" + MAPPED_OBJECT_JVM + ";"));
/*      */ 
/*  854 */     return list;
/*      */   }
/*      */ 
/*      */   private static InsnList generateMapInstructions(MappedSubtypeInfo mappedType, String className, boolean mapDirectMethod, boolean mallocMethod) {
/*  858 */     InsnList trg = new InsnList();
/*      */ 
/*  860 */     if (mallocMethod)
/*      */     {
/*  862 */       trg.add(getIntNode(mappedType.sizeof));
/*      */ 
/*  864 */       trg.add(new InsnNode(104));
/*      */ 
/*  866 */       trg.add(new MethodInsnNode(184, mappedType.cacheLinePadded ? jvmClassName(CacheUtil.class) : jvmClassName(BufferUtils.class), "createByteBuffer", "(I)L" + jvmClassName(ByteBuffer.class) + ";"));
/*      */     }
/*  868 */     else if (mapDirectMethod)
/*      */     {
/*  870 */       trg.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "newBuffer", "(JI)L" + jvmClassName(ByteBuffer.class) + ";"));
/*      */     }
/*      */ 
/*  875 */     trg.add(new TypeInsnNode(187, className));
/*      */ 
/*  877 */     trg.add(new InsnNode(89));
/*      */ 
/*  879 */     trg.add(new MethodInsnNode(183, className, "<init>", "()V"));
/*      */ 
/*  881 */     trg.add(new InsnNode(90));
/*      */ 
/*  883 */     trg.add(new InsnNode(95));
/*      */ 
/*  885 */     trg.add(getIntNode(mappedType.align));
/*      */ 
/*  887 */     trg.add(getIntNode(mappedType.sizeof));
/*      */ 
/*  889 */     trg.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "setup", "(L" + MAPPED_OBJECT_JVM + ";Ljava/nio/ByteBuffer;II)V"));
/*      */ 
/*  892 */     return trg;
/*      */   }
/*      */ 
/*      */   static InsnList transformFieldAccess(FieldInsnNode fieldInsn)
/*      */   {
/*  897 */     MappedSubtypeInfo mappedSubtype = (MappedSubtypeInfo)className_to_subtype.get(fieldInsn.owner);
/*  898 */     if (mappedSubtype == null)
/*      */     {
/*  901 */       if (("view".equals(fieldInsn.name)) && (fieldInsn.owner.startsWith(MAPPEDSET_PREFIX))) {
/*  902 */         return generateSetViewInstructions(fieldInsn);
/*      */       }
/*  904 */       return null;
/*      */     }
/*      */ 
/*  907 */     if ("SIZEOF".equals(fieldInsn.name)) {
/*  908 */       return generateSIZEOFInstructions(fieldInsn, mappedSubtype);
/*      */     }
/*  910 */     if ("view".equals(fieldInsn.name)) {
/*  911 */       return generateViewInstructions(fieldInsn, mappedSubtype);
/*      */     }
/*  913 */     if (("baseAddress".equals(fieldInsn.name)) || ("viewAddress".equals(fieldInsn.name))) {
/*  914 */       return generateAddressInstructions(fieldInsn);
/*      */     }
/*      */ 
/*  917 */     FieldInfo field = (FieldInfo)mappedSubtype.fields.get(fieldInsn.name);
/*  918 */     if (field == null) {
/*  919 */       return null;
/*      */     }
/*      */ 
/*  922 */     if (fieldInsn.desc.equals("L" + jvmClassName(ByteBuffer.class) + ";")) {
/*  923 */       return generateByteBufferInstructions(fieldInsn, mappedSubtype, field.offset);
/*      */     }
/*      */ 
/*  926 */     return generateFieldInstructions(fieldInsn, field);
/*      */   }
/*      */ 
/*      */   private static InsnList generateSetViewInstructions(FieldInsnNode fieldInsn) {
/*  930 */     if (fieldInsn.getOpcode() == 180)
/*  931 */       throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
/*  932 */     if (fieldInsn.getOpcode() != 181) {
/*  933 */       throw new InternalError();
/*      */     }
/*  935 */     InsnList list = new InsnList();
/*      */ 
/*  938 */     if (MAPPED_SET2_JVM.equals(fieldInsn.owner))
/*  939 */       list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_views", "(L" + MAPPED_SET2_JVM + ";I)V"));
/*  940 */     else if (MAPPED_SET3_JVM.equals(fieldInsn.owner))
/*  941 */       list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_views", "(L" + MAPPED_SET3_JVM + ";I)V"));
/*  942 */     else if (MAPPED_SET4_JVM.equals(fieldInsn.owner))
/*  943 */       list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_views", "(L" + MAPPED_SET4_JVM + ";I)V"));
/*      */     else {
/*  945 */       throw new InternalError();
/*      */     }
/*      */ 
/*  948 */     return list;
/*      */   }
/*      */ 
/*      */   private static InsnList generateSIZEOFInstructions(FieldInsnNode fieldInsn, MappedSubtypeInfo mappedSubtype) {
/*  952 */     if (!"I".equals(fieldInsn.desc)) {
/*  953 */       throw new InternalError();
/*      */     }
/*  955 */     InsnList list = new InsnList();
/*      */ 
/*  957 */     if (fieldInsn.getOpcode() == 178) {
/*  958 */       list.add(getIntNode(mappedSubtype.sizeof));
/*  959 */       return list;
/*      */     }
/*      */ 
/*  962 */     if (fieldInsn.getOpcode() == 179) {
/*  963 */       throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
/*      */     }
/*  965 */     throw new InternalError();
/*      */   }
/*      */ 
/*      */   private static InsnList generateViewInstructions(FieldInsnNode fieldInsn, MappedSubtypeInfo mappedSubtype) {
/*  969 */     if (!"I".equals(fieldInsn.desc)) {
/*  970 */       throw new InternalError();
/*      */     }
/*  972 */     InsnList list = new InsnList();
/*      */ 
/*  974 */     if (fieldInsn.getOpcode() == 180) {
/*  975 */       if (mappedSubtype.sizeof_shift != 0)
/*      */       {
/*  977 */         list.add(getIntNode(mappedSubtype.sizeof_shift));
/*      */ 
/*  979 */         list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "get_view_shift", "(L" + MAPPED_OBJECT_JVM + ";I)I"));
/*      */       }
/*      */       else
/*      */       {
/*  983 */         list.add(getIntNode(mappedSubtype.sizeof));
/*      */ 
/*  985 */         list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "get_view", "(L" + MAPPED_OBJECT_JVM + ";I)I"));
/*      */       }
/*      */ 
/*  988 */       return list;
/*      */     }
/*      */ 
/*  991 */     if (fieldInsn.getOpcode() == 181) {
/*  992 */       if (mappedSubtype.sizeof_shift != 0)
/*      */       {
/*  994 */         list.add(getIntNode(mappedSubtype.sizeof_shift));
/*      */ 
/*  996 */         list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_view_shift", "(L" + MAPPED_OBJECT_JVM + ";II)V"));
/*      */       }
/*      */       else
/*      */       {
/* 1000 */         list.add(getIntNode(mappedSubtype.sizeof));
/*      */ 
/* 1002 */         list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "put_view", "(L" + MAPPED_OBJECT_JVM + ";II)V"));
/*      */       }
/*      */ 
/* 1005 */       return list;
/*      */     }
/*      */ 
/* 1008 */     throw new InternalError();
/*      */   }
/*      */ 
/*      */   private static InsnList generateAddressInstructions(FieldInsnNode fieldInsn) {
/* 1012 */     if (!"J".equals(fieldInsn.desc)) {
/* 1013 */       throw new IllegalStateException();
/*      */     }
/* 1015 */     if (fieldInsn.getOpcode() == 180) {
/* 1016 */       return null;
/*      */     }
/* 1018 */     if (fieldInsn.getOpcode() == 181) {
/* 1019 */       throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
/*      */     }
/* 1021 */     throw new InternalError();
/*      */   }
/*      */ 
/*      */   private static InsnList generateByteBufferInstructions(FieldInsnNode fieldInsn, MappedSubtypeInfo mappedSubtype, long fieldOffset) {
/* 1025 */     if (fieldInsn.getOpcode() == 181) {
/* 1026 */       throwAccessErrorOnReadOnlyField(fieldInsn.owner, fieldInsn.name);
/*      */     }
/* 1028 */     if (fieldInsn.getOpcode() == 180) {
/* 1029 */       InsnList list = new InsnList();
/*      */ 
/* 1032 */       list.add(new FieldInsnNode(180, mappedSubtype.className, "viewAddress", "J"));
/*      */ 
/* 1034 */       list.add(new LdcInsnNode(Long.valueOf(fieldOffset)));
/*      */ 
/* 1036 */       list.add(new InsnNode(97));
/*      */ 
/* 1038 */       list.add(new LdcInsnNode(Long.valueOf(((FieldInfo)mappedSubtype.fields.get(fieldInsn.name)).length)));
/*      */ 
/* 1040 */       list.add(new InsnNode(136));
/*      */ 
/* 1042 */       list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, "newBuffer", "(JI)L" + jvmClassName(ByteBuffer.class) + ";"));
/*      */ 
/* 1045 */       return list;
/*      */     }
/*      */ 
/* 1048 */     throw new InternalError();
/*      */   }
/*      */ 
/*      */   private static InsnList generateFieldInstructions(FieldInsnNode fieldInsn, FieldInfo field) {
/* 1052 */     InsnList list = new InsnList();
/*      */ 
/* 1054 */     if (fieldInsn.getOpcode() == 181)
/*      */     {
/* 1056 */       list.add(getIntNode((int)field.offset));
/*      */ 
/* 1058 */       list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, field.getAccessType() + "put", "(L" + MAPPED_OBJECT_JVM + ";" + fieldInsn.desc + "I)V"));
/*      */ 
/* 1060 */       return list;
/*      */     }
/*      */ 
/* 1063 */     if (fieldInsn.getOpcode() == 180)
/*      */     {
/* 1065 */       list.add(getIntNode((int)field.offset));
/*      */ 
/* 1067 */       list.add(new MethodInsnNode(184, MAPPED_HELPER_JVM, field.getAccessType() + "get", "(L" + MAPPED_OBJECT_JVM + ";I)" + fieldInsn.desc));
/*      */ 
/* 1069 */       return list;
/*      */     }
/*      */ 
/* 1072 */     throw new InternalError();
/*      */   }
/*      */ 
/*      */   static int transformArrayAccess(InsnList instructions, int i, Map<AbstractInsnNode, Frame<BasicValue>> frameMap, VarInsnNode loadInsn, MappedSubtypeInfo mappedSubtype, int var)
/*      */   {
/* 1077 */     int loadStackSize = ((Frame)frameMap.get(loadInsn)).getStackSize() + 1;
/*      */ 
/* 1079 */     AbstractInsnNode nextInsn = loadInsn;
/*      */     while (true)
/*      */     {
/* 1082 */       nextInsn = nextInsn.getNext();
/* 1083 */       if (nextInsn == null) {
/* 1084 */         throw new InternalError();
/*      */       }
/* 1086 */       Frame frame = (Frame)frameMap.get(nextInsn);
/* 1087 */       if (frame != null)
/*      */       {
/* 1090 */         int stackSize = frame.getStackSize();
/*      */ 
/* 1092 */         if ((stackSize == loadStackSize + 1) && (nextInsn.getOpcode() == 50)) {
/* 1093 */           AbstractInsnNode aaLoadInsn = nextInsn;
/*      */           do
/*      */             while (true) {
/* 1096 */               nextInsn = nextInsn.getNext();
/* 1097 */               if (nextInsn == null) {
/*      */                 break label537;
/*      */               }
/* 1100 */               frame = (Frame)frameMap.get(nextInsn);
/* 1101 */               if (frame != null)
/*      */               {
/* 1103 */                 stackSize = frame.getStackSize();
/*      */ 
/* 1105 */                 if ((stackSize == loadStackSize + 1) && (nextInsn.getOpcode() == 181)) {
/* 1106 */                   FieldInsnNode fieldInsn = (FieldInsnNode)nextInsn;
/*      */ 
/* 1109 */                   instructions.insert(nextInsn, new MethodInsnNode(184, mappedSubtype.className, setterName(fieldInsn.name), "(L" + mappedSubtype.className + ";I" + fieldInsn.desc + ")V"));
/*      */ 
/* 1111 */                   instructions.remove(nextInsn);
/*      */ 
/* 1113 */                   break label537;
/* 1114 */                 }if ((stackSize == loadStackSize) && (nextInsn.getOpcode() == 180)) {
/* 1115 */                   FieldInsnNode fieldInsn = (FieldInsnNode)nextInsn;
/*      */ 
/* 1118 */                   instructions.insert(nextInsn, new MethodInsnNode(184, mappedSubtype.className, getterName(fieldInsn.name), "(L" + mappedSubtype.className + ";I)" + fieldInsn.desc));
/*      */ 
/* 1120 */                   instructions.remove(nextInsn);
/*      */ 
/* 1122 */                   break label537;
/* 1123 */                 }if ((stackSize != loadStackSize) || (nextInsn.getOpcode() != 89) || (nextInsn.getNext().getOpcode() != 180))
/*      */                   break;
/* 1125 */                 FieldInsnNode fieldInsn = (FieldInsnNode)nextInsn.getNext();
/*      */ 
/* 1127 */                 MethodInsnNode getter = new MethodInsnNode(184, mappedSubtype.className, getterName(fieldInsn.name), "(L" + mappedSubtype.className + ";I)" + fieldInsn.desc);
/*      */ 
/* 1130 */                 instructions.insert(nextInsn, new InsnNode(92));
/*      */ 
/* 1132 */                 instructions.insert(nextInsn.getNext(), getter);
/*      */ 
/* 1135 */                 instructions.remove(nextInsn);
/* 1136 */                 instructions.remove(fieldInsn);
/*      */ 
/* 1138 */                 nextInsn = getter;
/*      */               }
/*      */             }
/* 1140 */           while (stackSize >= loadStackSize);
/* 1141 */           throw new ClassFormatError("Invalid " + mappedSubtype.className + " view array usage detected: " + getOpcodeName(nextInsn));
/*      */ 
/* 1144 */           label537: instructions.remove(aaLoadInsn);
/*      */ 
/* 1146 */           return i;
/* 1147 */         }if ((stackSize == loadStackSize) && (nextInsn.getOpcode() == 190)) {
/* 1148 */           if ((LWJGLUtil.DEBUG) && (loadInsn.getNext() != nextInsn)) {
/* 1149 */             throw new InternalError();
/*      */           }
/* 1151 */           instructions.remove(nextInsn);
/* 1152 */           loadInsn.var = var;
/* 1153 */           instructions.insert(loadInsn, new MethodInsnNode(182, mappedSubtype.className, "capacity", "()I"));
/*      */ 
/* 1155 */           return i + 1;
/* 1156 */         }if (stackSize < loadStackSize)
/* 1157 */           throw new ClassFormatError("Invalid " + mappedSubtype.className + " view array usage detected: " + getOpcodeName(nextInsn));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void getClassEnums(Class clazz, Map<Integer, String> map, String[] prefixFilters)
/*      */   {
/*      */     try
/*      */     {
/* 1230 */       label128: for (Field field : clazz.getFields())
/* 1231 */         if ((Modifier.isStatic(field.getModifiers())) && (field.getType() == Integer.TYPE))
/*      */         {
/* 1234 */           for (String filter : prefixFilters) {
/* 1235 */             if (field.getName().startsWith(filter)) {
/*      */               break label128;
/*      */             }
/*      */           }
/* 1239 */           if (map.put((Integer)field.get(null), field.getName()) != null)
/* 1240 */             throw new IllegalStateException();
/*      */         }
/*      */     } catch (Exception e) {
/* 1243 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   static String getOpcodeName(AbstractInsnNode insn) {
/* 1248 */     String op = (String)OPCODE_TO_NAME.get(Integer.valueOf(insn.getOpcode()));
/* 1249 */     return (String)INSNTYPE_TO_NAME.get(Integer.valueOf(insn.getType())) + ": " + insn.getOpcode() + (op == null ? "" : new StringBuilder().append(" [").append((String)OPCODE_TO_NAME.get(Integer.valueOf(insn.getOpcode()))).append("]").toString());
/*      */   }
/*      */ 
/*      */   static String jvmClassName(Class<?> type) {
/* 1253 */     return type.getName().replace('.', '/');
/*      */   }
/*      */ 
/*      */   static String getterName(String fieldName) {
/* 1257 */     return "get$" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) + "$LWJGL";
/*      */   }
/*      */ 
/*      */   static String setterName(String fieldName) {
/* 1261 */     return "set$" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1) + "$LWJGL";
/*      */   }
/*      */ 
/*      */   private static void checkInsnAfterIsArray(AbstractInsnNode instruction, int opcode) {
/* 1265 */     if (instruction == null) {
/* 1266 */       throw new ClassFormatError("Unexpected end of instructions after .asArray() method.");
/*      */     }
/* 1268 */     if (instruction.getOpcode() != opcode)
/* 1269 */       throw new ClassFormatError("The result of .asArray() must be stored to a local variable. Found: " + getOpcodeName(instruction));
/*      */   }
/*      */ 
/*      */   static AbstractInsnNode getIntNode(int value) {
/* 1273 */     if ((value <= 5) && (-1 <= value)) {
/* 1274 */       return new InsnNode(2 + value + 1);
/*      */     }
/* 1276 */     if ((value >= -128) && (value <= 127)) {
/* 1277 */       return new IntInsnNode(16, value);
/*      */     }
/* 1279 */     if ((value >= -32768) && (value <= 32767)) {
/* 1280 */       return new IntInsnNode(17, value);
/*      */     }
/* 1282 */     return new LdcInsnNode(Integer.valueOf(value));
/*      */   }
/*      */ 
/*      */   static void visitIntNode(MethodVisitor mv, int value) {
/* 1286 */     if ((value <= 5) && (-1 <= value))
/* 1287 */       mv.visitInsn(2 + value + 1);
/* 1288 */     else if ((value >= -128) && (value <= 127))
/* 1289 */       mv.visitIntInsn(16, value);
/* 1290 */     else if ((value >= -32768) && (value <= 32767))
/* 1291 */       mv.visitIntInsn(17, value);
/*      */     else
/* 1293 */       mv.visitLdcInsn(Integer.valueOf(value));
/*      */   }
/*      */ 
/*      */   static int replace(InsnList instructions, int i, AbstractInsnNode location, InsnList list)
/*      */   {
/* 1298 */     int size = list.size();
/*      */ 
/* 1300 */     instructions.insert(location, list);
/* 1301 */     instructions.remove(location);
/*      */ 
/* 1303 */     return i + (size - 1);
/*      */   }
/*      */ 
/*      */   private static void throwAccessErrorOnReadOnlyField(String className, String fieldName) {
/* 1307 */     throw new IllegalAccessError("The " + className + "." + fieldName + " field is final.");
/*      */   }
/*      */ 
/*      */   private static void printBytecode(byte[] bytecode) {
/* 1311 */     StringWriter sw = new StringWriter();
/* 1312 */     ClassVisitor tracer = new TraceClassVisitor(new ClassWriter(0), new PrintWriter(sw));
/* 1313 */     new ClassReader(bytecode).accept(tracer, 0);
/* 1314 */     String dump = sw.toString();
/*      */ 
/* 1316 */     LWJGLUtil.log(dump);
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*   69 */     PRINT_ACTIVITY = (LWJGLUtil.DEBUG) && (LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.PrintActivity"));
/*   70 */     PRINT_TIMING = (PRINT_ACTIVITY) && (LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.PrintTiming"));
/*   71 */     PRINT_BYTECODE = (LWJGLUtil.DEBUG) && (LWJGLUtil.getPrivilegedBoolean("org.lwjgl.util.mapped.PrintBytecode"));
/*      */ 
/*   75 */     MAPPED_OBJECT_JVM = jvmClassName(MappedObject.class);
/*   76 */     MAPPED_HELPER_JVM = jvmClassName(MappedHelper.class);
/*      */ 
/*   78 */     MAPPEDSET_PREFIX = jvmClassName(MappedSet.class);
/*   79 */     MAPPED_SET2_JVM = jvmClassName(MappedSet2.class);
/*   80 */     MAPPED_SET3_JVM = jvmClassName(MappedSet3.class);
/*   81 */     MAPPED_SET4_JVM = jvmClassName(MappedSet4.class);
/*      */ 
/*   83 */     CACHE_LINE_PAD_JVM = "L" + jvmClassName(CacheLinePad.class) + ";";
/*      */ 
/*   95 */     OPCODE_TO_NAME = new HashMap();
/*   96 */     INSNTYPE_TO_NAME = new HashMap();
/*      */ 
/*  101 */     getClassEnums(Opcodes.class, OPCODE_TO_NAME, new String[] { "V1_", "ACC_", "T_", "F_", "MH_" });
/*  102 */     getClassEnums(AbstractInsnNode.class, INSNTYPE_TO_NAME, new String[0]);
/*      */ 
/*  104 */     className_to_subtype = new HashMap();
/*      */ 
/*  121 */     className_to_subtype.put(MAPPED_OBJECT_JVM, new MappedSubtypeInfo(MAPPED_OBJECT_JVM, null, -1, -1, -1, false));
/*      */ 
/*  124 */     String vmName = System.getProperty("java.vm.name");
/*  125 */     if ((vmName != null) && (!vmName.contains("Server")))
/*  126 */       System.err.println("Warning: " + MappedObject.class.getSimpleName() + "s have inferiour performance on Client VMs, please consider switching to a Server VM.");
/*      */   }
/*      */ 
/*      */   private static class MappedSubtypeInfo
/*      */   {
/*      */     final String className;
/*      */     final int sizeof;
/*      */     final int sizeof_shift;
/*      */     final int align;
/*      */     final int padding;
/*      */     final boolean cacheLinePadded;
/*      */     final Map<String, MappedObjectTransformer.FieldInfo> fields;
/*      */ 
/*      */     MappedSubtypeInfo(String className, Map<String, MappedObjectTransformer.FieldInfo> fields, int sizeof, int align, int padding, boolean cacheLinePadded)
/*      */     {
/* 1198 */       this.className = className;
/*      */ 
/* 1200 */       this.sizeof = sizeof;
/* 1201 */       if ((sizeof - 1 & sizeof) == 0)
/* 1202 */         this.sizeof_shift = getPoT(sizeof);
/*      */       else
/* 1204 */         this.sizeof_shift = 0;
/* 1205 */       this.align = align;
/* 1206 */       this.padding = padding;
/* 1207 */       this.cacheLinePadded = cacheLinePadded;
/*      */ 
/* 1209 */       this.fields = fields;
/*      */     }
/*      */ 
/*      */     private static int getPoT(int value) {
/* 1213 */       int pot = -1;
/* 1214 */       while (value > 0) {
/* 1215 */         pot++;
/* 1216 */         value >>= 1;
/*      */       }
/* 1218 */       return pot;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class FieldInfo
/*      */   {
/*      */     final long offset;
/*      */     final long length;
/*      */     final long lengthPadded;
/*      */     final Type type;
/*      */     final boolean isVolatile;
/*      */     final boolean isPointer;
/*      */ 
/*      */     FieldInfo(long offset, long length, long lengthPadded, Type type, boolean isVolatile, boolean isPointer)
/*      */     {
/* 1171 */       this.offset = offset;
/* 1172 */       this.length = length;
/* 1173 */       this.lengthPadded = lengthPadded;
/* 1174 */       this.type = type;
/* 1175 */       this.isVolatile = isVolatile;
/* 1176 */       this.isPointer = isPointer;
/*      */     }
/*      */ 
/*      */     String getAccessType() {
/* 1180 */       return this.type.getDescriptor().toLowerCase() + (this.isVolatile ? "v" : "");
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class TransformationAdapter extends ClassAdapter
/*      */   {
/*      */     final String className;
/*      */     boolean transformed;
/*      */ 
/*      */     TransformationAdapter(ClassVisitor cv, String className)
/*      */     {
/*  503 */       super();
/*  504 */       this.className = className;
/*      */     }
/*      */ 
/*      */     public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
/*      */     {
/*  510 */       MappedObjectTransformer.MappedSubtypeInfo mappedSubtype = (MappedObjectTransformer.MappedSubtypeInfo)MappedObjectTransformer.className_to_subtype.get(this.className);
/*  511 */       if ((mappedSubtype != null) && (mappedSubtype.fields.containsKey(name))) {
/*  512 */         if (MappedObjectTransformer.PRINT_ACTIVITY)
/*  513 */           LWJGLUtil.log(MappedObjectTransformer.class.getSimpleName() + ": discarding field: " + this.className + "." + name + ":" + desc);
/*  514 */         return null;
/*      */       }
/*      */ 
/*  517 */       if ((access & 0x8) == 0) {
/*  518 */         return new FieldNode(access, name, desc, signature, value) {
/*      */           public void visitEnd() {
/*  520 */             if (this.visibleAnnotations == null) {
/*  521 */               accept(MappedObjectTransformer.TransformationAdapter.this.cv);
/*  522 */               return;
/*      */             }
/*      */ 
/*  525 */             boolean before = false;
/*  526 */             boolean after = false;
/*  527 */             int byteLength = 0;
/*  528 */             for (AnnotationNode pad : this.visibleAnnotations) {
/*  529 */               if (MappedObjectTransformer.CACHE_LINE_PAD_JVM.equals(pad.desc)) {
/*  530 */                 if (("J".equals(this.desc)) || ("D".equals(this.desc)))
/*  531 */                   byteLength = 8;
/*  532 */                 else if (("I".equals(this.desc)) || ("F".equals(this.desc)))
/*  533 */                   byteLength = 4;
/*  534 */                 else if (("S".equals(this.desc)) || ("C".equals(this.desc)))
/*  535 */                   byteLength = 2;
/*  536 */                 else if (("B".equals(this.desc)) || ("Z".equals(this.desc)))
/*  537 */                   byteLength = 1;
/*      */                 else {
/*  539 */                   throw new ClassFormatError("The @CacheLinePad annotation cannot be used on non-primitive fields: " + MappedObjectTransformer.TransformationAdapter.this.className + "." + this.name);
/*      */                 }
/*  541 */                 MappedObjectTransformer.TransformationAdapter.this.transformed = true;
/*      */ 
/*  543 */                 after = true;
/*  544 */                 if (pad.values == null) break;
/*  545 */                 for (int i = 0; i < pad.values.size(); i += 2) {
/*  546 */                   boolean value = pad.values.get(i + 1).equals(Boolean.TRUE);
/*  547 */                   if ("before".equals(pad.values.get(i)))
/*  548 */                     before = value;
/*      */                   else
/*  550 */                     after = value;
/*      */                 }
/*  545 */                 break;
/*      */               }
/*      */ 
/*      */             }
/*      */ 
/*  565 */             if (before) {
/*  566 */               int count = CacheUtil.getCacheLineSize() / byteLength - 1;
/*  567 */               for (int i = count; i >= 1; i--) {
/*  568 */                 MappedObjectTransformer.TransformationAdapter.this.cv.visitField(this.access | 0x1 | 0x1000, this.name + "$PAD_" + i, this.desc, this.signature, null);
/*      */               }
/*      */             }
/*  571 */             accept(MappedObjectTransformer.TransformationAdapter.this.cv);
/*      */ 
/*  573 */             if (after) {
/*  574 */               int count = CacheUtil.getCacheLineSize() / byteLength - 1;
/*  575 */               for (int i = 1; i <= count; i++)
/*  576 */                 MappedObjectTransformer.TransformationAdapter.this.cv.visitField(this.access | 0x1 | 0x1000, this.name + "$PAD" + i, this.desc, this.signature, null);
/*      */             }
/*      */           }
/*      */         };
/*      */       }
/*  581 */       return super.visitField(access, name, desc, signature, value);
/*      */     }
/*      */ 
/*      */     public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
/*      */     {
/*  587 */       if ("<init>".equals(name)) {
/*  588 */         MappedObjectTransformer.MappedSubtypeInfo mappedSubtype = (MappedObjectTransformer.MappedSubtypeInfo)MappedObjectTransformer.className_to_subtype.get(this.className);
/*  589 */         if (mappedSubtype != null) {
/*  590 */           if (!"()V".equals(desc)) {
/*  591 */             throw new ClassFormatError(this.className + " can only have a default constructor, found: " + desc);
/*      */           }
/*  593 */           MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
/*  594 */           mv.visitVarInsn(25, 0);
/*  595 */           mv.visitMethodInsn(183, MappedObjectTransformer.MAPPED_OBJECT_JVM, "<init>", "()V");
/*  596 */           mv.visitInsn(177);
/*  597 */           mv.visitMaxs(0, 0);
/*      */ 
/*  600 */           name = "constructView$LWJGL";
/*      */         }
/*      */       }
/*      */ 
/*  604 */       final MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
/*  605 */       return new MethodNode(access, name, desc, signature, exceptions)
/*      */       {
/*      */         boolean needsTransformation;
/*      */ 
/*      */         public void visitMaxs(int a, int b)
/*      */         {
/*      */           try
/*      */           {
/*  614 */             MappedObjectTransformer.is_currently_computing_frames = true;
/*  615 */             super.visitMaxs(a, b);
/*      */           } finally {
/*  617 */             MappedObjectTransformer.is_currently_computing_frames = false;
/*      */           }
/*      */         }
/*      */ 
/*      */         public void visitFieldInsn(int opcode, String owner, String name, String desc)
/*      */         {
/*  623 */           if ((MappedObjectTransformer.className_to_subtype.containsKey(owner)) || (owner.startsWith(MappedObjectTransformer.MAPPEDSET_PREFIX))) {
/*  624 */             this.needsTransformation = true;
/*      */           }
/*  626 */           super.visitFieldInsn(opcode, owner, name, desc);
/*      */         }
/*      */ 
/*      */         public void visitMethodInsn(int opcode, String owner, String name, String desc)
/*      */         {
/*  631 */           if (MappedObjectTransformer.className_to_subtype.containsKey(owner)) {
/*  632 */             this.needsTransformation = true;
/*      */           }
/*  634 */           super.visitMethodInsn(opcode, owner, name, desc);
/*      */         }
/*      */ 
/*      */         public void visitEnd()
/*      */         {
/*  639 */           if (this.needsTransformation)
/*      */           {
/*  641 */             MappedObjectTransformer.TransformationAdapter.this.transformed = true;
/*      */             try {
/*  643 */               transformMethod(analyse());
/*      */             } catch (Exception e) {
/*  645 */               throw new RuntimeException(e);
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*  650 */           accept(mv);
/*      */         }
/*      */ 
/*      */         private Frame<BasicValue>[] analyse() throws AnalyzerException {
/*  654 */           Analyzer a = new Analyzer(new SimpleVerifier());
/*  655 */           a.analyze(MappedObjectTransformer.TransformationAdapter.this.className, this);
/*  656 */           return a.getFrames();
/*      */         }
/*      */ 
/*      */         private void transformMethod(Frame<BasicValue>[] frames) {
/*  660 */           InsnList instructions = this.instructions;
/*      */ 
/*  662 */           Map arrayVars = new HashMap();
/*      */ 
/*  669 */           Map frameMap = new HashMap();
/*  670 */           for (int i = 0; i < frames.length; i++) {
/*  671 */             frameMap.put(instructions.get(i), frames[i]);
/*      */           }
/*  673 */           for (int i = 0; i < instructions.size(); i++) {
/*  674 */             AbstractInsnNode instruction = instructions.get(i);
/*      */ 
/*  678 */             switch (instruction.getType()) {
/*      */             case 2:
/*  680 */               if (instruction.getOpcode() == 25) {
/*  681 */                 VarInsnNode varInsn = (VarInsnNode)instruction;
/*  682 */                 MappedObjectTransformer.MappedSubtypeInfo mappedSubtype = (MappedObjectTransformer.MappedSubtypeInfo)arrayVars.get(Integer.valueOf(varInsn.var));
/*  683 */                 if (mappedSubtype != null)
/*  684 */                   i = MappedObjectTransformer.transformArrayAccess(instructions, i, frameMap, varInsn, mappedSubtype, varInsn.var); 
/*      */               }
/*  685 */               break;
/*      */             case 4:
/*  688 */               FieldInsnNode fieldInsn = (FieldInsnNode)instruction;
/*      */ 
/*  690 */               InsnList list = MappedObjectTransformer.transformFieldAccess(fieldInsn);
/*  691 */               if (list != null)
/*  692 */                 i = MappedObjectTransformer.replace(instructions, i, instruction, list); break;
/*      */             case 5:
/*  696 */               MethodInsnNode methodInsn = (MethodInsnNode)instruction;
/*  697 */               MappedObjectTransformer.MappedSubtypeInfo mappedType = (MappedObjectTransformer.MappedSubtypeInfo)MappedObjectTransformer.className_to_subtype.get(methodInsn.owner);
/*  698 */               if (mappedType != null)
/*  699 */                 i = MappedObjectTransformer.transformMethodCall(instructions, i, frameMap, methodInsn, mappedType, arrayVars);
/*      */               break;
/*      */             case 3:
/*      */             }
/*      */           }
/*      */         }
/*      */       };
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedObjectTransformer
 * JD-Core Version:    0.6.2
 */