package com.example.lab3databases;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    TextView productId;
    EditText productName, productPrice;
    Button addBtn, findBtn, deleteBtn;
    ListView productListView;

    ArrayList<String> productList;
    ArrayAdapter adapter;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();

        // info layout
        try {
            productId = findViewById(R.id.productId);
            productName = findViewById(R.id.productName);
            productPrice = findViewById(R.id.productPrice);
        } catch (Exception e) {
            if (productPrice == null){
                System.out.println("Please enter a price for the product");
            } else {
                System.out.println("Error! Could not create this item!");
            }
        }

        //buttons
        addBtn = findViewById(R.id.addBtn);
        findBtn = findViewById(R.id.findBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        // listview
        productListView = findViewById(R.id.productListView);

        // db handler
        dbHandler = new MyDBHandler(this);

        // button listeners
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = productName.getText().toString();
                    double price = Double.parseDouble(productPrice.getText().toString());
                    Product product = new Product(name, price); //initializing product object
                    dbHandler.addProduct(product);

                    productName.setText("");
                    productPrice.setText("");

                    //                Toast.makeText(MainActivity.this, "Add product", Toast.LENGTH_SHORT).show();
                    viewProducts();
                } catch (Exception e){
                    if (Double.isNaN(Double.parseDouble(productPrice.getText().toString()))){ //if no value is entered for the price
                        System.out.println("Please enter a price for the product");
                    } else {
                        System.out.println("Error! Could not create this item!");
                    }
                }
            }
        });

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productName.getText().toString();
                try {
                    double price = Double.parseDouble(productPrice.getText().toString());
                    if (!name.isEmpty()) {
                        viewProducts(name, price);
                    } else {
                        viewProducts(price);
                    }
                } catch (Exception ignored) {
                    if (!name.isEmpty()) {
                        viewProducts(name);
                    } else {
                        viewProducts();
                    }
                }
               // Toast.makeText(MainActivity.this, "Find product", Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productName.getText().toString();
                dbHandler.deleteProduct(name);
                viewProducts();
                //Toast.makeText(MainActivity.this, "Delete product", Toast.LENGTH_SHORT).show();
            }
        });


        viewProducts();
    }

    private void viewProducts() {
        productList.clear();
        Cursor cursor = dbHandler.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                productList.add(cursor.getString(1) + " (" +cursor.getString(2)+")");
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);
    }

    private void viewProducts(String name) {
        productList.clear();
        Cursor cursor = dbHandler.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            int numItems = 0;
            while (cursor.moveToNext()) {
                if (cursor.getString(1).startsWith(name)) {
                    numItems++;
                    productList.add(cursor.getString(1) + " (" + cursor.getString(2) + ")");
                }
            }
            if (numItems == 0) {
                Toast.makeText(MainActivity.this, "No products found", Toast.LENGTH_SHORT).show();
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);
    }

    private void viewProducts(double price) {
        try {
            productList.clear();
            Cursor cursor = dbHandler.getData();
            if (cursor.getCount() == 0) {
                Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
            } else {
                int numItems = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getDouble(2) == price) {
                        numItems++;
                        productList.add(cursor.getString(1) + " (" + cursor.getString(2) + ")");
                    }
                }
                if (numItems == 0) {
                    Toast.makeText(MainActivity.this, "No products found", Toast.LENGTH_SHORT).show();
                }
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
            productListView.setAdapter(adapter);
        } catch (Exception e) {
            System.out.println("Error! Could not find products");
        }
    }

    private void viewProducts(String name, double price) {
        productList.clear();
        Cursor cursor = dbHandler.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            int numItems = 0;
            while (cursor.moveToNext()) {
                if (cursor.getString(1).startsWith(name) && cursor.getDouble(2) == price) {
                    numItems++;
                    productList.add(cursor.getString(1) + " (" + cursor.getString(2) + ")");
                }
            }
            if (numItems == 0) {
                Toast.makeText(MainActivity.this, "No products found", Toast.LENGTH_SHORT).show();
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        productListView.setAdapter(adapter);
    }
}