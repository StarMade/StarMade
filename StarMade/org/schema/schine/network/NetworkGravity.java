package org.schema.schine.network;

import javax.vecmath.Vector3f;

public class NetworkGravity
{
  public int gravityId = -1;
  public int gravityIdReceive = -1;
  public Vector3f gravity = new Vector3f();
  public Vector3f gravityReceive = new Vector3f();
  public boolean gravityReceived;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.NetworkGravity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */