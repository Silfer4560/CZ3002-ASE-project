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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Iterator;


public class SearchResult extends AppCompatActivity {
    TextView searchResultHSPName,searchResultHSPStatus, searchResultHSPType,searchResultHSPRating;
    Button messageButton;
    private static final String TAG = "SearchResult";
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        searchResultHSPName = (TextView)findViewById(R.id.hspNameSearchResultTextView);
        searchResultHSPType=(TextView)findViewById(R.id.hspTypeSearchResultTextView);
        searchResultHSPRating = (TextView)findViewById(R.id.hspRatingSearchResultTextView);
        searchResultHSPStatus = (TextView)findViewById(R.id.hspStatusSearchResultTextView);
        //take the searched name from searchFunction and query db for the user details
        Intent intent = getIntent();
        final String searchedHSPName = intent.getExtras().getString("SearchFunctionMessage");
        Toast toast = Toast.makeText(getApplicationContext(), "Text received: "+searchedHSPName,Toast.LENGTH_SHORT);
        dbReference= FirebaseDatabase.getInstance().getReference();
        Query userQuery = dbReference.orderByChild("HSPUsers");

        //attach Value eventlistener to get details
        userQuery.addValueEventListener(new ValueEventListener() {
            //            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                HSPUsers user = dataSnapshot.getValue(HSPUsers.class);
//                searchResultHSPName.setText(user.getHSPUserName());
//            }
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> users = dataSnapshot.getChildren().iterator();
                while (users.hasNext()){
                    DataSnapshot searchedUser = users.next();
                    String userName, userType, userStatus,userRating;
                    userName = searchedUser.child("HSPName").getValue().toString();
                    if (userName==searchedHSPName){
                        userStatus = searchedUser.child("HSPStatus").getValue().toString();
                        userType = searchedUser.child("HSPType").getValue().toString();
                        userRating = searchedUser.child("HSPRating").getValue().toString();

                        searchResultHSPName.setText(userName);
                        searchResultHSPRating.setText(userRating);
                        searchResultHSPType.setText(userType);
                        searchResultHSPStatus.setText(userStatus);}

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
