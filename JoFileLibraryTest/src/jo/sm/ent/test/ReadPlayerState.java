package jo.sm.ent.test;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class ReadPlayerState extends ReadBase
{
    @Test
    public void readPlayerCharacter()
    {
        List<File> files = findFiles("_PLAYERSTATE_");
        testFiles(files);
    }
}
