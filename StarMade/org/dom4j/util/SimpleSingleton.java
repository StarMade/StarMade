package org.dom4j.util;

public class SimpleSingleton
  implements SingletonStrategy
{
  private String singletonClassName = null;
  private Object singletonInstance = null;
  
  public Object instance()
  {
    return this.singletonInstance;
  }
  
  public void reset()
  {
    if (this.singletonClassName != null)
    {
      Class clazz = null;
      try
      {
        clazz = Thread.currentThread().getContextClassLoader().loadClass(this.singletonClassName);
        this.singletonInstance = clazz.newInstance();
      }
      catch (Exception ignore)
      {
        try
        {
          clazz = Class.forName(this.singletonClassName);
          this.singletonInstance = clazz.newInstance();
        }
        catch (Exception ignore2) {}
      }
    }
  }
  
  public void setSingletonClassName(String singletonClassName)
  {
    this.singletonClassName = singletonClassName;
    reset();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.SimpleSingleton
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */