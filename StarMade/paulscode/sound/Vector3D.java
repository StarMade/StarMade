package paulscode.sound;

public class Vector3D
{
  public float field_2107;
  public float field_2108;
  public float field_2109;
  
  public Vector3D()
  {
    this.field_2107 = 0.0F;
    this.field_2108 = 0.0F;
    this.field_2109 = 0.0F;
  }
  
  public Vector3D(float local_nx, float local_ny, float local_nz)
  {
    this.field_2107 = local_nx;
    this.field_2108 = local_ny;
    this.field_2109 = local_nz;
  }
  
  public Vector3D clone()
  {
    return new Vector3D(this.field_2107, this.field_2108, this.field_2109);
  }
  
  public Vector3D cross(Vector3D local_A, Vector3D local_B)
  {
    return new Vector3D(local_A.field_2108 * local_B.field_2109 - local_B.field_2108 * local_A.field_2109, local_A.field_2109 * local_B.field_2107 - local_B.field_2109 * local_A.field_2107, local_A.field_2107 * local_B.field_2108 - local_B.field_2107 * local_A.field_2108);
  }
  
  public Vector3D cross(Vector3D local_B)
  {
    return new Vector3D(this.field_2108 * local_B.field_2109 - local_B.field_2108 * this.field_2109, this.field_2109 * local_B.field_2107 - local_B.field_2109 * this.field_2107, this.field_2107 * local_B.field_2108 - local_B.field_2107 * this.field_2108);
  }
  
  public float dot(Vector3D local_A, Vector3D local_B)
  {
    return local_A.field_2107 * local_B.field_2107 + local_A.field_2108 * local_B.field_2108 + local_A.field_2109 * local_B.field_2109;
  }
  
  public float dot(Vector3D local_B)
  {
    return this.field_2107 * local_B.field_2107 + this.field_2108 * local_B.field_2108 + this.field_2109 * local_B.field_2109;
  }
  
  public Vector3D add(Vector3D local_A, Vector3D local_B)
  {
    return new Vector3D(local_A.field_2107 + local_B.field_2107, local_A.field_2108 + local_B.field_2108, local_A.field_2109 + local_B.field_2109);
  }
  
  public Vector3D add(Vector3D local_B)
  {
    return new Vector3D(this.field_2107 + local_B.field_2107, this.field_2108 + local_B.field_2108, this.field_2109 + local_B.field_2109);
  }
  
  public Vector3D subtract(Vector3D local_A, Vector3D local_B)
  {
    return new Vector3D(local_A.field_2107 - local_B.field_2107, local_A.field_2108 - local_B.field_2108, local_A.field_2109 - local_B.field_2109);
  }
  
  public Vector3D subtract(Vector3D local_B)
  {
    return new Vector3D(this.field_2107 - local_B.field_2107, this.field_2108 - local_B.field_2108, this.field_2109 - local_B.field_2109);
  }
  
  public float length()
  {
    return (float)Math.sqrt(this.field_2107 * this.field_2107 + this.field_2108 * this.field_2108 + this.field_2109 * this.field_2109);
  }
  
  public void normalize()
  {
    double local_t = Math.sqrt(this.field_2107 * this.field_2107 + this.field_2108 * this.field_2108 + this.field_2109 * this.field_2109);
    this.field_2107 = ((float)(this.field_2107 / local_t));
    this.field_2108 = ((float)(this.field_2108 / local_t));
    this.field_2109 = ((float)(this.field_2109 / local_t));
  }
  
  public String toString()
  {
    return "Vector3D (" + this.field_2107 + ", " + this.field_2108 + ", " + this.field_2109 + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.Vector3D
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */