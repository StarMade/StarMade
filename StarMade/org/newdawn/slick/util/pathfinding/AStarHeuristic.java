package org.newdawn.slick.util.pathfinding;

public abstract interface AStarHeuristic
{
  public abstract float getCost(TileBasedMap paramTileBasedMap, Mover paramMover, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.AStarHeuristic
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */