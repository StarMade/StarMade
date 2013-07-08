/*  1:   */import org.schema.game.common.data.element.ElementInformation;
/*  2:   */import org.schema.game.common.data.element.ElementKeyMap;
/*  3:   */
/* 17:   */final class bp
/* 18:   */  implements ww
/* 19:   */{
/* 20:   */  bp(bo parambo, short paramShort) {}
/* 21:   */  
/* 22:   */  public final boolean a(String paramString, wB paramwB)
/* 23:   */  {
/* 24:   */    try
/* 25:   */    {
/* 26:26 */      if (paramString.length() > 0) {
/* 27:27 */        ElementInformation localElementInformation = ElementKeyMap.getInfo(this.jdField_a_of_type_Short);
/* 28:   */        
/* 30:30 */        if ((paramString = Integer.parseInt(paramString)) <= 0) {
/* 31:31 */          this.jdField_a_of_type_Bo.a().a().b("ERROR: Invalid quantity");
/* 32:32 */          return false;
/* 33:   */        }
/* 34:   */        kf localkf;
/* 35:35 */        if ((localkf = this.jdField_a_of_type_Bo.a().a()) == null) {
/* 36:36 */          this.jdField_a_of_type_Bo.a().a().b("ERROR: no shop available");
/* 37:37 */          return false;
/* 38:   */        }
/* 39:   */        
/* 44:44 */        if (((i = localkf.a().a(this.jdField_a_of_type_Short)) < 0) || (localkf.a().a(i) < paramString)) {
/* 45:45 */          this.jdField_a_of_type_Bo.a().a().b("ERROR: shop out of item");
/* 46:46 */          if (i >= 0) {
/* 47:47 */            this.jdField_a_of_type_Bo.a().a();
/* 48:48 */            this.jdField_a_of_type_Bo.a().a(String.valueOf(localkf.a().a(i)));
/* 49:49 */            this.jdField_a_of_type_Bo.a().e();
/* 50:50 */            this.jdField_a_of_type_Bo.a().g();
/* 51:   */          }
/* 52:52 */          return false;
/* 53:   */        }
/* 54:   */        
/* 56:56 */        int i = localkf.a(localElementInformation, paramString);
/* 57:   */        
/* 58:58 */        if (this.jdField_a_of_type_Bo.a().a().b() >= i)
/* 59:   */        {
/* 60:60 */          xe.b("0022_action - purchase with credits");
/* 61:61 */          return true;
/* 62:   */        }
/* 63:63 */        paramString = this.jdField_a_of_type_Bo.a().a().b() / localkf.a(localElementInformation, 1);
/* 64:64 */        this.jdField_a_of_type_Bo.a().a().b("ERROR\nYou can't affort that many!\nYou can only affort " + paramString);
/* 65:   */        
/* 68:68 */        this.jdField_a_of_type_Bo.a().a();
/* 69:69 */        this.jdField_a_of_type_Bo.a().a(String.valueOf(paramString));
/* 70:70 */        this.jdField_a_of_type_Bo.a().e();
/* 71:71 */        this.jdField_a_of_type_Bo.a().g();
/* 72:   */        
/* 73:73 */        return false;
/* 74:   */      }
/* 75:   */    }
/* 76:   */    catch (NumberFormatException localNumberFormatException) {}
/* 77:   */    
/* 78:78 */    paramwB.onFailedTextCheck("Please only enter numbers!");
/* 79:79 */    return false;
/* 80:   */  }
/* 81:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */