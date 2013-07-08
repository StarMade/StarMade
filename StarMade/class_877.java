import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_877
  extends class_1363
  implements Observer, class_1412
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_968 jdField_field_89_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private class_968 jdField_field_90_of_type_Class_968;
  private class_869 jdField_field_89_of_type_Class_869;
  private class_930 jdField_field_89_of_type_Class_930;
  private HashMap jdField_field_89_of_type_JavaUtilHashMap;
  private class_871 jdField_field_89_of_type_Class_871;
  private class_1414 jdField_field_89_of_type_Class_1414;
  private boolean jdField_field_89_of_type_Boolean = true;
  private boolean jdField_field_90_of_type_Boolean;
  private boolean field_92 = true;
  
  public class_877(ClientState paramClientState)
  {
    super(paramClientState);
    paramClientState.addObserver(this);
    paramClientState = this;
    e();
    paramClientState.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("shop-panel-gui-"), paramClientState.a24());
    paramClientState.jdField_field_90_of_type_Class_968 = new class_968(209.0F, 362.0F, paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_968 = new class_968(209.0F, 362.0F, paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_964 = new class_964(paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_1414 = new class_1414(paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_871 = new class_871(paramClientState.a24(), paramClientState.a124());
    paramClientState.jdField_field_89_of_type_Class_968.c7(paramClientState.jdField_field_89_of_type_Class_964);
    paramClientState.jdField_field_89_of_type_JavaUtilHashMap = new HashMap();
    paramClientState.jdField_field_89_of_type_Class_869 = new class_869(paramClientState.a24(), paramClientState.jdField_field_89_of_type_JavaUtilHashMap);
    paramClientState.jdField_field_90_of_type_Class_968.c7(paramClientState.jdField_field_89_of_type_Class_869);
    paramClientState.a9(paramClientState.jdField_field_89_of_type_Class_972);
    paramClientState.jdField_field_89_of_type_Class_1414.a9(paramClientState.jdField_field_90_of_type_Class_968);
    paramClientState.jdField_field_89_of_type_Class_1414.a9(paramClientState.jdField_field_89_of_type_Class_871);
    paramClientState.jdField_field_89_of_type_Class_871.a161(464.0F, 89.0F, 0.0F);
    paramClientState.jdField_field_89_of_type_Class_968.a161(252.0F, 89.0F, 0.0F);
    paramClientState.jdField_field_90_of_type_Class_968.a161(252.0F, 89.0F, 0.0F);
    paramClientState.jdField_field_89_of_type_Class_972.a9(paramClientState.jdField_field_89_of_type_Class_1414);
  }
  
  public final void a9(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_972.a9(paramclass_1363);
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_1363 == this.jdField_field_89_of_type_Class_972)
    {
      paramclass_1363 = this;
      if (this.jdField_field_89_of_type_Class_972.a_())
      {
        paramclass_939 = paramclass_1363.a24().getMouseEvents().iterator();
        while (paramclass_939.hasNext())
        {
          Object localObject;
          if (((localObject = (class_939)paramclass_939.next()).jdField_field_1163_of_type_Int == 0) && (!((class_939)localObject).jdField_field_1163_of_type_Boolean))
          {
            if (paramclass_1363.a24().getDragging() != null)
            {
              paramclass_1363.a24().getDragging();
              if (paramclass_1363.a24().getDragging() != paramclass_1363)
              {
                if (System.currentTimeMillis() - paramclass_1363.a24().getDragging().a4() > 50L)
                {
                  System.err.println("NOW DROPPING " + paramclass_1363.a24().getDragging());
                  class_185 localclass_185 = (class_185)paramclass_1363.a24().getDragging();
                  localObject = paramclass_1363;
                  localclass_185.a29(false);
                  class_743 localclass_743;
                  if ((localclass_743 = ((class_371)((class_877)localObject).a24()).a5()) != null) {
                    ((class_877)localObject).a124().a34(localclass_185.a70(), localclass_185.a68(true), localclass_743);
                  } else {
                    ((class_371)((class_877)localObject).a24()).a4().b1("ERROR: not in shop dist");
                  }
                }
                else
                {
                  System.err.println("NO DROP: time dragged to short");
                }
                paramclass_1363.a24().setDragging(null);
              }
            }
            if ((paramclass_1363.a24().getDragging() != null) && (paramclass_1363.a24().getDragging() == paramclass_1363)) {
              System.err.println("NO DROP: dragging and target are the same");
            }
            if (paramclass_1363.a24().getDragging() != null) {
              paramclass_1363.a24().getDragging();
            }
          }
        }
      }
      return;
    }
    if ((Mouse.getEventButtonState()) && (Mouse.getEventButton() == 0))
    {
      if ((paramclass_1363 instanceof class_959))
      {
        class_969.b("0022_menu_ui - enter");
        paramclass_939 = null;
        if ((a124().jdField_field_4_of_type_Class_959 != null) && (paramclass_1363 != a124().jdField_field_4_of_type_Class_959))
        {
          a124().jdField_field_4_of_type_Class_959.a29(false);
          if (a124().jdField_field_4_of_type_Class_959.a140() != ((class_959)paramclass_1363).a140()) {
            a124().jdField_field_4_of_type_Class_959.a140().e();
          }
        }
        ((class_959)paramclass_1363).a29(true);
        a124().jdField_field_4_of_type_Class_959 = ((class_959)paramclass_1363);
        return;
      }
      paramclass_939 = null;
      if (paramclass_1363.field_89 != null)
      {
        if (paramclass_1363.field_89.equals("ELEMENTS"))
        {
          class_969.b("0022_menu_ui - enter");
          this.jdField_field_89_of_type_Boolean = true;
          this.jdField_field_90_of_type_Boolean = true;
          return;
        }
        if (!field_93) {
          throw new AssertionError("not known command: '" + paramclass_1363.field_89 + "'");
        }
      }
    }
  }
  
  public final void a2() {}
  
  public final void b2(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_972.b2(paramclass_1363);
  }
  
  public final void b()
  {
    if (this.field_92)
    {
      localObject1 = this;
      this.jdField_field_89_of_type_Class_964.clear();
      ((class_877)localObject1).jdField_field_89_of_type_Class_964.a141((class_1412)localObject1);
      Iterator localIterator = ((class_371)((class_877)localObject1).a24()).a2().field_1043.iterator();
      while (localIterator.hasNext())
      {
        class_781 localclass_781 = (class_781)localIterator.next();
        System.err.println("[GUI] adding catalog entry: " + localclass_781);
        Object localObject2 = localclass_781.field_136;
        class_930 localclass_9301;
        (localclass_9301 = new class_930(256, 25, class_29.d(), ((class_877)localObject1).a24())).field_90 = new ArrayList();
        localclass_9301.field_90.add(localObject2);
        localclass_9301.a135(new Color(0.5F, 0.5F, 0.5F, 1.0F));
        class_930 localclass_9302;
        (localclass_9302 = new class_930(256, 25, class_29.d(), ((class_877)localObject1).a24())).field_90 = new ArrayList();
        localclass_9302.field_90.add(localObject2);
        (localObject2 = new class_959(localclass_9301, localclass_9302, ((class_877)localObject1).a24())).field_89 = localclass_781;
        ((class_877)localObject1).jdField_field_89_of_type_Class_964.a144((class_959)localObject2);
      }
      this.field_92 = false;
    }
    if (this.jdField_field_90_of_type_Boolean)
    {
      localObject1 = this;
      this.jdField_field_89_of_type_Class_930.field_89.field_1776 = 1.0F;
      ((class_877)localObject1).jdField_field_89_of_type_Class_930.field_89.field_1777 = 1.0F;
      ((class_877)localObject1).jdField_field_89_of_type_Class_930.field_89.field_1778 = 1.0F;
      ((class_877)localObject1).jdField_field_89_of_type_Class_972.b2(((class_877)localObject1).jdField_field_89_of_type_Class_1414);
      ((class_877)localObject1).jdField_field_89_of_type_Class_972.a9(((class_877)localObject1).jdField_field_89_of_type_Class_1414);
      ((class_877)localObject1).jdField_field_90_of_type_Boolean = false;
    }
    Object localObject1 = null;
    if ((this.jdField_field_89_of_type_Boolean) && (a124().jdField_field_4_of_type_Short >= 0))
    {
      (localObject1 = (class_873)this.jdField_field_89_of_type_JavaUtilHashMap.get(Short.valueOf(a124().jdField_field_4_of_type_Short))).a161(469.0F, 265.0F, 0.0F);
      this.jdField_field_89_of_type_Class_1414.a9((class_1363)localObject1);
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_972.b();
    GlUtil.c2();
    if (localObject1 != null) {
      this.jdField_field_89_of_type_Class_1414.b2((class_1363)localObject1);
    }
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  private class_303 a124()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public static void e()
  {
    class_875.field_1115.clear();
    short[] arrayOfShort;
    int i = (arrayOfShort = ElementKeyMap.typeList()).length;
    for (int j = 0; j < i; j++)
    {
      short s;
      ElementInformation localElementInformation;
      if ((localElementInformation = ElementKeyMap.getInfo(s = arrayOfShort[j])).isShoppable()) {
        class_875.field_1115.put(Integer.valueOf(localElementInformation.getBuildIconNum()), Short.valueOf(s));
      }
    }
  }
  
  public final boolean a4()
  {
    return false;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_90_of_type_Class_968.c();
    class_1414 localclass_1414;
    (localclass_1414 = new class_1414(a24(), 104.0F, 25.0F)).field_96 = true;
    localclass_1414.field_89 = "SHOP-ELEMENTS-ANCHOR";
    localclass_1414.field_89 = "ELEMENTS";
    localclass_1414.a141(this);
    localclass_1414.a161(252.0F, 66.0F, 0.0F);
    this.jdField_field_89_of_type_Class_972.a9(localclass_1414);
    this.jdField_field_89_of_type_Class_972.a141(this);
    this.jdField_field_89_of_type_Class_972.field_96 = true;
    this.jdField_field_89_of_type_Class_930 = new class_930(64, 20, class_29.b2(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_89 = "SHOP-ELEMENTS-BUTTON";
    this.jdField_field_89_of_type_Class_930.field_90.add("Blocks");
    this.jdField_field_89_of_type_Class_930.a161(10.0F, 2.0F, 0.0F);
    this.jdField_field_89_of_type_Class_930.a135(new Color(1.0F, 1.0F, 1.0F, 1.0F));
    this.jdField_field_89_of_type_Class_930.c();
    localclass_1414.a9(this.jdField_field_89_of_type_Class_930);
    this.field_96 = true;
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if ("CATALOG_UPDATE".equals(paramObject)) {
      this.field_92 = true;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_877
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */