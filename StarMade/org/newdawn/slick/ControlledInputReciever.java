package org.newdawn.slick;

public abstract interface ControlledInputReciever
{
  public abstract void setInput(Input paramInput);
  
  public abstract boolean isAcceptingInput();
  
  public abstract void inputEnded();
  
  public abstract void inputStarted();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.ControlledInputReciever
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */