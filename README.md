# Caching in AWS Lambda

This repository presents three different ways to use caching in AWS Lambda functions.
You can use this to start with your own caching setup by using one of the following approaches:

- [Simple Caching](/02-lambda-with-simple-caching): you only cache data within a Lambda function.
This makes sense if you your cached data will not change frequently.
The disadvantage is that no cache synchronization happens between the instances of your Lambda functions and their local caches.
- [Custom Caching](/03-lambda-with-custom-caching): you are deploying your own caching service.
Since you don't want to put your cached data to a public EC2 instance, you'll keep your cache instances in a VPC.
Unfortunately this requires you to also put your Lambda functions into the same VPC in order to let them access the cache.
In this example I'm using a Java Lambda function which is connecting to [Hazelcast](https://hazelcast.org/) on an EC2 instance.
- [Managed Caching](/04-lambda-with-managed-caching): you are using [ElastiCache](https://aws.amazon.com/elasticache/) which is a managed caching service by AWS.
The setup is the same as in the [Custom Caching](/03-lambda-with-custom-caching) example.
You can choose between Redis and Memcached here.
The example is based on a NodeJS Lambda connecting to a Redis cluster.


Each folder contains a `deploy.sh` file which lets you easily deploy the related CloudFormation template.
You only need to provide a `LAMBDA_BUCKET` environment variable pointing to an S3 bucket where the Lambda function artifacts are uploaded to (necessary for deployment).


For further information you can also read my blog post about [Caching in AWS Lambda](https://www.sebastianhesse.de/2018/12/16/caching-in-aws-lambda/) or take a look at the [slides of my talk](https://speakerdeck.com/sebastianhesse/caching-in-aws-lambda).

## Related Projects

[aws-lambda-boilerplate](https://github.com/seeebiii/aws-lambda-boilerplate)

[lambda-updater](https://github.com/seeebiii/lambda-updater)

## License

MIT License

Copyright (c) 2018 [Sebastian Hesse](https://www.sebastianhesse.de)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
