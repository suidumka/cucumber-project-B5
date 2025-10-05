package io.loop.testAI;

import io.loop.utilities.ChatGPTClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestAI {
    private static final Logger log = LoggerFactory.getLogger(TestAI.class);

    public static void main(String[] args) {
        String aiPrompt = "Generate me scenarios for www.google.com in Gherkin language, make sure to add negative ones as well";
         // you can read it from somewhere else as well
        String aiResponse = ChatGPTClient.getResponseFromPrompt(aiPrompt);

        System.out.println("\n.............. •••AI Generated Suggestions Start••• ........................\n");
        System.out.println(aiResponse);
        System.out.println("\n.............. •••AI Generated Suggestions End••• ........................\n");


        //folder path
        String folderPath = "/src/test/resources/ai_suggestions";

        //dynamic naming
        LocalDateTime localDateTime = LocalDateTime.now();
        String timeStamp = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String fileName = "ai_suggestions_"  + timeStamp + ".txt";

        try {
            //create folder if it does NOT exist
            Files.createDirectories(Paths.get(folderPath));

            //write AI output to the file
            PrintWriter out = new PrintWriter(folderPath + fileName);
            out.println(aiResponse);
            out.close();

            System.out.println("Output saved to: " + folderPath + fileName);
        }catch (IOException e) {
            System.out.println("Output failed to save output to file");
            e.printStackTrace();
        }
    }


}
