/*  1:   */import java.util.ArrayList;
/*  2:   */import java.util.List;
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */import javax.vecmath.Vector4f;
/*  5:   */import org.schema.schine.graphicsengine.core.GlUtil;
/*  6:   */import org.schema.schine.network.client.ClientState;
/*  7:   */
/* 13:   */public final class if
/* 14:   */  extends yD
/* 15:   */{
/* 16:   */  private id jdField_a_of_type_Id;
/* 17:   */  private yP jdField_a_of_type_YP;
/* 18:   */  private boolean jdField_a_of_type_Boolean;
/* 19:   */  private boolean b;
/* 20:   */  private yx jdField_a_of_type_Yx;
/* 21:   */  
/* 22:   */  public if(ClientState paramClientState, String paramString, id paramid, boolean paramBoolean)
/* 23:   */  {
/* 24:24 */    super(paramClientState);
/* 25:25 */    this.b = paramBoolean;
/* 26:26 */    this.a = paramString;
/* 27:27 */    this.jdField_a_of_type_Id = paramid;
/* 28:28 */    this.jdField_a_of_type_YP = new yP(315, 30, d.h(), paramClientState);
/* 29:29 */    this.jdField_a_of_type_YP.b = new ArrayList();
/* 30:30 */    this.jdField_a_of_type_YP.b.add(paramString);
/* 31:31 */    this.a = this;
/* 32:32 */    this.jdField_a_of_type_YP.a(paramid);
/* 33:33 */    this.jdField_a_of_type_Id.a(paramid);
/* 34:   */  }
/* 35:   */  
/* 38:   */  public final void a() {}
/* 39:   */  
/* 41:   */  public final void b()
/* 42:   */  {
/* 43:43 */    if (!this.jdField_a_of_type_Boolean) {
/* 44:44 */      c();
/* 45:   */    }
/* 46:46 */    GlUtil.d();
/* 47:47 */    r();
/* 48:48 */    if (this.b) {
/* 49:49 */      this.jdField_a_of_type_Yx.b();
/* 50:   */    }
/* 51:51 */    this.jdField_a_of_type_YP.b();
/* 52:   */    
/* 53:53 */    this.jdField_a_of_type_Id.a().x = this.jdField_a_of_type_YP.b();
/* 54:   */    
/* 55:55 */    this.jdField_a_of_type_Id.b();
/* 56:56 */    GlUtil.c();
/* 57:   */  }
/* 58:   */  
/* 59:   */  public final float a()
/* 60:   */  {
/* 61:61 */    return this.jdField_a_of_type_Id.a() + 5.0F;
/* 62:   */  }
/* 63:   */  
/* 64:   */  public final float b()
/* 65:   */  {
/* 66:66 */    return this.jdField_a_of_type_YP.b() + this.jdField_a_of_type_Id.b();
/* 67:   */  }
/* 68:   */  
/* 74:   */  public final void c()
/* 75:   */  {
/* 76:76 */    this.jdField_a_of_type_YP.c();
/* 77:77 */    this.jdField_a_of_type_Id.c();
/* 78:   */    
/* 79:79 */    this.jdField_a_of_type_YP.a().y += 8.0F;
/* 80:   */    
/* 81:81 */    this.jdField_a_of_type_Id.a().y += 8.0F;
/* 82:   */    
/* 83:83 */    if (this.b) {
/* 84:84 */      this.jdField_a_of_type_Yx = new yx(a(), b(), a(), new Vector4f(0.06F, 0.06F, 0.06F, 0.3F));
/* 85:   */    }
/* 86:86 */    this.jdField_a_of_type_YP.g = true;
/* 87:   */    
/* 88:88 */    this.jdField_a_of_type_Boolean = true;
/* 89:   */  }
/* 90:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     if
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */