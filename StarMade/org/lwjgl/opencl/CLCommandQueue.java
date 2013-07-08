/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import org.lwjgl.PointerBuffer;
/*   4:    */
/*  40:    */public final class CLCommandQueue
/*  41:    */  extends CLObjectChild<CLContext>
/*  42:    */{
/*  43: 43 */  private static final InfoUtil<CLCommandQueue> util = CLPlatform.getInfoUtilInstance(CLCommandQueue.class, "CL_COMMAND_QUEUE_UTIL");
/*  44:    */  
/*  45:    */  private final CLDevice device;
/*  46:    */  private final CLObjectRegistry<CLEvent> clEvents;
/*  47:    */  
/*  48:    */  CLCommandQueue(long pointer, CLContext context, CLDevice device)
/*  49:    */  {
/*  50: 50 */    super(pointer, context);
/*  51: 51 */    if (isValid()) {
/*  52: 52 */      this.device = device;
/*  53: 53 */      this.clEvents = new CLObjectRegistry();
/*  54: 54 */      context.getCLCommandQueueRegistry().registerObject(this);
/*  55:    */    } else {
/*  56: 56 */      this.device = null;
/*  57: 57 */      this.clEvents = null;
/*  58:    */    }
/*  59:    */  }
/*  60:    */  
/*  61:    */  public CLDevice getCLDevice() {
/*  62: 62 */    return this.device;
/*  63:    */  }
/*  64:    */  
/*  71:    */  public CLEvent getCLEvent(long id)
/*  72:    */  {
/*  73: 73 */    return (CLEvent)this.clEvents.getObject(id);
/*  74:    */  }
/*  75:    */  
/*  84:    */  public int getInfoInt(int param_name)
/*  85:    */  {
/*  86: 86 */    return util.getInfoInt(this, param_name);
/*  87:    */  }
/*  88:    */  
/*  89:    */  CLObjectRegistry<CLEvent> getCLEventRegistry()
/*  90:    */  {
/*  91: 91 */    return this.clEvents;
/*  92:    */  }
/*  93:    */  
/*  97:    */  void registerCLEvent(PointerBuffer event)
/*  98:    */  {
/*  99: 99 */    if (event != null)
/* 100:100 */      new CLEvent(event.get(event.position()), this);
/* 101:    */  }
/* 102:    */  
/* 103:    */  int release() {
/* 104:    */    try {
/* 105:105 */      return super.release();
/* 106:    */    } finally {
/* 107:107 */      if (!isValid()) {
/* 108:108 */        ((CLContext)getParent()).getCLCommandQueueRegistry().unregisterObject(this);
/* 109:    */      }
/* 110:    */    }
/* 111:    */  }
/* 112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLCommandQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */