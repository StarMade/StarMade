import java.io.PrintStream;
import java.util.Random;
import javax.vecmath.Vector3f;
import org.schema.game.client.view.cubes.noise.Simplex;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public abstract class class_1186
  extends class_1177
{
  private int[] jdField_field_248_of_type_ArrayOfInt;
  private long jdField_field_248_of_type_Long;
  protected class_1123[] field_248;
  private class_48 jdField_field_248_of_type_Class_48 = new class_48();
  private boolean jdField_field_248_of_type_Boolean = false;
  private class_48 field_257 = new class_48();
  private class_48 field_258 = new class_48();
  protected Random field_248;
  private final Vector3f jdField_field_248_of_type_JavaxVecmathVector3f = new Vector3f();
  private final class_48 field_259 = new class_48();
  
  public abstract void a4();
  
  public class_1186(long paramLong)
  {
    this.jdField_field_248_of_type_Long = paramLong;
  }
  
  private void a5(Segment paramSegment)
  {
    this.field_259.field_475 = (this.field_258.field_475 - this.field_257.field_475 << 4);
    this.field_259.field_476 = (this.field_258.field_476 - this.field_257.field_476 << 4);
    this.field_259.field_477 = (this.field_258.field_477 - this.field_257.field_477 << 4);
    class_48 localclass_48;
    (localclass_48 = Segment.a9(paramSegment.field_34.field_475 + this.field_259.field_475 / 2, paramSegment.field_34.field_476 + this.field_259.field_476 / 2, paramSegment.field_34.field_477 + this.field_259.field_477 / 2, this.jdField_field_248_of_type_Class_48)).field_475 <<= 4;
    localclass_48.field_476 <<= 4;
    localclass_48.field_477 <<= 4;
    this.jdField_field_248_of_type_JavaxVecmathVector3f.set(1.0F / this.field_259.field_475, 1.0F / this.field_259.field_476, 1.0F / this.field_259.field_477);
    for (int i = 0; i < 16; i = (byte)(i + 1)) {
      for (int j = 0; j < 16; j = (byte)(j + 1)) {
        for (int k = 0; k < 16; k = (byte)(k + 1))
        {
          Vector3f localVector3f = this.jdField_field_248_of_type_JavaxVecmathVector3f;
          float f4 = i + localclass_48.field_477;
          float f3 = j + localclass_48.field_476;
          float f2 = k + localclass_48.field_475;
          class_1186 localclass_1186 = this;
          float f6;
          if ((f6 = f3 * localVector3f.field_616) < 0.8D) {
            f3 = 1.0F;
          } else {
            f3 = 1.0F - (f6 - 0.8F) * 10.0F;
          }
          float f7 = f2 * localVector3f.field_615;
          f4 *= localVector3f.field_617;
          f2 = (f7 - 0.5F) * 1.5F;
          float f5 = (f6 - 1.0F) * 0.8F;
          float f8 = (f4 - 0.5F) * 1.5F;
          f2 = 0.1F / (f2 * f2 + f5 * f5 + f8 * f8);
          f2 = Simplex.b(f7, f6 * 0.5F, f4, localclass_1186.jdField_field_248_of_type_ArrayOfInt) * f2 * f3;
          float f1 = Simplex.a((f7 + 1.0F) * 2.8F, (f6 + 1.0F) * 2.8F, (f4 + 1.0F) * 2.8F, localclass_1186.jdField_field_248_of_type_ArrayOfInt);
          if ((f1 = f6 > 0.9D ? 0.0F : f2 * (f1 * f1)) > 3.1F)
          {
            short s = a6(f1);
            a12(k, j, i, paramSegment, Short.valueOf(s));
          }
        }
      }
    }
    paramSegment.a15().getSegmentBuffer().b6(paramSegment);
  }
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    if (!this.jdField_field_248_of_type_Boolean)
    {
      System.currentTimeMillis();
      this.jdField_field_248_of_type_JavaUtilRandom = new Random(this.jdField_field_248_of_type_Long);
      this.field_257.b1(paramSegmentController.getMinPos());
      this.field_258.b1(paramSegmentController.getMaxPos());
      this.jdField_field_248_of_type_ArrayOfInt = Simplex.a1(this.jdField_field_248_of_type_JavaUtilRandom);
      a4();
      this.jdField_field_248_of_type_Boolean = true;
    }
    this.jdField_field_248_of_type_JavaUtilRandom.setSeed(this.jdField_field_248_of_type_Long + paramSegment.field_34.hashCode());
    if ((paramSegment.field_34.field_475 <= this.field_258.field_475 << 4) && (paramSegment.field_34.field_476 <= this.field_258.field_476 << 4) && (paramSegment.field_34.field_477 <= this.field_258.field_477 << 4) && (paramSegment.field_34.field_475 >= this.field_257.field_475 << 4) && (paramSegment.field_34.field_476 >= this.field_257.field_476 << 4) && (paramSegment.field_34.field_477 >= this.field_257.field_477 << 4))
    {
      if (paramSegment.field_34.a4() > 180.0F) {
        System.err.println("[WORLDFACTORY] exception: anomaly detected on FLOATING ROCK " + paramSegmentController + ": " + paramSegmentController.getMinPos() + " - " + paramSegmentController.getMaxPos() + " Trying to build " + paramSegment.field_34);
      }
      a5(paramSegment);
      if (!paramSegment.g()) {
        for (paramSegmentController = 0; paramSegmentController < this.jdField_field_248_of_type_ArrayOfClass_1123.length; paramSegmentController++) {
          for (int i = 0; i < 8; i++) {
            this.jdField_field_248_of_type_ArrayOfClass_1123[paramSegmentController].a(paramSegment.a16(), this.jdField_field_248_of_type_JavaUtilRandom, paramSegment.field_34.field_475 + this.jdField_field_248_of_type_ArrayOfClass_1123[paramSegmentController].a1(this.jdField_field_248_of_type_JavaUtilRandom), paramSegment.field_34.field_476 + this.jdField_field_248_of_type_JavaUtilRandom.nextInt(16), paramSegment.field_34.field_477 + this.jdField_field_248_of_type_ArrayOfClass_1123[paramSegmentController].b(this.jdField_field_248_of_type_JavaUtilRandom));
          }
        }
      }
    }
  }
  
  protected abstract short a6(float paramFloat);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1186
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */