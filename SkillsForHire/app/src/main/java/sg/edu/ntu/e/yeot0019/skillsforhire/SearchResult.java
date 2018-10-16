package sg.edu.ntu.e.yeot0019.skillsforhire;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        //take the searched name from searchFunction and query db for the user details
        Intent intent = getIntent();
        String searchedHSPName = intent.getStringExtra("HSP Name");
        dbReference= FirebaseDatabase.getInstance().getReference();
        Query userQuery = dbReference.child( "HSPUser" ).equalTo(searchedHSPName);

        //attach Value eventlistener to get details
        userQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //return the user found
                HSPUser userSearched = dataSnapshot.getValue(HSPUser.class);
                searchResultHSPName.setText(userSearched.getHSPName());
                searchResultHSPStatus.setText(userSearched.getHSPStatus());
                searchResultHSPType.setText(userSearched.getHSPType());
                searchResultHSPRating.setText(userSearched.getHSPRating());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
