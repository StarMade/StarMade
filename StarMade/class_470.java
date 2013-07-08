public final class class_470
  extends class_456
{
  private static class_1003 jdField_field_166_of_type_Class_1003 = new class_381();
  private class_435 jdField_field_166_of_type_Class_435;
  
  public class_470(class_999 paramclass_9991, class_999 paramclass_9992, class_999 paramclass_9993, class_371 paramclass_371)
  {
    super(paramclass_9991, paramclass_9992, paramclass_9993, paramclass_371);
  }
  
  protected final class_999 a(class_999 paramclass_999)
  {
    class_302 localclass_302 = new class_302(this.field_166.a8(), "Please press '" + class_367.field_744.b1() + "' to open the shop!\n", this.jdField_field_166_of_type_Class_371);
    class_401 localclass_401 = new class_401(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Very Good!\nThis is the shop screen!\nLook around for a bit!\n", 10000);
    class_300 localclass_3001 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Shops can only be accessed,\nwhen you are near a shop\nand you see the [SHOP] sign\non top of you screen", 10000);
    class_300 localclass_3002 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "You can read more about every\nelement, if you click on it.\n", 6000);
    class_300 localclass_3003 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Every shop has an individual stock of items.\nThe price of an item is relative to\nthe amount in stock (demand)\n", 6000);
    class_300 localclass_3004 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "To buy or sell,\njust click on an element you like,\nand use the respective buttons, \nor drag items into the shop\n", 9000);
    class_448 localclass_448 = new class_448(this.field_166.a8(), "when you are done, press 'b' again \nto exit the shop screen", this.jdField_field_166_of_type_Class_371);
    class_300 localclass_3005 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "There is an infinite amount of shops\nscattered all over the universe.\nIf this one runs out of stock, try to find another one.\n", 0);
    class_300 localclass_3006 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "You can also profit by buying an item\nfrom a shop with good stock,\nand selling it to a shop that is currently\nout of that item.", 0);
    a4(paramclass_999, this.jdField_field_166_of_type_Class_435, this.field_167);
    a2(this.jdField_field_166_of_type_Class_435, localclass_302);
    a2(localclass_302, localclass_401);
    a2(localclass_401, localclass_3001);
    a2(localclass_3001, localclass_3002);
    a2(localclass_3002, localclass_3003);
    a2(localclass_3003, localclass_3004);
    a2(localclass_3004, localclass_448);
    a4(localclass_448, localclass_3005, localclass_3004);
    a2(localclass_3005, localclass_3006);
    return localclass_3006;
  }
  
  public final void a1()
  {
    this.jdField_field_166_of_type_Class_435 = new class_435(this.field_166.a8(), "You are not in Range of a Shop\nPlease go to the next Shop\n(follow the purple indicator)", this.jdField_field_166_of_type_Class_371);
    this.field_167 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Awesome!\nNow let's do some shopping!", 5000);
    this.field_798 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Very Good!\nYou have completed the shop introduction!\n", 5000);
  }
  
  protected final void a2(class_999 paramclass_9991, class_999 paramclass_9992)
  {
    super.a2(paramclass_9991, paramclass_9992);
    paramclass_9991.a9(jdField_field_166_of_type_Class_1003, this.jdField_field_166_of_type_Class_435);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_470
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */