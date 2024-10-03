// MainActivity.java
package com.example.studentapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etRollNo, etAddress, etPhone, etMotherName, etMotherPhone, etFatherName, etFatherPhone, etCGPA;
    private RadioGroup rgDayScholar, rgManagement;
    private Button btnSave, btnSearch ;

    private StudentDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new StudentDatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etRollNo = findViewById(R.id.etRollNo);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        etMotherName = findViewById(R.id.etMotherName);
        etMotherPhone = findViewById(R.id.etMotherPhone);
        etFatherName = findViewById(R.id.etFatherName);
        etFatherPhone = findViewById(R.id.etFatherPhone);
        etCGPA = findViewById(R.id.etCGPA);
        rgDayScholar = findViewById(R.id.rgDayScholar);
        rgManagement = findViewById(R.id.rgManagement);
        btnSave = findViewById(R.id.btnSave);
        btnSearch = findViewById(R.id.btnSearch);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String rollNo = etRollNo.getText().toString();
                String address = etAddress.getText().toString();
                String phone = etPhone.getText().toString();
                String motherName = etMotherName.getText().toString();
                String motherPhone = etMotherPhone.getText().toString();
                String fatherName = etFatherName.getText().toString();
                String fatherPhone = etFatherPhone.getText().toString();
                String dayScholar = ((RadioButton) findViewById(rgDayScholar.getCheckedRadioButtonId())).getText().toString();
                String management = ((RadioButton) findViewById(rgManagement.getCheckedRadioButtonId())).getText().toString();
                double cgpa = Double.parseDouble(etCGPA.getText().toString());

                long id = dbHelper.addStudent(name, rollNo, address, phone, motherName, motherPhone, fatherName, fatherPhone, dayScholar, management, cgpa);

                if (id > 0) {
                    Toast.makeText(MainActivity.this, "Student added successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error adding student.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNo = etRollNo.getText().toString();
                Cursor cursor = dbHelper.getStudentByRollNo(rollNo);

                if (cursor.moveToFirst()) {
                    Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                    intent.putExtra("studentData", cursor.getString(cursor.getColumnIndexOrThrow("name")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("roll_no")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("address")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("phone")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("mother_name")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("mother_phone")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("father_name")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("father_phone")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("day_scholar")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("management")) + "\n"
                            + cursor.getString(cursor.getColumnIndexOrThrow("cgpa")));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Student not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}



