package com.example.vacationtourapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vacationtourapp.R;
import com.example.vacationtourapp.model.BookingHistory;
import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.BookingHistoryViewHolder> {

    private List<BookingHistory> bookingHistoryList;

    // Constructor
    public BookingHistoryAdapter(List<BookingHistory> bookingHistoryList) {
        this.bookingHistoryList = bookingHistoryList;
    }

    @NonNull
    @Override
    public BookingHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each booking history item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking_history, parent, false);
        return new BookingHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryViewHolder holder, int position) {
        // Bind data to the ViewHolder
        BookingHistory bookingHistory = bookingHistoryList.get(position);
        holder.bookingName.setText(bookingHistory.getBookingName());
        holder.bookingStatus.setText(bookingHistory.getBookingStatus());
        holder.bookingPrice.setText(bookingHistory.getBookingPrice());
    }

    @Override
    public int getItemCount() {
        return bookingHistoryList.size();
    }

    // ViewHolder class for RecyclerView items
    public static class BookingHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView bookingName, bookingStatus, bookingPrice;

        public BookingHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the TextViews
            bookingName = itemView.findViewById(R.id.booking_name);
            bookingStatus = itemView.findViewById(R.id.booking_status);
            bookingPrice = itemView.findViewById(R.id.booking_price);
        }
    }
}
