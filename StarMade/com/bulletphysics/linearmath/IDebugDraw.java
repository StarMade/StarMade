package com.bulletphysics.linearmath;

import com.bulletphysics..Stack;
import javax.vecmath.Vector3f;

public abstract class IDebugDraw
{
  public abstract void drawLine(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3);
  
  public void drawTriangle(Vector3f local_v0, Vector3f local_v1, Vector3f local_v2, Vector3f local_n0, Vector3f local_n1, Vector3f local_n2, Vector3f color, float alpha)
  {
    drawTriangle(local_v0, local_v1, local_v2, color, alpha);
  }
  
  public void drawTriangle(Vector3f local_v0, Vector3f local_v1, Vector3f local_v2, Vector3f color, float alpha)
  {
    drawLine(local_v0, local_v1, color);
    drawLine(local_v1, local_v2, color);
    drawLine(local_v2, local_v0, color);
  }
  
  public abstract void drawContactPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, int paramInt, Vector3f paramVector3f3);
  
  public abstract void reportErrorWarning(String paramString);
  
  public abstract void draw3dText(Vector3f paramVector3f, String paramString);
  
  public abstract void setDebugMode(int paramInt);
  
  public abstract int getDebugMode();
  
  public void drawAabb(Vector3f arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f(local_to);
      halfExtents.sub(from);
      halfExtents.scale(0.5F);
      Vector3f center = localStack.get$javax$vecmath$Vector3f(local_to);
      center.add(from);
      center.scale(0.5F);
      Vector3f edgecoord = localStack.get$javax$vecmath$Vector3f();
      edgecoord.set(1.0F, 1.0F, 1.0F);
      Vector3f local_pa = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_pb = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < 4; local_i++)
      {
        for (int local_j = 0; local_j < 3; local_j++)
        {
          local_pa.set(edgecoord.field_615 * halfExtents.field_615, edgecoord.field_616 * halfExtents.field_616, edgecoord.field_617 * halfExtents.field_617);
          local_pa.add(center);
          int othercoord = local_j % 3;
          VectorUtil.mulCoord(edgecoord, othercoord, -1.0F);
          local_pb.set(edgecoord.field_615 * halfExtents.field_615, edgecoord.field_616 * halfExtents.field_616, edgecoord.field_617 * halfExtents.field_617);
          local_pb.add(center);
          drawLine(local_pa, local_pb, color);
        }
        edgecoord.set(-1.0F, -1.0F, -1.0F);
        if (local_i < 3) {
          VectorUtil.mulCoord(edgecoord, local_i, -1.0F);
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.IDebugDraw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */