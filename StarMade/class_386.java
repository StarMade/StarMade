import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.schema.common.FastMath;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public class class_386
{
  private SegmentData jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData;
  private class_382 jdField_field_778_of_type_Class_382;
  private class_392 jdField_field_778_of_type_Class_392;
  private CubeMeshBufferContainer jdField_field_778_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer;
  private class_35 jdField_field_778_of_type_Class_35;
  private class_35 jdField_field_779_of_type_Class_35;
  private ShortBuffer jdField_field_778_of_type_JavaNioShortBuffer;
  private FloatBuffer jdField_field_778_of_type_JavaNioFloatBuffer;
  private FloatBuffer jdField_field_779_of_type_JavaNioFloatBuffer;
  private FloatBuffer jdField_field_780_of_type_JavaNioFloatBuffer;
  private ByteBuffer jdField_field_778_of_type_JavaNioByteBuffer;
  private class_48 jdField_field_778_of_type_Class_48;
  private class_48 jdField_field_779_of_type_Class_48;
  private boolean jdField_field_778_of_type_Boolean;
  private Segment[] jdField_field_778_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment;
  private float jdField_field_778_of_type_Float;
  private final class_48 jdField_field_780_of_type_Class_48;
  private int[] jdField_field_778_of_type_ArrayOfInt;
  private final Segment jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegment;
  
  public class_386()
  {
    new class_48(-16, 32, 32);
    this.jdField_field_778_of_type_Class_35 = new class_35();
    this.jdField_field_779_of_type_Class_35 = new class_35();
    this.jdField_field_778_of_type_JavaNioShortBuffer = BufferUtils.createShortBuffer(5832);
    this.jdField_field_778_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(34992);
    this.jdField_field_779_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(34992);
    this.jdField_field_780_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(104976);
    this.jdField_field_778_of_type_JavaNioByteBuffer = BufferUtils.createByteBuffer(34992);
    new class_48();
    new class_35();
    this.jdField_field_778_of_type_Class_48 = new class_48();
    this.jdField_field_779_of_type_Class_48 = new class_48();
    this.jdField_field_778_of_type_Boolean = false;
    this.jdField_field_778_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment = new Segment[729];
    this.jdField_field_780_of_type_Class_48 = new class_48();
    this.jdField_field_778_of_type_ArrayOfInt = new int[] { a6(0, -1, -1), -a6(0, -1, -1), a6(-1, 0, -1), -a6(-1, 0, -1), a6(-1, -1, 0), -a6(-1, -1, 0) };
    this.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegment = new class_672(null);
    this.jdField_field_778_of_type_Class_392 = new class_392();
    if (this.jdField_field_778_of_type_Class_382 == null)
    {
      this.jdField_field_778_of_type_Class_382 = new class_382();
      this.jdField_field_778_of_type_Class_382.a();
    }
    this.jdField_field_778_of_type_Float = 0.1F;
  }
  
  private void a()
  {
    int i = 0;
    int j;
    class_386 localclass_3861;
    for (ElementInformation localElementInformation1 = -1; localElementInformation1 < 17; localElementInformation1 = (byte)(localElementInformation1 + 1)) {
      for (j = -1; j < 17; j = (byte)(j + 1)) {
        for (localclass_3861 = -1; localclass_3861 < 17; localclass_3861 = (byte)(localclass_3861 + 1))
        {
          this.jdField_field_779_of_type_Class_35.b(localclass_3861, j, localElementInformation1);
          int k = a6(this.jdField_field_779_of_type_Class_35.field_453, this.jdField_field_779_of_type_Class_35.field_454, this.jdField_field_779_of_type_Class_35.field_455);
          int n;
          int i2;
          ElementInformation localElementInformation4;
          int i3;
          class_386 localclass_3862;
          int i6;
          int i7;
          int i8;
          int i12;
          class_35 localclass_353;
          if (((n = this.jdField_field_778_of_type_JavaNioShortBuffer.get(k)) != 0) && (SegmentData.valid(this.jdField_field_779_of_type_Class_35.field_453, this.jdField_field_779_of_type_Class_35.field_454, this.jdField_field_779_of_type_Class_35.field_455)) && (this.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.containsFast(i2 = SegmentData.getInfoIndex(this.jdField_field_779_of_type_Class_35))))
          {
            ElementInformation localElementInformation2 = ElementKeyMap.getInfo((short)Math.abs(n));
            localElementInformation4 = localElementInformation2;
            i3 = k;
            localclass_3862 = this;
            i6 = 63;
            i7 = localclass_3862.jdField_field_778_of_type_JavaNioShortBuffer.get(i3) < 0 ? 1 : 0;
            if (localElementInformation4.getBlockStyle() == 3) {
              i6 = 51;
            } else {
              for (i8 = 0; i8 < 6; i8++)
              {
                i12 = i7;
                int i10 = i3;
                int i9 = i8;
                class_386 localclass_3864;
                if ((localclass_353 = (localclass_3864 = localclass_3862).jdField_field_778_of_type_JavaNioShortBuffer.get(i10 + localclass_3864.jdField_field_778_of_type_ArrayOfInt[i9])) != 0)
                {
                  i10 = localclass_353;
                  ElementInformation localElementInformation5;
                  if ((((localElementInformation5 = ElementKeyMap.getInfo(FastMath.a1(localclass_353))).getBlockStyle() > 0) || ((localElementInformation5.getId() == 122) && (i10 < 0)) ? 1 : 0) != 0) {
                    break label315;
                  }
                }
                label315:
                if ((localclass_353 > 0 ? 1 : i12 != 0 ? 0 : localclass_353 != 0 ? 1 : 0) != 0) {
                  i6 = (byte)(i6 - org.schema.game.common.data.element.Element.SIDE_FLAG[i8]);
                }
              }
            }
            if ((localElementInformation4.getBlockStyle() == 1) || (localElementInformation4.getBlockStyle() == 2)) {
              i6 = 63;
            }
            k = i6;
            this.jdField_field_778_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer.a6(i2 / 3, k);
            i = (i != 0) || (k > 0) ? 1 : 0;
          }
          if (n <= 0)
          {
            localElementInformation4 = localElementInformation1;
            i3 = j;
            localclass_3862 = localclass_3861;
            i2 = ((localElementInformation4 < 16) && (i3 < 16) && (localclass_3862 < 16) && (localElementInformation4 >= 0) && (i3 >= 0) && (localclass_3862 >= 0) ? 1 : 0) == 0 ? 1 : 0;
            ElementInformation localElementInformation3 = a6(this.jdField_field_779_of_type_Class_35.field_453, this.jdField_field_779_of_type_Class_35.field_454, this.jdField_field_779_of_type_Class_35.field_455);
            for (int m = 0; m < 6; m++)
            {
              i8 = i2;
              i7 = org.schema.game.common.data.element.Element.OPPOSITE_SIDE[m];
              i6 = m;
              class_35 localclass_351 = org.schema.game.common.data.element.Element.DIRECTIONSb[m];
              localElementInformation4 = localElementInformation3;
              class_35 localclass_352 = this.jdField_field_779_of_type_Class_35;
              localclass_3862 = this;
              localclass_353 = (byte)(localclass_352.field_453 + localclass_351.field_453);
              class_35 localclass_354 = (byte)(localclass_352.field_454 + localclass_351.field_454);
              int i11 = (byte)(localclass_352.field_455 + localclass_351.field_455);
              if (i8 != 0)
              {
                i8 = i11;
                localclass_352 = localclass_354;
                localclass_351 = localclass_353;
                if (((i8 < 17) && (localclass_352 < 17) && (localclass_351 < 17) && (i8 >= -1) && (localclass_352 >= -1) && (localclass_351 >= -1) ? 1 : 0) == 0) {}
              }
              else
              {
                i12 = localElementInformation4 + localclass_3862.jdField_field_778_of_type_ArrayOfInt[i6];
                if (localclass_3862.jdField_field_778_of_type_JavaNioShortBuffer.get(i12) != 0)
                {
                  float f7 = i12 * 6;
                  FloatBuffer localFloatBuffer = localclass_3862.jdField_field_779_of_type_JavaNioFloatBuffer;
                  int i5 = localclass_354 = localElementInformation4 * 6;
                  float f9 = localFloatBuffer.get(i5);
                  localFloatBuffer = localclass_3862.jdField_field_779_of_type_JavaNioFloatBuffer;
                  i5 = localclass_354;
                  float f1 = localFloatBuffer.get(i5 + 1);
                  localFloatBuffer = localclass_3862.jdField_field_779_of_type_JavaNioFloatBuffer;
                  i5 = localclass_354;
                  float f3 = localFloatBuffer.get(i5 + 2);
                  i12 = i6;
                  localFloatBuffer = localclass_3862.jdField_field_778_of_type_JavaNioFloatBuffer;
                  i5 = localclass_354;
                  float f8 = localFloatBuffer.get(i5 + i12);
                  float f5 = f8;
                  float f4 = f3;
                  float f2 = f1;
                  float f6 = f9;
                  f3 = f7;
                  Object localObject = localclass_3862;
                  f6 = f5 + f6;
                  f2 = f5 + f2;
                  f4 = f5 + f4;
                  int i4 = f3 * 3 + i7 * 3;
                  localObject.jdField_field_780_of_type_JavaNioFloatBuffer.put(i4, f6);
                  localObject.jdField_field_780_of_type_JavaNioFloatBuffer.put(i4 + 1, f2);
                  localObject.jdField_field_780_of_type_JavaNioFloatBuffer.put(i4 + 2, f4);
                }
              }
            }
          }
        }
      }
    }
    if (i != 0)
    {
      ((class_657)this.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegment()).c2(true);
      for (localElementInformation1 = 0; localElementInformation1 < 16; localElementInformation1 = (byte)(localElementInformation1 + 1)) {
        for (j = 0; j < 16; j = (byte)(j + 1)) {
          for (localclass_3861 = 0; localclass_3861 < 16; localclass_3861 = (byte)(localclass_3861 + 1))
          {
            this.jdField_field_778_of_type_Class_48.b(localclass_3861, j, localElementInformation1);
            class_48 localclass_48 = this.jdField_field_778_of_type_Class_48;
            class_386 localclass_3863 = this;
            int i1;
            if ((this.jdField_field_778_of_type_JavaNioShortBuffer.get(a6(localclass_48.field_475, localclass_48.field_476, localclass_48.field_477)) != 0) && ((i1 = localclass_3863.jdField_field_778_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer.a3((byte)localclass_48.field_475, (byte)localclass_48.field_476, (byte)localclass_48.field_477)) > 0))
            {
              if (i1 >= 32)
              {
                localclass_3863.jdField_field_778_of_type_Class_392.a3(localclass_48, 5, localclass_3863);
                i1 = (byte)(i1 - 32);
              }
              if (i1 >= 16)
              {
                localclass_3863.jdField_field_778_of_type_Class_392.a3(localclass_48, 4, localclass_3863);
                i1 = (byte)(i1 - 16);
              }
              if (i1 >= 8)
              {
                localclass_3863.jdField_field_778_of_type_Class_392.a2(localclass_48, localclass_3863);
                i1 = (byte)(i1 - 8);
              }
              if (i1 >= 4)
              {
                localclass_3863.jdField_field_778_of_type_Class_392.d(localclass_48, localclass_3863);
                i1 = (byte)(i1 - 4);
              }
              if (i1 >= 2)
              {
                localclass_3863.jdField_field_778_of_type_Class_392.b(localclass_48, localclass_3863);
                i1 = (byte)(i1 - 2);
              }
              if (i1 > 0) {
                localclass_3863.jdField_field_778_of_type_Class_392.c(localclass_48, localclass_3863);
              }
            }
          }
        }
      }
      return;
    }
    ((class_657)this.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegment()).c2(false);
  }
  
  public final void a1(SegmentData paramSegmentData, CubeMeshBufferContainer paramCubeMeshBufferContainer)
  {
    this.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData = paramSegmentData;
    this.jdField_field_778_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer = paramCubeMeshBufferContainer;
    paramCubeMeshBufferContainer = this;
    this.jdField_field_778_of_type_Boolean = false;
    for (Segment localSegment1 = -1; localSegment1 < 17; localSegment1 = (byte)(localSegment1 + 1)) {
      for (byte b1 = -1; b1 < 17; b1 = (byte)(b1 + 1)) {
        for (byte b2 = -1; b2 < 17; b2 = (byte)(b2 + 1))
        {
          Segment localSegment3 = localSegment1;
          byte b4 = b1;
          byte b3 = b2;
          CubeMeshBufferContainer localCubeMeshBufferContainer;
          (localCubeMeshBufferContainer = paramCubeMeshBufferContainer).jdField_field_779_of_type_Class_35.b(b3, b4, localSegment3);
          Segment localSegment4;
          if (SegmentData.allNeighborsInside(b3, b4, localSegment3)) {
            localSegment4 = localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegment();
          } else {
            localSegment4 = localCubeMeshBufferContainer.a5(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_35, localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegment(), localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegment(), localCubeMeshBufferContainer.jdField_field_779_of_type_Class_48);
          }
          int j = a6(b3, b4, localSegment3);
          localCubeMeshBufferContainer.jdField_field_778_of_type_JavaNioShortBuffer.put(j, (short)0);
          if (localSegment4 == null)
          {
            if (localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegmentController().isInboundSegmentPos(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_48))
            {
              localCubeMeshBufferContainer.jdField_field_778_of_type_Boolean = true;
            }
            else
            {
              localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegment.a25(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_48.field_475, localCubeMeshBufferContainer.jdField_field_779_of_type_Class_48.field_476, localCubeMeshBufferContainer.jdField_field_779_of_type_Class_48.field_477);
              localSegment4 = localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegment;
            }
          }
          else
          {
            boolean bool;
            int n;
            int i1;
            if ((!(bool = localSegment4.g())) && (localSegment4.a16().containsFast(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_35)))
            {
              n = SegmentData.getInfoIndex(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_35);
              if ((i1 = localSegment4.a16().getType(n)) == 62) {
                localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer.field_1665.put((short)n);
              }
              if ((ElementKeyMap.getInfo(i1).isBlended()) || ((i1 == 122) && (!localSegment4.a16().isActive(n)))) {
                localCubeMeshBufferContainer.jdField_field_778_of_type_JavaNioShortBuffer.put(j, (short)-i1);
              } else {
                localCubeMeshBufferContainer.jdField_field_778_of_type_JavaNioShortBuffer.put(j, i1);
              }
            }
            else
            {
              n = SegmentData.getInfoIndex(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_35);
            }
            j = bool ? 0 : localSegment4.a16().getType(n);
            if ((bool) || (j == 0) || (ElementKeyMap.getInfo(j).isBlended()) || (!ElementKeyMap.getInfo(j).isPhysical(localSegment4.a16().isActive(n))))
            {
              class_48 localclass_48 = localCubeMeshBufferContainer.jdField_field_779_of_type_Class_48;
              Segment localSegment5 = localSegment4;
              class_35 localclass_35 = localCubeMeshBufferContainer.jdField_field_779_of_type_Class_35;
              if (localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegmentController().hasNeighborElements(localSegment5, localclass_35.field_453, localclass_35.field_454, localclass_35.field_455, localclass_48))
              {
                localSegment5 = localSegment3;
                int i4 = 0;
                int k = a6(b3, b4, localSegment5) * 6;
                Object localObject;
                float f2;
                for (class_388 localclass_388 : localCubeMeshBufferContainer.jdField_field_778_of_type_Class_382.field_774)
                {
                  localSegment3 = 0;
                  int i3 = 0;
                  for (i4 = 0; i3 < localclass_388.jdField_field_781_of_type_ArrayOfByte.length; i4++)
                  {
                    b3 = localclass_388.jdField_field_781_of_type_ArrayOfByte[i3];
                    b4 = localclass_388.jdField_field_781_of_type_ArrayOfByte[(i3 + 1)];
                    int i5 = localclass_388.jdField_field_781_of_type_ArrayOfByte[(i3 + 2)];
                    i1 = (byte)(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_35.field_453 + b3);
                    b3 = (byte)(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_35.field_454 + b4);
                    b4 = (byte)(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_35.field_455 + i5);
                    localCubeMeshBufferContainer.jdField_field_778_of_type_Class_35.b(i1, b3, b4);
                    if ((!jdField_field_779_of_type_Boolean) && (localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegment() == null)) {
                      throw new AssertionError();
                    }
                    Segment localSegment2;
                    if ((localSegment2 = localCubeMeshBufferContainer.a5(localCubeMeshBufferContainer.jdField_field_778_of_type_Class_35, localSegment4, localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegment(), localCubeMeshBufferContainer.jdField_field_779_of_type_Class_48)) == null)
                    {
                      if (!localCubeMeshBufferContainer.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegmentController().isInboundSegmentPos(localCubeMeshBufferContainer.jdField_field_779_of_type_Class_48)) {
                        break;
                      }
                      localCubeMeshBufferContainer.jdField_field_778_of_type_Boolean = true;
                      break;
                    }
                    if (!localSegment2.g())
                    {
                      int i = SegmentData.getInfoIndex(localCubeMeshBufferContainer.jdField_field_778_of_type_Class_35.field_453, localCubeMeshBufferContainer.jdField_field_778_of_type_Class_35.field_454, localCubeMeshBufferContainer.jdField_field_778_of_type_Class_35.field_455);
                      short s;
                      if ((s = localSegment2.a16().getType(i)) != 0)
                      {
                        if (((localObject = ElementKeyMap.getInfo(s)).isBlended()) || (!((ElementInformation)localObject).isPhysical(localSegment2.a16().isActive(i)))) {
                          break;
                        }
                        localSegment3 = 1;
                        if ((!((ElementInformation)localObject).isLightSource()) || (!localSegment2.a16().isActive(i))) {
                          break;
                        }
                        float f1 = localclass_388.field_782[i4] * 2.3F;
                        localCubeMeshBufferContainer.a2(k, ((ElementInformation)localObject).getLightSourceColor().field_615 * f1);
                        localCubeMeshBufferContainer.a2(k + 1, ((ElementInformation)localObject).getLightSourceColor().field_616 * f1);
                        localCubeMeshBufferContainer.a2(k + 2, ((ElementInformation)localObject).getLightSourceColor().field_617 * f1);
                        break;
                      }
                    }
                    i3 += 3;
                  }
                  if (localSegment3 == 0) {
                    for (i3 = 0; i3 < 6; i3++)
                    {
                      f2 = localclass_388.jdField_field_781_of_type_ArrayOfFloat[i3];
                      i4 = k + i3;
                      (localObject = localCubeMeshBufferContainer).jdField_field_778_of_type_JavaNioFloatBuffer.put(i4, ((class_386)localObject).jdField_field_778_of_type_JavaNioFloatBuffer.get(i4) + f2);
                    }
                  }
                }
                for (int m = 0; m < 6; m++)
                {
                  f2 = localCubeMeshBufferContainer.jdField_field_778_of_type_Float;
                  i4 = k + m;
                  (localObject = localCubeMeshBufferContainer).jdField_field_778_of_type_JavaNioFloatBuffer.put(i4, Math.min(1.0F, ((class_386)localObject).jdField_field_778_of_type_JavaNioFloatBuffer.get(i4) * f2));
                }
              }
            }
          }
        }
      }
    }
    paramCubeMeshBufferContainer.a();
    if (this.jdField_field_778_of_type_Boolean)
    {
      ((class_657)paramSegmentData.getSegment()).field_34 = true;
      ((class_657)paramSegmentData.getSegment()).field_35 = System.currentTimeMillis();
    }
  }
  
  private void a2(int paramInt, float paramFloat)
  {
    this.jdField_field_779_of_type_JavaNioFloatBuffer.put(paramInt, Math.min(1.0F, this.jdField_field_779_of_type_JavaNioFloatBuffer.get(paramInt) + paramFloat));
  }
  
  public final CubeMeshBufferContainer a3()
  {
    return this.jdField_field_778_of_type_OrgSchemaGameClientViewCubesCubeMeshBufferContainer;
  }
  
  public final boolean a4(class_48 paramclass_48, int paramInt, Vector3f paramVector3f)
  {
    if (this.jdField_field_778_of_type_JavaNioShortBuffer.get(paramclass_48 = a6(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477)) != 0)
    {
      paramclass_48 = paramclass_48 * 6 * 3 + paramInt * 3;
      paramVector3f.field_615 = this.jdField_field_780_of_type_JavaNioFloatBuffer.get(paramclass_48);
      paramVector3f.field_616 = this.jdField_field_780_of_type_JavaNioFloatBuffer.get(paramclass_48 + 1);
      paramVector3f.field_617 = this.jdField_field_780_of_type_JavaNioFloatBuffer.get(paramclass_48 + 2);
      return true;
    }
    return false;
  }
  
  private Segment a5(class_35 paramclass_35, Segment paramSegment1, Segment paramSegment2, class_48 paramclass_48)
  {
    if (((paramclass_35.field_453 | paramclass_35.field_454 | paramclass_35.field_455) & 0xF0) == 0) {
      return paramSegment1;
    }
    int i = paramclass_35.field_453 >> 4;
    int j = paramclass_35.field_454 >> 4;
    int k = paramclass_35.field_455 >> 4;
    this.jdField_field_780_of_type_Class_48.field_475 = (paramSegment1.field_35.field_475 + i);
    this.jdField_field_780_of_type_Class_48.field_476 = (paramSegment1.field_35.field_476 + j);
    this.jdField_field_780_of_type_Class_48.field_477 = (paramSegment1.field_35.field_477 + k);
    paramclass_48.field_475 = (paramSegment1.field_34.field_475 + (i << 4));
    paramclass_48.field_476 = (paramSegment1.field_34.field_476 + (j << 4));
    paramclass_48.field_477 = (paramSegment1.field_34.field_477 + (k << 4));
    paramclass_35.field_453 = ((byte)(paramclass_35.field_453 & 0xF));
    paramclass_35.field_454 = ((byte)(paramclass_35.field_454 & 0xF));
    paramclass_35.field_455 = ((byte)(paramclass_35.field_455 & 0xF));
    paramclass_48 = paramSegment2;
    paramSegment2 = this.jdField_field_780_of_type_Class_48;
    paramSegment1 = paramclass_48;
    paramclass_35 = this;
    i = paramSegment2.field_475 - paramclass_48.field_35.field_475 + 4;
    j = paramSegment2.field_476 - paramclass_48.field_35.field_476 + 4;
    paramSegment2 = (paramSegment2.field_477 - paramclass_48.field_35.field_477 + 4) * 81 + j * 9 + i;
    if (paramclass_35.jdField_field_778_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[paramSegment2] == null) {
      if ((paramSegment1 = paramclass_35.jdField_field_778_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSegmentController().getSegmentBuffer().a5(paramSegment1)) != null) {
        paramclass_35.jdField_field_778_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[paramSegment2] = paramSegment1;
      } else {
        return null;
      }
    }
    return paramclass_35.jdField_field_778_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[paramSegment2];
  }
  
  private static int a6(int paramInt1, int paramInt2, int paramInt3)
  {
    return (paramInt3 + 1) * 324 + (paramInt2 + 1) * 18 + (paramInt1 + 1);
  }
  
  public final void a7(CubeMeshBufferContainer paramCubeMeshBufferContainer)
  {
    for (int i = 0; i < 34992; i++)
    {
      this.jdField_field_778_of_type_JavaNioFloatBuffer.put(i, 0.0F);
      this.jdField_field_779_of_type_JavaNioFloatBuffer.put(i, 0.0F);
      this.jdField_field_778_of_type_JavaNioByteBuffer.put(i, (byte)0);
    }
    for (i = 0; i < 104976; i++) {
      this.jdField_field_780_of_type_JavaNioFloatBuffer.put(i, 0.0F);
    }
    Arrays.fill(this.jdField_field_778_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment, null);
    paramCubeMeshBufferContainer.a4();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_386
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */