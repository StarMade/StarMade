import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Vector;
import javax.vecmath.Vector3f;
import org.w3c.dom.Document;

public abstract class class_996
  extends class_984
{
  public Vector3f field_90;
  private float jdField_field_89_of_type_Float;
  public Vector3f[] field_89;
  public int field_95;
  public IntBuffer field_89;
  public FloatBuffer field_90;
  private Document jdField_field_89_of_type_OrgW3cDomDocument;
  
  public class_996()
  {
    new Vector();
    this.field_90 = new Vector3f(0.0F, 0.0F, 0.0F);
  }
  
  public final IntBuffer a162()
  {
    return this.jdField_field_89_of_type_JavaNioIntBuffer;
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Float;
  }
  
  public final Document a163()
  {
    return this.jdField_field_89_of_type_OrgW3cDomDocument;
  }
  
  public final int a57()
  {
    return this.field_95;
  }
  
  public final void a164(IntBuffer paramIntBuffer)
  {
    this.jdField_field_89_of_type_JavaNioIntBuffer = paramIntBuffer;
  }
  
  public final void a(float paramFloat)
  {
    this.jdField_field_89_of_type_Float = paramFloat;
  }
  
  public final void a165(Document paramDocument)
  {
    this.jdField_field_89_of_type_OrgW3cDomDocument = paramDocument;
  }
  
  public final void a72(int paramInt)
  {
    this.field_95 = paramInt;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_996
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */