package domain.Lectores;

import java.io.BufferedReader;
import java.io.FileReader;

public class LectorJSON_String {

    public LectorJSON_String() {
    }

    public String leerJSON(String path){ //direccion del archivo

        String json = "";

        try{
            BufferedReader bf = new BufferedReader(new FileReader(path));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                temp = temp + bfRead;
            }
            json = temp;

        }catch(Exception e){
            System.err.println("No se encontro archivo");
        }

        return json;
    }


}
