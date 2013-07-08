package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SavedState;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;

public class SavedStateTest
  extends BasicGame
  implements ComponentListener
{
  private TextField name;
  private TextField age;
  private String nameValue = "none";
  private int ageValue = 0;
  private SavedState state;
  private String message = "Enter a name and age to store";
  private static AppGameContainer container;
  
  public SavedStateTest()
  {
    super("Saved State Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.state = new SavedState("testdata");
    this.nameValue = this.state.getString("name", "DefaultName");
    this.ageValue = ((int)this.state.getNumber("age", 64.0D));
    this.name = new TextField(container, container.getDefaultFont(), 100, 100, 300, 20, this);
    this.age = new TextField(container, container.getDefaultFont(), 100, 150, 201, 20, this);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    this.name.render(container, local_g);
    this.age.render(container, local_g);
    container.getDefaultFont().drawString(100.0F, 300.0F, "Stored Name: " + this.nameValue);
    container.getDefaultFont().drawString(100.0F, 350.0F, "Stored Age: " + this.ageValue);
    container.getDefaultFont().drawString(200.0F, 500.0F, this.message);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      container = new AppGameContainer(new SavedStateTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException local_e)
    {
      local_e.printStackTrace();
    }
  }
  
  public void componentActivated(AbstractComponent source)
  {
    if (source == this.name)
    {
      this.nameValue = this.name.getText();
      this.state.setString("name", this.nameValue);
    }
    if (source == this.age) {
      try
      {
        this.ageValue = Integer.parseInt(this.age.getText());
        this.state.setNumber("age", this.ageValue);
      }
      catch (NumberFormatException local_e) {}
    }
    try
    {
      this.state.save();
    }
    catch (Exception local_e)
    {
      this.message = (System.currentTimeMillis() + " : Failed to save state");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.SavedStateTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */