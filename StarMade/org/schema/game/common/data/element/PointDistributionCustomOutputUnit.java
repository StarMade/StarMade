package org.schema.game.common.data.element;

import class_46;
import class_48;
import class_753;
import class_796;
import class_886;
import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public abstract class PointDistributionCustomOutputUnit
  extends PointDistributionUnit
{
  private class_48 output = new class_48(0, 0, 0);
  
  public class_48 getOutput()
  {
    return this.output;
  }
  
  public void onChangeFinished()
  {
    super.onChangeFinished();
    this.output.b1(getSignificator());
    class_796 localclass_7961 = new class_796();
    int i = 0;
    try
    {
      localclass_48 = new class_48();
      Iterator localIterator = getNeighboringCollection().iterator();
      while (localIterator.hasNext())
      {
        getPosFromIndex(((Long)localIterator.next()).longValue(), localclass_48);
        class_796 localclass_7962;
        if (((localclass_7962 = getController().getSegmentBuffer().a10(localclass_48, false, localclass_7961)) != null) && (localclass_7962.a10()) && (localclass_7962.a9() == getClazzId()))
        {
          this.output.b1(localclass_48);
          i = 1;
          break;
        }
      }
    }
    catch (IOException localIOException)
    {
      localclass_48 = null;
      localIOException.printStackTrace();
    }
    catch (InterruptedException localInterruptedException)
    {
      class_48 localclass_48 = null;
      localInterruptedException.printStackTrace();
    }
    if (i == 0) {
      this.output.b1(getSignificator());
    }
  }
  
  public void setMainPiece(class_796 paramclass_796, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (paramclass_796.a7().a15().isOnServer())
      {
        class_796 localclass_796 = new class_796();
        class_48 localclass_48 = new class_48();
        Iterator localIterator = getNeighboringCollection().iterator();
        while (localIterator.hasNext())
        {
          getPosFromIndex(((Long)localIterator.next()).longValue(), localclass_48);
          if (!paramclass_796.a1(localclass_48)) {
            try
            {
              getController().getSegmentBuffer().a10(localclass_48, false, localclass_796);
              if (localclass_796.a10())
              {
                class_46 localclass_46;
                (localclass_46 = new class_46()).a(localclass_48.field_475, localclass_48.field_476, localclass_48.field_477, -2);
                ((class_753)getController()).getBlockActivationBuffer().enqueue(localclass_46);
              }
            }
            catch (IOException localIOException)
            {
              localIOException;
            }
            catch (InterruptedException localInterruptedException)
            {
              localInterruptedException;
            }
          }
        }
      }
      System.err.println("NEW OUTPUT SET: " + paramclass_796.a2(new class_48()) + "; ACTIVE: " + paramBoolean);
      this.output.b1(paramclass_796.a2(new class_48()));
    }
  }
  
  public void setMainPiece()
  {
    class_796 localclass_796 = new class_796();
    class_48 localclass_48 = new class_48();
    Iterator localIterator = getNeighboringCollection().iterator();
    while (localIterator.hasNext())
    {
      getPosFromIndex(((Long)localIterator.next()).longValue(), localclass_48);
      try
      {
        if ((localclass_796 = getController().getSegmentBuffer().a10(localclass_48, false, localclass_796)).a10())
        {
          class_46 localclass_46;
          (localclass_46 = new class_46()).a(localclass_48.field_475, localclass_48.field_476, localclass_48.field_477, -2);
          ((class_753)getController()).getBlockActivationBuffer().enqueue(localclass_46);
        }
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
    }
    System.err.println("NEW OUTPUT SET: " + localclass_796.a2(new class_48()) + ";");
    this.output.b1(localclass_796.a2(new class_48()));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.PointDistributionCustomOutputUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */