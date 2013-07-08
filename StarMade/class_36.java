import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public final class class_36
  implements Externalizable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private float field_456;
  private float field_457;
  private float field_458;
  private float field_459;
  
  public class_36()
  {
    this.field_456 = (this.field_457 = this.field_458 = this.field_459 = 1.0F);
  }
  
  private class_36(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.field_456 = paramFloat1;
    this.field_457 = paramFloat2;
    this.field_458 = paramFloat3;
    this.field_459 = 1.0F;
  }
  
  private class_36 a()
  {
    try
    {
      return (class_36)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError();
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof class_36)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (class_36)paramObject;
    if (Float.compare(this.field_456, paramObject.field_456) != 0) {
      return false;
    }
    if (Float.compare(this.field_457, paramObject.field_457) != 0) {
      return false;
    }
    if (Float.compare(this.field_458, paramObject.field_458) != 0) {
      return false;
    }
    return Float.compare(this.field_459, paramObject.field_459) == 0;
  }
  
  public final int hashCode()
  {
    int tmp14_13 = (37 + (1369 + Float.floatToIntBits(this.field_456)));
    int tmp27_26 = (tmp14_13 + (tmp14_13 * 37 + Float.floatToIntBits(this.field_457)));
    int tmp40_39 = (tmp27_26 + (tmp27_26 * 37 + Float.floatToIntBits(this.field_458)));
    return tmp40_39 + (tmp40_39 * 37 + Float.floatToIntBits(this.field_459));
  }
  
  public final void readExternal(ObjectInput paramObjectInput)
  {
    this.field_456 = paramObjectInput.readFloat();
    this.field_457 = paramObjectInput.readFloat();
    this.field_458 = paramObjectInput.readFloat();
    this.field_459 = paramObjectInput.readFloat();
  }
  
  public final String toString()
  {
    return "com.jme.renderer.ColorRGBA: [R=" + this.field_456 + ", G=" + this.field_457 + ", B=" + this.field_458 + ", A=" + this.field_459 + "]";
  }
  
  public final void writeExternal(ObjectOutput paramObjectOutput)
  {
    paramObjectOutput.writeFloat(this.field_456);
    paramObjectOutput.writeFloat(this.field_457);
    paramObjectOutput.writeFloat(this.field_458);
    paramObjectOutput.writeFloat(this.field_459);
  }
  
  static
  {
    new class_36(0.0F, 0.0F, 0.0F);
    new class_36(1.0F, 1.0F, 1.0F);
    new class_36(0.2F, 0.2F, 0.2F);
    new class_36(0.5F, 0.5F, 0.5F);
    new class_36(0.8F, 0.8F, 0.8F);
    new class_36(1.0F, 0.0F, 0.0F);
    new class_36(0.0F, 1.0F, 0.0F);
    new class_36(0.0F, 0.0F, 1.0F);
    new class_36(1.0F, 1.0F, 0.0F);
    new class_36(1.0F, 0.0F, 1.0F);
    new class_36(0.0F, 1.0F, 1.0F);
    new class_36(0.9843137F, 0.509804F, 0.0F);
    new class_36(0.254902F, 0.1568628F, 0.09803922F);
    new class_36(1.0F, 0.68F, 0.68F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_36
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */