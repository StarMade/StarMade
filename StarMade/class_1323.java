import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;
import org.schema.game.common.staremote.Staremote;

public final class class_1323
  extends AbstractListModel
{
  private static final long serialVersionUID = 6959802025354159616L;
  private ArrayList field_1505 = new ArrayList();
  
  public class_1323()
  {
    try
    {
      b();
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  public final Object getElementAt(int paramInt)
  {
    return this.field_1505.get(paramInt);
  }
  
  public final int getSize()
  {
    return this.field_1505.size();
  }
  
  public final void a(class_1315 paramclass_1315)
  {
    this.field_1505.add(paramclass_1315);
    a1();
    try
    {
      b();
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  private void a1()
  {
    try
    {
      if (!(localObject = new File(Staremote.a3())).exists()) {
        ((File)localObject).createNewFile();
      }
      Object localObject = new BufferedWriter(new FileWriter((File)localObject));
      Iterator localIterator = this.field_1505.iterator();
      while (localIterator.hasNext())
      {
        class_1315 localclass_1315 = (class_1315)localIterator.next();
        ((BufferedWriter)localObject).append(localclass_1315.jdField_field_1492_of_type_JavaLangString + "," + localclass_1315.field_1493 + ":" + localclass_1315.jdField_field_1492_of_type_Int + "\n");
      }
      ((BufferedWriter)localObject).flush();
      ((BufferedWriter)localObject).close();
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
  
  private void b()
  {
    this.field_1505.clear();
    if (!(localObject1 = new File(Staremote.a3())).exists()) {
      throw new FileNotFoundException();
    }
    Object localObject1 = new BufferedReader(new FileReader((File)localObject1));
    Object localObject2;
    while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null)
    {
      String str1 = (localObject2 = ((String)localObject2).split(",", 21))[0];
      String str2 = (localObject2 = localObject2[1].split(":", 2))[0];
      int i = Integer.parseInt(localObject2[1]);
      class_1315 localclass_1315 = new class_1315(str2, i, str1);
      this.field_1505.add(localclass_1315);
    }
    ((BufferedReader)localObject1).close();
    fireContentsChanged(this, 0, this.field_1505.size() - 1);
  }
  
  public final void b1(class_1315 paramclass_1315)
  {
    this.field_1505.remove(paramclass_1315);
    a1();
    try
    {
      b();
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1323
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */