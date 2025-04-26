package org.arash;

import org.bson.Document;
import org.bson.types.ObjectId;

public class CreateBsonDocument {

    public static Document createDocument()
    {
        Document inspection = new Document("_id" , new ObjectId())
                .append("business_id", "10021-2015-ENFO")
                .append("result", "no violation");

        return inspection;
    }

}
