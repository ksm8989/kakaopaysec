package com.test.kakaopay;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtils {
    private static Log logger = LogFactory.getLog(CsvUtils.class);

    public static List<List<String>> readToList(String path) {
        List<List<String>> list = new ArrayList<List<String>>();

        URL url = CsvUtils.class.getClassLoader().getResource(path);

        BufferedReader br = null;
        try {
            File csv = new File(url.toURI());
            FileInputStream input = new FileInputStream(csv);
            InputStreamReader reader = new InputStreamReader(input, "UTF-8");
            br = new BufferedReader(reader);

            String line = "";

            while ((line = br.readLine()) != null) {
                String[] token = line.split(",");
                List<String> tempList = new ArrayList<String>(Arrays.asList(token));
                list.add(tempList);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }


        return list;
    }
}