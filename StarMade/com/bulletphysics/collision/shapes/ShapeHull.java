package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.convexhull.HullDesc;
import com.bulletphysics.linearmath.convexhull.HullFlags;
import com.bulletphysics.linearmath.convexhull.HullLibrary;
import com.bulletphysics.linearmath.convexhull.HullResult;
import com.bulletphysics.util.IntArrayList;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

public class ShapeHull
{
  protected ObjectArrayList<Vector3f> vertices = new ObjectArrayList();
  protected IntArrayList indices = new IntArrayList();
  protected int numIndices;
  protected ConvexShape shape;
  protected ObjectArrayList<Vector3f> unitSpherePoints = new ObjectArrayList();
  private static int NUM_UNITSPHERE_POINTS = 42;
  private static ObjectArrayList<Vector3f> constUnitSpherePoints = new ObjectArrayList();
  
  public ShapeHull(ConvexShape shape)
  {
    this.shape = shape;
    this.vertices.clear();
    this.indices.clear();
    this.numIndices = 0;
    MiscUtil.resize(this.unitSpherePoints, NUM_UNITSPHERE_POINTS + 20, Vector3f.class);
    for (int local_i = 0; local_i < constUnitSpherePoints.size(); local_i++) {
      ((Vector3f)this.unitSpherePoints.getQuick(local_i)).set((Tuple3f)constUnitSpherePoints.getQuick(local_i));
    }
  }
  
  public boolean buildHull(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f norm = localStack.get$javax$vecmath$Vector3f();
      int numSampleDirections = NUM_UNITSPHERE_POINTS;
      int numPDA = this.shape.getNumPreferredPenetrationDirections();
      if (numPDA != 0) {
        for (int local_i = 0; local_i < numPDA; local_i++)
        {
          this.shape.getPreferredPenetrationDirection(local_i, norm);
          ((Vector3f)this.unitSpherePoints.getQuick(numSampleDirections)).set(norm);
          numSampleDirections++;
        }
      }
      ObjectArrayList<Vector3f> numPDA = new ObjectArrayList();
      MiscUtil.resize(numPDA, NUM_UNITSPHERE_POINTS + 20, Vector3f.class);
      for (int local_i = 0; local_i < numSampleDirections; local_i++) {
        this.shape.localGetSupportingVertex((Vector3f)this.unitSpherePoints.getQuick(local_i), (Vector3f)numPDA.getQuick(local_i));
      }
      HullDesc local_i = new HullDesc();
      local_i.flags = HullFlags.TRIANGLES;
      local_i.vcount = numSampleDirections;
      local_i.vertices = numPDA;
      HullLibrary local_hl = new HullLibrary();
      HullResult local_hr = new HullResult();
      if (!local_hl.createConvexHull(local_i, local_hr)) {
        return false;
      }
      MiscUtil.resize(this.vertices, local_hr.numOutputVertices, Vector3f.class);
      for (int local_i1 = 0; local_i1 < local_hr.numOutputVertices; local_i1++) {
        ((Vector3f)this.vertices.getQuick(local_i1)).set((Tuple3f)local_hr.outputVertices.getQuick(local_i1));
      }
      this.numIndices = local_hr.numIndices;
      MiscUtil.resize(this.indices, this.numIndices, 0);
      for (int local_i1 = 0; local_i1 < this.numIndices; local_i1++) {
        this.indices.set(local_i1, local_hr.indices.get(local_i1));
      }
      local_hl.releaseResult(local_hr);
      return true;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public int numTriangles()
  {
    return this.numIndices / 3;
  }
  
  public int numVertices()
  {
    return this.vertices.size();
  }
  
  public int numIndices()
  {
    return this.numIndices;
  }
  
  public ObjectArrayList<Vector3f> getVertexPointer()
  {
    return this.vertices;
  }
  
  public IntArrayList getIndexPointer()
  {
    return this.indices;
  }
  
  static
  {
    constUnitSpherePoints.add(new Vector3f(0.0F, -0.0F, -1.0F));
    constUnitSpherePoints.add(new Vector3f(0.723608F, -0.525725F, -0.447219F));
    constUnitSpherePoints.add(new Vector3f(-0.276388F, -0.850649F, -0.447219F));
    constUnitSpherePoints.add(new Vector3f(-0.894426F, -0.0F, -0.447216F));
    constUnitSpherePoints.add(new Vector3f(-0.276388F, 0.850649F, -0.44722F));
    constUnitSpherePoints.add(new Vector3f(0.723608F, 0.525725F, -0.447219F));
    constUnitSpherePoints.add(new Vector3f(0.276388F, -0.850649F, 0.44722F));
    constUnitSpherePoints.add(new Vector3f(-0.723608F, -0.525725F, 0.447219F));
    constUnitSpherePoints.add(new Vector3f(-0.723608F, 0.525725F, 0.447219F));
    constUnitSpherePoints.add(new Vector3f(0.276388F, 0.850649F, 0.447219F));
    constUnitSpherePoints.add(new Vector3f(0.894426F, 0.0F, 0.447216F));
    constUnitSpherePoints.add(new Vector3f(-0.0F, 0.0F, 1.0F));
    constUnitSpherePoints.add(new Vector3f(0.425323F, -0.309011F, -0.850654F));
    constUnitSpherePoints.add(new Vector3f(-0.162456F, -0.499995F, -0.850654F));
    constUnitSpherePoints.add(new Vector3f(0.262869F, -0.809012F, -0.525738F));
    constUnitSpherePoints.add(new Vector3f(0.425323F, 0.309011F, -0.850654F));
    constUnitSpherePoints.add(new Vector3f(0.850648F, -0.0F, -0.525736F));
    constUnitSpherePoints.add(new Vector3f(-0.52573F, -0.0F, -0.850652F));
    constUnitSpherePoints.add(new Vector3f(-0.68819F, -0.499997F, -0.525736F));
    constUnitSpherePoints.add(new Vector3f(-0.162456F, 0.499995F, -0.850654F));
    constUnitSpherePoints.add(new Vector3f(-0.68819F, 0.499997F, -0.525736F));
    constUnitSpherePoints.add(new Vector3f(0.262869F, 0.809012F, -0.525738F));
    constUnitSpherePoints.add(new Vector3f(0.951058F, 0.309013F, 0.0F));
    constUnitSpherePoints.add(new Vector3f(0.951058F, -0.309013F, 0.0F));
    constUnitSpherePoints.add(new Vector3f(0.587786F, -0.809017F, 0.0F));
    constUnitSpherePoints.add(new Vector3f(0.0F, -1.0F, 0.0F));
    constUnitSpherePoints.add(new Vector3f(-0.587786F, -0.809017F, 0.0F));
    constUnitSpherePoints.add(new Vector3f(-0.951058F, -0.309013F, -0.0F));
    constUnitSpherePoints.add(new Vector3f(-0.951058F, 0.309013F, -0.0F));
    constUnitSpherePoints.add(new Vector3f(-0.587786F, 0.809017F, -0.0F));
    constUnitSpherePoints.add(new Vector3f(-0.0F, 1.0F, -0.0F));
    constUnitSpherePoints.add(new Vector3f(0.587786F, 0.809017F, -0.0F));
    constUnitSpherePoints.add(new Vector3f(0.68819F, -0.499997F, 0.525736F));
    constUnitSpherePoints.add(new Vector3f(-0.262869F, -0.809012F, 0.525738F));
    constUnitSpherePoints.add(new Vector3f(-0.850648F, 0.0F, 0.525736F));
    constUnitSpherePoints.add(new Vector3f(-0.262869F, 0.809012F, 0.525738F));
    constUnitSpherePoints.add(new Vector3f(0.68819F, 0.499997F, 0.525736F));
    constUnitSpherePoints.add(new Vector3f(0.52573F, 0.0F, 0.850652F));
    constUnitSpherePoints.add(new Vector3f(0.162456F, -0.499995F, 0.850654F));
    constUnitSpherePoints.add(new Vector3f(-0.425323F, -0.309011F, 0.850654F));
    constUnitSpherePoints.add(new Vector3f(-0.425323F, 0.309011F, 0.850654F));
    constUnitSpherePoints.add(new Vector3f(0.162456F, 0.499995F, 0.850654F));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.ShapeHull
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */