import java.util.ArrayList;

public final class class_464
  extends class_456
{
  public class_464(class_999 paramclass_9991, class_999 paramclass_9992, class_999 paramclass_9993, class_371 paramclass_371)
  {
    super(paramclass_9991, paramclass_9992, paramclass_9993, paramclass_371);
  }
  
  protected final class_999 a(class_999 paramclass_999)
  {
    ArrayList localArrayList;
    (localArrayList = new ArrayList()).add(new class_300(this.field_166.a8(), this.field_166, "Let's start with basic movement\n", 5000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "currently there is no time\nconstraint on how long your\ncharacter can survive in space,\nbut this \"feature\" is planned", 8000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "Your player's health\nis indicated by the bar in the \nbottom of your screen,\n(if you die, you lose half \nof your credits)", 12000));
    localArrayList.add(new class_401(this.field_166.a8(), this.field_166, "Use the mouse to look around,\nand '" + class_367.field_713.b1() + class_367.field_711.b1() + class_367.field_714.b1() + class_367.field_712.b1() + "' to move your character,\nand '" + class_367.field_715.b1() + "'/'" + class_367.field_716.b1() + "' to float Up/Down\n", 20000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "Keybord assignments can be\nchanged in the options (ESC)", 20000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "Here are a few things about\nthe interface.", 10000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "Every indicator on screen\nstands for another Entity.", 10000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "Red indicators are Enemies,\n\nGreen indicators are Friends.", 9000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "Light Blue indicators are\nempty ships,\n\nPurple indicators are shops.\n", 9000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "On the top right is a small\nmap of your surroundings\nseen from the top of the camera", 10000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "Use '" + class_367.field_752.b1() + "' to switch to the \ntarget you are looking at.\nThe target's indicator has to be in the.\ncenter of the screen.\n", 12000));
    localArrayList.add(new class_401(this.field_166.a8(), this.field_166, "You can select an object\nby looking at it,\nand pressing '" + class_367.field_752.b1() + "'.", 16000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "The object will be highlighted,\nwhich can be vital in battle\nto help you keep track of an enemy", 6000));
    localArrayList.add(new class_300(this.field_166.a8(), this.field_166, "There are also a lot more other\ntechniques to select an object.\nYou can for example also select\nan object from a list by pressing '" + class_367.field_747.b1() + "'", 15000));
    a2(paramclass_999, (class_999)localArrayList.get(0));
    for (paramclass_999 = 0; paramclass_999 < localArrayList.size() - 1; paramclass_999++) {
      a2((class_999)localArrayList.get(paramclass_999), (class_999)localArrayList.get(paramclass_999 + 1));
    }
    return (class_999)localArrayList.get(localArrayList.size() - 1);
  }
  
  public final void a1()
  {
    this.field_167 = new class_300(this.field_166.a8(), this.field_166, "This tutorial will guide\nyou through the basic\nfunctions of the game\n", 3000);
    this.field_798 = new class_401(this.field_166.a8(), this.field_166, "take some time to get \naccustomed to the controls\n", 20000);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_464
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */