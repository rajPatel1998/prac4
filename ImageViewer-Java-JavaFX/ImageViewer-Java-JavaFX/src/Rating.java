public class Rating extends Decorator
{
    private int rating;

    public Rating(ImageRecord next, int inRating)
    {
        super(next);
        rating = inRating;
    }

    public String getFilename()
    {
        return next.getFilename();
    }

    public String getCaption()
    {
        return next.getCaption() + " Rating : " + rating;
    }
}