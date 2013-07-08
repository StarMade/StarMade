import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import org.schema.game.common.data.element.Element;
import org.schema.schine.network.NetworkStateContainer;

public final class class_373
{
  private final class_371 field_770;
  public class_48 field_770;
  public class_48 field_771;
  private class_48 field_772 = new class_48();
  private class_48 field_773 = new class_48();
  
  public class_373(class_371 paramclass_371)
  {
    this.jdField_field_770_of_type_Class_371 = paramclass_371;
  }
  
  public final void a(int paramInt)
  {
    if (this.jdField_field_770_of_type_Class_48 != null)
    {
      if ((paramInt = (class_665)this.jdField_field_770_of_type_Class_371.getLocalAndRemoteObjectContainer().getLocalObjects().get(paramInt)) == null)
      {
        this.jdField_field_770_of_type_Class_371.a4().field_39 = true;
        return;
      }
      paramInt = paramInt.a34();
      System.err.println("UPDATE WAYPOINT: " + this.jdField_field_770_of_type_Class_48);
      if (this.field_771 == null) {
        this.field_771 = new class_48();
      }
      if (paramInt.equals(this.jdField_field_770_of_type_Class_48))
      {
        a1(null);
        return;
      }
      this.field_773.b(0, 0, 0);
      for (int i = 0; i < Element.DIRECTIONSi.length; i++)
      {
        this.field_772.b2(paramInt, Element.DIRECTIONSi[i]);
        if (this.field_772.equals(this.jdField_field_770_of_type_Class_48))
        {
          this.field_771.b2(paramInt, Element.DIRECTIONSi[i]);
          break;
        }
        System.err.println("CHECKING WAYPOINT: " + paramInt + ": " + this.field_772);
        this.field_772.c1(this.jdField_field_770_of_type_Class_48);
        if ((this.field_773.a4() == 0.0F) || (this.field_772.a4() < this.field_773.a4()))
        {
          this.field_771.b2(paramInt, Element.DIRECTIONSi[i]);
          this.field_773.b1(this.field_772);
        }
        else
        {
          System.err.println("NOT TAKING: " + this.field_772.a4() + " / " + this.field_773.a4());
        }
      }
      System.err.println("NEAREST WAYPOINT " + this.field_771);
    }
  }
  
  public final void a1(class_48 paramclass_48)
  {
    System.err.println("SETTING WAYPOINT: " + paramclass_48);
    this.jdField_field_770_of_type_Class_48 = paramclass_48;
    this.field_771 = null;
    a(this.jdField_field_770_of_type_Class_371.a8());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_373
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */