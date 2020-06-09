package Homework_15_4;

import java.util.List;

public class FileAccess
{
    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     * for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath)
    {

    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path)
    {

    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content)
    {

    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path)
    {

        return null;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path)
    {

    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path)
    {
        return false;
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path)
    {
        return null;
    }
}
