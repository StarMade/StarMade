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
/*  20:    */public class SelectTransition
/*  21:    */  implements Transition
/*  22:    */{
/*  23: 23 */  protected static SGL GL = ;
/*  24:    */  
/*  26:    */  private GameState prev;
/*  27:    */  
/*  29:    */  private boolean finish;
/*  30:    */  
/*  31:    */  private Color background;
/*  32:    */  
/*  33: 33 */  private float scale1 = 1.0F;
/*  34:    */  
/*  35: 35 */  private float xp1 = 0.0F;
/*  36:    */  
/*  37: 37 */  private float yp1 = 0.0F;
/*  38:    */  
/*  39: 39 */  private float scale2 = 0.4F;
/*  40:    */  
/*  41: 41 */  private float xp2 = 0.0F;
/*  42:    */  
/*  43: 43 */  private float yp2 = 0.0F;
/*  44:    */  
/*  45: 45 */  private boolean init = false;
/*  46:    */  
/*  48: 48 */  private boolean moveBackDone = false;
/*  49:    */  
/*  50: 50 */  private int pause = 300;
/*  51:    */  
/*  57:    */  public SelectTransition() {}
/*  58:    */  
/*  63:    */  public SelectTransition(Color background)
/*  64:    */  {
/*  65: 65 */    this.background = background;
/*  66:    */  }
/*  67:    */  
/*  70:    */  public void init(GameState firstState, GameState secondState)
/*  71:    */  {
/*  72: 72 */    this.prev = secondState;
/*  73:    */  }
/*  74:    */  
/*  77:    */  public boolean isComplete()
/*  78:    */  {
/*  79: 79 */    return this.finish;
/*  80:    */  }
/*  81:    */  
/*  83:    */  public void postRender(StateBasedGame game, GameContainer container, Graphics g)
/*  84:    */    throws SlickException
/*  85:    */  {
/*  86: 86 */    g.resetTransform();
/*  87:    */    
/*  88: 88 */    if (!this.moveBackDone) {
/*  89: 89 */      g.translate(this.xp1, this.yp1);
/*  90: 90 */      g.scale(this.scale1, this.scale1);
/*  91: 91 */      g.setClip((int)this.xp1, (int)this.yp1, (int)(this.scale1 * container.getWidth()), (int)(this.scale1 * container.getHeight()));
/*  92: 92 */      this.prev.render(container, game, g);
/*  93: 93 */      g.resetTransform();
/*  94: 94 */      g.clearClip();
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/* 100:    */  public void preRender(StateBasedGame game, GameContainer container, Graphics g)
/* 101:    */    throws SlickException
/* 102:    */  {
/* 103:103 */    if (this.moveBackDone) {
/* 104:104 */      g.translate(this.xp1, this.yp1);
/* 105:105 */      g.scale(this.scale1, this.scale1);
/* 106:106 */      g.setClip((int)this.xp1, (int)this.yp1, (int)(this.scale1 * container.getWidth()), (int)(this.scale1 * container.getHeight()));
/* 107:107 */      this.prev.render(container, game, g);
/* 108:108 */      g.resetTransform();
/* 109:109 */      g.clearClip();
/* 110:    */    }
/* 111:    */    
/* 112:112 */    g.translate(this.xp2, this.yp2);
/* 113:113 */    g.scale(this.scale2, this.scale2);
/* 114:114 */    g.setClip((int)this.xp2, (int)this.yp2, (int)(this.scale2 * container.getWidth()), (int)(this.scale2 * container.getHeight()));
/* 115:    */  }
/* 116:    */  
/* 119:    */  public void update(StateBasedGame game, GameContainer container, int delta)
/* 120:    */    throws SlickException
/* 121:    */  {
/* 122:122 */    if (!this.init) {
/* 123:123 */      this.init = true;
/* 124:124 */      this.xp2 = (container.getWidth() / 2 + 50);
/* 125:125 */      this.yp2 = (container.getHeight() / 4);
/* 126:    */    }
/* 127:    */    
/* 128:128 */    if (!this.moveBackDone) {
/* 129:129 */      if (this.scale1 > 0.4F) {
/* 130:130 */        this.scale1 -= delta * 0.002F;
/* 131:131 */        if (this.scale1 <= 0.4F) {
/* 132:132 */          this.scale1 = 0.4F;
/* 133:    */        }
/* 134:134 */        this.xp1 += delta * 0.3F;
/* 135:135 */        if (this.xp1 > 50.0F) {
/* 136:136 */          this.xp1 = 50.0F;
/* 137:    */        }
/* 138:138 */        this.yp1 += delta * 0.5F;
/* 139:139 */        if (this.yp1 > container.getHeight() / 4) {
/* 140:140 */          this.yp1 = (container.getHeight() / 4);
/* 141:    */        }
/* 142:    */      } else {
/* 143:143 */        this.moveBackDone = true;
/* 144:    */      }
/* 145:    */    } else {
/* 146:146 */      this.pause -= delta;
/* 147:147 */      if (this.pause > 0) {
/* 148:148 */        return;
/* 149:    */      }
/* 150:150 */      if (this.scale2 < 1.0F) {
/* 151:151 */        this.scale2 += delta * 0.002F;
/* 152:152 */        if (this.scale2 >= 1.0F) {
/* 153:153 */          this.scale2 = 1.0F;
/* 154:    */        }
/* 155:155 */        this.xp2 -= delta * 1.5F;
/* 156:156 */        if (this.xp2 < 0.0F) {
/* 157:157 */          this.xp2 = 0.0F;
/* 158:    */        }
/* 159:159 */        this.yp2 -= delta * 0.5F;
/* 160:160 */        if (this.yp2 < 0.0F) {
/* 161:161 */          this.yp2 = 0.0F;
/* 162:    */        }
/* 163:    */      } else {
/* 164:164 */        this.finish = true;
/* 165:    */      }
/* 166:    */    }
/* 167:    */  }
/* 168:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.transition.SelectTransition
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */