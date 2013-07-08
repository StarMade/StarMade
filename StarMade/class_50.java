import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.util.ArrayList;

final class class_50
  implements Runnable
{
  class_50(class_52 paramclass_52, class_797[] paramArrayOfclass_797, IntOpenHashSet paramIntOpenHashSet) {}
  
  public final void run()
  {
    synchronized (class_52.a38(this.jdField_field_478_of_type_Class_52))
    {
      for (class_797 localclass_797 : this.jdField_field_478_of_type_ArrayOfClass_797) {
        if ((!this.jdField_field_478_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet.contains(localclass_797.getSectorId())) && ((localclass_797 instanceof class_753)))
        {
          ((class_753)localclass_797).writeAllBufferedSegmentsToDatabase();
          synchronized (class_52.a39(this.jdField_field_478_of_type_Class_52))
          {
            class_52.a39(this.jdField_field_478_of_type_Class_52).add((class_753)localclass_797);
          }
        }
      }
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_50
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */