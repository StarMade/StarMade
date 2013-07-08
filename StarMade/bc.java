/*  1:   */
/* 12:   */public class bc
/* 13:   */  extends H
/* 14:   */{
/* 15:   */  private final gZ jdField_a_of_type_GZ;
/* 16:   */  private final lP jdField_a_of_type_LP;
/* 17:   */  
/* 18:   */  public bc(ct paramct, lP paramlP)
/* 19:   */  {
/* 20:20 */    super(paramct);
/* 21:21 */    this.jdField_a_of_type_GZ = new gZ(this.a, this, paramlP);
/* 22:22 */    this.jdField_a_of_type_LP = paramlP;
/* 23:   */  }
/* 24:   */  
/* 29:   */  public final boolean a()
/* 30:   */  {
/* 31:31 */    return false;
/* 32:   */  }
/* 33:   */  
/* 38:   */  public void handleKeyEvent() {}
/* 39:   */  
/* 44:   */  public final yz a()
/* 45:   */  {
/* 46:46 */    return this.jdField_a_of_type_GZ;
/* 47:   */  }
/* 48:   */  
/* 49:   */  public void a()
/* 50:   */  {
/* 51:51 */    this.a.a().a.a.a.a(500);
/* 52:   */  }
/* 53:   */  
/* 56:   */  public final void a(yz paramyz, xp paramxp)
/* 57:   */  {
/* 58:58 */    if (paramxp.a()) {
/* 59:59 */      paramxp = this.jdField_a_of_type_GZ.a();
/* 60:   */      
/* 61:61 */      for (int i = 0; i < 5; i++) {
/* 62:62 */        if (paramxp[i].f()) {
/* 63:63 */          return;
/* 64:   */        }
/* 65:   */      }
/* 66:   */      
/* 67:67 */      if (paramyz.b().equals("OK"))
/* 68:   */      {
/* 69:   */        mc localmc;
/* 70:70 */        (localmc = new mc()).a = this.jdField_a_of_type_LP.a();
/* 71:71 */        for (paramyz = 0; paramyz < 5; paramyz++) {
/* 72:72 */          if (paramxp[paramyz].e()) {
/* 73:73 */            localmc.a()[paramyz] |= 1L;
/* 74:   */          }
/* 75:75 */          if (paramxp[paramyz].c()) {
/* 76:76 */            localmc.a()[paramyz] |= 4L;
/* 77:   */          }
/* 78:78 */          if (paramxp[paramyz].b()) {
/* 79:79 */            localmc.a()[paramyz] |= 2L;
/* 80:   */          }
/* 81:81 */          if (paramxp[paramyz].d()) {
/* 82:82 */            localmc.a()[paramyz] |= 8L;
/* 83:   */          }
/* 84:84 */          localmc.a()[paramyz] = paramxp[paramyz].a();
/* 85:   */        }
/* 86:   */        
/* 87:87 */        paramyz = null;this.a.a().a(localmc);
/* 88:   */        
/* 89:89 */        d();
/* 90:90 */        return; } if ((paramyz.b().equals("CANCEL")) || (paramyz.b().equals("X"))) {
/* 91:91 */        paramyz = null;d();
/* 92:   */      }
/* 93:   */    }
/* 94:   */  }
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */