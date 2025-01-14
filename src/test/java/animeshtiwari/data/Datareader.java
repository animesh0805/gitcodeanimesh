package animeshtiwari.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Datareader {
    public void getJsonDataToMap() throws IOException {
        //read json to string
        File fis = new File(System.getProperty("user.dir")+"\\src\\test\\java\\animeshtiwari\\data\\Datareader.java");
        String jsoncontent=FileUtils.readFileToString(fis);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {
        });

        //String to hashmap jackson data bind
    }
}
