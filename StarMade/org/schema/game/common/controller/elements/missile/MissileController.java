/*   1:    */package org.schema.game.common.controller.elements.missile;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import java.io.PrintStream;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import lb;
/*   7:    */import lk;
/*   8:    */import ll;
/*   9:    */import lm;
/*  10:    */import ln;
/*  11:    */import lo;
/*  12:    */import mF;
/*  13:    */import org.schema.game.network.objects.NetworkClientChannel;
/*  14:    */import org.schema.schine.network.server.ServerState;
/*  15:    */import vg;
/*  16:    */import xq;
/*  17:    */
/*  23:    */public class MissileController
/*  24:    */{
/*  25:    */  private vg state;
/*  26:    */  private final boolean onServer;
/*  27:    */  private static short missileIdCreator;
/*  28:    */  private final lo missileManager;
/*  29:    */  
/*  30:    */  public MissileController(vg paramvg)
/*  31:    */  {
/*  32: 32 */    this.state = paramvg;
/*  33: 33 */    this.onServer = (paramvg instanceof ServerState);
/*  34: 34 */    this.missileManager = new lo(paramvg);
/*  35:    */  }
/*  36:    */  
/*  37:    */  public void addDumbMissile(lb paramlb, Transform paramTransform, Vector3f paramVector3f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/*  38:    */    lk locallk;
/*  39: 39 */    (locallk = new lk(this.state)).a(paramFloat2);
/*  40: 40 */    locallk.c(paramFloat1);
/*  41: 41 */    locallk.b(paramFloat4);
/*  42: 42 */    locallk.a((int)paramFloat3);
/*  43: 43 */    addMissile(locallk, paramTransform, paramVector3f, paramlb);
/*  44:    */  }
/*  45:    */  
/*  46: 46 */  public void addFafoMissile(lb paramlb, Transform paramTransform, Vector3f paramVector3f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, mF parammF) { System.err.println("ADDING FAFO MISSILE!!!!!!!!!!!!!!! Dir: " + paramVector3f + " onServer: " + isOnServer());
/*  47:    */    ll localll;
/*  48: 48 */    (localll = new ll(this.state)).a(paramFloat2);
/*  49: 49 */    localll.c(paramFloat1);
/*  50: 50 */    localll.b(paramFloat4);
/*  51: 51 */    localll.a((int)paramFloat3);
/*  52: 52 */    paramVector3f.normalize();
/*  53: 53 */    System.err.println("MISSILE TARGET " + parammF);
/*  54: 54 */    localll.a(parammF);
/*  55: 55 */    addMissile(localll, paramTransform, paramVector3f, paramlb);
/*  56:    */  }
/*  57:    */  
/*  59:    */  public void addHeatMissile(lb paramlb, Transform paramTransform, Vector3f paramVector3f, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
/*  60:    */  {
/*  61: 61 */    System.err.println("ADDING HEAT MISSILE!!!!!!!!!!!!!!!");
/*  62:    */    lm locallm;
/*  63: 63 */    (locallm = new lm(this.state)).a(paramFloat2);
/*  64: 64 */    locallm.c(paramFloat1);
/*  65: 65 */    locallm.b(paramFloat4);
/*  66: 66 */    locallm.a((int)paramFloat3);
/*  67:    */    
/*  68: 68 */    addMissile(locallm, paramTransform, paramVector3f, paramlb);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public void addMissile(ln paramln, Transform paramTransform, Vector3f paramVector3f, lb paramlb)
/*  72:    */  {
/*  73: 73 */    assert (isOnServer());
/*  74: 74 */    if (paramlb == null) {
/*  75: 75 */      throw new NullPointerException("OWNER NULL");
/*  76:    */    }
/*  77: 77 */    System.err.println("[MISSILE] NEW MISSILE: " + paramTransform.origin + "; " + paramln + ": Speed: " + paramln.c() + ", Damage: " + paramln.a() + ", Radius: " + paramln.a() + ", Lifetime: " + paramln.b());
/*  78: 78 */    paramln.a(paramlb);
/*  79: 79 */    paramln.b(((mF)paramlb).getSectorId());
/*  80:    */    
/*  81: 81 */    paramln.a().set(paramTransform);
/*  82: 82 */    paramln.b().set(paramTransform);
/*  83: 83 */    paramln.a(paramVector3f);
/*  84: 84 */    paramln.a(missileIdCreator++);
/*  85:    */    
/*  86: 86 */    this.missileManager.a(paramln);
/*  87:    */  }
/*  88:    */  
/*  92:    */  public boolean isOnServer()
/*  93:    */  {
/*  94: 94 */    return this.onServer;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public void fromNetwork(NetworkClientChannel paramNetworkClientChannel) {
/*  98: 98 */    this.missileManager.a(paramNetworkClientChannel);
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void updateServer(xq paramxq) {
/* 102:102 */    this.missileManager.a(paramxq);
/* 103:    */  }
/* 104:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.MissileController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */