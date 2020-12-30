import { WebPlugin } from '@capacitor/core';
import { WallpaperPluginPlugin, WallpaperResult } from './definitions';

export class WallpaperPluginWeb extends WebPlugin implements WallpaperPluginPlugin {
  constructor() {
    super({
      name: 'WallpaperPlugin',
      platforms: ['web'],
    });
  }

  setWallpaper(name: string): Promise<WallpaperResult> {
    console.log({name});
    throw new Error("This plugin does not support implementation for browser, test plugin in Android or iOS device")
  }

  setWallpaperBase64(base64: string): Promise<WallpaperResult> {
    console.log({base64});
    throw new Error("This plugin does not support implementation for browser, test plugin in Android or iOS device")
  }
}

const WallpaperPlugin = new WallpaperPluginWeb();

export { WallpaperPlugin };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(WallpaperPlugin);
