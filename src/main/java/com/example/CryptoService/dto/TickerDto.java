package com.example.CryptoService.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TickerDto {
    private String id;//: "90",
    private String symbol;//: "BTC",
    private String name;//: "Bitcoin",
    private String nameid;//: "bitcoin",
    private Integer rank;//: 1,
    private String price_usd;//: "19791.00",
    private String percent_change_24h;//: "0.06",
    private String percent_change_1h;//: "0.11",
    private String percent_change_7d;//: "-2.54",
    private String market_cap_usd;//: "377365290813.16",
    private String volume24;//: "19787080529.95",
    private String volume24_native;//: "999802.00",
    private String csupply;//: "19067521.00",
    private String price_btc;//: "1.00",
    private String tsupply;//: "19067521",
    private String msupply;//: "21000000"
}
