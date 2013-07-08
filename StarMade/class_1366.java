import java.io.PrintStream;
import org.lwjgl.opengl.GL20;
import org.schema.schine.graphicsengine.core.GLException;

public final class class_1366
  extends class_1370
  implements class_965
{
  private class_1365 jdField_field_274_of_type_Class_1365;
  private class_1364 jdField_field_274_of_type_Class_1364;
  private class_1367 jdField_field_274_of_type_Class_1367;
  
  public class_1366(class_925 paramclass_925)
  {
    this.field_1541 = paramclass_925;
    e();
  }
  
  public final void a() {}
  
  private void e()
  {
    System.err.println("BLOOM SHADER INITIALIZING!!!");
    if (this.jdField_field_274_of_type_Class_925 != null) {
      this.jdField_field_274_of_type_Class_925.a();
    }
    if (this.field_1540 != null) {
      this.field_1540.a();
    }
    this.jdField_field_274_of_type_Class_925 = new class_925(class_933.b() / 2, class_933.a() / 2);
    this.jdField_field_274_of_type_Class_925.e();
    this.field_1540 = new class_925(class_933.b() / 2, class_933.a() / 2);
    this.field_1540.e();
    this.jdField_field_274_of_type_Class_1365 = new class_1365(this.field_1541, this.jdField_field_274_of_type_Class_925, this);
    this.jdField_field_274_of_type_Class_1364 = new class_1364(this.field_1541, this.jdField_field_274_of_type_Class_925, this.field_1540);
    this.jdField_field_274_of_type_Class_1367 = new class_1367(this.field_1541, this.jdField_field_274_of_type_Class_925, this.field_1540);
  }
  
  public final void b()
  {
    if (class_933.b1()) {
      try
      {
        e();
      }
      catch (GLException localGLException2)
      {
        GLException localGLException1;
        (localGLException1 = localGLException2).printStackTrace();
        class_933.a2(localGLException1);
      }
    }
    d();
    this.jdField_field_274_of_type_Class_1365.b();
    this.jdField_field_274_of_type_Class_1364.b();
    this.jdField_field_274_of_type_Class_1367.b();
    GL20.glUseProgram(0);
  }
  
  public final void c() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1366
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */