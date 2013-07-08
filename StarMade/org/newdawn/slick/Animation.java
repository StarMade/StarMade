package org.newdawn.slick;

import java.util.ArrayList;
import org.lwjgl.Sys;
import org.newdawn.slick.util.Log;

public class Animation
  implements Renderable
{
  private ArrayList frames = new ArrayList();
  private int currentFrame = -1;
  private long nextChange = 0L;
  private boolean stopped = false;
  private long timeLeft;
  private float speed = 1.0F;
  private int stopAt = -2;
  private long lastUpdate;
  private boolean firstUpdate = true;
  private boolean autoUpdate = true;
  private int direction = 1;
  private boolean pingPong;
  private boolean loop = true;
  private SpriteSheet spriteSheet = null;
  
  public Animation()
  {
    this(true);
  }
  
  public Animation(Image[] frames, int duration)
  {
    this(frames, duration, true);
  }
  
  public Animation(Image[] frames, int[] durations)
  {
    this(frames, durations, true);
  }
  
  public Animation(boolean autoUpdate)
  {
    this.currentFrame = 0;
    this.autoUpdate = autoUpdate;
  }
  
  public Animation(Image[] frames, int duration, boolean autoUpdate)
  {
    for (int local_i = 0; local_i < frames.length; local_i++) {
      addFrame(frames[local_i], duration);
    }
    this.currentFrame = 0;
    this.autoUpdate = autoUpdate;
  }
  
  public Animation(Image[] frames, int[] durations, boolean autoUpdate)
  {
    this.autoUpdate = autoUpdate;
    if (frames.length != durations.length) {
      throw new RuntimeException("There must be one duration per frame");
    }
    for (int local_i = 0; local_i < frames.length; local_i++) {
      addFrame(frames[local_i], durations[local_i]);
    }
    this.currentFrame = 0;
  }
  
  public Animation(SpriteSheet frames, int duration)
  {
    this(frames, 0, 0, frames.getHorizontalCount() - 1, frames.getVerticalCount() - 1, true, duration, true);
  }
  
  public Animation(SpriteSheet frames, int local_x1, int local_y1, int local_x2, int local_y2, boolean horizontalScan, int duration, boolean autoUpdate)
  {
    this.autoUpdate = autoUpdate;
    if (!horizontalScan) {
      for (int local_x = local_x1; local_x <= local_x2; local_x++) {
        for (int local_y = local_y1; local_y <= local_y2; local_y++) {
          addFrame(frames.getSprite(local_x, local_y), duration);
        }
      }
    } else {
      for (int local_x = local_y1; local_x <= local_y2; local_x++) {
        for (int local_y = local_x1; local_y <= local_x2; local_y++) {
          addFrame(frames.getSprite(local_y, local_x), duration);
        }
      }
    }
  }
  
  public Animation(SpriteSheet local_ss, int[] frames, int[] duration)
  {
    this.spriteSheet = local_ss;
    int local_x = -1;
    int local_y = -1;
    for (int local_i = 0; local_i < frames.length / 2; local_i++)
    {
      local_x = frames[(local_i * 2)];
      local_y = frames[(local_i * 2 + 1)];
      addFrame(duration[local_i], local_x, local_y);
    }
  }
  
  public void addFrame(int duration, int local_x, int local_y)
  {
    if (duration == 0)
    {
      Log.error("Invalid duration: " + duration);
      throw new RuntimeException("Invalid duration: " + duration);
    }
    if (this.frames.isEmpty()) {
      this.nextChange = ((int)(duration / this.speed));
    }
    this.frames.add(new Frame(duration, local_x, local_y));
    this.currentFrame = 0;
  }
  
  public void setAutoUpdate(boolean auto)
  {
    this.autoUpdate = auto;
  }
  
  public void setPingPong(boolean pingPong)
  {
    this.pingPong = pingPong;
  }
  
  public boolean isStopped()
  {
    return this.stopped;
  }
  
  public void setSpeed(float spd)
  {
    if (spd > 0.0F)
    {
      this.nextChange = (((float)this.nextChange * this.speed / spd));
      this.speed = spd;
    }
  }
  
  public float getSpeed()
  {
    return this.speed;
  }
  
  public void stop()
  {
    if (this.frames.size() == 0) {
      return;
    }
    this.timeLeft = this.nextChange;
    this.stopped = true;
  }
  
  public void start()
  {
    if (!this.stopped) {
      return;
    }
    if (this.frames.size() == 0) {
      return;
    }
    this.stopped = false;
    this.nextChange = this.timeLeft;
  }
  
  public void restart()
  {
    if (this.frames.size() == 0) {
      return;
    }
    this.stopped = false;
    this.currentFrame = 0;
    this.nextChange = ((int)(((Frame)this.frames.get(0)).duration / this.speed));
    this.firstUpdate = true;
    this.lastUpdate = 0L;
  }
  
  public void addFrame(Image frame, int duration)
  {
    if (duration == 0)
    {
      Log.error("Invalid duration: " + duration);
      throw new RuntimeException("Invalid duration: " + duration);
    }
    if (this.frames.isEmpty()) {
      this.nextChange = ((int)(duration / this.speed));
    }
    this.frames.add(new Frame(frame, duration));
    this.currentFrame = 0;
  }
  
  public void draw()
  {
    draw(0.0F, 0.0F);
  }
  
  public void draw(float local_x, float local_y)
  {
    draw(local_x, local_y, getWidth(), getHeight());
  }
  
  public void draw(float local_x, float local_y, Color filter)
  {
    draw(local_x, local_y, getWidth(), getHeight(), filter);
  }
  
  public void draw(float local_x, float local_y, float width, float height)
  {
    draw(local_x, local_y, width, height, Color.white);
  }
  
  public void draw(float local_x, float local_y, float width, float height, Color col)
  {
    if (this.frames.size() == 0) {
      return;
    }
    if (this.autoUpdate)
    {
      long now = getTime();
      long delta = now - this.lastUpdate;
      if (this.firstUpdate)
      {
        delta = 0L;
        this.firstUpdate = false;
      }
      this.lastUpdate = now;
      nextFrame(delta);
    }
    Frame now = (Frame)this.frames.get(this.currentFrame);
    now.image.draw(local_x, local_y, width, height, col);
  }
  
  public void renderInUse(int local_x, int local_y)
  {
    if (this.frames.size() == 0) {
      return;
    }
    if (this.autoUpdate)
    {
      long now = getTime();
      long delta = now - this.lastUpdate;
      if (this.firstUpdate)
      {
        delta = 0L;
        this.firstUpdate = false;
      }
      this.lastUpdate = now;
      nextFrame(delta);
    }
    Frame now = (Frame)this.frames.get(this.currentFrame);
    this.spriteSheet.renderInUse(local_x, local_y, now.field_1805, now.field_1806);
  }
  
  public int getWidth()
  {
    return ((Frame)this.frames.get(this.currentFrame)).image.getWidth();
  }
  
  public int getHeight()
  {
    return ((Frame)this.frames.get(this.currentFrame)).image.getHeight();
  }
  
  public void drawFlash(float local_x, float local_y, float width, float height)
  {
    drawFlash(local_x, local_y, width, height, Color.white);
  }
  
  public void drawFlash(float local_x, float local_y, float width, float height, Color col)
  {
    if (this.frames.size() == 0) {
      return;
    }
    if (this.autoUpdate)
    {
      long now = getTime();
      long delta = now - this.lastUpdate;
      if (this.firstUpdate)
      {
        delta = 0L;
        this.firstUpdate = false;
      }
      this.lastUpdate = now;
      nextFrame(delta);
    }
    Frame now = (Frame)this.frames.get(this.currentFrame);
    now.image.drawFlash(local_x, local_y, width, height, col);
  }
  
  /**
   * @deprecated
   */
  public void updateNoDraw()
  {
    if (this.autoUpdate)
    {
      long now = getTime();
      long delta = now - this.lastUpdate;
      if (this.firstUpdate)
      {
        delta = 0L;
        this.firstUpdate = false;
      }
      this.lastUpdate = now;
      nextFrame(delta);
    }
  }
  
  public void update(long delta)
  {
    nextFrame(delta);
  }
  
  public int getFrame()
  {
    return this.currentFrame;
  }
  
  public void setCurrentFrame(int index)
  {
    this.currentFrame = index;
  }
  
  public Image getImage(int index)
  {
    Frame frame = (Frame)this.frames.get(index);
    return frame.image;
  }
  
  public int getFrameCount()
  {
    return this.frames.size();
  }
  
  public Image getCurrentFrame()
  {
    Frame frame = (Frame)this.frames.get(this.currentFrame);
    return frame.image;
  }
  
  private void nextFrame(long delta)
  {
    if (this.stopped) {
      return;
    }
    if (this.frames.size() == 0) {
      return;
    }
    int realDuration;
    for (this.nextChange -= delta; (this.nextChange < 0L) && (!this.stopped); this.nextChange += realDuration)
    {
      if (this.currentFrame == this.stopAt)
      {
        this.stopped = true;
        break;
      }
      if ((this.currentFrame == this.frames.size() - 1) && (!this.loop) && (!this.pingPong))
      {
        this.stopped = true;
        break;
      }
      this.currentFrame = ((this.currentFrame + this.direction) % this.frames.size());
      if (this.pingPong) {
        if (this.currentFrame <= 0)
        {
          this.currentFrame = 0;
          this.direction = 1;
          if (!this.loop)
          {
            this.stopped = true;
            break;
          }
        }
        else if (this.currentFrame >= this.frames.size() - 1)
        {
          this.currentFrame = (this.frames.size() - 1);
          this.direction = -1;
        }
      }
      realDuration = (int)(((Frame)this.frames.get(this.currentFrame)).duration / this.speed);
    }
  }
  
  public void setLooping(boolean loop)
  {
    this.loop = loop;
  }
  
  private long getTime()
  {
    return Sys.getTime() * 1000L / Sys.getTimerResolution();
  }
  
  public void stopAt(int frameIndex)
  {
    this.stopAt = frameIndex;
  }
  
  public int getDuration(int index)
  {
    return ((Frame)this.frames.get(index)).duration;
  }
  
  public void setDuration(int index, int duration)
  {
    ((Frame)this.frames.get(index)).duration = duration;
  }
  
  public int[] getDurations()
  {
    int[] durations = new int[this.frames.size()];
    for (int local_i = 0; local_i < this.frames.size(); local_i++) {
      durations[local_i] = getDuration(local_i);
    }
    return durations;
  }
  
  public String toString()
  {
    String res = "[Animation (" + this.frames.size() + ") ";
    for (int local_i = 0; local_i < this.frames.size(); local_i++)
    {
      Frame frame = (Frame)this.frames.get(local_i);
      res = res + frame.duration + ",";
    }
    res = res + "]";
    return res;
  }
  
  public Animation copy()
  {
    Animation copy = new Animation();
    copy.spriteSheet = this.spriteSheet;
    copy.frames = this.frames;
    copy.autoUpdate = this.autoUpdate;
    copy.direction = this.direction;
    copy.loop = this.loop;
    copy.pingPong = this.pingPong;
    copy.speed = this.speed;
    return copy;
  }
  
  private class Frame
  {
    public Image image;
    public int duration;
    public int field_1805 = -1;
    public int field_1806 = -1;
    
    public Frame(Image image, int duration)
    {
      this.image = image;
      this.duration = duration;
    }
    
    public Frame(int duration, int local_x, int local_y)
    {
      this.image = Animation.this.spriteSheet.getSubImage(local_x, local_y);
      this.duration = duration;
      this.field_1805 = local_x;
      this.field_1806 = local_y;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.Animation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */