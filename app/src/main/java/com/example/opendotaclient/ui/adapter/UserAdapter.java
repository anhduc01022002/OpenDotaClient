package com.example.opendotaclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opendotaclient.R;
import com.example.opendotaclient.ui.SelectListener;
import com.example.opendotaclient.ui.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {

    private Context context;
    private List<User> mListUsers;
    private List<User> mListUsersAll;
    private SelectListener mSelectListener;

    public UserAdapter(List<User> mListUsers, Context context, SelectListener listener ) {

        this.context = context;
        this.mListUsers = mListUsers;
        this.mListUsersAll = mListUsers;
        this.mSelectListener = listener;

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_result,parent,false);

        final UserViewHolder viewHolder = new UserViewHolder(view);



        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.useravatar.setImageResource(mListUsers.get(position).getAvatar());
        holder.username.setText(mListUsers.get(position).getName());
        holder.userid.setText(mListUsers.get(position).getId().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectListener.onItemClicked(mListUsers.get(position).getId());
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    mListUsers = mListUsersAll;
                }else{
                    List<User> list = new ArrayList<>();
                    for (User user : mListUsersAll){
                        if(user.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(user);
                        }
                    }

                    mListUsers = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListUsers;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListUsers = (List<User>) results.values;
                notifyDataSetChanged();
            }
        } ;
    }

    @Override
    public int getItemCount() {
        if (mListUsers != null) {
            return mListUsers.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        private ImageView useravatar;
        private TextView username;
        private TextView userid;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            useravatar = itemView.findViewById(R.id.user_avatar);
            username = itemView.findViewById(R.id.user_name);
            userid = itemView.findViewById(R.id.user_id);
            cardView = itemView.findViewById(R.id.main_container);
        }
    }
}
