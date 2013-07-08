/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.BigImage;
/*   6:    */import org.newdawn.slick.Color;
/*   7:    */import org.newdawn.slick.GameContainer;
/*   8:    */import org.newdawn.slick.Graphics;
/*   9:    */import org.newdawn.slick.Image;
/*  10:    */import org.newdawn.slick.Input;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */import org.newdawn.slick.SpriteSheet;
/*  13:    */
/*  26:    */public class BigImageTest
/*  27:    */  extends BasicGame
/*  28:    */{
/*  29:    */  private Image original;
/*  30:    */  private Image image;
/*  31:    */  private Image imageX;
/*  32:    */  private Image imageY;
/*  33:    */  private Image sub;
/*  34:    */  private Image scaledSub;
/*  35:    */  private float x;
/*  36:    */  private float y;
/*  37: 37 */  private float ang = 30.0F;
/*  38:    */  
/*  40:    */  private SpriteSheet bigSheet;
/*  41:    */  
/*  43:    */  public BigImageTest()
/*  44:    */  {
/*  45: 45 */    super("Big Image Test");
/*  46:    */  }
/*  47:    */  
/*  50:    */  public void init(GameContainer container)
/*  51:    */    throws SlickException
/*  52:    */  {
/*  53: 53 */    this.original = (this.image = new BigImage("testdata/bigimage.tga", 2, 512));
/*  54: 54 */    this.sub = this.image.getSubImage(210, 210, 200, 130);
/*  55: 55 */    this.scaledSub = this.sub.getScaledCopy(2.0F);
/*  56: 56 */    this.image = this.image.getScaledCopy(0.3F);
/*  57: 57 */    this.imageX = this.image.getFlippedCopy(true, false);
/*  58: 58 */    this.imageY = this.imageX.getFlippedCopy(true, true);
/*  59:    */    
/*  60: 60 */    this.bigSheet = new SpriteSheet(this.original, 16, 16);
/*  61:    */  }
/*  62:    */  
/*  65:    */  public void render(GameContainer container, Graphics g)
/*  66:    */  {
/*  67: 67 */    this.original.draw(0.0F, 0.0F, new Color(1.0F, 1.0F, 1.0F, 0.4F));
/*  68:    */    
/*  69: 69 */    this.image.draw(this.x, this.y);
/*  70: 70 */    this.imageX.draw(this.x + 400.0F, this.y);
/*  71: 71 */    this.imageY.draw(this.x, this.y + 300.0F);
/*  72: 72 */    this.scaledSub.draw(this.x + 300.0F, this.y + 300.0F);
/*  73:    */    
/*  74: 74 */    this.bigSheet.getSprite(7, 5).draw(50.0F, 10.0F);
/*  75: 75 */    g.setColor(Color.white);
/*  76: 76 */    g.drawRect(50.0F, 10.0F, 64.0F, 64.0F);
/*  77: 77 */    g.rotate(this.x + 400.0F, this.y + 165.0F, this.ang);
/*  78: 78 */    g.drawImage(this.sub, this.x + 300.0F, this.y + 100.0F);
/*  79:    */  }
/*  80:    */  
/*  84:    */  public static void main(String[] argv)
/*  85:    */  {
/*  86:    */    try
/*  87:    */    {
/*  88: 88 */      AppGameContainer container = new AppGameContainer(new BigImageTest());
/*  89: 89 */      container.setDisplayMode(800, 600, false);
/*  90: 90 */      container.start();
/*  91:    */    } catch (SlickException e) {
/*  92: 92 */      e.printStackTrace();
/*  93:    */    }
/*  94:    */  }
/*  95:    */  
/*  97:    */  public void update(GameContainer container, int delta)
/*  98:    */    throws SlickException
/*  99:    */  {
/* 100:100 */    this.ang += delta * 0.1F;
/* 101:    */    
/* 102:102 */    if (container.getInput().isKeyDown(203)) {
/* 103:103 */      this.x -= delta * 0.1F;
/* 104:    */    }
/* 105:105 */    if (container.getInput().isKeyDown(205)) {
/* 106:106 */      this.x += delta * 0.1F;
/* 107:    */    }
/* 108:108 */    if (container.getInput().isKeyDown(200)) {
/* 109:109 */      this.y -= delta * 0.1F;
/* 110:    */    }
/* 111:111 */    if (container.getInput().isKeyDown(208)) {
/* 112:112 */      this.y += delta * 0.1F;
/* 113:    */    }
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.BigImageTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */