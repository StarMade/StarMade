/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.GameContainer;
/*  6:   */import org.newdawn.slick.Graphics;
/*  7:   */import org.newdawn.slick.SlickException;
/*  8:   */import org.newdawn.slick.svg.Diagram;
/*  9:   */import org.newdawn.slick.svg.InkscapeLoader;
/* 10:   */import org.newdawn.slick.svg.SVGMorph;
/* 11:   */import org.newdawn.slick.svg.SimpleDiagramRenderer;
/* 12:   */
/* 20:   */public class MorphSVGTest
/* 21:   */  extends BasicGame
/* 22:   */{
/* 23:   */  private SVGMorph morph;
/* 24:   */  private Diagram base;
/* 25:   */  private float time;
/* 26:26 */  private float x = -300.0F;
/* 27:   */  
/* 30:   */  public MorphSVGTest()
/* 31:   */  {
/* 32:32 */    super("MorphShapeTest");
/* 33:   */  }
/* 34:   */  
/* 36:   */  public void init(GameContainer container)
/* 37:   */    throws SlickException
/* 38:   */  {
/* 39:39 */    this.base = InkscapeLoader.load("testdata/svg/walk1.svg");
/* 40:40 */    this.morph = new SVGMorph(this.base);
/* 41:41 */    this.morph.addStep(InkscapeLoader.load("testdata/svg/walk2.svg"));
/* 42:42 */    this.morph.addStep(InkscapeLoader.load("testdata/svg/walk3.svg"));
/* 43:43 */    this.morph.addStep(InkscapeLoader.load("testdata/svg/walk4.svg"));
/* 44:   */    
/* 45:45 */    container.setVSync(true);
/* 46:   */  }
/* 47:   */  
/* 50:   */  public void update(GameContainer container, int delta)
/* 51:   */    throws SlickException
/* 52:   */  {
/* 53:53 */    this.morph.updateMorphTime(delta * 0.003F);
/* 54:   */    
/* 55:55 */    this.x += delta * 0.2F;
/* 56:56 */    if (this.x > 550.0F) {
/* 57:57 */      this.x = -450.0F;
/* 58:   */    }
/* 59:   */  }
/* 60:   */  
/* 63:   */  public void render(GameContainer container, Graphics g)
/* 64:   */    throws SlickException
/* 65:   */  {
/* 66:66 */    g.translate(this.x, 0.0F);
/* 67:67 */    SimpleDiagramRenderer.render(g, this.morph);
/* 68:   */  }
/* 69:   */  
/* 74:   */  public static void main(String[] argv)
/* 75:   */  {
/* 76:   */    try
/* 77:   */    {
/* 78:78 */      AppGameContainer container = new AppGameContainer(new MorphSVGTest());
/* 79:   */      
/* 80:80 */      container.setDisplayMode(800, 600, false);
/* 81:81 */      container.start();
/* 82:   */    } catch (SlickException e) {
/* 83:83 */      e.printStackTrace();
/* 84:   */    }
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.MorphSVGTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */