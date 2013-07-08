package paulscode.sound;

public class ListenerData
{
  public Vector3D position;
  public Vector3D lookAt;
  public Vector3D field_1833;
  public Vector3D velocity;
  public float angle = 0.0F;
  
  public ListenerData()
  {
    this.position = new Vector3D(0.0F, 0.0F, 0.0F);
    this.lookAt = new Vector3D(0.0F, 0.0F, -1.0F);
    this.field_1833 = new Vector3D(0.0F, 1.0F, 0.0F);
    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
    this.angle = 0.0F;
  }
  
  public ListenerData(float local_pX, float local_pY, float local_pZ, float local_lX, float local_lY, float local_lZ, float local_uX, float local_uY, float local_uZ, float local_a)
  {
    this.position = new Vector3D(local_pX, local_pY, local_pZ);
    this.lookAt = new Vector3D(local_lX, local_lY, local_lZ);
    this.field_1833 = new Vector3D(local_uX, local_uY, local_uZ);
    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
    this.angle = local_a;
  }
  
  public ListenerData(Vector3D local_p, Vector3D local_l, Vector3D local_u, float local_a)
  {
    this.position = local_p.clone();
    this.lookAt = local_l.clone();
    this.field_1833 = local_u.clone();
    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
    this.angle = local_a;
  }
  
  public void setData(float local_pX, float local_pY, float local_pZ, float local_lX, float local_lY, float local_lZ, float local_uX, float local_uY, float local_uZ, float local_a)
  {
    this.position.field_2107 = local_pX;
    this.position.field_2108 = local_pY;
    this.position.field_2109 = local_pZ;
    this.lookAt.field_2107 = local_lX;
    this.lookAt.field_2108 = local_lY;
    this.lookAt.field_2109 = local_lZ;
    this.field_1833.field_2107 = local_uX;
    this.field_1833.field_2108 = local_uY;
    this.field_1833.field_2109 = local_uZ;
    this.angle = local_a;
  }
  
  public void setData(Vector3D local_p, Vector3D local_l, Vector3D local_u, float local_a)
  {
    this.position.field_2107 = local_p.field_2107;
    this.position.field_2108 = local_p.field_2108;
    this.position.field_2109 = local_p.field_2109;
    this.lookAt.field_2107 = local_l.field_2107;
    this.lookAt.field_2108 = local_l.field_2108;
    this.lookAt.field_2109 = local_l.field_2109;
    this.field_1833.field_2107 = local_u.field_2107;
    this.field_1833.field_2108 = local_u.field_2108;
    this.field_1833.field_2109 = local_u.field_2109;
    this.angle = local_a;
  }
  
  public void setData(ListenerData local_l)
  {
    this.position.field_2107 = local_l.position.field_2107;
    this.position.field_2108 = local_l.position.field_2108;
    this.position.field_2109 = local_l.position.field_2109;
    this.lookAt.field_2107 = local_l.lookAt.field_2107;
    this.lookAt.field_2108 = local_l.lookAt.field_2108;
    this.lookAt.field_2109 = local_l.lookAt.field_2109;
    this.field_1833.field_2107 = local_l.field_1833.field_2107;
    this.field_1833.field_2108 = local_l.field_1833.field_2108;
    this.field_1833.field_2109 = local_l.field_1833.field_2109;
    this.angle = local_l.angle;
  }
  
  public void setPosition(float local_x, float local_y, float local_z)
  {
    this.position.field_2107 = local_x;
    this.position.field_2108 = local_y;
    this.position.field_2109 = local_z;
  }
  
  public void setPosition(Vector3D local_p)
  {
    this.position.field_2107 = local_p.field_2107;
    this.position.field_2108 = local_p.field_2108;
    this.position.field_2109 = local_p.field_2109;
  }
  
  public void setOrientation(float local_lX, float local_lY, float local_lZ, float local_uX, float local_uY, float local_uZ)
  {
    this.lookAt.field_2107 = local_lX;
    this.lookAt.field_2108 = local_lY;
    this.lookAt.field_2109 = local_lZ;
    this.field_1833.field_2107 = local_uX;
    this.field_1833.field_2108 = local_uY;
    this.field_1833.field_2109 = local_uZ;
  }
  
  public void setOrientation(Vector3D local_l, Vector3D local_u)
  {
    this.lookAt.field_2107 = local_l.field_2107;
    this.lookAt.field_2108 = local_l.field_2108;
    this.lookAt.field_2109 = local_l.field_2109;
    this.field_1833.field_2107 = local_u.field_2107;
    this.field_1833.field_2108 = local_u.field_2108;
    this.field_1833.field_2109 = local_u.field_2109;
  }
  
  public void setVelocity(Vector3D local_v)
  {
    this.velocity.field_2107 = local_v.field_2107;
    this.velocity.field_2108 = local_v.field_2108;
    this.velocity.field_2109 = local_v.field_2109;
  }
  
  public void setVelocity(float local_x, float local_y, float local_z)
  {
    this.velocity.field_2107 = local_x;
    this.velocity.field_2108 = local_y;
    this.velocity.field_2109 = local_z;
  }
  
  public void setAngle(float local_a)
  {
    this.angle = local_a;
    this.lookAt.field_2107 = (-1.0F * (float)Math.sin(this.angle));
    this.lookAt.field_2109 = (-1.0F * (float)Math.cos(this.angle));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.ListenerData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */