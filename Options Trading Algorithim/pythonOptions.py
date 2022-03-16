
def menu_choice_options():
    print("")
    print("1. Buy")
    print("2. Sell")
    print("3. Exit")
    print("")
    menu_choice_options = input("Select a menu item by typing it: ")
    return menu_choice_options





# Custom Option Spread
# Buy to open 2 contracts of the XYZ Jan 17, 2020 $43 Put and Sell to open 1 contracts of the XYZ Jan 18, 2019 $45 Put at the Market good for the Day.
"""
custom_option_spread = {
    "orderStrategyType": "SINGLE",
    "orderType": "MARKET",
    "orderLegCollection": [
        {
        "instrument": {
            "assetType": "OPTION",
            "symbol": "XYZ_011819P45"
        },
        "instruction": "SELL_TO_OPEN",
        "quantity": 1
        },
        {
        "instrument": {
            "assetType": "OPTION",
            "symbol": "XYZ_011720P43"
        },
        "instruction": "BUY_TO_OPEN",
        "quantity": 2
        }
    ],
    "complexOrderStrategyType": "CUSTOM",
    "duration": "DAY",
    "session": "NORMAL"
}

"""

