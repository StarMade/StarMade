package org.schema.game.common.data.physics.octree;

public class OctreeLevel
{
  public static final short START = 0;
  public static final short END = 1;
  public static final short DIM = 2;
  public static final short HALF = 3;
  byte level;
  int index;
  int field_1828;
  
  public boolean equals(Object paramObject)
  {
    return (this.level == ((OctreeLevel)paramObject).level) && (this.field_1828 == ((OctreeLevel)paramObject).field_1828) && (this.index == ((OctreeLevel)paramObject).index);
  }
  
  public int hashCode()
  {
    return (this.level << 3) + this.index + this.field_1828 * 100000;
  }
  
  public String toString()
  {
    return "OctreeLevel [level=" + this.level + ", index=" + this.index + ", id=" + this.field_1828 + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeLevel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */