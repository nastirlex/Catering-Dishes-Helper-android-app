# -*- coding: utf-8 -*-
import numpy as np
import pandas as pd
from urllib.parse import quote_plus
import pymongo as pym
import dns.resolver
import json
from bson import ObjectId



dns.resolver.default_resolver=dns.resolver.Resolver(configure=False)
dns.resolver.default_resolver.nameservers=['1.1.1.1']


def connect_to_mongodb():
    username = quote_plus('fanot')
    password = quote_plus('funny1cool')
    url = 'mongodb+srv://'+ username + ':' + password +'@cluster0.t4mdtbd.mongodb.net/?retryWrites=true&w=majority'
    client = pym.MongoClient(url)

    return client.get_database('Cluster0')


def recommend(name):
    recommend_restaurant = []
    db = connect_to_mongodb()
    df_percent = pd.DataFrame.from_dict(db['DF'].find())
    df_percent.set_index('name', inplace=True)
    indices = pd.Series(df_percent.index)
    idx = indices[indices == name].index[0]
    df_percent = pd.DataFrame.from_dict(db['DF'].find())
    collection = db['sim']
    result = collection.find_one()
    cosine_similarities = np.array(result['matrix'])
    score_series = pd.Series(cosine_similarities[idx]).sort_values(ascending=False)
    top31_indexes = list(score_series.iloc[0:31].index)
    for each in top31_indexes:
        recommend_restaurant.append(list(df_percent.index)[each])
        df_new = pd.DataFrame(columns=['id','address_name','name','cuisines', 'general_rating', 'avg_price'])
    for each in recommend_restaurant:
        df_new = df_new.append(pd.DataFrame(df_percent[['id','address_name','name','cuisines', 'general_rating', 'avg_price']][df_percent.index == each].sample()))
    df_new = df_new.drop_duplicates(subset=['cuisines', 'general_rating', 'avg_price'], keep=False)
    df_new = df_new.head(15)
    print(type(df_new))
    #print('Топ заведений с похожими отзывам: %d, %s' % (len(df_new), name))
    return df_new.to_json(orient='records', force_ascii=False)



def getCater():
    db = connect_to_mongodb()
    cursor = db['DF'].find()
    data = []
    for document in cursor:
        document['_id'] = str(document['_id'])
        data.append(document)
    return json.loads(json.dumps(data, ensure_ascii=False))


def getCatter_dishes():
    db = connect_to_mongodb()
    cursor = db['dishes'].find()
    data = []
    for document in cursor:
        document['_id'] = str(document['_id'])
        data.append(document)
    return json.loads(json.dumps(data, ensure_ascii=False))