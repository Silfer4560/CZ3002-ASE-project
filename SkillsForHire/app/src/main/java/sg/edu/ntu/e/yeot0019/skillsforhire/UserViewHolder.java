package sg.edu.ntu.e.yeot0019.skillsforhire;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


class UserViewHolder extends RecyclerView.ViewHolder {
    TextView userName,userType;
    public UserViewHolder(View itemView) {
        super(itemView);
        userName = (TextView)itemView.findViewById(R.id.textViewHSPName);
        userType = (TextView)itemView.findViewById(R.id.textViewHSPType);

    }
}
