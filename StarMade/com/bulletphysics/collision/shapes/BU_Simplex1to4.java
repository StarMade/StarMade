package com.bulletphysics.collision.shapes;

import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import javax.vecmath.Vector3f;

public class BU_Simplex1to4
  extends PolyhedralConvexShape
{
  protected int numVertices = 0;
  protected Vector3f[] vertices = new Vector3f[4];
  
  public BU_Simplex1to4() {}
  
  public BU_Simplex1to4(Vector3f pt0)
  {
    addVertex(pt0);
  }
  
  public BU_Simplex1to4(Vector3f pt0, Vector3f pt1)
  {
    addVertex(pt0);
    addVertex(pt1);
  }
  
  public BU_Simplex1to4(Vector3f pt0, Vector3f pt1, Vector3f pt2)
  {
    addVertex(pt0);
    addVertex(pt1);
    addVertex(pt2);
  }
  
  public BU_Simplex1to4(Vector3f pt0, Vector3f pt1, Vector3f pt2, Vector3f pt3)
  {
    addVertex(pt0);
    addVertex(pt1);
    addVertex(pt2);
    addVertex(pt3);
  }
  
  public void reset()
  {
    this.numVertices = 0;
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.TETRAHEDRAL_SHAPE_PROXYTYPE;
  }
  
  public void addVertex(Vector3f local_pt)
  {
    if (this.vertices[this.numVertices] == null) {
      this.vertices[this.numVertices] = new Vector3f();
    }
    this.vertices[(this.numVertices++)] = local_pt;
    recalcLocalAabb();
  }
  
  public int getNumVertices()
  {
    return this.numVertices;
  }
  
  public int getNumEdges()
  {
    switch (this.numVertices)
    {
    case 0: 
      return 0;
    case 1: 
      return 0;
    case 2: 
      return 1;
    case 3: 
      return 3;
    case 4: 
      return 6;
    }
    return 0;
  }
  
  public void getEdge(int local_i, Vector3f local_pa, Vector3f local_pb)
  {
    switch (this.numVertices)
    {
    case 2: 
      local_pa.set(this.vertices[0]);
      local_pb.set(this.vertices[1]);
      break;
    case 3: 
      switch (local_i)
      {
      case 0: 
        local_pa.set(this.vertices[0]);
        local_pb.set(this.vertices[1]);
        break;
      case 1: 
        local_pa.set(this.vertices[1]);
        local_pb.set(this.vertices[2]);
        break;
      case 2: 
        local_pa.set(this.vertices[2]);
        local_pb.set(this.vertices[0]);
      }
      break;
    case 4: 
      switch (local_i)
      {
      case 0: 
        local_pa.set(this.vertices[0]);
        local_pb.set(this.vertices[1]);
        break;
      case 1: 
        local_pa.set(this.vertices[1]);
        local_pb.set(this.vertices[2]);
        break;
      case 2: 
        local_pa.set(this.vertices[2]);
        local_pb.set(this.vertices[0]);
        break;
      case 3: 
        local_pa.set(this.vertices[0]);
        local_pb.set(this.vertices[3]);
        break;
      case 4: 
        local_pa.set(this.vertices[1]);
        local_pb.set(this.vertices[3]);
        break;
      case 5: 
        local_pa.set(this.vertices[2]);
        local_pb.set(this.vertices[3]);
      }
      break;
    }
  }
  
  public void getVertex(int local_i, Vector3f vtx)
  {
    vtx.set(this.vertices[local_i]);
  }
  
  public int getNumPlanes()
  {
    switch (this.numVertices)
    {
    case 0: 
      return 0;
    case 1: 
      return 0;
    case 2: 
      return 0;
    case 3: 
      return 2;
    case 4: 
      return 4;
    }
    return 0;
  }
  
  public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int local_i) {}
  
  public int getIndex(int local_i)
  {
    return 0;
  }
  
  public boolean isInside(Vector3f local_pt, float tolerance)
  {
    return false;
  }
  
  public String getName()
  {
    return "BU_Simplex1to4";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.BU_Simplex1to4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */