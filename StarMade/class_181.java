import javax.vecmath.Vector4f;

public final class class_181
  extends class_196
{
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_1414 jdField_field_90_of_type_Class_1414;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_930 jdField_field_92_of_type_Class_930;
  private class_930 jdField_field_93_of_type_Class_930;
  private class_930 jdField_field_94_of_type_Class_930;
  private class_930 jdField_field_95_of_type_Class_930;
  private class_930 field_96;
  private class_930 field_97;
  private class_1404 jdField_field_89_of_type_Class_1404;
  private class_1404 jdField_field_90_of_type_Class_1404;
  private class_1404 jdField_field_92_of_type_Class_1404;
  private class_1404 jdField_field_93_of_type_Class_1404;
  private class_1404 jdField_field_94_of_type_Class_1404;
  private class_1404 jdField_field_95_of_type_Class_1404;
  public class_340 field_89;
  
  public class_181(class_371 paramclass_371, class_340 paramclass_340, class_336 paramclass_336)
  {
    super(paramclass_371, paramclass_336, "Edit Entry Permission", "Edit Entry Permission");
    this.jdField_field_89_of_type_Class_340 = paramclass_340;
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 410.0F, 90.0F, new Vector4f(0.1F, 0.1F, 0.1F, 1.0F));
  }
  
  public final void c()
  {
    super.c();
    this.jdField_field_90_of_type_Class_1414 = new class_1414(a24(), 400.0F, 100.0F);
    this.jdField_field_93_of_type_Class_930 = new class_930(100, 20, a24());
    this.jdField_field_94_of_type_Class_930 = new class_930(100, 20, a24());
    this.jdField_field_95_of_type_Class_930 = new class_930(100, 20, a24());
    this.jdField_field_92_of_type_Class_930 = new class_930(100, 20, a24());
    this.field_96 = new class_930(100, 20, a24());
    this.field_97 = new class_930(100, 20, a24());
    this.jdField_field_90_of_type_Class_930 = new class_930(100, 20, a24());
    this.jdField_field_92_of_type_Class_930.a136("Asteroids");
    this.jdField_field_93_of_type_Class_930.a136("Planets");
    this.jdField_field_94_of_type_Class_930.a136("Astronauts");
    this.jdField_field_95_of_type_Class_930.a136("Ships");
    this.field_96.a136("Shops");
    this.field_97.a136("Space Stations");
    this.jdField_field_90_of_type_Class_930.a136("Filters");
    this.jdField_field_89_of_type_Class_1404 = new class_179(this, a24(), 8L);
    this.jdField_field_90_of_type_Class_1404 = new class_179(this, a24(), 16L);
    this.jdField_field_92_of_type_Class_1404 = new class_179(this, a24(), 64L);
    this.jdField_field_93_of_type_Class_1404 = new class_179(this, a24(), 32L);
    this.jdField_field_94_of_type_Class_1404 = new class_179(this, a24(), 1L);
    this.jdField_field_95_of_type_Class_1404 = new class_179(this, a24(), 4L);
    this.jdField_field_92_of_type_Class_930.a161(0.0F, 0.0F, 0.0F);
    this.jdField_field_93_of_type_Class_930.a161(70.0F, 0.0F, 0.0F);
    this.jdField_field_94_of_type_Class_930.a161(140.0F, 0.0F, 0.0F);
    this.jdField_field_95_of_type_Class_930.a161(210.0F, 0.0F, 0.0F);
    this.field_96.a161(0.0F, 50.0F, 0.0F);
    this.field_97.a161(70.0F, 50.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_92_of_type_Class_930);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_93_of_type_Class_930);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_94_of_type_Class_930);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_95_of_type_Class_930);
    this.jdField_field_90_of_type_Class_1414.a9(this.field_96);
    this.jdField_field_90_of_type_Class_1414.a9(this.field_97);
    this.jdField_field_89_of_type_Class_1404.a161(0.0F, 12.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1404.a161(70.0F, 12.0F, 0.0F);
    this.jdField_field_92_of_type_Class_1404.a161(140.0F, 12.0F, 0.0F);
    this.jdField_field_93_of_type_Class_1404.a161(210.0F, 12.0F, 0.0F);
    this.jdField_field_94_of_type_Class_1404.a161(0.0F, 62.0F, 0.0F);
    this.jdField_field_95_of_type_Class_1404.a161(70.0F, 62.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_1404);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_90_of_type_Class_1404);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_92_of_type_Class_1404);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_94_of_type_Class_1404);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_95_of_type_Class_1404);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_93_of_type_Class_1404);
    this.jdField_field_90_of_type_Class_1414.a83().field_616 = 0.0F;
    this.jdField_field_89_of_type_Class_1402.a9(this.jdField_field_90_of_type_Class_1414);
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_1402);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    k();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_1402.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_1402.b1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_181
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */