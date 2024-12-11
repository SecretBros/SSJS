<script  runat="server" language="Javascript">
Platform.Load("Core","1");


try{
    var contentType="dataextension"
  /**====================================== PART 1 ======================================== */
  /** Start 1.This line tell us that we are trying to fetch the root folder */
    var parentFolderCategoryID = RetriveFolderCategoryID("Data Extensions",contentType);
    Write(parentFolderCategoryID);

  /** End.This line tell us that we are trying to fetch the root folder */

/**===================================== PART 2 =============================================== */
    /**Name of new Folder */
  var newFolderName="SSJS Learning Session 11";

 /**Checking if the folder already exist or not */
  var childFolderID=RetriveFolderCategoryID(newFolderName,contentType);

 /** Create a new folder if it does not already exist */
 if(!childFolderID){
  //var newFolderStatus=CreateFolder(newFolderName,contentType,parentFolderCategoryID);
  childFolderID=RetriveFolderCategoryID(newFolderName,contentType);
}
/**====================================== PART 3 ===================================================== */
 var deSearchName="SignUpDataExtension";
 var deName = SearchDataExtension(deSearchName);  
;

/***=========================================  PART 4 ==================================================== */
if(!deName){
    var newDE=SignUpDataExtension(childFolderID,deSearchName);
    }
/**=============================================PART 5============================================= */

var checkRecords=AddRecordInSignUpDataExtension(deSearchName);
Write(Stringify(checkRecords));


}
catch(ex){
 // Handle any exceptions and output the error details
    Write("Error Message: " + ex.message + '\n');
    Write("Error Description: " + ex.description + '\n');
}

/** Step1 Function to  Retrive the root folder */

function RetriveFolderCategoryID(name,ContentType){

   var filter1={
      Property: "Name",
      SimpleOperator: "equals",
      Value: name
    }
  
  var filter2={
      Property: "ContentType",
      SimpleOperator: "equals",
      Value: ContentType
    }

  var complexFilter = {
         LeftOperand: filter1,
        LogicalOperator: "AND",
        RightOperand: filter2
 }

    var result=Folder.Retrieve(complexFilter);
    return result[0].ID;

}



function CreateFolder(newFolderName,contentType,parentFolderCategoryID){
    var newFolder = {
      "Name" : newFolderName,
      "CustomerKey" : newFolderName,
      "Description" : "Test added",
      "ContentType" : contentType,
      "IsActive" : "true",
      "IsEditable" : "true",
      "AllowChildren" : "true",
      "ParentFolderID" : parentFolderCategoryID // Parent folder ID; adjust as needed
    };

    // Add the new folder
    var status = Folder.Add(newFolder);
    return status;

}


function SearchDataExtension(deName){

var filter={
       Property: "CustomerKey",
      SimpleOperator: "equals",
      Value: deName
    }
    var results = DataExtension.Retrieve(filter);
    return results[0].Name;

}


function SignUpDataExtension(childFolderID,deSearchName) {
    // Define the properties of the Sendable Data Extension to be created
    var deObj = {
      "CustomerKey": deSearchName,
      "Name": deSearchName,
      "CategoryID": childFolderID,
      "Fields": [
        { "Name": "EmailAddress", "FieldType": "EmailAddress" },
        { "Name": "MobileNumber", "FieldType": "Number"}
      ],
      "SendableInfo": {
        "Field":  { "Name": "EmailAddress", "FieldType": "EmailAddress" },
        "RelatesOn": "Subscriber Key"
      },
      "IsTestable": true
    };

    // Create the Sendable Data Extension
    var myDE = DataExtension.Add(deObj);

    // Output the created Sendable Data Extension details
    Write(Stringify(myDE));
}



function AddRecordInSignUpDataExtension(deSearchName){
 
   try {
    // Initialize the Data Extension using the Customer Key
   
    var updateDE = DataExtension.Init(deSearchName);
     // Define an array of contacts to be added
    // Add a row to the Data Extension
    var record = [
  {EmailAddress: "jdoe1@mailinator.com", FirstName: "John"},
  {EmailAddress: "mjones1@mailinator.com", FirstName: "Mike"},
  {EmailAddress: "ajackson1@mailinator.com", FirstName: "Alice"},
  {EmailAddress: "bsmith1@mailinator.com", FirstName: "Bob"},
  {EmailAddress: "csanders1@mailinator.com", FirstName: "Catherine"},
  {EmailAddress: "dlee1@mailinator.com", FirstName: "David"},
  {EmailAddress: "efoster1@mailinator.com", FirstName: "Eve"},
  {EmailAddress: "fgonzalez1@mailinator.com", FirstName: "Frank"},
  {EmailAddress: "hmartin1@mailinator.com", FirstName: "Helen"},
  {EmailAddress: "jclark1@mailinator.com", FirstName: "James"},
  {EmailAddress: "kwhite1@mailinator.com", FirstName: "Kathy"},
  {EmailAddress: "lmoore1@mailinator.com", FirstName: "Liam"},
  {EmailAddress: "nmorris1@mailinator.com", FirstName: "Nancy"},
  {EmailAddress: "operez1@mailinator.com", FirstName: "Oscar"},
  {EmailAddress: "pward1@mailinator.com", FirstName: "Paul"},
  {EmailAddress: "qjohnson1@mailinator.com", FirstName: "Quinn"},
  {EmailAddress: "rsanchez1@mailinator.com", FirstName: "Rachel"},
  {EmailAddress: "tsmith1@mailinator.com", FirstName: "Tom"},
  {EmailAddress: "ugreen1@mailinator.com", FirstName: "Uma"},
  {EmailAddress: "vthomas1@mailinator.com", FirstName: "Victor"},
  {EmailAddress: "wallen1@mailinator.com", FirstName: "Wendy"},
  {EmailAddress: "xallen1@mailinator.com", FirstName: "Xander"},
  {EmailAddress: "ygarcia1@mailinator.com", FirstName: "Yara"},
  {EmailAddress: "zbrown1@mailinator.com", FirstName: "Zach"},
  {EmailAddress: "bjackson2@mailinator.com", FirstName: "Billie"},
  {EmailAddress: "cchavez2@mailinator.com", FirstName: "Clara"},
  {EmailAddress: "djones2@mailinator.com", FirstName: "Diana"},
  {EmailAddress: "fcarter2@mailinator.com", FirstName: "Fiona"},
  {EmailAddress: "gparker2@mailinator.com", FirstName: "Gina"},
  {EmailAddress: "hscott2@mailinator.com", FirstName: "Henry"},
  {EmailAddress: "ikarlsen2@mailinator.com", FirstName: "Isabel"},
  {EmailAddress: "jroberts2@mailinator.com", FirstName: "Jack"},
  {EmailAddress: "lking2@mailinator.com", FirstName: "Laura"},
  {EmailAddress: "mnguyen2@mailinator.com", FirstName: "Ming"},
  {EmailAddress: "npatel2@mailinator.com", FirstName: "Nina"},
  {EmailAddress: "oqiu2@mailinator.com", FirstName: "Oscar"},
  {EmailAddress: "rchan2@mailinator.com", FirstName: "Rita"},
  {EmailAddress: "shernandez2@mailinator.com", FirstName: "Sophie"},
  {EmailAddress: "tkim2@mailinator.com", FirstName: "Tina"},
  {EmailAddress: "ulima2@mailinator.com", FirstName: "Ursula"},
  {EmailAddress: "vramirez2@mailinator.com", FirstName: "Vera"},
  {EmailAddress: "wpaul2@mailinator.com", FirstName: "Warren"},
  {EmailAddress: "xjones2@mailinator.com", FirstName: "Ximena"},
  {EmailAddress: "ymurphy2@mailinator.com", FirstName: "Yasmin"},
  {EmailAddress: "zgarcia2@mailinator.com", FirstName: "Zane"},
  {EmailAddress: "ahenderson3@mailinator.com", FirstName: "Amelia"},
  {EmailAddress: "bjames3@mailinator.com", FirstName: "Brad"},
  {EmailAddress: "ctaylor3@mailinator.com", FirstName: "Charlie"},
  {EmailAddress: "dsimmons3@mailinator.com", FirstName: "Daniel"},
  {EmailAddress: "earnold3@mailinator.com", FirstName: "Elena"},
  {EmailAddress: "fwalker3@mailinator.com", FirstName: "Felix"},
  {EmailAddress: "ggomez3@mailinator.com", FirstName: "Grace"},
  {EmailAddress: "hlewis3@mailinator.com", FirstName: "Helen"},
  {EmailAddress: "ijsr3@mailinator.com", FirstName: "Ian"},
  {EmailAddress: "jwalker3@mailinator.com", FirstName: "Jake"},
  {EmailAddress: "kjohnson3@mailinator.com", FirstName: "Kira"},
  {EmailAddress: "ldavis3@mailinator.com", FirstName: "Leo"},
  {EmailAddress: "mgarcia3@mailinator.com", FirstName: "Maya"},
  {EmailAddress: "njackson3@mailinator.com", FirstName: "Nina"},
  {EmailAddress: "olawrence3@mailinator.com", FirstName: "Oliver"},
  {EmailAddress: "pphillips3@mailinator.com", FirstName: "Paul"},
  {EmailAddress: "qwest3@mailinator.com", FirstName: "Quincy"},
  {EmailAddress: "rward3@mailinator.com", FirstName: "Ravi"},
  {EmailAddress: "sgarcia3@mailinator.com", FirstName: "Sasha"},
  {EmailAddress: "tharris3@mailinator.com", FirstName: "Travis"},
  {EmailAddress: "uhunt3@mailinator.com", FirstName: "Ulysses"},
  {EmailAddress: "vwilson3@mailinator.com", FirstName: "Vicky"},
  {EmailAddress: "xjames3@mailinator.com", FirstName: "Xander"},
  {EmailAddress: "ylopez3@mailinator.com", FirstName: "Yasmine"},
  {EmailAddress: "zellis3@mailinator.com", FirstName: "Zoe"},
  {EmailAddress: "abrown4@mailinator.com", FirstName: "Aaron"},
  {EmailAddress: "bsmith4@mailinator.com", FirstName: "Bella"},
  {EmailAddress: "cstevens4@mailinator.com", FirstName: "Chris"},
  {EmailAddress: "dwilson4@mailinator.com", FirstName: "Dylan"},
  {EmailAddress: "ecameron4@mailinator.com", FirstName: "Eva"},
  {EmailAddress: "flong4@mailinator.com", FirstName: "Finn"},
  {EmailAddress: "gmartin4@mailinator.com", FirstName: "Gabriel"},
  {EmailAddress: "htravis4@mailinator.com", FirstName: "Holly"},
  {EmailAddress: "iwest4@mailinator.com", FirstName: "Ivy"},
  {EmailAddress: "jporter4@mailinator.com", FirstName: "Jordan"},
  {EmailAddress: "lkelly4@mailinator.com", FirstName: "Liam"},
  {EmailAddress: "mfranklin4@mailinator.com", FirstName: "Maggie"},
  {EmailAddress: "npatterson4@mailinator.com", FirstName: "Nash"},
  {EmailAddress: "oqi4@mailinator.com", FirstName: "Olivia"},
  {EmailAddress: "rclark4@mailinator.com", FirstName: "Rhea"},
  {EmailAddress: "sjohnson4@mailinator.com", FirstName: "Simon"},
  {EmailAddress: "tkerr4@mailinator.com", FirstName: "Tara"},
  {EmailAddress: "ulane4@mailinator.com", FirstName: "Ursula"},
  {EmailAddress: "vbowen4@mailinator.com", FirstName: "Vince"},
  {EmailAddress: "whernandez4@mailinator.com", FirstName: "Wendy"},
  {EmailAddress: "xhill4@mailinator.com", FirstName: "Xena"},
  {EmailAddress: "ybrown4@mailinator.com", FirstName: "Yannick"},
  {EmailAddress: "zmartin4@mailinator.com", FirstName: "Zane"}
]

       
    var status = updateDE.Rows.Add(record);
    return status;

    // Check and display the result
    if (status) {
      Write("Add Rows Status: Success\n");
    } else {
      Write("Add Rows Status: Failed. Ensure field names match and primary keys are provided.\n");
    }
  } catch (ex) {
    // Handle errors
    Write("Error Message: " + ex.message + '\n');
    Wri}te("Error Description: " + ex.description + '\n')
}
</script>