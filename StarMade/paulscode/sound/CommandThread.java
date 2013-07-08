package paulscode.sound;

public class CommandThread
  extends SimpleThread
{
  protected SoundSystemLogger logger = SoundSystemConfig.getLogger();
  private SoundSystem soundSystem;
  protected String className = "CommandThread";
  
  public CommandThread(SoundSystem local_s)
  {
    this.soundSystem = local_s;
  }
  
  protected void cleanup()
  {
    kill();
    this.logger = null;
    this.soundSystem = null;
    super.cleanup();
  }
  
  public void run()
  {
    long previousTime = System.currentTimeMillis();
    long currentTime = previousTime;
    if (this.soundSystem == null)
    {
      errorMessage("SoundSystem was null in method run().", 0);
      cleanup();
      return;
    }
    snooze(3600000L);
    while (!dying())
    {
      this.soundSystem.ManageSources();
      this.soundSystem.CommandQueue(null);
      currentTime = System.currentTimeMillis();
      if ((!dying()) && (currentTime - previousTime > 10000L))
      {
        previousTime = currentTime;
        this.soundSystem.removeTemporarySources();
      }
      if (!dying()) {
        snooze(3600000L);
      }
    }
    cleanup();
  }
  
  protected void message(String message, int indent)
  {
    this.logger.message(message, indent);
  }
  
  protected void importantMessage(String message, int indent)
  {
    this.logger.importantMessage(message, indent);
  }
  
  protected boolean errorCheck(boolean error, String message)
  {
    return this.logger.errorCheck(error, this.className, message, 0);
  }
  
  protected void errorMessage(String message, int indent)
  {
    this.logger.errorMessage(this.className, message, indent);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.CommandThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */