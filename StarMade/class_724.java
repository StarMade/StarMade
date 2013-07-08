import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.Map.Entry;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.server.ServerStateInterface;

public class class_724
{
  private static final Object2ObjectOpenHashMap jdField_field_997_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap = new Object2ObjectOpenHashMap();
  private static final Object2ObjectOpenHashMap jdField_field_998_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap = new Object2ObjectOpenHashMap();
  private final Object2ObjectOpenHashMap field_999 = new Object2ObjectOpenHashMap();
  private SegmentController jdField_field_997_of_type_OrgSchemaGameCommonControllerSegmentController;
  private boolean jdField_field_997_of_type_Boolean;
  
  public class_724(StateInterface paramStateInterface, SegmentController paramSegmentController)
  {
    this.jdField_field_997_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_997_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
  }
  
  private final Object2ObjectOpenHashMap a()
  {
    if (this.jdField_field_997_of_type_Boolean) {
      return jdField_field_997_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap;
    }
    return jdField_field_998_of_type_ItUnimiDsiFastutilObjectsObject2ObjectOpenHashMap;
  }
  
  public final void a1()
  {
    synchronized (this)
    {
      Iterator localIterator = this.field_999.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        synchronized (a())
        {
          a().remove(localEntry.getKey());
          synchronized (((class_716)localEntry.getValue()).a())
          {
            ((class_716)localEntry.getValue()).jdField_field_982_of_type_JavaIoRandomAccessFile.close();
          }
        }
      }
      this.field_999.clear();
      return;
    }
  }
  
  public final RandomAccessFile a2(String arg1)
  {
    Object localObject2;
    Object localObject3;
    if (a().size() >= 512)
    {
      localObject2 = this;
      localObject3 = null;
      long l1 = System.currentTimeMillis();
      synchronized (localObject2)
      {
        synchronized (((class_724)localObject2).a())
        {
          long l2 = -1L;
          Object localObject4 = null;
          Iterator localIterator = ((class_724)localObject2).a().values().iterator();
          while (localIterator.hasNext())
          {
            localObject3 = (class_716)localIterator.next();
            if ((l2 < 0L) || (localObject4.jdField_field_982_of_type_Long < l2)) {
              l2 = (localObject4 = localObject3).jdField_field_982_of_type_Long;
            }
          }
          localObject3 = localObject4.jdField_field_982_of_type_JavaLangString;
          if ((!jdField_field_998_of_type_Boolean) && (localObject4 == null)) {
            throw new AssertionError();
          }
          synchronized (localObject4.a())
          {
            localObject4.jdField_field_982_of_type_JavaIoRandomAccessFile.close();
          }
          ((class_724)localObject2).a().remove(localObject4.jdField_field_982_of_type_JavaLangString);
          ((class_724)localObject2).field_999.remove(localObject4.jdField_field_982_of_type_JavaLangString);
        }
      }
      System.err.println("[IOFileManager] removing oldest handle DONE took " + (System.currentTimeMillis() - l1) + " ms (" + (String)localObject3 + ")");
    }
    synchronized (this)
    {
      if ((??? = (class_716)this.field_999.get(???)) == null)
      {
        ??? = new class_716(new RandomAccessFile(new File(???), "rw"), ???);
        synchronized (a())
        {
          this.field_999.put(((class_716)???).jdField_field_982_of_type_JavaLangString, ???);
          a().put(((class_716)???).jdField_field_982_of_type_JavaLangString, ???);
        }
      }
      if (!localObject1.a().getChannel().isOpen())
      {
        System.err.println("[IFFileManager] WARNING: the file " + localObject1.jdField_field_982_of_type_JavaLangString + " was closed for some reason. Reopening!");
        if ((localObject2 = localObject1).jdField_field_982_of_type_JavaIoRandomAccessFile != null) {
          try
          {
            ((class_716)localObject2).jdField_field_982_of_type_JavaIoRandomAccessFile.close();
          }
          catch (IOException localIOException)
          {
            localObject3 = null;
            localIOException.printStackTrace();
          }
        }
        ((class_716)localObject2).jdField_field_982_of_type_JavaIoRandomAccessFile = new RandomAccessFile(new File(((class_716)localObject2).jdField_field_982_of_type_JavaLangString), "rw");
      }
      return localObject1.a();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_724
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */