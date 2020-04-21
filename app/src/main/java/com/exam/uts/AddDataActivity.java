package com.exam.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {

    private EditText itemName, description, quantity;
    Button submitBtn;

    private String pItem, pDesc, pQty, pTimeStamp;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        itemName = findViewById(R.id.itemName);
        description = findViewById(R.id.itemDesc);
        quantity = findViewById(R.id.itemQty);

        submitBtn = findViewById(R.id.btn_submit);

        dbHelper = new DbHelper(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                startActivity(new Intent(AddDataActivity.this, MainActivity.class));
                Toast.makeText(AddDataActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {

        pItem = "" + itemName.getText().toString().trim();
        pDesc = "" + description.getText().toString().trim();
        pQty = "" + quantity.getText().toString().trim();

        pTimeStamp = "" + System.currentTimeMillis();

        dbHelper.insertInfo(
                "" + pItem,
                "" + pDesc,
                "" + pQty,
                "" + pTimeStamp,
                "" + pTimeStamp
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
