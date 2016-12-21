Feature: Test api for Project

@smoke
Scenario: Test Api for creating project
Given make project call with input body and post method api
Then Validate Status for project code
And Validate Response Body for project