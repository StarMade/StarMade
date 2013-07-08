/*   1:    */package org.schema.schine.network.objects;
/*   2:    */
/*   3:    */import java.util.Arrays;
/*   4:    */import org.schema.schine.network.StateInterface;
/*   5:    */
/*  60:    */public class NetworkRemoteController
/*  61:    */  extends NetworkObject
/*  62:    */{
/*  63:    */  public long clientID;
/*  64:    */  public long id;
/*  65: 65 */  public String name = "unknownController";
/*  66:    */  
/*  68:    */  public String[] fNames;
/*  69:    */  
/*  71:    */  public int[] fTypes;
/*  72:    */  
/*  73:    */  public String[] fValues;
/*  74:    */  
/*  75:    */  public int[] fControllable;
/*  76:    */  
/*  77:    */  public long entityID;
/*  78:    */  
/*  79:    */  public String customName;
/*  80:    */  
/*  82:    */  public NetworkRemoteController(StateInterface paramStateInterface)
/*  83:    */  {
/*  84: 84 */    super(paramStateInterface);
/*  85:    */  }
/*  86:    */  
/*  93:    */  public void onDelete(StateInterface paramStateInterface) {}
/*  94:    */  
/* 100:    */  public void onInit(StateInterface paramStateInterface) {}
/* 101:    */  
/* 107:    */  public String toString()
/* 108:    */  {
/* 109:109 */    return "[" + this.id + "]" + this.name + " " + Arrays.toString(this.fNames) + ", " + Arrays.toString(this.fValues);
/* 110:    */  }
/* 111:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.NetworkRemoteController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */