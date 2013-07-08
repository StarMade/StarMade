package org.newdawn.slick.tests;

import java.io.PrintStream;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.util.Log;

public class GUITest
  extends BasicGame
  implements ComponentListener
{
  private Image image;
  private MouseOverArea[] areas = new MouseOverArea[4];
  private GameContainer container;
  private String message = "Demo Menu System with stock images";
  private TextField field;
  private TextField field2;
  private Image background;
  private Font font;
  private AppGameContainer app;
  
  public GUITest()
  {
    super("GUI Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    if ((container instanceof AppGameContainer))
    {
      this.app = ((AppGameContainer)container);
      this.app.setIcon("testdata/icon.tga");
    }
    this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
    this.field = (container, this.font, 150, 20, 500, 35, new ComponentListener()
    {
      public void componentActivated(AbstractComponent source)
      {
        GUITest.this.message = ("Entered1: " + GUITest.this.field.getText());
        GUITest.this.field2.setFocus(true);
      }
    });
    this.field2 = (container, this.font, 150, 70, 500, 35, new ComponentListener()
    {
      public void componentActivated(AbstractComponent source)
      {
        GUITest.this.message = ("Entered2: " + GUITest.this.field2.getText());
        GUITest.this.field.setFocus(true);
      }
    });
    this.field2.setBorderColor(Color.red);
    this.container = container;
    this.image = new Image("testdata/logo.tga");
    this.background = new Image("testdata/dungeontiles.gif");
    container.setMouseCursor("testdata/cursor.tga", 0, 0);
    for (int local_i = 0; local_i < 4; local_i++)
    {
      this.areas[local_i] = new MouseOverArea(container, this.image, 300, 100 + local_i * 100, 200, 90, this);
      this.areas[local_i].setNormalColor(new Color(1.0F, 1.0F, 1.0F, 0.8F));
      this.areas[local_i].setMouseOverColor(new Color(1.0F, 1.0F, 1.0F, 0.9F));
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    this.background.draw(0.0F, 0.0F, 800.0F, 500.0F);
    for (int local_i = 0; local_i < 4; local_i++) {
      this.areas[local_i].render(container, local_g);
    }
    this.field.render(container, local_g);
    this.field2.render(container, local_g);
    local_g.setFont(this.font);
    local_g.drawString(this.message, 200.0F, 550.0F);
  }
  
  public void update(GameContainer container, int delta) {}
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
    if (key == 60) {
      this.app.setDefaultMouseCursor();
    }
    if ((key == 59) && (this.app != null)) {
      try
      {
        this.app.setDisplayMode(640, 480, false);
      }
      catch (SlickException local_e)
      {
        Log.error(local_e);
      }
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new GUITest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  public void componentActivated(AbstractComponent source)
  {
    System.out.println("ACTIVL : " + source);
    for (int local_i = 0; local_i < 4; local_i++) {
      if (source == this.areas[local_i]) {
        this.message = ("Option " + (local_i + 1) + " pressed!");
      }
    }
    if (source == this.field2) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.GUITest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */