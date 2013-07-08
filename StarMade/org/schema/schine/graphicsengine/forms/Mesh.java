package org.schema.schine.graphicsengine.forms;

import class_1002;
import class_1386;
import class_1393;
import class_1432;
import class_949;
import class_984;
import class_988;
import class_996;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.schema.schine.graphicsengine.core.GlUtil;

public class Mesh
  extends class_996
{
  public class_1002[] field_89;
  private Vector3f[] jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f;
  private Vector3f[] jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f;
  private boolean jdField_field_89_of_type_Boolean = false;
  private IntBuffer jdField_field_93_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private IntBuffer jdField_field_94_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private IntBuffer jdField_field_95_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private IntBuffer field_96;
  private IntBuffer field_97;
  private IntBuffer jdField_field_100_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  public int field_96;
  private int jdField_field_89_of_type_Int;
  public int field_97;
  private int jdField_field_90_of_type_Int;
  private boolean jdField_field_90_of_type_Boolean = true;
  private class_1432[] jdField_field_89_of_type_ArrayOfClass_1432;
  private class_1386 jdField_field_89_of_type_Class_1386;
  private boolean jdField_field_92_of_type_Boolean;
  private static int jdField_field_92_of_type_Int;
  private int jdField_field_93_of_type_Int = 0;
  public IntBuffer field_90;
  public IntBuffer field_92;
  public FloatBuffer field_92;
  public FloatBuffer field_93;
  private static int jdField_field_94_of_type_Int;
  private int jdField_field_100_of_type_Int = 4;
  private boolean jdField_field_93_of_type_Boolean;
  private Vector3f jdField_field_92_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public static void a174(Mesh paramMesh)
  {
    if ((paramMesh.jdField_field_93_of_type_Int == 0) && (jdField_field_92_of_type_Int > 0)) {
      return;
    }
    if (paramMesh.jdField_field_93_of_type_Int == 0) {
      jdField_field_92_of_type_Int += 1;
    }
    jdField_field_94_of_type_Int += 1;
    if ((paramMesh.jdField_field_93_of_type_Int != 0) && (jdField_field_94_of_type_Int % 5 != 0)) {
      return;
    }
    long l1 = System.currentTimeMillis();
    if (paramMesh.jdField_field_93_of_type_Int == 0)
    {
      paramMesh.jdField_field_93_of_type_Int += 1;
      return;
    }
    if (paramMesh.jdField_field_93_of_type_Int == 1)
    {
      paramMesh.jdField_field_93_of_type_Int += 1;
      return;
    }
    if (paramMesh.jdField_field_93_of_type_Int == 2)
    {
      paramMesh.jdField_field_93_of_type_Int += 1;
      return;
    }
    l1 = System.currentTimeMillis() - l1;
    long l2 = System.currentTimeMillis();
    if (paramMesh.jdField_field_93_of_type_Int == 3)
    {
      paramMesh.jdField_field_93_of_type_Int += 1;
      return;
    }
    l2 = System.currentTimeMillis() - l2;
    long l3 = System.currentTimeMillis();
    l3 = System.currentTimeMillis() - l3;
    long l4 = System.currentTimeMillis();
    if (paramMesh.jdField_field_93_of_type_Int == 4)
    {
      if ((!jdField_field_95_of_type_Boolean) && (paramMesh.jdField_field_90_of_type_JavaNioFloatBuffer.position() != 0)) {
        throw new AssertionError();
      }
      a175(2, paramMesh);
      paramMesh.jdField_field_93_of_type_Int += 1;
      return;
    }
    if (paramMesh.jdField_field_93_of_type_Int == 5)
    {
      if ((!jdField_field_95_of_type_Boolean) && (paramMesh.jdField_field_92_of_type_JavaNioFloatBuffer.position() != 0)) {
        throw new AssertionError();
      }
      a175(4, paramMesh);
      paramMesh.jdField_field_93_of_type_Int += 1;
      return;
    }
    if (paramMesh.jdField_field_93_of_type_Int == 6)
    {
      if ((!jdField_field_95_of_type_Boolean) && (paramMesh.jdField_field_93_of_type_JavaNioFloatBuffer.position() != 0)) {
        throw new AssertionError();
      }
      a175(3, paramMesh);
      paramMesh.jdField_field_93_of_type_Int += 1;
      return;
    }
    if (paramMesh.jdField_field_93_of_type_Int == 7)
    {
      if ((!jdField_field_95_of_type_Boolean) && (paramMesh.field_89.position() != 0)) {
        throw new AssertionError();
      }
      paramMesh.field_89.capacity();
      a175(1, paramMesh);
      paramMesh.jdField_field_93_of_type_Int += 1;
      return;
    }
    l4 = System.currentTimeMillis() - l4;
    if (paramMesh.jdField_field_93_of_type_Int == 8)
    {
      paramMesh.jdField_field_96_of_type_Int = 3;
      jdField_field_92_of_type_Int -= 1;
      System.err.println("BOUND " + paramMesh.b14() + " to VBO(" + paramMesh.jdField_field_93_of_type_JavaNioIntBuffer.get(0) + "): faces: " + paramMesh.jdField_field_97_of_type_Int + ", indices: " + paramMesh.field_89.capacity() + " type: " + paramMesh.jdField_field_96_of_type_Int + ", alloc: " + l1 + ", prep: " + l2 + ", gen: " + l3 + ", bufferList: " + l4 + ", total: " + (l1 + l2 + l3 + l4) + ", currently building: " + jdField_field_92_of_type_Int);
      paramMesh.field_94 = true;
    }
    GL15.glBindBuffer(34962, 0);
  }
  
  private static void a175(int paramInt, Mesh paramMesh)
  {
    switch (paramInt)
    {
    case 1: 
      if (paramMesh.jdField_field_95_of_type_JavaNioIntBuffer.get(0) == 0) {
        GL15.glGenBuffers(paramMesh.jdField_field_95_of_type_JavaNioIntBuffer);
      }
      GL15.glBindBuffer(34963, paramMesh.jdField_field_95_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34963, paramMesh.field_89, 35040);
      GL15.glBindBuffer(34963, 0);
      break;
    case 5: 
      if (paramMesh.jdField_field_95_of_type_JavaNioIntBuffer.get(0) == 0) {
        GL15.glGenBuffers(paramMesh.jdField_field_95_of_type_JavaNioIntBuffer);
      }
      GL15.glBindBuffer(34963, paramMesh.jdField_field_97_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34963, paramMesh.jdField_field_90_of_type_JavaNioIntBuffer, 35040);
      GL15.glBindBuffer(34963, 0);
      break;
    case 6: 
      if (paramMesh.jdField_field_95_of_type_JavaNioIntBuffer.get(0) == 0) {
        GL15.glGenBuffers(paramMesh.jdField_field_95_of_type_JavaNioIntBuffer);
      }
      GL15.glBindBuffer(34963, paramMesh.jdField_field_96_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34963, paramMesh.jdField_field_92_of_type_JavaNioIntBuffer, 35040);
      GL15.glBindBuffer(34963, 0);
      break;
    case 2: 
      GL15.glGenBuffers(paramMesh.jdField_field_93_of_type_JavaNioIntBuffer);
      GL15.glBindBuffer(34962, paramMesh.jdField_field_93_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34962, paramMesh.jdField_field_90_of_type_JavaNioFloatBuffer, 35044);
      GL15.glBindBuffer(34962, 0);
      break;
    case 4: 
      GL15.glGenBuffers(paramMesh.jdField_field_94_of_type_JavaNioIntBuffer);
      GL15.glBindBuffer(34962, paramMesh.jdField_field_94_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34962, paramMesh.jdField_field_92_of_type_JavaNioFloatBuffer, 35044);
      GL15.glBindBuffer(34962, 0);
      break;
    case 3: 
      GL15.glGenBuffers(paramMesh.jdField_field_100_of_type_JavaNioIntBuffer);
      GL15.glBindBuffer(34962, paramMesh.jdField_field_100_of_type_JavaNioIntBuffer.get(0));
      GL15.glBufferData(34962, paramMesh.jdField_field_93_of_type_JavaNioFloatBuffer, 35044);
      GL15.glBindBuffer(34962, 0);
    }
    GlUtil.f1();
  }
  
  private static Mesh a176(String paramString)
  {
    Mesh localMesh;
    String str1 = paramString;
    String[] arrayOfString = null;
    (localMesh = new Mesh()).jdField_field_89_of_type_JavaLangString = str1;
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    if (!(paramString = new File(paramString)).exists()) {
      throw new FileNotFoundException(paramString.getPath());
    }
    BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramString));
    try
    {
      while (localBufferedReader.ready())
      {
        String str2;
        if ((str2 = localBufferedReader.readLine()).contains("vn ")) {
          localMesh.jdField_field_90_of_type_Int += 1;
        }
        if (str2.contains("vt ")) {
          localMesh.jdField_field_89_of_type_Int += 1;
        }
        if (str2.contains("clazz ")) {
          localMesh.field_95 += 1;
        }
        if (str2.contains("f ")) {
          localMesh.jdField_field_97_of_type_Int += 1;
        }
      }
      localMesh.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[localMesh.jdField_field_90_of_type_Int];
      localMesh.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[localMesh.jdField_field_89_of_type_Int];
      localMesh.jdField_field_89_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[localMesh.field_95];
      localMesh.jdField_field_89_of_type_ArrayOfClass_1002 = new class_1002[localMesh.jdField_field_97_of_type_Int];
      for (int n = 0; n < localMesh.jdField_field_89_of_type_ArrayOfClass_1002.length; n++) {
        localMesh.jdField_field_89_of_type_ArrayOfClass_1002[n] = new class_1002();
      }
      localBufferedReader = new BufferedReader(new FileReader(paramString));
      while (localBufferedReader.ready())
      {
        String str3;
        if ((str3 = localBufferedReader.readLine()).contains("vn "))
        {
          paramString = str3.split("[\\s]+");
          localMesh.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[j] = new Vector3f(Float.parseFloat(paramString[1]), Float.parseFloat(paramString[2]), Float.parseFloat(paramString[3]));
          j++;
        }
        if (str3.contains("vt "))
        {
          paramString = str3.split("[\\s]+");
          localMesh.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f[k] = new Vector3f(Float.parseFloat(paramString[1]), Float.parseFloat(paramString[2]), Float.parseFloat(paramString[3]));
          k++;
        }
        if (str3.contains("clazz "))
        {
          paramString = str3.split("[\\s]+");
          localMesh.jdField_field_89_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f(Float.parseFloat(paramString[1]), Float.parseFloat(paramString[2]), Float.parseFloat(paramString[3]));
          i++;
        }
        if (str3.contains("f "))
        {
          paramString = str3.split("[\\s]+");
          localMesh.jdField_field_89_of_type_Boolean = true;
          for (int i1 = 1; i1 < paramString.length; i1++)
          {
            arrayOfString = paramString[i1].split("/");
            localMesh.jdField_field_89_of_type_ArrayOfClass_1002[m].field_1280[(i1 - 1)] = (Integer.parseInt(arrayOfString[0]) - 1);
            localMesh.jdField_field_89_of_type_ArrayOfClass_1002[m].field_1282[(i1 - 1)] = (Integer.parseInt(arrayOfString[1]) - 1);
            localMesh.jdField_field_89_of_type_ArrayOfClass_1002[m].field_1281[(i1 - 1)] = (Integer.parseInt(arrayOfString[2]) - 1);
          }
          m++;
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    return localMesh;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      a176("data/test.obj");
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException;
    }
  }
  
  public Mesh()
  {
    this.jdField_field_96_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
    this.jdField_field_97_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
    this.jdField_field_89_of_type_Class_1393 = new class_1393();
  }
  
  public void a2()
  {
    if (a153() != null) {
      a153().b();
    }
    if (this.jdField_field_96_of_type_Int == 3)
    {
      this.jdField_field_93_of_type_JavaNioIntBuffer.rewind();
      this.jdField_field_94_of_type_JavaNioIntBuffer.rewind();
      this.jdField_field_100_of_type_JavaNioIntBuffer.rewind();
      GL15.glDeleteBuffers(this.jdField_field_93_of_type_JavaNioIntBuffer);
      GL15.glDeleteBuffers(this.jdField_field_94_of_type_JavaNioIntBuffer);
      GL15.glDeleteBuffers(this.jdField_field_100_of_type_JavaNioIntBuffer);
    }
    Iterator localIterator = this.jdField_field_89_of_type_JavaUtilList.iterator();
    while (localIterator.hasNext()) {
      ((class_984)localIterator.next()).a2();
    }
  }
  
  public void b()
  {
    if (h2()) {
      return;
    }
    if (class_949.field_1213.b1())
    {
      this.jdField_field_92_of_type_Boolean = true;
      GL11.glPolygonMode(1032, 6913);
    }
    Object localObject = this;
    if (!h2())
    {
      if (((Mesh)localObject).jdField_field_90_of_type_Boolean)
      {
        ((Mesh)localObject).c();
        ((Mesh)localObject).jdField_field_90_of_type_Boolean = false;
      }
      if (((Mesh)localObject).a154() != null) {
        ((Mesh)localObject).jdField_field_92_of_type_Boolean = ((Mesh)((Mesh)localObject).a154()).jdField_field_92_of_type_Boolean;
      }
      GlUtil.d1();
      GL11.glDisable(3553);
      GL11.glBindTexture(3553, 0);
      GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(2896);
      GL11.glEnable(2929);
      if (((Mesh)localObject).jdField_field_92_of_type_Boolean)
      {
        GL11.glDisable(3042);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(2903);
        GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      }
      else
      {
        ((Mesh)localObject).jdField_field_89_of_type_Class_1393.a2();
        GL11.glEnable(3553);
      }
    }
    switch (this.jdField_field_96_of_type_Int)
    {
    case 3: 
      if ((!jdField_field_95_of_type_Boolean) && (this.jdField_field_95_of_type_JavaNioIntBuffer.get(0) == 0)) {
        throw new AssertionError();
      }
      e();
      break;
    case 2: 
      throw new UnsupportedOperationException();
    case 0: 
    case 1: 
    default: 
      System.err.println("SOFTWARE " + this);
      d();
    }
    localObject = this.jdField_field_89_of_type_JavaUtilList.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((class_984)((Iterator)localObject).next()).b();
    }
    localObject = this;
    GlUtil.c2();
    GL11.glDisable(2884);
    ((Mesh)localObject).jdField_field_89_of_type_Class_1393.c();
    if (class_949.field_1213.b1())
    {
      GL11.glPolygonMode(1032, 6914);
      this.jdField_field_92_of_type_Boolean = false;
    }
  }
  
  public final void e()
  {
    h1();
    i();
    m1();
  }
  
  public final void f()
  {
    if ((!jdField_field_95_of_type_Boolean) && (!this.jdField_field_93_of_type_Boolean)) {
      throw new AssertionError();
    }
    GL11.glDrawElements(this.jdField_field_100_of_type_Int, this.jdField_field_97_of_type_Int * 3, 5125, 0L);
  }
  
  public final class_1386 a177()
  {
    return this.jdField_field_89_of_type_Class_1386;
  }
  
  public final class_1432[] a178()
  {
    return this.jdField_field_89_of_type_ArrayOfClass_1432;
  }
  
  public final boolean a4()
  {
    this.jdField_field_89_of_type_Class_988.b(this.jdField_field_92_of_type_JavaxVecmathVector3f);
    this.jdField_field_92_of_type_JavaxVecmathVector3f.sub(this.field_90);
    return this.jdField_field_92_of_type_JavaxVecmathVector3f.length() < 2.0F;
  }
  
  public final void g()
  {
    if ((!jdField_field_95_of_type_Boolean) && (this.jdField_field_93_of_type_Boolean)) {
      throw new AssertionError("Type is: " + this.jdField_field_96_of_type_Int);
    }
    if ((!jdField_field_95_of_type_Boolean) && (this.jdField_field_96_of_type_Int != 3)) {
      throw new AssertionError("Type is: " + this.jdField_field_96_of_type_Int);
    }
    if ((!jdField_field_95_of_type_Boolean) && (this.jdField_field_93_of_type_JavaNioIntBuffer.get(0) == 0)) {
      throw new AssertionError();
    }
    if ((!jdField_field_95_of_type_Boolean) && (this.jdField_field_94_of_type_JavaNioIntBuffer.get(0) == 0)) {
      throw new AssertionError();
    }
    if ((!jdField_field_95_of_type_Boolean) && (this.jdField_field_100_of_type_JavaNioIntBuffer.get(0) == 0)) {
      throw new AssertionError();
    }
    if ((!jdField_field_95_of_type_Boolean) && (this.jdField_field_95_of_type_JavaNioIntBuffer.get(0) == 0)) {
      throw new AssertionError();
    }
    GL20.glEnableVertexAttribArray(0);
    GL20.glEnableVertexAttribArray(1);
    GL20.glEnableVertexAttribArray(2);
    GL15.glBindBuffer(34962, this.jdField_field_93_of_type_JavaNioIntBuffer.get(0));
    GL20.glVertexAttribPointer(0, 3, 5126, false, 0, 0L);
    GL15.glBindBuffer(34962, this.jdField_field_100_of_type_JavaNioIntBuffer.get(0));
    GL20.glVertexAttribPointer(1, 3, 5126, false, 0, 0L);
    GL15.glBindBuffer(34962, this.jdField_field_94_of_type_JavaNioIntBuffer.get(0));
    GL20.glVertexAttribPointer(2, 2, 5126, false, 0, 0L);
    GL15.glBindBuffer(34963, this.jdField_field_95_of_type_JavaNioIntBuffer.get(0));
    this.jdField_field_93_of_type_Boolean = true;
  }
  
  public final void h1()
  {
    GL11.glEnableClientState(32884);
    GL15.glBindBuffer(34962, this.jdField_field_93_of_type_JavaNioIntBuffer.get(0));
    GL11.glVertexPointer(3, 5126, 0, 0L);
    GL11.glEnableClientState(32885);
    GL15.glBindBuffer(34962, this.jdField_field_100_of_type_JavaNioIntBuffer.get(0));
    GL11.glNormalPointer(5126, 0, 0L);
    GL11.glEnableClientState(32888);
    GL15.glBindBuffer(34962, this.jdField_field_94_of_type_JavaNioIntBuffer.get(0));
    GL11.glTexCoordPointer(2, 5126, 0, 0L);
    if (this.jdField_field_89_of_type_Class_1386 != null) {
      this.jdField_field_89_of_type_Class_1386.a2();
    }
    GL15.glBindBuffer(34963, this.jdField_field_95_of_type_JavaNioIntBuffer.get(0));
  }
  
  public void c() {}
  
  public final void i()
  {
    GL11.glDrawElements(this.jdField_field_100_of_type_Int, this.jdField_field_97_of_type_Int * 3, 5125, 0L);
  }
  
  public final void b13(int paramInt)
  {
    this.jdField_field_97_of_type_Int = paramInt;
  }
  
  public final void j()
  {
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  public final void a179(class_1386 paramclass_1386)
  {
    this.jdField_field_89_of_type_Class_1386 = paramclass_1386;
  }
  
  public static void k() {}
  
  public final void c4(int paramInt)
  {
    this.jdField_field_89_of_type_Int = paramInt;
  }
  
  public final void a180(class_1432[] paramArrayOfclass_1432)
  {
    this.jdField_field_89_of_type_ArrayOfClass_1432 = paramArrayOfclass_1432;
  }
  
  private void d()
  {
    GL11.glBegin(4);
    if ((this.jdField_field_89_of_type_ArrayOfClass_1002 == null) || (this.jdField_field_89_of_type_ArrayOfClass_1002.length <= 0)) {
      throw new IllegalArgumentException("Mesh " + b14() + " has no faces");
    }
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfClass_1002.length; i++)
    {
      if ((this.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f != null) && (this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1282 != null)) {
        GL11.glTexCoord2f(this.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1282[0]].field_615, this.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1282[0]].field_616);
      }
      if (this.jdField_field_89_of_type_Boolean) {
        GL11.glNormal3f(this.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1281[0]].field_615, this.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1281[0]].field_616, this.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1281[0]].field_617);
      } else {
        GL11.glNormal3f(null.field_615, null.field_616, null.field_617);
      }
      Vector3f[] arrayOfVector3f;
      GL11.glVertex3f((arrayOfVector3f = this.jdField_field_89_of_type_ArrayOfJavaxVecmathVector3f)[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1280[0]].field_615, arrayOfVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1280[0]].field_616, arrayOfVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1280[0]].field_617);
      if ((this.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f != null) && (this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1282 != null)) {
        GL11.glTexCoord2f(this.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1282[1]].field_615, this.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1282[1]].field_616);
      }
      if (this.jdField_field_89_of_type_Boolean) {
        GL11.glNormal3f(this.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1281[1]].field_615, this.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1281[1]].field_616, this.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1281[1]].field_617);
      } else {
        GL11.glNormal3f(null.field_615, null.field_616, null.field_617);
      }
      GL11.glVertex3f(arrayOfVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1280[1]].field_615, arrayOfVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1280[1]].field_616, arrayOfVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1280[1]].field_617);
      if ((this.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f != null) && (this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1282 != null)) {
        GL11.glTexCoord2f(this.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1282[2]].field_615, this.jdField_field_90_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1282[2]].field_616);
      }
      if (this.jdField_field_89_of_type_Boolean) {
        GL11.glNormal3f(this.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1281[2]].field_615, this.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1281[2]].field_616, this.jdField_field_92_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1281[2]].field_617);
      } else {
        GL11.glNormal3f(null.field_615, null.field_616, null.field_617);
      }
      GL11.glVertex3f(arrayOfVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1280[2]].field_615, arrayOfVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1280[2]].field_616, arrayOfVector3f[this.jdField_field_89_of_type_ArrayOfClass_1002[i].field_1280[2]].field_617);
    }
    GL11.glEnd();
  }
  
  public final void l()
  {
    GL15.glBindBuffer(34963, 0);
    GL15.glBindBuffer(34962, 0);
    GL20.glDisableVertexAttribArray(0);
    GL20.glDisableVertexAttribArray(1);
    GL20.glDisableVertexAttribArray(2);
    this.jdField_field_93_of_type_Boolean = false;
  }
  
  public final void m1()
  {
    GL15.glBindBuffer(34962, 0);
    GL11.glDisableClientState(32884);
    GL11.glDisableClientState(32888);
    GL11.glDisableClientState(32885);
    if (this.jdField_field_89_of_type_Class_1386 != null) {
      this.jdField_field_89_of_type_Class_1386.b();
    }
    GL15.glBindBuffer(34963, 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.graphicsengine.forms.Mesh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */