package com.example.storage.Bean;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Checklist.Checklist_photo;
import com.example.storage.Device.Verify_device;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.ShowTime;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jerry on 2017/12/5.
 */
public class ChooseDeviceAdapter extends ArrayAdapter<NewDevice> {

    private int resoureId;
    private List<NewCheckin> checkinList = new ArrayList<>();
    private int[] color = {R.color.red,R.color.yellow,R.color.orange,R.color.pink,R.color.green,R.color.blue};
    private RadioButton[] btns = new RadioButton[5];
    private String[] str = {"产出","消耗","磨损","丢失","修订错误"};

    public ChooseDeviceAdapter(Context context, int textViewResourceId, List<NewDevice> objects) {
        super(context,textViewResourceId,objects);
        resoureId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NewDevice device = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        final TextView device_SKU = (TextView)view.findViewById(R.id.SKU);
        final TextView device_name = (TextView)view.findViewById(R.id.name);
        final TextView count = (TextView)view.findViewById(R.id.supplier_count);
        TextView first_name = (TextView)view.findViewById(R.id.first_name);
        device_SKU.setText(device.getSKU());
        device_name.setText(device.getName());

        checkinList = DataSupport.where("device_SKU = ?",device_SKU.getText().toString()).find(NewCheckin.class);
        if (checkinList.size() == 0) {
            count.setText("0");
        } else {
            for (NewCheckin checkin: checkinList) {
                count.setText(checkin.getCount());
            }
        }

        if (count != null) {
            count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Activity.class.isInstance(getContext())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.checklist_update_item, null);

                        DrawableColor.changeColor(view,R.id.add,R.drawable.ic_add_black_24dp,R.color.select_device);
                        DrawableColor.changeColor(view,R.id.minus,R.drawable.ic_remove_black_24dp,R.color.select_device);
                        DrawableColor.changeColor(view,R.id.image,R.drawable.ic_image_24dp,R.color.select_device);
                        DrawableColor.changeColor(view,R.id.expand,R.drawable.ic_description_black_24dp,R.color.select_device);
                        DrawableColor.changeColor(view,R.id.back,R.drawable.ic_chevron_right_white_24dp,R.color.select_device);

                        final TextView table_head = (TextView)view.findViewById(R.id.table_head);
                        final TextView count_now = (TextView)view.findViewById(R.id.count_now);
                        TextView date = (TextView)view.findViewById(R.id.date);
                        TextView SKU = (TextView)view.findViewById(R.id.SKU);
                        TextView name = (TextView)view.findViewById(R.id.name);
                        TextView cancel = (TextView)view.findViewById(R.id.cancel);
                        TextView ensure = (TextView)view.findViewById(R.id.ensure);
                        final TextView error = (TextView)view.findViewById(R.id.error);
                        final EditText describe = (EditText)view.findViewById(R.id.describe);
                        ImageButton add = (ImageButton)view.findViewById(R.id.add);
                        ImageButton minus = (ImageButton)view.findViewById(R.id.minus);
                        ImageButton image = (ImageButton)view.findViewById(R.id.image);
                        ImageButton expand = (ImageButton)view.findViewById(R.id.expand);
                        ImageButton back = (ImageButton)view.findViewById(R.id.back);
                        final RelativeLayout relative = (RelativeLayout)view.findViewById(R.id.expand_layout);

                        count_now.setText(count.getText());
                        SKU.setText(device_SKU.getText());
                        name.setText(device_name.getText());
                        ShowTime.show(date);

                        builder.setView(view);
                        builder.create();
                        final AlertDialog dialog = builder.show();

                        error.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                View view = LayoutInflater.from(getContext()).inflate(R.layout.error_select, null);
                                builder.setView(view);
                                builder.create();
                                final AlertDialog dialog = builder.show();

                                btns[0]=(RadioButton)view.findViewById(R.id.output);
                                btns[1]=(RadioButton)view.findViewById(R.id.consume);
                                btns[2]=(RadioButton)view.findViewById(R.id.attrition);
                                btns[3]=(RadioButton)view.findViewById(R.id.lose);
                                btns[4]=(RadioButton)view.findViewById(R.id.error);
                                TextView cancel = (TextView)view.findViewById(R.id.type_cencel);
                                TextView ensure = (TextView)view.findViewById(R.id.type_ensure);
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                ensure.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        for (int i = 0;i < btns.length;i ++) {
                                            if (btns[i].isChecked()) {
                                                error.setText(str[i]);
                                                dialog.dismiss();
                                            }
                                        }
                                    }
                                });
                            }
                        });

                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                count_now.setText(Integer.parseInt(count_now.getText().toString())+1+"");
                            }
                        });

                        minus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                count_now.setText(Integer.parseInt(count_now.getText().toString())-1+"");
                            }
                        });

                        image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                getContext().startActivity(new Intent(getContext(), Checklist_photo.class));
                            }
                        });

                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent verify = new Intent(getContext(), Verify_device.class);
                                verify.putExtra("device",device);
                                getContext().startActivity(verify);
                            }
                        });

                        expand.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (relative.getVisibility() == View.GONE) {
                                    relative.setVisibility(View.VISIBLE);
                                } else {
                                    relative.setVisibility(View.GONE);
                                }
                            }
                        });

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        ensure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (count_now.getText().toString().equals(count.getText().toString())) {
                                    Toast.makeText(getContext(),"库存量未变!",Toast.LENGTH_SHORT).show();
                                } else {
                                    Connector.getDatabase();
                                    NewCheckin checkin = new NewCheckin();
                                    checkin.setBeforeCount(count.getText().toString());
                                    checkin.setCount(count_now.getText().toString());
                                    checkin.setChange(table_head.getText().toString().substring(6));
                                    checkin.setErrorDescribe(describe.getText().toString());
                                    checkin.updateAll("device_SKU = ?",device.getSKU());
                                    count.setText(count_now.getText());
                                    dialog.dismiss();
                                }
                            }
                        });

                        count_now.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count1) {
                                if(count_now.getText().toString().length() == 0) {
                                    table_head.setText("现有库存量");
                                } else {
                                    int result = Integer.parseInt(count_now.getText().toString())-Integer.parseInt(count.getText().toString());
                                    if ( result > 0) {
                                        table_head.setText("现有库存量~生"+result);
                                    } else if (result < 0 ) {
                                        table_head.setText("现有库存量~亏"+result);
                                    } else {
                                        table_head.setText("现有库存量~持平");
                                    }
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });


                    }
                }
            });
        }

        if (first_name != null) {
            first_name.setText(device.getName().substring(0,1));
            int num = new Random().nextInt(6);
            first_name.setBackgroundResource(color[num]);
        }
        return view;
    }

}
