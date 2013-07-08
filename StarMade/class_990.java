import org.lwjgl.opengl.GL11;

public final class class_990
{
  private static final float[][] jdField_field_1275_of_type_Array2dOfFloat = { { -0.5F, -0.5F, -0.5F }, { 0.5F, -0.5F, -0.5F }, { 0.5F, 0.5F, -0.5F }, { -0.5F, 0.5F, -0.5F }, { -0.5F, -0.5F, 0.5F }, { 0.5F, -0.5F, 0.5F }, { 0.5F, 0.5F, 0.5F }, { -0.5F, 0.5F, 0.5F } };
  private static final float[][] field_1276 = { { 0.0F, 0.0F, -1.0F }, { 0.0F, 0.0F, 1.0F }, { 0.0F, -1.0F, 0.0F }, { 0.0F, 1.0F, 0.0F }, { -1.0F, 0.0F, 0.0F }, { 1.0F, 0.0F, 0.0F } };
  private static final byte[][] jdField_field_1275_of_type_Array2dOfByte = { { 0, 3, 2, 1 }, { 4, 5, 6, 7 }, { 0, 1, 5, 4 }, { 3, 7, 6, 2 }, { 0, 4, 7, 3 }, { 1, 2, 6, 5 } };
  
  public static void a()
  {
    for (int i = 0; i < 6; i++)
    {
      GL11.glBegin(2);
      for (int j = 0; j < 4; j++)
      {
        float[] arrayOfFloat = jdField_field_1275_of_type_Array2dOfFloat[jdField_field_1275_of_type_Array2dOfByte[i][j]];
        GL11.glNormal3f(field_1276[i][0], field_1276[i][1], field_1276[i][2]);
        GL11.glVertex3f(arrayOfFloat[0] * 100.0F, arrayOfFloat[1] * 100.0F, arrayOfFloat[2] * 100.0F);
      }
      GL11.glEnd();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_990
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */