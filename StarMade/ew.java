/*  1:   */import java.util.Map;
/*  2:   */
/*  9:   */public final class ew
/* 10:   */  implements xg
/* 11:   */{
/* 12:   */  private ct jdField_a_of_type_Ct;
/* 13:   */  private boolean jdField_a_of_type_Boolean;
/* 14:   */  private yg[] jdField_a_of_type_ArrayOfYg;
/* 15:   */  
/* 16:   */  public ew(ct paramct)
/* 17:   */  {
/* 18:18 */    this.jdField_a_of_type_Ct = paramct;
/* 19:   */  }
/* 20:   */  
/* 24:   */  public final void a() {}
/* 25:   */  
/* 28:   */  public final void b()
/* 29:   */  {
/* 30:30 */    if (!this.jdField_a_of_type_Boolean) {
/* 31:31 */      c();
/* 32:   */    }
/* 33:   */    mv localmv;
/* 34:34 */    if (((localmv = this.jdField_a_of_type_Ct.a()) == null) || (localmv.a().isEmpty()))
/* 35:   */    {
/* 36:36 */      return;
/* 37:   */    }
/* 38:38 */    for (int i = 0; i < this.jdField_a_of_type_ArrayOfYg.length; i++)
/* 39:   */    {
/* 40:   */      yg localyg;
/* 41:41 */      (localyg = this.jdField_a_of_type_ArrayOfYg[i]).b(0.01F, 0.01F, 0.01F);
/* 42:42 */      localyg.c(true);
/* 43:43 */      localyg.a(true);
/* 44:   */      
/* 45:45 */      yg.a(localyg, localmv.a().values(), xe.a());
/* 46:   */      
/* 47:47 */      localyg.a(false);
/* 48:48 */      localyg.c(false);
/* 49:49 */      localyg.b(1.0F, 1.0F, 1.0F);
/* 50:   */    }
/* 51:   */  }
/* 52:   */  
/* 58:   */  public final void c()
/* 59:   */  {
/* 60:60 */    this.jdField_a_of_type_ArrayOfYg = new yg[3];
/* 61:61 */    this.jdField_a_of_type_ArrayOfYg[0] = xe.a().a("build-icons-00-16x16-gui-");
/* 62:62 */    this.jdField_a_of_type_ArrayOfYg[1] = xe.a().a("build-icons-01-16x16-gui-");
/* 63:63 */    this.jdField_a_of_type_ArrayOfYg[2] = xe.a().a("build-icons-extra-gui-");
/* 64:   */    
/* 65:65 */    this.jdField_a_of_type_Boolean = true;
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ew
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */