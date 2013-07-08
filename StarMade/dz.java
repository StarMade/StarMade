/*  1:   */import java.io.PrintStream;
/*  2:   */import javax.vecmath.Vector3f;
/*  3:   */import org.lwjgl.input.Keyboard;
/*  4:   */import org.schema.game.common.controller.SegmentController;
/*  5:   */
/* 18:   */public final class dz
/* 19:   */  extends dy
/* 20:   */  implements wx
/* 21:   */{
/* 22:   */  public final void handleKeyEvent()
/* 23:   */  {
/* 24:   */    q localq;
/* 25:25 */    (localq = new q(this.jdField_a_of_type_Q)).a(this.jdField_a_of_type_Al.a());
/* 26:26 */    if (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b(localq)) {
/* 27:27 */      while ((this.jdField_a_of_type_Q.a() > 0.0F) && (!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b(localq))) {
/* 28:28 */        this.jdField_a_of_type_Q.a /= 2;
/* 29:29 */        this.jdField_a_of_type_Q.b /= 2;
/* 30:30 */        this.jdField_a_of_type_Q.c /= 2;
/* 31:31 */        localq.b(this.jdField_a_of_type_Q);
/* 32:32 */        localq.a(this.jdField_a_of_type_Al.a());
/* 33:33 */        System.err.println("[CAM] SEARCHING anchor");
/* 34:   */      }
/* 35:   */    }
/* 36:36 */    int i = 1;
/* 37:37 */    Vector3f localVector3f = new Vector3f(localq.a, localq.b, localq.c);
/* 38:38 */    if (Keyboard.getEventKeyState()) {
/* 39:39 */      if (!Keyboard.isKeyDown(42)) {
/* 40:40 */        if (Keyboard.getEventKey() == cv.a.a()) {
/* 41:41 */          localVector3f.sub(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamLeftLocal());
/* 42:42 */        } else if (Keyboard.getEventKey() == cv.b.a()) {
/* 43:43 */          localVector3f.add(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamLeftLocal());
/* 44:44 */        } else if (Keyboard.getEventKey() == cv.c.a()) {
/* 45:45 */          localVector3f.add(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamForwLocal());
/* 46:46 */        } else if (Keyboard.getEventKey() == cv.d.a()) {
/* 47:47 */          localVector3f.sub(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamForwLocal());
/* 48:48 */        } else if (Keyboard.getEventKey() == cv.e.a()) {
/* 49:49 */          localVector3f.add(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamUpLocal());
/* 50:50 */        } else if (Keyboard.getEventKey() == cv.f.a()) {
/* 51:51 */          localVector3f.sub(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getCamUpLocal());
/* 52:   */        } else {
/* 53:53 */          i = 0;
/* 54:   */        }
/* 55:   */      } else {
/* 56:56 */        i = 0;
/* 57:   */      }
/* 58:   */      
/* 59:59 */      localq.b(Math.round(localVector3f.x), Math.round(localVector3f.y), Math.round(localVector3f.z));
/* 60:60 */      if (i != 0) {
/* 61:61 */        if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b(localq)) {
/* 62:62 */          localq.c(this.jdField_a_of_type_Al.a());
/* 63:63 */          System.err.println("EXISTS!. pos mod set to " + localq);
/* 64:64 */          this.jdField_a_of_type_Q.b(localq);return;
/* 65:   */        }
/* 66:66 */        System.err.println("NOT EXISTS!. pos mod NOT set to " + localq);
/* 67:   */      }
/* 68:   */    }
/* 69:   */  }
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */