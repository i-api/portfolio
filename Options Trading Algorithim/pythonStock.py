from tradingAlgo import *


#@app.route("/stock/") #flask
def menu_choice_stock():
    ticker_to_trade = str(input("\nEnter the Stock Ticker to trade: ").upper())
    print_select_ticker_quotes(ticker_to_trade)


    print("1. Buy")
    print("2. Sell")
    print("3. Exit")
    menu_choice_stock = input("Select a menu item by typing it: ")

    if menu_choice_stock in {"Buy", "buy"}:
        user_bid_price = str(input("Enter your bid price: "))
        user_share_quantity = str(input("How many shares are you buying?: "))
        buy_stock(ticker_to_trade, user_bid_price, user_share_quantity)
    elif menu_choice_stock in {"Sell", "sell"}:
        user_ask_price = str(input("Enter your ask price: "))
        user_share_quantity = str(input("How many shares are you selling?: "))
        sell_stock(ticker_to_trade, user_ask_price, user_share_quantity)
    elif menu_choice_stock in {"Exit", "exit", "3"}:
        print("\nProgram will exit.\n")
        sys.exit()
    else:
        print("\nInvalid Input. Program will exit.\n")
        sys.exit()
    return menu_choice_stock



def print_select_ticker_quotes(ticker_to_trade):
    # sample - quote, and create dictionary
    quotes_response = td_client.get_quotes(instruments = [ticker_to_trade])
    #write json file and dictionary
    #import json
    """
    Note that dump and load convert between files and objects, 
    while dumps and loads convert between strings and objects. 
    You can think of the s-less functions as wrappers around the s functions:
    json loads -> returns an object from a string representing a json object.
    json dumps -> returns a string representing a json object from an object.
    load and dump -> read/write from/to file instead of string
    """
    with open('TickerQuote.json', 'w') as outfile:
        json.dump(quotes_response, outfile) #alternative: outfile.write(str(quotes_response))
        outfile.close()

    ticker_dictionary = json.load(open('TickerQuote.json'))

    tick = ticker_to_trade
    bid = str(ticker_dictionary[ticker_to_trade]['bidPrice'])
    ask = str(ticker_dictionary[ticker_to_trade]['askPrice'])
    last = str(ticker_dictionary[ticker_to_trade]['lastPrice'])
    vol = str(ticker_dictionary[ticker_to_trade]['volatility'])

    print(("\n" + tick + "   | " + "Bid: " + bid + " | " + "Ask: " + ask + " | " 
    + "Last: " + last + " | " + "Volatility: " + vol + "\n\n"))





def buy_stock(ticker_to_trade, user_bid_price, user_share_quantity):
    limit_order = {
        "orderType": "LIMIT",
        "session": "NORMAL", #NORMAL, AM, PM, Seamless.
        "duration": "DAY",
        "price": user_bid_price,
        "orderStrategyType": "SINGLE",
        "orderLegCollection": [
            {
                "instruction": "BUY",
                "quantity": user_share_quantity,
                "instrument": {
                    "symbol": ticker_to_trade,
                    "assetType": "EQUITY"
                }
            }
        ]
    }
    # Place the new order.
    new_order_response = td_client.place_order(account = ACCOUNT_NUMBER, order = limit_order)
    pprint.pprint(new_order_response)
    make_order_dictionary(new_order_response)





def sell_stock(ticker_to_trade, user_ask_price, user_share_quantity):
    limit_order = {
        "orderType": "LIMIT",
        "session": "NORMAL", #NORMAL, AM, PM, Seamless.
        "duration": "DAY",
        "price": user_ask_price,
        "orderStrategyType": "SINGLE",
        "orderLegCollection": [
            {
                "instruction": "SELL",
                "quantity": user_share_quantity,
                "instrument": {
                    "symbol": ticker_to_trade,
                    "assetType": "EQUITY"
                }
            }
        ]
    }
    # Place the new order.
    new_order_response = td_client.place_order(account = ACCOUNT_NUMBER, order = limit_order)
    pprint.pprint(new_order_response)
    make_order_dictionary(new_order_response)


def make_order_dictionary(new_order_response):
    
    #python_dictionary = new_order_response
    #json_object = json.dumps(python_dictionary, indent = 4)  #FIXME
    #print(type(json_object))

    # Why does td_client.get_quotes(instruments = ['AAPL']) return a proper python dictionary?
    # But new_order_response = td_client.place_order(account = ACCOUNT_NUMBER, order = limit_order)
    #  return a non serializable dictionary?
    # TypeError: Object of type CaseInsensitiveDict is not JSON serializable

    with open('order.jsonc', 'w') as outfile:
        json.dump(str(new_order_response), outfile) #str remove #FIXME
        outfile.close()
    """
    with open('order.jsonc') as infile:
        new_dict = json.loads(infile)
        infile.close()
    #order_dictionary = json.load(open('order.jsonc'))
    """
    #a = json.loads(new_order_response)
    #print(a['request_body']['orderType'])
    #print(new_order_response['request_body']['orderLegCollection']['instrument']['assetType']) #FIXME

    #print(new_order_response['request_body']['orderType']) ##FIXME TypeError: byte indices must be integers or slices, not str

    temp_order_id = new_order_response['order_id']
    get_order_info = td_client.get_orders(account = ACCOUNT_NUMBER, order_id = temp_order_id)
    
    #wrtes to json file more order info
    with open('more_order_info.jsonc', 'w') as outfile:
        json.dump(get_order_info, outfile)
        outfile.close()

 
"""
Stock
kirk
Buy
1
1
   
    """