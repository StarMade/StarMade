/*  1:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  2:   */import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*  3:   */import org.schema.schine.network.client.ClientState;
/*  4:   */
/* 14:   */public class fs
/* 15:   */  extends yL
/* 16:   */  implements ys
/* 17:   */{
/* 18:   */  private yE jdField_a_of_type_YE;
/* 19:   */  private yE b;
/* 20:   */  private ko jdField_a_of_type_Ko;
/* 21:   */  
/* 22:   */  public fs(ClientState paramClientState, ko paramko)
/* 23:   */  {
/* 24:24 */    super(paramClientState);
/* 25:25 */    this.g = true;
/* 26:26 */    a(this);
/* 27:27 */    this.jdField_a_of_type_Ko = paramko;
/* 28:28 */    if ((!jdField_a_of_type_Boolean) && (!(paramko.a() instanceof Boolean))) throw new AssertionError();
/* 29:29 */    this.jdField_a_of_type_YE = new yE(xe.a().a("tools-16x16-gui-"), a());
/* 30:30 */    this.b = new yE(xe.a().a("tools-16x16-gui-"), a());
/* 31:   */  }
/* 32:   */  
/* 34:   */  public final void a(yz paramyz, xp paramxp)
/* 35:   */  {
/* 36:36 */    paramyz = null; if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0))
/* 37:   */    {
/* 38:   */      try
/* 39:   */      {
/* 40:40 */        this.jdField_a_of_type_Ko.a(); return;
/* 41:   */      } catch (StateParameterNotFoundException localStateParameterNotFoundException) {
/* 42:42 */        (paramyz = 
/* 43:   */        
/* 44:44 */          localStateParameterNotFoundException).printStackTrace();xm.a(paramyz);
/* 45:   */      }
/* 46:   */    }
/* 47:   */  }
/* 48:   */  
/* 52:   */  public final void a() {}
/* 53:   */  
/* 57:   */  protected final void d() {}
/* 58:   */  
/* 61:   */  public final void b()
/* 62:   */  {
/* 63:62 */    GlUtil.d();
/* 64:63 */    r();
/* 65:64 */    l();
/* 66:65 */    this.jdField_a_of_type_YE.b();
/* 67:66 */    if (this.jdField_a_of_type_Ko.a()) {
/* 68:67 */      this.b.b();
/* 69:   */    }
/* 70:69 */    GlUtil.c();
/* 71:   */  }
/* 72:   */  
/* 73:   */  public final float a()
/* 74:   */  {
/* 75:74 */    return this.jdField_a_of_type_YE.a();
/* 76:   */  }
/* 77:   */  
/* 78:   */  public final float b()
/* 79:   */  {
/* 80:79 */    return this.jdField_a_of_type_YE.b();
/* 81:   */  }
/* 82:   */  
/* 84:   */  public final boolean a()
/* 85:   */  {
/* 86:85 */    return false;
/* 87:   */  }
/* 88:   */  
/* 96:   */  public final void c()
/* 97:   */  {
/* 98:97 */    this.jdField_a_of_type_YE.a_(18);
/* 99:98 */    this.b.a_(19);
/* 100:   */  }
/* 101:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fs
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */