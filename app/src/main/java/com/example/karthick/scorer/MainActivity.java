package com.example.karthick.scorer;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TableLayout scoresTableLayout;
    private ArrayList<String>  playersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scoresTableLayout = (TableLayout) findViewById(R.id.scores_table);

        //Add Players
        //playersList.add("Andrews");
        //playersList.add("Kanchana");
        playersList.add("KarthicK");
        playersList.add("Nandhini");
       // playersList.add("Anand");
        playersList.add("Sylvia");

        addHeader(this);
        addFooter(this);
        nextRound(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calculateTotals();
                nextRound(getBaseContext());
                Snackbar.make(view, "Total Rows : " , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    /*Method to add Header row with Player names*/
    private void addHeader(Context context){
        //Create Header Row
        TableRow headerRow = new TableRow(context);

        //Add Round Text View
        TextView roundTextView = new TextView(context);
        TableRow.LayoutParams roundViewParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        roundTextView.setLayoutParams(roundViewParams);
        roundTextView.setGravity(Gravity.CENTER);
        roundTextView.setTypeface(Typeface.DEFAULT_BOLD);
        roundTextView.setText(R.string.round_label);
        headerRow.addView(roundTextView);

        //Add Player Names
        for(String player: playersList){
            TextView playerNameText = new TextView(context);
            TableRow.LayoutParams playerNameTextParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            playerNameText.setLayoutParams(playerNameTextParams);
            playerNameText.setText(player);
            playerNameText.setGravity(Gravity.CENTER);
            playerNameText.setTypeface(Typeface.DEFAULT_BOLD);
            headerRow.addView(playerNameText);
        }

        //Add header to table
        scoresTableLayout.addView(headerRow, 0);
    }

    /*Method to add Footer row with default totals*/
    private void addFooter(Context context){
        //Create Footer Row
        TableRow footerRow = new TableRow(context);

        //Add Total Text View
        TextView totalTextView = new TextView(context);
        TableRow.LayoutParams totalViewParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        totalTextView.setLayoutParams(totalViewParams);
        totalTextView.setGravity(Gravity.CENTER);
        totalTextView.setTypeface(Typeface.DEFAULT_BOLD);
        totalTextView.setText(R.string.total_label);
        footerRow.addView(totalTextView);

        //Add default total for each player
        for(String player: playersList){
            TextView totalText = new TextView(context);
            TableRow.LayoutParams totalTextParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            totalText.setLayoutParams(totalTextParams);
            totalText.setText(R.string.default_score);
            totalText.setGravity(Gravity.CENTER);
            totalText.setTypeface(Typeface.DEFAULT_BOLD);
            footerRow.addView(totalText);
        }

        //Add footer to table
        scoresTableLayout.addView(footerRow, scoresTableLayout.getChildCount());
    }

    /*Method to add new row for next Round*/
    private void nextRound(Context context){
        //Create New Row
        TableRow newRow = new TableRow(context);
        newRow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorScoresRowBG));
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams();
        tableRowParams.bottomMargin=1;
        newRow.setLayoutParams(tableRowParams);

        //Create round number text view
        TextView roundNumView = new TextView(context);
        TableRow.LayoutParams roundNumViewParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        roundNumView.setLayoutParams(roundNumViewParams);
        roundNumView.setGravity(Gravity.CENTER);
        roundNumView.setTypeface(Typeface.DEFAULT_BOLD);
        roundNumView.setText(((Integer)(scoresTableLayout.getChildCount()-1)).toString());
        newRow.addView(roundNumView);

        //Add player score views
        for(String player : playersList){
            EditText scoreText = new EditText(context);
            TableRow.LayoutParams scoreTextParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            scoreText.setLayoutParams(scoreTextParams);
            scoreText.setText(R.string.default_score);
            scoreText.setGravity(Gravity.CENTER);
            newRow.addView(scoreText);
        }

        scoresTableLayout.addView(newRow, scoresTableLayout.getChildCount()-1);

    }

    /*Method to calculate and update score totals for each player*/
    private void calculateTotals(){
        //scoresTableLayout = (TableLayout) findViewById(R.id.scores_table);
        final int numberOfRows = scoresTableLayout.getChildCount();
        if(numberOfRows > 0 ){
            TableRow firstRow = (TableRow) scoresTableLayout.getChildAt(0);
            TableRow totalRow = (TableRow) scoresTableLayout.getChildAt(numberOfRows-1);
            final int numberOfCols = firstRow.getChildCount();
            if(numberOfCols > 0){
                /*Iterate all players to calculate their score totals */
                for(int colNum=1; colNum<numberOfCols; colNum++){
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
