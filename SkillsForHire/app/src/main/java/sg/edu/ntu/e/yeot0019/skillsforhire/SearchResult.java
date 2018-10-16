package sg.edu.ntu.e.yeot0019.skillsforhire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

import sg.edu.ntu.e.yeot0019.skillsforhire.R;

public class SearchResult extends AppCompatActivity {
    TextView searchResultHSPName,searchResultHSPStatus, searchResultHSPType,searchResultHSPRating;
    Button messageButton;
    private static final String TAG = "SearchResult";
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Intent intent = getIntent();
        String searchedHSPName = intent.getStringExtra("HSP Name");
        searchResultHSPName = (TextView) findViewById( R.id.hspNameSearchResultTextView );
        searchResultHSPStatus = (TextView) findViewById( R.id.hspStatusSearchResultTextView );
        searchResultHSPType = (TextView) findViewById( R.id.hspTypeSearchResultTextView );
        searchResultHSPRating =(TextView)findViewById( R.id.hspRatingSearchResultTextView );

        dbReference= FirebaseDatabase.getInstance().getReference();
        Query userQuery = dbReference.child( "HSP" ).equalTo(searchedHSPName);



    }

    private void setUserDetails(String hspName){
        TextView searchResultHSPName = findViewById( R.id.hspNameSearchResultTextView );
        searchResultHSPName.setText(hspName);
        }
}
