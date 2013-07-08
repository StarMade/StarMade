package org.lwjgl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import sun.misc.Unsafe;
import sun.reflect.FieldAccessor;

final class MemoryUtilSun
{
  private static class AccessorReflectFast
    implements MemoryUtil.Accessor
  {
    private final FieldAccessor addressAccessor;
    
    AccessorReflectFast()
    {
      Field address;
      try
      {
        address = MemoryUtil.getAddressField();
      }
      catch (NoSuchFieldException local_e)
      {
        throw new UnsupportedOperationException(local_e);
      }
      address.setAccessible(true);
      try
      {
        Method local_e = Field.class.getDeclaredMethod("acquireFieldAccessor", new Class[] { Boolean.TYPE });
        local_e.setAccessible(true);
        this.addressAccessor = ((FieldAccessor)local_e.invoke(address, new Object[] { Boolean.valueOf(true) }));
      }
      catch (Exception local_e)
      {
        throw new UnsupportedOperationException(local_e);
      }
    }
    
    public long getAddress(Buffer buffer)
    {
      return this.addressAccessor.getLong(buffer);
    }
  }
  
  private static class AccessorUnsafe
    implements MemoryUtil.Accessor
  {
    private final Unsafe unsafe;
    private final long address;
    
    AccessorUnsafe()
    {
      try
      {
        this.unsafe = getUnsafeInstance();
        this.address = this.unsafe.objectFieldOffset(MemoryUtil.getAddressField());
      }
      catch (Exception local_e)
      {
        throw new UnsupportedOperationException(local_e);
      }
    }
    
    public long getAddress(Buffer buffer)
    {
      return this.unsafe.getLong(buffer, this.address);
    }
    
    private static Unsafe getUnsafeInstance()
    {
      Field[] fields = Unsafe.class.getDeclaredFields();
      for (Field field : fields) {
        if (field.getType().equals(Unsafe.class))
        {
          int modifiers = field.getModifiers();
          if ((Modifier.isStatic(modifiers)) && (Modifier.isFinal(modifiers)))
          {
            field.setAccessible(true);
            try
            {
              return (Unsafe)field.get(null);
            }
            catch (IllegalAccessException local_e) {}
          }
        }
      }
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.MemoryUtilSun
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */