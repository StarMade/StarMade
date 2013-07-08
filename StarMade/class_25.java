import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public final class class_25
  extends class_16
{
  private class_18 field_4;
  public class_467 field_4;
  public class_477 field_4;
  public class_26 field_4;
  public class_24 field_4;
  public boolean field_7;
  
  public class_25(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void b()
  {
    if (class_843.b3()) {
      return;
    }
    boolean bool = this.jdField_field_4_of_type_Class_26.field_5;
    this.jdField_field_4_of_type_Class_467.c2(bool);
    if ((!bool) && (this.jdField_field_4_of_type_Class_477.field_5)) {
      this.jdField_field_4_of_type_Class_477.c2(false);
    }
    this.jdField_field_4_of_type_Class_26.c2(!bool);
    if (!this.jdField_field_4_of_type_Class_26.field_5) {
      synchronized (a6().b())
      {
        for (int i = 0; i < a6().b().size(); i++) {
          if (((class_12)a6().b().get(i) instanceof class_2))
          {
            ((class_12)a6().b().get(i)).d();
            break;
          }
        }
        return;
      }
    }
  }
  
  public final class_24 a17()
  {
    return this.jdField_field_4_of_type_Class_24;
  }
  
  public final class_467 a18()
  {
    return this.jdField_field_4_of_type_Class_467;
  }
  
  public final class_26 a19()
  {
    return this.jdField_field_4_of_type_Class_26;
  }
  
  public final void handleKeyEvent()
  {
    super.handleKeyEvent();
    if (Keyboard.isKeyDown(15)) {
      this.jdField_field_4_of_type_Class_18.handleKeyEvent();
    }
    if (!this.jdField_field_4_of_type_Class_467.field_5) {
      b1();
    }
    if (Keyboard.getEventKeyState())
    {
      if (Keyboard.getEventKey() == class_367.field_753.a5()) {
        class_1046.field_1307 = !class_1046.field_1307;
      } else if ((Keyboard.getEventKey() == class_367.field_760.a5()) && (!Keyboard.isKeyDown(15))) {
        class_227.field_92 = true;
      } else if ((Keyboard.getEventKey() == class_367.field_761.a5()) && (!Keyboard.isKeyDown(15))) {
        class_227.field_93 = true;
      }
      boolean bool;
      switch (Keyboard.getEventKey())
      {
      case 59: 
        bool = this.jdField_field_4_of_type_Class_24.field_5;
        this.jdField_field_4_of_type_Class_24.c2(!bool);
        return;
      case 37: 
        if ((a6().a4().a5() != null) && (a6().b().isEmpty()))
        {
          a6().a4().a5().f();
          return;
        }
        break;
      case 210: 
        if ((a6().a4().a5() != null) && (a6().b().isEmpty()))
        {
          a6().a4().a5().d();
          return;
        }
        break;
      case 199: 
        if ((a6().a4().a5() != null) && (a6().b().isEmpty()))
        {
          a6().a4().a5().e();
          return;
        }
        break;
      case 211: 
        if ((a6().a4().a5() != null) && (a6().b().isEmpty()))
        {
          a6().a4().a5().b();
          return;
        }
        break;
      case 207: 
        if ((a6().a4().a5() != null) && (a6().b().isEmpty()))
        {
          a6().a4().a5().c();
          return;
        }
        break;
      case 1: 
        if (class_949.field_1245.b1())
        {
          System.err.println("ESCAPE: EXIT: " + class_949.field_1245.b1());
          class_933.a3();
          return;
        }
        if ((this.jdField_field_4_of_type_Class_467.field_4.field_4.jdField_field_4_of_type_Boolean) || (!this.jdField_field_4_of_type_Class_467.field_4.field_4.field_5))
        {
          System.err.println("DEACTIVATING ALL");
          this.jdField_field_4_of_type_Class_467.field_4.b();
          return;
        }
        b();
        return;
      case 78: 
        bool = this.jdField_field_4_of_type_Class_477.field_5;
        this.jdField_field_4_of_type_Class_467.c2(bool);
        if ((!bool) && (this.jdField_field_4_of_type_Class_26.field_5)) {
          this.jdField_field_4_of_type_Class_26.c2(false);
        }
        this.jdField_field_4_of_type_Class_477.c2(!bool);
        if (!this.jdField_field_4_of_type_Class_477.field_5) {
          synchronized (a6().b())
          {
            for (int i = 0; i < a6().b().size(); i++) {
              if (((class_12)a6().b().get(i) instanceof class_4))
              {
                ((class_12)a6().b().get(i)).d();
                break;
              }
            }
            return;
          }
        }
        break;
      }
    }
  }
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
    if ((Mouse.getEventButtonState()) && (class_1046.field_1307)) {
      class_1046.field_1307 = false;
    }
  }
  
  public final void c1()
  {
    this.jdField_field_4_of_type_Class_18 = new class_18(a6());
    this.jdField_field_4_of_type_Class_467 = new class_467(a6());
    this.jdField_field_4_of_type_Class_477 = new class_477(a6());
    this.jdField_field_4_of_type_Class_26 = new class_26(a6());
    this.jdField_field_4_of_type_Class_24 = new class_24(a6());
    this.jdField_field_4_of_type_JavaUtilHashSet.add(this.jdField_field_4_of_type_Class_18);
    this.jdField_field_4_of_type_JavaUtilHashSet.add(this.jdField_field_4_of_type_Class_467);
    this.jdField_field_4_of_type_JavaUtilHashSet.add(this.jdField_field_4_of_type_Class_477);
    this.jdField_field_4_of_type_JavaUtilHashSet.add(this.jdField_field_4_of_type_Class_26);
    this.jdField_field_4_of_type_JavaUtilHashSet.add(this.jdField_field_4_of_type_Class_24);
    this.jdField_field_4_of_type_Class_467.c2(true);
    this.field_7 = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_25
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */