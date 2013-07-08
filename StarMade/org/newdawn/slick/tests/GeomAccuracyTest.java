package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;

public class GeomAccuracyTest
  extends BasicGame
{
  private GameContainer container;
  private Color geomColor;
  private Color overlayColor;
  private boolean hideOverlay;
  private int colorIndex;
  private int curTest;
  private static final int NUMTESTS = 3;
  private Image magImage;
  
  public GeomAccuracyTest()
  {
    super("Geometry Accuracy Tests");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.container = container;
    this.geomColor = Color.magenta;
    this.overlayColor = Color.white;
    this.magImage = new Image(21, 21);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    String text = new String();
    switch (this.curTest)
    {
    case 0: 
      text = "Rectangles";
      rectTest(local_g);
      break;
    case 1: 
      text = "Ovals";
      ovalTest(local_g);
      break;
    case 2: 
      text = "Arcs";
      arcTest(local_g);
    }
    local_g.setColor(Color.white);
    local_g.drawString("Press T to toggle overlay", 200.0F, 55.0F);
    local_g.drawString("Press N to switch tests", 200.0F, 35.0F);
    local_g.drawString("Press C to cycle drawing colors", 200.0F, 15.0F);
    local_g.drawString("Current Test:", 400.0F, 35.0F);
    local_g.setColor(Color.blue);
    local_g.drawString(text, 485.0F, 35.0F);
    local_g.setColor(Color.white);
    local_g.drawString("Normal:", 10.0F, 150.0F);
    local_g.drawString("Filled:", 10.0F, 300.0F);
    local_g.drawString("Drawn with Graphics context", 125.0F, 400.0F);
    local_g.drawString("Drawn using Shapes", 450.0F, 400.0F);
    local_g.copyArea(this.magImage, container.getInput().getMouseX() - 10, container.getInput().getMouseY() - 10);
    this.magImage.draw(351.0F, 451.0F, 5.0F);
    local_g.drawString("Mag Area -", 250.0F, 475.0F);
    local_g.setColor(Color.darkGray);
    local_g.drawRect(350.0F, 450.0F, 106.0F, 106.0F);
    local_g.setColor(Color.white);
    local_g.drawString("NOTE:", 500.0F, 450.0F);
    local_g.drawString("lines should be flush with edges", 525.0F, 470.0F);
    local_g.drawString("corners should be symetric", 525.0F, 490.0F);
  }
  
  void arcTest(Graphics local_g)
  {
    if (!this.hideOverlay)
    {
      local_g.setColor(this.overlayColor);
      local_g.drawLine(198.0F, 100.0F, 198.0F, 198.0F);
      local_g.drawLine(100.0F, 198.0F, 198.0F, 198.0F);
    }
    local_g.setColor(this.geomColor);
    local_g.drawArc(100.0F, 100.0F, 99.0F, 99.0F, 0.0F, 90.0F);
  }
  
  void ovalTest(Graphics local_g)
  {
    local_g.setColor(this.geomColor);
    local_g.drawOval(100.0F, 100.0F, 99.0F, 99.0F);
    local_g.fillOval(100.0F, 250.0F, 99.0F, 99.0F);
    Ellipse elip = new Ellipse(449.0F, 149.0F, 49.0F, 49.0F);
    local_g.draw(elip);
    elip = new Ellipse(449.0F, 299.0F, 49.0F, 49.0F);
    local_g.fill(elip);
    if (!this.hideOverlay)
    {
      local_g.setColor(this.overlayColor);
      local_g.drawLine(100.0F, 149.0F, 198.0F, 149.0F);
      local_g.drawLine(149.0F, 100.0F, 149.0F, 198.0F);
      local_g.drawLine(100.0F, 299.0F, 198.0F, 299.0F);
      local_g.drawLine(149.0F, 250.0F, 149.0F, 348.0F);
      local_g.drawLine(400.0F, 149.0F, 498.0F, 149.0F);
      local_g.drawLine(449.0F, 100.0F, 449.0F, 198.0F);
      local_g.drawLine(400.0F, 299.0F, 498.0F, 299.0F);
      local_g.drawLine(449.0F, 250.0F, 449.0F, 348.0F);
    }
  }
  
  void rectTest(Graphics local_g)
  {
    local_g.setColor(this.geomColor);
    local_g.drawRect(100.0F, 100.0F, 99.0F, 99.0F);
    local_g.fillRect(100.0F, 250.0F, 99.0F, 99.0F);
    local_g.drawRoundRect(250.0F, 100.0F, 99.0F, 99.0F, 10);
    local_g.fillRoundRect(250.0F, 250.0F, 99.0F, 99.0F, 10);
    Rectangle rect = new Rectangle(400.0F, 100.0F, 99.0F, 99.0F);
    local_g.draw(rect);
    rect = new Rectangle(400.0F, 250.0F, 99.0F, 99.0F);
    local_g.fill(rect);
    RoundedRectangle rrect = new RoundedRectangle(550.0F, 100.0F, 99.0F, 99.0F, 10.0F);
    local_g.draw(rrect);
    rrect = new RoundedRectangle(550.0F, 250.0F, 99.0F, 99.0F, 10.0F);
    local_g.fill(rrect);
    if (!this.hideOverlay)
    {
      local_g.setColor(this.overlayColor);
      local_g.drawLine(100.0F, 149.0F, 198.0F, 149.0F);
      local_g.drawLine(149.0F, 100.0F, 149.0F, 198.0F);
      local_g.drawLine(250.0F, 149.0F, 348.0F, 149.0F);
      local_g.drawLine(299.0F, 100.0F, 299.0F, 198.0F);
      local_g.drawLine(400.0F, 149.0F, 498.0F, 149.0F);
      local_g.drawLine(449.0F, 100.0F, 449.0F, 198.0F);
      local_g.drawLine(550.0F, 149.0F, 648.0F, 149.0F);
      local_g.drawLine(599.0F, 100.0F, 599.0F, 198.0F);
      local_g.drawLine(100.0F, 299.0F, 198.0F, 299.0F);
      local_g.drawLine(149.0F, 250.0F, 149.0F, 348.0F);
      local_g.drawLine(250.0F, 299.0F, 348.0F, 299.0F);
      local_g.drawLine(299.0F, 250.0F, 299.0F, 348.0F);
      local_g.drawLine(400.0F, 299.0F, 498.0F, 299.0F);
      local_g.drawLine(449.0F, 250.0F, 449.0F, 348.0F);
      local_g.drawLine(550.0F, 299.0F, 648.0F, 299.0F);
      local_g.drawLine(599.0F, 250.0F, 599.0F, 348.0F);
    }
  }
  
  public void update(GameContainer container, int delta) {}
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
    if (key == 49)
    {
      this.curTest += 1;
      this.curTest %= 3;
    }
    if (key == 46)
    {
      this.colorIndex += 1;
      this.colorIndex %= 4;
      setColors();
    }
    if (key == 20) {
      this.hideOverlay = (!this.hideOverlay);
    }
  }
  
  private void setColors()
  {
    switch (this.colorIndex)
    {
    case 0: 
      this.overlayColor = Color.white;
      this.geomColor = Color.magenta;
      break;
    case 1: 
      this.overlayColor = Color.magenta;
      this.geomColor = Color.white;
      break;
    case 2: 
      this.overlayColor = Color.red;
      this.geomColor = Color.green;
      break;
    case 3: 
      this.overlayColor = Color.red;
      this.geomColor = Color.white;
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new GeomAccuracyTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.GeomAccuracyTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */