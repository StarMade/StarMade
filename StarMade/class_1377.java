import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;

public class class_1377
{
  public FloatBuffer field_1578;
  Map jdField_field_1578_of_type_JavaUtilMap;
  public class_1369 field_1578;
  public String field_1578;
  private String field_1579;
  int jdField_field_1578_of_type_Int;
  public boolean field_1578;
  
  public class_1377(String paramString1, String paramString2)
  {
    this.jdField_field_1578_of_type_JavaLangString = paramString1;
    this.field_1579 = paramString2;
    a1();
    this.jdField_field_1578_of_type_JavaUtilMap = new HashMap();
  }
  
  public void a(int paramInt) {}
  
  public final void a1()
  {
    try
    {
      GlUtil.f1();
      class_1377 localclass_1377 = this;
      GlUtil.f1();
      int k = GL20.glCreateShader(35633);
      GlUtil.f1();
      Object localObject1 = new BufferedReader(new InputStreamReader(class_73.field_134.a2(localclass_1377.jdField_field_1578_of_type_JavaLangString)));
      GlUtil.f1();
      String str;
      for (Object localObject2 = ""; (str = ((BufferedReader)localObject1).readLine()) != null; localObject2 = (String)localObject2 + str + "\n") {}
      (localObject2 = GlUtil.a5((localObject1 = ((String)localObject2).getBytes()).length, 0)).put((byte[])localObject1);
      ((ByteBuffer)localObject2).rewind();
      GL20.glShaderSource(k, (ByteBuffer)localObject2);
      GlUtil.f1();
      GL20.glCompileShader(k);
      GlUtil.f1();
      if ((i = GL20.glGetShader(k, 35713)) != 1)
      {
        localObject2 = BufferUtils.createIntBuffer(1);
        GL20.glGetShader(k, 35716, (IntBuffer)localObject2);
        GlUtil.f1();
        localObject2 = GL20.glGetShaderInfoLog(k, ((IntBuffer)localObject2).get(0));
        System.err.println("[SHADER] ERROR COMPILING VERTEX SHADER " + localclass_1377.jdField_field_1578_of_type_JavaLangString + " STATUS: " + i);
        System.err.println("LOG: " + (String)localObject2);
        throw new ErrorDialogException("\n" + localclass_1377.jdField_field_1578_of_type_JavaLangString + " \n\n" + (String)localObject2 + "\n");
      }
      int i = this.jdField_field_1578_of_type_JavaLangString == null ? -1 : k;
      GlUtil.f1();
      int j = a2();
      GlUtil.f1();
      GlUtil.f1();
      if (this.jdField_field_1578_of_type_Int != 0) {
        GL20.glDeleteProgram(this.jdField_field_1578_of_type_Int);
      }
      GlUtil.f1();
      this.jdField_field_1578_of_type_Int = GL20.glCreateProgram();
      GlUtil.f1();
      if (i > 0)
      {
        GL20.glAttachShader(this.jdField_field_1578_of_type_Int, i);
        GlUtil.f1();
      }
      if (j > 0)
      {
        GL20.glAttachShader(this.jdField_field_1578_of_type_Int, j);
        GlUtil.f1();
      }
      GlUtil.f1();
      GL20.glLinkProgram(this.jdField_field_1578_of_type_Int);
      GlUtil.f1();
      a(this.jdField_field_1578_of_type_Int);
      GlUtil.f1();
      GL20.glLinkProgram(this.jdField_field_1578_of_type_Int);
      GlUtil.f1();
      localIntBuffer = BufferUtils.createIntBuffer(1);
      GlUtil.f1();
      GL20.glGetProgram(this.jdField_field_1578_of_type_Int, 35714, localIntBuffer);
      GlUtil.f1();
      Object localObject3;
      if (localIntBuffer.get(0) != 1)
      {
        localObject3 = BufferUtils.createIntBuffer(1);
        GL20.glGetProgram(this.jdField_field_1578_of_type_Int, 35716, (IntBuffer)localObject3);
        localObject3 = GL20.glGetProgramInfoLog(this.jdField_field_1578_of_type_Int, ((IntBuffer)localObject3).get(0));
        throw new ErrorDialogException("\n" + this.jdField_field_1578_of_type_JavaLangString + ", \n" + this.field_1579 + " \n\n" + (String)localObject3 + "\nLINK STATUS: " + localIntBuffer.get(0));
      }
      GL20.glValidateProgram(this.jdField_field_1578_of_type_Int);
      localIntBuffer.rewind();
      GL20.glGetProgram(this.jdField_field_1578_of_type_Int, 35715, localIntBuffer);
      GlUtil.f1();
      if (localIntBuffer.get(0) != 1)
      {
        localObject3 = BufferUtils.createIntBuffer(1);
        GL20.glGetProgram(this.jdField_field_1578_of_type_Int, 35716, (IntBuffer)localObject3);
        localObject3 = GL20.glGetProgramInfoLog(this.jdField_field_1578_of_type_Int, ((IntBuffer)localObject3).get(0));
        throw new ErrorDialogException("\n" + this.jdField_field_1578_of_type_JavaLangString + ", \n" + this.field_1579 + " \n\n" + (String)localObject3 + "\nLINK STATUS: " + localIntBuffer.get(0));
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localIntBuffer = null;
      localFileNotFoundException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      IntBuffer localIntBuffer = null;
      localIOException.printStackTrace();
    }
    GlUtil.f1();
    this.jdField_field_1578_of_type_Boolean = true;
  }
  
  private int a2()
  {
    if (this.field_1579 == null) {
      return -1;
    }
    int i = GL20.glCreateShader(35632);
    Object localObject2 = new BufferedReader(new InputStreamReader(class_73.field_134.a2(this.field_1579)));
    Object localObject1;
    for (String str2 = ""; (localObject1 = ((BufferedReader)localObject2).readLine()) != null; str2 = str2 + (String)localObject1 + "\n") {}
    (localObject2 = GlUtil.a5((localObject1 = str2.getBytes()).length, 0)).put((byte[])localObject1);
    ((ByteBuffer)localObject2).rewind();
    GL20.glShaderSource(i, (ByteBuffer)localObject2);
    GL20.glCompileShader(i);
    int j;
    String str1;
    if ((j = GL20.glGetShader(i, 35713)) != 1)
    {
      localObject2 = BufferUtils.createIntBuffer(1);
      GL20.glGetShader(i, 35716, (IntBuffer)localObject2);
      str1 = GL20.glGetShaderInfoLog(i, ((IntBuffer)localObject2).get(0));
      System.err.println("[SHADER] ERROR COMPILING FRAGMENT SHADER " + this.jdField_field_1578_of_type_JavaLangString + " STATUS: " + j);
      System.err.println("LOG: " + str1);
      throw new ErrorDialogException("\n" + this.field_1579 + " \n\n" + str1 + "\n");
    }
    return str1;
  }
  
  public final int a3(String paramString)
  {
    Integer localInteger;
    int i;
    if ((localInteger = (Integer)this.jdField_field_1578_of_type_JavaUtilMap.get(paramString)) == null)
    {
      i = GL20.glGetUniformLocation(this.jdField_field_1578_of_type_Int, paramString);
      this.jdField_field_1578_of_type_JavaUtilMap.put(paramString, Integer.valueOf(i));
      return i;
    }
    return i.intValue();
  }
  
  public final void b()
  {
    if ((!class_949.field_1203.b1()) || (class_949.field_1213.b1())) {
      return;
    }
    GL20.glUseProgram(this.jdField_field_1578_of_type_Int);
    if (this.jdField_field_1578_of_type_Class_1369 != null) {
      this.jdField_field_1578_of_type_Class_1369.a(this);
    }
  }
  
  public final void c()
  {
    if ((!class_949.field_1203.b1()) || (class_949.field_1213.b1())) {
      return;
    }
    GL20.glUseProgram(this.jdField_field_1578_of_type_Int);
  }
  
  public String toString()
  {
    return this.jdField_field_1578_of_type_JavaLangString + ", " + this.field_1579 + " bound to " + this.jdField_field_1578_of_type_Int + " with interface " + this.jdField_field_1578_of_type_Class_1369;
  }
  
  public final void d()
  {
    if ((!class_949.field_1203.b1()) || (class_949.field_1213.b1())) {
      return;
    }
    if (this.jdField_field_1578_of_type_Class_1369 != null) {
      this.jdField_field_1578_of_type_Class_1369.d();
    }
    GL20.glUseProgram(0);
  }
  
  public static void e()
  {
    if ((!class_949.field_1203.b1()) || (class_949.field_1213.b1())) {
      return;
    }
    GL20.glUseProgram(0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1377
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */