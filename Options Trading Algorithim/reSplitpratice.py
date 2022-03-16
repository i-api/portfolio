import re
import pprint #pretty print


nonSerializableDictString = "{'order_id': '5787785440', 'headers': {'Date': 'Sun, 19 Dec 2021 00:58:54 GMT', 'Content-Length': '0', 'Connection': 'keep-alive', 'Location': 'https://api.tdameritrade.com/v1/accounts/144495813/orders/5787785440', 'X-API-Version': '1.16.18', 'Cache-Control': 'no-cache, no-store, max-age=0, must-revalidate', 'Pragma': 'no-cache', 'Expires': '0', 'X-XSS-Protection': '1; mode=block, 1; mode=block', 'X-Frame-Options': 'DENY, SAMEORIGIN', 'X-Content-Type-Options': 'nosniff, nosniff', 'Access-Control-Allow-Headers': 'origin, x-requested-with, accept, authorization, content-type, correlationid, apikey, application-name', 'Access-Control-Max-Age': '3628800', 'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS, HEAD, PATCH', 'Content-Security-Policy': \"frame-ancestors 'self'\", 'Strict-Transport-Security': 'max-age=31536000; includeSubDomains, max-age=31536000'}, 'content': b'', 'status_code': 201, 'request_body': b'{\"orderType\": \"LIMIT\", \"session\": \"NORMAL\", \"duration\": \"DAY\", \"price\": \"1\", \"orderStrategyType\": \"SINGLE\", \"orderLegCollection\": [{\"instruction\": \"BUY\", \"quantity\": \"1\", \"instrument\": {\"symbol\": \"AAPL\", \"assetType\": \"EQUITY\"}}]}', 'request_method': 'POST'}"

pprint.pprint(re.split("'b'", nonSerializableDictString))
#pprint.pprint(nonSerializableDictString)

