/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ 
/*    */ class CLObjectRegistry<T extends CLObjectChild>
/*    */ {
/*    */   private FastLongMap<T> registry;
/*    */ 
/*    */   final boolean isEmpty()
/*    */   {
/* 18 */     return (this.registry == null) || (this.registry.isEmpty());
/*    */   }
/*    */ 
/*    */   final T getObject(long id) {
/* 22 */     return this.registry == null ? null : (CLObjectChild)this.registry.get(id);
/*    */   }
/*    */ 
/*    */   final boolean hasObject(long id) {
/* 26 */     return (this.registry != null) && (this.registry.containsKey(id));
/*    */   }
/*    */ 
/*    */   final Iterable<FastLongMap.Entry<T>> getAll() {
/* 30 */     return this.registry;
/*    */   }
/*    */ 
/*    */   void registerObject(T object) {
/* 34 */     FastLongMap map = getMap();
/* 35 */     Long key = Long.valueOf(object.getPointer());
/*    */ 
/* 37 */     if ((LWJGLUtil.DEBUG) && (map.containsKey(key.longValue()))) {
/* 38 */       throw new IllegalStateException("Duplicate object found: " + object.getClass() + " - " + key);
/*    */     }
/* 40 */     getMap().put(object.getPointer(), object);
/*    */   }
/*    */ 
/*    */   void unregisterObject(T object) {
/* 44 */     getMap().remove(object.getPointerUnsafe());
/*    */   }
/*    */ 
/*    */   private FastLongMap<T> getMap() {
/* 48 */     if (this.registry == null) {
/* 49 */       this.registry = new FastLongMap();
/*    */     }
/* 51 */     return this.registry;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLObjectRegistry
 * JD-Core Version:    0.6.2
 */