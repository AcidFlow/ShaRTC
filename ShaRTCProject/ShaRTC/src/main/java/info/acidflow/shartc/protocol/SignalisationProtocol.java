package info.acidflow.shartc.protocol;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by acidflow on 27/10/13.
 */
public class SignalisationProtocol {

    public static String getMessage(BufferedReader reader) throws IOException {
        char c;
        StringBuilder lengthToRead = new StringBuilder();
        StringBuilder response = new StringBuilder();
            while(Character.isDigit(c = (char) reader.read())){
                lengthToRead.append(c);
            }
            response.append(c);
            int responseLenght = Integer.parseInt(lengthToRead.toString());
            for(int i = 1; i < responseLenght; i++){
                response.append((char) reader.read());
            }
            return response.toString();
    }


    public static void sendMessage(PrintWriter writer, JSONObject message){
        StringBuilder formatedMessage = new StringBuilder();
        formatedMessage.append(message.toString().length());
        formatedMessage.append(message.toString());
        writer.write(formatedMessage.toString());
        writer.flush();
    }

}
