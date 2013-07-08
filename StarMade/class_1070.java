import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.File;
import java.util.HashMap;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.server.controller.EntityAlreadyExistsException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.client.ClientStateInterface;

public final class class_1070
{
  private Object[] field_1358;
  public final Class field_1358;
  public final int field_1358;
  
  public static String a(String paramString)
  {
    return "ENTITY_SHIP_" + paramString;
  }
  
  public static String b(String paramString)
  {
    return "ENTITY_SPACESTATION_" + paramString;
  }
  
  public static String c(String paramString)
  {
    return "ENTITY_FLOATINGROCK_" + paramString;
  }
  
  public static String d(String paramString)
  {
    return "ENTITY_SHOP_" + paramString;
  }
  
  public static boolean a1(StateInterface paramStateInterface, String paramString)
  {
    File localFile = new File(class_1041.field_144 + paramString + ".ent");
    if (((paramStateInterface instanceof class_1041)) && (((class_1041)paramStateInterface).c8().containsKey(paramString))) {
      throw new EntityAlreadyExistsException(paramString);
    }
    if (localFile.exists()) {
      throw new EntityAlreadyExistsException(paramString);
    }
    return false;
  }
  
  public static class_737 a2(StateInterface paramStateInterface, String paramString1, int paramInt1, String paramString2, float[] paramArrayOfFloat, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    class_737 localclass_737;
    (localclass_737 = new class_737(paramStateInterface)).setUniqueIdentifier(paramString1);
    localclass_737.getMinPos().b1(new class_48(paramInt2, paramInt3, paramInt4));
    localclass_737.getMaxPos().b1(new class_48(paramInt5, paramInt6, paramInt7));
    localclass_737.setCreatorId(class_780.field_1038.ordinal());
    localclass_737.setId(paramStateInterface.getNextFreeObjectId());
    localclass_737.setSectorId(paramInt1);
    localclass_737.setRealName(paramString2);
    localclass_737.initialize();
    localclass_737.getInitialTransform().setFromOpenGLMatrix(paramArrayOfFloat);
    return localclass_737;
  }
  
  public static class_747 a3(StateInterface paramStateInterface, String paramString1, int paramInt1, String paramString2, float[] paramArrayOfFloat, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, String paramString3)
  {
    class_747 localclass_747;
    (localclass_747 = new class_747(paramStateInterface)).setUniqueIdentifier(paramString1);
    localclass_747.getMinPos().b1(new class_48(paramInt2, paramInt3, paramInt4));
    localclass_747.getMaxPos().b1(new class_48(paramInt5, paramInt6, paramInt7));
    localclass_747.setId(paramStateInterface.getNextFreeObjectId());
    localclass_747.setSectorId(paramInt1);
    localclass_747.setRealName(paramString2);
    localclass_747.initialize();
    localclass_747.getInitialTransform().setFromOpenGLMatrix(paramArrayOfFloat);
    localclass_747.setSpawner(paramString3);
    return localclass_747;
  }
  
  public static boolean a4(String paramString)
  {
    return (paramString.length() > 0) && (paramString.matches("[a-zA-Z0-9_-]+"));
  }
  
  public class_1070(int paramInt, Object[] paramArrayOfObject, Class paramClass)
  {
    this.jdField_field_1358_of_type_Int = paramInt;
    this.jdField_field_1358_of_type_ArrayOfJavaLangObject = paramArrayOfObject;
    this.jdField_field_1358_of_type_JavaLangClass = paramClass;
  }
  
  public final class_747 a5(class_1041 paramclass_1041)
  {
    Object localObject1 = new float[16];
    int i = 0;
    for (int j = 0; j < 16; j++) {
      localObject1[j] = ((Float)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).floatValue();
    }
    j = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int k = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int m = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int n = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int i1 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int i2 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int i3 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    String str = (String)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)];
    Object localObject2 = (String)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[i];
    class_748 localclass_748 = (class_748)paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().get(i3);
    a1(paramclass_1041, str);
    localObject1 = a3(paramclass_1041, str, localclass_748.c2(), (String)localObject2, (float[])localObject1, j, k, m, n, i1, i2, localclass_748.getUniqueIdentifier());
    (localObject2 = new class_672((SegmentController)localObject1)).a22(new SegmentData(paramclass_1041 instanceof ClientStateInterface));
    ((class_672)localObject2).a16().setSegment((Segment)localObject2);
    ((class_672)localObject2).a16().setInfoElement((byte)8, (byte)8, (byte)8, (short)1, true);
    ((class_672)localObject2).a46(System.currentTimeMillis());
    ((class_747)localObject1).getSegmentBuffer().a((Segment)localObject2);
    ((class_747)localObject1).getSegmentBuffer().b6((Segment)localObject2);
    return localObject1;
  }
  
  public final class_786 a6(class_1041 paramclass_1041)
  {
    Object localObject1 = new float[16];
    int i = 0;
    for (class_748 localclass_7481 = 0; localclass_7481 < 16; localclass_7481++) {
      localObject1[localclass_7481] = ((Float)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).floatValue();
    }
    localclass_7481 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    String str1 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int j = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    class_748 localclass_7482 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    String str2 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int k = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int m = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    String str3 = (String)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)];
    Object localObject2 = (String)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[i];
    a1(paramclass_1041, str3);
    class_748 localclass_7483 = (class_748)paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().get(m);
    String str4 = localclass_7483.getUniqueIdentifier();
    int n = k;
    str3 = str2;
    localclass_7483 = localclass_7482;
    k = j;
    str2 = str1;
    localclass_7482 = localclass_7481;
    Object localObject4 = localObject1;
    Object localObject3 = localObject2;
    localclass_7481 = localclass_7483.c2();
    localObject2 = str3;
    localObject1 = paramclass_1041;
    class_786 localclass_786;
    (localclass_786 = new class_786((StateInterface)localObject1)).setUniqueIdentifier((String)localObject2);
    localclass_786.getMinPos().b1(new class_48(localclass_7482, str2, k));
    localclass_786.getMaxPos().b1(new class_48(localclass_7483, str3, n));
    localclass_786.setId(((StateInterface)localObject1).getNextFreeObjectId());
    localclass_786.setSectorId(localclass_7481);
    localclass_786.setRealName(localObject3);
    localclass_786.initialize();
    localclass_786.getInitialTransform().setFromOpenGLMatrix(localObject4);
    localclass_786.setSpawner(str4);
    localObject1 = localclass_786;
    (localObject2 = new class_672((SegmentController)localObject1)).a22(new SegmentData(paramclass_1041 instanceof ClientStateInterface));
    ((class_672)localObject2).a16().setSegment((Segment)localObject2);
    ((class_672)localObject2).a16().setInfoElement((byte)8, (byte)8, (byte)8, (short)1, true);
    ((class_672)localObject2).a46(System.currentTimeMillis());
    ((class_786)localObject1).getSegmentBuffer().a((Segment)localObject2);
    return localObject1;
  }
  
  public final class_737 a7(class_1041 paramclass_1041)
  {
    Object localObject1 = new float[16];
    int i = 0;
    for (int j = 0; j < 16; j++) {
      localObject1[j] = ((Float)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).floatValue();
    }
    j = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int k = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int m = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int n = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int i1 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int i2 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    int i3 = ((Integer)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
    String str = (String)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[(i++)];
    Object localObject2 = (String)this.jdField_field_1358_of_type_ArrayOfJavaLangObject[i];
    a1(paramclass_1041, str);
    class_748 localclass_748 = (class_748)paramclass_1041.getLocalAndRemoteObjectContainer().getLocalObjects().get(i3);
    localObject1 = a2(paramclass_1041, str, localclass_748.c2(), (String)localObject2, (float[])localObject1, j, k, m, n, i1, i2);
    (localObject2 = new class_672((SegmentController)localObject1)).a22(new SegmentData(paramclass_1041 instanceof ClientStateInterface));
    ((class_672)localObject2).a16().setSegment((Segment)localObject2);
    ((class_672)localObject2).a16().setInfoElement((byte)8, (byte)8, (byte)8, (short)5, true);
    ((class_672)localObject2).a46(System.currentTimeMillis());
    ((class_737)localObject1).getSegmentBuffer().a((Segment)localObject2);
    return localObject1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1070
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */