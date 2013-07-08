/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*  38:    */public final class CLEvent
/*  39:    */  extends CLObjectChild<CLContext>
/*  40:    */{
/*  41: 41 */  private static final CLEventUtil util = (CLEventUtil)CLPlatform.getInfoUtilInstance(CLEvent.class, "CL_EVENT_UTIL");
/*  42:    */  private final CLCommandQueue queue;
/*  43:    */  
/*  44:    */  CLEvent(long pointer, CLContext context)
/*  45:    */  {
/*  46: 46 */    this(pointer, context, null);
/*  47:    */  }
/*  48:    */  
/*  49:    */  CLEvent(long pointer, CLCommandQueue queue) {
/*  50: 50 */    this(pointer, (CLContext)queue.getParent(), queue);
/*  51:    */  }
/*  52:    */  
/*  53:    */  CLEvent(long pointer, CLContext context, CLCommandQueue queue) {
/*  54: 54 */    super(pointer, context);
/*  55: 55 */    if (isValid()) {
/*  56: 56 */      this.queue = queue;
/*  57: 57 */      if (queue == null) {
/*  58: 58 */        context.getCLEventRegistry().registerObject(this);
/*  59:    */      } else
/*  60: 60 */        queue.getCLEventRegistry().registerObject(this);
/*  61:    */    } else {
/*  62: 62 */      this.queue = null;
/*  63:    */    }
/*  64:    */  }
/*  65:    */  
/*  70:    */  public CLCommandQueue getCLCommandQueue()
/*  71:    */  {
/*  72: 72 */    return this.queue;
/*  73:    */  }
/*  74:    */  
/*  83:    */  public int getInfoInt(int param_name)
/*  84:    */  {
/*  85: 85 */    return util.getInfoInt(this, param_name);
/*  86:    */  }
/*  87:    */  
/*  97:    */  public long getProfilingInfoLong(int param_name)
/*  98:    */  {
/*  99: 99 */    return util.getProfilingInfoLong(this, param_name);
/* 100:    */  }
/* 101:    */  
/* 110:    */  CLObjectRegistry<CLEvent> getParentRegistry()
/* 111:    */  {
/* 112:112 */    if (this.queue == null) {
/* 113:113 */      return ((CLContext)getParent()).getCLEventRegistry();
/* 114:    */    }
/* 115:115 */    return this.queue.getCLEventRegistry();
/* 116:    */  }
/* 117:    */  
/* 118:    */  int release() {
/* 119:    */    try {
/* 120:120 */      return super.release();
/* 121:    */    } finally {
/* 122:122 */      if (!isValid()) {
/* 123:123 */        if (this.queue == null) {
/* 124:124 */          ((CLContext)getParent()).getCLEventRegistry().unregisterObject(this);
/* 125:    */        } else {
/* 126:126 */          this.queue.getCLEventRegistry().unregisterObject(this);
/* 127:    */        }
/* 128:    */      }
/* 129:    */    }
/* 130:    */  }
/* 131:    */  
/* 132:    */  static abstract interface CLEventUtil
/* 133:    */    extends InfoUtil<CLEvent>
/* 134:    */  {
/* 135:    */    public abstract long getProfilingInfoLong(CLEvent paramCLEvent, int paramInt);
/* 136:    */  }
/* 137:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLEvent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */