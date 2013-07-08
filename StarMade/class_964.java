import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.client.ClientState;

public final class class_964
  extends class_1363
  implements List
{
  private static int jdField_field_89_of_type_Int = 1;
  private int field_90;
  private int field_92;
  private ArrayList jdField_field_89_of_type_JavaUtilArrayList = new ArrayList();
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_964(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final boolean a144(class_959 paramclass_959)
  {
    paramclass_959 = this.jdField_field_89_of_type_JavaUtilArrayList.add(paramclass_959);
    f();
    return paramclass_959;
  }
  
  public final boolean addAll(Collection paramCollection)
  {
    paramCollection = this.jdField_field_89_of_type_JavaUtilArrayList.addAll(paramCollection);
    f();
    return paramCollection;
  }
  
  public final boolean addAll(int paramInt, Collection paramCollection)
  {
    paramInt = this.jdField_field_89_of_type_JavaUtilArrayList.addAll(paramInt, paramCollection);
    f();
    return paramInt;
  }
  
  public final void a2() {}
  
  public final void clear()
  {
    for (int i = 0; i < this.jdField_field_89_of_type_JavaUtilArrayList.size(); i++) {
      try
      {
        if (this.jdField_field_89_of_type_JavaUtilArrayList.get(i) != null)
        {
          if (((class_959)this.jdField_field_89_of_type_JavaUtilArrayList.get(i)).a139() != null) {
            ((class_959)this.jdField_field_89_of_type_JavaUtilArrayList.get(i)).a139().a2();
          }
          if (((class_959)this.jdField_field_89_of_type_JavaUtilArrayList.get(i)).b18() != null) {
            ((class_959)this.jdField_field_89_of_type_JavaUtilArrayList.get(i)).b18().a2();
          }
        }
      }
      catch (ErrorDialogException localErrorDialogException)
      {
        localErrorDialogException;
      }
    }
    this.jdField_field_89_of_type_JavaUtilArrayList.clear();
    f();
  }
  
  public final boolean contains(Object paramObject)
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.contains(paramObject);
  }
  
  public final boolean containsAll(Collection paramCollection)
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.containsAll(paramCollection);
  }
  
  public final void e()
  {
    Iterator localIterator = this.jdField_field_89_of_type_JavaUtilArrayList.iterator();
    while (localIterator.hasNext()) {
      ((class_959)localIterator.next()).a29(false);
    }
  }
  
  protected final void d() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    this.field_95 = false;
    if (a154() != null)
    {
      this.field_95 = ((class_1363)a154()).a_();
      a_();
    }
    GlUtil.d1();
    r();
    class_964 localclass_964 = this;
    for (int i = 0; i < localclass_964.jdField_field_89_of_type_JavaUtilArrayList.size(); i++)
    {
      class_959 localclass_959;
      (localclass_959 = (class_959)localclass_964.jdField_field_89_of_type_JavaUtilArrayList.get(i)).b19(localclass_964.field_89);
      localclass_959.b();
      if (jdField_field_89_of_type_Int == 0) {
        GlUtil.c4(localclass_959.jdField_field_89_of_type_JavaxVecmathVector3f.field_615 * localclass_959.b1(), 0.0F, 0.0F);
      } else {
        GlUtil.c4(0.0F, localclass_959.jdField_field_89_of_type_JavaxVecmathVector3f.field_616 * localclass_959.a3(), 0.0F);
      }
    }
    GlUtil.c2();
  }
  
  public final class_959 a145(int paramInt)
  {
    return (class_959)this.jdField_field_89_of_type_JavaUtilArrayList.get(paramInt);
  }
  
  public final float a3()
  {
    return this.field_90;
  }
  
  public final float b1()
  {
    return this.field_92;
  }
  
  public final int indexOf(Object paramObject)
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.indexOf(paramObject);
  }
  
  public final boolean isEmpty()
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.isEmpty();
  }
  
  public final Iterator iterator()
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.iterator();
  }
  
  public final int lastIndexOf(Object paramObject)
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.lastIndexOf(paramObject);
  }
  
  public final ListIterator listIterator()
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.listIterator();
  }
  
  public final ListIterator listIterator(int paramInt)
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.listIterator(paramInt);
  }
  
  public final void c()
  {
    Iterator localIterator = this.jdField_field_89_of_type_JavaUtilArrayList.iterator();
    while (localIterator.hasNext()) {
      ((class_959)localIterator.next()).c();
    }
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  public final class_959 b20(int paramInt)
  {
    paramInt = (class_959)this.jdField_field_89_of_type_JavaUtilArrayList.remove(paramInt);
    f();
    return paramInt;
  }
  
  public final boolean remove(Object paramObject)
  {
    paramObject = this.jdField_field_89_of_type_JavaUtilArrayList.remove(paramObject);
    f();
    return paramObject;
  }
  
  public final boolean removeAll(Collection paramCollection)
  {
    paramCollection = this.jdField_field_89_of_type_JavaUtilArrayList.removeAll(paramCollection);
    f();
    return paramCollection;
  }
  
  public final boolean retainAll(Collection paramCollection)
  {
    paramCollection = this.jdField_field_89_of_type_JavaUtilArrayList.retainAll(paramCollection);
    f();
    return paramCollection;
  }
  
  public final int size()
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.size();
  }
  
  public final List subList(int paramInt1, int paramInt2)
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.subList(paramInt1, paramInt2);
  }
  
  public final Object[] toArray()
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.toArray();
  }
  
  public final Object[] toArray(Object[] paramArrayOfObject)
  {
    return this.jdField_field_89_of_type_JavaUtilArrayList.toArray(paramArrayOfObject);
  }
  
  public final void a12(class_941 paramclass_941)
  {
    for (int i = 0; i < this.jdField_field_89_of_type_JavaUtilArrayList.size(); i++) {
      ((class_959)this.jdField_field_89_of_type_JavaUtilArrayList.get(i)).a12(paramclass_941);
    }
  }
  
  public final void f()
  {
    this.field_90 = 0;
    this.field_92 = 0;
    this.jdField_field_89_of_type_JavaUtilList.clear();
    Iterator localIterator = this.jdField_field_89_of_type_JavaUtilArrayList.iterator();
    while (localIterator.hasNext())
    {
      class_959 localclass_959;
      if (((localclass_959 = (class_959)localIterator.next()).a139() instanceof class_964)) {
        ((class_964)localclass_959.a139()).f();
      }
      if ((localclass_959.a139() instanceof class_961)) {
        ((class_961)localclass_959.a139()).a140().f();
      }
      if (jdField_field_89_of_type_Int == 0)
      {
        this.field_92 = ((int)(this.field_92 + localclass_959.b1()));
        this.field_90 = ((int)localclass_959.a3());
      }
      else
      {
        this.field_92 = ((int)localclass_959.b1());
        this.field_90 = ((int)(this.field_90 + localclass_959.a3()));
      }
      a9(localclass_959);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_964
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */