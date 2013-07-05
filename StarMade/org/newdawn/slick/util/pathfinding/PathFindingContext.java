package org.newdawn.slick.util.pathfinding;

public abstract interface PathFindingContext
{
  public abstract Mover getMover();

  public abstract int getSourceX();

  public abstract int getSourceY();

  public abstract int getSearchDistance();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.PathFindingContext
 * JD-Core Version:    0.6.2
 */