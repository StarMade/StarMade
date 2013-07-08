package org.newdawn.slick.command;

public class ControllerDirectionControl
  extends ControllerControl
{
  public static final Direction LEFT = new Direction(1);
  public static final Direction field_375 = new Direction(3);
  public static final Direction DOWN = new Direction(4);
  public static final Direction RIGHT = new Direction(2);
  
  public ControllerDirectionControl(int controllerIndex, Direction dir)
  {
    super(controllerIndex, dir.event, 0);
  }
  
  private static class Direction
  {
    private int event;
    
    public Direction(int event)
    {
      this.event = event;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.command.ControllerDirectionControl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */