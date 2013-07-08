/*  1:   */package org.schema.game.common.data.physics.octree;
/*  2:   */
/*  4:   */public class OctreeLevel
/*  5:   */{
/*  6:   */  public static final short START = 0;
/*  7:   */  
/*  8:   */  public static final short END = 1;
/*  9:   */  public static final short DIM = 2;
/* 10:   */  public static final short HALF = 3;
/* 11:   */  byte level;
/* 12:   */  int index;
/* 13:   */  int id;
/* 14:   */  
/* 15:   */  public boolean equals(Object paramObject)
/* 16:   */  {
/* 17:17 */    return (this.level == ((OctreeLevel)paramObject).level) && (this.id == ((OctreeLevel)paramObject).id) && (this.index == ((OctreeLevel)paramObject).index);
/* 18:   */  }
/* 19:   */  
/* 22:   */  public int hashCode()
/* 23:   */  {
/* 24:24 */    return (this.level << 3) + this.index + this.id * 100000;
/* 25:   */  }
/* 26:   */  
/* 29:   */  public String toString()
/* 30:   */  {
/* 31:31 */    return "OctreeLevel [level=" + this.level + ", index=" + this.index + ", id=" + this.id + "]";
/* 32:   */  }
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeLevel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */