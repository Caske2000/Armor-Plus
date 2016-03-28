package net.caske2000.armorplus.util;

import net.caske2000.armorplus.lib.Reference;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Caske2000 on 28/03/2016.
 */
public class VersionChecker implements Runnable
{
    private static boolean isLatestVersion = false;
    private static String latestVersion = "";

    @Override
    public void run()
    {
        InputStream in = null;
        try
        {
            in = new URL("https://raw.githubusercontent.com/Caske2000/Armor-Plus/master/MOD.VERSION").openStream();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            latestVersion = IOUtils.readLines(in).get(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(in);
        }
        System.out.println("Latest mod version = " + latestVersion);
        isLatestVersion = Reference.VERSION.equals(latestVersion);
        System.out.println("Are you running latest version = " + isLatestVersion);
    }

    public static String getLatestVersion()
    {
        return latestVersion;
    }

    public static boolean isLatestVersion()
    {
        return isLatestVersion;
    }
}
