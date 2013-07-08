/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.Input;
/*  9:   */import org.newdawn.slick.SlickException;
/* 10:   */import org.newdawn.slick.geom.Polygon;
/* 11:   */
/* 19:   */public class PolygonTest
/* 20:   */  extends BasicGame
/* 21:   */{
/* 22:   */  private Polygon poly;
/* 23:   */  private boolean in;
/* 24:   */  private float y;
/* 25:   */  
/* 26:   */  public PolygonTest()
/* 27:   */  {
/* 28:28 */    super("Polygon Test");
/* 29:   */  }
/* 30:   */  
/* 32:   */  public void init(GameContainer container)
/* 33:   */    throws SlickException
/* 34:   */  {
/* 35:35 */    this.poly = new Polygon();
/* 36:36 */    this.poly.addPoint(300.0F, 100.0F);
/* 37:37 */    this.poly.addPoint(320.0F, 200.0F);
/* 38:38 */    this.poly.addPoint(350.0F, 210.0F);
/* 39:39 */    this.poly.addPoint(280.0F, 250.0F);
/* 40:40 */    this.poly.addPoint(300.0F, 200.0F);
/* 41:41 */    this.poly.addPoint(240.0F, 150.0F);
/* 42:   */  }
/* 43:   */  
/* 46:   */  public void update(GameContainer container, int delta)
/* 47:   */    throws SlickException
/* 48:   */  {
/* 49:49 */    this.in = this.poly.contains(container.getInput().getMouseX(), container.getInput().getMouseY());
/* 50:   */    
/* 51:51 */    this.poly.setCenterY(0.0F);
/* 52:   */  }
/* 53:   */  
/* 55:   */  public void render(GameContainer container, Graphics g)
/* 56:   */    throws SlickException
/* 57:   */  {
/* 58:58 */    if (this.in) {
/* 59:59 */      g.setColor(Color.red);
/* 60:60 */      g.fill(this.poly);
/* 61:   */    }
/* 62:62 */    g.setColor(Color.yellow);
/* 63:63 */    g.fillOval(this.poly.getCenterX() - 3.0F, this.poly.getCenterY() - 3.0F, 6.0F, 6.0F);
/* 64:64 */    g.setColor(Color.white);
/* 65:65 */    g.draw(this.poly);
/* 66:   */  }
/* 67:   */  
/* 71:   */  public static void main(String[] argv)
/* 72:   */  {
/* 73:   */    try
/* 74:   */    {
/* 75:75 */      AppGameContainer container = new AppGameContainer(new PolygonTest(), 640, 480, false);
/* 76:76 */      container.start();
/* 77:   */    } catch (Exception e) {
/* 78:78 */      e.printStackTrace();
/* 79:   */    }
/* 80:   */  }
/* 81:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.PolygonTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */