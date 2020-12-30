declare module '@capacitor/core' {
  interface PluginRegistry {
    Wallpaper: WallpaperPlugin;
  }
}

export interface WallpaperResult {
  success: boolean
}

export interface WallpaperPlugin {
  setImage(name: string): Promise<WallpaperResult>;
  setBase64(base64: string): Promise<WallpaperResult>;
}
