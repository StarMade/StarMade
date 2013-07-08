/*  1:   */package org.newdawn.slick.geom;
/*  2:   */
/* 16:   */public class Point
/* 17:   */  extends Shape
/* 18:   */{
/* 19:   */  public Point(float x, float y)
/* 20:   */  {
/* 21:21 */    this.x = x;
/* 22:22 */    this.y = y;
/* 23:23 */    checkPoints();
/* 24:   */  }
/* 25:   */  
/* 29:   */  public Shape transform(Transform transform)
/* 30:   */  {
/* 31:31 */    float[] result = new float[this.points.length];
/* 32:32 */    transform.transform(this.points, 0, result, 0, this.points.length / 2);
/* 33:   */    
/* 34:34 */    return new Point(this.points[0], this.points[1]);
/* 35:   */  }
/* 36:   */  
/* 40:   */  protected void createPoints()
/* 41:   */  {
/* 42:42 */    this.points = new float[2];
/* 43:43 */    this.points[0] = getX();
/* 44:44 */    this.points[1] = getY();
/* 45:   */    
/* 46:46 */    this.maxX = this.x;
/* 47:47 */    this.maxY = this.y;
/* 48:48 */    this.minX = this.x;
/* 49:49 */    this.minY = this.y;
/* 50:   */    
/* 51:51 */    findCenter();
/* 52:52 */    calculateRadius();
/* 53:   */  }
/* 54:   */  
/* 58:   */  protected void findCenter()
/* 59:   */  {
/* 60:60 */    this.center = new float[2];
/* 61:61 */    this.center[0] = this.points[0];
/* 62:62 */    this.center[1] = this.points[1];
/* 63:   */  }
/* 64:   */  
/* 68:   */  protected void calculateRadius()
/* 69:   */  {
/* 70:70 */    this.boundingCircleRadius = 0.0F;
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Point
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */