package se.andreasottesen.btcchartsviewer.app.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andreas.ottesen on 2014-05-13.
 */
public class MarketContent {
    public static List<MarketItem> ITEMS = new ArrayList<MarketItem>();
    public static Map<String, MarketItem> ITEM_MAP = new HashMap<String, MarketItem>();

    public static void addItem(MarketItem item){
        ITEMS.add(item);
        ITEM_MAP.put(item.symbol, item);
    }

    public static void addItems(List<MarketItem> items){
        for (MarketItem item : items){
            ITEMS.add(item);
            ITEM_MAP.put(item.symbol, item);
        }
    }

    /* For testing
    static{
        MarketItem item = new MarketItem();
        item.symbol = "marketSEK";
        item.currency = "SEK";
        item.ask = 2000;

        addItem(item);

        item = new MarketItem();
        item.symbol = "marketNOK";
        item.currency = "NOK";
        item.ask = 1800;

        addItem(item);
    }
    */

    public static class MarketItem{
        public String symbol;
        public String currency;
        public float high;
        public float low;
        public float ask;
        public float bid;
        public float close;
        public int n_traders;
        public float currency_volume;
        public float volume;

        @Override
        public String toString() {
            return symbol;
        }
    }
}
