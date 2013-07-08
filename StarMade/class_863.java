import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.schema.game.common.Starter;
import org.schema.game.common.gui.ConnectionDialog;
import org.schema.schine.network.LoginFailedException;
import org.schema.schine.network.server.ServerController;

public final class class_863
  implements Runnable
{
  private class_899 jdField_field_1099_of_type_Class_899;
  private ConnectionDialog jdField_field_1099_of_type_OrgSchemaGameCommonGuiConnectionDialog;
  private class_333 jdField_field_1099_of_type_Class_333;
  private class_371 jdField_field_1099_of_type_Class_371;
  private class_52 jdField_field_1099_of_type_Class_52;
  
  public class_863(class_899 paramclass_899)
  {
    this.jdField_field_1099_of_type_Class_899 = paramclass_899;
    this.jdField_field_1099_of_type_OrgSchemaGameCommonGuiConnectionDialog = new ConnectionDialog();
    this.jdField_field_1099_of_type_OrgSchemaGameCommonGuiConnectionDialog.setVisible(true);
  }
  
  public final void run()
  {
    System.err.println("[CLIENT] initializing " + class_1266.jdField_field_1446_of_type_Float + " (" + class_1266.jdField_field_1446_of_type_JavaLangString + ")");
    class_367.a2();
    try
    {
      class_367.b();
    }
    catch (IOException localIOException2)
    {
      (localIOException1 = localIOException2).printStackTrace();
      class_29.a13(localIOException1, true);
    }
    IOException localIOException1 = null;
    new File(class_371.field_145).mkdirs();
    try
    {
      while (!Starter.field_2078) {
        synchronized (Starter.field_2076)
        {
          Starter.field_2076.wait(60000L);
        }
      }
      if (Starter.field_2079)
      {
        System.err.println("[LOCAL GAME] CHANGED PORT TO " + ServerController.port);
        this.jdField_field_1099_of_type_Class_899.jdField_field_1125_of_type_Int = ServerController.port;
      }
      this.jdField_field_1099_of_type_Class_333 = new class_333();
      class_969.a4(this.jdField_field_1099_of_type_Class_333);
      this.jdField_field_1099_of_type_Class_371 = new class_371();
      this.jdField_field_1099_of_type_Class_52 = new class_52(this.jdField_field_1099_of_type_Class_371, this.jdField_field_1099_of_type_OrgSchemaGameCommonGuiConnectionDialog);
      this.jdField_field_1099_of_type_Class_52.a1(this.jdField_field_1099_of_type_Class_899);
      this.jdField_field_1099_of_type_Class_52.d();
      class_949.c();
      this.jdField_field_1099_of_type_Class_52.f();
      return;
    }
    catch (LoginFailedException localLoginFailedException)
    {
      switch (((LoginFailedException)???).getErrorCode())
      {
      case -3: 
        str = "Server: access denied";
        break;
      case -2: 
        str = "Server: name already logged in on this server\n\n\n\n(If you are retrying after a socket exception,\nplease wait 3 minutes for the server to time-out your old connection)";
        break;
      case -7: 
        str = "Server Reject: this login name is protected on this server!\n\nYou either aren't logged in via uplink,\nor the protected name belongs to another user!\n\nPlease use the \"Uplink\" menu to authenticate this name!";
        break;
      case -1: 
        str = "Server: general error";
        break;
      case -4: 
        str = "Server: this server is full";
        break;
      case -6: 
        str = "Server: you are banned from this server";
        break;
      case -8: 
        str = "Server: you are not whitelisted on this server";
        break;
      case -5: 
        str = "Server: your client version is not allowed to connect.\nPlease download the correct version from www.star-made.org\nClient Version: " + class_1266.jdField_field_1446_of_type_Float + "\nServer Version: " + class_371.serverVersion;
        break;
      default: 
        str = "Server: LOGIN ERROR";
      }
      (??? = new JFrame("LoginError")).setAlwaysOnTop(true);
      i = 0;
      switch (JOptionPane.showOptionDialog((Component)???, str, "Login Failed", 1, 0, null, new String[] { "Back To Login Screen", "EXIT" }, "Back To Login Screen"))
      {
      case 0: 
        int i;
        this.jdField_field_1099_of_type_OrgSchemaGameCommonGuiConnectionDialog.field_1738 = false;
        new Thread(new class_861()).start();
        break;
      case 1: 
        System.err.println("Exiting because of login failed");
        System.exit(0);
      }
      return;
    }
    catch (Exception localException)
    {
      String str = localException.getClass().getSimpleName() + ": " + localException.getMessage();
      if ((localException instanceof ConnectException)) {
        str = str + ", \n\nfailed to connect to \"" + this.jdField_field_1099_of_type_Class_899.jdField_field_1125_of_type_JavaLangString + ":" + this.jdField_field_1099_of_type_Class_899.jdField_field_1125_of_type_Int + "\".\nEither server is down, blocking, or the adress is wrong!";
      }
      localException.printStackTrace();
      j = 0;
      switch (JOptionPane.showOptionDialog(null, str, "ERROR", 1, 0, null, new String[] { "Ok", "Exit" }, "Exit"))
      {
      case 0: 
        int j;
        this.jdField_field_1099_of_type_OrgSchemaGameCommonGuiConnectionDialog.field_1738 = false;
        Starter.a();
        break;
      case 1: 
        System.err.println("Exiting because of failed conenct");
        System.exit(0);
        break;
      case 2: 
        System.err.println("Exiting because of failed conenct");
        System.exit(0);
      }
      return;
    }
    finally
    {
      this.jdField_field_1099_of_type_OrgSchemaGameCommonGuiConnectionDialog.dispose();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_863
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */