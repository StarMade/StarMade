/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.CachedRender;
/*  6:   */import org.newdawn.slick.Color;
/*  7:   */import org.newdawn.slick.GameContainer;
/*  8:   */import org.newdawn.slick.Graphics;
/*  9:   */import org.newdawn.slick.Input;
/* 10:   */import org.newdawn.slick.SlickException;
/* 11:   */
/* 21:   */public class CachedRenderTest
/* 22:   */  extends BasicGame
/* 23:   */{
/* 24:   */  private Runnable operations;
/* 25:   */  private CachedRender cached;
/* 26:   */  private boolean drawCached;
/* 27:   */  
/* 28:   */  public CachedRenderTest()
/* 29:   */  {
/* 30:30 */    super("Cached Render Test");
/* 31:   */  }
/* 32:   */  
/* 34:   */  public void init(final GameContainer container)
/* 35:   */    throws SlickException
/* 36:   */  {
/* 37:37 */    this.operations = new Runnable() {
/* 38:   */      public void run() {
/* 39:39 */        for (int i = 0; i < 100; i++) {
/* 40:40 */          int c = i + 100;
/* 41:41 */          container.getGraphics().setColor(new Color(c, c, c, c));
/* 42:42 */          container.getGraphics().drawOval(i * 5 + 50, i * 3 + 50, 100.0F, 100.0F);
/* 43:   */        }
/* 44:   */        
/* 45:   */      }
/* 46:46 */    };
/* 47:47 */    this.cached = new CachedRender(this.operations);
/* 48:   */  }
/* 49:   */  
/* 51:   */  public void update(GameContainer container, int delta)
/* 52:   */    throws SlickException
/* 53:   */  {
/* 54:54 */    if (container.getInput().isKeyPressed(57)) {
/* 55:55 */      this.drawCached = (!this.drawCached);
/* 56:   */    }
/* 57:   */  }
/* 58:   */  
/* 60:   */  public void render(GameContainer container, Graphics g)
/* 61:   */    throws SlickException
/* 62:   */  {
/* 63:63 */    g.setColor(Color.white);
/* 64:64 */    g.drawString("Press space to toggle caching", 10.0F, 130.0F);
/* 65:65 */    if (this.drawCached) {
/* 66:66 */      g.drawString("Drawing from cache", 10.0F, 100.0F);
/* 67:67 */      this.cached.render();
/* 68:   */    } else {
/* 69:69 */      g.drawString("Drawing direct", 10.0F, 100.0F);
/* 70:70 */      this.operations.run();
/* 71:   */    }
/* 72:   */  }
/* 73:   */  
/* 77:   */  public static void main(String[] argv)
/* 78:   */  {
/* 79:   */    try
/* 80:   */    {
/* 81:81 */      AppGameContainer container = new AppGameContainer(new CachedRenderTest());
/* 82:82 */      container.setDisplayMode(800, 600, false);
/* 83:83 */      container.start();
/* 84:   */    } catch (SlickException e) {
/* 85:85 */      e.printStackTrace();
/* 86:   */    }
/* 87:   */  }
/* 88:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.CachedRenderTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */