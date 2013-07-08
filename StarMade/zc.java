/*  1:   */import javax.vecmath.Vector3f;
/*  2:   */
/* 11:   */public class zc
/* 12:   */  extends yW
/* 13:   */{
/* 14:14 */  private Vector3f a = new Vector3f();
/* 15:   */  
/* 16:16 */  private Vector3f b = new Vector3f();
/* 17:   */  
/* 22:   */  public zc()
/* 23:   */  {
/* 24:24 */    super(16);
/* 25:   */  }
/* 26:   */  
/* 41:   */  public boolean a(int paramInt, xq paramxq)
/* 42:   */  {
/* 43:43 */    paramxq = this.a.a(paramInt);
/* 44:44 */    this.a.a(paramInt, this.a);
/* 45:45 */    this.a.d(paramInt, this.b);
/* 46:   */    
/* 47:47 */    this.a.a(paramInt, this.a.x + this.b.x, this.a.y + this.b.y, this.a.z + this.b.z);
/* 48:48 */    return paramxq < 1300.0F;
/* 49:   */  }
/* 50:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */