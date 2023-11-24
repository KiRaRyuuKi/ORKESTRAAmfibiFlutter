<?php
require 'vendor/autoload.php';

if (isset($_POST['authKey']) && ($_POST['authKey'] == 'abc')) {
    $stripe = new \Stripe\StripeClient('sk_test_51OFYSpLz8qBEbQZ9grK14vqvUPvTefXFno6qShpWgeAKxjzoz0C1QIcdxMiJrG0eTa3DFAPyfDeornA2wlsVptjZ00AK3EC7Ml');

    // Use an existing Customer ID if this is a returning customer.
    $customer = $stripe->customers->create(
        [
            'name' => 'KiRa',
            'address' => [
                'line1' => 'Demo Address',
                'postal_code' => '68271',
                'city' => 'Bondowoso',
                'state' => 'Jawa Timur',
                'country' => 'IDN',
            ]
        ]
    );
    $ephemeralKey = $stripe->ephemeralKeys->create([
        'customer' => $customer->id,
    ], [
        'stripe_version' => '2022-08-01',
    ]);
    $paymentIntent = $stripe->paymentIntents->create([
        'amount' => 10999,
        'currency' => 'idr',
        'description' => 'ORKESTRA Payment for Android',
        'customer' => $customer->id,
        // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
        'automatic_payment_methods' => [
            'enabled' => 'true',
        ],
    ]);

    echo json_encode(
        [
            'paymentIntent' => $paymentIntent->client_secret,
            'ephemeralKey' => $ephemeralKey->secret,
            'customer' => $customer->id,
            'publishableKey' => 'pk_test_51OFYSpLz8qBEbQZ9aO0fDbRvspdUUv9aaoiU6nCkqO8ANl6Ez7FcGP6GvjwmwL0NTiBmIqaIq9pBDoWzMQBBqK2400OwrJRCMK'
        ]
    );
    http_response_code(200);
} else echo "Not Authorised";