import javax.vecmath.Vector3f;

public class class_1353
{
  public static Vector3f[][] a(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    return a1(paramVector3f1, paramVector3f2, a2());
  }
  
  public static Vector3f[][] a1(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f[][] paramArrayOfVector3f)
  {
    if ((!field_1534) && (paramArrayOfVector3f == null)) {
      throw new AssertionError();
    }
    if ((!field_1534) && (paramArrayOfVector3f.length != 6)) {
      throw new AssertionError(paramArrayOfVector3f.length);
    }
    if ((!field_1534) && (paramArrayOfVector3f[0].length != 4)) {
      throw new AssertionError(paramArrayOfVector3f[0].length);
    }
    paramArrayOfVector3f[0][0].set(paramVector3f2.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[0][1].set(paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[0][2].set(paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[0][3].set(paramVector3f2.field_615, paramVector3f1.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[1][3].set(paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[1][2].set(paramVector3f1.field_615, paramVector3f2.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[1][1].set(paramVector3f1.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[1][0].set(paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[2][3].set(paramVector3f1.field_615, paramVector3f2.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[2][2].set(paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[2][1].set(paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[2][0].set(paramVector3f1.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[3][0].set(paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[3][1].set(paramVector3f2.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[3][2].set(paramVector3f2.field_615, paramVector3f1.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[3][3].set(paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[4][0].set(paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[4][1].set(paramVector3f2.field_615, paramVector3f1.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[4][2].set(paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[4][3].set(paramVector3f1.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    paramArrayOfVector3f[5][3].set(paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[5][2].set(paramVector3f2.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[5][1].set(paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f1.field_617);
    paramArrayOfVector3f[5][0].set(paramVector3f1.field_615, paramVector3f2.field_616, paramVector3f1.field_617);
    return paramArrayOfVector3f;
  }
  
  public static Vector3f[][] a2()
  {
    Vector3f[][] arrayOfVector3f = new Vector3f[6][4];
    for (int i = 0; i < arrayOfVector3f.length; i++) {
      for (int j = 0; j < arrayOfVector3f[i].length; j++) {
        arrayOfVector3f[i][j] = new Vector3f();
      }
    }
    return arrayOfVector3f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1353
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */