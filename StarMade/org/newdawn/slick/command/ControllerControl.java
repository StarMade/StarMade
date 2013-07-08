package org.newdawn.slick.command;

abstract class ControllerControl
  implements Control
{
  protected static final int BUTTON_EVENT = 0;
  protected static final int LEFT_EVENT = 1;
  protected static final int RIGHT_EVENT = 2;
  protected static final int UP_EVENT = 3;
  protected static final int DOWN_EVENT = 4;
  private int event;
  private int button;
  private int controllerNumber;
  
  protected ControllerControl(int controllerNumber, int event, int button)
  {
    this.event = event;
    this.button = button;
    this.controllerNumber = controllerNumber;
  }
  
  public boolean equals(Object local_o)
  {
    if (local_o == null) {
      return false;
    }
    if (!(local_o instanceof ControllerControl)) {
      return false;
    }
    ControllerControl local_c = (ControllerControl)local_o;
    return (local_c.controllerNumber == this.controllerNumber) && (local_c.event == this.event) && (local_c.button == this.button);
  }
  
  public int hashCode()
  {
    return this.event + this.button + this.controllerNumber;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.command.ControllerControl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */