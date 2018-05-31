/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package justjava.android.example.com.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100){
            //show an error message as a toast
            Toast.makeText(this,"You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            //Exit this method early as there is nothing left to do
            return;
        }
        quantity += 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //show an error message as a toast
            Toast.makeText(this,"You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            //Exit this method early as there is nothing left to do
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * Log.v("mainActivity", "Has whipped cream: " +hasWhippedCream);
     *
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // figure out if the user  wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //figure out if the user wants chocolate cream topping
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate  = chocolateCheckbox.isChecked();

        // add the name of the user
        EditText nameEditText = (EditText) findViewById(R.id.name_field);
        String name = nameEditText.getText().toString();


        int price = calculatePrice(hasChocolate,hasWhippedCream);
        displayMessage(createOrderSummary(price,hasWhippedCream,hasChocolate,name));

    }

    /**
     * Calculates the price of the order.
     * @return total price
     */
    private int calculatePrice(boolean addChocolate, boolean addWhippedCream) {
        //price of one cup of coffee
        int basePrice = 5;

        // add 1$ if the user wants whipped cream
        if (addWhippedCream){
            basePrice = basePrice + 1;
        }

        // add 2$ if the user wants chocolate
        if (addChocolate){
            basePrice = basePrice + 2;
        }
        return quantity * basePrice;

    }
    /**
     * Summary of the order.
     * @param name of the customer
     * @param price of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate or not
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {
        String priceMessage ="Name: " + name;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nAdd whipped cream " + addWhippedCream;
        priceMessage +="\nAdd chocolate " + addChocolate;
        priceMessage += "\nTotal: $" + price ;
        priceMessage += "\nThank You!";
        displayMessage(priceMessage);
        return priceMessage;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }



    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}