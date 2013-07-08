/*  1:   */import javax.vecmath.Vector4f;
/*  2:   */import org.schema.schine.network.client.ClientState;
/*  3:   */
/*  9:   */public final class yt
/* 10:   */  extends yz
/* 11:   */{
/* 12:   */  private yx jdField_a_of_type_Yx;
/* 13:   */  private yG jdField_a_of_type_YG;
/* 14:   */  private yP jdField_a_of_type_YP;
/* 15:   */  
/* 16:   */  public yt(ClientState paramClientState)
/* 17:   */  {
/* 18:18 */    super(paramClientState);
/* 19:19 */    this.jdField_a_of_type_YP = new yP(400, 150, d.n(), paramClientState);
/* 20:20 */    this.jdField_a_of_type_YP.a = 100;
/* 21:   */    
/* 22:22 */    this.jdField_a_of_type_YP.b = a().getGeneralChatLog();
/* 23:   */    
/* 24:24 */    this.jdField_a_of_type_YG = new yH(paramClientState);
/* 25:25 */    this.jdField_a_of_type_Yx = new yx(paramClientState, 400.0F, 150.0F, new Vector4f(1.0F, 1.0F, 1.0F, 0.1F));
/* 26:   */  }
/* 27:   */  
/* 30:   */  public final void a() {}
/* 31:   */  
/* 34:   */  public final void b()
/* 35:   */  {
/* 36:36 */    this.jdField_a_of_type_Yx.b();
/* 37:   */  }
/* 38:   */  
/* 40:   */  public final void c()
/* 41:   */  {
/* 42:42 */    this.jdField_a_of_type_Yx.c = 5.0F;
/* 43:   */    
/* 44:44 */    this.jdField_a_of_type_Yx.a(this.jdField_a_of_type_YG);
/* 45:45 */    this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YP);
/* 46:   */    
/* 47:47 */    this.jdField_a_of_type_Yx.c();
/* 48:48 */    this.jdField_a_of_type_YG.c();
/* 49:49 */    this.jdField_a_of_type_YP.c();
/* 50:   */  }
/* 51:   */  
/* 53:   */  public final float a()
/* 54:   */  {
/* 55:55 */    return 0.0F;
/* 56:   */  }
/* 57:   */  
/* 59:   */  public final float b()
/* 60:   */  {
/* 61:61 */    return 0.0F;
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */