package jo.sm.logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jo.sm.data.Vector3b;
import jo.sm.data.Vector3f;
import jo.sm.data.Vector3i;
import jo.sm.data.Vector3s;

public class IOLogic
{
    public static Vector3b readVector3b(DataInputStream dis) throws IOException
    {
        return new Vector3b(dis.readByte(), dis.readByte(), dis.readByte());
    }
    public static Vector3s readVector3s(DataInputStream dis) throws IOException
    {
        return new Vector3s(dis.readShort(), dis.readShort(), dis.readShort());
    }
    public static Vector3i readVector3i(DataInputStream dis) throws IOException
    {
        return new Vector3i(dis.readInt(), dis.readInt(), dis.readInt());
    }
    public static Vector3f readVector3f(DataInputStream dis) throws IOException
    {
        return new Vector3f(dis.readFloat(), dis.readFloat(), dis.readFloat());
    }
    public static void write(DataOutputStream dos, Vector3b v) throws IOException
    {
        dos.writeByte(v.a);
        dos.writeByte(v.b);
        dos.writeByte(v.c);
    }
    public static void write(DataOutputStream dos, Vector3s v) throws IOException
    {
        dos.writeShort(v.a);
        dos.writeShort(v.b);
        dos.writeShort(v.c);
    }
    public static void write(DataOutputStream dos, Vector3i v) throws IOException
    {
        dos.writeInt(v.a);
        dos.writeInt(v.b);
        dos.writeInt(v.c);
    }
    public static void write(DataOutputStream dos, Vector3f v) throws IOException
    {
        dos.writeFloat(v.x);
        dos.writeFloat(v.y);
        dos.writeFloat(v.z);
    }
}
