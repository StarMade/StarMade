package org.newdawn.slick;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.LineStripRenderer;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.FastTrig;
import org.newdawn.slick.util.Log;

public class Graphics
{
  protected static SGL field_1687 = ;
  private static LineStripRenderer LSR = Renderer.getLineStripRenderer();
  public static int MODE_NORMAL = 1;
  public static int MODE_ALPHA_MAP = 2;
  public static int MODE_ALPHA_BLEND = 3;
  public static int MODE_COLOR_MULTIPLY = 4;
  public static int MODE_ADD = 5;
  public static int MODE_SCREEN = 6;
  private static final int DEFAULT_SEGMENTS = 50;
  protected static Graphics currentGraphics = null;
  protected static Font DEFAULT_FONT;
  private float field_1688 = 1.0F;
  private float field_1689 = 1.0F;
  private Font font;
  private Color currentColor = Color.white;
  protected int screenWidth;
  protected int screenHeight;
  private boolean pushed;
  private Rectangle clip;
  private DoubleBuffer worldClip = BufferUtils.createDoubleBuffer(4);
  private ByteBuffer readBuffer = BufferUtils.createByteBuffer(4);
  private boolean antialias;
  private Rectangle worldClipRecord;
  private int currentDrawingMode = MODE_NORMAL;
  private float lineWidth = 1.0F;
  private ArrayList stack = new ArrayList();
  private int stackIndex;
  
  public static void setCurrent(Graphics current)
  {
    if (currentGraphics != current)
    {
      if (currentGraphics != null) {
        currentGraphics.disable();
      }
      currentGraphics = current;
      currentGraphics.enable();
    }
  }
  
  public Graphics() {}
  
  public Graphics(int width, int height)
  {
    if (DEFAULT_FONT == null) {
      AccessController.doPrivileged(new PrivilegedAction()
      {
        public Object run()
        {
          try
          {
            Graphics.DEFAULT_FONT = new AngelCodeFont("org/newdawn/slick/data/defaultfont.fnt", "org/newdawn/slick/data/defaultfont.png");
          }
          catch (SlickException local_e)
          {
            Log.error(local_e);
          }
          return null;
        }
      });
    }
    this.font = DEFAULT_FONT;
    this.screenWidth = width;
    this.screenHeight = height;
  }
  
  void setDimensions(int width, int height)
  {
    this.screenWidth = width;
    this.screenHeight = height;
  }
  
  public void setDrawMode(int mode)
  {
    predraw();
    this.currentDrawingMode = mode;
    if (this.currentDrawingMode == MODE_NORMAL)
    {
      field_1687.glEnable(3042);
      field_1687.glColorMask(true, true, true, true);
      field_1687.glBlendFunc(770, 771);
    }
    if (this.currentDrawingMode == MODE_ALPHA_MAP)
    {
      field_1687.glDisable(3042);
      field_1687.glColorMask(false, false, false, true);
    }
    if (this.currentDrawingMode == MODE_ALPHA_BLEND)
    {
      field_1687.glEnable(3042);
      field_1687.glColorMask(true, true, true, false);
      field_1687.glBlendFunc(772, 773);
    }
    if (this.currentDrawingMode == MODE_COLOR_MULTIPLY)
    {
      field_1687.glEnable(3042);
      field_1687.glColorMask(true, true, true, true);
      field_1687.glBlendFunc(769, 768);
    }
    if (this.currentDrawingMode == MODE_ADD)
    {
      field_1687.glEnable(3042);
      field_1687.glColorMask(true, true, true, true);
      field_1687.glBlendFunc(1, 1);
    }
    if (this.currentDrawingMode == MODE_SCREEN)
    {
      field_1687.glEnable(3042);
      field_1687.glColorMask(true, true, true, true);
      field_1687.glBlendFunc(1, 769);
    }
    postdraw();
  }
  
