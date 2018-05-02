
package com.example.storage.Search;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.storage.Bean.ChooseBuyerAdapter;
import com.example.storage.Bean.ChooseSupplierAdapter;
import com.example.storage.Bean.NewBuyer;
import com.example.storage.Bean.NewSupplier;
import com.example.storage.Buyer.Verify_buyer;
import com.example.storage.R;
import com.example.storage.Supplier.Verify_supplier;
import com.example.storage.Utils.ControlUpDown;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.SortListview;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Choose_buyer extends AppCompatActivity {

    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;

    private ChooseBuyerAdapter adapter;
    private boolean flag;
    private int sort_id;
    private List<NewBuyer> buyerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_buyer);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.checkout));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.checkout));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        init();
        adapter = new ChooseBuyerAdapter(this,R.layout.choose_supplier_item,buyerList);
        ListView listView = (ListView)findViewById(R.id.list_view);
        LinearLayout footer = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.list_footer, null);
        listView.addFooterView(footer);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewBuyer buyer = buyerList.get(position);
                Intent verify = new Intent(Choose_buyer.this, Verify_buyer.class);
                verify.putExtra("buyer",buyer);
                startActivity(verify);
            }
        });

    }

    private void init() {
        List<NewBuyer> buyers = DataSupport.findAll(NewBuyer.class);
        buyerList.clear();
        for (NewBuyer buyer:buyers) {
            buyerList.add(buyer);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_supplier,menu);
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_select_buyer,null);
        DrawableColor.changeColor(view,menu.findItem(R.id.sort),R.drawable.ic_sort_white_24dp,R.color.textColorPrimary);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.skip:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.supplier_skip2,null);
                builder.setView(view);
                builder.create();
                final AlertDialog dialog = builder.show();
                TextView cancel = (TextView)view.findViewById(R.id.skip_cancel);
                TextView ensure = (TextView)view.findViewById(R.id.ensure);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.sort:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                View view1 = LayoutInflater.from(this).inflate(R.layout.supplier_sort2,null);
                builder1.setView(view1);
                builder1.create();
                DrawableColor.changeColor(view1,R.id.btn1,R.drawable.ic_arrow_down_24dp,R.color.checkout);
                DrawableColor.changeColor(view1,R.id.btn1,R.drawable.ic_arrow_up_24dp,R.color.checkout);
                DrawableColor.changeColor(view1,R.id.btn2,R.drawable.ic_arrow_down_24dp,R.color.checkout);
                DrawableColor.changeColor(view1,R.id.btn2,R.drawable.ic_arrow_up_24dp,R.color.checkout);
                DrawableColor.changeColor(view1,R.id.btn3,R.drawable.ic_arrow_down_24dp,R.color.checkout);
                DrawableColor.changeColor(view1,R.id.btn3,R.drawable.ic_arrow_up_24dp,R.color.checkout);
                final AlertDialog dialog1 = builder1.show();
                TextView cancel1 = (TextView)view1.findViewById(R.id.search_checkin_sort_cencel);
                TextView ensure1 = (TextView)view1.findViewById(R.id.supplier_sort_ensure);
                cancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                ensure1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SortListview.buyerSort(adapter,buyerList,flag,sort_id);
                        flag = true;
                        sort_id = 0;
                        dialog1.dismiss();
                    }
                });
                btn1 = (ImageButton) view1.findViewById(R.id.btn1);
                btn2 = (ImageButton) view1.findViewById(R.id.btn2);
                btn3 = (ImageButton) view1.findViewById(R.id.btn3);
                view1.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn1,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn2,btn3);
                        sort_id = 1;
                    }
                });
                view1.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn2,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3);
                        sort_id = 2;
                    }
                });
                view1.findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn3,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn2);
                        sort_id = 4;
                    }
                });
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
