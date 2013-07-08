/*  1:   */package org.newdawn.slick.svg;
/*  2:   */
/*  3:   */import org.newdawn.slick.geom.Line;
/*  4:   */import org.newdawn.slick.geom.Shape;
/*  5:   */import org.newdawn.slick.geom.TexCoordGenerator;
/*  6:   */import org.newdawn.slick.geom.Transform;
/*  7:   */import org.newdawn.slick.geom.Vector2f;
/*  8:   */
/* 23:   */public class LinearGradientFill
/* 24:   */  implements TexCoordGenerator
/* 25:   */{
/* 26:   */  private Vector2f start;
/* 27:   */  private Vector2f end;
/* 28:   */  private Gradient gradient;
/* 29:   */  private Line line;
/* 30:   */  private Shape shape;
/* 31:   */  
/* 32:   */  public LinearGradientFill(Shape shape, Transform trans, Gradient gradient)
/* 33:   */  {
/* 34:34 */    this.gradient = gradient;
/* 35:   */    
/* 36:36 */    float x = gradient.getX1();
/* 37:37 */    float y = gradient.getY1();
/* 38:38 */    float mx = gradient.getX2();
/* 39:39 */    float my = gradient.getY2();
/* 40:   */    
/* 41:41 */    float h = my - y;
/* 42:42 */    float w = mx - x;
/* 43:   */    
/* 44:44 */    float[] s = { x, y + h / 2.0F };
/* 45:45 */    gradient.getTransform().transform(s, 0, s, 0, 1);
/* 46:46 */    trans.transform(s, 0, s, 0, 1);
/* 47:47 */    float[] e = { x + w, y + h / 2.0F };
/* 48:48 */    gradient.getTransform().transform(e, 0, e, 0, 1);
/* 49:49 */    trans.transform(e, 0, e, 0, 1);
/* 50:   */    
/* 51:51 */    this.start = new Vector2f(s[0], s[1]);
/* 52:52 */    this.end = new Vector2f(e[0], e[1]);
/* 53:   */    
/* 54:54 */    this.line = new Line(this.start, this.end);
/* 55:   */  }
/* 56:   */  
/* 59:   */  public Vector2f getCoordFor(float x, float y)
/* 60:   */  {
/* 61:61 */    Vector2f result = new Vector2f();
/* 62:62 */    this.line.getClosestPoint(new Vector2f(x, y), result);
/* 63:63 */    float u = result.distance(this.start);
/* 64:64 */    u /= this.line.length();
/* 65:   */    
/* 66:66 */    return new Vector2f(u, 0.0F);
/* 67:   */  }
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.LinearGradientFill
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */