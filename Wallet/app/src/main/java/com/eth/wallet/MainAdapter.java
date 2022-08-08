package com.eth.wallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eth.wallet.database.bean.Person;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends ListAdapter<Person, MainAdapter.PersonViewHolder> {

    protected MainAdapter(@NonNull DiffUtil.ItemCallback<Person> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PersonViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
            holder.bind(getItem(position));
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder{
        private View rootView;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView=itemView;
        }

        private void bind(Person person) {
            final TextView itemTv = rootView.findViewById(R.id.item_tv);
            itemTv.setText(person.getAge());
        }

        static PersonViewHolder create(ViewGroup parent){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item,parent,false);
            return new PersonViewHolder(view);
        }
    }

    static class PeopleDiff extends DiffUtil.ItemCallback<Person> {

        @Override
        public boolean areItemsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem.getId()==(newItem.getId());
        }
    }
}
