import java.util.*;

/**
 * Represents an album of images.
 */
public class Album 
{
     private List<ImageRecord> records;
     private ListIterator<ImageRecord> itr;
     private ImageRecord currentImage;

     public Album()
     {
         records = new ArrayList<ImageRecord>();
     }

     public void insertImage(ImageRecord newImageRecord)
     {
         records.add(newImageRecord);
     }

     public void createIterator()
     {
         itr = records.listIterator();
     }

     public ImageRecord getNext()
     {
         ImageRecord img;
         img = itr.next();
         currentImage = img;
         return img;
     }

     public ImageRecord getPrev()
     {
         ImageRecord img = itr.previous();
         currentImage = img;
         return img;
     }

     public ImageRecord getInfo()
     {
         return currentImage;
     }
}
