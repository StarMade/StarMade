import org.schema.game.server.controller.world.factory.terrain.NoiseGeneratorOctaves;

public final class class_1140
  extends class_1119
{
  public class_1140(long paramLong)
  {
    super(paramLong);
    this.field_1373 = 0.65D;
    this.jdField_field_1374_of_type_Boolean = true;
  }
  
  protected final void a()
  {
    this.jdField_field_1373_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves = new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 20);
    this.jdField_field_1374_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves = new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 19);
    this.jdField_field_1375_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves = new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 7);
    new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 5);
    this.jdField_field_1376_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves = new NoiseGeneratorOctaves(this.jdField_field_1373_of_type_JavaUtilRandom, 8);
  }
  
  protected final void a1(int paramInt1, int paramInt2)
  {
    this.jdField_field_1376_of_type_ArrayOfDouble = this.jdField_field_1376_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves.a1(this.jdField_field_1376_of_type_ArrayOfDouble, paramInt1, paramInt2, 100.0D, 100.0D);
    this.jdField_field_1373_of_type_ArrayOfDouble = this.jdField_field_1375_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves.a(this.jdField_field_1373_of_type_ArrayOfDouble, paramInt1, 0, paramInt2, 17, 9.860300000000001D, 3.580066666666667D, 9.860300000000001D);
    this.jdField_field_1374_of_type_ArrayOfDouble = this.jdField_field_1373_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves.a(this.jdField_field_1374_of_type_ArrayOfDouble, paramInt1, 0, paramInt2, 17, 1183.2360000000001D, 402.75749999999999D, 1183.2360000000001D);
    this.jdField_field_1375_of_type_ArrayOfDouble = this.jdField_field_1374_of_type_OrgSchemaGameServerControllerWorldFactoryTerrainNoiseGeneratorOctaves.a(this.jdField_field_1375_of_type_ArrayOfDouble, paramInt1, 0, paramInt2, 17, 1183.2360000000001D, 339.1642105263158D, 1183.2360000000001D);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1140
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */