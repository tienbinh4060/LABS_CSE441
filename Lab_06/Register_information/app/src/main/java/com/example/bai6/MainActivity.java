package com.example.bai6;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtten, editCMND, editBosung;
    CheckBox chkdocbao, chkdocsach, chkdoccode;
    Button btnsend;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtten = findViewById(R.id.edtten);
        editCMND = findViewById(R.id.edtcmnd);
        editBosung = findViewById(R.id.edtbosung);
        chkdocbao = findViewById(R.id.chkdocbao);
        chkdoccode = findViewById(R.id.chkcode);
        chkdocsach = findViewById(R.id.chkdocsach);
        btnsend = findViewById(R.id.btnsend);
        group = findViewById(R.id.idgruop);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doShowInformation();
            }
        });

        // Đăng ký callback cho nút back
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(
                    OnBackInvokedDispatcher.PRIORITY_DEFAULT,
                    () -> showExitDialog()
            );
        }
    }

    public void doShowInformation() {
        String ten = edtten.getText().toString().trim();
        if (ten.length() < 3) {
            edtten.requestFocus();
            edtten.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        String cmnd = editCMND.getText().toString().trim();
        if (cmnd.length() != 9) {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        int id = group.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = findViewById(id);
        String bang = rad.getText().toString();

        String sothich = "";
        if (chkdocbao.isChecked()) sothich += chkdocbao.getText() + "\n";
        if (chkdocsach.isChecked()) sothich += chkdocsach.getText() + "\n";
        if (chkdoccode.isChecked()) sothich += chkdoccode.getText() + "\n";

        String bosung = editBosung.getText().toString();

        String msg = ten + "\n" + cmnd + "\n" + bang + "\n" + sothich
                + "—————————–\nThông tin bổ sung:\n" + bosung + "\n—————————–";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setMessage(msg);
        builder.setPositiveButton("Đóng", (dialog, which) -> dialog.cancel());
        builder.create().show();
    }

    private void showExitDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Question")
                .setMessage("Are you sure you want to exit?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", (dialog, which) -> dialog.cancel())
                .show();
    }
}
