package org.newdawn.slick.tests;

import java.util.ArrayList;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.opengl.renderer.Renderer;

public class ShapeTest
  extends BasicGame
{
  private Rectangle rect;
  private RoundedRectangle roundRect;
  private Ellipse ellipse;
  private Circle circle;
  private Polygon polygon;
  private ArrayList shapes;
  private boolean[] keys;
  private char[] lastChar;
  private Polygon randomShape = new Polygon();
  
  public ShapeTest()
  {
    super("Geom Test");
  }
  
  public void createPoly(float local_x, float local_y)
  {
    int size = 20;
    int change = 10;
    this.randomShape = new Polygon();
    this.randomShape.addPoint(0 + (int)(Math.random() * change), 0 + (int)(Math.random() * change));
    this.randomShape.addPoint(size - (int)(Math.random() * change), 0 + (int)(Math.random() * change));
    this.randomShape.addPoint(size - (int)(Math.random() * change), size - (int)(Math.random() * change));
    this.randomShape.addPoint(0 + (int)(Math.random() * change), size - (int)(Math.random() * change));
    this.randomShape.setCenterX(local_x);
    this.randomShape.setCenterY(local_y);
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.shapes = new ArrayList();
    this.rect = new Rectangle(10.0F, 10.0F, 100.0F, 80.0F);
    this.shapes.add(this.rect);
    this.roundRect = new RoundedRectangle(150.0F, 10.0F, 60.0F, 80.0F, 20.0F);
    this.shapes.add(this.roundRect);
    this.ellipse = new Ellipse(350.0F, 40.0F, 50.0F, 30.0F);
    this.shapes.add(this.ellipse);
    this.circle = new Circle(470.0F, 60.0F, 50.0F);
    this.shapes.add(this.circle);
    this.polygon = new Polygon(new float[] { 550.0F, 10.0F, 600.0F, 40.0F, 620.0F, 100.0F, 570.0F, 130.0F });
    this.shapes.add(this.polygon);
    this.keys = new boolean[256];
    this.lastChar = new char[256];
    createPoly(200.0F, 200.0F);
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.setColor(Color.green);
    for (int local_i = 0; local_i < this.shapes.size(); local_i++) {
      local_g.fill((Shape)this.shapes.get(local_i));
    }
    local_g.fill(this.randomShape);
    local_g.setColor(Color.black);
    local_g.setAntiAlias(true);
    local_g.draw(this.randomShape);
    local_g.setAntiAlias(false);
    local_g.setColor(Color.white);
    local_g.drawString("keys", 10.0F, 300.0F);
    local_g.drawString("wasd - move rectangle", 10.0F, 315.0F);
    local_g.drawString("WASD - resize rectangle", 10.0F, 330.0F);
    local_g.drawString("tgfh - move rounded rectangle", 10.0F, 345.0F);
    local_g.drawString("TGFH - resize rounded rectangle", 10.0F, 360.0F);
    local_g.drawString("ry - resize corner radius on rounded rectangle", 10.0F, 375.0F);
    local_g.drawString("ikjl - move ellipse", 10.0F, 390.0F);
    local_g.drawString("IKJL - resize ellipse", 10.0F, 405.0F);
    local_g.drawString("Arrows - move circle", 10.0F, 420.0F);
    local_g.drawString("Page Up/Page Down - resize circle", 10.0F, 435.0F);
    local_g.drawString("numpad 8546 - move polygon", 10.0F, 450.0F);
  }
  
  public void update(GameContainer container, int delta)
  {
    createPoly(200.0F, 200.0F);
    if (this.keys[1] != 0) {
      System.exit(0);
    }
    if (this.keys[17] != 0) {
      if (this.lastChar[17] == 'w') {
        this.rect.setY(this.rect.getY() - 1.0F);
      } else {
        this.rect.setHeight(this.rect.getHeight() - 1.0F);
      }
    }
    if (this.keys[31] != 0) {
      if (this.lastChar[31] == 's') {
        this.rect.setY(this.rect.getY() + 1.0F);
      } else {
        this.rect.setHeight(this.rect.getHeight() + 1.0F);
      }
    }
    if (this.keys[30] != 0) {
      if (this.lastChar[30] == 'a') {
        this.rect.setX(this.rect.getX() - 1.0F);
      } else {
        this.rect.setWidth(this.rect.getWidth() - 1.0F);
      }
    }
    if (this.keys[32] != 0) {
      if (this.lastChar[32] == 'd') {
        this.rect.setX(this.rect.getX() + 1.0F);
      } else {
        this.rect.setWidth(this.rect.getWidth() + 1.0F);
      }
    }
    if (this.keys[20] != 0) {
      if (this.lastChar[20] == 't') {
        this.roundRect.setY(this.roundRect.getY() - 1.0F);
      } else {
        this.roundRect.setHeight(this.roundRect.getHeight() - 1.0F);
      }
    }
    if (this.keys[34] != 0) {
      if (this.lastChar[34] == 'g') {
        this.roundRect.setY(this.roundRect.getY() + 1.0F);
      } else {
        this.roundRect.setHeight(this.roundRect.getHeight() + 1.0F);
      }
    }
    if (this.keys[33] != 0) {
      if (this.lastChar[33] == 'f') {
        this.roundRect.setX(this.roundRect.getX() - 1.0F);
      } else {
        this.roundRect.setWidth(this.roundRect.getWidth() - 1.0F);
      }
    }
    if (this.keys[35] != 0) {
      if (this.lastChar[35] == 'h') {
        this.roundRect.setX(this.roundRect.getX() + 1.0F);
      } else {
        this.roundRect.setWidth(this.roundRect.getWidth() + 1.0F);
      }
    }
    if (this.keys[19] != 0) {
      this.roundRect.setCornerRadius(this.roundRect.getCornerRadius() - 1.0F);
    }
    if (this.keys[21] != 0) {
      this.roundRect.setCornerRadius(this.roundRect.getCornerRadius() + 1.0F);
    }
    if (this.keys[23] != 0) {
      if (this.lastChar[23] == 'i') {
        this.ellipse.setCenterY(this.ellipse.getCenterY() - 1.0F);
      } else {
        this.ellipse.setRadius2(this.ellipse.getRadius2() - 1.0F);
      }
    }
    if (this.keys[37] != 0) {
      if (this.lastChar[37] == 'k') {
        this.ellipse.setCenterY(this.ellipse.getCenterY() + 1.0F);
      } else {
        this.ellipse.setRadius2(this.ellipse.getRadius2() + 1.0F);
      }
    }
    if (this.keys[36] != 0) {
      if (this.lastChar[36] == 'j') {
        this.ellipse.setCenterX(this.ellipse.getCenterX() - 1.0F);
      } else {
        this.ellipse.setRadius1(this.ellipse.getRadius1() - 1.0F);
      }
    }
    if (this.keys[38] != 0) {
      if (this.lastChar[38] == 'l') {
        this.ellipse.setCenterX(this.ellipse.getCenterX() + 1.0F);
      } else {
        this.ellipse.setRadius1(this.ellipse.getRadius1() + 1.0F);
      }
    }
    if (this.keys['È'] != 0) {
      this.circle.setCenterY(this.circle.getCenterY() - 1.0F);
    }
    if (this.keys['Ð'] != 0) {
      this.circle.setCenterY(this.circle.getCenterY() + 1.0F);
    }
    if (this.keys['Ë'] != 0) {
      this.circle.setCenterX(this.circle.getCenterX() - 1.0F);
    }
    if (this.keys['Í'] != 0) {
      this.circle.setCenterX(this.circle.getCenterX() + 1.0F);
    }
    if (this.keys['É'] != 0) {
      this.circle.setRadius(this.circle.getRadius() - 1.0F);
    }
    if (this.keys['Ñ'] != 0) {
      this.circle.setRadius(this.circle.getRadius() + 1.0F);
    }
    if (this.keys[72] != 0) {
      this.polygon.setY(this.polygon.getY() - 1.0F);
    }
    if (this.keys[76] != 0) {
      this.polygon.setY(this.polygon.getY() + 1.0F);
    }
    if (this.keys[75] != 0) {
      this.polygon.setX(this.polygon.getX() - 1.0F);
    }
    if (this.keys[77] != 0) {
      this.polygon.setX(this.polygon.getX() + 1.0F);
    }
  }
  
  public void keyPressed(int key, char local_c)
  {
    this.keys[key] = true;
    this.lastChar[key] = local_c;
  }
  
  public void keyReleased(int key, char local_c)
  {
    this.keys[key] = false;
  }
  
  public static void main(String[] argv)
  {
    try
    {
      Renderer.setRenderer(2);
      AppGameContainer container = new AppGameContainer(new ShapeTest());
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
 * Qualified Name:     org.newdawn.slick.tests.ShapeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */