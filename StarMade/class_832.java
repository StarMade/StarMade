import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.schema.schine.network.client.ClientState;

public final class class_832
  extends class_972
  implements class_1418
{
  private long field_89 = -1L;
  private boolean field_90;
  
  public class_832(class_1380 paramclass_1380, ClientState paramClientState)
  {
    super(paramclass_1380, paramClientState);
    this.field_96 = true;
    a141(this);
    new class_936(paramClientState, "unknown", this);
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    paramclass_1363 = a24().getMouseEvents().iterator();
    while (paramclass_1363.hasNext()) {
      if (((paramclass_939 = (class_939)paramclass_1363.next()).jdField_field_1163_of_type_Int == 0) && (paramclass_939.jdField_field_1163_of_type_Boolean))
      {
        if (a_())
        {
          this.field_89 = System.currentTimeMillis();
          a24().setDragging(this);
        }
      }
      else {
        this.field_89 = -1L;
      }
    }
    paramclass_1363 = this;
    if (a_())
    {
      paramclass_939 = paramclass_1363.a24().getMouseEvents().iterator();
      while (paramclass_939.hasNext())
      {
        Object localObject = (class_939)paramclass_939.next();
        if ((paramclass_1363.a24().getDragging() != null) && (paramclass_1363.a24().getDragging().a2((class_939)localObject)))
        {
          System.err.println("CHECKING " + paramclass_1363 + " MOUSE NO MORE GRABBED");
          if ((paramclass_1363.a71(paramclass_1363.a24().getDragging())) && (paramclass_1363.a24().getDragging() != paramclass_1363))
          {
            if (System.currentTimeMillis() - paramclass_1363.a24().getDragging().a4() > 50L)
            {
              System.err.println("NOW DROPPING " + paramclass_1363.a24().getDragging());
              paramclass_1363.a24().getDragging();
              localObject = paramclass_1363;
              System.err.println("dropped ");
              ((class_832)localObject).field_90 = false;
            }
            else
            {
              System.err.println("NO DROP: time dragged to short");
            }
            paramclass_1363.a24().setDragging(null);
          }
          if (paramclass_1363.a24().getDragging() == paramclass_1363) {
            System.err.println("NO DROP: dragging and target are the same");
          }
          if (!paramclass_1363.a71(paramclass_1363.a24().getDragging())) {
            System.err.println("NO DROP: not a target: " + paramclass_1363);
          }
        }
      }
    }
  }
  
  public final boolean a67(class_939 paramclass_939)
  {
    return ((paramclass_939.jdField_field_1163_of_type_Int == 0) && (!paramclass_939.jdField_field_1163_of_type_Boolean)) || ((paramclass_939.jdField_field_1163_of_type_Int == 0) && (paramclass_939.jdField_field_1163_of_type_Boolean) && (a24().getDragging().b()));
  }
  
  public final void b()
  {
    super.b();
  }
  
  public final Object a75()
  {
    return null;
  }
  
  public final long a69()
  {
    return this.field_89;
  }
  
  public final boolean a4()
  {
    return !((class_371)a24()).b().isEmpty();
  }
  
  public final boolean b3()
  {
    return this.field_90;
  }
  
  private boolean a71(class_1418 paramclass_1418)
  {
    return getClass().isInstance(paramclass_1418);
  }
  
  public final void c4(int paramInt) {}
  
  public final void d4(int paramInt) {}
  
  public final void a29(boolean paramBoolean)
  {
    this.field_90 = paramBoolean;
  }
  
  public final void a73(long paramLong)
  {
    this.field_89 = paramLong;
  }
  
  public static void e() {}
  
  public final void a_2(int paramInt)
  {
    if (paramInt != this.jdField_field_89_of_type_Int)
    {
      int i = paramInt / 256;
      this.jdField_field_89_of_type_Class_1380 = class_969.a2().a5("build-icons-" + class_41.b(i) + "-16x16-gui-");
    }
    super.a_2(paramInt % 256);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_832
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */