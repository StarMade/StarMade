/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import java.util.ArrayList;
/*  4:   */import org.newdawn.slick.AngelCodeFont;
/*  5:   */import org.newdawn.slick.AppGameContainer;
/*  6:   */import org.newdawn.slick.BasicGame;
/*  7:   */import org.newdawn.slick.Color;
/*  8:   */import org.newdawn.slick.GameContainer;
/*  9:   */import org.newdawn.slick.Graphics;
/* 10:   */import org.newdawn.slick.SlickException;
/* 11:   */
/* 20:   */public class FontPerformanceTest
/* 21:   */  extends BasicGame
/* 22:   */{
/* 23:   */  private AngelCodeFont font;
/* 24:24 */  private String text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin bibendum. Aliquam ac sapien a elit congue iaculis. Quisque et justo quis mi mattis euismod. Donec elementum, mi quis aliquet varius, nisi leo volutpat magna, quis ultricies eros augue at risus. Integer non magna at lorem sodales molestie. Integer diam nulla, ornare sit amet, mattis quis, euismod et, mauris. Proin eget tellus non nisl mattis laoreet. Nunc at nunc id elit pretium tempor. Duis vulputate, nibh eget rhoncus eleifend, tellus lectus sollicitudin mi, rhoncus tincidunt nisi massa vitae ipsum. Praesent tellus diam, luctus ut, eleifend nec, auctor et, orci. Praesent eu elit. Pellentesque ante orci, volutpat placerat, ornare eget, cursus sit amet, eros. Duis pede sapien, euismod a, volutpat pellentesque, convallis eu, mauris. Nunc eros. Ut eu risus et felis laoreet viverra. Curabitur a metus.";
/* 25:   */  
/* 26:26 */  private ArrayList lines = new ArrayList();
/* 27:   */  
/* 28:28 */  private boolean visible = true;
/* 29:   */  
/* 32:   */  public FontPerformanceTest()
/* 33:   */  {
/* 34:34 */    super("Font Performance Test");
/* 35:   */  }
/* 36:   */  
/* 38:   */  public void init(GameContainer container)
/* 39:   */    throws SlickException
/* 40:   */  {
/* 41:41 */    this.font = new AngelCodeFont("testdata/perffont.fnt", "testdata/perffont.png");
/* 42:   */    
/* 43:43 */    for (int j = 0; j < 2; j++) {
/* 44:44 */      int lineLen = 90;
/* 45:45 */      for (int i = 0; i < this.text.length(); i += lineLen) {
/* 46:46 */        if (i + lineLen > this.text.length()) {
/* 47:47 */          lineLen = this.text.length() - i;
/* 48:   */        }
/* 49:   */        
/* 50:50 */        this.lines.add(this.text.substring(i, i + lineLen));
/* 51:   */      }
/* 52:52 */      this.lines.add("");
/* 53:   */    }
/* 54:   */  }
/* 55:   */  
/* 58:   */  public void render(GameContainer container, Graphics g)
/* 59:   */  {
/* 60:60 */    g.setFont(this.font);
/* 61:   */    
/* 62:62 */    if (this.visible) {
/* 63:63 */      for (int i = 0; i < this.lines.size(); i++) {
/* 64:64 */        this.font.drawString(10.0F, 50 + i * 20, (String)this.lines.get(i), i > 10 ? Color.red : Color.green);
/* 65:   */      }
/* 66:   */    }
/* 67:   */  }
/* 68:   */  
/* 71:   */  public void update(GameContainer container, int delta)
/* 72:   */    throws SlickException
/* 73:   */  {}
/* 74:   */  
/* 77:   */  public void keyPressed(int key, char c)
/* 78:   */  {
/* 79:79 */    if (key == 1) {
/* 80:80 */      System.exit(0);
/* 81:   */    }
/* 82:82 */    if (key == 57) {
/* 83:83 */      this.visible = (!this.visible);
/* 84:   */    }
/* 85:   */  }
/* 86:   */  
/* 90:   */  public static void main(String[] argv)
/* 91:   */  {
/* 92:   */    try
/* 93:   */    {
/* 94:94 */      AppGameContainer container = new AppGameContainer(new FontPerformanceTest());
/* 95:95 */      container.setDisplayMode(800, 600, false);
/* 96:96 */      container.start();
/* 97:   */    } catch (SlickException e) {
/* 98:98 */      e.printStackTrace();
/* 99:   */    }
/* 100:   */  }
/* 101:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.FontPerformanceTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */