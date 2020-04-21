package com.exam.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    private EditText itemName, description, quantity;
    Button submitBtn;

    private String pId, pItem, pDesc, pQty, pAddTimeStamp, pUpdateTimeStamp;
    private boolean editMode = false;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        itemName = findViewById(R.id.itemName);
        description = findViewById(R.id.itemDesc);
        quantity = findViewById(R.id.itemQty);

        submitBtn = findViewById(R.id.btn_submit);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editMode", editMode);
        pId = intent.getStringExtra("ID");
        pItem = intent.getStringExtra("ITEM");
        pDesc = intent.getStringExtra("DESC");
        pQty = intent.getStringExtra("QTY");
        pAddTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        pUpdateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        if (editMode) {
            editMode = intent.getBooleanExtra("editMode", editMode);
            pId = intent.getStringExtra("ID");
            pItem = intent.getStringExtra("ITEM");
            pDesc = intent.getStringExtra("DESC");
            pQty = intent.getStringExtra("QTY");
            pAddTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
            pUpdateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

            itemName.setText(pItem);
            description.setText(pDesc);
            quantity.setText(pQty);
        }

        dbHelper = new DbHelper(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                startActivity(new Intent(EditDataActivity.this, MainActivity.class));
                Toast.makeText(EditDataActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {

        pItem = "" + itemName.getText().toString().trim();
        pDesc = "" + description.getText().toString().trim();
        pQty = "" + quantity.getText().toString().trim();

        if (editMode) {
            String newUpdateTime = "" + System.currentTimeMillis();

            dbHelper.updateInfo(
                    "" + pId,
                    "" + pItem,
                    "" + pDesc,
                    "" + pQty,
                    "" + pAddTimeStamp,
                    "" + newUpdateTime
            );
        }
        else {
            String pTimeStamp = "" + System.currentTimeMillis();

            dbHelper.insertInfo(
                    "" + pItem,
                    "" + pDesc,
                    "" + pQty,
                    "" + pTimeStamp,
                    "" + pTimeStamp
            );
        }

        //Toast.makeText(this, "Data added to ID: " + id, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditDataActivity.this, MainActivity.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}