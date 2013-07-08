import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import org.schema.schine.ai.stateMachines.FSMException;

public final class class_468
  extends class_456
  implements Observer
{
  private class_452 jdField_field_166_of_type_Class_452;
  private class_450 jdField_field_166_of_type_Class_450;
  private class_300 jdField_field_166_of_type_Class_300;
  private class_452 jdField_field_167_of_type_Class_452;
  private class_450 jdField_field_167_of_type_Class_450;
  private class_466 jdField_field_166_of_type_Class_466;
  private class_300 jdField_field_167_of_type_Class_300;
  
  public class_468(class_999 paramclass_9991, class_999 paramclass_9992, class_999 paramclass_9993, class_371 paramclass_371)
  {
    super(paramclass_9991, paramclass_9992, paramclass_9993, paramclass_371);
    paramclass_371.a14().a18().a79().a60().a51().a45().a36().addObserver(this);
    paramclass_371.a14().a18().a79().a60().a51().a45().a38().addObserver(this);
    paramclass_371.a14().a18().a79().a60().a52().addObserver(this);
  }
  
  protected final class_999 a(class_999 paramclass_999)
  {
    (paramclass_999 = new ArrayList()).add(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Please wait until the new ship is!\nloaded. In multiplayer, it might take\na short moment.", 6000));
    paramclass_999.add(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Congratulations!\nThis is your first Ship!\n", 4000));
    paramclass_999.add(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "The block you see is the core\nmodule.\nIt's the most important part of a ship", 8000));
    paramclass_999.add(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "If your core takes too much damage,\nyou will die, and the core will overheat.\nYou can stop any core from overheating,\nif you enter it.\n", 8000));
    paramclass_999.add(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "The bigger your ship is,\nthe longer the overheating will take.\nShould the counter reach zero,\nyour ship will be destroyed!", 8000));
    paramclass_999.add(new class_446(this.field_166.a8(), "You can enter your ship this way:\nAim at the core element, and\npress '" + class_367.field_732.b1() + "'. You have to be near\nenough to the core, to enter a ship.", this.jdField_field_166_of_type_Class_371).a9(new class_389(), this.jdField_field_166_of_type_Class_466));
    paramclass_999.add(f(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Good job!\nYou are now in the special build mode\nof this ship. You can of course add blocks\nfrom outside the ship, but this mode\noffers a lot of advantages.", 4000)));
    paramclass_999.add(f(new class_401(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "You can move around the same way\n you do outside the ship.\nNote, that in this build mode, you will always\nbe orientated to the ship.", 12000)));
    paramclass_999.add(f(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "With '" + class_367.field_730.b1() + "' you can reset the\nview to the ship\n", 8000)));
    paramclass_999.add(f(new class_401(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "The left mouse button builds,\nand the right removes blocks.\nSelect the block you want to place\nwith the mouse wheel, or number key\n('" + class_367.field_730.b1() + "' to reset camera)", 25000)));
    paramclass_999.add(f(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "You can switch the mouse\nbuttons in the options\n", 8000)));
    paramclass_999.add(f(new class_401(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Holding down " + class_367.field_758.b1() + "\nwill let you place blocks\nfrom a fixed camera angle.\n", 20000)));
    paramclass_999.add(f(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Holding down " + class_367.field_758.b1() + "\nalso lets you use advanced\nbuilding tools like symmetry.\n", 20000)));
    paramclass_999.add(f(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "The arrow on top of the screen\nindicates, where the front of\nthe ship is.\nBe careful not to build sideways!", 6000)));
    paramclass_999.add(f(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "You can switch between build mode and\nflight mode with '" + class_367.field_728.b1() + "'.\n", 8000)));
    this.jdField_field_166_of_type_Class_452 = new class_452(this.field_166.a8(), "You are not in build mode.\nPlease press '" + class_367.field_728.b1() + "'\nto switch back to build mode.\n", this.jdField_field_166_of_type_Class_371);
    paramclass_999.add(f(this.jdField_field_166_of_type_Class_452));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "To build a functional ship,\nyou must add some thrust to your ship\n", 5000))));
    paramclass_999.add(f(b(new class_440(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, (short)8))));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Great!\nBut the thrusters need power\nso we have to place a\npower generator", 5000))));
    paramclass_999.add(f(b(new class_440(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, (short)2))));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Great work!\nPower Blocks will supply\nyour ship with energy\n", 7000))));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "The more usable blocks you have\n(Thrusters, Weapons, Shield, etc...),\nthe more power you will need.\n", 7000))));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "As you can see,\nyour ship's power bar\nat the bottom of the screen\nwill now fill up.\n", 7000))));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Blocks like Thrusters and Weapons\nwill consume that power,\nbut don't worry: \npower automatically regenerates.\n", 7000))));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "You can also achieve a higher\ncapacity and regeneration rate of \npower blocks by grouping\nthem together.\n", 7000))));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Note, that thrusters are\nconnected by default.\nConnections can be customized\nin star-made, but that will be part\nof the advanced Tutorial", 5000))));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "The more thruster elements\nyou have,\nthe faster your space ship \naccelerates", 10000))));
    paramclass_999.add(f(b(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Group thruster elements\ntogether to get an even \nbetter efficiency\n", 10000))));
    this.jdField_field_166_of_type_Class_450 = new class_450(this.field_166.a8(), "You are now ready to fly\nyour first space ship!\nPlease use the '" + class_367.field_728.b1() + "'\nto switch into flight mode.\n", this.jdField_field_166_of_type_Class_371);
    paramclass_999.add(f(this.jdField_field_166_of_type_Class_450));
    paramclass_999.add(f(d(new class_401(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "You are now in Flight mode!\nMove your ship with '" + class_367.field_713.b1() + class_367.field_711.b1() + class_367.field_714.b1() + class_367.field_712.b1() + "'.\nRoll with the mouse while holding \nthe '" + class_367.field_727.b1() + "' key.\nPress '" + class_367.field_726.b1() + "' to break", 40000))));
    paramclass_999.add(f(d(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Now let's add some weapons!\n", 8000))));
    this.jdField_field_167_of_type_Class_452 = new class_452(this.field_166.a8(), "Please use the '" + class_367.field_728.b1() + "'\nto switch back into build mode.\n", this.jdField_field_166_of_type_Class_371);
    paramclass_999.add(f(this.jdField_field_167_of_type_Class_452));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "First you have to place a\n\"weapon controller\".\nNote that Weapon controllers can\nalso be entered by other players.", 12000))));
    paramclass_999.add(f(c(new class_440(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, (short)6))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Well done!\nThe wobbling boxes mean, that the weapon\ncontroller is connected to the core.", 12000))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Now you need a weapon to \nconnect to this weapon controller.\n", 7000))));
    paramclass_999.add(f(c(new class_440(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, (short)16))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Awesome!\nArranging Weapons into larger groups\nwill increase that weapon's \nspeed, range, damage, and\ndecreases its reload time.\n", 14000))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Yay!\nYou can now obliterate \nyour enemies.\n", 7000))));
    this.jdField_field_167_of_type_Class_450 = new class_450(this.field_166.a8(), "You are now ready to test\nyour spaceship's weapons\nPlease use the '" + class_367.field_728.b1() + "'\nto switch to flight mode.\n", this.jdField_field_166_of_type_Class_371);
    paramclass_999.add(f(this.jdField_field_167_of_type_Class_450));
    paramclass_999.add(f(e(new class_401(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Press the left mouse button to fire\n", 12000))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "In StarMade, there is a connection system for each ship!\n\nIn order to use a block (e.g. weapon) from where you\nhave entered the ship, it has to be directly,\nor indirectly connected to the ship.\n", 7000))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Thrusters have to be connected to the core!\nHowever, if you place down a thruster, it will be connected\nby default, so you don't have to worry about that.\n", 7000))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "If you place down any controller (like the weapon computer),\nit will automatically connected to the core, and selected,\nso every following weapon will be connected to that weapon controller.\nThe connection chain for weapons is: weapon -> weapon computer -> core", 7000))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "All controllers connected to the core will be\navailable to use for the player that entered the core", 7000))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Note, that this chain goes for every\nusable module in the game:\nmodule -> controller -> core \n", 7000))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "If you connect two modules to one\ncontroller, they will fire\nat the same time. Grouping them \nwill make the weapon stronger", 7000))));
    paramclass_999.add(f(c(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Every controller will give you\nan action, that can be\nreassigned to a slot\nin your bottom bar\n", 8000))));
    paramclass_999.add(f(e(new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "In the bottom action bar you see\nwhich controllers are currently\nassigned to which key\n", 12000))));
    paramclass_999.add(f(e(new class_401(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "You can press '" + class_367.field_746.b1() + "' to assign a number\nkey to a controller action by selecting\nit from the list and pressing a number.\n", 25000))));
    a2(this.jdField_field_166_of_type_Class_466, (class_999)paramclass_999.get(0));
    for (int i = 0; i < paramclass_999.size() - 1; i++) {
      a2((class_999)paramclass_999.get(i), (class_999)paramclass_999.get(i + 1));
    }
    return (class_999)paramclass_999.get(paramclass_999.size() - 1);
  }
  
  public final void a1()
  {
    this.jdField_field_166_of_type_Class_300 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "You have exited the ship.\nThe ship tutorial will now restar\n(skip 'ship build and use' tutorial\nwith 'END')", 4000);
    this.jdField_field_167_of_type_Class_300 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Time to build your first ship!\nNote, that you have to have a core\nmodule to create a new ship.\n\nShip Cores are very valuable so don't lose it", 8000);
    this.jdField_field_166_of_type_Class_466 = new class_466(this.field_166.a8(), "To create a ship, \npress '" + class_367.field_734.b1() + "' to open the ship \ncreation dialog. Then\nchose a name for your ship.", this.jdField_field_166_of_type_Class_371);
    a2(this.jdField_field_167_of_type_Class_300, this.jdField_field_166_of_type_Class_466);
    a4(this.jdField_field_166_of_type_Class_300, this.jdField_field_166_of_type_Class_466, this.jdField_field_167_of_type_Class_300);
    this.field_167 = this.jdField_field_167_of_type_Class_300;
    this.field_798 = new class_300(this.field_166.a8(), this.jdField_field_166_of_type_Class_371, "Congratulations!\nyou now know how to \nbuild and fly ships", 5000);
  }
  
  private class_999 b(class_999 paramclass_999)
  {
    paramclass_999.a9(new class_397(), this.jdField_field_166_of_type_Class_452);
    return paramclass_999;
  }
  
  private class_999 c(class_999 paramclass_999)
  {
    paramclass_999.a9(new class_397(), this.jdField_field_167_of_type_Class_452);
    return paramclass_999;
  }
  
  private class_999 d(class_999 paramclass_999)
  {
    paramclass_999.a9(new class_387(), this.jdField_field_166_of_type_Class_450);
    return paramclass_999;
  }
  
  private class_999 e(class_999 paramclass_999)
  {
    paramclass_999.a9(new class_387(), this.jdField_field_167_of_type_Class_450);
    return paramclass_999;
  }
  
  private class_999 f(class_999 paramclass_999)
  {
    paramclass_999.a9(new class_379(), this.jdField_field_166_of_type_Class_300);
    paramclass_999.a9(new class_389(), this.jdField_field_166_of_type_Class_466);
    return paramclass_999;
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    System.err.println("UPDATED TUTORIAL STATE " + paramObservable);
    if (((paramObservable instanceof class_16)) && (((class_16)paramObservable).c()))
    {
      if ((paramObservable instanceof class_453)) {
        try
        {
          this.field_166.a8().a4().a2().a1(new class_387());
        }
        catch (FSMException localFSMException1) {}
      }
      if ((paramObservable instanceof class_332)) {
        try
        {
          this.field_166.a8().a4().a2().a1(new class_397());
        }
        catch (FSMException localFSMException2) {}
      }
      if ((paramObservable instanceof class_431)) {
        try
        {
          this.field_166.a8().a4().a2().a1(new class_379());
          return;
        }
        catch (FSMException localFSMException3) {}
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_468
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */