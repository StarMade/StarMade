/*   1:    */package org.lwjgl.util.mapped;
/*   2:    */
/*   3:    */import java.lang.reflect.Field;
/*   4:    */import java.lang.reflect.Modifier;
/*   5:    */import java.nio.ByteBuffer;
/*   6:    */import java.nio.ByteOrder;
/*   7:    */import sun.misc.Unsafe;
/*   8:    */
/*  46:    */final class MappedObjectUnsafe
/*  47:    */{
/*  48: 48 */  static final Unsafe INSTANCE = ;
/*  49:    */  
/*  50: 50 */  private static final long BUFFER_ADDRESS_OFFSET = getObjectFieldOffset(ByteBuffer.class, "address");
/*  51: 51 */  private static final long BUFFER_CAPACITY_OFFSET = getObjectFieldOffset(ByteBuffer.class, "capacity");
/*  52:    */  
/*  53: 53 */  private static final ByteBuffer global = ByteBuffer.allocateDirect(4096);
/*  54:    */  
/*  55:    */  static ByteBuffer newBuffer(long address, int capacity) {
/*  56: 56 */    if ((address <= 0L) || (capacity < 0)) {
/*  57: 57 */      throw new IllegalStateException("you almost crashed the jvm");
/*  58:    */    }
/*  59: 59 */    ByteBuffer buffer = global.duplicate().order(ByteOrder.nativeOrder());
/*  60: 60 */    INSTANCE.putLong(buffer, BUFFER_ADDRESS_OFFSET, address);
/*  61: 61 */    INSTANCE.putInt(buffer, BUFFER_CAPACITY_OFFSET, capacity);
/*  62: 62 */    buffer.position(0);
/*  63: 63 */    buffer.limit(capacity);
/*  64: 64 */    return buffer;
/*  65:    */  }
/*  66:    */  
/*  67:    */  private static long getObjectFieldOffset(Class<?> type, String fieldName) {
/*  68: 68 */    while (type != null) {
/*  69:    */      try {
/*  70: 70 */        return INSTANCE.objectFieldOffset(type.getDeclaredField(fieldName));
/*  71:    */      } catch (Throwable t) {
/*  72: 72 */        type = type.getSuperclass();
/*  73:    */      }
/*  74:    */    }
/*  75:    */    
/*  76: 76 */    throw new UnsupportedOperationException();
/*  77:    */  }
/*  78:    */  
/*  79:    */  private static Unsafe getUnsafeInstance() {
/*  80: 80 */    Field[] fields = Unsafe.class.getDeclaredFields();
/*  81:    */    
/*  90: 90 */    for (Field field : fields) {
/*  91: 91 */      if (field.getType().equals(Unsafe.class))
/*  92:    */      {
/*  94: 94 */        int modifiers = field.getModifiers();
/*  95: 95 */        if ((Modifier.isStatic(modifiers)) && (Modifier.isFinal(modifiers)))
/*  96:    */        {
/*  98: 98 */          field.setAccessible(true);
/*  99:    */          try {
/* 100:100 */            return (Unsafe)field.get(null);
/* 101:    */          }
/* 102:    */          catch (IllegalAccessException e) {}
/* 103:    */        }
/* 104:    */      }
/* 105:    */    }
/* 106:    */    
/* 107:107 */    throw new UnsupportedOperationException();
/* 108:    */  }
/* 109:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedObjectUnsafe
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */