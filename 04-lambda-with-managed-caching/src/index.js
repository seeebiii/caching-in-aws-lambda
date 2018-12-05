const request = require('request');
const redis = require('redis');

const client = redis.createClient(process.env.REDIS_URL);


module.exports.handler = function(event, context, callback) {
    console.log('Starting Lambda.');

    testInternetAccess(function() {
        let cacheKey = 'foo';

        client.get(cacheKey, function(err, reply) {
            if (reply && reply.toString()) {
                console.log('Data already in cache: ', reply.toString());
            } else {
                console.log('Add new data to cache');
                client.set(cacheKey, 'bar');
            }

            client.quit();
        });
    });
};

function testInternetAccess(callback) {
    request('https://www.google.com', function(err, res, body) {
        if (err) {
            console.log('Error: ', err);
        } else {
            console.log('Status code: ', res && res.statusCode);
        }

        callback();
    });
}

