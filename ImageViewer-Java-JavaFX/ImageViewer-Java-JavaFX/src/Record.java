/**
 * Represents an image in an album.
 */
public class Record implements ImageRecord
{
    private String filename;
    private String caption;
    
    public Record(String newFilename, String newCaption)
    {
        filename = newFilename;
        caption = newCaption;
    }
    
    public String getFilename()
    {
        return filename;
    }
    
    public String getCaption()
    {
        return caption;
    }
}

