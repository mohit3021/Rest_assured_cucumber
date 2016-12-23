@hello
Feature: Test api for Token

@smoke
Scenario: Test Token API with Credentials
      Given Making api token request with post method API
      Then Validate Status code
      And Validate Response Body
      And Save JWT token in a variable



    
