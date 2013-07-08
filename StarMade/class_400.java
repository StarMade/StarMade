import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
import org.schema.game.client.view.cubes.CubeOptOptMesh;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.SegmentData;

public final class class_400
{
  private int field_791;
  private int field_792;
  private int field_793;
  public class_657 field_794;
  private static IntOpenHashSet field_794;
  public CubeOptOptMesh field_794;
  public boolean field_794;
  public static int field_794;
  
  public class_400()
  {
    new FloatArrayList();
  }
  
  public final void a(SegmentData paramSegmentData, CubeMeshBufferContainer paramCubeMeshBufferContainer)
  {
    paramCubeMeshBufferContainer.jdField_field_1665_of_type_JavaNioFloatBuffer.rewind();
    paramCubeMeshBufferContainer.jdField_field_1665_of_type_JavaNioFloatBuffer.limit(paramCubeMeshBufferContainer.jdField_field_1665_of_type_JavaNioFloatBuffer.capacity());
    this.field_792 = 0;
    this.field_791 = 0;
    int j;
    int m;
    for (int i = 0; i < 4096; i++)
    {
      j = i * 3;
      short s;
      if ((((s = paramSegmentData.getType(j)) != 122) || (paramSegmentData.isActive(j))) && (s != 0))
      {
        this.field_792 += 1;
        if (ElementKeyMap.getInfo(s).isBlended())
        {
          paramCubeMeshBufferContainer.jdField_field_1665_of_type_JavaNioIntBuffer.put(this.field_791, i);
          paramCubeMeshBufferContainer.field_1666.put(this.field_791, s);
          this.field_791 += 1;
        }
        else
        {
          for (m = 0; m < 6; m++) {
            CubeMeshBufferContainer.a8(paramCubeMeshBufferContainer, i, j, paramSegmentData, class_1390.field_1589[m], m, s);
          }
        }
      }
    }
    paramCubeMeshBufferContainer.jdField_field_1665_of_type_JavaNioFloatBuffer.position();
    for (i = 0; i < this.field_791; i++)
    {
      int k = (j = paramCubeMeshBufferContainer.jdField_field_1665_of_type_JavaNioIntBuffer.get(i)) * 3;
      for (m = 0; m < 6; m++) {
        CubeMeshBufferContainer.a8(paramCubeMeshBufferContainer, j, k, paramSegmentData, class_1390.field_1589[m], m, paramCubeMeshBufferContainer.field_1666.get(i));
      }
    }
    this.field_793 = paramCubeMeshBufferContainer.jdField_field_1665_of_type_JavaNioFloatBuffer.position();
  }
  
  public final int a1()
  {
    return this.field_791;
  }
  
  public final void a2(CubeMeshBufferContainer paramCubeMeshBufferContainer)
  {
    if (this.jdField_field_794_of_type_OrgSchemaGameClientViewCubesCubeOptOptMesh == null) {
      this.jdField_field_794_of_type_OrgSchemaGameClientViewCubesCubeOptOptMesh = new CubeOptOptMesh();
    }
    this.jdField_field_794_of_type_OrgSchemaGameClientViewCubesCubeOptOptMesh.a181(paramCubeMeshBufferContainer.jdField_field_1665_of_type_JavaNioFloatBuffer);
  }
  
  public final void a3()
  {
    this.jdField_field_794_of_type_OrgSchemaGameClientViewCubesCubeOptOptMesh.b();
    jdField_field_794_of_type_Int += 1;
  }
  
  public static void b()
  {
    jdField_field_794_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet.clear();
  }
  
  static
  {
    jdField_field_794_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet = new IntOpenHashSet();
    new Object2ObjectOpenHashMap();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_400
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */