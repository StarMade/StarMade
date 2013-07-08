package org.lwjgl.opencl;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.List;
import org.lwjgl.PointerBuffer;
import org.lwjgl.opencl.api.Filter;

public final class CLPlatform
  extends CLObject
{
  private static final CLPlatformUtil util = (CLPlatformUtil)getInfoUtilInstance(CLPlatform.class, "CL_PLATFORM_UTIL");
  private static final FastLongMap<CLPlatform> clPlatforms = new FastLongMap();
  private final CLObjectRegistry<CLDevice> clDevices;
  private Object caps;
  
  CLPlatform(long pointer)
  {
    super(pointer);
    if (isValid())
    {
      clPlatforms.put(pointer, this);
      this.clDevices = new CLObjectRegistry();
    }
    else
    {
      this.clDevices = null;
    }
  }
  
  public static CLPlatform getCLPlatform(long local_id)
  {
    return (CLPlatform)clPlatforms.get(local_id);
  }
  
  public CLDevice getCLDevice(long local_id)
  {
    return (CLDevice)this.clDevices.getObject(local_id);
  }
  
  static <T extends CLObject> InfoUtil<T> getInfoUtilInstance(Class<T> clazz, String fieldName)
  {
    InfoUtil<T> instance = null;
    try
    {
      Class<?> infoUtil = Class.forName("org.lwjgl.opencl.InfoUtilFactory");
      instance = (InfoUtil)infoUtil.getDeclaredField(fieldName).get(null);
    }
    catch (Exception infoUtil) {}
    return instance;
  }
  
  public static List<CLPlatform> getPlatforms()
  {
    return getPlatforms(null);
  }
  
  public static List<CLPlatform> getPlatforms(Filter<CLPlatform> filter)
  {
    return util.getPlatforms(filter);
  }
  
  public String getInfoString(int param_name)
  {
    return util.getInfoString(this, param_name);
  }
  
  public List<CLDevice> getDevices(int device_type)
  {
    return getDevices(device_type, null);
  }
  
  public List<CLDevice> getDevices(int device_type, Filter<CLDevice> filter)
  {
    return util.getDevices(this, device_type, filter);
  }
  
  void setCapabilities(Object caps)
  {
    this.caps = caps;
  }
  
  Object getCapabilities()
  {
    return this.caps;
  }
  
  static void registerCLPlatforms(PointerBuffer platforms, IntBuffer num_platforms)
  {
    if (platforms == null) {
      return;
    }
    int pos = platforms.position();
    int count = Math.min(num_platforms.get(0), platforms.remaining());
    for (int local_i = 0; local_i < count; local_i++)
    {
      long local_id = platforms.get(pos + local_i);
      if (!clPlatforms.containsKey(local_id)) {
        new CLPlatform(local_id);
      }
    }
  }
  
  CLObjectRegistry<CLDevice> getCLDeviceRegistry()
  {
    return this.clDevices;
  }
  
  void registerCLDevices(PointerBuffer devices, IntBuffer num_devices)
  {
    int pos = devices.position();
    int count = Math.min(num_devices.get(num_devices.position()), devices.remaining());
    for (int local_i = 0; local_i < count; local_i++)
    {
      long local_id = devices.get(pos + local_i);
      if (!this.clDevices.hasObject(local_id)) {
        new CLDevice(local_id, this);
      }
    }
  }
  
  void registerCLDevices(ByteBuffer devices, PointerBuffer num_devices)
  {
    int pos = devices.position();
    int count = Math.min((int)num_devices.get(num_devices.position()), devices.remaining()) / PointerBuffer.getPointerSize();
    for (int local_i = 0; local_i < count; local_i++)
    {
      int offset = pos + local_i * PointerBuffer.getPointerSize();
      long local_id = PointerBuffer.is64Bit() ? devices.getLong(offset) : devices.getInt(offset);
      if (!this.clDevices.hasObject(local_id)) {
        new CLDevice(local_id, this);
      }
    }
  }
  
  static abstract interface CLPlatformUtil
    extends InfoUtil<CLPlatform>
  {
    public abstract List<CLPlatform> getPlatforms(Filter<CLPlatform> paramFilter);
    
    public abstract List<CLDevice> getDevices(CLPlatform paramCLPlatform, int paramInt, Filter<CLDevice> paramFilter);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLPlatform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */