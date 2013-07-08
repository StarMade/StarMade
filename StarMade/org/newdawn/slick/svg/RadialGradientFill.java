/*  1:   */package org.newdawn.slick.svg;
/*  2:   */
/*  3:   */import org.newdawn.slick.geom.Shape;
/*  4:   */import org.newdawn.slick.geom.TexCoordGenerator;
/*  5:   */import org.newdawn.slick.geom.Transform;
/*  6:   */import org.newdawn.slick.geom.Vector2f;
/*  7:   */
/* 21:   */public class RadialGradientFill
/* 22:   */  implements TexCoordGenerator
/* 23:   */{
/* 24:   */  private Vector2f centre;
/* 25:   */  private float radius;
/* 26:   */  private Gradient gradient;
/* 27:   */  private Shape shape;
/* 28:   */  
/* 29:   */  public RadialGradientFill(Shape shape, Transform trans, Gradient gradient)
/* 30:   */  {
/* 31:31 */    this.gradient = gradient;
/* 32:   */    
/* 33:33 */    this.radius = gradient.getR();
/* 34:34 */    float x = gradient.getX1();
/* 35:35 */    float y = gradient.getY1();
/* 36:   */    
/* 37:37 */    float[] c = { x, y };
/* 38:38 */    gradient.getTransform().transform(c, 0, c, 0, 1);
/* 39:39 */    trans.transform(c, 0, c, 0, 1);
/* 40:40 */    float[] rt = { x, y - this.radius };
/* 41:41 */    gradient.getTransform().transform(rt, 0, rt, 0, 1);
/* 42:42 */    trans.transform(rt, 0, rt, 0, 1);
/* 43:   */    
/* 44:44 */    this.centre = new Vector2f(c[0], c[1]);
/* 45:45 */    Vector2f dis = new Vector2f(rt[0], rt[1]);
/* 46:46 */    this.radius = dis.distance(this.centre);
/* 47:   */  }
/* 48:   */  
/* 51:   */  public Vector2f getCoordFor(float x, float y)
/* 52:   */  {
/* 53:53 */    float u = this.centre.distance(new Vector2f(x, y));
/* 54:54 */    u /= this.radius;
/* 55:   */    
/* 56:56 */    if (u > 0.99F) {
/* 57:57 */      u = 0.99F;
/* 58:   */    }
/* 59:   */    
/* 60:60 */    return new Vector2f(u, 0.0F);
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.RadialGradientFill
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */