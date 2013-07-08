/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.awt.Font;
/*   4:    */import java.util.ArrayList;
/*   5:    */import org.newdawn.slick.AppGameContainer;
/*   6:    */import org.newdawn.slick.BasicGame;
/*   7:    */import org.newdawn.slick.Color;
/*   8:    */import org.newdawn.slick.GameContainer;
/*   9:    */import org.newdawn.slick.Graphics;
/*  10:    */import org.newdawn.slick.SlickException;
/*  11:    */import org.newdawn.slick.TrueTypeFont;
/*  12:    */
/*  23:    */public class TrueTypeFontPerformanceTest
/*  24:    */  extends BasicGame
/*  25:    */{
/*  26:    */  private Font awtFont;
/*  27:    */  private TrueTypeFont font;
/*  28: 28 */  private String text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin bibendum. Aliquam ac sapien a elit congue iaculis. Quisque et justo quis mi mattis euismod. Donec elementum, mi quis aliquet varius, nisi leo volutpat magna, quis ultricies eros augue at risus. Integer non magna at lorem sodales molestie. Integer diam nulla, ornare sit amet, mattis quis, euismod et, mauris. Proin eget tellus non nisl mattis laoreet. Nunc at nunc id elit pretium tempor. Duis vulputate, nibh eget rhoncus eleifend, tellus lectus sollicitudin mi, rhoncus tincidunt nisi massa vitae ipsum. Praesent tellus diam, luctus ut, eleifend nec, auctor et, orci. Praesent eu elit. Pellentesque ante orci, volutpat placerat, ornare eget, cursus sit amet, eros. Duis pede sapien, euismod a, volutpat pellentesque, convallis eu, mauris. Nunc eros. Ut eu risus et felis laoreet viverra. Curabitur a metus.";
/*  29:    */  
/*  30: 30 */  private ArrayList lines = new ArrayList();
/*  31:    */  
/*  32: 32 */  private boolean visible = true;
/*  33:    */  
/*  36:    */  public TrueTypeFontPerformanceTest()
/*  37:    */  {
/*  38: 38 */    super("Font Performance Test");
/*  39:    */  }
/*  40:    */  
/*  42:    */  public void init(GameContainer container)
/*  43:    */    throws SlickException
/*  44:    */  {
/*  45: 45 */    this.awtFont = new Font("Verdana", 0, 16);
/*  46: 46 */    this.font = new TrueTypeFont(this.awtFont, false);
/*  47:    */    
/*  48: 48 */    for (int j = 0; j < 2; j++) {
/*  49: 49 */      int lineLen = 90;
/*  50: 50 */      for (int i = 0; i < this.text.length(); i += lineLen) {
/*  51: 51 */        if (i + lineLen > this.text.length()) {
/*  52: 52 */          lineLen = this.text.length() - i;
/*  53:    */        }
/*  54:    */        
/*  55: 55 */        this.lines.add(this.text.substring(i, i + lineLen));
/*  56:    */      }
/*  57: 57 */      this.lines.add("");
/*  58:    */    }
/*  59:    */  }
/*  60:    */  
/*  64:    */  public void render(GameContainer container, Graphics g)
/*  65:    */  {
/*  66: 66 */    g.setFont(this.font);
/*  67:    */    
/*  68: 68 */    if (this.visible) {
/*  69: 69 */      for (int i = 0; i < this.lines.size(); i++) {
/*  70: 70 */        this.font.drawString(10.0F, 50 + i * 20, (String)this.lines.get(i), i > 10 ? Color.red : Color.green);
/*  71:    */      }
/*  72:    */    }
/*  73:    */  }
/*  74:    */  
/*  79:    */  public void update(GameContainer container, int delta)
/*  80:    */    throws SlickException
/*  81:    */  {}
/*  82:    */  
/*  86:    */  public void keyPressed(int key, char c)
/*  87:    */  {
/*  88: 88 */    if (key == 1) {
/*  89: 89 */      System.exit(0);
/*  90:    */    }
/*  91: 91 */    if (key == 57) {
/*  92: 92 */      this.visible = (!this.visible);
/*  93:    */    }
/*  94:    */  }
/*  95:    */  
/* 100:    */  public static void main(String[] argv)
/* 101:    */  {
/* 102:    */    try
/* 103:    */    {
/* 104:104 */      AppGameContainer container = new AppGameContainer(new TrueTypeFontPerformanceTest());
/* 105:    */      
/* 106:106 */      container.setDisplayMode(800, 600, false);
/* 107:107 */      container.start();
/* 108:    */    } catch (SlickException e) {
/* 109:109 */      e.printStackTrace();
/* 110:    */    }
/* 111:    */  }
/* 112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.TrueTypeFontPerformanceTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */