package com.example.homework;

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

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    interface OnItemSelected {
        void loadData(String data);
    }

    private MyDataAdapter adapter;
    private String TransmittedText;
    private List<String> myData;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            myData = savedInstanceState.getStringArrayList("DATA");
        else {
            if (myData == null) {
                myData = new ArrayList<>();
                String text;
                for (int i = 1; i < 101; i++) {
                    text = Integer.toString(i);
                    myData.add(text);
                }
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View[] view = {inflater.inflate(R.layout.fragment_list, container, false)};
        return view[0];
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Button button = view.findViewById(R.id.button);

        int numberOfColumns = 3;

        final RecyclerView recyclerView = view.findViewById(R.id.list);
        if (getResources().getInteger(R.integer.column_amount) == 4)
            numberOfColumns = 4;
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyDataAdapter(myData);
        recyclerView.setAdapter(adapter);
        button.setOnClickListener(v -> {
            adapter.addItem();
            adapter.notifyItemInserted(adapter.mData.size());


        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("DATA", (ArrayList<String>) myData);
    }


    class MyDataAdapter extends RecyclerView.Adapter<FirstFragment.MyViewHolder> {
        List<String> mData;

        MyDataAdapter(List<String> data) {
            mData = data;
        }

        @NonNull
        @Override
        public FirstFragment.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FirstFragment.MyViewHolder holder, int position) {
            if ((position + 1) % 2 != 0) {

                holder.textView.setTextColor(Color.RED);
                holder.textView.setText(mData.get(position));
            } else {
                holder.textView.setTextColor(Color.BLUE);
                holder.textView.setText(mData.get(position));
            }


        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void addItem() {
            Integer n = myData.size() + 1;
            myData.add(n.toString());
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            textView.setOnClickListener(v -> {
                int pos = getLayoutPosition();
                TransmittedText = adapter.mData.get(pos);
                ((OnItemSelected) getActivity()).loadData(TransmittedText);


            });
        }
    }


}
