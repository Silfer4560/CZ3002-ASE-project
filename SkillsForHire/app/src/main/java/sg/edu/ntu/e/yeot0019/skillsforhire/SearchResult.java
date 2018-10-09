package sg.edu.ntu.e.yeot0019.skillsforhire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sg.edu.ntu.e.yeot0019.skillsforhire.R;

public class SearchResult extends AppCompatActivity {
    EditText searchResultHSPName,searchResultHSPStatus, searchResultHSPType,searchResultHSPRating;
    Button messageButton;
    private static final String TAG = "SearchResult";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        String searchText = intent.getStringExtra( "message" );


    }
    private void getIncomingIntent(){
        Log.d(TAG,"Getincoming intent");
        if(getIntent().hasExtra( "HSP Name" )){
            String userNameSearchResult = getIntent().getStringExtra( "HSP Name" );
        }
    }
    private void setUserDetails(String hspName){
        TextView searchResultHSPName = findViewById( R.id.hspNameSearchResultTextView );
        searchResultHSPName.setText(hspName);

    }
}
