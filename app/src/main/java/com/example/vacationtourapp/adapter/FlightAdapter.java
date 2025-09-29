package com.example.vacationtourapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vacationtourapp.R;
import com.example.vacationtourapp.model.Flight;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private List<Flight> flightList;

    public FlightAdapter(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_item, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flightList.get(position);
        holder.flightNumber.setText(flight.getFlightNumber());
        holder.departureTime.setText("Departure: " + flight.getDepartureTime());
        holder.arrivalTime.setText("Arrival: " + flight.getArrivalTime());
        holder.price.setText("Price: " + flight.getPrice());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView flightNumber;
        TextView departureTime;
        TextView arrivalTime;
        TextView price;
        CardView cardView;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            flightNumber = itemView.findViewById(R.id.flight_number);
            departureTime = itemView.findViewById(R.id.departure_time);
            arrivalTime = itemView.findViewById(R.id.arrival_time);
            price = itemView.findViewById(R.id.price);
            cardView = itemView.findViewById(R.id.card_view); // Update with the correct ID
        }
    }
}
