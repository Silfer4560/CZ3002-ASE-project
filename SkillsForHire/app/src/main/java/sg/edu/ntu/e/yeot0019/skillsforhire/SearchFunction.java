package sg.edu.ntu.e.yeot0019.skillsforhire;

import android.content.Context;
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
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_function);

        //set the reference to HSP user path within DB
        firebaseDBReference = FirebaseDatabase.getInstance().getReference().child("HSPUsers");
        userArrayList = new ArrayList<>();
        mSearchField = (EditText)findViewById(R.id.searchField);

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        options = new FirebaseRecyclerOptions.Builder<HSPUser>().setQuery(firebaseDBReference,HSPUser.class).build();
        adapter = new FirebaseRecyclerAdapter<HSPUser, UserViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull HSPUser model) {
                holder.userName.setText(model.getHSPName());
                holder.userType.setText(model.getHSPType());
            }


            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout,viewGroup,false);

                return new UserViewHolder(view);
            }
        };
    }

    private void searchForUser(String s) {
        Query firebaseQuery = firebaseDBReference.orderByChild("HSPName").startAt(s.toString()).endAt(s.toString()+"\uf8ff");
        firebaseQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    userArrayList.clear();
                    for(DataSnapshot userSnapshot:dataSnapshot.getChildren()){
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

    @Override
    protected void onStart(){
        super.onStart();
        if(adapter!=null){
            adapter.startListening();
        }
    }
    @Override
    protected void onStop(){
        if(adapter!=null){
            adapter.stopListening();
        }
        super.onStop();
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(adapter!=null){
            adapter.startListening();
        }
    }
}
