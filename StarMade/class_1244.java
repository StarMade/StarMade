import java.util.HashSet;
import javax.vecmath.Vector3f;
import org.schema.game.network.objects.NetworkShip;
import org.schema.schine.ai.stateMachines.FSMException;
import org.schema.schine.network.objects.remote.RemoteVector3f;

public final class class_1244
  extends class_1260
{
  public class_1244(class_747 paramclass_747, boolean paramBoolean)
  {
    super("S&D_ENT", paramclass_747);
    new Vector3f();
    if (paramclass_747.isOnServer()) {
      this.field_223 = new class_1246(this, paramBoolean);
    }
    System.currentTimeMillis();
    Math.random();
  }
  
  public final void b1(class_941 paramclass_941)
  {
    super.b1(paramclass_941);
    Vector3f localVector3f1;
    if ((a5() instanceof class_1232))
    {
      a26().a113().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
      a26().a113().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
      (localVector3f1 = new Vector3f()).set(((class_1232)a5()).a4());
      a26().a113().moveDir.set(localVector3f1);
      a27(paramclass_941, localVector3f1, true);
    }
    else if ((a5() instanceof class_1234))
    {
      a26().a113().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
      a26().a113().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
      (localVector3f1 = new Vector3f()).set(((class_1234)a5()).a4());
      a26().a113().moveDir.set(localVector3f1);
      a27(paramclass_941, localVector3f1, true);
    }
    else if ((a5() instanceof class_1230))
    {
      a26().a113().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
      a26().a113().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
      (localVector3f1 = new Vector3f()).set(((class_1230)a5()).a4());
      a26().a113().moveDir.set(localVector3f1);
      a27(paramclass_941, localVector3f1, false);
    }
    else
    {
      Vector3f localVector3f2;
      if ((a5() instanceof class_1228))
      {
        a26().a113().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
        a26().a113().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
        (localVector3f1 = new Vector3f()).set(((class_1228)a5()).a4());
        a26().a113().orientationDir.set(localVector3f1);
        void tmp413_410 = new Vector3f(0.0F, 1.0F, 0.0F);
        tmp413_410.cross(localVector3f2 = tmp413_410, localVector3f1);
        localVector3f2.scale(400.0F);
        a27(paramclass_941, localVector3f2, false);
        a29(paramclass_941, localVector3f1);
      }
      else if ((a5() instanceof class_1149))
      {
        a26().a113().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
        a26().a113().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
        localVector3f1 = new Vector3f();
        localVector3f2 = new Vector3f();
        localVector3f1.set(((class_1149)a5()).a4());
        localVector3f2.set(((class_1149)a5()).b2());
        if (localVector3f1.length() > 0.0F)
        {
          a26().a113().targetPosition.set(localVector3f1);
          a26().a113().targetVelocity.set(localVector3f2);
          a30(localVector3f1, localVector3f2);
          a31(localVector3f1);
          b3(localVector3f1);
          ((class_1149)a5()).a4().set(0.0F, 0.0F, 0.0F);
          a5().a12(new class_1117());
        }
      }
      else
      {
        a26().a113().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
        a26().a113().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
      }
    }
    if ((this.field_223.size() > 0) && (((a5() instanceof class_1228)) || ((a5() instanceof class_1232)) || ((a5() instanceof class_1149)))) {
      try
      {
        a5().a12(new class_1143());
        return;
      }
      catch (FSMException localFSMException)
      {
        localFSMException;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1244
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */