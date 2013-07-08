/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.BigImage;
/*  6:   */import org.newdawn.slick.Font;
/*  7:   */import org.newdawn.slick.GameContainer;
/*  8:   */import org.newdawn.slick.Graphics;
/*  9:   */import org.newdawn.slick.Image;
/* 10:   */import org.newdawn.slick.Input;
/* 11:   */import org.newdawn.slick.SlickException;
/* 12:   */import org.newdawn.slick.SpriteSheet;
/* 13:   */
/* 19:   */public class BigSpriteSheetTest
/* 20:   */  extends BasicGame
/* 21:   */{
/* 22:   */  private Image original;
/* 23:   */  private SpriteSheet bigSheet;
/* 24:24 */  private boolean oldMethod = true;
/* 25:   */  
/* 28:   */  public BigSpriteSheetTest()
/* 29:   */  {
/* 30:30 */    super("Big SpriteSheet Test");
/* 31:   */  }
/* 32:   */  
/* 34:   */  public void init(GameContainer container)
/* 35:   */    throws SlickException
/* 36:   */  {
/* 37:37 */    this.original = new BigImage("testdata/bigimage.tga", 2, 256);
/* 38:38 */    this.bigSheet = new SpriteSheet(this.original, 16, 16);
/* 39:   */  }
/* 40:   */  
/* 43:   */  public void render(GameContainer container, Graphics g)
/* 44:   */  {
/* 45:45 */    if (this.oldMethod) {
/* 46:46 */      for (int x = 0; x < 43; x++) {
/* 47:47 */        for (int y = 0; y < 27; y++) {
/* 48:48 */          this.bigSheet.getSprite(x, y).draw(10 + x * 18, 50 + y * 18);
/* 49:   */        }
/* 50:   */      }
/* 51:   */    } else {
/* 52:52 */      this.bigSheet.startUse();
/* 53:53 */      for (int x = 0; x < 43; x++) {
/* 54:54 */        for (int y = 0; y < 27; y++) {
/* 55:55 */          this.bigSheet.renderInUse(10 + x * 18, 50 + y * 18, x, y);
/* 56:   */        }
/* 57:   */      }
/* 58:58 */      this.bigSheet.endUse();
/* 59:   */    }
/* 60:   */    
/* 61:61 */    g.drawString("Press space to toggle rendering method", 10.0F, 30.0F);
/* 62:   */    
/* 63:63 */    container.getDefaultFont().drawString(10.0F, 100.0F, "TEST");
/* 64:   */  }
/* 65:   */  
/* 69:   */  public static void main(String[] argv)
/* 70:   */  {
/* 71:   */    try
/* 72:   */    {
/* 73:73 */      AppGameContainer container = new AppGameContainer(new BigSpriteSheetTest());
/* 74:74 */      container.setDisplayMode(800, 600, false);
/* 75:75 */      container.start();
/* 76:   */    } catch (SlickException e) {
/* 77:77 */      e.printStackTrace();
/* 78:   */    }
/* 79:   */  }
/* 80:   */  
/* 82:   */  public void update(GameContainer container, int delta)
/* 83:   */    throws SlickException
/* 84:   */  {
/* 85:85 */    if (container.getInput().isKeyPressed(57)) {
/* 86:86 */      this.oldMethod = (!this.oldMethod);
/* 87:   */    }
/* 88:   */  }
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.BigSpriteSheetTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */