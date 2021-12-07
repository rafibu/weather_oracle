# weather_oracle
Author: Rafael Burkhalter, University of Bern

## Description
Project for the Seminar Cryptography and Data Security 2021.
The idea is to create a Proxy which gathers the current temperature of a specified PLZ (Postleitzahl) or City.
The main goal is to use it as an example for Oracles in Solidty Smart Contracts in the Ethereum Virtual Machine.

## Usage
On the main page we can see the different data sources and if they are still currently active (when the server is running they are checked periodically every 5 minutes).
We can also send a request with a specified PLZ or City to all sources. The responses are then listed underneath.

An API gives the current tempereature as a JSON file through the following GET Request
<pre>
/weather?plz={plz}
</pre>
