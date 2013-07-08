/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.ContactDestroyedCallback;
/*  4:   */
/* 78:   */class SequentialImpulseConstraintSolverExt$1
/* 79:   */  extends ContactDestroyedCallback
/* 80:   */{
/* 81:   */  SequentialImpulseConstraintSolverExt$1(SequentialImpulseConstraintSolverExt paramSequentialImpulseConstraintSolverExt) {}
/* 82:   */  
/* 83:   */  public boolean contactDestroyed(Object paramObject)
/* 84:   */  {
/* 85:85 */    assert (paramObject != null);
/* 86:86 */    SequentialImpulseConstraintSolverExt.access$010(this.this$0);
/* 87:   */    
/* 90:90 */    return true;
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SequentialImpulseConstraintSolverExt.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */