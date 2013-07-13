package jo.sm.logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.vecmath.Point3b;
import javax.vecmath.Point3f;
import javax.vecmath.Point3i;
import javax.vecmath.Point3s;

public class IOLogic
{
    public static Point3b readPoint3b(DataInputStream dis) throws IOException
    {
        return new Point3b(dis.readByte(), dis.readByte(), dis.readByte());
    }
    public static Point3s readPoint3s(DataInputStream dis) throws IOException
    {
        return new Point3s(dis.readShort(), dis.readShort(), dis.readShort());
    }
    public static Point3i readPoint3i(DataInputStream dis) throws IOException
    {
        return new Point3i(dis.readInt(), dis.readInt(), dis.readInt());
    }
    public static Point3f readPoint3f(DataInputStream dis) throws IOException
    {
        return new Point3f(dis.readFloat(), dis.readFloat(), dis.readFloat());
    }
    public static void write(DataOutputStream dos, Point3b v) throws IOException
    {
        dos.writeByte(v.x);
        dos.writeByte(v.y);
        dos.writeByte(v.z);
    }
    public static void write(DataOutputStream dos, Point3s v) throws IOException
    {
        dos.writeShort(v.x);
        dos.writeShort(v.y);
        dos.writeShort(v.z);
    }
    public static void write(DataOutputStream dos, Point3i v) throws IOException
    {
        dos.writeInt(v.x);
        dos.writeInt(v.y);
        dos.writeInt(v.z);
    }
    public static void write(DataOutputStream dos, Point3f v) throws IOException
    {
        dos.writeFloat(v.x);
        dos.writeFloat(v.y);
        dos.writeFloat(v.z);
    }
}
