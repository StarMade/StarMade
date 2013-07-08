import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.game.common.data.world.DeserializationException;

public final class class_17
  extends Thread
{
  private ArrayList field_438 = new ArrayList();
  
  public class_17()
  {
    super("LocalSegmentRetriever");
  }
  
  public final void a(class_22 paramclass_22)
  {
    synchronized (this.field_438)
    {
      this.field_438.add(paramclass_22);
      this.field_438.notify();
      return;
    }
  }
  
  private class_22 a1()
  {
    synchronized (this.field_438)
    {
      while (this.field_438.isEmpty()) {
        try
        {
          this.field_438.wait();
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException;
        }
      }
      return (S)this.field_438.remove(0);
    }
  }
  
  public final void run()
  {
    for (;;)
    {
      class_22 localclass_22;
      class_672 localclass_672 = (localclass_22 = a1()).jdField_field_440_of_type_Class_672;
      int j = 0;
      int i;
      try
      {
        i = localclass_22.jdField_field_440_of_type_Class_20.a9(localclass_672);
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        System.err.println("CORRUPT SEGMENT DATA: RELOADING FROM SERVER. Writing Jobs: " + class_20.field_14);
        j = 1;
        i = 0;
      }
      catch (DeserializationException localDeserializationException)
      {
        localDeserializationException.printStackTrace();
        System.err.println("CORRUPT SEGMENT DATA: RELOADING FROM SERVER. Writing Jobs: " + class_20.field_14);
        j = 1;
        i = 0;
      }
      if ((j != 0) || (i == 2))
      {
        if (j != 0) {
          System.err.println("[CLIENT][SEGMENTPROVIDER][WARNING] Requesting corrupt segment!! " + localclass_672.field_34 + ": " + localclass_672.a15());
        } else {
          System.err.println("[CLIENT][SEGMENTPROVIDER][WARNING] VersionOK but no data in DB! Re-Requesting segment from server!! " + localclass_672.field_34 + ": " + localclass_672.a15());
        }
        synchronized (localclass_22.jdField_field_440_of_type_Class_20.c2())
        {
          localclass_22.jdField_field_440_of_type_Class_20.c2().add(new class_48(localclass_672.field_34));
        }
      }
      if ((??? == 1) && (localclass_672.field_34.field_475 == 0) && (localclass_672.field_34.field_476 == 0) && (localclass_672.field_34.field_477 == 0)) {
        System.err.println("[CLIENT][DB-READ] WARNING 0,0,0 on " + localObject1.jdField_field_440_of_type_Class_20.a26() + " IS EMPTY");
      }
      synchronized (localObject1.jdField_field_440_of_type_Class_20.a4())
      {
        localObject1.jdField_field_440_of_type_Class_20.a4().add(localclass_672);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_17
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */