package com.insoutt.capacitor.wallpaper;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
public class WallpaperPlugin extends Plugin {

    @PluginMethod
    public void setWallpaper(PluginCall call) {

        if (!call.getData().has("name")) {
            call.reject("Must provide an name");
            return;
        }

        Context context = this.getContext();
        String name = call.getString("name");

        try {
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            int height = Resources.getSystem().getDisplayMetrics().heightPixels;
            // System.out.println("w: " + width + " h:" + height);

            int drawableResourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());

            // Fit image to screen size
            Bitmap tempBitMap = BitmapFactory.decodeResource(context.getResources(), drawableResourceId);
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
        } catch (Exception e) {
            e.printStackTrace();
            call.reject("Image " + name + " does not exists in drawable folder.", e);
        }
    }
}
