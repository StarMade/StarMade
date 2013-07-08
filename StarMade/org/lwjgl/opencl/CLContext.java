package org.lwjgl.opencl;

import java.nio.IntBuffer;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.opencl.api.CLImageFormat;
import org.lwjgl.opencl.api.Filter;
import org.lwjgl.opengl.Drawable;

public final class CLContext
  extends CLObjectChild<CLPlatform>
{
  private static final CLContextUtil util = (CLContextUtil)CLPlatform.getInfoUtilInstance(CLContext.class, "CL_CONTEXT_UTIL");
  private final CLObjectRegistry<CLCommandQueue> clCommandQueues;
  private final CLObjectRegistry<CLMem> clMems;
  private final CLObjectRegistry<CLSampler> clSamplers;
  private final CLObjectRegistry<CLProgram> clPrograms;
  private final CLObjectRegistry<CLEvent> clEvents;
  private long contextCallback;
  private long printfCallback;
  
  CLContext(long pointer, CLPlatform platform)
  {
    super(pointer, platform);
    if (isValid())
    {
      this.clCommandQueues = new CLObjectRegistry();
      this.clMems = new CLObjectRegistry();
      this.clSamplers = new CLObjectRegistry();
      this.clPrograms = new CLObjectRegistry();
      this.clEvents = new CLObjectRegistry();
    }
    else
    {
      this.clCommandQueues = null;
      this.clMems = null;
      this.clSamplers = null;
      this.clPrograms = null;
      this.clEvents = null;
    }
  }
  
  public CLCommandQueue getCLCommandQueue(long local_id)
  {
    return (CLCommandQueue)this.clCommandQueues.getObject(local_id);
  }
  
  public CLMem getCLMem(long local_id)
  {
    return (CLMem)this.clMems.getObject(local_id);
  }
  
  public CLSampler getCLSampler(long local_id)
  {
    return (CLSampler)this.clSamplers.getObject(local_id);
  }
  
  public CLProgram getCLProgram(long local_id)
  {
    return (CLProgram)this.clPrograms.getObject(local_id);
  }
  
  public CLEvent getCLEvent(long local_id)
  {
    return (CLEvent)this.clEvents.getObject(local_id);
  }
  
  public static CLContext create(CLPlatform platform, List<CLDevice> devices, IntBuffer errcode_ret)
    throws LWJGLException
  {
    return create(platform, devices, null, null, errcode_ret);
  }
  
  public static CLContext create(CLPlatform platform, List<CLDevice> devices, CLContextCallback pfn_notify, IntBuffer errcode_ret)
    throws LWJGLException
  {
    return create(platform, devices, pfn_notify, null, errcode_ret);
  }
  
  public static CLContext create(CLPlatform platform, List<CLDevice> devices, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret)
    throws LWJGLException
  {
    return util.create(platform, devices, pfn_notify, share_drawable, errcode_ret);
  }
  
  public static CLContext createFromType(CLPlatform platform, long device_type, IntBuffer errcode_ret)
    throws LWJGLException
  {
    return util.createFromType(platform, device_type, null, null, errcode_ret);
  }
  
  public static CLContext createFromType(CLPlatform platform, long device_type, CLContextCallback pfn_notify, IntBuffer errcode_ret)
    throws LWJGLException
  {
    return util.createFromType(platform, device_type, pfn_notify, null, errcode_ret);
  }
  
  public static CLContext createFromType(CLPlatform platform, long device_type, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret)
    throws LWJGLException
  {
    return util.createFromType(platform, device_type, pfn_notify, share_drawable, errcode_ret);
  }
  
  public int getInfoInt(int param_name)
  {
    return util.getInfoInt(this, param_name);
  }
  
  public List<CLDevice> getInfoDevices()
  {
    return util.getInfoDevices(this);
  }
  
  public List<CLImageFormat> getSupportedImageFormats(long flags, int image_type)
  {
    return getSupportedImageFormats(flags, image_type, null);
  }
  
  public List<CLImageFormat> getSupportedImageFormats(long flags, int image_type, Filter<CLImageFormat> filter)
  {
    return util.getSupportedImageFormats(this, flags, image_type, filter);
  }
  
  CLObjectRegistry<CLCommandQueue> getCLCommandQueueRegistry()
  {
    return this.clCommandQueues;
  }
  
  CLObjectRegistry<CLMem> getCLMemRegistry()
  {
    return this.clMems;
  }
  
  CLObjectRegistry<CLSampler> getCLSamplerRegistry()
  {
    return this.clSamplers;
  }
  
  CLObjectRegistry<CLProgram> getCLProgramRegistry()
  {
    return this.clPrograms;
  }
  
  CLObjectRegistry<CLEvent> getCLEventRegistry()
  {
    return this.clEvents;
  }
  
  private boolean checkCallback(long callback, int result)
  {
    if ((result == 0) && ((callback == 0L) || (isValid()))) {
      return true;
    }
    if (callback != 0L) {
      CallbackUtil.deleteGlobalRef(callback);
    }
    return false;
  }
  
  void setContextCallback(long callback)
  {
    if (checkCallback(callback, 0)) {
      this.contextCallback = callback;
    }
  }
  
  void setPrintfCallback(long callback, int result)
  {
    if (checkCallback(callback, result)) {
      this.printfCallback = callback;
    }
  }
  
  void releaseImpl()
  {
    if (release() > 0) {
      return;
    }
    if (this.contextCallback != 0L) {
      CallbackUtil.deleteGlobalRef(this.contextCallback);
    }
    if (this.printfCallback != 0L) {
      CallbackUtil.deleteGlobalRef(this.printfCallback);
    }
  }
  
  static abstract interface CLContextUtil
    extends InfoUtil<CLContext>
  {
    public abstract List<CLDevice> getInfoDevices(CLContext paramCLContext);
    
    public abstract CLContext create(CLPlatform paramCLPlatform, List<CLDevice> paramList, CLContextCallback paramCLContextCallback, Drawable paramDrawable, IntBuffer paramIntBuffer)
      throws LWJGLException;
    
    public abstract CLContext createFromType(CLPlatform paramCLPlatform, long paramLong, CLContextCallback paramCLContextCallback, Drawable paramDrawable, IntBuffer paramIntBuffer)
      throws LWJGLException;
    
    public abstract List<CLImageFormat> getSupportedImageFormats(CLContext paramCLContext, long paramLong, int paramInt, Filter<CLImageFormat> paramFilter);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */