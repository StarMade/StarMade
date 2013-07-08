package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import org.newdawn.slick.state.transition.RotateTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.newdawn.slick.state.transition.Transition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;
import org.newdawn.slick.util.Log;

public class TransitionTest
  extends StateBasedGame
{
  private Class[][] transitions = { { null, VerticalSplitTransition.class }, { FadeOutTransition.class, FadeInTransition.class }, { null, RotateTransition.class }, { null, HorizontalSplitTransition.class }, { null, BlobbyTransition.class }, { null, SelectTransition.class } };
  private int index;
  
  public TransitionTest()
  {
    super("Transition Test - Hit Space To Transition");
  }
  
  public void initStatesList(GameContainer container)
    throws SlickException
  {
    addState(new ImageState(0, "testdata/wallpaper/paper1.png", 1));
    addState(new ImageState(1, "testdata/wallpaper/paper2.png", 2));
    addState(new ImageState(2, "testdata/bigimage.tga", 0));
  }
  
  public Transition[] getNextTransitionPair()
  {
    Transition[] pair = new Transition[2];
    try
    {
      if (this.transitions[this.index][0] != null) {
        pair[0] = ((Transition)this.transitions[this.index][0].newInstance());
      }
      if (this.transitions[this.index][1] != null) {
        pair[1] = ((Transition)this.transitions[this.index][1].newInstance());
      }
    }
    catch (Throwable local_e)
    {
      Log.error(local_e);
    }
    this.index += 1;
    if (this.index >= this.transitions.length) {
      this.index = 0;
    }
    return pair;
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new TransitionTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  private class ImageState
    extends BasicGameState
  {
    private int field_287;
    private int next;
    private String ref;
    private Image image;
    
    public ImageState(int local_id, String ref, int next)
    {
      this.ref = ref;
      this.field_287 = local_id;
      this.next = next;
    }
    
    public int getID()
    {
      return this.field_287;
    }
    
    public void init(GameContainer container, StateBasedGame game)
      throws SlickException
    {
      this.image = new Image(this.ref);
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics local_g)
      throws SlickException
    {
      this.image.draw(0.0F, 0.0F, 800.0F, 600.0F);
      local_g.setColor(Color.red);
      local_g.fillRect(-50.0F, 200.0F, 50.0F, 50.0F);
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta)
      throws SlickException
    {
      if (container.getInput().isKeyPressed(57))
      {
        Transition[] pair = TransitionTest.this.getNextTransitionPair();
        game.enterState(this.next, pair[0], pair[1]);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.TransitionTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */