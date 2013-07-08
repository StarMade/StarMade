/*  1:   */import javax.vecmath.Vector3f;
/*  2:   */
/*  7:   */public final class ut
/*  8:   */  extends uu
/*  9:   */{
/* 10:10 */  private q c = new q();
/* 11:11 */  private q d = new q();
/* 12:12 */  private q e = new q();
/* 13:13 */  private q f = new q();
/* 14:   */  
/* 16:   */  private boolean a;
/* 17:   */  
/* 19:   */  public ut(boolean paramBoolean, uu[] paramArrayOfuu, q paramq1, q paramq2, int paramInt)
/* 20:   */  {
/* 21:21 */    super(paramArrayOfuu, paramq1, paramq2, paramInt, 0);new q();new q();new Vector3f();new Vector3f();
/* 22:22 */    this.jdField_a_of_type_Boolean = paramBoolean;
/* 23:   */  }
/* 24:   */  
/* 25:   */  protected final short a(q paramq)
/* 26:   */  {
/* 27:27 */    a(paramq, this.c);
/* 28:28 */    a(this.b, this.d);
/* 29:29 */    a(this.jdField_a_of_type_Q, this.e);
/* 30:   */    
/* 31:31 */    a(this.e, this.d);
/* 32:32 */    this.f.b((this.d.a - this.e.a) / 2, this.d.b, (this.d.c - this.e.c) / 2);
/* 33:   */    
/* 34:34 */    paramq = this.d.b - this.c.b - 1;
/* 35:   */    
/* 36:36 */    if ((this.f.equals(this.c)) || ((this.c.a >= this.f.a - paramq) && (this.c.a <= this.f.a + paramq) && (this.c.c >= this.f.c - paramq) && (this.c.c <= this.f.c + paramq)))
/* 37:   */    {
/* 38:38 */      if ((paramq > (this.d.b - this.e.b) / 4) && (this.c.b > 1) && 
/* 39:39 */        (this.c.a >= this.f.a - paramq + 2) && (this.c.a <= this.f.a + paramq - 2) && (this.c.c >= this.f.c - paramq + 2) && (this.c.c <= this.f.c + paramq - 2))
/* 40:   */      {
/* 42:42 */        if (((this.d.a - this.e.a) / 4 == this.c.a) && ((this.d.c - this.e.c) / 4 == this.c.c))
/* 43:   */        {
/* 44:44 */          if (this.c.b < (this.d.b - this.e.b) / 8)
/* 45:45 */            return 2;
/* 46:46 */          if (this.c.b == (this.d.b - this.e.b) / 8) {
/* 47:47 */            return 55;
/* 48:   */          }
/* 49:   */        }
/* 50:50 */        if (((this.d.a - this.e.a) / 4 == this.c.a) && ((this.d.c - this.e.c) / 4 * 3 == this.c.c)) {
/* 51:51 */          if (this.c.b < (this.d.b - this.e.b) / 8)
/* 52:52 */            return 2;
/* 53:53 */          if (this.c.b == (this.d.b - this.e.b) / 8) {
/* 54:54 */            return 55;
/* 55:   */          }
/* 56:   */        }
/* 57:57 */        if (((this.d.a - this.e.a) / 4 * 3 == this.c.a) && ((this.d.c - this.e.c) / 4 == this.c.c)) {
/* 58:58 */          if (this.c.b < (this.d.b - this.e.b) / 8)
/* 59:59 */            return 2;
/* 60:60 */          if (this.c.b == (this.d.b - this.e.b) / 8) {
/* 61:61 */            return 55;
/* 62:   */          }
/* 63:   */        }
/* 64:64 */        if (((this.d.a - this.e.a) / 4 * 3 == this.c.a) && ((this.d.c - this.e.c) / 4 * 3 == this.c.c)) {
/* 65:65 */          if (this.c.b < (this.d.b - this.e.b) / 8)
/* 66:66 */            return 2;
/* 67:67 */          if (this.c.b == (this.d.b - this.e.b) / 8) {
/* 68:68 */            return 55;
/* 69:   */          }
/* 70:   */        }
/* 71:   */        
/* 74:74 */        return 0;
/* 75:   */      }
/* 76:   */      
/* 81:81 */      return 79;
/* 82:   */    }
/* 83:   */    
/* 84:84 */    return 32767;
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ut
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */