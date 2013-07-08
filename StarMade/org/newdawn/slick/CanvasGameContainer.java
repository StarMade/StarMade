package org.newdawn.slick;

import java.awt.Canvas;
import javax.swing.SwingUtilities;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.util.Log;

public class CanvasGameContainer
  extends Canvas
{
  protected Container container;
  protected Game game;
  
  public CanvasGameContainer(Game game)
    throws SlickException
  {
    this(game, false);
  }
  
  public CanvasGameContainer(Game game, boolean shared)
    throws SlickException
  {
    this.game = game;
    setIgnoreRepaint(true);
    requestFocus();
    setSize(500, 500);
    this.container = new Container(game, shared);
    this.container.setForceExit(false);
  }
  
  public void start()
    throws SlickException
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          
          try
          {
            Display.setParent(CanvasGameContainer.this);
          }
          catch (LWJGLException local_e)
          {
            throw new SlickException("Failed to setParent of canvas", local_e);
          }
          CanvasGameContainer.this.container.setup();
          CanvasGameContainer.this.scheduleUpdate();
        }
        catch (SlickException local_e)
        {
          local_e.printStackTrace();
          System.exit(0);
        }
      }
    });
  }
  
  private void scheduleUpdate()
  {
    if (!isVisible()) {
      return;
    }
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          CanvasGameContainer.this.container.gameLoop();
        }
        catch (SlickException local_e)
        {
          local_e.printStackTrace();
        }
        CanvasGameContainer.this.container.checkDimensions();
        CanvasGameContainer.this.scheduleUpdate();
      }
    });
  }
  
  public void dispose() {}
  
  public GameContainer getContainer()
  {
    return this.container;
  }
  
  private class Container
    extends AppGameContainer
  {
    public Container(Game game, boolean shared)
      throws SlickException
    {
      super(CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
      this.width = CanvasGameContainer.this.getWidth();
      this.height = CanvasGameContainer.this.getHeight();
      if (shared) {
        enableSharedContext();
      }
    }
    
    protected void updateFPS()
    {
      super.updateFPS();
    }
    
    protected boolean running()
    {
      return (super.running()) && (CanvasGameContainer.this.isDisplayable());
    }
    
    public int getHeight()
    {
      return CanvasGameContainer.this.getHeight();
    }
    
    public int getWidth()
    {
      return CanvasGameContainer.this.getWidth();
    }
    
    public void checkDimensions()
    {
      if ((this.width != CanvasGameContainer.this.getWidth()) || (this.height != CanvasGameContainer.this.getHeight())) {
        try
        {
          setDisplayMode(CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
        }
        catch (SlickException local_e)
        {
          Log.error(local_e);
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.CanvasGameContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */