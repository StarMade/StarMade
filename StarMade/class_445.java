import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;

public final class class_445
  extends class_16
{
  public class_443 field_4;
  public class_469 field_4;
  public class_303 field_4;
  public class_334 field_4;
  public class_338 field_4;
  public class_475 field_4;
  public class_19 field_4;
  public class_423 field_4;
  public class_427 field_4;
  private final ObjectArrayList field_4;
  
  public class_445(class_371 paramclass_371)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
    paramclass_371 = this;
    this.jdField_field_4_of_type_Class_443 = new class_443(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_469 = new class_469(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_303 = new class_303(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_334 = new class_334(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_338 = new class_338(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_475 = new class_475(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_19 = new class_19(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_423 = new class_423(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_427 = new class_427(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_443);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_469);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_303);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_334);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_338);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_19);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_475);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_423);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_427);
    paramclass_371.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_371.jdField_field_4_of_type_Class_469);
    paramclass_371.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_371.jdField_field_4_of_type_Class_303);
    paramclass_371.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_371.jdField_field_4_of_type_Class_334);
    paramclass_371.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_371.jdField_field_4_of_type_Class_338);
    paramclass_371.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_371.jdField_field_4_of_type_Class_19);
    paramclass_371.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_371.jdField_field_4_of_type_Class_475);
    paramclass_371.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_371.jdField_field_4_of_type_Class_423);
    paramclass_371.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramclass_371.jdField_field_4_of_type_Class_427);
  }
  
  public final void a16(class_796 paramclass_796)
  {
    if (a6().a25() != null)
    {
      if (a6().a25().a87().a184() != null)
      {
        this.jdField_field_4_of_type_Class_19.a16(a6().a25().a87().a184());
      }
      else
      {
        this.jdField_field_4_of_type_Class_19.a16(null);
        a6().a4().b1("No AI context available\nEither use this in a ship\nwith an AI Module or\nactivate an AI Module externally");
      }
    }
    else {
      this.jdField_field_4_of_type_Class_19.a16(paramclass_796);
    }
    if (this.jdField_field_4_of_type_Class_469.field_5) {
      this.jdField_field_4_of_type_Class_469.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_423.field_5) {
      this.jdField_field_4_of_type_Class_423.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_427.field_5) {
      this.jdField_field_4_of_type_Class_427.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_303.field_5) {
      this.jdField_field_4_of_type_Class_303.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_334.field_5) {
      this.jdField_field_4_of_type_Class_334.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_338.field_5) {
      this.jdField_field_4_of_type_Class_338.c2(false);
    }
    paramclass_796 = !this.jdField_field_4_of_type_Class_19.field_5 ? 1 : 0;
    this.jdField_field_4_of_type_Class_19.d2(paramclass_796);
  }
  
  public final class_19 a57()
  {
    return this.jdField_field_4_of_type_Class_19;
  }
  
  public final class_469 a58()
  {
    return this.jdField_field_4_of_type_Class_469;
  }
  
  public final class_338 a59()
  {
    return this.jdField_field_4_of_type_Class_338;
  }
  
  public final class_443 a60()
  {
    return this.jdField_field_4_of_type_Class_443;
  }
  
  public final class_303 a61()
  {
    return this.jdField_field_4_of_type_Class_303;
  }
  
  public final class_334 a62()
  {
    return this.jdField_field_4_of_type_Class_334;
  }
  
  public final void b()
  {
    Iterator localIterator = this.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.iterator();
    while (localIterator.hasNext()) {
      ((class_16)localIterator.next()).c2(false);
    }
    this.jdField_field_4_of_type_Class_443.a13(600);
  }
  
  public final void handleKeyEvent()
  {
    if ((Keyboard.getEventKeyState()) && (a6().b().isEmpty()))
    {
      if ((this.jdField_field_4_of_type_Class_469.field_5) && (Keyboard.getEventKey() == class_367.field_733.a5())) {
        a63(null);
      }
      if (Keyboard.getEventKey() == class_367.field_745.a5())
      {
        a63(null);
      }
      else if (Keyboard.getEventKey() == class_367.field_744.a5())
      {
        class_445 localclass_445 = this;
        boolean bool2 = !this.jdField_field_4_of_type_Class_303.field_5;
        if (!localclass_445.a6().d2())
        {
          localclass_445.a6().a4().d1("ERROR: You are not near any shop");
        }
        else
        {
          if (localclass_445.jdField_field_4_of_type_Class_469.field_5) {
            localclass_445.jdField_field_4_of_type_Class_469.c2(false);
          }
          if (localclass_445.jdField_field_4_of_type_Class_475.field_5) {
            localclass_445.jdField_field_4_of_type_Class_475.c2(false);
          }
          if (localclass_445.jdField_field_4_of_type_Class_423.field_5) {
            localclass_445.jdField_field_4_of_type_Class_423.c2(false);
          }
          if (localclass_445.jdField_field_4_of_type_Class_334.field_5) {
            localclass_445.jdField_field_4_of_type_Class_334.c2(false);
          }
          if (localclass_445.jdField_field_4_of_type_Class_338.field_5) {
            localclass_445.jdField_field_4_of_type_Class_338.c2(false);
          }
          if (localclass_445.jdField_field_4_of_type_Class_427.field_5) {
            localclass_445.jdField_field_4_of_type_Class_427.c2(false);
          }
          if (localclass_445.jdField_field_4_of_type_Class_19.field_5) {
            localclass_445.jdField_field_4_of_type_Class_19.c2(false);
          }
          localclass_445.jdField_field_4_of_type_Class_303.d2(bool2);
        }
      }
      else if (Keyboard.getEventKey() == class_367.field_746.a5())
      {
        if (!this.jdField_field_4_of_type_Class_443.a51().field_5)
        {
          a6().a4().b1("ERROR: Weapon Menu only available\ninside ship");
          return;
        }
        if (this.jdField_field_4_of_type_Class_423.field_5) {
          this.jdField_field_4_of_type_Class_423.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_427.field_5) {
          this.jdField_field_4_of_type_Class_427.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_469.field_5) {
          this.jdField_field_4_of_type_Class_469.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_303.field_5) {
          this.jdField_field_4_of_type_Class_303.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_475.field_5) {
          this.jdField_field_4_of_type_Class_475.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_338.field_5) {
          this.jdField_field_4_of_type_Class_338.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_19.field_5) {
          this.jdField_field_4_of_type_Class_19.c2(false);
        }
        bool1 = !this.jdField_field_4_of_type_Class_334.field_5;
        this.jdField_field_4_of_type_Class_334.d2(bool1);
      }
      else if (Keyboard.getEventKey() == class_367.field_747.a5())
      {
        if (this.jdField_field_4_of_type_Class_469.field_5) {
          this.jdField_field_4_of_type_Class_469.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_303.field_5) {
          this.jdField_field_4_of_type_Class_303.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_423.field_5) {
          this.jdField_field_4_of_type_Class_423.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_427.field_5) {
          this.jdField_field_4_of_type_Class_427.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_334.field_5) {
          this.jdField_field_4_of_type_Class_334.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_475.field_5) {
          this.jdField_field_4_of_type_Class_475.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_19.field_5) {
          this.jdField_field_4_of_type_Class_19.c2(false);
        }
        bool1 = !this.jdField_field_4_of_type_Class_338.field_5;
        this.jdField_field_4_of_type_Class_338.d2(bool1);
      }
      else if (Keyboard.getEventKey() == class_367.field_763.a5())
      {
        if (this.jdField_field_4_of_type_Class_469.field_5) {
          this.jdField_field_4_of_type_Class_469.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_303.field_5) {
          this.jdField_field_4_of_type_Class_303.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_423.field_5) {
          this.jdField_field_4_of_type_Class_423.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_427.field_5) {
          this.jdField_field_4_of_type_Class_427.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_334.field_5) {
          this.jdField_field_4_of_type_Class_334.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_338.field_5) {
          this.jdField_field_4_of_type_Class_338.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_19.field_5) {
          this.jdField_field_4_of_type_Class_19.c2(false);
        }
        bool1 = !this.jdField_field_4_of_type_Class_475.field_5;
        System.err.println("ACTIVATE MAP " + bool1);
        this.jdField_field_4_of_type_Class_475.d2(bool1);
      }
      else if (Keyboard.getEventKey() == class_367.field_748.a5())
      {
        a16(null);
      }
      else if (Keyboard.getEventKey() == class_367.field_762.a5())
      {
        if (this.jdField_field_4_of_type_Class_469.field_5) {
          this.jdField_field_4_of_type_Class_469.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_303.field_5) {
          this.jdField_field_4_of_type_Class_303.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_338.field_5) {
          this.jdField_field_4_of_type_Class_338.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_427.field_5) {
          this.jdField_field_4_of_type_Class_427.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_475.field_5) {
          this.jdField_field_4_of_type_Class_475.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_334.field_5) {
          this.jdField_field_4_of_type_Class_334.c2(false);
        }
        if (this.jdField_field_4_of_type_Class_19.field_5) {
          this.jdField_field_4_of_type_Class_19.c2(false);
        }
        bool1 = !this.jdField_field_4_of_type_Class_423.field_5;
        this.jdField_field_4_of_type_Class_423.d2(bool1);
      }
    }
    boolean bool1 = false;
    Iterator localIterator = this.jdField_field_4_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.iterator();
    while (localIterator.hasNext())
    {
      class_16 localclass_16 = (class_16)localIterator.next();
      bool1 = (bool1) || (localclass_16.field_5);
    }
    if (this.jdField_field_4_of_type_Class_443.jdField_field_4_of_type_Boolean != bool1) {
      this.jdField_field_4_of_type_Class_443.e2(bool1);
    }
    if (a6().b().isEmpty()) {
      super.handleKeyEvent();
    }
  }
  
  public final void a63(class_639 paramclass_639)
  {
    if (this.jdField_field_4_of_type_Class_303.field_5) {
      this.jdField_field_4_of_type_Class_303.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_334.field_5) {
      this.jdField_field_4_of_type_Class_334.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_19.field_5) {
      this.jdField_field_4_of_type_Class_19.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_338.field_5) {
      this.jdField_field_4_of_type_Class_338.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_423.field_5) {
      this.jdField_field_4_of_type_Class_423.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_475.field_5) {
      this.jdField_field_4_of_type_Class_475.c2(false);
    }
    if (this.jdField_field_4_of_type_Class_427.field_5) {
      this.jdField_field_4_of_type_Class_427.c2(false);
    }
    boolean bool;
    if ((bool = !this.jdField_field_4_of_type_Class_469.field_5 ? 1 : 0) != 0)
    {
      class_639 localclass_639 = paramclass_639;
      paramclass_639 = this.jdField_field_4_of_type_Class_469;
      System.err.println("SECOND INVENTORY SET TO " + localclass_639);
      paramclass_639.field_4 = localclass_639;
    }
    this.jdField_field_4_of_type_Class_469.d2(bool);
  }
  
  public final void b2(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.jdField_field_4_of_type_Class_338.c2(false);
      this.jdField_field_4_of_type_Class_303.c2(false);
      this.jdField_field_4_of_type_Class_334.c2(false);
      this.jdField_field_4_of_type_Class_469.c2(false);
      this.jdField_field_4_of_type_Class_475.c2(false);
      this.jdField_field_4_of_type_Class_443.d2(true);
    }
    else
    {
      this.jdField_field_4_of_type_Class_469.c2(false);
      this.jdField_field_4_of_type_Class_338.c2(false);
      this.jdField_field_4_of_type_Class_303.c2(false);
      this.jdField_field_4_of_type_Class_334.c2(false);
      this.jdField_field_4_of_type_Class_475.c2(false);
    }
    super.b2(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    boolean bool = (this.jdField_field_4_of_type_Class_469.field_5) || (this.jdField_field_4_of_type_Class_303.field_5) || (this.jdField_field_4_of_type_Class_334.field_5) || (this.jdField_field_4_of_type_Class_423.field_5) || (this.jdField_field_4_of_type_Class_19.field_5) || (this.jdField_field_4_of_type_Class_427.field_5);
    if (this.jdField_field_4_of_type_Class_443.jdField_field_4_of_type_Boolean != bool) {
      this.jdField_field_4_of_type_Class_443.e2(bool);
    }
    super.a15(paramclass_941);
  }
  
  public final class_423 a64()
  {
    return this.jdField_field_4_of_type_Class_423;
  }
  
  public final class_427 a65()
  {
    return this.jdField_field_4_of_type_Class_427;
  }
  
  public final class_475 a66()
  {
    return this.jdField_field_4_of_type_Class_475;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_445
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */