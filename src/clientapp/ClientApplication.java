package clientapp;

import com.decryption.utility.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class ClientApplication {
    public static void main(String[] args) throws IOException, ParseException {

        boolean isLicenseAvailable = false;

        Scanner scanner = new Scanner(System.in);

        String filePath = "D:\\WORK\\Hackathon2021\\ClientAplication\\src\\text.txt";
        String requestURL = "http://localhost:8081/uploadLicense";

        List<String> generalLicensedFeatures = new ArrayList<>();
        generalLicensedFeatures.add("feature1");
        generalLicensedFeatures.add("feature2");
        generalLicensedFeatures.add("feature3");

        List<String> proprietaryLicensedFeatures = new ArrayList<>();
        proprietaryLicensedFeatures.add("AutoTuning");
        proprietaryLicensedFeatures.add("SyncedTuning");
        proprietaryLicensedFeatures.add("PlayTogether");

        Map<String,Boolean> featureAndItsVisibility = IsFeatureLicensedImplementation.isFeatureLicensed(filePath, proprietaryLicensedFeatures);
        for(boolean value : featureAndItsVisibility.values()){
            if (value) {
                isLicenseAvailable = true;
                break;
            }
        }

        //clean the license file
        System.out.println("Hi Welcome to Harman Licensing feature");
        System.out.println("The available features are " +
                "\n1. Feature 1"+
                "\n2. Feature 2"+
                "\n3. Feature 3"+
                "\n4. AutoTuning" +
                "\n5. SyncedTuning" +
                "\n6. Play Together"
        );

//        if(!isLicenseAvailable){
//
//
//        }

        ReadLocalLicenseImplementation.requestReadLicense(filePath, requestURL);





    }
}
