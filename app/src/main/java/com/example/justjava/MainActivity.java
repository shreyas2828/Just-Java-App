package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
public class MainActivity extends AppCompatActivity {
    int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public int calculatePrice(boolean addWhipCream,boolean addChoco)
    {
        int basePrice=5;
        if(addChoco)
        {
            basePrice+=2;
        }
        if(addWhipCream){
            basePrice+=1;
        }
        return basePrice*n;
    }
    public String createOrderMethod(String name,int price,boolean whippedCream,boolean choco)
    {
        String msg;
        msg ="Name: "+name+"\n";
        msg+="whipped cream?"+whippedCream+"\n";
        msg+="chocolate?"+choco+"\n";
        msg+="quantity= "+n+"\n"+"total= $"+price+"\n"+getString(R.string.thank_you);

        return msg;
    }
    public void submitOrder(View view) {

        EditText text=(EditText)findViewById(R.id.name_text);
        String nameText=text.getText().toString();

        CheckBox whippedCream =(CheckBox)findViewById(R.id.whipped_cream);
        boolean whipped_Cream=whippedCream.isChecked();

        CheckBox chocolateCream =(CheckBox)findViewById(R.id.chocolate);
        boolean Chocolate_Cream=chocolateCream.isChecked();

        int price=calculatePrice(whipped_Cream,Chocolate_Cream);
        String PriceMessage= createOrderMethod(nameText,price,whipped_Cream,Chocolate_Cream);

        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"just java app for "+nameText);
        intent.putExtra(Intent.EXTRA_TEXT,PriceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(PriceMessage);
    }


    public void increment(View view) {
        if(n<=100)
            n++;
        else
            Toast.makeText(this,"cannot have more than 100 cups of coffee",Toast.LENGTH_SHORT).show();

        display(n);

    }
    public void decrement(View view) {
        if(n>0)
            n--;
        else
            Toast.makeText(this,"cannot have less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
        display(n);

    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById (R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}