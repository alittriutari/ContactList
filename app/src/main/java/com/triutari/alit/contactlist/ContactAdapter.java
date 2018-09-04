package com.triutari.alit.contactlist;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.triutari.alit.contactlist.Database.ContactEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    List<ContactEntity> contactList = new ArrayList<>();

    public ContactAdapter(List<ContactEntity> contact){
        this.contactList = contact;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent, false);

        return new ViewHolder(v); //mengirim layout dalam bentuk view ke Holder
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {
        ContactEntity m = contactList.get(position);
        holder.txtName.setText(m.getmName());
        holder.txtNumber.setText(m.getmNumber());
        int ResImg = (m.ismSim()) ? R.drawable.men_red : R.drawable.men_blue;
        holder.imgContact.setImageResource(ResImg);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.hint_letter)
        TextView tvHint;
        @BindView(R.id.tvName)
        TextView txtName;
        @BindView(R.id.tvNumber)
        TextView txtNumber;
        @BindView(R.id.ivContact)
        ImageView imgContact;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
