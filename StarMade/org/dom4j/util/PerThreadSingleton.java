package org.dom4j.util;

import java.lang.ref.WeakReference;

public class PerThreadSingleton
  implements SingletonStrategy
{
  private String singletonClassName = null;
  private ThreadLocal perThreadCache = new ThreadLocal();
  
  public void reset()
  {
    this.perThreadCache = new ThreadLocal();
  }
  
  public Object instance()
  {
    Object singletonInstancePerThread = null;
    WeakReference ref = (WeakReference)this.perThreadCache.get();
    if ((ref == null) || (ref.get() == null))
    {
      Class clazz = null;
      try
      {
        clazz = Thread.currentThread().getContextClassLoader().loadClass(this.singletonClassName);
        singletonInstancePerThread = clazz.newInstance();
      }
      catch (Exception ignore)
      {
        try
        {
          clazz = Class.forName(this.singletonClassName);
          singletonInstancePerThread = clazz.newInstance();
        }
        catch (Exception ignore2) {}
      }
      this.perThreadCache.set(new WeakReference(singletonInstancePerThread));
    }
    else
    {
      singletonInstancePerThread = ref.get();
    }
    return singletonInstancePerThread;
  }
  
  public void setSingletonClassName(String singletonClassName)
  {
    this.singletonClassName = singletonClassName;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.PerThreadSingleton
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */