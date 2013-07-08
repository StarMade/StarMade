/*  1:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  2:   */import org.schema.schine.network.client.ClientState;
/*  3:   */
/* 10:   */public final class il
/* 11:   */  extends yz
/* 12:   */{
/* 13:   */  private zC jdField_a_of_type_ZC;
/* 14:   */  private yE jdField_a_of_type_YE;
/* 15:   */  private yQ jdField_a_of_type_YQ;
/* 16:   */  private int jdField_a_of_type_Int;
/* 17:   */  private int b;
/* 18:   */  private im jdField_a_of_type_Im;
/* 19:   */  private ih jdField_a_of_type_Ih;
/* 20:   */  
/* 21:   */  public il(ClientState paramClientState)
/* 22:   */  {
/* 23:23 */    super(paramClientState);
/* 24:24 */    this.b = 113;
/* 25:25 */    this.jdField_a_of_type_Int = 113;
/* 26:   */  }
/* 27:   */  
/* 31:   */  public final void a() {}
/* 32:   */  
/* 36:   */  public final void b()
/* 37:   */  {
/* 38:38 */    GlUtil.d();
/* 39:39 */    r();
/* 40:   */    
/* 41:41 */    this.jdField_a_of_type_YE.b();
/* 42:   */    
/* 43:43 */    GlUtil.c();
/* 44:   */  }
/* 45:   */  
/* 46:   */  public final void c()
/* 47:   */  {
/* 48:48 */    this.jdField_a_of_type_ZC = xe.a().a("radarBackground").a().a();
/* 49:49 */    this.jdField_a_of_type_YQ = new yQ(a(), this.b, this.jdField_a_of_type_Int, this.jdField_a_of_type_ZC);
/* 50:50 */    this.jdField_a_of_type_YQ.c();
/* 51:   */    
/* 52:52 */    this.jdField_a_of_type_YE = new yE(xe.a().a("radarGUIBackground"), a());
/* 53:   */    
/* 54:54 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YQ);
/* 55:55 */    this.jdField_a_of_type_YQ.a(8.0F, 8.0F, 0.0F);
/* 56:   */    
/* 57:57 */    this.jdField_a_of_type_Im = new im(a(), this.b);
/* 58:58 */    this.jdField_a_of_type_Im.c();
/* 59:59 */    this.jdField_a_of_type_YQ.a(this.jdField_a_of_type_Im);
/* 60:   */    
/* 61:61 */    this.jdField_a_of_type_Ih = new ih(a());
/* 62:62 */    this.jdField_a_of_type_Ih.c();
/* 63:63 */    this.jdField_a_of_type_YE.a(this.jdField_a_of_type_Ih);
/* 64:   */  }
/* 65:   */  
/* 67:   */  public final float a()
/* 68:   */  {
/* 69:69 */    return this.jdField_a_of_type_YE.a();
/* 70:   */  }
/* 71:   */  
/* 72:   */  public final float b()
/* 73:   */  {
/* 74:74 */    return this.jdField_a_of_type_YE.b();
/* 75:   */  }
/* 76:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     il
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */