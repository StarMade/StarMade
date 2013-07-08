package org.lwjgl.opencl;

public final class CLEvent
  extends CLObjectChild<CLContext>
{
  private static final CLEventUtil util = (CLEventUtil)CLPlatform.getInfoUtilInstance(CLEvent.class, "CL_EVENT_UTIL");
  private final CLCommandQueue queue;
  
  CLEvent(long pointer, CLContext context)
  {
    this(pointer, context, null);
  }
  
  CLEvent(long pointer, CLCommandQueue queue)
  {
    this(pointer, (CLContext)queue.getParent(), queue);
  }
  
  CLEvent(long pointer, CLContext context, CLCommandQueue queue)
  {
    super(pointer, context);
    if (isValid())
    {
      this.queue = queue;
      if (queue == null) {
        context.getCLEventRegistry().registerObject(this);
      } else {
        queue.getCLEventRegistry().registerObject(this);
      }
    }
    else
    {
      this.queue = null;
    }
  }
  
  public CLCommandQueue getCLCommandQueue()
  {
    return this.queue;
  }
  
  public int getInfoInt(int param_name)
  {
    return util.getInfoInt(this, param_name);
  }
  
  public long getProfilingInfoLong(int param_name)
  {
    return util.getProfilingInfoLong(this, param_name);
  }
  
  CLObjectRegistry<CLEvent> getParentRegistry()
  {
    if (this.queue == null) {
      return ((CLContext)getParent()).getCLEventRegistry();
    }
    return this.queue.getCLEventRegistry();
  }
  
  int release()
  {
    try
    {
      int i = super.release();
      return i;
    }
    finally
    {
      if (!isValid()) {
        if (this.queue == null) {
          ((CLContext)getParent()).getCLEventRegistry().unregisterObject(this);
        } else {
          this.queue.getCLEventRegistry().unregisterObject(this);
        }
      }
    }
  }
  
  static abstract interface CLEventUtil
    extends InfoUtil<CLEvent>
  {
    public abstract long getProfilingInfoLong(CLEvent paramCLEvent, int paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLEvent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */