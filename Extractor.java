/**
 * Created by Alessandro Arosio on 12/04/2017.
 */
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;


public class Extractor {
    public static void main(String[] args) {
        try
        {
            String userHomeFolder = System.getProperty("user.home");
            JOptionPane.showMessageDialog(null,"Please move the file on the desktop");
            String path = JOptionPane.showInputDialog("Input the file name:");
            File file = new File(userHomeFolder + "\\desktop\\" + path);
            PrintWriter newFile = new PrintWriter(userHomeFolder + "\\desktop\\Temperature extracted.csv");
            Scanner scanner = new Scanner(file);
            JOptionPane.showMessageDialog(null, "the new file has been generated on the desktop");
            newFile.print("Date/Time," + "GPU No.," + "Model," + "Bus-ID," +
                    "Temp (C)," + "Pwr:Usage (W)," + "Pwr:Cap (W)," + "Memory usage (MiB)," +"Memory CAP (MiB)," + "GPU-Util %");
            newFile.println();
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                if (line.contains("2017"))
                {
                    newFile.print(line.substring(0,20) + ","); // Date/Time field
                }
                if (line.contains("0 |"))
                {
                    newFile.print(line.substring(4, 5) + ","); // No. of GPU
                    newFile.print(line.substring(7, 15) + ","); // Model field
                    newFile.print(line.substring(34, 46) + ","); // Bus-ID field
                }
                if (line.contains("| N/A"))
                {
                    newFile.print(line.substring(8,10) + ","); // Temperature field
                    newFile.print(line.substring(21, 23) + ","); // Power current usage field
                    newFile.print(line.substring(28, 30) + ","); // Power CAP field
                    newFile.print(line.substring(36, 40) + ","); // Memory current usage field
                    newFile.print(line.substring(47, 51) + ","); // Memory CAP field
                    newFile.print(line.substring(60, 63) + ","); // GPU Util field
                    newFile.println();
                }
            }
            newFile.close();
        }
        catch (IOException ioe)
        {
            JOptionPane.showMessageDialog(null, "IO Exception. file not found, check path name");
        }
    }
}
