package sg.edu.ntu.e.yeot0019.skillsforhire;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class SearchFunction extends AppCompatActivity{

    //variables
    EditText mSearchField;
    RecyclerView recyclerView;
    DatabaseReference firebaseDBReference;
    FirebaseRecyclerOptions<HSPUser> options;
    FirebaseRecyclerAdapter<HSPUser, UserViewHolder> adapter;
    ArrayList<HSPUser> userArrayList;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_function);

        //set the reference to HSP user path within DB
        firebaseDBReference = FirebaseDatabase.getInstance().getReference().child("HSPUsers");
        //initialize arraylist
        userArrayList = new ArrayList<>();

        //bind variables to layout items
        mSearchField = (EditText)findViewById(R.id.searchField);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //upon changing the search field
        mSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){//if searchField is not empty, search the DB for a user that matches the string
                    searchForUser(s.toString());
                }
                else{
                    searchForUser("");
                }
            }
        });




        options = new FirebaseRecyclerOptions.Builder<HSPUser>().setQuery(firebaseDBReference,HSPUser.class).build();
        adapter = new FirebaseRecyclerAdapter<HSPUser, UserViewHolder>(options) {
            @Override//bind viewholder variables to adapter
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull HSPUser model) {
                holder.userName.setText(model.getHSPName());
                holder.userType.setText(model.getHSPType());
            }


            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                //on creating adapter, use the layout from list_layout
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout,viewGroup,false);

                return new UserViewHolder(view);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

        searchButton = (Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to profile activity
                Intent intent = new Intent(SearchFunction.this, SearchResult.class);
                intent.putExtra( "HSP Name" , mSearchField.toString());
                startActivity(intent);
            }
        });
    }

    private void searchForUser(String s) {
        //create a query for firebase that starts at whatever is entered and ends beyond it
        Query firebaseQuery = firebaseDBReference.orderByChild("HSPName").startAt(s).endAt(s+"\uf8ff");

        //use valueEventListener to update query whenever there is a change
        firebaseQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    userArrayList.clear();//clear previous values
                    for(DataSnapshot userSnapshot:dataSnapshot.getChildren()){//get instance of userdata and add to arraylist
                        final HSPUser user = userSnapshot.getValue(HSPUser.class);
                        userArrayList.add(user);
                    }
                    SearchAdapter adapter = new SearchAdapter(getApplicationContext(),userArrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
