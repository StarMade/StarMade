import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_185
  extends class_972
  implements class_1418
{
  private class_936 jdField_field_89_of_type_Class_936;
  private class_936 jdField_field_90_of_type_Class_936;
  private class_1380 jdField_field_90_of_type_Class_1380;
  private class_1380 jdField_field_92_of_type_Class_1380;
  private class_1363 jdField_field_89_of_type_Class_1363;
  private int jdField_field_90_of_type_Int;
  private short jdField_field_89_of_type_Short;
  private long jdField_field_89_of_type_Long = -1L;
  private int jdField_field_92_of_type_Int;
  private int jdField_field_93_of_type_Int;
  private int field_94;
  private short jdField_field_90_of_type_Short = 0;
  private boolean jdField_field_90_of_type_Boolean;
  private int field_95;
  private boolean jdField_field_92_of_type_Boolean;
  
  public class_185(ClientState paramClientState, boolean paramBoolean, class_1363 paramclass_1363)
  {
    super(class_969.a2().a5("build-icons-00-16x16-gui-"), paramClientState);
    this.field_96 = true;
    if (paramBoolean) {
      a141(this);
    }
    this.jdField_field_89_of_type_Class_1363 = paramclass_1363;
    this.jdField_field_92_of_type_Class_1380 = class_969.a2().a5("build-icons-00-16x16-gui-");
    this.jdField_field_90_of_type_Class_1380 = class_969.a2().a5("build-icons-01-16x16-gui-");
    this.jdField_field_89_of_type_Class_936 = new class_936(paramClientState, "unknown", this);
    this.jdField_field_90_of_type_Class_936 = new class_936(paramClientState, "Drop", this);
    this.jdField_field_90_of_type_Class_936.b9().set(0.8F, 0.0F, 0.0F);
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    paramclass_1363 = a24().getMouseEvents().iterator();
    while (paramclass_1363.hasNext()) {
      if (((paramclass_939 = (class_939)paramclass_1363.next()).jdField_field_1163_of_type_Int == 0) && (paramclass_939.jdField_field_1163_of_type_Boolean))
      {
        if (((a24().getDragging() == null) || (!a24().getDragging().b())) && (a_()) && (a68(false) > 0))
        {
          this.jdField_field_89_of_type_Long = System.currentTimeMillis();
          a24().setDragging(this);
          this.jdField_field_93_of_type_Int = ((int)this.field_90.field_615);
          this.field_94 = ((int)this.field_90.field_616);
        }
      }
      else
      {
        if ((paramclass_939.jdField_field_1163_of_type_Int == 1) && (paramclass_939.jdField_field_1163_of_type_Boolean))
        {
          paramclass_939 = this;
          synchronized (((class_371)a24()).b())
          {
            if (paramclass_939.jdField_field_89_of_type_Short != 0) {
              ((class_371)paramclass_939.a24()).b().add(new class_463((class_371)paramclass_939.a24(), paramclass_939.jdField_field_89_of_type_Short, "Split Inventory Slot", "How many " + ElementKeyMap.getInfo(paramclass_939.jdField_field_89_of_type_Short).getName() + " do you want to take?", paramclass_939));
            }
          }
        }
        this.jdField_field_89_of_type_Long = -1L;
      }
    }
    g();
  }
  
  public final boolean a67(class_939 paramclass_939)
  {
    return ((paramclass_939.jdField_field_1163_of_type_Int == 0) && (!paramclass_939.jdField_field_1163_of_type_Boolean)) || ((paramclass_939.jdField_field_1163_of_type_Int == 0) && (paramclass_939.jdField_field_1163_of_type_Boolean) && (a24().getDragging().b()));
  }
  
  private void g()
  {
    if (a_())
    {
      Iterator localIterator = a24().getMouseEvents().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (class_939)localIterator.next();
        if ((a24().getDragging() != null) && (a24().getDragging().a2((class_939)localObject)))
        {
          System.err.println("CHECKING " + this + " " + hashCode() + " MOUSE NO MORE GRABBED");
          if ((a71(a24().getDragging())) && (a24().getDragging() != this))
          {
            if (System.currentTimeMillis() - a24().getDragging().a4() > 50L)
            {
              System.err.println("NOW DROPPING " + a24().getDragging());
              class_185 localclass_185 = (class_185)a24().getDragging();
              localObject = this;
              System.err.println("SWITCHING SLOTS: " + ((class_185)localObject).jdField_field_90_of_type_Int + ", " + localclass_185.jdField_field_90_of_type_Int);
              class_639 localclass_639;
              if ((localclass_639 = localclass_185.a52()) == ((class_185)localObject).a52())
              {
                if ((!jdField_field_93_of_type_Boolean) && (((class_185)localObject).jdField_field_90_of_type_Int == localclass_185.jdField_field_90_of_type_Int)) {
                  throw new AssertionError();
                }
                ((class_185)localObject).a52().a52(((class_185)localObject).jdField_field_90_of_type_Int, localclass_185.jdField_field_90_of_type_Int, localclass_185.a68(true));
              }
              else
              {
                System.err.println("Inventory Switched: " + ((class_185)localObject).a52() + " -> " + localclass_639);
                ((class_185)localObject).a52().a53(((class_185)localObject).jdField_field_90_of_type_Int, localclass_185.jdField_field_90_of_type_Int, localclass_639, localclass_185.a68(true));
              }
              localclass_185.jdField_field_90_of_type_Boolean = false;
              localclass_185.field_95 = 0;
            }
            else
            {
              System.err.println("NO DROP: time dragged to short");
            }
            a24().setDragging(null);
          }
          if (!a71(a24().getDragging())) {
            System.err.println("NO DROP: not a target: " + this);
          }
          if (a24().getDragging() == this)
          {
            System.err.println("NO DROP: dragging and target are the same. this: " + this.jdField_field_89_of_type_Short + "; dragging " + a24().getDragging().a3());
            if (a24().getDragging().b())
            {
              a24().getDragging().a5(false);
              a24().setDragging(null);
            }
          }
        }
      }
    }
  }
  
  public final void b()
  {
    super.b();
  }
  
  public final void e()
  {
    if (this.jdField_field_89_of_type_Short > 0)
    {
      GlUtil.d1();
      if (this.jdField_field_89_of_type_Short != this.jdField_field_90_of_type_Short)
      {
        String str = ElementKeyMap.getInfo(this.jdField_field_89_of_type_Short).getName();
        this.jdField_field_89_of_type_Class_936.a17(str);
        this.jdField_field_90_of_type_Short = this.jdField_field_89_of_type_Short;
      }
      this.jdField_field_89_of_type_Class_936.b();
      GlUtil.c2();
      if (this.jdField_field_92_of_type_Boolean)
      {
        GlUtil.d1();
        this.jdField_field_90_of_type_Class_936.e();
        GlUtil.c2();
        this.jdField_field_92_of_type_Boolean = false;
      }
    }
  }
  
  public final void a(float paramFloat)
  {
    if (this.jdField_field_89_of_type_Short > 0)
    {
      GlUtil.d1();
      if (this.jdField_field_89_of_type_Short != this.jdField_field_90_of_type_Short)
      {
        String str = ElementKeyMap.getInfo(this.jdField_field_89_of_type_Short).getName();
        this.jdField_field_89_of_type_Class_936.a17(str);
        this.jdField_field_90_of_type_Short = this.jdField_field_89_of_type_Short;
      }
      r();
      this.jdField_field_89_of_type_Class_936.a29(false);
      this.jdField_field_89_of_type_Class_936.a83().field_616 = 54.0F;
      this.jdField_field_89_of_type_Class_936.a83().field_615 = 0.0F;
      if (paramFloat < 0.15F) {
        this.jdField_field_89_of_type_Class_936.a(paramFloat / 0.15F);
      } else if (paramFloat > 0.8F) {
        this.jdField_field_89_of_type_Class_936.a((1.0F - paramFloat) / 0.2F);
      }
      this.jdField_field_89_of_type_Class_936.b();
      this.jdField_field_89_of_type_Class_936.a29(true);
      GlUtil.c2();
      this.jdField_field_89_of_type_Class_936.a(-1.0F);
    }
  }
  
  public final int a68(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.jdField_field_90_of_type_Boolean)) {
      return this.field_95;
    }
    if (a24().getDragging() == this) {
      return this.jdField_field_92_of_type_Int - this.field_95;
    }
    return this.jdField_field_92_of_type_Int;
  }
  
  public final int a57()
  {
    return this.jdField_field_93_of_type_Int;
  }
  
  public final int b12()
  {
    return this.field_94;
  }
  
  private class_639 a52()
  {
    if ((this.jdField_field_89_of_type_Class_1363 instanceof class_161)) {
      return ((class_161)this.jdField_field_89_of_type_Class_1363).a52();
    }
    return ((class_371)a24()).a20().getInventory(null);
  }
  
  public final long a69()
  {
    return this.jdField_field_89_of_type_Long;
  }
  
  public final short a70()
  {
    return this.jdField_field_89_of_type_Short;
  }
  
  public final boolean a_()
  {
    return (super.a_()) && ((this.jdField_field_89_of_type_Class_1363 == null) || (this.jdField_field_89_of_type_Class_1363.a_()));
  }
  
  public final boolean a4()
  {
    return !((class_371)a24()).b().isEmpty();
  }
  
  public final boolean b3()
  {
    return this.jdField_field_90_of_type_Boolean;
  }
  
  private boolean a71(class_1418 paramclass_1418)
  {
    return getClass().isInstance(paramclass_1418);
  }
  
  public final void a72(int paramInt)
  {
    this.jdField_field_92_of_type_Int = paramInt;
  }
  
  public final void b13(int paramInt)
  {
    this.field_95 = paramInt;
  }
  
  public final void c4(int paramInt)
  {
    this.jdField_field_93_of_type_Int = paramInt;
  }
  
  public final void d4(int paramInt)
  {
    this.field_94 = paramInt;
  }
  
  public final void e4(int paramInt)
  {
    if (paramInt == 0)
    {
      this.field_89 = this.jdField_field_92_of_type_Class_1380;
      return;
    }
    this.field_89 = this.jdField_field_90_of_type_Class_1380;
  }
  
  public final void f4(int paramInt)
  {
    this.jdField_field_90_of_type_Int = paramInt;
  }
  
  public final void a29(boolean paramBoolean)
  {
    this.jdField_field_90_of_type_Boolean = paramBoolean;
  }
  
  public final void a73(long paramLong)
  {
    this.jdField_field_89_of_type_Long = paramLong;
  }
  
  public final void a74(short paramShort)
  {
    this.jdField_field_89_of_type_Short = paramShort;
  }
  
  public final void f()
  {
    this.jdField_field_92_of_type_Boolean = true;
  }
  
  public final int c5()
  {
    return this.jdField_field_90_of_type_Int;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_185
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */