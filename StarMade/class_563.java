import java.util.ArrayList;
import org.schema.game.common.data.element.BlockFactory;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public final class class_563
{
  ArrayList field_869;
  public short field_869;
  
  public class_563()
  {
    this.jdField_field_869_of_type_JavaUtilArrayList = new ArrayList();
  }
  
  public final void a(short paramShort)
  {
    ElementInformation localElementInformation;
    if ((localElementInformation = ElementKeyMap.getInfo(paramShort)).getFactory() != null)
    {
      this.jdField_field_869_of_type_Short = localElementInformation.getFactory().enhancer;
      for (int i = 0; i < localElementInformation.getFactory().input.length; i++)
      {
        class_561 localclass_561;
        (localclass_561 = new class_561()).jdField_field_866_of_type_Short = paramShort;
        for (int j = 0; j < localElementInformation.getFactory().input[i].length; j++) {
          localclass_561.jdField_field_866_of_type_JavaUtilArrayList.add(localElementInformation.getFactory().input[i][j]);
        }
        for (j = 0; j < localElementInformation.getFactory().output[i].length; j++) {
          localclass_561.field_867.add(localElementInformation.getFactory().output[i][j]);
        }
        this.jdField_field_869_of_type_JavaUtilArrayList.add(localclass_561);
      }
    }
  }
  
  public final String toString()
  {
    return "Factory Products: " + this.jdField_field_869_of_type_JavaUtilArrayList.size();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_563
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */