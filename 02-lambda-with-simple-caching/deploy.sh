#!/usr/bin/env bash

LAMBDA_BUCKET="${LAMBDA_BUCKET}"
STACK_NAME="lambda-with-simple-caching"

if [[ "${LAMBDA_BUCKET}" == "" || "${STACK_NAME}" == "" ]]; then
    echo "You must set LAMBDA_BUCKET and STACK_NAME first."
    exit 1;
fi

yarn
yarn run build
aws cloudformation package --template-file cfn.yml --s3-bucket ${LAMBDA_BUCKET} --output-template-file cfn.packaged.yml
aws cloudformation deploy --template-file cfn.packaged.yml --stack-name ${STACK_NAME} --capabilities CAPABILITY_IAM