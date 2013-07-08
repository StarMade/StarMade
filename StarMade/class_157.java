import org.lwjgl.input.Mouse;
import org.schema.game.common.data.element.pointeffect.PointEffect;

final class class_157
  implements class_1412
{
  private long jdField_field_91_of_type_Long;
  private long field_99;
  
  class_157(class_155 paramclass_155) {}
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (Mouse.isButtonDown(0))
    {
      if ((!class_155.a42(this.jdField_field_91_of_type_Class_155)) || ((this.field_99 > 0L) && (System.currentTimeMillis() - this.field_99 > 500L)))
      {
        if (System.currentTimeMillis() - this.jdField_field_91_of_type_Long > 90L)
        {
          class_155.a43(this.jdField_field_91_of_type_Class_155).decreaseDist(1);
          class_155.a44(this.jdField_field_91_of_type_Class_155);
          this.jdField_field_91_of_type_Long = System.currentTimeMillis();
        }
        if (!class_155.a42(this.jdField_field_91_of_type_Class_155)) {
          this.field_99 = System.currentTimeMillis();
        }
      }
    }
    else {
      this.field_99 = -1L;
    }
    class_155.a45(this.jdField_field_91_of_type_Class_155, Mouse.isButtonDown(0));
  }
  
  public final boolean a1()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_157
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */