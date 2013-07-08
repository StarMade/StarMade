/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.Color;
/*   5:    */import org.newdawn.slick.GameContainer;
/*   6:    */import org.newdawn.slick.Graphics;
/*   7:    */import org.newdawn.slick.Image;
/*   8:    */import org.newdawn.slick.Input;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.state.BasicGameState;
/*  11:    */import org.newdawn.slick.state.StateBasedGame;
/*  12:    */import org.newdawn.slick.state.transition.BlobbyTransition;
/*  13:    */import org.newdawn.slick.state.transition.FadeInTransition;
/*  14:    */import org.newdawn.slick.state.transition.FadeOutTransition;
/*  15:    */import org.newdawn.slick.state.transition.HorizontalSplitTransition;
/*  16:    */import org.newdawn.slick.state.transition.RotateTransition;
/*  17:    */import org.newdawn.slick.state.transition.SelectTransition;
/*  18:    */import org.newdawn.slick.state.transition.Transition;
/*  19:    */import org.newdawn.slick.state.transition.VerticalSplitTransition;
/*  20:    */import org.newdawn.slick.util.Log;
/*  21:    */
/*  26:    */public class TransitionTest
/*  27:    */  extends StateBasedGame
/*  28:    */{
/*  29: 29 */  private Class[][] transitions = { { null, VerticalSplitTransition.class }, { FadeOutTransition.class, FadeInTransition.class }, { null, RotateTransition.class }, { null, HorizontalSplitTransition.class }, { null, BlobbyTransition.class }, { null, SelectTransition.class } };
/*  30:    */  
/*  35:    */  private int index;
/*  36:    */  
/*  42:    */  public TransitionTest()
/*  43:    */  {
/*  44: 44 */    super("Transition Test - Hit Space To Transition");
/*  45:    */  }
/*  46:    */  
/*  48:    */  public void initStatesList(GameContainer container)
/*  49:    */    throws SlickException
/*  50:    */  {
/*  51: 51 */    addState(new ImageState(0, "testdata/wallpaper/paper1.png", 1));
/*  52: 52 */    addState(new ImageState(1, "testdata/wallpaper/paper2.png", 2));
/*  53: 53 */    addState(new ImageState(2, "testdata/bigimage.tga", 0));
/*  54:    */  }
/*  55:    */  
/*  60:    */  public Transition[] getNextTransitionPair()
/*  61:    */  {
/*  62: 62 */    Transition[] pair = new Transition[2];
/*  63:    */    try
/*  64:    */    {
/*  65: 65 */      if (this.transitions[this.index][0] != null) {
/*  66: 66 */        pair[0] = ((Transition)this.transitions[this.index][0].newInstance());
/*  67:    */      }
/*  68: 68 */      if (this.transitions[this.index][1] != null) {
/*  69: 69 */        pair[1] = ((Transition)this.transitions[this.index][1].newInstance());
/*  70:    */      }
/*  71:    */    } catch (Throwable e) {
/*  72: 72 */      Log.error(e);
/*  73:    */    }
/*  74:    */    
/*  75: 75 */    this.index += 1;
/*  76: 76 */    if (this.index >= this.transitions.length) {
/*  77: 77 */      this.index = 0;
/*  78:    */    }
/*  79:    */    
/*  80: 80 */    return pair;
/*  81:    */  }
/*  82:    */  
/*  86:    */  private class ImageState
/*  87:    */    extends BasicGameState
/*  88:    */  {
/*  89:    */    private int id;
/*  90:    */    
/*  93:    */    private int next;
/*  94:    */    
/*  97:    */    private String ref;
/*  98:    */    
/* 100:    */    private Image image;
/* 101:    */    
/* 104:    */    public ImageState(int id, String ref, int next)
/* 105:    */    {
/* 106:106 */      this.ref = ref;
/* 107:107 */      this.id = id;
/* 108:108 */      this.next = next;
/* 109:    */    }
/* 110:    */    
/* 113:    */    public int getID()
/* 114:    */    {
/* 115:115 */      return this.id;
/* 116:    */    }
/* 117:    */    
/* 119:    */    public void init(GameContainer container, StateBasedGame game)
/* 120:    */      throws SlickException
/* 121:    */    {
/* 122:122 */      this.image = new Image(this.ref);
/* 123:    */    }
/* 124:    */    
/* 126:    */    public void render(GameContainer container, StateBasedGame game, Graphics g)
/* 127:    */      throws SlickException
/* 128:    */    {
/* 129:129 */      this.image.draw(0.0F, 0.0F, 800.0F, 600.0F);
/* 130:130 */      g.setColor(Color.red);
/* 131:131 */      g.fillRect(-50.0F, 200.0F, 50.0F, 50.0F);
/* 132:    */    }
/* 133:    */    
/* 135:    */    public void update(GameContainer container, StateBasedGame game, int delta)
/* 136:    */      throws SlickException
/* 137:    */    {
/* 138:138 */      if (container.getInput().isKeyPressed(57)) {
/* 139:139 */        Transition[] pair = TransitionTest.this.getNextTransitionPair();
/* 140:140 */        game.enterState(this.next, pair[0], pair[1]);
/* 141:    */      }
/* 142:    */    }
/* 143:    */  }
/* 144:    */  
/* 149:    */  public static void main(String[] argv)
/* 150:    */  {
/* 151:    */    try
/* 152:    */    {
/* 153:153 */      AppGameContainer container = new AppGameContainer(new TransitionTest());
/* 154:    */      
/* 155:155 */      container.setDisplayMode(800, 600, false);
/* 156:156 */      container.start();
/* 157:    */    } catch (SlickException e) {
/* 158:158 */      e.printStackTrace();
/* 159:    */    }
/* 160:    */  }
/* 161:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.TransitionTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */