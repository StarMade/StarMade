package org.lwjgl.openal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public final class ALCdevice
{
  final long device;
  private boolean valid;
  private final HashMap<Long, ALCcontext> contexts = new HashMap();
  
  ALCdevice(long device)
  {
    this.device = device;
    this.valid = true;
  }
  
  public boolean equals(Object device)
  {
    if ((device instanceof ALCdevice)) {
      return ((ALCdevice)device).device == this.device;
    }
    return super.equals(device);
  }
  
  void addContext(ALCcontext context)
  {
    synchronized (this.contexts)
    {
      this.contexts.put(Long.valueOf(context.context), context);
    }
  }
  
  void removeContext(ALCcontext context)
  {
    synchronized (this.contexts)
    {
      this.contexts.remove(Long.valueOf(context.context));
    }
  }
  
  void setInvalid()
  {
    this.valid = false;
    synchronized (this.contexts)
    {
      Iterator local_i$ = this.contexts.values().iterator();
      while (local_i$.hasNext())
      {
        ALCcontext context = (ALCcontext)local_i$.next();
        context.setInvalid();
      }
    }
    this.contexts.clear();
  }
  
  public boolean isValid()
  {
    return this.valid;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.openal.ALCdevice
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */