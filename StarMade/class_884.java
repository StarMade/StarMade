import com.bulletphysics.collision.dispatch.CollisionObject;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.vecmath.Vector3f;
import org.schema.common.util.ByteUtil;
import org.schema.game.client.view.SegmentDrawer;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.SegmentBufferManager;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.SegmentOutOfBoundsException;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public class class_884
  implements class_886
{
  private final class_48 jdField_field_208_of_type_Class_48 = new class_48();
  private final class_48 jdField_field_209_of_type_Class_48 = new class_48();
  private final class_48 jdField_field_210_of_type_Class_48 = new class_48();
  private final class_48 jdField_field_211_of_type_Class_48 = new class_48();
  private int jdField_field_208_of_type_Int = 0;
  private final SegmentController jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController;
  private Segment[] jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment;
  private final class_988 jdField_field_208_of_type_Class_988;
  private SegmentBufferManager jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager;
  private long jdField_field_208_of_type_Long;
  private int jdField_field_209_of_type_Int;
  private int jdField_field_210_of_type_Int;
  private class_48 field_212 = new class_48();
  private class_35 jdField_field_208_of_type_Class_35 = new class_35();
  private class_48 field_213 = new class_48();
  private int jdField_field_211_of_type_Int = 0;
  
  public class_884(SegmentController paramSegmentController, class_48 paramclass_481, class_48 paramclass_482, SegmentBufferManager paramSegmentBufferManager)
  {
    this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_208_of_type_Class_48.b1(paramclass_481);
    this.jdField_field_209_of_type_Class_48.b1(paramclass_482);
    this.jdField_field_210_of_type_Class_48.b1(paramclass_481);
    this.jdField_field_210_of_type_Class_48.a5(16);
    this.jdField_field_211_of_type_Class_48.b1(paramclass_482);
    this.jdField_field_211_of_type_Class_48.a5(16);
    this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment = new Segment[512];
    this.jdField_field_208_of_type_Class_988 = new class_988();
    this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager = paramSegmentBufferManager;
    System.currentTimeMillis();
  }
  
  public final void a(Segment paramSegment)
  {
    synchronized (this)
    {
      if ((!jdField_field_208_of_type_Boolean) && (!paramSegment.g()) && (paramSegment.a16().getSegment() != paramSegment)) {
        throw new AssertionError();
      }
      Segment localSegment1 = paramSegment;
      class_48 localclass_48;
      Segment localSegment3 = localSegment1;
      int k = localclass_48.field_477;
      int j = localclass_48.field_476;
      int i = (localclass_48 = paramSegment.field_34).field_475;
      Object localObject1 = this;
      i = a7(i, j, k);
      Segment localSegment2 = localObject1.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[i];
      ((class_884)localObject1).jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[i] = localSegment3;
      if (localSegment2 == null)
      {
        this.jdField_field_210_of_type_Int += 1;
        this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.e();
        if (!paramSegment.g())
        {
          this.jdField_field_208_of_type_Int += 1;
          this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.f();
          b6(paramSegment);
          if ((this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.toString().contains("CSS-Schema_1367765418233"))) {
            System.err.println("SERVER UPDATED AABB " + paramSegment + "; " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getBoundingBox());
          }
          if ((!this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.toString().contains("CSS-Schema_1367765418233"))) {
            System.err.println("CLIENT UPDATED AABB " + paramSegment + "; " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getBoundingBox());
          }
        }
      }
      paramSegment.field_36 = false;
      if ((paramSegment instanceof class_657))
      {
        (localObject1 = (class_657)paramSegment).field_34 = class_663.field_934;
        ((class_20)this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider()).a13((class_657)localObject1);
      }
      c1();
      if ((paramSegment.a15().getPhysicsDataContainer().isInitialized()) && (paramSegment.a15().getPhysicsDataContainer().getObject() != null)) {
        paramSegment.a15().getPhysicsDataContainer().getObject().activate();
      }
      return;
    }
  }
  
  public final int a1()
  {
    synchronized (this)
    {
      int i = 0;
      while (this.jdField_field_210_of_type_Int > 0) {
        for (int j = 0; j < this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment.length; j++)
        {
          Segment localSegment;
          if ((localSegment = this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[j]) != null)
          {
            if ((localSegment = a22(localSegment)) != null)
            {
              SegmentData localSegmentData = localSegment.a16();
              if (localSegment.a16() != null) {
                synchronized (localSegment.a16())
                {
                  this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a27(localSegment, localSegmentData, false);
                  i++;
                }
              }
            }
            this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[j] = null;
          }
          if ((!jdField_field_208_of_type_Boolean) && (this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[j] != null)) {
            throw new AssertionError();
          }
        }
      }
      this.jdField_field_208_of_type_Class_988.a8();
      return localObject1;
    }
  }
  
  public final int b()
  {
    int i = 0;
    while (this.jdField_field_210_of_type_Int > 0) {
      for (int j = 0; j < this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment.length; j++)
      {
        Segment localSegment;
        if ((localSegment = this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[j]) != null)
        {
          if ((localSegment = a22(localSegment)) != null)
          {
            SegmentData localSegmentData = localSegment.a16();
            if (localSegment.a16() != null) {
              synchronized (localSegment.a16())
              {
                this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a27(localSegment, localSegmentData, true);
                i++;
              }
            }
          }
          this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[j] = null;
        }
        if ((!jdField_field_208_of_type_Boolean) && (this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[j] != null)) {
          throw new AssertionError();
        }
      }
    }
    this.jdField_field_208_of_type_Class_988.a8();
    return localObject1;
  }
  
  public final boolean a2(int paramInt1, int paramInt2, int paramInt3)
  {
    return a4(paramInt1, paramInt2, paramInt3) != null;
  }
  
  public final boolean a3(class_48 paramclass_48)
  {
    return a5(paramclass_48) != null;
  }
  
  public final boolean b1(class_48 paramclass_48)
  {
    byte b1 = (byte)ByteUtil.d(paramclass_48.field_475);
    byte b2 = (byte)ByteUtil.d(paramclass_48.field_476);
    byte b3 = (byte)ByteUtil.d(paramclass_48.field_477);
    int i = ByteUtil.b(paramclass_48.field_475);
    int j = ByteUtil.b(paramclass_48.field_476);
    paramclass_48 = ByteUtil.b(paramclass_48.field_477);
    i <<= 4;
    j <<= 4;
    paramclass_48 <<= 4;
    if (this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a2(i, j, paramclass_48))
    {
      if (!(paramclass_48 = this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a4(i, j, paramclass_48)).g()) {
        return paramclass_48.a16().contains(b1, b2, b3);
      }
      return false;
    }
    this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a18(i, j, paramclass_48);
    return false;
  }
  
  public final Segment a4(int paramInt1, int paramInt2, int paramInt3)
  {
    return this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[a7(paramInt1, paramInt2, paramInt3)];
  }
  
  public final Segment a5(class_48 paramclass_48)
  {
    return a4(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public final class_988 a6()
  {
    return this.jdField_field_208_of_type_Class_988;
  }
  
  private int a7(int paramInt1, int paramInt2, int paramInt3)
  {
    class_48 localclass_48 = this.jdField_field_208_of_type_Class_48;
    paramInt3 = paramInt3;
    paramInt2 = paramInt2;
    int i = ByteUtil.b(paramInt1 = paramInt1);
    int j = ByteUtil.b(paramInt2);
    int k = ByteUtil.b(paramInt3);
    int m = i - localclass_48.field_475;
    int n = j - localclass_48.field_476;
    int i1;
    int i2 = ((i1 = k - localclass_48.field_477) << 6) + (n << 3) + m;
    if ((!jdField_field_208_of_type_Boolean) && ((i2 >= 512) || (i2 < 0))) {
      throw new AssertionError("PUTTING " + i2 + "/512; (" + paramInt1 + "; " + paramInt2 + "; " + paramInt3 + "): divU16(" + i + ", " + j + ", " + k + "): -Start(" + m + ", " + n + ", " + i1 + "):   " + localclass_48);
    }
    return i2;
  }
  
  public final long a8()
  {
    return this.jdField_field_208_of_type_Long;
  }
  
  public final class_796 a9(class_48 paramclass_48, boolean paramBoolean)
  {
    return a10(paramclass_48, paramBoolean, new class_796());
  }
  
  public final class_796 a10(class_48 paramclass_48, boolean paramBoolean, class_796 paramclass_796)
  {
    byte b1 = (byte)ByteUtil.d(paramclass_48.field_475);
    byte b2 = (byte)ByteUtil.d(paramclass_48.field_476);
    byte b3 = (byte)ByteUtil.d(paramclass_48.field_477);
    int i = ByteUtil.b(paramclass_48.field_475);
    int j = ByteUtil.b(paramclass_48.field_476);
    int k = ByteUtil.b(paramclass_48.field_477);
    i <<= 4;
    j <<= 4;
    k <<= 4;
    Segment localSegment;
    if ((localSegment = this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a4(i, j, k)) != null)
    {
      if ((!localSegment.g()) && (localSegment.a16() != null))
      {
        paramclass_796.a11(localSegment, b1, b2, b3);
        return paramclass_796;
      }
      paramclass_796.a17(localSegment);
      paramclass_796.a16(new class_35(b1, b2, b3));
      return paramclass_796;
    }
    if (paramBoolean)
    {
      if (!this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
      {
        if (this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.isInboundAbs(i, j, k))
        {
          ((class_20)this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider()).a19(new class_48(i, j, k));
        }
        else
        {
          System.err.println("[WARNING] not inbound: " + i + ", " + j + ", " + k + " -> returning as free");
          return null;
        }
        throw new CannotImmediateRequestOnClientException(new class_48(i, j, k));
      }
      try
      {
        Object localObject;
        try
        {
          localObject = this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a12(i, j, k);
        }
        catch (SegmentOutOfBoundsException localSegmentOutOfBoundsException1)
        {
          localObject = Segment.a9(i, j, k, new class_48());
          System.err.println("[SEGMENTBUFFER] Exception: POINT REQUESTED BUT OUT OF BOUNDS: segment.pos " + i + ", " + j + ", " + k + "; -div16-> " + localObject + "; MIN/MAX: " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos() + " - " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos());
          this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_475 = Math.min(((class_48)localObject).field_475, this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_475);
          this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_476 = Math.min(((class_48)localObject).field_476, this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_476);
          this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_477 = Math.min(((class_48)localObject).field_477, this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos().field_477);
          this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_475 = Math.max(((class_48)localObject).field_475, this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_475);
          this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_476 = Math.max(((class_48)localObject).field_476, this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_476);
          this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_477 = Math.max(((class_48)localObject).field_477, this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos().field_477);
          System.err.println("[SEGMENTBUFFER] exteding dimesion of " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController + " to: " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMinPos() + "; " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getMaxPos());
          localObject = this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a12(i, j, k);
        }
        if (!a2(i, j, k)) {
          System.err.println("Exception: DOES NOT CONTAIN AFTER REQUEST: RETURNED: " + localObject + "; Rstart " + this.jdField_field_208_of_type_Class_48 + " Hash(" + hashCode() + ") segIndex(" + i + ", " + j + ", " + k + ") " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController + " " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " buffered: " + Arrays.toString(this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment));
        }
        return a10(paramclass_48, paramBoolean, paramclass_796);
      }
      catch (SegmentOutOfBoundsException localSegmentOutOfBoundsException2)
      {
        localSegmentOutOfBoundsException2.printStackTrace();
        return null;
      }
    }
    if (paramBoolean) {
      System.err.println("[WARNING] AUTOREQUEST FOR " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController + " with " + paramclass_48 + " (" + i + ", " + j + ", " + k + ") IS RETURNING NULL! empty: false");
    }
    return null;
  }
  
  public final class_48 a11()
  {
    return this.jdField_field_208_of_type_Class_48;
  }
  
  public final SegmentController a12()
  {
    return this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController;
  }
  
  public final int c()
  {
    return this.jdField_field_208_of_type_Int;
  }
  
  public final boolean a13(class_48 paramclass_48, class_888 paramclass_888)
  {
    if (((paramclass_48 = a5(paramclass_48)) != null) && (!(paramclass_48 = paramclass_888.handle(paramclass_48)))) {
      return paramclass_48;
    }
    return true;
  }
  
  public final boolean a14(int paramInt1, int paramInt2, int paramInt3, class_888 paramclass_888)
  {
    if (((paramInt1 = a4(paramInt1, paramInt2, paramInt3)) != null) && (!paramInt1.g()) && (!(paramInt1 = paramclass_888.handle(paramInt1)))) {
      return paramInt1;
    }
    return true;
  }
  
  public final void a15(int paramInt, Segment paramSegment)
  {
    this.jdField_field_211_of_type_Int += paramInt;
  }
  
  public final boolean a16()
  {
    return this.jdField_field_211_of_type_Int > 0;
  }
  
  public final boolean b2()
  {
    return this.jdField_field_210_of_type_Int <= 0;
  }
  
  public final boolean a17(class_888 paramclass_888, class_48 paramclass_481, class_48 paramclass_482)
  {
    class_48 localclass_48 = new class_48();
    for (int i = paramclass_481.field_475; i < paramclass_482.field_475; i += 16) {
      for (int j = paramclass_481.field_476; j < paramclass_482.field_476; j += 16) {
        for (int k = paramclass_481.field_477; k < paramclass_482.field_477; k += 16)
        {
          localclass_48.b(i, j, k);
          Segment localSegment;
          boolean bool;
          if (((localSegment = a5(localclass_48)) != null) && (!(bool = paramclass_888.handle(localSegment)))) {
            return bool;
          }
        }
      }
    }
    return true;
  }
  
  public final boolean a18(class_888 paramclass_888, boolean arg2)
  {
    boolean bool;
    if (??? != 0) {
      synchronized (this)
      {
        for (int i = 0; i < this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment.length; i++)
        {
          Segment localSegment2;
          if (((localSegment2 = this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[i]) != null) && (!(bool = paramclass_888.handle(localSegment2)))) {
            return bool;
          }
        }
      }
    }
    for (??? = false; ??? < this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment.length; ???++)
    {
      Segment localSegment1;
      if (((localSegment1 = this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[???]) != null) && (!(bool = paramclass_888.handle(localSegment1)))) {
        return bool;
      }
    }
    return true;
  }
  
  public final boolean b3(class_888 paramclass_888, boolean arg2)
  {
    boolean bool;
    if (??? != 0) {
      synchronized (this)
      {
        for (int i = 0; i < this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment.length; i++)
        {
          Segment localSegment2;
          if (((localSegment2 = this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[i]) != null) && (!localSegment2.g()) && (!(bool = paramclass_888.handle(localSegment2)))) {
            return bool;
          }
        }
      }
    }
    for (??? = false; ??? < this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment.length; ???++)
    {
      Segment localSegment1;
      if (((localSegment1 = this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[???]) != null) && (!localSegment1.g()) && (!(bool = paramclass_888.handle(localSegment1)))) {
        return bool;
      }
    }
    return true;
  }
  
  public final boolean b4(class_888 paramclass_888, class_48 paramclass_481, class_48 paramclass_482)
  {
    class_48 localclass_48 = new class_48();
    for (int i = paramclass_481.field_475; i < paramclass_482.field_475; i += 16) {
      for (int j = paramclass_481.field_476; j < paramclass_482.field_476; j += 16) {
        for (int k = paramclass_481.field_477; k < paramclass_482.field_477; k += 16)
        {
          localclass_48.b(i, j, k);
          Segment localSegment;
          boolean bool;
          if (((localSegment = a5(localclass_48)) != null) && (!localSegment.g()) && (!(bool = paramclass_888.handle(localSegment)))) {
            return bool;
          }
        }
      }
    }
    return true;
  }
  
  private void a19(Segment paramSegment, class_35 paramclass_35)
  {
    if (((paramclass_35 = this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getNeighboringSegment(paramclass_35, paramSegment, this.field_213)) != null) && (paramclass_35 != paramSegment)) {
      paramclass_35.a11(true);
    }
  }
  
  private void a20(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    if ((!this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (paramSegment.a16() != null) && (!paramSegment.a16().isRevalidating()))
    {
      paramSegment.a13(paramByte1, paramByte2, paramByte3, this.field_212);
      this.jdField_field_208_of_type_Class_35.b((byte)(paramByte1 + 10), paramByte2, paramByte3);
      a19(paramSegment, this.jdField_field_208_of_type_Class_35);
      this.jdField_field_208_of_type_Class_35.b((byte)(paramByte1 - 10), paramByte2, paramByte3);
      a19(paramSegment, this.jdField_field_208_of_type_Class_35);
      this.jdField_field_208_of_type_Class_35.b(paramByte1, (byte)(paramByte2 + 10), paramByte3);
      a19(paramSegment, this.jdField_field_208_of_type_Class_35);
      this.jdField_field_208_of_type_Class_35.b(paramByte1, (byte)(paramByte2 - 10), paramByte3);
      a19(paramSegment, this.jdField_field_208_of_type_Class_35);
      this.jdField_field_208_of_type_Class_35.b(paramByte1, paramByte2, (byte)(paramByte3 + 10));
      a19(paramSegment, this.jdField_field_208_of_type_Class_35);
      this.jdField_field_208_of_type_Class_35.b(paramByte1, paramByte2, (byte)(paramByte3 - 10));
      a19(paramSegment, this.jdField_field_208_of_type_Class_35);
    }
  }
  
  public final void a21(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    if (paramInt == 0)
    {
      this.jdField_field_208_of_type_Int += 1;
      this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.f();
      b6(paramSegment);
    }
    a20(paramByte1, paramByte2, paramByte3, paramSegment);
  }
  
  public final void b5(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    if (paramInt == 1)
    {
      this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.d1();
      this.jdField_field_208_of_type_Int -= 1;
      a23();
    }
    a20(paramByte1, paramByte2, paramByte3, paramSegment);
  }
  
  private Segment a22(Segment paramSegment)
  {
    if (paramSegment != null)
    {
      this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.c1();
      this.jdField_field_210_of_type_Int -= 1;
      if (!paramSegment.g())
      {
        this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.d1();
        this.jdField_field_208_of_type_Int -= 1;
        if ((paramSegment instanceof class_657)) {
          synchronized (localArrayList = ((class_371)this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a27().a94().a57())
          {
            ArrayList localArrayList;
            localArrayList.add((class_657)paramSegment);
          }
        }
      }
      c1();
    }
    return paramSegment;
  }
  
  public final void a23()
  {
    this.jdField_field_208_of_type_Class_988.a8();
    for (int i = 0; i < this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment.length; i++)
    {
      Segment localSegment;
      if (((localSegment = this.jdField_field_208_of_type_ArrayOfOrgSchemaGameCommonDataWorldSegment[i]) != null) && (!localSegment.g())) {
        b6(localSegment);
      }
    }
  }
  
  public final void a24(SegmentData paramSegmentData)
  {
    float f1 = (paramSegmentData = paramSegmentData.getSegment()).field_34.field_475 + paramSegmentData.a16().getMin().field_453 - 8 - 1.0F;
    float f2 = paramSegmentData.field_34.field_476 + paramSegmentData.a16().getMin().field_454 - 8 - 1.0F;
    float f3 = paramSegmentData.field_34.field_477 + paramSegmentData.a16().getMin().field_455 - 8 - 1.0F;
    float f4 = paramSegmentData.field_34.field_475 + paramSegmentData.a16().getMax().field_453 - 8 + 1.0F;
    float f5 = paramSegmentData.field_34.field_476 + paramSegmentData.a16().getMax().field_454 - 8 + 1.0F;
    paramSegmentData = paramSegmentData.field_34.field_477 + paramSegmentData.a16().getMax().field_455 - 8 + 1.0F;
    if ((f1 == this.jdField_field_208_of_type_Class_988.field_1273.field_615) || (f2 == this.jdField_field_208_of_type_Class_988.field_1273.field_616) || (f3 == this.jdField_field_208_of_type_Class_988.field_1273.field_617) || (f4 == this.jdField_field_208_of_type_Class_988.field_1274.field_615) || (f5 == this.jdField_field_208_of_type_Class_988.field_1274.field_616) || (paramSegmentData == this.jdField_field_208_of_type_Class_988.field_1274.field_617))
    {
      System.err.println("[" + (this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT") + "][SEGMENTBUFFER] EDGE UPDATE: RESTRUCTION OF AABB REQUIRED " + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController + ": (" + f1 + ", " + f2 + ", " + f3 + "), (" + f4 + ", " + f5 + ", " + paramSegmentData + ") ---- " + this.jdField_field_208_of_type_Class_988.field_1273 + ", " + this.jdField_field_208_of_type_Class_988.field_1274);
      this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.a23();
    }
  }
  
  public final int d()
  {
    return this.jdField_field_210_of_type_Int;
  }
  
  public String toString()
  {
    return "[" + this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController + "(" + this.jdField_field_208_of_type_Class_48 + " - " + this.jdField_field_209_of_type_Class_48 + ")]";
  }
  
  public final void b6(Segment paramSegment)
  {
    if (!paramSegment.g())
    {
      float f1 = paramSegment.field_34.field_475 + paramSegment.a16().getMin().field_453 - 8 - 1.0F;
      float f2 = paramSegment.field_34.field_476 + paramSegment.a16().getMin().field_454 - 8 - 1.0F;
      float f3 = paramSegment.field_34.field_477 + paramSegment.a16().getMin().field_455 - 8 - 1.0F;
      float f4 = paramSegment.field_34.field_475 + paramSegment.a16().getMax().field_453 - 8 + 1.0F;
      float f5 = paramSegment.field_34.field_476 + paramSegment.a16().getMax().field_454 - 8 + 1.0F;
      paramSegment = paramSegment.field_34.field_477 + paramSegment.a16().getMax().field_455 - 8 + 1.0F;
      this.jdField_field_208_of_type_Class_988.a2(f1, f2, f3, f4, f5, paramSegment);
      if (this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized()) {
        this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.setFlagSegmentBufferAABBUpdate(true);
      }
    }
    if (this.jdField_field_208_of_type_Class_988.a6()) {
      this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.a31(this.jdField_field_208_of_type_Class_988);
    }
    this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.aabbRecalcFlag();
  }
  
  private void c1()
  {
    this.jdField_field_208_of_type_Long = System.currentTimeMillis();
    this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentBufferManager.g();
  }
  
  public final void b7()
  {
    this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a24();
    this.jdField_field_209_of_type_Int = this.jdField_field_208_of_type_Int;
    if (this.jdField_field_208_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
    {
      class_1041.field_144 += this.jdField_field_209_of_type_Int;
      return;
    }
    class_371.field_144 += this.jdField_field_209_of_type_Int;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_884
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */