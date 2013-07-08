import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.vecmath.Vector4f;
import org.schema.game.common.data.element.BlockFactory;
import org.schema.game.common.data.element.ElementCategory;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.ElementParser;
import org.schema.game.common.data.element.FactoryResource;
import org.schema.schine.network.client.ClientState;

public class class_869
  extends class_1414
  implements Observer
{
  private final class_964 jdField_field_89_of_type_Class_964;
  private HashMap jdField_field_89_of_type_JavaUtilHashMap;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_869(ClientState paramClientState, HashMap paramHashMap)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_JavaUtilHashMap = paramHashMap;
    this.jdField_field_89_of_type_Class_964 = new class_964(paramClientState);
  }
  
  private void a121(ElementCategory paramElementCategory, class_964 paramclass_964, int paramInt)
  {
    paramclass_964.a141(a124());
    Object localObject;
    ElementInformation localElementInformation = null;
    (localObject = new class_930(176, 30, class_29.h(), a24())).field_90 = new ArrayList();
    ((class_930)localObject).field_90.add("+ " + ElementParser.getStringFromType(paramElementCategory.getCategory()));
    class_930 localclass_930;
    (localclass_930 = new class_930(176, 30, class_29.h(), a24())).field_90 = new ArrayList();
    localclass_930.field_90.add("- " + ElementParser.getStringFromType(paramElementCategory.getCategory()));
    (localObject = new class_961(a24(), (class_1363)localObject, localclass_930)).a83().field_615 = (paramInt * 5);
    int i = 1;
    if (paramElementCategory.hasChildren()) {
      for (j = 0; j < paramElementCategory.getChildren().size(); j++)
      {
        a121((ElementCategory)paramElementCategory.getChildren().get(j), ((class_961)localObject).a140(), paramInt + 1);
        i = 0;
      }
    }
    ((class_961)localObject).a140().a141(a124());
    int j = 0;
    for (paramInt = 0; paramInt < paramElementCategory.getInfoElements().size(); paramInt++) {
      if ((localElementInformation = (ElementInformation)paramElementCategory.getInfoElements().get(paramInt)).isShoppable())
      {
        a122(((class_961)localObject).a140(), localElementInformation, j);
        j++;
        i = 0;
      }
    }
    if (i == 0)
    {
      ((class_961)localObject).addObserver(this);
      ((class_1363)localObject).field_89 = "CATEGORY";
      ((class_961)localObject).c();
      ((class_1363)localObject).field_96 = true;
      paramInt = new class_959((class_1363)localObject, (class_1363)localObject, a24());
      ((class_961)localObject).b25(this);
      paramclass_964.a144(paramInt);
    }
  }
  
  private void a122(class_964 paramclass_964, ElementInformation paramElementInformation, int paramInt)
  {
    (paramInt = new class_895(a24(), paramElementInformation, paramInt)).c();
    class_1361 localclass_1361 = new class_1361(paramInt.a24(), paramInt.b1(), paramInt.a3(), new Vector4f(1.0F, 1.0F, 1.0F, 0.2F), paramInt);
    paramclass_964 = null;
    paramclass_964.a144(new class_959(paramInt, localclass_1361, paramInt.a24()));
    if (paramElementInformation.getId() >= 0)
    {
      (paramclass_964 = new class_873(paramElementInformation, a24(), class_29.n())).field_89 = new ArrayList();
      if ((!field_90) && (paramElementInformation.getDescription() == null)) {
        throw new AssertionError(paramElementInformation.getId() + "; " + paramElementInformation.getName());
      }
      paramInt = paramElementInformation.getDescription().split("\\n");
      for (int i = 0; i < paramInt.length; i++)
      {
        paramInt[i] = paramInt[i].replace("$ACTIVATE", class_367.field_733.b1());
        if (paramInt[i].contains("$RESOURCES")) {
          paramInt[i] = paramInt[i].replace("$RESOURCES", a123(paramElementInformation));
        }
        if (paramInt[i].contains("$ARMOUR")) {
          paramInt[i] = paramInt[i].replace("$ARMOUR", String.valueOf(paramElementInformation.getAmour()));
        }
        if (paramInt[i].contains("$HP")) {
          paramInt[i] = paramInt[i].replace("$HP", String.valueOf(paramElementInformation.getMaxHitPoints()));
        }
        paramclass_964.field_89.add(paramInt[i]);
      }
      this.jdField_field_89_of_type_JavaUtilHashMap.put(Short.valueOf(paramElementInformation.getId()), paramclass_964);
    }
  }
  
  private static CharSequence a123(ElementInformation paramElementInformation)
  {
    if (paramElementInformation.getFactory() == null) {
      return "CANNOT DISPLAY RESOURCES: NOT A FACTORY";
    }
    StringBuffer localStringBuffer;
    (localStringBuffer = new StringBuffer()).append("----------Factory Production--------------\n\n");
    for (int i = 0; i < paramElementInformation.getFactory().input.length; i++)
    {
      localStringBuffer.append("----------Product-<" + (i + 1) + ">--------------\n");
      localStringBuffer.append("--- Required Resources:\n");
      FactoryResource localFactoryResource;
      for (localFactoryResource : paramElementInformation.getFactory().input[i]) {
        localStringBuffer.append(localFactoryResource.count + "x " + ElementKeyMap.getInfo(localFactoryResource.type).getName() + "\n");
      }
      localStringBuffer.append("\n\n--- Produces Resources:\n");
      for (localFactoryResource : paramElementInformation.getFactory().output[i]) {
        localStringBuffer.append(localFactoryResource.count + "x " + ElementKeyMap.getInfo(localFactoryResource.type).getName() + "\n");
      }
      localStringBuffer.append("\n");
    }
    localStringBuffer.append("\n---------------------------------------------\n\n");
    return localStringBuffer.toString();
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    super.b();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_964.a3();
  }
  
  private class_303 a124()
  {
    return ((class_371)a24()).a14().field_4.field_4.field_4;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_964.b1();
  }
  
  public final boolean a_()
  {
    if (a154() != null) {
      return (((class_1363)a154()).a_()) && (super.a_());
    }
    return super.a_();
  }
  
  public final void c()
  {
    super.c();
    ElementCategory localElementCategory;
    if ((localElementCategory = ElementKeyMap.getCategoryHirarchy()).hasChildren()) {
      for (i = 0; i < localElementCategory.getChildren().size(); i++) {
        a121((ElementCategory)localElementCategory.getChildren().get(i), this.jdField_field_89_of_type_Class_964, 0);
      }
    }
    int i = 0;
    for (int j = 0; j < localElementCategory.getInfoElements().size(); j++)
    {
      ElementInformation localElementInformation;
      if ((localElementInformation = (ElementInformation)localElementCategory.getInfoElements().get(j)).isShoppable())
      {
        a122(this.jdField_field_89_of_type_Class_964, localElementInformation, i);
        i++;
      }
    }
    a9(this.jdField_field_89_of_type_Class_964);
    this.jdField_field_89_of_type_Class_964.c();
    this.jdField_field_89_of_type_Class_964.field_96 = true;
    this.field_96 = true;
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_89_of_type_Class_964.f();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_869
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */