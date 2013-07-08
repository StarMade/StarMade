/*  1:   */
/* 18:   */final class bx
/* 19:   */  implements ww
/* 20:   */{
/* 21:   */  bx(bw parambw, short paramShort) {}
/* 22:   */  
/* 23:   */  public final boolean a(String paramString, wB paramwB)
/* 24:   */  {
/* 25:   */    try
/* 26:   */    {
/* 27:27 */      if (paramString.length() > 0) {
/* 28:28 */        paramString = Integer.parseInt(paramString);
/* 29:29 */        String str = this.jdField_a_of_type_Bw.a().a().getInventory(null).b(this.jdField_a_of_type_Short);
/* 30:   */        
/* 32:   */        kf localkf;
/* 33:   */        
/* 34:34 */        if ((localkf = this.jdField_a_of_type_Bw.a().a()) == null) {
/* 35:35 */          this.jdField_a_of_type_Bw.a().a().b("ERROR: no shop available");
/* 36:36 */          return false;
/* 37:   */        }
/* 38:38 */        if (paramString <= 0) {
/* 39:39 */          this.jdField_a_of_type_Bw.a().a().b("ERROR: Invalid quantity");
/* 40:40 */          return false;
/* 41:   */        }
/* 42:   */        
/* 43:   */        int i;
/* 44:44 */        if ((i = localkf.a().a(this.jdField_a_of_type_Short)) >= 0) { localkf.a(); if (localkf.a().a(i) + paramString > mn.e()) {
/* 45:45 */            this.jdField_a_of_type_Bw.a().a().b("ERROR: Shop cannot stock any\nmore of that item!");
/* 46:46 */            localkf.a();this.jdField_a_of_type_Bw.a().a(String.valueOf(mn.e() - localkf.a().a(i)));
/* 47:47 */            this.jdField_a_of_type_Bw.a().e();
/* 48:48 */            return false;
/* 49:   */          } }
/* 50:50 */        if (paramString <= str) {
/* 51:51 */          xe.b("0022_action - receive credits");
/* 52:52 */          return true;
/* 53:   */        }
/* 54:54 */        this.jdField_a_of_type_Bw.a().a().b("ERROR\nYou can't sell that many!\nYou can only sell " + str + "!");
/* 55:   */        
/* 59:59 */        this.jdField_a_of_type_Bw.a().a(String.valueOf(str));
/* 60:60 */        this.jdField_a_of_type_Bw.a().e();
/* 61:61 */        this.jdField_a_of_type_Bw.a().g();
/* 62:   */        
/* 63:63 */        return false;
/* 64:   */      }
/* 65:   */    }
/* 66:   */    catch (NumberFormatException localNumberFormatException) {}
/* 67:   */    
/* 68:68 */    paramwB.onFailedTextCheck("Please only enter numbers!");
/* 69:69 */    return false;
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */