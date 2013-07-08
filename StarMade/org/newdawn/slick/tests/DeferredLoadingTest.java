/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import org.newdawn.slick.AngelCodeFont;
/*   5:    */import org.newdawn.slick.AppGameContainer;
/*   6:    */import org.newdawn.slick.BasicGame;
/*   7:    */import org.newdawn.slick.Font;
/*   8:    */import org.newdawn.slick.GameContainer;
/*   9:    */import org.newdawn.slick.Graphics;
/*  10:    */import org.newdawn.slick.Image;
/*  11:    */import org.newdawn.slick.Music;
/*  12:    */import org.newdawn.slick.SlickException;
/*  13:    */import org.newdawn.slick.Sound;
/*  14:    */import org.newdawn.slick.loading.DeferredResource;
/*  15:    */import org.newdawn.slick.loading.LoadingList;
/*  16:    */
/*  31:    */public class DeferredLoadingTest
/*  32:    */  extends BasicGame
/*  33:    */{
/*  34:    */  private Music music;
/*  35:    */  private Sound sound;
/*  36:    */  private Image image;
/*  37:    */  private Font font;
/*  38:    */  private DeferredResource nextResource;
/*  39:    */  private boolean started;
/*  40:    */  
/*  41:    */  public DeferredLoadingTest()
/*  42:    */  {
/*  43: 43 */    super("Deferred Loading Test");
/*  44:    */  }
/*  45:    */  
/*  47:    */  public void init(GameContainer container)
/*  48:    */    throws SlickException
/*  49:    */  {
/*  50: 50 */    LoadingList.setDeferredLoading(true);
/*  51:    */    
/*  52: 52 */    new Sound("testdata/cbrown01.wav");
/*  53: 53 */    new Sound("testdata/engine.wav");
/*  54: 54 */    this.sound = new Sound("testdata/restart.ogg");
/*  55: 55 */    new Music("testdata/testloop.ogg");
/*  56: 56 */    this.music = new Music("testdata/SMB-X.XM");
/*  57:    */    
/*  58: 58 */    new Image("testdata/cursor.png");
/*  59: 59 */    new Image("testdata/cursor.tga");
/*  60: 60 */    new Image("testdata/cursor.png");
/*  61: 61 */    new Image("testdata/cursor.png");
/*  62: 62 */    new Image("testdata/dungeontiles.gif");
/*  63: 63 */    new Image("testdata/logo.gif");
/*  64: 64 */    this.image = new Image("testdata/logo.tga");
/*  65: 65 */    new Image("testdata/logo.png");
/*  66: 66 */    new Image("testdata/rocket.png");
/*  67: 67 */    new Image("testdata/testpack.png");
/*  68:    */    
/*  69: 69 */    this.font = new AngelCodeFont("testdata/demo.fnt", "testdata/demo_00.tga");
/*  70:    */  }
/*  71:    */  
/*  74:    */  public void render(GameContainer container, Graphics g)
/*  75:    */  {
/*  76: 76 */    if (this.nextResource != null) {
/*  77: 77 */      g.drawString("Loading: " + this.nextResource.getDescription(), 100.0F, 100.0F);
/*  78:    */    }
/*  79:    */    
/*  80: 80 */    int total = LoadingList.get().getTotalResources();
/*  81: 81 */    int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
/*  82:    */    
/*  83: 83 */    float bar = loaded / total;
/*  84: 84 */    g.fillRect(100.0F, 150.0F, loaded * 40, 20.0F);
/*  85: 85 */    g.drawRect(100.0F, 150.0F, total * 40, 20.0F);
/*  86:    */    
/*  87: 87 */    if (this.started) {
/*  88: 88 */      this.image.draw(100.0F, 200.0F);
/*  89: 89 */      this.font.drawString(100.0F, 500.0F, "LOADING COMPLETE");
/*  90:    */    }
/*  91:    */  }
/*  92:    */  
/*  94:    */  public void update(GameContainer container, int delta)
/*  95:    */    throws SlickException
/*  96:    */  {
/*  97: 97 */    if (this.nextResource != null) {
/*  98:    */      try {
/*  99: 99 */        this.nextResource.load();
/* 100:    */        try {
/* 101:101 */          Thread.sleep(50L);
/* 102:    */        } catch (Exception e) {}
/* 103:103 */      } catch (IOException e) { throw new SlickException("Failed to load: " + this.nextResource.getDescription(), e);
/* 104:    */      }
/* 105:    */      
/* 106:106 */      this.nextResource = null;
/* 107:    */    }
/* 108:    */    
/* 109:109 */    if (LoadingList.get().getRemainingResources() > 0) {
/* 110:110 */      this.nextResource = LoadingList.get().getNext();
/* 111:    */    }
/* 112:112 */    else if (!this.started) {
/* 113:113 */      this.started = true;
/* 114:114 */      this.music.loop();
/* 115:115 */      this.sound.play();
/* 116:    */    }
/* 117:    */  }
/* 118:    */  
/* 123:    */  public static void main(String[] argv)
/* 124:    */  {
/* 125:    */    try
/* 126:    */    {
/* 127:127 */      AppGameContainer container = new AppGameContainer(new DeferredLoadingTest());
/* 128:128 */      container.setDisplayMode(800, 600, false);
/* 129:129 */      container.start();
/* 130:    */    } catch (SlickException e) {
/* 131:131 */      e.printStackTrace();
/* 132:    */    }
/* 133:    */  }
/* 134:    */  
/* 135:    */  public void keyPressed(int key, char c) {}
/* 136:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.DeferredLoadingTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */