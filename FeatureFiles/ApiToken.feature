Feature: Test api for Token

Scenario: Test Token API with Credentials
      Given Making api token request with post method API
      Then Validate Status code
      And Validate Response Body
      And Save JWT token in a variable
      
      
      
Scenario: Test Api for creating project
Given make project call with input body and post method api
Then Validate Status for project code
And Validate Response Body for project


    
