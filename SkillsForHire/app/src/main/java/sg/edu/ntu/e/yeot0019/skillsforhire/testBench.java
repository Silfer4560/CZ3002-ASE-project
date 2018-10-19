package sg.edu.ntu.e.yeot0019.skillsforhire;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;


import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testBench extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        final TextView searchtext;
        searchtext = (TextView)findViewById(R.id.testView);

        DatabaseReference firebasereference;
        firebasereference = FirebaseDatabase.getInstance().getReference();
        Query firebaseHSPUser = firebasereference.child("HSPUsers");
        firebaseHSPUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String name = datas.child("HSPName").getValue().toString();
                    searchtext.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
