#
# This ProGuard configuration file illustrates how to process applications.
# Usage:
#     java -jar proguard.jar @applications.pro
#

# Specify the input jars, output jars, and library jars.

-injars  ../publish/build/StarMade-pac.jar
-outjars ../publish/build/StarMade-pacObf.jar

-libraryjars <java.home>/lib/rt.jar
-libraryjars ../lib/dom4j-1.6.1.jar
-libraryjars ../lib/jaxen-1.1.4.jar
-libraryjars ../lib/jbullet.jar
-libraryjars ../lib/jogg-0.0.7.jar
-libraryjars ../lib/jorbis-0.0.15.jar
-libraryjars ../lib/unused/jinput.jar
-libraryjars ../lib/junit-4.10.jar
-libraryjars ../lib/unused/pdfview.jar
-libraryjars ../lib/slick.jar
-libraryjars ../lib/vecmath.jar
-libraryjars ../lib/lwjgl.jar
-libraryjars ../lib/lwjgl_util.jar




-keep class !org.schema.** { *; }
-keep public class org.schema.game.common.Starter
-keep class org.schema.schine.network.StateInterface { *; }
-keep class org.schema.schine.network.** { *; }
-keep class org.schema.game.network.** { *; }

#-keep class !org.schema.schine.network.synchronization.** { *; }
#-keep class !org.schema.schine.network.server.ServerProcessor { *; }
#-keep class !org.schema.schine.network.client.ClientProcessor { *; }
#-keep class !org.schema.schine.network.client.Command { *; }

-keep public class org.schema.schine.network.objects.SimpleTransformableSendableObject { *; }
-keepclassmembers class * extends org.schema.game.common.data.world.SimpleTransformableSendableObject{
 	public <init>(org.schema.schine.network.StateInterface);
}


# Save the obfuscation mapping to a file, so you can de-obfuscate any stack
# traces later on. Keep a fixed source file attribute and all line number
# tables to get line numbers in the stack traces.
# You can comment this out if you're not interested in stack traces.




-printmapping ../publish/StarMade.map
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# Preserve all annotations.

-keepattributes *Annotation*

# You can print out the seeds that are matching the keep options below.

#-printseeds out.seeds

# Preserve all public applications.

-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}

# Preserve all native method names and the names of their classes.

-keepclasseswithmembernames class * {
    native <methods>;
}





# Preserve the special static methods that are required in all enumeration
# classes.

-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your application doesn't use serialization.
# If your code contains serializable classes that have to be backward 
# compatible, please refer to the manual.

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Your application may contain more items that need to be preserved; 
# typically classes that are dynamically created using Class.forName:

# -keep public class mypackage.MyClass
# -keep public interface mypackage.MyInterface
# -keep public class * implements mypackage.MyInterface

-forceprocessing
