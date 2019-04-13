public class Gps extends Decorator
{
    private double one, two, three;

    public Gps(ImageRecord next, double inOne, double inTwo, double inThree)
    {
        super(next);
        one = inOne;
        two = inTwo;
        three = inThree;
    }

    public String getFilename()
    {
        return  next.getFilename();
    }

    public String getCaption()
    {
        return next.getCaption() + " GPS Coordinates : " + one + " " + two + " " + three ;
    }
}