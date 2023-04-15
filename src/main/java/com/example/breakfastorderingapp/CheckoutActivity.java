package com.example.breakfastorderingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private TextView itemName;
    private TextView itemPrice;
    private EditText deliveryAddress;
    private RadioGroup chopsticksGroup;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        itemName = findViewById(R.id.checkout_item_name);
        itemPrice = findViewById(R.id.checkout_item_price);
        deliveryAddress = findViewById(R.id.checkout_delivery_address);
        chopsticksGroup = findViewById(R.id.checkout_chopsticks_group);
        checkoutButton = findViewById(R.id.checkout_button);

        // Get the MenuItem from the Intent extras
        MenuItem menuItem = (MenuItem) getIntent().getSerializableExtra("menuItem");

        if (menuItem != null) {
            itemName.setText(menuItem.getName());
            itemPrice.setText(String.format("$%.2f", menuItem.getPrice()));
        }

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = deliveryAddress.getText().toString().trim();
                RadioButton selectedChopsticksOption = findViewById(chopsticksGroup.getCheckedRadioButtonId());
                String chopsticksOption = selectedChopsticksOption != null ? selectedChopsticksOption.getText().toString() : "No";

                if (address.isEmpty()) {
                    Toast.makeText(CheckoutActivity.this, "Please enter a delivery address.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CheckoutActivity.this, "Order placed. Chopsticks: " + chopsticksOption, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}

