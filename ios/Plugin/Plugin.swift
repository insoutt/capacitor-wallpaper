import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(WallpaperPlugin)
public class WallpaperPlugin: CAPPlugin {

    @objc func setWallpaper(_ call: CAPPluginCall) {
        print("Hola swift")
        call.success([
            "success": true
        ])
    }
}
