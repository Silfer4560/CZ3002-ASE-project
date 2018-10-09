package sg.edu.ntu.e.yeot0019.skillsforhire;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class SearchFunction extends AppCompatActivity {
    private Context mContext = SearchFunction.this;
    private String TAG = "searchFunction";
    //widgets
    private EditText mSearchText;
    private ListView mListView;
    //variable
    private List<HSPUser> mUserList;
    private SearchAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
     super.onCreate( savedInstanceState );
     setContentView( R.layout.activity_search_function );
     mSearchText = (EditText) findViewById( R.id.searchField );
     mListView = (ListView)findViewById( R.id.listView );

     initTextListener();
    }
    private  void searchForUser(String userName){
        mUserList.clear();
        //update userlist
        if(userName.length()==0);{
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Query firebasequery = reference.child("HSPUsers").orderByChild( "HSPUsers" ).equalTo(userName);
            firebasequery.addListenerForSingleValueEvent( new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot singleSnapshot:dataSnapshot.getChildren()){
                        mUserList.add(singleSnapshot.getValue(HSPUser.class));
                        updateUserList();
                    }
                }

                @Override
                public void onCancelled( DatabaseError databaseError) {

                }
            } );
        }
    }
    private void initTextListener(){
        mUserList = new ArrayList<>(  );
        mSearchText.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = mSearchText.getText().toString().toLowerCase();
                searchForUser( text );
            }
        } );
    }
    private void updateUserList(){
        Log.d(TAG, "updateUsersList: updating users list");

        mAdapter = new SearchAdapter(SearchFunction.this, R.layout.list_layout, mUserList);

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: selected user: " + mUserList.get(position).toString());

                //navigate to profile activity

            }
        });
    }



}