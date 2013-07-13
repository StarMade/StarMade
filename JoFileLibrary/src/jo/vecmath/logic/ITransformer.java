package jo.vecmath.logic;

import javax.vecmath.Matrix4f;

public interface ITransformer
{
    public Matrix4f calcTransform(Matrix4f transform);
}
