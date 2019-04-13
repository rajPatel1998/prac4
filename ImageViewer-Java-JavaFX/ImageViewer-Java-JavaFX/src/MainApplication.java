import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
import java.lang.*;

/**
 * Main class representing the entry point (and controller) of the application.
 */
public class MainApplication extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args); // Run JavaFX
        // This will effectively do 'new MainApplication()' and then call 'start(...)'.
    }
    
    /**
     * Loads an image album and then creates a window to display it.
     */
    @Override
    public void start(Stage stage)
    {
        String albumFilename;
        
        // Input the album filename.
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter album filename: ");
        albumFilename = sc.nextLine();
        
        // Construct an album object.
        Album album = new Album();
        
        try
        {
            // Attempt to read an album file.
            readAlbumFile(albumFilename, album);
            album.createIterator();
            ImageRecord check = album.getNext();

            System.out.println(check.getFilename());
            System.out.println(check.getCaption());



            MainWindow window = new MainWindow(album, stage);
            stage.show();
        }
        catch(IOException e)
        {
            System.err.println("Error while reading " + albumFilename);
            System.exit(1);
        }
    }
    
    /**
     * Reads an album file, given a filename and an Album object. Returns true if
     * successful, or false if the file could not be read.
     *
     * @param albumFilename The file storing the list of image filenames and their captions.
     * @param album An Album object to populate.
     * 
     * @throws IOException If the file could not be read.
     */
    private static void readAlbumFile(String albumFilename, Album album) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(albumFilename));
        String line = reader.readLine();
        while(line != null)
        {
            if(line.trim().length() > 0) // Ignore blank lines
            {
                String[] parts = line.split(":");
                String imageFilename = parts[0];
                String imageCaption = "";

                //THIS CONDITION IS TO CHECK IF THERE ARE ANY LABELS
                if((parts.length > 2) && (parts.length <= 5))
                {
                    ImageRecord[] image = new ImageRecord[4];
                    image[0] = new Record(imageFilename, imageCaption);

                        //LOOP THROUGH THE LABELS
                        for(int i = 2; i < parts.length; i++)
                        {
                            String[] lables = parts[i].split("=");   //SPLIT EACH LABEL

                            if(lables[0].equals("rating"))
                            {
                                image[i-1] = new Rating(image[i-2], Integer.parseInt(lables[1]));
                            }
                            else if(lables[0].equals("gps"))
                            {
                                double[] gpsCoordinates = new double[3];
                                String[] extractCoordinates = lables[1].split(" ");
                                for(int j = 0; j < extractCoordinates.length; j++)
                                {
                                    gpsCoordinates[j] = Double.parseDouble(extractCoordinates[i]);
                                }

                                image[i-1] = new Gps(image[i-2], gpsCoordinates[0], gpsCoordinates[1], gpsCoordinates[2]);
                                gpsCoordinates = null;
                            }
                            else if(lables[0].equals("date"))
                            {
                                image[i-1] = new Date(image[i-2], lables[1]);
                            }

                        }
                     album.insertImage(image[parts.length - 1]);
                }
                else if(parts.length == 2)
                {
                    imageCaption = parts[1];
                    ImageRecord newImage = new Record(imageFilename, imageCaption);
                    album.insertImage(newImage);
                }
            }
                        
            line = reader.readLine();
        }
        reader.close();
    }

}
