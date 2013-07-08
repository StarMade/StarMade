package org.lwjgl.input;

import java.util.ArrayList;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;
import net.java.games.input.Rumbler;

class JInputController
  implements Controller
{
  private net.java.games.input.Controller target;
  private int index;
  private ArrayList<Component> buttons = new ArrayList();
  private ArrayList<Component> axes = new ArrayList();
  private ArrayList<Component> pov = new ArrayList();
  private Rumbler[] rumblers;
  private boolean[] buttonState;
  private float[] povValues;
  private float[] axesValue;
  private float[] axesMax;
  private float[] deadZones;
  private int xaxis = -1;
  private int yaxis = -1;
  private int zaxis = -1;
  private int rxaxis = -1;
  private int ryaxis = -1;
  private int rzaxis = -1;
  
  JInputController(int index, net.java.games.input.Controller target)
  {
    this.target = target;
    this.index = index;
    Component[] sourceAxes = target.getComponents();
    for (Component sourceAxis : sourceAxes) {
      if ((sourceAxis.getIdentifier() instanceof Component.Identifier.Button)) {
        this.buttons.add(sourceAxis);
      } else if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.POV)) {
        this.pov.add(sourceAxis);
      } else {
        this.axes.add(sourceAxis);
      }
    }
    this.buttonState = new boolean[this.buttons.size()];
    this.povValues = new float[this.pov.size()];
    this.axesValue = new float[this.axes.size()];
    int arr$ = 0;
    int len$ = 0;
    for (Component sourceAxis : sourceAxes) {
      if ((sourceAxis.getIdentifier() instanceof Component.Identifier.Button))
      {
        this.buttonState[arr$] = (sourceAxis.getPollData() != 0.0F ? 1 : false);
        arr$++;
      }
      else if (!sourceAxis.getIdentifier().equals(Component.Identifier.Axis.POV))
      {
        this.axesValue[len$] = sourceAxis.getPollData();
        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.X)) {
          this.xaxis = len$;
        }
        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.Y)) {
          this.yaxis = len$;
        }
        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.Z)) {
          this.zaxis = len$;
        }
        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.RX)) {
          this.rxaxis = len$;
        }
        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.RY)) {
          this.ryaxis = len$;
        }
        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.RZ)) {
          this.rzaxis = len$;
        }
        len$++;
      }
    }
    this.axesMax = new float[this.axes.size()];
    this.deadZones = new float[this.axes.size()];
    for (int local_i$ = 0; local_i$ < this.axesMax.length; local_i$++)
    {
      this.axesMax[local_i$] = 1.0F;
      this.deadZones[local_i$] = 0.05F;
    }
    this.rumblers = target.getRumblers();
  }
  
  public String getName()
  {
    String name = this.target.getName();
    return name;
  }
  
  public int getIndex()
  {
    return this.index;
  }
  
  public int getButtonCount()
  {
    return this.buttons.size();
  }
  
  public String getButtonName(int index)
  {
    return ((Component)this.buttons.get(index)).getName();
  }
  
  public boolean isButtonPressed(int index)
  {
    return this.buttonState[index];
  }
  
  public void poll()
  {
    this.target.poll();
    Event event = new Event();
    EventQueue queue = this.target.getEventQueue();
    while (queue.getNextEvent(event))
    {
      if (this.buttons.contains(event.getComponent()))
      {
        Component button = event.getComponent();
        int buttonIndex = this.buttons.indexOf(button);
        this.buttonState[buttonIndex] = (event.getValue() != 0.0F ? 1 : false);
        Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 1, buttonIndex, false, false));
      }
      if (this.pov.contains(event.getComponent()))
      {
        Component button = event.getComponent();
        int buttonIndex = this.pov.indexOf(button);
        float prevX = getPovX();
        float prevY = getPovY();
        this.povValues[buttonIndex] = event.getValue();
        if (prevX != getPovX()) {
          Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 3, 0, false, false));
        }
        if (prevY != getPovY()) {
          Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 4, 0, false, false));
        }
      }
      if (this.axes.contains(event.getComponent()))
      {
        Component button = event.getComponent();
        int buttonIndex = this.axes.indexOf(button);
        float prevX = button.getPollData();
        if (Math.abs(prevX) < this.deadZones[buttonIndex]) {
          prevX = 0.0F;
        }
        if (Math.abs(prevX) < button.getDeadZone()) {
          prevX = 0.0F;
        }
        if (Math.abs(prevX) > this.axesMax[buttonIndex]) {
          this.axesMax[buttonIndex] = Math.abs(prevX);
        }
        prevX /= this.axesMax[buttonIndex];
        Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 2, buttonIndex, buttonIndex == this.xaxis, buttonIndex == this.yaxis));
        this.axesValue[buttonIndex] = prevX;
      }
    }
  }
  
  public int getAxisCount()
  {
    return this.axes.size();
  }
  
  public String getAxisName(int index)
  {
    return ((Component)this.axes.get(index)).getName();
  }
  
  public float getAxisValue(int index)
  {
    return this.axesValue[index];
  }
  
  public float getXAxisValue()
  {
    if (this.xaxis == -1) {
      return 0.0F;
    }
    return getAxisValue(this.xaxis);
  }
  
  public float getYAxisValue()
  {
    if (this.yaxis == -1) {
      return 0.0F;
    }
    return getAxisValue(this.yaxis);
  }
  
  public float getXAxisDeadZone()
  {
    if (this.xaxis == -1) {
      return 0.0F;
    }
    return getDeadZone(this.xaxis);
  }
  
  public float getYAxisDeadZone()
  {
    if (this.yaxis == -1) {
      return 0.0F;
    }
    return getDeadZone(this.yaxis);
  }
  
  public void setXAxisDeadZone(float zone)
  {
    setDeadZone(this.xaxis, zone);
  }
  
  public void setYAxisDeadZone(float zone)
  {
    setDeadZone(this.yaxis, zone);
  }
  
  public float getDeadZone(int index)
  {
    return this.deadZones[index];
  }
  
  public void setDeadZone(int index, float zone)
  {
    this.deadZones[index] = zone;
  }
  
  public float getZAxisValue()
  {
    if (this.zaxis == -1) {
      return 0.0F;
    }
    return getAxisValue(this.zaxis);
  }
  
  public float getZAxisDeadZone()
  {
    if (this.zaxis == -1) {
      return 0.0F;
    }
    return getDeadZone(this.zaxis);
  }
  
  public void setZAxisDeadZone(float zone)
  {
    setDeadZone(this.zaxis, zone);
  }
  
  public float getRXAxisValue()
  {
    if (this.rxaxis == -1) {
      return 0.0F;
    }
    return getAxisValue(this.rxaxis);
  }
  
  public float getRXAxisDeadZone()
  {
    if (this.rxaxis == -1) {
      return 0.0F;
    }
    return getDeadZone(this.rxaxis);
  }
  
  public void setRXAxisDeadZone(float zone)
  {
    setDeadZone(this.rxaxis, zone);
  }
  
  public float getRYAxisValue()
  {
    if (this.ryaxis == -1) {
      return 0.0F;
    }
    return getAxisValue(this.ryaxis);
  }
  
  public float getRYAxisDeadZone()
  {
    if (this.ryaxis == -1) {
      return 0.0F;
    }
    return getDeadZone(this.ryaxis);
  }
  
  public void setRYAxisDeadZone(float zone)
  {
    setDeadZone(this.ryaxis, zone);
  }
  
  public float getRZAxisValue()
  {
    if (this.rzaxis == -1) {
      return 0.0F;
    }
    return getAxisValue(this.rzaxis);
  }
  
  public float getRZAxisDeadZone()
  {
    if (this.rzaxis == -1) {
      return 0.0F;
    }
    return getDeadZone(this.rzaxis);
  }
  
  public void setRZAxisDeadZone(float zone)
  {
    setDeadZone(this.rzaxis, zone);
  }
  
  public float getPovX()
  {
    if (this.pov.size() == 0) {
      return 0.0F;
    }
    float value = this.povValues[0];
    if ((value == 0.875F) || (value == 0.125F) || (value == 1.0F)) {
      return -1.0F;
    }
    if ((value == 0.625F) || (value == 0.375F) || (value == 0.5F)) {
      return 1.0F;
    }
    return 0.0F;
  }
  
  public float getPovY()
  {
    if (this.pov.size() == 0) {
      return 0.0F;
    }
    float value = this.povValues[0];
    if ((value == 0.875F) || (value == 0.625F) || (value == 0.75F)) {
      return 1.0F;
    }
    if ((value == 0.125F) || (value == 0.375F) || (value == 0.25F)) {
      return -1.0F;
    }
    return 0.0F;
  }
  
  public int getRumblerCount()
  {
    return this.rumblers.length;
  }
  
  public String getRumblerName(int index)
  {
    return this.rumblers[index].getAxisName();
  }
  
  public void setRumblerStrength(int index, float strength)
  {
    this.rumblers[index].rumble(strength);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.input.JInputController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */