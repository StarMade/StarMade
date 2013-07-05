package org.schema.game.common.controller.elements;

import java.util.Timer;

public abstract interface TimedUpdateInterface
{
  public abstract void update(Timer paramTimer);

  public abstract long getLastExecution();

  public abstract long getNextExecution();

  public abstract long getTimeStep();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.TimedUpdateInterface
 * JD-Core Version:    0.6.2
 */