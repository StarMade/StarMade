import com.bulletphysics.linearmath.Transform;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.game.server.controller.EntityAlreadyExistsException;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;

final class class_1037
  extends Thread
{
  private ArrayList jdField_field_1305_of_type_JavaUtilArrayList = new ArrayList();
  
  private class_1037(class_1041 paramclass_1041) {}
  
  public final void run()
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(1024000);
    for (;;)
    {
      class_1031 localclass_1031;
      synchronized (this.jdField_field_1305_of_type_JavaUtilArrayList)
      {
        if (this.jdField_field_1305_of_type_JavaUtilArrayList.isEmpty())
        {
          try
          {
            this.jdField_field_1305_of_type_JavaUtilArrayList.wait();
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException;
          }
          continue;
        }
        localclass_1031 = (class_1031)this.jdField_field_1305_of_type_JavaUtilArrayList.remove(0);
      }
      ??? = new Transform();
      List localList = localclass_1031.jdField_field_1300_of_type_Class_1216.a7();
      ArrayList localArrayList = new ArrayList();
      for (int i = 0; i < localclass_1031.jdField_field_1300_of_type_Int; i++)
      {
        ((Transform)???).set(localclass_1031.jdField_field_1300_of_type_ComBulletphysicsLinearmathTransform);
        ((Transform)???).origin.set(((Transform)???).origin.field_615 + (float)(Math.random() - 0.5D) * 256.0F, ((Transform)???).origin.field_616 + (float)(Math.random() - 0.5D) * 256.0F, ((Transform)???).origin.field_617 + (float)(Math.random() - 0.5D) * 256.0F);
        Object localObject2;
        if (class_1070.a4(localObject2 = "MOB_" + localclass_1031.jdField_field_1300_of_type_JavaLangString + "_" + System.currentTimeMillis() + "_" + i)) {
          try
          {
            (localObject2 = class_1216.a6(this.jdField_field_1305_of_type_Class_1041, localclass_1031.jdField_field_1300_of_type_JavaLangString, (String)localObject2, (Transform)???, -1, localclass_1031.field_1302, localList, localclass_1031.field_1301, localArrayList, "<system>", localByteBuffer)).field_1379 = localclass_1031.field_1301;
            synchronized (this.jdField_field_1305_of_type_Class_1041.b())
            {
              this.jdField_field_1305_of_type_Class_1041.b().add(localObject2);
            }
          }
          catch (EntityNotFountException localEntityNotFountException)
          {
            localEntityNotFountException;
          }
          catch (IOException localIOException)
          {
            localIOException;
          }
          catch (ErrorDialogException localErrorDialogException)
          {
            localErrorDialogException;
          }
          catch (EntityAlreadyExistsException localEntityAlreadyExistsException)
          {
            localEntityAlreadyExistsException;
          }
        } else {
          System.err.println("[ADMIN] ERROR: Not a valid name: " + str);
        }
      }
    }
  }
  
  public final void a(int paramInt1, String arg2, int paramInt2, Transform paramTransform, int paramInt3, class_1216 paramclass_1216)
  {
    paramInt1 = new class_1031(paramInt1, ???, paramInt2, paramTransform, paramInt3, paramclass_1216);
    synchronized (this.jdField_field_1305_of_type_JavaUtilArrayList)
    {
      this.jdField_field_1305_of_type_JavaUtilArrayList.add(paramInt1);
      this.jdField_field_1305_of_type_JavaUtilArrayList.notify();
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1037
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */