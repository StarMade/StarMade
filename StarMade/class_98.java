final class class_98
{
  class_98(class_101 paramclass_101, class_773 paramclass_773) {}
  
  public final String toString()
  {
    if (this.jdField_field_622_of_type_Class_773.a3() == ((class_371)this.jdField_field_622_of_type_Class_101.a24()).a20().a141().a3()) {
      return "*own*";
    }
    class_765 localclass_765;
    boolean bool2 = (localclass_765 = ((class_371)this.jdField_field_622_of_type_Class_101.a24()).a45()).a158(this.jdField_field_622_of_type_Class_773.a3(), ((class_371)this.jdField_field_622_of_type_Class_101.a24()).a20().a141().a3());
    boolean bool1 = localclass_765.b28(this.jdField_field_622_of_type_Class_773.a3(), ((class_371)this.jdField_field_622_of_type_Class_101.a24()).a20().a141().a3());
    if (bool2) {
      return "Enemy";
    }
    if (bool1) {
      return "Ally";
    }
    return "Neutral";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_98
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */