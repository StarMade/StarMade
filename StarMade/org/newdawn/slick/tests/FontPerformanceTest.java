package org.newdawn.slick.tests;

import java.util.ArrayList;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class FontPerformanceTest
  extends BasicGame
{
  private AngelCodeFont font;
  private String text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin bibendum. Aliquam ac sapien a elit congue iaculis. Quisque et justo quis mi mattis euismod. Donec elementum, mi quis aliquet varius, nisi leo volutpat magna, quis ultricies eros augue at risus. Integer non magna at lorem sodales molestie. Integer diam nulla, ornare sit amet, mattis quis, euismod et, mauris. Proin eget tellus non nisl mattis laoreet. Nunc at nunc id elit pretium tempor. Duis vulputate, nibh eget rhoncus eleifend, tellus lectus sollicitudin mi, rhoncus tincidunt nisi massa vitae ipsum. Praesent tellus diam, luctus ut, eleifend nec, auctor et, orci. Praesent eu elit. Pellentesque ante orci, volutpat placerat, ornare eget, cursus sit amet, eros. Duis pede sapien, euismod a, volutpat pellentesque, convallis eu, mauris. Nunc eros. Ut eu risus et felis laoreet viverra. Curabitur a metus.";
  private ArrayList lines = new ArrayList();
  private boolean visible = true;
  
  public FontPerformanceTest()
  {
    super("Font Performance Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.font = new AngelCodeFont("testdata/perffont.fnt", "testdata/perffont.png");
    for (int local_j = 0; local_j < 2; local_j++)
    {
      int lineLen = 90;
      int local_i = 0;
      while (local_i < this.text.length())
      {
        if (local_i + lineLen > this.text.length()) {
          lineLen = this.text.length() - local_i;
        }
        this.lines.add(this.text.substring(local_i, local_i + lineLen));
        local_i += lineLen;
      }
      this.lines.add("");
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.setFont(this.font);
    if (this.visible) {
      for (int local_i = 0; local_i < this.lines.size(); local_i++) {
        this.font.drawString(10.0F, 50 + local_i * 20, (String)this.lines.get(local_i), local_i > 10 ? Color.red : Color.green);
      }
    }
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
    if (key == 57) {
      this.visible = (!this.visible);
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new FontPerformanceTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.FontPerformanceTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */