package clientapp;

import com.decryption.utility.GetDateOfExpiryImplementation;
import com.decryption.utility.IsFeatureLicensedImplementation;
import com.decryption.utility.ReadLocalLicenseImplementation;
import com.sun.org.apache.xpath.internal.operations.Bool;
import utils.FileOperations;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class ClientApplication {
    public static void main(String[] args) throws IOException, ParseException {

        boolean isLicenseAvailable = false;

        Scanner scanner = new Scanner(System.in);

        String filePath = "D:\\WORK\\Hackathon2021\\ClientAplication\\resources\\license.txt";
        String requestURL = "http://localhost:8081/uploadLicense";

        List<String> generalLicensedFeatures = new ArrayList<>();
        generalLicensedFeatures.add("feature1");
        generalLicensedFeatures.add("feature2");
        generalLicensedFeatures.add("feature3");

        List<String> proprietaryLicensedFeatures = new ArrayList<>();
        proprietaryLicensedFeatures.add("AutoTuning");
        proprietaryLicensedFeatures.add("SyncedTuning");
        proprietaryLicensedFeatures.add("PlayTogether");

        Map<String, Boolean> featureAndItsVisibility = IsFeatureLicensedImplementation.isFeatureLicensed(filePath, proprietaryLicensedFeatures);
        for (boolean value : featureAndItsVisibility.values()) {
            if (value) {
                isLicenseAvailable = true;
                break;
            }
        }

        //clean the license file
        System.out.println("Hi Welcome to Harman Licensing feature");
        System.out.println("The available features are " +
                "\n1. Feature 1" +
                "\n2. Feature 2" +
                "\n3. Feature 3" +
                "\n4. AutoTuning" +
                "\n5. SyncedTuning" +
                "\n6. Play Together"
        );


        if (!isLicenseAvailable) {
            System.out.println("Do you want to access the paid features y/n?");
            String response = scanner.next();

            if (response.equals("y")) {
                File file = ReadLocalLicenseImplementation.requestReadLicense(filePath, requestURL);
                FileOperations.unZipFile(file);
                isLicenseAvailable = true;
                Map<String, Boolean> featureMap = IsFeatureLicensedImplementation.isFeatureLicensed(filePath, proprietaryLicensedFeatures);
                List<String> licensedFeatures = new ArrayList<>();

                for(Map.Entry<String, Boolean> map : featureMap.entrySet()){
                    if(map.getValue()) licensedFeatures.add(map.getKey());
                }

                Map<String, Date> timeToExpiryMap = GetDateOfExpiryImplementation.getLicenseExpiration(filePath,licensedFeatures);

                //execute
                for(Map.Entry<String, Date> map : timeToExpiryMap.entrySet()){
                    System.out.println("The feature "+map.getValue()+" expires on "+map.getValue());
                }

                //get local time match with time to expiry and run those features
            }

            else {
                // run(GLL)
                // run(licensed features)
            }
        }

    }
}