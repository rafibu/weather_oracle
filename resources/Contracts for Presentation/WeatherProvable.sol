pragma solidity >= 0.5.0 < 0.6.0;

import "github.com/provable-things/ethereum-api/provableAPI_0.5.sol";

contract WeatherSwitzerland is usingProvable {

    string public temperature;
    string public zip;

    event LogNewWeather(string temperature, string plz);
    event LogNewProvableQuery(string description);

    constructor()
        public
    {
        
    }

    function __callback(
        bytes32 _myid,
        string memory _result
    )
        public
    {
        require(msg.sender == provable_cbAddress());
        emit LogNewWeather(_result, zip);
        temperature = _result;
    }

    function update(string memory plz)
        public
       payable
    {
        zip = plz;
        string memory query = strConcat(strConcat("json(https://weatherswitzerland.herokuapp.com/weather?plz=", plz), ").temperature");
        emit LogNewProvableQuery("Provable query was sent, standing by for the answer...");
        provable_query("URL", query);
    }
}