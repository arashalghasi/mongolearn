db.help()
This command will return a list of commonly used commands and a brief description of each one

Organizaitons:
Group and define users and teams
Grant access to projects 
Projects:
Define and organize resources
Create separate projects for development, testing, and production


In mongodb the data will be stored in json files however in traditional relational database the data will be saved in tables
Document: the basic unit of data in mangodb
Collection: a group of documents
Database: a container for collections
the mongodb database is at the core of the atlas
Mongodb is a general purpose document database

This command will return a list of commonly used commands and a brief description of each one: db.help()
Documents and datatypes that we can store in the mongodb and it has flexible schema model
Mongodb store the document with a structure like json object, the data is displayed in jSON and stored in BSON (Added support data types unavailable in JSON)
json is completely language independent.
Bson is an extentions of json to support more datatype BSON, short for Bin­ary JSON, is a bin­ary-en­coded seri­al­iz­a­tion of JSON-like doc­u­ments. is primarily used internally by MongoDB for efficient storage and data traversal.

a bson file 
{"hello": "world"} →
\x16\x00\x00\x00           // total document size
\x02                       // 0x02 = type String
hello\x00                  // field name
\x06\x00\x00\x00world\x00  // field value
\x00                       // 0x00 = type EOO ('end of object')
 
{"BSON": ["awesome", 5.05, 1986]} →
\x31\x00\x00\x00
 \x04BSON\x00
 \x26\x00\x00\x00
 \x02\x30\x00\x08\x00\x00\x00awesome\x00
 \x01\x31\x00\x33\x33\x33\x33\x33\x33\x14\x40
 \x10\x32\x00\xc2\x07\x00\x00
 \x00
 \x00


mongodb driver : one of the use is to convert json data to bson and viceversa
MongoDB uses BSON to offer powerful indexing and querying features on top of the web's most popular data format.

One particular way in which BSON differs from JSON is in its support for some more advanced types of data. JSON does not, for instance, differentiate between integers (which are round numbers) and floating-point numbers (which have decimal precision to various degrees).

ObjectId is a special datatype that mongodb use to create unique identifier
Collections can have different documents (polymorphic documents) and every document may contain different fields and fields may contain different types

Atlas data explorer
interact and manage data from atlas UI
we use data explorer 
to create an view databases
collections
documents

Data modelling: the process of defining how data is stored and the relationships that exist among different entities in your data.
we refer the organization of data into the database as the schema.
Data that mean to be access together needs to be stored together.
embedded documents model enables us to build complex relationships among data.
The two priamry ways of modelling data relationships in mongoDB are emebedding and referencing.

Embedding: avoid application joins, Provides better performance for read operations. Allows developers to update related data in a single write operations.
Used when you have one to many or many to many relationships in the data that's being stored
Embedded documents also called nested docuemtns.
embedding can be used for every type of data relationships.

warnings of using embedded documents:
1- over time can make huge documents (latency for reads and excessive memory occupation) large documents have to be read into memory in full, which can result in a slow application performance for your end user.
2- Continously adding data without limit creates unbounded documents. Unbound docuemtns may exceed the BSON documents threshold of 16 MB. Both large documents and unbounded documents are anti patterns which should be avoided.

Refrences:
Save the _id field of one documents in another document as a link between the two. simple and sufficient for most use cases.
Using references is called linking or data normalization.

Refrencing: Querying from multiple documents costs extra resourses and impacts read performance.
1- No duplicaton of data.
2- Smaller documetns.

Unbounded documents: are documents that grow infinitely.

Scema anti patterns: are guidlines that help developers plan, organize, and model data.
Most anit patterns used: massive arrays, Massive number of collections, Bloated documents, Unnecessary indexes, queries without indexes, data that's accessed together but stored in different collections.

Data explorer and performance adviser can help us to find the antipatterns.


Data in MongoDB has a flexible schema model, which means:

Documents within a single collection are not required to have the same set of fields.
A field's data type can differ between documents within a collection.

Indexing
To improve performance for queries that your application runs frequently, create indexes on commonly queried fields. As your application grows, monitor your deployment's index use to ensure that your indexes are still supporting relevant queries.
If an appropriate index exists for a query, MongoDB uses the index to limit the number of documents it must scan.
Although indexes improve query performance, adding an index has negative performance impact for write operations.
For collections with a high write-to-read ratio, indexes are expensive because each insert must also update any indexes.

the mongodb connection string allows to connect to the cluster and work with our data.
can be used in two formato: standard format and DNS seed list format.
DNS seel list format: provides a DNS server list to our connection string. Gives mroe flexibility of depolyment. ability to change servers in rotation without reconfiguring clients.

Mongodb String: mongodb+srv://arash:<db_password>@cluster0.00lnwre.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0
+srv : sets the TLS security option to true and tells MongoDB to use the DNS seed list.
@cluster0.00lnwre.mongodb.net/ : host and optional number to our database.
?retryWrites=true&w=majority&appName=Cluster0 : contian connection timeout, TLS and SSL, Connection pooling and read and write concerns.
retryWrites=true : let the Mongodb retries when certain types of write operations fail.

after mongodb shell installation to find out the version of mongodb
mongosh --version o mongo --version

to connect to the mongodb atlas in shell
we need to paste the dns mongo string connection in the command line
mongosh "mongodb+srv://cluster0.00lnwre.mongodb.net/" --apiVersion 1 --username arash

the response after connection:
C:\Users\arash>mongosh "mongodb+srv://cluster0.00lnwre.mongodb.net/" --apiVersion 1 --username arash
Enter password: *****
Current Mongosh Log ID: 68069886e8620e7e90b5f898
Connecting to:          mongodb+srv://<credentials>@cluster0.00lnwre.mongodb.net/?appName=mongosh+2.5.0
Using MongoDB:          8.0.8 (API Version 1)
Using Mongosh:          2.5.0

For mongosh info see: https://www.mongodb.com/docs/mongodb-shell/


To help improve our products, anonymous usage data is collected and sent to MongoDB periodically (https://www.mongodb.com/legal/privacy-policy).
You can opt-out by running the disableTelemetry() command.

the mongodb shell is a node.js REPL environment : this gives us access to javaScript variables, functions, conditionals, loops, and control flow.
command will output a document that describes the role of the mongodb instance that you have connected to by using the mongosh: db.hello()


SCRAM
Salted Challenge Response Authentication Mechanism (SCRAM) is the default authentication mechanism for MongoDB.
When a user authenticates themselves, MongoDB uses SCRAM to verify the supplied user credentials against the user's name, password and authentication database.

Mongodb compass is a graphical user interface or GUI, that allows to query and analyze our data, and compose aggregation pipelines.

Mongodb drivers allow to connect our application to our database by using a connection string.

An application should use a single MongoCLient instance for all database requests.
Instantiating a MongoClient instance is resource intensive.

By default mongo db listen on port 27017

Connection string must be a valid uri
if the connection string contain embedded credentials that use any of the characters !*'();:@&=+$,/?%#[]  we must precent encode the credential characters
like p@ssw0rd'9'! -> p%40ssw0rd%279%27%21

Insert a document
db.grades.insertOne(
{
student_id : 4762847,
scores : [
{
type: "quiz",
score: 50,
},
{
type: "homework",
score: 70,
}
]
}
)

db.<collection>.insertMany(
[
<document1>,
<document2>,
<document3>,
]
)

every document must have a unique _id field and if we do not provide an id the mongodb automatically assign
an id to the document.

to find the records in a specific database we can use 
db.<collection>.find()

example: db.zips.find({ _id: ObjectId("5c8eccc1caa187d17ca6ed16") })

to change between the databases we can use the use keyword
use <database name>

when we want to retrieve a specific document from our collection
1- {field: {$eq: <value>}}
2- {field: <value>}

$in operator allows us to select all documents that have a field value equal to any of the values specified in tha array

example: db.zips.find({ city: { $in: ["PHOENIX", "CHICAGO"] } })

db.<collection>.find(
{
<field>: {
$in : [<value>,<value>, ...]
}
)


Find

Per select a record on a collection sales -> db.sales.find({ _id: ObjectId("5bd761dcae323e45a93ccff4")})
For finding some record db.sales.find({ storeLocation: { $in: ["London", "New York"] } })

the mongodb operators:
$gt greater than
$lt less than
$lte less than equal
$gte greater than equal

$gt
Use the $gt operator to match documents with a field greater than the given value. For example:

db.sales.find({ "items.price": { $gt: 50}})
$lt
Use the $lt operator to match documents with a field less than the given value. For example:

db.sales.find({ "items.price": { $lt: 50}})
$lte
Use the $lte operator to match documents with a field less than or equal to the given value. For example:

db.sales.find({ "customer.age": { $lte: 65}})
$gte
Use the $gte operator to match documents with a field greater than or equal to the given value. For example:

db.sales.find({ "customer.age": { $gte: 65}})

Find Documents with an Array That Contains a Specified Value
In the following example, "InvestmentFund" is not enclosed in square brackets, so MongoDB returns all documents within the products array that contain the specified value.

db.accounts.find({ products: "InvestmentFund"})
Find a Document by Using the $elemMatch Operator
Use the $elemMatch operator to find all documents that contain the specified subdocument. For example:

db.sales.find({
  items: {
    $elemMatch: { name: "laptop", price: { $gt: 800 }, quantity: { $gte: 1 } },
  },
})

Logical operators in mongodb

Find a Document by Using Implicit $and
Use implicit $and to select documents that match multiple expressions. For example:

db.routes.find({ "airline.name": "Southwest Airlines", stops: { $gte: 1 } })
Find a Document by Using the $or Operator
Use the $or operator to select documents that match at least one of the included expressions. For example:

db.routes.find({
  $or: [{ dst_airport: "SEA" }, { src_airport: "SEA" }],
})
Find a Document by Using the $and Operator
Use the $and operator to use multiple $or expressions in your query.

db.routes.find({
  $and: [
    { $or: [{ dst_airport: "SEA" }, { src_airport: "SEA" }] },
    { $or: [{ "airline.name": "American Airlines" }, { airplane: 320 }] },
  ]
})


Docker and mongodb
MongoDB can run in a container.

docker run --name mongodb -d -p 27017:27017 mongodb/mongodb-community-server:6.0-ubi8
docker run --name mongodb -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=user -e MONGO_INITDB_ROOT_PASSWORD=pass -v $(pwd)/data:/data/db mongodb/mongodb-community-server:6.0-ubi8

If your application is running inside a container itself, you can run MongoDB as part of the same Docker network as your application using --network. With this method, you will connect to MongoDB on mongodb://mongodb:27017 from the other containerized applications in the network.
docker run --name mongodb -d --network mongodb mongodb/mongodb-community-server:6.0-ubi8


for docker-compose.yaml file

version: '3.8'

services:
  mongodb:
    image: mongodb/mongodb-community-server:6.0-ubi8
    container_name: mongodb
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_ROOT_USERNAME: user
      MONGO_INITDB_ROOT_PASSWORD: pass
    volumes:
      - ./data:/data/db
    restart: unless-stopped



Mongo db replace a document in the collection

db.books.replaceOne(
  {
    _id: ObjectId("6282afeb441a74a98dbbec4e"),
  },
  {
    title: "Data Science Fundamentals for Python and MongoDB",
    isbn: "1484235967",
    publishedDate: new Date("2018-5-10"),
    thumbnailUrl:
      "https://m.media-amazon.com/images/I/71opmUBc2wL._AC_UY218_.jpg",
    authors: ["David Paper"],
    categories: ["Data Science"],
  }
)

Mongo db Update a document in the collection

The updateOne() method accepts a filter document, an update document, and an optional options object. MongoDB provides update operators and options to help you update documents. In this section, we'll cover three of them: $set, upsert, and $push.

$set
The $set operator replaces the value of a field with the specified value, as shown in the following code:

db.podcasts.updateOne(
  {
    _id: ObjectId("5e8f8f8f8f8f8f8f8f8f8f8"),
  },

  {
    $set: {
      subscribers: 98562,
    },
  }
)
upsert
The upsert option creates a new document if no documents match the filtered criteria. Here's an example:

db.podcasts.updateOne(
  { title: "The Developer Hub" },
  { $set: { topics: ["databases", "MongoDB"] } },
  { upsert: true }
)
$push
The $push operator adds a new value to the hosts array field. Here's an example:

db.podcasts.updateOne(
  { _id: ObjectId("5e8f8f8f8f8f8f8f8f8f8f8") },
  { $push: { hosts: "Nic Raboy" } }
)


findAndModify()
return the document that has been updted

Updating MongoDB Documents by Using findAndModify()
The findAndModify() method is used to find and replace a single document in MongoDB. It accepts a filter document, a replacement document, and an optional options object. The following code shows an example:

db.podcasts.findAndModify({
  query: { _id: ObjectId("6261a92dfee1ff300dc80bf1") },
  update: { $inc: { subscribers: 1 } },
  new: true,
})



Updating MongoDB Documents by Using updateMany()
To update multiple documents, use the updateMany() method. This method accepts a filter document, an update document, and an optional options object. The following code shows an example:

db.books.updateMany(
  { publishedDate: { $lt: new Date("2019-01-01") } },
  { $set: { status: "LEGACY" } }
)

Deleting Documents in MongoDB
To delete documents, use the deleteOne() or deleteMany() methods. Both methods accept a filter document and an options object.

Delete One Document
The following code shows an example of the deleteOne() method:

db.podcasts.deleteOne({ _id: Objectid("6282c9862acb966e76bbf20a") })
Delete Many Documents
The following code shows an example of the deleteMany() method:

db.podcasts.deleteMany({category: “crime”})

Cursor:
A pointer to result set of a query - find() returns a cursor


Sorting and Limiting Query Results in MongoDB
Review the following code, which demonstrates how to sort and limit query results.

Sorting Results
Use cursor.sort() to return query results in a specified order. Within the parentheses of sort(), include an object that specifies the field(s) to sort by and the order of the sort. Use 1 for ascending order, and -1 for descending order.

Syntax:

db.collection.find(<query>).sort(<sort>)
Example:

// Return data on all music companies, sorted alphabetically from A to Z.
db.companies.find({ category_code: "music" }).sort({ name: 1 });
To ensure documents are returned in a consistent order, include a field that contains unique values in the sort. An easy way to do this is to include the _id field in the sort. Here's an example:

// Return data on all music companies, sorted alphabetically from A to Z. Ensure consistent sort order
db.companies.find({ category_code: "music" }).sort({ name: 1, _id: 1 });
Limiting Results
Use cursor.limit() to specify the maximum number of documents the cursor will return. Within the parentheses of limit(), specify the maximum number of documents to return.

Syntax:

db.companies.find(<query>).limit(<number>)
Example:

// Return the three music companies with the highest number of employees. Ensure consistent sort order.
db.companies
  .find({ category_code: "music" })
  .sort({ number_of_employees: -1, _id: 1 })
  .limit(3);


Returning Specific Data from a Query in MongoDB
Review the following code, which demonstrates how to return selected fields from a query.

Add a Projection Document
To specify fields to include or exclude in the result set, add a projection document as the second parameter in the call to db.collection.find().

Syntax:

db.collection.find( <query>, <projection> )
Include a Field
To include a field, set its value to 1 in the projection document.

Syntax:

db.collection.find( <query>, { <field> : 1 })
Example:

// Return all restaurant inspections - business name, result, and _id fields only
db.inspections.find(
  { sector: "Restaurant - 818" },
  { business_name: 1, result: 1 }
)
Exclude a Field
To exclude a field, set its value to 0 in the projection document.

Syntax:

db.collection.find(query, { <field> : 0, <field>: 0 })
Example:

// Return all inspections with result of "Pass" or "Warning" - exclude date and zip code
db.inspections.find(
  { result: { $in: ["Pass", "Warning"] } },
  { date: 0, "address.zip": 0 }
)
While the _id field is included by default, it can be suppressed by setting its value to 0 in any projection.

// Return all restaurant inspections - business name and result fields only
db.inspections.find(
  { sector: "Restaurant - 818" },
  { business_name: 1, result: 1, _id: 0 }
)

Counting Documents in a MongoDB Collection
Review the following code, which demonstrates how to count the number of documents that match a query.

Count Documents
Use db.collection.countDocuments() to count the number of documents that match a query. countDocuments() takes two parameters: a query document and an options document.

Syntax:

db.collection.countDocuments( <query>, <options> )
The query selects the documents to be counted.

Examples:

// Count number of docs in trip collection
db.trips.countDocuments({})
// Count number of trips over 120 minutes by subscribers
db.trips.countDocuments({ tripduration: { $gt: 120 }, usertype: "Subscriber" })


