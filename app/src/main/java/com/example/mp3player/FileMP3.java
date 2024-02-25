package com.example.mp3player;

public class FileMP3 {
    private String FileName, Album;

    public FileMP3(String fileName, String album) {
        FileName = fileName;
        Album = album;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }
}
