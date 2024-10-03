package com.example.myapplication;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.color.MaterialColors;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button changeColorButton;
    private Button changeFontButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        changeColorButton = findViewById(R.id.changeColorButton);
        changeFontButton = findViewById(R.id.changeFontButton);

        changeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorPicker();
            }
        });

        changeFontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFontPicker();
            }
        });
    }

    private void showColorPicker() {
        final List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Black");
        colors.add("Yellow");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Color")
                .setItems(colors.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                textView.setTextColor(Color.RED);
                                break;
                            case 1:
                                textView.setTextColor(Color.GREEN);
                                break;
                            case 2:
                                textView.setTextColor(Color.BLUE);
                                break;
                            case 3:
                                textView.setTextColor(Color.BLACK);
                                break;
                            case 4:
                                textView.setTextColor(Color.YELLOW);
                                break;
                        }
                        Toast.makeText(MainActivity.this, "Color changed!", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    private void showFontPicker() {
        final List<String> fonts = new ArrayList<>();
        fonts.add("Normal");
        fonts.add("Bold");
        fonts.add("Italic");
        fonts.add("Monospace");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Font")
                .setItems(fonts.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                textView.setTypeface(Typeface.DEFAULT);
                                break;
                            case 1:
                                textView.setTypeface(Typeface.DEFAULT_BOLD);
                                break;
                            case 2:
                                textView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                                break;
                            case 3:
                                textView.setTypeface(Typeface.MONOSPACE);
                                break;
                        }
                        Toast.makeText(MainActivity.this, "Font changed!", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }
}