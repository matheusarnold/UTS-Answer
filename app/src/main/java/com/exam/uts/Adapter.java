package com.exam.uts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        Model model = arrayList.get(position);
        final String id = model.getId();
        final String item = model.getItem();
        final String desc = model.getDesc();
        final String qty = model.getQty();
        final String addTimeStamp = model.getAddTimeStamp();
        final String updateTimeStamp = model.getUpdateTimeStamp();

        holder.item.setText(item);
        holder.desc.setText(desc);
        holder.qty.setText(qty);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog(
                        "" + position,
                        "" + id,
                        "" + item,
                        "" + desc,
                        "" + qty,
                        "" + addTimeStamp,
                        "" + updateTimeStamp
                );
            }
        });

    }

    private void editDialog(String position, final String id, final String item, final String desc, final String qty, final String addTimeStamp, final String updateTimeStamp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Do you want to update the data?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_edit_foreground);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent (context, EditDataActivity.class);
                intent.putExtra("ID", id);
                intent.putExtra("ITEM", item);
                intent.putExtra("DESC", desc);
                intent.putExtra("QTY", qty);
                intent.putExtra("ADD_TIMESTAMP", addTimeStamp);
                intent.putExtra("UPDATE_TIMESTAMP", updateTimeStamp);
                intent.putExtra("editMode", true);
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView item, desc, qty;
        ImageButton editButton;

        public Holder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.itemTitle);
            desc = itemView.findViewById(R.id.descTitle);
            qty = itemView.findViewById(R.id.qtyTitle);
            editButton = itemView.findViewById(R.id.editBtn);
        }
    }


}
