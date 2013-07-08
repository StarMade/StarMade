/*  1:   */package org.newdawn.slick.command;
/*  2:   */
/*  8:   */public class ControllerDirectionControl
/*  9:   */  extends ControllerControl
/* 10:   */{
/* 11:11 */  public static final Direction LEFT = new Direction(1);
/* 12:   */  
/* 13:13 */  public static final Direction UP = new Direction(3);
/* 14:   */  
/* 15:15 */  public static final Direction DOWN = new Direction(4);
/* 16:   */  
/* 17:17 */  public static final Direction RIGHT = new Direction(2);
/* 18:   */  
/* 24:   */  public ControllerDirectionControl(int controllerIndex, Direction dir)
/* 25:   */  {
/* 26:26 */    super(controllerIndex, dir.event, 0);
/* 27:   */  }
/* 28:   */  
/* 34:   */  private static class Direction
/* 35:   */  {
/* 36:   */    private int event;
/* 37:   */    
/* 42:   */    public Direction(int event)
/* 43:   */    {
/* 44:44 */      this.event = event;
/* 45:   */    }
/* 46:   */  }
/* 47:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.command.ControllerDirectionControl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */