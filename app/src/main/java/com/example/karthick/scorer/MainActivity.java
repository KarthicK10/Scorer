package com.example.karthick.scorer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TableLayout roundsTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TableLayout scoresTableLayout = (TableLayout) findViewById(R.id.scores_table);
        final int numberOfRows = scoresTableLayout.getChildCount();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int a = calculateTotals();
                Snackbar.make(view, "Total Rows : " + a, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
    /*Method to calculate and update score totals for each player*/
    private int calculateTotals(){
        TableLayout scoresTableLayout = (TableLayout) findViewById(R.id.scores_table);
        final int numberOfRows = scoresTableLayout.getChildCount();
        if(numberOfRows > 0 ){
            TableRow firstRow = (TableRow) scoresTableLayout.getChildAt(0);
            TableRow totalRow = (TableRow) scoresTableLayout.getChildAt(numberOfRows-1);
            final int numberOfCols = firstRow.getChildCount();
            if(numberOfCols > 0){
                /*Iterate all players to calculate their score totals */
                for(int colNum=1; colNum<numberOfCols-1; colNum++){
                    Integer total = 0;
                    for(int rowNum=1; rowNum<numberOfRows-1; rowNum++){
                        TableRow currentRow = (TableRow) scoresTableLayout.getChildAt(rowNum);
                        EditText currentCell = (EditText) currentRow.getChildAt(colNum);
                        int currentValue = Integer.parseInt(currentCell.getText().toString());
                        total = total + currentValue;
                    }
                    TextView totalCell = (TextView) totalRow.getChildAt(colNum);
                    String totalValue = total.toString();
                    totalCell.setText(totalValue);
                }
            }
        }

        return 0;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
