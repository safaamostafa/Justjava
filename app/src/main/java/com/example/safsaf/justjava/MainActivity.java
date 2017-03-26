package com.example.safsaf.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
     int quantity =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }// protected void onCreate(Bundle savedInstanceState)
    public void submitOrder(View view){
        EditText nameField =(EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity" ,"Name: " + name);
        CheckBox  whippedCreamCheckbox = (CheckBox)findViewById(R.id.whippedCream);
        boolean haswhippedCreamCheckbox= whippedCreamCheckbox.isChecked();
         boolean addW = false;
        CheckBox chocolateCheckbox =(CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate= chocolateCheckbox.isChecked();
        boolean addC=false;
        int price= calcultPrice(haswhippedCreamCheckbox,hasChocolate);
        String priceMessage=createOrderSummery(price, haswhippedCreamCheckbox, hasChocolate,name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        displayMessage(priceMessage);

    }//    public void submitOrder(View view)



    private  int calcultPrice (boolean whippedCreamCheckbox, boolean chocolateCheckbox) {

       int basePrice = 5;

        if (whippedCreamCheckbox){

            basePrice= basePrice+1;

        }

        if (chocolateCheckbox){

            basePrice= basePrice+2;
        }


        return  basePrice*quantity;}

    public void increment(View view){
        if (quantity==100){

            Toast.makeText(this,"You cannot have more than 100 caps",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);


    }
    public void decrement(View view){
        if (quantity==1){
            Toast.makeText(this,"You cannot have less than 1 cap",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity=quantity-1;
        display(quantity);

    }
    private  void display(int number){

        TextView quantityTextView=(TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);


    }//private  void display(int number)

    private  void displayPrice(int number) {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private  String createOrderSummery (int price,boolean addW,boolean addC,String name){

        String priceMessage= getString(R.string.name_text)+ name;
        priceMessage+="\n"+getString(R.string.cream_text)+addW;
        priceMessage+="\n"+getString(R.string.chocolate_text)+addC;
        priceMessage+="\n"+getString(R.string.text_quantity)+quantity;
        priceMessage+="\n"+getString(R.string.total_text)+price;
        priceMessage+="\n"+getString(R.string.thank_text);
        return priceMessage;
    };

}//public class MainActivity extends AppCompatActivity
