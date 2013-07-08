import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public abstract class class_16
  extends Observable
  implements class_954, class_938
{
  private final class_371 jdField_field_4_of_type_Class_371;
  public boolean field_4;
  public boolean field_5;
  public HashSet field_4;
  private int jdField_field_4_of_type_Int = -1;
  public boolean field_6;
  public long field_4;
  private int jdField_field_5_of_type_Int;
  private long jdField_field_5_of_type_Long;
  
  public class_16(class_371 paramclass_371)
  {
    this.jdField_field_4_of_type_Boolean = false;
    this.jdField_field_4_of_type_Class_371 = paramclass_371;
    this.jdField_field_4_of_type_JavaUtilHashSet = new HashSet();
    addObserver(paramclass_371.a27().a92());
  }
  
  public final void a11(class_16 paramclass_16)
  {
    Iterator localIterator = this.jdField_field_4_of_type_JavaUtilHashSet.iterator();
    while (localIterator.hasNext())
    {
      class_16 localclass_16;
      if ((localclass_16 = (class_16)localIterator.next()) != paramclass_16) {
        localclass_16.c2(false);
      }
    }
    if (!paramclass_16.jdField_field_5_of_type_Boolean) {
      paramclass_16.c2(true);
    }
  }
  
  public final void a2()
  {
    if (this.jdField_field_4_of_type_Int >= 0) {
      c2(this.jdField_field_4_of_type_Int == 1);
    }
    this.jdField_field_4_of_type_Int = -1;
    Iterator localIterator = this.jdField_field_4_of_type_JavaUtilHashSet.iterator();
    while (localIterator.hasNext()) {
      ((class_16)localIterator.next()).a2();
    }
  }
  
  public class_371 a6()
  {
    return this.jdField_field_4_of_type_Class_371;
  }
  
  public final long a5()
  {
    return this.jdField_field_4_of_type_Long;
  }
  
  protected final boolean b1()
  {
    synchronized (a6().b())
    {
      int i;
      if ((i = a6().b().size()) > 0)
      {
        ((class_12)a6().b().get(i - 1)).handleKeyEvent();
        return true;
      }
      return false;
    }
  }
  
  public void handleKeyEvent()
  {
    if ((!this.jdField_field_4_of_type_Boolean) && (!e1()))
    {
      Iterator localIterator = this.jdField_field_4_of_type_JavaUtilHashSet.iterator();
      while (localIterator.hasNext())
      {
        class_16 localclass_16;
        if ((!(localclass_16 = (class_16)localIterator.next()).jdField_field_4_of_type_Boolean) && (localclass_16.jdField_field_5_of_type_Boolean)) {
          localclass_16.handleKeyEvent();
        }
      }
    }
  }
  
  public void a12(class_939 paramclass_939)
  {
    if ((!this.jdField_field_4_of_type_Boolean) && (!e1()))
    {
      Iterator localIterator = this.jdField_field_4_of_type_JavaUtilHashSet.iterator();
      while (localIterator.hasNext())
      {
        class_16 localclass_16;
        if ((!(localclass_16 = (class_16)localIterator.next()).jdField_field_4_of_type_Boolean) && (localclass_16.jdField_field_5_of_type_Boolean)) {
          localclass_16.a12(paramclass_939);
        }
      }
    }
  }
  
  public final void a13(int paramInt)
  {
    this.jdField_field_5_of_type_Int = paramInt;
    this.jdField_field_5_of_type_Long = System.currentTimeMillis();
  }
  
  public final boolean c()
  {
    return this.jdField_field_5_of_type_Boolean;
  }
  
  public final boolean d1()
  {
    return this.jdField_field_4_of_type_Int >= 0;
  }
  
  public final boolean e1()
  {
    return System.currentTimeMillis() - this.jdField_field_5_of_type_Long < this.jdField_field_5_of_type_Int;
  }
  
  public final boolean f1()
  {
    return this.jdField_field_4_of_type_Boolean;
  }
  
  public final boolean g()
  {
    return this.field_6;
  }
  
  public void a14(boolean paramBoolean)
  {
    a6().a20().a124().a9(paramBoolean);
  }
  
  public void b2(boolean paramBoolean)
  {
    this.field_6 = paramBoolean;
    class_16 localclass_16 = null;
    Iterator localIterator = this.jdField_field_4_of_type_JavaUtilHashSet.iterator();
    while (localIterator.hasNext()) {
      (localclass_16 = (class_16)localIterator.next()).b2((paramBoolean) && (localclass_16.jdField_field_5_of_type_Boolean));
    }
  }
  
  public final void b3(int paramInt)
  {
    if (this.jdField_field_5_of_type_Boolean)
    {
      String str = "";
      for (int i = 0; i < paramInt; i++) {
        str = str + "->";
      }
      str = str + getClass().getSimpleName();
      class_971.field_98.add("|-- " + str);
      Iterator localIterator = this.jdField_field_4_of_type_JavaUtilHashSet.iterator();
      while (localIterator.hasNext()) {
        ((class_16)localIterator.next()).b3(paramInt + 1);
      }
    }
  }
  
  public void c2(boolean paramBoolean)
  {
    int i = paramBoolean != this.jdField_field_5_of_type_Boolean ? 1 : 0;
    this.jdField_field_5_of_type_Boolean = paramBoolean;
    if (i != 0)
    {
      b2(paramBoolean);
      setChanged();
      notifyObservers("ON_SWITCH");
    }
  }
  
  public synchronized void deleteObserver(Observer paramObserver)
  {
    super.deleteObserver(paramObserver);
  }
  
  public synchronized void deleteObservers()
  {
    super.deleteObservers();
  }
  
  public final void d2(boolean paramBoolean)
  {
    this.jdField_field_4_of_type_Int = (paramBoolean ? 1 : 0);
  }
  
  public final void e2(boolean paramBoolean)
  {
    if (paramBoolean != this.jdField_field_4_of_type_Boolean)
    {
      a14(paramBoolean);
      this.jdField_field_4_of_type_Boolean = paramBoolean;
    }
    if (!paramBoolean) {
      this.jdField_field_4_of_type_Long = System.currentTimeMillis();
    }
  }
  
  public void a15(class_941 paramclass_941)
  {
    if (!this.jdField_field_4_of_type_Boolean)
    {
      Iterator localIterator = this.jdField_field_4_of_type_JavaUtilHashSet.iterator();
      while (localIterator.hasNext())
      {
        class_16 localclass_16;
        if ((!(localclass_16 = (class_16)localIterator.next()).jdField_field_4_of_type_Boolean) && (localclass_16.jdField_field_5_of_type_Boolean)) {
          localclass_16.a15(paramclass_941);
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_16
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */