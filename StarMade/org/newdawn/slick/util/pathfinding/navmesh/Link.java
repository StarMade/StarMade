package org.newdawn.slick.util.pathfinding.navmesh;

public class Link
{
  private float field_1847;
  private float field_1848;
  private Space target;
  
  public Link(float local_px, float local_py, Space target)
  {
    this.field_1847 = local_px;
    this.field_1848 = local_py;
    this.target = target;
  }
  
  public float distance2(float local_tx, float local_ty)
  {
    float local_dx = local_tx - this.field_1847;
    float local_dy = local_ty - this.field_1848;
    return local_dx * local_dx + local_dy * local_dy;
  }
  
  public float getX()
  {
    return this.field_1847;
  }
  
  public float getY()
  {
    return this.field_1848;
  }
  
  public Space getTarget()
  {
    return this.target;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.navmesh.Link
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */