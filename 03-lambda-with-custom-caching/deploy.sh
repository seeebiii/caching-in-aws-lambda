#!/usr/bin/env bash

LAMBDA_BUCKET="${LAMBDA_BUCKET}"
STACK_NAME="lambda-with-custom-caching"
SPRING_BOOT_HAZELCAST_APP=spring-boot-hazelcast.jar

if [[ "${LAMBDA_BUCKET}" == "" || "${STACK_NAME}" == "" ]]; then
    echo "You must set LAMBDA_BUCKET and STACK_NAME first."
    exit 1;
fi

cd ec2-hazelcast
mvn clean compile package
aws s3 cp target/${SPRING_BOOT_HAZELCAST_APP} s3://${LAMBDA_BUCKET}/${SPRING_BOOT_HAZELCAST_APP}
cd ..

cd lambda
mvn clean compile package
cd ..

aws cloudformation package --template-file cfn.yml --s3-bucket ${LAMBDA_BUCKET} --output-template-file cfn.packaged.yml
aws cloudformation deploy --template-file cfn.packaged.yml --stack-name ${STACK_NAME} --capabilities CAPABILITY_IAM --parameter-overrides S3Bucket="${LAMBDA_BUCKET}" S3Key="${SPRING_BOOT_HAZELCAST_APP}"