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
/* 11:   */import org.newdawn.slick.geom.Rectangle;
/* 12:   */import org.newdawn.slick.geom.ShapeRenderer;
/* 13:   */import org.newdawn.slick.geom.TexCoordGenerator;
/* 14:   */import org.newdawn.slick.geom.Vector2f;
/* 15:   */
/* 20:   */public class TexturePaintTest
/* 21:   */  extends BasicGame
/* 22:   */{
/* 23:23 */  private Polygon poly = new Polygon();
/* 24:   */  
/* 26:   */  private Image image;
/* 27:   */  
/* 28:28 */  private Rectangle texRect = new Rectangle(50.0F, 50.0F, 100.0F, 100.0F);
/* 29:   */  
/* 31:   */  private TexCoordGenerator texPaint;
/* 32:   */  
/* 34:   */  public TexturePaintTest()
/* 35:   */  {
/* 36:36 */    super("Texture Paint Test");
/* 37:   */  }
/* 38:   */  
/* 40:   */  public void init(GameContainer container)
/* 41:   */    throws SlickException
/* 42:   */  {
/* 43:43 */    this.poly.addPoint(120.0F, 120.0F);
/* 44:44 */    this.poly.addPoint(420.0F, 100.0F);
/* 45:45 */    this.poly.addPoint(620.0F, 420.0F);
/* 46:46 */    this.poly.addPoint(300.0F, 320.0F);
/* 47:   */    
/* 48:48 */    this.image = new Image("testdata/rocks.png");
/* 49:   */    
/* 50:50 */    this.texPaint = new TexCoordGenerator() {
/* 51:   */      public Vector2f getCoordFor(float x, float y) {
/* 52:52 */        float tx = (TexturePaintTest.this.texRect.getX() - x) / TexturePaintTest.this.texRect.getWidth();
/* 53:53 */        float ty = (TexturePaintTest.this.texRect.getY() - y) / TexturePaintTest.this.texRect.getHeight();
/* 54:   */        
/* 55:55 */        return new Vector2f(tx, ty);
/* 56:   */      }
/* 57:   */    };
/* 58:   */  }
/* 59:   */  
/* 62:   */  public void update(GameContainer container, int delta)
/* 63:   */    throws SlickException
/* 64:   */  {}
/* 65:   */  
/* 67:   */  public void render(GameContainer container, Graphics g)
/* 68:   */    throws SlickException
/* 69:   */  {
/* 70:70 */    g.setColor(Color.white);
/* 71:71 */    g.texture(this.poly, this.image);
/* 72:   */    
/* 73:73 */    ShapeRenderer.texture(this.poly, this.image, this.texPaint);
/* 74:   */  }
/* 75:   */  
/* 79:   */  public static void main(String[] argv)
/* 80:   */  {
/* 81:   */    try
/* 82:   */    {
/* 83:83 */      AppGameContainer container = new AppGameContainer(new TexturePaintTest());
/* 84:84 */      container.setDisplayMode(800, 600, false);
/* 85:85 */      container.start();
/* 86:   */    } catch (SlickException e) {
/* 87:87 */      e.printStackTrace();
/* 88:   */    }
/* 89:   */  }
/* 90:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.TexturePaintTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */