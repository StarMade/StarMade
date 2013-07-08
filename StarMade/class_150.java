import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.schema.game.common.controller.elements.DistributionCollectionManager;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.cloaking.CloakingCollectionManager;
import org.schema.game.common.controller.elements.cloaking.CloakingUnit;
import org.schema.game.common.controller.elements.harvest.SalvageBeamCollectionManager;
import org.schema.game.common.controller.elements.harvest.SalvageUnit;
import org.schema.game.common.controller.elements.jamming.JammingCollectionManager;
import org.schema.game.common.controller.elements.jamming.JammingUnit;
import org.schema.game.common.controller.elements.repair.RepairBeamCollectionManager;
import org.schema.game.common.controller.elements.repair.RepairUnit;
import org.schema.game.common.data.element.PointDistributionUnit;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_150
  extends class_1363
{
  ArrayList jdField_field_89_of_type_JavaUtilArrayList;
  class_930 jdField_field_89_of_type_Class_930;
  private class_964 jdField_field_89_of_type_Class_964;
  class_968 jdField_field_89_of_type_Class_968;
  private boolean jdField_field_89_of_type_Boolean = true;
  
  public class_150(ClientState paramClientState, UnicodeFont paramUnicodeFont)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_930 = new class_930(256, 512, paramUnicodeFont, paramClientState);
    this.jdField_field_89_of_type_Class_968 = new class_968(241.0F, 275.0F, paramClientState);
    this.jdField_field_89_of_type_Class_964 = new class_964(paramClientState);
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_89_of_type_JavaUtilArrayList.add("");
    this.jdField_field_89_of_type_Class_930.a72(256);
    this.jdField_field_89_of_type_Class_930.a135(Color.green);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    this.jdField_field_89_of_type_Class_930.field_90 = this.jdField_field_89_of_type_JavaUtilArrayList;
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_968.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_968.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_968.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_968.c();
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void a21(ElementCollectionManager paramElementCollectionManager)
  {
    if (paramElementCollectionManager.getContainer().getSegmentController() != ((class_371)a24()).a6()) {
      return;
    }
    Object localObject;
    if ((paramElementCollectionManager instanceof DistributionCollectionManager))
    {
      this.jdField_field_89_of_type_Class_964.clear();
      paramElementCollectionManager = (DistributionCollectionManager)paramElementCollectionManager;
      for (int i = 0; i < paramElementCollectionManager.getCollection().size(); i++)
      {
        PointDistributionUnit localPointDistributionUnit = (PointDistributionUnit)paramElementCollectionManager.getCollection().get(i);
        (localObject = new class_144(a24())).a37(localPointDistributionUnit);
        this.jdField_field_89_of_type_Class_964.a144((class_959)localObject);
      }
      this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_964);
    }
    else
    {
      StringBuffer localStringBuffer;
      int j;
      if ((paramElementCollectionManager instanceof CloakingCollectionManager))
      {
        paramElementCollectionManager = (CloakingCollectionManager)paramElementCollectionManager;
        (localStringBuffer = new StringBuffer()).append("Type: \t\t\tCloak\n\nGroups:\t\t" + paramElementCollectionManager.getCollection().size() + "\n");
        localStringBuffer.append("-------------------------------------------------\n");
        for (j = 0; j < paramElementCollectionManager.getCollection().size(); j++)
        {
          localObject = (CloakingUnit)paramElementCollectionManager.getCollection().get(j);
          localStringBuffer.append("Unit " + (j + 1) + " \n");
          localStringBuffer.append("  Dimension:      " + ((CloakingUnit)localObject).getMin() + ", " + ((CloakingUnit)localObject).getMax() + "\n");
          localStringBuffer.append("-------------------------------------------------\n");
        }
        this.jdField_field_89_of_type_JavaUtilArrayList.set(0, localStringBuffer.toString());
        this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
      }
      else if ((paramElementCollectionManager instanceof JammingCollectionManager))
      {
        paramElementCollectionManager = (JammingCollectionManager)paramElementCollectionManager;
        (localStringBuffer = new StringBuffer()).append("Type: \t\t\tRadar Jam\n\nGroups:\t\t" + paramElementCollectionManager.getCollection().size() + "\n");
        localStringBuffer.append("-------------------------------------------------\n");
        for (j = 0; j < paramElementCollectionManager.getCollection().size(); j++)
        {
          localObject = (JammingUnit)paramElementCollectionManager.getCollection().get(j);
          localStringBuffer.append("Unit " + (j + 1) + " \n");
          localStringBuffer.append("  Dimension:      " + ((JammingUnit)localObject).getMin() + ", " + ((JammingUnit)localObject).getMax() + "\n");
          localStringBuffer.append("-------------------------------------------------\n");
        }
        this.jdField_field_89_of_type_JavaUtilArrayList.set(0, localStringBuffer.toString());
        this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
      }
      else if ((paramElementCollectionManager instanceof SalvageBeamCollectionManager))
      {
        paramElementCollectionManager = (SalvageBeamCollectionManager)paramElementCollectionManager;
        (localStringBuffer = new StringBuffer()).append("Type: \t\t\tSalvage\nLocation:\t\t" + paramElementCollectionManager.getControllerPos() + "\n\nGroups:\t\t" + paramElementCollectionManager.getCollection().size() + "\n");
        localStringBuffer.append("-------------------------------------------------\n");
        for (j = 0; j < paramElementCollectionManager.getCollection().size(); j++)
        {
          localObject = (SalvageUnit)paramElementCollectionManager.getCollection().get(j);
          localStringBuffer.append("Unit " + (j + 1) + " \n");
          localStringBuffer.append("  Dimension:      " + ((SalvageUnit)localObject).getMin() + ", " + ((SalvageUnit)localObject).getMax() + "\n");
          localStringBuffer.append("  Salvage Speed:  " + ((SalvageUnit)localObject).getSalvageSpeedFactor() + "\n");
          localStringBuffer.append("-------------------------------------------------\n");
        }
        this.jdField_field_89_of_type_JavaUtilArrayList.set(0, localStringBuffer.toString());
        this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
      }
      else if ((paramElementCollectionManager instanceof RepairBeamCollectionManager))
      {
        paramElementCollectionManager = (RepairBeamCollectionManager)paramElementCollectionManager;
        (localStringBuffer = new StringBuffer()).append("Type: \t\t\tRepair Beam\nLocation:\t\t" + paramElementCollectionManager.getControllerPos() + "\n\nGroups:\t\t" + paramElementCollectionManager.getCollection().size() + "\n");
        localStringBuffer.append("-------------------------------------------------\n");
        for (j = 0; j < paramElementCollectionManager.getCollection().size(); j++)
        {
          localObject = (RepairUnit)paramElementCollectionManager.getCollection().get(j);
          localStringBuffer.append("Unit " + (j + 1) + " \n");
          localStringBuffer.append("  Dimension:      " + ((RepairUnit)localObject).getMin() + ", " + ((RepairUnit)localObject).getMax() + "\n");
          localStringBuffer.append("  Rapair Speed:  " + ((RepairUnit)localObject).getRepairSpeedFactor() + "\n");
          localStringBuffer.append("-------------------------------------------------\n");
        }
        this.jdField_field_89_of_type_JavaUtilArrayList.set(0, localStringBuffer.toString());
        this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
      }
      else
      {
        System.err.println("EXCEPTION: UNKNOWN MANAGER: " + paramElementCollectionManager);
      }
    }
    if (this.jdField_field_89_of_type_Class_968.a139() == this.jdField_field_89_of_type_Class_930)
    {
      this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_Boolean = false;
      this.jdField_field_89_of_type_Class_930.e();
    }
  }
  
  public final void a17(String paramString)
  {
    if (paramString.equals("CORE"))
    {
      (paramString = new StringBuffer()).append("Type: \t\tDocking Beam\nLocation:\t\t" + class_747.field_136 + "\n");
      this.jdField_field_89_of_type_JavaUtilArrayList.set(0, paramString.toString());
      this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
      return;
    }
    if (paramString.equals("DOCK"))
    {
      (paramString = new StringBuffer()).append("Undock yourself by executing this!\n");
      this.jdField_field_89_of_type_JavaUtilArrayList.set(0, paramString.toString());
      this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
    }
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if (this.jdField_field_89_of_type_Class_968.a139() == this.jdField_field_89_of_type_Class_964) {
      this.jdField_field_89_of_type_Class_964.a12(paramclass_941);
    }
    super.a12(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_150
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */