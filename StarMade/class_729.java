import java.io.PrintStream;

public final class class_729
{
  class_901 field_1006 = new class_901();
  
  public class_729(String paramString)
  {
    Object localObject = new class_723(this);
    (localObject = new Thread((Runnable)localObject)).setName(paramString + "_SEGMENT_WRITER_THREAD");
    ((Thread)localObject).start();
  }
  
  public final void a(class_672 paramclass_672)
  {
    while ((paramclass_672 != null) && (this.field_1006.a(paramclass_672))) {
      try
      {
        System.err.println("WAITING TO FINISH WRITING " + paramclass_672);
        Thread.sleep(100L);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_729
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */