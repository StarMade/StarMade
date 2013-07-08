package org.newdawn.slick.geom;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.LineStripRenderer;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public final class ShapeRenderer
{
  private static SGL field_484 = ;
  private static LineStripRenderer LSR = Renderer.getLineStripRenderer();
  
  public static final void draw(Shape shape)
  {
    Texture local_t = TextureImpl.getLastBind();
    TextureImpl.bindNone();
    float[] points = shape.getPoints();
    LSR.start();
    for (int local_i = 0; local_i < points.length; local_i += 2) {
      LSR.vertex(points[local_i], points[(local_i + 1)]);
    }
    if (shape.closed()) {
      LSR.vertex(points[0], points[1]);
    }
    LSR.end();
    if (local_t == null) {
      TextureImpl.bindNone();
    } else {
      local_t.bind();
    }
  }
  
  public static final void draw(Shape shape, ShapeFill fill)
  {
    float[] points = shape.getPoints();
    Texture local_t = TextureImpl.getLastBind();
    TextureImpl.bindNone();
    float[] center = shape.getCenter();
    field_484.glBegin(3);
    for (int local_i = 0; local_i < points.length; local_i += 2)
    {
      fill.colorAt(shape, points[local_i], points[(local_i + 1)]).bind();
      Vector2f offset = fill.getOffsetAt(shape, points[local_i], points[(local_i + 1)]);
      field_484.glVertex2f(points[local_i] + offset.field_1680, points[(local_i + 1)] + offset.field_1681);
    }
    if (shape.closed())
    {
      fill.colorAt(shape, points[0], points[1]).bind();
      Vector2f local_i = fill.getOffsetAt(shape, points[0], points[1]);
      field_484.glVertex2f(points[0] + local_i.field_1680, points[1] + local_i.field_1681);
    }
    field_484.glEnd();
    if (local_t == null) {
      TextureImpl.bindNone();
    } else {
      local_t.bind();
    }
  }
  
  public static boolean validFill(Shape shape)
  {
    if (shape.getTriangles() == null) {
      return false;
    }
    return shape.getTriangles().getTriangleCount() != 0;
  }
  
  public static final void fill(Shape shape)
  {
    if (!validFill(shape)) {
      return;
    }
    Texture local_t = TextureImpl.getLastBind();
    TextureImpl.bindNone();
    fill(shape, new PointCallback()
    {
      public float[] preRenderPoint(Shape shape, float local_x, float local_y)
      {
        return null;
      }
    });
    if (local_t == null) {
      TextureImpl.bindNone();
    } else {
      local_t.bind();
    }
  }
  
  private static final void fill(Shape shape, PointCallback callback)
  {
    Triangulator tris = shape.getTriangles();
    field_484.glBegin(4);
    for (int local_i = 0; local_i < tris.getTriangleCount(); local_i++) {
      for (int local_p = 0; local_p < 3; local_p++)
      {
        float[] local_pt = tris.getTrianglePoint(local_i, local_p);
        float[] local_np = callback.preRenderPoint(shape, local_pt[0], local_pt[1]);
        if (local_np == null) {
          field_484.glVertex2f(local_pt[0], local_pt[1]);
        } else {
          field_484.glVertex2f(local_np[0], local_np[1]);
        }
      }
    }
    field_484.glEnd();
  }
  
  public static final void texture(Shape shape, Image image)
  {
    texture(shape, image, 0.01F, 0.01F);
  }
  
  public static final void textureFit(Shape shape, Image image)
  {
    textureFit(shape, image, 1.0F, 1.0F);
  }
  
  public static final void texture(Shape shape, final Image image, float scaleX, final float scaleY)
  {
    if (!validFill(shape)) {
      return;
    }
    Texture local_t = TextureImpl.getLastBind();
    image.getTexture().bind();
    fill(shape, new PointCallback()
    {
      public float[] preRenderPoint(Shape shape, float local_x, float local_y)
      {
        float local_tx = local_x * this.val$scaleX;
        float local_ty = local_y * scaleY;
        local_tx = image.getTextureOffsetX() + image.getTextureWidth() * local_tx;
        local_ty = image.getTextureOffsetY() + image.getTextureHeight() * local_ty;
        ShapeRenderer.field_484.glTexCoord2f(local_tx, local_ty);
        return null;
      }
    });
    float[] points = shape.getPoints();
    if (local_t == null) {
      TextureImpl.bindNone();
    } else {
      local_t.bind();
    }
  }
  
  public static final void textureFit(Shape shape, final Image image, float scaleX, final float scaleY)
  {
    if (!validFill(shape)) {
      return;
    }
    float[] points = shape.getPoints();
    Texture local_t = TextureImpl.getLastBind();
    image.getTexture().bind();
    float minX = shape.getX();
    float minY = shape.getY();
    float maxX = shape.getMaxX() - minX;
    float maxY = shape.getMaxY() - minY;
    fill(shape, new PointCallback()
    {
      public float[] preRenderPoint(Shape shape, float local_x, float local_y)
      {
        local_x -= shape.getMinX();
        local_y -= shape.getMinY();
        local_x /= (shape.getMaxX() - shape.getMinX());
        local_y /= (shape.getMaxY() - shape.getMinY());
        float local_tx = local_x * this.val$scaleX;
        float local_ty = local_y * scaleY;
        local_tx = image.getTextureOffsetX() + image.getTextureWidth() * local_tx;
        local_ty = image.getTextureOffsetY() + image.getTextureHeight() * local_ty;
        ShapeRenderer.field_484.glTexCoord2f(local_tx, local_ty);
        return null;
      }
    });
    if (local_t == null) {
      TextureImpl.bindNone();
    } else {
      local_t.bind();
    }
  }
  
  public static final void fill(Shape shape, ShapeFill fill)
  {
    if (!validFill(shape)) {
      return;
    }
    Texture local_t = TextureImpl.getLastBind();
    TextureImpl.bindNone();
    float[] center = shape.getCenter();
    fill(shape, new PointCallback()
    {
      public float[] preRenderPoint(Shape shape, float local_x, float local_y)
      {
        this.val$fill.colorAt(shape, local_x, local_y).bind();
        Vector2f offset = this.val$fill.getOffsetAt(shape, local_x, local_y);
        return new float[] { offset.field_1680 + local_x, offset.field_1681 + local_y };
      }
    });
    if (local_t == null) {
      TextureImpl.bindNone();
    } else {
      local_t.bind();
    }
  }
  
  public static final void texture(Shape shape, final Image image, final float scaleX, final float scaleY, ShapeFill fill)
  {
    if (!validFill(shape)) {
      return;
    }
    Texture local_t = TextureImpl.getLastBind();
    image.getTexture().bind();
    final float[] center = shape.getCenter();
    fill(shape, new PointCallback()
    {
      public float[] preRenderPoint(Shape shape, float local_x, float local_y)
      {
        this.val$fill.colorAt(shape, local_x - center[0], local_y - center[1]).bind();
        Vector2f offset = this.val$fill.getOffsetAt(shape, local_x, local_y);
        local_x += offset.field_1680;
        local_y += offset.field_1681;
        float local_tx = local_x * scaleX;
        float local_ty = local_y * scaleY;
        local_tx = image.getTextureOffsetX() + image.getTextureWidth() * local_tx;
        local_ty = image.getTextureOffsetY() + image.getTextureHeight() * local_ty;
        ShapeRenderer.field_484.glTexCoord2f(local_tx, local_ty);
        return new float[] { offset.field_1680 + local_x, offset.field_1681 + local_y };
      }
    });
    if (local_t == null) {
      TextureImpl.bindNone();
    } else {
      local_t.bind();
    }
  }
  
  public static final void texture(Shape shape, Image image, TexCoordGenerator gen)
  {
    Texture local_t = TextureImpl.getLastBind();
    image.getTexture().bind();
    float[] center = shape.getCenter();
    fill(shape, new PointCallback()
    {
      public float[] preRenderPoint(Shape shape, float local_x, float local_y)
      {
        Vector2f tex = this.val$gen.getCoordFor(local_x, local_y);
        ShapeRenderer.field_484.glTexCoord2f(tex.field_1680, tex.field_1681);
        return new float[] { local_x, local_y };
      }
    });
    if (local_t == null) {
      TextureImpl.bindNone();
    } else {
      local_t.bind();
    }
  }
  
  private static abstract interface PointCallback
  {
    public abstract float[] preRenderPoint(Shape paramShape, float paramFloat1, float paramFloat2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.ShapeRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */