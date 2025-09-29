package com.example.vacationtourapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vacationtourapp.R;
import com.example.vacationtourapp.model.Hotel;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> hotelList;

    public HotelAdapter(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_item, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.hotelName.setText(hotel.getName());
        holder.hotelAddress.setText(hotel.getAddress());
        holder.hotelPrice.setText("Price: " + hotel.getPrice());
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView hotelName;
        TextView hotelAddress;
        TextView hotelPrice;
        CardView cardView;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotel_name);
            hotelAddress = itemView.findViewById(R.id.hotel_address);
            hotelPrice = itemView.findViewById(R.id.hotel_price);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
