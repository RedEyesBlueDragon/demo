  import logo from './logo.svg';
  import './App.css';
  import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
  import 'react-tabs/style/react-tabs.css';
  import React, { useState, useEffect } from 'react';
  import SlidingPane from 'react-sliding-pane';
import 'react-sliding-pane/dist/react-sliding-pane.css'; 

  function App() {


    const [locationId, setLocationId] = useState('');
    const [city, setCity] = useState('');
    const [country, setCountry] = useState('');
    const [locationCode, setLocationCode] = useState('');
    const [name, setName] = useState('');

    const [deleteLocationId, setDeleteLocationId] = useState('');


    const [message, setMessage] = useState('');
    const [message2, setMessage2] = useState('');

    const addLocation = async () => {
      if (city && country && locationCode && name) {
          const data = {
            location: {
              city: city,
              country: country,
              id: locationId,
              locationCode: locationCode,
              name: name
            },
          };
        try {
          const response = await fetch('http://localhost:8080/api/placementService/addOrUpdateLocation', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
          });
          if (response.ok) {
            setMessage('Success!'); 
            fetchLocations();
          } else {
            setMessage('Invalid Input.');
          }
        } catch (error) {
          setMessage(`Error: ${error.message}`);
        }
      } else {
        setMessage('Please fill in both text fields!');
      }
    };


    const deleteLocation = async () => {
      if (deleteLocationId ) {
        try {
            const response = await fetch(`http://localhost:8080/api/placementService/deleteLocation?locationId=${deleteLocationId}`, {
            method: 'DELETE',
          });
          if (response.ok) {
            setMessage2('Success!'); 
            fetchLocations();
          } else {
            setMessage2('Invalid Input.');
          }
        } catch (error) {
          setMessage2(`Error: ${error.message}`);
        }
      } else {
        setMessage2('Please fill in both text fields!');
      }
    };


    const [locations, setLocations] = useState([]);
    const [transportations, setTransportations] = useState([]);
    const [routes, setRoutes] = useState([]);
    const [selectedRoute, setSelectedRoute] = useState([]);



    const fetchLocations = async () => {
          const response = await fetch('http://localhost:8080/api/placementService/getAllLocations');
          const data = await response.json();
          setLocations(data.locations);
        };

    const fetchTransportations = async () => {
          const response = await fetch('http://localhost:8080/api/placementService/getAllTransportations');
          const data = await response.json();
          setTransportations(data.transportations);
        };  
         

    useEffect(() => {
      fetchLocations();
      fetchTransportations();
      }, 
    []);




    const [transportationId, setTransportationId] = useState('');
    const [destination, setDestination] = useState('');
    const [origin, setOrigin] = useState('');
    const [transportationType, setTransportationType] = useState('');

    const [deleteTransportationId, setDeleteTransportationId] = useState('');


    const [addTransportationMessage, setaddTransportationMessage] = useState('');
    const [deleteTransportationMessage, setDeleteTransportationMessage] = useState('');

    const addTransportation = async () => {
      if (destination && origin && transportationType) {
          const data = {
              id: transportationId,
              destination: destination,
              origin: origin,
              transportationType: transportationType
          };
        try {
          const response = await fetch('http://localhost:8080/api/placementService/addOrUpdateTransportation', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
          });
          if (response.ok) {
            setaddTransportationMessage('Success!'); 
            fetchTransportations();
          } else {
            setaddTransportationMessage('Invalid Input.');
          }
        } catch (error) {
          setaddTransportationMessage(`Error: ${error.message}`);
        }
      } else {
        setaddTransportationMessage('Please fill in both text fields!');
      }
    };


    const deleteTransportation = async () => {
      if (deleteTransportationId ) {
        try {
            const response = await fetch(`http://localhost:8080/api/placementService/deleteTransportation?transportationId=${deleteTransportationId}`, {
            method: 'DELETE',
          });
          if (response.ok) {
            setDeleteTransportationMessage('Success!'); 
            fetchTransportations();
          } else {
            setDeleteTransportationMessage('Invalid Input.');
          }
        } catch (error) {
          setDeleteTransportationMessage(`Error: ${error.message}`);
        }
      } else {
        setDeleteTransportationMessage('Please fill in both text fields!');
      }
    };





  const [selectedOrigin, setSelectedOrigin] = useState('');
  const [selectedDestination, setSelectedDestination] = useState('');

  const handleOrigin = (event) => {
    setSelectedOrigin(event.target.value);
  };

  const handleDestination = (event) => {
    setSelectedDestination(event.target.value);
  };

  const selectRoute = (route)  => {
    setSelectedRoute(route);  
    setIsOpen(true);

  };


  const [messageRoutes, setMessageRoutes] = useState('');

    const fetchRoutes = async () => {
      if (selectedOrigin && selectedDestination ) {
        try {
            const response = await fetch(`http://localhost:8080/api/routes/allRoutes?endLocation=${selectedDestination}&startLocation=${selectedOrigin}`, {
            method: 'GET',
          });
          if (response.ok) {
            const data = await response.json();
            setRoutes(data.routes);
          } else {
            setMessageRoutes('Invalid Input.');
          }
        } catch (error) {
          setMessageRoutes(`Error: ${error.message}`);
        }
      } else {
        setMessageRoutes('Please Select!');
      }

    };     

  const [isOpen, setIsOpen] = useState(false);

    return (
      <div style={{ display: 'flex', marginLeft: '20px' }}>
        <Tabs>
          <TabList>
            <Tab>Locations</Tab>
            <Tab>Transportations</Tab>
            <Tab>Routes</Tab>
          </TabList>

          <TabPanel>
            <div>

              <h2>Location Services</h2>
                <h4>Add Update Location</h4>
                <div>
                  <input
                    type="text"
                    value={locationId}
                    onChange={(e) => setLocationId(e.target.value)}
                    placeholder="Id For Updating"
                  />
                  <input
                    type="text"
                    value={city}
                    onChange={(e) => setCity(e.target.value)}
                    placeholder="City"
                  />
                  <input
                    type="text"
                    value={country}
                    onChange={(e) => setCountry(e.target.value)}
                    placeholder="Country"
                  />
                  <input
                    type="text"
                    value={locationCode}
                    onChange={(e) => setLocationCode(e.target.value)}
                    placeholder="Location Code"
                  />
                  <input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder="Name"
                  />
                  <button onClick={addLocation}>Add Location</button>
                  {message && <p>{message}</p>}
                </div>
                <div style={{marginTop: '20px'}}>
                  <input
                    type="text"
                    value={deleteLocationId}
                    onChange={(e) => setDeleteLocationId(e.target.value)}
                    placeholder="Delete By Id"
                  />
                  <button onClick={deleteLocation}>Delete Location</button>
                  {message2 && <p>{message2}</p>}
                </div>

              <h3>Listing</h3>
              <ul>
                <div>
                <div style={{ display: 'flex', fontWeight: 'bold', borderBottom: '2px solid black', paddingBottom: '5px' }}>
                  <span style={{ flex: 0.2, textAlign: 'left' }}>ID</span> |
                  <span style={{ flex: 0.2, textAlign: 'center' }}>Name</span> |
                  <span style={{ flex: 0.2, textAlign: 'center' }}>City</span> |
                  <span style={{ flex: 0.2, textAlign: 'center' }}>Country</span> |
                  <span style={{ flex: 0.2, textAlign: 'center' }}>Code</span> 
                </div>
                  {locations.map((location, index) => (
                    <div key={index} style={{ display: 'flex', marginBottom: '10px', borderBottom: '1px solid #ccc' }}>
                      <span style={{ flex: 0.2, textAlign: 'left' }}>{location.id}</span>
                      <span> | </span>
                      <span style={{ flex: 0.2, textAlign: 'center' }}>{location.name}</span>
                      <span> | </span>
                      <span style={{ flex: 0.2, textAlign: 'center' }}>{location.city}</span>
                      <span> | </span>
                      <span style={{ flex: 0.2, textAlign: 'center' }}>{location.country}</span>
                      <span> | </span>
                      <span style={{ flex: 0.2, textAlign: 'center' }}>{location.locationCode}</span>
                    </div>
                  ))}
                </div>
              </ul>
            </div>  
          </TabPanel>

          <TabPanel>
            <div>


            <h2>Transportations Services</h2>
                <h4>Add Update Transportation</h4>
                <div>
                  <input 
                    type="text"
                    value={transportationId}
                    onChange={(e) => setTransportationId(e.target.value)}
                    placeholder="Id For Updating"
                  />
                  <input
                    type="text"
                    value={origin}
                    onChange={(e) => setOrigin(e.target.value)}
                    placeholder="Origin Code"
                  />
                  <input
                    type="text"
                    value={destination}
                    onChange={(e) => setDestination(e.target.value)}
                    placeholder="Destination Code"
                  />
                                    
                    <select
                      id="location-select"
                      value={transportationType}
                      onChange={(e) => setTransportationType(e.target.value)}>
                      <option value="" disabled>
                        - Select a Transportation -
                      </option>
                      <option value="FLIGHT">FLIGHT</option>
                      <option value="BUS">BUS</option>
                      <option value="SUBWAY">SUBWAY</option>
                      <option value="UBER">UBER</option>

                    </select>

                  <button onClick={addTransportation}>Add Transportation</button>
                  {addTransportationMessage && <p>{addTransportationMessage}</p>}
                </div>

                <div style={{marginTop: '20px'}}>
                  <input
                    type="text"
                    value={deleteTransportationId}
                    onChange={(e) => setDeleteTransportationId(e.target.value)}
                    placeholder="Delete By Id"
                  />
                  <button onClick={deleteTransportation}>Delete Transportation</button>
                  {deleteTransportationMessage && <p>{deleteTransportationMessage}</p>}
                </div>

              
              <h3>Listing</h3>
              <ul>
                <div>
                  <div style={{ display: 'flex', fontWeight: 'bold', borderBottom: '2px solid black', paddingBottom: '5px' }}>
                    <span style={{ flex: 0.2, textAlign: 'left' }}>ID</span> |
                    <span style={{ flex: 0.2, textAlign: 'center' }}>Origin Code</span> |
                    <span style={{ flex: 0.2, textAlign: 'center' }}>Destination Code</span> |
                    <span style={{ flex: 0.2, textAlign: 'center' }}>Transportation Type</span> 
                  </div>
                  {transportations.map((transportation, index) => (
                    <div key={index} style={{ display: 'flex', marginBottom: '10px', borderBottom: '1px solid #ccc' }}>
                      <span style={{ flex: 0.2, textAlign: 'left' }}>{transportation.id}</span>
                      <span> | </span>
                      <span style={{ flex: 0.2, textAlign: 'center' }}>{transportation.origin.locationCode}</span>
                      <span> | </span>
                      <span style={{ flex: 0.2, textAlign: 'center' }}>{transportation.destination.locationCode}</span>
                      <span> | </span>
                      <span style={{ flex: 0.2, textAlign: 'center' }}>{transportation.transportationType}</span>
                    </div>
                  ))}
                </div>
              </ul>

            </div>
          </TabPanel>
          
          <TabPanel>
            <h2>Routes</h2>
            <p>Select Origin and Destination Location To See Available Transportations.</p>


            <div>
                    <div>
                      <label><strong>Select Origin:</strong></label>
                      <select value={selectedOrigin} onChange={handleOrigin}>
                        <option value="" disabled>Select Origin Location</option>
                        {locations.map((location) => (
                          <option key={location.id} value={location.id}>
                            {location.locationCode} -- {location.name} ({location.city}, {location.country})
                          </option>
                        ))}
                      </select>

                      <label style={{ paddingLeft: '20px' }}>
                      <strong>Select Destination:</strong></label>
                      <select value={selectedDestination} onChange={handleDestination}>
                        <option value="" disabled>Select Destination Location</option>
                        {locations.filter(s => s.id != selectedOrigin).map((location) => (
                          <option key={location.id} value={location.id}>
                            {location.locationCode} -- {location.name} ({location.city}, {location.country})
                          </option>
                        ))}
                      </select>

                      <button style={{marginLeft: '15px' }} onClick={fetchRoutes}>List Available Routes</button>
                      {messageRoutes && <p>{messageRoutes}</p>}

                    </div>

                    <div>
                      <div>
                        <div style={{ fontWeight: 'bold', borderBottom: '2px solid black', paddingBottom: '5px', paddingTop: '15px' }}>
                          Available Routes
                        </div>
                        {routes.map((route, index) => (
                          <div
                            key={index}
                            style={{
                              display: 'flex',
                              gap: '20px',
                              borderBottom: '1px solid gray',
                              padding: '5px 0',
                              cursor: 'pointer'
                            }}
                            onClick={() => selectRoute(route)}
                          >
                            <span>
                              {route.filter(s => s.transportationType === "FLIGHT").map(s => `--> Via ${s.origin.name} (${s.origin.locationCode})`) }
                            </span>
                          </div>
                        ))}
                      </div>
                    </div>

                     <div>

                      <SlidingPane
                        isOpen={isOpen}
                        from="right"  
                        width="400px"
                        onRequestClose={() => setIsOpen(false)} 
                      >
                        <h2>Selected Route</h2>
                        <ul>
                          {selectedRoute.map((route, index) => {
                          const showOrigin = index === 0;
                          
                          return (
                            <div key={route.id} style={{ display: 'flex',flexDirection: "column" }}>
                              {showOrigin && <span style={{ flex: 0.2, textAlign: 'center' }}>{route.origin.name}</span>}
                              <span style={{ flex: 0.2, textAlign: 'center' }}>|</span>
                              <span style={{ flex: 0.2, textAlign: 'center' }}>|</span>
                              <span style={{ flex: 0.2, textAlign: 'center' }}>({route.transportationType})</span>
                              <span style={{ flex: 0.2, textAlign: 'center' }}>|</span>
                              <span style={{ flex: 0.2, textAlign: 'center' }}>|</span>
                              <span style={{ flex: 0.2, textAlign: 'center' }}>{route.destination.name}</span>
                            </div>
                            );
                          })}
                        </ul>
                      </SlidingPane>
                    </div>

            </div>


          </TabPanel>
        </Tabs>
      </div>
    );
  }

  export default App;
