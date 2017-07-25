package me.karimoff.myapplication_lecture6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Intent intent;

    ListView productsListView;
    ArrayAdapter<String> adapter;
    String[] stringArrayList;

    String[] buyingProducts = {};

    Button buyButton;

    public static final String FROM = "me.karimoff.myapplication_lecture6.FROM";
    public static final String TO = "me.karimoff.myapplication_lecture6.TO";
    public static final String SUBJECT = "me.karimoff.myapplication_lecture6.SUBJECT";
    public static final String MESSAGE = "me.karimoff.myapplication_lecture6.MESSAGE";

    String user = "default user";
    String to = "to@example.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set username
        intent = getIntent();
        textView = (TextView) findViewById(R.id.loginTextView);
        user = intent.getStringExtra(LoginActivity.USER);
        textView.setText(getString(R.string.you_are_login_as, user));

        //set products
        productsListView = (ListView) findViewById(R.id.productListView);
        stringArrayList = getResources().getStringArray(R.array.products);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, stringArrayList);
        productsListView.setAdapter(adapter);

        buyButton = (Button) findViewById(R.id.buyButton);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productsListView.getCheckedItemCount() != 0){
                    SparseBooleanArray checkedProducts = productsListView.getCheckedItemPositions();
                    String message = "I would like to buy \n";

                    for (int i = 0; i < checkedProducts.size(); i++) {
                        message += stringArrayList[checkedProducts.keyAt(i)] + " ";
                    }
                    sendData(user, to, getString(R.string.subject, user), message);
                } else {
                    Toast.makeText(MainActivity.this, "No product selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void sendData(String from, String to, String subject, String message){
        Intent intent = new Intent(MainActivity.this, EmailActivity.class);
        intent.putExtra(FROM, from);
        intent.putExtra(TO, to);
        intent.putExtra(SUBJECT, subject);
        intent.putExtra(MESSAGE, message);
        startActivity(intent);
    }
}
