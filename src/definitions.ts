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
}
