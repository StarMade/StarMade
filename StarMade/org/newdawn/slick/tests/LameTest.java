/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.Image;
/*  9:   */import org.newdawn.slick.SlickException;
/* 10:   */import org.newdawn.slick.geom.Polygon;
/* 11:   */
/* 16:   */public class LameTest
/* 17:   */  extends BasicGame
/* 18:   */{
/* 19:19 */  private Polygon poly = new Polygon();
/* 20:   */  
/* 22:   */  private Image image;
/* 23:   */  
/* 25:   */  public LameTest()
/* 26:   */  {
/* 27:27 */    super("Lame Test");
/* 28:   */  }
/* 29:   */  
/* 31:   */  public void init(GameContainer container)
/* 32:   */    throws SlickException
/* 33:   */  {
/* 34:34 */    this.poly.addPoint(100.0F, 100.0F);
/* 35:35 */    this.poly.addPoint(120.0F, 100.0F);
/* 36:36 */    this.poly.addPoint(120.0F, 120.0F);
/* 37:37 */    this.poly.addPoint(100.0F, 120.0F);
/* 38:   */    
/* 39:39 */    this.image = new Image("testdata/rocks.png");
/* 40:   */  }
/* 41:   */  
/* 44:   */  public void update(GameContainer container, int delta)
/* 45:   */    throws SlickException
/* 46:   */  {}
/* 47:   */  
/* 49:   */  public void render(GameContainer container, Graphics g)
/* 50:   */    throws SlickException
/* 51:   */  {
/* 52:52 */    g.setColor(Color.white);
/* 53:53 */    g.texture(this.poly, this.image);
/* 54:   */  }
/* 55:   */  
/* 59:   */  public static void main(String[] argv)
/* 60:   */  {
/* 61:   */    try
/* 62:   */    {
/* 63:63 */      AppGameContainer container = new AppGameContainer(new LameTest());
/* 64:64 */      container.setDisplayMode(800, 600, false);
/* 65:65 */      container.start();
/* 66:   */    } catch (SlickException e) {
/* 67:67 */      e.printStackTrace();
/* 68:   */    }
/* 69:   */  }
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.LameTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */