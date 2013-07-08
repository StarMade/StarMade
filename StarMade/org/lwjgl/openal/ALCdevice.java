/*   1:    */package org.lwjgl.openal;
/*   2:    */
/*   3:    */import java.util.HashMap;
/*   4:    */
/*  56:    */public final class ALCdevice
/*  57:    */{
/*  58:    */  final long device;
/*  59:    */  private boolean valid;
/*  60: 60 */  private final HashMap<Long, ALCcontext> contexts = new HashMap();
/*  61:    */  
/*  66:    */  ALCdevice(long device)
/*  67:    */  {
/*  68: 68 */    this.device = device;
/*  69: 69 */    this.valid = true;
/*  70:    */  }
/*  71:    */  
/*  74:    */  public boolean equals(Object device)
/*  75:    */  {
/*  76: 76 */    if ((device instanceof ALCdevice)) {
/*  77: 77 */      return ((ALCdevice)device).device == this.device;
/*  78:    */    }
/*  79: 79 */    return super.equals(device);
/*  80:    */  }
/*  81:    */  
/*  86:    */  void addContext(ALCcontext context)
/*  87:    */  {
/*  88: 88 */    synchronized (this.contexts) {
/*  89: 89 */      this.contexts.put(Long.valueOf(context.context), context);
/*  90:    */    }
/*  91:    */  }
/*  92:    */  
/*  97:    */  void removeContext(ALCcontext context)
/*  98:    */  {
/*  99: 99 */    synchronized (this.contexts) {
/* 100:100 */      this.contexts.remove(Long.valueOf(context.context));
/* 101:    */    }
/* 102:    */  }
/* 103:    */  
/* 106:    */  void setInvalid()
/* 107:    */  {
/* 108:108 */    this.valid = false;
/* 109:109 */    synchronized (this.contexts) {
/* 110:110 */      for (ALCcontext context : this.contexts.values())
/* 111:111 */        context.setInvalid();
/* 112:    */    }
/* 113:113 */    this.contexts.clear();
/* 114:    */  }
/* 115:    */  
/* 118:    */  public boolean isValid()
/* 119:    */  {
/* 120:120 */    return this.valid;
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.ALCdevice
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */