/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.Image;
/*  9:   */import org.newdawn.slick.SlickException;
/* 10:   */
/* 18:   */public class AlphaMapTest
/* 19:   */  extends BasicGame
/* 20:   */{
/* 21:   */  private Image alphaMap;
/* 22:   */  private Image textureMap;
/* 23:   */  
/* 24:   */  public AlphaMapTest()
/* 25:   */  {
/* 26:26 */    super("AlphaMap Test");
/* 27:   */  }
/* 28:   */  
/* 30:   */  public void init(GameContainer container)
/* 31:   */    throws SlickException
/* 32:   */  {
/* 33:33 */    this.alphaMap = new Image("testdata/alphamap.png");
/* 34:34 */    this.textureMap = new Image("testdata/grass.png");
/* 35:35 */    container.getGraphics().setBackground(Color.black);
/* 36:   */  }
/* 37:   */  
/* 41:   */  public void update(GameContainer container, int delta)
/* 42:   */    throws SlickException
/* 43:   */  {}
/* 44:   */  
/* 47:   */  public void render(GameContainer container, Graphics g)
/* 48:   */    throws SlickException
/* 49:   */  {
/* 50:50 */    g.clearAlphaMap();
/* 51:51 */    g.setDrawMode(Graphics.MODE_NORMAL);
/* 52:52 */    this.textureMap.draw(10.0F, 50.0F);
/* 53:53 */    g.setColor(Color.red);
/* 54:54 */    g.fillRect(290.0F, 40.0F, 200.0F, 200.0F);
/* 55:55 */    g.setColor(Color.white);
/* 56:   */    
/* 57:57 */    g.setDrawMode(Graphics.MODE_ALPHA_MAP);
/* 58:58 */    this.alphaMap.draw(300.0F, 50.0F);
/* 59:59 */    g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
/* 60:60 */    this.textureMap.draw(300.0F, 50.0F);
/* 61:61 */    g.setDrawMode(Graphics.MODE_NORMAL);
/* 62:   */  }
/* 63:   */  
/* 68:   */  public void keyPressed(int key, char c) {}
/* 69:   */  
/* 73:   */  public static void main(String[] argv)
/* 74:   */  {
/* 75:   */    try
/* 76:   */    {
/* 77:77 */      AppGameContainer container = new AppGameContainer(new AlphaMapTest());
/* 78:78 */      container.setDisplayMode(800, 600, false);
/* 79:79 */      container.start();
/* 80:   */    } catch (SlickException e) {
/* 81:81 */      e.printStackTrace();
/* 82:   */    }
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.AlphaMapTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */