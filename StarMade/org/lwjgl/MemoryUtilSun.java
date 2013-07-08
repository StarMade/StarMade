/*   1:    */package org.lwjgl;
/*   2:    */
/*   3:    */import java.lang.reflect.Field;
/*   4:    */import java.lang.reflect.Method;
/*   5:    */import java.lang.reflect.Modifier;
/*   6:    */import java.nio.Buffer;
/*   7:    */import sun.misc.Unsafe;
/*   8:    */import sun.reflect.FieldAccessor;
/*   9:    */
/*  50:    */final class MemoryUtilSun
/*  51:    */{
/*  52:    */  private static class AccessorUnsafe
/*  53:    */    implements MemoryUtil.Accessor
/*  54:    */  {
/*  55:    */    private final Unsafe unsafe;
/*  56:    */    private final long address;
/*  57:    */    
/*  58:    */    AccessorUnsafe()
/*  59:    */    {
/*  60:    */      try
/*  61:    */      {
/*  62: 62 */        this.unsafe = getUnsafeInstance();
/*  63: 63 */        this.address = this.unsafe.objectFieldOffset(MemoryUtil.getAddressField());
/*  64:    */      } catch (Exception e) {
/*  65: 65 */        throw new UnsupportedOperationException(e);
/*  66:    */      }
/*  67:    */    }
/*  68:    */    
/*  69:    */    public long getAddress(Buffer buffer) {
/*  70: 70 */      return this.unsafe.getLong(buffer, this.address);
/*  71:    */    }
/*  72:    */    
/*  73:    */    private static Unsafe getUnsafeInstance() {
/*  74: 74 */      Field[] fields = Unsafe.class.getDeclaredFields();
/*  75:    */      
/*  84: 84 */      for (Field field : fields) {
/*  85: 85 */        if (field.getType().equals(Unsafe.class))
/*  86:    */        {
/*  88: 88 */          int modifiers = field.getModifiers();
/*  89: 89 */          if ((Modifier.isStatic(modifiers)) && (Modifier.isFinal(modifiers)))
/*  90:    */          {
/*  92: 92 */            field.setAccessible(true);
/*  93:    */            try {
/*  94: 94 */              return (Unsafe)field.get(null);
/*  95:    */            }
/*  96:    */            catch (IllegalAccessException e) {}
/*  97:    */          }
/*  98:    */        }
/*  99:    */      }
/* 100:    */      
/* 101:101 */      throw new UnsupportedOperationException();
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 105:    */  private static class AccessorReflectFast implements MemoryUtil.Accessor
/* 106:    */  {
/* 107:    */    private final FieldAccessor addressAccessor;
/* 108:    */    
/* 109:    */    AccessorReflectFast()
/* 110:    */    {
/* 111:    */      Field address;
/* 112:    */      try
/* 113:    */      {
/* 114:114 */        address = MemoryUtil.getAddressField();
/* 115:    */      } catch (NoSuchFieldException e) {
/* 116:116 */        throw new UnsupportedOperationException(e);
/* 117:    */      }
/* 118:118 */      address.setAccessible(true);
/* 119:    */      try
/* 120:    */      {
/* 121:121 */        Method m = Field.class.getDeclaredMethod("acquireFieldAccessor", new Class[] { Boolean.TYPE });
/* 122:122 */        m.setAccessible(true);
/* 123:123 */        this.addressAccessor = ((FieldAccessor)m.invoke(address, new Object[] { Boolean.valueOf(true) }));
/* 124:    */      } catch (Exception e) {
/* 125:125 */        throw new UnsupportedOperationException(e);
/* 126:    */      }
/* 127:    */    }
/* 128:    */    
/* 129:    */    public long getAddress(Buffer buffer) {
/* 130:130 */      return this.addressAccessor.getLong(buffer);
/* 131:    */    }
/* 132:    */  }
/* 133:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.MemoryUtilSun
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */