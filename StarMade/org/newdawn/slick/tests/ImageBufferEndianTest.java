/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.nio.ByteOrder;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.Color;
/*   7:    */import org.newdawn.slick.GameContainer;
/*   8:    */import org.newdawn.slick.Graphics;
/*   9:    */import org.newdawn.slick.Image;
/*  10:    */import org.newdawn.slick.ImageBuffer;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */
/*  23:    */public class ImageBufferEndianTest
/*  24:    */  extends BasicGame
/*  25:    */{
/*  26:    */  private ImageBuffer redImageBuffer;
/*  27:    */  private ImageBuffer blueImageBuffer;
/*  28:    */  private Image fromRed;
/*  29:    */  private Image fromBlue;
/*  30:    */  private String endian;
/*  31:    */  
/*  32:    */  public ImageBufferEndianTest()
/*  33:    */  {
/*  34: 34 */    super("ImageBuffer Endian Test");
/*  35:    */  }
/*  36:    */  
/*  40:    */  public static void main(String[] args)
/*  41:    */  {
/*  42:    */    try
/*  43:    */    {
/*  44: 44 */      AppGameContainer container = new AppGameContainer(new ImageBufferEndianTest());
/*  45: 45 */      container.setDisplayMode(800, 600, false);
/*  46: 46 */      container.start();
/*  47:    */    } catch (SlickException e) {
/*  48: 48 */      e.printStackTrace();
/*  49:    */    }
/*  50:    */  }
/*  51:    */  
/*  54:    */  public void render(GameContainer container, Graphics g)
/*  55:    */    throws SlickException
/*  56:    */  {
/*  57: 57 */    g.setColor(Color.white);
/*  58: 58 */    g.drawString("Endianness is " + this.endian, 10.0F, 100.0F);
/*  59:    */    
/*  60: 60 */    g.drawString("Image below should be red", 10.0F, 200.0F);
/*  61: 61 */    g.drawImage(this.fromRed, 10.0F, 220.0F);
/*  62: 62 */    g.drawString("Image below should be blue", 410.0F, 200.0F);
/*  63: 63 */    g.drawImage(this.fromBlue, 410.0F, 220.0F);
/*  64:    */  }
/*  65:    */  
/*  69:    */  public void init(GameContainer container)
/*  70:    */    throws SlickException
/*  71:    */  {
/*  72: 72 */    if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
/*  73: 73 */      this.endian = "Big endian";
/*  74: 74 */    } else if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
/*  75: 75 */      this.endian = "Little endian";
/*  76:    */    } else {
/*  77: 77 */      this.endian = "no idea";
/*  78:    */    }
/*  79: 79 */    this.redImageBuffer = new ImageBuffer(100, 100);
/*  80: 80 */    fillImageBufferWithColor(this.redImageBuffer, Color.red, 100, 100);
/*  81:    */    
/*  82: 82 */    this.blueImageBuffer = new ImageBuffer(100, 100);
/*  83: 83 */    fillImageBufferWithColor(this.blueImageBuffer, Color.blue, 100, 100);
/*  84:    */    
/*  85: 85 */    this.fromRed = this.redImageBuffer.getImage();
/*  86: 86 */    this.fromBlue = this.blueImageBuffer.getImage();
/*  87:    */  }
/*  88:    */  
/*  96:    */  private void fillImageBufferWithColor(ImageBuffer buffer, Color c, int width, int height)
/*  97:    */  {
/*  98: 98 */    for (int x = 0; x < width; x++) {
/*  99: 99 */      for (int y = 0; y < height; y++) {
/* 100:100 */        buffer.setRGBA(x, y, c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
/* 101:    */      }
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 105:    */  public void update(GameContainer container, int delta)
/* 106:    */    throws SlickException
/* 107:    */  {}
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ImageBufferEndianTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */