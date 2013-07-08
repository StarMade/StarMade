package org.newdawn.slick.tests;

import java.io.PrintStream;
import java.util.ArrayList;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

public class InputTest
  extends BasicGame
{
  private String message = "Press any key, mouse button, or drag the mouse";
  private ArrayList lines = new ArrayList();
  private boolean buttonDown;
  private float field_54;
  private float field_318;
  private Color[] cols = { Color.red, Color.green, Color.blue, Color.white, Color.magenta, Color.cyan };
  private int index;
  private Input input;
  private int ypos;
  private AppGameContainer app;
  private boolean space;
  private boolean lshift;
  private boolean rshift;
  
  public InputTest()
  {
    super("Input Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    if ((container instanceof AppGameContainer)) {
      this.app = ((AppGameContainer)container);
    }
    this.input = container.getInput();
    this.field_54 = 300.0F;
    this.field_318 = 300.0F;
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.drawString("left shift down: " + this.lshift, 100.0F, 240.0F);
    local_g.drawString("right shift down: " + this.rshift, 100.0F, 260.0F);
    local_g.drawString("space down: " + this.space, 100.0F, 280.0F);
    local_g.setColor(Color.white);
    local_g.drawString(this.message, 10.0F, 50.0F);
    local_g.drawString("" + container.getInput().getMouseY(), 10.0F, 400.0F);
    local_g.drawString("Use the primary gamepad to control the blob, and hit a gamepad button to change the color", 10.0F, 90.0F);
    for (int local_i = 0; local_i < this.lines.size(); local_i++)
    {
      Line line = (Line)this.lines.get(local_i);
      line.draw(local_g);
    }
    local_g.setColor(this.cols[this.index]);
    local_g.fillOval((int)this.field_54, (int)this.field_318, 50.0F, 50.0F);
    local_g.setColor(Color.yellow);
    local_g.fillRect(50.0F, 200 + this.ypos, 40.0F, 40.0F);
  }
  
  public void update(GameContainer container, int delta)
  {
    this.lshift = container.getInput().isKeyDown(42);
    this.rshift = container.getInput().isKeyDown(54);
    this.space = container.getInput().isKeyDown(57);
    if (this.controllerLeft[0] != 0) {
      this.field_54 -= delta * 0.1F;
    }
    if (this.controllerRight[0] != 0) {
      this.field_54 += delta * 0.1F;
    }
    if (this.controllerUp[0] != 0) {
      this.field_318 -= delta * 0.1F;
    }
    if (this.controllerDown[0] != 0) {
      this.field_318 += delta * 0.1F;
    }
  }
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
    if ((key == 59) && (this.app != null)) {
      try
      {
        this.app.setDisplayMode(600, 600, false);
        this.app.reinit();
      }
      catch (Exception local_e)
      {
        Log.error(local_e);
      }
    }
  }
  
  public void keyReleased(int key, char local_c)
  {
    this.message = ("You pressed key code " + key + " (character = " + local_c + ")");
  }
  
  public void mousePressed(int button, int local_x, int local_y)
  {
    if (button == 0) {
      this.buttonDown = true;
    }
    this.message = ("Mouse pressed " + button + " " + local_x + "," + local_y);
  }
  
  public void mouseReleased(int button, int local_x, int local_y)
  {
    if (button == 0) {
      this.buttonDown = false;
    }
    this.message = ("Mouse released " + button + " " + local_x + "," + local_y);
  }
  
  public void mouseClicked(int button, int local_x, int local_y, int clickCount)
  {
    System.out.println("CLICKED:" + local_x + "," + local_y + " " + clickCount);
  }
  
  public void mouseWheelMoved(int change)
  {
    this.message = ("Mouse wheel moved: " + change);
    if (change < 0) {
      this.ypos -= 10;
    }
    if (change > 0) {
      this.ypos += 10;
    }
  }
  
  public void mouseMoved(int oldx, int oldy, int newx, int newy)
  {
    if (this.buttonDown) {
      this.lines.add(new Line(oldx, oldy, newx, newy));
    }
  }
  
  public void controllerButtonPressed(int controller, int button)
  {
    super.controllerButtonPressed(controller, button);
    this.index += 1;
    this.index %= this.cols.length;
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new InputTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  private class Line
  {
    private int oldx;
    private int oldy;
    private int newx;
    private int newy;
    
    public Line(int oldx, int oldy, int newx, int newy)
    {
      this.oldx = oldx;
      this.oldy = oldy;
      this.newx = newx;
      this.newy = newy;
    }
    
    public void draw(Graphics local_g)
    {
      local_g.drawLine(this.oldx, this.oldy, this.newx, this.newy);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.InputTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */