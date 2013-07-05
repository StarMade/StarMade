/*    */ package org.schema.game.common.data.physics.octree;
/*    */ 
/*    */ class TreeCache
/*    */ {
/*    */   byte[] lvlToIndex;
/*    */   boolean initialized;
/*    */ 
/*    */   public TreeCache()
/*    */   {
/* 10 */     this.initialized = false;
/* 11 */     this.lvlToIndex = new byte[3];
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.TreeCache
 * JD-Core Version:    0.6.2
 */