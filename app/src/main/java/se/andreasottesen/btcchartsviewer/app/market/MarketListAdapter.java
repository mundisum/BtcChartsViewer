package se.andreasottesen.btcchartsviewer.app.market;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import se.andreasottesen.btcchartsviewer.app.R;

/**
 * Created by andreas.ottesen on 2014-05-13.
 */
public class MarketListAdapter extends BaseAdapter {
    private List<MarketContent.MarketItem> markets;
    private Context context;

    public MarketListAdapter(Context context, List<MarketContent.MarketItem> markets) {
        this.markets = markets;
        this.context = context;
    }

    public void setMarkets(List<MarketContent.MarketItem> markets) {
        this.markets = markets;
    }

    public List<MarketContent.MarketItem> getMarkets() {
        return this.markets;
    }

    @Override
    public int getCount() {
        if (markets != null)
            return markets.size();

        return 0;
    }

    @Override
    public Object getItem(int i) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        //MarketViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_item_list, null);
        }

        MarketContent.MarketItem marketItem = (MarketContent.MarketItem) getItem(position);

        TextView textView = (TextView)view.findViewById(android.R.id.text1);
        textView.setText(marketItem.symbol);

        textView = (TextView) view.findViewById(android.R.id.text2);
        textView.setText(marketItem.currency);

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
}
