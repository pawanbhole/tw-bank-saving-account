# tw-bank-saving-account
Simple saving account model based on Spring boot and storing data in memory. For locking using java concurrency api.


### Installation


To run it you need [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) and [maven](https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/) installed. Run following commands to start server

```sh
$ mvn clean install
$ cd tw-bank-saving-account-app
$ mvn spring-boot:run
```

### APIs

# Create account

Used to create new account with specific currency.

**URL** : `/saving-account/`

**Method** : `POST`

**Auth required** : NO


**Headers**
```
Content-Type: application/json
```

**Data constraints**

```json
{
	"currency": "<valid currency code e.g. USD>"
}
```

**Data example**

```json
{
	"currency": "CAD"
}
```

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "balance": {
        "amount": {
            "value": 0,
            "currency": "CAD"
        },
        "timestamp": "2019-03-28T00:45:51.417518+05:30"
    },
    "id": "A00001",
    "creationDate": "2019-03-28T00:45:51.416734+05:30"
}
```



# Get balance
Used to get balance of specific account.

**URL** : `/saving-account/<accountId>/balance`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "amount": {
        "value": 0,
        "currency": "CAD"
    },
    "timestamp": "2019-03-28T00:49:56.768876+05:30"
}
```

## Error Response

**Condition** : If 'account doesnot exists.

**Code** : `404 Not found`

**Content** :

```json
{
    "timestamp": "2019-03-27T19:20:25.290+0000",
    "message": "Resource with id 'A0000d1' not found.",
    "details": "uri=/saving-account/A0000d1/balance"
}
```



# Deposit

Used to deposit account with specific amount.

**URL** : `/saving-account/<accountId>/deposit`

**Method** : `POST`

**Headers**
```
Content-Type: application/json
```

**Data constraints**

```json
{
            "value": <amount>,
            "currency": "<valid currency>"
}
```

**Data example**

```json
{
            "value": 10,
            "currency": "CAD"
}
```

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "balance": {
        "amount": {
            "value": 10,
            "currency": "CAD"
        },
        "timestamp": "2019-03-28T00:51:58.475254+05:30"
    },
    "transaction": {
        "id": "T00001",
        "type": "DEPOSIT",
        "status": "SUCCESSFUL",
        "reasonCode": null,
        "amount": {
            "value": 10,
            "currency": "USD"
        },
        "transactionDate": "2019-03-28T00:51:58.471887+05:30"
    }
}
```


## Error Response

**Condition** : If 'account does not exists.

**Code** : `404 Not found`

**Content** :

```json
{
    "timestamp": "2019-03-27T19:23:42.999+0000",
    "message": "Resource with id 'A00d001' not found.",
    "details": "uri=/saving-account/A00d001/deposit"
}
```




# Withdraw

Used to withdraw account with specific amount.

**URL** : `/saving-account/<accountId>/withdraw`

**Method** : `POST`

**Headers**
```
Content-Type: application/json
```

**Data constraints**

```json
{
            "value": <amount>,
            "currency": "<valid currency>"
}
```

**Data example**

```json
{
            "value": 10,
            "currency": "CAD"
}
```

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "balance": {
        "amount": {
            "value": -2.5,
            "currency": "CAD"
        },
        "timestamp": "2019-03-28T00:56:18.308087+05:30"
    },
    "transaction": {
        "id": "T00002",
        "type": "WITHDRAW",
        "status": "SUCCESSFUL",
        "reasonCode": null,
        "amount": {
            "value": 12.5,
            "currency": "USD"
        },
        "transactionDate": "2019-03-28T00:56:18.305576+05:30"
    }
}
```


## Error Response

**Condition** : If 'account does not exists.

**Code** : `404 Not found`

**Content** :

```json
{
    "timestamp": "2019-03-27T19:23:42.999+0000",
    "message": "Resource with id 'A00d001' not found.",
    "details": "uri=/saving-account/A00d001/withdraw"
}
```


