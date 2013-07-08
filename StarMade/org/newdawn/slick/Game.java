package org.newdawn.slick;

public abstract interface Game
{
  public abstract void init(GameContainer paramGameContainer)
    throws SlickException;
  
  public abstract void update(GameContainer paramGameContainer, int paramInt)
    throws SlickException;
  
  public abstract void render(GameContainer paramGameContainer, Graphics paramGraphics)
    throws SlickException;
  
  public abstract boolean closeRequested();
  
  public abstract String getTitle();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.Game
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */