package org.lwjgl.opencl;

import org.lwjgl.LWJGLUtil;

class CLObjectRegistry<T extends CLObjectChild>
{
  private FastLongMap<T> registry;
  
  final boolean isEmpty()
  {
    return (this.registry == null) || (this.registry.isEmpty());
  }
  
  final T getObject(long local_id)
  {
    return this.registry == null ? null : (CLObjectChild)this.registry.get(local_id);
  }
  
  final boolean hasObject(long local_id)
  {
    return (this.registry != null) && (this.registry.containsKey(local_id));
  }
  
  final Iterable<FastLongMap.Entry<T>> getAll()
  {
    return this.registry;
  }
  
  void registerObject(T object)
  {
    FastLongMap<T> map = getMap();
    Long key = Long.valueOf(object.getPointer());
    if ((LWJGLUtil.DEBUG) && (map.containsKey(key.longValue()))) {
      throw new IllegalStateException("Duplicate object found: " + object.getClass() + " - " + key);
    }
    getMap().put(object.getPointer(), object);
  }
  
  void unregisterObject(T object)
  {
    getMap().remove(object.getPointerUnsafe());
  }
  
  private FastLongMap<T> getMap()
  {
    if (this.registry == null) {
      this.registry = new FastLongMap();
    }
    return this.registry;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLObjectRegistry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */