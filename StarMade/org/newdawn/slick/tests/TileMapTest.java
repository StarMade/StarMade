/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.GameContainer;
/*   6:    */import org.newdawn.slick.Graphics;
/*   7:    */import org.newdawn.slick.SlickException;
/*   8:    */import org.newdawn.slick.tiled.TiledMap;
/*   9:    */
/*  25:    */public class TileMapTest
/*  26:    */  extends BasicGame
/*  27:    */{
/*  28:    */  private TiledMap map;
/*  29:    */  private String mapName;
/*  30:    */  private String monsterDifficulty;
/*  31:    */  private String nonExistingMapProperty;
/*  32:    */  private String nonExistingLayerProperty;
/*  33: 33 */  private int updateCounter = 0;
/*  34:    */  
/*  36: 36 */  private static int UPDATE_TIME = 1000;
/*  37:    */  
/*  39: 39 */  private int originalTileID = 0;
/*  40:    */  
/*  43:    */  public TileMapTest()
/*  44:    */  {
/*  45: 45 */    super("Tile Map Test");
/*  46:    */  }
/*  47:    */  
/*  49:    */  public void init(GameContainer container)
/*  50:    */    throws SlickException
/*  51:    */  {
/*  52: 52 */    this.map = new TiledMap("testdata/testmap.tmx", "testdata");
/*  53:    */    
/*  54: 54 */    this.mapName = this.map.getMapProperty("name", "Unknown map name");
/*  55: 55 */    this.monsterDifficulty = this.map.getLayerProperty(0, "monsters", "easy peasy");
/*  56: 56 */    this.nonExistingMapProperty = this.map.getMapProperty("zaphod", "Undefined map property");
/*  57: 57 */    this.nonExistingLayerProperty = this.map.getLayerProperty(1, "beeblebrox", "Undefined layer property");
/*  58:    */    
/*  60: 60 */    this.originalTileID = this.map.getTileId(10, 10, 0);
/*  61:    */  }
/*  62:    */  
/*  65:    */  public void render(GameContainer container, Graphics g)
/*  66:    */  {
/*  67: 67 */    this.map.render(10, 10, 4, 4, 15, 15);
/*  68:    */    
/*  69: 69 */    g.scale(0.35F, 0.35F);
/*  70: 70 */    this.map.render(1400, 0);
/*  71: 71 */    g.resetTransform();
/*  72:    */    
/*  73: 73 */    g.drawString("map name: " + this.mapName, 10.0F, 500.0F);
/*  74: 74 */    g.drawString("monster difficulty: " + this.monsterDifficulty, 10.0F, 550.0F);
/*  75:    */    
/*  76: 76 */    g.drawString("non existing map property: " + this.nonExistingMapProperty, 10.0F, 525.0F);
/*  77: 77 */    g.drawString("non existing layer property: " + this.nonExistingLayerProperty, 10.0F, 575.0F);
/*  78:    */  }
/*  79:    */  
/*  82:    */  public void update(GameContainer container, int delta)
/*  83:    */  {
/*  84: 84 */    this.updateCounter += delta;
/*  85: 85 */    if (this.updateCounter > UPDATE_TIME)
/*  86:    */    {
/*  87: 87 */      this.updateCounter -= UPDATE_TIME;
/*  88: 88 */      int currentTileID = this.map.getTileId(10, 10, 0);
/*  89: 89 */      if (currentTileID != this.originalTileID) {
/*  90: 90 */        this.map.setTileId(10, 10, 0, this.originalTileID);
/*  91:    */      } else {
/*  92: 92 */        this.map.setTileId(10, 10, 0, 1);
/*  93:    */      }
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/*  98:    */  public void keyPressed(int key, char c)
/*  99:    */  {
/* 100:100 */    if (key == 1) {
/* 101:101 */      System.exit(0);
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 108:    */  public static void main(String[] argv)
/* 109:    */  {
/* 110:    */    try
/* 111:    */    {
/* 112:112 */      AppGameContainer container = new AppGameContainer(new TileMapTest());
/* 113:113 */      container.setDisplayMode(800, 600, false);
/* 114:114 */      container.start();
/* 115:    */    } catch (SlickException e) {
/* 116:116 */      e.printStackTrace();
/* 117:    */    }
/* 118:    */  }
/* 119:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.TileMapTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */