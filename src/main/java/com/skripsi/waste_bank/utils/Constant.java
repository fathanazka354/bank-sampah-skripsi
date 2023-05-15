package com.skripsi.waste_bank.utils;

import org.springframework.stereotype.Service;

@Service
public class Constant {
    public static final String FIREBASE_SDK_JSON = "templates/serviceAccountKey2.json";//copy the sdk-config file root address, if its in root ,filename is enough
    public static final String FIREBASE_BUCKET = "gs://skripsi-be.appspot.com";//enter your bucket name
    public static final String FIREBASE_PROJECT_ID ="skripsi-be";//enter your project id
    public static final String DEFAULT_URL = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fionicframework.com%2Fdocs%2Fapi%2Favatar&psig=AOvVaw0geLoqs4nDurSIiiVg5wvf&ust=1679379009769000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCOCVv7js6f0CFQAAAAAdAAAAABAE";
}
