#!/bin/env python


import logging
from authlib.integrations.requests_client import OAuth2Session
import secrets
import spotify_helpers as utils

# set up logging
logger = logging.getLogger('PySpotify')
logger.setLevel(logging.INFO)
ch = logging.StreamHandler()
ch.setLevel(logging.INFO)
formatter = logging.Formatter('%(asctime)s - %(name)s - ' +
                              '%(levelname)s - %(message)s')
ch.setFormatter(formatter)
logger.addHandler(ch)


def save_token_to_s3(toke: dict):
    access_token = utils.token_string(toke)
    conn = utils.connect_to_s3(secrets.aws_access_key, secrets.aws_secret_key)
    print(access_token)
    (utils.create_key(conn, secrets.bucket, access_token))


redirect_uri = "https://www.google.com/"
scope = r'user-read-private user-top-read'
authorization_endpoint = 'https://accounts.spotify.com/authorize'
token_endpoint = "https://accounts.spotify.com/api/token"

if __name__ == "__main__":

    client = OAuth2Session(client_id=secrets.client_id,
                           client_secret=secrets.client_secret,
                           authorization_endpoint=authorization_endpoint,
                           token_endpoint=token_endpoint,
                           scope=scope,
                           redirect_uri=redirect_uri)

    uri, state = client.create_authorization_url(authorization_endpoint)

    token = client.fetch_access_token(url=token_endpoint)
    (save_token_to_s3(token))
