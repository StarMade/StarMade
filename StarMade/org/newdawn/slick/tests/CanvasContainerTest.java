/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.awt.Frame;
/*   4:    */import java.awt.GridLayout;
/*   5:    */import java.awt.event.WindowAdapter;
/*   6:    */import java.awt.event.WindowEvent;
/*   7:    */import org.newdawn.slick.BasicGame;
/*   8:    */import org.newdawn.slick.CanvasGameContainer;
/*   9:    */import org.newdawn.slick.GameContainer;
/*  10:    */import org.newdawn.slick.Graphics;
/*  11:    */import org.newdawn.slick.Image;
/*  12:    */import org.newdawn.slick.SlickException;
/*  13:    */
/*  28:    */public class CanvasContainerTest
/*  29:    */  extends BasicGame
/*  30:    */{
/*  31:    */  private Image tga;
/*  32:    */  private Image scaleMe;
/*  33:    */  private Image scaled;
/*  34:    */  private Image gif;
/*  35:    */  private Image image;
/*  36:    */  private Image subImage;
/*  37:    */  private float rot;
/*  38:    */  
/*  39:    */  public CanvasContainerTest()
/*  40:    */  {
/*  41: 41 */    super("Canvas Container Test");
/*  42:    */  }
/*  43:    */  
/*  45:    */  public void init(GameContainer container)
/*  46:    */    throws SlickException
/*  47:    */  {
/*  48: 48 */    this.image = (this.tga = new Image("testdata/logo.tga"));
/*  49: 49 */    this.scaleMe = new Image("testdata/logo.tga", true, 2);
/*  50: 50 */    this.gif = new Image("testdata/logo.gif");
/*  51: 51 */    this.scaled = this.gif.getScaledCopy(120, 120);
/*  52: 52 */    this.subImage = this.image.getSubImage(200, 0, 70, 260);
/*  53: 53 */    this.rot = 0.0F;
/*  54:    */  }
/*  55:    */  
/*  58:    */  public void render(GameContainer container, Graphics g)
/*  59:    */  {
/*  60: 60 */    this.image.draw(0.0F, 0.0F);
/*  61: 61 */    this.image.draw(500.0F, 0.0F, 200.0F, 100.0F);
/*  62: 62 */    this.scaleMe.draw(500.0F, 100.0F, 200.0F, 100.0F);
/*  63: 63 */    this.scaled.draw(400.0F, 500.0F);
/*  64: 64 */    Image flipped = this.scaled.getFlippedCopy(true, false);
/*  65: 65 */    flipped.draw(520.0F, 500.0F);
/*  66: 66 */    Image flipped2 = flipped.getFlippedCopy(false, true);
/*  67: 67 */    flipped2.draw(520.0F, 380.0F);
/*  68: 68 */    Image flipped3 = flipped2.getFlippedCopy(true, false);
/*  69: 69 */    flipped3.draw(400.0F, 380.0F);
/*  70:    */    
/*  71: 71 */    for (int i = 0; i < 3; i++) {
/*  72: 72 */      this.subImage.draw(200 + i * 30, 300.0F);
/*  73:    */    }
/*  74:    */    
/*  75: 75 */    g.translate(500.0F, 200.0F);
/*  76: 76 */    g.rotate(50.0F, 50.0F, this.rot);
/*  77: 77 */    g.scale(0.3F, 0.3F);
/*  78: 78 */    this.image.draw();
/*  79: 79 */    g.resetTransform();
/*  80:    */  }
/*  81:    */  
/*  84:    */  public void update(GameContainer container, int delta)
/*  85:    */  {
/*  86: 86 */    this.rot += delta * 0.1F;
/*  87: 87 */    if (this.rot > 360.0F) {
/*  88: 88 */      this.rot -= 360.0F;
/*  89:    */    }
/*  90:    */  }
/*  91:    */  
/*  95:    */  public static void main(String[] argv)
/*  96:    */  {
/*  97:    */    try
/*  98:    */    {
/*  99: 99 */      CanvasGameContainer container = new CanvasGameContainer(new CanvasContainerTest());
/* 100:    */      
/* 101:101 */      Frame frame = new Frame("Test");
/* 102:102 */      frame.setLayout(new GridLayout(1, 2));
/* 103:103 */      frame.setSize(500, 500);
/* 104:104 */      frame.add(container);
/* 105:    */      
/* 106:106 */      frame.addWindowListener(new WindowAdapter() {
/* 107:    */        public void windowClosing(WindowEvent e) {
/* 108:108 */          System.exit(0);
/* 109:    */        }
/* 110:110 */      });
/* 111:111 */      frame.setVisible(true);
/* 112:112 */      container.start();
/* 113:    */    } catch (Exception e) {
/* 114:114 */      e.printStackTrace();
/* 115:    */    }
/* 116:    */  }
/* 117:    */  
/* 120:    */  public void keyPressed(int key, char c)
/* 121:    */  {
/* 122:122 */    if (key == 57) {
/* 123:123 */      if (this.image == this.gif) {
/* 124:124 */        this.image = this.tga;
/* 125:    */      } else {
/* 126:126 */        this.image = this.gif;
/* 127:    */      }
/* 128:    */    }
/* 129:    */  }
/* 130:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.CanvasContainerTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */