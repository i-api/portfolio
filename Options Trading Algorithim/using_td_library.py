# pip install td-ameritrade-python-api # from areed1192's api library
import pprint #pretty print
from datetime import datetime, timedelta #for date
from config import CONSUMER_KEY, REDIRECT_URI, JSON_PATH
from config import ACCOUNT_NUMBER, ACCOUNT_PASSWORD, TD_ACCOUNT
from td.client import TDClient

# In case u need these bruh
account_number = ACCOUNT_NUMBER
account_password = ACCOUNT_PASSWORD
td_account = TD_ACCOUNT

todays_date = datetime.now().strftime('%Y-%m-%d')
todays_date_plus30 = timedelta(30) + datetime.now()
todays_date_plus60 = timedelta(60) + datetime.now()


# Create a new instance of the client
td_client = TDClient(client_id = CONSUMER_KEY, 
redirect_uri = REDIRECT_URI, credentials_path = JSON_PATH)

# Login to a new session - refresh token has 90 days of validity
td_client.login()

# Get Current Quotes
"""
quotes_response = td_client.get_quotes(instruments = ['AAPL', 
'MSFT', 'GOOG', 'AMZN', 'ADBE', 'TSLA', 'KIRK', 'SPY', 'QQQ', 'VIX'])
pprint.pprint(quotes_response)
"""

# Positions and Orders for an Account(s)
"""
orders_and_positions = td_client.get_accounts(account = 'all', 
fields = ['positions', 'orders'])
pprint.pprint(orders_and_positions)
"""

# Grab all the transactions, you can specifiy which transactions you want
"""
transactions = td_client.get_transactions(account = 'TD_ACCOUNT', 
transaction_type = 'SELL_ONLY', symbol = 'AAPL', start_date = '2019-01-01',
end_date = '2021-11-31')
pprint.pprint(transactions)
"""

# Market Hours
"""
market_hours = td_client.get_market_hours(markets = ['EQUITY', 'BOND'], 
date = '2021-11-08');
pprint.pprint(market_hours)
"""

# Option CHAIN Dictionary  d
opt_chain = {
    'symbol': 'SPY',
    'contract_type': '',
    'optionType': '',
    'daysToExpiration': '', # days to expiration is only used in ANALYTICAL strategy chains
    'fromDate': todays_date, #theta inflection point is 45 DTE
    'toDate': todays_date_plus60.strftime('%Y-%m-%d'),
    'strikeCount': '',
    'includeQuotes': '',
    'range': '',
    'Strategy': '',
    'volatility': '',
}

# Get Option Chains
"""
opt_chains = td_client.get_options_chain(opt_chain) #args_dictionary = opt_chain
pprint.pprint(opt_chains)
"""

# Select Ticker to trade
"""
ticker_to_trade = str(input("Enter the Stock Ticker to trade: ").upper())
"""


"""
# Search Instuments - Symbol Search
search_instrument = td_client.search_instruments(symbol = 'AAPL', projection = 'symbol-search')
pprint.pprint(search_instrument)

# Search Instuments - Symbol Regex
search_instrument = td_client.search_instruments(symbol = 'M.*', projection = 'symbol-regex')
pprint.pprint(search_instrument)

# Search Instuments - Description
search_instrument = td_client.search_instruments(symbol = 'quantum', projection = 'desc-search')
pprint.pprint(search_instrument)

# Search Instuments - Description Regex
search_instrument = td_client.search_instruments(symbol = 'Technology.*', projection = 'desc-regex')
pprint.pprint(search_instrument)

# Search Instruments - Fundamental Data
search_instrument = td_client.search_instruments(symbol = 'MSFT', projection = 'fundamental')
pprint.pprint(search_instrument)
"""

"""
# Get Instruments
instrument = td_client.get_instruments(cusip = '594918104')
pprint.pprint(instrument)

# Get Movers
spx_movers = td_client.get_movers(market = '$SPX.X', direction = 'up', change = 'percent')
pprint.pprint(spx_movers)
"""

"""
# 1 Minute Chart - keep in mind 15 minute delay in historical price data
minute_data = td_client.get_price_history(symbol = 'MSFT', period_type = 'day', 
frequency_type = 'minute', frequency = '1', extended_hours = 'true')
pprint.pprint(minute_data)

# 5 Minute Chart
minute_data = td_client.get_price_history(symbol = 'MSFT', period_type = 'day', 
frequency_type = 'minute', frequency = '5', extended_hours = 'true')
pprint.pprint(minute_data)

# Daily Chart
daily_data = td_client.get_price_history(symbol = 'MSFT', period_type = 'month', 
frequency_type = 'daily', frequency = '1', extended_hours = 'true')
pprint.pprint(daily_data)
"""
