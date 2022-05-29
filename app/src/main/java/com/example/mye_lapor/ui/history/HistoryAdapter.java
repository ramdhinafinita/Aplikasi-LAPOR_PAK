package com.example.mye_lapor.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mye_lapor.R;
import com.example.mye_lapor.model.ModelDatabase;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<ModelDatabase> modelDatabase;
    Context mContext;
    HistoryAdapterCallback mAdapterCallback;

    public HistoryAdapter(Context context, List<ModelDatabase> modelDatabaseList,
                          HistoryAdapterCallback adapterCallback) {
        this.mContext = context;
        this.modelDatabase = modelDatabaseList;
        this.mAdapterCallback = adapterCallback;
    }

    public void setDataAdapter(List<ModelDatabase> items) {
        modelDatabase.clear();
        modelDatabase.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelDatabase data = modelDatabase.get(position);

        holder.tvKategori.setText(data.getKategori());
        holder.tvNama.setText(data.getKategori());
        holder.tvDate.setText(data.getTanggal());
        holder.tvLokasi.setText(data.getLokasi());

        switch (data.getKategori()) {
            case "Laporan Kebakaran":
                holder.layoutHeader.setBackgroundResource(R.color.red);
                break;
            case "Laporan Medis":
                holder.layoutHeader.setBackgroundResource(R.color.blue);
                break;
            case "Laporan Bencana Alam":
                holder.layoutHeader.setBackgroundResource(R.color.green);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return modelDatabase.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKategori, tvNama, tvDate, tvLokasi;
        public CardView cvHistory;
        public LinearLayout layoutHeader;

        public ViewHolder(View itemView) {
            super(itemView);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvLokasi = itemView.findViewById(R.id.tvLokasi);
            cvHistory = itemView.findViewById(R.id.cvHistory);
            layoutHeader = itemView.findViewById(R.id.layoutHeader);

            cvHistory.setOnClickListener(view -> {
                ModelDatabase modelLaundry = modelDatabase.get(getAdapterPosition());
                mAdapterCallback.onDelete(modelLaundry);
            });
        }
    }

    public interface HistoryAdapterCallback {
        void onDelete(ModelDatabase modelDatabase);
    }
}
