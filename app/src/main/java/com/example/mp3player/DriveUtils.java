package com.example.mp3player;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;

public class DriveUtils {

    public String getFolderIdByName(Drive driveService, String folderName) throws IOException {
        String folderId = null;

        // Tạo truy vấn để tìm kiếm thư mục theo tên
        String query = "mimeType='application/vnd.google-apps.folder' and name='" + folderName + "'";
        FileList result = driveService.files().list().setQ(query).execute();

        // Lặp qua các kết quả
        for (com.google.api.services.drive.model.File file : result.getFiles()) {
            // Lấy ID của thư mục đầu tiên phù hợp với tên
            folderId = file.getId();
            break; // Chỉ lấy ID của thư mục đầu tiên tìm thấy
        }

        return folderId;
    }
}