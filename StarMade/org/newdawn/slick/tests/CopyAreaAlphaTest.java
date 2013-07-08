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
/* 18:   */public class CopyAreaAlphaTest
/* 19:   */  extends BasicGame
/* 20:   */{
/* 21:   */  private Image textureMap;
/* 22:   */  private Image copy;
/* 23:   */  
/* 24:   */  public CopyAreaAlphaTest()
/* 25:   */  {
/* 26:26 */    super("CopyArea Alpha Test");
/* 27:   */  }
/* 28:   */  
/* 30:   */  public void init(GameContainer container)
/* 31:   */    throws SlickException
/* 32:   */  {
/* 33:33 */    this.textureMap = new Image("testdata/grass.png");
/* 34:34 */    container.getGraphics().setBackground(Color.black);
/* 35:35 */    this.copy = new Image(100, 100);
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
/* 52:52 */    g.setColor(Color.white);
/* 53:53 */    g.fillOval(100.0F, 100.0F, 150.0F, 150.0F);
/* 54:54 */    this.textureMap.draw(10.0F, 50.0F);
/* 55:   */    
/* 56:56 */    g.copyArea(this.copy, 100, 100);
/* 57:57 */    g.setColor(Color.red);
/* 58:58 */    g.fillRect(300.0F, 100.0F, 200.0F, 200.0F);
/* 59:59 */    this.copy.draw(350.0F, 150.0F);
/* 60:   */  }
/* 61:   */  
/* 66:   */  public void keyPressed(int key, char c) {}
/* 67:   */  
/* 71:   */  public static void main(String[] argv)
/* 72:   */  {
/* 73:   */    try
/* 74:   */    {
/* 75:75 */      AppGameContainer container = new AppGameContainer(new CopyAreaAlphaTest());
/* 76:76 */      container.setDisplayMode(800, 600, false);
/* 77:77 */      container.start();
/* 78:   */    } catch (SlickException e) {
/* 79:79 */      e.printStackTrace();
/* 80:   */    }
/* 81:   */  }
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.CopyAreaAlphaTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */