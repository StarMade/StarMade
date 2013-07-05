package org.schema.schine.network;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadPoolExecutor;
import org.schema.schine.network.objects.Sendable;
import wB;

public abstract interface StateInterface
{
  public abstract void chat(ChatSystem paramChatSystem, String paramString1, String paramString2, boolean paramBoolean);

  public abstract ChatSystem getChat();

  public abstract String[] getCommandPrefixes();

  public abstract ControllerInterface getController();

  public abstract byte[] getDataBuffer();

  public abstract ByteBuffer getDataByteBuffer();

  public abstract int getId();

  public abstract NetworkStateContainer getLocalAndRemoteObjectContainer();

  public abstract float getMaxMsBetweenUpdates();

  public abstract NetworkStatus getNetworkStatus();

  public abstract int getNextFreeObjectId();

  public abstract ThreadPoolExecutor getThreadPool();

  public abstract short getUpdateNumber();

  public abstract float getVersion();

  public abstract void incUpdateNumber();

  public abstract boolean isReadingBigChunk();

  public abstract boolean isReady();

  public abstract void notifyOfAddedObject(Sendable paramSendable);

  public abstract void notifyOfRemovedObject(Sendable paramSendable);

  public abstract void needsNotify(Sendable paramSendable);

  public abstract String onAutoComplete(String paramString1, wB paramwB, String paramString2);

  public abstract void onStringCommand(String paramString1, wB paramwB, String paramString2);

  public abstract boolean onChatTextEnterHook(ChatSystem paramChatSystem, String paramString, boolean paramBoolean);

  public abstract int getServerTimeDifference();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.StateInterface
 * JD-Core Version:    0.6.2
 */