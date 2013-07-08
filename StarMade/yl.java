/*  1:   */import javax.vecmath.Vector4f;
/*  2:   */
/*  5:   */public class yl
/*  6:   */{
/*  7: 7 */  private float b = 1000.0F;
/*  8:   */  protected Vector4f a;
/*  9:   */  
/* 10:10 */  public yl() { this.jdField_a_of_type_Float = 10.0F;
/* 11:   */    
/* 13:13 */    this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 14:   */  }
/* 15:   */  
/* 16:   */  public yl(byte paramByte)
/* 17:   */  {
/* 18:10 */    this.jdField_a_of_type_Float = 10.0F;
/* 19:   */    
/* 24:16 */    this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 25:17 */    this.jdField_a_of_type_Float = 10.0F;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public final float a() {
/* 29:21 */    return Math.max(0.0F, 1.0F - b() / this.b);
/* 30:   */  }
/* 31:   */  
/* 32:   */  private long a;
/* 33:   */  public float a;
/* 34:   */  private float b() {
/* 35:27 */    return (float)(System.currentTimeMillis() - this.jdField_a_of_type_Long);
/* 36:   */  }
/* 37:   */  
/* 38:30 */  public final boolean a() { return b() < this.b; }
/* 39:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */