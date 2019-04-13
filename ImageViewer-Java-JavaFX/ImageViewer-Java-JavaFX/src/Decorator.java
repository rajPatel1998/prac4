public abstract class Decorator implements ImageRecord
{
    protected ImageRecord next;

    public Decorator(ImageRecord inNext)
    {
        next = inNext;
    }

    public String getFilename()
    {
        return next.getFilename();
    }

    public String getCaption()
    {
        return next.getCaption();
    }
}