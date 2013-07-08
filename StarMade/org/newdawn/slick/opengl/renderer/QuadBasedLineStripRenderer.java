package org.newdawn.slick.opengl.renderer;

public class QuadBasedLineStripRenderer
  implements LineStripRenderer
{
  private SGL field_364 = Renderer.get();
  public static int MAX_POINTS = 10000;
  private boolean antialias;
  private float width = 1.0F;
  private float[] points = new float[MAX_POINTS * 2];
  private float[] colours = new float[MAX_POINTS * 4];
  private int pts;
  private int cpt;
  private DefaultLineStripRenderer def = new DefaultLineStripRenderer();
  private boolean renderHalf;
  private boolean lineCaps = false;
  
  public void setLineCaps(boolean caps)
  {
    this.lineCaps = caps;
  }
  
  public void start()
  {
    if (this.width == 1.0F)
    {
      this.def.start();
      return;
    }
    this.pts = 0;
    this.cpt = 0;
    this.field_364.flush();
    float[] col = this.field_364.getCurrentColor();
    color(col[0], col[1], col[2], col[3]);
  }
  
  public void end()
  {
    if (this.width == 1.0F)
    {
      this.def.end();
      return;
    }
    renderLines(this.points, this.pts);
  }
  
  public void vertex(float local_x, float local_y)
  {
    if (this.width == 1.0F)
    {
      this.def.vertex(local_x, local_y);
      return;
    }
    this.points[(this.pts * 2)] = local_x;
    this.points[(this.pts * 2 + 1)] = local_y;
    this.pts += 1;
    int index = this.pts - 1;
    color(this.colours[(index * 4)], this.colours[(index * 4 + 1)], this.colours[(index * 4 + 2)], this.colours[(index * 4 + 3)]);
  }
  
  public void setWidth(float width)
  {
    this.width = width;
  }
  
  public void setAntiAlias(boolean antialias)
  {
    this.def.setAntiAlias(antialias);
    this.antialias = antialias;
  }
  
  public void renderLines(float[] points, int count)
  {
    if (this.antialias)
    {
      this.field_364.glEnable(2881);
      renderLinesImpl(points, count, this.width + 1.0F);
    }
    this.field_364.glDisable(2881);
    renderLinesImpl(points, count, this.width);
    if (this.antialias) {
      this.field_364.glEnable(2881);
    }
  }
  
  public void renderLinesImpl(float[] points, int count, float local_w)
  {
    float width = local_w / 2.0F;
    float lastx1 = 0.0F;
    float lasty1 = 0.0F;
    float lastx2 = 0.0F;
    float lasty2 = 0.0F;
    this.field_364.glBegin(7);
    for (int local_i = 0; local_i < count + 1; local_i++)
    {
      int current = local_i;
      int next = local_i + 1;
      int prev = local_i - 1;
      if (prev < 0) {
        prev += count;
      }
      if (next >= count) {
        next -= count;
      }
      if (current >= count) {
        current -= count;
      }
      float local_x1 = points[(current * 2)];
      float local_y1 = points[(current * 2 + 1)];
      float local_x2 = points[(next * 2)];
      float local_y2 = points[(next * 2 + 1)];
      float local_dx = local_x2 - local_x1;
      float local_dy = local_y2 - local_y1;
      if ((local_dx != 0.0F) || (local_dy != 0.0F))
      {
        float local_d2 = local_dx * local_dx + local_dy * local_dy;
        float local_d = (float)Math.sqrt(local_d2);
        local_dx *= width;
        local_dy *= width;
        local_dx /= local_d;
        local_dy /= local_d;
        float local_tx = local_dy;
        float local_ty = -local_dx;
        if (local_i != 0)
        {
          bindColor(prev);
          this.field_364.glVertex3f(lastx1, lasty1, 0.0F);
          this.field_364.glVertex3f(lastx2, lasty2, 0.0F);
          bindColor(current);
          this.field_364.glVertex3f(local_x1 + local_tx, local_y1 + local_ty, 0.0F);
          this.field_364.glVertex3f(local_x1 - local_tx, local_y1 - local_ty, 0.0F);
        }
        lastx1 = local_x2 - local_tx;
        lasty1 = local_y2 - local_ty;
        lastx2 = local_x2 + local_tx;
        lasty2 = local_y2 + local_ty;
        if (local_i < count - 1)
        {
          bindColor(current);
          this.field_364.glVertex3f(local_x1 + local_tx, local_y1 + local_ty, 0.0F);
          this.field_364.glVertex3f(local_x1 - local_tx, local_y1 - local_ty, 0.0F);
          bindColor(next);
          this.field_364.glVertex3f(local_x2 - local_tx, local_y2 - local_ty, 0.0F);
          this.field_364.glVertex3f(local_x2 + local_tx, local_y2 + local_ty, 0.0F);
        }
      }
    }
    this.field_364.glEnd();
    float local_i = width <= 12.5F ? 5.0F : 180.0F / (float)Math.ceil(width / 2.5D);
    if (this.lineCaps)
    {
      float current = points[2] - points[0];
      float next = points[3] - points[1];
      float prev = (float)Math.toDegrees(Math.atan2(next, current)) + 90.0F;
      if ((current != 0.0F) || (next != 0.0F))
      {
        this.field_364.glBegin(6);
        bindColor(0);
        this.field_364.glVertex2f(points[0], points[1]);
        for (int local_x1 = 0; local_x1 < 180.0F + local_i; local_x1 = (int)(local_x1 + local_i))
        {
          float local_y1 = (float)Math.toRadians(prev + local_x1);
          this.field_364.glVertex2f(points[0] + (float)(Math.cos(local_y1) * width), points[1] + (float)(Math.sin(local_y1) * width));
        }
        this.field_364.glEnd();
      }
    }
    if (this.lineCaps)
    {
      float current = points[(count * 2 - 2)] - points[(count * 2 - 4)];
      float next = points[(count * 2 - 1)] - points[(count * 2 - 3)];
      float prev = (float)Math.toDegrees(Math.atan2(next, current)) - 90.0F;
      if ((current != 0.0F) || (next != 0.0F))
      {
        this.field_364.glBegin(6);
        bindColor(count - 1);
        this.field_364.glVertex2f(points[(count * 2 - 2)], points[(count * 2 - 1)]);
        for (int local_x1 = 0; local_x1 < 180.0F + local_i; local_x1 = (int)(local_x1 + local_i))
        {
          float local_y1 = (float)Math.toRadians(prev + local_x1);
          this.field_364.glVertex2f(points[(count * 2 - 2)] + (float)(Math.cos(local_y1) * width), points[(count * 2 - 1)] + (float)(Math.sin(local_y1) * width));
        }
        this.field_364.glEnd();
      }
    }
  }
  
  private void bindColor(int index)
  {
    if (index < this.cpt) {
      if (this.renderHalf) {
        this.field_364.glColor4f(this.colours[(index * 4)] * 0.5F, this.colours[(index * 4 + 1)] * 0.5F, this.colours[(index * 4 + 2)] * 0.5F, this.colours[(index * 4 + 3)] * 0.5F);
      } else {
        this.field_364.glColor4f(this.colours[(index * 4)], this.colours[(index * 4 + 1)], this.colours[(index * 4 + 2)], this.colours[(index * 4 + 3)]);
      }
    }
  }
  
  public void color(float local_r, float local_g, float local_b, float local_a)
  {
    if (this.width == 1.0F)
    {
      this.def.color(local_r, local_g, local_b, local_a);
      return;
    }
    this.colours[(this.pts * 4)] = local_r;
    this.colours[(this.pts * 4 + 1)] = local_g;
    this.colours[(this.pts * 4 + 2)] = local_b;
    this.colours[(this.pts * 4 + 3)] = local_a;
    this.cpt += 1;
  }
  
  public boolean applyGLLineFixes()
  {
    if (this.width == 1.0F) {
      return this.def.applyGLLineFixes();
    }
    return this.def.applyGLLineFixes();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.renderer.QuadBasedLineStripRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */