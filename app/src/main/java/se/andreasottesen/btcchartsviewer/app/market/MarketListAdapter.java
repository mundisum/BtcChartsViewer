package se.andreasottesen.btcchartsviewer.app.market;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.andreasottesen.btcchartsviewer.app.R;

/**
 * Created by andreas.ottesen on 2014-05-13.
 */
public class MarketListAdapter extends ArrayAdapter<MarketContent.MarketItem> {
    private List<MarketContent.MarketItem> markets;
    private List<MarketContent.MarketItem> filteredMarkets;
    private Context context;
    private MarketListFilter filter;

    public MarketListAdapter(Context context, List<MarketContent.MarketItem> markets) {
        super(context, R.layout.fragment_item_list);
        this.markets = markets;
        this.filteredMarkets = markets;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (markets != null)
            return markets.size();

        return 0;
    }

    @Override
    public MarketContent.MarketItem getItem(int i) {
        if (markets != null)
            return markets.get(i);

        return null;
    }

    @Override
    public long getItemId(int i) {
        if (markets != null)
            return markets.get(i).hashCode();

        return 0;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new MarketListFilter();
        }

        return filter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        //MarketViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_item_list, null);
        }

        // Get market item from filtered items
        MarketContent.MarketItem marketItem = filteredMarkets.get(position);

        TextView textView = (TextView)view.findViewById(R.id.txtSymbol);
        textView.setText(marketItem.symbol);

        textView = (TextView) view.findViewById(R.id.txtCurrency);
        textView.setText(marketItem.currency);

        textView = (TextView)view.findViewById(R.id.txtAsk);
        textView.setText(Float.toString(marketItem.ask));

        textView = (TextView)view.findViewById(R.id.txtBid);
        textView.setText(Float.toString(marketItem.bid));

        /*
        holder = new MarketViewHolder();
        holder.star = (CheckBox)view.findViewById(R.id.btn_star);
        holder.star.setOnCheckedChangeListener(starOnCheckedChanged);

        holder.market = (BtcMarket) getItem(position);
        holder.content = (TextView) view.findViewById(android.R.id.text1);
        holder.content.setText(holder.market.symbol);

        holder.currency = (TextView) view.findViewById(android.R.id.text2);
        holder.currency.setText(holder.market.currency);

        */

        return view;
    }

    public class MarketListFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence contraint) {
            FilterResults retVal = new FilterResults();
            retVal.values = markets;
            retVal.count = markets.size();

            if (contraint != null && contraint.toString().length() > 0){
                String constraintString = contraint.toString().toUpperCase();
                ArrayList filt = new ArrayList();
                ArrayList tempItems = new ArrayList();
                tempItems.addAll(markets);

                for (int i=0;i<tempItems.size();i++){
                    MarketContent.MarketItem item =(MarketContent.MarketItem) tempItems.get(i);
                    if (item.symbol.toUpperCase().contains(constraintString)){
                        filt.add(item);
                    }
                }
                retVal.count = filt.size();
                retVal.values = filt;
            }

            return retVal;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredMarkets = (List< MarketContent.MarketItem>)results.values;
            notifyDataSetChanged();

            markets.clear();

            Log.d("Filter", "Starting publish results");

            for (int i=0;i<filteredMarkets.size();i++){
                markets.add(filteredMarkets.get(i));
            }

            Log.d("Filter", "Finished publish results");
            notifyDataSetInvalidated();
        }
    }
}
