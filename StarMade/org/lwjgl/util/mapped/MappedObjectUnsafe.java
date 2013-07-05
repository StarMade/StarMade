/*     */ package org.lwjgl.util.mapped;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import sun.misc.Unsafe;
/*     */ 
/*     */ final class MappedObjectUnsafe
/*     */ {
/*  48 */   static final Unsafe INSTANCE = getUnsafeInstance();
/*     */ 
/*  50 */   private static final long BUFFER_ADDRESS_OFFSET = getObjectFieldOffset(ByteBuffer.class, "address");
/*  51 */   private static final long BUFFER_CAPACITY_OFFSET = getObjectFieldOffset(ByteBuffer.class, "capacity");
/*     */ 
/*  53 */   private static final ByteBuffer global = ByteBuffer.allocateDirect(4096);
/*     */ 
/*     */   static ByteBuffer newBuffer(long address, int capacity) {
/*  56 */     if ((address <= 0L) || (capacity < 0)) {
/*  57 */       throw new IllegalStateException("you almost crashed the jvm");
/*     */     }
/*  59 */     ByteBuffer buffer = global.duplicate().order(ByteOrder.nativeOrder());
/*  60 */     INSTANCE.putLong(buffer, BUFFER_ADDRESS_OFFSET, address);
/*  61 */     INSTANCE.putInt(buffer, BUFFER_CAPACITY_OFFSET, capacity);
/*  62 */     buffer.position(0);
/*  63 */     buffer.limit(capacity);
/*  64 */     return buffer;
/*     */   }
/*     */ 
/*     */   private static long getObjectFieldOffset(Class<?> type, String fieldName) {
/*  68 */     while (type != null) {
/*     */       try {
/*  70 */         return INSTANCE.objectFieldOffset(type.getDeclaredField(fieldName));
/*     */       } catch (Throwable t) {
/*  72 */         type = type.getSuperclass();
/*     */       }
/*     */     }
/*     */ 
/*  76 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   private static Unsafe getUnsafeInstance() {
/*  80 */     Field[] fields = Unsafe.class.getDeclaredFields();
/*     */ 
/*  90 */     for (Field field : fields)
/*  91 */       if (field.getType().equals(Unsafe.class))
/*     */       {
/*  94 */         int modifiers = field.getModifiers();
/*  95 */         if ((Modifier.isStatic(modifiers)) && (Modifier.isFinal(modifiers)))
/*     */         {
/*  98 */           field.setAccessible(true);
/*     */           try {
/* 100 */             return (Unsafe)field.get(null);
/*     */           }
/*     */           catch (IllegalAccessException e)
/*     */           {
/*     */           }
/*     */         }
/*     */       }
/* 107 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedObjectUnsafe
 * JD-Core Version:    0.6.2
 */