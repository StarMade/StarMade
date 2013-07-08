package paulscode.sound;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class StreamThread
  extends SimpleThread
{
  private SoundSystemLogger logger = SoundSystemConfig.getLogger();
  private List<Source> streamingSources = new LinkedList();
  private final Object listLock = new Object();
  
  protected void cleanup()
  {
    kill();
    super.cleanup();
  }
  
  public void run()
  {
    snooze(3600000L);
    while (!dying())
    {
      while ((!dying()) && (!this.streamingSources.isEmpty()))
      {
        synchronized (this.listLock)
        {
          ListIterator<Source> iter = this.streamingSources.listIterator();
          while ((!dying()) && (iter.hasNext()))
          {
            Source src = (Source)iter.next();
            if (src == null)
            {
              iter.remove();
            }
            else if (src.stopped())
            {
              if (!src.rawDataStream) {
                iter.remove();
              }
            }
            else if (!src.active())
            {
              if ((src.toLoop) || (src.rawDataStream)) {
                src.toPlay = true;
              }
              iter.remove();
            }
            else if (!src.paused())
            {
              src.checkFadeOut();
              if ((!src.stream()) && (!src.rawDataStream) && ((src.channel == null) || (!src.channel.processBuffer())))
              {
                if (src.nextCodec == null) {
                  src.readBuffersFromNextSoundInSequence();
                }
                if (src.toLoop)
                {
                  if (!src.playing())
                  {
                    SoundSystemConfig.notifyEOS(src.sourcename, src.getSoundSequenceQueueSize());
                    if (src.checkFadeOut())
                    {
                      src.preLoad = true;
                    }
                    else
                    {
                      src.incrementSoundSequence();
                      src.preLoad = true;
                    }
                  }
                }
                else if (!src.playing())
                {
                  SoundSystemConfig.notifyEOS(src.sourcename, src.getSoundSequenceQueueSize());
                  if (!src.checkFadeOut()) {
                    if (src.incrementSoundSequence()) {
                      src.preLoad = true;
                    } else {
                      iter.remove();
                    }
                  }
                }
              }
            }
          }
        }
        if ((!dying()) && (!this.streamingSources.isEmpty())) {
          snooze(20L);
        }
      }
      if ((!dying()) && (this.streamingSources.isEmpty())) {
        snooze(3600000L);
      }
    }
    cleanup();
  }
  
  public void watch(Source source)
  {
    if (source == null) {
      return;
    }
    if (this.streamingSources.contains(source)) {
      return;
    }
    synchronized (this.listLock)
    {
      ListIterator<Source> iter = this.streamingSources.listIterator();
      while (iter.hasNext())
      {
        Source src = (Source)iter.next();
        if (src == null)
        {
          iter.remove();
        }
        else if (source.channel == src.channel)
        {
          src.stop();
          iter.remove();
        }
      }
      this.streamingSources.add(source);
    }
  }
  
  private void message(String message)
  {
    this.logger.message(message, 0);
  }
  
  private void importantMessage(String message)
  {
    this.logger.importantMessage(message, 0);
  }
  
  private boolean errorCheck(boolean error, String message)
  {
    return this.logger.errorCheck(error, "StreamThread", message, 0);
  }
  
  private void errorMessage(String message)
  {
    this.logger.errorMessage("StreamThread", message, 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     paulscode.sound.StreamThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */