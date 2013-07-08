import java.io.PrintStream;

final class class_51
  implements Runnable
{
  class_51(class_52 paramclass_52) {}
  
  public final void run()
  {
    System.out.println("[CLIENT] CLIENT SHUTDOWN. Dumping client data!");
    try
    {
      this.field_479.g();
      System.out.println("[CLIENT] CLIENT SHUTDOWN. client data saved!");
      class_949.b();
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_51
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */