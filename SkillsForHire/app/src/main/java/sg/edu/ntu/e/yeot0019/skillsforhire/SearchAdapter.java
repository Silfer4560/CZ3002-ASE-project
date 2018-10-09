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

public class SearchAdapter extends ArrayAdapter<HSPUser>{

    private static final String TAG = "SearchAdapter";


    private LayoutInflater mInflater;
    private List<HSPUser> mUsers = null;
    private int layoutResource;
    private Activity activity;

    public SearchAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<HSPUser> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.mUsers = objects;
    }

    private class ViewHolder{
        TextView userName, userStatus, userType, userRating;

        public ViewHolder(View v) {
        userName= v.findViewById( R.id.textViewHSPName );
        userStatus = v.findViewById( R.id.textViewHSPStatus );
        userType = v.findViewById( R.id.textViewHSPType );
        userRating = v.findViewById( R.id.textViewHSPRating );

        }
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource =0;
        HSPUser hspUser = getItem( position );
        int viewType = getItemViewType( position );
        if(convertView == null){
            convertView = mInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //set listView content
        holder.userName.setText(getItem(position).getHSPName());
        holder.userStatus.setText(getItem(position).getHSPStatus());
        holder.userType.setText(getItem(position).getHSPType());
        holder.userRating.setText(getItem(position).getHSPRating());

        return convertView;
    }
    public int getViewTypeCount() {
    return 1;
    }
}
