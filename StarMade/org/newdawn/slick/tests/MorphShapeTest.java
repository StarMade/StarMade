/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.SlickException;
/*  9:   */import org.newdawn.slick.geom.MorphShape;
/* 10:   */import org.newdawn.slick.geom.Rectangle;
/* 11:   */import org.newdawn.slick.geom.Shape;
/* 12:   */import org.newdawn.slick.geom.Transform;
/* 13:   */
/* 24:   */public class MorphShapeTest
/* 25:   */  extends BasicGame
/* 26:   */{
/* 27:   */  private Shape a;
/* 28:   */  private Shape b;
/* 29:   */  private Shape c;
/* 30:   */  private MorphShape morph;
/* 31:   */  private float time;
/* 32:   */  
/* 33:   */  public MorphShapeTest()
/* 34:   */  {
/* 35:35 */    super("MorphShapeTest");
/* 36:   */  }
/* 37:   */  
/* 39:   */  public void init(GameContainer container)
/* 40:   */    throws SlickException
/* 41:   */  {
/* 42:42 */    this.a = new Rectangle(100.0F, 100.0F, 50.0F, 200.0F);
/* 43:43 */    this.a = this.a.transform(Transform.createRotateTransform(0.1F, 100.0F, 100.0F));
/* 44:44 */    this.b = new Rectangle(200.0F, 100.0F, 50.0F, 200.0F);
/* 45:45 */    this.b = this.b.transform(Transform.createRotateTransform(-0.6F, 100.0F, 100.0F));
/* 46:46 */    this.c = new Rectangle(300.0F, 100.0F, 50.0F, 200.0F);
/* 47:47 */    this.c = this.c.transform(Transform.createRotateTransform(-0.2F, 100.0F, 100.0F));
/* 48:   */    
/* 49:49 */    this.morph = new MorphShape(this.a);
/* 50:50 */    this.morph.addShape(this.b);
/* 51:51 */    this.morph.addShape(this.c);
/* 52:   */    
/* 53:53 */    container.setVSync(true);
/* 54:   */  }
/* 55:   */  
/* 58:   */  public void update(GameContainer container, int delta)
/* 59:   */    throws SlickException
/* 60:   */  {
/* 61:61 */    this.time += delta * 0.001F;
/* 62:62 */    this.morph.setMorphTime(this.time);
/* 63:   */  }
/* 64:   */  
/* 67:   */  public void render(GameContainer container, Graphics g)
/* 68:   */    throws SlickException
/* 69:   */  {
/* 70:70 */    g.setColor(Color.green);
/* 71:71 */    g.draw(this.a);
/* 72:72 */    g.setColor(Color.red);
/* 73:73 */    g.draw(this.b);
/* 74:74 */    g.setColor(Color.blue);
/* 75:75 */    g.draw(this.c);
/* 76:76 */    g.setColor(Color.white);
/* 77:77 */    g.draw(this.morph);
/* 78:   */  }
/* 79:   */  
/* 84:   */  public static void main(String[] argv)
/* 85:   */  {
/* 86:   */    try
/* 87:   */    {
/* 88:88 */      AppGameContainer container = new AppGameContainer(new MorphShapeTest());
/* 89:   */      
/* 90:90 */      container.setDisplayMode(800, 600, false);
/* 91:91 */      container.start();
/* 92:   */    } catch (SlickException e) {
/* 93:93 */      e.printStackTrace();
/* 94:   */    }
/* 95:   */  }
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.MorphShapeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */