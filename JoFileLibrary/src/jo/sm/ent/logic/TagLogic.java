package jo.sm.ent.logic;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import jo.sm.ent.data.Tag;
import jo.sm.ent.data.TagType;
import jo.sm.logic.DebugLogic;
import jo.vecmath.Point3b;
import jo.vecmath.Point3i;
import jo.vecmath.Vector3f;

public class TagLogic
{
    public static Tag readFile(InputStream is, boolean closeStream)
            throws IOException
    {
        DebugLogic.debug("Reading file");
        DebugLogic.indent();
        if (!(is instanceof PushbackInputStream))
            is = new PushbackInputStream(is, 2);
        byte header[] = new byte[2];
        is.read(header);
        ((PushbackInputStream)is).unread(header);
        DataInputStream dis;
        if (header[0] == 31 && header[1] == -117)
        {
            DebugLogic.debug("Zipped input");
            dis = new DataInputStream(new GZIPInputStream(is, 4096));
        }
        else
        {
            dis = new DataInputStream(new BufferedInputStream(is, 4096));
            dis.readShort();
        }
        byte t = dis.readByte();
        TagType type = TagType.values()[t];
        Tag input = null;
        if (type == TagType.FINISH)
        {
            input = new Tag(TagType.FINISH, null,  ((TagLogic[])(null)));
        }
        else
        {
            String name = dis.readUTF();
            DebugLogic.debug("Reading "+name);
            DebugLogic.indent();
            try
            {
                Object val = readValue(dis, type);
                input = new Tag(type, name, val);
            }
            catch (IOException e)
            {
                System.err.println("EXCEPTION WHILE READING TAG " + name);
                throw e;
            }
            DebugLogic.outdent();
        }
        if (closeStream)
        {
            DebugLogic.debug("Closing");
            is.close();
        }
        DebugLogic.outdent();
        DebugLogic.debug("Done reading file");
        return input;
    }

    public static void writeFile(Tag tag, OutputStream os, boolean closeFile)
            throws IOException
    {
        DataOutputStream dos;
        if (os instanceof DataOutputStream)
            dos = (DataOutputStream)os;
        else
            dos = new DataOutputStream(os);
        dos.writeShort(0);
        dos.writeByte(tag.getType().ordinal());
        if (tag.getType() != TagType.FINISH)
        {
            dos.writeUTF(tag.getName());
            writeValue(tag, dos);
        }
        if (closeFile)
            dos.close();
    }
    
    private static Object readValue(DataInputStream dis, TagType type)
            throws IOException
    {
        DebugLogic.indent();
    	Object value = null;
        switch (type)
        {
            case FINISH:
                value = null;
                break;
            case BYTE:
            	value = Byte.valueOf(dis.readByte());
                break;
            case SHORT:
            	value = Short.valueOf(dis.readShort());
                break;
            case INT:
            	value = Integer.valueOf(dis.readInt());
                break;
            case LONG:
            	value = Long.valueOf(dis.readLong());
                break;
            case FLOAT:
            	value = Float.valueOf(dis.readFloat());
                break;
            case DOUBLE:
            	value = Double.valueOf(dis.readDouble());
                break;
            case BYTE_ARRAY:
            {
                byte[] buf = new byte[dis.readInt()];
                dis.readFully(buf);
                value = buf;
                break;
            }
            case STRING:
                value = dis.readUTF();
                break;
            case VECTOR3f:
                value = new Vector3f(dis.readFloat(),
                        dis.readFloat(),
                        dis.readFloat());
                break;
            case Point3i:
                value = new Point3i(dis.readInt(),
                        dis.readInt(), dis.readInt());
                break;
            case VECTOR3b:
                value = new Point3b((byte)dis.read(),
                        (byte)dis.read(), (byte)dis.read());
                break;
            case LIST:
            {
                DebugLogic.debug("Reading list");
                byte st = dis.readByte();
                TagType subtype = TagType.values()[st];
                int len = dis.readInt();
                Tag tagbuf[] = new Tag[len];
                for (int j = 0; j < len; j++)
                    tagbuf[j] = new Tag(subtype, null, readValue(dis, subtype));
                if (tagbuf.length == 0)
                    value = subtype;
                else
                    value = tagbuf;
                break;
            }
            case STRUCT:
            {
                DebugLogic.debug("Reading struct");
                List<Tag> inbuf = new ArrayList<Tag>();
                TagType nextType;
                do
                {
                    byte nt = dis.readByte();
                    nextType = TagType.values()[nt];
                    String name = null;
                    String msg = "Reading member #"+(inbuf.size() + 1);
                    if (nextType != TagType.FINISH)
                    {
                        name = dis.readUTF();
                        msg += " " + name;
                    }
                    msg += " ("+nextType+")";
                    DebugLogic.debug(msg);
                    Object val = readValue(dis, nextType);
                    Tag t = new Tag(nextType, name, val);
                    inbuf.add(t);
                } while (nextType != TagType.FINISH);
                value = inbuf.toArray(new Tag[0]);
                break;
            }
            case SERIALIZABLE:
            {
                DebugLogic.debug("Reading Serializable");
                List<Tag> tagbuf = new ArrayList<Tag>();
                for (;;)
                {
                    byte st = dis.readByte();
                    TagType subtype = TagType.values()[st]; 
                    if (subtype == TagType.FINISH)
                    {
                        tagbuf.add(new Tag());
                        break;
                    }
                    String name = dis.readUTF();
                    DebugLogic.debug("Reading element #"+(tagbuf.size() + 1)+" "+name+" ("+subtype+")");
                    Object val = readValue(dis, subtype);
                    Tag t = new Tag(subtype, name, val);
                    tagbuf.add(t);
                }
                value = tagbuf.toArray(new Tag[0]);
                break;
            }
        }
        DebugLogic.outdent();
        return value;
    }


    private static void writeValue(Tag tag, DataOutputStream dos)
            throws IOException
    {
        switch (tag.getType())
        {
            case FINISH:
                return;
            case BYTE:
                dos.writeByte(((Byte)tag.getValue()).byteValue());
                return;
            case SHORT:
                dos.writeShort(((Short)tag.getValue()).shortValue());
                return;
            case INT: 
                dos.writeInt(((Integer)tag.getValue()).intValue());
                return;
            case LONG:
                dos.writeLong(((Long)tag.getValue()).longValue());
                return;
            case FLOAT:
                dos.writeFloat(((Float)tag.getValue()).floatValue());
                return;
            case DOUBLE:
                dos.writeDouble(((Double)tag.getValue()).doubleValue());
                return;
            case BYTE_ARRAY:
                byte outbuf[] = (byte[])tag.getValue();
                dos.writeInt(outbuf.length);
                dos.write(outbuf);
                return;
            case STRING:
                dos.writeUTF((String)tag.getValue());
                return;
            case VECTOR3f:
                dos.writeFloat(((Vector3f)tag.getValue()).x);
                dos.writeFloat(((Vector3f)tag.getValue()).y);
                dos.writeFloat(((Vector3f)tag.getValue()).z);
                return;
            case Point3i:
                dos.writeInt(((Point3i)tag.getValue()).x);
                dos.writeInt(((Point3i)tag.getValue()).y);
                dos.writeInt(((Point3i)tag.getValue()).z);
                return;
            case VECTOR3b:
                dos.write(((Point3b)tag.getValue()).x);
                dos.write(((Point3b)tag.getValue()).y);
                dos.write(((Point3b)tag.getValue()).z);
                return;
            case LIST:
            {
                Tag[] val = (Tag[])tag.getValue();
                if (val.length > 0)
                    dos.writeByte(val[0].getType().ordinal());
                else
                    dos.writeByte(tag.getSubType().ordinal());
                dos.writeInt(val.length);
                for (int i = 0; i < val.length; i++)
                {
                    Tag v = val[i];
                    writeValue(v, dos);
                }
                /*
                 * dataoutputstream.writeByte(((Ab)a_java_lang_Object_fld).
                 * getFactoryId());
                 * ((Ab)a_java_lang_Object_fld).writeToTag(dataoutputstream);
                 */
                return;
            }
            case STRUCT:
            {
                Tag[] tagbuf = (Tag[])tag.getValue();
                for (Tag t : tagbuf)
                {
                    dos.writeByte(t.getType().ordinal());
                    if (t.getType() != TagType.FINISH)
                        dos.writeUTF(t.getName());
                    writeValue(t, dos);
                }
                /*
                 * Ad aad[] = (Ad[])a_java_lang_Object_fld;
                 * dataoutputstream.writeByte(b.ordinal());
                 * dataoutputstream.writeInt(aad.length); int i = aad.length;
                 * for(int j = 0; j < i; j++)
                 * aad[j].writeValue(dataoutputstream);
                 */
                return;
            }
            case SERIALIZABLE:
            {
                Tag[] tagbuf = (Tag[])tag.getValue();
                for (Tag t : tagbuf)
                {
                    dos.writeByte(t.getType().ordinal());
                    if (t.getType() != TagType.FINISH)
                    {
                        dos.writeUTF(t.getName() == null ? "null" : t.getName());
                        writeValue(t, dos);
                    }
                }
                return;
            }
        }
    }

    public static void setValue(Tag tag, Object value)
    {
        switch (tag.getType())
        {
            case FINISH:
                if (value != null)
                    throw new IllegalArgumentException();
                break;
            case BYTE:
                if (!(value instanceof Byte))
                    throw new IllegalArgumentException();
                break;
            case SHORT:
                if (!(value instanceof Short))
                    throw new IllegalArgumentException();
                break;
            case INT:
                if (!(value instanceof Integer))
                    throw new IllegalArgumentException();
                break;
            case LONG:
                if (!(value instanceof Long))
                    throw new IllegalArgumentException();
                break;
            case FLOAT:
                if (!(value instanceof Float))
                    throw new IllegalArgumentException();
                break;
            case DOUBLE:
                if (!(value instanceof Double))
                    throw new IllegalArgumentException();
                break;
            case BYTE_ARRAY:
                if (!(value instanceof byte[]))
                    throw new IllegalArgumentException();
                break;
            case STRING:
                if (!(value instanceof String))
                    throw new IllegalArgumentException();
                break;
            case VECTOR3f:
                if (!(value instanceof Vector3f))
                    throw new IllegalArgumentException();
                break;
            case Point3i:
                if (!(value instanceof Point3i))
                    throw new IllegalArgumentException();
                break;
            case VECTOR3b:
                if (!(value instanceof Point3b))
                    throw new IllegalArgumentException();
                break;
            case LIST:
                if (value instanceof TagType)
                {
                    tag.setSubType((TagType)value);
                    value = new Tag[0];
                    break;
                }
                if (!(value instanceof Tag[]))
                    throw new IllegalArgumentException();
                tag.setSubType(((Tag[])value)[0].getType());
                break;
            case STRUCT:
                if (!(value instanceof Tag[]))
                    throw new IllegalArgumentException();
                break;
            case SERIALIZABLE:
                if (!(value instanceof Tag[]))
                    throw new IllegalArgumentException();
                break;
            default:
                throw new IllegalArgumentException();
        }
        tag.setValue(value);
    }
}
