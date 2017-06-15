# LeagueTable

A spring servie that provides endpoints for fetching a soccer league's table based on results coming from OpenLigaDB. 

## Endpoints:

### /api/leagueTable/bl1
*Returns the current table of league with identifier "bl1".*

## Starting application:

### './gradlew bootRun'
*Starts the application.*

## Gradle testing tasks:

### './gradlew runUnitTests'
*Runs all Unit tests.*

### './gradlew runIntegrationTests'
*Runs all Integration tests.*

### './gradlew. runFeatureTests'
*Runs all cucumber scenarios.*

## Gradle report tasks:

### './gradlew unitTestReport'
*Creates Jacoco unit test report after a unit test run.*

### './gradlew integrationTestReport'
*Creates Jacoco integration test report after an integration test run.*

### './gradlew unitAndITcoverageReport'
*Creates Jacoco report about coverage of all unit and integration tests. A unit and integration test run is necessary for that.*

### './gradlew aggregate'
*Creates a SerenityBDD report for feature overview. A feature test run is necessary for that.*
