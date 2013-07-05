package org.lwjgl.util.mapped;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface MappedType
{
  public abstract int padding();

  public abstract boolean cacheLinePadding();

  public abstract int align();

  public abstract boolean autoGenerateOffsets();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.mapped.MappedType
 * JD-Core Version:    0.6.2
 */