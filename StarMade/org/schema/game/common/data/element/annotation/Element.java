package org.schema.game.common.data.element.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Element
{
  public abstract String tag();

  public abstract int from();

  public abstract int to();

  public abstract String[] states();

  public abstract String collectionElementTag();

  public abstract String collectionType();

  public abstract boolean textArea();

  public abstract boolean level();

  public abstract boolean factory();

  public abstract boolean vector3f();

  public abstract boolean updateTextures();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.annotation.Element
 * JD-Core Version:    0.6.2
 */