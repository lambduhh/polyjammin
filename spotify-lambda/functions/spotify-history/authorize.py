#!/bin/env python

from authlib.integrations.requests_client import OAuth2Session
import spotify_helpers as utils
import os

client_id = os.environ['client_id']
secret_client_id = os.environ['secret_client_id']
aws_access_key = os.environ['aws_access_key']
aws_secret_key = os.environ['aws_secret_key']
bucket = 'spotify-data-polyjam'

redirect_uri = "https://www.google.com/"
scope = r'user-read-private user-top-read'
authorization_endpoint = 'https://accounts.spotify.com/authorize'
token_endpoint = "https://accounts.spotify.com/api/token"


def save_token_to_s3(toke: dict):
    access_token = utils.token_string(toke)
    conn = utils.connect_to_s3(aws_access_key, aws_secret_key)
    print(access_token)
    (utils.create_key(conn, bucket, access_token))


def lambda_handler(event=None, context=None):
    client = OAuth2Session(client_id=client_id,
                           client_secret=secret_client_id,
                           authorization_endpoint=authorization_endpoint,
                           token_endpoint=token_endpoint,
                           scope=scope,
                           redirect_uri=redirect_uri)

    uri, state = client.create_authorization_url(authorization_endpoint)

    token = client.fetch_access_token(url=token_endpoint)
    return token


if __name__ == "__main__":
    (lambda_handler())
