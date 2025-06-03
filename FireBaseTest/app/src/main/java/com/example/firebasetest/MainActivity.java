package com.example.firebasetest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference ref;
    List<Player> players;
    PlayerAdapter adapter;
    RecyclerView recyclerView;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo Firebase
        ref = FirebaseDatabase.getInstance().getReference("players");

        // Ánh xạ view
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);

        // Khởi tạo list và adapter
        players = new ArrayList<>();
        adapter = new PlayerAdapter(this, players);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Load dữ liệu từ Firebase
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                players.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Player p = snap.getValue(Player.class);
                    players.add(p);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Lỗi đọc dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });

        // Bắt sự kiện nút thêm
        btnAdd.setOnClickListener(v -> showAddDialog());
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm hội viên");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_player, null);
        EditText edtUsername = view.findViewById(R.id.edtName);
        EditText edtHometown = view.findViewById(R.id.edtHometown);

        builder.setView(view);

        builder.setPositiveButton("Thêm", (dialog, which) -> {
            String username = edtUsername.getText().toString().trim();
            String hometown = edtHometown.getText().toString().trim();

            if (username.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên hội viên", Toast.LENGTH_SHORT).show();
                return;
            }

            String member_code = ref.push().getKey(); // Firebase tự sinh ID

            Player p = new Player(member_code, username, "", "", hometown, "", 0, 0);
            ref.child(member_code).setValue(p);
        });

        builder.setNegativeButton("Hủy", null);
        builder.show();
    }
}


