/*  1:   */import org.schema.game.common.data.element.BlockFactory;
/*  2:   */import org.schema.game.common.data.element.ElementKeyMap;
/*  3:   */
/*  4:   */public final class oy
/*  5:   */{
/*  6:   */  java.util.ArrayList a;
/*  7:   */  public short a;
/*  8:   */  
/*  9:   */  public oy()
/* 10:   */  {
/* 11:11 */    this.jdField_a_of_type_JavaUtilArrayList = new java.util.ArrayList();
/* 12:   */  }
/* 13:   */  
/* 15:   */  public final void a(short paramShort)
/* 16:   */  {
/* 17:   */    org.schema.game.common.data.element.ElementInformation localElementInformation;
/* 18:18 */    if ((localElementInformation = ElementKeyMap.getInfo(paramShort)).getFactory() != null) {
/* 19:19 */      this.jdField_a_of_type_Short = localElementInformation.getFactory().enhancer;
/* 20:20 */      for (int i = 0; i < localElementInformation.getFactory().input.length; i++) {
/* 21:   */        oz localoz;
/* 22:22 */        (localoz = new oz()).jdField_a_of_type_Short = paramShort;
/* 23:   */        
/* 24:24 */        for (int j = 0; j < localElementInformation.getFactory().input[i].length; j++) {
/* 25:25 */          localoz.jdField_a_of_type_JavaUtilArrayList.add(localElementInformation.getFactory().input[i][j]);
/* 26:   */        }
/* 27:27 */        for (j = 0; j < localElementInformation.getFactory().output[i].length; j++) {
/* 28:28 */          localoz.b.add(localElementInformation.getFactory().output[i][j]);
/* 29:   */        }
/* 30:30 */        this.jdField_a_of_type_JavaUtilArrayList.add(localoz);
/* 31:   */      }
/* 32:   */    }
/* 33:   */  }
/* 34:   */  
/* 61:   */  public final String toString()
/* 62:   */  {
/* 63:63 */    return "Factory Products: " + this.jdField_a_of_type_JavaUtilArrayList.size();
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */