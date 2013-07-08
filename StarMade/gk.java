/*  1:   */import java.util.Observable;
/*  2:   */import java.util.Observer;
/*  3:   */import org.schema.schine.network.client.ClientState;
/*  4:   */
/* 12:   */public final class gk
/* 13:   */  extends yz
/* 14:   */  implements Observer
/* 15:   */{
/* 16:   */  private gm jdField_a_of_type_Gm;
/* 17:   */  private gd jdField_a_of_type_Gd;
/* 18:   */  private boolean jdField_a_of_type_Boolean;
/* 19:   */  
/* 20:   */  public gk(ClientState paramClientState)
/* 21:   */  {
/* 22:22 */    super(paramClientState);
/* 23:   */  }
/* 24:   */  
/* 28:   */  public final void a() {}
/* 29:   */  
/* 32:   */  public final void b()
/* 33:   */  {
/* 34:34 */    if (this.jdField_a_of_type_Boolean) {
/* 35:35 */      this.jdField_a_of_type_Gm.jdField_a_of_type_Boolean = true;
/* 36:36 */      this.jdField_a_of_type_Boolean = false;
/* 37:   */    }
/* 38:38 */    k();
/* 39:   */  }
/* 40:   */  
/* 42:   */  public final void c()
/* 43:   */  {
/* 44:44 */    this.jdField_a_of_type_Gd = new gd(a());
/* 45:45 */    this.jdField_a_of_type_Gd.c();
/* 46:46 */    this.jdField_a_of_type_Gm = new gl(a(), (int)(340.0F - this.jdField_a_of_type_Gd.a()));
/* 47:   */    
/* 55:55 */    this.jdField_a_of_type_Gm.c();
/* 56:56 */    this.jdField_a_of_type_Gm.a().y = this.jdField_a_of_type_Gd.a();
/* 57:57 */    a(this.jdField_a_of_type_Gm);
/* 58:58 */    a(this.jdField_a_of_type_Gd);
/* 59:   */  }
/* 60:   */  
/* 61:   */  public final float a()
/* 62:   */  {
/* 63:63 */    return this.jdField_a_of_type_Gm.a();
/* 64:   */  }
/* 65:   */  
/* 66:   */  public final float b()
/* 67:   */  {
/* 68:68 */    return this.jdField_a_of_type_Gm.b();
/* 69:   */  }
/* 70:   */  
/* 71:   */  public final void update(Observable paramObservable, Object paramObject)
/* 72:   */  {
/* 73:73 */    this.jdField_a_of_type_Boolean = true;
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */