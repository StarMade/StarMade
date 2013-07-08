package org.lwjgl.opencl;

import org.lwjgl.PointerBuffer;

public final class CLCommandQueue
  extends CLObjectChild<CLContext>
{
  private static final InfoUtil<CLCommandQueue> util = CLPlatform.getInfoUtilInstance(CLCommandQueue.class, "CL_COMMAND_QUEUE_UTIL");
  private final CLDevice device;
  private final CLObjectRegistry<CLEvent> clEvents;
  
  CLCommandQueue(long pointer, CLContext context, CLDevice device)
  {
    super(pointer, context);
    if (isValid())
    {
      this.device = device;
      this.clEvents = new CLObjectRegistry();
      context.getCLCommandQueueRegistry().registerObject(this);
    }
    else
    {
      this.device = null;
      this.clEvents = null;
    }
  }
  
  public CLDevice getCLDevice()
  {
    return this.device;
  }
  
  public CLEvent getCLEvent(long local_id)
  {
    return (CLEvent)this.clEvents.getObject(local_id);
  }
  
  public int getInfoInt(int param_name)
  {
    return util.getInfoInt(this, param_name);
  }
  
  CLObjectRegistry<CLEvent> getCLEventRegistry()
  {
    return this.clEvents;
  }
  
  void registerCLEvent(PointerBuffer event)
  {
    if (event != null) {
      new CLEvent(event.get(event.position()), this);
    }
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
        ((CLContext)getParent()).getCLCommandQueueRegistry().unregisterObject(this);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLCommandQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */