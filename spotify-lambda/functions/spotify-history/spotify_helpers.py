import logging
import boto3
from boto.s3.connection import S3Connection
from boto.s3.key import Key

# set up logging
logger = logging.getLogger('PySpotify')


def set_access_boto_session():
    session = boto3.Session(profile_name='lambda')


def connect_to_s3(aws_access_key, aws_secret_key):
    set_access_boto_session()
    conn: object = S3Connection(aws_access_key, aws_secret_key)
    return conn


def create_key(connect, bucket, token: str):
    b = connect.get_bucket(bucket)
    k = Key(b)
    k.key = 'access_tokenzz'
    k.set_contents_from_string(token)


def token_string(token: dict) -> str:
    return token.get('access_token')

