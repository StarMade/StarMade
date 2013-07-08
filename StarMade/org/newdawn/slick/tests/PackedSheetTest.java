/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.Animation;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.PackedSpriteSheet;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */import org.newdawn.slick.SpriteSheet;
/*  12:    */
/*  20:    */public class PackedSheetTest
/*  21:    */  extends BasicGame
/*  22:    */{
/*  23:    */  private PackedSpriteSheet sheet;
/*  24:    */  private GameContainer container;
/*  25: 25 */  private float r = -500.0F;
/*  26:    */  
/*  28:    */  private Image rocket;
/*  29:    */  
/*  30:    */  private Animation runner;
/*  31:    */  
/*  32:    */  private float ang;
/*  33:    */  
/*  35:    */  public PackedSheetTest()
/*  36:    */  {
/*  37: 37 */    super("Packed Sprite Sheet Test");
/*  38:    */  }
/*  39:    */  
/*  41:    */  public void init(GameContainer container)
/*  42:    */    throws SlickException
/*  43:    */  {
/*  44: 44 */    this.container = container;
/*  45:    */    
/*  46: 46 */    this.sheet = new PackedSpriteSheet("testdata/testpack.def", 2);
/*  47: 47 */    this.rocket = this.sheet.getSprite("rocket");
/*  48:    */    
/*  49: 49 */    SpriteSheet anim = this.sheet.getSpriteSheet("runner");
/*  50: 50 */    this.runner = new Animation();
/*  51:    */    
/*  52: 52 */    for (int y = 0; y < 2; y++) {
/*  53: 53 */      for (int x = 0; x < 6; x++) {
/*  54: 54 */        this.runner.addFrame(anim.getSprite(x, y), 50);
/*  55:    */      }
/*  56:    */    }
/*  57:    */  }
/*  58:    */  
/*  61:    */  public void render(GameContainer container, Graphics g)
/*  62:    */  {
/*  63: 63 */    this.rocket.draw((int)this.r, 100.0F);
/*  64: 64 */    this.runner.draw(250.0F, 250.0F);
/*  65: 65 */    g.scale(1.2F, 1.2F);
/*  66: 66 */    this.runner.draw(250.0F, 250.0F);
/*  67: 67 */    g.scale(1.2F, 1.2F);
/*  68: 68 */    this.runner.draw(250.0F, 250.0F);
/*  69: 69 */    g.resetTransform();
/*  70:    */    
/*  71: 71 */    g.rotate(670.0F, 470.0F, this.ang);
/*  72: 72 */    this.sheet.getSprite("floppy").draw(600.0F, 400.0F);
/*  73:    */  }
/*  74:    */  
/*  77:    */  public void update(GameContainer container, int delta)
/*  78:    */  {
/*  79: 79 */    this.r += delta * 0.4F;
/*  80: 80 */    if (this.r > 900.0F) {
/*  81: 81 */      this.r = -500.0F;
/*  82:    */    }
/*  83:    */    
/*  84: 84 */    this.ang += delta * 0.1F;
/*  85:    */  }
/*  86:    */  
/*  90:    */  public static void main(String[] argv)
/*  91:    */  {
/*  92:    */    try
/*  93:    */    {
/*  94: 94 */      AppGameContainer container = new AppGameContainer(new PackedSheetTest());
/*  95: 95 */      container.setDisplayMode(800, 600, false);
/*  96: 96 */      container.start();
/*  97:    */    } catch (SlickException e) {
/*  98: 98 */      e.printStackTrace();
/*  99:    */    }
/* 100:    */  }
/* 101:    */  
/* 104:    */  public void keyPressed(int key, char c)
/* 105:    */  {
/* 106:106 */    if (key == 1) {
/* 107:107 */      this.container.exit();
/* 108:    */    }
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.PackedSheetTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */