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
        
        let base64 = call.getString("base64") ?? ""
        let data = call.getString("data") ?? ""
       
        if(base64 == "") {
            call.reject("The 'base64' param is required")
            return;
        }
        if(data == "") {
            call.reject("The 'type' param is required")
            return;
        }
        
        let decodedData = NSData(base64Encoded: base64, options: [])
        if let data = decodedData {
            let decodedimage = UIImage(data: data as Data)
            if(decodedimage != nil) {
                UIImageWriteToSavedPhotosAlbum(decodedimage!, self, nil, nil)
            }
        } else {
            call.reject("Error with decodedData")
            return;
        }
        
        call.success([
            "success": true
        ])
    }
}
