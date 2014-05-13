package se.andreasottesen.btcchartsviewer.app.market;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by andreas.ottesen on 2014-05-13.
 */
public interface IMarketService {
    @GET("/v1/markets.json")
    List<MarketContent.MarketItem> marketItems();
}
