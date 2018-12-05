let cachedValue;

module.exports.handler = function(event, context, callback) {
    console.log('Starting Lambda.');

    if (!cachedValue) {
        console.log('Setting cachedValue now...');
        cachedValue = 'Foobar';
    } else {
        console.log('Cached value is already set: ', cachedValue);
    }
};