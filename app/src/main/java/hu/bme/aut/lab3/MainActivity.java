package hu.bme.aut.lab3;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText amountEditText;
    private ToggleButton typeChooserButton;
    private Button saveButton;
    private LinearLayout listOfRows;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameEditText = findViewById(R.id.salary_name);
        amountEditText = findViewById(R.id.salary_amount);
        typeChooserButton = findViewById(R.id.expense_or_income);
        saveButton = findViewById(R.id.save_button);
        listOfRows = findViewById(R.id.list_of_rows);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().isEmpty() || amountEditText.getText().toString().isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, R.string.warn_message, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                }
                View rowItem = inflater.inflate(R.layout.salary_row, null);
                ImageView icon = rowItem.findViewById(R.id.salary_direction_icon);
                TextView rowItemSalaryName = rowItem.findViewById(R.id.row_salary_name);
                TextView rowItemSalaryAmount = rowItem.findViewById(R.id.row_salary_amount);
                icon.setImageResource(typeChooserButton.isChecked() ? R.drawable.expense : R.drawable.income);
                rowItemSalaryName.setText(nameEditText.getText().toString());
                rowItemSalaryAmount.setText(amountEditText.getText().toString());
                listOfRows.addView(rowItem);
                TextView sum = findViewById(R.id.sum);
                sum.setVisibility(View.VISIBLE);
                if (typeChooserButton.isChecked())
                    sum.setText(String.valueOf(Integer.valueOf(sum.getText().toString()) - Integer.valueOf(amountEditText.getText().toString())));
                else
                    sum.setText(String.valueOf(Integer.valueOf(sum.getText().toString()) + Integer.valueOf(amountEditText.getText().toString())));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_all) {
            listOfRows.removeAllViews();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
