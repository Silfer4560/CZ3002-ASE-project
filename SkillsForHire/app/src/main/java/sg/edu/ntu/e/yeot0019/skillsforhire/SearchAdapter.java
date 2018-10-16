package sg.edu.ntu.e.yeot0019.skillsforhire;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.userViewHolder>{

    public Context ctx;
    public ArrayList<HSPUser> arrayList;
    public SearchAdapter(Context ctx, ArrayList<HSPUser> arrayList){
        this.ctx= ctx;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout,viewGroup,false);
        return new SearchAdapter.userViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        HSPUser user = arrayList.get(position);
        holder.userName.setText(user.getHSPName());
        holder.userType.setText(user.getHSPType());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    public class userViewHolder extends RecyclerView.ViewHolder {
        public TextView userName,userType;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = (TextView)itemView.findViewById(R.id.textViewHSPName);
            userType = (TextView)itemView.findViewById(R.id.textViewHSPType);
        }
    }

}