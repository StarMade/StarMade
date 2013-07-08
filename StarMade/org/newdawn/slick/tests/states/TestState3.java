package org.newdawn.slick.tests.states;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TestState3
  extends BasicGameState
{
  public static final int field_265 = 3;
  private Font font;
  private String[] options = { "Start Game", "Credits", "Highscores", "Instructions", "Exit" };
  private int selected;
  private StateBasedGame game;
  
  public int getID()
  {
    return 3;
  }
  
  public void init(GameContainer container, StateBasedGame game)
    throws SlickException
  {
    this.font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
    this.game = game;
  }
  
  public void render(GameContainer container, StateBasedGame game, Graphics local_g)
  {
    local_g.setFont(this.font);
    local_g.setColor(Color.blue);
    local_g.drawString("This is State 3", 200.0F, 50.0F);
    local_g.setColor(Color.white);
    for (int local_i = 0; local_i < this.options.length; local_i++)
    {
      local_g.drawString(this.options[local_i], 400 - this.font.getWidth(this.options[local_i]) / 2, 200 + local_i * 50);
      if (this.selected == local_i) {
        local_g.drawRect(200.0F, 190 + local_i * 50, 400.0F, 50.0F);
      }
    }
  }
  
  public void update(GameContainer container, StateBasedGame game, int delta) {}
  
  public void keyReleased(int key, char local_c)
  {
    if (key == 208)
    {
      this.selected += 1;
      if (this.selected >= this.options.length) {
        this.selected = 0;
      }
    }
    if (key == 200)
    {
      this.selected -= 1;
      if (this.selected < 0) {
        this.selected = (this.options.length - 1);
      }
    }
    if (key == 2) {
      this.game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    }
    if (key == 3) {
      this.game.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.states.TestState3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */