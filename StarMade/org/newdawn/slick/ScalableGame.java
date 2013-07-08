/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import org.newdawn.slick.opengl.SlickCallable;
/*   4:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*   5:    */import org.newdawn.slick.opengl.renderer.SGL;
/*   6:    */
/*  16:    */public class ScalableGame
/*  17:    */  implements Game
/*  18:    */{
/*  19: 19 */  private static SGL GL = ;
/*  20:    */  
/*  22:    */  private float normalWidth;
/*  23:    */  
/*  25:    */  private float normalHeight;
/*  26:    */  
/*  28:    */  private Game held;
/*  29:    */  
/*  31:    */  private boolean maintainAspect;
/*  32:    */  
/*  34:    */  private int targetWidth;
/*  35:    */  
/*  37:    */  private int targetHeight;
/*  38:    */  
/*  39:    */  private GameContainer container;
/*  40:    */  
/*  42:    */  public ScalableGame(Game held, int normalWidth, int normalHeight)
/*  43:    */  {
/*  44: 44 */    this(held, normalWidth, normalHeight, false);
/*  45:    */  }
/*  46:    */  
/*  54:    */  public ScalableGame(Game held, int normalWidth, int normalHeight, boolean maintainAspect)
/*  55:    */  {
/*  56: 56 */    this.held = held;
/*  57: 57 */    this.normalWidth = normalWidth;
/*  58: 58 */    this.normalHeight = normalHeight;
/*  59: 59 */    this.maintainAspect = maintainAspect;
/*  60:    */  }
/*  61:    */  
/*  63:    */  public void init(GameContainer container)
/*  64:    */    throws SlickException
/*  65:    */  {
/*  66: 66 */    this.container = container;
/*  67:    */    
/*  68: 68 */    recalculateScale();
/*  69: 69 */    this.held.init(container);
/*  70:    */  }
/*  71:    */  
/*  75:    */  public void recalculateScale()
/*  76:    */    throws SlickException
/*  77:    */  {
/*  78: 78 */    this.targetWidth = this.container.getWidth();
/*  79: 79 */    this.targetHeight = this.container.getHeight();
/*  80:    */    
/*  81: 81 */    if (this.maintainAspect) {
/*  82: 82 */      boolean normalIsWide = this.normalWidth / this.normalHeight > 1.6D;
/*  83: 83 */      boolean containerIsWide = this.targetWidth / this.targetHeight > 1.6D;
/*  84: 84 */      float wScale = this.targetWidth / this.normalWidth;
/*  85: 85 */      float hScale = this.targetHeight / this.normalHeight;
/*  86:    */      
/*  87: 87 */      if ((normalIsWide & containerIsWide)) {
/*  88: 88 */        float scale = wScale < hScale ? wScale : hScale;
/*  89: 89 */        this.targetWidth = ((int)(this.normalWidth * scale));
/*  90: 90 */        this.targetHeight = ((int)(this.normalHeight * scale));
/*  91: 91 */      } else if ((normalIsWide & !containerIsWide)) {
/*  92: 92 */        this.targetWidth = ((int)(this.normalWidth * wScale));
/*  93: 93 */        this.targetHeight = ((int)(this.normalHeight * wScale));
/*  94: 94 */      } else if ((!normalIsWide & containerIsWide)) {
/*  95: 95 */        this.targetWidth = ((int)(this.normalWidth * hScale));
/*  96: 96 */        this.targetHeight = ((int)(this.normalHeight * hScale));
/*  97:    */      } else {
/*  98: 98 */        float scale = wScale < hScale ? wScale : hScale;
/*  99: 99 */        this.targetWidth = ((int)(this.normalWidth * scale));
/* 100:100 */        this.targetHeight = ((int)(this.normalHeight * scale));
/* 101:    */      }
/* 102:    */    }
/* 103:    */    
/* 105:105 */    if ((this.held instanceof InputListener)) {
/* 106:106 */      this.container.getInput().addListener((InputListener)this.held);
/* 107:    */    }
/* 108:108 */    this.container.getInput().setScale(this.normalWidth / this.targetWidth, this.normalHeight / this.targetHeight);
/* 109:    */    
/* 112:112 */    int yoffset = 0;
/* 113:113 */    int xoffset = 0;
/* 114:    */    
/* 115:115 */    if (this.targetHeight < this.container.getHeight()) {
/* 116:116 */      yoffset = (this.container.getHeight() - this.targetHeight) / 2;
/* 117:    */    }
/* 118:118 */    if (this.targetWidth < this.container.getWidth()) {
/* 119:119 */      xoffset = (this.container.getWidth() - this.targetWidth) / 2;
/* 120:    */    }
/* 121:121 */    this.container.getInput().setOffset(-xoffset / (this.targetWidth / this.normalWidth), -yoffset / (this.targetHeight / this.normalHeight));
/* 122:    */  }
/* 123:    */  
/* 127:    */  public void update(GameContainer container, int delta)
/* 128:    */    throws SlickException
/* 129:    */  {
/* 130:130 */    if ((this.targetHeight != container.getHeight()) || (this.targetWidth != container.getWidth()))
/* 131:    */    {
/* 132:132 */      recalculateScale();
/* 133:    */    }
/* 134:    */    
/* 135:135 */    this.held.update(container, delta);
/* 136:    */  }
/* 137:    */  
/* 140:    */  public final void render(GameContainer container, Graphics g)
/* 141:    */    throws SlickException
/* 142:    */  {
/* 143:143 */    int yoffset = 0;
/* 144:144 */    int xoffset = 0;
/* 145:    */    
/* 146:146 */    if (this.targetHeight < container.getHeight()) {
/* 147:147 */      yoffset = (container.getHeight() - this.targetHeight) / 2;
/* 148:    */    }
/* 149:149 */    if (this.targetWidth < container.getWidth()) {
/* 150:150 */      xoffset = (container.getWidth() - this.targetWidth) / 2;
/* 151:    */    }
/* 152:    */    
/* 153:153 */    SlickCallable.enterSafeBlock();
/* 154:154 */    g.setClip(xoffset, yoffset, this.targetWidth, this.targetHeight);
/* 155:155 */    GL.glTranslatef(xoffset, yoffset, 0.0F);
/* 156:156 */    g.scale(this.targetWidth / this.normalWidth, this.targetHeight / this.normalHeight);
/* 157:157 */    GL.glPushMatrix();
/* 158:158 */    this.held.render(container, g);
/* 159:159 */    GL.glPopMatrix();
/* 160:160 */    g.clearClip();
/* 161:161 */    SlickCallable.leaveSafeBlock();
/* 162:    */    
/* 163:163 */    renderOverlay(container, g);
/* 164:    */  }
/* 165:    */  
/* 171:    */  protected void renderOverlay(GameContainer container, Graphics g) {}
/* 172:    */  
/* 177:    */  public boolean closeRequested()
/* 178:    */  {
/* 179:179 */    return this.held.closeRequested();
/* 180:    */  }
/* 181:    */  
/* 184:    */  public String getTitle()
/* 185:    */  {
/* 186:186 */    return this.held.getTitle();
/* 187:    */  }
/* 188:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.ScalableGame
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */