package com.example.timestyle;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageHelper {
    public static byte[] convertImageToByteArray(Context context, String imagePath) throws IOException {
        AssetManager assetManager = context.getAssets();

        try (InputStream inputStream = assetManager.open(imagePath); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            return baos.toByteArray();
        }
    }

    public static Bitmap convertByteArrayToBitmap(byte[] imageData){
        return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
    }
}
