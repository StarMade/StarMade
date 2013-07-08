/*  1:   */import java.io.PrintStream;
/*  2:   */import org.lwjgl.opengl.GL20;
/*  3:   */import org.schema.schine.graphicsengine.core.GLException;
/*  4:   */
/*  9:   */public final class zy
/* 10:   */  extends zu
/* 11:   */  implements xg
/* 12:   */{
/* 13:   */  private zv jdField_a_of_type_Zv;
/* 14:   */  private zw jdField_a_of_type_Zw;
/* 15:   */  private zx jdField_a_of_type_Zx;
/* 16:   */  
/* 17:   */  public zy(xi paramxi)
/* 18:   */  {
/* 19:19 */    this.c = paramxi;
/* 20:   */    
/* 22:22 */    e();
/* 23:   */  }
/* 24:   */  
/* 30:   */  public final void a() {}
/* 31:   */  
/* 36:   */  private void e()
/* 37:   */  {
/* 38:38 */    System.err.println("BLOOM SHADER INITIALIZING!!!");
/* 39:39 */    if (this.jdField_a_of_type_Xi != null) {
/* 40:40 */      this.jdField_a_of_type_Xi.a();
/* 41:   */    }
/* 42:42 */    if (this.b != null) {
/* 43:43 */      this.b.a();
/* 44:   */    }
/* 45:45 */    this.jdField_a_of_type_Xi = new xi(xm.b() / 2, xm.a() / 2);
/* 46:46 */    this.jdField_a_of_type_Xi.e();
/* 47:47 */    this.b = new xi(xm.b() / 2, xm.a() / 2);
/* 48:48 */    this.b.e();
/* 49:49 */    this.jdField_a_of_type_Zv = new zv(this.c, this.jdField_a_of_type_Xi, this);
/* 50:50 */    this.jdField_a_of_type_Zw = new zw(this.c, this.jdField_a_of_type_Xi, this.b);
/* 51:51 */    this.jdField_a_of_type_Zx = new zx(this.c, this.jdField_a_of_type_Xi, this.b);
/* 52:   */  }
/* 53:   */  
/* 54:   */  public final void b() {
/* 55:55 */    if (xm.b()) {
/* 56:   */      try {
/* 57:57 */        e();
/* 58:   */      } catch (GLException localGLException2) { GLException localGLException1;
/* 59:59 */        (localGLException1 = 
/* 60:   */        
/* 61:61 */          localGLException2).printStackTrace();xm.a(localGLException1);
/* 62:   */      }
/* 63:   */    }
/* 64:63 */    d();
/* 65:64 */    this.jdField_a_of_type_Zv.b();
/* 66:65 */    this.jdField_a_of_type_Zw.b();
/* 67:66 */    this.jdField_a_of_type_Zx.b();
/* 68:   */    
/* 69:68 */    GL20.glUseProgram(0);
/* 70:   */  }
/* 71:   */  
/* 72:   */  public final void c() {}
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */