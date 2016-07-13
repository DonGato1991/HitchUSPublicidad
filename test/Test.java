
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.glassfish.jersey.internal.util.Base64;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JuanAndresCaspi
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        File file = new File("C://icon.png");
        BufferedInputStream bufferis = new BufferedInputStream(new FileInputStream(file));
        int bytes = (int) file.length();
        byte[] buffer = new byte[bytes];
        int readBytes = bufferis.read(buffer);
        bufferis.close();

        /*Codificamos a base 64*/
        byte[] encodedString = Base64.encode(buffer);
        System.out.println("edf"+encodedString.toString());
        /*Decodificamos y creamos una imagen nueva que será idéntica a la inicial*/
        byte[] buffer2 = Base64.decode(encodedString);
        File file2 = new File("C://salida.png");
        BufferedOutputStream bufferos = new BufferedOutputStream(new FileOutputStream(file2));
        bufferos.write(buffer2);
        bufferos.close();
        System.out.println(encodedString);

    }

}
