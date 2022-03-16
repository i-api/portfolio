# pip install td-ameritrade-python-api # from areed1192's api library
import sys # for command line arguments, ie. sys.exit()
import pprint #pretty print
from datetime import datetime, timedelta #for date
from config import CONSUMER_KEY, REDIRECT_URI, JSON_PATH
from config import ACCOUNT_NUMBER, ACCOUNT_PASSWORD, TD_ACCOUNT
from td.client import TDClient

from pythonStock import *
from pythonOptions import *

import json

todays_date = datetime.now().strftime('%Y-%m-%d')
todays_date_plus30 = timedelta(30) + datetime.now()
todays_date_plus60 = timedelta(60) + datetime.now()

# Create a new instance of the client
td_client = TDClient(client_id = CONSUMER_KEY, 
redirect_uri = REDIRECT_URI, credentials_path = JSON_PATH)

# Login to a new session - refresh token has 90 days of validity
td_client.login()


#from flask import Flask #      export FLASK_APP=tradingAlgo.py       flask run
#app = Flask(__name__)
#@app.route("/") # flask
def main():
    print ("\n\n1. Stock")
    print ("2. Options")
    menu_choice = input("Select a Security: ")

    if(menu_choice == "Stock" or menu_choice == "stock"):
        menu_choice_stock()
        sys.exit()
    elif(menu_choice == "Options" or menu_choice == "options"):
        menu_choice_options()
        sys.exit()
    else:
        print("\nDon't f*ck it up. Program will exit.\n")
        sys.exit()










# main method call executes the program, but only if this file is run directly
if __name__ == '__main__':
    main()
