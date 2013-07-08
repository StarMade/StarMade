public final class class_458
  extends class_456
{
  public class_458(class_999 paramclass_9991, class_999 paramclass_9992, class_999 paramclass_9993, class_371 paramclass_371)
  {
    super(paramclass_9991, paramclass_9992, paramclass_9993, paramclass_371);
  }
  
  protected final class_999 a(class_999 paramclass_999)
  {
    class_442 localclass_442 = new class_442(this.field_166.a8(), "Press '" + class_367.field_745.b1() + "' to enter \nyour inventory.", this.field_166);
    class_300 localclass_3001 = new class_300(this.field_166.a8(), this.field_166, "Good!\nHere you see all the building\nmodules you currently have.\n", 8000);
    class_300 localclass_3002 = new class_300(this.field_166.a8(), this.field_166, "You can drag and stack item\n", 8000);
    class_300 localclass_3003 = new class_300(this.field_166.a8(), this.field_166, "To take a custom amount of an item,\nright-click on it.", 12000);
    class_300 localclass_3004 = new class_300(this.field_166.a8(), this.field_166, "You can also drag them in the\naction bar at the bottom to assign them\nto the respective number on your keyboard.", 12000);
    a2(paramclass_999, localclass_442);
    a2(localclass_442, localclass_3001);
    a2(localclass_3001, localclass_3002);
    a2(localclass_3002, localclass_3003);
    a2(localclass_3003, localclass_3004);
    return localclass_3004;
  }
  
  public final void a1()
  {
    this.field_167 = new class_300(this.field_166.a8(), this.field_166, "Let's go on to learn about the inventory", 8000);
    this.field_798 = new class_444(this.field_166.a8(), "when you are done, press '" + class_367.field_745.b1() + "' again \nto exit from your inventory", this.field_166);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_458
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */