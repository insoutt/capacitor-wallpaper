declare module '@capacitor/core' {
  interface PluginRegistry {
    WallpaperPlugin: WallpaperPluginPlugin;
  }
}

export interface WallpaperResult {
  success: boolean
}

export interface WallpaperPluginPlugin {
  setWallpaper(name: string): Promise<WallpaperResult>;
  setWallpaperBase64(base64: string): Promise<WallpaperResult>;
}
