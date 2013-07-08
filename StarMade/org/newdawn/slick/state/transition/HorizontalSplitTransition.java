/*   1:    */package org.newdawn.slick.state.transition;
/*   2:    */
/*   3:    */import org.newdawn.slick.Color;
/*   4:    */import org.newdawn.slick.GameContainer;
/*   5:    */import org.newdawn.slick.Graphics;
/*   6:    */import org.newdawn.slick.SlickException;
/*   7:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*   8:    */import org.newdawn.slick.opengl.renderer.SGL;
/*   9:    */import org.newdawn.slick.state.GameState;
/*  10:    */import org.newdawn.slick.state.StateBasedGame;
/*  11:    */
/*  19:    */public class HorizontalSplitTransition
/*  20:    */  implements Transition
/*  21:    */{
/*  22: 22 */  protected static SGL GL = ;
/*  23:    */  
/*  26:    */  private GameState prev;
/*  27:    */  
/*  30:    */  private float offset;
/*  31:    */  
/*  33:    */  private boolean finish;
/*  34:    */  
/*  36:    */  private Color background;
/*  37:    */  
/*  40:    */  public HorizontalSplitTransition() {}
/*  41:    */  
/*  44:    */  public HorizontalSplitTransition(Color background)
/*  45:    */  {
/*  46: 46 */    this.background = background;
/*  47:    */  }
/*  48:    */  
/*  51:    */  public void init(GameState firstState, GameState secondState)
/*  52:    */  {
/*  53: 53 */    this.prev = secondState;
/*  54:    */  }
/*  55:    */  
/*  58:    */  public boolean isComplete()
/*  59:    */  {
/*  60: 60 */    return this.finish;
/*  61:    */  }
/*  62:    */  
/*  64:    */  public void postRender(StateBasedGame game, GameContainer container, Graphics g)
/*  65:    */    throws SlickException
/*  66:    */  {
/*  67: 67 */    g.translate(-this.offset, 0.0F);
/*  68: 68 */    g.setClip((int)-this.offset, 0, container.getWidth() / 2, container.getHeight());
/*  69: 69 */    if (this.background != null) {
/*  70: 70 */      Color c = g.getColor();
/*  71: 71 */      g.setColor(this.background);
/*  72: 72 */      g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
/*  73: 73 */      g.setColor(c);
/*  74:    */    }
/*  75: 75 */    GL.glPushMatrix();
/*  76: 76 */    this.prev.render(container, game, g);
/*  77: 77 */    GL.glPopMatrix();
/*  78: 78 */    g.clearClip();
/*  79:    */    
/*  80: 80 */    g.translate(this.offset * 2.0F, 0.0F);
/*  81: 81 */    g.setClip((int)(container.getWidth() / 2 + this.offset), 0, container.getWidth() / 2, container.getHeight());
/*  82: 82 */    if (this.background != null) {
/*  83: 83 */      Color c = g.getColor();
/*  84: 84 */      g.setColor(this.background);
/*  85: 85 */      g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
/*  86: 86 */      g.setColor(c);
/*  87:    */    }
/*  88: 88 */    GL.glPushMatrix();
/*  89: 89 */    this.prev.render(container, game, g);
/*  90: 90 */    GL.glPopMatrix();
/*  91: 91 */    g.clearClip();
/*  92: 92 */    g.translate(-this.offset, 0.0F);
/*  93:    */  }
/*  94:    */  
/*  98:    */  public void preRender(StateBasedGame game, GameContainer container, Graphics g)
/*  99:    */    throws SlickException
/* 100:    */  {}
/* 101:    */  
/* 104:    */  public void update(StateBasedGame game, GameContainer container, int delta)
/* 105:    */    throws SlickException
/* 106:    */  {
/* 107:107 */    this.offset += delta * 1.0F;
/* 108:108 */    if (this.offset > container.getWidth() / 2) {
/* 109:109 */      this.finish = true;
/* 110:    */    }
/* 111:    */  }
/* 112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.HorizontalSplitTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */