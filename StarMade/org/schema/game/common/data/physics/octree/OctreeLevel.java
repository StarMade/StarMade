/*    */ package org.schema.game.common.data.physics.octree;
/*    */ 
/*    */ public class OctreeLevel
/*    */ {
/*    */   public static final short START = 0;
/*    */   public static final short END = 1;
/*    */   public static final short DIM = 2;
/*    */   public static final short HALF = 3;
/*    */   byte level;
/*    */   int index;
/*    */   int id;
/*    */ 
/*    */   public boolean equals(Object paramObject)
/*    */   {
/* 17 */     return (this.level == ((OctreeLevel)paramObject).level) && (this.id == ((OctreeLevel)paramObject).id) && (this.index == ((OctreeLevel)paramObject).index);
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 24 */     return (this.level << 3) + this.index + this.id * 100000;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 31 */     return "OctreeLevel [level=" + this.level + ", index=" + this.index + ", id=" + this.id + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeLevel
 * JD-Core Version:    0.6.2
 */