  public void clearAlphaMap()
  {
    pushTransform();
    field_1687.glLoadIdentity();
    int originalMode = this.currentDrawingMode;
    setDrawMode(MODE_ALPHA_MAP);
    setColor(new Color(0, 0, 0, 0));
    fillRect(0.0F, 0.0F, this.screenWidth, this.screenHeight);
    setColor(this.currentColor);
    setDrawMode(originalMode);
    popTransform();
  }
  
  private void predraw()
  {
    setCurrent(this);
  }
  
  private void postdraw() {}
  
  protected void enable() {}
  
  public void flush()
  {
    if (currentGraphics == this)
    {
      currentGraphics.disable();
      currentGraphics = null;
    }
  }
  
  protected void disable() {}
  
  public Font getFont()
  {
    return this.font;
  }
  
  public void setBackground(Color color)
  {
    predraw();
    field_1687.glClearColor(color.field_1776, color.field_1777, color.field_1778, color.field_1779);
    postdraw();
  }
  
  public Color getBackground()
  {
    predraw();
    FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
    field_1687.glGetFloat(3106, buffer);
    postdraw();
    return new Color(buffer);
  }
  
  public void clear()
  {
    predraw();
    field_1687.glClear(16384);
    postdraw();
  }
  
  public void resetTransform()
  {
    this.field_1688 = 1.0F;
    this.field_1689 = 1.0F;
    if (this.pushed)
    {
      predraw();
      field_1687.glPopMatrix();
      this.pushed = false;
      postdraw();
    }
  }
  
  private void checkPush()
  {
    if (!this.pushed)
    {
      predraw();
      field_1687.glPushMatrix();
      this.pushed = true;
      postdraw();
    }
  }
  
  public void scale(float local_sx, float local_sy)
  {
    this.field_1688 *= local_sx;
    this.field_1689 *= local_sy;
    checkPush();
    predraw();
    field_1687.glScalef(local_sx, local_sy, 1.0F);
    postdraw();
  }
  
  public void rotate(float local_rx, float local_ry, float ang)
  {
    checkPush();
    predraw();
    translate(local_rx, local_ry);
    field_1687.glRotatef(ang, 0.0F, 0.0F, 1.0F);
    translate(-local_rx, -local_ry);
    postdraw();
  }
  
  public void translate(float local_x, float local_y)
  {
    checkPush();
    predraw();
    field_1687.glTranslatef(local_x, local_y, 0.0F);
    postdraw();
  }
  
  public void setFont(Font font)
  {
    this.font = font;
  }
  
  public void resetFont()
  {
    this.font = DEFAULT_FONT;
  }
  
  public void setColor(Color color)
  {
    if (color == null) {
      return;
    }
    this.currentColor = new Color(color);
    predraw();
    this.currentColor.bind();
    postdraw();
  }
  
  public Color getColor()
  {
    return new Color(this.currentColor);
  }
  
  public void drawLine(float local_x1, float local_y1, float local_x2, float local_y2)
  {
    float lineWidth = this.lineWidth - 1.0F;
    if (LSR.applyGLLineFixes())
    {
      if (local_x1 == local_x2)
      {
        if (local_y1 > local_y2)
        {
          float temp = local_y2;
          local_y2 = local_y1;
          local_y1 = temp;
        }
        float temp = 1.0F / this.field_1689;
        lineWidth /= this.field_1689;
        fillRect(local_x1 - lineWidth / 2.0F, local_y1 - lineWidth / 2.0F, lineWidth + temp, local_y2 - local_y1 + lineWidth + temp);
        return;
      }
      if (local_y1 == local_y2)
      {
        if (local_x1 > local_x2)
        {
          float temp = local_x2;
          local_x2 = local_x1;
          local_x1 = temp;
        }
        float temp = 1.0F / this.field_1688;
        lineWidth /= this.field_1688;
        fillRect(local_x1 - lineWidth / 2.0F, local_y1 - lineWidth / 2.0F, local_x2 - local_x1 + lineWidth + temp, lineWidth + temp);
        return;
      }
    }
    predraw();
    this.currentColor.bind();
    TextureImpl.bindNone();
    LSR.start();
    LSR.vertex(local_x1, local_y1);
    LSR.vertex(local_x2, local_y2);
    LSR.end();
    postdraw();
  }
  
  public void draw(Shape shape, ShapeFill fill)
  {
    predraw();
    TextureImpl.bindNone();
    ShapeRenderer.draw(shape, fill);
    this.currentColor.bind();
    postdraw();
  }
  
  public void fill(Shape shape, ShapeFill fill)
  {
    predraw();
    TextureImpl.bindNone();
    ShapeRenderer.fill(shape, fill);
    this.currentColor.bind();
    postdraw();
  }
  
  public void draw(Shape shape)
  {
    predraw();
    TextureImpl.bindNone();
    this.currentColor.bind();
    ShapeRenderer.draw(shape);
    postdraw();
  }
  
  public void fill(Shape shape)
  {
    predraw();
    TextureImpl.bindNone();
    this.currentColor.bind();
    ShapeRenderer.fill(shape);
    postdraw();
  }
  
  public void texture(Shape shape, Image image)
  {
    texture(shape, image, 0.01F, 0.01F, false);
  }
  
  public void texture(Shape shape, Image image, ShapeFill fill)
  {
    texture(shape, image, 0.01F, 0.01F, fill);
  }
  
  public void texture(Shape shape, Image image, boolean fit)
  {
    if (fit) {
      texture(shape, image, 1.0F, 1.0F, true);
    } else {
      texture(shape, image, 0.01F, 0.01F, false);
    }
  }
  
  public void texture(Shape shape, Image image, float scaleX, float scaleY)
  {
    texture(shape, image, scaleX, scaleY, false);
  }
  
  public void texture(Shape shape, Image image, float scaleX, float scaleY, boolean fit)
  {
    predraw();
    TextureImpl.bindNone();
    this.currentColor.bind();
    if (fit) {
      ShapeRenderer.textureFit(shape, image, scaleX, scaleY);
    } else {
      ShapeRenderer.texture(shape, image, scaleX, scaleY);
    }
    postdraw();
  }
  
  public void texture(Shape shape, Image image, float scaleX, float scaleY, ShapeFill fill)
  {
    predraw();
    TextureImpl.bindNone();
    this.currentColor.bind();
    ShapeRenderer.texture(shape, image, scaleX, scaleY, fill);
    postdraw();
  }
  
  public void drawRect(float local_x1, float local_y1, float width, float height)
  {
    float lineWidth = getLineWidth();
    drawLine(local_x1, local_y1, local_x1 + width, local_y1);
    drawLine(local_x1 + width, local_y1, local_x1 + width, local_y1 + height);
    drawLine(local_x1 + width, local_y1 + height, local_x1, local_y1 + height);
    drawLine(local_x1, local_y1 + height, local_x1, local_y1);
  }
  
  public void clearClip()
  {
    this.clip = null;
    predraw();
    field_1687.glDisable(3089);
    postdraw();
  }
  
  public void setWorldClip(float local_x, float local_y, float width, float height)
  {
    predraw();
    this.worldClipRecord = new Rectangle(local_x, local_y, width, height);
    field_1687.glEnable(12288);
    this.worldClip.put(1.0D).put(0.0D).put(0.0D).put(-local_x).flip();
    field_1687.glClipPlane(12288, this.worldClip);
    field_1687.glEnable(12289);
    this.worldClip.put(-1.0D).put(0.0D).put(0.0D).put(local_x + width).flip();
    field_1687.glClipPlane(12289, this.worldClip);
    field_1687.glEnable(12290);
    this.worldClip.put(0.0D).put(1.0D).put(0.0D).put(-local_y).flip();
    field_1687.glClipPlane(12290, this.worldClip);
    field_1687.glEnable(12291);
    this.worldClip.put(0.0D).put(-1.0D).put(0.0D).put(local_y + height).flip();
    field_1687.glClipPlane(12291, this.worldClip);
    postdraw();
  }
  
  public void clearWorldClip()
  {
    predraw();
    this.worldClipRecord = null;
    field_1687.glDisable(12288);
    field_1687.glDisable(12289);
    field_1687.glDisable(12290);
    field_1687.glDisable(12291);
    postdraw();
  }
  
  public void setWorldClip(Rectangle clip)
  {
    if (clip == null) {
      clearWorldClip();
    } else {
      setWorldClip(clip.getX(), clip.getY(), clip.getWidth(), clip.getHeight());
    }
  }
  
  public Rectangle getWorldClip()
  {
    return this.worldClipRecord;
  }
  
  public void setClip(int local_x, int local_y, int width, int height)
  {
    predraw();
    if (this.clip == null)
    {
      field_1687.glEnable(3089);
      this.clip = new Rectangle(local_x, local_y, width, height);
    }
    else
    {
      this.clip.setBounds(local_x, local_y, width, height);
    }
    field_1687.glScissor(local_x, this.screenHeight - local_y - height, width, height);
    postdraw();
  }
  
  public void setClip(Rectangle rect)
  {
    if (rect == null)
    {
      clearClip();
      return;
    }
    setClip((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
  }
  
  public Rectangle getClip()
  {
    return this.clip;
  }
  
  public void fillRect(float local_x, float local_y, float width, float height, Image pattern, float offX, float offY)
  {
    int cols = (int)Math.ceil(width / pattern.getWidth()) + 2;
    int rows = (int)Math.ceil(height / pattern.getHeight()) + 2;
    Rectangle preClip = getWorldClip();
    setWorldClip(local_x, local_y, width, height);
    predraw();
    for (int local_c = 0; local_c < cols; local_c++) {
      for (int local_r = 0; local_r < rows; local_r++) {
        pattern.draw(local_c * pattern.getWidth() + local_x - offX, local_r * pattern.getHeight() + local_y - offY);
      }
    }
    postdraw();
    setWorldClip(preClip);
  }
  
  public void fillRect(float local_x1, float local_y1, float width, float height)
  {
    predraw();
    TextureImpl.bindNone();
    this.currentColor.bind();
    field_1687.glBegin(7);
    field_1687.glVertex2f(local_x1, local_y1);
    field_1687.glVertex2f(local_x1 + width, local_y1);
    field_1687.glVertex2f(local_x1 + width, local_y1 + height);
    field_1687.glVertex2f(local_x1, local_y1 + height);
    field_1687.glEnd();
    postdraw();
  }
  
  public void drawOval(float local_x1, float local_y1, float width, float height)
  {
    drawOval(local_x1, local_y1, width, height, 50);
  }
  
  public void drawOval(float local_x1, float local_y1, float width, float height, int segments)
  {
    drawArc(local_x1, local_y1, width, height, segments, 0.0F, 360.0F);
  }
  
  public void drawArc(float local_x1, float local_y1, float width, float height, float start, float end)
  {
    drawArc(local_x1, local_y1, width, height, 50, start, end);
  }
  
  public void drawArc(float local_x1, float local_y1, float width, float height, int segments, float start, float end)
  {
    predraw();
    TextureImpl.bindNone();
    this.currentColor.bind();
    while (end < start) {
      end += 360.0F;
    }
    float local_cx = local_x1 + width / 2.0F;
    float local_cy = local_y1 + height / 2.0F;
    LSR.start();
    int step = 360 / segments;
    int local_a = (int)start;
    while (local_a < (int)(end + step))
    {
      float ang = local_a;
      if (ang > end) {
        ang = end;
      }
      float local_x = (float)(local_cx + FastTrig.cos(Math.toRadians(ang)) * width / 2.0D);
      float local_y = (float)(local_cy + FastTrig.sin(Math.toRadians(ang)) * height / 2.0D);
      LSR.vertex(local_x, local_y);
      local_a += step;
    }
    LSR.end();
    postdraw();
  }
  
  public void fillOval(float local_x1, float local_y1, float width, float height)
  {
    fillOval(local_x1, local_y1, width, height, 50);
  }
  
  public void fillOval(float local_x1, float local_y1, float width, float height, int segments)
  {
    fillArc(local_x1, local_y1, width, height, segments, 0.0F, 360.0F);
  }
  
  public void fillArc(float local_x1, float local_y1, float width, float height, float start, float end)
  {
    fillArc(local_x1, local_y1, width, height, 50, start, end);
  }
  
  public void fillArc(float local_x1, float local_y1, float width, float height, int segments, float start, float end)
  {
    predraw();
    TextureImpl.bindNone();
    this.currentColor.bind();
    while (end < start) {
      end += 360.0F;
    }
    float local_cx = local_x1 + width / 2.0F;
    float local_cy = local_y1 + height / 2.0F;
    field_1687.glBegin(6);
    int step = 360 / segments;
    field_1687.glVertex2f(local_cx, local_cy);
    int local_a = (int)start;
    while (local_a < (int)(end + step))
    {
      float ang = local_a;
      if (ang > end) {
        ang = end;
      }
      float local_x = (float)(local_cx + FastTrig.cos(Math.toRadians(ang)) * width / 2.0D);
      float local_y = (float)(local_cy + FastTrig.sin(Math.toRadians(ang)) * height / 2.0D);
      field_1687.glVertex2f(local_x, local_y);
      local_a += step;
    }
    field_1687.glEnd();
    if (this.antialias)
    {
      field_1687.glBegin(6);
      field_1687.glVertex2f(local_cx, local_cy);
      if (end != 360.0F) {
        end -= 10.0F;
      }
      int local_a = (int)start;
      while (local_a < (int)(end + step))
      {
        float ang = local_a;
        if (ang > end) {
          ang = end;
        }
        float local_x = (float)(local_cx + FastTrig.cos(Math.toRadians(ang + 10.0F)) * width / 2.0D);
        float local_y = (float)(local_cy + FastTrig.sin(Math.toRadians(ang + 10.0F)) * height / 2.0D);
        field_1687.glVertex2f(local_x, local_y);
        local_a += step;
      }
      field_1687.glEnd();
    }
    postdraw();
  }
  
  public void drawRoundRect(float local_x, float local_y, float width, float height, int cornerRadius)
  {
    drawRoundRect(local_x, local_y, width, height, cornerRadius, 50);
  }
  
  public void drawRoundRect(float local_x, float local_y, float width, float height, int cornerRadius, int segs)
  {
    if (cornerRadius < 0) {
      throw new IllegalArgumentException("corner radius must be > 0");
    }
    if (cornerRadius == 0)
    {
      drawRect(local_x, local_y, width, height);
      return;
    }
    int local_mr = (int)Math.min(width, height) / 2;
    if (cornerRadius > local_mr) {
      cornerRadius = local_mr;
    }
    drawLine(local_x + cornerRadius, local_y, local_x + width - cornerRadius, local_y);
    drawLine(local_x, local_y + cornerRadius, local_x, local_y + height - cornerRadius);
    drawLine(local_x + width, local_y + cornerRadius, local_x + width, local_y + height - cornerRadius);
    drawLine(local_x + cornerRadius, local_y + height, local_x + width - cornerRadius, local_y + height);
    float local_d = cornerRadius * 2;
    drawArc(local_x + width - local_d, local_y + height - local_d, local_d, local_d, segs, 0.0F, 90.0F);
    drawArc(local_x, local_y + height - local_d, local_d, local_d, segs, 90.0F, 180.0F);
    drawArc(local_x + width - local_d, local_y, local_d, local_d, segs, 270.0F, 360.0F);
    drawArc(local_x, local_y, local_d, local_d, segs, 180.0F, 270.0F);
  }
  
  public void fillRoundRect(float local_x, float local_y, float width, float height, int cornerRadius)
  {
    fillRoundRect(local_x, local_y, width, height, cornerRadius, 50);
  }
  
  public void fillRoundRect(float local_x, float local_y, float width, float height, int cornerRadius, int segs)
  {
    if (cornerRadius < 0) {
      throw new IllegalArgumentException("corner radius must be > 0");
    }
    if (cornerRadius == 0)
    {
      fillRect(local_x, local_y, width, height);
      return;
    }
    int local_mr = (int)Math.min(width, height) / 2;
    if (cornerRadius > local_mr) {
      cornerRadius = local_mr;
    }
    float local_d = cornerRadius * 2;
    fillRect(local_x + cornerRadius, local_y, width - local_d, cornerRadius);
    fillRect(local_x, local_y + cornerRadius, cornerRadius, height - local_d);
    fillRect(local_x + width - cornerRadius, local_y + cornerRadius, cornerRadius, height - local_d);
    fillRect(local_x + cornerRadius, local_y + height - cornerRadius, width - local_d, cornerRadius);
    fillRect(local_x + cornerRadius, local_y + cornerRadius, width - local_d, height - local_d);
    fillArc(local_x + width - local_d, local_y + height - local_d, local_d, local_d, segs, 0.0F, 90.0F);
    fillArc(local_x, local_y + height - local_d, local_d, local_d, segs, 90.0F, 180.0F);
    fillArc(local_x + width - local_d, local_y, local_d, local_d, segs, 270.0F, 360.0F);
    fillArc(local_x, local_y, local_d, local_d, segs, 180.0F, 270.0F);
  }
  
  public void setLineWidth(float width)
  {
    predraw();
    this.lineWidth = width;
    LSR.setWidth(width);
    field_1687.glPointSize(width);
    postdraw();
  }
  
  public float getLineWidth()
  {
    return this.lineWidth;
  }
  
  public void resetLineWidth()
  {
    predraw();
    Renderer.getLineStripRenderer().setWidth(1.0F);
    field_1687.glLineWidth(1.0F);
    field_1687.glPointSize(1.0F);
    postdraw();
  }
  
  public void setAntiAlias(boolean anti)
  {
    predraw();
    this.antialias = anti;
    LSR.setAntiAlias(anti);
    if (anti) {
      field_1687.glEnable(2881);
    } else {
      field_1687.glDisable(2881);
    }
    postdraw();
  }
  
  public boolean isAntiAlias()
  {
    return this.antialias;
  }
  
  public void drawString(String str, float local_x, float local_y)
  {
    predraw();
    this.font.drawString(local_x, local_y, str, this.currentColor);
    postdraw();
  }
  
  public void drawImage(Image image, float local_x, float local_y, Color col)
  {
    predraw();
    image.draw(local_x, local_y, col);
    this.currentColor.bind();
    postdraw();
  }
  
  public void drawAnimation(Animation anim, float local_x, float local_y)
  {
    drawAnimation(anim, local_x, local_y, Color.white);
  }
  
  public void drawAnimation(Animation anim, float local_x, float local_y, Color col)
  {
    predraw();
    anim.draw(local_x, local_y, col);
    this.currentColor.bind();
    postdraw();
  }
  
  public void drawImage(Image image, float local_x, float local_y)
  {
    drawImage(image, local_x, local_y, Color.white);
  }
  
  public void drawImage(Image image, float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2)
  {
    predraw();
    image.draw(local_x, local_y, local_x2, local_y2, srcx, srcy, srcx2, srcy2);
    this.currentColor.bind();
    postdraw();
  }
  
  public void drawImage(Image image, float local_x, float local_y, float srcx, float srcy, float srcx2, float srcy2)
  {
    drawImage(image, local_x, local_y, local_x + image.getWidth(), local_y + image.getHeight(), srcx, srcy, srcx2, srcy2);
  }
  
  public void copyArea(Image target, int local_x, int local_y)
  {
    int format = target.getTexture().hasAlpha() ? 6408 : 6407;
    target.bind();
    field_1687.glCopyTexImage2D(3553, 0, format, local_x, this.screenHeight - (local_y + target.getHeight()), target.getTexture().getTextureWidth(), target.getTexture().getTextureHeight(), 0);
    target.ensureInverted();
  }
  
  private int translate(byte local_b)
  {
    if (local_b < 0) {
      return 256 + local_b;
    }
    return local_b;
  }
  
  public Color getPixel(int local_x, int local_y)
  {
    predraw();
    field_1687.glReadPixels(local_x, this.screenHeight - local_y, 1, 1, 6408, 5121, this.readBuffer);
    postdraw();
    return new Color(translate(this.readBuffer.get(0)), translate(this.readBuffer.get(1)), translate(this.readBuffer.get(2)), translate(this.readBuffer.get(3)));
  }
  
  public void getArea(int local_x, int local_y, int width, int height, ByteBuffer target)
  {
    if (target.capacity() < width * height * 4) {
      throw new IllegalArgumentException("Byte buffer provided to get area is not big enough");
    }
    predraw();
    field_1687.glReadPixels(local_x, this.screenHeight - local_y - height, width, height, 6408, 5121, target);
    postdraw();
  }
  
  public void drawImage(Image image, float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2, Color col)
  {
    predraw();
    image.draw(local_x, local_y, local_x2, local_y2, srcx, srcy, srcx2, srcy2, col);
    this.currentColor.bind();
    postdraw();
  }
  
  public void drawImage(Image image, float local_x, float local_y, float srcx, float srcy, float srcx2, float srcy2, Color col)
  {
    drawImage(image, local_x, local_y, local_x + image.getWidth(), local_y + image.getHeight(), srcx, srcy, srcx2, srcy2, col);
  }
  
  public void drawGradientLine(float local_x1, float local_y1, float red1, float green1, float blue1, float alpha1, float local_x2, float local_y2, float red2, float green2, float blue2, float alpha2)
  {
    predraw();
    TextureImpl.bindNone();
    field_1687.glBegin(1);
    field_1687.glColor4f(red1, green1, blue1, alpha1);
    field_1687.glVertex2f(local_x1, local_y1);
    field_1687.glColor4f(red2, green2, blue2, alpha2);
    field_1687.glVertex2f(local_x2, local_y2);
    field_1687.glEnd();
    postdraw();
  }
  
  public void drawGradientLine(float local_x1, float local_y1, Color Color1, float local_x2, float local_y2, Color Color2)
  {
    predraw();
    TextureImpl.bindNone();
    field_1687.glBegin(1);
    Color1.bind();
    field_1687.glVertex2f(local_x1, local_y1);
    Color2.bind();
    field_1687.glVertex2f(local_x2, local_y2);
    field_1687.glEnd();
    postdraw();
  }
  
  public void pushTransform()
  {
    predraw();
    FloatBuffer buffer;
    if (this.stackIndex >= this.stack.size())
    {
      FloatBuffer buffer = BufferUtils.createFloatBuffer(18);
      this.stack.add(buffer);
    }
    else
    {
      buffer = (FloatBuffer)this.stack.get(this.stackIndex);
    }
    field_1687.glGetFloat(2982, buffer);
    buffer.put(16, this.field_1688);
    buffer.put(17, this.field_1689);
    this.stackIndex += 1;
    postdraw();
  }
  
  public void popTransform()
  {
    if (this.stackIndex == 0) {
      throw new RuntimeException("Attempt to pop a transform that hasn't be pushed");
    }
    predraw();
    this.stackIndex -= 1;
    FloatBuffer oldBuffer = (FloatBuffer)this.stack.get(this.stackIndex);
    field_1687.glLoadMatrix(oldBuffer);
    this.field_1688 = oldBuffer.get(16);
    this.field_1689 = oldBuffer.get(17);
    postdraw();
  }
  
  public void destroy() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.Graphics
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */