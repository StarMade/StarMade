import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.GameServerController;
import org.schema.game.server.data.blueprintnw.BlueprintEntry;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.SynchronizationContainerController;

public final class class_1131
  extends class_1133
{
  private int field_227;
  
  public class_1131(StateInterface paramStateInterface, class_1074 paramclass_1074, String paramString1, String paramString2, float[] paramArrayOfFloat, int paramInt, class_48 paramclass_481, class_48 paramclass_482, String paramString3)
  {
    super(paramStateInterface, paramclass_1074, paramString1, paramString2, paramArrayOfFloat, paramclass_481, paramclass_482, paramString3);
    this.jdField_field_227_of_type_Int = paramInt;
    a2(this.jdField_field_227_of_type_Int);
  }
  
  public final void a(int paramInt, boolean paramBoolean)
  {
    class_1070.a1(this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface, this.jdField_field_1379_of_type_JavaLangString);
    class_747 localclass_747;
    (localclass_747 = class_1070.a3(this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface, this.jdField_field_1379_of_type_JavaLangString, paramInt, this.jdField_field_227_of_type_JavaLangString, this.jdField_field_1379_of_type_ArrayOfFloat, this.jdField_field_1379_of_type_Class_48.field_475, this.jdField_field_1379_of_type_Class_48.field_476, this.jdField_field_1379_of_type_Class_48.field_477, this.jdField_field_227_of_type_Class_48.field_475, this.jdField_field_227_of_type_Class_48.field_476, this.jdField_field_227_of_type_Class_48.field_477, this.field_1380)).setFactionId(this.jdField_field_227_of_type_Int);
    localclass_747.getControlElementMap().set(this.jdField_field_1379_of_type_Class_1074.a1());
    Object localObject;
    if (((this.jdField_field_1379_of_type_Class_1074 instanceof BlueprintEntry)) && ((localObject = (BlueprintEntry)this.jdField_field_1379_of_type_Class_1074).a13() != null))
    {
      localclass_747.a112().loadInventoriesFromTag = false;
      System.err.println("[OUTLINE) BLUEPRINT MANAGER TAG EXISTS");
      localclass_747.a112().fromTagStructure(((BlueprintEntry)localObject).a13());
      localclass_747.a112().loadInventoriesFromTag = true;
    }
    if (this.jdField_field_1379_of_type_JavaUtilArrayList != null)
    {
      localObject = this.jdField_field_1379_of_type_JavaUtilArrayList.iterator();
      while (((Iterator)localObject).hasNext())
      {
        class_1133 localclass_1133;
        (localclass_1133 = (class_1133)((Iterator)localObject).next()).field_1381 = this.jdField_field_1379_of_type_JavaLangString;
        localclass_1133.a(paramInt, false);
      }
    }
    if (this.jdField_field_227_of_type_Int != 0)
    {
      localclass_747.a87().a183(class_776.field_1032).a174("Any", true);
      if ((this.field_1381 != null) && (((BlueprintEntry)this.jdField_field_1379_of_type_Class_1074).a16() == 7)) {
        localclass_747.a87().a183(class_776.field_1033).a174("Turret", true);
      } else {
        localclass_747.a87().a183(class_776.field_1033).a174("Ship", true);
      }
      localclass_747.a87().a183(class_776.field_1034).a174("true", true);
      localclass_747.a87().a13();
    }
    if (this.field_1381 != null)
    {
      localObject = (BlueprintEntry)this.jdField_field_1379_of_type_Class_1074;
      System.err.println("[SHIPOUTLINE] ADDING DELAYED DOCK " + this.jdField_field_1379_of_type_JavaLangString + " to " + this.field_1381);
      localclass_747.getDockingController().a13().set(((BlueprintEntry)localObject).a21());
      ((BlueprintEntry)localObject).a20();
      localclass_747.getDockingController().a7(new String(this.field_1381), new class_48(((BlueprintEntry)localObject).a15()));
      localclass_747.setHidden(true);
    }
    if (paramBoolean) {
      localclass_747.a116(this.jdField_field_1379_of_type_Class_1074, this.field_1380);
    }
    ((class_1041)this.jdField_field_1379_of_type_OrgSchemaSchineNetworkStateInterface).a59().a4().addNewSynchronizedObjectQueued(localclass_747);
  }
  
  public final void a1(class_48 paramclass_48, class_1041 paramclass_1041, int paramInt, ObjectArrayList paramObjectArrayList)
  {
    Transform localTransform;
    (localTransform = new Transform()).setFromOpenGLMatrix(this.jdField_field_1379_of_type_ArrayOfFloat);
    class_1070.a1(paramclass_1041, this.jdField_field_1379_of_type_JavaLangString);
    class_747 localclass_747;
    (localclass_747 = class_1070.a3(paramclass_1041, this.jdField_field_1379_of_type_JavaLangString, -123, this.jdField_field_227_of_type_JavaLangString, this.jdField_field_1379_of_type_ArrayOfFloat, this.jdField_field_1379_of_type_Class_48.field_475, this.jdField_field_1379_of_type_Class_48.field_476, this.jdField_field_1379_of_type_Class_48.field_477, this.jdField_field_227_of_type_Class_48.field_475, this.jdField_field_227_of_type_Class_48.field_476, this.jdField_field_227_of_type_Class_48.field_477, this.field_1380)).setFactionId(this.jdField_field_227_of_type_Int);
    localclass_747.getControlElementMap().set(this.jdField_field_1379_of_type_Class_1074.a1());
    localclass_747.getWorldTransform().set(localTransform);
    localclass_747.getInitialTransform().set(localTransform);
    Object localObject;
    if (this.jdField_field_1379_of_type_JavaUtilArrayList != null)
    {
      localObject = this.jdField_field_1379_of_type_JavaUtilArrayList.iterator();
      while (((Iterator)localObject).hasNext())
      {
        class_1133 localclass_1133;
        (localclass_1133 = (class_1133)((Iterator)localObject).next()).field_1381 = this.jdField_field_1379_of_type_JavaLangString;
        localclass_1133.a1(paramclass_48, paramclass_1041, paramInt, paramObjectArrayList);
      }
    }
    if (this.jdField_field_227_of_type_Int != 0)
    {
      localclass_747.a87().a183(class_776.field_1032).a174("Any", true);
      localclass_747.a87().a183(class_776.field_1033).a174("Ship", true);
      localclass_747.a87().a183(class_776.field_1034).a174("true", true);
      localclass_747.a87().a13();
    }
    localclass_747.transientSector.b1(paramclass_48);
    if (this.field_1381 != null)
    {
      localObject = (BlueprintEntry)this.jdField_field_1379_of_type_Class_1074;
      System.err.println("[SHIPOUTLINE] DATABASE ADDING DELAYED DOCK " + this.jdField_field_1379_of_type_JavaLangString + " to " + this.field_1381);
      localclass_747.getDockingController().a13().set(((BlueprintEntry)localObject).a21());
      ((BlueprintEntry)localObject).a20();
      localclass_747.getDockingController().field_973 = new class_1129(this.field_1381, new class_48(((BlueprintEntry)localObject).a15()));
    }
    paramclass_1041.a59().a51(localclass_747, false);
    localclass_747.getDockingController().field_973 = null;
    paramclass_1041.a66().a18(this.jdField_field_1379_of_type_JavaLangString.split("_", 3)[2], paramclass_48, 5, Universe.getRandom().nextLong(), "<sim>", "<sim>", this.jdField_field_227_of_type_JavaLangString, this.jdField_field_227_of_type_Int, localTransform.origin, this.jdField_field_1379_of_type_Class_48, this.jdField_field_227_of_type_Class_48, paramInt);
    System.err.println("[BLUEPRINT][SPAWNINDB] added to members: " + this.jdField_field_1379_of_type_JavaLangString);
    paramObjectArrayList.add(new String(this.jdField_field_1379_of_type_JavaLangString));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1131
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */