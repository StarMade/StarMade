package org.newdawn.slick;

import java.io.IOException;
import java.io.InputStream;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.pbuffer.GraphicsFactory;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.Log;

public class Image
  implements Renderable
{
  public static final int TOP_LEFT = 0;
  public static final int TOP_RIGHT = 1;
  public static final int BOTTOM_RIGHT = 2;
  public static final int BOTTOM_LEFT = 3;
  protected static SGL field_204 = ;
  protected static Image inUse;
  public static final int FILTER_LINEAR = 1;
  public static final int FILTER_NEAREST = 2;
  protected Texture texture;
  protected int width;
  protected int height;
  protected float textureWidth;
  protected float textureHeight;
  protected float textureOffsetX;
  protected float textureOffsetY;
  protected float angle;
  protected float alpha = 1.0F;
  protected String ref;
  protected boolean inited = false;
  protected byte[] pixelData;
  protected boolean destroyed;
  protected float centerX;
  protected float centerY;
  protected String name;
  protected Color[] corners;
  private int filter = 9729;
  private boolean flipped;
  private Color transparent;
  
  protected Image(Image other)
  {
    this.width = other.getWidth();
    this.height = other.getHeight();
    this.texture = other.texture;
    this.textureWidth = other.textureWidth;
    this.textureHeight = other.textureHeight;
    this.ref = other.ref;
    this.textureOffsetX = other.textureOffsetX;
    this.textureOffsetY = other.textureOffsetY;
    this.centerX = (this.width / 2);
    this.centerY = (this.height / 2);
    this.inited = true;
  }
  
  protected Image() {}
  
  public Image(Texture texture)
  {
    this.texture = texture;
    this.ref = texture.toString();
    clampTexture();
  }
  
  public Image(String ref)
    throws SlickException
  {
    this(ref, false);
  }
  
  public Image(String ref, Color trans)
    throws SlickException
  {
    this(ref, false, 1, trans);
  }
  
  public Image(String ref, boolean flipped)
    throws SlickException
  {
    this(ref, flipped, 1);
  }
  
  public Image(String ref, boolean flipped, int filter)
    throws SlickException
  {
    this(ref, flipped, filter, null);
  }
  
  public Image(String ref, boolean flipped, int local_f, Color transparent)
    throws SlickException
  {
    this.filter = (local_f == 1 ? 9729 : 9728);
    this.transparent = transparent;
    this.flipped = flipped;
    try
    {
      this.ref = ref;
      int[] trans = null;
      if (transparent != null)
      {
        trans = new int[3];
        trans[0] = ((int)(transparent.field_1776 * 255.0F));
        trans[1] = ((int)(transparent.field_1777 * 255.0F));
        trans[2] = ((int)(transparent.field_1778 * 255.0F));
      }
      this.texture = InternalTextureLoader.get().getTexture(ref, flipped, this.filter, trans);
    }
    catch (IOException trans)
    {
      Log.error(trans);
      throw new SlickException("Failed to load image from: " + ref, trans);
    }
  }
  
  public void setFilter(int local_f)
  {
    this.filter = (local_f == 1 ? 9729 : 9728);
    this.texture.bind();
    field_204.glTexParameteri(3553, 10241, this.filter);
    field_204.glTexParameteri(3553, 10240, this.filter);
  }
  
  public Image(int width, int height)
    throws SlickException
  {
    this(width, height, 2);
  }
  
  public Image(int width, int height, int local_f)
    throws SlickException
  {
    this.ref = super.toString();
    this.filter = (local_f == 1 ? 9729 : 9728);
    try
    {
      this.texture = InternalTextureLoader.get().createTexture(width, height, this.filter);
    }
    catch (IOException local_e)
    {
      Log.error(local_e);
      throw new SlickException("Failed to create empty image " + width + "x" + height);
    }
    init();
  }
  
  public Image(InputStream local_in, String ref, boolean flipped)
    throws SlickException
  {
    this(local_in, ref, flipped, 1);
  }
  
  public Image(InputStream local_in, String ref, boolean flipped, int filter)
    throws SlickException
  {
    load(local_in, ref, flipped, filter, null);
  }
  
  Image(ImageBuffer buffer)
  {
    this(buffer, 1);
    TextureImpl.bindNone();
  }
  
  Image(ImageBuffer buffer, int filter)
  {
    this(buffer, filter);
    TextureImpl.bindNone();
  }
  
  public Image(ImageData data)
  {
    this(data, 1);
  }
  
  public Image(ImageData data, int local_f)
  {
    try
    {
      this.filter = (local_f == 1 ? 9729 : 9728);
      this.texture = InternalTextureLoader.get().getTexture(data, this.filter);
      this.ref = this.texture.toString();
    }
    catch (IOException local_e)
    {
      Log.error(local_e);
    }
  }
  
  public int getFilter()
  {
    return this.filter;
  }
  
  public String getResourceReference()
  {
    return this.ref;
  }
  
  public void setImageColor(float local_r, float local_g, float local_b, float local_a)
  {
    setColor(0, local_r, local_g, local_b, local_a);
    setColor(1, local_r, local_g, local_b, local_a);
    setColor(3, local_r, local_g, local_b, local_a);
    setColor(2, local_r, local_g, local_b, local_a);
  }
  
  public void setImageColor(float local_r, float local_g, float local_b)
  {
    setColor(0, local_r, local_g, local_b);
    setColor(1, local_r, local_g, local_b);
    setColor(3, local_r, local_g, local_b);
    setColor(2, local_r, local_g, local_b);
  }
  
  public void setColor(int corner, float local_r, float local_g, float local_b, float local_a)
  {
    if (this.corners == null) {
      this.corners = new Color[] { new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F) };
    }
    this.corners[corner].field_1776 = local_r;
    this.corners[corner].field_1777 = local_g;
    this.corners[corner].field_1778 = local_b;
    this.corners[corner].field_1779 = local_a;
  }
  
  public void setColor(int corner, float local_r, float local_g, float local_b)
  {
    if (this.corners == null) {
      this.corners = new Color[] { new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F), new Color(1.0F, 1.0F, 1.0F, 1.0F) };
    }
    this.corners[corner].field_1776 = local_r;
    this.corners[corner].field_1777 = local_g;
    this.corners[corner].field_1778 = local_b;
  }
  
  public void clampTexture()
  {
    if (field_204.canTextureMirrorClamp())
    {
      field_204.glTexParameteri(3553, 10242, 34627);
      field_204.glTexParameteri(3553, 10243, 34627);
    }
    else
    {
      field_204.glTexParameteri(3553, 10242, 10496);
      field_204.glTexParameteri(3553, 10243, 10496);
    }
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Graphics getGraphics()
    throws SlickException
  {
    return GraphicsFactory.getGraphicsForImage(this);
  }
  
  private void load(InputStream local_in, String ref, boolean flipped, int local_f, Color transparent)
    throws SlickException
  {
    this.filter = (local_f == 1 ? 9729 : 9728);
    try
    {
      this.ref = ref;
      int[] trans = null;
      if (transparent != null)
      {
        trans = new int[3];
        trans[0] = ((int)(transparent.field_1776 * 255.0F));
        trans[1] = ((int)(transparent.field_1777 * 255.0F));
        trans[2] = ((int)(transparent.field_1778 * 255.0F));
      }
      this.texture = InternalTextureLoader.get().getTexture(local_in, ref, flipped, this.filter, trans);
    }
    catch (IOException trans)
    {
      Log.error(trans);
      throw new SlickException("Failed to load image from: " + ref, trans);
    }
  }
  
  public void bind()
  {
    this.texture.bind();
  }
  
  protected void reinit()
  {
    this.inited = false;
    init();
  }
  
  protected final void init()
  {
    if (this.inited) {
      return;
    }
    this.inited = true;
    if (this.texture != null)
    {
      this.width = this.texture.getImageWidth();
      this.height = this.texture.getImageHeight();
      this.textureOffsetX = 0.0F;
      this.textureOffsetY = 0.0F;
      this.textureWidth = this.texture.getWidth();
      this.textureHeight = this.texture.getHeight();
    }
    initImpl();
    this.centerX = (this.width / 2);
    this.centerY = (this.height / 2);
  }
  
  protected void initImpl() {}
  
  public void draw()
  {
    draw(0.0F, 0.0F);
  }
  
  public void drawCentered(float local_x, float local_y)
  {
    draw(local_x - getWidth() / 2, local_y - getHeight() / 2);
  }
  
  public void draw(float local_x, float local_y)
  {
    init();
    draw(local_x, local_y, this.width, this.height);
  }
  
  public void draw(float local_x, float local_y, Color filter)
  {
    init();
    draw(local_x, local_y, this.width, this.height, filter);
  }
  
  public void drawEmbedded(float local_x, float local_y, float width, float height)
  {
    init();
    if (this.corners == null)
    {
      field_204.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
      field_204.glVertex3f(local_x, local_y, 0.0F);
      field_204.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
      field_204.glVertex3f(local_x, local_y + height, 0.0F);
      field_204.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
      field_204.glVertex3f(local_x + width, local_y + height, 0.0F);
      field_204.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
      field_204.glVertex3f(local_x + width, local_y, 0.0F);
    }
    else
    {
      this.corners[0].bind();
      field_204.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
      field_204.glVertex3f(local_x, local_y, 0.0F);
      this.corners[3].bind();
      field_204.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
      field_204.glVertex3f(local_x, local_y + height, 0.0F);
      this.corners[2].bind();
      field_204.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
      field_204.glVertex3f(local_x + width, local_y + height, 0.0F);
      this.corners[1].bind();
      field_204.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
      field_204.glVertex3f(local_x + width, local_y, 0.0F);
    }
  }
  
  public float getTextureOffsetX()
  {
    init();
    return this.textureOffsetX;
  }
  
  public float getTextureOffsetY()
  {
    init();
    return this.textureOffsetY;
  }
  
  public float getTextureWidth()
  {
    init();
    return this.textureWidth;
  }
  
  public float getTextureHeight()
  {
    init();
    return this.textureHeight;
  }
  
  public void draw(float local_x, float local_y, float scale)
  {
    init();
    draw(local_x, local_y, this.width * scale, this.height * scale, Color.white);
  }
  
  public void draw(float local_x, float local_y, float scale, Color filter)
  {
    init();
    draw(local_x, local_y, this.width * scale, this.height * scale, filter);
  }
  
  public void draw(float local_x, float local_y, float width, float height)
  {
    init();
    draw(local_x, local_y, width, height, Color.white);
  }
  
  public void drawSheared(float local_x, float local_y, float hshear, float vshear)
  {
    drawSheared(local_x, local_y, hshear, vshear, Color.white);
  }
  
  public void drawSheared(float local_x, float local_y, float hshear, float vshear, Color filter)
  {
    if (this.alpha != 1.0F)
    {
      if (filter == null) {
        filter = Color.white;
      }
      filter = new Color(filter);
      filter.field_1779 *= this.alpha;
    }
    if (filter != null) {
      filter.bind();
    }
    this.texture.bind();
    field_204.glTranslatef(local_x, local_y, 0.0F);
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glBegin(7);
    init();
    field_204.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
    field_204.glVertex3f(0.0F, 0.0F, 0.0F);
    field_204.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
    field_204.glVertex3f(hshear, this.height, 0.0F);
    field_204.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
    field_204.glVertex3f(this.width + hshear, this.height + vshear, 0.0F);
    field_204.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
    field_204.glVertex3f(this.width, vshear, 0.0F);
    field_204.glEnd();
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glTranslatef(-local_x, -local_y, 0.0F);
  }
  
  public void draw(float local_x, float local_y, float width, float height, Color filter)
  {
    if (this.alpha != 1.0F)
    {
      if (filter == null) {
        filter = Color.white;
      }
      filter = new Color(filter);
      filter.field_1779 *= this.alpha;
    }
    if (filter != null) {
      filter.bind();
    }
    this.texture.bind();
    field_204.glTranslatef(local_x, local_y, 0.0F);
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glBegin(7);
    drawEmbedded(0.0F, 0.0F, width, height);
    field_204.glEnd();
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glTranslatef(-local_x, -local_y, 0.0F);
  }
  
  public void drawFlash(float local_x, float local_y, float width, float height)
  {
    drawFlash(local_x, local_y, width, height, Color.white);
  }
  
  public void setCenterOfRotation(float local_x, float local_y)
  {
    this.centerX = local_x;
    this.centerY = local_y;
  }
  
  public float getCenterOfRotationX()
  {
    init();
    return this.centerX;
  }
  
  public float getCenterOfRotationY()
  {
    init();
    return this.centerY;
  }
  
  public void drawFlash(float local_x, float local_y, float width, float height, Color col)
  {
    init();
    col.bind();
    this.texture.bind();
    if (field_204.canSecondaryColor())
    {
      field_204.glEnable(33880);
      field_204.glSecondaryColor3ubEXT((byte)(int)(col.field_1776 * 255.0F), (byte)(int)(col.field_1777 * 255.0F), (byte)(int)(col.field_1778 * 255.0F));
    }
    field_204.glTexEnvi(8960, 8704, 8448);
    field_204.glTranslatef(local_x, local_y, 0.0F);
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glBegin(7);
    drawEmbedded(0.0F, 0.0F, width, height);
    field_204.glEnd();
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glTranslatef(-local_x, -local_y, 0.0F);
    if (field_204.canSecondaryColor()) {
      field_204.glDisable(33880);
    }
  }
  
  public void drawFlash(float local_x, float local_y)
  {
    drawFlash(local_x, local_y, getWidth(), getHeight());
  }
  
  public void setRotation(float angle)
  {
    this.angle = (angle % 360.0F);
  }
  
  public float getRotation()
  {
    return this.angle;
  }
  
  public float getAlpha()
  {
    return this.alpha;
  }
  
  public void setAlpha(float alpha)
  {
    this.alpha = alpha;
  }
  
  public void rotate(float angle)
  {
    this.angle += angle;
    this.angle %= 360.0F;
  }
  
  public Image getSubImage(int local_x, int local_y, int width, int height)
  {
    init();
    float newTextureOffsetX = local_x / this.width * this.textureWidth + this.textureOffsetX;
    float newTextureOffsetY = local_y / this.height * this.textureHeight + this.textureOffsetY;
    float newTextureWidth = width / this.width * this.textureWidth;
    float newTextureHeight = height / this.height * this.textureHeight;
    Image sub = new Image();
    sub.inited = true;
    sub.texture = this.texture;
    sub.textureOffsetX = newTextureOffsetX;
    sub.textureOffsetY = newTextureOffsetY;
    sub.textureWidth = newTextureWidth;
    sub.textureHeight = newTextureHeight;
    sub.width = width;
    sub.height = height;
    sub.ref = this.ref;
    sub.centerX = (width / 2);
    sub.centerY = (height / 2);
    return sub;
  }
  
  public void draw(float local_x, float local_y, float srcx, float srcy, float srcx2, float srcy2)
  {
    draw(local_x, local_y, local_x + this.width, local_y + this.height, srcx, srcy, srcx2, srcy2);
  }
  
  public void draw(float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2)
  {
    draw(local_x, local_y, local_x2, local_y2, srcx, srcy, srcx2, srcy2, Color.white);
  }
  
  public void draw(float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2, Color filter)
  {
    init();
    if (this.alpha != 1.0F)
    {
      if (filter == null) {
        filter = Color.white;
      }
      filter = new Color(filter);
      filter.field_1779 *= this.alpha;
    }
    filter.bind();
    this.texture.bind();
    field_204.glTranslatef(local_x, local_y, 0.0F);
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glBegin(7);
    drawEmbedded(0.0F, 0.0F, local_x2 - local_x, local_y2 - local_y, srcx, srcy, srcx2, srcy2);
    field_204.glEnd();
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glTranslatef(-local_x, -local_y, 0.0F);
  }
  
  public void drawEmbedded(float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2)
  {
    drawEmbedded(local_x, local_y, local_x2, local_y2, srcx, srcy, srcx2, srcy2, null);
  }
  
  public void drawEmbedded(float local_x, float local_y, float local_x2, float local_y2, float srcx, float srcy, float srcx2, float srcy2, Color filter)
  {
    if (filter != null) {
      filter.bind();
    }
    float mywidth = local_x2 - local_x;
    float myheight = local_y2 - local_y;
    float texwidth = srcx2 - srcx;
    float texheight = srcy2 - srcy;
    float newTextureOffsetX = srcx / this.width * this.textureWidth + this.textureOffsetX;
    float newTextureOffsetY = srcy / this.height * this.textureHeight + this.textureOffsetY;
    float newTextureWidth = texwidth / this.width * this.textureWidth;
    float newTextureHeight = texheight / this.height * this.textureHeight;
    field_204.glTexCoord2f(newTextureOffsetX, newTextureOffsetY);
    field_204.glVertex3f(local_x, local_y, 0.0F);
    field_204.glTexCoord2f(newTextureOffsetX, newTextureOffsetY + newTextureHeight);
    field_204.glVertex3f(local_x, local_y + myheight, 0.0F);
    field_204.glTexCoord2f(newTextureOffsetX + newTextureWidth, newTextureOffsetY + newTextureHeight);
    field_204.glVertex3f(local_x + mywidth, local_y + myheight, 0.0F);
    field_204.glTexCoord2f(newTextureOffsetX + newTextureWidth, newTextureOffsetY);
    field_204.glVertex3f(local_x + mywidth, local_y, 0.0F);
  }
  
  public void drawWarped(float local_x1, float local_y1, float local_x2, float local_y2, float local_x3, float local_y3, float local_x4, float local_y4)
  {
    Color.white.bind();
    this.texture.bind();
    field_204.glTranslatef(local_x1, local_y1, 0.0F);
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glBegin(7);
    init();
    field_204.glTexCoord2f(this.textureOffsetX, this.textureOffsetY);
    field_204.glVertex3f(0.0F, 0.0F, 0.0F);
    field_204.glTexCoord2f(this.textureOffsetX, this.textureOffsetY + this.textureHeight);
    field_204.glVertex3f(local_x2 - local_x1, local_y2 - local_y1, 0.0F);
    field_204.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY + this.textureHeight);
    field_204.glVertex3f(local_x3 - local_x1, local_y3 - local_y1, 0.0F);
    field_204.glTexCoord2f(this.textureOffsetX + this.textureWidth, this.textureOffsetY);
    field_204.glVertex3f(local_x4 - local_x1, local_y4 - local_y1, 0.0F);
    field_204.glEnd();
    if (this.angle != 0.0F)
    {
      field_204.glTranslatef(this.centerX, this.centerY, 0.0F);
      field_204.glRotatef(-this.angle, 0.0F, 0.0F, 1.0F);
      field_204.glTranslatef(-this.centerX, -this.centerY, 0.0F);
    }
    field_204.glTranslatef(-local_x1, -local_y1, 0.0F);
  }
  
  public int getWidth()
  {
    init();
    return this.width;
  }
  
  public int getHeight()
  {
    init();
    return this.height;
  }
  
  public Image copy()
  {
    init();
    return getSubImage(0, 0, this.width, this.height);
  }
  
  public Image getScaledCopy(float scale)
  {
    init();
    return getScaledCopy((int)(this.width * scale), (int)(this.height * scale));
  }
  
  public Image getScaledCopy(int width, int height)
  {
    init();
    Image image = copy();
    image.width = width;
    image.height = height;
    image.centerX = (width / 2);
    image.centerY = (height / 2);
    return image;
  }
  
  public void ensureInverted()
  {
    if (this.textureHeight > 0.0F)
    {
      this.textureOffsetY += this.textureHeight;
      this.textureHeight = (-this.textureHeight);
    }
  }
  
  public Image getFlippedCopy(boolean flipHorizontal, boolean flipVertical)
  {
    init();
    Image image = copy();
    if (flipHorizontal)
    {
      this.textureOffsetX += this.textureWidth;
      image.textureWidth = (-this.textureWidth);
    }
    if (flipVertical)
    {
      this.textureOffsetY += this.textureHeight;
      image.textureHeight = (-this.textureHeight);
    }
    return image;
  }
  
  public void endUse()
  {
    if (inUse != this) {
      throw new RuntimeException("The sprite sheet is not currently in use");
    }
    inUse = null;
    field_204.glEnd();
  }
  
  public void startUse()
  {
    if (inUse != null) {
      throw new RuntimeException("Attempt to start use of a sprite sheet before ending use with another - see endUse()");
    }
    inUse = this;
    init();
    Color.white.bind();
    this.texture.bind();
    field_204.glBegin(7);
  }
  
  public String toString()
  {
    init();
    return "[Image " + this.ref + " " + this.width + "x" + this.height + "  " + this.textureOffsetX + "," + this.textureOffsetY + "," + this.textureWidth + "," + this.textureHeight + "]";
  }
  
  public Texture getTexture()
  {
    return this.texture;
  }
  
  public void setTexture(Texture texture)
  {
    this.texture = texture;
    reinit();
  }
  
  private int translate(byte local_b)
  {
    if (local_b < 0) {
      return 256 + local_b;
    }
    return local_b;
  }
  
  public Color getColor(int local_x, int local_y)
  {
    if (this.pixelData == null) {
      this.pixelData = this.texture.getTextureData();
    }
    int local_xo = (int)(this.textureOffsetX * this.texture.getTextureWidth());
    int local_yo = (int)(this.textureOffsetY * this.texture.getTextureHeight());
    if (this.textureWidth < 0.0F) {
      local_x = local_xo - local_x;
    } else {
      local_x = local_xo + local_x;
    }
    if (this.textureHeight < 0.0F) {
      local_y = local_yo - local_y;
    } else {
      local_y = local_yo + local_y;
    }
    int offset = local_x + local_y * this.texture.getTextureWidth();
    offset *= (this.texture.hasAlpha() ? 4 : 3);
    if (this.texture.hasAlpha()) {
      return new Color(translate(this.pixelData[offset]), translate(this.pixelData[(offset + 1)]), translate(this.pixelData[(offset + 2)]), translate(this.pixelData[(offset + 3)]));
    }
    return new Color(translate(this.pixelData[offset]), translate(this.pixelData[(offset + 1)]), translate(this.pixelData[(offset + 2)]));
  }
  
  public boolean isDestroyed()
  {
    return this.destroyed;
  }
  
  public void destroy()
    throws SlickException
  {
    if (isDestroyed()) {
      return;
    }
    this.destroyed = true;
    this.texture.release();
    GraphicsFactory.releaseGraphicsForImage(this);
  }
  
  public void flushPixelData()
  {
    this.pixelData = null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.Image
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */