/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.SlickException;
/*   9:    */import org.newdawn.slick.geom.Curve;
/*  10:    */import org.newdawn.slick.geom.Polygon;
/*  11:    */import org.newdawn.slick.geom.Vector2f;
/*  12:    */
/*  18:    */public class CurveTest
/*  19:    */  extends BasicGame
/*  20:    */{
/*  21:    */  private Curve curve;
/*  22: 22 */  private Vector2f p1 = new Vector2f(100.0F, 300.0F);
/*  23:    */  
/*  24: 24 */  private Vector2f c1 = new Vector2f(100.0F, 100.0F);
/*  25:    */  
/*  26: 26 */  private Vector2f c2 = new Vector2f(300.0F, 100.0F);
/*  27:    */  
/*  28: 28 */  private Vector2f p2 = new Vector2f(300.0F, 300.0F);
/*  29:    */  
/*  31:    */  private Polygon poly;
/*  32:    */  
/*  35:    */  public CurveTest()
/*  36:    */  {
/*  37: 37 */    super("Curve Test");
/*  38:    */  }
/*  39:    */  
/*  41:    */  public void init(GameContainer container)
/*  42:    */    throws SlickException
/*  43:    */  {
/*  44: 44 */    container.getGraphics().setBackground(Color.white);
/*  45:    */    
/*  46: 46 */    this.curve = new Curve(this.p2, this.c2, this.c1, this.p1);
/*  47: 47 */    this.poly = new Polygon();
/*  48: 48 */    this.poly.addPoint(500.0F, 200.0F);
/*  49: 49 */    this.poly.addPoint(600.0F, 200.0F);
/*  50: 50 */    this.poly.addPoint(700.0F, 300.0F);
/*  51: 51 */    this.poly.addPoint(400.0F, 300.0F);
/*  52:    */  }
/*  53:    */  
/*  58:    */  public void update(GameContainer container, int delta)
/*  59:    */    throws SlickException
/*  60:    */  {}
/*  61:    */  
/*  65:    */  private void drawMarker(Graphics g, Vector2f p)
/*  66:    */  {
/*  67: 67 */    g.drawRect(p.x - 5.0F, p.y - 5.0F, 10.0F, 10.0F);
/*  68:    */  }
/*  69:    */  
/*  71:    */  public void render(GameContainer container, Graphics g)
/*  72:    */    throws SlickException
/*  73:    */  {
/*  74: 74 */    g.setColor(Color.gray);
/*  75: 75 */    drawMarker(g, this.p1);
/*  76: 76 */    drawMarker(g, this.p2);
/*  77: 77 */    g.setColor(Color.red);
/*  78: 78 */    drawMarker(g, this.c1);
/*  79: 79 */    drawMarker(g, this.c2);
/*  80:    */    
/*  81: 81 */    g.setColor(Color.black);
/*  82: 82 */    g.draw(this.curve);
/*  83: 83 */    g.fill(this.curve);
/*  84:    */    
/*  85: 85 */    g.draw(this.poly);
/*  86: 86 */    g.fill(this.poly);
/*  87:    */  }
/*  88:    */  
/*  92:    */  public static void main(String[] argv)
/*  93:    */  {
/*  94:    */    try
/*  95:    */    {
/*  96: 96 */      AppGameContainer container = new AppGameContainer(new CurveTest());
/*  97: 97 */      container.setDisplayMode(800, 600, false);
/*  98: 98 */      container.start();
/*  99:    */    } catch (SlickException e) {
/* 100:100 */      e.printStackTrace();
/* 101:    */    }
/* 102:    */  }
/* 103:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.CurveTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */