/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.BasicGame;
/*  4:   */import org.newdawn.slick.GameContainer;
/*  5:   */import org.newdawn.slick.Graphics;
/*  6:   */import org.newdawn.slick.SlickException;
/*  7:   */import org.newdawn.slick.tiled.TiledMap;
/*  8:   */import org.newdawn.slick.util.Bootstrap;
/*  9:   */
/* 16:   */public class IsoTiledTest
/* 17:   */  extends BasicGame
/* 18:   */{
/* 19:   */  private TiledMap tilemap;
/* 20:   */  
/* 21:   */  public IsoTiledTest()
/* 22:   */  {
/* 23:23 */    super("Isometric Tiled Map Test");
/* 24:   */  }
/* 25:   */  
/* 28:   */  public void init(GameContainer container)
/* 29:   */    throws SlickException
/* 30:   */  {
/* 31:31 */    this.tilemap = new TiledMap("testdata/isoexample.tmx", "testdata/");
/* 32:   */  }
/* 33:   */  
/* 38:   */  public void update(GameContainer container, int delta)
/* 39:   */    throws SlickException
/* 40:   */  {}
/* 41:   */  
/* 45:   */  public void render(GameContainer container, Graphics g)
/* 46:   */    throws SlickException
/* 47:   */  {
/* 48:48 */    this.tilemap.render(350, 150);
/* 49:   */  }
/* 50:   */  
/* 55:   */  public static void main(String[] argv)
/* 56:   */  {
/* 57:57 */    Bootstrap.runAsApplication(new IsoTiledTest(), 800, 600, false);
/* 58:   */  }
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.IsoTiledTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */