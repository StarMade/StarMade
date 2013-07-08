/*  1:   */import java.util.ArrayList;
/*  2:   */
/* 31:   */final class fh
/* 32:   */  implements ys
/* 33:   */{
/* 34:   */  private fh(fg paramfg) {}
/* 35:   */  
/* 36:   */  public final void a(yz paramyz, xp paramxp)
/* 37:   */  {
/* 38:38 */    if ((paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0)) {
/* 39:39 */      if (paramyz.b().equals("INVENTORY")) {
/* 40:40 */        if (!fg.a(this.jdField_a_of_type_Fg)) {
/* 41:41 */          fg.a(this.jdField_a_of_type_Fg);
/* 42:   */        }
/* 43:43 */      } else if (paramyz.b().equals("WEAPON")) {
/* 44:44 */        if (!fg.b(this.jdField_a_of_type_Fg)) {
/* 45:45 */          fg.b(this.jdField_a_of_type_Fg);
/* 46:   */        }
/* 47:47 */      } else if (paramyz.b().equals("FACTION")) {
/* 48:48 */        if (!fg.c(this.jdField_a_of_type_Fg)) {
/* 49:49 */          fg.c(this.jdField_a_of_type_Fg);
/* 50:   */        }
/* 51:51 */      } else if (paramyz.b().equals("CATALOG")) {
/* 52:52 */        if (!fg.d(this.jdField_a_of_type_Fg)) {
/* 53:53 */          fg.d(this.jdField_a_of_type_Fg);
/* 54:   */        }
/* 55:55 */      } else if (paramyz.b().equals("AI")) {
/* 56:56 */        if (!fg.e(this.jdField_a_of_type_Fg)) {
/* 57:57 */          fg.e(this.jdField_a_of_type_Fg);
/* 58:   */        }
/* 59:59 */      } else if (paramyz.b().equals("SHOP")) {
/* 60:60 */        if (!fg.f(this.jdField_a_of_type_Fg)) {
/* 61:61 */          fg.f(this.jdField_a_of_type_Fg);
/* 62:   */        }
/* 63:63 */      } else if (paramyz.b().equals("NAVIGATION")) {
/* 64:64 */        if (!fg.g(this.jdField_a_of_type_Fg))
/* 65:65 */          fg.g(this.jdField_a_of_type_Fg);
/* 66:   */      } else {
/* 67:67 */        if (paramyz.b().equals("X")) {
/* 68:68 */          fg.h(this.jdField_a_of_type_Fg);return;
/* 69:   */        }
/* 70:70 */        if (!jdField_a_of_type_Boolean) { throw new AssertionError("not known command: " + paramyz.b());
/* 71:   */        }
/* 72:   */      }
/* 73:   */    }
/* 74:   */  }
/* 75:   */  
/* 80:   */  public final boolean a()
/* 81:   */  {
/* 82:82 */    return !this.jdField_a_of_type_Fg.a().b().isEmpty();
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */