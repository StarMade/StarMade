/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import org.newdawn.slick.AppGameContainer;
/*   5:    */import org.newdawn.slick.BasicGame;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Image;
/*   9:    */import org.newdawn.slick.Input;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */import org.newdawn.slick.imageout.ImageOut;
/*  12:    */import org.newdawn.slick.particles.ParticleIO;
/*  13:    */import org.newdawn.slick.particles.ParticleSystem;
/*  14:    */
/*  26:    */public class ImageOutTest
/*  27:    */  extends BasicGame
/*  28:    */{
/*  29:    */  private GameContainer container;
/*  30:    */  private ParticleSystem fire;
/*  31:    */  private Graphics g;
/*  32:    */  private Image copy;
/*  33:    */  private String message;
/*  34:    */  
/*  35:    */  public ImageOutTest()
/*  36:    */  {
/*  37: 37 */    super("Image Out Test");
/*  38:    */  }
/*  39:    */  
/*  41:    */  public void init(GameContainer container)
/*  42:    */    throws SlickException
/*  43:    */  {
/*  44: 44 */    this.container = container;
/*  45:    */    try
/*  46:    */    {
/*  47: 47 */      this.fire = ParticleIO.loadConfiguredSystem("testdata/system.xml");
/*  48:    */    } catch (IOException e) {
/*  49: 49 */      throw new SlickException("Failed to load particle systems", e);
/*  50:    */    }
/*  51:    */    
/*  52: 52 */    this.copy = new Image(400, 300);
/*  53: 53 */    String[] formats = ImageOut.getSupportedFormats();
/*  54: 54 */    this.message = "Formats supported: ";
/*  55: 55 */    for (int i = 0; i < formats.length; i++) {
/*  56: 56 */      this.message += formats[i];
/*  57: 57 */      if (i < formats.length - 1) {
/*  58: 58 */        this.message += ",";
/*  59:    */      }
/*  60:    */    }
/*  61:    */  }
/*  62:    */  
/*  65:    */  public void render(GameContainer container, Graphics g)
/*  66:    */  {
/*  67: 67 */    g.drawString("T - TGA Snapshot", 10.0F, 50.0F);
/*  68: 68 */    g.drawString("J - JPG Snapshot", 10.0F, 70.0F);
/*  69: 69 */    g.drawString("P - PNG Snapshot", 10.0F, 90.0F);
/*  70:    */    
/*  71: 71 */    g.setDrawMode(Graphics.MODE_ADD);
/*  72: 72 */    g.drawImage(this.copy, 200.0F, 300.0F);
/*  73: 73 */    g.setDrawMode(Graphics.MODE_NORMAL);
/*  74:    */    
/*  75: 75 */    g.drawString(this.message, 10.0F, 400.0F);
/*  76: 76 */    g.drawRect(200.0F, 0.0F, 400.0F, 300.0F);
/*  77: 77 */    g.translate(400.0F, 250.0F);
/*  78: 78 */    this.fire.render();
/*  79: 79 */    this.g = g;
/*  80:    */  }
/*  81:    */  
/*  86:    */  private void writeTo(String fname)
/*  87:    */    throws SlickException
/*  88:    */  {
/*  89: 89 */    this.g.copyArea(this.copy, 200, 0);
/*  90: 90 */    ImageOut.write(this.copy, fname);
/*  91: 91 */    this.message = ("Written " + fname);
/*  92:    */  }
/*  93:    */  
/*  95:    */  public void update(GameContainer container, int delta)
/*  96:    */    throws SlickException
/*  97:    */  {
/*  98: 98 */    this.fire.update(delta);
/*  99:    */    
/* 100:100 */    if (container.getInput().isKeyPressed(25)) {
/* 101:101 */      writeTo("ImageOutTest.png");
/* 102:    */    }
/* 103:103 */    if (container.getInput().isKeyPressed(36)) {
/* 104:104 */      writeTo("ImageOutTest.jpg");
/* 105:    */    }
/* 106:106 */    if (container.getInput().isKeyPressed(20)) {
/* 107:107 */      writeTo("ImageOutTest.tga");
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 114:    */  public static void main(String[] argv)
/* 115:    */  {
/* 116:    */    try
/* 117:    */    {
/* 118:118 */      AppGameContainer container = new AppGameContainer(new ImageOutTest());
/* 119:119 */      container.setDisplayMode(800, 600, false);
/* 120:120 */      container.start();
/* 121:    */    } catch (SlickException e) {
/* 122:122 */      e.printStackTrace();
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 128:    */  public void keyPressed(int key, char c)
/* 129:    */  {
/* 130:130 */    if (key == 1) {
/* 131:131 */      this.container.exit();
/* 132:    */    }
/* 133:    */  }
/* 134:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.ImageOutTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */