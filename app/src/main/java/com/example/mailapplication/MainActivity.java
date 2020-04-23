package com.example.mailapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mailapplication.adapter.MailAdapter;
import com.example.mailapplication.adapter.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements  MailItemClickListener {

    List<ItemModel> items;
    TextView txtFindKeyword;
    Button filterFavorite;
    MailAdapter adapter;
    int filterWithFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items.add(new ItemModel("Reddit",
                "About \"How can I survive over this?\"",
                "This email was sent by Reddit. If you don't want to receive this type of email in the future, please unsubscribe.",
                "12:30 PM"));
        items.add(new ItemModel("Indeed",
                "Tuyển fresher Java at Nhà Tuyển Dụng",
                "FIST ALLIANCES, Ky Nguyen Van",
                "7:00 AM"));
        items.add(new ItemModel("Sống Vui",
                "HappierLife",
                "Tuần này của bạn thế nào vậy?",
                "Apr 22"));
        items.add(new ItemModel("Đỗ Thị Mai Hương",
                "[EM2030] Quản trị học đại cương",
                "Chào các bạn, đây là tài liệu học tập của kì này",
                "Mar 14"));
        items.add(new ItemModel("Chris Mandor",
                "Help us to make a better project",
                "Let us know your thoughts! Help us to build this!",
                "11:39 AM"));
        items.add(new ItemModel("Geeks Tutorials",
                "Winsock",
                "New guides published on the Geeksforgeeks",
                "Apr 9"));
        items.add(new ItemModel("Github",
                "Verify your email",
                "Your accID temporarry has been sent!",
                "Apr 8"));
        items.add(new ItemModel("Sống Vui",
                "HappierLife",
                "Tuần này của bạn thế nào vậy?",
                "Apr 19"));
        items.add(new ItemModel("Nguyen Hoang Duong",
                "[ET2030] Tài liệu học tập",
                "Chào các bạn, đây là tài liệu học tập của kì này",
                "Mar 14"));
        items.add(new ItemModel("[Tuyển sinh] Lớp học lập trình C",
                "[ĐH FPT]_Thông tin Khoá Học Trực tuyến Lập trình viên",
                "Dear Em ! Lớp học mới mở tháng này sẽ đáp ứng được yêu cầu của em",
                "14:00 PM"));

        txtFindKeyword = (TextView) findViewById(R.id.find_keyword);
        filterFavorite = (Button) findViewById(R.id.filter_favorite);
        filterWithFavorite = 0;

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MailAdapter(items, this);
        recyclerView.setAdapter(adapter);

        txtFindKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() >= 3 || s.length() == 0)
                    filter(s.toString());
            }
        });


        filterFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ItemModel> filteredList = new ArrayList<>();
                if(filterWithFavorite == 0){
                    filterWithFavorite = 1;
                    for(ItemModel item : items){
                        if(item.isFavorite()){
                            filteredList.add(item);
                        }
                        adapter.filterList(filteredList);
                    }
                } else{
                    filterWithFavorite = 0;
                    for(ItemModel item : items){
                        if(!item.isFavorite()){
                            filteredList.add(item);
                        }
                        adapter.filterList(filteredList);
                    }
                }

            }
        });
    }

    private void filter(String query){
        ArrayList<ItemModel> filteredList = new ArrayList<>();
        String queryToLowerCase = query.toLowerCase();
        for(ItemModel item : items){
            if(item.getName().toLowerCase().contains(queryToLowerCase)
                    || item.getContent().toLowerCase().contains(queryToLowerCase)
                    || item.getHeader().toLowerCase().contains(queryToLowerCase)){
                filteredList.add(item);
            }
            adapter.filterList(filteredList);
        }
    }

    @Override
    public void OnItemClick(int position) { }
}
