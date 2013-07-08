/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.Input;
/*  9:   */import org.newdawn.slick.SlickException;
/* 10:   */import org.newdawn.slick.geom.Path;
/* 11:   */import org.newdawn.slick.geom.Polygon;
/* 12:   */import org.newdawn.slick.opengl.renderer.LineStripRenderer;
/* 13:   */import org.newdawn.slick.opengl.renderer.Renderer;
/* 14:   */
/* 18:   */public class LineRenderTest
/* 19:   */  extends BasicGame
/* 20:   */{
/* 21:21 */  private Polygon polygon = new Polygon();
/* 22:   */  
/* 23:23 */  private Path path = new Path(100.0F, 100.0F);
/* 24:   */  
/* 25:25 */  private float width = 10.0F;
/* 26:   */  
/* 27:27 */  private boolean antialias = true;
/* 28:   */  
/* 31:   */  public LineRenderTest()
/* 32:   */  {
/* 33:33 */    super("LineRenderTest");
/* 34:   */  }
/* 35:   */  
/* 37:   */  public void init(GameContainer container)
/* 38:   */    throws SlickException
/* 39:   */  {
/* 40:40 */    this.polygon.addPoint(100.0F, 100.0F);
/* 41:41 */    this.polygon.addPoint(200.0F, 80.0F);
/* 42:42 */    this.polygon.addPoint(320.0F, 150.0F);
/* 43:43 */    this.polygon.addPoint(230.0F, 210.0F);
/* 44:44 */    this.polygon.addPoint(170.0F, 260.0F);
/* 45:   */    
/* 46:46 */    this.path.curveTo(200.0F, 200.0F, 200.0F, 100.0F, 100.0F, 200.0F);
/* 47:47 */    this.path.curveTo(400.0F, 100.0F, 400.0F, 200.0F, 200.0F, 100.0F);
/* 48:48 */    this.path.curveTo(500.0F, 500.0F, 400.0F, 200.0F, 200.0F, 100.0F);
/* 49:   */  }
/* 50:   */  
/* 52:   */  public void update(GameContainer container, int delta)
/* 53:   */    throws SlickException
/* 54:   */  {
/* 55:55 */    if (container.getInput().isKeyPressed(57)) {
/* 56:56 */      this.antialias = (!this.antialias);
/* 57:   */    }
/* 58:   */  }
/* 59:   */  
/* 61:   */  public void render(GameContainer container, Graphics g)
/* 62:   */    throws SlickException
/* 63:   */  {
/* 64:64 */    g.setAntiAlias(this.antialias);
/* 65:65 */    g.setLineWidth(50.0F);
/* 66:66 */    g.setColor(Color.red);
/* 67:67 */    g.draw(this.path);
/* 68:   */  }
/* 69:   */  
/* 88:   */  public static void main(String[] argv)
/* 89:   */  {
/* 90:   */    try
/* 91:   */    {
/* 92:92 */      Renderer.setLineStripRenderer(4);
/* 93:93 */      Renderer.getLineStripRenderer().setLineCaps(true);
/* 94:   */      
/* 95:95 */      AppGameContainer container = new AppGameContainer(new LineRenderTest());
/* 96:96 */      container.setDisplayMode(800, 600, false);
/* 97:97 */      container.start();
/* 98:   */    } catch (SlickException e) {
/* 99:99 */      e.printStackTrace();
/* 100:   */    }
/* 101:   */  }
/* 102:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.LineRenderTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */