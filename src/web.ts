import { WebPlugin } from '@capacitor/core';
import { WallpaperPlugin, WallpaperResult } from './definitions';

export class WallpaperWeb extends WebPlugin implements WallpaperPlugin {
  constructor() {
    super({
      name: 'Wallpaper',
      platforms: ['web'],
    });
  }

  setImage(name: string): Promise<WallpaperResult> {
    console.log({name});
    throw new Error("This plugin does not support implementation for browser, test plugin in Android or iOS device")
  }

  setBase64(base64: string): Promise<WallpaperResult> {
    console.log({base64});
    throw new Error("This plugin does not support implementation for browser, test plugin in Android or iOS device")
  }
}

const Wallpaper = new WallpaperWeb();

export { Wallpaper };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(Wallpaper);
