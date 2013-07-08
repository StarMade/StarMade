import java.util.ArrayList;
import java.util.List;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.server.data.blueprintnw.BlueprintEntry;

public final class class_303
  extends class_16
  implements class_1412
{
  public short field_4;
  public class_959 field_4;
  private long jdField_field_5_of_type_Long;
  private class_959 jdField_field_5_of_type_Class_959;
  
  public class_303(class_371 paramclass_371)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Short = -1;
  }
  
  public final void a(class_1363 paramclass_1363, class_939 arg2)
  {
    String str = null;
    if ((???.jdField_field_1163_of_type_Boolean) && (???.jdField_field_1163_of_type_Int == 0))
    {
      if ((paramclass_1363 instanceof class_959))
      {
        ??? = (class_959)paramclass_1363;
        if (!"CATEGORY".equals(???.a139().b29()))
        {
          if (this.jdField_field_5_of_type_Class_959 != null)
          {
            this.jdField_field_5_of_type_Class_959.a140().e();
            this.jdField_field_5_of_type_Class_959.a29(false);
          }
          ???.a29(true);
          this.jdField_field_4_of_type_Short = ((Short)???.a139().b29()).shortValue();
          this.jdField_field_5_of_type_Class_959 = ???;
        }
      }
      if ("upload".equals(paramclass_1363.b29()))
      {
        if (System.currentTimeMillis() - this.jdField_field_5_of_type_Long < 5000L)
        {
          a6().a4().b1("Cannot upload now!\nplease wait " + (System.currentTimeMillis() - this.jdField_field_5_of_type_Long) / 1000L + " seconds");
          return;
        }
        this.jdField_field_5_of_type_Long = System.currentTimeMillis();
        ??? = class_1216.field_1429.a7();
        str = "Please enter in a name for your blue print!\n\nAvailable:\n" + ???;
        (??? = new class_408(this, a6(), "BluePrint", str, ???.isEmpty() ? "" : ((BlueprintEntry)???.get(0)).toString())).a10(new class_476());
        synchronized (a6().b())
        {
          a6().b().add(???);
          e2(true);
        }
      }
      if ("save_local".equals(paramclass_1363.b29()))
      {
        if (System.currentTimeMillis() - this.jdField_field_5_of_type_Long < 5000L)
        {
          a6().a4().b1("Cannot save now!\nplease wait " + (System.currentTimeMillis() - this.jdField_field_5_of_type_Long) / 1000L + " seconds");
          return;
        }
        this.jdField_field_5_of_type_Long = System.currentTimeMillis();
        ??? = "Please enter in a name for your blue print!";
        (??? = new class_478(this, a6(), "BluePrint", ???, "BLUEPRINT_" + System.currentTimeMillis())).a10(new class_472());
        synchronized (a6().b())
        {
          a6().b().add(???);
          e2(true);
        }
      }
      if ("save_catalog".equals(paramclass_1363.b29()))
      {
        if (System.currentTimeMillis() - this.jdField_field_5_of_type_Long < 5000L)
        {
          a6().a4().b1("Cannot save now!\nplease wait " + (System.currentTimeMillis() - this.jdField_field_5_of_type_Long) / 1000L + " seconds");
          return;
        }
        this.jdField_field_5_of_type_Long = System.currentTimeMillis();
        ??? = "Please enter in a name for your blue print!";
        (??? = new class_474(this, a6(), "BluePrint", ???, "BLUEPRINT_" + System.currentTimeMillis())).a10(new class_484());
        synchronized (a6().b())
        {
          a6().b().add(???);
          e2(true);
        }
      }
      if (("buy_catalog".equals(paramclass_1363.b29())) && (a33() != null))
      {
        if (System.currentTimeMillis() - this.jdField_field_5_of_type_Long < 5000L)
        {
          a6().a4().b1("Cannot buy now!\nplease wait " + (System.currentTimeMillis() - this.jdField_field_5_of_type_Long) / 1000L + " seconds");
          return;
        }
        this.jdField_field_5_of_type_Long = System.currentTimeMillis();
        ??? = "Please type in a name for your new Ship!";
        (??? = new class_486(this, a6(), "New Ship", ???, a33().field_136 + "_" + System.currentTimeMillis())).a10(new class_480());
        synchronized (a6().b())
        {
          a6().b().add(???);
          e2(true);
        }
      }
      if (((paramclass_1363 instanceof class_972)) && (this.jdField_field_4_of_type_Short >= 0))
      {
        short s;
        if ("buy".equals(paramclass_1363.b29()))
        {
          s = this.jdField_field_4_of_type_Short;
          ??? = this;
          class_743 localclass_7432;
          ???.a6().a4().b1("ERROR: no shop available");
          int k = localclass_7432.a108().a42(s);
          int i = localclass_7432.a108().a41(k);
          ???.a6().a4().b1("ERROR: shop out of item");
          ???.a6().a4().b1("ERROR: not enough credits");
          if ((???.a6().a20().b5() < localclass_7432.a107(ElementKeyMap.getInfo(s), 1) ? 0 : (k < 0) || (i <= 0) ? 0 : (localclass_7432 = a6().a5()) == null ? 0 : 1) != 0)
          {
            class_969.b("0022_action - purchase with credits");
            a6().a20().a125().a(this.jdField_field_4_of_type_Short, 1);
          }
        }
        if ("sell".equals(paramclass_1363.b29()))
        {
          s = this.jdField_field_4_of_type_Short;
          ??? = this;
          if (a6().a20().getInventory(null).a42(s) >= 0)
          {
            class_743 localclass_7431;
            if ((localclass_7431 = ???.a6().a5()) != null)
            {
              int j;
              if ((j = localclass_7431.a108().a42(s)) >= 0) {
                localclass_7431.a108();
              }
              ???.a6().a4().b1("ERROR: shop has reached the max\nstock for the item\n" + ElementKeyMap.getInfo(s).getName());
            }
            else
            {
              ???.a6().a4().b1("ERROR: no shop in distance");
            }
          }
          else
          {
            ???.a6().a4().b1("ERROR: you don't own that item\n" + ElementKeyMap.getInfo(s).getName());
          }
          if ((localclass_7431.a108().a41(j) < class_649.e() ? 1 : 0) != 0)
          {
            class_969.b("0022_action - receive credits");
            a6().a20().a125().b(this.jdField_field_4_of_type_Short, 1);
          }
        }
        if ("sell_more".equals(paramclass_1363.b29())) {
          if ((??? = a6().a5()) != null) {
            a34(this.jdField_field_4_of_type_Short, 1, ???);
          } else {
            a6().a4().b1("ERROR: shop no more in distance");
          }
        }
        if (("buy_more".equals(paramclass_1363.b29())) && ((??? = a6().a5()) != null)) {
          synchronized (a6().b())
          {
            a6().b().add(new class_322(a6(), this.jdField_field_4_of_type_Short, ???));
            e2(true);
            return;
          }
        }
      }
    }
  }
  
  public final class_781 a33()
  {
    if (this.jdField_field_4_of_type_Class_959 != null) {
      return (class_781)this.jdField_field_4_of_type_Class_959.b29();
    }
    return null;
  }
  
  public final void handleKeyEvent() {}
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
  }
  
  public final boolean a1()
  {
    return !a6().b().isEmpty();
  }
  
  public final void b2(boolean paramBoolean)
  {
    class_1046.field_1306 = !paramBoolean;
    if (paramBoolean) {
      class_969.b("0022_menu_ui - swoosh scroll large");
    } else {
      class_969.b("0022_menu_ui - swoosh scroll small");
    }
    a6().a14().field_4.field_4.jdField_field_4_of_type_Class_443.a51().a45().jdField_field_4_of_type_Class_332.e2(paramBoolean);
    a6().a14().field_4.field_4.jdField_field_4_of_type_Class_443.a51().a45().jdField_field_4_of_type_Class_453.e2(paramBoolean);
    super.b2(paramBoolean);
  }
  
  public final void a34(short paramShort, int paramInt, class_743 paramclass_743)
  {
    synchronized (a6().b())
    {
      a6().b().add(new class_304(a6(), paramShort, paramInt, paramclass_743));
      e2(true);
      return;
    }
  }
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = false;
    if (!a6().d2())
    {
      a6().a4().b1("no shop in range");
      d2(false);
      a6().a14().field_4.field_4.jdField_field_4_of_type_Class_469.d2(true);
    }
    a6().a14().field_4.field_4.jdField_field_4_of_type_Class_443.e2(true);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_303
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */