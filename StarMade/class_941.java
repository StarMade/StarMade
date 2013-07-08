public final class class_941
{
  private long jdField_field_1169_of_type_Long = 0L;
  private long jdField_field_1170_of_type_Long;
  private long field_1171;
  private int jdField_field_1169_of_type_Int;
  private long field_1172 = 0L;
  private double jdField_field_1169_of_type_Double;
  private static double jdField_field_1170_of_type_Double = 1000000000.0D;
  
  public final float a()
  {
    return (float)this.jdField_field_1169_of_type_Double;
  }
  
  public final long a1()
  {
    return this.jdField_field_1170_of_type_Long;
  }
  
  public final void a2()
  {
    this.field_1171 = System.currentTimeMillis();
    this.jdField_field_1169_of_type_Long = System.nanoTime();
  }
  
  public final void b()
  {
    if (System.currentTimeMillis() - this.field_1171 > 1000L)
    {
      this.jdField_field_1170_of_type_Long = this.jdField_field_1169_of_type_Int;
      this.jdField_field_1169_of_type_Int = 0;
      this.field_1171 += 1000L;
    }
    this.jdField_field_1169_of_type_Int += 1;
    this.jdField_field_1169_of_type_Long = this.field_1172;
    this.field_1172 = System.nanoTime();
    long l = this.field_1172 - this.jdField_field_1169_of_type_Long;
    this.jdField_field_1169_of_type_Double = (l / jdField_field_1170_of_type_Double);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_941
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */