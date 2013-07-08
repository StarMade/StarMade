/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.awt.Canvas;
/*   4:    */import javax.swing.SwingUtilities;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */import org.lwjgl.opengl.Display;
/*   7:    */import org.newdawn.slick.util.Log;
/*   8:    */
/*  20:    */public class CanvasGameContainer
/*  21:    */  extends Canvas
/*  22:    */{
/*  23:    */  protected Container container;
/*  24:    */  protected Game game;
/*  25:    */  
/*  26:    */  public CanvasGameContainer(Game game)
/*  27:    */    throws SlickException
/*  28:    */  {
/*  29: 29 */    this(game, false);
/*  30:    */  }
/*  31:    */  
/*  40:    */  public CanvasGameContainer(Game game, boolean shared)
/*  41:    */    throws SlickException
/*  42:    */  {
/*  43: 43 */    this.game = game;
/*  44: 44 */    setIgnoreRepaint(true);
/*  45: 45 */    requestFocus();
/*  46: 46 */    setSize(500, 500);
/*  47:    */    
/*  48: 48 */    this.container = new Container(game, shared);
/*  49: 49 */    this.container.setForceExit(false);
/*  50:    */  }
/*  51:    */  
/*  55:    */  public void start()
/*  56:    */    throws SlickException
/*  57:    */  {
/*  58: 58 */    SwingUtilities.invokeLater(new Runnable()
/*  59:    */    {
/*  60:    */      public void run() {
/*  61:    */        try {
/*  62:    */          
/*  63:    */          try {
/*  64: 64 */            Display.setParent(CanvasGameContainer.this);
/*  65:    */          } catch (LWJGLException e) {
/*  66: 66 */            throw new SlickException("Failed to setParent of canvas", e);
/*  67:    */          }
/*  68:    */          
/*  69: 69 */          CanvasGameContainer.this.container.setup();
/*  70: 70 */          CanvasGameContainer.this.scheduleUpdate();
/*  71:    */        } catch (SlickException e) {
/*  72: 72 */          e.printStackTrace();
/*  73: 73 */          System.exit(0);
/*  74:    */        }
/*  75:    */      }
/*  76:    */    });
/*  77:    */  }
/*  78:    */  
/*  81:    */  private void scheduleUpdate()
/*  82:    */  {
/*  83: 83 */    if (!isVisible()) {
/*  84: 84 */      return;
/*  85:    */    }
/*  86:    */    
/*  87: 87 */    SwingUtilities.invokeLater(new Runnable() {
/*  88:    */      public void run() {
/*  89:    */        try {
/*  90: 90 */          CanvasGameContainer.this.container.gameLoop();
/*  91:    */        } catch (SlickException e) {
/*  92: 92 */          e.printStackTrace();
/*  93:    */        }
/*  94: 94 */        CanvasGameContainer.this.container.checkDimensions();
/*  95: 95 */        CanvasGameContainer.this.scheduleUpdate();
/*  96:    */      }
/*  97:    */    });
/*  98:    */  }
/*  99:    */  
/* 104:    */  public void dispose() {}
/* 105:    */  
/* 109:    */  public GameContainer getContainer()
/* 110:    */  {
/* 111:111 */    return this.container;
/* 112:    */  }
/* 113:    */  
/* 124:    */  private class Container
/* 125:    */    extends AppGameContainer
/* 126:    */  {
/* 127:    */    public Container(Game game, boolean shared)
/* 128:    */      throws SlickException
/* 129:    */    {
/* 130:130 */      super(CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
/* 131:    */      
/* 132:132 */      this.width = CanvasGameContainer.this.getWidth();
/* 133:133 */      this.height = CanvasGameContainer.this.getHeight();
/* 134:    */      
/* 135:135 */      if (shared) {
/* 136:136 */        enableSharedContext();
/* 137:    */      }
/* 138:    */    }
/* 139:    */    
/* 142:    */    protected void updateFPS()
/* 143:    */    {
/* 144:144 */      super.updateFPS();
/* 145:    */    }
/* 146:    */    
/* 149:    */    protected boolean running()
/* 150:    */    {
/* 151:151 */      return (super.running()) && (CanvasGameContainer.this.isDisplayable());
/* 152:    */    }
/* 153:    */    
/* 156:    */    public int getHeight()
/* 157:    */    {
/* 158:158 */      return CanvasGameContainer.this.getHeight();
/* 159:    */    }
/* 160:    */    
/* 163:    */    public int getWidth()
/* 164:    */    {
/* 165:165 */      return CanvasGameContainer.this.getWidth();
/* 166:    */    }
/* 167:    */    
/* 170:    */    public void checkDimensions()
/* 171:    */    {
/* 172:172 */      if ((this.width != CanvasGameContainer.this.getWidth()) || (this.height != CanvasGameContainer.this.getHeight()))
/* 173:    */      {
/* 174:    */        try
/* 175:    */        {
/* 176:176 */          setDisplayMode(CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
/* 177:    */        }
/* 178:    */        catch (SlickException e) {
/* 179:179 */          Log.error(e);
/* 180:    */        }
/* 181:    */      }
/* 182:    */    }
/* 183:    */  }
/* 184:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.CanvasGameContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */