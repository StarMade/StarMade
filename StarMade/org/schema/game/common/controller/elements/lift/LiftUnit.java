/*   1:    */package org.schema.game.common.controller.elements.lift;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.shapes.BoxShape;
/*   4:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   5:    */import com.bulletphysics.linearmath.MotionState;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import ct;
/*   8:    */import dj;
/*   9:    */import ex;
/*  10:    */import java.util.ArrayList;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */import org.schema.game.common.controller.SegmentController;
/*  13:    */import org.schema.game.common.data.element.ElementCollection;
/*  14:    */import org.schema.game.common.data.physics.PhysicsExt;
/*  15:    */import xq;
/*  16:    */import zQ;
/*  17:    */
/*  18:    */public class LiftUnit extends ElementCollection
/*  19:    */{
/*  20: 20 */  private q significator = new q();
/*  21: 21 */  private float height = -1.0F;
/*  22:    */  private com.bulletphysics.dynamics.RigidBody body;
/*  23:    */  private Transform t;
/*  24:    */  private float maxHeight;
/*  25: 25 */  private float timeSpendUp = 0.0F;
/*  26:    */  
/*  31:    */  public void activate()
/*  32:    */  {
/*  33: 33 */    PhysicsExt localPhysicsExt = getController().getPhysics();
/*  34:    */    
/*  35: 35 */    Object localObject2 = new BoxShape(new Vector3f(Math.max(2, getMax().a - getMin().a), 0.2F, Math.max(2, getMax().c - getMin().c)));
/*  36:    */    
/*  37: 37 */    Vector3f localVector3f = new Vector3f(getSignificator().a - 8, getMin().b - 8 - 1.0F, getSignificator().c - 8);
/*  38:    */    
/*  39: 39 */    getController().getWorldTransform().transform(localVector3f);
/*  40:    */    
/*  41: 41 */    this.t = new Transform();
/*  42: 42 */    this.t.setIdentity();
/*  43: 43 */    this.t.origin.set(localVector3f);
/*  44: 44 */    this.timeSpendUp = 0.0F;
/*  45:    */    
/*  46: 46 */    if (getBody() != null) {
/*  47: 47 */      localPhysicsExt.removeObject(getBody());
/*  48:    */    }
/*  49: 49 */    setBody(localPhysicsExt.getBodyFromShape((CollisionShape)localObject2, 0.0F, this.t));
/*  50: 50 */    this.height = 0.0F;
/*  51: 51 */    this.maxHeight = (getMax().b - getMin().b + 1.5F);
/*  52:    */    
/*  53: 53 */    localPhysicsExt.addObject(getBody());
/*  54:    */    
/*  55: 55 */    if (!getController().isOnServer()) {
/*  56: 56 */      int i = 0;localObject2 = this;Object localObject1 = null;((ct)getController().getState()).a().a().a.add(localObject2);
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  62:    */  public void cleanUp()
/*  63:    */  {
/*  64: 64 */    deactivate();
/*  65: 65 */    super.cleanUp();
/*  66:    */  }
/*  67:    */  
/*  69:    */  public void deactivate()
/*  70:    */  {
/*  71: 71 */    if (isActive())
/*  72:    */    {
/*  73: 73 */      PhysicsExt localPhysicsExt = getController().getPhysics();
/*  74: 74 */      if (getBody() != null) {
/*  75: 75 */        localPhysicsExt.removeObject(getBody());
/*  76:    */      }
/*  77:    */    }
/*  78: 78 */    this.height = -1.0F;
/*  79: 79 */    this.timeSpendUp = 0.0F;
/*  80: 80 */    setChanged();
/*  81: 81 */    notifyObservers(Boolean.valueOf(false));
/*  82:    */  }
/*  83:    */  
/*  89:    */  public com.bulletphysics.dynamics.RigidBody getBody()
/*  90:    */  {
/*  91: 91 */    return this.body;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public q getSignificator() {
/*  95: 95 */    return this.significator;
/*  96:    */  }
/*  97:    */  
/* 102:    */  public boolean isActive()
/* 103:    */  {
/* 104:104 */    return this.height >= 0.0F;
/* 105:    */  }
/* 106:    */  
/* 110:    */  public void refreshLiftCapabilities() {}
/* 111:    */  
/* 114:    */  public void setBody(com.bulletphysics.dynamics.RigidBody paramRigidBody)
/* 115:    */  {
/* 116:116 */    this.body = paramRigidBody;
/* 117:    */  }
/* 118:    */  
/* 123:    */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 124:    */  {
/* 125:125 */    this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 126:126 */    this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 127:127 */    this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/* 128:    */  }
/* 129:    */  
/* 130:    */  public void update(xq paramxq) {
/* 131:131 */    if (isActive()) {
/* 132:132 */      paramxq = paramxq.a() * Math.max(1.0F, (getMax().b - getMin().b) / 16.0F);
/* 133:133 */      if (this.height < this.maxHeight) {
/* 134:134 */        this.height += paramxq;
/* 135:135 */        this.t.origin.y += paramxq;
/* 136:136 */        getBody().setActivationState(1);
/* 137:    */        
/* 138:138 */        getBody().getMotionState().setWorldTransform(this.t);
/* 139:139 */        getBody().setWorldTransform(this.t);return;
/* 140:    */      }
/* 141:141 */      this.timeSpendUp += paramxq;
/* 142:    */      
/* 143:143 */      if (this.timeSpendUp > 5.0F) {
/* 144:144 */        deactivate();
/* 145:    */      }
/* 146:    */    }
/* 147:    */  }
/* 148:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.lift.LiftUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */