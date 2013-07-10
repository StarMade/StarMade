package jo.sm.ship.data;

import jo.sm.data.Vector3f;

/*
 * +        0         int                       unknown int
+        4         int                       unknown int
+        8         float[3]                 3d float vector (bounding box of ship)
+        20        float[3]                 3d float fector (bounding box of ship)
+        32        int                       number of block table entries (N)
+        36        blockEntry[N]             block entry
+        
+        blockEntry is a 6 byte value
+            0   short       blockID
+            2   int         blockQuantity 
 */
public class Meta 
{

}
