package com.example.appnote_dongian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView listView_conviec;
    ArrayList<CongViec> congViecArrayList;
    CongViecAdapter congViecAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //khoi tao cac doi tuong va truyen vao adapter
        listView_conviec = findViewById(R.id.lv_congviec);
        congViecArrayList = new ArrayList<>();

        congViecAdapter = new CongViecAdapter(this,R.layout.item_dongcongviec,congViecArrayList);
        listView_conviec.setAdapter(congViecAdapter);
        //congViecAdapter.notifyDataSetChanged(); : goi khi arraylis co data
//        //khoi tao database
//        database = new Database(this,"filedataGhichu.sqlite",null,1);
//
//        //tao bang cong viec
//        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenCV VACHAR(200))");
//
//        //Insert database
//        database.QueryData("INSERT INTO CongViec(null,'Lam bai tap ve nha Android')");
//
//        //Edit database
//        database.QueryData("UPDATE CongViec SET TenCV='Chinh sua cong viec' WHERE Id='1'");
//
//        //Delete database
//        database.QueryData("DELETE FROM CongViec WHERE Id='1'");
//
//        //Select
    }
    private void getDataCV(){  //lay data va day len sql
        //get tat ca du lieu nhung cong viec da tao
        Cursor dataCongViec = database.getDataCursor("SELECT * FROM CongViec");
        while (dataCongViec.moveToNext()){
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            congViecArrayList.add(new CongViec(id,ten));
        }

        //sau khi tao data - get data  > do du lieu vao
        congViecAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            DielogAdd();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DielogAdd() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_themcongviec);
        dialog.show();
        final EditText editText_AddCV = dialog.findViewById(R.id.edt_addCongViec);
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lay ra
                String tenCV = editText_AddCV.getText().toString();
                if(tenCV.equals("")){
                    Toast.makeText(MainActivity.this,"Please insert Note",Toast.LENGTH_LONG).show();
                }else{
                    //insert congviec ghi vao len database
                    database.QueryData("INSERT INTO CongViec(null,'"+tenCV+"')");
                    dialog.dismiss();
                    getDataCV();
                }
            }
        });
    }

    public void DialogEdit(String tenCV, final int idCV) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit);
        final EditText editText_CV = dialog.findViewById(R.id.edt_editCongViec);
        editText_CV.setText(tenCV);
        dialog.show();
        dialog.findViewById(R.id.btn_cancelEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.btn_saveEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newCV = editText_CV.getText().toString().trim();
                //sua du lieu trong data base va cap nhat lai danh sach hien thi
                database.QueryData("UPDATE CongViec SET TenCV='"+newCV+"' WHERE Id='"+idCV+"'");
                dialog.dismiss();

            }
        });

    }
}
