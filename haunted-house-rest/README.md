# PA165 Haunted House - REST module guide

## Install

Run server by command:

```
mvn tomcat7:run
```

## Ability entity

### Get abilities by visibility

```
/pa165/rest/ability/all/{visible}?searchFilter={searchFilter}
```

**Example**

```
curl -i -X GET http://localhost:8080/pa165/rest/ability/all/true
curl -i -X GET http://localhost:8080/pa165/rest/ability/all/true?searchFilter=a
```

### Get ability

```
/pa165/rest/ability/{id}
```

**Example**

```
curl -i -X GET http://localhost:8080/pa165/rest/ability/1
```

### Create ability

```
/pa165/rest/ability/create
```

**Example**

```
curl -i -X POST -H "Content-Type: application/json" --data '{"name":"Ability name","info":"Ability info"}' http://localhost:8080/pa165/rest/ability/create
```

### Update ability

```
/pa165/rest/ability/edit
```

**Example**

```
curl -i -X PUT -H "Content-Type: application/json" --data '{ "id": 1, "name":"New ability name", "info":"New ability info", "image": "null" }' http://localhost:8080/pa165/rest/ability/edit
```

### Set ability visibility

```
/pa165/rest/ability/visible/{id}/{visible}
```

**Example**

```
curl -i -X GET http://localhost:8080/pa165/rest/ability/visible/1/false
```

### Add spook to ability

```
/pa165/rest/ability/addSpook/{abilityId}/{spookId}
```

**Example**

```
curl -i -X GET http://localhost:8080/pa165/rest/ability/addSpook/1/2
```

### Remove spook from ability

```
/pa165/rest/ability/removeSpook/{abilityId}/{spookId}
```

**Example**

```
curl -i -X GET http://localhost:8080/pa165/rest/ability/removeSpook/1/2
```
