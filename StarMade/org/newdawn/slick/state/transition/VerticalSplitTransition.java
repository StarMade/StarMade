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
/*  19:    */public class VerticalSplitTransition
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
/*  40:    */  public VerticalSplitTransition() {}
/*  41:    */  
/*  44:    */  public VerticalSplitTransition(Color background)
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
/*  67: 67 */    g.translate(0.0F, -this.offset);
/*  68: 68 */    g.setClip(0, (int)-this.offset, container.getWidth(), container.getHeight() / 2);
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
/*  79: 79 */    g.resetTransform();
/*  80:    */    
/*  81: 81 */    g.translate(0.0F, this.offset);
/*  82: 82 */    g.setClip(0, (int)(container.getHeight() / 2 + this.offset), container.getWidth(), container.getHeight() / 2);
/*  83: 83 */    if (this.background != null) {
/*  84: 84 */      Color c = g.getColor();
/*  85: 85 */      g.setColor(this.background);
/*  86: 86 */      g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
/*  87: 87 */      g.setColor(c);
/*  88:    */    }
/*  89: 89 */    GL.glPushMatrix();
/*  90: 90 */    this.prev.render(container, game, g);
/*  91: 91 */    GL.glPopMatrix();
/*  92: 92 */    g.clearClip();
/*  93: 93 */    g.translate(0.0F, -this.offset);
/*  94:    */  }
/*  95:    */  
/*  99:    */  public void preRender(StateBasedGame game, GameContainer container, Graphics g)
/* 100:    */    throws SlickException
/* 101:    */  {}
/* 102:    */  
/* 105:    */  public void update(StateBasedGame game, GameContainer container, int delta)
/* 106:    */    throws SlickException
/* 107:    */  {
/* 108:108 */    this.offset += delta * 1.0F;
/* 109:109 */    if (this.offset > container.getHeight() / 2) {
/* 110:110 */      this.finish = true;
/* 111:    */    }
/* 112:    */  }
/* 113:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.VerticalSplitTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */