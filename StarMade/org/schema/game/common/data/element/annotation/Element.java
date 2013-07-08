package org.schema.game.common.data.element.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Element
{
  String tag();
  
  int from() default -1;
  
  int to() default -1;
  
  String[] states() default {};
  
  String collectionElementTag() default "";
  
  String collectionType() default "";
  
  boolean textArea() default false;
  
  boolean level() default false;
  
  boolean factory() default false;
  
  boolean vector3f() default false;
  
  boolean updateTextures() default false;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.annotation.Element
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */