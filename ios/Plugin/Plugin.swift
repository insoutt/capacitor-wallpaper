import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(Wallpaper)
public class Wallpaper: CAPPlugin {

    @objc func setImage(_ call: CAPPluginCall) {
        call.success([
            "success": true
        ])
    }

    @objc func setBase64(_ call: CAPPluginCall) {
        call.success([
            "success": true
        ])
    }
}
