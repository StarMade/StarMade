/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.GameContainer;
/*   6:    */import org.newdawn.slick.Graphics;
/*   7:    */import org.newdawn.slick.Music;
/*   8:    */import org.newdawn.slick.MusicListener;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */
/*  15:    */public class MusicListenerTest
/*  16:    */  extends BasicGame
/*  17:    */  implements MusicListener
/*  18:    */{
/*  19: 19 */  private boolean musicEnded = false;
/*  20:    */  
/*  21: 21 */  private boolean musicSwapped = false;
/*  22:    */  
/*  24:    */  private Music music;
/*  25:    */  
/*  26:    */  private Music stream;
/*  27:    */  
/*  29:    */  public MusicListenerTest()
/*  30:    */  {
/*  31: 31 */    super("Music Listener Test");
/*  32:    */  }
/*  33:    */  
/*  35:    */  public void init(GameContainer container)
/*  36:    */    throws SlickException
/*  37:    */  {
/*  38: 38 */    this.music = new Music("testdata/restart.ogg", false);
/*  39: 39 */    this.stream = new Music("testdata/restart.ogg", false);
/*  40:    */    
/*  41: 41 */    this.music.addListener(this);
/*  42: 42 */    this.stream.addListener(this);
/*  43:    */  }
/*  44:    */  
/*  47:    */  public void update(GameContainer container, int delta)
/*  48:    */    throws SlickException
/*  49:    */  {}
/*  50:    */  
/*  53:    */  public void musicEnded(Music music)
/*  54:    */  {
/*  55: 55 */    this.musicEnded = true;
/*  56:    */  }
/*  57:    */  
/*  60:    */  public void musicSwapped(Music music, Music newMusic)
/*  61:    */  {
/*  62: 62 */    this.musicSwapped = true;
/*  63:    */  }
/*  64:    */  
/*  66:    */  public void render(GameContainer container, Graphics g)
/*  67:    */    throws SlickException
/*  68:    */  {
/*  69: 69 */    g.drawString("Press M to play music", 100.0F, 100.0F);
/*  70: 70 */    g.drawString("Press S to stream music", 100.0F, 150.0F);
/*  71: 71 */    if (this.musicEnded) {
/*  72: 72 */      g.drawString("Music Ended", 100.0F, 200.0F);
/*  73:    */    }
/*  74: 74 */    if (this.musicSwapped) {
/*  75: 75 */      g.drawString("Music Swapped", 100.0F, 250.0F);
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  81:    */  public void keyPressed(int key, char c)
/*  82:    */  {
/*  83: 83 */    if (key == 50) {
/*  84: 84 */      this.musicEnded = false;
/*  85: 85 */      this.musicSwapped = false;
/*  86: 86 */      this.music.play();
/*  87:    */    }
/*  88: 88 */    if (key == 31) {
/*  89: 89 */      this.musicEnded = false;
/*  90: 90 */      this.musicSwapped = false;
/*  91: 91 */      this.stream.play();
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  98:    */  public static void main(String[] argv)
/*  99:    */  {
/* 100:    */    try
/* 101:    */    {
/* 102:102 */      AppGameContainer container = new AppGameContainer(new MusicListenerTest());
/* 103:103 */      container.setDisplayMode(800, 600, false);
/* 104:104 */      container.start();
/* 105:    */    } catch (SlickException e) {
/* 106:106 */      e.printStackTrace();
/* 107:    */    }
/* 108:    */  }
/* 109:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.MusicListenerTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */