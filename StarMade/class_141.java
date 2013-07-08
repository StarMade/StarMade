import org.schema.schine.network.client.ClientState;

public final class class_141
  extends class_196
{
  class_797 field_89;
  
  public class_141(ClientState paramClientState, class_422 paramclass_422, class_797 paramclass_797)
  {
    super(paramClientState, paramclass_422, "Faction Block Config", "");
    this.jdField_field_89_of_type_Class_797 = paramclass_797;
  }
  
  public final void c()
  {
    super.c();
    class_934 localclass_9341;
    (localclass_9341 = new class_934(a24(), 200, 20, "Reset Faction Signitaure", this.jdField_field_89_of_type_Class_1412)).jdField_field_89_of_type_JavaLangObject = "NEUTRAL";
    class_934 localclass_9342;
    (localclass_9342 = new class_934(a24(), 200, 20, "Enter Faction Signiture", this.jdField_field_89_of_type_Class_1412)).jdField_field_89_of_type_JavaLangObject = "FACTION";
    localclass_9342.a83().field_616 = 30.0F;
    class_136 localclass_136;
    (localclass_136 = new class_136(this, a24(), "Make Faction Home", this.jdField_field_89_of_type_Class_1412)).jdField_field_89_of_type_JavaLangObject = "HOMEBASE";
    localclass_136.a83().field_616 = 60.0F;
    this.jdField_field_89_of_type_Class_1414.a9(localclass_136);
    this.jdField_field_89_of_type_Class_1414.a9(localclass_9341);
    this.jdField_field_89_of_type_Class_1414.a9(localclass_9342);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_141
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */