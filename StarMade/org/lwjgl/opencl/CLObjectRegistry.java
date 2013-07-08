/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.LWJGLUtil;
/*  4:   */
/* 12:   */class CLObjectRegistry<T extends CLObjectChild>
/* 13:   */{
/* 14:   */  private FastLongMap<T> registry;
/* 15:   */  
/* 16:   */  final boolean isEmpty()
/* 17:   */  {
/* 18:18 */    return (this.registry == null) || (this.registry.isEmpty());
/* 19:   */  }
/* 20:   */  
/* 21:   */  final T getObject(long id) {
/* 22:22 */    return this.registry == null ? null : (CLObjectChild)this.registry.get(id);
/* 23:   */  }
/* 24:   */  
/* 25:   */  final boolean hasObject(long id) {
/* 26:26 */    return (this.registry != null) && (this.registry.containsKey(id));
/* 27:   */  }
/* 28:   */  
/* 29:   */  final Iterable<FastLongMap.Entry<T>> getAll() {
/* 30:30 */    return this.registry;
/* 31:   */  }
/* 32:   */  
/* 33:   */  void registerObject(T object) {
/* 34:34 */    FastLongMap<T> map = getMap();
/* 35:35 */    Long key = Long.valueOf(object.getPointer());
/* 36:   */    
/* 37:37 */    if ((LWJGLUtil.DEBUG) && (map.containsKey(key.longValue()))) {
/* 38:38 */      throw new IllegalStateException("Duplicate object found: " + object.getClass() + " - " + key);
/* 39:   */    }
/* 40:40 */    getMap().put(object.getPointer(), object);
/* 41:   */  }
/* 42:   */  
/* 43:   */  void unregisterObject(T object) {
/* 44:44 */    getMap().remove(object.getPointerUnsafe());
/* 45:   */  }
/* 46:   */  
/* 47:   */  private FastLongMap<T> getMap() {
/* 48:48 */    if (this.registry == null) {
/* 49:49 */      this.registry = new FastLongMap();
/* 50:   */    }
/* 51:51 */    return this.registry;
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLObjectRegistry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */