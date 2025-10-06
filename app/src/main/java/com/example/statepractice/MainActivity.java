package com.example.statepractice;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    User user = new User("undefined", 0, "No description");
    final String userVariableKey = "USER_VARIABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        user = (User) saveInstanceState.getSerializable(userVariableKey);
        TextView dataView = findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + "\nAge: " + user.getAge() + "\nDescription: " + user.getDescription());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(userVariableKey, user);
    }

    public void saveData(View view) {
        EditText nameBox = findViewById(R.id.nameBox);
        EditText yearBox = findViewById(R.id.yearBox);
        EditText descriptionBox = findViewById(R.id.descriptionBox);

        String name = nameBox.getText().toString();
        String description = descriptionBox.getText().toString();
        int age = 0;
        try {
            age = Integer.parseInt(yearBox.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        user = new User(name, age, description);
    }

    public void getData(View view) {
        TextView dataView = findViewById(R.id.dataView);
        dataView.setText("Name: " + user.getName() + "\nAge: " + user.getAge() + "\nDescription: " + user.getDescription());
    }
}