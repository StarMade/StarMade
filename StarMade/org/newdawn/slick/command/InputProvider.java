package org.newdawn.slick.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.newdawn.slick.Input;
import org.newdawn.slick.util.InputAdapter;

public class InputProvider
{
  private HashMap commands;
  private ArrayList listeners = new ArrayList();
  private Input input;
  private HashMap commandState = new HashMap();
  private boolean active = true;
  
  public InputProvider(Input input)
  {
    this.input = input;
    input.addListener(new InputListenerImpl(null));
    this.commands = new HashMap();
  }
  
  public List getUniqueCommands()
  {
    List uniqueCommands = new ArrayList();
    Iterator local_it = this.commands.values().iterator();
    while (local_it.hasNext())
    {
      Command command = (Command)local_it.next();
      if (!uniqueCommands.contains(command)) {
        uniqueCommands.add(command);
      }
    }
    return uniqueCommands;
  }
  
  public List getControlsFor(Command command)
  {
    List controlsForCommand = new ArrayList();
    Iterator local_it = this.commands.entrySet().iterator();
    while (local_it.hasNext())
    {
      Map.Entry entry = (Map.Entry)local_it.next();
      Control key = (Control)entry.getKey();
      Command value = (Command)entry.getValue();
      if (value == command) {
        controlsForCommand.add(key);
      }
    }
    return controlsForCommand;
  }
  
  public void setActive(boolean active)
  {
    this.active = active;
  }
  
  public boolean isActive()
  {
    return this.active;
  }
  
  public void addListener(InputProviderListener listener)
  {
    this.listeners.add(listener);
  }
  
  public void removeListener(InputProviderListener listener)
  {
    this.listeners.remove(listener);
  }
  
  public void bindCommand(Control control, Command command)
  {
    this.commands.put(control, command);
    if (this.commandState.get(command) == null) {
      this.commandState.put(command, new CommandState(null));
    }
  }
  
  public void clearCommand(Command command)
  {
    List controls = getControlsFor(command);
    for (int local_i = 0; local_i < controls.size(); local_i++) {
      unbindCommand((Control)controls.get(local_i));
    }
  }
  
  public void unbindCommand(Control control)
  {
    Command command = (Command)this.commands.remove(control);
    if ((command != null) && (!this.commands.keySet().contains(command))) {
      this.commandState.remove(command);
    }
  }
  
  private CommandState getState(Command command)
  {
    return (CommandState)this.commandState.get(command);
  }
  
  public boolean isCommandControlDown(Command command)
  {
    return getState(command).isDown();
  }
  
  public boolean isCommandControlPressed(Command command)
  {
    return getState(command).isPressed();
  }
  
  protected void firePressed(Command command)
  {
    getState(command).down = true;
    getState(command).pressed = true;
    if (!isActive()) {
      return;
    }
    for (int local_i = 0; local_i < this.listeners.size(); local_i++) {
      ((InputProviderListener)this.listeners.get(local_i)).controlPressed(command);
    }
  }
  
  protected void fireReleased(Command command)
  {
    getState(command).down = false;
    if (!isActive()) {
      return;
    }
    for (int local_i = 0; local_i < this.listeners.size(); local_i++) {
      ((InputProviderListener)this.listeners.get(local_i)).controlReleased(command);
    }
  }
  
  private class InputListenerImpl
    extends InputAdapter
  {
    private InputListenerImpl() {}
    
    public boolean isAcceptingInput()
    {
      return true;
    }
    
    public void keyPressed(int key, char local_c)
    {
      Command command = (Command)InputProvider.this.commands.get(new KeyControl(key));
      if (command != null) {
        InputProvider.this.firePressed(command);
      }
    }
    
    public void keyReleased(int key, char local_c)
    {
      Command command = (Command)InputProvider.this.commands.get(new KeyControl(key));
      if (command != null) {
        InputProvider.this.fireReleased(command);
      }
    }
    
    public void mousePressed(int button, int local_x, int local_y)
    {
      Command command = (Command)InputProvider.this.commands.get(new MouseButtonControl(button));
      if (command != null) {
        InputProvider.this.firePressed(command);
      }
    }
    
    public void mouseReleased(int button, int local_x, int local_y)
    {
      Command command = (Command)InputProvider.this.commands.get(new MouseButtonControl(button));
      if (command != null) {
        InputProvider.this.fireReleased(command);
      }
    }
    
    public void controllerLeftPressed(int controller)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
      if (command != null) {
        InputProvider.this.firePressed(command);
      }
    }
    
    public void controllerLeftReleased(int controller)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
      if (command != null) {
        InputProvider.this.fireReleased(command);
      }
    }
    
    public void controllerRightPressed(int controller)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
      if (command != null) {
        InputProvider.this.firePressed(command);
      }
    }
    
    public void controllerRightReleased(int controller)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
      if (command != null) {
        InputProvider.this.fireReleased(command);
      }
    }
    
    public void controllerUpPressed(int controller)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.field_375));
      if (command != null) {
        InputProvider.this.firePressed(command);
      }
    }
    
    public void controllerUpReleased(int controller)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.field_375));
      if (command != null) {
        InputProvider.this.fireReleased(command);
      }
    }
    
    public void controllerDownPressed(int controller)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
      if (command != null) {
        InputProvider.this.firePressed(command);
      }
    }
    
    public void controllerDownReleased(int controller)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
      if (command != null) {
        InputProvider.this.fireReleased(command);
      }
    }
    
    public void controllerButtonPressed(int controller, int button)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerButtonControl(controller, button));
      if (command != null) {
        InputProvider.this.firePressed(command);
      }
    }
    
    public void controllerButtonReleased(int controller, int button)
    {
      Command command = (Command)InputProvider.this.commands.get(new ControllerButtonControl(controller, button));
      if (command != null) {
        InputProvider.this.fireReleased(command);
      }
    }
  }
  
  private class CommandState
  {
    private boolean down;
    private boolean pressed;
    
    private CommandState() {}
    
    public boolean isPressed()
    {
      if (this.pressed)
      {
        this.pressed = false;
        return true;
      }
      return false;
    }
    
    public boolean isDown()
    {
      return this.down;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.command.InputProvider
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */