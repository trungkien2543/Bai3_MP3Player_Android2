package com.example.mp3player;

import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Spinner spinnerAlbum;
    private RecyclerView recyclerView;
    private ArrayList<FileMP3> listFileMP3;
    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Tạo GoogleAccountCredential với quyền truy cập vào Google Drive
        GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                MainActivity.this, Collections.singleton(DriveScopes.DRIVE_FILE));

        // Đặt tài khoản Google mà người dùng đã đăng nhập
        credential.setSelectedAccountName("kieenlee77@gmail.com");

        // Tạo một instance của GoogleDriveService
        Drive service = new Drive.Builder(new NetHttpTransport(), new GsonFactory(), credential)
                .setApplicationName("MP3 Player")
                .build();



        AnhXa();

//        ArrayList<String> data = new ArrayList<>();
//        data.add("Album 1");
//        data.add("Album 2");
//        data.add("Album 3");
//
//        MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_spinner_item, data);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinnerAlbum.setAdapter(adapter);
//
//        spinnerAlbum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //Toast.makeText(MainActivity.this,data.get(position),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        ArrayList<String> listItem = new ArrayList<>();
        listItem.add("Java");
        listItem.add("PHP");
        listItem.add("Python");
        listItem.add("C");
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Hoặc GridLayoutManager nếu bạn muốn hiển thị dưới dạng lưới
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(listItem);
        recyclerView.setAdapter(recycleViewAdapter);


        LoadList(service);

    }

    private void AnhXa(){
        //spinnerAlbum = findViewById(R.id.spinnerAlbum);
        recyclerView = findViewById(R.id.ListMusic);
    }

    public void LoadList(Drive driveService) {
        try {
            // Lấy danh sách file MP3 trong Google Drive
            List<File> files = driveService.files().list()
                    .setQ("'" + new DriveUtils().getFolderIdByName(driveService,"File_Nhac") + "' in parents and mimeType = 'audio/mpeg'")
                    .execute().getFiles();

            // Lấy tên file và tên album (nếu có)
            for (File file : files) {
                String fileName = file.getName();
                String albumName = (String) file.get("album");

                // Kiểm tra nếu albumName không rỗng hoặc null
                if (albumName != null && !albumName.isEmpty()) {
                    Log.e("Tên file: ",fileName);
                    Log.e("Tên album: ",albumName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}