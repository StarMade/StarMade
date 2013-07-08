/*  1:   */
/* 13:   */public final class uy
/* 14:   */  extends uu
/* 15:   */{
/* 16:16 */  private q c = new q();
/* 17:17 */  private q d = new q();
/* 18:18 */  private q e = new q();
/* 19:   */  private int b;
/* 20:20 */  private q f = new q();
/* 21:   */  private q g;
/* 22:   */  
/* 23:   */  public uy(uu[] paramArrayOfuu, q paramq1, q paramq2, q paramq3) {
/* 24:24 */    super(paramArrayOfuu, paramq1, paramq2, 5, 0);
/* 25:25 */    this.jdField_b_of_type_Int = 3;
/* 26:26 */    this.g = paramq3;
/* 27:   */  }
/* 28:   */  
/* 29:   */  protected final short a(q paramq)
/* 30:   */  {
/* 31:31 */    a(paramq, this.c);
/* 32:32 */    a(this.jdField_b_of_type_Q, this.d);
/* 33:33 */    a(this.a, this.e);
/* 34:   */    
/* 35:35 */    a(this.e, this.d);
/* 36:   */    
/* 38:38 */    this.f.b(this.g);
/* 39:39 */    q localq = org.schema.game.common.data.element.Element.DIRECTIONSi[this.jdField_b_of_type_Int];
/* 40:40 */    this.f.a(localq);
/* 41:   */    
/* 42:42 */    if (((localq.a > 0) && (paramq.a > this.g.a)) || ((localq.a < 0) && (paramq.a < this.g.a)) || ((localq.jdField_b_of_type_Int > 0) && (paramq.jdField_b_of_type_Int > this.g.jdField_b_of_type_Int)) || ((localq.jdField_b_of_type_Int < 0) && (paramq.jdField_b_of_type_Int < this.g.jdField_b_of_type_Int)) || ((localq.c > 0) && (paramq.c > this.g.c)) || ((localq.c < 0) && (paramq.c < this.g.c)))
/* 43:   */    {
/* 47:47 */      return 32767;
/* 48:   */    }
/* 49:49 */    this.f.b(this.g);
/* 50:50 */    this.f.c(localq);
/* 51:   */    
/* 52:52 */    if ((this.c.a == (this.d.a - this.e.a) / 2) || (this.c.jdField_b_of_type_Int == (this.d.jdField_b_of_type_Int - this.e.jdField_b_of_type_Int) / 2))
/* 53:   */    {
/* 55:55 */      if ((this.c.c > this.d.c - 3) && (this.c.c < this.d.c - 1)) {
/* 56:56 */        return 282;
/* 57:   */      }
/* 58:58 */      if (this.c.c < this.d.c - 1) {
/* 59:59 */        return 286;
/* 60:   */      }
/* 61:   */    }
/* 62:   */    
/* 63:63 */    if ((this.c.c % 2 == 0) && (this.c.c < this.d.c - 4)) {
/* 64:64 */      return 75;
/* 65:   */    }
/* 66:   */    
/* 70:70 */    return 32767;
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */