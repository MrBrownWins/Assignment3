package me.karimoff.myapplication_lecture6;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity {

    EditText fromEditText;
    EditText toEditText;
    EditText subjectEditText;
    EditText messageEditText;

    String from;
    String to;
    String subject;
    String message;


    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Intent intent = getIntent();

        from = intent.getStringExtra(MainActivity.FROM);
        to = intent.getStringExtra(MainActivity.TO);
        subject = intent.getStringExtra(MainActivity.SUBJECT);
        message = intent.getStringExtra(MainActivity.MESSAGE);

        fromEditText = (EditText) findViewById(R.id.fromEditText);
        toEditText = (EditText) findViewById(R.id.toEditText);
        subjectEditText = (EditText) findViewById(R.id.subjectEditText);
        messageEditText = (EditText) findViewById(R.id.messageEditText);

        fromEditText.setText(from);
        toEditText.setText(to);
        subjectEditText.setText(subject);
        messageEditText.setText(message);

        sendButton = (Button) findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, from);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(emailIntent, getResources().getText(R.string.send_to)));
            }
        });
    }
}
