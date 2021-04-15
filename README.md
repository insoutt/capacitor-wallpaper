<p align="center"><br><img src="https://user-images.githubusercontent.com/236501/85893648-1c92e880-b7a8-11ea-926d-95355b8175c7.png" width="128" height="128" /></p>
<h3 align="center">Capacitor Wallpaper</h3>
<p align="center"><strong><code>insoutt/capacitor-wallpaper</code></strong></p>
<p align="center">
  Capacitor community plugin for something awesome.
</p>

<p align="center">
  <img src="https://img.shields.io/maintenance/yes/2020?style=flat-square" />
  <a href="https://github.com/insoutt/capacitor-wallpaper/actions?query=workflow%3A%22CI%22"><img src="https://img.shields.io/github/workflow/status/capacitor-community/example/CI?style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/capacitor-wallpaper"><img src="https://img.shields.io/npm/l/@insoutt/capacitor-wallpaper?style=flat-square" /></a>
<br>
  <a href="https://www.npmjs.com/package/capacitor-wallpaper"><img src="https://img.shields.io/npm/dw/@insoutt/capacitor-wallpaper?style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/capacitor-wallpaper"><img src="https://img.shields.io/npm/v/@insoutt/capacitor-wallpaper?style=flat-square" /></a>
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
<a href="#contributors-"><img src="https://img.shields.io/badge/all%20contributors-0-orange?style=flat-square" /></a>
<!-- ALL-CONTRIBUTORS-BADGE:END -->
</p>

## Maintainers

| Maintainer | GitHub | Social |
| -----------| -------| -------|
| insoutt    | [insoutt](https://github.com/insoutt) | [@insoutt](https://twitter.com/insoutt) |

## Installation

`npm i capacitor-wallpaper`

### iOS
No further steps are needed.

### Android
Register the plugin in your main activity
```java
import com.insoutt.capacitor.wallpaper.Wallpaper;

public class MainActivity extends BridgeActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Initializes the Bridge
    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
      // Additional plugins you've installed go here
      // Ex: add(TotallyAwesomePlugin.class);
      add(Wallpaper.class);
    }});
  }
}

```
## Configuration

No configuration required for this plugin

## Usage
You must import plugin
```javascript
import {Plugins} from '@capacitor/core';
const { Wallpaper } = Plugins;
```

### Usage for Android
On Android you can use two ways to define a wallpaper.

The first way you can use a base64 image.
```typescript
Wallpaper.setBase64({
    base64: string,
    data: string,
}):Promise<WallpaperResult>
```
| Parameter | Type | Description | Example |
| -----------| -------| -------| -------|
| base64    | string | Base64 value of image | `{image: ''}`|
| data    | string |  Type of image for example | `{data: 'data:image/png;base64,'}` |

The second way you can set an image located in `main/res/drawable` path.

```typescript
Wallpaper.setImage({ 
  name: string 
}): Promise<WallpaperResult>;
```

| Parameter | Type | Description | Example |
| -----------| -------| -------| -------|
| name    | string | Name of image in drawable path | `{name: 'myWallpaper'}`|


Interface WallpaperResult:
```javascript
export interface WallpaperResult {
  success: boolean
}
```

### Usage for iOS
For iOS there is no way to define a wallpaper programaticly

> In Apple devices, There is no provision for the change iOS Screen wallpaper programmatically. The user has to do that manually through the settings or photos app. For things like changing wallpapers, you'd need to jailbreak your device.

[Can I change iOS Screen wallpaper programmatically In Swift 5 and IOS 12](https://stackoverflow.com/questions/56112014/can-i-change-ios-screen-wallpaper-programmatically-in-swift-5-and-ios-12)

But with this plugin you can save image in Gallery and then the user has to define the wallpaper manually

To save image in gallery you can use `setBase64()` method
```typescript
Wallpaper.setBase64({
    base64: string,
    data: string,
}):Promise<WallpaperResult>
```
## Why save base64 image?
If you are using IONIC you must have images inside your webview, 
but there is no way to access to this images from Android or iOS.

First I saved images in `drawable` path and with `setImage()` I used to define wallpaper, but this caused that app size increase.  
So I solved this problem transforming images stored in webview to base64 and then passing this value to the plugin to define wallpaper.

## Example

If you are going to use `setBase64` method you need a method to transform images to base64, I used this method:
```javascript

function getBase64Image(img) {
    const canvas = document.createElement('canvas');
    canvas.width = this.wallpaper.width;
    canvas.height = this.wallpaper.height;
    const ctx = canvas.getContext('2d');
    ctx.drawImage(img, 0, 0);
    const dataURL = canvas.toDataURL('image/png');
    return dataURL.replace(/^data:image\/(png|jpg);base64,/, '');
}

const img64 = this.getBase64Image(document.getElementById('image-tag')); // <img id="image-tag" src="path-to-image.png"/>

Wallpaper.setBase64({
  base64: img64,
  data: 'data:image/png;base64,',
}).then((res) => {
  if (this.isIOS()) { // You must define logic to check if device is iOS
    console.log('Image saved in Gallery, you must define wallpaper manually')
    return;
  }
  console.log('Wallpaper defined')

}).catch((err) => {
  console.log('Oops!');
});
```

If you are goint to use `setImage()` method (only for Android) you must save image in drawable (`main/res/drawable`) path.

Image: `main/res/drawable/myWallpaper.png`

```javascript
Wallpaper.setImage({
  name: 'myWallpaper',
}).then((res) => {
  console.log('Wallpaper defined')
}).catch((err) => {
  console.log('Oops!');
});
```

## License

See [LICENSE](https://github.com/insoutt/capacitor-wallpaper/blob/main/LICENSE).
