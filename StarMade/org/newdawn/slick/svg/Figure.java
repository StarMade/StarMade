/*  1:   */package org.newdawn.slick.svg;
/*  2:   */
/*  3:   */import org.newdawn.slick.geom.Shape;
/*  4:   */import org.newdawn.slick.geom.Transform;
/*  5:   */
/* 28:   */public class Figure
/* 29:   */{
/* 30:   */  public static final int ELLIPSE = 1;
/* 31:   */  public static final int LINE = 2;
/* 32:   */  public static final int RECTANGLE = 3;
/* 33:   */  public static final int PATH = 4;
/* 34:   */  public static final int POLYGON = 5;
/* 35:   */  private int type;
/* 36:   */  private Shape shape;
/* 37:   */  private NonGeometricData data;
/* 38:   */  private Transform transform;
/* 39:   */  
/* 40:   */  public Figure(int type, Shape shape, NonGeometricData data, Transform transform)
/* 41:   */  {
/* 42:42 */    this.shape = shape;
/* 43:43 */    this.data = data;
/* 44:44 */    this.type = type;
/* 45:45 */    this.transform = transform;
/* 46:   */  }
/* 47:   */  
/* 53:   */  public Transform getTransform()
/* 54:   */  {
/* 55:55 */    return this.transform;
/* 56:   */  }
/* 57:   */  
/* 62:   */  public int getType()
/* 63:   */  {
/* 64:64 */    return this.type;
/* 65:   */  }
/* 66:   */  
/* 71:   */  public Shape getShape()
/* 72:   */  {
/* 73:73 */    return this.shape;
/* 74:   */  }
/* 75:   */  
/* 80:   */  public NonGeometricData getData()
/* 81:   */  {
/* 82:82 */    return this.data;
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.Figure
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */