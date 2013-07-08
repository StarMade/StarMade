package com.bulletphysics.linearmath;

import com.bulletphysics..Stack;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class GeometryUtil
{
  public static boolean isPointInsidePlanes(ObjectArrayList<Vector4f> planeEquations, Vector3f point, float margin)
  {
    int numbrushes = planeEquations.size();
    for (int local_i = 0; local_i < numbrushes; local_i++)
    {
      Vector4f local_N1 = (Vector4f)planeEquations.getQuick(local_i);
      float dist = VectorUtil.dot3(local_N1, point) + local_N1.field_599 - margin;
      if (dist > 0.0F) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean areVerticesBehindPlane(Vector4f planeNormal, ObjectArrayList<Vector3f> vertices, float margin)
  {
    int numvertices = vertices.size();
    for (int local_i = 0; local_i < numvertices; local_i++)
    {
      Vector3f local_N1 = (Vector3f)vertices.getQuick(local_i);
      float dist = VectorUtil.dot3(planeNormal, local_N1) + planeNormal.field_599 - margin;
      if (dist > 0.0F) {
        return false;
      }
    }
    return true;
  }
  
  private static boolean notExist(Vector4f planeEquation, ObjectArrayList<Vector4f> planeEquations)
  {
    int numbrushes = planeEquations.size();
    for (int local_i = 0; local_i < numbrushes; local_i++)
    {
      Vector4f local_N1 = (Vector4f)planeEquations.getQuick(local_i);
      if (VectorUtil.dot3(planeEquation, local_N1) > 0.999F) {
        return false;
      }
    }
    return true;
  }
  
  public static void getPlaneEquationsFromVertices(ObjectArrayList<Vector3f> arg0, ObjectArrayList<Vector4f> arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Vector4f();
      Vector4f planeEquation = localStack.get$javax$vecmath$Vector4f();
      Vector3f edge0 = localStack.get$javax$vecmath$Vector3f();
      Vector3f edge1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      int numvertices = vertices.size();
      for (int local_i = 0; local_i < numvertices; local_i++)
      {
        Vector3f local_N1 = (Vector3f)vertices.getQuick(local_i);
        for (int local_j = local_i + 1; local_j < numvertices; local_j++)
        {
          Vector3f local_N2 = (Vector3f)vertices.getQuick(local_j);
          for (int local_k = local_j + 1; local_k < numvertices; local_k++)
          {
            Vector3f local_N3 = (Vector3f)vertices.getQuick(local_k);
            edge0.sub(local_N2, local_N1);
            edge1.sub(local_N3, local_N1);
            float normalSign = 1.0F;
            for (int local_ww = 0; local_ww < 2; local_ww++)
            {
              tmp.cross(edge0, edge1);
              planeEquation.field_596 = (normalSign * tmp.field_615);
              planeEquation.field_597 = (normalSign * tmp.field_616);
              planeEquation.field_598 = (normalSign * tmp.field_617);
              if (VectorUtil.lengthSquared3(planeEquation) > 1.0E-004F)
              {
                VectorUtil.normalize3(planeEquation);
                if (notExist(planeEquation, planeEquationsOut))
                {
                  planeEquation.field_599 = (-VectorUtil.dot3(planeEquation, local_N1));
                  if (areVerticesBehindPlane(planeEquation, vertices, 0.01F)) {
                    planeEquationsOut.add(new Vector4f(planeEquation));
                  }
                }
              }
              normalSign = -1.0F;
            }
          }
        }
      }
      return;
    }
    finally
    {
      .Stack tmp284_282 = localStack;
      tmp284_282.pop$javax$vecmath$Vector3f();
      tmp284_282.pop$javax$vecmath$Vector4f();
    }
  }
  
  public static void getVerticesFromPlaneEquations(ObjectArrayList<Vector4f> arg0, ObjectArrayList<Vector3f> arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f n2n3 = localStack.get$javax$vecmath$Vector3f();
      Vector3f n3n1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f n1n2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f potentialVertex = localStack.get$javax$vecmath$Vector3f();
      int numbrushes = planeEquations.size();
      for (int local_i = 0; local_i < numbrushes; local_i++)
      {
        Vector4f local_N1 = (Vector4f)planeEquations.getQuick(local_i);
        for (int local_j = local_i + 1; local_j < numbrushes; local_j++)
        {
          Vector4f local_N2 = (Vector4f)planeEquations.getQuick(local_j);
          for (int local_k = local_j + 1; local_k < numbrushes; local_k++)
          {
            Vector4f local_N3 = (Vector4f)planeEquations.getQuick(local_k);
            VectorUtil.cross3(n2n3, local_N2, local_N3);
            VectorUtil.cross3(n3n1, local_N3, local_N1);
            VectorUtil.cross3(n1n2, local_N1, local_N2);
            if ((n2n3.lengthSquared() > 1.0E-004F) && (n3n1.lengthSquared() > 1.0E-004F) && (n1n2.lengthSquared() > 1.0E-004F))
            {
              float quotient = VectorUtil.dot3(local_N1, n2n3);
              if (Math.abs(quotient) > 1.0E-006F)
              {
                quotient = -1.0F / quotient;
                n2n3.scale(local_N1.field_599);
                n3n1.scale(local_N2.field_599);
                n1n2.scale(local_N3.field_599);
                potentialVertex.set(n2n3);
                potentialVertex.add(n3n1);
                potentialVertex.add(n1n2);
                potentialVertex.scale(quotient);
                if (isPointInsidePlanes(planeEquations, potentialVertex, 0.01F)) {
                  verticesOut.add(new Vector3f(potentialVertex));
                }
              }
            }
          }
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
 * Qualified Name:     com.bulletphysics.linearmath.GeometryUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */