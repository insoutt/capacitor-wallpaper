declare module '@capacitor/core' {
  interface PluginRegistry {
    Wallpaper: WallpaperPlugin;
  }
}

export interface WallpaperResult {
  success: boolean
}

export interface WallpaperPlugin {
  setImage(options: { name: string }): Promise<WallpaperResult>;
  setBase64(options: { base64: string, data: string }): Promise<WallpaperResult>;
}
