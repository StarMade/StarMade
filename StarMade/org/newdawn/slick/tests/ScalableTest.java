/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.Game;
/*  7:   */import org.newdawn.slick.GameContainer;
/*  8:   */import org.newdawn.slick.Graphics;
/*  9:   */import org.newdawn.slick.Input;
/* 10:   */import org.newdawn.slick.ScalableGame;
/* 11:   */import org.newdawn.slick.SlickException;
/* 12:   */
/* 17:   */public class ScalableTest
/* 18:   */  extends BasicGame
/* 19:   */{
/* 20:   */  public ScalableTest()
/* 21:   */  {
/* 22:22 */    super("Scalable Test For Widescreen");
/* 23:   */  }
/* 24:   */  
/* 27:   */  public void init(GameContainer container)
/* 28:   */    throws SlickException
/* 29:   */  {}
/* 30:   */  
/* 33:   */  public void update(GameContainer container, int delta)
/* 34:   */    throws SlickException
/* 35:   */  {}
/* 36:   */  
/* 38:   */  public void render(GameContainer container, Graphics g)
/* 39:   */    throws SlickException
/* 40:   */  {
/* 41:41 */    g.setColor(new Color(0.4F, 0.6F, 0.8F));
/* 42:42 */    g.fillRect(0.0F, 0.0F, 1024.0F, 568.0F);
/* 43:43 */    g.setColor(Color.white);
/* 44:44 */    g.drawRect(5.0F, 5.0F, 1014.0F, 558.0F);
/* 45:   */    
/* 46:46 */    g.setColor(Color.white);
/* 47:47 */    g.drawString(container.getInput().getMouseX() + "," + container.getInput().getMouseY(), 10.0F, 400.0F);
/* 48:48 */    g.setColor(Color.red);
/* 49:49 */    g.fillOval(container.getInput().getMouseX() - 10, container.getInput().getMouseY() - 10, 20.0F, 20.0F);
/* 50:   */  }
/* 51:   */  
/* 81:   */  public static void main(String[] argv)
/* 82:   */  {
/* 83:   */    try
/* 84:   */    {
/* 85:85 */      ScalableGame game = new ScalableGame(new ScalableTest(), 1024, 568, true)
/* 86:   */      {
/* 87:   */        protected void renderOverlay(GameContainer container, Graphics g) {
/* 88:88 */          g.setColor(Color.white);
/* 89:89 */          g.drawString("Outside The Game", 350.0F, 10.0F);
/* 90:90 */          g.drawString(container.getInput().getMouseX() + "," + container.getInput().getMouseY(), 400.0F, 20.0F);
/* 91:   */        }
/* 92:   */        
/* 94:94 */      };
/* 95:95 */      AppGameContainer container = new AppGameContainer(game);
/* 96:96 */      container.setDisplayMode(800, 600, false);
/* 97:97 */      container.start();
/* 98:   */    } catch (SlickException e) {
/* 99:99 */      e.printStackTrace();
/* 100:   */    }
/* 101:   */  }
/* 102:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ScalableTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */