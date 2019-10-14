package com.example.homework;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstFragment extends Fragment {

    private MyDataAdapter adapter;
   String TransmittedText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        int numberOfColumns = 3;
        final View[] view = {inflater.inflate(R.layout.fragment_list,container,false)};
        final RecyclerView recyclerView = (RecyclerView) view[0].findViewById(R.id.list);
        int horizontal=getResources().getBoolean(R.bool.is_horizontal)?
                numberOfColumns=4:3;

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyDataAdapter(MyDataSource.getInstance().getData());
        recyclerView.setAdapter(adapter);
        return view[0];
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AtomicInteger n;
        Integer s;
        s=adapter.mData.size(); n=new AtomicInteger(s);
        final Button button=(Button) view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            n.getAndIncrement();
            MyDataSource.MyData m=new MyDataSource.MyData(n.toString());
            adapter.mData.add(m);
            adapter.notifyDataSetChanged();
        });
    }
    public void toActivity(String data) {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
            ((MainActivity) activity).fromFragmentData(data);
        }
    }
    class MyDataAdapter extends RecyclerView.Adapter<FirstFragment.MyViewHolder>{
        List<MyDataSource.MyData> mData;
        MyDataAdapter (List<MyDataSource.MyData> data){
            mData=data;
        }
        @NonNull
        @Override
        public FirstFragment.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item,parent,false);
           FirstFragment.MyViewHolder myViewHolder=new FirstFragment.MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull FirstFragment.MyViewHolder holder,int position) {
            if((position+1)%2!=0){

                holder.textView.setTextColor(Color.RED) ;
                holder.textView.setText(mData.get(position).mytext);}
            else{ holder.textView.setTextColor(Color.BLUE) ;
                holder.textView.setText(mData.get(position).mytext);}


        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;

        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            textView= itemView.findViewById(R.id.text);
            textView.setOnClickListener(v ->{
               int pos= getLayoutPosition();
               TransmittedText=adapter.mData.get(pos).mytext;

               toActivity(TransmittedText);


            });
        }
    }


}
