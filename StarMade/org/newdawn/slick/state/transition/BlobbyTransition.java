/*   1:    */package org.newdawn.slick.state.transition;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.Color;
/*   5:    */import org.newdawn.slick.GameContainer;
/*   6:    */import org.newdawn.slick.Graphics;
/*   7:    */import org.newdawn.slick.SlickException;
/*   8:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*   9:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  10:    */import org.newdawn.slick.state.GameState;
/*  11:    */import org.newdawn.slick.state.StateBasedGame;
/*  12:    */import org.newdawn.slick.util.MaskUtil;
/*  13:    */
/*  22:    */public class BlobbyTransition
/*  23:    */  implements Transition
/*  24:    */{
/*  25: 25 */  protected static SGL GL = ;
/*  26:    */  
/*  28:    */  private GameState prev;
/*  29:    */  
/*  30:    */  private boolean finish;
/*  31:    */  
/*  32:    */  private Color background;
/*  33:    */  
/*  34: 34 */  private ArrayList blobs = new ArrayList();
/*  35:    */  
/*  36: 36 */  private int timer = 1000;
/*  37:    */  
/*  38: 38 */  private int blobCount = 10;
/*  39:    */  
/*  45:    */  public BlobbyTransition() {}
/*  46:    */  
/*  51:    */  public BlobbyTransition(Color background)
/*  52:    */  {
/*  53: 53 */    this.background = background;
/*  54:    */  }
/*  55:    */  
/*  58:    */  public void init(GameState firstState, GameState secondState)
/*  59:    */  {
/*  60: 60 */    this.prev = secondState;
/*  61:    */  }
/*  62:    */  
/*  65:    */  public boolean isComplete()
/*  66:    */  {
/*  67: 67 */    return this.finish;
/*  68:    */  }
/*  69:    */  
/*  73:    */  public void postRender(StateBasedGame game, GameContainer container, Graphics g)
/*  74:    */    throws SlickException
/*  75:    */  {}
/*  76:    */  
/*  79:    */  public void preRender(StateBasedGame game, GameContainer container, Graphics g)
/*  80:    */    throws SlickException
/*  81:    */  {
/*  82: 82 */    this.prev.render(container, game, g);
/*  83:    */    
/*  84: 84 */    MaskUtil.defineMask();
/*  85: 85 */    for (int i = 0; i < this.blobs.size(); i++) {
/*  86: 86 */      ((Blob)this.blobs.get(i)).render(g);
/*  87:    */    }
/*  88: 88 */    MaskUtil.finishDefineMask();
/*  89:    */    
/*  90: 90 */    MaskUtil.drawOnMask();
/*  91: 91 */    if (this.background != null) {
/*  92: 92 */      Color c = g.getColor();
/*  93: 93 */      g.setColor(this.background);
/*  94: 94 */      g.fillRect(0.0F, 0.0F, container.getWidth(), container.getHeight());
/*  95: 95 */      g.setColor(c);
/*  96:    */    }
/*  97:    */  }
/*  98:    */  
/* 101:    */  public void update(StateBasedGame game, GameContainer container, int delta)
/* 102:    */    throws SlickException
/* 103:    */  {
/* 104:104 */    if (this.blobs.size() == 0) {
/* 105:105 */      for (int i = 0; i < this.blobCount; i++) {
/* 106:106 */        this.blobs.add(new Blob(container));
/* 107:    */      }
/* 108:    */    }
/* 109:    */    
/* 110:110 */    for (int i = 0; i < this.blobs.size(); i++) {
/* 111:111 */      ((Blob)this.blobs.get(i)).update(delta);
/* 112:    */    }
/* 113:    */    
/* 114:114 */    this.timer -= delta;
/* 115:115 */    if (this.timer < 0) {
/* 116:116 */      this.finish = true;
/* 117:    */    }
/* 118:    */  }
/* 119:    */  
/* 123:    */  private class Blob
/* 124:    */  {
/* 125:    */    private float x;
/* 126:    */    
/* 129:    */    private float y;
/* 130:    */    
/* 132:    */    private float growSpeed;
/* 133:    */    
/* 135:    */    private float rad;
/* 136:    */    
/* 139:    */    public Blob(GameContainer container)
/* 140:    */    {
/* 141:141 */      this.x = ((float)(Math.random() * container.getWidth()));
/* 142:142 */      this.y = ((float)(Math.random() * container.getWidth()));
/* 143:143 */      this.growSpeed = ((float)(1.0D + Math.random() * 1.0D));
/* 144:    */    }
/* 145:    */    
/* 150:    */    public void update(int delta)
/* 151:    */    {
/* 152:152 */      this.rad += this.growSpeed * delta * 0.4F;
/* 153:    */    }
/* 154:    */    
/* 159:    */    public void render(Graphics g)
/* 160:    */    {
/* 161:161 */      g.fillOval(this.x - this.rad, this.y - this.rad, this.rad * 2.0F, this.rad * 2.0F);
/* 162:    */    }
/* 163:    */  }
/* 164:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.BlobbyTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */