package com.insoutt.capacitor.wallpaper;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import java.io.IOException;

@NativePlugin(
    permissions={
        Manifest.permission.SET_WALLPAPER_HINTS
    }
)
public class Wallpaper extends Plugin {

    @PluginMethod
    public void setImage(PluginCall call) {

        if (!call.getData().has("name")) {
            call.reject("Must provide an name");
            return;
        }

        Context context = this.getContext();
        String name = call.getString("name");

        try {
            int drawableResourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());

            // Fit image to screen size
            Bitmap tempBitMap = BitmapFactory.decodeResource(context.getResources(), drawableResourceId);

            setWallpaper(call, context, tempBitMap);
        } catch (Exception e) {
            e.printStackTrace();
            call.reject("Image " + name + " does not exists in drawable folder.", e);
        }
    }

    @PluginMethod
    public void setBase64(PluginCall call) {

        if (!call.getData().has("base64")) {
            call.reject("Must provide an base64 string");
            return;
        }

        Context context = this.getContext();
        String base64Image = call.getString("base64");

        // Transform base64 to Bitmap
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        try {
            setWallpaper(call, context, decodedByte);
        } catch (Exception e) {
            call.reject("Can not define base64 wallpaper.", e);
        }
    }

    private void setWallpaper(PluginCall call, Context context, Bitmap tempBitMap) {
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        // System.out.println("w: " + width + " h:" + height);

        Bitmap bitmap = Bitmap.createScaledBitmap(tempBitMap, width, height, true);

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        wallpaperManager.setWallpaperOffsetSteps(1, 1);
        wallpaperManager.suggestDesiredDimensions(width, height);

        try {
            // Define wallpaper
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSObject result = new JSObject();
        result.put("success", true);
        call.success(result);
    }
}
