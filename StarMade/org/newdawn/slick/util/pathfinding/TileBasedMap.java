package org.newdawn.slick.util.pathfinding;

public abstract interface TileBasedMap
{
  public abstract int getWidthInTiles();
  
  public abstract int getHeightInTiles();
  
  public abstract void pathFinderVisited(int paramInt1, int paramInt2);
  
  public abstract boolean blocked(PathFindingContext paramPathFindingContext, int paramInt1, int paramInt2);
  
  public abstract float getCost(PathFindingContext paramPathFindingContext, int paramInt1, int paramInt2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.TileBasedMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */