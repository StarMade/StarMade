/*  1:   */package org.newdawn.slick.command;
/*  2:   */
/*  6:   */abstract class ControllerControl
/*  7:   */  implements Control
/*  8:   */{
/*  9:   */  protected static final int BUTTON_EVENT = 0;
/* 10:   */  
/* 12:   */  protected static final int LEFT_EVENT = 1;
/* 13:   */  
/* 15:   */  protected static final int RIGHT_EVENT = 2;
/* 16:   */  
/* 18:   */  protected static final int UP_EVENT = 3;
/* 19:   */  
/* 21:   */  protected static final int DOWN_EVENT = 4;
/* 22:   */  
/* 24:   */  private int event;
/* 25:   */  
/* 27:   */  private int button;
/* 28:   */  
/* 30:   */  private int controllerNumber;
/* 31:   */  
/* 34:   */  protected ControllerControl(int controllerNumber, int event, int button)
/* 35:   */  {
/* 36:36 */    this.event = event;
/* 37:37 */    this.button = button;
/* 38:38 */    this.controllerNumber = controllerNumber;
/* 39:   */  }
/* 40:   */  
/* 43:   */  public boolean equals(Object o)
/* 44:   */  {
/* 45:45 */    if (o == null)
/* 46:46 */      return false;
/* 47:47 */    if (!(o instanceof ControllerControl)) {
/* 48:48 */      return false;
/* 49:   */    }
/* 50:50 */    ControllerControl c = (ControllerControl)o;
/* 51:   */    
/* 52:52 */    return (c.controllerNumber == this.controllerNumber) && (c.event == this.event) && (c.button == this.button);
/* 53:   */  }
/* 54:   */  
/* 57:   */  public int hashCode()
/* 58:   */  {
/* 59:59 */    return this.event + this.button + this.controllerNumber;
/* 60:   */  }
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.command.ControllerControl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */