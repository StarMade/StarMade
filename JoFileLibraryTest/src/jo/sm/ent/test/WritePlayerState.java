package jo.sm.ent.test;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class WritePlayerState extends WriteBase
{
    @Test
    public void readPlayerCharacter()
    {
        List<File> files = findFiles("_PLAYERSTATE_");
        testFiles(files);
    }
}
