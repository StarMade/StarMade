import java.util.ArrayList;

final class class_225
  implements Runnable
{
  class_225(class_227 paramclass_227) {}
  
  public final void run()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < 2744; i++) {
      if (class_808.values()[class_227.a103(this.field_643).a20().a136().b(i)] == class_808.field_1067) {
        localArrayList.add(Integer.valueOf(i));
      }
    }
    class_227.a104(this.field_643, (Integer[])localArrayList.toArray(new Integer[localArrayList.size()]));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_225
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */