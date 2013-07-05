/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import org.schema.game.network.objects.NetworkGameState;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteFloat;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ import org.schema.schine.network.objects.remote.RemoteString;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public class lg
/*     */   implements Sendable
/*     */ {
/*     */   private final StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   private int jdField_a_of_type_Int;
/*     */   private final boolean jdField_a_of_type_Boolean;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private boolean jdField_c_of_type_Boolean;
/*     */   private NetworkGameState jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState;
/*     */   private float jdField_a_of_type_Float;
/*     */   private float jdField_b_of_type_Float;
/*     */   private float jdField_c_of_type_Float;
/*     */   private String jdField_a_of_type_JavaLangString;
/*     */   private final lT jdField_a_of_type_LT;
/*     */   private final lJ jdField_a_of_type_LJ;
/*  49 */   private String jdField_b_of_type_JavaLangString = "(TODO description)";
/*  50 */   private String jdField_c_of_type_JavaLangString = "(TODO name)";
/*     */ 
/*     */   public final lT a()
/*     */   {
/*  39 */     return this.jdField_a_of_type_LT;
/*     */   }
/*     */ 
/*     */   public lg(StateInterface paramStateInterface)
/*     */   {
/*  53 */     this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  54 */     this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*  55 */     if (this.jdField_a_of_type_Boolean) {
/*  56 */       this.jdField_a_of_type_Float = ((Integer)vo.r.a()).intValue();
/*     */     }
/*  58 */     this.jdField_a_of_type_LT = new lT(this);
/*  59 */     this.jdField_a_of_type_LJ = new lJ(this);
/*  60 */     if (this.jdField_a_of_type_Boolean) {
/*     */       try {
/*  62 */         a();
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*  66 */         localIOException.printStackTrace();
/*     */ 
/*  65 */         this.jdField_a_of_type_JavaLangString = "";
/*     */       }
/*     */ 
/*  68 */       this.jdField_b_of_type_Float = ((Float)vo.x.a()).floatValue();
/*  69 */       this.jdField_c_of_type_Float = ((Float)vo.y.a()).floatValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cleanUpOnEntityDelete()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  82 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final NetworkGameState a()
/*     */   {
/*  87 */     return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState;
/*     */   }
/*     */ 
/*     */   public StateInterface getState()
/*     */   {
/*  92 */     return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   }
/*     */ 
/*     */   public void initFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/*  98 */     setId(((Integer)paramNetworkObject.id.get()).intValue());
/*  99 */     lT.c();
/* 100 */     lJ.c();
/* 101 */     if (!isOnServer()) {
/* 102 */       this.jdField_a_of_type_Float = ((Float)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.serverMaxSpeed.get()).floatValue();
/* 103 */       this.jdField_b_of_type_Float = ((Float)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.linearDamping.get()).floatValue();
/* 104 */       this.jdField_c_of_type_Float = ((Float)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.rotationalDamping.get()).floatValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatile()
/*     */   {
/* 125 */     return this.jdField_b_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatileSent()
/*     */   {
/* 130 */     return this.jdField_c_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public boolean isOnServer()
/*     */   {
/* 135 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject()
/*     */   {
/* 140 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState = new NetworkGameState(getState());
/*     */   }
/*     */ 
/*     */   public final void a() {
/* 144 */     if (!(
/* 144 */       localObject = new File("./server-message.txt"))
/* 144 */       .exists()) {
/* 145 */       ((File)localObject).createNewFile();
/* 146 */       this.jdField_a_of_type_JavaLangString = ""; return;
/*     */     }
/* 148 */     Object localObject = new BufferedReader(new FileReader((File)localObject));
/* 149 */     String str = null;
/* 150 */     StringBuffer localStringBuffer = new StringBuffer();
/* 151 */     while ((str = ((BufferedReader)localObject).readLine()) != null) {
/* 152 */       localStringBuffer.append(str + "\n");
/*     */     }
/* 154 */     ((BufferedReader)localObject).close();
/* 155 */     this.jdField_a_of_type_JavaLangString = localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public void setId(int paramInt)
/*     */   {
/* 161 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatile(boolean paramBoolean)
/*     */   {
/* 166 */     this.jdField_b_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/*     */   {
/* 172 */     this.jdField_c_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 179 */     if (!this.jdField_a_of_type_Boolean) {
/* 180 */       vo.b((NetworkGameState)paramNetworkObject);
/*     */     }
/* 182 */     this.jdField_a_of_type_LT.a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/* 183 */     this.jdField_a_of_type_LJ.b(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq paramxq)
/*     */   {
/* 188 */     this.jdField_a_of_type_LT.b();
/* 189 */     this.jdField_a_of_type_LJ.a();
/*     */   }
/*     */ 
/*     */   public void updateToFullNetworkObject()
/*     */   {
/* 194 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.id.set(Integer.valueOf(getId()));
/* 195 */     if ((!d) && (this.jdField_a_of_type_JavaLangString == null)) throw new AssertionError();
/* 196 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.serverMessage.set(this.jdField_a_of_type_JavaLangString);
/* 197 */     if (this.jdField_a_of_type_Boolean) {
/* 198 */       vo.a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/* 199 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.saveSlotsAllowed.set((Integer)vo.n.a());
/* 200 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.serverMaxSpeed.set(Float.valueOf(this.jdField_a_of_type_Float));
/* 201 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.linearDamping.set(Float.valueOf(this.jdField_b_of_type_Float));
/* 202 */       this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.rotationalDamping.set(Float.valueOf(this.jdField_c_of_type_Float));
/*     */     }
/* 204 */     this.jdField_a_of_type_LT.b(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/* 205 */     this.jdField_a_of_type_LJ.a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/*     */   }
/*     */ 
/*     */   public void updateToNetworkObject()
/*     */   {
/* 210 */     this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.id.set(Integer.valueOf(getId()));
/* 211 */     lT.d();
/* 212 */     lJ.b();
/*     */   }
/*     */ 
/*     */   public final lJ a()
/*     */   {
/* 221 */     return this.jdField_a_of_type_LJ;
/*     */   }
/*     */ 
/*     */   public void destroyPersistent()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForPermanentDelete()
/*     */   {
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */   public void markedForPermanentDelete(boolean paramBoolean)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isUpdatable()
/*     */   {
/* 251 */     return true;
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 260 */     return this.jdField_a_of_type_Float;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 274 */     return "SenableGameState(" + this.jdField_a_of_type_Int + ")";
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/* 280 */     return this.jdField_b_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final String b()
/*     */   {
/* 286 */     return this.jdField_c_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 291 */     return this.jdField_b_of_type_Float;
/*     */   }
/*     */ 
/*     */   public final float c() {
/* 295 */     return this.jdField_c_of_type_Float;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lg
 * JD-Core Version:    0.6.2
 */