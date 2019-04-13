public class Date extends Decorator
{
    String date;
    public Date(ImageRecord next, String inDate)
    {
        super(next);
        date = inDate;
    }

    public String getFilename()
    {
        return next.getFilename();
    }

    public String getCaption()
    {
        return next.getCaption() + " Date : " + date;
    }

}