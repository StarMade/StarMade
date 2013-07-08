import com.bulletphysics.collision.dispatch.CollisionObject;

public class class_1403
{
  public long field_1620;
  public long field_1621;
  public CollisionObject field_1620;
  public CollisionObject field_1621;
  
  public class_1403(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, long paramLong1, long paramLong2)
  {
    this.jdField_field_1620_of_type_ComBulletphysicsCollisionDispatchCollisionObject = paramCollisionObject1;
    this.jdField_field_1621_of_type_ComBulletphysicsCollisionDispatchCollisionObject = paramCollisionObject2;
    if ((!jdField_field_1620_of_type_Boolean) && (paramCollisionObject1 == paramCollisionObject2)) {
      throw new AssertionError();
    }
    this.jdField_field_1620_of_type_Long = paramLong1;
    this.jdField_field_1621_of_type_Long = paramLong2;
  }
  
  public boolean equals(Object paramObject)
  {
    return (((paramObject = (class_1403)paramObject).jdField_field_1620_of_type_ComBulletphysicsCollisionDispatchCollisionObject.equals(this.jdField_field_1620_of_type_ComBulletphysicsCollisionDispatchCollisionObject)) && (paramObject.jdField_field_1621_of_type_ComBulletphysicsCollisionDispatchCollisionObject.equals(this.jdField_field_1621_of_type_ComBulletphysicsCollisionDispatchCollisionObject))) || ((paramObject.jdField_field_1621_of_type_ComBulletphysicsCollisionDispatchCollisionObject.equals(this.jdField_field_1620_of_type_ComBulletphysicsCollisionDispatchCollisionObject)) && (paramObject.jdField_field_1620_of_type_ComBulletphysicsCollisionDispatchCollisionObject.equals(this.jdField_field_1621_of_type_ComBulletphysicsCollisionDispatchCollisionObject)));
  }
  
  static
  {
    jdField_field_1620_of_type_Boolean = !zY.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1403
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */