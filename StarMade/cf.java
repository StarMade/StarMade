/*  1:   */
/*  7:   */public final class cf
/*  8:   */  extends cb
/*  9:   */{
/* 10:   */  private static final long serialVersionUID = 9159389806588076558L;
/* 11:   */  private boolean c;
/* 12:   */  
/* 13:   */  public cf(wk paramwk, ct paramct, String paramString, int paramInt)
/* 14:   */  {
/* 15:15 */    super(paramwk, paramct, paramString, paramInt);
/* 16:   */  }
/* 17:   */  
/* 19:   */  protected final boolean a()
/* 20:   */  {
/* 21:21 */    this.a.a().a();bJ.a();
/* 22:22 */    if ((this.c != this.jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Boolean)) {
/* 23:23 */      this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 24:   */    }
/* 25:25 */    this.c = this.jdField_a_of_type_Boolean;
/* 26:   */    
/* 27:27 */    if (!this.jdField_a_of_type_Boolean) { this.a.a().a();bJ.a();
/* 28:28 */    } else { return (!this.a.a().a().b()) && (System.currentTimeMillis() - this.jdField_a_of_type_Long > this.a);
/* 29:   */    }
/* 30:30 */    return false;
/* 31:   */  }
/* 32:   */  
/* 39:   */  public final String a()
/* 40:   */  {
/* 41:41 */    if (this.jdField_a_of_type_Boolean) { this.a.a().a();bJ.a();
/* 42:42 */      return super.a() + "(Tutorial will resume in " + (this.a - (System.currentTimeMillis() - this.jdField_a_of_type_Long)) / 1000L + " sec\nor press 'k')";
/* 43:   */    }
/* 44:44 */    return super.a();
/* 45:   */  }
/* 46:   */  
/* 52:   */  public final boolean c()
/* 53:   */  {
/* 54:54 */    this.c = false;
/* 55:55 */    return super.c();
/* 56:   */  }
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     cf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */