/*  1:   */import javax.vecmath.Vector3f;
/*  2:   */import org.lwjgl.input.Mouse;
/*  3:   */import org.schema.schine.network.client.ClientState;
/*  4:   */
/*  8:   */public final class yq
/*  9:   */  extends yr
/* 10:   */{
/* 11:   */  private boolean jdField_a_of_type_Boolean;
/* 12:12 */  private q jdField_a_of_type_Q = new q();
/* 13:   */  public yz a;
/* 14:   */  private boolean b;
/* 15:   */  
/* 16:   */  public yq(ClientState paramClientState, yz paramyz)
/* 17:   */  {
/* 18:18 */    super(paramClientState, 380.0F, 40.0F);
/* 19:   */    
/* 20:20 */    this.jdField_a_of_type_Yz = paramyz;
/* 21:   */  }
/* 22:   */  
/* 27:   */  public final void b()
/* 28:   */  {
/* 29:29 */    this.g = true;
/* 30:30 */    super.b();
/* 31:   */    
/* 32:32 */    if ((a_()) && (Mouse.isButtonDown(0)) && 
/* 33:33 */      (!this.b) && 
/* 34:34 */      (!this.jdField_a_of_type_Boolean)) {
/* 35:35 */      this.jdField_a_of_type_Boolean = true;
/* 36:   */      
/* 37:37 */      this.jdField_a_of_type_Q.b(Mouse.getX(), xm.a() - Mouse.getY(), 0);
/* 38:   */    }
/* 39:   */    
/* 42:42 */    this.b = Mouse.isButtonDown(0);
/* 43:   */    Object localObject;
/* 44:44 */    if (this.jdField_a_of_type_Boolean)
/* 45:   */    {
/* 46:46 */      (localObject = new q(Mouse.getX(), xm.a() - Mouse.getY(), 0)).c(this.jdField_a_of_type_Q);
/* 47:47 */      this.jdField_a_of_type_Yz.a().x += ((q)localObject).a;
/* 48:48 */      this.jdField_a_of_type_Yz.a().y += ((q)localObject).b;
/* 49:   */      
/* 52:52 */      this.jdField_a_of_type_Q.b(Mouse.getX(), xm.a() - Mouse.getY(), 0);
/* 53:53 */      if (!Mouse.isButtonDown(0))
/* 54:54 */        this.jdField_a_of_type_Boolean = false; }
/* 55:55 */    if ((localObject = this.jdField_a_of_type_Yz).a().x < 0.0F) ((yz)localObject).a().x = 0.0F; if (((yz)localObject).a().y < 0.0F) ((yz)localObject).a().y = 0.0F; if (((yz)localObject).a().x + ((yz)localObject).b() > xm.b()) ((yz)localObject).a().x = (xm.b() - ((yz)localObject).b()); if (((yz)localObject).a().y + ((yz)localObject).a() > xm.a()) ((yz)localObject).a().y = (xm.a() - ((yz)localObject).a());
/* 56:   */  }
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */