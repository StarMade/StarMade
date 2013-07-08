/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.GameContainer;
/*  6:   */import org.newdawn.slick.Graphics;
/*  7:   */import org.newdawn.slick.Input;
/*  8:   */import org.newdawn.slick.SlickException;
/*  9:   */
/* 17:   */public class KeyRepeatTest
/* 18:   */  extends BasicGame
/* 19:   */{
/* 20:   */  private int count;
/* 21:   */  private Input input;
/* 22:   */  
/* 23:   */  public KeyRepeatTest()
/* 24:   */  {
/* 25:25 */    super("KeyRepeatTest");
/* 26:   */  }
/* 27:   */  
/* 29:   */  public void init(GameContainer container)
/* 30:   */    throws SlickException
/* 31:   */  {
/* 32:32 */    this.input = container.getInput();
/* 33:33 */    this.input.enableKeyRepeat(300, 100);
/* 34:   */  }
/* 35:   */  
/* 38:   */  public void render(GameContainer container, Graphics g)
/* 39:   */  {
/* 40:40 */    g.drawString("Key Press Count: " + this.count, 100.0F, 100.0F);
/* 41:41 */    g.drawString("Press Space to Toggle Key Repeat", 100.0F, 150.0F);
/* 42:42 */    g.drawString("Key Repeat Enabled: " + this.input.isKeyRepeatEnabled(), 100.0F, 200.0F);
/* 43:   */  }
/* 44:   */  
/* 49:   */  public void update(GameContainer container, int delta) {}
/* 50:   */  
/* 54:   */  public static void main(String[] argv)
/* 55:   */  {
/* 56:   */    try
/* 57:   */    {
/* 58:58 */      AppGameContainer container = new AppGameContainer(new KeyRepeatTest());
/* 59:59 */      container.setDisplayMode(800, 600, false);
/* 60:60 */      container.start();
/* 61:   */    } catch (SlickException e) {
/* 62:62 */      e.printStackTrace();
/* 63:   */    }
/* 64:   */  }
/* 65:   */  
/* 68:   */  public void keyPressed(int key, char c)
/* 69:   */  {
/* 70:70 */    this.count += 1;
/* 71:71 */    if (key == 57) {
/* 72:72 */      if (this.input.isKeyRepeatEnabled()) {
/* 73:73 */        this.input.disableKeyRepeat();
/* 74:   */      } else {
/* 75:75 */        this.input.enableKeyRepeat(300, 100);
/* 76:   */      }
/* 77:   */    }
/* 78:   */  }
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.KeyRepeatTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */