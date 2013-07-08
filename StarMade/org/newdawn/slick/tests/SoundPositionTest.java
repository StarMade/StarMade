/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.Color;
/*  6:   */import org.newdawn.slick.GameContainer;
/*  7:   */import org.newdawn.slick.Graphics;
/*  8:   */import org.newdawn.slick.Music;
/*  9:   */import org.newdawn.slick.SlickException;
/* 10:   */import org.newdawn.slick.openal.SoundStore;
/* 11:   */
/* 20:   */public class SoundPositionTest
/* 21:   */  extends BasicGame
/* 22:   */{
/* 23:   */  private GameContainer myContainer;
/* 24:   */  private Music music;
/* 25:25 */  private int[] engines = new int[3];
/* 26:   */  
/* 29:   */  public SoundPositionTest()
/* 30:   */  {
/* 31:31 */    super("Music Position Test");
/* 32:   */  }
/* 33:   */  
/* 35:   */  public void init(GameContainer container)
/* 36:   */    throws SlickException
/* 37:   */  {
/* 38:38 */    SoundStore.get().setMaxSources(32);
/* 39:   */    
/* 40:40 */    this.myContainer = container;
/* 41:41 */    this.music = new Music("testdata/kirby.ogg", true);
/* 42:42 */    this.music.play();
/* 43:   */  }
/* 44:   */  
/* 47:   */  public void render(GameContainer container, Graphics g)
/* 48:   */  {
/* 49:49 */    g.setColor(Color.white);
/* 50:50 */    g.drawString("Position: " + this.music.getPosition(), 100.0F, 100.0F);
/* 51:51 */    g.drawString("Space - Pause/Resume", 100.0F, 130.0F);
/* 52:52 */    g.drawString("Right Arrow - Advance 5 seconds", 100.0F, 145.0F);
/* 53:   */  }
/* 54:   */  
/* 58:   */  public void update(GameContainer container, int delta) {}
/* 59:   */  
/* 63:   */  public void keyPressed(int key, char c)
/* 64:   */  {
/* 65:65 */    if (key == 57) {
/* 66:66 */      if (this.music.playing()) {
/* 67:67 */        this.music.pause();
/* 68:   */      } else {
/* 69:69 */        this.music.resume();
/* 70:   */      }
/* 71:   */    }
/* 72:72 */    if (key == 205) {
/* 73:73 */      this.music.setPosition(this.music.getPosition() + 5.0F);
/* 74:   */    }
/* 75:   */  }
/* 76:   */  
/* 80:   */  public static void main(String[] argv)
/* 81:   */  {
/* 82:   */    try
/* 83:   */    {
/* 84:84 */      AppGameContainer container = new AppGameContainer(new SoundPositionTest());
/* 85:85 */      container.setDisplayMode(800, 600, false);
/* 86:86 */      container.start();
/* 87:   */    } catch (SlickException e) {
/* 88:88 */      e.printStackTrace();
/* 89:   */    }
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.SoundPositionTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */